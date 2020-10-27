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
import com.kevamdg.sr.dao.CustomerDAO;
import com.kevamdg.sr.entity.CustomerEntity;
import com.kevamdg.sr.repository.CustomerRepository;
import com.kevamdg.sr.vo.AuthDetailsVO;
import com.kevamdg.sr.vo.CustomerVO;

@Service
public class CustomerService extends CommonAction<CustomerVO> {

	private static final Logger logger = LogManager.getLogger(CustomerService.class);

	@Autowired
	private CustomerDAO customerDAO;

	@Autowired
	private CustomerRepository customerRepository;

	@Transactional
	public List<CustomerVO> getAll() throws CommonException {

		List<CustomerVO> customerVOList = new ArrayList<CustomerVO>();

		try {
			List<Object[]> list = customerDAO.getAll();

			for (Object[] object : list) {

				CustomerVO customerVO = new CustomerVO();

				if (null != ((Object[]) object)[0]) {
					customerVO.setId((int) ((Object[]) object)[0]);
				}
				if (null != ((Object[]) object)[1]) {
					customerVO.setCustomerCode((String) ((Object[]) object)[1]);
				}
				if (null != ((Object[]) object)[2]) {
					customerVO.setCustomerName((String) ((Object[]) object)[2]);
				}
				if (null != ((Object[]) object)[3]) {
					customerVO.setAreaNagar((String) ((Object[]) object)[3]);
				}
				if (null != ((Object[]) object)[4]) {
					customerVO.setParentCode((String) ((Object[]) object)[4]);
				}
				if (null != ((Object[]) object)[5]) {
					customerVO.setParentName((String) ((Object[]) object)[5]);
				}
				if (null != ((Object[]) object)[6]) {
					customerVO.setActiveFlag((boolean) ((Object[]) object)[6]);
				}
				if (null != ((Object[]) object)[7]) {
					customerVO.setSalesOrgName((String) ((Object[]) object)[7]);
				}
				if (null != ((Object[]) object)[8]) {
					customerVO.setDistributionName((String) ((Object[]) object)[8]);
				}
				if (null != ((Object[]) object)[9]) {
					customerVO.setPlantName((String) ((Object[]) object)[9]);
				}
				if (null != ((Object[]) object)[10]) {
					customerVO.setCityName((String) ((Object[]) object)[10]);
				}
				if (customerVO.isActiveFlag()) {
					customerVO.setStatus(CommonConstant.Active);
				} else {
					customerVO.setStatus(CommonConstant.InActive);
				}

				customerVOList.add(customerVO);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("dataFailure"));
		}
		return customerVOList;
	}

	@Transactional
	public List<CustomerVO> search(CustomerVO customer) throws CommonException {
		List<CustomerVO> customerList = new ArrayList<CustomerVO>();

		try {
			List<Object[]> list = customerDAO.search(customer);

			for (Object[] object : list) {

				CustomerVO customerVO = new CustomerVO();

				if (null != ((Object[]) object)[0]) {
					customerVO.setId((int) ((Object[]) object)[0]);
				}
				if (null != ((Object[]) object)[1]) {
					customerVO.setCustomerCode((String) ((Object[]) object)[1]);
				}
				if (null != ((Object[]) object)[2]) {
					customerVO.setCustomerName((String) ((Object[]) object)[2]);
				}
				if (null != ((Object[]) object)[3]) {
					customerVO.setAreaNagar((String) ((Object[]) object)[3]);
				}
				if (null != ((Object[]) object)[4]) {
					customerVO.setParentCode((String) ((Object[]) object)[4]);
				}
				if (null != ((Object[]) object)[5]) {
					customerVO.setParentName((String) ((Object[]) object)[5]);
				}
				if (null != ((Object[]) object)[6]) {
					customerVO.setActiveFlag((boolean) ((Object[]) object)[6]);
				}
				if (null != ((Object[]) object)[7]) {
					customerVO.setSalesOrgName((String) ((Object[]) object)[7]);
				}
				if (null != ((Object[]) object)[8]) {
					customerVO.setDistributionName((String) ((Object[]) object)[8]);
				}
				if (null != ((Object[]) object)[9]) {
					customerVO.setPlantName((String) ((Object[]) object)[9]);
				}
				if (null != ((Object[]) object)[10]) {
					customerVO.setCityName((String) ((Object[]) object)[10]);
				}

				if (customerVO.isActiveFlag()) {
					customerVO.setStatus(CommonConstant.Active);
				} else {
					customerVO.setStatus(CommonConstant.InActive);
				}

				customerList.add(customerVO);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("dataFailure"));
		}
		return customerList;
	}

