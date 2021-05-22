package com.inovvet.core.rest.cadastro.loja;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.inovvet.app.exception.ServiceException;
import com.inovvet.core.entity.base.dto.FiltroDTO;
import com.inovvet.core.entity.categoria.dto.CategoriaFiltro;
import com.inovvet.core.entity.loja.dto.LojaDTO;
import com.inovvet.core.entity.loja.dto.LojaFiltroDTO;
import com.inovvet.core.service.cadastro.loja.LojaService;

@RestController
@RequestMapping("/loja")
public class LojaResource {

	@Autowired
	public LojaService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> carregar(@PathVariable Integer id) throws ServiceException {
		return ResponseEntity.ok(service.carregar(id));
	}
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody @Valid LojaDTO dto) throws ServiceException {
		return ResponseEntity.ok(service.salvar(dto));
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	@PreAuthorize("hasAuthority('LOJA_EDITAR')")
	public ResponseEntity<?> atualizar(@PathVariable Integer id, @Valid @RequestBody LojaDTO dto)
			throws ServiceException {
		return ResponseEntity.ok(service.atualizar(id, dto));
	}
	
	@PostMapping("/filtrar")
	public ResponseEntity<?> filtrar(@RequestBody FiltroDTO<LojaFiltroDTO> filtro) throws ServiceException {
		return ResponseEntity.ok(service.filtrar(filtro));
	}
	
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('LOJA_REMOVER')")
	public void excluir(@PathVariable Integer id) throws ServiceException {
		service.remover(id);
	}

}
