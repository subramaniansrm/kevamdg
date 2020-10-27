package com.kevamdg.sr.vo;

import java.util.Date;
  
public class CostBookVo  {

	private Integer id;
	private String costBook;
	private String description;
	private String rmcApproval;
	private Integer rmcApprovalId;
	private Character deleteFlag;
	private Date createdDate;
	private String createdBy;
	private Date updatedDate;
	private String updatedBy;
	private CurrencyVo currencyMaster; 
	private Integer[] deleteItem;
	private Integer currencyId;
	private Integer sysAppId;
	 
	
	/**
	 * @return the costBook
	 */
	public String getCostBook() {
		return costBook;
	}

	/**
	 * @param costBook
	 *            the costBook to set
	 */
	public void setCostBook(String costBook) {
		this.costBook = costBook;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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
	 * @return the currencyId
	 */
	public Integer getCurrencyId() {
		return currencyId;
	}

	/**
	 * @param currencyId
	 *            the currencyId to set
	 */
	public void setCurrencyId(Integer currencyId) {
		this.currencyId = currencyId;
	}

	/**
	 * @return the sysAppId
	 */
	public Integer getSysAppId() {
		return sysAppId;
	}

	/**
	 * @param sysAppId
	 *            the sysAppId to set
	 */
	public void setSysAppId(Integer sysAppId) {
		this.sysAppId = sysAppId;
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
	 * @return the rmcApproval
	 */
	public String getRmcApproval() {
		return rmcApproval;
	}

	/**
	 * @param rmcApproval the rmcApproval to set
	 */
	public void setRmcApproval(String rmcApproval) {
		this.rmcApproval = rmcApproval;
	}

	/**
	 * @return the rmcApprovalId
	 */
	public Integer getRmcApprovalId() {
		return rmcApprovalId;
	}

	/**
	 * @param rmcApprovalId the rmcApprovalId to set
	 */
	public void setRmcApprovalId(Integer rmcApprovalId) {
		this.rmcApprovalId = rmcApprovalId;
	}

	/**
	 * @return the currencyMaster
	 */
	public CurrencyVo getCurrencyMaster() {
		return currencyMaster;
	}

	/**
	 * @param currencyMaster the currencyMaster to set
	 */
	public void setCurrencyMaster(CurrencyVo currencyMaster) {
		this.currencyMaster = currencyMaster;
	}	 
}
