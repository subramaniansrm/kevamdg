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

/**
 *
 * @author vigneshs
 */
@Data
@Entity
@Table(name = "common_drop_down")
public class CommonDropdownEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "COMMON_DROP_DOWN_ID")
	private Integer id;
	@Column(name = "SALES_ORG_ID")
	private Integer salesOrgId;
	@Column(name = "COMMON_DROP_DOWN_FIELD_ID")
	private Integer fieldId;
	@Column(name = "COMMON_DROP_DOWN_CODE")
	private String dropdownCode;
	@Column(name = "COMMON_DROP_DOWN_NAME")
	private String dropdownName;
	@Column(name = "ACTIVE_FLAG")
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

	@Column(name = "DELETE_FLAG")
	private boolean deleteFlag;

}
