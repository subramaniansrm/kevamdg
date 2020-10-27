package com.kevamdg.sr.vo;
 
import java.util.Date;
import java.util.List;

public class UserMasterVo {
	
	private Integer id;
	private String userEmployeeId;
	private String firstName;
	private String middleName;
	private String lastName;
	private String password;
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
	
	private int commonId;
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
	private DepartmentVO userDepartmentMaster;
	private UserRoleVO userRoleMaster;
	private UserLocationVo userLocationMaster;
	private SubLocationVO subLocationMaster;
	private DivisionMasterVo divisionMaster;	
	private Integer[] deleteItem;
	private List<UserMasterVo> userMasterVoList;
	private String status;
	
	/**
	 * @return the userLoginId
	 */
	public String getUserLoginId() {
		return userLoginId;
	}

	/**
	 * @param userLoginId the userLoginId to set
	 */
	public void setUserLoginId(String userLoginId) {
		this.userLoginId = userLoginId;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName
	 *            the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the currentAddress
	 */
	public String getCurrentAddress() {
		return currentAddress;
	}

	/**
	 * @param currentAddress
	 *            the currentAddress to set
	 */
	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}

	/**
	 * @return the permanentAddress
	 */
	public String getPermanentAddress() {
		return permanentAddress;
	}

	/**
	 * @param permanentAddress
	 *            the permanentAddress to set
	 */
	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile
	 *            the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber
	 *            the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId
	 *            the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	/**
	 * @return the activeFlag
	 */
	

	/**
	 * @return the deleteFlag
	 */
	public Character getDeleteFlag() {
		return deleteFlag;
	}

	public boolean isActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(boolean activeFlag) {
		this.activeFlag = activeFlag;
	}

	/**
	 * @param deleteFlag
	 *            the deleteFlag to set
	 */
	public void setDeleteFlag(Character deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate
	 *            the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy
	 *            the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the updatedDate
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * @param updatedDate
	 *            the updatedDate to set
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * @return the updatedBy
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * @param updatedBy
	 *            the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	
	/**
	 * @return the userDepartmentMaster
	 */
	public DepartmentVO getUserDepartmentMaster() {
		return userDepartmentMaster;
	}

	/**
	 * @param userDepartmentMaster
	 *            the userDepartmentMaster to set
	 */
	public void setUserDepartmentMaster(DepartmentVO userDepartmentMaster) {
		this.userDepartmentMaster = userDepartmentMaster;
	}

	/**
	 * @return the userRoleMaster
	 */
	public UserRoleVO getUserRoleMaster() {
		return userRoleMaster;
	}

	/**
	 * @param userRoleMaster
	 *            the userRoleMaster to set
	 */
	public void setUserRoleMaster(UserRoleVO userRoleMaster) {
		this.userRoleMaster = userRoleMaster;
	}

	/**
	 * @return the userLocationMaster
	 */
	public UserLocationVo getUserLocationMaster() {
		return userLocationMaster;
	}

	/**
	 * @param userLocationMaster
	 *            the userLocationMaster to set
	 */
	public void setUserLocationMaster(UserLocationVo userLocationMaster) {
		this.userLocationMaster = userLocationMaster;
	}	 

	/**
	 * @return the divisionMaster
	 */
	public DivisionMasterVo getDivisionMaster() {
		return divisionMaster;
	}

	/**
	 * @param divisionMaster
	 *            the divisionMaster to set
	 */
	public void setDivisionMaster(DivisionMasterVo divisionMaster) {
		this.divisionMaster = divisionMaster;
	} 
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}	 

	/**
	 * @return the deleteItem
	 */
	public Integer[] getDeleteItem() {
		return deleteItem;
	}

	/**
	 * @param deleteItem
	 *            the deleteItem to set
	 */
	public void setDeleteItem(Integer[] deleteItem) {
		this.deleteItem = deleteItem;
	}


	/**
	 * @return the salutationId
	 */
	public Integer getSalutationId() {
		return salutationId;
	}

