package com.kevamdg.sr.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * COmmon Vo for all drop down
 * @author raathikaabm
 *
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonRoleDropDownVO {
	
	private Integer roleId;
	
	private String name;
	
	private String code;
	
	private boolean activeFlag;
	
	private String userName;

}
