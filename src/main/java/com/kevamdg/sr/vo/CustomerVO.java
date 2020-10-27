package com.kevamdg.sr.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerVO {

	private Integer id;

	private String customerCode;

	private String customerName;

	private Integer salesOrgId;

	private Integer plantId;

	private Integer distributionId;
	
	private Integer cityId;

	private String salesOrgName;

	private String plantName;

	private String distributionName;
	
	private String cityName;

	private String areaNagar;

	private String parentCode;

	private String parentName;
	private boolean activeFlag;

	private String status;

	private Integer[] deleteItem;
	private String accessToken;

}
