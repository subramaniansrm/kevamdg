package com.kevamdg.sr.auth;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import com.kevamdg.sr.config.CustomUserDetails;
import com.kevamdg.sr.config.TokenManager;
import com.kevamdg.sr.security.OAuth2AuthenticationUser;

public class AuthUtil {
	
	/**
	 * Static Method used to get user details.
	 * 
	 * @return AuthUserInfo
	 */
	public static AuthUserInfo getAuthUserInfo() {
		return TokenManager.getAuthUserInfo();
	}

	
	public static CustomUserDetails getUserDetails() {
		CustomUserDetails customUserDetails=null;
		if ((!(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken))) {
			OAuth2AuthenticationUser authentication = (OAuth2AuthenticationUser) SecurityContextHolder.getContext()
					.getAuthentication();
			if (authentication != null) {
				 customUserDetails = authentication.getCustomUserDetails();
				
			}
		}
			return customUserDetails;
	}

	/**
	 * Static Method used to get the user Id.
	 * 
	 * @return Integer
	 */
	public static Integer getUserId() {
		AuthUserInfo authUserInfo = getAuthUserInfo();
		if (authUserInfo != null) {
			return authUserInfo.getCustomUserDetails().getUserId();
		}else {
		return getUserDetails().getUserId(); 
		}
	}
	

	/**
	 * Static Method used to get the entity Id.
	 * 
	 * @return Integer
	 */
	public static Integer getEntityId() {
		AuthUserInfo authUserInfo = getAuthUserInfo();
		if (authUserInfo != null) {
			return authUserInfo.getCustomUserDetails().getEntityId();
		}else {
		return getUserDetails().getEntityId(); 
		}
	}
	
	
	/**
	 * Static Method used to get the entity Id.
	 * 
	 * @return Integer
	 */
	public static Integer getRoleId() {
		AuthUserInfo authUserInfo = getAuthUserInfo();
		if (authUserInfo != null) {
			return authUserInfo.getCustomUserDetails().getRoleId();
		}else {
		return getUserDetails().getRoleId(); 
		}
	}
	
	
	/**
	 * Static Method used to get the user name.
	 * 
	 * @return String 
	 */
	public static String getUserName() {
		AuthUserInfo authUserInfo = getAuthUserInfo();
		if (authUserInfo != null) {
			return authUserInfo.getCustomUserDetails().getFirstName()+" "+authUserInfo.getCustomUserDetails().getLastName();
		}else {
		return getUserDetails().getFirstName()+" "+getUserDetails().getLastName(); 
		}
	}
}
