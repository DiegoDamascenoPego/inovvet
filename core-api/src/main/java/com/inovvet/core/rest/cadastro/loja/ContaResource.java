package com.inovvet.core.rest.cadastro.loja;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.inovvet.app.exception.ServiceException;
import com.inovvet.core.entity.conta.dto.ContaDTO;
import com.inovvet.core.service.cadastro.loja.ContaService;

@RestController
@RequestMapping("/conta")
public class ContaResource {
	@Autowired
	private ContaService service;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void salvar(@RequestBody  ContaDTO dto) throws ServiceException {
		service.salvar(dto);
	}
	
	@PostMapping("/deploy")
	@ResponseStatus(HttpStatus.CREATED)
	public void novo(@RequestBody  ContaDTO dto) throws ServiceException {
		service.novoSchema(dto);
	}
	
	@PutMapping("/token")
	@ResponseStatus(HttpStatus.OK)
	public void atualizar(@RequestParam String  token, @RequestBody @Valid ContaDTO dto) throws ServiceException {
		service.atualizar(token, dto);
	}

}
