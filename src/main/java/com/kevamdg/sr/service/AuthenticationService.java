package com.kevamdg.sr.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.log.Logger;
import com.itextpdf.text.log.LoggerFactory;
import com.kevamdg.sr.config.CommonException;
import com.kevamdg.sr.constants.CommonConstant;
import com.kevamdg.sr.controller.CommonAction;
import com.kevamdg.sr.dao.AuthenticationDAO;
import com.kevamdg.sr.entity.UserScreenFieldMappingEntity;
import com.kevamdg.sr.entity.UserScreenFunctionMappingEntity;
import com.kevamdg.sr.entity.UserScreenMappingEntity;
import com.kevamdg.sr.entity.UserSubScreenMappingEntity;
import com.kevamdg.sr.repository.UserScreenFieldMappingRepository;
import com.kevamdg.sr.repository.UserScreenFunctionMappingRepository;
import com.kevamdg.sr.repository.UserScreenMappingRepository;
import com.kevamdg.sr.repository.UserSubScreenMappingRepository;
import com.kevamdg.sr.vo.AuthDetailsVO;
import com.kevamdg.sr.vo.CommonDropDownVO;
import com.kevamdg.sr.vo.CommonScreenDropDownVO;
import com.kevamdg.sr.vo.CommonScreenFieldDropDownVO;
import com.kevamdg.sr.vo.CommonScreenFunctionDropDownVO;
import com.kevamdg.sr.vo.CommonSubScreenDropDownVO;
import com.kevamdg.sr.vo.MappingAuthenticationVO;
import com.kevamdg.sr.vo.UserScreenFieldMappingVO;
import com.kevamdg.sr.vo.UserScreenFunctionMappingVO;
import com.kevamdg.sr.vo.UserScreenMappingVO;
import com.kevamdg.sr.vo.UserVO;

@Service
public class AuthenticationService extends CommonAction<MappingAuthenticationVO> {

	Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

	@Autowired
	AuthenticationDAO authenticationDAO;

	@Autowired
	UserScreenMappingRepository userScreenMappingRepository;

	@Autowired
	UserSubScreenMappingRepository userSubScreenMappingRepository;

	@Autowired
	UserScreenFieldMappingRepository userScreenFieldMappingRepository;

	@Autowired
	UserScreenFunctionMappingRepository userScreenFunctionMappingRepository;

	public List<UserVO> getAll() {

		List<Object[]> userEntityList = authenticationDAO.getAll();

		List<UserVO> userList = new ArrayList<>();

		for (Object[] obj : userEntityList) {

			UserVO commonDropDownVO = new UserVO();

			if (null != ((Object[]) obj)[0]) {
				commonDropDownVO.setId((Integer) ((Object[]) obj)[0]);
			}
			if (null != ((Object[]) obj)[1] && null != ((Object[]) obj)[2]) {
				commonDropDownVO
						.setFirstName(((String) ((Object[]) obj)[1]).concat(" " + (String) ((Object[]) obj)[2]));
			} else if (null != ((Object[]) obj)[1]) {
				commonDropDownVO.setFirstName((String) ((Object[]) obj)[1]);
			}
			if (null != ((Object[]) obj)[3]) {
				commonDropDownVO.setUserLocationName((String) ((Object[]) obj)[3]);
			}
			if (null != ((Object[]) obj)[4]) {
				commonDropDownVO.setSubLocationName((String) ((Object[]) obj)[4]);
			}
			if (null != ((Object[]) obj)[5]) {
				commonDropDownVO.setUserDepartmentName((String) ((Object[]) obj)[5]);
			}

			userList.add(commonDropDownVO);

		}

		return userList;
	}

