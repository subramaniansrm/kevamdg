package com.kevamdg.sr.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.kevamdg.sr.constants.CommonConstant;
import com.kevamdg.sr.entity.UserMappingEntity;
import com.kevamdg.sr.vo.AuthDetailsVO;
import com.kevamdg.sr.vo.ScreenAuthenticationVO;
import com.kevamdg.sr.vo.ScreenAuthorizationVO;
import com.kevamdg.sr.vo.ScreenVO;
import com.kevamdg.sr.vo.UserMappingVo;

/**
 * This DAO class is used to Create , Update , Delete , Get all , Get all search,
 *  Load the user mapping.
 * 
 * @author Sai
 *
 */
/**
 * This Method is to get all the details.
 * 
 * @return userMappingEntityList List<userMappingEntity>
 *
 */

@Repository
public class UserMappingDao {
	
	@Autowired
	EntityManager entityManager;
	
	@Value("${commonDatabaseSchema}")
	public String commonDatabaseSchema;

	@SuppressWarnings("unchecked")
	public List<Object[]> getAll() {
		
		List<Object[]> userMappingEntityList = null;
		
		
		try{
			/*String qury = " SELECT u.userMappingId,u.userLocationEntity.id,loc.userLocationName as locname, "
					+ " u.SubLocationEntity.sublocationId,sub.subLocationName as subname, u.userDepartmentEntity.id,"
					+ " dep.userDepartmentName as deptname,u.userRoleEntity.id,ur.userRoleName,u.level.commonId,com.itemValue,"
					+ " u.reportingLocationEntity.id,u.reportingSublocationEntity.sublocationId,u.reportingUserDepartmentEntity.id,"
					+ " u.reportingToUser.id,u.userEntity.id," 
					+ " CONCAT(us.firstName,' ',us.lastName) as userName,rloc.userLocationName as rlocname,"
					+ " rsub.subLocationName as rsubname,udep.userDepartmentName as rdeptname,"
					+ " CONCAT(urt.firstName,' ',urt.lastName) as reportingUserName FROM UserMappingEntity u "
					+ " JOIN CommonStorageEntity com ON com.commonId =  u.level.commonId "
					+ " JOIN SubLocationEntity sub ON sub.sublocationId = u.SubLocationEntity.sublocationId "
					+ " JOIN UserLocationEntity loc ON loc.id = u.userLocationEntity.id "
					+ " JOIN UserDepartmentEntity dep ON dep.id = u.userDepartmentEntity.id "
					+ " JOIN UserRoleEntity ur ON ur.id = u.userRoleEntity.id "
					+ " JOIN UserLocationEntity rloc ON rloc.id =  u.reportingLocationEntity.id"
					+ " JOIN SubLocationEntity rsub ON rsub.sublocationId  = u.u.reportingSublocationEntity.sublocationId "
					+ " JOIN UserDepartmentEntity udep ON udep.id = u.reportingUserDepartmentEntity.id "
					+ " JOIN UserEntity us ON us.id = u.userEntity.id "
					+ " JOIN UserEntity urt ON urt.id = u.reportingToUser.id "
					+ " WHERE u.deleteFlag = '" + CommonConstant.CONSTANT_ZERO +"'" 
					+ " AND u.sysAppEntity.sysAppId = " + CommonConstant.CONSTANT_TWO
					+ " ORDER BY u.userMappingId DESC";*/
			
			
			
		String query = " SELECT u.USER_MAPPING_ID,u.USER_LOCATION_ID,loc.USER_LOCATION_NAME as locname,u.USER_SUBLOCATION_ID,"
				+ " sub.SUBLOCATION_NAME as subname,"
				+ " u.USER_DEPARTMENT_ID,dep.USER_DEPARTMENT_NAME as deptname ,u.USER_ROLE_ID,ur.USER_ROLE_NAME,u.LEVEL,com.ITEM_VALUE,"
				+ " u.REPORTING_TO_LOCATION,u.REPORTING_TO_SUBLOCATION,u.REPORTING_DEPARTMENT,u.REPORTING_TO,u.USER_ID,"
				+ " CONCAT(us.FIRST_NAME,' ',us.LAST_NAME) as userName,rloc.USER_LOCATION_NAME as rlocname,"
				+ " rsub.SUBLOCATION_NAME as rsubname,udep.USER_DEPARTMENT_NAME as rdeptname,"
				+ " CONCAT(urt.FIRST_NAME,' ',urt.LAST_NAME) as reportingUserName FROM user_mapping u "
				+ " LEFT JOIN common_storage com ON com.COMMON_ID =  u.LEVEL "
				+ " LEFT JOIN user_sublocation sub ON sub.SUBLOCATION_ID = u.USER_SUBLOCATION_ID "
				+ " LEFT JOIN user_location loc ON loc.USER_LOCATION_ID = u.USER_LOCATION_ID "
				+ " LEFT JOIN user_department dep ON dep.USER_DEPARTMENT_ID = u.USER_DEPARTMENT_ID "
				+ " LEFT JOIN user_role ur ON ur.ROLE_ID = u.USER_ROLE_ID "
				+ " LEFT JOIN user_location rloc ON rloc.USER_LOCATION_ID = u.REPORTING_TO_LOCATION "
				+ " LEFT JOIN user_sublocation rsub ON rsub.SUBLOCATION_ID  = u.REPORTING_TO_SUBLOCATION "
				+ " LEFT JOIN user_department udep ON udep.USER_DEPARTMENT_ID = u.REPORTING_DEPARTMENT "
				+ " LEFT JOIN `user` us ON us.USER_ID = u.USER_ID "
				+ " LEFT JOIN `user` urt ON urt.USER_ID = u.REPORTING_TO "
				+ " WHERE u.DELETE_FLAG = '0' ORDER BY u.USER_MAPPING_ID DESC";

		 userMappingEntityList = (List<Object[]>) entityManager.createNativeQuery(query)
				.getResultList();
		return userMappingEntityList;
		}
		catch(Exception e){
			e.getMessage();
			e.printStackTrace();
		}
		return userMappingEntityList;
	}

