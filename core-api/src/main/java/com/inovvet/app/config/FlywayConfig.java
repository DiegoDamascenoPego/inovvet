package com.inovvet.app.config;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.inovvet.app.service.AccountsService;

@Configuration
public class FlywayConfig {

	@Autowired
	protected AccountsService accountsService;
	
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
	
	public Flyway flyway(String schema) {
		return Flyway.configure()
				.dataSource(
				projeto.getSpring().getDatasource().get("url"),  
				projeto.getSpring().getDatasource().get("username"),  
				projeto.getSpring().getDatasource().get("password"))
				.baselineOnMigrate(true)
				.cleanDisabled(true)	
				.schemas(schema)
				.load();
	}	
	
	@Bean
    public Boolean tenantsFlyway(DataSource dataSource){		
		
        accountsService.findSchemas().forEach(tenant -> {
        	
            Flyway.configure()
			.dataSource(
			projeto.getSpring().getDatasource().get("url"),  
			projeto.getSpring().getDatasource().get("username"),  
			projeto.getSpring().getDatasource().get("password"))
			.baselineOnMigrate(true)
			.cleanDisabled(true)	
			.schemas(tenant)
			.load()
			.migrate();
        });
        return true;
    }
}
