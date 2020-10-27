package com.kevamdg.sr.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevamdg.sr.config.CommonException;
import com.kevamdg.sr.constants.CommonConstant;
import com.kevamdg.sr.controller.CommonAction;
import com.kevamdg.sr.dao.UserRoleDAO;
import com.kevamdg.sr.entity.UserRoleEntity;
import com.kevamdg.sr.repository.UserRoleRepository;
import com.kevamdg.sr.vo.AuthDetailsVO;
import com.kevamdg.sr.vo.UserRoleVO;

@Service
public class UserRoleService extends CommonAction<UserRoleVO> {

	Logger logger = LoggerFactory.getLogger(UserRoleService.class);

	@Autowired
	UserRoleDAO userRoleDao;

	@Autowired
	private UserRoleRepository userRoleRepo;

	/**
	 * method to list userRole
	 * 
	 * @return userRoleList List<UserRoleVo>
	 * @throws CommonException
	 */
	@Transactional
	public List<UserRoleVO> getUserRoleList() throws CommonException {
		List<UserRoleVO> userRoleList = new ArrayList<UserRoleVO>();

		try {
			List<Object[]> list = userRoleRepo.getUserRoleList();

			for (Object[] object : list) {

				UserRoleVO userRoleMaster = new UserRoleVO();

				if (0 != (int) object[0]) {
					userRoleMaster.setId((int) object[0]);
				}
				if (null != (String) object[1]) {
					userRoleMaster.setUserRoleName((String) object[1]);
				}
				if (null != (String) object[2]) {
					userRoleMaster.setRoleTypeName((String) object[2]);
				}
				if (null != (String) object[3]) {
					userRoleMaster.setDescription((String) object[3]);
				}
				if (null != (String) object[4]) {
					userRoleMaster.setUserLocationName((String) object[4]);
				}
				if (null != (String) object[5]) {
					userRoleMaster.setSublocationName((String) object[5]);
				}
				if (null != (String) object[6]) {
					userRoleMaster.setUserDepartmentName((String) object[6]);
				}
				
				if (null != (Integer) object[7]) {
					userRoleMaster.setUserLocation((Integer) object[7]);
				}
				if (null != (Integer) object[8]) {
					userRoleMaster.setSublocationId((Integer) object[8]);
				}
				if (null != (Integer) object[8]) {
					userRoleMaster.setUserDepartment((Integer) object[8]);
				}

				userRoleList.add(userRoleMaster);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("dataFailure"));
		}
		return userRoleList;
	}

	@Transactional
	public void duplicateRole(UserRoleVO userRoleMaster) throws CommonException {

		try {
			int count = userRoleDao.duplicateRole(userRoleMaster);
			if (count > 0) {
				throw new CommonException(getMessage("roleAlreadyExists"));
			}
		} catch (CommonException e) {
			logger.error(e.getMessage());
			throw new CommonException(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("dataFailure"));
		}

	}

	/**
	 * method to add new userRole
	 * 
	 * @param saveUserRoleMaster
	 *            UserRoleVo
	 * @throws CommonException
	 */
	@Transactional
	public void saveUserRole(UserRoleVO saveUserRoleMaster,AuthDetailsVO authDetailsVo) throws CommonException {

		try {
			if (null != saveUserRoleMaster) {
				UserRoleEntity saveUserRoleEntity = new UserRoleEntity();

				BeanUtils.copyProperties(saveUserRoleMaster, saveUserRoleEntity);

				saveUserRoleEntity.setDeleteFlag(CommonConstant.BOOLEAN_FALSE);
				saveUserRoleEntity.setCreateBy(authDetailsVo.getUserId());
				saveUserRoleEntity.setCreateDate(CommonConstant.getCalenderDate());
				saveUserRoleEntity.setUpdateBy(authDetailsVo.getUserId());
				saveUserRoleEntity.setUpdateDate(CommonConstant.getCalenderDate());
				
				userRoleRepo.save(saveUserRoleEntity);

			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("dataFailure"));
		}

	}

