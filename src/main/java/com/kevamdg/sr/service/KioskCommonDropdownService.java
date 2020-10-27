package com.kevamdg.sr.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevamdg.sr.config.CommonException;
import com.kevamdg.sr.controller.CommonAction;
import com.kevamdg.sr.dao.KioskCommonDropdownDao;
import com.kevamdg.sr.entity.CityEntity;
import com.kevamdg.sr.entity.CountryEntity;
import com.kevamdg.sr.entity.DepartmentEntity;
import com.kevamdg.sr.entity.DivisionEntity;
import com.kevamdg.sr.entity.LocationEntity;
import com.kevamdg.sr.entity.Screen;
import com.kevamdg.sr.entity.StateEntity;
import com.kevamdg.sr.entity.UserEntity;
import com.kevamdg.sr.entity.UserType;
import com.kevamdg.sr.vo.CityVo;
import com.kevamdg.sr.vo.CommonStorageVo;
import com.kevamdg.sr.vo.CommonVO;
import com.kevamdg.sr.vo.DivisionMasterVo;
import com.kevamdg.sr.vo.DropdownDepartmentVo;
import com.kevamdg.sr.vo.DropdownLocationVo;
import com.kevamdg.sr.vo.KioskCountryVo;
import com.kevamdg.sr.vo.KioskDepartmentVo;
import com.kevamdg.sr.vo.ScreenDropdownVo;
import com.kevamdg.sr.vo.StateVO;
import com.kevamdg.sr.vo.SubLocationVO;
import com.kevamdg.sr.vo.UserLocationVo;
import com.kevamdg.sr.vo.UserMasterVo;
import com.kevamdg.sr.vo.UserRoleTypeVO;
import com.kevamdg.sr.vo.UserRoleVO;

@Service
public class KioskCommonDropdownService extends CommonAction<T> {
	
	@Autowired
	private KioskCommonDropdownDao kioskCommonDropdownDao;

	/**
	 * Method is used to Load the Location List
	 * 
	 * @return userLocationMasterVo
	 */
	@Transactional
	public List<UserLocationVo> getAllLocation() {
		try {
			List<UserLocationVo> userLocationMasterVo = new ArrayList<UserLocationVo>();

			List<Object[]> locationList = kioskCommonDropdownDao.getAllLocation();

			for (Object[] userLocationEntity : locationList) {
				UserLocationVo locationMasterVo = new UserLocationVo();

				if (null != ((Object[]) userLocationEntity)[0]) {
					locationMasterVo.setId((int) ((Object[]) userLocationEntity)[0]);
				}
				if (null != (String) ((Object[]) userLocationEntity)[1]) {
					locationMasterVo.setUserLocationName((String) ((Object[]) userLocationEntity)[1]);
				}
				userLocationMasterVo.add(locationMasterVo);
			}

			return userLocationMasterVo;
		} catch (Exception e) {
			throw new CommonException(getMessage("dataFailure"));
		}
	}

	/**
	 * Method is used to Load the Department List
	 * 
	 * @return userDepartmentMasterVo
	 */
	@Transactional
	public List<KioskDepartmentVo> getAllDepartment(KioskDepartmentVo departmentVo) {

		try {

			List<KioskDepartmentVo> userDepartmentMasterVo = new ArrayList<KioskDepartmentVo>();

			List<Object[]> departmentList = kioskCommonDropdownDao.getAllDepartment(departmentVo);

			for (Object[] userDepartmentEntity : departmentList) {
				KioskDepartmentVo departmentMasterVo = new KioskDepartmentVo();
				if (0 != (int) ((Object[]) userDepartmentEntity)[0]) {
					departmentMasterVo.setId((int) ((Object[]) userDepartmentEntity)[0]);
				}
				if (null != (String) ((Object[]) userDepartmentEntity)[1]) {
					departmentMasterVo.setUserDepartmentName((String) ((Object[]) userDepartmentEntity)[1]);
				}
				if (null != (String) ((Object[]) userDepartmentEntity)[2]) {
					departmentMasterVo.setToolTipName(departmentMasterVo.getUserDepartmentName()
							.concat(" - " + (String) ((Object[]) userDepartmentEntity)[2]));
				} else {
					departmentMasterVo.setToolTipName(departmentMasterVo.getUserDepartmentName());
				}
				userDepartmentMasterVo.add(departmentMasterVo);
			}
			return userDepartmentMasterVo;
		} catch (Exception e) {
			e.printStackTrace();
			throw new CommonException(getMessage("dataFailure"));
		}
	}

