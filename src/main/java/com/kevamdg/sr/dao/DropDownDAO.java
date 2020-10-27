package com.kevamdg.sr.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.kevamdg.sr.constants.CommonConstant;
import com.kevamdg.sr.entity.CityEntity;
import com.kevamdg.sr.entity.CommonScreenFieldEntity;
import com.kevamdg.sr.entity.CountryEntity;
import com.kevamdg.sr.entity.DepartmentEntity;
import com.kevamdg.sr.entity.ModuleEntity;
import com.kevamdg.sr.entity.StateEntity;
import com.kevamdg.sr.entity.UserType;
import com.kevamdg.sr.vo.DepartmentVO;
import com.kevamdg.sr.vo.UserRoleTypeVO;
import com.kevamdg.sr.vo.UserRoleVO;
import com.kevamdg.sr.vo.UserVO;

/**
 * 
 * Common Drop Down Dao
 * 
 * @author raathikaabm
 *
 */

@Repository
public class DropDownDAO {

	@Autowired
	EntityManager manager;

	@Value("${commonDatabaseSchema}")
	private String commonDatabaseSchema;

	@Value("${mmDatabaseSchema}")
	private String mmDatabaseSchema;

	/**
	 * LOCATION DROPDOWN
	 * 
	 * @return
	 */

	public List<Object[]> getAllLocation() {
		try {
			String query = "SELECT l.id,l.userLocationName from LocationEntity l where l.deleteFlag= '0' order by l.id desc ";

			List<Object[]> list = (List<Object[]>) manager.createQuery(query).getResultList();

			return list;
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();

		}
		return null;
	}

