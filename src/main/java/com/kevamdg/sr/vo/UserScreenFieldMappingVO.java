package com.kevamdg.sr.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserScreenFieldMappingVO {

	private Integer screenFieldId;

	private Integer subScreenId;

	private Integer subScreenName;

	private String screenFieldName;

}
