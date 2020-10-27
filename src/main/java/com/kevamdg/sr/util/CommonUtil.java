package com.kevamdg.sr.util;

import java.util.Base64;
import java.util.Map;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.kevamdg.sr.auth.AuthUtil;
import com.kevamdg.sr.config.CommonException;



/**
 * 
 *
 */
public class CommonUtil {
	
	
	private static final String DUPLICATE_CODE ="DUPLICATE_CODE";
	private static final String DUPLICATE_CODE_MULTIPLE ="DUPLICATE_CODE_MULTIPLE";
	/**
	 * 
	 * 
	 * @param orginalValue user input as String
	 * @param encodedValue logged user's encoded password fetch from database 
	 * @return true match found, return false match not  found  
	 */

	public static Boolean strEncodedValMatcher(String orginalValue, String encodedValue) {
		BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
		Boolean matchcheck = false;
		if (orginalValue != null && encodedValue != null) {
			matchcheck = pwEncoder.matches(orginalValue, encodedValue);
		}
		return matchcheck;
	}
	
	public static boolean tokenValidate() throws CommonException{
		if(null != AuthUtil.getUserId()){
			return false;
			
		}
		return true;
	}
	
	
	

	/**
	 * 
	 * @param newPassword
	 * @return
	 */
	public static String encodeString(String originalValue) {
		BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
		String encodedValue = null;
		if (originalValue != null) {
			encodedValue = pwEncoder.encode(originalValue);
		}
		return encodedValue;
	}
	
	
	

	public static String encode(String value) {
		return Base64.getEncoder().encodeToString(value.getBytes());
	}
	
	public static String decode(String value) {
		return new String(Base64.getDecoder().decode(value));
	}
	
	public static String uuidGeneration() {
		
		UUID uuid = UUID.randomUUID();
			
		return uuid.toString();
	}
	
	/* Academic coding start */
	/**
	 * This method is used to set the parameter in the JPA query
	 * @param query - JPA Query object
	 * @param paramMap- input parameter map
	 */
	public static void setQueryParam(javax.persistence.Query query, Map<String, Object> paramMap) {
		for (String key : paramMap.keySet()) {
			query.setParameter(key, paramMap.get(key));
		}
	}
	
	
}
