
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
@Table(name = "help")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "HelpEntity.findAll", query = "SELECT h FROM HelpEntity h"),
		@NamedQuery(name = "HelpEntity.findByHelpId", query = "SELECT h FROM HelpEntity h WHERE h.helpId = :helpId"),
		@NamedQuery(name = "HelpEntity.findByTopic", query = "SELECT h FROM HelpEntity h WHERE h.topic = :topic"),
		@NamedQuery(name = "HelpEntity.findByDetail", query = "SELECT h FROM HelpEntity h WHERE h.detail = :detail") })
public class HelpEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "HELP_ID")
	private Integer helpId;
	@Column(name = "TOPIC")
	private String topic;
	@Column(name = "DETAIL")
	private String detail;

	/**
	 * @return the helpId
	 */
	public Integer getHelpId() {
		return helpId;
	}

	/**
	 * @param helpId
	 *            the helpId to set
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
	 * @param topic
	 *            the topic to set
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
	 * @param detail
	 *            the detail to set
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}

}
