/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kevamdg.sr.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author aarthimp
 */
@Entity
@Table(name = "user_mapping")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserMappingEntity.findAll", query = "SELECT s FROM UserMappingEntity s"),
    @NamedQuery(name = "UserMappingEntity.findByLevel", query = "SELECT s FROM UserMappingEntity s WHERE s.level = :level"),
    @NamedQuery(name = "UserMappingEntity.findByDeleteFlag", query = "SELECT s FROM UserMappingEntity s WHERE s.deleteFlag = :deleteFlag"),
    @NamedQuery(name = "UserMappingEntity.findByCreatedDate", query = "SELECT s FROM UserMappingEntity s WHERE s.createdDate = :createdDate"),
    @NamedQuery(name = "UserMappingEntity.findByCreatedBy", query = "SELECT s FROM UserMappingEntity s WHERE s.createdBy = :createdBy"),
    @NamedQuery(name = "UserMappingEntity.findByUpdatedDate", query = "SELECT s FROM UserMappingEntity s WHERE s.updatedDate = :updatedDate"),
    @NamedQuery(name = "UserMappingEntity.findByUpdatedBy", query = "SELECT s FROM UserMappingEntity s WHERE s.updatedBy = :updatedBy")})
public class UserMappingEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "USER_MAPPING_ID")
    private Integer userMappingId;
    @JoinColumn(name = "LEVEL", referencedColumnName = "COMMON_ID")
    @ManyToOne
    private CommonStorageEntity level;
    @JoinColumn(name = "REPORTING_TO", referencedColumnName = "USER_ID")
    @ManyToOne
    private UserEntity reportingToUser;
    @Column(name = "DELETE_FLAG")
    private Character deleteFlag;
    @Column(name = "CREATED_DATE" ,updatable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "CREATED_BY" )
    private int createdBy;
    @Column(name = "UPDATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    @Column(name = "UPDATED_BY")
    private int updatedBy;
    @JoinColumn(name = "USER_DEPARTMENT_ID", referencedColumnName = "USER_DEPARTMENT_ID")
    @ManyToOne
    private DepartmentEntity userDepartmentEntity;
    @JoinColumn(name = "USER_LOCATION_ID", referencedColumnName = "USER_LOCATION_ID")
    @ManyToOne
    private LocationEntity userLocationEntity;
    
    
    @JoinColumn(name = "USER_SUBLOCATION_ID", referencedColumnName = "SUBLOCATION_ID")
    @ManyToOne
    private SubLocationEntity subLocationEntity;
    
    @JoinColumn(name = "REPORTING_TO_LOCATION", referencedColumnName = "USER_LOCATION_ID")
    @ManyToOne
    private LocationEntity reportingLocationEntity;
    
    
    @JoinColumn(name = "REPORTING_TO_SUBLOCATION", referencedColumnName = "SUBLOCATION_ID")
    @ManyToOne
    private SubLocationEntity reportingSublocationEntity;
    
    
    
    @JoinColumn(name = "REPORTING_DEPARTMENT", referencedColumnName = "USER_DEPARTMENT_ID")
    @ManyToOne
    private DepartmentEntity reportingUserDepartmentEntity;
    @JoinColumn(name = "USER_ROLE_ID", referencedColumnName = "ROLE_ID")
    @ManyToOne
    private UserRoleEntity userRoleEntity;
   
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    @ManyToOne
    private UserEntity userEntity;
    
    public UserMappingEntity() {
    }

    
	/**
	 * @return the userEntity
	 */
	public UserEntity getUserEntity() {
		return userEntity;
	}


	/**
	 * @param userEntity the userEntity to set
	 */
	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}



	/**
	 * @return the userMappingId
	 */
	public Integer getUserMappingId() {
		return userMappingId;
	}


	/**
	 * @param userMappingId the userMappingId to set
	 */
	public void setUserMappingId(Integer userMappingId) {
		this.userMappingId = userMappingId;
	}




	/**
	 * @return the reportingToUser
	 */
	public UserEntity getReportingToUser() {
		return reportingToUser;
	}

	/**
	 * @param reportingToUser the reportingToUser to set
	 */
	public void setReportingToUser(UserEntity reportingToUser) {
		this.reportingToUser = reportingToUser;
	}

	/**
	 * @return the deleteFlag
	 */
	public Character getDeleteFlag() {
		return deleteFlag;
	}

	/**
	 * @param deleteFlag the deleteFlag to set
	 */
	public void setDeleteFlag(Character deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the createdBy
	 */
	public int getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the updatedDate
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * @return the updatedBy
	 */
	public int getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * @return the userDepartmentEntity
	 */
	public DepartmentEntity getUserDepartmentEntity() {
		return userDepartmentEntity;
	}

	/**
	 * @param userDepartmentEntity the userDepartmentEntity to set
	 */
	public void setUserDepartmentEntity(DepartmentEntity userDepartmentEntity) {
		this.userDepartmentEntity = userDepartmentEntity;
	}

	/**
	 * @return the userLocationEntity
	 */
	public LocationEntity getUserLocationEntity() {
		return userLocationEntity;
	}

	/**
	 * @param userLocationEntity the userLocationEntity to set
	 */
	public void setUserLocationEntity(LocationEntity userLocationEntity) {
		this.userLocationEntity = userLocationEntity;
	}

	/**
	 * @return the reportingUserDepartmentEntity
	 */
	public DepartmentEntity getReportingUserDepartmentEntity() {
		return reportingUserDepartmentEntity;
	}

	/**
	 * @param reportingUserDepartmentEntity the reportingUserDepartmentEntity to set
	 */
	public void setReportingUserDepartmentEntity(DepartmentEntity reportingUserDepartmentEntity) {
		this.reportingUserDepartmentEntity = reportingUserDepartmentEntity;
	}

	/**
	 * @return the userRoleEntity
	 */
	public UserRoleEntity getUserRoleEntity() {
		return userRoleEntity;
	}

	/**
	 * @param userRoleEntity the userRoleEntity to set
	 */
	public void setUserRoleEntity(UserRoleEntity userRoleEntity) {
		this.userRoleEntity = userRoleEntity;
	}

	


	/**
	 * @return the level
	 */
	public CommonStorageEntity getLevel() {
		return level;
	}


	/**
	 * @param level the level to set
	 */
	public void setLevel(CommonStorageEntity level) {
		this.level = level;
	}


	public SubLocationEntity getSubLocationEntity() {
		return subLocationEntity;
	}


	public void setSubLocationEntity(SubLocationEntity subLocationEntity) {
		this.subLocationEntity = subLocationEntity;
	}


	public LocationEntity getReportingLocationEntity() {
		return reportingLocationEntity;
	}


	public void setReportingLocationEntity(LocationEntity reportingLocationEntity) {
		this.reportingLocationEntity = reportingLocationEntity;
	}


	public SubLocationEntity getReportingSublocationEntity() {
		return reportingSublocationEntity;
	}


	public void setReportingSublocationEntity(SubLocationEntity reportingSublocationEntity) {
		this.reportingSublocationEntity = reportingSublocationEntity;
	}
	
	
	

}
