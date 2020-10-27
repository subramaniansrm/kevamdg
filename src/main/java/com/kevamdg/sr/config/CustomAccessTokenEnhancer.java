package com.kevamdg.sr.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import com.kevamdg.sr.auth.AuthUserInfo;


public class CustomAccessTokenEnhancer implements TokenEnhancer {

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Authentication userAuthentication = authentication.getUserAuthentication();
		if (userAuthentication != null) {
			Object principal = authentication.getUserAuthentication().getPrincipal();
			if (principal instanceof CustomUserDetails) {
				Map<String, Object> additionalInfo = new HashMap<>();
				AuthUserInfo authUserInfo=new AuthUserInfo();
				authUserInfo.setCustomUserDetails((CustomUserDetails)principal);
				additionalInfo.put("userDetails", principal);
				TokenManager.tokenMap.put("auth", authUserInfo);
				((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
			}
		}
		return accessToken;
	}
}