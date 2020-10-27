package com.kevamdg.sr.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Data
@Table(name = "user_location")
public class LocationEntity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "USER_LOCATION_ID")
	private Integer id;
	
	
	@Column(name = "USER_LOCATION_NAME")
	private String userLocationName;
	
	
	@Column(name = "USER_LOCATION_DETAILS")
	private String userLocationDetails;
	
	
		
	@Column(name = "ZIP")
	private String zip;
	
	
	@Column(name = "PHONE")
	private String phone;
	
	
	@Column(name = "FAX")
	private String fax;
	
	@Column(name = "EMAIL")
	private String email;
	
	
	@Column(name = "CONTACT_NAME")
	private String contactName;
	
	@Column(name = "DELETE_FLAG")
	private Character deleteFlag;
	
	@Column(name = "CREATED_DATE" ,updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@Column(name = "CREATED_BY" ,updatable=false)
	private Integer createdBy;
	
	@Column(name = "UPDATED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;
	
	@Column(name = "UPDATED_BY")
	private Integer updatedBy;
	
	@Column(name = "CITY")
	private Integer cityId;
	
	@Column(name = "COUNTRY")
	private Integer countryId;
	
	@Column(name = "STATE")
	private Integer stateId;
	
	
	@Column(name = "ACTIVE")
	private Character activeFlag;
	

}
