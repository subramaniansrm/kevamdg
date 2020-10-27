package com.kevamdg.sr.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kevamdg.sr.config.CommonException;
import com.kevamdg.sr.constants.CommonConstant;
import com.kevamdg.sr.controller.CommonAction;
import com.kevamdg.sr.dao.ScreenAssignmentDao;
import com.kevamdg.sr.dao.UserMappingDao;
import com.kevamdg.sr.entity.FieldAuthentication;
import com.kevamdg.sr.entity.FunctionAuthentication;
import com.kevamdg.sr.entity.Screen;
import com.kevamdg.sr.entity.ScreenAuthentication;
import com.kevamdg.sr.entity.ScreenField;
import com.kevamdg.sr.entity.ScreenFunction;
import com.kevamdg.sr.entity.SubScreen;
import com.kevamdg.sr.entity.UserRoleEntity;
import com.kevamdg.sr.vo.AuthDetailsVO;
import com.kevamdg.sr.vo.AuthenticationListCombo;
import com.kevamdg.sr.vo.FieldAuthenticationMaster;
import com.kevamdg.sr.vo.FunctionAuthenticationMaster;
import com.kevamdg.sr.vo.ScreenAuthenticationMaster;
import com.kevamdg.sr.vo.ScreenAuthenticationVO;
import com.kevamdg.sr.vo.ScreenAuthorizationVO;
import com.kevamdg.sr.vo.UserMasterVo;
import com.kevamdg.sr.vo.UserRoleMasterListDisplayVo;



/**
 * 
 * @author raathikaabm
 *
 */
@Service
public class ScreenAssignmentService extends CommonAction<ScreenAuthenticationMaster> {

	@Autowired
	ScreenAssignmentDao screenAssignmentDao;
	
	@Autowired
	UserMappingDao userMappingDao;

	@Transactional
	public List<UserRoleMasterListDisplayVo> searchScreenAuthentication(
			ScreenAuthenticationMaster screenAuthenticationMaster) {

		return screenAssignmentDao.searchScreenAuthentication(screenAuthenticationMaster);
	}

	/*@Transactional
	public ScreenAuthenticationMaster load() {
		return screenAssignmentDao.load();
	}*/

	/**
	 * method to save screen Assignment
	 * 
	 * @param screenAuthenticationMaster
	 * @return screenAuthenticationMaster
	 */
	@Transactional
	public ScreenAuthenticationMaster saveScreenAssignment(ScreenAuthenticationMaster screenAuthenticationMaster, AuthDetailsVO authDetailsVO) {

		if (null != screenAuthenticationMaster.getScreenAuthenticationMasterList()
				&& screenAuthenticationMaster.getScreenAuthenticationMasterList().size() > 0) {

			for (ScreenAuthenticationMaster screenAuthenticationMaster2 : screenAuthenticationMaster
					.getScreenAuthenticationMasterList()) {
				screenAuthenticationMaster2.setRoleId(screenAuthenticationMaster.getRoleId());
				List<Integer> list = screenAssignmentDao.checkScreenExists(screenAuthenticationMaster.getRoleId(),
						screenAuthenticationMaster2.getScreenId(), screenAuthenticationMaster2.getSubScreenId());
				if (null != list && list.size() > 0) {
					for (Integer id : list) {
						// screenAuthenticationMaster2.setRoleId(screenAuthenticationMaster.getRoleId());
						if (null != id) {
							updateScreenAuthentication(id, screenAuthenticationMaster2,authDetailsVO);
						}
					}
				} else {
					// Save Screen Authentication
					saveScreenAuthentication(screenAuthenticationMaster2,authDetailsVO);
				}

			}

		}

		return screenAuthenticationMaster;
	}

