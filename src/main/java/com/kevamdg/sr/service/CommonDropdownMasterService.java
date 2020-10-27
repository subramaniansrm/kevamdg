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
import org.springframework.stereotype.Service;

import com.kevamdg.sr.config.CommonException;
import com.kevamdg.sr.constants.CommonConstant;
import com.kevamdg.sr.controller.CommonAction;
import com.kevamdg.sr.dao.CommonDropdownMasterDAO;
import com.kevamdg.sr.entity.CommonDropdownEntity;
import com.kevamdg.sr.entity.CommonScreenFieldEntity;
import com.kevamdg.sr.entity.SalesOrgEntity;
import com.kevamdg.sr.repository.CommonDropdownRepository;
import com.kevamdg.sr.repository.CommonScreenFieldRepository;
import com.kevamdg.sr.repository.SalesOrgRepository;
import com.kevamdg.sr.vo.AuthDetailsVO;
import com.kevamdg.sr.vo.DropdownCommonVO;

@Service
public class CommonDropdownMasterService extends CommonAction<DropdownCommonVO> {

	Logger logger = LoggerFactory.getLogger(CommonDropdownMasterService.class);

	@Autowired
	CommonDropdownMasterDAO commonDropdownMasterDao;

	@Autowired
	private CommonDropdownRepository commonDropdownRepository;

	@Autowired
	private CommonScreenFieldRepository commonScreenFieldRepository;
	
	@Autowired
	private SalesOrgRepository salesOrgRepository;

	@Transactional
	public List<DropdownCommonVO> getAll() throws CommonException {

		List<DropdownCommonVO> dropdownCommonVOList = new ArrayList<DropdownCommonVO>();

		try {
			List<Object[]> list = commonDropdownRepository.getAll();

			for (Object[] object : list) {

				DropdownCommonVO dropdownCommonVO = new DropdownCommonVO();

				if (null != ((Object[]) object)[0]) {
					dropdownCommonVO.setId((int) ((Object[]) object)[0]);
				}
				if (null != ((Object[]) object)[1]) {
					dropdownCommonVO.setDropdownCode((String) ((Object[]) object)[1]);
				}
				if (null != ((Object[]) object)[2]) {
					dropdownCommonVO.setDropdownName((String) ((Object[]) object)[2]);
				}
				if (null != ((Object[]) object)[3]) {
					dropdownCommonVO.setActiveFlag((boolean) ((Object[]) object)[3]);
				}
				if (null != ((Object[]) object)[4]) {
					dropdownCommonVO.setFieldName((String) ((Object[]) object)[4]);
				}

				if (dropdownCommonVO.isActiveFlag()) {
					dropdownCommonVO.setStatus(CommonConstant.Active);
				} else {
					dropdownCommonVO.setStatus(CommonConstant.InActive);
				}
				if (null != ((Object[]) object)[5]) {
					dropdownCommonVO.setSalesOrgName((String) ((Object[]) object)[5]);
				}

				dropdownCommonVOList.add(dropdownCommonVO);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("dataFailure"));
		}
		return dropdownCommonVOList;
	}

	@Transactional
	public List<DropdownCommonVO> search(DropdownCommonVO dropdownCommonVO) throws CommonException {

		List<DropdownCommonVO> dropdownCommonVOList = new ArrayList<DropdownCommonVO>();

		try {
			List<Object[]> list = commonDropdownMasterDao.search(dropdownCommonVO);

			for (Object[] object : list) {

				DropdownCommonVO dropdownCommon = new DropdownCommonVO();

				if (null != ((Object[]) object)[0]) {
					dropdownCommonVO.setId((int) ((Object[]) object)[0]);
				}
				if (null != ((Object[]) object)[1]) {
					dropdownCommonVO.setDropdownCode((String) ((Object[]) object)[1]);
				}
				if (null != ((Object[]) object)[2]) {
					dropdownCommonVO.setDropdownName((String) ((Object[]) object)[2]);
				}
				if (null != ((Object[]) object)[3]) {
					dropdownCommonVO.setActiveFlag((boolean) ((Object[]) object)[3]);
				}
				if (null != ((Object[]) object)[4]) {
					dropdownCommonVO.setFieldName((String) ((Object[]) object)[4]);
				}

				if (dropdownCommonVO.isActiveFlag()) {
					dropdownCommonVO.setStatus(CommonConstant.Active);
				} else {
					dropdownCommonVO.setStatus(CommonConstant.InActive);
				}
				if (null != ((Object[]) object)[5]) {
					dropdownCommonVO.setSalesOrgName((String) ((Object[]) object)[5]);
				}
				dropdownCommonVOList.add(dropdownCommonVO);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("dataFailure"));
		}
		return dropdownCommonVOList;
	}

