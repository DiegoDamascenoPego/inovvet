package com.inovvet.core.service;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.inovvet.app.exception.EnumError;
import com.inovvet.app.exception.ServiceException;
import com.inovvet.app.service.AbstractService;
import com.inovvet.app.service.MensagemService;
import com.inovvet.app.util.FunctionUtil;
import com.inovvet.core.entity.Conta;
import com.inovvet.core.entity.Loja;
import com.inovvet.core.entity.dto.ContaDTO;
import com.inovvet.core.entity.dto.LojaDTO;
import com.inovvet.core.entity.dto.UsuarioDTO;
import com.inovvet.core.repository.ContaRepository;

@Service
public class ContaService extends AbstractService {

	@Autowired
	private ContaRepository repository;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private MensagemService mensagemService;
	
	@Autowired
	private LojaService lojaService;

	private void validarConta(ContaDTO dto) throws ServiceException {
		if (!FunctionUtil.validarCPF(dto.getCpf())) {
			throw new ServiceException(EnumError.PARAMETROS_INVALIDOS);
		}

		if (!FunctionUtil.validarEmail(dto.getEmail())) {
			throw new ServiceException(EnumError.PARAMETROS_INVALIDOS);
		}

		Conta conta = repository.findByCpfAndAtivo(FunctionUtil.removerMascaraTexto(dto.getCpf()), true).orElse(null);

		if (conta != null) {
			throw new ServiceException(EnumError.CONTA_INVALIDA);
		}
	}

	private Conta salvar(Conta conta) throws ServiceException {
		try {
			conta = repository.save(conta);

			return conta;
		} catch (Exception e) {
			throw new ServiceException(EnumError.FALHA_SALVAR_REGISTRO);
		}
	}

	@Transactional
	public void salvar(ContaDTO dto) throws ServiceException {

		validarConta(dto);

		Conta conta = new Conta();

		mapper.map(dto, conta);
		conta.setToken(FunctionUtil.getToken());
		conta.setDataCredito(LocalDate.now().plusMonths(1));

		try {
			conta = salvar(conta);
			mensagemService.ativacao(conta.getEmail(), conta.getNome(), conta.getToken());
		} catch (Exception e) {
			throw new ServiceException(EnumError.FALHA_INESPERADA, e.getMessage());
		}
	}

	public Conta carregar(String token) throws ServiceException {
		return repository.findByToken(token).orElseThrow(() -> new ServiceException(EnumError.PARAMETROS_INVALIDOS, "Token não encontrado"));

	}

	public Iterable<Conta> carregar() throws ServiceException {
		return repository.findAll();
	}

	@Transactional
	public void adicionarUsuario(String token, UsuarioDTO dto) {
		Conta conta = carregar(token);
		usuarioService.salvar(conta, dto);

	}
	
	
	@Transactional
	public Loja adicionarLoja(String token, LojaDTO dto) {
		Conta conta = carregar(token);
		
		return lojaService.salvar(dto, conta);

	}

	@Transactional
	public void ativar(String token) {
		Conta conta = carregar(token);
		conta.setDataCredito(LocalDate.now().plusMonths(1));

		if (conta.getBaseDados() == null || conta.getBaseDados().isEmpty()) {
			conta.setBaseDados("plataforma_" + String.format("%3d", conta.getId()).trim());
		}

		salvar(conta);

		restTemplateService.post("conta/deploy", new Gson().toJson(conta), conta.getToken());
	}

	public void registrar(String token, UsuarioDTO dto) {
		Conta conta = carregar(token);

		restTemplateService.post("conta", new Gson().toJson(conta), conta.getToken());

//		lojaService.sincronizarLojas(conta);

		adicionarUsuario(token, dto);

	}
	
	
	public Conta carregarContaLoja(String token) throws ServiceException {
		Loja loja =  lojaService.carregar(token);
		
		return repository.findById(loja.getContaId()).orElseThrow(() 
				-> new ServiceException(EnumError.CONTA_INVALIDA, "Registro Produto não encontrado"));
	}
}
