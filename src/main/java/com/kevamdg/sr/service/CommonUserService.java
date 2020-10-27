package com.kevamdg.sr.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kevamdg.sr.config.CommonException;
import com.kevamdg.sr.constants.CodeSecurity;
import com.kevamdg.sr.constants.CommonConstant;
import com.kevamdg.sr.controller.CommonAction;
import com.kevamdg.sr.dao.UserMasterDao;
import com.kevamdg.sr.entity.UserEntity;
import com.kevamdg.sr.entity.UserMappingModuleEntity;
import com.kevamdg.sr.repository.UserMappingModuleRepository;
import com.kevamdg.sr.repository.UserRepository;
import com.kevamdg.sr.vo.AuthDetailsVO;
import com.kevamdg.sr.vo.UserMappingModuleVO;
import com.kevamdg.sr.vo.UserVO;

@Service("commonUserService")
public class CommonUserService extends CommonAction<UserVO> {

	Logger logger = LoggerFactory.getLogger(CommonUserService.class);

	@Autowired
	private UserMasterDao userDao;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private UserMappingModuleRepository userMappingModuleRepo;

	/**
	 * Method is used to Get all the user details (only active and non delete
	 * user).
	 * 
	 * @return userMasterVo List<UserVO>
	 * @throws CommonException
	 */
	@Transactional
	public List<UserVO> getAll() throws CommonException {

		List<UserVO> listUserMasterVo = new ArrayList<UserVO>();

		List<Object[]> userList = null;

		// To get all the user Details from DB
		try {

			userList = userDao.getAll();

		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("dbFailure"));
		}

