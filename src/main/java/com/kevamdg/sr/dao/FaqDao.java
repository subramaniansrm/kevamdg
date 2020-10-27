package com.kevamdg.sr.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kevamdg.sr.constants.CommonConstant;
import com.kevamdg.sr.entity.FaqEntity;

/**
 * This Dao class is used to get frequently asked question details from faq
 * table
 * 
 * @author manoj [SRM]
 */
@Repository
public class FaqDao {

	@Autowired
	EntityManager entityManager;
	
	/**
	 * This method is to retrieve frequently asked question details from faq
	 * table
	 * 
	 * @return FaqForm
	 */
	public List<FaqEntity> getCommonFaq() {

		String query = "FROM FaqEntity where deleteFlag = " + CommonConstant.CONSTANT_ZERO;
		// " ORDER BY create_date DESC ";

		@SuppressWarnings("unchecked")
		List<FaqEntity> listFaqEntity = (List<FaqEntity>) entityManager.createQuery(query).getResultList();

		return listFaqEntity;

	}
}
