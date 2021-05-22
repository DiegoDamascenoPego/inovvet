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

	
	@Autowired
	private SpringProperties spring;
	
	@Autowired
	private AppProperties app;
	
	@Autowired
	private EmailProperties email;
	
	private String plataformaApi;
	
	private String plataformaUi;
	
	@Configuration
	@EnableConfigurationProperties
	@ConfigurationProperties(prefix = "app")
	@Getter
	@Setter
	class AppProperties {
	

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
	@ConfigurationProperties(prefix = "email")
	@Getter
	@Setter
	class EmailProperties {
		
		private String username;
		private String password;
		private Integer port;
		private String host;

	}

	@Configuration
	@EnableConfigurationProperties
	@ConfigurationProperties(prefix = "spring")
	@Getter
	@Setter
	class SpringProperties {

		private Map<String, String> datasource;

	}

}
