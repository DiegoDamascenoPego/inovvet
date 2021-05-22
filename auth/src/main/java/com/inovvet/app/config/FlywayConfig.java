package com.inovvet.app.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayConfig {

	@Autowired
	private Projeto projeto;

	@Bean(initMethod = "migrate")
	public Flyway flyway() {
		return Flyway.configure()
				.dataSource(
				projeto.getSpring().getDatasource().get("url"),  
				projeto.getSpring().getDatasource().get("username"),  
				projeto.getSpring().getDatasource().get("password"))
				.baselineOnMigrate(true)
				.cleanDisabled(true)			
				.load();
	}

}
