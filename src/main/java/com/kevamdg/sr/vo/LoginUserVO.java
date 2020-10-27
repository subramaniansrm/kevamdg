package com.kevamdg.sr.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginUserVO {

	private String username;

	private String password;

	private Integer roleId;

	private Integer LocationId;

	private Integer groupId;

	private Integer departmentId;

	private Integer userId;

	private String logoutStatus;
	
	private String grantType;

}
