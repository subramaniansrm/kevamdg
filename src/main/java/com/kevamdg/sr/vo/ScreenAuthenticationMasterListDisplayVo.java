package com.kevamdg.sr.vo;

import java.io.Serializable;
import java.util.List;

public class ScreenAuthenticationMasterListDisplayVo extends CommonVO {

	private Integer screenAuthenticationId;
	private String departmentName;
	private String cdcCenterName;
	private String screenName;
	private String subScreenName;

	private String roleName;
	private List<ScreenAuthenticationMasterListDisplayVo> screenAuthenticationMasterListDisplayVoList;

	/**
	 * @return the screenAuthenticationId
	 */
	public Integer getScreenAuthenticationId() {
		return screenAuthenticationId;
	}

	/**
	 * @param screenAuthenticationId
	 *            the screenAuthenticationId to set
	 */
	public void setScreenAuthenticationId(Integer screenAuthenticationId) {
		this.screenAuthenticationId = screenAuthenticationId;
	}

	/**
	 * @return the departmentName
	 */
	public String getDepartmentName() {
		return departmentName;
	}

	/**
	 * @param departmentName
	 *            the departmentName to set
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}



	/**
	 * @return the screenName
	 */
	public String getScreenName() {
		return screenName;
	}

	/**
	 * @param screenName
	 *            the screenName to set
	 */
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName
	 *            the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * @return the screenAuthenticationMasterListDisplayVoList
	 */
	public List<ScreenAuthenticationMasterListDisplayVo> getScreenAuthenticationMasterListDisplayVoList() {
		return screenAuthenticationMasterListDisplayVoList;
	}

	/**
	 * @param screenAuthenticationMasterListDisplayVoList
	 *            the screenAuthenticationMasterListDisplayVoList to set
	 */
	public void setScreenAuthenticationMasterListDisplayVoList(
			List<ScreenAuthenticationMasterListDisplayVo> screenAuthenticationMasterListDisplayVoList) {
		this.screenAuthenticationMasterListDisplayVoList = screenAuthenticationMasterListDisplayVoList;
	}

	/**
	 * @return the cdcCenterName
	 */
	public String getCdcCenterName() {
		return cdcCenterName;
	}

	/**
	 * @param cdcCenterName the cdcCenterName to set
	 */
	public void setCdcCenterName(String cdcCenterName) {
		this.cdcCenterName = cdcCenterName;
	}

	/**
	 * @return the subScreenName
	 */
	public String getSubScreenName() {
		return subScreenName;
	}

	/**
	 * @param subScreenName the subScreenName to set
	 */
	public void setSubScreenName(String subScreenName) {
		this.subScreenName = subScreenName;
	}

}
