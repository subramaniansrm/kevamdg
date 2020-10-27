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
import com.kevamdg.sr.dao.CountryDAO;
import com.kevamdg.sr.entity.CountryEntity;
import com.kevamdg.sr.repository.CountryRepository;
import com.kevamdg.sr.vo.AuthDetailsVO;
import com.kevamdg.sr.vo.CountryVO;

@Service
public class CountryService extends CommonAction<CountryVO> {

	Logger logger = LoggerFactory.getLogger(UserRoleService.class);

	@Autowired
	CountryDAO countryDao;

	@Autowired
	private CountryRepository coutryRepo;

	/**
	 * method to list country
	 * 
	 * @return countryList List<CountryVO>
	 * @throws CommonException
	 */
	@Transactional
	public List<CountryVO> getCountry() throws CommonException {
		List<CountryVO> countryList = new ArrayList<CountryVO>();

		try {
			List<CountryEntity> list = coutryRepo.getAll();

			for (CountryEntity object : list) {

				CountryVO country = new CountryVO();

				BeanUtils.copyProperties(object, country);

				countryList.add(country);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("dataFailure"));
		}
		return countryList;
	}

	/**
	 * method to list country
	 * 
	 * @return countryList List<CountryVO>
	 * @throws CommonException
	 */
	@Transactional
	public List<CountryVO> getSearch(CountryVO countryVO) throws CommonException {
		List<CountryVO> countryList = new ArrayList<CountryVO>();

		try {
			List<CountryEntity> list = countryDao.getSearch(countryVO);

			for (CountryEntity object : list) {

				CountryVO country = new CountryVO();

				BeanUtils.copyProperties(object, country);

				countryList.add(country);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("dataFailure"));
		}
		return countryList;
	}

	/**
	 * Method used to get the duplicate validation
	 * 
	 * @param countryVO
	 *            CountryVO
	 * @throws CommonException
	 */
	@Transactional
	public void duplicate(CountryVO countryVO) throws CommonException {

		try {
			int count = countryDao.duplicate(countryVO);
			if (count > 0) {
				throw new CommonException(getMessage("countryAlreadyExists"));
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
	 * method to add new Country
	 * 
	 * @param countryVO
	 *            CountryVO
	 * @throws CommonException
	 */
	@Transactional
	public void save(CountryVO countryVO,AuthDetailsVO authDetailsVo) throws CommonException {

		try {
			if (null != countryVO) {
				CountryEntity country = new CountryEntity();

				BeanUtils.copyProperties(countryVO, country);

				country.setDeleteFlag(CommonConstant.BOOLEAN_FALSE);
				country.setCreateBy(authDetailsVo.getUserId());
				country.setCreateDate(CommonConstant.getCalenderDate());
				country.setUpdateBy(authDetailsVo.getUserId());
				country.setUpdateDate(CommonConstant.getCalenderDate());

				coutryRepo.save(country);

			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("dataFailure"));
		}

	}

	/**
	 * This method is used to update the Country.
	 * 
	 * @param countryVO
	 *            CountryVO
	 * @throws CommonException
	 */
	@Transactional
	public void update(CountryVO countryVO,AuthDetailsVO authDetailsVo) throws CommonException {
		try {
			if (null != countryVO.getId()) {

				CountryEntity country = coutryRepo.findOne(countryVO.getId());

				if (null != country) {

					BeanUtils.copyProperties(countryVO, country);

					country.setUpdateBy(authDetailsVo.getUserId());
					country.setUpdateDate(CommonConstant.getCalenderDate());

					coutryRepo.save(country);

				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("dataFailure"));
		}

	}

	/**
	 * Method used to delete the country
	 * 
	 * @param countryVO
	 *            CountryVO
	 * @throws CommonException
	 */
	@Transactional
	public void delete(CountryVO countryVO,AuthDetailsVO authDetailsVo) throws CommonException {

		boolean country = false;

		List<String> codeList = new ArrayList<String>();

		try {
			Integer[] deleteItems = countryVO.getDeleteItem();
			for (int i = 0; i < deleteItems.length; i++) {

				boolean Type = false;

				// Type = deleteCode(deleteItems[i]);

				CountryEntity countryEntity = coutryRepo.findOne(deleteItems[i]);

				if (Type) {
					country = true;
					codeList.add(countryEntity.getCountry());
					continue;
				}

				countryEntity.setDeleteFlag(CommonConstant.BOOLEAN_TRUE);
				countryEntity.setUpdateBy(authDetailsVo.getUserId());
				countryEntity.setUpdateDate(CommonConstant.getCalenderDate());

				coutryRepo.save(countryEntity);
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
		if (country) {
			throw new CommonException(CommonConstant.format(getMessage("unUsedRecordOnlyBeDeleted"), codeList));

		}

	}

	/**
	 * Method used to view the country.
	 * 
	 * @param countryVO
	 *            CountryVO
	 * @return country CountryVO
	 * @throws CommonException 
	 */
	public CountryVO view(int id) throws CommonException {

		
		CountryVO country = new CountryVO();

		try{
		if (0 != id) {

			CountryEntity countryEntity = coutryRepo.findOne(id);

			BeanUtils.copyProperties(countryEntity, country);
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
	
		return country;
	}

}
