package com.kevamdg.sr.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.kevamdg.sr.constants.CommonConstant;
import com.kevamdg.sr.entity.FieldAuthentication;
import com.kevamdg.sr.entity.FunctionAuthentication;
import com.kevamdg.sr.entity.ScreenAuthentication;
import com.kevamdg.sr.entity.UserRoleEntity;
import com.kevamdg.sr.vo.AuthDetailsVO;
import com.kevamdg.sr.vo.ScreenAuthenticationMaster;
import com.kevamdg.sr.vo.ScreenAuthenticationVO;
import com.kevamdg.sr.vo.ScreenAuthorizationVO;
import com.kevamdg.sr.vo.ScreenVO;
import com.kevamdg.sr.vo.UserRoleMasterListDisplayVo;


/**
 * @author manoj
 *
 */
@Component
public class ScreenAssignmentDao {

	@Autowired
	EntityManager entityManager;

	
	@Value("${commonDatabaseSchema}")
	private String commonDatabaseSchema;

	@SuppressWarnings("unchecked")
	public List<UserRoleMasterListDisplayVo> searchScreenAuthentication(
			ScreenAuthenticationMaster screenAuthenticationMstr) {

		List<UserRoleMasterListDisplayVo> userRoleMasterListDisplayVoList = new ArrayList<UserRoleMasterListDisplayVo>();

		String query = "FROM UserRoleEntity c where c.deleteFlag= '" + CommonConstant.FLAG_ZERO + "'";

		if (screenAuthenticationMstr.getRoleId() != null) {
			query = query + " and c.id = '" + screenAuthenticationMstr.getRoleId() + "'";
		}
		if (screenAuthenticationMstr.getRoleName() != null) {
			query = query + " and LOWER(c.userRoleName) LIKE LOWER('%" + screenAuthenticationMstr.getRoleName() + "%')";
		}

		List<UserRoleEntity> screenAuthenticationList = (List<UserRoleEntity>) entityManager.createQuery(query)
				.getResultList();

		UserRoleMasterListDisplayVo screenAuthenticationMasterMap = null;
		@SuppressWarnings("unused")
		ScreenAuthenticationMaster screenAuthenticationMaster = null;

		for (UserRoleEntity screenAuthenticationEntity : screenAuthenticationList) {

			screenAuthenticationMasterMap = new UserRoleMasterListDisplayVo();
			screenAuthenticationMaster = new ScreenAuthenticationMaster();
			BeanUtils.copyProperties(screenAuthenticationEntity, screenAuthenticationMasterMap);

			userRoleMasterListDisplayVoList.add(screenAuthenticationMasterMap);
		}
		return userRoleMasterListDisplayVoList;
	}

