package com.kevamdg.sr.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.kevamdg.sr.vo.LocationVO;

/**
 * DAO class used to access database and get result
 * 
 * @author raathikaabm
 *
 */
@Repository
public class LocationDao {

	@Autowired
	EntityManager entityManager;
	
	@Value("${commonDatabaseSchema}")
	private String commonDatabaseSchema;
	
	@Value("${mmDatabaseSchema}")
	private String mmDatabaseSchema;

	/**
	 * Method used to search location details
	 * 
	 * @param userLocationMasterVo
	 * @return
	 */
	public List<Object[]> search(LocationVO userLocationMasterVo) {

		String query = "Select l.USER_LOCATION_ID,l.USER_LOCATION_NAME,l.USER_LOCATION_DETAILS,l.CITY,l.STATE,"
				+ " l.COUNTRY as name,l.ZIP,l.PHONE,l.FAX,l.EMAIL,l.CONTACT_NAME,l.ACTIVE,l.DELETE_FLAG,l.SYS_APP_ID,l.CREATED_DATE,"
				+ " l.CREATED_BY,l.UPDATED_DATE,l.UPDATED_BY,c.CITY_NAME,s.STATE_NAME ,ct.COUNTRY"
				+ " from "
				+ commonDatabaseSchema+".user_location l LEFT JOIN "
				+ commonDatabaseSchema+".city c on "
				+ " l.CITY = c.CITY_ID LEFT JOIN "
				+ commonDatabaseSchema+".STATE s ON l.STATE = s.STATE_ID"
				+ " LEFT JOIN country ct on l.COUNTRY = ct.COUNTRY_ID "
				+ " where l.delete_flag='0'";

		StringBuffer modifiedQuery = new StringBuffer(query);

		if (userLocationMasterVo.getUserLocationDetails() != null
				&& !userLocationMasterVo.getUserLocationDetails().isEmpty()) {
			modifiedQuery.append(" and LOWER(l.USER_LOCATION_DETAILS) LIKE LOWER('%"
					+ userLocationMasterVo.getUserLocationDetails() + "%')");
		}

		if (userLocationMasterVo.getUserLocationName() != null
				&& !userLocationMasterVo.getUserLocationName().isEmpty()) {
			modifiedQuery.append(" and LOWER(l.USER_LOCATION_NAME) LIKE LOWER('%"
					+ userLocationMasterVo.getUserLocationName() + "%')");
		}

		if (userLocationMasterVo.getZip() != null && !userLocationMasterVo.getZip().isEmpty()) {
			modifiedQuery.append(" and l.ZIP LIKE '%" + userLocationMasterVo.getZip() + "%'");
		}

		if (userLocationMasterVo.getPhone() != null && !userLocationMasterVo.getPhone().isEmpty()) {
			modifiedQuery.append(" and  l.PHONE LIKE '%" + userLocationMasterVo.getPhone() + "%'");
		}

		if (userLocationMasterVo.getFax() != null && !userLocationMasterVo.getFax().isEmpty()) {
			modifiedQuery.append(" and l.FAX LIKE '%" + userLocationMasterVo.getFax() + "%'");
		}
		if (userLocationMasterVo.getEmail() != null && !userLocationMasterVo.getEmail().isEmpty()) {
			modifiedQuery.append(" and LOWER(l.EMAIL) LIKE LOWER('%" + userLocationMasterVo.getEmail() + "%')");
		}
		if (userLocationMasterVo.getContactName() != null && !userLocationMasterVo.getContactName().isEmpty()) {
			modifiedQuery
					.append(" and LOWER(l.CONTACT_NAME) LIKE LOWER('%" + userLocationMasterVo.getContactName() + "%')");
		}

		if (userLocationMasterVo.getCityName() != null) {
			modifiedQuery.append(" and c.CITY_NAME like '%" + userLocationMasterVo.getCityName() + "%'");
		}
		/*
		 * if (userLocationMasterVo.getCountryName() != null) {
		 * modifiedQuery.append(" and c.countryEntity.country like '%" +
		 * userLocationMasterVo.getCountryName() + "%'"); }
		 */
		if (userLocationMasterVo.getStateName() != null) {
			modifiedQuery.append(" and s.STATE_NAME like '%" + userLocationMasterVo.getStateName() + "%'");
		}
		
		if (userLocationMasterVo.getCountryName()!= null) {
			modifiedQuery.append(" and ct.COUNTRY like '%" + userLocationMasterVo.getCountryName() + "%'");
		}

		modifiedQuery.append(" ORDER BY l.USER_LOCATION_ID DESC");

		@SuppressWarnings("unchecked")
		List<Object[]> userLocationList = (List<Object[]>) entityManager.createNativeQuery(modifiedQuery.toString())
				.getResultList();

		return userLocationList;
	}

	/**
	 * Method used to get all location details
	 * 
	 * @return
	 */
	public List<Object[]> getLocation() {

		String query = "Select l.USER_LOCATION_ID,l.USER_LOCATION_NAME,l.USER_LOCATION_DETAILS,l.CITY,l.STATE,"
				+ " l.COUNTRY as name,l.ZIP,l.PHONE,l.FAX,l.EMAIL,l.CONTACT_NAME,l.ACTIVE,l.DELETE_FLAG,l.SYS_APP_ID,l.CREATED_DATE,"
				+ " l.CREATED_BY,l.UPDATED_DATE,l.UPDATED_BY,c.CITY_NAME,s.STATE_NAME,ct.COUNTRY from user_location l "
				+ " LEFT JOIN city c on "
				+ " l.CITY = c.CITY_ID LEFT JOIN STATE s ON l.STATE = s.STATE_ID"
				+ " LEFT JOIN country ct on l.COUNTRY = ct.COUNTRY_ID "
				+ " where l.delete_flag='0' ORDER BY l.USER_LOCATION_ID DESC";
		@SuppressWarnings("unchecked")
		List<Object[]> userLocationList = (List<Object[]>) entityManager.createNativeQuery(query.toString())
				.getResultList();

		return userLocationList;

	}

	public List<Object[]> findOne(Integer id) {
		String query = "Select l.USER_LOCATION_ID,l.USER_LOCATION_NAME,l.USER_LOCATION_DETAILS,l.CITY,l.STATE,"
				+ " l.COUNTRY as name,l.ZIP,l.PHONE,l.FAX,l.EMAIL,l.CONTACT_NAME,l.ACTIVE,l.DELETE_FLAG,l.SYS_APP_ID,l.CREATED_DATE,"
				+ " l.CREATED_BY,l.UPDATED_DATE,l.UPDATED_BY,c.CITY_NAME,s.STATE_NAME,ct.COUNTRY  from user_location l "
				+ " LEFT JOIN city c on "
				+ " l.CITY = c.CITY_ID LEFT JOIN STATE s ON l.STATE = s.STATE_ID "
				+ "  LEFT JOIN country ct on l.COUNTRY = ct.COUNTRY_ID"
				+ " where l.delete_flag='0' and l.USER_LOCATION_ID= "
				+ id + "";
		@SuppressWarnings("unchecked")
		List<Object[]> userLocationList = (List<Object[]>) entityManager.createNativeQuery(query.toString())
				.getResultList();

		return userLocationList;
	}

}
