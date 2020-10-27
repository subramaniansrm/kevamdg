package com.kevamdg.sr.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kevamdg.sr.constants.CommonConstant;
import com.kevamdg.sr.entity.SalesOrgEntity;
import com.kevamdg.sr.vo.SalesOrgVO;

@Repository
public class SalesOrgDAO {
	
	@Autowired
	EntityManager entityManager;
	
	
	
	@SuppressWarnings("unchecked")
	public List<SalesOrgEntity> search(SalesOrgVO salesOrgVO) {

		String query = "select e from SalesOrgEntity e where e.deleteFlag=0 ";

		if (null != salesOrgVO.getSalesOrgName()) {
			query = query + " and LOWER(e.salesOrgName) LIKE LOWER('%"+salesOrgVO.getSalesOrgName()+ "%')";
		}
		if (null != salesOrgVO.getSalesOrgCode()) {
			query = query + " and LOWER(e.salesOrgCode) LIKE LOWER('%"+salesOrgVO.getSalesOrgCode()+ "%')";
		}
		if (salesOrgVO.getStatus() != null) {
			if (salesOrgVO.getStatus().equals(CommonConstant.Active)) {
				query = query + " AND e.activeFlag =" + CommonConstant.ACTIVE;
			} else {
				query = query + " AND e.activeFlag =" + CommonConstant.CONSTANT_ZERO;
			}
		}

		query = query + " order by e.id desc";
		
		List<SalesOrgEntity> list = (List<SalesOrgEntity>)entityManager.createQuery(query).getResultList();
	
		return list;
	}
	
	
	public int duplicate(SalesOrgVO salesOrgVO) {
		int count = 0;

		String query = "SELECT COUNT(id) FROM SalesOrgEntity " + " where deleteFlag = "
				+ CommonConstant.CONSTANT_ZERO + " AND LOWER(salesOrgName) = LOWER('"
				+ salesOrgVO.getSalesOrgName().trim() + "')";
		StringBuffer modifiedQuery = new StringBuffer(query);
		if (null != salesOrgVO.getId()) {
			modifiedQuery.append(" AND id != " + salesOrgVO.getId());
		}

		count = (int) (long) entityManager.createQuery(modifiedQuery.toString()).getSingleResult();

		return count;

	}
	
	
	

}
