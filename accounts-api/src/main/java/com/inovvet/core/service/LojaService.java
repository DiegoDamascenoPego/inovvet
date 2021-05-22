package com.inovvet.core.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inovvet.app.exception.EnumError;
import com.inovvet.app.exception.ServiceException;
import com.inovvet.app.service.AbstractService;
import com.inovvet.app.util.FunctionUtil;
import com.inovvet.core.entity.Conta;
import com.inovvet.core.entity.Loja;
import com.inovvet.core.entity.dto.LojaCadastroDTO;
import com.inovvet.core.entity.dto.LojaDTO;
import com.inovvet.core.entity.enumerator.EnumTipoLoja;
import com.inovvet.core.repository.LojaRepository;

@Service
public class LojaService extends AbstractService {

	@Autowired
	private LojaRepository repository;

	public Loja salvar(Loja loja) {
		try {
			return repository.save(loja);
		} catch (Exception e) {
			throw new ServiceException(EnumError.FALHA_SALVAR_REGISTRO, e.getMessage());
		}
	}

	private void validarLoja(LojaDTO dto) throws ServiceException {
		if (FunctionUtil.isEmpty(dto.getDocumento())) {
			throw new ServiceException(EnumError.PARAMETROS_INVALIDOS);
		}

		if (FunctionUtil.isCpf(dto.getDocumento())) {
			if (!FunctionUtil.validarCPF(dto.getDocumento())) {
				throw new ServiceException(EnumError.PARAMETROS_INVALIDOS);
			}
		} else {

			if (!FunctionUtil.validarCNPJ(dto.getDocumento())) {
				throw new ServiceException(EnumError.PARAMETROS_INVALIDOS);
			}
		}
	}

	@Transactional
	public Loja salvar(LojaDTO dto, Conta conta) {

		validarLoja(dto);

		Loja loja = new Loja();

		mapper.map(dto, loja);
		loja.setContaId(conta.getId());
		loja.setToken(FunctionUtil.getToken());
		loja.setAtivo(true);
		loja.setTipoLoja(EnumTipoLoja.PETSHOP);
		loja = salvar(loja);
		
		return loja;

	}
	
	public Loja carregar(String token) {
		return repository.findByToken(token).orElseThrow(() 
				-> new ServiceException(EnumError.PARAMETROS_INVALIDOS, "Registro Loja não encontrado"));
		
	}

	@Transactional
	public void sincronizarLojas(Conta conta) {

		repository.findByContaId(conta.getId()).forEach(loja -> {
			restTemplateService.postLoja("loja", loja, conta.getToken());
		});

	}

}
