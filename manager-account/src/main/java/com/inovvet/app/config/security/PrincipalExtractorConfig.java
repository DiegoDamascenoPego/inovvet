package com.inovvet.app.config.security;

import java.util.Map;

import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;


public class PrincipalExtractorConfig implements PrincipalExtractor {

	@Override
	public Object extractPrincipal(Map<String, Object> map) {
		return map.get("name");
	}

}
