package com.kevamdg.sr.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonScreenFieldVO {

	
	private Integer id;
	
	private String screenFieldName;
	
	private boolean activeFlag;
	
	private Integer[] deleteItem;
	private String accessToken;
	
}
