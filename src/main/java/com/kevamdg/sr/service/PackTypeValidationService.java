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
import com.kevamdg.sr.dao.PackTypeValidationDAO;
import com.kevamdg.sr.entity.PackTypeValidationEntity;
import com.kevamdg.sr.repository.PackTypeValidayionRepository;
import com.kevamdg.sr.vo.AuthDetailsVO;
import com.kevamdg.sr.vo.PackTypeValidationVO;

@Service
public class PackTypeValidationService extends CommonAction<PackTypeValidationVO> {

	private static final Logger logger = LogManager.getLogger(PackTypeValidationService.class);

	@Autowired
	private PackTypeValidationDAO packTypeValidationDAO;

	@Autowired
	private PackTypeValidayionRepository packTypeValidayionRepository;

	@Transactional
	public List<PackTypeValidationVO> getAll() throws CommonException {
		List<PackTypeValidationVO> packTypeValidationVOList = new ArrayList<PackTypeValidationVO>();

		// int id = AuthInstance.getUserVO().getId();
		try {
			List<Object[]> list = packTypeValidationDAO.getAll();

			for (Object[] obj : list) {

				PackTypeValidationVO packTypeValidationVO = new PackTypeValidationVO();

				if (null != ((Object[]) obj)[0]) {
					packTypeValidationVO.setId((int) ((Object[]) obj)[0]);
				}
				if (null != ((Object[]) obj)[1]) {
					packTypeValidationVO.setPackTypeName((String) ((Object[]) obj)[1]);
				}
				if (null != ((Object[]) obj)[2]) {
					packTypeValidationVO.setSalesOrgName((String) ((Object[]) obj)[2]);
				}
				if (null != ((Object[]) obj)[3]) {
					packTypeValidationVO.setPackSizeName((String) ((Object[]) obj)[3]);
				}
				if (null != ((Object[]) obj)[4]) {
					packTypeValidationVO.setActiveFlag((boolean) ((Object[]) obj)[4]);
				}

				if (packTypeValidationVO.isActiveFlag()) {
					packTypeValidationVO.setStatus(CommonConstant.Active);
				} else {
					packTypeValidationVO.setStatus(CommonConstant.InActive);
				}

				packTypeValidationVOList.add(packTypeValidationVO);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("dataFailure"));
		}
		return packTypeValidationVOList;
	}

	@Transactional
	public List<PackTypeValidationVO> search(PackTypeValidationVO packTypeValidation) throws CommonException {
		List<PackTypeValidationVO> packTypeValidationVOList = new ArrayList<PackTypeValidationVO>();

		// int id = AuthInstance.getUserVO().getId();
		try {
			List<Object[]> list = packTypeValidationDAO.search(packTypeValidation);

			for (Object[] obj : list) {

				PackTypeValidationVO packTypeValidationVO = new PackTypeValidationVO();

				if (null != ((Object[]) obj)[0]) {
					packTypeValidationVO.setId((int) ((Object[]) obj)[0]);
				}
				if (null != ((Object[]) obj)[1]) {
					packTypeValidationVO.setPackTypeName((String) ((Object[]) obj)[1]);
				}
				if (null != ((Object[]) obj)[2]) {
					packTypeValidationVO.setSalesOrgName((String) ((Object[]) obj)[2]);
				}
				if (null != ((Object[]) obj)[3]) {
					packTypeValidationVO.setPackSizeName((String) ((Object[]) obj)[3]);
				}
				if (null != ((Object[]) obj)[4]) {
					packTypeValidationVO.setActiveFlag((boolean) ((Object[]) obj)[4]);
				}

				if (packTypeValidationVO.isActiveFlag()) {
					packTypeValidationVO.setStatus(CommonConstant.Active);
				} else {
					packTypeValidationVO.setStatus(CommonConstant.InActive);
				}

				packTypeValidationVOList.add(packTypeValidationVO);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("dataFailure"));
		}
		return packTypeValidationVOList;
	}

