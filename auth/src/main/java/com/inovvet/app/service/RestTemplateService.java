package com.inovvet.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.inovvet.app.config.Projeto;
import com.inovvet.app.exception.EnumError;
import com.inovvet.app.exception.ServiceException;

@Service
public class RestTemplateService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private Projeto projeto;

	public void post(String uri, String body, String token) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("Authorization", "Bearer " + "7a438573-cdc1-405b-9f74-e2317c960c3d");
			headers.set("Token", token);

			HttpEntity<String> entity = new HttpEntity<String>(body, headers);

			restTemplate.postForEntity(projeto.getPlataformaApi() + "/" + uri, entity, String.class);
		} catch (Exception e) {
			throw new ServiceException(EnumError.FALHA_API, e.getMessage());
		}
	}
}
