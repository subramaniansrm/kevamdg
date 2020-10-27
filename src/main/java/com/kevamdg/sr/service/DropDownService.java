package com.kevamdg.sr.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kevamdg.sr.config.CommonException;
import com.kevamdg.sr.controller.CommonAction;
import com.kevamdg.sr.dao.DropDownDAO;
import com.kevamdg.sr.entity.CityEntity;
import com.kevamdg.sr.entity.CommonScreenFieldEntity;
import com.kevamdg.sr.entity.CountryEntity;
import com.kevamdg.sr.entity.DepartmentEntity;
import com.kevamdg.sr.entity.ModuleEntity;
import com.kevamdg.sr.entity.StateEntity;
import com.kevamdg.sr.entity.UserEntity;
import com.kevamdg.sr.entity.UserType;
import com.kevamdg.sr.repository.UserRepository;
import com.kevamdg.sr.vo.CommonDropDownVO;
import com.kevamdg.sr.vo.CommonScreenFieldVO;
import com.kevamdg.sr.vo.DepartmentVO;
import com.kevamdg.sr.vo.LocationVO;
import com.kevamdg.sr.vo.ModuleVO;
import com.kevamdg.sr.vo.ScreenMMVO;
import com.kevamdg.sr.vo.UserMappingModuleVO;
import com.kevamdg.sr.vo.UserRoleTypeVO;
import com.kevamdg.sr.vo.UserRoleVO;
import com.kevamdg.sr.vo.UserVO;

/**
 * Common Drop DOwn Service
 * 
 * @author raathikaabm
 *
 */
@Service
public class DropDownService extends CommonAction<LocationVO> {

	Logger logger = LoggerFactory.getLogger(DropDownService.class);

	@Autowired
	DropDownDAO dropdownDao;

	@Autowired
	UserRepository userRepository;

