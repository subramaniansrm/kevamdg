package com.kevamdg.sr.dao;

import java.util.Base64;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.kevamdg.sr.constants.CommonConstant;
import com.kevamdg.sr.entity.CodeGenerationEntity;
import com.kevamdg.sr.entity.UserEntity;
import com.kevamdg.sr.vo.AuthDetailsVO;

@Repository
public class CommonDao {

	@Autowired
	EntityManager entityManager;
	
	@Value("${commonDatabaseSchema}")
	private String commonDatabaseSchema;
	
	@Value("${mmDatabaseSchema}")
	private String mmDatabaseSchema;

	/**
	 * This method is used to autogenerate the widgetCode by executing the
	 * query.
	 * 
	 * 
	 * @return String widgetCode
	 */
	public String findAutoGenericCode(String comboName) {

		String requestCode;

		String query = "SELECT p.* FROM "
				+ commonDatabaseSchema+".`code_generation` p LEFT JOIN "
				+ commonDatabaseSchema+".`combo_details` c ON c.COMBO_ID = p.CODE_NAME" + " WHERE p.DELETE_FLAG = '"
				+ CommonConstant.FLAG_ZERO + "' and c.COMBO_VALUE = '" + comboName + "'";
		Query qry = entityManager.createNativeQuery(query, CodeGenerationEntity.class);

		CodeGenerationEntity codeGenerationEntity = (CodeGenerationEntity) qry.getSingleResult();

		int startingNumber = codeGenerationEntity.getStartingNumber() + CommonConstant.CONSTANT_ONE;

		requestCode = codeGenerationEntity.getPrefix() + String.format("%0" + codeGenerationEntity.getCounter() + "d",
				codeGenerationEntity.getStartingNumber());

		String query1 = " update CodeGenerationEntity set startingNumber =" + startingNumber + " where deleteFlag =   "
				+ CommonConstant.CONSTANT_ZERO + " and codeGenerationId = "
				+ codeGenerationEntity.getCodeGenerationId();
		entityManager.createQuery(query1).executeUpdate();

		return requestCode;

	}

