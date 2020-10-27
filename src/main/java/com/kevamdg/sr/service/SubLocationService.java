package com.kevamdg.sr.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kevamdg.sr.config.CommonException;
import com.kevamdg.sr.constants.CommonConstant;
import com.kevamdg.sr.controller.CommonAction;
import com.kevamdg.sr.dao.CommonDao;
import com.kevamdg.sr.dao.SubLocationDAO;
import com.kevamdg.sr.entity.SubLocationEntity;
import com.kevamdg.sr.repository.SubLocationRepository;
import com.kevamdg.sr.vo.AuthDetailsVO;
import com.kevamdg.sr.vo.SubLocationVO;

@Service
public class SubLocationService extends CommonAction<SubLocationVO> {

	Logger logger = LoggerFactory.getLogger(SubLocationService.class);

	@Autowired
	CommonDao commonDAO;

	@Autowired
	private SubLocationRepository subLocationRepository;

	@Autowired
	private SubLocationDAO subLocationDao;

	/**
	 * Method is for get all details
	 * 
	 * @return subLocationVoList List<SubLocationVo>
	 * @throws ApplicationException
	 * @throws CommonException
	 */
	@Transactional
	public List<SubLocationVO> getAll() throws  CommonException {

		List<SubLocationVO> subLocationVoList = new ArrayList<SubLocationVO>();
		try {
			SubLocationVO subLocationVo = null;

			List<Object[]> subLocationEntityList = subLocationRepository.getAll();

			for (Object[] object : subLocationEntityList) {

				subLocationVo = new SubLocationVO();

				if (null != (String) object[0]) {
					subLocationVo.setSublocationId((int) object[4]);
					subLocationVo.setSubLocationCode((String) object[0]);
				}
				if (null != (String) object[1]) {

					subLocationVo.setSubLocationName((String) object[1]);
				}
				if (null != (String) ((Object[]) object)[3]) {
					subLocationVo.setId((int) object[5]);
					subLocationVo.setUserLocationName((String) object[3]);
				}
				if ((int) object[2] == 1) {
					subLocationVo.setSubLocationIsActive(true);
				} else if ((int) object[2] == 0) {
					subLocationVo.setSubLocationIsActive(false);
				}
				if (subLocationVo.isSubLocationIsActive()) {
					subLocationVo.setStatus(CommonConstant.Active);
				} else {
					subLocationVo.setStatus(CommonConstant.InActive);
				}

				subLocationVoList.add(subLocationVo);
			}

			return subLocationVoList;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("dataFailure"));
		}

	}

	@Transactional
	public void duplicateSubLocation(SubLocationVO subLocationVo) throws CommonException {

		try {
			int count = subLocationDao.duplicateSubLocation(subLocationVo);
			if (count > 0) {
				throw new CommonException(getMessage("subLocationAlreadyExists"));
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
	 * Method is to create sub location.
	 * 
	 * @param subLocationVo
	 *            SubLocationVo
	 * @throws CommonException
	 */
	@Transactional
	public void create(SubLocationVO subLocationVo,AuthDetailsVO authDetailsVo) throws CommonException {

		SubLocationEntity subLocationEntity = new SubLocationEntity();
		String code = null;
		try {
			code = commonDAO.findAutoGenericCode(CommonConstant.SubLocation);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("autoCodeGenerationFailure"));
		}
		try {
			if (subLocationVo != null) {

				subLocationEntity.setSubLocationCode(code);
				subLocationEntity.setSubLocationName(subLocationVo.getSubLocationName());
				subLocationEntity.setId(subLocationVo.getId());
				subLocationEntity.setSubLocationIsActive(subLocationVo.isSubLocationIsActive());
				subLocationEntity.setCreateBy(authDetailsVo.getUserId());
				subLocationEntity.setCreateDate(CommonConstant.getCalenderDate());
				subLocationEntity.setUpdateBy(authDetailsVo.getUserId());
				subLocationEntity.setUpdateDate(CommonConstant.getCalenderDate());
				subLocationRepository.save(subLocationEntity);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("dataFailure"));
		}
	}

	/**
	 * Method is to update sub location.
	 * 
	 * @param subLocationVo
	 *            SubLocationVo
	 * @throws CommonException
	 */
	@Transactional
	public void updateSublocation(SubLocationVO subLocationVo,AuthDetailsVO authDetailsVo) throws CommonException {
		SubLocationEntity subLocationEntity = new SubLocationEntity();
		boolean subLocation = false;
		if (subLocationVo.isSubLocationIsActive() == false) {

			// subLocation =
			// deleteSubLocation(subLocationVo.getSublocationId());
		}
		if (subLocation) {
			throw new CommonException(getMessage("cannotsetinactive"));
		}

		try {

			subLocationEntity = subLocationRepository.findOne(subLocationVo.getSublocationId());

			if (subLocationEntity != null) {

				subLocationVo.setSubLocationCode(subLocationEntity.getSubLocationCode());
				BeanUtils.copyProperties(subLocationVo, subLocationEntity);
				subLocationEntity.setUpdateBy(authDetailsVo.getUserId());
				subLocationEntity.setUpdateDate(CommonConstant.getCalenderDate());

				subLocationRepository.save(subLocationEntity);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("dataFailure"));

		}
	}

	/**
	 * Method is to view sub location
	 * 
	 * @param subLocationVo
	 *            SubLocationVo
	 * @return subLocation SubLocationVo
	 * @throws CommonException
	 */
	@Transactional
	public SubLocationVO viewSublocation(int id) throws CommonException {
		try {
			Object[] object = subLocationDao.findBySubId(id);

			SubLocationVO subLocation = new SubLocationVO();

			if (null != (String) object[0]) {
				subLocation.setSublocationId((int) object[4]);
				subLocation.setSubLocationCode((String) object[0]);
			}
			if (null != (String) object[1]) {

				subLocation.setSubLocationName((String) object[1]);
			}
			if (null != (String) ((Object[]) object)[3]) {
				subLocation.setId((int) object[5]);
				subLocation.setUserLocationName((String) object[3]);
			}
			if ((int) object[2] == 1) {
				subLocation.setSubLocationIsActive(true);
			} else if ((int) object[2] == 0) {
				subLocation.setSubLocationIsActive(false);
			}

			return subLocation;
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
	 * Method is for Search Sub location
	 * 
	 * @param subLocationVoSearch
	 *            SubLocationVo
	 * @return subLocationVoList List<SubLocationVo>
	 * @throws BusinessException
	 */
	@Transactional
	public List<SubLocationVO> getAllSearch(SubLocationVO subLocationVoSearch) throws CommonException {
		try {
			List<SubLocationVO> subLocationVoList = new ArrayList<SubLocationVO>();

			List<Object[]> subLocationEntityList = null;

			subLocationEntityList = subLocationDao.getAllSearch(subLocationVoSearch);

			for (Object[] object : subLocationEntityList) {

				SubLocationVO subLocationVo = new SubLocationVO();

				if (null != (String) object[0]) {
					subLocationVo.setSublocationId((int) object[4]);
					subLocationVo.setSubLocationCode((String) object[0]);
				}
				if (null != (String) object[1]) {

					subLocationVo.setSubLocationName((String) object[1]);
				}
				if (null != (String) ((Object[]) object)[3]) {
					subLocationVo.setId((int) object[5]);
					subLocationVo.setUserLocationName((String) object[3]);
				}
				if ((int) object[2] == 1) {
					subLocationVo.setSubLocationIsActive(true);
				} else if ((int) object[2] == 0) {
					subLocationVo.setSubLocationIsActive(false);
				}
				if (subLocationVo.isSubLocationIsActive()) {
					subLocationVo.setStatus(CommonConstant.Active);
				} else {
					subLocationVo.setStatus(CommonConstant.InActive);
				}

				subLocationVoList.add(subLocationVo);
			}

			return subLocationVoList;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("dataFailure"));
		}
	}

	/**
	 * This method is used to delete values in subLocation.
	 * 
	 * 
	 * @param SubLocationVo
	 *            SubLocationVo
	 * 
	 */
	@Transactional
	public void deletesublocation(SubLocationVO subLocationVo,AuthDetailsVO authDetailsVo) throws CommonException {
		SubLocationEntity subLocationEntity = new SubLocationEntity();

		boolean subLocation = false;

		boolean subLocationDelete = false;

		List<String> codeList = new ArrayList<String>();

		for (Integer id : subLocationVo.getDeleteItem()) {

			// subLocationDelete = findUserLocation(id);
			try {

				subLocationEntity = findId(id);

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
			if (subLocationDelete) {
				subLocation = true;
				codeList.add(subLocationEntity.getSubLocationName());
				continue;
			}

			subLocationEntity.setDeleteFlag(true);
			subLocationEntity.setUpdateBy(authDetailsVo.getUserId());
			subLocationEntity.setUpdateDate(CommonConstant.getCalenderDate());
			try {
				subLocationRepository.save(subLocationEntity);

			} catch (Exception e) {
				logger.error(e.getMessage());
				throw new CommonException(getMessage("dataFailure"));
			}
		}

		if (subLocation) {
			throw new CommonException(CommonConstant.format(getMessage("unUsedRecordOnlyBeDeleted"), codeList));

		}
	}

	private SubLocationEntity findId(Integer id) {
		SubLocationEntity subLocationEntity = (SubLocationEntity) subLocationRepository.findOne(id);

		return subLocationEntity;
	}

}
