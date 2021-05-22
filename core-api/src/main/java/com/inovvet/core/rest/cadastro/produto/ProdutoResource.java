package com.inovvet.core.rest.cadastro.produto;

import javax.validation.Valid;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
import com.inovvet.core.entity.produto.dto.ProdutoDTO;
import com.inovvet.core.entity.produto.dto.ProdutoFiltroDTO;
import com.inovvet.core.service.cadastro.produto.ProdutoService;
import com.inovvet.core.service.cadastro.produto.TipoProdutoService;
import com.inovvet.core.service.cadastro.produto.UnidadeService;

@RestController
@RequestMapping("/produto")
@PreAuthorize("hasAuthority('PRODUTO_CONSULTAR')")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService service;
	
	@Autowired
	private TipoProdutoService tipoProdutoService;
	
	@Autowired
	private UnidadeService unidadeService;
	
	@Cacheable("tipoProduto")
	@GetMapping("/tipo/listar")
	public ResponseEntity<?> listarTipoProduto() throws ServiceException {
		return ResponseEntity.ok(tipoProdutoService.listar());
	}
	
	@Cacheable("unidade")
	@GetMapping("/unidade/listar")
	public ResponseEntity<?> listarUnidade() throws ServiceException {
		return ResponseEntity.ok(unidadeService.listar());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> carregar(@PathVariable Integer id) throws ServiceException {
		return ResponseEntity.ok(service.carregar(id));
	}
	
	@GetMapping
	public ResponseEntity<?> carregar(@RequestParam String nome) throws ServiceException {
		return ResponseEntity.ok(service.consultar(nome));
	}
	

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('PRODUTO_CADASTRAR')")
	public ResponseEntity<?> novo(@Valid @RequestBody ProdutoDTO dto) throws ServiceException {
		return ResponseEntity.ok(service.novo(dto));
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	@PreAuthorize("hasAuthority('PRODUTO_EDITAR')")
	public ResponseEntity<?> atualizar(@PathVariable Integer id, @Valid @RequestBody ProdutoDTO dto)
			throws ServiceException {
		return ResponseEntity.ok(service.atualizar(id, dto));
	}
	
	@GetMapping("/listar")
	public ResponseEntity<?> listar() throws ServiceException {
		return ResponseEntity.ok(service.listar());
	}
	
	@PostMapping("/filtrar")
	public ResponseEntity<?> filtrar(@RequestBody FiltroDTO<ProdutoFiltroDTO> filtro) throws ServiceException {
		return ResponseEntity.ok(service.filtrar(filtro));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('PRODUTO_REMOVER')")
	public void remover(@PathVariable Integer id) throws ServiceException {
		service.remover(id);
	}

}