	@Transactional
	public ScreenAuthenticationMaster updateScreenAuthentication(Integer authenticationId,
			ScreenAuthenticationMaster screenAuthenticationMaster,AuthDetailsVO authDetailsVO) {

		if (null != authenticationId) {
			screenAssignmentDao.deleteScreenFunction(authenticationId, screenAuthenticationMaster);
			screenAssignmentDao.deleteScreenFields(authenticationId, screenAuthenticationMaster);
			screenAssignmentDao.deleteScreenAuthentication(authenticationId, screenAuthenticationMaster);
			saveScreenAuthentication(screenAuthenticationMaster,authDetailsVO);

		}

		return screenAuthenticationMaster;

	}

	@Transactional
	public ScreenAuthenticationMaster saveScreenAuthentication(ScreenAuthenticationMaster screenAuthenticationMaster,AuthDetailsVO authDetailsVO) {

		if (null != screenAuthenticationMaster.getRoleId()) {

			ScreenAuthentication screenAuthenticationEntity = new ScreenAuthentication();
			screenAuthenticationEntity.setDeleteFlag(CommonConstant.FLAG_ZERO);
			screenAuthenticationEntity.setCreatedBy(authDetailsVO.getUserName());
			screenAuthenticationEntity.setCreatedDate(CommonConstant.getCalenderDate());
			screenAuthenticationEntity.setUpdatedBy(authDetailsVO.getUserName());
			screenAuthenticationEntity.setUpdatedDate(CommonConstant.getCalenderDate());

//			SystemApplicationEntity systemApplicationEntity = new SystemApplicationEntity();
//			systemApplicationEntity.setSysAppId(userConstants.getSysAppId());
//			screenAuthenticationEntity.setSysAppEntity(systemApplicationEntity);

		UserRoleEntity userRoleEntity = new UserRoleEntity();
			userRoleEntity.setId(screenAuthenticationMaster.getRoleId());
			screenAuthenticationEntity.setUserRoleEntity(userRoleEntity);
		
		

			if (null != screenAuthenticationMaster.getScreenId()) {
				Screen screenEntity = new Screen();
				screenEntity.setScreenId(screenAuthenticationMaster.getScreenId());
				screenAuthenticationEntity.setScreenEntity(screenEntity);
			}
			if (null != screenAuthenticationMaster.getSubScreenId()) {
				SubScreen subScreenEntity = new SubScreen();
				subScreenEntity.setSubScreenId(screenAuthenticationMaster.getSubScreenId());
				screenAuthenticationEntity.setSubScreenEntity(subScreenEntity);
			}
			
			if (null == screenAuthenticationMaster.getSubScreenId()) {
				throw new CommonException(getMessage("getSelectSubScreen()"));
			}
			ScreenAuthentication screenAuthenticationEntity2 = screenAssignmentDao
					.saveScreenAuthentication(screenAuthenticationEntity);

			if (screenAuthenticationMaster.getFieldAuthenticationMasterList().isEmpty()
					&& screenAuthenticationMaster.getFunctionAuthenticationMasterList().isEmpty()) {

				if (CommonConstant.getStaticScreenIds().contains(screenAuthenticationMaster.getScreenId())) {
					// Save Screen Fields
					saveScreenFields(screenAuthenticationMaster,
							screenAuthenticationEntity2.getScreenAuthenticationId(),authDetailsVO);
					// Save Screen Funcions
					saveScreenFunctions(screenAuthenticationMaster,
							screenAuthenticationEntity2.getScreenAuthenticationId(),authDetailsVO);

				} else {
					screenAssignmentDao.deleteScreenAuthentication(
							screenAuthenticationEntity2.getScreenAuthenticationId(), screenAuthenticationMaster);
				}
			} else if (null != screenAuthenticationEntity2
					&& null != screenAuthenticationEntity2.getScreenAuthenticationId()) {
				// Save Screen Fields
				saveScreenFields(screenAuthenticationMaster, screenAuthenticationEntity2.getScreenAuthenticationId(),authDetailsVO);
				// Save Screen Funcions
				saveScreenFunctions(screenAuthenticationMaster,
						screenAuthenticationEntity2.getScreenAuthenticationId(),authDetailsVO);
			}
		}

		return screenAuthenticationMaster;
	}
	@Transactional
	public ScreenAuthenticationMaster saveScreenFunctions(ScreenAuthenticationMaster screenAuthenticationMaster,
			Integer screenAuthenticationId,AuthDetailsVO authDetailsVO) {

		if (null != screenAuthenticationId && null != screenAuthenticationMaster.getFunctionAuthenticationMasterList()
				&& screenAuthenticationMaster.getFunctionAuthenticationMasterList().size() > 0) {
			FunctionAuthentication functionAuthenticationEntity = null;
			for (FunctionAuthenticationMaster functionAuthenticationMaster : screenAuthenticationMaster
					.getFunctionAuthenticationMasterList()) {

				if (null != functionAuthenticationMaster.getScreenFunctionId()) {
					functionAuthenticationEntity = new FunctionAuthentication();

					functionAuthenticationEntity.setCreatedBy(authDetailsVO.getUserName());
					functionAuthenticationEntity.setCreatedDate(CommonConstant.getCalenderDate());
					functionAuthenticationEntity.setUpdatedBy(authDetailsVO.getUserName());
					functionAuthenticationEntity.setUpdatedDate(CommonConstant.getCalenderDate());
					functionAuthenticationEntity.setDeleteFlag(CommonConstant.FLAG_ZERO);

//					SystemApplicationEntity systemApplicationEntity = new SystemApplicationEntity();
//					systemApplicationEntity.setSysAppId(userConstants.getSysAppId());
//					functionAuthenticationEntity.setSysAppEntity(systemApplicationEntity);

					ScreenAuthentication screenAuthenticationEntity = new ScreenAuthentication();
					screenAuthenticationEntity.setScreenAuthenticationId(screenAuthenticationId);
					functionAuthenticationEntity.setScreenAuthenticationEntity(screenAuthenticationEntity);

					if (null != functionAuthenticationMaster.getScreenFunctionId()) {

						ScreenFunction screenFunctionEntity = new ScreenFunction();
						screenFunctionEntity.setScreenFunctionId(functionAuthenticationMaster.getScreenFunctionId());
						functionAuthenticationEntity.setScreenFunctionEntity(screenFunctionEntity);
					}

					screenAssignmentDao.saveScreenFunction(functionAuthenticationEntity);
				}
			}
		}
		return screenAuthenticationMaster;
	}

