package com.kevamdg.sr.vo;

public class AuthenticationVo {

	// Field Authentication
	private Integer authenticationId;
	private Integer screenId;
	private Integer roleId;
	private Integer addFlag;
	private Integer modifyFlag;
	private Integer deleteFlag;
	private Integer viewFlag;
	private String roleName;
	private String screenName;

	public Integer getAuthenticationId() {
		return authenticationId;
	}

	public void setAuthenticationId(Integer authenticationId) {
		this.authenticationId = authenticationId;
	}

	public Integer getScreenId() {
		return screenId;
	}

	public void setScreenId(Integer screenId) {
		this.screenId = screenId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getAddFlag() {
		return addFlag;
	}

	public void setAddFlag(Integer addFlag) {
		this.addFlag = addFlag;
	}

	public Integer getModifyFlag() {
		return modifyFlag;
	}

	public void setModifyFlag(Integer modifyFlag) {
		this.modifyFlag = modifyFlag;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Integer getViewFlag() {
		return viewFlag;
	}

	public void setViewFlag(Integer viewFlag) {
		this.viewFlag = viewFlag;
	}

	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * @return the screenName
	 */
	public String getScreenName() {
		return screenName;
	}

	/**
	 * @param screenName the screenName to set
	 */
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	

}
