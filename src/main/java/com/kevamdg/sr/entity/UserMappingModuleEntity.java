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
@Table(name = "user_mapping_module")
public class UserMappingModuleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "USER_MAPPING_MODULE_PK")
	private Integer id;
	@Column(name = "USER_FK")
	private Integer userId;
	@Column(name = "MODULE_FK")
	private Integer moduleId;
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
