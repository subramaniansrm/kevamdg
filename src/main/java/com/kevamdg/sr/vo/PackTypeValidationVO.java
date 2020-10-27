package com.kevamdg.sr.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PackTypeValidationVO {
	
	private Integer id;
	
	private Integer salesOrgId;
	
	private String salesOrgName;
	
	private Integer packSize;
	
	private String packSizeName;
	
	private String packTypeName;
	
	private boolean activeFlag;
	
	private String status;
	
	private Integer[] deleteItem;

}
