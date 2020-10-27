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
public class FunctionAuthenticationMaster extends CommonVO implements Serializable {

	private static final long serialVersionUID = 1L;
    private Integer functionAuthenticationId;
    private Character deleteFlag;
    private Date createdDate;
    private String createdBy;
    private Date updatedDate;
    private String updatedBy;
//    private ScreenAuthorizationMaster screenAuthorizationMaster;
//    private ScreenFunctionMaster screenFunctionMaster;

    private Integer screenFunctionId;
    private Integer screenAuthenticationId;
    
    
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
	 * @return the screenFunctionId
	 */
	public Integer getScreenFunctionId() {
		return screenFunctionId;
	}

	/**
	 * @param screenFunctionId the screenFunctionId to set
	 */
	public void setScreenFunctionId(Integer screenFunctionId) {
		this.screenFunctionId = screenFunctionId;
	}

	public FunctionAuthenticationMaster() {
    }

    public FunctionAuthenticationMaster(Integer functionAuthenticationId) {
        this.functionAuthenticationId = functionAuthenticationId;
    }

	/**
	 * @return the functionAuthenticationId
	 */
	public Integer getFunctionAuthenticationId() {
		return functionAuthenticationId;
	}

	/**
	 * @param functionAuthenticationId the functionAuthenticationId to set
	 */
	public void setFunctionAuthenticationId(Integer functionAuthenticationId) {
		this.functionAuthenticationId = functionAuthenticationId;
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
	 * @return the screenFunctionMaster
	 */
//	public ScreenFunctionMaster getScreenFunctionMaster() {
//		return screenFunctionMaster;
//	}
//
//	/**
//	 * @param screenFunctionMaster the screenFunctionMaster to set
//	 */
//	public void setScreenFunctionMaster(ScreenFunctionMaster screenFunctionMaster) {
//		this.screenFunctionMaster = screenFunctionMaster;
//	}
//
//	/**
//	 * @return the screenAuthorizationMaster
//	 */
//	public ScreenAuthorizationMaster getScreenAuthorizationMaster() {
//		return screenAuthorizationMaster;
//	}
//
//	/**
//	 * @param screenAuthorizationMaster the screenAuthorizationMaster to set
//	 */
//	public void setScreenAuthorizationMaster(ScreenAuthorizationMaster screenAuthorizationMaster) {
//		this.screenAuthorizationMaster = screenAuthorizationMaster;
//	}
//

}