	public int UserMapping(UserMappingVo userMappingVo) {
		String query = "SELECT COUNT(u.userMappingId) FROM UserMappingEntity u WHERE u.userLocationEntity.id = " + userMappingVo.getUserLocationId()
				+ " AND u.userDepartmentEntity.departmentId = " + userMappingVo.getUserDepartmentId()
				+ "  AND u.userRoleEntity.id = " + userMappingVo.getUserRoleId() + " AND u.level.commonId = "
				+ userMappingVo.getLevelId() + " AND u.reportingUserDepartmentEntity.departmentId = "
				+ userMappingVo.getReportingUserDepartment() + " AND u.reportingToUser.id = "
				+ userMappingVo.getReportingToUser() + " AND u.userEntity.id = " + userMappingVo.getUserId()
				+ " AND u.deleteFlag  = '" + CommonConstant.FLAG_ZERO + "'";

		StringBuffer modifiedQuery = new StringBuffer(query);
		if (0 != userMappingVo.getUserMappingId()) {
			modifiedQuery.append(" AND u.userMappingId != " + userMappingVo.getUserMappingId());
		}

		int count = (int) (long) entityManager.createQuery(modifiedQuery.toString()).getSingleResult();
		return count;

	}

	/**
	 * Method used to Create user mapping.
	 * 
	 * @param userMappingEntity
	 *            UserMappingEntity
	 * 
	 */
	public void create(UserMappingEntity userMappingEntity) {

		entityManager.persist(userMappingEntity);

	}

	/**
	 * Method used to update user mapping.
	 * 
	 * @param userMappingEntity
	 *            UserMappingEntity
	 * 
	 */

	public void update(UserMappingEntity userMappingEntity) {

		entityManager.persist(userMappingEntity);

	}

	/**
	 * Method used to find id in user mapping.
	 * 
	 * @param userMappingEntity
	 *            UserMappingEntity
	 * 
	 */
	public UserMappingEntity findId(int id) {

		UserMappingEntity userMappingEntity = new UserMappingEntity();
		String query = "FROM  UserMappingEntity where deleteFlag = " + CommonConstant.ZERO + " AND userMappingId = "
				+ id;
		userMappingEntity = (UserMappingEntity) entityManager.createQuery(query).getSingleResult();
		return userMappingEntity;

	}

