/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kevamdg.sr.vo;

import java.io.Serializable;

import javax.persistence.Column;

/**
 *
 * @author rajalakshmib
 */
public class ScreenFieldMaster extends CommonVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer fieldId;
	private String fieldName;
	private Character activeFlag;
	private SubScreenMaster subScreenMaster;
	private String controlType;
	private String mandatory;
	private String numericOnly;
    private Integer decimal;
    private Integer length;
	
	
	
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

	public ScreenFieldMaster() {
	}

	public ScreenFieldMaster(Integer fieldId) {
		this.fieldId = fieldId;
	}

	/**
	 * @return the fieldId
	 */
	public Integer getFieldId() {
		return fieldId;
	}

	/**
	 * @param fieldId
	 *            the fieldId to set
	 */
	public void setFieldId(Integer fieldId) {
		this.fieldId = fieldId;
	}

	/**
	 * @return the fieldName
	 */
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * @param fieldName
	 *            the fieldName to set
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	/**
	 * @return the activeFlag
	 */
	public Character getActiveFlag() {
		return activeFlag;
	}

	/**
	 * @param activeFlag
	 *            the activeFlag to set
	 */
	public void setActiveFlag(Character activeFlag) {
		this.activeFlag = activeFlag;
	}

	/**
	 * @return the subScreenMaster
	 */
	public SubScreenMaster getSubScreenMaster() {
		return subScreenMaster;
	}

	/**
	 * @param subScreenMaster
	 *            the subScreenMaster to set
	 */
	public void setSubScreenMaster(SubScreenMaster subScreenMaster) {
		this.subScreenMaster = subScreenMaster;
	}

	/**
	 * @return the controlType
	 */
	public String getControlType() {
		return controlType;
	}

	/**
	 * @param controlType
	 *            the controlType to set
	 */
	public void setControlType(String controlType) {
		this.controlType = controlType;
	}

}
