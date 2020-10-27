package com.kevamdg.sr.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kevamdg.sr.constants.CommonConstant;
import com.kevamdg.sr.entity.CountryEntity;
import com.kevamdg.sr.vo.CountryVO;

@Repository
public class CountryDAO {
	
	@Autowired
	EntityManager entityManager;

	/**
	 * Methods used to search value in 
	 * @param countryVO CountryVO
	 * @return countryList List<CountryEntity>
	 */
	@SuppressWarnings("unchecked")
	public List<CountryEntity> getSearch(CountryVO countryVO) {

		String query = "select e from CountryEntity e where e.deleteFlag=0 ";

		if (null != countryVO.getCountry()) {
			query = query + "and LOWER(e.country) LIKE LOWER('%"+countryVO.getCountry()+ "%')";
		}
		if (null != countryVO.getCountryCode()) {
			query = query + "and LOWER(e.countryCode) LIKE LOWER('%"+countryVO.getCountryCode()+ "%')";
		}

		query = query + " order by e.id desc";
		
		List<CountryEntity> countryList = (List<CountryEntity>)entityManager.createQuery(query).getResultList();
	
		return countryList;
	}
	
	
	
	
	
	/**
	 * Method used to duplicate the country validation
	 * @param countryVO CountryVO
	 * @return int
	 */
	public int duplicate(CountryVO countryVO) {
		int count = 0;

		String query = "SELECT COUNT(id) FROM CountryEntity " + " where deleteFlag = "
				+ CommonConstant.CONSTANT_ZERO + " AND LOWER(country) = LOWER('"
				+ countryVO.getCountry().trim() + "') AND LOWER(countryCode) = LOWER('"
				+ countryVO.getCountryCode().trim() + "')";
		StringBuffer modifiedQuery = new StringBuffer(query);
		if (null != countryVO.getId()) {
			modifiedQuery.append(" AND id != " + countryVO.getId());
		}

		count = (int) (long) entityManager.createQuery(modifiedQuery.toString()).getSingleResult();

		return count;

	}

}
