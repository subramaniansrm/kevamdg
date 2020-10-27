
package com.kevamdg.sr.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Priya [SRM]
 */
@Entity
@Table(name = "faq")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "FaqEntity.findAll", query = "SELECT f FROM FaqEntity f"),
		@NamedQuery(name = "FaqEntity.findByFaqId", query = "SELECT f FROM FaqEntity f WHERE f.faqId = :faqId"),
		@NamedQuery(name = "FaqEntity.findByQuestion", query = "SELECT f FROM FaqEntity f WHERE f.question = :question"),
		@NamedQuery(name = "FaqEntity.findByAnswer", query = "SELECT f FROM FaqEntity f WHERE f.answer = :answer") })
public class FaqEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "FAQ_ID")
	private Integer faqId;
	@Column(name = "QUESTION")
	private String question;
	@Column(name = "ANSWER")
	private String answer;
	
	@Column(name = "rin_ma_faq_is_active")
	private Integer isFaqActive;
	
	@Column(name = "delete_flag")
	private Integer deleteFlag;

	/**
	 * @return the faqId
	 */
	public Integer getFaqId() {
		return faqId;
	}

	/**
	 * @param faqId
	 *            the faqId to set
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
	 * @param question
	 *            the question to set
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
	 * @param answer
	 *            the answer to set
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
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
