package com.inovvet.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import com.inovvet.app.config.Projeto;
import com.inovvet.app.exception.EnumError;
import com.inovvet.app.exception.ServiceException;

@Service
public class RestTemplateAuthApiService {

	@Autowired
	private OAuth2RestTemplate oauth2RestTemplate;

	@Autowired
	private Projeto projeto;

	private String endpoint;

	public RestTemplateAuthApiService authApi() {
		this.endpoint = projeto.getAuthApi() + "/";
		return this;
	}

	public RestTemplateAuthApiService accontsApi() {
		this.endpoint = projeto.getAuthApi() + "/";
		return this;
	}

	public void post(String uri, String body, String token) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("Token", token);

			HttpEntity<String> entity = new HttpEntity<String>(body, headers);

			oauth2RestTemplate.postForEntity(this.endpoint + uri, entity, String.class);
		} catch (Exception e) {
			throw new ServiceException(EnumError.FALHA_INESPERADA, e.getMessage());
		}
	}

	public ResponseEntity<String> get(String uri, String token) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("Token", token);

			HttpEntity<String> entity = new HttpEntity<String>("", headers);

			return oauth2RestTemplate.exchange(this.endpoint + uri, HttpMethod.GET, entity, String.class);

		} catch (Exception e) {
			throw new ServiceException(EnumError.FALHA_INESPERADA, e.getMessage());
		}
	}

}
