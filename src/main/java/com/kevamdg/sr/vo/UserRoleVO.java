package com.kevamdg.sr.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRoleVO {
	
	private Integer id;
	private String userRoleName;
	private String description;
	
	private Integer userDepartment;
	private Integer userLocation;
	
	private Integer[] deleteItem;
	private Integer roleType;
	private String roleTypeName;
	private String userDepartmentName;
	private String userLocationName;
	private Integer sublocationId;
	private String sublocationName;
	
	private String accessToken;


}
