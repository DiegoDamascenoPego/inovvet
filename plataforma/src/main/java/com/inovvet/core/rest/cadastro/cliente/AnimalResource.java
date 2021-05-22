package com.inovvet.core.rest.cadastro.cliente;

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

import com.inovvet.core.entity.animal.EnumTipoAnimal;
import com.inovvet.core.entity.animal.dto.AnimalDTO;
import com.inovvet.core.entity.animal.dto.AnimalFiltroDTO;
import com.inovvet.core.entity.base.dto.FiltroDTO;
import com.inovvet.core.service.cadastro.clientes.AnimalService;

@RestController
@RequestMapping("/animal")
@PreAuthorize("hasAuthority('CLIENTE_CONSULTAR')")
public class AnimalResource {

	@Autowired
	private AnimalService animalService;

	@GetMapping("/raca")
	public ResponseEntity<?> listarRaca(@RequestParam EnumTipoAnimal tipoAnimal) throws ServiceException {
		return ResponseEntity.ok(animalService.listarRaca(tipoAnimal));
	}

	@GetMapping("/cor")
	public ResponseEntity<?> listarCor() throws ServiceException {
		return ResponseEntity.ok(animalService.listarCor());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('CLIENTE_CADASTRAR')")
	public ResponseEntity<?> novo(@Valid @RequestBody AnimalDTO dto) throws ServiceException {
		return ResponseEntity.ok(animalService.novo(dto));
	}

	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	@PreAuthorize("hasAuthority('CLIENTE_EDITAR')")
	public ResponseEntity<?> novo(@PathVariable Integer id, @Valid @RequestBody AnimalDTO dto) throws ServiceException {
		return ResponseEntity.ok(animalService.atualizar(id, dto));
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> carregar(@PathVariable Integer id ) throws ServiceException {
		return ResponseEntity.ok(animalService.carregar(id));
	}
	
	@DeleteMapping("{id}")
	@PreAuthorize("hasAuthority('CLIENTE_REMOVER')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> remover(@PathVariable Integer id ) throws ServiceException {
		animalService.remover(id);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/filtrar")
	public ResponseEntity<?> filtrar(@RequestBody FiltroDTO<AnimalFiltroDTO> filtro) throws ServiceException {
		return ResponseEntity.ok(animalService.filtrar(filtro));
	}
	
	@GetMapping
	public ResponseEntity<?> listarAnimal(@RequestParam Integer clienteId) throws ServiceException {
		return ResponseEntity.ok(animalService.listar(clienteId));
	}
	

}
