package com.kevamdg.sr.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * @author vigneshs
 *
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CountryVO {

	

	private Integer id;
	private String countryCode;
	private String country;
	
	private boolean deleteFlag;
	private Date createdDate;
	private String createdBy;
	private Date updatedDate;
	private String updatedBy;
	
	private Integer[] deleteItem;
	private String accessToken;
}
