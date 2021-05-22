package com.inovvet.core.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth")
public class AuthResource {

	@Autowired
    private TokenStore tokenStore;
	
	@PostMapping("/logout")
	@ResponseStatus(HttpStatus.OK)
	public void revoke(HttpServletRequest request) {
	    try {
	        String authorization = request.getHeader("Authorization");
	        if (authorization != null && authorization.contains("Bearer")) {
	            String tokenValue = authorization.replace("Bearer", "").trim();

	            OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
	            tokenStore.removeAccessToken(accessToken);

	            //OAuth2RefreshToken refreshToken = tokenStore.readRefreshToken(tokenValue);
	            OAuth2RefreshToken refreshToken = accessToken.getRefreshToken();
	            tokenStore.removeRefreshToken(refreshToken);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
