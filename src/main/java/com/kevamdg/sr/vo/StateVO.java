package com.kevamdg.sr.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * State Vo class
 * @author raathikaabm
 *
 */


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StateVO  {
	
	
	private Integer stateId;
	
	private String stateName;
	
	private Integer sysAppId;
	
	private Integer countryId;
	
	private String countryName;
	
	private boolean deleteFlag;
	
	private Integer createdBy;
	
	private Date createdDate;
	
	private Integer updatedBy;
	
	private Date updatedDate;
	
	private Integer[] deleteItem;

	private String accessToken;
	
	

}
