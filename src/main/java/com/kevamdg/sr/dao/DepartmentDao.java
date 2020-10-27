package com.kevamdg.sr.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kevamdg.sr.vo.DepartmentVO;


/**
 * Department DAO 
 * @author raathikaabm
 *
 */
@Repository
public class DepartmentDao {

	@Autowired
	EntityManager entityManager;

	public List<Object[]> search(DepartmentVO userDepartmentMasterVo) {

		String query = "SELECT d.USER_DEPARTMENT_ID,d.USER_DEPARTMENT_NAME,d.USER_LOCATION_ID,d.USER_SUBLOCATION_ID,"
				+ "  d.DESCRIPTION,u.USER_LOCATION_NAME,s.SUBLOCATION_NAME FROM user_department d"
				+ "  JOIN user_location u ON d.USER_LOCATION_ID = u.USER_LOCATION_ID "
				+ " JOIN user_sublocation s ON d.USER_SUBLOCATION_ID = s.SUBLOCATION_ID WHERE d.delete_flag = '0' ";

		if (userDepartmentMasterVo.getDepartmentName() != null && !userDepartmentMasterVo.getDepartmentName().isEmpty())
			query = query + " AND LOWER(d.USER_DEPARTMENT_NAME) LIKE LOWER('%"
					+ userDepartmentMasterVo.getDepartmentName() + "%')";

		if (userDepartmentMasterVo.getLocationName() != null && !userDepartmentMasterVo.getLocationName().isEmpty()) {
			query = query + " AND LOWER(u.USER_LOCATION_NAME) LIKE LOWER('%" + userDepartmentMasterVo.getLocationName()
					+ "%')";
		}

		if (userDepartmentMasterVo.getSublocationName() != null
				&& !userDepartmentMasterVo.getSublocationName().isEmpty()) {
			query = query + " AND LOWER(s.SUBLOCATION_NAME) LIKE LOWER('%" + userDepartmentMasterVo.getSublocationName()
					+ "%')";
		}
		if (userDepartmentMasterVo.getDescription() != null && !userDepartmentMasterVo.getDescription().isEmpty()) {
			query = query + " AND LOWER(d.DESCRIPTION) LIKE LOWER('%" + userDepartmentMasterVo.getDescription() + "%')";
		}

		query = query + (" ORDER BY d.USER_DEPARTMENT_ID DESC");

		@SuppressWarnings("unchecked")
		List<Object[]> departmentList = (List<Object[]>) entityManager.createNativeQuery(query.toString())
				.getResultList();

		return departmentList;

	}

}
