package com.kevamdg.sr.vo;

import lombok.Data;

@Data
public class AuthDetailsVO {
	
	
	private Integer userId;
	private String firstName;
	private String lastName;
	private Integer entityId;
	private Integer roleId;
	private String langCode;
	private String userName;
	private Integer locationId;
	private Integer departmentId;
	private Integer subLocationId;

}
