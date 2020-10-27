/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kevamdg.sr.vo;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author rajalakshmib
 */
public class FieldAuthenticationMaster extends CommonVO implements Serializable {

	private static final long serialVersionUID = 1L;
    private Integer fieldAuthenticationId;
    private Boolean baseFilter;
    private Character deleteFlag;
    private Date createdDate;
    private String createdBy;
    private Date updatedDate;
    private String updatedBy;
//    private ScreenFieldMaster screenFieldMaster;
//    private ScreenAuthorizationMaster screenAuthorizationMaster;

    private Integer fieldId;
    private Integer screenAuthenticationId;
    
    private String baseFilterSearch;
    
    
    private String mandatory;
	private String numericOnly;
	private Integer decimal;
	private Integer length;
	private String fieldName;
	private String controlType;
	
	
    /**
	 * @return the mandatory
	 */
	public String getMandatory() {
		return mandatory;
	}

	/**
	 * @param mandatory the mandatory to set
	 */
	public void setMandatory(String mandatory) {
		this.mandatory = mandatory;
	}

	/**
	 * @return the numericOnly
	 */
	public String getNumericOnly() {
		return numericOnly;
	}

	/**
	 * @param numericOnly the numericOnly to set
	 */
	public void setNumericOnly(String numericOnly) {
		this.numericOnly = numericOnly;
	}

	/**
	 * @return the decimal
	 */
	public Integer getDecimal() {
		return decimal;
	}

	/**
	 * @param decimal the decimal to set
	 */
	public void setDecimal(Integer decimal) {
		this.decimal = decimal;
	}

	/**
	 * @return the length
	 */
	public Integer getLength() {
		return length;
	}

	/**
	 * @param length the length to set
	 */
	public void setLength(Integer length) {
		this.length = length;
	}

	/**
	 * @return the fieldName
	 */
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * @param fieldName the fieldName to set
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	/**
	 * @return the controlType
	 */
	public String getControlType() {
		return controlType;
	}

	/**
	 * @param controlType the controlType to set
	 */
	public void setControlType(String controlType) {
		this.controlType = controlType;
	}

	/**
	 * @return the baseFilterSearch
	 */
	public String getBaseFilterSearch() {
		return baseFilterSearch;
	}

	/**
	 * @param baseFilterSearch the baseFilterSearch to set
	 */
	public void setBaseFilterSearch(String baseFilterSearch) {
		this.baseFilterSearch = baseFilterSearch;
	}

	/**
	 * @return the screenAuthenticationId
	 */
	public Integer getScreenAuthenticationId() {
		return screenAuthenticationId;
	}

	/**
	 * @param screenAuthenticationId the screenAuthenticationId to set
	 */
	public void setScreenAuthenticationId(Integer screenAuthenticationId) {
		this.screenAuthenticationId = screenAuthenticationId;
	}

	/**
	 * @return the fieldId
	 */
	public Integer getFieldId() {
		return fieldId;
	}

	/**
	 * @param fieldId the fieldId to set
	 */
	public void setFieldId(Integer fieldId) {
		this.fieldId = fieldId;
	}

	public FieldAuthenticationMaster() {
    }

    public FieldAuthenticationMaster(Integer fieldAuthenticationId) {
        this.fieldAuthenticationId = fieldAuthenticationId;
    }

	/**
	 * @return the fieldAuthenticationId
	 */
	public Integer getFieldAuthenticationId() {
		return fieldAuthenticationId;
	}

	/**
	 * @param fieldAuthenticationId the fieldAuthenticationId to set
	 */
	public void setFieldAuthenticationId(Integer fieldAuthenticationId) {
		this.fieldAuthenticationId = fieldAuthenticationId;
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
	 * @return the screenFieldMaster
	 */
		/**
	 * @return the baseFilter
	 */
	public Boolean getBaseFilter() {
		return baseFilter;
	}

	/**
	 * @param baseFilter the baseFilter to set
	 */
	public void setBaseFilter(Boolean baseFilter) {
		this.baseFilter = baseFilter;
	}



}