	@Transactional
	public void update(MappingAuthenticationVO mappingAuthenticationVO, AuthDetailsVO authDetailsVo) {

		if (null != mappingAuthenticationVO.getUserScreenMappingVOList()) {

			for (UserScreenMappingVO userScreenMappingVO : mappingAuthenticationVO.getUserScreenMappingVOList()) {

				try {
					int id = authenticationDAO.screenId(userScreenMappingVO.getScreenId());

					if (id != 0) {
						authenticationDAO.screenDelete(id);
					}
				} catch (NoResultException e) {

				}
				UserScreenMappingEntity userScreenMappingEntity = new UserScreenMappingEntity();

				userScreenMappingEntity.setScreenId(userScreenMappingVO.getScreenId());
				userScreenMappingEntity.setUserId(mappingAuthenticationVO.getUserId());
				userScreenMappingEntity.setActiveFlag(CommonConstant.BOOLEAN_TRUE);
				userScreenMappingEntity.setCreateBy(authDetailsVo.getUserId());
				userScreenMappingEntity.setCreateDate(CommonConstant.getCalenderDate());
				userScreenMappingEntity.setUpdateBy(authDetailsVo.getUserId());
				userScreenMappingEntity.setUpdateDate(CommonConstant.getCalenderDate());

				userScreenMappingRepository.save(userScreenMappingEntity);

				if (null != userScreenMappingVO.getSubScreenId()) {
					try {
						int id1 = authenticationDAO.subScreenId(userScreenMappingVO.getSubScreenId());

						if (id1 != 0) {
							authenticationDAO.subScreenDelete(id1);
						}
					} catch (NoResultException e) {

					}

					UserSubScreenMappingEntity userSubScreenMappingEntity = new UserSubScreenMappingEntity();

					userSubScreenMappingEntity.setSubScreenId(userScreenMappingVO.getSubScreenId());
					userSubScreenMappingEntity.setScreenId(userScreenMappingVO.getScreenId());
					userSubScreenMappingEntity.setUserId(mappingAuthenticationVO.getUserId());
					userSubScreenMappingEntity.setActiveFlag(CommonConstant.BOOLEAN_TRUE);
					userSubScreenMappingEntity.setCreateBy(authDetailsVo.getUserId());
					userSubScreenMappingEntity.setCreateDate(CommonConstant.getCalenderDate());
					userSubScreenMappingEntity.setUpdateBy(authDetailsVo.getUserId());
					userSubScreenMappingEntity.setUpdateDate(CommonConstant.getCalenderDate());

					userSubScreenMappingRepository.save(userSubScreenMappingEntity);

				}

				if (null != userScreenMappingVO.getUserScreenFieldMappingVOList()) {

					authenticationDAO.screenFieldDelete(mappingAuthenticationVO.getUserId(),
							userScreenMappingVO.getSubScreenId());

					for (UserScreenFieldMappingVO userScreenFieldMappingVO : userScreenMappingVO
							.getUserScreenFieldMappingVOList()) {

						UserScreenFieldMappingEntity userScreenFieldMappingEntity = new UserScreenFieldMappingEntity();

						userScreenFieldMappingEntity.setSubScreenId(userScreenMappingVO.getSubScreenId());
						userScreenFieldMappingEntity.setScreenFieldId(userScreenFieldMappingVO.getScreenFieldId());
						userScreenFieldMappingEntity.setUserId(mappingAuthenticationVO.getUserId());
						userScreenFieldMappingEntity.setActiveFlag(CommonConstant.BOOLEAN_TRUE);
						userScreenFieldMappingEntity.setCreateBy(authDetailsVo.getUserId());
						userScreenFieldMappingEntity.setCreateDate(CommonConstant.getCalenderDate());
						userScreenFieldMappingEntity.setUpdateBy(authDetailsVo.getUserId());
						userScreenFieldMappingEntity.setUpdateDate(CommonConstant.getCalenderDate());

						userScreenFieldMappingRepository.save(userScreenFieldMappingEntity);

					}

				}

				if (null != userScreenMappingVO.getUserScreenFunctionMappingVOList()) {

					authenticationDAO.screenFunctionDelete(mappingAuthenticationVO.getUserId(),
							userScreenMappingVO.getSubScreenId());

					for (UserScreenFunctionMappingVO userScreenFunctionMappingVO : userScreenMappingVO
							.getUserScreenFunctionMappingVOList()) {

						UserScreenFunctionMappingEntity userScreenFunctionMappingEntity = new UserScreenFunctionMappingEntity();

						userScreenFunctionMappingEntity.setSubScreenId(userScreenMappingVO.getSubScreenId());
						userScreenFunctionMappingEntity
								.setScreenFunctionId(userScreenFunctionMappingVO.getScreenFunctionId());
						userScreenFunctionMappingEntity.setUserId(mappingAuthenticationVO.getUserId());
						userScreenFunctionMappingEntity.setActiveFlag(CommonConstant.BOOLEAN_TRUE);
						userScreenFunctionMappingEntity.setCreateBy(authDetailsVo.getUserId());
						userScreenFunctionMappingEntity.setCreateDate(CommonConstant.getCalenderDate());
						userScreenFunctionMappingEntity.setUpdateBy(authDetailsVo.getUserId());
						userScreenFunctionMappingEntity.setUpdateDate(CommonConstant.getCalenderDate());

						userScreenFunctionMappingRepository.save(userScreenFunctionMappingEntity);

					}

				}

			}

		}

	}
	
	
	
