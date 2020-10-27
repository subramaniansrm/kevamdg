package com.kevamdg.sr.vo;

 
import java.util.List;

/**
 * 
 * @author Priya [SRM]
 */
public class HelpVo {	 
	
	private Integer helpId;
	private String topic;
	private String detail;
	private String helpSearchText;
	private List<HelpVo> helpVoList;
	
	/**
	 * @return the helpId
	 */
	public Integer getHelpId() {
		return helpId;
	}
	/**
	 * @param helpId the helpId to set
	 */
	public void setHelpId(Integer helpId) {
		this.helpId = helpId;
	}
	/**
	 * @return the topic
	 */
	public String getTopic() {
		return topic;
	}
	/**
	 * @param topic the topic to set
	 */
	public void setTopic(String topic) {
		this.topic = topic;
	}
	/**
	 * @return the detail
	 */
	public String getDetail() {
		return detail;
	}
	/**
	 * @param detail the detail to set
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}
	/**
	 * @return the helpSearchText
	 */
	public String getHelpSearchText() {
		return helpSearchText;
	}
	/**
	 * @param helpSearchText the helpSearchText to set
	 */
	public void setHelpSearchText(String helpSearchText) {
		this.helpSearchText = helpSearchText;
	}
	/**
	 * @return the helpVoList
	 */
	public List<HelpVo> getHelpVoList() {
		return helpVoList;
	}
	/**
	 * @param helpVoList the helpVoList to set
	 */
	public void setHelpVoList(List<HelpVo> helpVoList) {
		this.helpVoList = helpVoList;
	}
	
}
