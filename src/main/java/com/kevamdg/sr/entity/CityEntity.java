package com.kevamdg.sr.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


/**
 * City Entity
 * @author raathikaabm
 *
 */

@Entity
@Data
@Table(name = "city")
public class CityEntity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "CITY_ID")
	private Integer cityId;
	
	
	@Column(name = "CITY_NAME")
	private String cityName;
	

}