		// Set all the fields of user details
		if (userList != null && userList.size() > 0) {

			try {

				listUserMasterVo = getAllList(userList);

			} catch (Exception e) {
				logger.error(e.getMessage());
				throw new CommonException(e.getMessage());
			}

		}
		return listUserMasterVo;

	}

	/**
	 * Method is used to Get all list the user details.
	 * 
	 * @param userList
	 *            List<UserEntity>
	 * @return userMasterVoList List<UserVO>
	 * @throws CommonException
	 */
	public List<UserVO> getAllList(List<Object[]> userList) throws CommonException {

		List<UserVO> userMasterVoList = new ArrayList<UserVO>();
		for (Object[] userEntity : userList) {

			String decryptedPassword = null;

			UserVO userMasterVo = new UserVO();

			/*
			 * if (null != (String) ((Object[]) userEntity)[6]) { // Password
			 * decryption try { decryptedPassword =
			 * CodeSecurity.decrypt((String) ((Object[]) userEntity)[6]); }
			 * catch (Exception e) { logger.error(e.getMessage()); throw new
			 * CommonException(getMessage("passwordEncription")); } }
			 */
			if (0 != (int) ((Object[]) userEntity)[0]) {
				userMasterVo.setId((int) ((Object[]) userEntity)[0]);
			}
			if (null != ((Object[]) userEntity)[1]) {
				userMasterVo.setUserLoginId((String) ((Object[]) userEntity)[1]);
			}
			if (null != ((Object[]) userEntity)[2]) {
				userMasterVo.setUserEmployeeId((String) ((Object[]) userEntity)[2]);
			}

			if (null != decryptedPassword) {
				userMasterVo.setPassword(decryptedPassword);
			}

			if (null != ((Object[]) userEntity)[3]) {
				userMasterVo.setFirstName((String) ((Object[]) userEntity)[3]);
			}

			if (null != ((Object[]) userEntity)[4]) {
				userMasterVo.setMiddleName((String) ((Object[]) userEntity)[4]);
			}

			if (null != ((Object[]) userEntity)[5]) {
				userMasterVo.setLastName((String) ((Object[]) userEntity)[5]);
			}

			if (null != ((Object[]) userEntity)[7]) {
				userMasterVo.setEmailId((String) ((Object[]) userEntity)[7]);
			}

			if (null != ((Object[]) userEntity)[8]) {
				userMasterVo.setPhoneNumber((String) ((Object[]) userEntity)[8]);
			}

			if (null != ((Object[]) userEntity)[9]) {
				userMasterVo.setMobile((String) ((Object[]) userEntity)[9]);
			}

			if (null != ((Object[]) userEntity)[10]) {
				userMasterVo.setUserLocationName((String) ((Object[]) userEntity)[10]);
			}

			if (null != ((Object[]) userEntity)[11]) {
				userMasterVo.setSubLocationName((String) ((Object[]) userEntity)[11]);
			}

			if (null != ((Object[]) userEntity)[12]) {
				userMasterVo.setUserDepartmentName((String) ((Object[]) userEntity)[12]);
			}

			if (null != ((Object[]) userEntity)[13]) {
				userMasterVo.setUserRoleName((String) ((Object[]) userEntity)[13]);
			}

			userMasterVoList.add(userMasterVo);
		}
		return userMasterVoList;
	}

	/**
	 * Method is used to create the user details
	 * 
	 * @param userMasterVo
	 *            UserMasterVo
	 * @throws Exception
	 */
	@Transactional
	public void create(UserVO userMasterVo, AuthDetailsVO authDetailsVo) throws Exception {

		UserEntity userEntity = new UserEntity();
		String encryptedPassword = null;
		List<UserEntity> userEntityList = null;

		// Set all the fields of user details to create

		// userMasterVo.setPassword(passwordGenerator());
		userEntity.setUserEmployeeId(userMasterVo.getUserEmployeeId());
		userEntity.setFirstName(userMasterVo.getFirstName());
		userEntity.setMiddleName(userMasterVo.getMiddleName());
		userEntity.setLastName(userMasterVo.getLastName());

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		encryptedPassword = passwordEncoder.encode(userMasterVo.getPassword());
		
		String password = null;
		
		try {
			password = CodeSecurity.encrypt(userMasterVo.getPassword());
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("passwordEncription"));
		}
		userEntity.setBase64Password(password);
		userEntity.setPassword(encryptedPassword);
		userEntity.setEmailId(userMasterVo.getEmailId());
		userEntity.setPhoneNumber(userMasterVo.getPhoneNumber());
		userEntity.setMobile(userMasterVo.getMobile());
		userEntity.setCurrentAddress(userMasterVo.getCurrentAddress());
		userEntity.setPermanentAddress(userMasterVo.getPermanentAddress());
		userEntity.setLocationId(userMasterVo.getUserLocation());
		userEntity.setSublocationId(userMasterVo.getSubLocation());
		userEntity.setDepartmentId(userMasterVo.getUserDepartment());
		userEntity.setRoleId(userMasterVo.getUserRole());
		if (null != userMasterVo.getDivision()) {
			userEntity.setDivisionId(userMasterVo.getDivision());
		}

		try {

			userEntityList = userDao.uniqueUserLoginId(userMasterVo.getUserLoginId());

		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("dbFailure"));
		}

		if (userEntityList == null || userEntityList.size() == 0) {
			userEntity.setUserName(userMasterVo.getUserLoginId());
		} else {
			throw new CommonException(getMessage("uniqueLoginId"));
		}

		userEntity.setDeleteFlag(CommonConstant.BOOLEAN_FALSE);
		userEntity.setActiveFlag(userMasterVo.isActiveFlag());
		userEntity.setCreateBy(authDetailsVo.getUserId());
		userEntity.setCreateDate(CommonConstant.getCalenderDate());
		userEntity.setUpdateBy(authDetailsVo.getUserId());
		userEntity.setUpdateDate(CommonConstant.getCalenderDate());

		// Create new user
		try {
			userRepo.save(userEntity);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("dataFailure"));
		}

		if (!userMasterVo.getUserMappingModuleList().isEmpty() && userMasterVo.getUserMappingModuleList().size() > 0) {

			for (UserMappingModuleVO userMappingModuleVO : userMasterVo.getUserMappingModuleList()) {

				UserMappingModuleEntity userMappingModuleEntity = new UserMappingModuleEntity();

				userMappingModuleEntity.setModuleId(userMappingModuleVO.getModuleId());
				userMappingModuleEntity.setUserId(userEntity.getId());
				userMappingModuleEntity.setActiveFlag(CommonConstant.BOOLEAN_TRUE);
				userMappingModuleEntity.setCreateBy(authDetailsVo.getUserId());
				userMappingModuleEntity.setCreateDate(CommonConstant.getCalenderDate());
				userMappingModuleEntity.setUpdateBy(authDetailsVo.getUserId());
				userMappingModuleEntity.setUpdateDate(CommonConstant.getCalenderDate());

				userMappingModuleRepo.save(userMappingModuleEntity);
			}

		}
		
		userDao.roleMapping(userEntity, authDetailsVo);
		
	}

	/**
	 * Method is used to update the user details
	 * 
	 * @param userMasterVo
	 *            UserMasterVo
	 * @throws CommonException
	 */
	@Transactional
	public void update(UserVO userMasterVo, AuthDetailsVO authDetailsVo) throws CommonException {

		UserEntity userEntity = new UserEntity();
		String encryptedPassword = null;

		// Get the data from DB using user ID
		try {

			userEntity = userRepo.findOne(userMasterVo.getId());

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

		// To set all the fields in entity

		if (userEntity != null) {

			// try {
			// encryptedPassword =
			// CodeSecurity.encrypt(userMasterVo.getPassword());
			// } catch (Exception e) {
			// logger.error(e.getMessage());
			// throw new CommonException(getMessage("passwordEncription"));
			// }
			//
			// userEntity.setPassword(encryptedPassword);
			//
			userEntity.setUserEmployeeId(userMasterVo.getUserEmployeeId());
			userEntity.setFirstName(userMasterVo.getFirstName());
			userEntity.setMiddleName(userMasterVo.getMiddleName());
			userEntity.setLastName(userMasterVo.getLastName());
			userEntity.setEmailId(userMasterVo.getEmailId());
			userEntity.setPhoneNumber(userMasterVo.getPhoneNumber());
			userEntity.setMobile(userMasterVo.getMobile());
			userEntity.setCurrentAddress(userMasterVo.getCurrentAddress());
			userEntity.setPermanentAddress(userMasterVo.getPermanentAddress());
			userEntity.setUserName(userMasterVo.getUserLoginId());
			userEntity.setLocationId(userMasterVo.getUserLocation());
			userEntity.setSublocationId(userMasterVo.getSubLocation());
			userEntity.setDepartmentId(userMasterVo.getUserDepartment());
			userEntity.setRoleId(userMasterVo.getUserRole());
			if (null != userMasterVo.getDivision()) {
				userEntity.setDivisionId(userMasterVo.getDivision());
			}

			userEntity.setActiveFlag(userMasterVo.isActiveFlag());
			userEntity.setUpdateBy(authDetailsVo.getUserId());
			userEntity.setUpdateDate(CommonConstant.getCalenderDate());
		}

		// Update the User details
		try {
			userRepo.save(userEntity);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("dataFailure"));
		}

		if (!userMasterVo.getUserMappingModuleList().isEmpty() && userMasterVo.getUserMappingModuleList().size() > 0) {

			for (UserMappingModuleVO userMappingModuleVO : userMasterVo.getUserMappingModuleList()) {

				UserMappingModuleEntity userMappingModuleEntity = new UserMappingModuleEntity();
				if (null != userMappingModuleVO.getId()) {
					userMappingModuleEntity.setId(userMappingModuleVO.getId());
				}
				userMappingModuleEntity.setModuleId(userMappingModuleVO.getModuleId());
				userMappingModuleEntity.setUserId(userEntity.getId());
				userMappingModuleEntity.setActiveFlag(userMappingModuleVO.isActiveFlag());
				userMappingModuleEntity.setCreateBy(authDetailsVo.getUserId());
				userMappingModuleEntity.setCreateDate(CommonConstant.getCalenderDate());
				userMappingModuleEntity.setUpdateBy(authDetailsVo.getUserId());
				userMappingModuleEntity.setUpdateDate(CommonConstant.getCalenderDate());

				userMappingModuleRepo.save(userMappingModuleEntity);
			}

		}
		
		Integer roleMappingId = userDao.role(userEntity);
		
		if(null != roleMappingId){
			userDao.roleMappingUpdate(userEntity, authDetailsVo,roleMappingId);
		}else{
			userDao.roleMapping(userEntity, authDetailsVo);
		}

	}

	/**
	 * Method is used to delete the user detail
	 * 
	 * @param userMasterVo
	 *            UserMasterVo
	 * @throws CommonException
	 */
	@Transactional
	public void delete(UserVO userMasterVo, AuthDetailsVO authDetailsVo) throws CommonException {

		for (Integer id : userMasterVo.getDeleteItem()) {

			UserEntity userEntity = new UserEntity();

			// Get the data from DB using the user Id
			try {

				userEntity = userRepo.findOne(id);

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

			// Set the fields and Update for delete the Record
			try {

				if (userEntity != null) {
					userEntity.setDeleteFlag(CommonConstant.BOOLEAN_TRUE);
					userEntity.setUpdateBy(authDetailsVo.getUserId());
					userEntity.setUpdateDate(CommonConstant.getCalenderDate());
					userRepo.save(userEntity);
				}

			} catch (Exception e) {
				logger.error(e.getMessage());
				throw new CommonException(getMessage("dataFailure"));
			}
		}

	}

	/**
	 * Method is used to Load of user detail
	 * 
	 * @param userMasterVo
	 *            UserMasterVo
	 * @return userMasterVo UserMasterVo
	 * @throws CommonException
	 */
	@Transactional
	public UserVO load(int id) throws CommonException {

		UserVO userMasterVo = new UserVO();

		Object[] userEntity = null;

		String decryptedPassword = null;

		// Get the data from DB using the User Id
		try {

			userEntity = userDao.findId(id);

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

		// Password decryption

		/*try {
			decryptedPassword = CodeSecurity.decrypt((String) ((Object[]) userEntity)[6]);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("passwordEncription"));
		}*/

		// Set all the fields

		if (userEntity != null) {

			if (0 != (int) ((Object[]) userEntity)[0]) {
				userMasterVo.setId((int) ((Object[]) userEntity)[0]);
			}
			if (null != ((Object[]) userEntity)[1]) {
				userMasterVo.setUserLoginId((String) ((Object[]) userEntity)[1]);
			}
			if (null != ((Object[]) userEntity)[2]) {
				userMasterVo.setUserEmployeeId((String) ((Object[]) userEntity)[2]);
			}

			/*if (null != decryptedPassword) {
				userMasterVo.setPassword(decryptedPassword);
			}*/

			if (null != ((Object[]) userEntity)[3]) {
				userMasterVo.setFirstName((String) ((Object[]) userEntity)[3]);
			}

			if (null != ((Object[]) userEntity)[4]) {
				userMasterVo.setMiddleName((String) ((Object[]) userEntity)[4]);
			}

			if (null != ((Object[]) userEntity)[5]) {
				userMasterVo.setLastName((String) ((Object[]) userEntity)[5]);
			}

			if (null != ((Object[]) userEntity)[7]) {
				userMasterVo.setEmailId((String) ((Object[]) userEntity)[7]);
			}

			if (null != ((Object[]) userEntity)[8]) {
				userMasterVo.setPhoneNumber((String) ((Object[]) userEntity)[8]);
			}

			if (null != ((Object[]) userEntity)[9]) {
				userMasterVo.setMobile((String) ((Object[]) userEntity)[9]);
			}

			if (null != ((Object[]) userEntity)[10]) {
				userMasterVo.setUserLocationName((String) ((Object[]) userEntity)[10]);
			}

			if (null != ((Object[]) userEntity)[11]) {
				userMasterVo.setSubLocationName((String) ((Object[]) userEntity)[11]);
			}

			if (null != ((Object[]) userEntity)[12]) {
				userMasterVo.setUserDepartmentName((String) ((Object[]) userEntity)[12]);
			}

			if (null != ((Object[]) userEntity)[13]) {
				userMasterVo.setUserRoleName((String) ((Object[]) userEntity)[13]);
			}
			if (null != ((Object[]) userEntity)[14]) {
				userMasterVo.setUserLocation((int) ((Object[]) userEntity)[14]);
			}
			if (null != ((Object[]) userEntity)[15]) {
				userMasterVo.setSubLocation((int) ((Object[]) userEntity)[15]);
			}
			if (null != ((Object[]) userEntity)[16]) {
				userMasterVo.setUserDepartment((int) ((Object[]) userEntity)[16]);
			}
			if (null != ((Object[]) userEntity)[17]) {
				userMasterVo.setUserRole((int) ((Object[]) userEntity)[17]);
			}
			if (null != ((Object[]) userEntity)[18]) {
				userMasterVo.setCurrentAddress((String) ((Object[]) userEntity)[18]);
			}
			if (null != ((Object[]) userEntity)[19]) {
				userMasterVo.setPermanentAddress((String) ((Object[]) userEntity)[19]);
			}
		}

		List<Object[]> userMappingModuleEntityList = userDao.getModuleList(id);

		List<UserMappingModuleVO> userMappingModuleVOList = new ArrayList<>();

		for (Object[] userMappingModuleEntity : userMappingModuleEntityList) {

			UserMappingModuleVO userMappingModuleVO = new UserMappingModuleVO();
			if (null != ((Object[]) userMappingModuleEntity)[0]) {
				userMappingModuleVO.setId((Integer) ((Object[]) userMappingModuleEntity)[0]);
			}
			if (null != ((Object[]) userMappingModuleEntity)[2]) {
				userMappingModuleVO.setModuleId((Integer) ((Object[]) userMappingModuleEntity)[2]);
			}
			if (null != ((Object[]) userMappingModuleEntity)[1]) {
				userMappingModuleVO.setUserId((Integer) ((Object[]) userMappingModuleEntity)[1]);
			}
			if (null != ((Object[]) userMappingModuleEntity)[3]) {
				userMappingModuleVO.setActiveFlag((boolean) ((Object[]) userMappingModuleEntity)[3]);
			}
			if (null != ((Object[]) userMappingModuleEntity)[4]) {
				userMappingModuleVO.setModuleName((String) ((Object[]) userMappingModuleEntity)[4]);
			}

			userMappingModuleVOList.add(userMappingModuleVO);

		}
		userMasterVo.setUserMappingModuleList(userMappingModuleVOList);

		return userMasterVo;

	}

	/**
	 * Method is to search the user
	 * 
	 * @param userMasterVo
	 *            UserMasterVo
	 * @return userMasterVoList List<UserMasterVo>
	 * @throws CommonException
	 */
	@Transactional
	public List<UserVO> getAllSearch(UserVO userMasterVo) throws CommonException {

		List<UserVO> userMasterVoList = new ArrayList<UserVO>();

		List<Object[]> userList = null;

		// Get and Search the date from DB.
		try {

			userList = userDao.getAllSearch(userMasterVo);

		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("dbFailure"));
		}

		// Set all the fields
		try {

			if (userList != null && userList.size() > 0) {

				userMasterVoList = getAllList(userList);

			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("copyFailure"));
		}

		return userMasterVoList;

	}

}