	@Transactional
	public ScreenAuthenticationMaster saveScreenFields(ScreenAuthenticationMaster screenAuthenticationMaster,
			Integer screenAuthenticationId,AuthDetailsVO authDetailsVO) {

		if (null != screenAuthenticationId && null != screenAuthenticationMaster.getFieldAuthenticationMasterList()
				&& screenAuthenticationMaster.getFieldAuthenticationMasterList().size() > 0) {
			FieldAuthentication fieldAuthenticationEntity = null;
			for (FieldAuthenticationMaster fieldAuthenticationMaster : screenAuthenticationMaster
					.getFieldAuthenticationMasterList()) {

				fieldAuthenticationEntity = new FieldAuthentication();
				fieldAuthenticationEntity.setDeleteFlag(CommonConstant.FLAG_ZERO);
				fieldAuthenticationEntity.setCreatedBy(authDetailsVO.getUserName());
				fieldAuthenticationEntity.setCreatedDate(CommonConstant.getCalenderDate());
				fieldAuthenticationEntity.setUpdatedBy(authDetailsVO.getUserName());
				fieldAuthenticationEntity.setUpdatedDate(CommonConstant.getCalenderDate());
//
//				SystemApplicationEntity systemApplicationEntity = new SystemApplicationEntity();
//				systemApplicationEntity.setSysAppId(userConstants.getSysAppId());
//				fieldAuthenticationEntity.setSysAppEntity(systemApplicationEntity);

				ScreenAuthentication screenAuthenticationEntity = new ScreenAuthentication();
				screenAuthenticationEntity.setScreenAuthenticationId(screenAuthenticationId);
				fieldAuthenticationEntity.setScreenAuthenticationEntity(screenAuthenticationEntity);

				ScreenField screenFieldEntity = new ScreenField();
				screenFieldEntity.setFieldId(fieldAuthenticationMaster.getFieldId());
				fieldAuthenticationEntity.setScreenFieldEntity(screenFieldEntity);

				/*
				 * if(null != fieldAuthenticationMaster.getBaseFilter() &&
				 * fieldAuthenticationMaster.getBaseFilter().equals(true)){
				 * fieldAuthenticationEntity.setBaseFilter(CommonConstant.
				 * STRING_Y); }else{
				 * fieldAuthenticationEntity.setBaseFilter(CommonConstant.
				 * STRING_N); }
				 */

				screenAssignmentDao.saveScreenField(fieldAuthenticationEntity);
			}
		}
		return screenAuthenticationMaster;
	}

