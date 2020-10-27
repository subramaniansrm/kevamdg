package com.kevamdg.sr.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kevamdg.sr.config.CommonException;
import com.kevamdg.sr.constants.CommonConstant;
import com.kevamdg.sr.controller.CommonAction;
import com.kevamdg.sr.dao.DepartmentDao;
import com.kevamdg.sr.entity.DepartmentEntity;
import com.kevamdg.sr.entity.LocationEntity;
import com.kevamdg.sr.entity.SubLocationEntity;
import com.kevamdg.sr.repository.DepartmentRepository;
import com.kevamdg.sr.repository.LocationRepository;
import com.kevamdg.sr.repository.SubLocationRepository;
import com.kevamdg.sr.vo.AuthDetailsVO;
import com.kevamdg.sr.vo.DepartmentVO;

/**
 * 
 * Class used to get all department list, create , update , delete and search
 * location details
 * 
 * @author raathikaabm
 *
 */
@Service
public class DepartmentService extends CommonAction<DepartmentVO> {

	Logger logger = LoggerFactory.getLogger(DepartmentService.class);

	@Autowired
	DepartmentRepository departmentRepository;

	@Autowired
	LocationRepository locationRepository;

	@Autowired
	SubLocationRepository subLocationRepository;

	@Autowired
	DepartmentDao departmentDao;

	/**
	 * Method used to get all department list
	 * 
	 * @return
	 * @throws CommonException
	 */

	public List<DepartmentVO> getAll() throws CommonException {

		List<DepartmentEntity> departmentEntityList = new ArrayList<DepartmentEntity>();
		List<DepartmentVO> departmentVOlist = new ArrayList<DepartmentVO>();

		try {
			departmentEntityList = departmentRepository.getAll();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("DbFailure"));
		}

		for (DepartmentEntity departmentEntity : departmentEntityList) {
			DepartmentVO departmentVO = new DepartmentVO();

			if (departmentEntity.getDepartmentId() != null) {
				departmentVO.setDepartmentId(departmentEntity.getDepartmentId());
			}

			if (departmentEntity.getDepartmentName() != null) {
				departmentVO.setDepartmentName(departmentEntity.getDepartmentName());
			}

			if (departmentEntity.getLocationId() != null) {
				departmentVO.setLocationId(departmentEntity.getLocationId());
				LocationEntity locationEntity = new LocationEntity();
				locationEntity = locationRepository.findOne(departmentVO.getLocationId());
				if (locationEntity.getUserLocationName() != null) {
					departmentVO.setLocationName(locationEntity.getUserLocationName());
					;
				}

			}

			if (departmentEntity.getSubLocationId() != null) {
				departmentVO.setSublocationId(departmentEntity.getSubLocationId());
				SubLocationEntity subLocationEntity = new SubLocationEntity();
				subLocationEntity = subLocationRepository.findOne(departmentVO.getSublocationId());
				if (subLocationEntity.getSubLocationName() != null) {
					departmentVO.setSublocationName(subLocationEntity.getSubLocationName());
				}

			}

			if (departmentEntity.getDescription() != null) {
				departmentVO.setDescription(departmentEntity.getDescription());
			}
			if (departmentEntity.getDeleteFlag() != null) {
				departmentVO.setDeleteFlag(departmentEntity.getDeleteFlag());
			}

			if (departmentEntity.getCreatedBy() != null) {
				departmentVO.setCreatedBy(departmentEntity.getCreatedBy());
			}

			if (departmentEntity.getUpdatedDate() != null) {
				departmentVO.setUpdatedDate(departmentEntity.getUpdatedDate());
			}

			if (departmentEntity.getUpdatedBy() != null) {
				departmentVO.setUpdatedBy(departmentEntity.getUpdatedBy());
			}

			if (departmentEntity.getSysAppId() != null) {
				departmentVO.setSysAppId(departmentEntity.getSysAppId());
			}
			departmentVOlist.add(departmentVO);
		}

