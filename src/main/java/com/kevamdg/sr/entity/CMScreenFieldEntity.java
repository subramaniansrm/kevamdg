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
 */
@Entity
@Table(name = "cm_screen_field",schema = "common_mdg")
@Data
@EqualsAndHashCode(callSuper=false)
public class CMScreenFieldEntity  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "FIELD_ID")
	private Integer fieldId;

	@Column(name = "FIELD_NAME")
	private String fieldName;
	@Column(name = "ACTIVE_FLAG")
	private Character activeFlag;
	@Column(name = "SEQUENCE")
	private Integer sequence;
	@Column(name = "SUB_SCREEN_ID")
	private int subScreenId;
	@Column(name = "CONTROL_TYPE")
	private String controlType;
	
	@Column(name = "MANDATORY")
	private String mandatory;
	@Column(name = "NUMERIC_ONLY")
	private String numericOnly;
	@Column(name = "DECIMAL_VALUE")
	private Integer decimal;
	@Column(name = "LENGTH")
	private Integer length;

	

}
