/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kevamdg.sr.vo;

import java.io.Serializable;

/**
 *
 * @author rajalakshmib
 */
public class ScreenFunctionMaster extends CommonVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer screenFunctionId;
    private String functionName;
    private Character activeFlag;
    private SubScreenMaster subScreenMaster;

    public ScreenFunctionMaster() {
    }

    public ScreenFunctionMaster(Integer screenFunctionId) {
        this.screenFunctionId = screenFunctionId;
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

	/**
	 * @return the functionName
	 */
	public String getFunctionName() {
		return functionName;
	}

	/**
	 * @param functionName the functionName to set
	 */
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	/**
	 * @return the activeFlag
	 */
	public Character getActiveFlag() {
		return activeFlag;
	}

	/**
	 * @param activeFlag the activeFlag to set
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
	 * @param subScreenMaster the subScreenMaster to set
	 */
	public void setSubScreenMaster(SubScreenMaster subScreenMaster) {
		this.subScreenMaster = subScreenMaster;
	}

}
