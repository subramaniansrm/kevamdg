package com.kevamdg.sr.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kevamdg.sr.constants.CommonConstant;
import com.kevamdg.sr.vo.PackTypeValidationVO;
import com.kevamdg.sr.vo.SalesOrgVO;

@Repository
public class PackTypeValidationDAO {

	@Autowired
	EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<Object[]> getAll() {

		String query = "SELECT pt.PACK_TYPE_ID,pt.PACK_TYPE_NAME,"
				+ " CONCAT (so.SALES_ORGANIZATIN_CODE,' ',so.SALES_ORGANIZATION_NAME),"
				+ " cd.COMMON_DROP_DOWN_NAME,pt.ACTIVE_FLAG FROM pack_type_map_with_sales_organisation pt"
				+ " LEFT JOIN sales_organization so ON so.SALES_ORGANIZATION_ID = pt.SALES_ORG_ID"
				+ " LEFT JOIN common_drop_down cd ON cd.COMMON_DROP_DOWN_ID = pt.PACK_SIZE_ID"
				+ " WHERE pt.DELETE_FLAG = 0 ORDER BY pt.PACK_TYPE_ID DESC";

		List<Object[]> list = (List<Object[]>) entityManager.createNativeQuery(query).getResultList();

		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> search(PackTypeValidationVO packTypeValidation) {

		String query = "SELECT pt.PACK_TYPE_ID,pt.PACK_TYPE_NAME,"
				+ " CONCAT (so.SALES_ORGANIZATIN_CODE,' ',so.SALES_ORGANIZATION_NAME),"
				+ " cd.COMMON_DROP_DOWN_NAME,pt.ACTIVE_FLAG FROM pack_type_map_with_sales_organisation pt"
				+ " LEFT JOIN sales_organization so ON so.SALES_ORGANIZATION_ID = pt.SALES_ORG_ID"
				+ " LEFT JOIN common_drop_down cd ON cd.COMMON_DROP_DOWN_ID = pt.PACK_SIZE_ID"
				+ " WHERE pt.DELETE_FLAG = 0";

		if (null != packTypeValidation.getSalesOrgName()) {
			query = query
					+ " and LOWER(CONCAT (so.SALES_ORGANIZATIN_CODE,' ',so.SALES_ORGANIZATION_NAME)) LIKE LOWER('%"
					+ packTypeValidation.getSalesOrgName() + "%')";
		}
		if (null != packTypeValidation.getPackSizeName()) {
			query = query + " and LOWER(cd.COMMON_DROP_DOWN_NAME) LIKE LOWER('%" + packTypeValidation.getPackSizeName()
					+ "%')";
		}
		if (null != packTypeValidation.getPackTypeName()) {
			query = query + " and LOWER(pt.PACK_TYPE_NAME) LIKE LOWER('%" + packTypeValidation.getPackTypeName()
					+ "%')";
		}
		if (packTypeValidation.getStatus() != null) {
			if (packTypeValidation.getStatus().equals(CommonConstant.Active)) {
				query = query + " AND pt.ACTIVE_FLAG  =" + CommonConstant.ACTIVE;
			} else {
				query = query + " AND pt.ACTIVE_FLAG  =" + CommonConstant.CONSTANT_ZERO;
			}
		}

		query = query + " ORDER BY pt.PACK_TYPE_ID DESC";

		List<Object[]> list = (List<Object[]>) entityManager.createNativeQuery(query).getResultList();

		return list;
	}

	public int duplicate(PackTypeValidationVO packTypeValidation) {
		int count = 0;

		String query = "SELECT COUNT(id) FROM PackTypeValidationEntity " + " where deleteFlag = "
				+ CommonConstant.CONSTANT_ZERO + " AND LOWER(packTypeName) = LOWER('"
				+ packTypeValidation.getPackTypeName().trim() + "') AND packSize =" + packTypeValidation.getPackSize();

		StringBuffer modifiedQuery = new StringBuffer(query);

		if (null == packTypeValidation.getSalesOrgId()) {
			modifiedQuery.append("AND salesOrgId IS NULL");
		} else {
			modifiedQuery.append("AND salesOrgId =" + packTypeValidation.getSalesOrgId());
		}

		if (null != packTypeValidation.getId()) {
			modifiedQuery.append(" AND id != " + packTypeValidation.getId());
		}

		count = (int) (long) entityManager.createQuery(modifiedQuery.toString()).getSingleResult();

		return count;

	}

	public Object[] view(Integer id) {

		String query = "SELECT pt.PACK_TYPE_ID,pt.PACK_TYPE_NAME,"
				+ " CONCAT (so.SALES_ORGANIZATIN_CODE,' ',so.SALES_ORGANIZATION_NAME),"
				+ " cd.COMMON_DROP_DOWN_NAME,pt.ACTIVE_FLAG,pt.SALES_ORG_ID,pt.PACK_SIZE_ID "
				+ " FROM pack_type_map_with_sales_organisation pt"
				+ " LEFT JOIN sales_organization so ON so.SALES_ORGANIZATION_ID = pt.SALES_ORG_ID"
				+ " LEFT JOIN common_drop_down cd ON cd.COMMON_DROP_DOWN_ID = pt.PACK_SIZE_ID"
				+ " WHERE pt.DELETE_FLAG = 0 AND pt.PACK_TYPE_ID =" + id;

		Object[] list = (Object[]) entityManager.createNativeQuery(query).getSingleResult();

		return list;
	}

}
