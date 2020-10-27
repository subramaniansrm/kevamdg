package com.kevamdg.sr.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "rin_ma_phone_book")
public class PhoneBookEntity  {

	/**
	 * @return the createBy
	 */
	public Integer getCreateBy() {
		return createBy;
	}

	/**
	 * @param createBy the createBy to set
	 */
	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the updateBy
	 */
	public Integer getUpdateBy() {
		return updateBy;
	}

	/**
	 * @param updateBy the updateBy to set
	 */
	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}

	/**
	 * @return the updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * @return the deleteFlag
	 */
	public boolean isDeleteFlag() {
		return deleteFlag;
	}

	/**
	 * @param deleteFlag the deleteFlag to set
	 */
	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idrin_ma_phone_book_id")
	private int phoneBookId;

	@Column(name = "rin_ma_phone_book_employee_id")
	private String employeeId;

	@Column(name = "rin_ma_phone_book_employee_name")
	private String employeeName;

	@Column(name = "rin_ma_phone_book_department_id")
	private int userDepartmentId;

	@Column(name = "rin_ma_phone_book_location_id")
	private int userLocationId;

	@Column(name = "rin_ma_phone_book_sublocation_id")
	private int sublocationId;

	@Column(name = "rin_ma_phone_book_mobile_number_c")
	private String mobileNumberC;

	@Column(name = "rin_ma_phone_book_extension_number")
	private String extensionNumber;

	@Column(name = "rin_ma_phone_book_email_id")
	private String emailId;

	@Column(name = "rin_ma_phone_book_mobile_number_p")
	private String mobileNumberP;

	@Column(name = "rin_ma_phone_book_pnone_number")
	private String phoneNumber;

	@Column(name = "rin_ma_phone_book_skype_id")
	private String skypeId;

	@Column(name = "rin_ma_phone_book_is_active")
	private Boolean phoneBookIsActive;
	
	
	@Column(name = "rin_ma_phone_book_profile")
	private String phoneBookProfile;

	
	@Column(name = "create_by")
	private Integer createBy;

	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	@Column(name = "update_by")
	private Integer updateBy;

	@Column(name = "update_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;

	@Column(name = "delete_flag")
	private boolean deleteFlag;

	public String getPhoneBookProfile() {
		return phoneBookProfile;
	}

	public void setPhoneBookProfile(String phoneBookProfile) {
		this.phoneBookProfile = phoneBookProfile;
	}

	/**
	 * @return the phoneBookId
	 */
	public int getPhoneBookId() {
		return phoneBookId;
	}

	/**
	 * @param phoneBookId the phoneBookId to set
	 */
	public void setPhoneBookId(int phoneBookId) {
		this.phoneBookId = phoneBookId;
	}

	/**
	 * @return the employeeId
	 */
	public String getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the employeeName
	 */
	public String getEmployeeName() {
		return employeeName;
	}

	/**
	 * @param employeeName the employeeName to set
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	/**
	 * @return the userDepartmentId
	 */
	public int getUserDepartmentId() {
		return userDepartmentId;
	}

	/**
	 * @param userDepartmentId the userDepartmentId to set
	 */
	public void setUserDepartmentId(int userDepartmentId) {
		this.userDepartmentId = userDepartmentId;
	}

	/**
	 * @return the userLocationId
	 */
	public int getUserLocationId() {
		return userLocationId;
	}

	/**
	 * @param userLocationId the userLocationId to set
	 */
	public void setUserLocationId(int userLocationId) {
		this.userLocationId = userLocationId;
	}

	/**
	 * @return the sublocationId
	 */
	public int getSublocationId() {
		return sublocationId;
	}

	/**
	 * @param sublocationId the sublocationId to set
	 */
	public void setSublocationId(int sublocationId) {
		this.sublocationId = sublocationId;
	}

	/**
	 * @return the mobileNumberC
	 */
	public String getMobileNumberC() {
		return mobileNumberC;
	}

	/**
	 * @param mobileNumberC the mobileNumberC to set
	 */
	public void setMobileNumberC(String mobileNumberC) {
		this.mobileNumberC = mobileNumberC;
	}

	/**
	 * @return the extensionNumber
	 */
	public String getExtensionNumber() {
		return extensionNumber;
	}

	/**
	 * @param extensionNumber the extensionNumber to set
	 */
	public void setExtensionNumber(String extensionNumber) {
		this.extensionNumber = extensionNumber;
	}

	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the mobileNumberP
	 */
	public String getMobileNumberP() {
		return mobileNumberP;
	}

	/**
	 * @param mobileNumberP the mobileNumberP to set
	 */
	public void setMobileNumberP(String mobileNumberP) {
		this.mobileNumberP = mobileNumberP;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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
	 * @return the phoneBookIsActive
	 */
	public Boolean getPhoneBookIsActive() {
		return phoneBookIsActive;
	}

	/**
	 * @param phoneBookIsActive the phoneBookIsActive to set
	 */
	public void setPhoneBookIsActive(Boolean phoneBookIsActive) {
		this.phoneBookIsActive = phoneBookIsActive;
	}

	
	

	

}
