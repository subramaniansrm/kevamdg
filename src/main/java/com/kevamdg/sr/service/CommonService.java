package com.kevamdg.sr.service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevamdg.sr.auth.AuthUtil;
import com.kevamdg.sr.config.CommonException;
import com.kevamdg.sr.controller.CommonAction;
import com.kevamdg.sr.dao.CommonDao;
import com.kevamdg.sr.entity.UserEntity;
import com.kevamdg.sr.repository.UserRepository;
import com.kevamdg.sr.vo.AuthDetailsVO;
import com.kevamdg.sr.vo.CommonVO;
import com.kevamdg.sr.vo.JSONRequestVO;
import com.kevamdg.sr.vo.ScreenAuthenticationVO;
import com.kevamdg.sr.vo.ScreenAuthorizationVO;
import com.kevamdg.sr.vo.ScreenVO;
import com.kevamdg.sr.vo.UserScreenMappingVO;

@Service
public class CommonService extends CommonAction<CommonVO> {

	@Autowired
	CommonDao commonDAO;
	
	@Autowired
	UserRepository userRepo;

	
	
	/**
	 * This method is used to Authenticate and Authorize the user
	 * 
	 * @param screenAuthorizationMaster
	 * @return
	 * @throws CommonException
	 */
	@Transactional
	public CommonVO getScreenFields(JSONRequestVO common,AuthDetailsVO authDetailsVo) throws CommonException {
		CommonVO commonVO = new CommonVO();

		ScreenAuthorizationVO screenAuthorizationMasterVo = getScreenAuthorization(common,authDetailsVo);
		if (null != screenAuthorizationMasterVo) {

			// Get the Fields List
			commonVO.setScreenFieldDisplayVoList(screenAuthorizationMasterVo.getScreenFieldDisplayVoList());

			// Get the Functions & Side Tab List
			commonVO.setScreenFunctionDisplayList(screenAuthorizationMasterVo.getScreenFunctionDisplayList());

		} else {

			throw new CommonException(getMessage("noAuthorizationAvailableForThisUser"));

		}

		ScreenAuthenticationVO screenAuthenticationMaster = getScreenAuhentication(authDetailsVo);

		if (null != screenAuthenticationMaster) {
			commonVO.setScreenVoList(screenAuthenticationMaster.getScreenVoList());

		} else {
			throw new CommonException(getMessage("noScreenAvailableForThisUser"));

		}

		commonVO.setUserName(AuthUtil.getUserName());

		return commonVO;
	}

	@Transactional
	public ScreenAuthorizationVO getScreenAuthorization(JSONRequestVO common,AuthDetailsVO authDetailsVo) {
		

		ScreenAuthorizationVO screenAuthorizationMaster = new ScreenAuthorizationVO();

		try {
			Integer screenAuthenticationId = commonDAO.getScreenAuthenticationId(authDetailsVo.getRoleId(), common.getScreenId(),
					common.getSubScreenId());
			if (screenAuthenticationId != null) {
				List<Object[]> listFieldObject = commonDAO.getScreenField(screenAuthenticationId);
				if (listFieldObject != null) {
					List<String> screenFieldDisplayVoList = new ArrayList<String>();
					for (Object[] object : listFieldObject) {

						screenFieldDisplayVoList.add((String) (((Object[]) object)[1]));
					}
					screenAuthorizationMaster.setScreenFieldDisplayVoList(screenFieldDisplayVoList);
				}
				List<Object[]> listFunctionObject = commonDAO.getScreenFunction(screenAuthenticationId);
				if (listFunctionObject != null) {
					List<String> screenFunctionDisplayList = new ArrayList<String>();
					for (Object[] object : listFunctionObject) {

						screenFunctionDisplayList.add((String) (((Object[]) object)[1]));
					}
					screenAuthorizationMaster.setScreenFunctionDisplayList(screenFunctionDisplayList);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return screenAuthorizationMaster;

	}

	@Transactional
	public ScreenAuthenticationVO getScreenAuhentication(AuthDetailsVO authDetailsVo) {
		ScreenAuthenticationVO screenAuthentication = new ScreenAuthenticationVO();
		List<ScreenVO> screenVoList = new ArrayList<ScreenVO>();
		int oldValue = 0;
		int newValue = 0;
		try {
			List<Object[]> screenAuthenticationEntities = commonDAO.getScreenAuhentication(authDetailsVo);
			if (null != screenAuthenticationEntities) {
				for (Object[] object : screenAuthenticationEntities) {
					ScreenVO screenVo = new ScreenVO();
					newValue = (Integer) (((Object[]) object)[0]);
					if (oldValue != newValue) {

						if (null != (((Object[]) object)[1])) {
							screenVo.setScreenName((String) (((Object[]) object)[1]));
						}
						if (null != (((Object[]) object)[2])) {
							screenVo.setScreenTypeFlag((Character) (((Object[]) object)[2]));
						}
						if (null != (((Object[]) object)[3])) {
							screenVo.setScreenUrl((String) (((Object[]) object)[3]));
						}
						if (null != (((Object[]) object)[4])) {
							screenVo.setScreenIcon((String) (((Object[]) object)[4]));
						}
						screenVoList.add(screenVo);
					}
					oldValue = newValue;
				}
				screenAuthentication.setScreenVoList(screenVoList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return screenAuthentication;
	}
	
	
	public boolean currentTokenValidate(String accessToken) throws CommonException {

		UserEntity user = userRepo.findOne(AuthUtil.getUserId());
		
		byte[] access = Base64.getDecoder().decode(user.getAccessToken());
		
		String token = new String(access);

		if (token.equals(accessToken)) {
			return false;

		}
		return true;
	}
	
	
	public AuthDetailsVO tokenValidate(String accessToken) {
		return commonDAO.tokenValidate(accessToken);

	}
	
	public String getHeaderAccessToken(HttpServletRequest request) {
		return commonDAO.getHeaderAccessToken(request);
	}
	
	
	public UserScreenMappingVO getScreen(Integer id, AuthDetailsVO authDetailsVO) {

		UserScreenMappingVO userScreenMappingVO = new UserScreenMappingVO();

		List<Object[]> list = commonDAO.getScreenField(id, authDetailsVO);

		List<Object> screenFieldMappingVOList = new ArrayList<>();

		for (Object[] obj : list) {

			if (null != ((Object[]) obj)[1]) {
				screenFieldMappingVOList.add(((Object[]) obj)[1]);
			}

		}

		userScreenMappingVO.setScreenFieldMappingVOList(screenFieldMappingVOList);

		List<Object[]> list1 = commonDAO.getScreenFunction(id, authDetailsVO);

		List<Object> screenFunctionMappingVOList = new ArrayList<>();

		for (Object[] obj : list1) {

			if (null != ((Object[]) obj)[1]) {
				screenFunctionMappingVOList.add(((Object[]) obj)[1]);
			}

		}

		userScreenMappingVO.setScreenFunctionMappingVOList(screenFunctionMappingVOList);

		return userScreenMappingVO;
	}

}
