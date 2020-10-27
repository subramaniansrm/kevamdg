package com.kevamdg.sr.vo;

import java.util.List;

public class UserMappingVo extends CommonVO{

	private int userMappingId;

	private int userLocationId;
	
	private int subLocationId;

	private int userDepartmentId;

	private int userRoleId;

	private int reportingUserDepartment;

	private int reportingToUser;

	private String userLocationName;
	
	private String subLocationName;
	
	private int reportingLocationId;
	
	private int reportingSubLocationId;
	
	private String reportingLocationName;
	
	private String reportingSubLocationName;
	

	private String userDepartmentName;

	private String userRoleName;

	private String reportingDepartmentName;

	private String reportingToUserName;

	private int userId;

	private String userName;

	private List<UserMappingVo> userMappingVoList;

	private int sysAppId;

	private int levelId;

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	private String levelName;

	private CommonStorageVo level;

	private List<Integer> userMappingList;

	private AuthDetailsVO kioskAuthDetailsVO;
	
	/**
	 * @return the userMappingId
	 */
	public int getUserMappingId() {
		return userMappingId;
	}

	public AuthDetailsVO getKioskAuthDetailsVO() {
		return kioskAuthDetailsVO;
	}

	public void setKioskAuthDetailsVO(AuthDetailsVO kioskAuthDetailsVO) {
		this.kioskAuthDetailsVO = kioskAuthDetailsVO;
	}

	/**
	 * @param userMappingId
	 *            the userMappingId to set
	 */
	public void setUserMappingId(int userMappingId) {
		this.userMappingId = userMappingId;
	}

	/**
	 * @return the userLocationId
	 */
	public int getUserLocationId() {
		return userLocationId;
	}

	/**
	 * @param userLocationId
	 *            the userLocationId to set
	 */
	public void setUserLocationId(int userLocationId) {
		this.userLocationId = userLocationId;
	}

	/**
	 * @return the userDepartmentId
	 */
	public int getUserDepartmentId() {
		return userDepartmentId;
	}

	/**
	 * @param userDepartmentId
	 *            the userDepartmentId to set
	 */
	public void setUserDepartmentId(int userDepartmentId) {
		this.userDepartmentId = userDepartmentId;
	}

	/**
	 * @return the userRoleId
	 */
	public int getUserRoleId() {
		return userRoleId;
	}

	/**
	 * @param userRoleId
	 *            the userRoleId to set
	 */
	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}


	/**
	 * @return the sysAppId
	 */
	public int getSysAppId() {
		return sysAppId;
	}

	/**
	 * @param sysAppId
	 *            the sysAppId to set
	 */
	public void setSysAppId(int sysAppId) {
		this.sysAppId = sysAppId;
	}

	

	/**
	 * @return the reportingToUser
	 */
	public int getReportingToUser() {
		return reportingToUser;
	}

	/**
	 * @param reportingToUser
	 *            the reportingToUser to set
	 */
	public void setReportingToUser(int reportingToUser) {
		this.reportingToUser = reportingToUser;
	}

	/**
	 * @return the userLocationName
	 */
	public String getUserLocationName() {
		return userLocationName;
	}

	/**
	 * @param userLocationName
	 *            the userLocationName to set
	 */
	public void setUserLocationName(String userLocationName) {
		this.userLocationName = userLocationName;
	}

	/**
	 * @return the userDepartmentName
	 */
	public String getUserDepartmentName() {
		return userDepartmentName;
	}

	/**
	 * @param userDepartmentName
	 *            the userDepartmentName to set
	 */
	public void setUserDepartmentName(String userDepartmentName) {
		this.userDepartmentName = userDepartmentName;
	}

	/**
	 * @return the userRoleName
	 */
	public String getUserRoleName() {
		return userRoleName;
	}

	/**
	 * @param userRoleName
	 *            the userRoleName to set
	 */
	public void setUserRoleName(String userRoleName) {
		this.userRoleName = userRoleName;
	}

	/**
	 * @return the reportingDepartmentName
	 */
	public String getReportingDepartmentName() {
		return reportingDepartmentName;
	}

	/**
	 * @param reportingDepartmentName
	 *            the reportingDepartmentName to set
	 */
	public void setReportingDepartmentName(String reportingDepartmentName) {
		this.reportingDepartmentName = reportingDepartmentName;
	}

	/**
	 * @return the reportingToUserName
	 */
	public String getReportingToUserName() {
		return reportingToUserName;
	}

	/**
	 * @param reportingToUserName
	 *            the reportingToUserName to set
	 */
	public void setReportingToUserName(String reportingToUserName) {
		this.reportingToUserName = reportingToUserName;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the reportingUserDepartment
	 */
	public int getReportingUserDepartment() {
		return reportingUserDepartment;
	}

	/**
	 * @param reportingUserDepartment
	 *            the reportingUserDepartment to set
	 */
	public void setReportingUserDepartment(int reportingUserDepartment) {
		this.reportingUserDepartment = reportingUserDepartment;
	}

	/**
	 * @return the userMappingVoList
	 */
	public List<UserMappingVo> getUserMappingVoList() {
		return userMappingVoList;
	}

	/**
	 * @param userMappingVoList
	 *            the userMappingVoList to set
	 */
	public void setUserMappingVoList(List<UserMappingVo> userMappingVoList) {
		this.userMappingVoList = userMappingVoList;
	}

	/**
	 * @return the userMappingList
	 */
	public List<Integer> getUserMappingList() {
		return userMappingList;
	}

	/**
	 * @param userMappingList
	 *            the userMappingList to set
	 */
	public void setUserMappingList(List<Integer> userMappingList) {
		this.userMappingList = userMappingList;
	}

	/**
	 * @return the levelId
	 */
	public int getLevelId() {
		return levelId;
	}

	/**
	 * @param levelId the levelId to set
	 */
	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}

	/**
	 * @return the levelName
	 */
	public String getLevelName() {
		return levelName;
	}

	/**
	 * @param levelName the levelName to set
	 */
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	/**
	 * @return the level
	 */
	public CommonStorageVo getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(CommonStorageVo level) {
		this.level = level;
	}

	public int getSubLocationId() {
		return subLocationId;
	}

	public void setSubLocationId(int subLocationId) {
		this.subLocationId = subLocationId;
	}

	public String getSubLocationName() {
		return subLocationName;
	}

	public void setSubLocationName(String subLocationName) {
		this.subLocationName = subLocationName;
	}

	public int getReportingLocationId() {
		return reportingLocationId;
	}

	public void setReportingLocationId(int reportingLocationId) {
		this.reportingLocationId = reportingLocationId;
	}

	public int getReportingSubLocationId() {
		return reportingSubLocationId;
	}

	public void setReportingSubLocationId(int reportingSubLocationId) {
		this.reportingSubLocationId = reportingSubLocationId;
	}

	public String getReportingLocationName() {
		return reportingLocationName;
	}

	public void setReportingLocationName(String reportingLocationName) {
		this.reportingLocationName = reportingLocationName;
	}

	public String getReportingSubLocationName() {
		return reportingSubLocationName;
	}

	public void setReportingSubLocationName(String reportingSubLocationName) {
		this.reportingSubLocationName = reportingSubLocationName;
	}

	
	
}
