package com.kevamdg.sr.vo;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * Response Class
 * 
 * @author raathikaabm
 *
 */

@Component
@Scope(value="prototype")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
	
	private String responseCode;
	
	private String responseMessage;
	
	private Object succesObject;
	
	
	
	

}