	@Transactional
	public ScreenAuthenticationMaster deleteScreenAssignment(ScreenAuthenticationMaster screenAuthenticationMaster)
			throws CommonException {

		// Get screenAuthenticationId Based on Role
		
		List<Integer> screenAuthenticationIdList = screenAssignmentDao
				.getAuthenticationId(screenAuthenticationMaster.getRoleId());
		
		List<Integer> userList = screenAssignmentDao.getRoleBasedUsers(screenAuthenticationMaster.getRoleId());
				
				
		if (null != userList && userList.size() > 0) {
			throw new CommonException(getMessage("getUserExistForSelectedRole()"));
		}
		for (Integer screenAuthenticationId : screenAuthenticationIdList) {

			// Delete Screen Functions Based on screenAuthenticationId
			screenAssignmentDao.deleteScreenFunction(screenAuthenticationId, screenAuthenticationMaster);

			// Delete Screen Fields Based on screenAuthenticationId
			screenAssignmentDao.deleteScreenFields(screenAuthenticationId, screenAuthenticationMaster);

			// Delete Screen Fields Based on screenAuthenticationId
			screenAssignmentDao.deleteScreenAuthentication(screenAuthenticationId, screenAuthenticationMaster);
		}

		return screenAuthenticationMaster;
	}

	@Transactional
	public ScreenAuthenticationMaster deleteAuthenticationBeforeUpdating(
			ScreenAuthenticationMaster screenAuthenticationMaster) {

		// Get screenAuthenticationId Based on Role
		List<Integer> screenAuthenticationIdList = screenAssignmentDao
				.getAuthenticationId(screenAuthenticationMaster.getRoleId());

		for (Integer screenAuthenticationId : screenAuthenticationIdList) {

			// Delete Screen Functions Based on screenAuthenticationId
			screenAssignmentDao.deleteScreenFunction(screenAuthenticationId, screenAuthenticationMaster);

			// Delete Screen Fields Based on screenAuthenticationId
			screenAssignmentDao.deleteScreenFields(screenAuthenticationId, screenAuthenticationMaster);

			// Delete Screen Fields Based on screenAuthenticationId
			screenAssignmentDao.deleteScreenAuthentication(screenAuthenticationId, screenAuthenticationMaster);
		}

		return screenAuthenticationMaster;
	}

	@Transactional
	public List<UserRoleMasterListDisplayVo> getRoleList() {
		// Get Role List
		List<Object> result = screenAssignmentDao.getRoleList();
		List<UserRoleMasterListDisplayVo> userRoleMasterListDisplayVoList = new ArrayList<UserRoleMasterListDisplayVo>();
		UserRoleMasterListDisplayVo userRoleMasterListDisplayVo = null;
		@SuppressWarnings("rawtypes")
		Iterator itr = result.iterator();
		while (itr.hasNext()) {
			Object[] obj = (Object[]) itr.next();

			userRoleMasterListDisplayVo = new UserRoleMasterListDisplayVo();

			if (null != obj[0]) {
				userRoleMasterListDisplayVo.setId(Integer.parseInt(String.valueOf(obj[0])));
			}
			if (null != obj[1]) {
				userRoleMasterListDisplayVo.setUserRoleName(String.valueOf(obj[1]));
			}
			userRoleMasterListDisplayVoList.add(userRoleMasterListDisplayVo);
		}
		return userRoleMasterListDisplayVoList;
	}

