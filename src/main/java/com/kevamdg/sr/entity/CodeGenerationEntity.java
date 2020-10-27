/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kevamdg.sr.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 *
 * @author rajalakshmib
 */
@Entity
@Table(name = "code_generation")
@NamedQueries({ @NamedQuery(name = "CodeGenerationEntity.findAll", query = "SELECT c FROM CodeGenerationEntity c"),
		@NamedQuery(name = "CodeGenerationEntity.findByCodeGenerationId", query = "SELECT c FROM CodeGenerationEntity c WHERE c.codeGenerationId = :codeGenerationId"),
		@NamedQuery(name = "CodeGenerationEntity.findByDivisionCount", query = "SELECT c FROM CodeGenerationEntity c WHERE c.divisionCount = :divisionCount"),
		@NamedQuery(name = "CodeGenerationEntity.findByNamePrefix", query = "SELECT c FROM CodeGenerationEntity c WHERE c.namePrefix = :namePrefix"),
		@NamedQuery(name = "CodeGenerationEntity.findByApplicationCount", query = "SELECT c FROM CodeGenerationEntity c WHERE c.applicationCount = :applicationCount"),
		@NamedQuery(name = "CodeGenerationEntity.findByPrefix", query = "SELECT c FROM CodeGenerationEntity c WHERE c.prefix = :prefix"),
		@NamedQuery(name = "CodeGenerationEntity.findByCounter", query = "SELECT c FROM CodeGenerationEntity c WHERE c.counter = :counter"),
		@NamedQuery(name = "CodeGenerationEntity.findByDeleteFlag", query = "SELECT c FROM CodeGenerationEntity c WHERE c.deleteFlag = :deleteFlag"),
		@NamedQuery(name = "CodeGenerationEntity.findByCreatedDate", query = "SELECT c FROM CodeGenerationEntity c WHERE c.createdDate = :createdDate"),
		@NamedQuery(name = "CodeGenerationEntity.findByCreatedBy", query = "SELECT c FROM CodeGenerationEntity c WHERE c.createdBy = :createdBy"),
		@NamedQuery(name = "CodeGenerationEntity.findByUpdatedDate", query = "SELECT c FROM CodeGenerationEntity c WHERE c.updatedDate = :updatedDate"),
		@NamedQuery(name = "CodeGenerationEntity.findByUpdatedBy", query = "SELECT c FROM CodeGenerationEntity c WHERE c.updatedBy = :updatedBy") })
public class CodeGenerationEntity  implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "CODE_GENERATION_ID")
	private Integer codeGenerationId;
	@Column(name = "DIVISION_STATUS")
	private Integer divisionStatus;
	@Column(name = "DIVISION_COUNT")
	private Integer divisionCount;
	@Column(name = "NAME_PREFIX")
	private Integer namePrefix;
	@Column(name = "APPLICATION_STATUS")
	private Integer applicationStatus;

	@Column(name = "APPLICATION_COUNT")
	private Integer applicationCount;
	@Column(name = "PREFIX")
	private String prefix;
	@Column(name = "COUNTER")
	private Integer counter;
	@Column(name = "STARTING_NUMBER")
	private Integer startingNumber;
	@Column(name = "DELETE_FLAG")
	private Character deleteFlag;
	@Column(name = "CREATED_DATE", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	@Column(name = "CREATED_BY", updatable = false)
	private String createdBy;
	@Column(name = "UPDATED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;
	@Column(name = "UPDATED_BY")
	private String updatedBy;

	@Column(name = "CODE_NAME")
	private Integer code;
    
	
	/**
	 * @return the codeGenerationId
	 */
	public Integer getCodeGenerationId() {
		return codeGenerationId;
	}

	/**
	 * @param codeGenerationId
	 *            the codeGenerationId to set
	 */
	public void setCodeGenerationId(Integer codeGenerationId) {
		this.codeGenerationId = codeGenerationId;
	}

	/**
	 * @return the divisionCount
	 */
	public Integer getDivisionCount() {
		return divisionCount;
	}

	/**
	 * @param divisionCount
	 *            the divisionCount to set
	 */
	public void setDivisionCount(Integer divisionCount) {
		this.divisionCount = divisionCount;
	}

	/**
	 * @return the namePrefix
	 */
	public Integer getNamePrefix() {
		return namePrefix;
	}

	/**
	 * @param namePrefix
	 *            the namePrefix to set
	 */
	public void setNamePrefix(Integer namePrefix) {
		this.namePrefix = namePrefix;
	}

	/**
	 * @return the applicationCount
	 */
	public Integer getApplicationCount() {
		return applicationCount;
	}

	/**
	 * @param applicationCount
	 *            the applicationCount to set
	 */
	public void setApplicationCount(Integer applicationCount) {
		this.applicationCount = applicationCount;
	}

	/**
	 * @return the prefix
	 */
	public String getPrefix() {
		return prefix;
	}

	/**
	 * @param prefix
	 *            the prefix to set
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	/**
	 * @return the counter
	 */
	public Integer getCounter() {
		return counter;
	}

	/**
	 * @param counter
	 *            the counter to set
	 */
	public void setCounter(Integer counter) {
		this.counter = counter;
	}

	/**
	 * @return the deleteFlag
	 */
	public Character getDeleteFlag() {
		return deleteFlag;
	}

	/**
	 * @param deleteFlag
	 *            the deleteFlag to set
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
	 * @param createdDate
	 *            the createdDate to set
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
	 * @param createdBy
	 *            the createdBy to set
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
	 * @param updatedDate
	 *            the updatedDate to set
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
	 * @param updatedBy
	 *            the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * @return the divisionStatus
	 */
	public Integer getDivisionStatus() {
		return divisionStatus;
	}

	/**
	 * @param divisionStatus
	 *            the divisionStatus to set
	 */
	public void setDivisionStatus(Integer divisionStatus) {
		this.divisionStatus = divisionStatus;
	}

	/**
	 * @return the applicationStatus
	 */
	public Integer getApplicationStatus() {
		return applicationStatus;
	}

	/**
	 * @param applicationStatus
	 *            the applicationStatus to set
	 */
	public void setApplicationStatus(Integer applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	/**
	 * @return the codeId
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * @param codeId
	 *            the codeId to set
	 */
	public void setCode(Integer code) {
		this.code = code;
	}

	/**
	 * @return the startingNumber
	 */
	public Integer getStartingNumber() {
		return startingNumber;
	}

	/**
	 * @param startingNumber the startingNumber to set
	 */
	public void setStartingNumber(Integer startingNumber) {
		this.startingNumber = startingNumber;
	}

	
}
