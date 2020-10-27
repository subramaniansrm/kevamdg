package com.kevamdg.sr.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonVO {

	private List<String> screenFieldDisplayVoList;
	private List<String> screenFunctionDisplayList;
	private List<String> screenMenuDisplayList;
    private List<ScreenVO> screenVoList;
    private List<ScreenMMVO> screenMMVoList;
    private List<ScreenMMVO> screenCMVoList;


    private Integer limit;
    //private Integer userId;
    private Integer entityId;
	private String userName;
	private ScreenAuthorizationVO screenAuthorizationMaster;
	private Integer screenId;
	private Integer subScreenId;
	private Integer id;//id for view method
	
	  private Integer requestId;

}