package com.inovvet.core.service.pessoa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.inovvet.app.config.Projeto;
import com.inovvet.app.exception.EnumError;
import com.inovvet.app.exception.ServiceException;

@Service
public class ReceitaWSImpl implements ConsultaPessoaWS {

	@Autowired
	private RestTemplate rest;

	@Autowired
	private Projeto projeto;

	private PessoaReceita buscar(String cnpj) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("Authorization","Bearer "+ projeto.getReceitaWs().getToken());

			HttpEntity<String> entity = new HttpEntity<String>("", headers);

			return rest.exchange(this.projeto.getReceitaWs().getUri() + "/" + cnpj, HttpMethod.GET, entity,
					PessoaReceita.class).getBody();

		} catch (Exception e) {
			throw new ServiceException(EnumError.FALHA_INESPERADA, "Falha ao Consultar os Dados do CNPJ");
		}

	}


	@Override
	public PessoaReceita consulta(String cnpj) {
		return buscar(cnpj);
	}

}
