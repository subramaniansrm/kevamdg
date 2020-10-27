package com.kevamdg.sr.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kevamdg.sr.constants.CommonConstant;
import com.kevamdg.sr.vo.DropdownCommonVO;

@Repository
public class CommonDropdownMasterDAO {

	@Autowired
	EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<Object[]> search(DropdownCommonVO dropdownCommonVO) {

		String query = "select e.id,e.dropdownCode,e.dropdownName,e.activeFlag,sf.screenFieldName "
				+ " ,CONCAT(so.salesOrgCode,' ',so.salesOrgName) from CommonDropdownEntity e,"
				+ " CommonScreenFieldEntity sf ,SalesOrgEntity so where "
				+ " e.fieldId=sf.id AND so.id = e.salesOrgId AND e.deleteFlag=0 ";

		if (null != dropdownCommonVO.getDropdownCode()) {
			query = query + " and LOWER(e.dropdownCode) LIKE LOWER('%" + dropdownCommonVO.getDropdownCode() + "%')";
		}
		if (null != dropdownCommonVO.getDropdownName()) {
			query = query + " and LOWER(e.dropdownName) LIKE LOWER('%" + dropdownCommonVO.getDropdownName() + "%')";
		}
		if (null != dropdownCommonVO.getFieldName()) {
			query = query + " and LOWER(sf.screenFieldName) LIKE LOWER('%" + dropdownCommonVO.getFieldName() + "%')";
		}
		if (null != dropdownCommonVO.getSalesOrgName()) {
			query = query + " and LOWER(CONCAT(so.salesOrgCode,' ',so.salesOrgName)) LIKE LOWER('%" + dropdownCommonVO.getSalesOrgName() + "%')";
		}
		if (dropdownCommonVO.getStatus() != null) {
			if (dropdownCommonVO.getStatus().equals(CommonConstant.Active)) {
				query = query + " AND e.activeFlag =" + CommonConstant.ACTIVE;
			} else {
				query = query + " AND e.activeFlag =" + CommonConstant.CONSTANT_ZERO;
			}
		}

		query = query + " order by e.id desc";

		List<Object[]> list = (List<Object[]>) entityManager.createQuery(query).getResultList();

		return list;
	}

	public int duplicate(DropdownCommonVO dropdownCommonVO) {
		int count = 0;

		String query = "SELECT COUNT(id) FROM CommonDropdownEntity " + " where deleteFlag = "
				+ CommonConstant.CONSTANT_ZERO + " AND LOWER(dropdownName) = LOWER('"
				+ dropdownCommonVO.getDropdownName().trim() + "')" + " AND fieldId = "
				+ dropdownCommonVO.getFieldId();
		StringBuffer modifiedQuery = new StringBuffer(query);
		if (null != dropdownCommonVO.getId()) {
			modifiedQuery.append(" AND id != " + dropdownCommonVO.getId());
		}

		count = (int) (long) entityManager.createQuery(modifiedQuery.toString()).getSingleResult();

		return count;

	}

}
