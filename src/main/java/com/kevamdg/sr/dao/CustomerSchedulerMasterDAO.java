package com.kevamdg.sr.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.kevamdg.sr.constants.CommonConstant;
import com.kevamdg.sr.entity.CustomerEntity;

@Repository
public class CustomerSchedulerMasterDAO {

	@Autowired
	EntityManager entityManager;

	@Value("${commonDatabaseSchema}")
	private String commonDatabaseSchema;

	@Value("${mmDatabaseSchema}")
	private String mmDatabaseSchema;

	public CustomerEntity customerCodeCheck(String Code) {

		try {
			String query = "SELECT e FROM CustomerEntity e " + " where e.deleteFlag = " + CommonConstant.CONSTANT_ZERO
					+ " AND LOWER(e.customerCode) = LOWER('" + Code.trim() + "')";

			CustomerEntity customer = (CustomerEntity) entityManager.createQuery(query).getSingleResult();

			return customer;
		} catch (NoResultException e) {

		}
		return null;

	}

	public Integer city(String Code) {

		try {
			String query = "SELECT e.cityId FROM CityEntity e " + " where LOWER(e.cityName) LIKE LOWER('" + Code.trim()
					+ "%')";

			Integer city = (Integer) entityManager.createQuery(query).getSingleResult();

			return city;
		} catch (NoResultException e) {

		}
		return null;

	}

	public Integer sales(String Code) {

		try {
			String query = "SELECT e.id FROM SalesOrgEntity e "
					+ " where e.activeFlag=1 and e.deleteFlag = 0 and LOWER(e.salesOrgCode) LIKE LOWER('" + Code.trim()
					+ "%')";

			Integer city = (Integer) entityManager.createQuery(query).getSingleResult();

			return city;
		} catch (NoResultException e) {

		}
		return null;

	}
	
	
	
	public Integer plant(String Code) {

		try {
			String query = "SELECT e.PLANT_ID FROM "+ mmDatabaseSchema + ".plant e "
					+ " where e.DELETE_FLAG = 0 and LOWER(e.PLANT_CODE) LIKE LOWER('" + Code.trim()
					+ "%')";

			Integer plant = (Integer) entityManager.createNativeQuery(query).getSingleResult();

			return plant;
		} catch (NoResultException e) {

		}
		return null;

	}
	
	public Integer dc(String Code) {

		try {
			String query = "SELECT l.id from CommonDropdownEntity l "
					+ " where l.deleteFlag= '0' and l.fieldId = '" + CommonConstant.DISTRIBUTION_CHANNEL
					+ "' and LOWER(l.dropdownCode) LIKE LOWER('" + Code.trim()+ "%')";

			Integer plant = (Integer) entityManager.createQuery(query).getSingleResult();

			return plant;
		} catch (NoResultException e) {

		}
		return null;

	}

}
