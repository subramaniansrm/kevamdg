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
@Table(name = "screen_function",schema = "common_mdg")
@Data
@EqualsAndHashCode(callSuper=false)
public class ScreenFunction  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "SCREEN_FUNCTION_ID")
	private Integer screenFunctionId;

	@Column(name = "FUNCTION_NAME")
	private String functionName;
	@Column(name = "ACTIVE_FLAG")
	private Character activeFlag;
	@Column(name = "SUB_SCREEN_ID")
	private int subScreenId;
	/*@JoinColumn(name = "rin_ma_entity_id", referencedColumnName = "idrin_ma_entity_id")
	@ManyToOne
	private EntityLicense entityLicenseEntity;*/

	public ScreenFunction() {
	}

	public ScreenFunction(Integer screenFunctionId) {
		this.screenFunctionId = screenFunctionId;
	}

	

}
