package com.kevamdg.sr.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevamdg.sr.config.CommonException;
import com.kevamdg.sr.constants.CommonConstant;
import com.kevamdg.sr.controller.CommonAction;
import com.kevamdg.sr.dao.SalesOrgDAO;
import com.kevamdg.sr.entity.SalesOrgEntity;
import com.kevamdg.sr.repository.SalesOrgRepository;
import com.kevamdg.sr.vo.AuthDetailsVO;
import com.kevamdg.sr.vo.SalesOrgVO;

@Service
public class SalesOrgService extends CommonAction<SalesOrgVO> {

	private static final Logger logger = LogManager.getLogger(SalesOrgService.class);

	
	@Autowired
	private SalesOrgDAO salesOrgDAO;

	@Autowired
	private SalesOrgRepository salesOrgRepo;

	@Transactional
	public List<SalesOrgVO> getAll() throws CommonException {
		List<SalesOrgVO> salesOrgVOList = new ArrayList<SalesOrgVO>();

		// int id = AuthInstance.getUserVO().getId();
		try {
			List<SalesOrgEntity> list = salesOrgRepo.getAll();

			for (SalesOrgEntity object : list) {

				SalesOrgVO salesOrgVO = new SalesOrgVO();

				BeanUtils.copyProperties(object, salesOrgVO);

				if (salesOrgVO.isActiveFlag()) {
					salesOrgVO.setStatus(CommonConstant.Active);
				} else {
					salesOrgVO.setStatus(CommonConstant.InActive);
				}

				salesOrgVOList.add(salesOrgVO);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("dataFailure"));
		}
		return salesOrgVOList;
	}

	@Transactional
	public List<SalesOrgVO> search(SalesOrgVO salesOrg) throws CommonException {
		List<SalesOrgVO> salesOrgVOList = new ArrayList<SalesOrgVO>();

		try {
			List<SalesOrgEntity> list = salesOrgDAO.search(salesOrg);

			for (SalesOrgEntity object : list) {

				SalesOrgVO salesOrgVO = new SalesOrgVO();

				BeanUtils.copyProperties(object, salesOrgVO);

				if (salesOrgVO.isActiveFlag()) {
					salesOrgVO.setStatus(CommonConstant.Active);
				} else {
					salesOrgVO.setStatus(CommonConstant.InActive);
				}

				salesOrgVOList.add(salesOrgVO);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("dataFailure"));
		}
		return salesOrgVOList;
	}

	@Transactional
	public void duplicate(SalesOrgVO salesOrgVO) throws CommonException {

		try {
			int count = salesOrgDAO.duplicate(salesOrgVO);
			if (count > 0) {
				throw new CommonException(getMessage("salesAlreadyExists"));
			}
		} catch (CommonException e) {
			logger.error(e.getMessage());
			throw new CommonException(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("dataFailure"));
		}

	}

	@Transactional
	public void save(SalesOrgVO salesOrgVO, AuthDetailsVO authDetailsVo) throws CommonException {

		try {

			if (null != salesOrgVO) {
				SalesOrgEntity salesOrgEntity = new SalesOrgEntity();

				BeanUtils.copyProperties(salesOrgVO, salesOrgEntity);
				salesOrgEntity.setDeleteFlag(CommonConstant.BOOLEAN_FALSE);
				salesOrgEntity.setCreateBy(authDetailsVo.getUserId());
				salesOrgEntity.setCreateDate(CommonConstant.getCalenderDate());
				salesOrgEntity.setUpdateBy(authDetailsVo.getUserId());
				salesOrgEntity.setUpdateDate(CommonConstant.getCalenderDate());

				salesOrgRepo.save(salesOrgEntity);

			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("dataFailure"));
		}

	}

	@Transactional
	public void update(SalesOrgVO salesOrgVO, AuthDetailsVO authDetailsVo) throws CommonException {
		try {
			if (null != salesOrgVO.getId()) {

				SalesOrgEntity salesOrgEntity = salesOrgRepo.findOne(salesOrgVO.getId());

				if (null != salesOrgEntity) {

					BeanUtils.copyProperties(salesOrgVO, salesOrgEntity);

					salesOrgEntity.setUpdateBy(authDetailsVo.getUserId());
					salesOrgEntity.setUpdateDate(CommonConstant.getCalenderDate());

					salesOrgRepo.save(salesOrgEntity);

				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("dataFailure"));
		}

	}

	public SalesOrgVO view(int id) throws CommonException {

		SalesOrgVO salesOrgVO = new SalesOrgVO();

		try {
			if (0 != id) {

				SalesOrgEntity salesOrgEntity = salesOrgRepo.findOne(id);

				BeanUtils.copyProperties(salesOrgEntity, salesOrgVO);
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

		return salesOrgVO;
	}

	@Transactional
	public void delete(SalesOrgVO salesOrgVO, AuthDetailsVO authDetailsVo) throws CommonException {

		boolean salesOrg = false;

		List<String> codeList = new ArrayList<String>();

		try {
			Integer[] deleteItems = salesOrgVO.getDeleteItem();
			for (int i = 0; i < deleteItems.length; i++) {

				boolean Type = false;

				// Type = deleteCode(deleteItems[i]);

				SalesOrgEntity salesOrgEntity = salesOrgRepo.findOne(deleteItems[i]);

				if (Type) {
					salesOrg = true;
					codeList.add(salesOrgEntity.getSalesOrgName());
					continue;
				}

				salesOrgEntity.setDeleteFlag(CommonConstant.BOOLEAN_TRUE);
				salesOrgEntity.setUpdateBy(authDetailsVo.getUserId());
				salesOrgEntity.setUpdateDate(CommonConstant.getCalenderDate());

				salesOrgRepo.save(salesOrgEntity);
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
		if (salesOrg) {
			throw new CommonException(CommonConstant.format(getMessage("unUsedRecordOnlyBeDeleted"), codeList));

		}

	}

}