	/**
	 * DEPARTMENT LOAD BASED ON LOCATION AND SUBLOCATION
	 * 
	 * @param departmentVo
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public List<Object[]> getAllDepartment(DepartmentVO departmentVo) {

		List<Object[]> list = null;

		String query = "select c.departmentId,c.departmentName FROM DepartmentEntity c WHERE  c.deleteFlag  = '"
				+ CommonConstant.FLAG_ZERO + "'";

		if (departmentVo.getLocationId() != null && departmentVo.getLocationId() != null) {

			query = query + " and c.locationId = " + departmentVo.getLocationId() + "";
		}
		if (departmentVo.getSublocationId() != null) {

			query = query + " and c.subLocationId = " + departmentVo.getSublocationId() + "";
		}

		query = query + " ORDER BY c.departmentId DESC ";

		try {
			list = (List<Object[]>) manager.createQuery(query).getResultList();
			return list;

		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * USER ROLE DROP DOWN BASED ON LOCATION, SUBLOCATION AND DEPARTMENT
	 * 
	 * @param userRoleVo
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public List<Object[]> getAllRole(UserRoleVO userRoleVo) {

		String query = "SELECT c.id,c.userRoleName FROM UserRoleEntity c WHERE  c.deleteFlag  = '"
				+ CommonConstant.FLAG_ZERO + "'";

		if (userRoleVo.getUserLocation() != null && userRoleVo.getUserLocation() != null) {

			query = query + " and c.userLocation = " + userRoleVo.getUserLocation();
		}

		if (userRoleVo.getSublocationId() != null) {

			query = query + " and c.sublocationId = " + userRoleVo.getSublocationId();
		}

		if (userRoleVo.getUserDepartment() != null && userRoleVo.getUserDepartment() != null) {

			query = query + " and c.userDepartment = " + userRoleVo.getUserDepartment();
		}

		query = query + " ORDER BY c.id DESC ";

		List<Object[]> list = (List<Object[]>) manager.createQuery(query).getResultList();

		return list;
	}

	@SuppressWarnings("unchecked")
	public List<DepartmentEntity> getLoadUserDepartmentDetails() {

		String query = "select c FROM DepartmentEntity c WHERE  c.deleteFlag  = '" + CommonConstant.FLAG_ZERO
				+ "' ORDER BY c.departmentId DESC ";

		List<DepartmentEntity> userDepartmentEntityList = (List<DepartmentEntity>) manager.createQuery(query)
				.getResultList();

		return userDepartmentEntityList;
	}

	/**
	 * COUNTRY DROP DOWN
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CountryEntity> getAllCountry() {

		String query = "SELECT c FROM CountryEntity c WHERE  c.deleteFlag  = '" + CommonConstant.FLAG_ZERO
				+ "' ORDER BY c.id DESC ";

		List<CountryEntity> list = (List<CountryEntity>) manager.createQuery(query).getResultList();

		return list;
	}

	/**
	 * STATE DROPDOWN
	 * 
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public List<StateEntity> getAllState() {

		String query = "SELECT c FROM StateEntity c WHERE c.deleteFlag ='0' ORDER BY c.id DESC ";

		List<StateEntity> list = (List<StateEntity>) manager.createQuery(query).getResultList();

		return list;
	}

	/**
	 * CITY DROPDOWN
	 * 
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public List<CityEntity> getAllCity() {

		String query = "SELECT c FROM CityEntity c ORDER BY c.cityId DESC ";

		List<CityEntity> list = (List<CityEntity>) manager.createQuery(query).getResultList();

		return list;
	}

	/**
	 * SUBLOCATION DROPDOWN BASED ON LOCATION
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllSublocatList(int id) {
		String query = "SELECT SUBLOCATION_ID,SUBLOCATION_NAME " + " FROM " + commonDatabaseSchema
				+ ".user_sublocation " + " WHERE DELETE_FLAG = 0 " + " AND SUBLOCATION_ISACTIVE = 1 ";

		if (id != 0) {

			query = query + " AND LOCATION_ID =" + id;
		}

		query = query + " ORDER BY SUBLOCATION_ID DESC";

		List<Object[]> subLocationEntityList = (List<Object[]>) manager.createNativeQuery(query).getResultList();
		return subLocationEntityList;

	}

	/**
	 * USER DROPDOWN
	 * 
	 * @param userMasterVo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object> getAllUser(UserVO userMasterVo) {

		String query = "select USER_ID,concat(FIRST_NAME,' ',LAST_NAME )FROM user c WHERE " + "  c.ACTIVE = '"
				+ CommonConstant.CONSTANT_ONE + "' AND c.DELETE_FLAG  = '" + CommonConstant.FLAG_ZERO + " '";

		if (userMasterVo.getUserLocation() != null && userMasterVo.getUserLocation() != 0) {

			query = query + " and c.USER_LOCATION_ID = " + userMasterVo.getUserLocation();
		}
		if (userMasterVo.getSubLocation() != null && userMasterVo.getSubLocation() != 0) {

			query = query + " and c.USER_SUBLOCATION_ID = " + userMasterVo.getSubLocation();
		}
		if (userMasterVo.getUserDepartment() != null && userMasterVo.getUserDepartment() != 0) {

			query = query + " and c.USER_DEPARTMENT_ID = " + userMasterVo.getUserDepartment();
		}
		if (userMasterVo.getUserRole() != null && userMasterVo.getUserRole() != 0) {

			query = query + " and c.USER_ROLE_ID= " + userMasterVo.getUserRole();
		}

		query = query + " ORDER BY USER_ID DESC ";

		List<Object> list = (List<Object>) manager.createNativeQuery(query).getResultList();

		return list;
	}

	public List<UserType> getRoleType(UserRoleTypeVO userRoleTypeVo) {
		String query = "FROM UserType c ORDER BY c.userTypeId DESC ";
		@SuppressWarnings("unchecked")
		List<UserType> list = (List<UserType>) manager.createQuery(query).getResultList();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<ModuleEntity> getModule() {
		String query = "FROM ModuleEntity c WHERE c.activeFlag = 1 ORDER BY c.id DESC ";

		List<ModuleEntity> list = (List<ModuleEntity>) manager.createQuery(query).getResultList();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<CommonScreenFieldEntity> screenField() {
		String query = "FROM CommonScreenFieldEntity c WHERE c.activeFlag = 1 and c.deleteFlag = 0 ORDER BY c.id DESC ";

		List<CommonScreenFieldEntity> list = (List<CommonScreenFieldEntity>) manager.createQuery(query).getResultList();
		return list;
	}

	public List<Object[]> getModuleRights(int id) {
		String query = "SELECT c.id,c.userId,c.moduleId,m.moduleTitle FROM UserMappingModuleEntity c,ModuleEntity m"
				+ " WHERE m.id=c.moduleId AND c.userId =" + id
				+ " AND c.activeFlag = 1 GROUP BY m.moduleTitle ORDER BY c.id DESC ";
		@SuppressWarnings("unchecked")
		List<Object[]> list = (List<Object[]>) manager.createQuery(query).getResultList();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> screenList(Integer id) {

		String query = "SELECT s.SCREEN_ID,s.MODULE_ID,s.SCREEN_NAME,s.SCREEN_TYPE_FLAG,s.SCREEN_URL,s.SCREEN_ICON,"
				+ " m.MODULE_NAME FROM " + commonDatabaseSchema + ".mm_screen s" + " LEFT JOIN " + mmDatabaseSchema
				+ ".user_screen_mapping usm ON usm.SCREEN_FK = s.SCREEN_ID" + " LEFT JOIN " + commonDatabaseSchema
				+ ".module m ON m.MODULE_PK = s.MODULE_ID"
				+ " WHERE s.MODULE_ID = 1 AND s.ACTIVE_FLAG ='1' AND usm.USER_FK =" + id + " GROUP BY s.SCREEN_ID";

		List<Object[]> list = (List<Object[]>) manager.createNativeQuery(query).getResultList();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> cmScreenList(Integer id) {

		String query = "SELECT s.SCREEN_ID,s.MODULE_ID,s.SCREEN_NAME,s.SCREEN_TYPE_FLAG,s.SCREEN_URL,"
				+ " s.SCREEN_ICON,m.MODULE_NAME FROM " + commonDatabaseSchema + ".cm_screen s" + " LEFT JOIN "
				+ commonDatabaseSchema + ".cm_user_screen_mapping usm ON usm.SCREEN_FK = s.SCREEN_ID" + " LEFT JOIN "
				+ commonDatabaseSchema + ".module m ON m.MODULE_PK = s.MODULE_ID"
				+ " WHERE s.MODULE_ID = 2 AND s.ACTIVE_FLAG ='1' AND usm.USER_FK =" + id + " GROUP BY s.SCREEN_ID";

		List<Object[]> list = (List<Object[]>) manager.createNativeQuery(query).getResultList();
		return list;
	}

	public List<Object[]> mdgModuleRights(int id) {
		String query = "SELECT c.id,c.userId,c.moduleId,m.moduleTitle,m.moduleName FROM UserMappingModuleEntity c,ModuleEntity m"
				+ " WHERE m.id=c.moduleId AND c.userId =" + id
				+ " AND m.moduleTitle = 'MDG' AND c.activeFlag = 1 ORDER BY c.id DESC ";
		@SuppressWarnings("unchecked")
		List<Object[]> list = (List<Object[]>) manager.createQuery(query).getResultList();
		return list;
	}

	public Object[] referTo() {

		String query = "SELECT ws.SEQUENCE_ROLE_ID,ws.SEQUENCE_USER_ID FROM request_workflow w"
				+ " LEFT JOIN request_workflow_sequence ws ON ws.WORKFLOW_ID = w.WORKFLOW_ID"
				+ " WHERE ws.WORKFLOW_SEQUENCE_NUMBER = 3 AND w.DELETE_FLAG =0"
				+ " AND w.REQUEST_SUBTYPE_ID = 12 AND w.SALES_ORG_ID = 1";

		Object[] obj = (Object[]) manager.createNativeQuery(query).getSingleResult();

		return obj;

	}

}
