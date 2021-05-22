package com.inovvet.core.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.inovvet.app.exception.ServiceException;
import com.inovvet.core.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioResource {

	@Autowired
	private UsuarioService service;

	@PostMapping("/ativar/{token}")
	@ResponseStatus(HttpStatus.OK)
	public void registrarSenha(@PathVariable String token, @RequestBody String senha) throws ServiceException {
		service.registrarSenha(token, senha);
	}
	
	@GetMapping("/carregar/{token}")
	public ResponseEntity<?> carregar(@PathVariable String token) throws ServiceException {
		return ResponseEntity.ok(service.carregar(token));
	}
}


