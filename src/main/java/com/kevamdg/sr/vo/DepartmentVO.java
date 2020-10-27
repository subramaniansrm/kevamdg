package com.kevamdg.sr.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * DepartmentVo
 * @author raathikaabm
 *
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DepartmentVO {

private Integer departmentId;

	private String departmentName;
	
	private String description;
	
	private Boolean deleteFlag;
	
	private Date createdDate;
	
	private Integer createdBy;
	
	private Date updatedDate;
	
	private Integer updatedBy;
	
	private Integer locationId;
	
	private String locationName;
	
	private Integer sublocationId;
	
	private String sublocationName;

	private Integer sysAppId;
	
	private Integer[] deleteItem;

	private String accessToken;


}