	/*
	 * @SuppressWarnings("unchecked") public ScreenAuthenticationMaster load() {
	 * 
	 * ScreenAuthenticationMaster screenAuthenticationMaster = new
	 * ScreenAuthenticationMaster(); ComboVo comboVo = null;
	 * 
	 * String query =
	 * "FROM UserLocationEntity c WHERE c.activeFlag = '1' and  c.deleteFlag  = '"
	 * + CommonConstant.FLAG_ZERO + "'";
	 * 
	 * List<UserLocationEntity> userLocationEntityList =
	 * (List<UserLocationEntity>) getEntityCommon()
	 * .createQuery(query).getResultList(); List<ComboVo> userLocationMasterMap
	 * = new ArrayList<ComboVo>();
	 * 
	 * for (UserLocationEntity userLocationEntity : userLocationEntityList) {
	 * comboVo = new ComboVo(); comboVo.setId(userLocationEntity.getId());
	 * comboVo.setName(userLocationEntity.getUserLocationName());
	 * userLocationMasterMap.add(comboVo); }
	 * screenAuthenticationMaster.setCdcCombo(userLocationMasterMap);
	 * 
	 * String userRoleQuery = "FROM UserRoleEntity c WHERE c.deleteFlag  = '" +
	 * CommonConstant.FLAG_ZERO + "'"; List<UserRoleEntity> userRoleEntityList =
	 * (List<UserRoleEntity>) getEntityCommon().createQuery(userRoleQuery)
	 * .getResultList();
	 * 
	 * List<ComboVo> roleMap = new ArrayList<ComboVo>();
	 * 
	 * for (UserRoleEntity userRoleEntity : userRoleEntityList) {
	 * 
	 * comboVo = new ComboVo(); comboVo.setId(userRoleEntity.getId());
	 * comboVo.setName(userRoleEntity.getUserRoleName()); roleMap.add(comboVo);
	 * } screenAuthenticationMaster.setRoleCombo(roleMap);
	 * 
	 * String userQuery = "FROM UserEntity c WHERE c.deleteFlag  = '" +
	 * CommonConstant.FLAG_ZERO + "'"; List<UserEntity> userEntityList =
	 * (List<UserEntity>)
	 * getEntityCommon().createQuery(userQuery).getResultList();
	 * 
	 * List<ComboVo> userMap = new ArrayList<ComboVo>();
	 * 
	 * for (UserEntity userEntity : userEntityList) {
	 * 
	 * comboVo = new ComboVo(); comboVo.setId(userEntity.getId());
	 * comboVo.setName(userEntity.getUserLoginId()); userMap.add(comboVo); }
	 * screenAuthenticationMaster.setReportingToCombo(userMap);
	 * 
	 * String departmentQuery =
	 * "FROM UserDepartmentEntity c WHERE c.deleteFlag  = '" +
	 * CommonConstant.FLAG_ZERO + "'"; List<UserDepartmentEntity>
	 * userDepartmentEntityList = (List<UserDepartmentEntity>) getEntityCommon()
	 * .createQuery(departmentQuery).getResultList();
	 * 
	 * List<ComboVo> departmentMap = new ArrayList<ComboVo>();
	 * 
	 * for (UserDepartmentEntity userDepartmentEntity :
	 * userDepartmentEntityList) {
	 * 
	 * comboVo = new ComboVo(); comboVo.setId(userDepartmentEntity.getId());
	 * comboVo.setName(userDepartmentEntity.getUserDepartmentName());
	 * departmentMap.add(comboVo); }
	 * screenAuthenticationMaster.setDepartmentCombo(departmentMap);
	 * 
	 * String screenQuery = "FROM ScreenEntity s WHERE s.activeFlag  = '" +
	 * CommonConstant.FLAG_ZERO + "'"; List<ScreenEntity> screenEntityList =
	 * (List<ScreenEntity>) getEntityCommon().createQuery(screenQuery)
	 * .getResultList();
	 * 
	 * List<ComboVo> screenMap = new ArrayList<ComboVo>();
	 * 
	 * for (ScreenEntity screenEntity : screenEntityList) {
	 * 
	 * comboVo = new ComboVo(); comboVo.setId(screenEntity.getScreenId());
	 * comboVo.setName(screenEntity.getScreenName()); screenMap.add(comboVo); }
	 * screenAuthenticationMaster.setScreenCombo(screenMap); return
	 * screenAuthenticationMaster;
	 * 
	 * }
	 */
	// Load User Role List

	@SuppressWarnings("unchecked")
	public List<Object> getRoleList() {

		String query = " SELECT r.ROLE_ID ,r.USER_ROLE_NAME "
				+ " FROM "+commonDatabaseSchema+".`user_role` r WHERE r.DELETE_FLAG = '0' ";

		List<Object> result = (List<Object>) entityManager.createNativeQuery(query).getResultList();
		return result;
	}

	// Load Users Based on Role

