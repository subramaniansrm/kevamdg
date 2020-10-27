package com.kevamdg.sr.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserScreenFunctionMappingVO {

	private Integer screenFunctionId;

	private Integer subScreenId;

	private Integer subScreenName;

	private String screenFunctionName;

}
