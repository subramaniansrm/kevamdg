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
import com.kevamdg.sr.dao.StateDao;
import com.kevamdg.sr.entity.StateEntity;
import com.kevamdg.sr.repository.StateRepository;
import com.kevamdg.sr.vo.AuthDetailsVO;
import com.kevamdg.sr.vo.StateVO;

/**
 * Class used to list,create,update,delete,search sub location details
 * 
 * @author raathikaabm
 *
 */
@Service
public class StateService extends CommonAction<StateVO> {

	Logger logger = LoggerFactory.getLogger(StateService.class);

	@Autowired
	StateRepository stateRepository;

	@Autowired
	StateDao stateDao;

	/**
	 * Method used to get all state details
	 * 
	 * @return
	 * @throws ApplicationException
	 * @throws CommonException
	 */
	@Transactional
	public List<StateVO> getAllList() throws  CommonException {

		List<StateVO> stateVOList = new ArrayList<StateVO>();
		List<Object[]> stateEntityList = new ArrayList<Object[]>();
		try {
			stateEntityList = stateDao.getAllList();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("DbFailure"));

		}

		for (Object[] object : stateEntityList) {
			StateVO stateVO = new StateVO();

			if (null != (Integer) object[0]) {
				stateVO.setStateId((Integer) object[0]);

			}

			if (null != (String) object[1]) {
				stateVO.setStateName((String) object[1]);

			}

			if (null != (Integer) object[2]) {
				stateVO.setSysAppId((Integer) object[2]);

			}
			if (null != (Integer) object[3]) {
				stateVO.setCountryId((Integer) object[3]);

			}
			if (null != (Boolean) object[4]) {
				stateVO.setDeleteFlag((Boolean) object[4]);

			}
			if (null != (Date) object[5]) {
				stateVO.setCreatedDate((Date) object[5]);

			}

			if (null != (Integer) object[6]) {
				stateVO.setCreatedBy((Integer) object[6]);

			}

			if (null != (Date) object[7]) {
				stateVO.setUpdatedDate((Date) object[7]);

			}
			if (null != (Integer) object[8]) {
				stateVO.setUpdatedBy((Integer) object[8]);

			}
			if (null != (String) object[9]) {
				stateVO.setCountryName((String) object[9]);

			}

			stateVOList.add(stateVO);

		}
		return stateVOList;

	}

	/**
	 * Method used to create state destails
	 * 
	 * @param stateVO
	 * @throws CommonException
	 */
	@Transactional
	public void create(StateVO stateVO,AuthDetailsVO authDetailsVo) throws CommonException {

		StateEntity stateEntity = new StateEntity();

		if (stateVO.getStateId() != null) {
			stateEntity.setStateId(stateVO.getStateId());
		}

		if (stateVO.getStateName() != null) {
			stateEntity.setStateName(stateVO.getStateName());
		}
		if (stateVO.getCountryId() != null) {
			stateEntity.setCountryId(stateVO.getCountryId());
		}
		stateEntity.setCreateBy(authDetailsVo.getUserId());
		stateEntity.setCreateDate(CommonConstant.getCalenderDate());
		stateEntity.setUpdateBy(authDetailsVo.getUserId());
		stateEntity.setUpdateDate(CommonConstant.getCalenderDate());
		try {
			stateRepository.save(stateEntity);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("DbFailure"));

		}
	}

	/**
	 * Method used to update state details
	 * 
	 * @param stateVO
	 * @throws CommonException
	 */

	@Transactional
	public void update(StateVO stateVO) throws CommonException {
		StateEntity stateEntity = new StateEntity();

		try {
			stateEntity = stateRepository.findOne(stateVO.getStateId());
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("DbFailure"));

		}
		if (stateVO.getStateId() != null) {
			stateEntity.setStateId(stateVO.getStateId());
		}

		if (stateVO.getStateName() != null) {
			stateEntity.setStateName(stateVO.getStateName());
		}
		if (stateVO.getCountryId() != null) {
			stateEntity.setCountryId(stateVO.getCountryId());
		}
		try {
			stateRepository.save(stateEntity);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("DbFailure"));

		}

	}

	/**
	 * Method used to delete state details
	 * 
	 * @param stateVO
	 * @throws CommonException
	 */

	@Transactional
	public void delete(StateVO stateVO) throws CommonException {
		StateEntity stateEntity = new StateEntity();

		for (Integer id : stateVO.getDeleteItem()) {
			try {
				stateEntity = stateRepository.findOne(id);
			} catch (Exception e) {
				logger.error(e.getMessage());
				throw new CommonException(getMessage("DbFailure"));

			}

			if (stateEntity.getStateId() != null) {
				stateEntity.setDeleteFlag(true);
			}
			try {
				stateRepository.save(stateEntity);
			} catch (Exception e) {
				logger.error(e.getMessage());
				throw new CommonException(getMessage("DbFailure"));

			}

		}
	}

	/**
	 * Metjod used to view state details
	 * 
	 * @param state
	 * @return
	 * @throws CommonException
	 */
	@Transactional
	public StateVO view(int id) throws CommonException {

		List<Object[]> stateEntity = new ArrayList<Object[]>();

		try {
			stateEntity = stateDao.view(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("DbFailure"));

		}
		StateVO stateVO = new StateVO();

		for (Object[] object : stateEntity) {

			if (null != (Integer) object[0]) {
				stateVO.setStateId((Integer) object[0]);

			}

			if (null != (String) object[1]) {
				stateVO.setStateName((String) object[1]);

			}

			if (null != (Integer) object[2]) {
				stateVO.setSysAppId((Integer) object[2]);

			}
			if (null != (Integer) object[3]) {
				stateVO.setCountryId((Integer) object[3]);

			}
			if (null != (Boolean) object[4]) {
				stateVO.setDeleteFlag((Boolean) object[4]);

			}
			if (null != (Date) object[5]) {
				stateVO.setCreatedDate((Date) object[5]);

			}

			if (null != (Integer) object[6]) {
				stateVO.setCreatedBy((Integer) object[6]);

			}

			if (null != (Date) object[7]) {
				stateVO.setUpdatedDate((Date) object[7]);

			}
			if (null != (Integer) object[8]) {
				stateVO.setUpdatedBy((Integer) object[8]);

			}
			if (null != (String) object[9]) {
				stateVO.setCountryName((String) object[9]);

			}

		}
		return stateVO;

	}

	/**
	 * Method used to search state details
	 * 
	 * @param stateVO
	 * @return
	 */
	@Transactional
	public List<StateVO> search(StateVO state) throws CommonException {

		List<StateVO> stateVOList = new ArrayList<StateVO>();

		List<Object[]> stateList = null;

		try {

			stateList = stateDao.search(state);

		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("DbFailure"));
		}

		for (Object[] object : stateList) {
			StateVO stateVO = new StateVO();

			if (null != (Integer) object[0]) {
				stateVO.setStateId((Integer) object[0]);

			}

			if (null != (String) object[1]) {
				stateVO.setStateName((String) object[1]);

			}

			if (null != (Integer) object[2]) {
				stateVO.setSysAppId((Integer) object[2]);

			}
			if (null != (Integer) object[3]) {
				stateVO.setCountryId((Integer) object[3]);

			}
			if (null != (Boolean) object[4]) {
				stateVO.setDeleteFlag((Boolean) object[4]);

			}
			if (null != (Date) object[5]) {
				stateVO.setCreatedDate((Date) object[5]);

			}

			if (null != (Integer) object[6]) {
				stateVO.setCreatedBy((Integer) object[6]);

			}

			if (null != (Date) object[7]) {
				stateVO.setUpdatedDate((Date) object[7]);

			}
			if (null != (Integer) object[8]) {
				stateVO.setUpdatedBy((Integer) object[8]);

			}
			if (null != (String) object[9]) {
				stateVO.setCountryName((String) object[9]);

			}

			stateVOList.add(stateVO);

		}
		return stateVOList;

	}

}
