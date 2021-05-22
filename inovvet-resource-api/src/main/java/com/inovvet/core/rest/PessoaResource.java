package com.inovvet.core.rest;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inovvet.core.service.pessoa.PessoaService;

@RequestMapping("/public/parceiro")
@RestController
public class PessoaResource {

	@Autowired
	private PessoaService service;

	@GetMapping("/consulta/{cnpj}")
	public ResponseEntity<?> consulta(@PathVariable String cnpj) throws ServiceException {
		return ResponseEntity.ok(service.consultar(cnpj));
	}

}
