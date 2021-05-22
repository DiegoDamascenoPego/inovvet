package com.inovvet.core.rest.cadastro.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inovvet.app.exception.ServiceException;
import com.inovvet.core.service.cadastro.produto.SeguimentoService;

@RestController
@RequestMapping("/seguimento")
public class SeguimentoResource {

	@Autowired
	private SeguimentoService service;

	@GetMapping("/listar")
	public ResponseEntity<?> listar() throws ServiceException {
		return ResponseEntity.ok(service.listar());
	}

}
