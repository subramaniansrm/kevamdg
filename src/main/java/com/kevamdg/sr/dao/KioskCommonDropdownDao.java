package com.kevamdg.sr.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.kevamdg.sr.auth.AuthUtil;
import com.kevamdg.sr.constants.CommonConstant;
import com.kevamdg.sr.entity.CityEntity;
import com.kevamdg.sr.entity.CountryEntity;
import com.kevamdg.sr.entity.DepartmentEntity;
import com.kevamdg.sr.entity.DivisionEntity;
import com.kevamdg.sr.entity.LocationEntity;
import com.kevamdg.sr.entity.Screen;
import com.kevamdg.sr.entity.StateEntity;
import com.kevamdg.sr.entity.UserEntity;
import com.kevamdg.sr.entity.UserType;
import com.kevamdg.sr.vo.CommonVO;
import com.kevamdg.sr.vo.KioskDepartmentVo;
import com.kevamdg.sr.vo.UserMasterVo;
import com.kevamdg.sr.vo.UserRoleTypeVO;
import com.kevamdg.sr.vo.UserRoleVO;

@Component
public class KioskCommonDropdownDao {
	
	@Autowired
	EntityManager entityManager;
	
	@Value("${commonDatabaseSchema}")
	public String commonDatabaseSchema;
	
	/**
	 * Method is used for Load the location
	 * 
	 * @return list List<LocationEntity>
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllLocation() {

		String query = "SELECT ul.id,ul.userLocationName FROM LocationEntity ul WHERE "
				+ " ul.activeFlag = '1' AND ul.deleteFlag = '0' ORDER BY ul.id DESC ";
				


		List<Object[]> list = (List<Object[]>) entityManager.createQuery(query).getResultList();

		return list;
	}

	/**
	 * Method is used for Load the Department
	 * 
	 * @return list List<DepartmentEntity>
	 */
	/*@SuppressWarnings("unchecked")
	public List<DepartmentEntity> getAllDepartment(KioskDepartmentVo departmentVo) {

		String query = "FROM DepartmentEntity c WHERE c.systemApplicationEntity.sysAppId = "
				+ CommonConstant.CONSTANT_TWO + " AND c.deleteFlag  = '" + CommonConstant.FLAG_ZERO + "'";

		if (departmentVo.getUserLocation() != null && departmentVo.getUserLocation() != 0) {

			query = query + " and c.userLocationEntity.id = " + departmentVo.getUserLocation() + "";
		}
		if (departmentVo.getSublocationId() != 0) {

			query = query + " and c.subLocationEntity.sublocationId = " + departmentVo.getSublocationId() + "";
		}

		query = query + " ORDER BY c.id DESC ";

		List<DepartmentEntity> list = (List<DepartmentEntity>) entityManager.createQuery(query)
				.getResultList();

		return list;
	}
*/
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllDepartment(KioskDepartmentVo departmentVo) {
		
		List<Object[]> list = null;

		String query = "select c.id,c.departmentName,"
				+ " sl.subLocationName "
				+ " FROM DepartmentEntity c,SubLocationEntity sl "
				+ " WHERE c.subLocationId = sl.sublocationId AND"
				+ " c.deleteFlag  = '" + CommonConstant.FLAG_ZERO + "'";

		if (departmentVo.getUserLocation() != null && departmentVo.getUserLocation() != 0) {

			query = query + " and c.locationId = " + departmentVo.getUserLocation() + "";
		}
		if (departmentVo.getSublocationId() != 0) {

			query = query + " and c.subLocationId = " + departmentVo.getSublocationId() + "";
		}

		query = query + " ORDER BY c.id DESC ";

		list = (List<Object[]>) entityManager.createQuery(query)
				.getResultList();


		return list;
	}

	/**
	 * Method is used for Load the role
	 * 
	 * @return list List<UserRoleEntity>
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllRole(UserRoleVO userRoleVo) {

		String query = "SELECT u.id,u.userRoleName FROM UserRoleEntity u WHERE u.deleteFlag = '0'";

		if (userRoleVo.getUserLocation() != null && userRoleVo.getUserLocation() != 0) {

			query = query + " and u.userLocation = " + userRoleVo.getUserLocation();
		}

		if (userRoleVo.getSublocationId() != 0) {

			query = query + " and u.sublocationId = " + userRoleVo.getSublocationId();
		}

		if (userRoleVo.getUserDepartment() != null && userRoleVo.getUserDepartment() != 0) {

			query = query + " and u.userDepartment = " + userRoleVo.getUserDepartment();
		}

		query = query + " ORDER BY u.id DESC ";

		List<Object[]> list = (List<Object[]>) entityManager.createQuery(query).getResultList();

		return list;
	}

	/**
	 * Method is used for Load the Division
	 * 
	 * @return list List<DivisionEntity>
	 */
	@SuppressWarnings("unchecked")
	public List<DivisionEntity> getAllDivision() {

		String query = "FROM DivisionEntity c WHERE  c.deleteFlag  = '" + CommonConstant.FLAG_ZERO 
				+ "' ORDER BY id DESC ";

		List<DivisionEntity> list = (List<DivisionEntity>) entityManager.createQuery(query).getResultList();

		return list;
	}

