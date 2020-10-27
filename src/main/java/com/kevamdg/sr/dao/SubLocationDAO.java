package com.kevamdg.sr.dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.kevamdg.sr.constants.CommonConstant;
import com.kevamdg.sr.vo.SubLocationVO;

@Repository
public class SubLocationDAO {

	@Autowired
	EntityManager entityManager;
	
	
	@Value("${commonDatabaseSchema}")
	private String commonDatabaseSchema;
	
	@Value("${mmDatabaseSchema}")
	private String mmDatabaseSchema;


	public int duplicateSubLocation(SubLocationVO subLocationVo) {
		

		String query = "SELECT COUNT(SUBLOCATION_ID) FROM user_sublocation  "
				+ " where  DELETE_FLAG = "
				+ CommonConstant.CONSTANT_ZERO + " AND LOWER(SUBLOCATION_NAME) = LOWER('"
				+ subLocationVo.getSubLocationName().trim() + "') and LOCATION_ID = "
				+ subLocationVo.getId();

		StringBuffer modifiedQuery = new StringBuffer(query);

		if (0 != subLocationVo.getSublocationId()) {
			modifiedQuery.append(" AND SUBLOCATION_ID != " + subLocationVo.getSublocationId());
		}

		BigInteger count = (BigInteger) entityManager.createNativeQuery(modifiedQuery.toString()).getSingleResult();

		return count.intValue();

	}

	/**
	 * Method is for find sub id.
	 * 
	 * @param sublocationId
	 * @return subLocationEntity Object[]
	 */
	public Object[] findBySubId(int sublocationId) {

		String query = "SELECT sub.SUBLOCATION_CODE,sub.SUBLOCATION_NAME,sub.SUBLOCATION_ISACTIVE,"
				+ " loca.USER_LOCATION_NAME , sub.SUBLOCATION_ID,sub.LOCATION_ID "
				+ " FROM "
				+ commonDatabaseSchema
				+ ".user_sublocation sub JOIN "
				+ commonDatabaseSchema
				+ ".user_location loca "
				+ " ON loca.USER_LOCATION_ID = sub.LOCATION_ID "
				+ " WHERE loca.DELETE_FLAG = '" + CommonConstant.CONSTANT_ZERO + "' AND sub.DELETE_FLAG = " + CommonConstant.CONSTANT_ZERO
				+ " AND sub.SUBLOCATION_ID = " + sublocationId;
				

		Object[] subLocationEntity = (Object[]) entityManager.createNativeQuery(query).getSingleResult();

		return subLocationEntity;
	}

	
	/**
	 * This method is used to get the particular values by using different
	 * search condition .
	 * 
	 * @param SubLocationVo
	 *            subLocationVo
	 * @return List<SubLocationEntity> list_SubLocationEntity
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllSearch(SubLocationVO subLocationVo) {

		String query = "SELECT sub.SUBLOCATION_CODE,sub.SUBLOCATION_NAME,sub.SUBLOCATION_ISACTIVE, loca.USER_LOCATION_NAME "
				+ " , sub.SUBLOCATION_ID,sub.LOCATION_ID "
				+ " FROM "
				+  commonDatabaseSchema
				+ ".user_sublocation sub JOIN "
				+ commonDatabaseSchema
				+ ".user_location loca "
				+ " ON loca.USER_LOCATION_ID = sub.LOCATION_ID"
				+ " WHERE loca.DELETE_FLAG = " + CommonConstant.CONSTANT_ZERO
				+ " AND sub.DELETE_FLAG = " + CommonConstant.CONSTANT_ZERO;

		StringBuffer modifyQuery = new StringBuffer(query);

		if (subLocationVo.getSublocationId() != 0)
			modifyQuery.append(" AND sub.SUBLOCATION_ID = " + subLocationVo.getSublocationId());

		if (subLocationVo.getUserLocationName() != null && !subLocationVo.getUserLocationName().isEmpty())
			modifyQuery.append(
					" AND LOWER(loca.USER_LOCATION_NAME) LIKE LOWER('%" + subLocationVo.getUserLocationName() + "%')");

		if (subLocationVo.getSubLocationName() != null && !subLocationVo.getSubLocationName().isEmpty())
			modifyQuery.append(" AND LOWER(sub.SUBLOCATION_NAME) LIKE LOWER('%"
					+ subLocationVo.getSubLocationName() + "%')");

		if (subLocationVo.getSubLocationCode() != null && !subLocationVo.getSubLocationCode().isEmpty())
			modifyQuery.append(" AND LOWER(sub.SUBLOCATION_CODE) LIKE LOWER('%"
					+ subLocationVo.getSubLocationCode() + "%')");

		if (subLocationVo.getStatus() != null) {
			if (subLocationVo.getStatus().equals(CommonConstant.Active)) {
				modifyQuery.append(" and sub.SUBLOCATION_ISACTIVE =" + CommonConstant.ACTIVE);
			} else {
				modifyQuery.append(" and sub.SUBLOCATION_ISACTIVE =" + CommonConstant.CONSTANT_ZERO);
			}
		}

		modifyQuery.append(" ORDER BY sub.SUBLOCATION_ID DESC ");

		List<Object[]> list_SubLocationEntity = (List<Object[]>) entityManager
				.createNativeQuery(modifyQuery.toString()).getResultList();

		return list_SubLocationEntity;

	}
	
}