	public Integer getScreenAuthenticationId(Integer roleId, Integer screenId, Integer subScreenId) {
		Integer screenAuthId = null;
		String query = "SELECT s.screenAuthenticationId FROM ScreenAuthentication s where s.deleteFlag = '0' "
				+ " and s.userRole = " + roleId + " and s.screenId = " + screenId + " and s.subScreenId = "
				+ subScreenId;
		// + " and s.entityLicenseEntity.id="+AuthUtil.getEntityId()+"" ;
		Query qry = null;
		try {
			qry = entityManager.createQuery(query);

		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();

		}
		screenAuthId = (Integer) qry.getSingleResult();
		return screenAuthId;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getScreenField(Integer screenAuthId) {
		List<Object[]> fieldList = null;
		String fieldQuery = "SELECT s.fieldId, s.fieldName FROM FieldAuthentication f ,ScreenField s "
				+ " where f.screenFieldId = s.fieldId" + " and f.deleteFlag = '0' and f.screenAuthenticationId = "
				+ screenAuthId + "  ORDER BY s.sequence ASC ";
		Query fieldQry = entityManager.createQuery(fieldQuery);
		fieldList = (List<Object[]>) fieldQry.getResultList();
		return fieldList;

	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getScreenFunction(Integer screenAuthId) {
		List<Object[]> functionList = null;
		String functionQuery = "SELECT sf.screenFunctionId, sf.functionName FROM FunctionAuthentication fn "
				+ " ,ScreenFunction sf where fn.screenFunctionId = sf.screenFunctionId " + "  and fn.deleteFlag = '0'"
				+ " and fn.screenAuthenticationId = " + screenAuthId;
		Query fieldQry = entityManager.createQuery(functionQuery);
		functionList = (List<Object[]>) fieldQry.getResultList();
		return functionList;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getScreenAuhentication(AuthDetailsVO authDetailsVo) {
		List<Object[]> screenAuthenticationEntities = null;
		String query = "SELECT a.SCREEN_ID,SCREEN_NAME,SCREEN_TYPE_FLAG,SCREEN_URL,SCREEN_ICON FROM "
				+ commonDatabaseSchema+".screen_authentication a "
				+ " JOIN screen s ON a.SCREEN_ID=s.SCREEN_ID WHERE a.DELETE_FLAG=0 AND " + " a.USER_ROLE_ID = "
				+ authDetailsVo.getRoleId() /*
										 * + " AND a.rin_ma_entity_id = " +
										 * AuthUtil.getEntityId() + " "
										 */ + " ORDER BY  a.SCREEN_ID DESC";
		Query screenQry = entityManager.createNativeQuery(query);
		screenAuthenticationEntities = (List<Object[]>) screenQry.getResultList();
		return screenAuthenticationEntities;

	}
	
	
	public UserEntity getAcessTokenUser(String accessToken) {

		String access = Base64.getEncoder().encodeToString(accessToken.getBytes());
		
		String query = "SELECT e.USER_ID,e.FIRST_NAME,e.LAST_NAME ,e.USER_ROLE_ID,e.access_token "
				+ " ,e.USER_DEPARTMENT_ID,e.USER_LOCATION_ID , e.USER_SUBLOCATION_ID " + " FROM "
				+ commonDatabaseSchema + ".`user` e where e.ACTIVE = " + CommonConstant.FLAG_ONE
				+ " and  e.access_token = '" + access + "' and e.delete_flag = " + CommonConstant.FLAG_ZERO;

		Object[] obj = (Object[]) entityManager.createNativeQuery(query).getSingleResult();
		UserEntity userEntity = new UserEntity();
			if (null != (Integer) ((obj)[0])) {
				userEntity.setId((Integer) ((obj)[0]));
			}
			if (null != (String) ((obj)[1])) {
				userEntity.setFirstName((String) ((obj)[1]));
			}
			if (null != (String) ((obj)[2])) {
				userEntity.setLastName((String) ((obj)[2]));
			}
			if (null != (Integer) ((obj)[3])) {
				userEntity.setRoleId((Integer) ((obj)[3]));
			}
			if (null != (Integer) ((obj)[5])) {
				userEntity.setDepartmentId((Integer) ((obj)[5]));
			}
			if (null != (Integer) ((obj)[6])) {
				userEntity.setLocationId((Integer) ((obj)[6]));
			}
			if (null != (Integer) ((obj)[7])) {
				userEntity.setSublocationId((Integer) ((obj)[7]));
			}
		
		return userEntity;
	}
	
	public AuthDetailsVO tokenValidate(String accessToken) {

		UserEntity userEntity = getAcessTokenUser(accessToken);
		AuthDetailsVO authUserInfo = null;

		if (null != userEntity) {
			authUserInfo = new AuthDetailsVO();

			if (null != userEntity.getId()) {
				authUserInfo.setUserId(userEntity.getId());
			}
			if (null != userEntity.getFirstName()) {
				authUserInfo.setFirstName(userEntity.getFirstName());
			}
			if (null != userEntity.getLastName()) {
				authUserInfo.setLastName(userEntity.getLastName());
			}
			if (null != userEntity.getRoleId()) {
				authUserInfo.setRoleId(userEntity.getRoleId());
			}
			if (null != userEntity.getFirstName()) {
				authUserInfo.setUserName(userEntity.getFirstName());
			}
			if (null != userEntity.getDepartmentId()) {
				authUserInfo.setDepartmentId(userEntity.getDepartmentId());
			}
			if (null != userEntity.getLocationId()) {
				authUserInfo.setLocationId(userEntity.getLocationId());
			}
			if (null != userEntity.getSublocationId()) {
				authUserInfo.setSubLocationId(userEntity.getSublocationId());

			}
		}
		return authUserInfo;

	}

	
	public String getHeaderAccessToken(HttpServletRequest request) {

		String accessToken = request.getHeader("Authorization");
		System.out.println("With Bearer" + accessToken);
		accessToken = accessToken.replaceFirst("Bearer ", "");
		System.out.println("Without Bearer" + accessToken);
		return accessToken;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getScreenField(Integer id, AuthDetailsVO authDetailsVO) {

		String query = "SELECT sf.SCREEN_FIELD_FK,s.FIELD_NAME FROM " + commonDatabaseSchema + ".cm_screen_field s"
				+ " LEFT JOIN " + commonDatabaseSchema
				+ ".cm_user_screen_field_mapping sf ON sf.SUB_SCREEN_ID = s.SUB_SCREEN_ID"
				+ " AND sf.SCREEN_FIELD_FK = s.FIELD_ID"
				+ " WHERE s.ACTIVE_FLAG ='1' AND sf.IS_ACTIVE=1 AND sf.USER_FK =" + authDetailsVO.getUserId()
				+ " AND sf.SUB_SCREEN_ID=" + id + " group by sf.SCREEN_FIELD_FK Order by s.SEQUENCE";

		List<Object[]> list = (List<Object[]>) entityManager.createNativeQuery(query).getResultList();
		return list;

	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getScreenFunction(Integer id, AuthDetailsVO authDetailsVO) {

		String query = "SELECT sf.SCREEN_FUNCTION_FK,s.FUNCTION_NAME FROM " + commonDatabaseSchema
				+ ".cm_screen_function s" + " LEFT JOIN " + commonDatabaseSchema
				+ ".cm_user_screen_function_mapping sf ON sf.SUB_SCREEN_ID = s.SUB_SCREEN_ID"
				+ " AND sf.SCREEN_FUNCTION_FK = s.SCREEN_FUNCTION_ID"
				+ " WHERE s.ACTIVE_FLAG ='1' AND sf.IS_ACTIVE=1 AND sf.USER_FK =" + authDetailsVO.getUserId()
				+ " AND sf.SUB_SCREEN_ID=" + id + " group by sf.SCREEN_FUNCTION_FK";

		List<Object[]> list = (List<Object[]>) entityManager.createNativeQuery(query).getResultList();
		return list;

	}
	
}