	/**
	 * Method is used for Load the State
	 * 
	 * @return list List<StateEntity>
	 */
	@SuppressWarnings("unchecked")
	public List<StateEntity> getAllState() {

		String query = "FROM StateEntity c ORDER BY id DESC ";

		List<StateEntity> list = (List<StateEntity>) entityManager.createQuery(query).getResultList();

		return list;
	}

	/**
	 * Method is used for Load the Screen.
	 * 
	 * @return list List<Screen>
	 */
	@SuppressWarnings("unchecked")
	public List<Screen> getAllScreen() {

		String query = "FROM Screen c ORDER BY screenId DESC ";

		List<Screen> list = (List<Screen>) entityManager.createQuery(query).getResultList();

		return list;
	}

	/**
	 * Method is used for Load the City.
	 * 
	 * @return list List<CityEntity>
	 */
	@SuppressWarnings("unchecked")
	public List<CityEntity> getAllCity() {

		String query = "FROM CityEntity c  ORDER BY id DESC ";

		List<CityEntity> list = (List<CityEntity>) entityManager.createQuery(query).getResultList();

		return list;
	}

	/**
	 * Method is used for Load the Country.
	 * 
	 * @return list List<CountryEntity>
	 */
	@SuppressWarnings("unchecked")
	public List<CountryEntity> getAllCountry() {

		String query = "FROM CountryEntity c WHERE  c.deleteFlag  = '" + CommonConstant.FLAG_ZERO + "' ORDER BY c.id DESC ";

		List<CountryEntity> list = (List<CountryEntity>) entityManager.createQuery(query).getResultList();

		return list;
	}

	
	

	/**
	 * method to load userDepartment
	 * 
	 * @return UserRoleMaster
	 */
	@SuppressWarnings("unchecked")
	public List<DepartmentEntity> getLoadUserDepartmentDetails() {

		String query = "FROM DepartmentEntity c WHERE c.deleteFlag  = '" + CommonConstant.FLAG_ZERO
				+ "' ORDER BY id DESC ";

		List<DepartmentEntity> userDepartmentEntityList = (List<DepartmentEntity>) entityManager
				.createQuery(query).getResultList();

		return userDepartmentEntityList;
	}

	/**
	 * method to load userLocation
	 * 
	 * @return UserRoleMaster
	 */

	@SuppressWarnings("unchecked")
	public List<LocationEntity> getLoadUserLocationDetails() {

		String query = "FROM LocationEntity c WHERE c.activeFlag = '" + CommonConstant.CONSTANT_ONE + "' AND c.deleteFlag  = '"
				+ CommonConstant.FLAG_ZERO + "' ORDER BY id DESC ";

		List<LocationEntity> userLocationEntityList = (List<LocationEntity>) entityManager
				.createQuery(query).getResultList();

		return userLocationEntityList;
	}

	/**
	 * method used to load Location
	 * 
	 * 
	 * @return
	 */
	public List<LocationEntity> loadDepartmentLocation() {

		String query = "FROM LocationEntity c WHERE c.deleteFlag  = '" + CommonConstant.FLAG_ZERO
				+ "' ORDER BY id DESC ";

		@SuppressWarnings("unchecked")
		List<LocationEntity> userLocationEntity = (List<LocationEntity>) entityManager.createQuery(query)
				.getResultList();

		return userLocationEntity;

	}

