package com.kevamdg.sr.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "cm_sub_screen",schema = "common_mdg")
@Data
public class CMSubScreenEntity  {

	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SUB_SCREEN_PK_ID")
    private Integer subScreenPkId;
    @Column(name = "SUB_SCREEN_ID")
    private Integer subScreenId;
    @Column(name = "SUB_SCREEN_NAME")
    private String subScreenName;
    @Column(name = "ACTIVE_FLAG")
    private Character activeFlag;
    @Column(name = "SCREEN_ID")
    private int screenId;
   }
