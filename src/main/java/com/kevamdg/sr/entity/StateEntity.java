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
 * State Entiy
 * @author raathikaabm
 *
 */
@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@Table(name = "state")
public class StateEntity extends CommonEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "STATE_ID")
	private Integer stateId;
	
	
	@Column(name = "STATE_NAME")
	private String stateName;
	
	
	
	@Column(name = "COUNTRY_ID")
	private Integer countryId;
	
	

	
	
	
	

}
