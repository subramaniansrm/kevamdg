package com.kevamdg.sr.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ScreenAuthorizationMaster extends CommonVO implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer screenAuthorizationId;
	private Character deleteFlag;
	private Date createdDate;
	private String createdBy;
	private Date updatedDate;
	private String updatedBy;
	
	private Integer screenId;
	private Integer subScreenId;
	private Integer departmentId;
	private Integer cdcCenterId;
	private Integer roleId;

	private ScreenMaster screenMaster;
	private SubScreenMaster subScreenMaster;
	/*private UserDepartmentMasterVo userDepartmentMaster;
	private UserLocationMasterVo userLocationMaster;
	private UserRoleMasterVo userRoleMaster;*/
	private Map<String, String> fieldMap;
	
	private List<FieldAuthenticationMaster> fieldAuthenticationMasterList;
	private List<FunctionAuthenticationMaster> functionAuthenticationMasterList;
	/*private List<UserLocationMasterVo> userLocationMasterList;
	private List<UserRoleMasterVo> userRoleMasterList;
	private List<UserDepartmentMasterVo> userDepartmentMasterList;*/
	private List<ScreenMaster> screenMasterList;
	private List<SubScreenMaster> subScreenMasterList;
	private List<ScreenFunctionMaster> screenFunctionMasterList;
	private List<ScreenFieldMaster> screenFieldMasterList;
	
	private List<ScreenAuthorizationMasterListDisplayVO> tableListMap;
	
	
	
	/**
	 * @return the screenMaster
	 */
	public ScreenMaster getScreenMaster() {
		return screenMaster;
	}
	/**
	 * @param screenMaster the screenMaster to set
	 */
	public void setScreenMaster(ScreenMaster screenMaster) {
		this.screenMaster = screenMaster;
	}
	/**
	 * @return the subScreenMaster
	 */
	public SubScreenMaster getSubScreenMaster() {
		return subScreenMaster;
	}
	/**
	 * @param subScreenMaster the subScreenMaster to set
	 */
	public void setSubScreenMaster(SubScreenMaster subScreenMaster) {
		this.subScreenMaster = subScreenMaster;
	}
	/**
	 * @return the screenAuthorizationId
	 */
	public Integer getScreenAuthorizationId() {
		return screenAuthorizationId;
	}
	/**
	 * @param screenAuthorizationId the screenAuthorizationId to set
	 */
	public void setScreenAuthorizationId(Integer screenAuthorizationId) {
		this.screenAuthorizationId = screenAuthorizationId;
	}
	/**
	 * @return the deleteFlag
	 */
	public Character getDeleteFlag() {
		return deleteFlag;
	}
	/**
	 * @param deleteFlag the deleteFlag to set
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
	 * @param createdDate the createdDate to set
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
	 * @param createdBy the createdBy to set
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
	 * @param updatedDate the updatedDate to set
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
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	/**
	 * @return the screenId
	 */
	public Integer getScreenId() {
		return screenId;
	}
	/**
	 * @param screenId the screenId to set
	 */
	public void setScreenId(Integer screenId) {
		this.screenId = screenId;
	}
	/**
	 * @return the subScreenId
	 */
	public Integer getSubScreenId() {
		return subScreenId;
	}
	/**
	 * @param subScreenId the subScreenId to set
	 */
	public void setSubScreenId(Integer subScreenId) {
		this.subScreenId = subScreenId;
	}
	/**
	 * @return the departmentId
	 */
	public Integer getDepartmentId() {
		return departmentId;
	}
	/**
	 * @param departmentId the departmentId to set
	 */
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	/**
	 * @return the cdcCenterId
	 */
	public Integer getCdcCenterId() {
		return cdcCenterId;
	}
	/**
	 * @param cdcCenterId the cdcCenterId to set
	 */
	public void setCdcCenterId(Integer cdcCenterId) {
		this.cdcCenterId = cdcCenterId;
	}
	/**
	 * @return the roleId
	 */
	public Integer getRoleId() {
		return roleId;
	}
	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	/**
	 * @return the fieldMap
	 */
	public Map<String, String> getFieldMap() {
		return fieldMap;
	}
	/**
	 * @param fieldMap the fieldMap to set
	 */
	public void setFieldMap(Map<String, String> fieldMap) {
		this.fieldMap = fieldMap;
	}
	/**
	 * @return the fieldAuthenticationMasterList
	 */
	public List<FieldAuthenticationMaster> getFieldAuthenticationMasterList() {
		return fieldAuthenticationMasterList;
	}
	/**
	 * @param fieldAuthenticationMasterList the fieldAuthenticationMasterList to set
	 */
	public void setFieldAuthenticationMasterList(List<FieldAuthenticationMaster> fieldAuthenticationMasterList) {
		this.fieldAuthenticationMasterList = fieldAuthenticationMasterList;
	}
	/**
	 * @return the functionAuthenticationMasterList
	 */
	public List<FunctionAuthenticationMaster> getFunctionAuthenticationMasterList() {
		return functionAuthenticationMasterList;
	}
	/**
	 * @param functionAuthenticationMasterList the functionAuthenticationMasterList to set
	 */
	public void setFunctionAuthenticationMasterList(List<FunctionAuthenticationMaster> functionAuthenticationMasterList) {
		this.functionAuthenticationMasterList = functionAuthenticationMasterList;
	}
	/**
	 * @return the screenMasterList
	 */
	public List<ScreenMaster> getScreenMasterList() {
		return screenMasterList;
	}
	/**
	 * @param screenMasterList the screenMasterList to set
	 */
	public void setScreenMasterList(List<ScreenMaster> screenMasterList) {
		this.screenMasterList = screenMasterList;
	}
	/**
	 * @return the subScreenMasterList
	 */
	public List<SubScreenMaster> getSubScreenMasterList() {
		return subScreenMasterList;
	}
	/**
	 * @param subScreenMasterList the subScreenMasterList to set
	 */
	public void setSubScreenMasterList(List<SubScreenMaster> subScreenMasterList) {
		this.subScreenMasterList = subScreenMasterList;
	}
	/**
	 * @return the screenFunctionMasterList
	 */
	public List<ScreenFunctionMaster> getScreenFunctionMasterList() {
		return screenFunctionMasterList;
	}
	/**
	 * @param screenFunctionMasterList the screenFunctionMasterList to set
	 */
	public void setScreenFunctionMasterList(List<ScreenFunctionMaster> screenFunctionMasterList) {
		this.screenFunctionMasterList = screenFunctionMasterList;
	}
	/**
	 * @return the screenFieldMasterList
	 */
	public List<ScreenFieldMaster> getScreenFieldMasterList() {
		return screenFieldMasterList;
	}
	/**
	 * @param screenFieldMasterList the screenFieldMasterList to set
	 */
	public void setScreenFieldMasterList(List<ScreenFieldMaster> screenFieldMasterList) {
		this.screenFieldMasterList = screenFieldMasterList;
	}
	/**
	 * @return the tableListMap
	 */
	public List<ScreenAuthorizationMasterListDisplayVO> getTableListMap() {
		return tableListMap;
	}
	/**
	 * @param tableListMap the tableListMap to set
	 */
	public void setTableListMap(List<ScreenAuthorizationMasterListDisplayVO> tableListMap) {
		this.tableListMap = tableListMap;
	}
	
	
	
}
