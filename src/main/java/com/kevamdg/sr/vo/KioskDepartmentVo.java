package com.kevamdg.sr.vo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KioskDepartmentVo {
	private Integer id;
	private String userDepartmentName;
	private String description;
	private UserLocationVo userLocationMaster;
	private Character gfiLocationFlag;
	private Character deleteFlag;
	private Date createdDate;
	private String createdBy;
	private Date updatedDate;
	private String updatedBy;

	private Integer userLocation;
	private Integer systemApplication;
	private Integer[] deleteItem;

	private Map<Integer, String> userLocationMasterMap;
	private String userLocationName;
	private List<KioskDepartmentVo> departmentList;
	private int sublocationId;
	private String sublocationName;
	private String toolTipName;
}
