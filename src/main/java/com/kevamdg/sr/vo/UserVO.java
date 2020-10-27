package com.kevamdg.sr.vo;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * User Vo class
 * 
 * @author raathikaabm
 *
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserVO {

	private Integer id;

	private String userEmployeeId;

	private String firstName;

	private String middleName;

	private String lastName;

	private String password;
	
	private String base64Password;

	private String currentAddress;

	private String permanentAddress;

	private String mobile;

	private String phoneNumber;

	private String emailId;

	private String skypeId;

	private String userProfile;

	private boolean activeFlag;

	private Character deleteFlag;

	private Date createdDate;

	private String createdBy;

	private Date updatedDate;

	private String updatedBy;

	private String userLoginId;

	private String url;

	private Date tokenDate;

	private String tokenContextKey;

	private Thread thread;

	private Integer commonId;

	private String itemValue;

	private Integer userDepartment;

	private Integer userRole;

	private Integer userLocation;

	private Integer subLocation;

	private Integer division;

	private String userDepartmentName;

	private String userRoleName;

	private String userLocationName;

	private String subLocationName;

	private String divisionName;

	private Integer salutationId;

	private String salutation;

	private Integer[] deleteItem;

	private String status;
	
	private Integer entity;
	
	private String accessToken;
	
	private List<UserMappingModuleVO> userMappingModuleList;

}
