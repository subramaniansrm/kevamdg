package com.kevamdg.sr.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * User Entity
 * 
 * @author raathikaabm
 *
 */


@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "user", schema = "common_mdg")
public class UserEntity extends CommonEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private Integer id;

	@Column(name = "USER_EMPLOYEE_ID")
	private String userEmployeeId;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "MIDDLE_NAME")
	private String middleName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "BASE_64_PASSWORD")
	private String base64Password;

	@Column(name = "SALUTATION")
	private Integer salutationId;

	@Column(name = "CURRENT_ADDRESS")
	private String currentAddress;

	@Column(name = "PERMANENT_ADDRESS")
	private String permanentAddress;

	@Column(name = "MOBILE")
	private String mobile;

	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;

	@Column(name = "EMAIL_ID")
	private String emailId;

	@Column(name = "SKYPE_ID")
	private String skypeId;

	@Column(name = "USER_PROFILE")
	private String userProfile;

	@Column(name = "THEME")
	private String theme;

	@Column(name = "PREFIX")
	private String prefix;
	
	@Column(name = "ACCESS_TOKEN")
	private String accessToken;

	@Column(name = "ACTIVE")
	private boolean activeFlag;

	
	@Column(name = "USER_LOGIN_ID", updatable = false)
	private String userName;

	@Column(name = "USER_DEPARTMENT_ID")
	private Integer departmentId;

	@Column(name = "USER_LOCATION_ID")
	private Integer locationId;

	@Column(name = "USER_SUBLOCATION_ID")
	private Integer sublocationId;

	@Column(name = "USER_ROLE_ID")
	private Integer roleId;

	@Column(name = "DIVISION_ID")
	private Integer divisionId;

	@Column(name = "APPLICATION_PROFILE_ID")
	private Integer applicationProfileId;

	@Column(name = "COST_BOOK_ID")
	private Integer costBookId;

}
