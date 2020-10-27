/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kevamdg.sr.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author vigneshs
 */
@Data
@Entity
@EqualsAndHashCode(callSuper=false)
@Table(name = "module")
public class ModuleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "MODULE_PK")
	private Integer id;
	@Column(name = "MODULE_CODE")
	private String moduleCode;
	@Column(name = "MODULE_NAME")
	private String moduleName;
	@Column(name = "MODULE_TITLE")
	private String moduleTitle;
	@Column(name = "IS_ACTIVE")
	private boolean activeFlag;
	@Column(name = "CREATED_BY")
	private Integer createBy;

	@Column(name = "CREATED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	@Column(name = "UPDATED_BY")
	private Integer updateBy;

	@Column(name = "UPDATED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;

	
}
