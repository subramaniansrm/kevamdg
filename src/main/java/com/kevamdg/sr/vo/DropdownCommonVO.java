package com.kevamdg.sr.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DropdownCommonVO {

	
	private Integer id;
	
	private Integer fieldId;
	
	private Integer salesOrgId;
	
	private String dropdownCode;
	
	private String dropdownName;
	
	private String fieldName;
	
	private String salesOrgName;
	
	private String status;

	private boolean activeFlag;
	
	private Integer[] deleteItem;
	private String accessToken;
	
}
