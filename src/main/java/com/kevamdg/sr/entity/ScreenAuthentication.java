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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 */
@Entity
@Table(name = "screen_authentication",schema = "common_mdg")
@Data
@EqualsAndHashCode(callSuper=false)
public class ScreenAuthentication  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "SCREEN_AUTHENTICATION_ID")
	private Integer screenAuthenticationId;

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
	
	 @JoinColumn(name = "SCREEN_ID", referencedColumnName = "SCREEN_ID")
	    @ManyToOne
	    private Screen screenEntity;
	    @JoinColumn(name = "SUB_SCREEN_ID", referencedColumnName = "SUB_SCREEN_ID")
	    @ManyToOne
	    private SubScreen subScreenEntity;
	
	/*@JoinColumn(name = "rin_ma_entity_id", referencedColumnName = "idrin_ma_entity_id")
	@ManyToOne
	private EntityLicense entityLicenseEntity;*/

    @JoinColumn(name = "USER_DEPARTMENT_ID", referencedColumnName = "USER_DEPARTMENT_ID")
    @ManyToOne
    private DepartmentEntity userDepartmentEntity;
    @JoinColumn(name = "USER_LOCATION_ID", referencedColumnName = "USER_LOCATION_ID")
    @ManyToOne
    private LocationEntity userLocationEntity;
    @JoinColumn(name = "USER_ROLE_ID", referencedColumnName = "ROLE_ID")
    @ManyToOne
    private UserRoleEntity userRoleEntity;
    @JoinColumn(name = "USER_SUBLOCATION_ID", referencedColumnName = "SUBLOCATION_ID")
    @ManyToOne
    private SubLocationEntity subLocationEntity;

	
}