	/**
	 * Method used to delete user mapping.
	 * 
	 * @param userMappingEntity
	 *            UserMappingEntity
	 * 
	 */
	public void delete(UserMappingEntity userMappingEntity) {

		entityManager.persist(userMappingEntity);

	}

	/**
	 * Method used to search user mapping.
	 * 
	 * @param userMappingEntity
	 *            UserMappingEntity
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllSearch(UserMappingVo userMappingVo) {

		String query = " SELECT u.USER_MAPPING_ID,u.USER_LOCATION_ID,loc.USER_LOCATION_NAME as locname,u.USER_SUBLOCATION_ID,"
				+ " sub.SUBLOCATION_NAME as subname,"
				+ " u.USER_DEPARTMENT_ID,dep.USER_DEPARTMENT_NAME as deptname ,u.USER_ROLE_ID,ur.USER_ROLE_NAME,u.LEVEL,com.ITEM_VALUE,"
				+ " u.REPORTING_TO_LOCATION,u.REPORTING_TO_SUBLOCATION,u.REPORTING_DEPARTMENT,u.REPORTING_TO,u.USER_ID,"
				+ " CONCAT(us.FIRST_NAME,' ',us.LAST_NAME) as userName,rloc.USER_LOCATION_NAME as rlocname,"
				+ " rsub.SUBLOCATION_NAME as rsubname,udep.USER_DEPARTMENT_NAME as rdeptname,"
				+ " CONCAT(urt.FIRST_NAME,' ',urt.LAST_NAME) as reportingUserName FROM user_mapping u "
				+ " LEFT JOIN common_storage com ON com.COMMON_ID =  u.LEVEL "
				+ " LEFT JOIN user_sublocation sub ON sub.SUBLOCATION_ID = u.USER_SUBLOCATION_ID "
				+ " LEFT JOIN user_location loc ON loc.USER_LOCATION_ID = u.USER_LOCATION_ID "
				+ " LEFT JOIN user_department dep ON dep.USER_DEPARTMENT_ID = u.USER_DEPARTMENT_ID "
				+ " LEFT JOIN user_role ur ON ur.ROLE_ID = u.USER_ROLE_ID "
				+ " LEFT JOIN user_location rloc ON rloc.USER_LOCATION_ID = u.REPORTING_TO_LOCATION "
				+ " LEFT JOIN user_sublocation rsub ON rsub.SUBLOCATION_ID  = u.REPORTING_TO_SUBLOCATION "
				+ " LEFT JOIN user_department udep ON udep.USER_DEPARTMENT_ID = u.REPORTING_DEPARTMENT "
				+ " LEFT JOIN `user` us ON us.USER_ID = u.USER_ID "
				+ " LEFT JOIN `user` urt ON urt.USER_ID = u.REPORTING_TO "
				+ " WHERE u.DELETE_FLAG = '0' AND u.SYS_APP_ID = 2 ";
		
		StringBuffer modifiedQuery = new StringBuffer(query);

		if (userMappingVo.getUserLocationName() != null && !userMappingVo.getUserLocationName().isEmpty()) {
			modifiedQuery.append(" and LOWER(loc.USER_LOCATION_NAME) LIKE LOWER('%"
					+ userMappingVo.getUserLocationName() + "%')");
		}
		
		

		if (userMappingVo.getSubLocationName() != null && !userMappingVo.getSubLocationName().isEmpty()) {
			modifiedQuery.append(" and LOWER(sub.SUBLOCATION_NAME) LIKE LOWER('%"
					+ userMappingVo.getSubLocationName() + "%')");
		}
		

		if (userMappingVo.getReportingLocationName() != null && !userMappingVo.getReportingLocationName().isEmpty()) {
			modifiedQuery.append(" and LOWER(rloc.USER_LOCATION_NAME) LIKE LOWER('%"
					+ userMappingVo.getReportingLocationName()+ "%')");
		}
		
		if (userMappingVo.getReportingSubLocationName() != null && !userMappingVo.getReportingSubLocationName().isEmpty()) {
			modifiedQuery.append(" and LOWER(rsub.SUBLOCATION_NAME as rsubname) LIKE LOWER('%"
					+ userMappingVo.getReportingSubLocationName() + "%')");
		}
		
		
		
		
		if (userMappingVo.getLevelName() != null) {
			modifiedQuery.append(" and LOWER(com.ITEM_VALUE) LIKE LOWER('%" + userMappingVo.getLevelName() + "%')");
		}

		if (userMappingVo.getUserDepartmentName() != null && !userMappingVo.getUserDepartmentName().isEmpty()) {

			modifiedQuery.append(" and LOWER(dep.USER_DEPARTMENT_NAME) LIKE LOWER('%"
					+ userMappingVo.getUserDepartmentName() + "%')");
		}
		if (userMappingVo.getUserRoleName() != null && !userMappingVo.getUserRoleName().isEmpty()) {

			modifiedQuery.append(" and LOWER(ur.USER_ROLE_NAME) LIKE LOWER('%"
					+ userMappingVo.getUserRoleName() + "%')");
		}
		if (userMappingVo.getUserName() != null && !userMappingVo.getUserName().isEmpty()) {

			modifiedQuery
					.append(" and LOWER(CONCAT(us.FIRST_NAME,' ',us.LAST_NAME)) LIKE LOWER('%" + userMappingVo.getUserName() + "%')");
		}
		if (userMappingVo.getReportingDepartmentName() != null
				&& !userMappingVo.getReportingDepartmentName().isEmpty()) {

			modifiedQuery.append(" and LOWER(udep.USER_DEPARTMENT_NAME) LIKE LOWER('%"
					+ userMappingVo.getReportingDepartmentName() + "%')");
		}
		if (userMappingVo.getReportingToUserName() != null && !userMappingVo.getReportingToUserName().isEmpty()) {

			modifiedQuery.append(" and LOWER(CONCAT(urt.FIRST_NAME,' ',urt.LAST_NAME)) LIKE LOWER('%"
					+ userMappingVo.getReportingToUserName() + "%')");
		}

		modifiedQuery.append("ORDER BY u.USER_MAPPING_ID DESC");

		List<Object[]> userMappingEntityList = (List<Object[]>) entityManager
				.createNativeQuery(modifiedQuery.toString()).getResultList();

		return userMappingEntityList;
	}

	public int findDuplicate(UserMappingVo userMappingVo) {
		int count = 0;
		
		String query = "SELECT COUNT(userMappingId) FROM UserMappingEntity "
				+ " where  userEntity.id = "+userMappingVo.getUserId()+" "
				+ "  and deleteFlag ='0' ";
			
		StringBuffer modifiedQuery = new StringBuffer(query);
		if (0 != userMappingVo.getUserMappingId()) {
			modifiedQuery.append(" AND userMappingId != " + userMappingVo.getUserMappingId());
		}

		count = (int) (long) entityManager.createQuery(modifiedQuery.toString()).getSingleResult();

		return count;
		
		
		
		
	}
	
	
	@SuppressWarnings("unchecked")
	public ScreenAuthorizationVO getScreenAuthorization(ScreenAuthorizationVO screenAuthorizationMaster) {

		String query = "SELECT s.SCREEN_AUTHENTICATION_ID FROM " + commonDatabaseSchema
				+ ".screen_authentication s where s.DELETE_FLAG = '0' and s.USER_ROLE_ID = "
				+ screenAuthorizationMaster.getAuthDetailsVo().getRoleId()
				+ " and s.SCREEN_ID = " + screenAuthorizationMaster.getScreenMaster().getScreenId()
				+ " and s.SUB_SCREEN_ID = "
				+ screenAuthorizationMaster.getSubScreenMaster().getSubScreenId() ;

		Integer screenAuthenticationId = (Integer) entityManager.createNativeQuery(query).getSingleResult();
		ScreenAuthorizationVO screenAuthorization = new ScreenAuthorizationVO();

		// get list of fields of the current screen assigned to user by admin
		String fieldQuery = "SELECT s.FIELD_ID, s.FIELD_NAME FROM " + commonDatabaseSchema
				+ ".field_validiation f LEFT JOIN " + commonDatabaseSchema
				+ ".screen_field s "
				+ " ON f.FIELD_ID = s.FIELD_ID"
				+ " where f.DELETE_FLAG = '0' and f.SCREEN_AUTHENTICATION_ID = "
				+ screenAuthenticationId + "  ORDER BY s.SEQUENCE ASC ";

		List<Object[]> fieldList = (List<Object[]>) entityManager.createNativeQuery(fieldQuery).getResultList();

		List<String> screenFieldDisplayVoList = new ArrayList<String>();
		for (Object[] object : fieldList) {

			screenFieldDisplayVoList.add((String) (((Object[]) object)[1]));

		}
		screenAuthorization.setScreenFieldDisplayVoList(screenFieldDisplayVoList);

		// get list of functions of the current screen assigned to user by
		// admin
		String functionQuery = "SELECT sf.SCREEN_FUNCTION_ID, sf.FUNCTION_NAME FROM " + commonDatabaseSchema
				+ ".function_authentication fn "
				+ " LEFT JOIN " + commonDatabaseSchema
				+ ".screen_function sf ON fn.SCREEN_FUNCTION_ID = sf.SCREEN_FUNCTION_ID "
				+ " where fn.DELETE_FLAG = '0'" + " and fn.SCREEN_AUTHENTICATION_ID = "
				+ screenAuthenticationId ;

		List<Object[]> functionList = (List<Object[]>) entityManager.createNativeQuery(functionQuery).getResultList();

		List<String> screenFunctionDisplayList = new ArrayList<String>();
		for (Object[] object : functionList) {

			screenFunctionDisplayList.add((String) (((Object[]) object)[1]));
		}
		screenAuthorization.setScreenFunctionDisplayList(screenFunctionDisplayList);

		return screenAuthorization;
	}

	@SuppressWarnings("unchecked")
	public ScreenAuthenticationVO getScreenAuhentication(ScreenAuthorizationVO screenAuthorizationMaster) {

		ScreenAuthenticationVO screenAuthentication = new ScreenAuthenticationVO();

		String query = "SELECT a.SCREEN_ID,SCREEN_NAME,SCREEN_TYPE_FLAG,SCREEN_URL,SCREEN_ICON FROM " + commonDatabaseSchema
				+ ".screen_authentication a "
				+ " JOIN " + commonDatabaseSchema
				+ ".screen s ON a.SCREEN_ID=s.SCREEN_ID WHERE a.DELETE_FLAG=0 AND " + " a.USER_ROLE_ID = "
				+ screenAuthorizationMaster.getAuthDetailsVo().getRoleId()
				+ " ORDER BY  a.SCREEN_ID DESC";

		List<Object[]> screenAuthenticationEntities = (List<Object[]>) entityManager.createNativeQuery(query)
				.getResultList();
		List<ScreenVO> screenVoList = new ArrayList<ScreenVO>();
		int oldValue = 0;
		int newValue = 0;

		for (Object[] object : screenAuthenticationEntities) {
			ScreenVO screenVo = new ScreenVO();
			newValue = (Integer) (((Object[]) object)[0]);
			if (oldValue != newValue) {

				if (null != (((Object[]) object)[1])) {
					screenVo.setScreenName((String) (((Object[]) object)[1]));
				}
				if (null != (((Object[]) object)[2])) {
					screenVo.setScreenTypeFlag((Character) (((Object[]) object)[2]));
				}
				if (null != (((Object[]) object)[3])) {
					screenVo.setScreenUrl((String) (((Object[]) object)[3]));
				}
				if (null != (((Object[]) object)[4])) {
					screenVo.setScreenIcon((String) (((Object[]) object)[4]));
				}
				screenVoList.add(screenVo);
			}

			oldValue = newValue;

		}
		screenAuthentication.setScreenVoList(screenVoList);

		return screenAuthentication;
	}
	
	
}
