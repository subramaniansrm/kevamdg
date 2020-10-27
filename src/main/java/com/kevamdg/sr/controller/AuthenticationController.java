package com.kevamdg.sr.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.log.Logger;
import com.itextpdf.text.log.LoggerFactory;
import com.kevamdg.sr.config.CommonException;
import com.kevamdg.sr.config.LoginAuthException;
import com.kevamdg.sr.constants.CommonConstant;
import com.kevamdg.sr.constants.FilePathConstants;
import com.kevamdg.sr.service.AuthenticationService;
import com.kevamdg.sr.vo.AuthDetailsVO;
import com.kevamdg.sr.vo.CommonDropDownVO;
import com.kevamdg.sr.vo.CommonScreenDropDownVO;
import com.kevamdg.sr.vo.CommonScreenFieldDropDownVO;
import com.kevamdg.sr.vo.CommonScreenFunctionDropDownVO;
import com.kevamdg.sr.vo.CommonSubScreenDropDownVO;
import com.kevamdg.sr.vo.JSONRequestVO;
import com.kevamdg.sr.vo.JSONResponse;
import com.kevamdg.sr.vo.MappingAuthenticationVO;
import com.kevamdg.sr.vo.UserScreenMappingVO;
import com.kevamdg.sr.vo.UserVO;

@RestController
public class AuthenticationController extends CommonAction<MappingAuthenticationVO> {

	Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

	@Autowired
	AuthenticationService authenticationService;

	@PostMapping(FilePathConstants.AUTHENTICATION_GETALL)
	@ResponseBody
	public ResponseEntity<JSONResponse> getAll(HttpServletRequest request, @RequestBody JSONRequestVO mmJSONRequestVO)
			throws CommonException {

		JSONResponse jsonResponse = new JSONResponse();
		String accessToken = getHeaderAccessToken(request);
		AuthDetailsVO authDetailsVo = null;
		try {
			authDetailsVo = tokenValidate(accessToken);
			if (null == authDetailsVo) {
				// get from message.properties
				throw new LoginAuthException("Token Expired / Already Logined");
			}
			UserScreenMappingVO userScreenMappingVO = getScreen(mmJSONRequestVO.getSubScreenId(), authDetailsVo);
			List<UserVO> common = authenticationService.getAll();
			jsonResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
			jsonResponse.setSuccesObject(common);
			jsonResponse.setAuthSuccesObject(userScreenMappingVO);
			jsonResponse.setResponseMessage(getMessage("successMessage"));
			return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
		} catch (CommonException e) {
			logger.error("error", e);
			jsonResponse.setResponseCode(CommonConstant.FAILURE_CODE);
			jsonResponse.setResponseMessage(e.getMessage());
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		} catch (LoginAuthException e) {
			logger.error("error", e);
			jsonResponse.setResponseCode(CommonConstant.FAILURE_CODE);
			jsonResponse.setResponseMessage(e.getMessage());
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		} catch (Exception e) {
			logger.error("error", e);
			jsonResponse.setResponseCode(CommonConstant.ERROR_CODE);
			jsonResponse.setResponseMessage(getMessage("errorMessage"));
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		}
		return new ResponseEntity<>(jsonResponse, HttpStatus.OK);

	}

	@PostMapping(FilePathConstants.AUTHENTICATION_UPDATE)
	@ResponseBody
	public ResponseEntity<JSONResponse> update(HttpServletRequest request,
			@RequestBody MappingAuthenticationVO mappingAuthenticationVO) throws CommonException {

		JSONResponse jsonResponse = new JSONResponse();
		String accessToken = getHeaderAccessToken(request);
		AuthDetailsVO authDetailsVo = null;
		try {
			authDetailsVo = tokenValidate(accessToken);
			if (null == authDetailsVo) {
				// get from message.properties
				throw new LoginAuthException("Token Expired / Already Logined");
			}
			authenticationService.update(mappingAuthenticationVO, authDetailsVo);
			jsonResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
			jsonResponse.setResponseMessage(getMessage("updateSuccessMessage"));
			jsonResponse.setSuccesObject(CommonConstant.NULL);
			return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
		} catch (CommonException e) {
			logger.error(e.getMessage());
			jsonResponse.setResponseCode(CommonConstant.FAILURE_CODE);
			jsonResponse.setResponseMessage(e.getMessage());
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		} catch (LoginAuthException e) {
			logger.error("error", e);
			jsonResponse.setResponseCode(CommonConstant.FAILURE_CODE);
			jsonResponse.setResponseMessage(e.getMessage());
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		} catch (Exception e) {
			logger.error(e.getMessage());
			jsonResponse.setResponseCode(CommonConstant.ERROR_CODE);
			jsonResponse.setResponseMessage(getMessage("updateErrorMessage"));
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		}
		return new ResponseEntity<>(jsonResponse, HttpStatus.OK);

	}

	@PostMapping(FilePathConstants.SCREEN_MAPPING_VIEW)
	@ResponseBody
	public ResponseEntity<JSONResponse> screen(HttpServletRequest request,
			@RequestBody CommonDropDownVO commonDropDownVO) throws IOException {

		JSONResponse jsonResponse = new JSONResponse();
		String accessToken = getHeaderAccessToken(request);
		AuthDetailsVO authDetailsVo = null;
		try {
			authDetailsVo = tokenValidate(accessToken);
			if (null == authDetailsVo) {
				// get from message.properties
				throw new LoginAuthException("Token Expired / Already Logined");
			}
			List<CommonScreenDropDownVO> screenList = authenticationService.screen(authDetailsVo);
			jsonResponse.setUserName(authDetailsVo.getFirstName().concat(" "+authDetailsVo.getLastName()));
			jsonResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
			jsonResponse.setSuccesObject(screenList);
			jsonResponse.setResponseMessage(getMessage("successMessage"));
			return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
		} catch (CommonException e) {
			logger.error("error", e);
			jsonResponse.setResponseCode(CommonConstant.FAILURE_CODE);
			jsonResponse.setResponseMessage(e.getMessage());
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		} catch (LoginAuthException e) {
			logger.error("error", e);
			jsonResponse.setResponseCode(CommonConstant.FAILURE_CODE);
			jsonResponse.setResponseMessage(e.getMessage());
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		} catch (Exception e) {
			logger.error("error", e);
			jsonResponse.setResponseCode(CommonConstant.ERROR_CODE);
			jsonResponse.setResponseMessage(getMessage("errorMessage"));
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		}
		return new ResponseEntity<>(jsonResponse, HttpStatus.OK);

	}