	@Transactional
	public void duplicate(DropdownCommonVO dropdownCommonVO) throws CommonException {

		try {
			int count = commonDropdownMasterDao.duplicate(dropdownCommonVO);
			if (count > 0) {
				throw new CommonException(getMessage("cdmAlreadyExists"));
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
	public void save(DropdownCommonVO dropdownCommonVO, AuthDetailsVO authDetailsVo) throws CommonException {

		try {

			if (null != dropdownCommonVO) {
				CommonDropdownEntity commonDropdownEntity = new CommonDropdownEntity();

				BeanUtils.copyProperties(dropdownCommonVO, commonDropdownEntity);
				commonDropdownEntity.setDeleteFlag(CommonConstant.BOOLEAN_FALSE);
				commonDropdownEntity.setCreateBy(authDetailsVo.getUserId());
				commonDropdownEntity.setCreateDate(CommonConstant.getCalenderDate());
				commonDropdownEntity.setUpdateBy(authDetailsVo.getUserId());
				commonDropdownEntity.setUpdateDate(CommonConstant.getCalenderDate());

				commonDropdownRepository.save(commonDropdownEntity);

			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("dataFailure"));
		}

	}

	@Transactional
	public void update(DropdownCommonVO dropdownCommonVO, AuthDetailsVO authDetailsVo) throws CommonException {
		try {
			if (null != dropdownCommonVO.getId()) {

				CommonDropdownEntity commonDropdownEntity = commonDropdownRepository.findOne(dropdownCommonVO.getId());

				if (null != commonDropdownEntity) {

					BeanUtils.copyProperties(dropdownCommonVO, commonDropdownEntity);

					commonDropdownEntity.setUpdateBy(authDetailsVo.getUserId());
					commonDropdownEntity.setUpdateDate(CommonConstant.getCalenderDate());

					commonDropdownRepository.save(commonDropdownEntity);

				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("dataFailure"));
		}

	}

	public DropdownCommonVO view(int id) throws CommonException {

		DropdownCommonVO dropdownCommonVO = new DropdownCommonVO();

		try {
			if (0 != id) {

				CommonDropdownEntity commonDropdownEntity = commonDropdownRepository.findOne(id);

				BeanUtils.copyProperties(commonDropdownEntity, dropdownCommonVO);

				CommonScreenFieldEntity commonScreenFieldEntity = commonScreenFieldRepository
						.findOne(dropdownCommonVO.getFieldId());

				dropdownCommonVO.setFieldName(commonScreenFieldEntity.getScreenFieldName());
				
				if(null != commonDropdownEntity.getSalesOrgId()){
					
					SalesOrgEntity sales = salesOrgRepository.findOne(commonDropdownEntity.getSalesOrgId());
					
					dropdownCommonVO.setSalesOrgName(sales.getSalesOrgName());
				}
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

		return dropdownCommonVO;
	}

	@Transactional
	public void delete(DropdownCommonVO dropdownCommonVO, AuthDetailsVO authDetailsVo) throws CommonException {

		boolean dropdownCommon = false;

		List<String> codeList = new ArrayList<String>();

		try {
			Integer[] deleteItems = dropdownCommonVO.getDeleteItem();
			for (int i = 0; i < deleteItems.length; i++) {

				boolean Type = false;

				// Type = deleteCode(deleteItems[i]);

				CommonDropdownEntity commonDropdownEntity = commonDropdownRepository.findOne(deleteItems[i]);

				if (Type) {
					dropdownCommon = true;
					codeList.add(commonDropdownEntity.getDropdownName());
					continue;
				}

				commonDropdownEntity.setDeleteFlag(CommonConstant.BOOLEAN_TRUE);
				commonDropdownEntity.setUpdateBy(authDetailsVo.getUserId());
				commonDropdownEntity.setUpdateDate(CommonConstant.getCalenderDate());

				commonDropdownRepository.save(commonDropdownEntity);
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
		if (dropdownCommon) {
			throw new CommonException(CommonConstant.format(getMessage("unUsedRecordOnlyBeDeleted"), codeList));

		}

	}

}
