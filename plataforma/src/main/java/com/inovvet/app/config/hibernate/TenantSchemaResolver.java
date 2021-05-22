package com.inovvet.app.config.hibernate;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;

import com.inovvet.app.config.Contexto;

@Component
public class TenantSchemaResolver implements CurrentTenantIdentifierResolver {
	
	private String defaultTenant ="plataforma";
 
	@Override
	public String resolveCurrentTenantIdentifier() {
		String t = Contexto.getContaSchema();
		if (t != null) {
			return t;
		} else {
			return defaultTenant;
		}
	}

	@Override
	public boolean validateExistingCurrentSessions() {
		return true;
	}

}
