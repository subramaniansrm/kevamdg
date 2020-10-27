/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kevamdg.sr.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author priyas
 */
@Data
@Entity
@EqualsAndHashCode(callSuper=false)
@Table(name = "user_role")
public class UserRoleEntity extends CommonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ROLE_ID")
	private Integer id;

	@Column(name = "USER_ROLE_NAME")
	private String userRoleName;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "GFI_LOCATION_FLAG")
	private Character gfiLocationFlag;

	@Column(name = "USER_DEPARTMENT_ID")
	private Integer userDepartment;

	@Column(name = "USER_LOCATION_ID")
	private Integer userLocation;

	@Column(name = "SUBLOCATION_ID")
	private Integer sublocationId;

	@Column(name = "ROLE_TYPE")
	private Integer roleType;

}
