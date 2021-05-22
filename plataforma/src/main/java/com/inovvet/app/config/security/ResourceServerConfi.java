package com.inovvet.app.config.security;

import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfi extends ResourceServerConfigurerAdapter {
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
        .authorizeRequests()
            .antMatchers(HttpMethod.GET, "/site/**/**", "/site/**")
            .permitAll()
            .and()
    		.antMatcher("/**")
    		.authorizeRequests()
    		.anyRequest()
    		.authenticated();
	}
	
	
	@Bean
	public PrincipalExtractor baeldungPrincipalExtractor() {
		return new PrincipalExtractorConfig();
	}

	@Bean
	public AuthoritiesExtractor baeldungAuthoritiesExtractor() {
		return new AuthoritiesExtractorConfig();
	}

	@Bean
	@ConfigurationProperties(prefix = "security.oauth2.client")
	public ClientCredentialsResourceDetails clientCredentialsResourceDetails() {
		return new ClientCredentialsResourceDetails();
	}

	@Bean
	public OAuth2RestTemplate oAuth2RestTemplate() {
		return new OAuth2RestTemplate(clientCredentialsResourceDetails());
	}
	
	

}
