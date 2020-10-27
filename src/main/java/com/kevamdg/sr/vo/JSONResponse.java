package com.kevamdg.sr.vo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@JsonInclude(Include.NON_NULL)
@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class JSONResponse {
	
	private String responseCode;
	private String responseMessage;
	private Object authSuccesObject;
	private Object succesObject;
	private Object cmSuccesObject;
	private String userName;

}
