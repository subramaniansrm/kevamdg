package com.kevamdg.sr.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.kevamdg.sr.constants.CommonConstant;
import com.kevamdg.sr.entity.UserEntity;
import com.kevamdg.sr.vo.AuthDetailsVO;
import com.kevamdg.sr.vo.UserVO;

@Repository
public class UserMasterDao {

	@Autowired
	EntityManager entityManager;

	@Value("${commonDatabaseSchema}")
	private String commonDatabaseSchema;

	@Value("${mmDatabaseSchema}")
	private String mmDatabaseSchema;

	/**
	 * Method is used to get all the users details.
	 * 
	 * @return list List<UserEntity>
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getAll() {

		String query = " SELECT u.USER_ID,u.USER_LOGIN_ID,u.USER_EMPLOYEE_ID,u.FIRST_NAME,u.MIDDLE_NAME,u.LAST_NAME,u.PASSWORD,u.EMAIL_ID,"
				+ " u.MOBILE,u.PHONE_NUMBER,ul.USER_LOCATION_NAME,sl.SUBLOCATION_NAME,ud.USER_DEPARTMENT_NAME,ur.USER_ROLE_NAME FROM USER u"
				+ " LEFT JOIN user_location ul ON ul.USER_LOCATION_ID = u.USER_LOCATION_ID "
				+ " LEFT JOIN user_sublocation sl ON sl.SUBLOCATION_ID = u.USER_SUBLOCATION_ID"
				+ " LEFT JOIN user_department ud ON ud.USER_DEPARTMENT_ID = u.USER_DEPARTMENT_ID "
				+ " LEFT JOIN user_role ur ON ur.ROLE_ID = u.USER_ROLE_ID"
				+ " WHERE u.DELETE_FLAG =0 AND u.ACTIVE = '1' ORDER BY u.USER_ID DESC";

		List<Object[]> list = (List<Object[]>) entityManager.createNativeQuery(query).getResultList();
		return list;

	}

	/**
	 * Method is used to get all the users details.
	 * 
	 * @return list List<UserEntity>
	 */
	public Object[] findId(int id) {

		String query = " SELECT u.USER_ID,u.USER_LOGIN_ID,u.USER_EMPLOYEE_ID,u.FIRST_NAME,u.MIDDLE_NAME,u.LAST_NAME,u.PASSWORD,u.EMAIL_ID,"
				+ " u.MOBILE,u.PHONE_NUMBER,ul.USER_LOCATION_NAME,sl.SUBLOCATION_NAME,ud.USER_DEPARTMENT_NAME"
				+ ",ur.USER_ROLE_NAME,u.USER_LOCATION_ID,u.USER_SUBLOCATION_ID,u.USER_DEPARTMENT_ID,u.USER_ROLE_ID,u.CURRENT_ADDRESS"
				+ ",u.PERMANENT_ADDRESS FROM USER u"
				+ " LEFT JOIN user_location ul ON ul.USER_LOCATION_ID = u.USER_LOCATION_ID "
				+ " LEFT JOIN user_sublocation sl ON sl.SUBLOCATION_ID = u.USER_SUBLOCATION_ID"
				+ " LEFT JOIN user_department ud ON ud.USER_DEPARTMENT_ID = u.USER_DEPARTMENT_ID "
				+ " LEFT JOIN user_role ur ON ur.ROLE_ID = u.USER_ROLE_ID"
				+ " WHERE u.DELETE_FLAG =0 AND u.ACTIVE = '1'" + " AND u.USER_ID=" + id + " ORDER BY u.USER_ID DESC";

		Object[] list = (Object[]) entityManager.createNativeQuery(query).getSingleResult();
		return list;

	}

