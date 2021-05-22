package com.inovvet.app.config;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "projeto")
@Getter
@Setter
public class Projeto {

	private String authApi;
	
	private String accountsApi;

	private String plataformaUi;
	
	private String cepApi;
	
	private String bucket;

	@Autowired
	private SpringProperties spring;
	@Autowired
	private ReceitaWs receitaWs;

	@Configuration
	@EnableConfigurationProperties
	@ConfigurationProperties(prefix = "spring")
	@Getter
	@Setter
	class SpringProperties {

		private Map<String, String> datasource;

	}

	@Autowired
	private DBProperties db;
	

	@Configuration
	@EnableConfigurationProperties
	@ConfigurationProperties(prefix = "db")
	@Getter
	@Setter
	class DBProperties {

		private String database;

	}
	
	@Configuration
	@EnableConfigurationProperties
	@ConfigurationProperties(prefix = "db")
	@Getter
	@Setter
	public class ReceitaWs {

		private String uri;
		private String token;

	}

}
