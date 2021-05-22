package com.inovvet.core.rest.cadastro.produto;

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

import com.inovvet.core.entity.base.dto.FiltroDTO;
import com.inovvet.core.entity.classificacao.dto.ClassificacaoDTO;
import com.inovvet.core.entity.classificacao.dto.ClassificacaoFiltroDTO;
import com.inovvet.core.service.cadastro.produto.ClassificacaoService;

@RestController
@RequestMapping("/classificacao")
@PreAuthorize("hasAuthority('CLASSIFICACAO_CONSULTAR')")
public class ClassificacaoResource {
	
	@Autowired
	private ClassificacaoService service;

	@GetMapping("/{id}")
	public ResponseEntity<?> carregar(@PathVariable Integer id) throws ServiceException {
		return ResponseEntity.ok(service.carregar(id));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('CLASSIFICACAO_CADASTRAR')")
	public ResponseEntity<?> novo(@Valid @RequestBody ClassificacaoDTO dto) throws ServiceException {
		return ResponseEntity.ok(service.novo(dto));
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	@PreAuthorize("hasAuthority('CLASSIFICACAO_EDITAR')")
	public ResponseEntity<?> atualizar(@PathVariable Integer id, @Valid @RequestBody ClassificacaoDTO dto)
			throws ServiceException {
		return ResponseEntity.ok(service.atualizar(id, dto));
	}
	
	@GetMapping("/listar")
	public ResponseEntity<?> listar() throws ServiceException {
		return ResponseEntity.ok(service.listar());
	}
	
	@GetMapping("/listar/descricao")
	public ResponseEntity<?> listarDescricao() throws ServiceException {
		return ResponseEntity.ok(service.listarDescricao());
	}
	
	@PostMapping("/filtrar")
	public ResponseEntity<?> filtrar(@RequestBody FiltroDTO<ClassificacaoFiltroDTO> filtro) throws ServiceException {
		return ResponseEntity.ok(service.filtrar(filtro));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('CLASSIFICACAO_REMOVER')")
	public void remover(@PathVariable Integer id) throws ServiceException {
		service.remover(id);
	}


}
