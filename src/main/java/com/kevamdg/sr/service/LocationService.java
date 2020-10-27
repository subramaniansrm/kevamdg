package com.kevamdg.sr.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kevamdg.sr.config.CommonException;
import com.kevamdg.sr.constants.CommonConstant;
import com.kevamdg.sr.controller.CommonAction;
import com.kevamdg.sr.dao.LocationDao;
import com.kevamdg.sr.entity.LocationEntity;
import com.kevamdg.sr.repository.LocationRepository;
import com.kevamdg.sr.vo.AuthDetailsVO;
import com.kevamdg.sr.vo.LocationVO;

/**
 * 
 * Class used to list, create, update and delete location details
 * 
 * @author raathikaabm
 *
 */

@Service
public class LocationService extends CommonAction<LocationVO> {

	Logger logger = LoggerFactory.getLogger(LocationService.class);

	@Autowired
	LocationRepository locationRepository;

	@Autowired
	LocationDao locationDao;

	/**
	 * Method is used to list the UserLocation
	 * 
	 * @return listUserLocationMasterVo List<UserLocationMasterVo>
	 * @throws CommonException
	 */

	@Transactional
	public List<LocationVO> getLocation() throws  CommonException {

		List<LocationVO> listUserLocationMasterVo = new ArrayList<LocationVO>();

		List<Object[]> listUserLocationEntity = null;
		try {
			listUserLocationEntity = locationDao.getLocation();

		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("DbFailure"));
		}

		for (Object[] object : listUserLocationEntity) {

			LocationVO locationVO = new LocationVO();

			if (null != (Integer) object[0]) {
				locationVO.setId((Integer) object[0]);

			}
			if (null != (String) object[1]) {

				locationVO.setUserLocationName((String) object[1]);
			}
			if (null != (String) ((Object[]) object)[2]) {
				locationVO.setUserLocationDetails((String) object[2]);

			}

			if (null != (Integer) ((Object[]) object)[3]) {
				locationVO.setCityId((Integer) object[3]);

			}

			if (null != (Integer) ((Object[]) object)[4]) {
				locationVO.setStateId((Integer) object[4]);

			}

			if (null != (Integer) ((Object[]) object)[5]) {
				locationVO.setCountryId((Integer) object[5]);

			}

			if (null != (String) ((Object[]) object)[6]) {
				locationVO.setZip((String) object[6]);

			}
			if (null != (String) ((Object[]) object)[7]) {
				locationVO.setPhone((String) object[7]);

			}

			if (null != (String) ((Object[]) object)[8]) {
				locationVO.setFax((String) object[8]);

			}

			if (null != (String) ((Object[]) object)[9]) {
				locationVO.setEmail((String) object[9]);

			}

			if (null != (String) ((Object[]) object)[10]) {
				locationVO.setContactName((String) object[10]);

			}

			if (null != (Character) ((Object[]) object)[11]) {
				locationVO.setActiveFlag((Character) object[11]);

			}
			if (null != (Character) ((Object[]) object)[12]) {
				locationVO.setDeleteFlag((Character) object[12]);

			}

			if (null != (Integer) ((Object[]) object)[13]) {
				locationVO.setSysAppId((Integer) object[13]);

			}

			if (null != (Date) ((Object[]) object)[14]) {
				locationVO.setCreatedDate((Date) object[14]);

			}

			if (null != (Integer) ((Object[]) object)[15]) {
				locationVO.setCreatedBy((Integer) object[15]);

			}

			if (null != (Date) ((Object[]) object)[16]) {
				locationVO.setUpdatedDate((Date) object[16]);

			}
			if (null != (Integer) ((Object[]) object)[17]) {
				locationVO.setUpdatedBy((Integer) object[17]);

			}

			if (null != (String) ((Object[]) object)[18]) {
				locationVO.setCityName((String) object[18]);

			}

			if (null != (String) ((Object[]) object)[19]) {
				locationVO.setStateName((String) object[19]);

			}
			
			if (null != (String) ((Object[]) object)[20]) {
				locationVO.setCountryName((String) object[20]);

			}


			listUserLocationMasterVo.add(locationVO);

		}

