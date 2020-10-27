package com.kevamdg.sr.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.log.Logger;
import com.itextpdf.text.log.LoggerFactory;
import com.kevamdg.sr.config.CommonException;
import com.kevamdg.sr.constants.CommonConstant;
import com.kevamdg.sr.controller.CommonAction;
import com.kevamdg.sr.dao.CommonDropdownDAO;
import com.kevamdg.sr.entity.PackTypeValidationEntity;
import com.kevamdg.sr.vo.CommonDropDownVO;
import com.kevamdg.sr.vo.CommonRoleDropDownVO;
import com.kevamdg.sr.vo.CommonSalesOrgDropDownVO;
import com.kevamdg.sr.vo.CommonScreenDropDownVO;
import com.kevamdg.sr.vo.CommonScreenFieldDropDownVO;
import com.kevamdg.sr.vo.CommonScreenFunctionDropDownVO;
import com.kevamdg.sr.vo.CommonSubScreenDropDownVO;
import com.kevamdg.sr.vo.PackTypeValidationVO;
import com.kevamdg.sr.vo.UserVO;

@Service
public class CommonDropdownService extends CommonAction<CommonDropDownVO> {

	Logger logger = LoggerFactory.getLogger(CommonDropdownService.class);

	@Autowired
	CommonDropdownDAO dropdownDao;