	/**
	 * Method is used for Load Sub location list
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllSublocatList(int id) {
		
		String query = "SELECT SUBLOCATION_ID,SUBLOCATION_NAME FROM user_sublocation "
				+ " WHERE DELETE_FLAG = 0 AND SUBLOCATION_ISACTIVE = 1"; 
				
		
		if (id != 0) {

			query = query + " AND LOCATION_ID =" + id;
		}

		query = query + " ORDER BY SUBLOCATION_ID DESC";
		
		List<Object[]> subLocationEntityList = (List<Object[]>) entityManager.createNativeQuery(query)
				.getResultList();

		return subLocationEntityList;
	}

	/**
	 * Method is used to GetAll the user for User dropdown
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	/*public List<UserEntity> getAllUser(UserMasterVo userMasterVo) {

		String query = "FROM UserEntity c WHERE c.systemApplicationEntity.sysAppId = " + CommonConstant.CONSTANT_TWO
				+ " AND c.activeFlag = '" + CommonConstant.CONSTANT_ONE + "' AND c.deleteFlag  = '"
				+ CommonConstant.FLAG_ZERO + "'";

		if (userMasterVo.getUserLocation() != null && userMasterVo.getUserLocation() != 0) {

			query = query + " and c.userLocationEntity.id = " + userMasterVo.getUserLocation();
		}
		if (userMasterVo.getSubLocation() != null && userMasterVo.getSubLocation() != 0) {

			query = query + " and c.subLocationEntity.sublocationId = " + userMasterVo.getSubLocation();
		}
		if (userMasterVo.getUserDepartment() != null && userMasterVo.getUserDepartment() != 0) {

			query = query + " and c.userDepartmentEntity.id = " + userMasterVo.getUserDepartment();
		}
		if (userMasterVo.getUserRole() != null && userMasterVo.getUserRole() != 0) {

			query = query + " and c.userRoleEntity.id = " + userMasterVo.getUserRole();
		}

		query = query + " ORDER BY id DESC ";

		List<UserEntity> list = (List<UserEntity>) entityManager.createQuery(query).getResultList();

		return list;
	}
*/
	
	public List<Object> getAllUser(UserMasterVo userMasterVo) {

		String query = "select USER_ID,concat(FIRST_NAME,' ',LAST_NAME )FROM user c WHERE c.ACTIVE = '" + CommonConstant.CONSTANT_ONE + "' AND c.DELETE_FLAG  = '"
				+ CommonConstant.FLAG_ZERO + "'";

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

		List<Object> list = (List<Object>) entityManager.createNativeQuery(query).getResultList();

		return list;
	}

	/**
	 * Method is used for Load user execuer
	 * 
	 * @param userMasterVo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getUserExecuter(UserMasterVo userMasterVo) {

		String query = "SELECT u.USER_ID,u.FIRST_NAME,u.USER_DEPARTMENT_ID FROM USER u "
				+ " WHERE u.DELETE_FLAG =0 AND  u.USER_DEPARTMENT_ID = "
				+ userMasterVo.getUserDepartment()
				+ " AND u.USER_ROLE_ID = " + userMasterVo.getUserRole()
				+ " AND u.USER_ID != " + userMasterVo.getId() + "";

		List<Object[]> list = (List<Object[]>) entityManager.createNativeQuery(query).getResultList();

		return list;
	}


	/**
	 * Method is used to get the Level
	 * 
	 * @return list
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllLevel() {

		String query = "SELECT COMMON_ID,ITEM_VALUE FROM common_storage ORDER BY COMMON_ID";

		List<Object[]> list = (List<Object[]>) entityManager.createNativeQuery(query)
				.getResultList();

		return list;
	}

	@SuppressWarnings("unchecked")
	public List<UserEntity> getUserDep(UserMasterVo userMasterVo) {
		
		String query = "FROM UserEntity c WHERE c.departmentId = " + userMasterVo.getUserDepartment() + " AND c.activeFlag = '"
				+ CommonConstant.CONSTANT_ONE + "' AND c.deleteFlag  = '" + CommonConstant.FLAG_ZERO
				+ "' ORDER BY id DESC ";
		List<UserEntity> list = (List<UserEntity>) entityManager.createQuery(query).getResultList();
		return list;
	}
	
	public List<UserType> getRoleType(UserRoleTypeVO userRoleTypeVo) {
		String query = "FROM UserType c ORDER BY c.userTypeId DESC ";
		@SuppressWarnings("unchecked")
		List<UserType> list = (List<UserType>) entityManager.createQuery(query).getResultList();
		return list;
	}

	public List<Object[]> getReassignUser(CommonVO requestWorkFlowAuditVo) {

		String query = "SELECT DISTINCT(u.USER_ID),u.FIRST_NAME,u.LAST_NAME FROM "+commonDatabaseSchema
				+ ".user u LEFT JOIN keva_intranet_kiosk.rin_tr_req_workflow_audit audit "
				+ " ON u.USER_ID = audit.rin_tr_req_workflow_audit_user_id "
				+ " WHERE audit.rin_tr_request_id = " +requestWorkFlowAuditVo.getRequestId()+ " AND "
				+ " audit.rin_tr_req_workflow_audit_user_id != "+AuthUtil.getUserId()+ " "
				+ " AND audit.rin_tr_req_workflow_audit_approval_executer =2";

		List<Object[]> list = (List<Object[]>) entityManager.createNativeQuery(query).getResultList();

		return list;
	}
}
