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
import org.springframework.stereotype.Component;

import com.kevamdg.sr.config.CommonException;
import com.kevamdg.sr.constants.CommonConstant;
import com.kevamdg.sr.controller.CommonAction;
import com.kevamdg.sr.dao.UserMappingDao;
import com.kevamdg.sr.entity.CommonStorageEntity;
import com.kevamdg.sr.entity.DepartmentEntity;
import com.kevamdg.sr.entity.LocationEntity;
import com.kevamdg.sr.entity.SubLocationEntity;
import com.kevamdg.sr.entity.UserEntity;
import com.kevamdg.sr.entity.UserMappingEntity;
import com.kevamdg.sr.entity.UserRoleEntity;
import com.kevamdg.sr.vo.ScreenAuthenticationVO;
import com.kevamdg.sr.vo.ScreenAuthorizationVO;
import com.kevamdg.sr.vo.UserMappingVo;

/**
 * This service class is used to Create , Update , Delete , Get all , Get all
 * search , Load the user mapping.
 * 
 * @author Sai
 *
 */

@Component
public class UserMappingService extends CommonAction<UserMappingVo> {

	Logger logger = LoggerFactory.getLogger(UserMappingService.class);

	@Autowired
	private UserMappingDao userMappingDao;
	
	

	
	/**
	 * This Method is to get all the details.
	 * 
	 * @return userMappingVoList List<userMappingVo>
	 *
	 */

