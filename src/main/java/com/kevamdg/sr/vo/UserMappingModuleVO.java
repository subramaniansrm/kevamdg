package com.kevamdg.sr.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserMappingModuleVO {

	private Integer id;

	private Integer userId;

	private Integer moduleId;

	private String userName;

	private String moduleName;
	
	private String moduleTitle;

	private boolean activeFlag;

}