	/**
	 * Method is used to Load the Role List
	 * 
	 * @return userRoleMasterVo
	 */
	@Transactional
	public List<UserRoleVO> getAllRole(UserRoleVO userRoleVo) {

		try {

			List<UserRoleVO> userRoleMasterVo = new ArrayList<UserRoleVO>();

			List<Object[]> roleList = kioskCommonDropdownDao.getAllRole(userRoleVo);

			for (Object[] userRoleEntity : roleList) {

				UserRoleVO roleMasterVo = new UserRoleVO();

				if (0 != (int) ((Object[]) userRoleEntity)[0]) {
					roleMasterVo.setId((int) ((Object[]) userRoleEntity)[0]);
				}
				if (null != (String) ((Object[]) userRoleEntity)[1]) {
					roleMasterVo.setUserRoleName((String) ((Object[]) userRoleEntity)[1]);
				}

				userRoleMasterVo.add(roleMasterVo);
			}
			return userRoleMasterVo;
		} catch (Exception e) {
			throw new CommonException(getMessage("dataFailure"));
		}
	}

	/**
	 * Method is used to Load the Division List
	 * 
	 * @return userDivisionMasterVo
	 */
	@Transactional
	public List<DivisionMasterVo> getAllDivision() {

		try {

			List<DivisionMasterVo> userDivisionMasterVo = new ArrayList<DivisionMasterVo>();

			List<DivisionEntity> divisionList = kioskCommonDropdownDao.getAllDivision();

			for (DivisionEntity divisionEntity : divisionList) {
				DivisionMasterVo divisionMasterVo = new DivisionMasterVo();
				divisionMasterVo.setId(divisionEntity.getId());
				divisionMasterVo.setDivision(divisionEntity.getDivisionName());
				userDivisionMasterVo.add(divisionMasterVo);
			}

			return userDivisionMasterVo;
		} catch (Exception e) {
			throw new CommonException(getMessage("dataFailure"));
		}
	}

	/**
	 * Method is used to Load the State List
	 * 
	 * @return stateMasterVoList List<StateMasterVo>
	 */
	@Transactional
	public List<StateVO> getAllState() {

		try {

			List<StateVO> stateMasterVoList = new ArrayList<StateVO>();

			List<StateEntity> stateEntityList = kioskCommonDropdownDao.getAllState();

			for (StateEntity stateEntity : stateEntityList) {
				StateVO stateMasterVo = new StateVO();
				stateMasterVo.setStateId(stateEntity.getStateId());
				stateMasterVo.setStateName(stateEntity.getStateName());
				stateMasterVoList.add(stateMasterVo);
			}
			return stateMasterVoList;
		} catch (Exception e) {
			throw new CommonException(getMessage("dataFailure"));
		}
	}

	/**
	 * Method is used to Load the City List
	 * 
	 * @return cityMasterVoList List<CityMasterVo>
	 */
	@Transactional
	public List<CityVo> getAllCity() {

		try {

			List<CityVo> cityMasterVoList = new ArrayList<CityVo>();

			List<CityEntity> cityEntityList = kioskCommonDropdownDao.getAllCity();

			for (CityEntity cityEntity : cityEntityList) {
				CityVo cityMasterVo = new CityVo();
				cityMasterVo.setCityId(cityEntity.getCityId());
				cityMasterVo.setCityName(cityEntity.getCityName());
				cityMasterVoList.add(cityMasterVo);
			}
			return cityMasterVoList;
		} catch (Exception e) {
			throw new CommonException(getMessage("dataFailure"));
		}

	}

