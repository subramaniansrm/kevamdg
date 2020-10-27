package com.kevamdg.sr.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MappingAuthenticationVO {

	private List<UserScreenMappingVO> userScreenMappingVOList;

	private Integer userId;

}
