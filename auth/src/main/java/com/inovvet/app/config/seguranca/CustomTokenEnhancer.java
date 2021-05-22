package com.inovvet.app.config.seguranca;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import com.inovvet.core.entity.User;

public class CustomTokenEnhancer implements TokenEnhancer {

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		if (!authentication.isClientOnly()) {
			User user = (User) authentication.getPrincipal();
			final Map<String, Object> additionalInfo = new HashMap<>();

			additionalInfo.put("ctoken", user.getConta_token());
			additionalInfo.put("utoken", user.getToken());

			((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
		}
		return accessToken;

	}

}
