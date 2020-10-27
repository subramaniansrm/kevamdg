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
public class CommonDropDownVO {
	
	private Integer id;
	
	private String name;
	
	private String code;
	
	private String parentCode;
	
	private Integer userId;
	
	private Integer plantMapId;

	private boolean defaultId;

	private boolean activeFlag;

}
