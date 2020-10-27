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
		return commonService.tokenValidate(accessToken);
	}

	public String getHeaderAccessToken(HttpServletRequest request) {
		return commonService.getHeaderAccessToken(request);
	}

	public UserScreenMappingVO getScreen(Integer id, AuthDetailsVO authDetailsVO) {
		return commonService.getScreen(id, authDetailsVO);
	}

}
