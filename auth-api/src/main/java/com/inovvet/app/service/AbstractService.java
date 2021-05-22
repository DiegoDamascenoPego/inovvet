package com.inovvet.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inovvet.app.util.MappingUtil;

@Service
public abstract class AbstractService {

	@Autowired
	protected MappingUtil mapper;

	@Autowired
	protected LogService log;

	@Autowired
	protected RestTemplateService restTemplateService;

}