	/**
	 * This method is used to update the user role.
	 * 
	 * @param updateUserRoleMaster
	 *            UserRoleVo
	 * @throws CommonException
	 */
	@Transactional
	public void updateUserRole(UserRoleVO updateUserRoleMaster,AuthDetailsVO authDetailsVo) throws CommonException {
		try {
			if (null != updateUserRoleMaster.getId()) {

				UserRoleEntity updateUserRoleEntity = userRoleRepo.findOne(updateUserRoleMaster.getId());

				if (null != updateUserRoleEntity) {

					BeanUtils.copyProperties(updateUserRoleMaster, updateUserRoleEntity);

					updateUserRoleEntity.setUpdateBy(authDetailsVo.getUserId());
					updateUserRoleEntity.setUpdateDate(CommonConstant.getCalenderDate());

					userRoleRepo.save(updateUserRoleEntity);

				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("dataFailure"));
		}

	}

	/**
	 * This method is used to delete userRole
	 * 
	 * @param deleteUserRoleMaster
	 *            UserRoleVo
	 * @throws CommonException
	 * 
	 */
	@Transactional
	public void deleteUserRole(UserRoleVO deleteUserRoleMaster,AuthDetailsVO authDetailsVo) throws CommonException {

		boolean userRole = false;

		List<String> codeList = new ArrayList<String>();

		try {
			Integer[] deleteItems = deleteUserRoleMaster.getDeleteItem();
			for (int i = 0; i < deleteItems.length; i++) {

				boolean Type = false;

				// Type = deleteCode(deleteItems[i]);

				UserRoleEntity userRoleEntity = userRoleRepo.findOne(deleteItems[i]);

				if (Type) {
					userRole = true;
					codeList.add(userRoleEntity.getUserRoleName());
					continue;
				}

				userRoleEntity.setDeleteFlag(CommonConstant.BOOLEAN_TRUE);
				userRoleEntity.setUpdateBy(authDetailsVo.getUserId());
				userRoleEntity.setUpdateDate(CommonConstant.getCalenderDate());

				userRoleRepo.save(userRoleEntity);
			}
		} catch (NoResultException e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("noResultFound"));
		} catch (NonUniqueResultException e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("noRecordFound"));
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("dataFailure"));
		}
		if (userRole) {
			throw new CommonException(CommonConstant.format(getMessage("unUsedRecordOnlyBeDeleted"), codeList));

		}

	}

	/**
	 * This method to search userRole
	 * 
	 * @param userMaster
	 *            UserRoleVo
	 * @return userRoleMasterListDisplayVoList List<UserRoleVo>
	 * @throws CommonException
	 */
	@Transactional
	public List<UserRoleVO> getGlobalSearchUserRole(UserRoleVO userMaster) throws CommonException {

		List<UserRoleVO> userRoleList = new ArrayList<UserRoleVO>();

		try {
			List<Object[]> list = userRoleDao.getGlobalSearchUserRole(userMaster);

			for (Object[] userRoleEntity : list) {

				UserRoleVO userRoleMaster = new UserRoleVO();

				if (0 != (int) ((Object[]) userRoleEntity)[0]) {
					userRoleMaster.setId((int) ((Object[]) userRoleEntity)[0]);
				}
				if (null != ((Object[]) userRoleEntity)[1]) {
					userRoleMaster.setUserRoleName((String) ((Object[]) userRoleEntity)[1]);
				}
				if (null != ((Object[]) userRoleEntity)[6]) {
					userRoleMaster.setRoleTypeName((String) ((Object[]) userRoleEntity)[6]);
				}
				if (null != ((Object[]) userRoleEntity)[5]) {
					userRoleMaster.setDescription((String) ((Object[]) userRoleEntity)[5]);
				}
				if (null != ((Object[]) userRoleEntity)[2]) {
					userRoleMaster.setUserLocationName((String) ((Object[]) userRoleEntity)[2]);
				}
				if (null != ((Object[]) userRoleEntity)[3]) {
					userRoleMaster.setSublocationName((String) ((Object[]) userRoleEntity)[3]);
				}
				if (null != ((Object[]) userRoleEntity)[4]) {
					userRoleMaster.setUserDepartmentName((String) ((Object[]) userRoleEntity)[4]);
				}

				userRoleList.add(userRoleMaster);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("dataFailure"));
		}

		return userRoleList;

	}

	/**
	 * This method to view userDepartment
	 * 
	 * @param id
	 *            int
	 * @return userRoleMstr UserRoleVo
	 * @throws CommonException 
	 */
	@Transactional
	public UserRoleVO viewUserRole(Integer integer) throws CommonException {

		UserRoleVO userRoleMstr = new UserRoleVO();

		Object[] obj = null;

		try {
			obj = userRoleDao.findId(integer);
		} catch (NoResultException e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("noResultFound"));
		} catch (NonUniqueResultException e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("noUniqueFound"));
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("dataFailure"));
		}

		if (0 != (int) ((Object[]) obj)[0]) {
			userRoleMstr.setId((int) ((Object[]) obj)[0]);
		}
		if (null != ((Object[]) obj)[1]) {
			userRoleMstr.setUserRoleName((String) ((Object[]) obj)[1]);
		}
		if (null != ((Object[]) obj)[6]) {
			userRoleMstr.setRoleTypeName((String) ((Object[]) obj)[6]);
		}
		if (null != ((Object[]) obj)[5]) {
			userRoleMstr.setDescription((String) ((Object[]) obj)[5]);
		}
		if (null != ((Object[]) obj)[2]) {
			userRoleMstr.setUserLocationName((String) ((Object[]) obj)[2]);
		}
		if (null != ((Object[]) obj)[3]) {
			userRoleMstr.setSublocationName((String) ((Object[]) obj)[3]);
		}
		if (null != ((Object[]) obj)[4]) {
			userRoleMstr.setUserDepartmentName((String) ((Object[]) obj)[4]);
		}
		if (0 != (int) ((Object[]) obj)[7]) {
			userRoleMstr.setUserLocation((int) ((Object[]) obj)[7]);
		}
		if (0 != (int) ((Object[]) obj)[8]) {
			userRoleMstr.setSublocationId((int) ((Object[]) obj)[8]);
		}
		if (0 != (int) ((Object[]) obj)[9]) {
			userRoleMstr.setUserDepartment((int) ((Object[]) obj)[9]);
		}
		if (0 != (int) ((Object[]) obj)[10]) {
			userRoleMstr.setRoleType((int) ((Object[]) obj)[10]);
		}

		return userRoleMstr;

	}

}
