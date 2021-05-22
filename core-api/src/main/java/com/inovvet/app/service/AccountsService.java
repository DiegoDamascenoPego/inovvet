package com.inovvet.app.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import com.inovvet.app.config.Projeto;
import com.inovvet.app.exception.EnumError;
import com.inovvet.app.exception.ServiceException;
import com.inovvet.core.entity.conta.dto.ContaDTO;
import com.inovvet.core.entity.loja.Loja;

@Service
public class AccountsService {

	@Autowired
	private OAuth2RestTemplate oauth2RestTemplate;

	@Autowired
	private Projeto projeto;

	public ContaDTO carregarConta(String token) {

		try {
			return oauth2RestTemplate.getForObject(projeto.getAccountsApi() + "/conta/" + token, ContaDTO.class);

		} catch (Exception e) {
			throw new ServiceException(EnumError.FALHA_INESPERADA, e.getMessage());
		}
	}
	
	public Loja salvarLoja(Loja loja, String token) {

		try {
			ResponseEntity<Loja> lojaCadastrada =  oauth2RestTemplate.postForEntity(projeto.getAccountsApi() + "/conta/registrar/loja/" + token, loja, Loja.class);
			return lojaCadastrada.getBody();

		} catch (Exception e) {
			throw new ServiceException(EnumError.FALHA_INESPERADA, e.getMessage());
		}
	}

	
	public ContaDTO carregarContaLoja(String token) {

		try {
			return oauth2RestTemplate.getForObject(projeto.getAccountsApi() + "/conta/loja/" + token, ContaDTO.class);

		} catch (Exception e) {
			throw new ServiceException(EnumError.FALHA_INESPERADA, e.getMessage());
		}
	}
	
	public List<String> findSchemas() {
		List<String> schemas = new ArrayList<String>();
		
		ResponseEntity<ContaDTO[]> response = oauth2RestTemplate.getForEntity(projeto.getAccountsApi() + "/conta",ContaDTO[].class);
		
		 Arrays.asList(response.getBody()).forEach(conta -> {
			 if	(conta.getBaseDados() != null) {
				 schemas.add(conta.getBaseDados());
			 }
			 
		 });
		 
		return schemas;
	}

}
