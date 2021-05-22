package com.inovvet.core.rest.cadastro.cliente;

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
import com.inovvet.core.entity.base.dto.FiltroDTO;
import com.inovvet.core.entity.cliente.dto.ClienteDTO;
import com.inovvet.core.entity.cliente.dto.ClienteFiltroDTO;
import com.inovvet.core.service.cadastro.clientes.ClienteService;

@RestController
@RequestMapping("/cliente")
@PreAuthorize("hasAuthority('CLIENTE_CONSULTAR')")
public class ClienteResource {

	@Autowired
	private ClienteService service;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('CLIENTE_CADASTRAR')")
	public ResponseEntity<?> salvar(@Valid @RequestBody ClienteDTO dto) throws ServiceException {
		return ResponseEntity.ok(service.salvar(dto));
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	@PreAuthorize("hasAuthority('CLIENTE_EDITAR')")
	public ResponseEntity<?> atualizar(@PathVariable Integer id, @Valid @RequestBody ClienteDTO dto)
			throws ServiceException {
		return ResponseEntity.ok(service.atualizar(id, dto));
	}

	@PostMapping("/filtrar")
	public ResponseEntity<?> filtrar(@RequestBody FiltroDTO<ClienteFiltroDTO> filtro) throws ServiceException {
		return ResponseEntity.ok(service.filtrar(filtro));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
		return ResponseEntity.ok(service.buscarPorId(id));
	}
	
	@GetMapping
	public ResponseEntity<?> carregar(@RequestParam String nome) throws ServiceException {
		return ResponseEntity.ok(service.consultar(nome));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('CLIENTE_REMOVER')")
	public ResponseEntity<?> excluir(@PathVariable Integer id) throws ServiceException {
		service.excluir(id);

		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{id}/endereco")
	public ResponseEntity<?> carregarEndereco(@PathVariable Integer id) throws ServiceException {
		return ResponseEntity.ok(service.carregarEndereco(id));
	}
	
	@GetMapping("/{id}/telefone")
	public ResponseEntity<?> carregarTelefone(@PathVariable Integer id) throws ServiceException {
		return ResponseEntity.ok(service.listarTelefonesDTO(id));
	}

}
	