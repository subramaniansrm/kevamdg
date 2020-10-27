package com.kevamdg.sr.vo;

 
import java.util.Date;
import java.util.List;
 
public class UserLocationVo {

	private Integer id;
	private String userLocationName;
	private String userLocationDetails;
	private String primaryLocation;
	private String zip;
	private String phone;
	private String fax;
	private String email;
	private String contactName;
	private String gfiLocationFlag;
	private Character deleteFlag;
	private Date createdDate;
	private String createdBy;
	private Date updatedDate;
	private String updatedBy;
	private String activeFlag; 
	 
	private Integer[] costBookMultiple;
	
    private Integer default1;

	private Integer cityId;
	
	private Integer stateId;
	private Integer countryId;
	private Integer sysAppId;
	private String city;
	private String state;
	private String country;
	private Integer costBookId;
	private String costBook;
	private Integer[] deleteItem;
	private List<UserLocationVo> userLocationMasterList;
	private List<AuthenticationVo> authenticationList;
	
	
	
	/**
	 * @return the authenticationList
	 */
	public List<AuthenticationVo> getAuthenticationList() {
		return authenticationList;
	}

	/**
	 * @param authenticationList the authenticationList to set
	 */
	public void setAuthenticationList(List<AuthenticationVo> authenticationList) {
		this.authenticationList = authenticationList;
	}

	/**
	 * @return the activeFlag
	 */
	public String getActiveFlag() {
		return activeFlag;
	}

	/**
	 * @param activeFlag the activeFlag to set
	 */
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
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
	 * @return the userLocationDetails
	 */
	public String getUserLocationDetails() {
		return userLocationDetails;
	}

	/**
	 * @param userLocationDetails
	 *            the userLocationDetails to set
	 */
	public void setUserLocationDetails(String userLocationDetails) {
		this.userLocationDetails = userLocationDetails;
	}

	/**
	 * @return the primaryLocation
	 */
	public String getPrimaryLocation() {
		return primaryLocation;
	}

	/**
	 * @param primaryLocation
	 *            the primaryLocation to set
	 */
	public void setPrimaryLocation(String primaryLocation) {
		this.primaryLocation = primaryLocation;
	}

	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @param zip
	 *            the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * @param fax
	 *            the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the contactName
	 */
	public String getContactName() {
		return contactName;
	}

	/**
	 * @param contactName
	 *            the contactName to set
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	/**
	 * @return the deleteFlag
	 */
	public Character getDeleteFlag() {
		return deleteFlag;
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
	 * @return the userLocationMasterList
	 */
	public List<UserLocationVo> getUserLocationMasterList() {
		return userLocationMasterList;
	}

	/**
	 * @param userLocationMasterList
	 *            the userLocationMasterList to set
	 */
	public void setUserLocationMasterList(List<UserLocationVo> userLocationMasterList) {
		this.userLocationMasterList = userLocationMasterList;
	}

	/**
	 * @return the gfiLocationFlag
	 */
	public String getGfiLocationFlag() {
		return gfiLocationFlag;
	}

	/**
	 * @param gfiLocationFlag
	 *            the gfiLocationFlag to set
	 */
	public void setGfiLocationFlag(String gfiLocationFlag) {
		this.gfiLocationFlag = gfiLocationFlag;
	}

	/**
	 * @return the city
	 */
	
	/**
	 * @param sysAppId
	 *            the sysAppId to set
	 */
	public void setSysAppId(Integer sysAppId) {
		this.sysAppId = sysAppId;
	}

	/**
	 * @return the state
	 */
	

	

	/**
	 * @return the cityId
	 */
	public Integer getCityId() {
		return cityId;
	}

	/**
	 * @param cityId the cityId to set
	 */
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	/**
	 * @return the stateId
	 */
	public Integer getStateId() {
		return stateId;
	}

	/**
	 * @param stateId the stateId to set
	 */
	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	/**
	 * @return the countryId
	 */
	public Integer getCountryId() {
		return countryId;
	}

	/**
	 * @param countryId the countryId to set
	 */
	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	/**
	 * @return the sysAppId
	 */
	public Integer getSysAppId() {
		return sysAppId;
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
	 * @return the costBookMultiple
	 */
	public Integer[] getCostBookMultiple() {
		return costBookMultiple;
	}

	/**
	 * @param costBookMultiple the costBookMultiple to set
	 */
	public void setCostBookMultiple(Integer[] costBookMultiple) {
		this.costBookMultiple = costBookMultiple;
	}

	/**
	 * @return the costBookId
	 */
	public Integer getCostBookId() {
		return costBookId;
	}

	/**
	 * @param costBookId the costBookId to set
	 */
	public void setCostBookId(Integer costBookId) {
		this.costBookId = costBookId;
	}

	 
	/**
	 * @return the costBook
	 */
	public String getCostBook() {
		return costBook;
	}

	/**
	 * @param costBook the costBook to set
	 */
	public void setCostBook(String costBook) {
		this.costBook = costBook;
	}

	 

	/**
	 * @return the default1
	 */
	public Integer getDefault1() {
		return default1;
	}

	/**
	 * @param default1 the default1 to set
	 */
	public void setDefault1(Integer default1) {
		this.default1 = default1;
	}


	
}