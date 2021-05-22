package com.inovvet.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inovvet.core.entity.LogHandler;
import com.inovvet.core.entity.dto.LogHandlerDTO;
import com.inovvet.core.repository.LogHandlerRepository;

@Service
public class LogService {

	@Autowired
	private LogHandlerRepository repository;
	
	
	private void salvar (LogHandler log) {
		this.repository.save(log);
	}
	
	public void salvar(LogHandlerDTO dto) {
		LogHandler log = new LogHandler();
		
		log.setErro(dto.getErro());
		log.setStackTrace(dto.getStackTrace());
		log.setUrl(dto.getUrl());
		
		salvar(log);
	}
}
