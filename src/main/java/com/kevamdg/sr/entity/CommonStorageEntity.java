/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author priyas
 */
@Entity
@Table(name = "common_storage")
@NamedQueries({ @NamedQuery(name = "CommonStorageEntity.findAll", query = "SELECT c FROM CommonStorageEntity c")
		 })
public class CommonStorageEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "COMMON_ID")
	private Integer commonId;
	@Column(name = "ITEM_REFERENCE_ID")
	private String itemReferenceId;
	@Column(name = "ITEM_NAME")
	private String itemName;
	@Column(name = "ITEM_VALUE")
	private String itemValue;
	
	
	/**
	 * @return the commonId
	 */
	public Integer getCommonId() {
		return commonId;
	}
	/**
	 * @param commonId the commonId to set
	 */
	public void setCommonId(Integer commonId) {
		this.commonId = commonId;
	}
	/**
	 * @return the itemReferenceId
	 */
	public String getItemReferenceId() {
		return itemReferenceId;
	}
	/**
	 * @param itemReferenceId the itemReferenceId to set
	 */
	public void setItemReferenceId(String itemReferenceId) {
		this.itemReferenceId = itemReferenceId;
	}
	/**
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}
	/**
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	/**
	 * @return the itemValue
	 */
	public String getItemValue() {
		return itemValue;
	}
	/**
	 * @param itemValue the itemValue to set
	 */
	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}	 

}
