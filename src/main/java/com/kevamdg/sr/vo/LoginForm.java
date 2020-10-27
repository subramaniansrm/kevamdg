package com.kevamdg.sr.vo;

/**
 * 
 * @author Priya [SRM]
 */
public class LoginForm {

	private int userId;
	private String userLoginId;
	private String userPassword;
	private String loginStatus;
	private String oldPassword;
	private String newPassword;
	private String confirmPassword;
	private String changePasswordStatus;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserLoginId() {
		return userLoginId;
	}

	public void setUserLoginId(String userLoginId) {
		this.userLoginId = userLoginId;
	}

	/**
	 * @return the userPassword
	 */
	public String getUserPassword() {
		return userPassword;
	}

	/**
	 * @param userPassword
	 *            the userPassword to set
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	/**
	 * @return the loginStatus
	 */
	public String getLoginStatus() {
		return loginStatus;
	}

	/**
	 * @param loginStatus
	 *            the loginStatus to set
	 */
	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}

	/**
	 * @return the oldPassword
	 */
	public String getOldPassword() {
		return oldPassword;
	}

	/**
	 * @param oldPassword
	 *            the oldPassword to set
	 */
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	/**
	 * @return the newPassword
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * @param newPassword
	 *            the newPassword to set
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	/**
	 * @return the confirmPassword
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}

	/**
	 * @param confirmPassword
	 *            the confirmPassword to set
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	/**
	 * @return the changePasswordStatus
	 */
	public String getChangePasswordStatus() {
		return changePasswordStatus;
	}

	/**
	 * @param changePasswordStatus
	 *            the changePasswordStatus to set
	 */
	public void setChangePasswordStatus(String changePasswordStatus) {
		this.changePasswordStatus = changePasswordStatus;
	}

}