	@Transactional
	public ScreenAuthenticationMaster load(ScreenAuthenticationMaster screenAuthenticationMaster) {

		// Load Users and Role Name For Role Id
		loadUserAndRoleName(screenAuthenticationMaster);

		// Load Screen List based on Role
		loadRoleBasedScreenList(screenAuthenticationMaster);

		return screenAuthenticationMaster;
	}

	@SuppressWarnings("rawtypes")
	@Transactional
	public List<AuthenticationListCombo> loadRoleBasedSubScreenList(
			ScreenAuthenticationMaster screenAuthenticationMaster) {

		// Load Sub Screen List based on Screen
		List<Object> result = screenAssignmentDao.getSubScreenList(screenAuthenticationMaster.getScreenId());

		// Load Sub Screen List based on Role and Screen
		List<Integer> subScreenList = screenAssignmentDao.authenticationRoleForScreen(
				screenAuthenticationMaster.getRoleId(), screenAuthenticationMaster.getScreenId());

		AuthenticationListCombo authenticationListCombo = null;
		List<AuthenticationListCombo> authenticationListComboList = new ArrayList<AuthenticationListCombo>();
		Iterator itr = result.iterator();
		while (itr.hasNext()) {
			authenticationListCombo = new AuthenticationListCombo();
			Object[] obj = (Object[]) itr.next();
			authenticationListCombo.setId(Integer.parseInt(String.valueOf(obj[0])));
			authenticationListCombo.setName(String.valueOf(obj[1]));
			if (null != subScreenList && subScreenList.size() > 0) {
				if (null != authenticationListCombo.getId()
						&& subScreenList.contains(authenticationListCombo.getId())) {
					authenticationListCombo.setResult(true);
				} else {
					authenticationListCombo.setResult(false);
				}
			}
			if (null == authenticationListCombo.getResult()) {
				authenticationListCombo.setResult(false);
			}
			authenticationListComboList.add(authenticationListCombo);
		}

		return authenticationListComboList;
	}

	@Transactional
	public Map<Integer, String> loadRoleCombo() {
		return screenAssignmentDao.loadRoleCombo();
	}

	@SuppressWarnings("rawtypes")
	public List<AuthenticationListCombo> loadRoleBasedFunctionList(
			ScreenAuthenticationMaster screenAuthenticationMaster) {

		// Load Function List Based on Sub Screen
		List<Object> result = screenAssignmentDao.getFunctionList(screenAuthenticationMaster.getSubScreenId());

		// Get Screen Authentication Id
		List<Integer> screenAuthenticationIdList = screenAssignmentDao.screenSubScreenBasedRole(
				screenAuthenticationMaster.getRoleId(), screenAuthenticationMaster.getScreenId(),
				screenAuthenticationMaster.getSubScreenId());
		List<AuthenticationListCombo> authenticationListComboList = new ArrayList<AuthenticationListCombo>();
		List<Integer> functionList = new ArrayList<Integer>();
		for (Integer screenAuthenticationId : screenAuthenticationIdList) {

			screenAuthenticationMaster.setScreenAuthenticationId(screenAuthenticationId);
			functionList = screenAssignmentDao.screenFunctionBasedRole(screenAuthenticationId);
		}

		// Load Screen Functions

		AuthenticationListCombo authenticationListCombo = null;
		Iterator itr = result.iterator();
		while (itr.hasNext()) {
			authenticationListCombo = new AuthenticationListCombo();
			Object[] obj = (Object[]) itr.next();

			authenticationListCombo.setId(Integer.parseInt(String.valueOf(obj[0])));
			authenticationListCombo.setName(String.valueOf(obj[1]));
			if (null != functionList && functionList.size() > 0) {
				if (null != authenticationListCombo.getId() && functionList.contains(authenticationListCombo.getId())) {
					authenticationListCombo.setResult(true);
				} else {
					authenticationListCombo.setResult(false);
				}
			}
			if (null == authenticationListCombo.getResult()) {
				authenticationListCombo.setResult(false);
			}
			authenticationListComboList.add(authenticationListCombo);
		}
		return authenticationListComboList;
	}

