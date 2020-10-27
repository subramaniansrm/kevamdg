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



/**
 * Department Entity
 * @author raathikaabm
 *
 */

@Data
@Entity
@Table(name = "user_department")
public class DepartmentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "USER_DEPARTMENT_ID")
	private Integer departmentId;

	@Column(name = "USER_DEPARTMENT_NAME")
	private String departmentName;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "DELETE_FLAG")
	private Boolean deleteFlag;

	@Column(name = "CREATED_DATE", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(name = "CREATED_BY", updatable = false)
	private Integer createdBy;

	@Column(name = "UPDATED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;
	
	@Column(name = "UPDATED_BY", updatable = false)
	private Integer updatedBy;

	@Column(name = "USER_LOCATION_ID")
	private Integer locationId;

	@Column(name = "USER_SUBLOCATION_ID")
	private Integer subLocationId;


	@Column(name = "SYS_APP_ID")
	private Integer sysAppId;

}