		return departmentVOlist;

	}

	/**
	 * Method used to create department details
	 * 
	 * @param departmentVO
	 * @throws CommonException
	 */
	@Transactional
	public void create(DepartmentVO departmentVO,AuthDetailsVO authDetailsVo) throws CommonException {

		DepartmentEntity departmentEntity = new DepartmentEntity();

		if (departmentVO.getDepartmentId() != null) {
			departmentEntity.setDepartmentId(departmentVO.getDepartmentId());
		}
		if (departmentVO.getDepartmentName() != null) {
			departmentEntity.setDepartmentName(departmentVO.getDepartmentName());
		}
		if (departmentVO.getDepartmentName() != null) {
			departmentEntity.setDepartmentName(departmentVO.getDepartmentName());
		}
		if (departmentVO.getLocationId() != null) {
			departmentEntity.setLocationId(departmentVO.getLocationId());
		}
		if (departmentVO.getSublocationId() != null) {
			departmentEntity.setSubLocationId(departmentVO.getSublocationId());
		}
		if (departmentVO.getDescription() != null) {
			departmentEntity.setDescription(departmentVO.getDescription());
		}

		departmentEntity.setDeleteFlag(false);
		departmentEntity.setCreatedBy(authDetailsVo.getUserId());
		departmentEntity.setCreatedDate(CommonConstant.getCalenderDate());
		departmentEntity.setUpdatedBy(authDetailsVo.getUserId());
		departmentEntity.setUpdatedDate(CommonConstant.getCalenderDate());

		try {
			departmentRepository.save(departmentEntity);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("DbFailure"));
		}

	}

	/**
	 * Method used to update department details
	 * 
	 * @param departmentVO
	 * @throws CommonException
	 */
	@Transactional
	public void update(DepartmentVO departmentVO,AuthDetailsVO authDetailsVo) throws CommonException {

		DepartmentEntity departmentEntity = new DepartmentEntity();
		try {
			departmentEntity = departmentRepository.findOne(departmentVO.getDepartmentId());
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("DbFailure"));
		}

		if (departmentEntity != null) {

			if (departmentVO.getDepartmentId() != null) {
				departmentEntity.setDepartmentId(departmentVO.getDepartmentId());
			}
			if (departmentVO.getDepartmentName() != null) {
				departmentEntity.setDepartmentName(departmentVO.getDepartmentName());
			}
			if (departmentVO.getDepartmentName() != null) {
				departmentEntity.setDepartmentName(departmentVO.getDepartmentName());
			}
			if (departmentVO.getLocationId() != null) {
				departmentEntity.setLocationId(departmentVO.getLocationId());
			}
			if (departmentVO.getSublocationId() != null) {
				departmentEntity.setSubLocationId(departmentVO.getSublocationId());
			}
			if (departmentVO.getDescription() != null) {
				departmentEntity.setDescription(departmentVO.getDescription());
			}

			departmentEntity.setDeleteFlag(false);
			departmentEntity.setUpdatedBy(authDetailsVo.getUserId());
			departmentEntity.setUpdatedDate(CommonConstant.getCalenderDate());

			try {
				departmentRepository.save(departmentEntity);
			} catch (Exception e) {
				logger.error(e.getMessage());
				throw new CommonException(getMessage("DbFailure"));
			}

		}
	}

	/**
	 * 
	 * Method used to delete department details
	 * 
	 * @param departmentVO
	 * @throws CommonException
	 */
	@Transactional
	public void delete(DepartmentVO departmentVO,AuthDetailsVO authDetailsVo) throws CommonException {

		DepartmentEntity departmentEntity = new DepartmentEntity();

		for (Integer id : departmentVO.getDeleteItem()) {
			try {
				departmentEntity = departmentRepository.findOne(id);
			} catch (Exception e) {
				logger.error(e.getMessage());
				throw new CommonException(getMessage("DbFailure"));
			}

			if (departmentEntity != null) {
				departmentEntity.setDeleteFlag(true);
				departmentEntity.setUpdatedBy(authDetailsVo.getUserId());
				departmentEntity.setUpdatedDate(CommonConstant.getCalenderDate());
			}
		}

	}

	/**
	 * Method used to view department details
	 * 
	 * @param integer
	 * @return
	 * @throws CommonException
	 */

	@Transactional
	public DepartmentVO view(Integer integer) throws CommonException {

		DepartmentEntity departmentEntity = new DepartmentEntity();
		try {
			departmentEntity = departmentRepository.findOne(integer);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("DbFailure"));
		}
		DepartmentVO departmentVO = new DepartmentVO();
		if (departmentEntity != null) {

			if (departmentEntity.getDepartmentId() != null) {
				departmentVO.setDepartmentId(departmentEntity.getDepartmentId());
			}

			if (departmentEntity.getDepartmentName() != null) {
				departmentVO.setDepartmentName(departmentEntity.getDepartmentName());
			}

			if (departmentEntity.getLocationId() != null) {
				departmentVO.setLocationId(departmentEntity.getLocationId());
				LocationEntity locationEntity = new LocationEntity();
				locationEntity = locationRepository.findOne(departmentVO.getLocationId());
				if (locationEntity.getUserLocationName() != null) {
					departmentVO.setLocationName(locationEntity.getUserLocationName());

				}

			}

			if (departmentEntity.getSubLocationId() != null) {
				departmentVO.setSublocationId(departmentEntity.getSubLocationId());
				SubLocationEntity subLocationEntity = new SubLocationEntity();
				subLocationEntity = subLocationRepository.findOne(departmentVO.getSublocationId());
				if (subLocationEntity.getSubLocationName() != null) {
					departmentVO.setSublocationName(subLocationEntity.getSubLocationName());
				}

			}

			if (departmentEntity.getDescription() != null) {
				departmentVO.setDescription(departmentEntity.getDescription());
			}
			if (departmentEntity.getDeleteFlag() != null) {
				departmentVO.setDeleteFlag(departmentEntity.getDeleteFlag());
			}

			if (departmentEntity.getCreatedBy() != null) {
				departmentVO.setCreatedBy(departmentEntity.getCreatedBy());
			}

			if (departmentEntity.getUpdatedDate() != null) {
				departmentVO.setUpdatedDate(departmentEntity.getUpdatedDate());
			}

			if (departmentEntity.getUpdatedBy() != null) {
				departmentVO.setUpdatedBy(departmentEntity.getUpdatedBy());
			}

			if (departmentEntity.getSysAppId() != null) {
				departmentVO.setSysAppId(departmentEntity.getSysAppId());
			}

		}

		return departmentVO;

	}

	/**
	 * Method used to view department details
	 * 
	 * @param department
	 * @return
	 * @throws CommonException
	 */
	@Transactional
	public List<DepartmentVO> search(DepartmentVO department) throws CommonException {
		List<Object[]> departmentEntity = new ArrayList<Object[]>();
		try {

			departmentEntity = departmentDao.search(department);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("DbFailure"));
		}
		List<DepartmentVO> departmentVOList = new ArrayList<DepartmentVO>();

		for (Object[] object : departmentEntity) {

			DepartmentVO departmentVO = new DepartmentVO();

			if (null != (Integer) object[0]) {
				departmentVO.setDepartmentId((Integer) object[0]);

			}
			if (null != (String) object[1]) {

				departmentVO.setDepartmentName((String) object[1]);
			}
			if (null != (Integer) ((Object[]) object)[2]) {
				departmentVO.setLocationId((Integer) object[2]);

			}

			if (null != (Integer) ((Object[]) object)[3]) {
				departmentVO.setSublocationId((Integer) object[3]);

			}

			if (null != (String) ((Object[]) object)[4]) {
				departmentVO.setDescription((String) object[4]);

			}

			if (null != (String) ((Object[]) object)[5]) {
				departmentVO.setLocationName((String) object[5]);

			}

			if (null != (String) ((Object[]) object)[6]) {
				departmentVO.setSublocationName((String) object[6]);

			}

			departmentVOList.add(departmentVO);

		}

		return departmentVOList;

	}

}
