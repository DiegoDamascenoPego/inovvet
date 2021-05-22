package com.inovvet.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import com.inovvet.app.config.Projeto;
import com.inovvet.app.exception.EnumError;
import com.inovvet.app.exception.ServiceException;
import com.inovvet.core.entity.Loja;

@Service
public class RestTemplateService {

	@Autowired
	private OAuth2RestTemplate  oauth2RestTemplate;

	@Autowired
	private Projeto projeto;

	public void post(String uri, String body, String token) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("Token", token);

			HttpEntity<String> entity = new HttpEntity<String>(body, headers);
			
			 oauth2RestTemplate.postForEntity(projeto.getPlataformaApi() + "/" + uri, entity, String.class);
		} catch (Exception e) {
			throw new ServiceException(EnumError.FALHA_API, e.getMessage());
		}
	}
	
	

	public void postLoja(String uri, Loja loja, String token) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("Token", token);

			HttpEntity<Loja> entity = new HttpEntity<Loja>(loja, headers);
			
			 oauth2RestTemplate.postForEntity(projeto.getPlataformaApi() + "/" + uri, entity, String.class);
		} catch (Exception e) {
			throw new ServiceException(EnumError.FALHA_API, e.getMessage());
		}
	}
	
	
}
