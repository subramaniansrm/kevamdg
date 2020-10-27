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
@Table(name = "function_authentication",schema = "common_mdg")
@Data
@EqualsAndHashCode(callSuper=false)
public class FunctionAuthentication  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "FUNCTION_AUTHENTICATION_ID")
    private Integer functionAuthenticationId;
    
    @Column(name = "DELETE_FLAG")
    private Character deleteFlag;
    @Column(name = "CREATED_DATE" ,updatable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "CREATED_BY" ,updatable=false)
    private String createdBy;
    @Column(name = "UPDATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    @Column(name = "UPDATED_BY")
    private String updatedBy;
    @JoinColumn(name = "SCREEN_AUTHENTICATION_ID", referencedColumnName = "SCREEN_AUTHENTICATION_ID")
    @ManyToOne
    private ScreenAuthentication screenAuthenticationEntity;
    @JoinColumn(name = "SCREEN_FUNCTION_ID", referencedColumnName = "SCREEN_FUNCTION_ID")
    @ManyToOne
    private ScreenFunction screenFunctionEntity;
   
    @Column(name = "MAIL_NAME")
    private int mailName;
    
   /* @JoinColumn(name = "rin_ma_entity_id", referencedColumnName = "idrin_ma_entity_id")
	@ManyToOne
	private EntityLicense entityLicenseEntity;*/
 
	public FunctionAuthentication() {
    }

    public FunctionAuthentication(Integer functionAuthenticationId) {
        this.functionAuthenticationId = functionAuthenticationId;
    }

	
}
