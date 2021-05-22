package com.inovvet.core.rest.cadastro.pessoa;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.inovvet.core.entity.base.dto.FiltroDTO;
import com.inovvet.core.entity.pessoa.dto.FabricanteDTO;
import com.inovvet.core.entity.pessoa.dto.PessoaFiltroDTO;
import com.inovvet.core.service.cadastro.pessoa.FabricanteService;

@RestController
@RequestMapping("/fabricante")
@PreAuthorize("hasAuthority('FABRICANTE_CONSULTAR')")
public class FabricanteResource {
	
	@Autowired
	private FabricanteService service;

	@GetMapping("/{id}")
	public ResponseEntity<?> carregar(@PathVariable Integer id) throws ServiceException {
		return ResponseEntity.ok(service.carregar(id));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('FABRICANTE_CADASTRAR')")
	public ResponseEntity<?> novo(@Valid @RequestBody FabricanteDTO dto) throws ServiceException {
		return ResponseEntity.ok(service.novo(dto));
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	@PreAuthorize("hasAuthority('FABRICANTE_EDITAR')")
	public ResponseEntity<?> atualizar(@PathVariable Integer id, @Valid @RequestBody FabricanteDTO dto)
			throws ServiceException {
		return ResponseEntity.ok(service.atualizar(id, dto));
	}
	
	@GetMapping("/listar")
	public ResponseEntity<?> listar() throws ServiceException {
		return ResponseEntity.ok(service.listar());
	}
	
	@GetMapping("/consultar")
	public ResponseEntity<?> pesquisar(@RequestParam String valor) throws ServiceException {
		return ResponseEntity.ok(service.consultarPorDescricaoOuCNPJ(valor));
	}
	
	@PostMapping("/filtrar")
	public ResponseEntity<?> filtrar(@RequestBody FiltroDTO<PessoaFiltroDTO> filtro) throws ServiceException {
		return ResponseEntity.ok(service.filtrar(filtro));
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('FABRICANTE_REMOVER')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer id) throws ServiceException {
		service.remover(id);
	}

	
	@GetMapping("/marca/listar")
	public ResponseEntity<?> pesquisarMarca(@RequestParam String nome) throws ServiceException {
		return ResponseEntity.ok(service.pesquisarMarca(nome));
	}

}
