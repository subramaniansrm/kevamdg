package com.kevamdg.sr.vo;
 
import java.util.List;
 

/**
 * 
 * @author Priya [SRM]
 */
public class FaqVo   {

	private Integer faqId;
	private String question;
	private String answer;
	private String faqSearchText;
	private Integer isFaqActive;	
	private Integer deleteFlag;
	private List<FaqVo> faqVoList;
	
	/**
	 * @return the faqId
	 */
	public Integer getFaqId() {
		return faqId;
	}
	/**
	 * @param faqId the faqId to set
	 */
	public void setFaqId(Integer faqId) {
		this.faqId = faqId;
	}
	/**
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}
	/**
	 * @param question the question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
	}
	/**
	 * @return the answer
	 */
	public String getAnswer() {
		return answer;
	}
	/**
	 * @param answer the answer to set
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	/**
	 * @return the faqSearchText
	 */
	public String getFaqSearchText() {
		return faqSearchText;
	}
	/**
	 * @param faqSearchText the faqSearchText to set
	 */
	public void setFaqSearchText(String faqSearchText) {
		this.faqSearchText = faqSearchText;
	}
	/**
	 * @return the faqVoList
	 */
	public List<FaqVo> getFaqVoList() {
		return faqVoList;
	}
	/**
	 * @param faqVoList the faqVoList to set
	 */
	public void setFaqVoList(List<FaqVo> faqVoList) {
		this.faqVoList = faqVoList;
	}
	/**
	 * @return the isFaqActive
	 */
	public Integer getIsFaqActive() {
		return isFaqActive;
	}
	/**
	 * @param isFaqActive the isFaqActive to set
	 */
	public void setIsFaqActive(Integer isFaqActive) {
		this.isFaqActive = isFaqActive;
	}
	/**
	 * @return the deleteFlag
	 */
	public Integer getDeleteFlag() {
		return deleteFlag;
	}
	/**
	 * @param deleteFlag the deleteFlag to set
	 */
	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
	 
	
	 
}
