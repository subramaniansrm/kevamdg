package com.kevamdg.sr.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import com.kevamdg.sr.service.CommonService;
import com.kevamdg.sr.vo.AuthDetailsVO;
import com.kevamdg.sr.vo.UserScreenMappingVO;

public class CommonAction<T> {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private CommonService commonService;

	protected String MODEL_ATTRIBUTE = null;

	public String getMessage(String code) {
		String message = "";
		try {
			message = getMessage(code, new Object[] {});
		} catch (NullPointerException e) {

		}
		return message;
	}

	public String getMessage(String code, Object args[]) {
		return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
	}

	// Validate token
	public AuthDetailsVO tokenValidate(String accessToken) {
		AuthDetailsVO authDetailsVo = new AuthDetailsVO();
		try {
			authDetailsVo = commonService.tokenValidate(accessToken);
		} catch (NullPointerException e) {

		}
		return authDetailsVo;
	}

	public String getHeaderAccessToken(HttpServletRequest request) {
		String access = "";
		try {
			access = commonService.getHeaderAccessToken(request);
		} catch (NullPointerException e) {

		}

		return access;
	}

	public UserScreenMappingVO getScreen(Integer id, AuthDetailsVO authDetailsVO) {
		UserScreenMappingVO userScreenMapping = new UserScreenMappingVO();
		try {
			userScreenMapping = commonService.getScreen(id, authDetailsVO);
		} catch (NullPointerException e) {

		}
		return userScreenMapping;
	}

}
