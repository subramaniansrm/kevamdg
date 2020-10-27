package com.kevamdg.sr.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

/**
 * Common Entity
 * 
 * @author vigneshs
 *
 */
@Data
@MappedSuperclass
public class CommonEntity implements Cloneable {

	@Column(name = "CREATED_BY")
	private Integer createBy;

	@Column(name = "CREATED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	@Column(name = "UPDATED_BY")
	private Integer updateBy;

	@Column(name = "UPDATED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;

	@Column(name = "DELETE_FLAG")
	private boolean deleteFlag;
	
	public CommonEntity clone() throws CloneNotSupportedException {
        return (CommonEntity) super.clone();
    }
	
}