	/**
	 * Method is used to Load the Screen List
	 * 
	 * @return ScreenDropdownVoList List<ScreenDropdownVo>
	 */
	@Transactional
	public List<ScreenDropdownVo> getAllScreen() {

		try {

			List<ScreenDropdownVo> screenDropdownVoList = new ArrayList<ScreenDropdownVo>();

			List<Screen> screenEntityList = kioskCommonDropdownDao.getAllScreen();

			for (Screen screenEntity : screenEntityList) {
				ScreenDropdownVo screenDropdownVo = new ScreenDropdownVo();

				screenDropdownVo.setScreenId(screenEntity.getScreenId());
				screenDropdownVo.setScreenName(screenEntity.getScreenName());
				screenDropdownVoList.add(screenDropdownVo);
			}
			return screenDropdownVoList;
		} catch (Exception e) {
			throw new CommonException(getMessage("dataFailure"));
		}

	}

	/**
	 * Method is used to Load the Country List
	 * 
	 * @return CountryMasterVoList List<CountryMasterVo>
	 */
	@Transactional
	public List<KioskCountryVo> getAllCountry() {

		try {

			List<KioskCountryVo> CountryMasterVoList = new ArrayList<KioskCountryVo>();

			List<CountryEntity> countryEntityList = kioskCommonDropdownDao.getAllCountry();

			for (CountryEntity countryEntity : countryEntityList) {
				KioskCountryVo countryMasterVo = new KioskCountryVo();

				countryMasterVo.setCountryId(countryEntity.getId());
				countryMasterVo.setCountryName(countryEntity.getCountry());
				CountryMasterVoList.add(countryMasterVo);
			}
			return CountryMasterVoList;
		} catch (Exception e) {
			throw new CommonException(getMessage("dataFailure"));
		}

	}


	/**
	 * Method is used to Load the Department List
	 * 
	 * @return UserRoleMaster
	 */
	@Transactional
	public List<DropdownDepartmentVo> getLoadUserDepartmentDetails() {
		try {
			List<DropdownDepartmentVo> dropdownDepartmentVo = new ArrayList<DropdownDepartmentVo>();

			List<DepartmentEntity> userDepartmentEntityList = kioskCommonDropdownDao.getLoadUserDepartmentDetails();

			for (DepartmentEntity userDepartmentEntity : userDepartmentEntityList) {

				DropdownDepartmentVo dropdownLocationVo = new DropdownDepartmentVo();

				dropdownLocationVo.setDepartmentId(userDepartmentEntity.getDepartmentId());
				dropdownLocationVo.setDepartmentName(userDepartmentEntity.getDepartmentName());

				dropdownDepartmentVo.add(dropdownLocationVo);

			}
			return dropdownDepartmentVo;
		} catch (Exception e) {
			throw new CommonException(getMessage("dataFailure"));
		}
	}

	/**
	 * Method is used to Load the Location List
	 * 
	 * @return UserRoleMaster
	 */

	@Transactional
	public List<DropdownLocationVo> getLoadUserLocationDetails() {
		try {
			List<LocationEntity> userLocation = kioskCommonDropdownDao.getLoadUserLocationDetails();

			List<DropdownLocationVo> locationVoList = new ArrayList<DropdownLocationVo>();

			for (LocationEntity userLocationEntity : userLocation) {

				DropdownLocationVo dropdownLocationVo = new DropdownLocationVo();

				dropdownLocationVo.setLocationId(userLocationEntity.getId());
				dropdownLocationVo.setLocationName(userLocationEntity.getUserLocationName());
				locationVoList.add(dropdownLocationVo);

			}
			return locationVoList;
		} catch (Exception e) {
			throw new CommonException(getMessage("dataFailure"));
		}
	}

	/**
	 * Method is used to Load the Department List
	 * 
	 * @return
	 */

