package com.kevamdg.sr.vo;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * @author aarthimp
 *
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScreenAuthenticationMaster extends CommonVO{
    
    private Integer screenAuthenticationId;
    private String level;
    private Character deleteFlag;
    private Date createdDate;
    private String createdBy;
    private Date updatedDate;
    private String updatedBy;
    
    private Integer screenId;
	private Integer subScreenId;
    private Integer departmentId;
    private Integer cdcCenterId;
    private Integer subLocationId;

    private Integer reportingToDepartmentId;
    private Integer roleId;
    private Integer reportingToUserId;
    private Integer userId;
    
    private String departmentName;
	private String cdcCenterName;
	private String subLocationName;

	private String screenName;
	private String subScreenName;
	private String userList;
	private String roleName;
	
	private ScreenVO screenMaster;
	private SubScreenVO subScreenMaster;
	
	private List<UserRoleMasterListDisplayVo> userRoleList;
	
	private AuthDetailsVO authDetailsVo;
	
	private List<AuthenticationListCombo> screenComboList;
private List<ScreenAuthenticationMaster> screenAuthenticationMasterList;

    private Integer[] deleteItem;
    
    //used
    private List<FieldAuthenticationMaster> fieldAuthenticationMasterList;
    private List<FunctionAuthenticationMaster> functionAuthenticationMasterList;
//    private List<AuthenticationCombo> fieldAuthenticationCombo;
//    private List<AuthenticationCombo> functionAuthenticationCombo;
	private List<String> screenMenuDisplayList;
    private List<ScreenVO> screenVoList;


    
   /* private UserDepartmentMasterVo userDepartmentMaster;
    private UserLocationMasterVo userLocationMaster;
    private UserDepartmentMasterVo reportingUserDepartmentMaster;
    private UserRoleMasterVo userRoleMaster;*/
    
    private List<AssignedScreenMaster> assignedScreenMasterList;
  /*  private List<UserLocationMasterVo> userLocationMasterList;
	private List<UserRoleMasterVo> userRoleMasterList;
	private List<UserDepartmentMasterVo> userDepartmentMasterList;*/
	private List<ScreenMaster> screenMasterList;
	
	private List<ScreenAuthenticationMasterListDisplayVo> tableListMap;
	
	
//	private List<ComboVO> cdcCombo;
//	private List<ComboVO> departmentCombo;
//	private List<ComboVO> roleCombo;
//	private List<ComboVO> screenCombo;
//	private List<ComboVO> reportingToCombo;
//	
//	
	


}