	/**
	 * LOCATION DROPDOWN
	 * 
	 * @return
	 * @throws CommonException
	 */
	public List<CommonDropDownVO> getAllLocation() throws CommonException {
		try {
			List<CommonDropDownVO> dropdownLocationVOList = new ArrayList<CommonDropDownVO>();

			List<Object[]> locationList = dropdownDao.getAllLocation();

			for (Object[] userLocationEntity : locationList) {
				CommonDropDownVO dropdownLocationVO = new CommonDropDownVO();

				if (null != ((Object[]) userLocationEntity)[0]) {
					dropdownLocationVO.setId((int) ((Object[]) userLocationEntity)[0]);
				}
				if (null != (String) ((Object[]) userLocationEntity)[1]) {
					dropdownLocationVO.setName((String) ((Object[]) userLocationEntity)[1]);
				}
				dropdownLocationVOList.add(dropdownLocationVO);
			}

			return dropdownLocationVOList;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("DbFailure"));
		}
	}

	/**
	 * DEPARTMENT LOAD BASED ON LOCATION AND SUBLOCATION
	 * 
	 * @param departmentVo
	 * @return
	 * @throws CommonException
	 */

	@Transactional
	public List<CommonDropDownVO> getAllDepartment(DepartmentVO departmentVo) throws CommonException {

		try {

			List<CommonDropDownVO> dropdownDepartmentVOList = new ArrayList<CommonDropDownVO>();

			List<Object[]> departmentList = dropdownDao.getAllDepartment(departmentVo);

			for (Object[] userDepartmentEntity : departmentList) {
				CommonDropDownVO dropdownDepartmentVO = new CommonDropDownVO();
				if (null != (Integer) ((Object[]) userDepartmentEntity)[0]) {
					dropdownDepartmentVO.setId((Integer) ((Object[]) userDepartmentEntity)[0]);
				}
				if (null != (String) ((Object[]) userDepartmentEntity)[1]) {
					dropdownDepartmentVO.setName((String) ((Object[]) userDepartmentEntity)[1]);
				}
				dropdownDepartmentVOList.add(dropdownDepartmentVO);
			}
			return dropdownDepartmentVOList;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("DbFailure"));
		}
	}

	/**
	 * 
	 * USER ROLE DROP DOWN BASED ON LOCATION, SUBLOCATION AND DEPARTMENT
	 * 
	 * @param userRoleVo
	 * @return
	 * @throws CommonException
	 */

	@Transactional
	public List<CommonDropDownVO> getAllRole(UserRoleVO userRoleVo) throws CommonException {

		try {

			List<CommonDropDownVO> dropdownUserRoleVOList = new ArrayList<CommonDropDownVO>();

			List<Object[]> roleList = dropdownDao.getAllRole(userRoleVo);

			for (Object[] userRoleEntity : roleList) {

				CommonDropDownVO dropdownUserRoleVO = new CommonDropDownVO();

				if (0 != (int) ((Object[]) userRoleEntity)[0]) {
					dropdownUserRoleVO.setId((int) ((Object[]) userRoleEntity)[0]);
				}
				if (null != (String) ((Object[]) userRoleEntity)[1]) {
					dropdownUserRoleVO.setName((String) ((Object[]) userRoleEntity)[1]);
				}

				dropdownUserRoleVOList.add(dropdownUserRoleVO);
			}
			return dropdownUserRoleVOList;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("DbFailure"));
		}
	}

	@Transactional
	public List<CommonDropDownVO> getLoadUserDepartmentDetails() throws CommonException {
		try {
			List<CommonDropDownVO> dropdownDepartmentVo = new ArrayList<CommonDropDownVO>();

			List<DepartmentEntity> userDepartmentEntityList = dropdownDao.getLoadUserDepartmentDetails();

			for (DepartmentEntity userDepartmentEntity : userDepartmentEntityList) {

				CommonDropDownVO dropdownLocationVo = new CommonDropDownVO();

				dropdownLocationVo.setId(userDepartmentEntity.getDepartmentId());
				dropdownLocationVo.setName(userDepartmentEntity.getDepartmentName());

				dropdownDepartmentVo.add(dropdownLocationVo);

			}
			return dropdownDepartmentVo;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("DbFailure"));
		}
	}

	/**
	 * COUNTRY DROP DOWN
	 * 
	 * @return
	 * @throws CommonException
	 */

	@Transactional
	public List<CommonDropDownVO> getAllCountry() throws CommonException {

		try {

			List<CommonDropDownVO> CountryMasterVoList = new ArrayList<CommonDropDownVO>();

			List<CountryEntity> countryEntityList = dropdownDao.getAllCountry();

			for (CountryEntity countryEntity : countryEntityList) {
				CommonDropDownVO countryMasterVo = new CommonDropDownVO();

				countryMasterVo.setId(countryEntity.getId());
				countryMasterVo.setName(countryEntity.getCountry());
				CountryMasterVoList.add(countryMasterVo);
			}
			return CountryMasterVoList;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("DbFailure"));
		}

	}

	/**
	 * 
	 * STATE DROPDOWN
	 * 
	 * @return
	 * @throws CommonException
	 */

	@Transactional
	public List<CommonDropDownVO> getAllState() throws CommonException {

		try {

			List<CommonDropDownVO> stateMasterVoList = new ArrayList<CommonDropDownVO>();

			List<StateEntity> stateEntityList = dropdownDao.getAllState();

			for (StateEntity stateEntity : stateEntityList) {
				CommonDropDownVO stateMasterVo = new CommonDropDownVO();
				stateMasterVo.setId(stateEntity.getStateId());
				stateMasterVo.setName(stateEntity.getStateName());
				stateMasterVoList.add(stateMasterVo);
			}
			return stateMasterVoList;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("DbFailure"));
		}
	}

	/**
	 * CITY DROPDOWN
	 * 
	 * @return
	 * @throws CommonException
	 */
	@Transactional
	public List<CommonDropDownVO> getAllCity() throws CommonException {

		try {

			List<CommonDropDownVO> cityMasterVoList = new ArrayList<CommonDropDownVO>();

			List<CityEntity> cityEntityList = dropdownDao.getAllCity();

			for (CityEntity cityEntity : cityEntityList) {
				CommonDropDownVO cityMasterVo = new CommonDropDownVO();
				cityMasterVo.setId(cityEntity.getCityId());
				cityMasterVo.setName(cityEntity.getCityName());
				cityMasterVoList.add(cityMasterVo);
			}
			return cityMasterVoList;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("DbFailure"));
		}

	}

	/**
	 * SUBLOCATION DROPDOWN BASED ON LOCATION
	 * 
	 * @param id
	 * @return
	 * @throws CommonException
	 */

	@Transactional
	public List<CommonDropDownVO> getAllsublocationList(int id) throws CommonException {

		try {

			List<CommonDropDownVO> dropdownSubLocationVOList = new ArrayList<CommonDropDownVO>();

			List<Object[]> listSubLocationEntity = dropdownDao.getAllSublocatList(id);

			for (Object[] subLocationEntityEntity : listSubLocationEntity) {

				CommonDropDownVO dropdownSubLocationVO = new CommonDropDownVO();
				if (null != (Integer) ((Object[]) subLocationEntityEntity)[0]) {
					dropdownSubLocationVO.setId((Integer) ((Object[]) subLocationEntityEntity)[0]);
				}
				if (null != (String) ((Object[]) subLocationEntityEntity)[1]) {
					dropdownSubLocationVO.setName((String) ((Object[]) subLocationEntityEntity)[1]);
				}
				dropdownSubLocationVOList.add(dropdownSubLocationVO);
			}

			return dropdownSubLocationVOList;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("DbFailure"));
		}
	}

	/**
	 * USER DROPDOWN
	 * 
	 * @param userMasterVo
	 * @return
	 * @throws CommonException
	 */
	@Transactional
	public List<CommonDropDownVO> getAllUser(UserVO userMasterVo) throws CommonException {
		try {
			List<CommonDropDownVO> userMasterVoList = new ArrayList<CommonDropDownVO>();

			List<Object> userEntityList = dropdownDao.getAllUser(userMasterVo);

			for (Object userEntity : userEntityList) {
				CommonDropDownVO userMaster = new CommonDropDownVO();
				if (0 != (int) ((Object[]) userEntity)[0]) {
					userMaster.setId((int) ((Object[]) userEntity)[0]);
				}
				if (null != (String) ((Object[]) userEntity)[1]) {
					userMaster.setName((String) ((Object[]) userEntity)[1]);
				}
				userMasterVoList.add(userMaster);
			}
			return userMasterVoList;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("DbFailure"));
		}
	}

	
	public CommonDropDownVO name(Integer id) {

		UserEntity user = userRepository.findOne(id);

		CommonDropDownVO commonDropDownVO = new CommonDropDownVO();

		commonDropDownVO.setId(user.getId());
		commonDropDownVO.setName(user.getFirstName().concat(" " + user.getLastName()));

		return commonDropDownVO;
	}

	public List<ScreenMMVO> screenList(Integer id) {

		List<Object[]> list = dropdownDao.screenList(id);

		List<ScreenMMVO> screenList = new ArrayList<>();

		for (Object[] obj : list) {

			ScreenMMVO screenMMVO = new ScreenMMVO();
			
			if (null != ((Object[]) obj)[0]) {
				screenMMVO.setScreenId((int) ((Object[]) obj)[0]);
			}
			if (null != ((Object[]) obj)[1]) {
				screenMMVO.setModuleId((int) ((Object[]) obj)[1]);
			}
			if (null != ((Object[]) obj)[2]) {
				screenMMVO.setScreenName((String) ((Object[]) obj)[2]);
			}
			if (null != ((Object[]) obj)[3]){
				screenMMVO.setScreenTypeFlag((Character) ((Object[]) obj)[3]);
			}
			if (null != ((Object[]) obj)[4]){
				screenMMVO.setScreenUrl((String) ((Object[]) obj)[4]);
			}
			if (null != ((Object[]) obj)[5]){
				screenMMVO.setScreenIcon((String) ((Object[]) obj)[5]);
			}
			if (null != ((Object[]) obj)[6]){
				screenMMVO.setModuleName((String) ((Object[]) obj)[6]);
			}

			screenList.add(screenMMVO);

		}

		return screenList;
	}
	
	
	public List<ScreenMMVO> cmScreenList(Integer id) {

		List<Object[]> list = dropdownDao.cmScreenList(id);

		List<ScreenMMVO> screenList = new ArrayList<>();

		for (Object[] obj : list) {

			ScreenMMVO screenMMVO = new ScreenMMVO();
			
			if (null != ((Object[]) obj)[0]) {
				screenMMVO.setScreenId((int) ((Object[]) obj)[0]);
			}
			if (null != ((Object[]) obj)[1]) {
				screenMMVO.setModuleId((int) ((Object[]) obj)[1]);
			}
			if (null != ((Object[]) obj)[2]) {
				screenMMVO.setScreenName((String) ((Object[]) obj)[2]);
			}
			if (null != ((Object[]) obj)[3]){
				screenMMVO.setScreenTypeFlag((Character) ((Object[]) obj)[3]);
			}
			if (null != ((Object[]) obj)[4]){
				screenMMVO.setScreenUrl((String) ((Object[]) obj)[4]);
			}
			if (null != ((Object[]) obj)[5]){
				screenMMVO.setScreenIcon((String) ((Object[]) obj)[5]);
			}
			if (null != ((Object[]) obj)[6]){
				screenMMVO.setModuleName((String) ((Object[]) obj)[6]);
			}

			screenList.add(screenMMVO);

		}

		return screenList;
	}

	public List<UserRoleTypeVO> getRoleType(UserRoleTypeVO userRoleTypeVo) throws CommonException {
		try {
			List<UserRoleTypeVO> userRoleTypeVoList = new ArrayList<UserRoleTypeVO>();
			List<UserType> userTypeEntityList = null;

			userTypeEntityList = dropdownDao.getRoleType(userRoleTypeVo);

			for (UserType userTypeEntity : userTypeEntityList) {
				UserRoleTypeVO userRoleType = new UserRoleTypeVO();
				userRoleType.setUserTypeId(userTypeEntity.getUserTypeId());
				userRoleType.setTypeOfUser(userTypeEntity.getTypeOfUser());
				userRoleTypeVoList.add(userRoleType);
			}
			return userRoleTypeVoList;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("DbFailure"));
		}
	}

	public List<ModuleVO> getModule() throws CommonException {
		try {
			List<ModuleVO> moduleVoList = new ArrayList<ModuleVO>();
			List<ModuleEntity> moduleEntityList = dropdownDao.getModule();

			for (ModuleEntity moduleEntity : moduleEntityList) {
				ModuleVO moduleVO = new ModuleVO();
				moduleVO.setModuleId(moduleEntity.getId());
				moduleVO.setModuleCode(moduleEntity.getModuleCode());
				moduleVO.setModuleName(moduleEntity.getModuleName());
				moduleVO.setActiveFlag(moduleEntity.isActiveFlag());
				moduleVoList.add(moduleVO);
			}
			return moduleVoList;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("DbFailure"));
		}
	}

	public List<UserMappingModuleVO> getModuleRights(int id) throws CommonException {
		try {
			List<UserMappingModuleVO> moduleVoList = new ArrayList<UserMappingModuleVO>();
			List<Object[]> moduleEntityList = dropdownDao.getModuleRights(id);

			for (Object[] moduleEntity : moduleEntityList) {
				UserMappingModuleVO moduleVO = new UserMappingModuleVO();
				if (0 != (int) ((Object[]) moduleEntity)[0]) {
					moduleVO.setId((int) ((Object[]) moduleEntity)[0]);
				}
				if (0 != (int) ((Object[]) moduleEntity)[2]) {
					moduleVO.setModuleId((int) ((Object[]) moduleEntity)[2]);
				}
				if (0 != (int) ((Object[]) moduleEntity)[1]) {
					moduleVO.setUserId((int) ((Object[]) moduleEntity)[1]);
				}
				if (null != (String) ((Object[]) moduleEntity)[3]) {
					moduleVO.setModuleTitle((String) ((Object[]) moduleEntity)[3]);
				}

				moduleVoList.add(moduleVO);
			}
			return moduleVoList;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("DbFailure"));
		}
	}

	public List<UserMappingModuleVO> mdgModuleRights(int id) throws CommonException {
		try {
			List<UserMappingModuleVO> moduleVoList = new ArrayList<UserMappingModuleVO>();
			List<Object[]> moduleEntityList = dropdownDao.mdgModuleRights(id);

			for (Object[] moduleEntity : moduleEntityList) {
				UserMappingModuleVO moduleVO = new UserMappingModuleVO();
				if (0 != (int) ((Object[]) moduleEntity)[0]) {
					moduleVO.setId((int) ((Object[]) moduleEntity)[0]);
				}
				if (0 != (int) ((Object[]) moduleEntity)[2]) {
					moduleVO.setModuleId((int) ((Object[]) moduleEntity)[2]);
				}
				if (0 != (int) ((Object[]) moduleEntity)[1]) {
					moduleVO.setUserId((int) ((Object[]) moduleEntity)[1]);
				}
				if (null != (String) ((Object[]) moduleEntity)[3]) {
					moduleVO.setModuleTitle((String) ((Object[]) moduleEntity)[3]);
				}
				if (null != (String) ((Object[]) moduleEntity)[4]) {
					moduleVO.setModuleName((String) ((Object[]) moduleEntity)[4]);
				}

				moduleVoList.add(moduleVO);
			}
			return moduleVoList;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("DbFailure"));
		}
	}

	public List<CommonScreenFieldVO> screenField() throws CommonException {
		try {
			List<CommonScreenFieldVO> commonScreenFieldVOList = new ArrayList<CommonScreenFieldVO>();
			List<CommonScreenFieldEntity> commonDropdownEntityList = dropdownDao.screenField();

			for (CommonScreenFieldEntity commonDropdownEntity : commonDropdownEntityList) {
				CommonScreenFieldVO commonDropDownVO = new CommonScreenFieldVO();

				BeanUtils.copyProperties(commonDropdownEntity, commonDropDownVO);

				commonScreenFieldVOList.add(commonDropDownVO);
			}
			return commonScreenFieldVOList;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("DbFailure"));
		}
	}

}