	@Transactional
	public void save(PackTypeValidationVO packTypeValidationVO, AuthDetailsVO authDetailsVo) throws CommonException {

		try {

			if (null != packTypeValidationVO) {
				PackTypeValidationEntity packTypeValidationEntity = new PackTypeValidationEntity();

				BeanUtils.copyProperties(packTypeValidationVO, packTypeValidationEntity);
				packTypeValidationEntity.setDeleteFlag(CommonConstant.BOOLEAN_FALSE);
				packTypeValidationEntity.setCreateBy(authDetailsVo.getUserId());
				packTypeValidationEntity.setCreateDate(CommonConstant.getCalenderDate());
				packTypeValidationEntity.setUpdateBy(authDetailsVo.getUserId());
				packTypeValidationEntity.setUpdateDate(CommonConstant.getCalenderDate());

				packTypeValidayionRepository.save(packTypeValidationEntity);

			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("dataFailure"));
		}

	}

	@Transactional
	public void duplicate(PackTypeValidationVO packTypeValidationVO) throws CommonException {

		try {
			int count = packTypeValidationDAO.duplicate(packTypeValidationVO);
			if (count > 0) {
				throw new CommonException(getMessage("packTypeAlreadyExists"));
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
	public void update(PackTypeValidationVO packTypeValidationVO, AuthDetailsVO authDetailsVo) throws CommonException {
		try {
			if (null != packTypeValidationVO.getId()) {

				PackTypeValidationEntity packTypeValidationEntity = packTypeValidayionRepository
						.findOne(packTypeValidationVO.getId());

				if (null != packTypeValidationEntity) {

					BeanUtils.copyProperties(packTypeValidationVO, packTypeValidationEntity);

					packTypeValidationEntity.setUpdateBy(authDetailsVo.getUserId());
					packTypeValidationEntity.setUpdateDate(CommonConstant.getCalenderDate());

					packTypeValidayionRepository.save(packTypeValidationEntity);

				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("dataFailure"));
		}

	}

	public PackTypeValidationVO view(Integer id) {

		Object[] obj = packTypeValidationDAO.view(id);

		PackTypeValidationVO packTypeValidationVO = new PackTypeValidationVO();

		if (null != ((Object[]) obj)[0]) {
			packTypeValidationVO.setId((int) ((Object[]) obj)[0]);
		}
		if (null != ((Object[]) obj)[1]) {
			packTypeValidationVO.setPackTypeName((String) ((Object[]) obj)[1]);
		}
		if (null != ((Object[]) obj)[2]) {
			packTypeValidationVO.setSalesOrgName((String) ((Object[]) obj)[2]);
		}
		if (null != ((Object[]) obj)[3]) {
			packTypeValidationVO.setPackSizeName((String) ((Object[]) obj)[3]);
		}
		if (null != ((Object[]) obj)[4]) {
			packTypeValidationVO.setActiveFlag((boolean) ((Object[]) obj)[4]);
		}
		if (null != ((Object[]) obj)[5]) {
			packTypeValidationVO.setSalesOrgId((Integer) ((Object[]) obj)[5]);
		}
		if (null != ((Object[]) obj)[6]) {
			packTypeValidationVO.setPackSize((Integer) ((Object[]) obj)[6]);
		}

		return packTypeValidationVO;

	}

	@Transactional
	public void delete(PackTypeValidationVO packTypeValidationVO, AuthDetailsVO authDetailsVo) throws CommonException {

		boolean packType = false;

		List<String> codeList = new ArrayList<String>();

		try {
			Integer[] deleteItems = packTypeValidationVO.getDeleteItem();
			for (int i = 0; i < deleteItems.length; i++) {

				boolean Type = false;

				// Type = deleteCode(deleteItems[i]);

				PackTypeValidationEntity packTypeValidationEntity = packTypeValidayionRepository
						.findOne(deleteItems[i]);

				if (Type) {
					packType = true;
					codeList.add(packTypeValidationEntity.getPackTypeName());
					continue;
				}

				packTypeValidationEntity.setDeleteFlag(CommonConstant.BOOLEAN_TRUE);
				packTypeValidationEntity.setUpdateBy(authDetailsVo.getUserId());
				packTypeValidationEntity.setUpdateDate(CommonConstant.getCalenderDate());

				packTypeValidayionRepository.save(packTypeValidationEntity);
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
		if (packType) {
			throw new CommonException(CommonConstant.format(getMessage("unUsedRecordOnlyBeDeleted"), codeList));

		}

	}

}