	@PostMapping(FilePathConstants.SUB_SCREEN_MAPPING_VIEW)
	@ResponseBody
	public ResponseEntity<JSONResponse> subScreen(HttpServletRequest request,
			@RequestBody CommonDropDownVO commonDropDownVO) throws IOException {

		JSONResponse jsonResponse = new JSONResponse();
		String accessToken = getHeaderAccessToken(request);
		AuthDetailsVO authDetailsVo = null;
		try {
			authDetailsVo = tokenValidate(accessToken);
			if (null == authDetailsVo) {
				// get from message.properties
				throw new LoginAuthException("Token Expired / Already Logined");
			}
			List<CommonSubScreenDropDownVO> screenList = authenticationService.subScreen(commonDropDownVO,authDetailsVo);
			jsonResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
			jsonResponse.setSuccesObject(screenList);
			jsonResponse.setResponseMessage(getMessage("successMessage"));
			return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
		} catch (CommonException e) {
			logger.error("error", e);
			jsonResponse.setResponseCode(CommonConstant.FAILURE_CODE);
			jsonResponse.setResponseMessage(e.getMessage());
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		} catch (LoginAuthException e) {
			logger.error("error", e);
			jsonResponse.setResponseCode(CommonConstant.FAILURE_CODE);
			jsonResponse.setResponseMessage(e.getMessage());
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		} catch (Exception e) {
			logger.error("error", e);
			jsonResponse.setResponseCode(CommonConstant.ERROR_CODE);
			jsonResponse.setResponseMessage(getMessage("errorMessage"));
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		}
		return new ResponseEntity<>(jsonResponse, HttpStatus.OK);

	}

	@PostMapping(FilePathConstants.SCREEN_FIELD_MAPPING_VIEW)
	@ResponseBody
	public ResponseEntity<JSONResponse> screenField(HttpServletRequest request,
			@RequestBody CommonDropDownVO commonDropDownVO) throws IOException {

		JSONResponse jsonResponse = new JSONResponse();
		String accessToken = getHeaderAccessToken(request);
		AuthDetailsVO authDetailsVo = null;
		try {
			authDetailsVo = tokenValidate(accessToken);
			if (null == authDetailsVo) {
				// get from message.properties
				throw new LoginAuthException("Token Expired / Already Logined");
			}
			List<CommonScreenFieldDropDownVO> screenList = authenticationService.screenField(commonDropDownVO,authDetailsVo);
			jsonResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
			jsonResponse.setSuccesObject(screenList);
			jsonResponse.setResponseMessage(getMessage("successMessage"));
			return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
		} catch (CommonException e) {
			logger.error("error", e);
			jsonResponse.setResponseCode(CommonConstant.FAILURE_CODE);
			jsonResponse.setResponseMessage(e.getMessage());
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		} catch (LoginAuthException e) {
			logger.error("error", e);
			jsonResponse.setResponseCode(CommonConstant.FAILURE_CODE);
			jsonResponse.setResponseMessage(e.getMessage());
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		} catch (Exception e) {
			logger.error("error", e);
			jsonResponse.setResponseCode(CommonConstant.ERROR_CODE);
			jsonResponse.setResponseMessage(getMessage("errorMessage"));
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		}
		return new ResponseEntity<>(jsonResponse, HttpStatus.OK);

	}

	@PostMapping(FilePathConstants.SCREEN_FUNCTION_MAPPING_VIEW)
	@ResponseBody
	public ResponseEntity<JSONResponse> screenFunction(HttpServletRequest request,
			@RequestBody CommonDropDownVO commonDropDownVO) throws IOException {

		JSONResponse jsonResponse = new JSONResponse();
		String accessToken = getHeaderAccessToken(request);
		AuthDetailsVO authDetailsVo = null;
		try {
			authDetailsVo = tokenValidate(accessToken);
			if (null == authDetailsVo) {
				// get from message.properties
				throw new LoginAuthException("Token Expired / Already Logined");
			}
			List<CommonScreenFunctionDropDownVO> screenList = authenticationService.screenFunction(commonDropDownVO,authDetailsVo);
			jsonResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
			jsonResponse.setSuccesObject(screenList);
			jsonResponse.setResponseMessage(getMessage("successMessage"));
			return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
		} catch (CommonException e) {
			logger.error("error", e);
			jsonResponse.setResponseCode(CommonConstant.FAILURE_CODE);
			jsonResponse.setResponseMessage(e.getMessage());
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		} catch (LoginAuthException e) {
			logger.error("error", e);
			jsonResponse.setResponseCode(CommonConstant.FAILURE_CODE);
			jsonResponse.setResponseMessage(e.getMessage());
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		} catch (Exception e) {
			logger.error("error", e);
			jsonResponse.setResponseCode(CommonConstant.ERROR_CODE);
			jsonResponse.setResponseMessage(getMessage("errorMessage"));
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		}
		return new ResponseEntity<>(jsonResponse, HttpStatus.OK);

	}

}
