package com.kevamdg.sr.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kevamdg.sr.vo.StateVO;

/**
 * State Dao used to retrieve details using query
 * 
 * @author raathikaabm
 *
 */

@Repository
public class StateDao {
	@Autowired
	EntityManager entityManager;

	/**
	 * Method used to get all state details
	 * 
	 * @return
	 */
	public List<Object[]> getAllList() {

		String query = "select s.STATE_ID,s.STATE_NAME,s.SYS_APP_ID,s.COUNTRY_ID,s.DELETE_FLAG,"
				+ " s.CREATED_DATE,s.CREATED_BY,s.UPDATED_DATE,s.UPDATED_BY,c.COUNTRY from state s left join "
				+ " country c on s.COUNTRY_ID = c.COUNTRY_ID where s.DELETE_FLAG = '0'";
		@SuppressWarnings("unchecked")
		List<Object[]> stateList = (List<Object[]>) entityManager.createNativeQuery(query.toString()).getResultList();

		return stateList;
	}

	/**
	 * Method used to view state details
	 * 
	 * @param stateId
	 * @return
	 */

	public List<Object[]> view(Integer stateId) {
		String query = "select s.STATE_ID,s.STATE_NAME,s.SYS_APP_ID,s.COUNTRY_ID,s.DELETE_FLAG,"
				+ " s.CREATED_DATE,s.CREATED_BY,s.UPDATED_DATE,s.UPDATED_BY,c.COUNTRY from state s left join "
				+ " country c on s.COUNTRY_ID = c.COUNTRY_ID where s.DELETE_FLAG = '0' " + " and s.STATE_ID = "
				+ stateId + " ";
		@SuppressWarnings("unchecked")
		List<Object[]> stateList = (List<Object[]>) entityManager.createNativeQuery(query.toString()).getResultList();

		return stateList;
	}

	/**
	 * Method used to search state details
	 * 
	 * @param stateVO
	 * @return
	 */
	public List<Object[]> search(StateVO stateVO) {

		String query = " select s.STATE_ID,s.STATE_NAME,s.SYS_APP_ID,s.COUNTRY_ID,s.DELETE_FLAG,"
				+ " s.CREATED_DATE,s.CREATED_BY,s.UPDATED_DATE,s.UPDATED_BY,c.COUNTRY from state s left join "
				+ " country c on s.COUNTRY_ID = c.COUNTRY_ID where s.DELETE_FLAG = '0'";
		StringBuffer modifiedQuery = new StringBuffer(query);

		if (stateVO.getStateName() != null && !stateVO.getStateName().isEmpty()) {
			modifiedQuery.append(" and LOWER(s.STATE_NAME) LIKE LOWER('%" + stateVO.getStateName() + "%')");
		}

		if (stateVO.getCountryName() != null) {
			modifiedQuery.append(" and c.COUNTRY like '%" + stateVO.getCountryName() + "%'");
		}

		modifiedQuery.append(" ORDER BY s.STATE_ID DESC");

		@SuppressWarnings("unchecked")
		List<Object[]> stateList = (List<Object[]>) entityManager.createNativeQuery(modifiedQuery.toString())
				.getResultList();

		return stateList;

	}

}