	@SuppressWarnings("rawtypes")
	@Transactional
	public List<AuthenticationListCombo> loadRoleBasedScreenFieldsList(
			ScreenAuthenticationMaster screenAuthenticationMaster) {

		// Load Field List Based on Sub Screen
		List<Object> result = screenAssignmentDao.getScreenFieldList(screenAuthenticationMaster.getSubScreenId());
		List<AuthenticationListCombo> authenticationListComboList = new ArrayList<AuthenticationListCombo>();
		// Get Screen Authentication Id
		List<Integer> screenAuthenticationIdList = screenAssignmentDao.screenSubScreenBasedRole(
				screenAuthenticationMaster.getRoleId(), screenAuthenticationMaster.getScreenId(),
				screenAuthenticationMaster.getSubScreenId());
		List<Integer> fieldList = new ArrayList<Integer>();

		for (Integer screenAuthenticationId : screenAuthenticationIdList) {

			screenAuthenticationMaster.setScreenAuthenticationId(screenAuthenticationId);

			// Get Fields of Authentication Id
			if (null != screenAuthenticationId) {
				fieldList = screenAssignmentDao.getAuthenticationBasedFields(screenAuthenticationId);
			}
		}
		AuthenticationListCombo authenticationListCombo = null;

		Iterator itr = result.iterator();
		while (itr.hasNext()) {
			authenticationListCombo = new AuthenticationListCombo();
			Object[] obj = (Object[]) itr.next();

			authenticationListCombo.setId(Integer.parseInt(String.valueOf(obj[0])));
			authenticationListCombo.setName(String.valueOf(obj[1]));
			if (null != fieldList && fieldList.size() > 0) {
				if (null != authenticationListCombo.getId() && fieldList.contains(authenticationListCombo.getId())) {
					authenticationListCombo.setResult(true);
				} else {
					authenticationListCombo.setResult(false);
				}
			}
			if (null == authenticationListCombo.getResult()) {
				authenticationListCombo.setResult(false);
			}

			/*// Get Base filter From Field Id
			if (null != authenticationListCombo.getId()
					&& null != screenAuthenticationMaster.getScreenAuthenticationId()) {
				List<String> baseFilter = screenAssignmentDao.getAuthenticationBasedFieldsWithBaseFilter(
						screenAuthenticationMaster.getScreenAuthenticationId(), authenticationListCombo.getId());
				if (null != baseFilter && baseFilter.size() > 0) {
					for (String filter : baseFilter) {
						if (null != filter && filter.equals(CommonConstant.STRING_Y)) {
							authenticationListCombo.setBaseResult(true);
						} else {
							authenticationListCombo.setBaseResult(false);
						}
					}
				}

			}
			if (null == authenticationListCombo.getBaseResult()) {
				authenticationListCombo.setBaseResult(false);
			}*/
			authenticationListComboList.add(authenticationListCombo);
		}

		return authenticationListComboList;
	}

	@SuppressWarnings("rawtypes")
	public ScreenAuthenticationMaster loadRoleBasedScreenList(ScreenAuthenticationMaster screenAuthenticationMaster) {

		// Load All Screen List
		List<Object> result = screenAssignmentDao.getScreenList();
		// Load Screen List Based on Role
		List<Integer> screenList = screenAssignmentDao.authenticationRole(screenAuthenticationMaster.getRoleId());
		AuthenticationListCombo authenticationListCombo = null;
		List<AuthenticationListCombo> authenticationListComboList = new ArrayList<AuthenticationListCombo>();
		Iterator itr = result.iterator();
		while (itr.hasNext()) {
			authenticationListCombo = new AuthenticationListCombo();
			Object[] obj = (Object[]) itr.next();

			authenticationListCombo.setId(Integer.parseInt(String.valueOf(obj[0])));
			authenticationListCombo.setName(String.valueOf(obj[1]));
			if (null != screenList && screenList.size() > 0) {
				if (null != authenticationListCombo.getId() && screenList.contains(authenticationListCombo.getId())) {
					authenticationListCombo.setResult(true);
				} else {
					authenticationListCombo.setResult(false);
				}
			}
			if (null == authenticationListCombo.getResult()) {
				authenticationListCombo.setResult(false);
			}
			authenticationListComboList.add(authenticationListCombo);
		}
		screenAuthenticationMaster.setScreenComboList(authenticationListComboList);
		return screenAuthenticationMaster;
	}

