package com.inovvet.core.rest;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.inovvet.core.entity.dto.LogHandlerDTO;
import com.inovvet.core.service.LogService;

@RequestMapping("/public/log")
@RestController
public class LogResource {
	
	@Autowired
	private LogService service;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void salvar(@RequestBody LogHandlerDTO dto) throws ServiceException {
		service.salvar(dto);
	}
	

}