	@Transactional
	public List<UserLocationVo> loadDepartmentLocation() {

		try {

			List<UserLocationVo> userLocationMasterVo = new ArrayList<UserLocationVo>();
			List<LocationEntity> userLocationEntity = kioskCommonDropdownDao.loadDepartmentLocation();
			userLocationMasterVo = getLocationList(userLocationEntity);
			return userLocationMasterVo;

		} catch (Exception e) {
			throw new CommonException(getMessage("dataFailure"));
		}
	}

	/**
	 * Method is used to Load the Location List
	 * 
	 * @param userLocationEntity
	 * @return
	 */
	@Transactional
	private List<UserLocationVo> getLocationList(List<LocationEntity> userLocationEntity) {

		try {

			List<UserLocationVo> userLocationMasterVo = new ArrayList<UserLocationVo>();

			for (LocationEntity locationEntity : userLocationEntity) {
				UserLocationVo locationMasterVo = new UserLocationVo();
				locationMasterVo.setId(locationEntity.getId());
				locationMasterVo.setUserLocationName(locationEntity.getUserLocationName());
				userLocationMasterVo.add(locationMasterVo);
			}
			return userLocationMasterVo;
		} catch (Exception e) {
			throw new CommonException(getMessage("dataFailure"));
		}
	}

	/**
	 * Method is used to Load the Sub Location List
	 * 
	 * @param id
	 * @return
	 */
	@Transactional
	public List<SubLocationVO> getAllsublocationList(int id) {

		try {

			List<SubLocationVO> listSubLocationVo = new ArrayList<SubLocationVO>();

			List<Object[]> listSubLocationEntity = kioskCommonDropdownDao.getAllSublocatList(id);

			for (Object[] subLocationEntityEntity : listSubLocationEntity) {

				SubLocationVO subLocationVo = new SubLocationVO();
				if (0 != (int) ((Object[]) subLocationEntityEntity)[0]) {
					subLocationVo.setSublocationId((int) ((Object[]) subLocationEntityEntity)[0]);
				}
				if (null != (String) ((Object[]) subLocationEntityEntity)[1]) {
					subLocationVo.setSubLocationName((String) ((Object[]) subLocationEntityEntity)[1]);
				}
				listSubLocationVo.add(subLocationVo);
			}

			return listSubLocationVo;
		} catch (Exception e) {
			throw new CommonException(getMessage("dataFailure"));
		}
	}

	/**
	 * Method is used for GetAll the user for User dropdown
	 * 
	 * @return
	 */
	@Transactional
	public List<UserMasterVo> getAllUser(UserMasterVo userMasterVo) {
		try {
			List<UserMasterVo> userMasterVoList = new ArrayList<UserMasterVo>();

			List<Object> userEntityList = kioskCommonDropdownDao.getAllUser(userMasterVo);

			for (Object userEntity : userEntityList) {
				UserMasterVo userMaster = new UserMasterVo();
				if (0 != (int) ((Object[]) userEntity)[0]) {
					userMaster.setId((int) ((Object[]) userEntity)[0]);
				}
				if (null != (String) ((Object[]) userEntity)[1]) {
					userMaster.setFirstName((String) ((Object[]) userEntity)[1]);
				}
				userMasterVoList.add(userMaster);
			}
			return userMasterVoList;
		} catch (Exception e) {
			throw new CommonException(getMessage("dataFailure"));
		}
	}

	/**
	 * Method is used to Load the user Executer List
	 * 
	 * @param userMasterVo
	 * @return
	 */
	@Transactional
	public List<UserMasterVo> getUserExecuter(UserMasterVo userMasterVo) {

		try {

			List<UserMasterVo> userMasterVoList = new ArrayList<UserMasterVo>();

			List<Object[]> userEntityList = kioskCommonDropdownDao.getUserExecuter(userMasterVo);

			for (Object[] object : userEntityList) {
				UserMasterVo userMaster = new UserMasterVo();
				userMaster.setId((int) object[0]);
				userMaster.setFirstName((String) object[1]);
				userMasterVoList.add(userMaster);
			}
			return userMasterVoList;
		} catch (Exception e) {
			throw new CommonException(getMessage("dataFailure"));
		}
	}


