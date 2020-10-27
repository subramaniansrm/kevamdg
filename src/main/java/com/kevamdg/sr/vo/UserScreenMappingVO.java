package com.kevamdg.sr.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserScreenMappingVO {

	private Integer screenId;

	private Integer subScreenId;

	private List<UserScreenFieldMappingVO> userScreenFieldMappingVOList;

	private List<UserScreenFunctionMappingVO> userScreenFunctionMappingVOList;
	
	private List<Object> screenFieldMappingVOList;

	private List<Object> screenFunctionMappingVOList;

}
