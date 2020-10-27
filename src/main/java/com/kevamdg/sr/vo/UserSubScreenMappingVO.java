package com.kevamdg.sr.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserSubScreenMappingVO {
	
	
	private Integer id;
	
	private Integer userId;
	
	private Integer screenId;
	
	private Integer subScreenId;
	
	private Integer subScreenName;
	
	private String screenName;
	
	private boolean activeFlag;

}
