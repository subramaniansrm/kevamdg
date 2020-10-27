package com.kevamdg.sr.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SalesOrgVO {

	private Integer id;
	
	private String salesOrgCode;
	
	private String salesOrgName;
	
	private String status;
	
	private boolean activeFlag;
	
	private Integer[] deleteItem;
	private String accessToken;
}
