package com.inovvet.core.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.inovvet.app.util.MappingUtil;


public abstract class AbstractService {
	
	@Autowired
	protected MappingUtil mapper;

}