		return listUserLocationMasterVo;

	}

	/**
	 * Method used to create Location details
	 * 
	 * @param userLocationMasterVo
	 * @throws CommonException
	 */
	@Transactional
	public void create(LocationVO userLocationMasterVo,AuthDetailsVO authDetailsVo) throws CommonException {

		LocationEntity userLocationEntity = new LocationEntity();

		if (null != userLocationMasterVo.getUserLocationName()) {
			userLocationEntity.setUserLocationName(userLocationMasterVo.getUserLocationName());

		}

		if (null != userLocationMasterVo.getUserLocationDetails()) {
			userLocationEntity.setUserLocationDetails(userLocationMasterVo.getUserLocationDetails());
		}

		if (null != userLocationMasterVo.getZip()) {
			userLocationEntity.setZip(userLocationMasterVo.getZip());
		}

		if (null != userLocationMasterVo.getPhone()) {
			userLocationEntity.setPhone(userLocationMasterVo.getPhone());
		}

		if (null != userLocationMasterVo.getFax()) {
			userLocationEntity.setFax(userLocationMasterVo.getFax());
		}

		if (null != userLocationMasterVo.getEmail()) {

			userLocationEntity.setEmail(userLocationMasterVo.getEmail());
		}

		if (null != userLocationMasterVo.getContactName()) {

			userLocationEntity.setContactName(userLocationMasterVo.getContactName());
		}

		if (null != userLocationMasterVo.getCityId()) {

			userLocationEntity.setCityId(userLocationMasterVo.getCityId());
		}

		if (null != userLocationMasterVo.getCountryId()) {

			userLocationEntity.setCountryId(userLocationMasterVo.getCountryId());
		}

		if (null != userLocationMasterVo.getStateId()) {

			userLocationEntity.setStateId(userLocationMasterVo.getStateId());
		}

		userLocationEntity.setActiveFlag(CommonConstant.FLAG_ONE);
		userLocationEntity.setDeleteFlag(CommonConstant.FLAG_ZERO);
		userLocationEntity.setCreatedBy(authDetailsVo.getUserId());
		userLocationEntity.setCreatedDate(CommonConstant.getCalenderDate());
		userLocationEntity.setUpdatedBy(authDetailsVo.getUserId());
		userLocationEntity.setUpdatedDate(CommonConstant.getCalenderDate());

		try {
			locationRepository.save(userLocationEntity);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("dbFailure"));
		}
	}

	/**
	 * Method used to update Location Details
	 * 
	 * @param userLocationMasterVo
	 * @throws CommonException
	 */
	@Transactional
	public void update(LocationVO userLocationMasterVo,AuthDetailsVO authDetailsVo) throws CommonException {
		LocationEntity userLocationEntity = new LocationEntity();
		userLocationEntity = locationRepository.findOne(userLocationMasterVo.getId());

		if (null != userLocationMasterVo.getId()) {
			userLocationEntity.setId(userLocationMasterVo.getId());
		}
		if (null != userLocationMasterVo.getUserLocationName()) {
			userLocationEntity.setUserLocationName(userLocationMasterVo.getUserLocationName());

		}

		if (null != userLocationMasterVo.getUserLocationDetails()) {
			userLocationEntity.setUserLocationDetails(userLocationMasterVo.getUserLocationDetails());
		}

		if (null != userLocationMasterVo.getZip()) {
			userLocationEntity.setZip(userLocationMasterVo.getZip());
		}

		if (null != userLocationMasterVo.getPhone()) {
			userLocationEntity.setPhone(userLocationMasterVo.getPhone());
		}

		if (null != userLocationMasterVo.getFax()) {
			userLocationEntity.setFax(userLocationMasterVo.getFax());
		}

		if (null != userLocationMasterVo.getEmail()) {

			userLocationEntity.setEmail(userLocationMasterVo.getEmail());
		}

		if (null != userLocationMasterVo.getContactName()) {

			userLocationEntity.setContactName(userLocationMasterVo.getContactName());
		}

		if (null != userLocationMasterVo.getCityId()) {

			userLocationEntity.setCityId(userLocationMasterVo.getCityId());
		}

		if (null != userLocationMasterVo.getCountryId()) {

			userLocationEntity.setCountryId(userLocationMasterVo.getCountryId());
		}

		if (null != userLocationMasterVo.getStateId()) {

			userLocationEntity.setStateId(userLocationMasterVo.getStateId());
		}

		userLocationEntity.setUpdatedBy(authDetailsVo.getUserId());
		userLocationEntity.setUpdatedDate(CommonConstant.getCalenderDate());

		try {
			locationRepository.save(userLocationEntity);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("dbFailure"));

		}

	}

	/**
	 * Method used to delete location details
	 * 
	 * @param userLocationMasterVo
	 * @throws CommonException
	 */
	@Transactional
	public void delete(LocationVO userLocationMasterVo,AuthDetailsVO authDetailsVo) throws CommonException {

		LocationEntity locationEntity = new LocationEntity();
		for (Integer id : userLocationMasterVo.getDeleteItem()) {
			locationEntity = locationRepository.findOne(id);

			if (null != locationEntity) {
				locationEntity.setDeleteFlag('1');
			}

			locationEntity.setUpdatedBy(authDetailsVo.getUserId());
			locationEntity.setUpdatedDate(CommonConstant.getCalenderDate());

			try {
				locationRepository.save(locationEntity);
			}

			catch (Exception e) {
				logger.error(e.getMessage());
				throw new CommonException(getMessage("dbFailure"));
			}

		}
	}

	/**
	 * Method used to view Loctaion details using its id
	 * 
	 * @param integer
	 * @return
	 * @throws CommonException
	 */
	@Transactional
	public LocationVO view(Integer integer) throws CommonException {

		List<Object[]> locationEntity = new ArrayList<Object[]>();

		try {
			locationEntity = locationDao.findOne(integer);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("dbFailure"));

		}
		LocationVO locationVO = new LocationVO();
		for (Object[] object : locationEntity) {

			if (null != (Integer) object[0]) {
				locationVO.setId((Integer) object[0]);

			}
			if (null != (String) object[1]) {

				locationVO.setUserLocationName((String) object[1]);
			}
			if (null != (String) ((Object[]) object)[2]) {
				locationVO.setUserLocationDetails((String) object[2]);

			}

			if (null != (Integer) ((Object[]) object)[3]) {
				locationVO.setCityId((Integer) object[3]);

			}

			if (null != (Integer) ((Object[]) object)[4]) {
				locationVO.setStateId((Integer) object[4]);

			}

			if (null != (Integer) ((Object[]) object)[5]) {
				locationVO.setCountryId((Integer) object[5]);

			}

			if (null != (String) ((Object[]) object)[6]) {
				locationVO.setZip((String) object[6]);

			}
			if (null != (String) ((Object[]) object)[7]) {
				locationVO.setPhone((String) object[7]);

			}

			if (null != (String) ((Object[]) object)[8]) {
				locationVO.setFax((String) object[8]);

			}

			if (null != (String) ((Object[]) object)[9]) {
				locationVO.setEmail((String) object[9]);

			}

			if (null != (String) ((Object[]) object)[10]) {
				locationVO.setContactName((String) object[10]);

			}

			if (null != (Character) ((Object[]) object)[11]) {
				locationVO.setActiveFlag((Character) object[11]);

			}
			if (null != (Character) ((Object[]) object)[12]) {
				locationVO.setDeleteFlag((Character) object[12]);

			}

			if (null != (Integer) ((Object[]) object)[13]) {
				locationVO.setSysAppId((Integer) object[13]);

			}

			if (null != (Date) ((Object[]) object)[14]) {
				locationVO.setCreatedDate((Date) object[14]);

			}

			if (null != (Integer) ((Object[]) object)[15]) {
				locationVO.setCreatedBy((Integer) object[15]);

			}

			if (null != (Date) ((Object[]) object)[16]) {
				locationVO.setUpdatedDate((Date) object[16]);

			}
			if (null != (Integer) ((Object[]) object)[17]) {
				locationVO.setUpdatedBy((Integer) object[17]);

			}

			if (null != (String) ((Object[]) object)[18]) {
				locationVO.setCityName((String) object[18]);

			}

			if (null != (String) ((Object[]) object)[19]) {

				locationVO.setStateName((String) object[19]);

			}
			if (null != (String) ((Object[]) object)[20]) {
				locationVO.setCountryName((String) object[20]);

			}

		}

		return locationVO;
	}

	/**
	 * Method used to search location details
	 * 
	 * @param userLocationMasterVo
	 * @return
	 * @throws ApplicationException
	 * @throws CommonException
	 */
	@Transactional
	public List<LocationVO> search(LocationVO userLocationMasterVo)
			throws CommonException {

		List<LocationVO> listUserLocationMasterVo = new ArrayList<LocationVO>();

		List<Object[]> userLocationList = null;

		try {

			userLocationList = locationDao.search(userLocationMasterVo);

		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("DbFailure"));
		}

		for (Object[] object : userLocationList) {

			LocationVO locationVO = new LocationVO();

			if (null != (Integer) object[0]) {
				locationVO.setId((Integer) object[0]);

			}
			if (null != (String) object[1]) {

				locationVO.setUserLocationName((String) object[1]);
			}
			if (null != (String) ((Object[]) object)[2]) {
				locationVO.setUserLocationDetails((String) object[2]);

			}

			if (null != (Integer) ((Object[]) object)[3]) {
				locationVO.setCityId((Integer) object[3]);

			}

			if (null != (Integer) ((Object[]) object)[4]) {
				locationVO.setStateId((Integer) object[4]);

			}

			if (null != (Integer) ((Object[]) object)[5]) {
				locationVO.setCountryId((Integer) object[5]);

			}

			if (null != (String) ((Object[]) object)[6]) {
				locationVO.setZip((String) object[6]);

			}
			if (null != (String) ((Object[]) object)[7]) {
				locationVO.setPhone((String) object[7]);

			}

			if (null != (String) ((Object[]) object)[8]) {
				locationVO.setFax((String) object[8]);

			}

			if (null != (String) ((Object[]) object)[9]) {
				locationVO.setEmail((String) object[9]);

			}

			if (null != (String) ((Object[]) object)[10]) {
				locationVO.setContactName((String) object[10]);

			}

			if (null != (Character) ((Object[]) object)[11]) {
				locationVO.setActiveFlag((Character) object[11]);

			}
			if (null != (Character) ((Object[]) object)[12]) {
				locationVO.setDeleteFlag((Character) object[12]);

			}

			if (null != (Integer) ((Object[]) object)[13]) {
				locationVO.setSysAppId((Integer) object[13]);

			}

			if (null != (Date) ((Object[]) object)[14]) {
				locationVO.setCreatedDate((Date) object[14]);

			}

			if (null != (Integer) ((Object[]) object)[15]) {
				locationVO.setCreatedBy((Integer) object[15]);

			}

			if (null != (Date) ((Object[]) object)[16]) {
				locationVO.setUpdatedDate((Date) object[16]);

			}
			if (null != (Integer) ((Object[]) object)[17]) {
				locationVO.setUpdatedBy((Integer) object[17]);

			}

			if (null != (String) ((Object[]) object)[18]) {
				locationVO.setCityName((String) object[18]);

			}

			if (null != (String) ((Object[]) object)[19]) {
				locationVO.setStateName((String) object[19]);

			}
			if (null != (String) ((Object[]) object)[20]) {
				locationVO.setCountryName((String) object[20]);

			}

			listUserLocationMasterVo.add(locationVO);

		}

		return listUserLocationMasterVo;

	}

}