	/**
	 * Method is used to Get the all user level
	 * 
	 * @param commonStorageVo
	 * @return commonStorageVoList
	 */
	@Transactional
	public List<CommonStorageVo> getUserLevel(CommonStorageVo commonStorageVo) {

		try {

			List<CommonStorageVo> commonStorageVoList = new ArrayList<CommonStorageVo>();

			List<Object[]> commonStorageEntityList = kioskCommonDropdownDao.getAllLevel();

			for (Object[] commonStorageEntity : commonStorageEntityList) {

				CommonStorageVo commonStorage = new CommonStorageVo();
				if (0 != (int) ((Object[]) commonStorageEntity)[0]) {
					commonStorage.setCommonId((int) ((Object[]) commonStorageEntity)[0]);
				}
				if (null != (String) ((Object[]) commonStorageEntity)[1]) {
					commonStorage.setItemValue((String) ((Object[]) commonStorageEntity)[1]);
				}
				commonStorageVoList.add(commonStorage);
			}
			return commonStorageVoList;
		} catch (Exception e) {
			throw new CommonException(getMessage("dataFailure"));
		}
	}

	@Transactional
	public List<UserMasterVo> getUserDep(UserMasterVo userMasterVo) {
		try {
			List<UserMasterVo> userMasterVoList = new ArrayList<UserMasterVo>();
			List<UserEntity> userEntityList = null;

			userEntityList = kioskCommonDropdownDao.getUserDep(userMasterVo);

			for (UserEntity userEntity : userEntityList) {
				UserMasterVo userMaster = new UserMasterVo();
				userMaster.setId(userEntity.getId());
				if (userEntity.getLastName() != null) {
					userMaster.setFirstName(userEntity.getFirstName().concat(" " + userEntity.getLastName()));
				} else {
					userMaster.setFirstName(userEntity.getFirstName());
				}

				userMasterVoList.add(userMaster);
			}
			return userMasterVoList;
		} catch (Exception e) {
			throw new CommonException(getMessage("dataFailure"));
		}
	}

	public List<UserRoleTypeVO> getRoleType(UserRoleTypeVO userRoleTypeVo) {
		try {
			List<UserRoleTypeVO> userRoleTypeVoList = new ArrayList<UserRoleTypeVO>();
			List<UserType> userTypeEntityList = null;

			userTypeEntityList = kioskCommonDropdownDao.getRoleType(userRoleTypeVo);

			for (UserType userTypeEntity : userTypeEntityList) {
				UserRoleTypeVO userRoleType = new UserRoleTypeVO();
				userRoleType.setUserTypeId(userTypeEntity.getUserTypeId());
				userRoleType.setTypeOfUser(userTypeEntity.getTypeOfUser());
				userRoleTypeVoList.add(userRoleType);
			}
			return userRoleTypeVoList;
		} catch (Exception e) {
			throw new CommonException(getMessage("dataFailure"));
		}
	}

	public List<UserMasterVo> getReassignUser(CommonVO requestWorkFlowAuditVo) {

		try {

			List<UserMasterVo> userMasterVoList = new ArrayList<UserMasterVo>();

			List<Object[]> userEntityList = kioskCommonDropdownDao.getReassignUser(requestWorkFlowAuditVo);

			for (Object[] object : userEntityList) {
				UserMasterVo userMaster = new UserMasterVo();
				userMaster.setId((int) object[0]);

				if ((String) object[2] != null) {
					userMaster.setFirstName((String) object[1].toString().concat(" " + (String) object[2]));
				} else {
					userMaster.setFirstName((String) object[1]);
				}
				userMasterVoList.add(userMaster);
			}
			return userMasterVoList;
		} catch (Exception e) {
			throw new CommonException(getMessage("dataFailure"));
		}
	}

}
