package com.kevamdg.sr.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.kevamdg.sr.constants.CommonConstant;
import com.kevamdg.sr.vo.AuthDetailsVO;
import com.kevamdg.sr.vo.CommonDropDownVO;

@Repository
public class AuthenticationDAO {

	@Autowired
	EntityManager entityManager;

	@Value("${commonDatabaseSchema}")
	private String commonDatabaseSchema;

	@Value("${mmDatabaseSchema}")
	private String mmDatabaseSchema;

	@SuppressWarnings("unchecked")
	public List<Object[]> getAll() {

		String query = "SELECT e.USER_ID,e.FIRST_NAME,e.LAST_NAME,ul.USER_LOCATION_NAME,us.SUBLOCATION_NAME"
				+ " ,ud.USER_DEPARTMENT_NAME FROM " + commonDatabaseSchema + ".user e" + " Left Join "
				+ commonDatabaseSchema + ".user_location ul on ul.USER_LOCATION_ID = e.USER_LOCATION_ID" + " Left Join "
				+ commonDatabaseSchema + ".user_sublocation us on us.SUBLOCATION_ID = e.USER_SUBLOCATION_ID"
				+ " Left Join " + commonDatabaseSchema
				+ ".user_department ud on ud.USER_DEPARTMENT_ID = e.USER_DEPARTMENT_ID" + " Left Join "
				+ commonDatabaseSchema + ".user_mapping_module umm on umm.USER_FK = e.USER_ID"
				+ " where e.ACTIVE ='1' and umm.MODULE_FK = 2" + " and e.DELETE_FLAG=" + CommonConstant.CONSTANT_ZERO;

		List<Object[]> list = (List<Object[]>) entityManager.createNativeQuery(query).getResultList();

		return list;
	}

	public void screenDelete(int id) {

		String query2 = "DELETE ur from " + commonDatabaseSchema
				+ ".cm_user_screen_mapping ur where ur.USER_SCREEN_MAPPING_PK =" + id;

		entityManager.createNativeQuery(query2).executeUpdate();

	}

	public void subScreenDelete(int id) {

		String query3 = "DELETE ur from " + commonDatabaseSchema
				+ ".cm_user_sub_screen_mapping ur where ur.USER_SUB_SCREEN_MAPPING_PK =" + id;

		entityManager.createNativeQuery(query3).executeUpdate();

	}

	public void screenFieldDelete(int id, int sub) {

		String query5 = "DELETE ur from " + commonDatabaseSchema + ".cm_user_screen_field_mapping ur where ur.USER_FK ="
				+ id + " and ur.SUB_SCREEN_ID =" + sub;

		entityManager.createNativeQuery(query5).executeUpdate();

	}

	public void screenFunctionDelete(int id, int sub) {

		String query4 = "DELETE ur from " + commonDatabaseSchema + ".cm_user_screen_function_mapping ur where ur.USER_FK ="
				+ id + " and ur.SUB_SCREEN_ID =" + sub;

		entityManager.createNativeQuery(query4).executeUpdate();

	}

	public int screenId(int id) {

		int count = 0;

		String query = "SELECT USER_SCREEN_MAPPING_PK FROM " + commonDatabaseSchema + ".cm_user_screen_mapping"
				+ " WHERE USER_FK = 1 AND SCREEN_FK =" + id;

		try {
			count = (int) entityManager.createNativeQuery(query).getSingleResult();
		} catch (NoResultException e) {

		}

		return count;
	}

