package com.kevamdg.sr.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kevamdg.sr.constants.CommonConstant;
import com.kevamdg.sr.vo.UserRoleVO;

@Repository
public class UserRoleDAO {

	@Autowired
	EntityManager entityManager;

	public int duplicateRole(UserRoleVO userRoleMaster) {
		int count = 0;

		String query = "SELECT COUNT(id) FROM UserRoleEntity " + " where deleteFlag = "
				+ CommonConstant.CONSTANT_ZERO + " AND LOWER(userRoleName) = LOWER('"
				+ userRoleMaster.getUserRoleName().trim() + "') and userDepartment = "
				+ userRoleMaster.getUserDepartment();
		StringBuffer modifiedQuery = new StringBuffer(query);
		if (null != userRoleMaster.getId()) {
			modifiedQuery.append(" AND id != " + userRoleMaster.getId());
		}

		count = (int) (long) entityManager.createQuery(modifiedQuery.toString()).getSingleResult();

		return count;

	}

	/**
	 * method to search userRole
	 * 
	 * @param searchUserRoleMaster
	 *            UserRoleVo
	 * @return list List<UserRoleEntity>
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getGlobalSearchUserRole(UserRoleVO searchUserRoleMaster) {

		String query = " SELECT ur.ROLE_ID,ur.USER_ROLE_NAME,ul.USER_LOCATION_NAME,us.SUBLOCATION_NAME"
				+ " ,ud.USER_DEPARTMENT_NAME,ur.DESCRIPTION,ut.TYPE_OF_USER"
				+ " FROM user_role ur LEFT JOIN user_type ut ON ut.USER_TYPE_ID = ur.ROLE_TYPE"
				+ " LEFT JOIN user_location ul ON ul.USER_LOCATION_ID = ur.USER_LOCATION_ID"
				+ " LEFT JOIN user_sublocation us ON us.SUBLOCATION_ID = ur.SUBLOCATION_ID"
				+ " LEFT JOIN user_department ud ON ud.USER_DEPARTMENT_ID = ur.USER_DEPARTMENT_ID"
				+ " WHERE ur.DELETE_FLAG = 0 ";

		if (searchUserRoleMaster.getUserRoleName() != null && !searchUserRoleMaster.getUserRoleName().isEmpty()) {
			query = query + " and LOWER(ur.USER_ROLE_NAME) LIKE LOWER('%" + searchUserRoleMaster.getUserRoleName()
					+ "%')";
		}
		if (searchUserRoleMaster.getUserDepartmentName() != null) {
			query = query + " and LOWER(ud.USER_DEPARTMENT_NAME) LIKE LOWER('%"
					+ searchUserRoleMaster.getUserDepartmentName() + "%')";
		}
		if (searchUserRoleMaster.getUserLocationName() != null) {
			query = query + " and LOWER(ul.USER_LOCATION_NAME) LIKE LOWER('%"
					+ searchUserRoleMaster.getUserLocationName() + "%')";
		}

		if (searchUserRoleMaster.getSublocationName() != null) {
			query = query + " and LOWER(us.SUBLOCATION_NAME) LIKE LOWER('%" + searchUserRoleMaster.getSublocationName()
					+ "%')";
		}

		if (searchUserRoleMaster.getRoleTypeName() != null) {
			query = query + " and LOWER(ut.TYPE_OF_USER) LIKE LOWER('%" + searchUserRoleMaster.getRoleTypeName()
					+ "%')";
		}

		if (searchUserRoleMaster.getDescription() != null && !searchUserRoleMaster.getDescription().isEmpty()) {
			query = query + " and LOWER(ur.DESCRIPTION) LIKE LOWER('%" + searchUserRoleMaster.getDescription() + "%')";
		}
		query = query + " ORDER BY ur.ROLE_ID desc";

		List<Object[]> list = (List<Object[]>) entityManager.createNativeQuery(query).getResultList();

		return list;
	}

	/**
	 * method to search userRole
	 * 
	 * @param searchUserRoleMaster
	 *            UserRoleVo
	 * @return list List<UserRoleEntity>
	 */
	public Object[] findId(int id) {

		String query = " SELECT ur.ROLE_ID,ur.USER_ROLE_NAME,ul.USER_LOCATION_NAME,us.SUBLOCATION_NAME"
				+ " ,ud.USER_DEPARTMENT_NAME,ur.DESCRIPTION,ut.TYPE_OF_USER,ur.USER_LOCATION_ID"
				+ " ,ur.SUBLOCATION_ID,ur.USER_DEPARTMENT_ID,ur.ROLE_TYPE"
				+ " FROM user_role ur LEFT JOIN user_type ut ON ut.USER_TYPE_ID = ur.ROLE_TYPE"
				+ " LEFT JOIN user_location ul ON ul.USER_LOCATION_ID = ur.USER_LOCATION_ID"
				+ " LEFT JOIN user_sublocation us ON us.SUBLOCATION_ID = ur.SUBLOCATION_ID"
				+ " LEFT JOIN user_department ud ON ud.USER_DEPARTMENT_ID = ur.USER_DEPARTMENT_ID"
				+ " WHERE ur.DELETE_FLAG = 0 " + " AND ur.ROLE_ID = " + id;

		Object[] list = (Object[]) entityManager.createNativeQuery(query).getSingleResult();

		return list;
	}

}
