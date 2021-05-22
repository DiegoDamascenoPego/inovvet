package com.inovvet.app.config.security;

import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@Configuration
@EnableResourceServer
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	 

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/**").authorizeRequests().anyRequest().authenticated();
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
	public PasswordEncoder passwordEncoder() {
		return new PwEnconder();
	}

	@Bean
	@ConfigurationProperties(prefix = "security.oauth2.client")
	public ClientCredentialsResourceDetails clientCredentialsResourceDetails() {
		ClientCredentialsResourceDetails resource =new  ClientCredentialsResourceDetails();
		 return resource;
	}

	@Bean
	public OAuth2RestTemplate oAuth2RestTemplate() {
		OAuth2RestTemplate oauth2RestTemplate = new OAuth2RestTemplate(clientCredentialsResourceDetails());
		return oauth2RestTemplate;
	}

}