	@Transactional
	public List<UserMappingVo> getAll() {
		try {
			List<UserMappingVo> userMappingVoList = new ArrayList<UserMappingVo>();
			List<Object[]> userMappingEntityList = null;

			userMappingEntityList = userMappingDao.getAll();
			
			if(userMappingEntityList != null && !userMappingEntityList.isEmpty()){
			userMappingVoList = getAllList(userMappingEntityList);
			}
			return userMappingVoList;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("dataFailure"));
		}
	}

	/**
	 * method is to get all the detailsList.
	 * 
	 * @param userMappingEntityList
	 * @return userMappingVoList
	 * 
	 */
	public List<UserMappingVo> getAllList(List<Object[]> userMappingEntityList) {

		List<UserMappingVo> userMappingVoList = new ArrayList<UserMappingVo>();
		for (Object userMappingEntity : userMappingEntityList) {
			UserMappingVo userMappingVo = new UserMappingVo();

			if (0 != (int) ((Object[]) userMappingEntity)[0]) {
				userMappingVo.setUserMappingId((int) ((Object[]) userMappingEntity)[0]);
			}

			if (0 != (int) ((Object[]) userMappingEntity)[1]) {
				userMappingVo.setUserLocationId((int) ((Object[]) userMappingEntity)[1]);
			}
			
			if (null != (String) ((Object[]) userMappingEntity)[2]) {
				userMappingVo.setUserLocationName((String) ((Object[]) userMappingEntity)[2]);
			}

			if (0 != (int) ((Object[]) userMappingEntity)[3]) {
				
				userMappingVo.setSubLocationId((int) ((Object[]) userMappingEntity)[3]);
			}
			if (null != (String) ((Object[]) userMappingEntity)[4]) {
				
				userMappingVo.setSubLocationName((String) ((Object[]) userMappingEntity)[4]);
			}
			
			

			if (0 != (int) ((Object[]) userMappingEntity)[5]) {
				userMappingVo.setUserDepartmentId((int) ((Object[]) userMappingEntity)[5]);
			}
			
			if (null != (String) ((Object[]) userMappingEntity)[6]) {
				userMappingVo
						.setUserDepartmentName((String) ((Object[]) userMappingEntity)[6]);
			}
			
			if (0 != (int) ((Object[]) userMappingEntity)[7]) {
				userMappingVo.setUserRoleId((int) ((Object[]) userMappingEntity)[7]);
			}
			
			if (null != (String) ((Object[]) userMappingEntity)[8]) {
				userMappingVo.setUserRoleName((String) ((Object[]) userMappingEntity)[8]);
			}
			
			if (0 != (int) ((Object[]) userMappingEntity)[9]) {
				userMappingVo.setLevelId((int) ((Object[]) userMappingEntity)[9]);
			}
			if (null != (String) ((Object[]) userMappingEntity)[10]) {
				userMappingVo.setLevelName((String) ((Object[]) userMappingEntity)[10]);
			}
			
			if (0 != (int) ((Object[]) userMappingEntity)[11]) {
				userMappingVo.setReportingLocationId((int) ((Object[]) userMappingEntity)[11]);
			}
			
			if (0 != (int) ((Object[]) userMappingEntity)[12]) {
				userMappingVo.setReportingSubLocationId((int) ((Object[]) userMappingEntity)[12]);
			}

			if (0 != (int) ((Object[]) userMappingEntity)[13]) {
				userMappingVo.setReportingUserDepartment((int) ((Object[]) userMappingEntity)[13]);
			}
			if (0 != (int) ((Object[]) userMappingEntity)[14]) {
				userMappingVo.setReportingToUser((int) ((Object[]) userMappingEntity)[14]);
			}
			if (0 != (int) ((Object[]) userMappingEntity)[15]) {
				userMappingVo.setUserId((int) ((Object[]) userMappingEntity)[15]);
			}
			if (null != (String) ((Object[]) userMappingEntity)[16]) {
				
					userMappingVo.setUserName((String) ((Object[]) userMappingEntity)[16]);
			
			}
			if (null != (String) ((Object[]) userMappingEntity)[17]){
				userMappingVo.setReportingLocationName((String) ((Object[]) userMappingEntity)[17]);
			}
			if (null != (String) ((Object[]) userMappingEntity)[18]){
				userMappingVo.setReportingSubLocationName((String) ((Object[]) userMappingEntity)[18]);
			}
			if (null != (String) ((Object[]) userMappingEntity)[19]){
				userMappingVo.setReportingDepartmentName((String) ((Object[]) userMappingEntity)[19]);
			}
			if (null != (String) ((Object[]) userMappingEntity)[20]){
				userMappingVo.setReportingToUserName((String) ((Object[]) userMappingEntity)[20]);
			}
			
			userMappingVoList.add(userMappingVo);
		}
		return userMappingVoList;
	}

	/**
	 * Method used to Create user mapping.
	 * 
	 * @param userMappingVo
	 *            UserMappingVo
	 * 
	 */

	@Transactional
	public void create(UserMappingVo userMappingVo) {
		try {

			UserMappingEntity userMappingEntity = new UserMappingEntity();

			LocationEntity userLocationEntity = new LocationEntity();
			userLocationEntity.setId(userMappingVo.getUserLocationId());
			userMappingEntity.setUserLocationEntity(userLocationEntity);
			
			SubLocationEntity subLocationEntity = new SubLocationEntity();
			subLocationEntity.setSublocationId(userMappingVo.getSubLocationId());
			subLocationEntity.setSubLocationName(userMappingVo.getSubLocationName());
			userMappingEntity.setSubLocationEntity(subLocationEntity);
			
			LocationEntity reportingLocationEntity = new LocationEntity();
			reportingLocationEntity.setId(userMappingVo.getReportingLocationId());
			reportingLocationEntity.setUserLocationName(userMappingVo.getReportingLocationName());
			userMappingEntity.setReportingLocationEntity(reportingLocationEntity);
			
			
			SubLocationEntity reportingSubLocationEntity = new SubLocationEntity();
			reportingSubLocationEntity.setSublocationId(userMappingVo.getReportingSubLocationId());
			reportingSubLocationEntity.setSubLocationName(userMappingVo.getReportingSubLocationName());
			userMappingEntity.setReportingSublocationEntity(reportingSubLocationEntity);

			UserEntity userEntity = new UserEntity();
			userEntity.setId(userMappingVo.getUserId());
			userMappingEntity.setUserEntity(userEntity);

			DepartmentEntity userDepartmentEntity = new DepartmentEntity();
			userDepartmentEntity.setDepartmentId(userMappingVo.getUserDepartmentId());
			userMappingEntity.setUserDepartmentEntity(userDepartmentEntity);

			UserRoleEntity userRoleEntity = new UserRoleEntity();
			userRoleEntity.setId(userMappingVo.getUserRoleId());
			userMappingEntity.setUserRoleEntity(userRoleEntity);

			DepartmentEntity userDepartmentEntity1 = new DepartmentEntity();
			userDepartmentEntity1.setDepartmentId(userMappingVo.getReportingUserDepartment());
			userMappingEntity.setReportingUserDepartmentEntity(userDepartmentEntity1);

			UserEntity userEntity1 = new UserEntity();
			userEntity1.setId(userMappingVo.getReportingToUser());
			userMappingEntity.setReportingToUser(userEntity1);

			CommonStorageEntity commonStorageEntity = new CommonStorageEntity();
			commonStorageEntity.setCommonId(userMappingVo.getLevelId());
			userMappingEntity.setLevel(commonStorageEntity);

			userMappingEntity.setDeleteFlag(CommonConstant.FLAG_ZERO);

			userMappingEntity.setCreatedDate(CommonConstant.getCalenderDate());

			userMappingEntity.setUpdatedDate(CommonConstant.getCalenderDate());

			userMappingEntity.setCreatedBy(userMappingVo.getKioskAuthDetailsVO().getUserId());

			userMappingEntity.setUpdatedBy(userMappingVo.getKioskAuthDetailsVO().getUserId());

			userMappingDao.create(userMappingEntity);

		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("dataFailure"));
		}

	}

	/**
	 * Method used to update user mapping.
	 * 
	 * @param userMappingVo
	 *            UserMappingVo
	 * 
	 */

	@Transactional
	public void update(UserMappingVo userMappingVo) {
		try {

			UserMappingEntity userMappingEntity = new UserMappingEntity();

			userMappingEntity = findId(userMappingVo.getUserMappingId());

			userMappingEntity.setUserMappingId(userMappingVo.getUserMappingId());

			LocationEntity userLocationEntity = new LocationEntity();
			userLocationEntity.setId(userMappingVo.getUserLocationId());
			userMappingEntity.setUserLocationEntity(userLocationEntity);

			UserEntity userEntity = new UserEntity();
			userEntity.setId(userMappingVo.getUserId());
			userMappingEntity.setUserEntity(userEntity);

			DepartmentEntity userDepartmentEntity = new DepartmentEntity();
			userDepartmentEntity.setDepartmentId(userMappingVo.getUserDepartmentId());
			userMappingEntity.setUserDepartmentEntity(userDepartmentEntity);

			UserRoleEntity userRoleEntity = new UserRoleEntity();
			userRoleEntity.setId(userMappingVo.getUserRoleId());
			userMappingEntity.setUserRoleEntity(userRoleEntity);

			DepartmentEntity userDepartmentEntity1 = new DepartmentEntity();
			userDepartmentEntity1.setDepartmentId(userMappingVo.getReportingUserDepartment());
			userMappingEntity.setReportingUserDepartmentEntity(userDepartmentEntity1);
			
			
			SubLocationEntity subLocationEntity = new SubLocationEntity();
			subLocationEntity.setSublocationId(userMappingVo.getSubLocationId());
			subLocationEntity.setSubLocationName(userMappingVo.getSubLocationName());
			userMappingEntity.setSubLocationEntity(subLocationEntity);
			
			LocationEntity reportingLocationEntity = new LocationEntity();
			reportingLocationEntity.setId(userMappingVo.getReportingLocationId());
			reportingLocationEntity.setUserLocationName(userMappingVo.getReportingLocationName());
			userMappingEntity.setReportingLocationEntity(reportingLocationEntity);
			
			
			SubLocationEntity reportingSubLocationEntity = new SubLocationEntity();
			reportingSubLocationEntity.setSublocationId(userMappingVo.getReportingSubLocationId());
			reportingSubLocationEntity.setSubLocationName(userMappingVo.getReportingSubLocationName());
			userMappingEntity.setReportingSublocationEntity(reportingSubLocationEntity);

			UserEntity userEntity1 = new UserEntity();
			userEntity1.setId(userMappingVo.getReportingToUser());
			userMappingEntity.setReportingToUser(userEntity1);

			userMappingEntity.setDeleteFlag(CommonConstant.FLAG_ZERO);

			CommonStorageEntity commonStorageEntity = new CommonStorageEntity();
			commonStorageEntity.setCommonId(userMappingVo.getLevelId());
			userMappingEntity.setLevel(commonStorageEntity);

			userMappingEntity.setUpdatedDate(CommonConstant.getCalenderDate());

			userMappingEntity.setUpdatedBy(userMappingVo.getKioskAuthDetailsVO().getUserId());

			userMappingDao.update(userMappingEntity);

		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("dataFailure"));
		}

	}

	/**
	 * Method used to find id in user mapping.
	 * 
	 * @param userMappingVo
	 *            UserMappingVo
	 * 
	 */
	@Transactional
	private UserMappingEntity findId(Integer id) {

		try {
			UserMappingEntity userMappingEntity = null;
			userMappingEntity = userMappingDao.findId(id);
			return userMappingEntity;
		} catch (NoResultException e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("noRecordFound"));
		} catch (NonUniqueResultException e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("noRecordFound"));
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("dataFailure"));
		}

	}

	/**
	 * Method used to load user mapping.
	 * 
	 * @param userMappingVo
	 *            UserMappingVo
	 * 
	 */

	@Transactional
	public UserMappingVo load(UserMappingVo userMapping) {

		try {
			UserMappingEntity userMappingEntity = new UserMappingEntity();

			userMappingEntity = findId(userMapping.getUserMappingId());

			UserMappingVo usermappingVo = new UserMappingVo();

			BeanUtils.copyProperties(userMapping, usermappingVo);

			if (null != userMappingEntity.getUserMappingId()) {
				usermappingVo.setUserMappingId(userMappingEntity.getUserMappingId());
			}

			if (null != userMappingEntity.getUserLocationEntity().getId()) {
				usermappingVo.setUserLocationId(userMappingEntity.getUserLocationEntity().getId());
			}

			if (null != userMappingEntity.getUserRoleEntity().getId()) {
				usermappingVo.setUserRoleId(userMappingEntity.getUserRoleEntity().getId());
			}

			if (null != userMappingEntity.getUserDepartmentEntity().getDepartmentId()) {
				usermappingVo.setUserDepartmentId(userMappingEntity.getUserDepartmentEntity().getDepartmentId());
			}

			if (null != userMappingEntity.getLevel()) {
				usermappingVo.setLevelId(userMappingEntity.getLevel().getCommonId());
			}
			if (null != userMappingEntity.getLevel()) {
				usermappingVo.setLevelName(userMappingEntity.getLevel().getItemValue());
			}
			if (null != userMappingEntity.getReportingUserDepartmentEntity()) {
				usermappingVo.setReportingUserDepartment(userMappingEntity.getReportingUserDepartmentEntity().getDepartmentId());
			}
			if (null != userMappingEntity.getReportingUserDepartmentEntity()) {
				usermappingVo.setReportingDepartmentName(
						userMappingEntity.getReportingUserDepartmentEntity().getDepartmentName());
			}

			if (null != userMappingEntity.getReportingToUser()) {
				usermappingVo.setReportingToUser(userMappingEntity.getReportingToUser().getId());
			}
			if (null != userMappingEntity.getUserRoleEntity()
					&& null != userMappingEntity.getUserRoleEntity().getUserRoleName()) {
				usermappingVo.setUserRoleName(userMappingEntity.getUserRoleEntity().getUserRoleName());
			}

			if (null != userMappingEntity.getUserDepartmentEntity()
					&& null != userMappingEntity.getUserDepartmentEntity().getDepartmentName()) {
				usermappingVo
						.setUserDepartmentName(userMappingEntity.getUserDepartmentEntity().getDepartmentName());
			}

			usermappingVo.setUserLocationId(userMappingEntity.getUserLocationEntity().getId());
			if (null != userMappingEntity.getUserLocationEntity()
					&& null != userMappingEntity.getUserLocationEntity().getUserLocationName()) {
				usermappingVo.setUserLocationName(userMappingEntity.getUserLocationEntity().getUserLocationName());
			}
			
			usermappingVo.setSubLocationId(userMappingEntity.getSubLocationEntity().getSublocationId());
			
			
			if (null != userMappingEntity.getSubLocationEntity()
					&& null != userMappingEntity.getSubLocationEntity().getSubLocationName()) {
				usermappingVo.setSubLocationName(userMappingEntity.getSubLocationEntity().getSubLocationName());
			}
			usermappingVo.setReportingLocationId(userMappingEntity.getReportingLocationEntity().getId());
			
			usermappingVo.setReportingLocationId(userMappingEntity.getReportingLocationEntity().getId());
			if (null != userMappingEntity.getReportingLocationEntity()
					&& null != userMappingEntity.getReportingLocationEntity().getUserLocationName()) {
				usermappingVo.setReportingLocationName(userMappingEntity.getReportingLocationEntity().getUserLocationName());
			}
			
			
			usermappingVo.setReportingSubLocationId(userMappingEntity.getReportingSublocationEntity().getSublocationId());
			
			if (null != userMappingEntity.getReportingSublocationEntity()
					&& null != userMappingEntity.getReportingSublocationEntity().getSubLocationName()) {
				usermappingVo.setReportingSubLocationName(userMappingEntity.getReportingSublocationEntity().getSubLocationName());
			}
			
			
			if (null != userMappingEntity.getReportingToUser()) {
				usermappingVo.setReportingToUserName(userMappingEntity.getReportingToUser().getFirstName());
			}

			if (null != userMappingEntity.getUserEntity()) {
				usermappingVo.setUserName(userMappingEntity.getUserEntity().getFirstName());
			}
			if (null != userMappingEntity.getUserEntity()) {
				usermappingVo.setUserId(userMappingEntity.getUserEntity().getId());
			}
			return usermappingVo;
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
	}

	/**
	 * Method used to delete user mapping.
	 * 
	 * @param userMappingVo
	 *            UserMappingVo
	 * 
	 */
	@Transactional
	public void delete(UserMappingVo userMappingVo) {
		try {
			for (Integer id : userMappingVo.getUserMappingList()) {

				UserMappingEntity userMappingEntity = new UserMappingEntity();

				userMappingEntity = userMappingDao.findId(id);

				userMappingEntity.setDeleteFlag(CommonConstant.FLAG_ONE);

				userMappingDao.delete(userMappingEntity);
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

	}

	/**
	 * Method used to search user mapping.
	 * 
	 * @param userMappingVo
	 *            UserMappingVo
	 * 
	 */
	@Transactional
	public List<UserMappingVo> getAllSearch(UserMappingVo userMappingVo) {

		try {
			List<UserMappingVo> userMappingVoList = new ArrayList<UserMappingVo>();

			List<Object[]> userMappingEntityList = userMappingDao.getAllSearch(userMappingVo);
			userMappingVoList = getAllList(userMappingEntityList);

			return userMappingVoList;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("dataFailure"));
		}
	}

	@Transactional
	public int UserMapping(UserMappingVo userMappingVo){
		
		try{
			int count = userMappingDao.UserMapping(userMappingVo);
			if(count > 0 ) {
				throw new CommonException(getMessage("duplicatemappingFound"));
			} }catch (CommonException e) {
				logger.error(e.getMessage());
				throw new CommonException(e.getMessage());
			} catch (Exception e) {
				logger.error(e.getMessage());
				throw new CommonException(getMessage("dataFailure"));
			}
			
		
	
		return 0;
	
	}

	public UserMappingVo getScreenFields(ScreenAuthorizationVO screenAuthorizationMaster) {

		UserMappingVo userMappingVo = new UserMappingVo();

		ScreenAuthorizationVO screenAuthorizationMasterVo = userMappingDao.getScreenAuthorization(screenAuthorizationMaster);
		if (null != screenAuthorizationMasterVo) {

			// Get the Fields List
			userMappingVo.setScreenFieldDisplayVoList(screenAuthorizationMasterVo.getScreenFieldDisplayVoList());

			// Get the Functions & Side Tab List
			userMappingVo.setScreenFunctionDisplayList(screenAuthorizationMasterVo.getScreenFunctionDisplayList());

		} else {
			throw new CommonException(getMessage("noAuthorizationAvailableForThisUser"));

		}

		ScreenAuthenticationVO screenAuthenticationMaster = userMappingDao.getScreenAuhentication(screenAuthorizationMaster);

		if (null != screenAuthenticationMaster) {
			userMappingVo.setScreenVoList(screenAuthenticationMaster.getScreenVoList());

		} else {
			throw new CommonException(getMessage("noScreenAvailableForThisUser"));

		}

		userMappingVo.setUserName(screenAuthorizationMaster.getAuthDetailsVo().getUserName());

		return userMappingVo;
	}

	
	@Transactional
	public void findDuplicate(UserMappingVo userMappingVo) {
		int count = userMappingDao.findDuplicate(userMappingVo);
		
		if (count > 0) {
			throw new CommonException(getMessage("userMapping_user_check"));
		}
	}

}
