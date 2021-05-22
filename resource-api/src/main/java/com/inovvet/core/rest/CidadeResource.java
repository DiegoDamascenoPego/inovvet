package com.inovvet.core.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inovvet.core.service.CidadeService;

@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/public/cidade")
@RestController
public class CidadeResource {
	
	@Autowired
	private CidadeService service;
	
	@Cacheable("estado")
	@GetMapping("/uf")	
	public ResponseEntity<?> listarUf(){
		return ResponseEntity.ok(service.listarUfs());
	}
	
	@Cacheable("cidades")
	@GetMapping("/{sigla}")
	public ResponseEntity<?> listarUf(@PathVariable String sigla){
		return ResponseEntity.ok(service.listar(sigla));
	}
	
	@GetMapping("/cep/{cep}")
	public ResponseEntity<?> buscarCep(@PathVariable String cep){
		return ResponseEntity.ok(service.buscarCep(cep));
	}

}