	public List<CommonScreenDropDownVO> screen(AuthDetailsVO authDetailsVo) throws CommonException {
		try {
			List<CommonScreenDropDownVO> dropdownVOList = new ArrayList<CommonScreenDropDownVO>();

			List<Object[]> objList = authenticationDAO.screen(authDetailsVo);

			for (Object[] obj : objList) {
				CommonScreenDropDownVO dropdownVO = new CommonScreenDropDownVO();

				if (null != ((Object[]) obj)[0]) {
					dropdownVO.setScreenId((int) ((Object[]) obj)[0]);
				}
				if (null != (String) ((Object[]) obj)[1]) {
					dropdownVO.setName((String) ((Object[]) obj)[1]);
				}
				if (CommonConstant.SUCCESS.equals(((String)((Object[]) obj)[2]))) {
					dropdownVO.setActiveFlag(CommonConstant.BOOLEAN_TRUE);
				}else{
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
	
	
	public List<CommonSubScreenDropDownVO> subScreen(CommonDropDownVO commonDropDownVO,AuthDetailsVO authDetailsVo) throws CommonException {
		try {
			List<CommonSubScreenDropDownVO> dropdownVOList = new ArrayList<CommonSubScreenDropDownVO>();

			List<Object[]> objList = authenticationDAO.subScreen(commonDropDownVO,authDetailsVo);

			for (Object[] obj : objList) {
				CommonSubScreenDropDownVO dropdownVO = new CommonSubScreenDropDownVO();

				if (null != ((Object[]) obj)[0]) {
					dropdownVO.setSubScreenId((int) ((Object[]) obj)[0]);
				}
				if (null != (String) ((Object[]) obj)[1]) {
					dropdownVO.setName((String) ((Object[]) obj)[1]);
				}
				if (CommonConstant.SUCCESS.equals(((String)((Object[]) obj)[2]))) {
					dropdownVO.setActiveFlag(CommonConstant.BOOLEAN_TRUE);
				}else{
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
	
	
	public List<CommonScreenFieldDropDownVO> screenField(CommonDropDownVO commonDropDownVO,AuthDetailsVO authDetailsVo) throws CommonException {
		try {
			List<CommonScreenFieldDropDownVO> dropdownVOList = new ArrayList<CommonScreenFieldDropDownVO>();

			List<Object[]> objList = authenticationDAO.screenField(commonDropDownVO,authDetailsVo);

			for (Object[] obj : objList) {
				CommonScreenFieldDropDownVO dropdownVO = new CommonScreenFieldDropDownVO();

				if (null != ((Object[]) obj)[0]) {
					dropdownVO.setScreenFieldId((int) ((Object[]) obj)[0]);
				}
				if (null != (String) ((Object[]) obj)[1]) {
					dropdownVO.setName((String) ((Object[]) obj)[1]);
				}
				if (CommonConstant.SUCCESS.equals(((String)((Object[]) obj)[2]))) {
					dropdownVO.setActiveFlag(CommonConstant.BOOLEAN_TRUE);
				}else{
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
	
	
	
	public List<CommonScreenFunctionDropDownVO> screenFunction(CommonDropDownVO commonDropDownVO,AuthDetailsVO authDetailsVo) throws CommonException {
		try {
			List<CommonScreenFunctionDropDownVO> dropdownVOList = new ArrayList<CommonScreenFunctionDropDownVO>();

			List<Object[]> objList = authenticationDAO.screenFunction(commonDropDownVO,authDetailsVo);

			for (Object[] obj : objList) {
				CommonScreenFunctionDropDownVO dropdownVO = new CommonScreenFunctionDropDownVO();

				if (null != ((Object[]) obj)[0]) {
					dropdownVO.setScreenFunctionId((int) ((Object[]) obj)[0]);
				}
				if (null != (String) ((Object[]) obj)[1]) {
					dropdownVO.setName((String) ((Object[]) obj)[1]);
				}
				if (CommonConstant.SUCCESS.equals(((String)((Object[]) obj)[2]))) {
					dropdownVO.setActiveFlag(CommonConstant.BOOLEAN_TRUE);
				}else{
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
	
	

}
