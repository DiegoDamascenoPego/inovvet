package com.inovvet.core.rest.cadastro.usuario;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.inovvet.core.entity.usuario.dto.UsuarioDTO;
import com.inovvet.core.service.cadastro.usuario.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioResource {

	@Autowired
	private UsuarioService service;

	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public void salvar(@Valid @RequestBody UsuarioDTO dto) {
		service.salvar(dto);
	}

	@GetMapping
	public ResponseEntity<?> carregarUsuario(@RequestParam String token) {
		return ResponseEntity.ok(service.carregar(token));
	}
	

}