	/**
	 * @param salutationId
	 *            the salutationId to set
	 */
	public void setSalutationId(Integer salutationId) {
		this.salutationId = salutationId;
	}

	/**
	 * @param salutation
	 *            the salutation to set
	 */
	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	/**
	 * @return the salutation
	 */
	public String getSalutation() {
		return salutation;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getTokenDate() {
		return tokenDate;
	}

	public void setTokenDate(Date tokenDate) {
		this.tokenDate = tokenDate;
	}

	public String getTokenContextKey() {
		return tokenContextKey;
	}

	public void setTokenContextKey(String tokenContextKey) {
		this.tokenContextKey = tokenContextKey;
	}

	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

	public Integer getUserDepartment() {
		return userDepartment;
	}

	public void setUserDepartment(Integer userDepartment) {
		this.userDepartment = userDepartment;
	}

	public Integer getUserRole() {
		return userRole;
	}

	public void setUserRole(Integer userRole) {
		this.userRole = userRole;
	}

	public Integer getUserLocation() {
		return userLocation;
	}

	public void setUserLocation(Integer userLocation) {
		this.userLocation = userLocation;
	}

	public Integer getDivision() {
		return division;
	}

	public void setDivision(Integer division) {
		this.division = division;
	}

	public String getUserDepartmentName() {
		return userDepartmentName;
	}

	public void setUserDepartmentName(String userDepartmentName) {
		this.userDepartmentName = userDepartmentName;
	}

	public String getUserRoleName() {
		return userRoleName;
	}

	public void setUserRoleName(String userRoleName) {
		this.userRoleName = userRoleName;
	}

	public String getUserLocationName() {
		return userLocationName;
	}

	public void setUserLocationName(String userLocationName) {
		this.userLocationName = userLocationName;
	}

	public String getDivisionName() {
		return divisionName;
	}

	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}

	/**
	 * @return the userMasterVoList
	 */
	public List<UserMasterVo> getUserMasterVoList() {
		return userMasterVoList;
	}

	/**
	 * @param userMasterVoList the userMasterVoList to set
	 */
	public void setUserMasterVoList(List<UserMasterVo> userMasterVoList) {
		this.userMasterVoList = userMasterVoList;
	}

	/**
	 * @return the commonId
	 */
	public int getCommonId() {
		return commonId;
	}

	/**
	 * @param commonId the commonId to set
	 */
	public void setCommonId(int commonId) {
		this.commonId = commonId;
	}

	/**
	 * @return the itemValue
	 */
	public String getItemValue() {
		return itemValue;
	}

	/**
	 * @param itemValue the itemValue to set
	 */
	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}

	public Integer getSubLocation() {
		return subLocation;
	}

	public void setSubLocation(Integer subLocation) {
		this.subLocation = subLocation;
	}

	public String getSubLocationName() {
		return subLocationName;
	}

	public void setSubLocationName(String subLocationName) {
		this.subLocationName = subLocationName;
	}

	public SubLocationVO getSubLocationMaster() {
		return subLocationMaster;
	}

	public void setSubLocationMaster(SubLocationVO subLocationMaster) {
		this.subLocationMaster = subLocationMaster;
	}

	/**
	 * @return the skypeId
	 */
	public String getSkypeId() {
		return skypeId;
	}

	/**
	 * @param skypeId the skypeId to set
	 */
	public void setSkypeId(String skypeId) {
		this.skypeId = skypeId;
	}

	/**
	 * @return the userProfile
	 */
	public String getUserProfile() {
		return userProfile;
	}

	/**
	 * @param userProfile the userProfile to set
	 */
	public void setUserProfile(String userProfile) {
		this.userProfile = userProfile;
	}

	/**
	 * @return the userEmployeeId
	 */
	public String getUserEmployeeId() {
		return userEmployeeId;
	}

	/**
	 * @param userEmployeeId the userEmployeeId to set
	 */
	public void setUserEmployeeId(String userEmployeeId) {
		this.userEmployeeId = userEmployeeId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

	
}