	@SuppressWarnings("unchecked")
	public List<Object> getRoleBasedUserList(Integer roleId) {

		String query = " SELECT u.USER_ID ,u.USER_LOGIN_ID, u.FIRST_NAME, u.LAST_NAME FROM "+commonDatabaseSchema+".`user` u WHERE u.DELETE_FLAG = "
				+ CommonConstant.FLAG_ZERO + " and u.ACTIVE =  " + CommonConstant.FLAG_ONE 
			 + " and u.USER_ROLE_ID = " + roleId;

		List<Object> result = (List<Object>) entityManager.createNativeQuery(query).getResultList();
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Object> getScreenList() {

		String query = " SELECT s.SCREEN_ID, s.SCREEN_NAME FROM "+commonDatabaseSchema+".`screen` s WHERE "
				 + " s.ACTIVE_FLAG = " + CommonConstant.FLAG_ZERO;
		List<Object> result = (List<Object>) entityManager.createNativeQuery(query).getResultList();
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Object> getSubScreenList(Integer screenId) {

		String query = " SELECT s.SUB_SCREEN_ID, s.SUB_SCREEN_NAME FROM "+commonDatabaseSchema+".`sub_screen` s WHERE "
				+ " s.SCREEN_ID = " + screenId + " AND s.ACTIVE_FLAG = " + CommonConstant.FLAG_ZERO;
			
		List<Object> result = (List<Object>) entityManager.createNativeQuery(query).getResultList();
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Object> getScreenFieldList(Integer subScreenId) {

		String query = " SELECT s.FIELD_ID, s.FIELD_NAME FROM "+commonDatabaseSchema+".`screen_field` s WHERE "
				+ " s.SUB_SCREEN_ID = " + subScreenId + " and s.ACTIVE_FLAG = " + CommonConstant.FLAG_ZERO
				 + " order by s.SEQUENCE asc";
		List<Object> result = (List<Object>) entityManager.createNativeQuery(query).getResultList();
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Object> getFunctionList(Integer subScreenId) {

		String query = " SELECT s.SCREEN_FUNCTION_ID, s.FUNCTION_NAME FROM "+commonDatabaseSchema+".`screen_function` s WHERE "
				+ " s.SUB_SCREEN_ID = " + subScreenId + " and s.ACTIVE_FLAG =" + CommonConstant.FLAG_ZERO;
		List<Object> result = (List<Object>) entityManager.createNativeQuery(query).getResultList();
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Integer> authenticationRole(Integer roleId) {

		String query = " SELECT s.SCREEN_ID FROM "+commonDatabaseSchema+".`screen_authentication` s WHERE " + " s.USER_ROLE_ID = "
				+ roleId + " and  s.DELETE_FLAG = " + CommonConstant.FLAG_ZERO ;
		List<Integer> result = (List<Integer>) entityManager.createNativeQuery(query).getResultList();
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Integer> screenSubScreenBasedRole(Integer roleId, Integer screenId, Integer subScreenId) {

		String query = " SELECT s.SCREEN_AUTHENTICATION_ID FROM "+commonDatabaseSchema+".`screen_authentication` s WHERE "
				+ " s.USER_ROLE_ID = " + roleId + " and  s.DELETE_FLAG = " + CommonConstant.FLAG_ZERO
				+ " and s.SCREEN_ID = " + screenId + " and s.SUB_SCREEN_ID = " + subScreenId + " ";
		List<Integer> result = (List<Integer>) entityManager.createNativeQuery(query).getResultList();
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Integer> getAuthenticationId(Integer roleId) {

		String query = " SELECT s.SCREEN_AUTHENTICATION_ID FROM "+commonDatabaseSchema+".`screen_authentication` s WHERE "
				+ " s.USER_ROLE_ID = " + roleId + " and  s.DELETE_FLAG = " + CommonConstant.FLAG_ZERO ;
		List<Integer> result = (List<Integer>) entityManager.createNativeQuery(query).getResultList();
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Integer> getRoleBasedUsers(Integer roleId) {

		String query = " SELECT s.USER_ID FROM "+commonDatabaseSchema+".`user` s WHERE " + " s.USER_ROLE_ID = " + roleId
				+ " and  s.DELETE_FLAG = " + CommonConstant.FLAG_ZERO + " ";
		List<Integer> result = (List<Integer>) entityManager.createNativeQuery(query).getResultList();
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Integer> screenFunctionBasedRole(Integer screenAuthenticationId) {

		String query = " SELECT  s.SCREEN_FUNCTION_ID   FROM "+commonDatabaseSchema+".`function_authentication` s WHERE "
				+ " s.SCREEN_AUTHENTICATION_ID = " + screenAuthenticationId + " and  s.DELETE_FLAG = "
				+ CommonConstant.FLAG_ZERO + " ";

		List<Integer> result = (List<Integer>) entityManager.createNativeQuery(query).getResultList();

		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Integer> getAuthenticationBasedFields(Integer screenAuthenticationId) {

		String query = " SELECT  s.FIELD_ID   FROM "+commonDatabaseSchema+".`field_validiation` s WHERE "
				+ " s.SCREEN_AUTHENTICATION_ID = " + screenAuthenticationId + " and  s.DELETE_FLAG = "
				+ CommonConstant.FLAG_ZERO + " ";

		List<Integer> result = (List<Integer>) entityManager.createNativeQuery(query).getResultList();

		return result;
	}

	/*
	 * @SuppressWarnings("unchecked") public List<String>
	 * getAuthenticationBasedFieldsWithBaseFilter(Integer
	 * screenAuthenticationId,Integer fieldId) {
	 * 
	 * String query =
	 * " SELECT  s.BASE_FILTER  FROM "+commonDatabaseSchema+".`field_validiation` s WHERE "
	 * + " s.SCREEN_AUTHENTICATION_ID = " + screenAuthenticationId +
	 * " and  s.DELETE_FLAG = " + CommonConstant.FLAG_ZERO +
	 * " and s.FIELD_ID  = " + fieldId + " and s.SYS_APP_ID = " +
	 * userConstants.getSysAppId();
	 * 
	 * List<String> result =
	 * (List<String>)getEntityCommon().createNativeQuery(query).getResultList();
	 * 
	 * return result; }
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> authenticationRoleForScreen(Integer roleId, Integer screenId) {

		String query = " SELECT s.SUB_SCREEN_ID FROM "+commonDatabaseSchema+".`screen_authentication` s WHERE "
				+ " s.USER_ROLE_ID = " + roleId + " and  s.DELETE_FLAG = " + CommonConstant.FLAG_ZERO
				 + " and s.SCREEN_ID = " + screenId;
		List<Integer> result = (List<Integer>) entityManager.createNativeQuery(query).getResultList();
		return result;
	}

	public UserRoleEntity getRoleName(Integer roleId) {

		UserRoleEntity userRoleEntity = null;

		if (null != roleId) {
			userRoleEntity = entityManager.find(UserRoleEntity.class, roleId);
		}
		return userRoleEntity;
	}

	public ScreenAuthenticationMaster deleteScreenFunction(Integer screenAuthenticationId,
			ScreenAuthenticationMaster screenAuthenticationMaster) {

		entityManager
				.createNativeQuery(
						" DELETE FROM "+commonDatabaseSchema+".`function_authentication` WHERE SCREEN_AUTHENTICATION_ID  =:id")
				.setParameter("id", screenAuthenticationId).executeUpdate();

		return screenAuthenticationMaster;
	}

	public ScreenAuthenticationMaster deleteScreenFields(Integer screenAuthenticationId,
			ScreenAuthenticationMaster screenAuthenticationMaster) {

		entityManager
				.createNativeQuery(" DELETE FROM "+commonDatabaseSchema+".`field_validiation` WHERE SCREEN_AUTHENTICATION_ID  =:id")
				.setParameter("id", screenAuthenticationId).executeUpdate();

		return screenAuthenticationMaster;
	}

	public ScreenAuthenticationMaster deleteScreenAuthentication(Integer screenAuthenticationId,
			ScreenAuthenticationMaster screenAuthenticationMaster) {

		entityManager
				.createNativeQuery(
						" DELETE FROM "+commonDatabaseSchema+".`screen_authentication` WHERE SCREEN_AUTHENTICATION_ID  =:id")
				.setParameter("id", screenAuthenticationId).executeUpdate();
		return screenAuthenticationMaster;
	}

	public ScreenAuthentication saveScreenAuthentication(ScreenAuthentication screenAuthenticationEntity) {

		entityManager.persist(screenAuthenticationEntity);

		return screenAuthenticationEntity;
	}

	public void saveScreenField(FieldAuthentication fieldAuthenticationEntity) {

		entityManager.persist(fieldAuthenticationEntity);

	}

	public void saveScreenFunction(FunctionAuthentication functionAuthenticationEntity) {

		entityManager.persist(functionAuthenticationEntity);

	}

	@SuppressWarnings("unchecked")
	public List<Integer> checkScreenExists(Integer roleId, Integer screenId, Integer subScreenId) {

		String query = " SELECT s.SCREEN_AUTHENTICATION_ID FROM "+commonDatabaseSchema+".`screen_authentication` s WHERE "
				+ " s.USER_ROLE_ID = " + roleId + " and  s.DELETE_FLAG = " + CommonConstant.FLAG_ZERO
				+ " and  s.SCREEN_ID = " + screenId + " and s.SUB_SCREEN_ID = " + subScreenId + " ";
		List<Integer> result = (List<Integer>) entityManager.createNativeQuery(query).getResultList();
		return result;

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<Integer, String> loadRoleCombo() {

		String query = " SELECT r.ROLE_ID ,r.USER_ROLE_NAME FROM "+commonDatabaseSchema+".`user_role` r WHERE r.DELETE_FLAG = '0' ";

		List<Object> result = (List<Object>) entityManager.createNativeQuery(query).getResultList();
		Map<Integer, String> userRoleMasterMap = new HashMap<>();
		Iterator itr = result.iterator();
		while (itr.hasNext()) {

			Object[] obj = (Object[]) itr.next();
			Integer value = Integer.parseInt(String.valueOf(obj[0]));
			String label = String.valueOf(obj[1]);
			userRoleMasterMap.put(value, label);
		}

		return userRoleMasterMap;
	}
	
	@SuppressWarnings("unchecked")
	public ScreenAuthorizationVO getScreenAuthorization(ScreenAuthorizationVO screenAuthorizationMaster,AuthDetailsVO authDetailsVO) {

		String query = "SELECT s.SCREEN_AUTHENTICATION_ID FROM " + commonDatabaseSchema
				+ ".screen_authentication s where s.DELETE_FLAG = '0' and s.USER_ROLE_ID = "
				+ authDetailsVO.getRoleId()
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
	public ScreenAuthenticationVO getScreenAuhentication(ScreenAuthorizationVO screenAuthorizationMaster,
			AuthDetailsVO authDetailsVO) {

		ScreenAuthenticationVO screenAuthentication = new ScreenAuthenticationVO();

		String query = "SELECT a.SCREEN_ID,SCREEN_NAME,SCREEN_TYPE_FLAG,SCREEN_URL,SCREEN_ICON FROM " + commonDatabaseSchema
				+ ".screen_authentication a "
				+ " JOIN " + commonDatabaseSchema
				+ ".screen s ON a.SCREEN_ID=s.SCREEN_ID WHERE a.DELETE_FLAG=0 AND " + " a.USER_ROLE_ID = "
				+ authDetailsVO.getRoleId()
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
