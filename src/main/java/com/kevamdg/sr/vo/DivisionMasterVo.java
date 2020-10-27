package com.kevamdg.sr.vo;
 
import java.util.Date;
import java.util.Map;
 
public class DivisionMasterVo {

	private Integer id;
	private String division;
	private Character deleteFlag;
	private Date createdDate;
	private String createdBy;
	private Date updatedDate;
	private String updatedBy;
	private CostBookVo costBookMaster;
	private String costBook;
	private Integer costBookId;
	
	private Integer divisionLocationId;
	private String location;
	private String description;	
	private Integer[] deleteItem;
	private Map<Integer, String> costBookMasterMap;	
 	 
	/**
	 * @return the divisionLocationId
	 */
	public Integer getDivisionLocationId() {
		return divisionLocationId;
	}
	/**
	 * @param divisionLocationId the divisionLocationId to set
	 */
	public void setDivisionLocationId(Integer divisionLocationId) {
		this.divisionLocationId = divisionLocationId;
	}
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the division
	 */
	public String getDivision() {
		return division;
	}
	/**
	 * @param division the division to set
	 */
	public void setDivision(String division) {
		this.division = division;
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
	 * @return the deleteItem
	 */
	public Integer[] getDeleteItem() {
		return deleteItem;
	}
	/**
	 * @param deleteItem the deleteItem to set
	 */
	public void setDeleteItem(Integer[] deleteItem) {
		this.deleteItem = deleteItem;
	}
	/**
	 * @return the costBookMasterMap
	 */
	public Map<Integer, String> getCostBookMasterMap() {
		return costBookMasterMap;
	}
	/**
	 * @param costBookMasterMap the costBookMasterMap to set
	 */
	public void setCostBookMasterMap(Map<Integer, String> costBookMasterMap) {
		this.costBookMasterMap = costBookMasterMap;
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
	 * @return the costBookMaster
	 */
	public CostBookVo getCostBookMaster() {
		return costBookMaster;
	}
	/**
	 * @param costBookMaster the costBookMaster to set
	 */
	public void setCostBookMaster(CostBookVo costBookMaster) {
		this.costBookMaster = costBookMaster;
	}	 
}
