package com.inovvet.core.rest.cadastro.atendimento;

import javax.validation.Valid;

import org.hibernate.service.spi.ServiceException;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.inovvet.core.entity.atendimento.dto.TipoAtendimentoDTO;
import com.inovvet.core.entity.base.dto.EntityFiltro;
import com.inovvet.core.entity.base.dto.FiltroDTO;
import com.inovvet.core.service.cadastro.atendimento.TipoAtendimentoService;

@RestController
@RequestMapping("/tipoatendimento")
@PreAuthorize("hasAuthority('TIPOATENDIMENTO_CONSULTAR')")
public class TipoAtendimentoResource {
	
	@Autowired
	private TipoAtendimentoService service;

	@GetMapping("/{id}")
	public ResponseEntity<?> carregar(@PathVariable Integer id) throws ServiceException {
		return ResponseEntity.ok(service.carregar(id));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('TIPOATENDIMENTO_CADASTRAR')")
	public ResponseEntity<?> novo(@Valid @RequestBody TipoAtendimentoDTO dto) throws ServiceException {
		return ResponseEntity.ok(service.novo(dto));
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	@PreAuthorize("hasAuthority('TIPOATENDIMENTO_EDITAR')")
	public ResponseEntity<?> atualizar(@PathVariable Integer id, @Valid @RequestBody TipoAtendimentoDTO dto)
			throws ServiceException {
		return ResponseEntity.ok(service.atualizar(id, dto));
	}

	@GetMapping("/listar")
	public ResponseEntity<?> listar() throws ServiceException {
		return ResponseEntity.ok(service.listar());
	}

	@PostMapping("/filtrar")
	public ResponseEntity<?> filtrar(@RequestBody FiltroDTO<EntityFiltro> filtro) throws ServiceException {
		return ResponseEntity.ok(service.filtrar(filtro));
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('TIPOATENDIMENTO_REMOVER')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer id) throws ServiceException {
		service.remover(id);
	}

}
