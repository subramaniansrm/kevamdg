package com.kevamdg.sr.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "user_sublocation", schema = "common_mdg")
public class SubLocationEntity extends CommonEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SUBLOCATION_ID")
	private Integer sublocationId;

	@Column(name = "LOCATION_ID")
	private Integer id;

	@Column(name = "SUBLOCATION_NAME")
	private String subLocationName;

	@Column(name = "SUBLOCATION_CODE")
	private String subLocationCode;

	@Column(name = "SUBLOCATION_ISACTIVE")
	private boolean subLocationIsActive;
	
		
}