	/**
	 * Distribution Channel DROPDOWN
	 * 
	 * @return
	 * @throws CommonException
	 */
	public List<CommonDropDownVO> loadDistributionChannel() throws CommonException {
		try {
			List<CommonDropDownVO> dropdownVOList = new ArrayList<CommonDropDownVO>();

			List<Object[]> objList = dropdownDao.loadDistributionChannel();

			for (Object[] obj : objList) {
				CommonDropDownVO dropdownVO = new CommonDropDownVO();

				if (null != ((Object[]) obj)[0]) {
					dropdownVO.setId((int) ((Object[]) obj)[0]);
				}
				if (null != (String) ((Object[]) obj)[1] && null != (String) ((Object[]) obj)[2]) {
					dropdownVO.setName(((String) ((Object[]) obj)[2]).concat(" - " + (String) ((Object[]) obj)[1]));
				} else if (null != (String) ((Object[]) obj)[1]) {
					dropdownVO.setName((String) ((Object[]) obj)[1]);
				}
				dropdownVOList.add(dropdownVO);
			}

			return dropdownVOList;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("DbFailure"));
		}
	}

	public List<CommonDropDownVO> materialGroup() throws CommonException {
		try {
			List<CommonDropDownVO> dropdownVOList = new ArrayList<CommonDropDownVO>();

			List<Object[]> objList = dropdownDao.materialGroup();

			for (Object[] obj : objList) {
				CommonDropDownVO dropdownVO = new CommonDropDownVO();

				if (null != ((Object[]) obj)[0]) {
					dropdownVO.setId((int) ((Object[]) obj)[0]);
				}
				if (null != (String) ((Object[]) obj)[1]) {
					dropdownVO.setName((String) ((Object[]) obj)[1]);
				}
				dropdownVOList.add(dropdownVO);
			}

			return dropdownVOList;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("DbFailure"));
		}
	}

	public List<CommonDropDownVO> flavourSolubility() throws CommonException {
		try {
			List<CommonDropDownVO> dropdownVOList = new ArrayList<CommonDropDownVO>();

			List<Object[]> objList = dropdownDao.flavourSolubility();

			for (Object[] obj : objList) {
				CommonDropDownVO dropdownVO = new CommonDropDownVO();

				if (null != ((Object[]) obj)[0]) {
					dropdownVO.setId((int) ((Object[]) obj)[0]);
				}
				if (null != (String) ((Object[]) obj)[1]) {
					dropdownVO.setName((String) ((Object[]) obj)[1]);
				}
				dropdownVOList.add(dropdownVO);
			}

			return dropdownVOList;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("DbFailure"));
		}
	}

	public List<CommonDropDownVO> legalStatus() throws CommonException {
		try {
			List<CommonDropDownVO> dropdownVOList = new ArrayList<CommonDropDownVO>();

			List<Object[]> objList = dropdownDao.legalStatus();

			for (Object[] obj : objList) {
				CommonDropDownVO dropdownVO = new CommonDropDownVO();

				if (null != ((Object[]) obj)[0]) {
					dropdownVO.setId((int) ((Object[]) obj)[0]);
				}
				if (null != (String) ((Object[]) obj)[1]) {
					dropdownVO.setName((String) ((Object[]) obj)[1]);
				}
				dropdownVOList.add(dropdownVO);
			}

			return dropdownVOList;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("DbFailure"));
		}
	}

	public List<CommonDropDownVO> legalDelcaration() throws CommonException {
		try {
			List<CommonDropDownVO> dropdownVOList = new ArrayList<CommonDropDownVO>();

			List<Object[]> objList = dropdownDao.legalDelcaration();

			for (Object[] obj : objList) {
				CommonDropDownVO dropdownVO = new CommonDropDownVO();

				if (null != ((Object[]) obj)[0]) {
					dropdownVO.setId((int) ((Object[]) obj)[0]);
				}
				if (null != (String) ((Object[]) obj)[1]) {
					dropdownVO.setName((String) ((Object[]) obj)[1]);
				}
				dropdownVOList.add(dropdownVO);
			}

			return dropdownVOList;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("DbFailure"));
		}
	}

	public List<CommonDropDownVO> loadMapCategory() throws CommonException {
		try {
			List<CommonDropDownVO> dropdownVOList = new ArrayList<CommonDropDownVO>();

			List<Object[]> objList = dropdownDao.loadMapCategory();

			for (Object[] obj : objList) {
				CommonDropDownVO dropdownVO = new CommonDropDownVO();

				if (null != ((Object[]) obj)[0]) {
					dropdownVO.setId((int) ((Object[]) obj)[0]);
				}
				if (null != (String) ((Object[]) obj)[1]) {
					dropdownVO.setName((String) ((Object[]) obj)[1]);
				}
				dropdownVOList.add(dropdownVO);
			}

			return dropdownVOList;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("DbFailure"));
		}
	}

	public List<CommonDropDownVO> loadOther(Integer id) throws CommonException {
		try {
			List<CommonDropDownVO> dropdownVOList = new ArrayList<CommonDropDownVO>();

			List<Object[]> objList = dropdownDao.loadOther(id);

			for (Object[] obj : objList) {
				CommonDropDownVO dropdownVO = new CommonDropDownVO();

				if (null != ((Object[]) obj)[0]) {
					dropdownVO.setId((int) ((Object[]) obj)[0]);
				}
				if (null != (String) ((Object[]) obj)[1]) {
					dropdownVO.setName((String) ((Object[]) obj)[1]);
				}
				dropdownVOList.add(dropdownVO);
			}

			return dropdownVOList;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("DbFailure"));
		}
	}

	public List<CommonDropDownVO> loadPackType(PackTypeValidationVO packTypeValidationVO) throws CommonException {
		try {
			List<CommonDropDownVO> dropdownVOList = new ArrayList<CommonDropDownVO>();

			List<Object[]> objList = dropdownDao.loadPackType(packTypeValidationVO);

			for (Object[] obj : objList) {
				CommonDropDownVO dropdownVO = new CommonDropDownVO();

				if (null != ((Object[]) obj)[0]) {
					dropdownVO.setId((int) ((Object[]) obj)[0]);
				}
				if (null != (String) ((Object[]) obj)[1]) {
					dropdownVO.setName((String) ((Object[]) obj)[1]);
				}
				dropdownVOList.add(dropdownVO);
			}

			return dropdownVOList;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("DbFailure"));
		}
	}

	public List<CommonDropDownVO> loadPackSize(Integer id) throws CommonException {
		try {
			List<CommonDropDownVO> dropdownVOList = new ArrayList<CommonDropDownVO>();

			List<Object[]> objList = dropdownDao.loadPackSize(id);

			for (Object[] obj : objList) {
				CommonDropDownVO dropdownVO = new CommonDropDownVO();

				if (null != ((Object[]) obj)[0]) {
					dropdownVO.setId((int) ((Object[]) obj)[0]);
				}
				if (null != (String) ((Object[]) obj)[1]) {
					dropdownVO.setName((String) ((Object[]) obj)[1]);
				}
				dropdownVOList.add(dropdownVO);
			}

			return dropdownVOList;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("DbFailure"));
		}
	}

	/**
	 * Division DROPDOWN
	 * 
	 * @return
	 * @throws CommonException
	 */
	public List<CommonDropDownVO> loadDivision(int id) throws CommonException {
		try {
			List<CommonDropDownVO> dropdownVOList = new ArrayList<CommonDropDownVO>();

			List<Object[]> objList = dropdownDao.loadDivision(id);

			for (Object[] obj : objList) {
				CommonDropDownVO dropdownVO = new CommonDropDownVO();

				if (null != ((Object[]) obj)[0]) {
					dropdownVO.setId((int) ((Object[]) obj)[0]);
				}
				if (null != (String) ((Object[]) obj)[1] && null != (String) ((Object[]) obj)[2]) {
					dropdownVO.setName(((String) ((Object[]) obj)[2]).concat(" - " + (String) ((Object[]) obj)[1]));
				} else if (null != (String) ((Object[]) obj)[1]) {
					dropdownVO.setName((String) ((Object[]) obj)[1]);
				}
				dropdownVOList.add(dropdownVO);
			}

			return dropdownVOList;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("DbFailure"));
		}
	}

	public List<CommonDropDownVO> currency() throws CommonException {
		try {
			List<CommonDropDownVO> dropdownVOList = new ArrayList<CommonDropDownVO>();

			List<Object[]> objList = dropdownDao.currency();

			for (Object[] obj : objList) {
				CommonDropDownVO dropdownVO = new CommonDropDownVO();

				if (null != ((Object[]) obj)[0]) {
					dropdownVO.setId((int) ((Object[]) obj)[0]);
				}
				if (null != (String) ((Object[]) obj)[1]) {
					dropdownVO.setName((String) ((Object[]) obj)[1]);
				}
				dropdownVOList.add(dropdownVO);
			}

			return dropdownVOList;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("DbFailure"));
		}
	}

	/**
	 * Division DROPDOWN
	 * 
	 * @return
	 * @throws CommonException
	 */
	public List<CommonDropDownVO> loadSalesOrg(int id) throws CommonException {
		try {
			List<CommonDropDownVO> dropdownVOList = new ArrayList<CommonDropDownVO>();

			List<Object[]> objList = dropdownDao.loadSalesOrg(id);

			for (Object[] obj : objList) {
				CommonDropDownVO dropdownVO = new CommonDropDownVO();

				if (null != ((Object[]) obj)[0]) {
					dropdownVO.setId((int) ((Object[]) obj)[0]);
				}
				if (null != (String) ((Object[]) obj)[1] && null != (String) ((Object[]) obj)[2]) {
					dropdownVO.setName(((String) ((Object[]) obj)[2]).concat(" - " + (String) ((Object[]) obj)[1]));
				} else if (null != (String) ((Object[]) obj)[1]) {
					dropdownVO.setName((String) ((Object[]) obj)[1]);
				}
				dropdownVOList.add(dropdownVO);
			}

			return dropdownVOList;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("DbFailure"));
		}
	}

	public List<CommonDropDownVO> loadMaterialType() throws CommonException {
		try {
			List<CommonDropDownVO> dropdownVOList = new ArrayList<CommonDropDownVO>();

			List<Object[]> objList = dropdownDao.loadMaterialType();

			for (Object[] obj : objList) {
				CommonDropDownVO dropdownVO = new CommonDropDownVO();

				if (null != ((Object[]) obj)[0]) {
					dropdownVO.setId((int) ((Object[]) obj)[0]);
				}
				if (null != (String) ((Object[]) obj)[1] && null != (String) ((Object[]) obj)[2]) {
					dropdownVO.setName(((String) ((Object[]) obj)[2]).concat(" - " + (String) ((Object[]) obj)[1]));
				} else if (null != (String) ((Object[]) obj)[1]) {
					dropdownVO.setName((String) ((Object[]) obj)[1]);
				}
				dropdownVOList.add(dropdownVO);
			}

			return dropdownVOList;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("DbFailure"));
		}
	}

	public List<CommonDropDownVO> newextends() throws CommonException {
		try {
			List<CommonDropDownVO> dropdownVOList = new ArrayList<CommonDropDownVO>();

			List<Object[]> objList = dropdownDao.newextends();

			for (Object[] obj : objList) {
				CommonDropDownVO dropdownVO = new CommonDropDownVO();

				if (null != ((Object[]) obj)[0]) {
					dropdownVO.setId((int) ((Object[]) obj)[0]);
				}
				if (null != (String) ((Object[]) obj)[1] && null != (String) ((Object[]) obj)[2]) {
					dropdownVO.setName(((String) ((Object[]) obj)[2]).concat(" - " + (String) ((Object[]) obj)[1]));
				} else if (null != (String) ((Object[]) obj)[1]) {
					dropdownVO.setName((String) ((Object[]) obj)[1]);
				}
				dropdownVOList.add(dropdownVO);
			}

			return dropdownVOList;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("DbFailure"));
		}
	}

	public List<CommonDropDownVO> loadMaterialPurpose() throws CommonException {
		try {
			List<CommonDropDownVO> dropdownVOList = new ArrayList<CommonDropDownVO>();

			List<Object[]> objList = dropdownDao.loadMaterialPurpose();

			for (Object[] obj : objList) {
				CommonDropDownVO dropdownVO = new CommonDropDownVO();

				if (null != ((Object[]) obj)[0]) {
					dropdownVO.setId((int) ((Object[]) obj)[0]);
				}
				if (null != (String) ((Object[]) obj)[1] && null != (String) ((Object[]) obj)[2]) {
					dropdownVO.setName(((String) ((Object[]) obj)[2]).concat(" - " + (String) ((Object[]) obj)[1]));
				} else if (null != (String) ((Object[]) obj)[1]) {
					dropdownVO.setName((String) ((Object[]) obj)[1]);
				}
				dropdownVOList.add(dropdownVO);
			}

			return dropdownVOList;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("DbFailure"));
		}
	}

	public List<CommonDropDownVO> loadUnit() throws CommonException {
		try {
			List<CommonDropDownVO> dropdownVOList = new ArrayList<CommonDropDownVO>();

			List<Object[]> objList = dropdownDao.loadUnit();

			for (Object[] obj : objList) {
				CommonDropDownVO dropdownVO = new CommonDropDownVO();

				if (null != ((Object[]) obj)[0]) {
					dropdownVO.setId((int) ((Object[]) obj)[0]);
				}
				if (null != (String) ((Object[]) obj)[1]) {
					dropdownVO.setName((String) ((Object[]) obj)[1]);
				}
				dropdownVOList.add(dropdownVO);
			}

			return dropdownVOList;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("DbFailure"));
		}
	}

	
	public List<CommonDropDownVO> uom() throws CommonException {
		try {
			List<CommonDropDownVO> dropdownVOList = new ArrayList<CommonDropDownVO>();

			List<Object[]> objList = dropdownDao.uom();

			for (Object[] obj : objList) {
				CommonDropDownVO dropdownVO = new CommonDropDownVO();

				if (null != ((Object[]) obj)[0]) {
					dropdownVO.setId((int) ((Object[]) obj)[0]);
				}
				if (null != (String) ((Object[]) obj)[1]) {
					dropdownVO.setName((String) ((Object[]) obj)[1]);
				}
				dropdownVOList.add(dropdownVO);
			}

			return dropdownVOList;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("DbFailure"));
		}
	}
	
	public List<CommonDropDownVO> loadPlant(int id) throws CommonException {
		try {
			List<CommonDropDownVO> dropdownVOList = new ArrayList<CommonDropDownVO>();

			List<Object[]> objList = dropdownDao.loadPlant(id);

			for (Object[] obj : objList) {
				CommonDropDownVO dropdownVO = new CommonDropDownVO();

				if (null != ((Object[]) obj)[0]) {
					dropdownVO.setId((int) ((Object[]) obj)[0]);
				}
				if (null != (String) ((Object[]) obj)[1] && null != (String) ((Object[]) obj)[2]) {
					dropdownVO.setName(((String) ((Object[]) obj)[1]).concat(" - " + (String) ((Object[]) obj)[2]));
				} else if (null != (String) ((Object[]) obj)[2]) {
					dropdownVO.setName((String) ((Object[]) obj)[2]);
				}
				dropdownVOList.add(dropdownVO);
			}

			return dropdownVOList;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("DbFailure"));
		}
	}

	public List<CommonDropDownVO> customer(CommonDropDownVO commonDropDownVO) throws CommonException {

		List<CommonDropDownVO> dropdownVOList = new ArrayList<CommonDropDownVO>();

		List<Object[]> objList = dropdownDao.customer(commonDropDownVO);

		if (objList.isEmpty() || objList.size() < 0) {
			throw new CommonException(getMessage("customer.validation"));
		}

		for (Object[] obj : objList) {
			CommonDropDownVO dropdownVO = new CommonDropDownVO();

			if (null != ((Object[]) obj)[0]) {
				dropdownVO.setId((int) ((Object[]) obj)[0]);
			}
			if (null != (String) ((Object[]) obj)[2]) {
				dropdownVO.setCode((String) ((Object[]) obj)[2]);
			}
			if (null != (String) ((Object[]) obj)[1]) {
				dropdownVO.setName((String) ((Object[]) obj)[1]);
			}
			if (null != (String) ((Object[]) obj)[3]) {
				dropdownVO.setParentCode((String) ((Object[]) obj)[3]);
			}
			dropdownVOList.add(dropdownVO);
		}

		return dropdownVOList;

	}

	public List<CommonRoleDropDownVO> role(int id) throws CommonException {
		try {
			List<CommonRoleDropDownVO> dropdownVOList = new ArrayList<CommonRoleDropDownVO>();

			List<Object[]> objList = dropdownDao.role(id);

			for (Object[] obj : objList) {
				CommonRoleDropDownVO dropdownVO = new CommonRoleDropDownVO();

				if (null != ((Object[]) obj)[0]) {
					dropdownVO.setRoleId((int) ((Object[]) obj)[0]);
				}
				if (null != (String) ((Object[]) obj)[1]) {
					dropdownVO.setName((String) ((Object[]) obj)[1]);
				}
				if (CommonConstant.SUCCESS.equals(((String) ((Object[]) obj)[2]))) {
					dropdownVO.setActiveFlag(CommonConstant.BOOLEAN_TRUE);
				} else {
					dropdownVO.setActiveFlag(CommonConstant.BOOLEAN_FALSE);
				}
				dropdownVOList.add(dropdownVO);
			}

			return dropdownVOList;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("DbFailure"));
		}
	}

	public List<CommonSalesOrgDropDownVO> salesOrgMapping(int id) throws CommonException {
		try {
			List<CommonSalesOrgDropDownVO> dropdownVOList = new ArrayList<CommonSalesOrgDropDownVO>();

			List<Object[]> objList = dropdownDao.salesOrgMapping(id);

			for (Object[] obj : objList) {
				CommonSalesOrgDropDownVO dropdownVO = new CommonSalesOrgDropDownVO();

				if (null != ((Object[]) obj)[0]) {
					dropdownVO.setSalesOrgId((int) ((Object[]) obj)[0]);
				}
				if (null != (String) ((Object[]) obj)[1] && null != (String) ((Object[]) obj)[2]) {
					dropdownVO.setName(((String) ((Object[]) obj)[2]).concat(" - " + (String) ((Object[]) obj)[1]));
				} else if (null != (String) ((Object[]) obj)[1]) {
					dropdownVO.setName((String) ((Object[]) obj)[1]);
				}
				if (null != ((Object[]) obj)[3]) {
					if ((boolean) ((Object[]) obj)[3]) {
						dropdownVO.setDefaultId(CommonConstant.BOOLEAN_TRUE);
					} else {
						dropdownVO.setDefaultId(CommonConstant.BOOLEAN_FALSE);
					}
				}
				if (CommonConstant.SUCCESS.equals(((String) ((Object[]) obj)[4]))) {
					dropdownVO.setActiveFlag(CommonConstant.BOOLEAN_TRUE);
				} else {
					dropdownVO.setActiveFlag(CommonConstant.BOOLEAN_FALSE);
				}
				if (null != ((Object[]) obj)[5]) {
					dropdownVO.setSalesOrgMapId((Integer) ((Object[]) obj)[5]);
				}
				dropdownVOList.add(dropdownVO);
			}

			return dropdownVOList;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("DbFailure"));
		}
	}

	public List<CommonDropDownVO> plantMapping(CommonDropDownVO commonDropDownVO) throws CommonException {
		try {
			List<CommonDropDownVO> dropdownVOList = new ArrayList<CommonDropDownVO>();

			List<Object[]> objList = dropdownDao.plantMapping(commonDropDownVO);

			for (Object[] obj : objList) {
				CommonDropDownVO dropdownVO = new CommonDropDownVO();

				if (null != ((Object[]) obj)[0]) {
					dropdownVO.setId((int) ((Object[]) obj)[0]);
				}
				if (null != (String) ((Object[]) obj)[1] && null != (String) ((Object[]) obj)[2]) {
					dropdownVO.setName(((String) ((Object[]) obj)[2]).concat(" - " + (String) ((Object[]) obj)[1]));
				} else if (null != (String) ((Object[]) obj)[1]) {
					dropdownVO.setName((String) ((Object[]) obj)[1]);
				}
				if (CommonConstant.SUCCESS.equals(((String) ((Object[]) obj)[3]))) {
					dropdownVO.setActiveFlag(CommonConstant.BOOLEAN_TRUE);
				} else {
					dropdownVO.setActiveFlag(CommonConstant.BOOLEAN_FALSE);
				}
				if (null != ((Object[]) obj)[4]) {
					if ((boolean) ((Object[]) obj)[4]) {
						dropdownVO.setDefaultId(CommonConstant.BOOLEAN_TRUE);
					} else {
						dropdownVO.setDefaultId(CommonConstant.BOOLEAN_FALSE);
					}
				}
				if(null != ((Object[]) obj)[5]){
					dropdownVO.setPlantMapId((int) ((Object[]) obj)[5]);
				}
				dropdownVOList.add(dropdownVO);
			}

			return dropdownVOList;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("DbFailure"));
		}
	}

	public List<CommonScreenDropDownVO> screen(int id) throws CommonException {
		try {
			List<CommonScreenDropDownVO> dropdownVOList = new ArrayList<CommonScreenDropDownVO>();

			List<Object[]> objList = dropdownDao.screen(id);

			for (Object[] obj : objList) {
				CommonScreenDropDownVO dropdownVO = new CommonScreenDropDownVO();

				if (null != ((Object[]) obj)[0]) {
					dropdownVO.setScreenId((int) ((Object[]) obj)[0]);
				}
				if (null != (String) ((Object[]) obj)[1]) {
					dropdownVO.setName((String) ((Object[]) obj)[1]);
				}
				if (CommonConstant.SUCCESS.equals(((String) ((Object[]) obj)[2]))) {
					dropdownVO.setActiveFlag(CommonConstant.BOOLEAN_TRUE);
				} else {
					dropdownVO.setActiveFlag(CommonConstant.BOOLEAN_FALSE);
				}
				dropdownVOList.add(dropdownVO);
			}

			return dropdownVOList;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("DbFailure"));
		}
	}

	public List<CommonSubScreenDropDownVO> subScreen(CommonDropDownVO commonDropDownVO) throws CommonException {
		try {
			List<CommonSubScreenDropDownVO> dropdownVOList = new ArrayList<CommonSubScreenDropDownVO>();

			List<Object[]> objList = dropdownDao.subScreen(commonDropDownVO);

			for (Object[] obj : objList) {
				CommonSubScreenDropDownVO dropdownVO = new CommonSubScreenDropDownVO();

				if (null != ((Object[]) obj)[0]) {
					dropdownVO.setSubScreenId((int) ((Object[]) obj)[0]);
				}
				if (null != (String) ((Object[]) obj)[1]) {
					dropdownVO.setName((String) ((Object[]) obj)[1]);
				}
				if (CommonConstant.SUCCESS.equals(((String) ((Object[]) obj)[2]))) {
					dropdownVO.setActiveFlag(CommonConstant.BOOLEAN_TRUE);
				} else {
					dropdownVO.setActiveFlag(CommonConstant.BOOLEAN_FALSE);
				}
				dropdownVOList.add(dropdownVO);
			}

			return dropdownVOList;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("DbFailure"));
		}
	}

	public List<CommonScreenFieldDropDownVO> screenField(CommonDropDownVO commonDropDownVO) throws CommonException {
		try {
			List<CommonScreenFieldDropDownVO> dropdownVOList = new ArrayList<CommonScreenFieldDropDownVO>();

			List<Object[]> objList = dropdownDao.screenField(commonDropDownVO);

			for (Object[] obj : objList) {
				CommonScreenFieldDropDownVO dropdownVO = new CommonScreenFieldDropDownVO();

				if (null != ((Object[]) obj)[0]) {
					dropdownVO.setScreenFieldId((int) ((Object[]) obj)[0]);
				}
				if (null != (String) ((Object[]) obj)[1]) {
					dropdownVO.setName((String) ((Object[]) obj)[1]);
				}
				if (CommonConstant.SUCCESS.equals(((String) ((Object[]) obj)[2]))) {
					dropdownVO.setActiveFlag(CommonConstant.BOOLEAN_TRUE);
				} else {
					dropdownVO.setActiveFlag(CommonConstant.BOOLEAN_FALSE);
				}
				dropdownVOList.add(dropdownVO);
			}

			return dropdownVOList;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("DbFailure"));
		}
	}

	public List<CommonScreenFunctionDropDownVO> screenFunction(CommonDropDownVO commonDropDownVO)
			throws CommonException {
		try {
			List<CommonScreenFunctionDropDownVO> dropdownVOList = new ArrayList<CommonScreenFunctionDropDownVO>();

			List<Object[]> objList = dropdownDao.screenFunction(commonDropDownVO);

			for (Object[] obj : objList) {
				CommonScreenFunctionDropDownVO dropdownVO = new CommonScreenFunctionDropDownVO();

				if (null != ((Object[]) obj)[0]) {
					dropdownVO.setScreenFunctionId((int) ((Object[]) obj)[0]);
				}
				if (null != (String) ((Object[]) obj)[1]) {
					dropdownVO.setName((String) ((Object[]) obj)[1]);
				}
				if (CommonConstant.SUCCESS.equals(((String) ((Object[]) obj)[2]))) {
					dropdownVO.setActiveFlag(CommonConstant.BOOLEAN_TRUE);
				} else {
					dropdownVO.setActiveFlag(CommonConstant.BOOLEAN_FALSE);
				}
				dropdownVOList.add(dropdownVO);
			}

			return dropdownVOList;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("DbFailure"));
		}
	}

	public PackTypeValidationVO findCommonDropdownId(Integer id) {

		PackTypeValidationEntity commonDropdown = dropdownDao.findCommonDropdownId(id);

		PackTypeValidationVO packTypeValidationVO = new PackTypeValidationVO();

		BeanUtils.copyProperties(commonDropdown, packTypeValidationVO);

		return packTypeValidationVO;

	}

	public List<UserVO> user(int id) {

		List<UserVO> list = new ArrayList<>();

		List<Object[]> objList = dropdownDao.user(id);

		for (Object[] object : objList) {

			UserVO userMMVO = new UserVO();

			if (null != ((Object[]) object)[0]) {

				userMMVO.setId((int) ((Object[]) object)[0]);

			}

			if (null != ((Object[]) object)[1] && null != ((Object[]) object)[2]) {

				userMMVO.setFirstName(((String) ((Object[]) object)[1]).concat((String) ((Object[]) object)[2]));

			} else if (null != ((Object[]) object)[1]) {
				userMMVO.setFirstName((String) ((Object[]) object)[1]);
			}

			list.add(userMMVO);
		}
		return list;
	}
}