	@Transactional
	public void duplicate(CustomerVO customerVO) throws CommonException {

		try {
			int count = customerDAO.duplicate(customerVO);
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

	@Transactional
	public void save(CustomerVO customerVO, AuthDetailsVO authDetailsVo) throws CommonException {

		try {

			if (null != customerVO) {
				CustomerEntity customerEntity = new CustomerEntity();

				BeanUtils.copyProperties(customerVO, customerEntity);

				customerEntity.setDeleteFlag(CommonConstant.BOOLEAN_FALSE);
				customerEntity.setCreateBy(authDetailsVo.getUserId());
				customerEntity.setCreateDate(CommonConstant.getCalenderDate());
				customerEntity.setUpdateBy(authDetailsVo.getUserId());
				customerEntity.setUpdateDate(CommonConstant.getCalenderDate());

				customerRepository.save(customerEntity);

			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("dataFailure"));
		}

	}

	@Transactional
	public void update(CustomerVO customerVO, AuthDetailsVO authDetailsVo) throws CommonException {
		try {
			if (null != customerVO.getId()) {

				CustomerEntity customerEntity = customerRepository.findOne(customerVO.getId());

				if (null != customerEntity) {

					BeanUtils.copyProperties(customerVO, customerEntity);

					customerEntity.setUpdateBy(authDetailsVo.getUserId());
					customerEntity.setUpdateDate(CommonConstant.getCalenderDate());

					customerRepository.save(customerEntity);

				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("dataFailure"));
		}

	}

	public CustomerVO view(int id) throws CommonException {

		CustomerVO customerVO = new CustomerVO();

		try {
			if (0 != id) {

				Object[] object = customerDAO.view(id);

				if (null != ((Object[]) object)[0]) {
					customerVO.setId((int) ((Object[]) object)[0]);
				}
				if (null != ((Object[]) object)[1]) {
					customerVO.setCustomerCode((String) ((Object[]) object)[1]);
				}
				if (null != ((Object[]) object)[2]) {
					customerVO.setCustomerName((String) ((Object[]) object)[2]);
				}
				if (null != ((Object[]) object)[3]) {
					customerVO.setAreaNagar((String) ((Object[]) object)[3]);
				}
				if (null != ((Object[]) object)[4]) {
					customerVO.setParentCode((String) ((Object[]) object)[4]);
				}
				if (null != ((Object[]) object)[5]) {
					customerVO.setParentName((String) ((Object[]) object)[5]);
				}
				if (null != ((Object[]) object)[6]) {
					customerVO.setActiveFlag((boolean) ((Object[]) object)[6]);
				}
				if (null != ((Object[]) object)[7]) {
					customerVO.setSalesOrgName((String) ((Object[]) object)[7]);
				}
				if (null != ((Object[]) object)[8]) {
					customerVO.setDistributionName((String) ((Object[]) object)[8]);
				}
				if (null != ((Object[]) object)[9]) {
					customerVO.setPlantName((String) ((Object[]) object)[9]);
				}
				if (null != ((Object[]) object)[10]) {
					customerVO.setCityName((String) ((Object[]) object)[10]);
				}
				if (null != ((Object[]) object)[11]) {
					customerVO.setSalesOrgId((int) ((Object[]) object)[11]);
				}
				if (null != ((Object[]) object)[12]) {
					customerVO.setPlantId((int) ((Object[]) object)[12]);
				}
				if (null != ((Object[]) object)[13]) {
					customerVO.setDistributionId((int) ((Object[]) object)[13]);
				}
				if (null != ((Object[]) object)[14]) {
					customerVO.setCityId((int) ((Object[]) object)[14]);
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

		return customerVO;
	}

	@Transactional
	public void delete(CustomerVO customerVO, AuthDetailsVO authDetailsVo) throws CommonException {

		boolean cus = false;

		List<String> codeList = new ArrayList<String>();

		try {
			Integer[] deleteItems = customerVO.getDeleteItem();
			for (int i = 0; i < deleteItems.length; i++) {

				boolean Type = false;

				// Type = deleteCode(deleteItems[i]);

				CustomerEntity customerEntity = customerRepository.findOne(deleteItems[i]);

				if (Type) {
					cus = true;
					codeList.add(customerEntity.getCustomerName());
					continue;
				}

				customerEntity.setDeleteFlag(CommonConstant.BOOLEAN_TRUE);
				customerEntity.setUpdateBy(authDetailsVo.getUserId());
				customerEntity.setUpdateDate(CommonConstant.getCalenderDate());

				customerRepository.save(customerEntity);
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
		if (cus) {
			throw new CommonException(CommonConstant.format(getMessage("unUsedRecordOnlyBeDeleted"), codeList));

		}

	}
}