	/**
	 * To find the user using user login id
	 * 
	 * @param loginId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<UserEntity> uniqueUserLoginId(String loginId) {

		String query = "FROM UserEntity c WHERE " + " c.activeFlag = '" + CommonConstant.CONSTANT_ONE
				+ "' AND c.deleteFlag  = " + CommonConstant.FLAG_ZERO + " AND c.userName = '" + loginId + "' ";

		List<UserEntity> userEntity = null;
		userEntity = (List<UserEntity>) entityManager.createQuery(query).getResultList();
		return userEntity;
	}

	/**
	 * Method is used for GetAllSearch the user details.
	 * 
	 * @param userMasterVo
	 *            UserMasterVo
	 * @return list List<UserEntity>
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllSearch(UserVO userMasterVo) {

		String query = " SELECT u.USER_ID,u.USER_LOGIN_ID,u.USER_EMPLOYEE_ID,u.FIRST_NAME,u.MIDDLE_NAME,u.LAST_NAME,u.PASSWORD,u.EMAIL_ID,"
				+ " u.MOBILE,u.PHONE_NUMBER,ul.USER_LOCATION_NAME,sl.SUBLOCATION_NAME,ud.USER_DEPARTMENT_NAME,ur.USER_ROLE_NAME FROM USER u"
				+ " LEFT JOIN user_location ul ON ul.USER_LOCATION_ID = u.USER_LOCATION_ID "
				+ " LEFT JOIN user_sublocation sl ON sl.SUBLOCATION_ID = u.USER_SUBLOCATION_ID"
				+ " LEFT JOIN user_department ud ON ud.USER_DEPARTMENT_ID = u.USER_DEPARTMENT_ID "
				+ " LEFT JOIN user_role ur ON ur.ROLE_ID = u.USER_ROLE_ID"
				+ " WHERE u.DELETE_FLAG =0 AND u.ACTIVE = '1' AND u.COMPANY_ID IN (0,1)";

		if (userMasterVo.getUserEmployeeId() != null && !userMasterVo.getUserEmployeeId().isEmpty()) {
			query = query + " and LOWER(u.USER_EMPLOYEE_ID) LIKE LOWER('%" + userMasterVo.getUserEmployeeId() + "%')";
		}
		if (userMasterVo.getFirstName() != null && !userMasterVo.getFirstName().isEmpty()) {
			query = query + " and LOWER(u.FIRST_NAME) LIKE LOWER('%" + userMasterVo.getFirstName() + "%')";
		}
		if (userMasterVo.getUserLoginId() != null && !userMasterVo.getUserLoginId().isEmpty()) {
			query = query + " and u.USER_LOGIN_ID =  '" + userMasterVo.getUserLoginId() + "'";
		}

		if (userMasterVo.getUserLocationName() != null) {
			query = query + " and LOWER(ul.USER_LOCATION_NAME) LIKE LOWER('%" + userMasterVo.getUserLocationName()
					+ "%')";
		}

		if (userMasterVo.getSubLocationName() != null) {
			query = query + " and LOWER(sl.SUBLOCATION_NAME) LIKE LOWER('%" + userMasterVo.getSubLocationName() + "%')";
		}

		if (userMasterVo.getMiddleName() != null && !userMasterVo.getMiddleName().isEmpty()) {
			query = query + " and LOWER(u.MIDDLE_NAME) LIKE LOWER('%" + userMasterVo.getMiddleName() + "%')";
		}

		if (userMasterVo.getLastName() != null && !userMasterVo.getLastName().isEmpty()) {
			query = query + " and LOWER(u.LAST_NAME) LIKE LOWER('%" + userMasterVo.getLastName() + "%')";
		}

		if (userMasterVo.getUserDepartmentName() != null) {
			query = query + " and LOWER(ud.USER_DEPARTMENT_NAME) LIKE LOWER('%" + userMasterVo.getUserDepartmentName()
					+ "%')";
		}

		if (userMasterVo.getUserRoleName() != null) {
			query = query + " and LOWER(ur.USER_ROLE_NAME) LIKE LOWER('%" + userMasterVo.getUserRoleName() + "%')";
		}
		if (userMasterVo.getMobile() != null && !userMasterVo.getMobile().isEmpty()) {
			query = query + " and u.MOBILE LIKE '%" + userMasterVo.getMobile() + "%'";
		}
		if (userMasterVo.getPhoneNumber() != null && !userMasterVo.getPhoneNumber().isEmpty()) {
			query = query + " and u.PHONE_NUMBER LIKE '%" + userMasterVo.getPhoneNumber() + "%'";
		}
		if (userMasterVo.getEmailId() != null && !userMasterVo.getEmailId().isEmpty()) {
			query = query + " and LOWER(u.EMAIL_ID) LIKE LOWER('%" + userMasterVo.getEmailId() + "%')";
		}

		query = query + " ORDER BY  u.USER_ID DESC";

		List<Object[]> list = (List<Object[]>) entityManager.createNativeQuery(query).getResultList();

		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getModuleList(int id) {

		String query = "Select m.USER_MAPPING_MODULE_PK,m.USER_FK,m.MODULE_FK,m.IS_ACTIVE,mo.MODULE_NAME"
				+ " from user_mapping_module m" + " LEFT JOIN module mo ON mo.MODULE_PK = m.MODULE_FK "
				+ " Where m.USER_FK =" + id + " order by m.USER_MAPPING_MODULE_PK desc";

		List<Object[]> list = (List<Object[]>) entityManager.createNativeQuery(query).getResultList();

		return list;

	}

	public void roleMapping(UserEntity userEntity, AuthDetailsVO authDetailsVO) {

		String query = " insert  into " + mmDatabaseSchema + ".user_role_mapping"
				+ " (USER_FK,ROLE_FK,IS_ACTIVE,CREATED_BY,CREATED_DATE,UPDATED_BY,UPDATED_DATE) " + " values ("
				+ userEntity.getId() + "," + userEntity.getRoleId() + "," + CommonConstant.ACTIVE + " ,"
				+ authDetailsVO.getUserId() + ",'" + CommonConstant.getCurrentDateTimeAsString() + "'" + ","
				+ authDetailsVO.getUserId() + ",'" + CommonConstant.getCurrentDateTimeAsString() + "')";

		entityManager.createNativeQuery(query).executeUpdate();

	}

	public void roleMappingUpdate(UserEntity userEntity, AuthDetailsVO authDetailsVO, Integer id) {

		String query = " Update " + mmDatabaseSchema + ".user_role_mapping" + " SET ROLE_FK =" + userEntity.getRoleId()
				+ ",UPDATED_BY =" + authDetailsVO.getUserId() + ",UPDATED_DATE ='"
				+ CommonConstant.getCurrentDateTimeAsString() + "' where USER_ROLE_MAPPING_PK =" + id;

		entityManager.createNativeQuery(query).executeUpdate();

	}

	public Integer role(UserEntity userEntity) {

		try {
			String query = " SELECT USER_ROLE_MAPPING_PK FROM " + mmDatabaseSchema + ".user_role_mapping" + " WHERE IS_ACTIVE = 1 AND USER_FK = "
					+ userEntity.getId() + " AND ROLE_FK = " + userEntity.getRoleId();

			Integer list = (Integer) entityManager.createNativeQuery(query).getSingleResult();

			return list;
		} catch (NoResultException e) {

		}

		return null;

	}

}
