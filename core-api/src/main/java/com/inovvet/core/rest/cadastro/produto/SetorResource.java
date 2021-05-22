package com.inovvet.core.rest.cadastro.produto;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.inovvet.app.exception.ServiceException;
import com.inovvet.core.entity.produto.dto.SetorDTO;
import com.inovvet.core.service.cadastro.produto.SetorService;

@RestController
@RequestMapping("/setor")
@PreAuthorize("hasAuthority('SETOR_CONSULTAR')")
public class SetorResource {

	@Autowired
	private SetorService service;

	@GetMapping("/{id}")
	public ResponseEntity<?> carregar(@PathVariable Integer id) throws ServiceException {
		return ResponseEntity.ok(service.localizar(id));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('SETOR_CADASTRAR')")
	public ResponseEntity<?> novo(@Valid @RequestBody SetorDTO dto) throws ServiceException {
		return ResponseEntity.ok(service.novo(dto));
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('SETOR_EDITAR')")
	public ResponseEntity<?> atualizar(@PathVariable Integer id, @Valid @RequestBody SetorDTO dto) throws ServiceException {
		return ResponseEntity.ok(service.atualizar(dto, id));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('SETOR_REMOVER')")
	public void remover(@PathVariable Integer id) throws ServiceException {
		service.remover(id);
	}

	@GetMapping
	public ResponseEntity<?> listar(@RequestParam String nome) throws ServiceException {
		return ResponseEntity.ok(service.listar(nome));
	}
	
	}
