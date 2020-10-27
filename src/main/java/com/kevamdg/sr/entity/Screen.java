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
@Table(name = "screen",schema = "common_mdg")
@Data
@EqualsAndHashCode(callSuper=false)
public class Screen  {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    
    @Column(name = "SCREEN_ID")
    private Integer screenId;
    @Column(name = "SCREEN_NAME")
    private String screenName;
    @Column(name = "SCREEN_TYPE_FLAG")
    private Character screenTypeFlag;
    
    @Column(name = "SCREEN_URL")
    private String screenUrl;
    
    @Column(name = "SCREEN_ICON")
    private String screenIcon;
    
    @Column(name = "ACTIVE_FLAG")
    private Character activeFlag;
    
    	
}
