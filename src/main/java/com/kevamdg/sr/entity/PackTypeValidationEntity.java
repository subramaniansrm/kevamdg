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
@Table(name = "pack_type_map_with_sales_organisation")
public class PackTypeValidationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "PACK_TYPE_ID")
	private Integer id;
	@Column(name = "SALES_ORG_ID")
	private Integer salesOrgId;
	@Column(name = "PACK_SIZE_ID")
	private Integer packSize;
	@Column(name = "PACK_TYPE_NAME")
	private String packTypeName;
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