	@SuppressWarnings("rawtypes")
	public ScreenAuthenticationMaster loadUserAndRoleName(ScreenAuthenticationMaster screenAuthenticationMaster) {
		// Load Users Based on Role
		if (null != screenAuthenticationMaster.getRoleId()) {

			List<Object> result = screenAssignmentDao.getRoleBasedUserList(screenAuthenticationMaster.getRoleId());
			UserMasterVo userMasterListDisplayVo = null;
			Iterator itr = result.iterator();
			String userList = null;
			while (itr.hasNext()) {
				Object[] obj = (Object[]) itr.next();

				userMasterListDisplayVo = new UserMasterVo();

				if (null != obj[0]) {
					userMasterListDisplayVo.setId(Integer.parseInt(String.valueOf(obj[0])));
				}
				if (null != obj[1]) {
					userMasterListDisplayVo.setUserLoginId(String.valueOf(obj[1]));
				}
				if (null != obj[2]) {
					userMasterListDisplayVo.setFirstName(String.valueOf(obj[2]));
				}
				if (null != obj[3]) {
					userMasterListDisplayVo.setLastName(String.valueOf(obj[3]));
				}
				if (null != userMasterListDisplayVo.getFirstName()) {
					if (null != userList) {
						userList = userList + " , " + userMasterListDisplayVo.getFirstName();
					} else {
						userList = userMasterListDisplayVo.getFirstName();
					}
				}

			}
			screenAuthenticationMaster.setUserList(userList);
			UserRoleEntity userRoleEntity = screenAssignmentDao.getRoleName(screenAuthenticationMaster.getRoleId());
			if (null != userRoleEntity && null != userRoleEntity.getUserRoleName()) {
				screenAuthenticationMaster.setRoleName(userRoleEntity.getUserRoleName());
			}
		}
		return screenAuthenticationMaster;
	}

	public ScreenAuthenticationMaster  getScreenFields(ScreenAuthorizationVO screenAuthorizationMaster,AuthDetailsVO authDetailsVO) {

		ScreenAuthenticationMaster  userMappingVo = new ScreenAuthenticationMaster();

		ScreenAuthorizationVO screenAuthorizationMasterVo = screenAssignmentDao.getScreenAuthorization(screenAuthorizationMaster,authDetailsVO);
		if (null != screenAuthorizationMasterVo) {

			// Get the Fields List
			userMappingVo.setScreenFieldDisplayVoList(screenAuthorizationMasterVo.getScreenFieldDisplayVoList());

			// Get the Functions & Side Tab List
			userMappingVo.setScreenFunctionDisplayList(screenAuthorizationMasterVo.getScreenFunctionDisplayList());

		} else {
			throw new CommonException(getMessage("noAuthorizationAvailableForThisUser"));

		}

		ScreenAuthenticationVO screenAuthenticationMaster = screenAssignmentDao.getScreenAuhentication(screenAuthorizationMaster,authDetailsVO);

		if (null != screenAuthenticationMaster) {
			userMappingVo.setScreenVoList(screenAuthenticationMaster.getScreenVoList());

		} else {
			throw new CommonException(getMessage("noScreenAvailableForThisUser"));

		}

		userMappingVo.setUserName(authDetailsVO.getUserName());

		return userMappingVo;
	}

}