	public int subScreenId(int id) {

		int count = 0;
		String query = "SELECT USER_SUB_SCREEN_MAPPING_PK FROM " + commonDatabaseSchema + ".cm_user_sub_screen_mapping"
				+ " WHERE USER_FK = 1 AND SUB_SCREEN_FK =" + id;
		try {
			count = (int) entityManager.createNativeQuery(query).getSingleResult();
		} catch (NoResultException e) {

		}
		return count;
	}

	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> screen(AuthDetailsVO authDetailsVo) {
		try {
			String query = "SELECT  r.SCREEN_ID,SCREEN_NAME,"
					+ " IFNULL(IF(SCREEN_FK!=0,'success','fail'),'fail')Selecttion FROM " + commonDatabaseSchema
					+ ".cm_screen r" + " LEFT JOIN  (SELECT SCREEN_FK  FROM " + commonDatabaseSchema
					+ ".cm_user_screen_mapping  WHERE USER_FK=" + authDetailsVo.getUserId() + ") a" + " ON SCREEN_FK=r.SCREEN_ID"
					+ " GROUP BY r.SCREEN_ID order by r.SCREEN_ID";

			List<Object[]> list = (List<Object[]>) entityManager.createNativeQuery(query).getResultList();

			return list;
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();

		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> subScreen(CommonDropDownVO commonDropDownVO,AuthDetailsVO authDetailsVo) {
		try {
			String query = "SELECT  r.SUB_SCREEN_PK_ID,SUB_SCREEN_NAME,"
					+ " IFNULL(IF(SUB_SCREEN_FK!=0,'success','fail'),'fail')Selecttion FROM " + commonDatabaseSchema
					+ ".cm_sub_screen r" + " LEFT JOIN  (SELECT SUB_SCREEN_FK  FROM " + commonDatabaseSchema
					+ ".cm_user_sub_screen_mapping" + " WHERE USER_FK=" + authDetailsVo.getUserId() + " AND SCREEN_ID ="
					+ commonDropDownVO.getId() + ") a ON SUB_SCREEN_FK=r.SUB_SCREEN_PK_ID" + " WHERE SCREEN_ID ="
					+ commonDropDownVO.getId() + " GROUP BY r.SUB_SCREEN_PK_ID order by r.SUB_SCREEN_PK_ID";

			List<Object[]> list = (List<Object[]>) entityManager.createNativeQuery(query).getResultList();

			return list;
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();

		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> screenField(CommonDropDownVO commonDropDownVO,AuthDetailsVO authDetailsVo) {
		try {
			String query = "SELECT  r.FIELD_ID,FIELD_NAME,"
					+ " IFNULL(IF(SCREEN_FIELD_FK!=0,'success','fail'),'fail')Selecttion" + " FROM "
					+ commonDatabaseSchema + ".cm_screen_field r" + " LEFT JOIN  (SELECT SCREEN_FIELD_FK  FROM "
					+ commonDatabaseSchema + ".cm_user_screen_field_mapping" + " WHERE USER_FK=" + authDetailsVo.getUserId()
					+ " AND SUB_SCREEN_ID =" + commonDropDownVO.getId()
					+ ") a ON SCREEN_FIELD_FK=r.FIELD_ID WHERE SUB_SCREEN_ID =" + commonDropDownVO.getId()
					+ " GROUP BY r.FIELD_ID order by r.FIELD_ID";

			List<Object[]> list = (List<Object[]>) entityManager.createNativeQuery(query).getResultList();

			return list;
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();

		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> screenFunction(CommonDropDownVO commonDropDownVO,AuthDetailsVO authDetailsVo) {
		try {
			String query = "SELECT  r.SCREEN_FUNCTION_ID,FUNCTION_NAME,"
					+ " IFNULL(IF(SCREEN_FUNCTION_FK!=0,'success','fail'),'fail')Selecttion FROM "
					+ commonDatabaseSchema + ".cm_screen_function r" + " LEFT JOIN  (SELECT SCREEN_FUNCTION_FK  FROM "
					+ commonDatabaseSchema + ".cm_user_screen_function_mapping" + " WHERE USER_FK="
					+ authDetailsVo.getUserId() + " AND SUB_SCREEN_ID = " + commonDropDownVO.getId()
					+ ") a ON SCREEN_FUNCTION_FK=r.SCREEN_FUNCTION_ID" + " WHERE SUB_SCREEN_ID = "
					+ commonDropDownVO.getId() + " GROUP BY r.SCREEN_FUNCTION_ID order by r.SCREEN_FUNCTION_ID";

			List<Object[]> list = (List<Object[]>) entityManager.createNativeQuery(query).getResultList();

			return list;
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();

		}
		return null;
	}
}
