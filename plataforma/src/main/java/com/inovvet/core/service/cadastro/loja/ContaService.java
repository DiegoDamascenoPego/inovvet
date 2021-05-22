package com.inovvet.core.service.cadastro.loja;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inovvet.app.config.FlywayConfig;
import com.inovvet.app.exception.EnumError;
import com.inovvet.app.exception.ServiceException;
import com.inovvet.app.service.AbstractService;
import com.inovvet.app.service.AccountsService;
import com.inovvet.app.util.FunctionUtil;
import com.inovvet.core.entity.conta.Conta;
import com.inovvet.core.entity.conta.dto.ContaDTO;
import com.inovvet.core.repository.cadastro.loja.ContaRepository;

@Service
public class ContaService extends AbstractService {

	@Autowired
	private ContaRepository repository;

	@Autowired
	private FlywayConfig flywayConfig;

	@Autowired
	private AccountsService accountsService;

	private void validarConta(ContaDTO dto) throws ServiceException {
		if (!FunctionUtil.validarCPF(dto.getCpf())) {
			throw new ServiceException(EnumError.PARAMETROS_INVALIDOS, "O campo CPF não informado");
		}

		if (!FunctionUtil.validarEmail(dto.getEmail())) {
			throw new ServiceException(EnumError.PARAMETROS_INVALIDOS, "O campo Email não informado");
		}

		if (FunctionUtil.isEmpty(dto.getToken())) {
			throw new ServiceException(EnumError.PARAMETROS_INVALIDOS, "O campo Token não informado");
		}

	}

	private void validarNovaConta(ContaDTO dto) throws ServiceException {
		validarConta(dto);
	}

	private Conta salvar(Conta conta) throws ServiceException {
		try {
			return repository.save(conta);
		} catch (Exception e) {
			throw new ServiceException(EnumError.FALHA_SALVAR_REGISTRO);
		}
	}

	@Transactional
	public void salvar(ContaDTO dto) throws ServiceException {

		validarNovaConta(dto);

		Conta conta = repository.findByCpfAndAtivo(FunctionUtil.removerMascaraTexto(dto.getCpf()), true).orElse(new Conta());

		mapper.map(dto, conta);

		try {
			salvar(conta);
		} catch (Exception e) {
			throw new ServiceException(EnumError.FALHA_INESPERADA, e.getMessage());
		}
	}

	public void novoSchema(ContaDTO dto) throws ServiceException {

		try {
			flywayConfig.flyway(dto.getBaseDados()).migrate();
		} catch (Exception e) {
			throw new ServiceException(EnumError.FALHA_INESPERADA, "Falha ao criar Banco de dados");
		}

	}

	@Transactional
	public void atualizar(String token, ContaDTO dto) throws ServiceException {

		Conta conta = repository.findByToken(token)
				.orElseThrow(() -> new ServiceException(EnumError.REGISTRO_INVALIDO));

		validarConta(dto);

		mapper.map(dto, conta);

		try {
			salvar(conta);
		} catch (Exception e) {
			throw new ServiceException(EnumError.FALHA_INESPERADA, e.getMessage());
		}
	}

	public ContaDTO carregar(String token) throws ServiceException {

		ContaDTO contaDTO = accountsService.carregarConta(token);

		if (contaDTO == null || contaDTO.getBaseDados() == null) {
			throw new ServiceException(EnumError.PARAMETROS_INVALIDOS);
		}

		return contaDTO;

	}

}
