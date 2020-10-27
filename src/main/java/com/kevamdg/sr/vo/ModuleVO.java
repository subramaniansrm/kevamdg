package com.kevamdg.sr.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ModuleVO {

	private Integer moduleId;
	
	private String moduleCode;
	
	private String moduleName;
	
	private String moduleTitle;
	
	private boolean activeFlag;

}
