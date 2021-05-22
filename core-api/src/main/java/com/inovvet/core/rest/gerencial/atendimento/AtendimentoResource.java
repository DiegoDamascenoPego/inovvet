package com.inovvet.core.rest.gerencial.atendimento;


import java.nio.file.Paths;
import java.time.LocalDate;

import javax.validation.Valid;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.inovvet.core.entity.atendimento.dto.AtendimentoDTO;
import com.inovvet.core.entity.atendimento.dto.AtendimentoFiltroDTO;
import com.inovvet.core.entity.base.dto.FiltroDTO;
import com.inovvet.core.service.gerencial.AtendimentoService;

@RestController
@RequestMapping("/atendimento")
@PreAuthorize("hasAuthority('ATENDIMENTO_CONSULTAR')")
public class AtendimentoResource {
	
	@Autowired
	private AtendimentoService service;
	
	@GetMapping("/{tipoAtendimentoId}/vagas")
	public ResponseEntity<?> carregar(@PathVariable Integer tipoAtendimentoId, 
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) throws ServiceException {
		return ResponseEntity.ok(service.listarVagas(tipoAtendimentoId, data));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('ATENDIMENTO_CADASTRAR')")
	public ResponseEntity<?> novo(@Valid @RequestBody AtendimentoDTO dto) throws ServiceException {
		return ResponseEntity.ok(service.novo(dto));
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
		return ResponseEntity.ok(service.carregar(id));
	}
	
	@PostMapping("/filtrar")
	public ResponseEntity<?> filtrar(@RequestBody FiltroDTO<AtendimentoFiltroDTO> filtro) throws ServiceException {
		return ResponseEntity.ok(service.filtrarAtendimento(filtro));
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	@PreAuthorize("hasAuthority('ATENDIMENTO_EDITAR')")
	public ResponseEntity<?> atualizar(@PathVariable Integer id, @Valid  @RequestBody AtendimentoDTO dto) throws ServiceException {
		return ResponseEntity.ok(service.atualizar(id, dto));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ATENDIMENTO_REMOVER')")
	public void excluir(@PathVariable Integer id) throws ServiceException {
		service.remover(id);
	}
	
	@PostMapping("/{id}/finalizar")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasAuthority('ATENDIMENTO_EDITAR')")
	public void atualizar(@PathVariable Integer id) throws ServiceException {
		service.finalizar(id);
	}

	
	@GetMapping("/relatorio")
	public ResponseEntity<?> relatorio() throws ServiceException {
		Resource file = null;
		try {
			file = new UrlResource(Paths.get(this.service.relatoriAtendimento()).toUri());
			if (file.exists()) {
				return ResponseEntity.ok()
						.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
						.contentType(MediaType.APPLICATION_PDF)
						.body(file);
			} else {
				return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getCause(), HttpStatus.CONFLICT);
		}
	}
}
