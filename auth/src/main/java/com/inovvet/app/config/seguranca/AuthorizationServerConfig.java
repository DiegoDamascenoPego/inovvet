package com.inovvet.app.config.seguranca;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	@Lazy
	private AuthenticationManager authenticationManager;

	@Autowired
	private DataSource datasource;

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.checkTokenAccess("permitAll()");
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.jdbc(datasource);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

		endpoints.tokenStore(tokenStore()).reuseRefreshTokens(false).authenticationManager(authenticationManager)
				.exceptionTranslator(webResponseExceptionTranslator())
				.tokenEnhancer(tokenEnhancer());
	}
	
	 @Bean
	    public TokenEnhancer tokenEnhancer() {
	        return new CustomTokenEnhancer();
	    }


	@Bean
	public TokenStore tokenStore() {
		return new JdbcTokenStore(datasource);
	}

	@Bean
	public WebResponseExceptionTranslator webResponseExceptionTranslator() {
		return new DefaultWebResponseExceptionTranslator() {

			@Override
			public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
				ResponseEntity<OAuth2Exception> responseEntity = super.translate(e);

				return new ResponseEntity<>(
						new OAuth2Exception(new String("Usuário e/ou senha inválidos".getBytes("UTF-8"))),
						responseEntity.getStatusCode());
			}
		};
	}

	@Bean
	public SecurityContextHolderAwareRequestFilter securityContextHolderAwareRequestFilter() {
		return new SecurityContextHolderAwareRequestFilter();
	}
}
