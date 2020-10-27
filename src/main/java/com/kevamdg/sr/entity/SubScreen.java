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
@Table(name = "sub_screen",schema = "common_mdg")
@Data
public class SubScreen  {

	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    
    @Column(name = "SUB_SCREEN_ID")
    private Integer subScreenId;
    @Column(name = "SUB_SCREEN_NAME")
    private String subScreenName;
    @Column(name = "ACTIVE_FLAG")
    private Character activeFlag;
    @Column(name = "SCREEN_ID")
    private int screenId;
    /*@JoinColumn(name = "rin_ma_entity_id", referencedColumnName = "idrin_ma_entity_id")
	@ManyToOne
	private EntityLicense entityLicenseEntity;
    */
}
