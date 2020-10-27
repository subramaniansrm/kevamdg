package com.kevamdg.sr.vo;

import java.io.Serializable;

public class SubScreenMaster extends CommonVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer subScreenId;
    private String subScreenName;
    private Character activeFlag;
 
    private ScreenMaster screenMaster;

	/**
	 * @return the subScreenId
	 */
	public Integer getSubScreenId() {
		return subScreenId;
	}

	/**
	 * @param subScreenId the subScreenId to set
	 */
	public void setSubScreenId(Integer subScreenId) {
		this.subScreenId = subScreenId;
	}

	/**
	 * @return the subScreenName
	 */
	public String getSubScreenName() {
		return subScreenName;
	}

	/**
	 * @param subScreenName the subScreenName to set
	 */
	public void setSubScreenName(String subScreenName) {
		this.subScreenName = subScreenName;
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
	 * @return the screenMaster
	 */
	public ScreenMaster getScreenMaster() {
		return screenMaster;
	}

	/**
	 * @param screenMaster the screenMaster to set
	 */
	public void setScreenMaster(ScreenMaster screenMaster) {
		this.screenMaster = screenMaster;
	}

   
}
