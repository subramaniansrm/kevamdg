package com.kevamdg.sr.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * Location Vo Class
 * 
 * @author raathikaabm
 *
 */


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LocationVO {
	

	private Integer id;
	
	private String userLocationName;
	
	private String userLocationDetails;
		
	private String zip;
	
	private String phone;
	
	private String fax;
	
	private String email;
	
	private String contactName;
		
	private Character deleteFlag;
	
	private Date createdDate;
	
	private Integer createdBy;
	
	private Date updatedDate;
	
	private Integer updatedBy;
	
	private Character activeFlag; 
	 
	
	
    private Integer default1;

	private Integer cityId;
	
	private Integer stateId;
	
	private Integer countryId;
	
	private Integer sysAppId;
	
	private String cityName;
	
	private String stateName;
	
	private String countryName;
	
	private Integer costBookId;
	
	private String costBook;
	
	private Integer[] deleteItem;
	
	private String accessToken;
	
	

}
