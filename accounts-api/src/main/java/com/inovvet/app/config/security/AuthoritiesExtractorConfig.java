package com.inovvet.app.config.security;

import java.util.List;
import java.util.Map;

import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

public class AuthoritiesExtractorConfig implements AuthoritiesExtractor {

	@Override
	public List<GrantedAuthority> extractAuthorities(Map<String, Object> map) {
		return AuthorityUtils
		          .commaSeparatedStringToAuthorityList(asAuthorities(map));
	}
	
	private String asAuthorities(Map<String, Object> map) {
		if ((Boolean) (map.get("clientOnly"))) {
			return "";
		}
		
		return "";
		
    }

}
