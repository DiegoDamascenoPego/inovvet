package com.inovvet.core.rest;

import javax.validation.Valid;

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
import com.inovvet.core.entity.dto.ContaDTO;
import com.inovvet.core.entity.dto.LojaDTO;
import com.inovvet.core.entity.dto.UsuarioDTO;
import com.inovvet.core.service.ContaService;

@RestController
@RequestMapping("/conta")
public class ContaResource {
	@Autowired
	private ContaService service;

	@GetMapping("{token}")
	public ResponseEntity<?> carregar(@PathVariable String token) throws ServiceException {
		return ResponseEntity.ok(service.carregar(token));
	}

	@GetMapping
	public ResponseEntity<?> carregar() throws ServiceException {
		return ResponseEntity.ok(service.carregar());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public void novo(@RequestBody @Valid ContaDTO dto) throws ServiceException {
		service.salvar(dto);
	}

	@PostMapping("/ativar/{token}")
	@ResponseStatus(HttpStatus.OK)
	public void registrar(@PathVariable String token) throws ServiceException {
		service.ativar(token);
	}

	@PostMapping("/registrar/{token}")
	@ResponseStatus(HttpStatus.OK)
	public void registrar(@PathVariable String token, @Valid @RequestBody UsuarioDTO dto) throws ServiceException {

		service.registrar(token, dto);

	}

	@PostMapping("/registrar/loja/{token}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> adicionarLoja(@PathVariable String token, @Valid @RequestBody LojaDTO dto)
			throws ServiceException {

		return ResponseEntity.ok(service.adicionarLoja(token, dto));

	}

	@GetMapping("/loja/{token}")
	public ResponseEntity<?> carregarContaLoja(@PathVariable String token) throws ServiceException {
		return ResponseEntity.ok(service.carregarContaLoja(token));
	}
}
