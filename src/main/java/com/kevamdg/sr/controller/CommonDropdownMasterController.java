package com.kevamdg.sr.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kevamdg.sr.config.CommonException;
import com.kevamdg.sr.config.LoginAuthException;
import com.kevamdg.sr.constants.CommonConstant;
import com.kevamdg.sr.constants.FilePathConstants;
import com.kevamdg.sr.service.CommonDropdownMasterService;
import com.kevamdg.sr.util.ValidationUtil;
import com.kevamdg.sr.vo.AuthDetailsVO;
import com.kevamdg.sr.vo.DropdownCommonVO;
import com.kevamdg.sr.vo.JSONRequestVO;
import com.kevamdg.sr.vo.JSONResponse;
import com.kevamdg.sr.vo.UserScreenMappingVO;

@Controller
public class CommonDropdownMasterController extends CommonAction<DropdownCommonVO> {
	
	Logger logger = LoggerFactory.getLogger(CommonDropdownMasterController.class);

	@Autowired
	CommonDropdownMasterService commonDropdownMasterService;
	
	@PostMapping(FilePathConstants.CDM_LIST)
	@ResponseBody
	public ResponseEntity<JSONResponse> getAll(HttpServletRequest request,@RequestBody JSONRequestVO commonVO) {

		JSONResponse response = new JSONResponse();
		String accessToken = getHeaderAccessToken(request);
		AuthDetailsVO authDetailsVo = null;
		try {
			 authDetailsVo = tokenValidate(accessToken);
			if (null == authDetailsVo) {
				//get from message.properties
				throw new LoginAuthException("Token Expired / Already Logined");
			}
			UserScreenMappingVO userScreenMappingVO = getScreen(commonVO.getSubScreenId(), authDetailsVo);
			List<DropdownCommonVO> list = commonDropdownMasterService.getAll();

			if (null == list) {
				throw new CommonException(getMessage("common.noRecord"));
			}
			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("successMessage"));
			response.setSuccesObject(list);
			response.setAuthSuccesObject(userScreenMappingVO);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (CommonException e) {
			logger.error(e.getMessage());
			response.setResponseCode(CommonConstant.FAILURE_CODE);
			response.setResponseMessage(e.getMessage());
			response.setSuccesObject(CommonConstant.NULL);
		} catch (LoginAuthException e) {
			logger.error("error", e);
			response.setResponseCode(CommonConstant.FAILURE_CODE);
			response.setResponseMessage(e.getMessage());
			response.setSuccesObject(CommonConstant.NULL);
		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setResponseCode(CommonConstant.ERROR_CODE);
			response.setResponseMessage(getMessage("errorMessage"));
			response.setSuccesObject(CommonConstant.NULL);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	
	
	@PostMapping(FilePathConstants.CDM_SEARCH)
	@ResponseBody
	public ResponseEntity<JSONResponse> search(HttpServletRequest request,@RequestBody DropdownCommonVO dropdownCommonVO) {
		JSONResponse response = new JSONResponse();
		String accessToken = getHeaderAccessToken(request);
		AuthDetailsVO authDetailsVo = null;
		try {
			 authDetailsVo = tokenValidate(accessToken);
			if (null == authDetailsVo) {
				//get from message.properties
				throw new LoginAuthException("Token Expired / Already Logined");
			}
			List<DropdownCommonVO> list = commonDropdownMasterService.search(dropdownCommonVO);
			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("successMessage"));
			response.setSuccesObject(list);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (CommonException e) {
			logger.error(e.getMessage());
			response.setResponseCode(CommonConstant.FAILURE_CODE);
			response.setResponseMessage(e.getMessage());
			response.setSuccesObject(CommonConstant.NULL);
		} catch (LoginAuthException e) {
			logger.error("error", e);
			response.setResponseCode(CommonConstant.FAILURE_CODE);
			response.setResponseMessage(e.getMessage());
			response.setSuccesObject(CommonConstant.NULL);
		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setResponseCode(CommonConstant.ERROR_CODE);
			response.setResponseMessage(getMessage("errorMessage"));
			response.setSuccesObject(CommonConstant.NULL);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	@PostMapping(FilePathConstants.CDM_ADD)
	@ResponseBody
	public ResponseEntity<JSONResponse> add(HttpServletRequest request,@RequestBody JSONRequestVO commonVO) {
		JSONResponse response = new JSONResponse();
		String accessToken = getHeaderAccessToken(request);
		AuthDetailsVO authDetailsVo = null;
		try {
			 authDetailsVo = tokenValidate(accessToken);
			if (null == authDetailsVo) {
				//get from message.properties
				throw new LoginAuthException("Token Expired / Already Logined");
			}
			UserScreenMappingVO userScreenMappingVO = getScreen(commonVO.getSubScreenId(), authDetailsVo);
			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("saveSuccessMessage"));
			response.setAuthSuccesObject(userScreenMappingVO);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (LoginAuthException e) {
			logger.error("error", e);
			response.setResponseCode(CommonConstant.FAILURE_CODE);
			response.setResponseMessage(e.getMessage());
			response.setSuccesObject(CommonConstant.NULL);
		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setResponseCode(CommonConstant.ERROR_CODE);
			response.setResponseMessage(getMessage("errorMessage"));
			response.setSuccesObject(CommonConstant.NULL);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	
	@PostMapping(FilePathConstants.CDM_CREATE)
	@ResponseBody
	public ResponseEntity<JSONResponse> save(HttpServletRequest request,@RequestBody DropdownCommonVO dropdownCommonVO) {
		JSONResponse response = new JSONResponse();
		String accessToken = getHeaderAccessToken(request);
		AuthDetailsVO authDetailsVo = null;
		try {
			 authDetailsVo = tokenValidate(accessToken);
			if (null == authDetailsVo) {
				//get from message.properties
				throw new LoginAuthException("Token Expired / Already Logined");
			}
			commonDropdownMasterService.duplicate(dropdownCommonVO);
			
			logger.info(getMessage("processValidation"));
			this.saveValidation(dropdownCommonVO);
			logger.info(getMessage("processValidationCompleted"));

			commonDropdownMasterService.save(dropdownCommonVO,authDetailsVo);

			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("saveSuccessMessage"));
			response.setSuccesObject(CommonConstant.NULL);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (CommonException e) {
			logger.error(e.getMessage());
			response.setResponseCode(CommonConstant.FAILURE_CODE);
			response.setResponseMessage(e.getMessage());
			response.setSuccesObject(CommonConstant.NULL);
		} catch (LoginAuthException e) {
			logger.error("error", e);
			response.setResponseCode(CommonConstant.FAILURE_CODE);
			response.setResponseMessage(e.getMessage());
			response.setSuccesObject(CommonConstant.NULL);
		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setResponseCode(CommonConstant.ERROR_CODE);
			response.setResponseMessage(getMessage("saveErrorMessage"));
			response.setSuccesObject(CommonConstant.NULL);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	public void saveValidation(DropdownCommonVO dropdownCommonVO){
		
		if (ValidationUtil.isNullOrBlank(dropdownCommonVO.getDropdownCode())) {
			throw new CommonException(getMessage("cdm.code.validation"));
		}
		if (ValidationUtil.onlyDigits(dropdownCommonVO.getDropdownCode())) {
			throw new CommonException(getMessage("cdm.digit.validation"));
		}
		if (dropdownCommonVO.getDropdownCode().length()>4) {
			throw new CommonException(getMessage("cdm.length.validation"));
		}
		if (ValidationUtil.isNullOrBlank(dropdownCommonVO.getDropdownName())) {
			throw new CommonException(getMessage("cdm.name.validation"));
		}
		if (dropdownCommonVO.getDropdownName().length()>50) {
			throw new CommonException(getMessage("cdm.name.length.validation"));
		}
		if (ValidationUtil.isNullOrBlank(dropdownCommonVO.getFieldId())) {
			throw new CommonException(getMessage("cdm.field.name.validation"));
		}
			
	}
	
	@PostMapping(FilePathConstants.CDM_UPDATE)
	@ResponseBody
	public ResponseEntity<JSONResponse> update(HttpServletRequest request,@RequestBody DropdownCommonVO dropdownCommonVO) {
		JSONResponse response = new JSONResponse();
		String accessToken = getHeaderAccessToken(request);
		AuthDetailsVO authDetailsVo = null;
		try {
			 authDetailsVo = tokenValidate(accessToken);
			if (null == authDetailsVo) {
				//get from message.properties
				throw new LoginAuthException("Token Expired / Already Logined");
			}
			commonDropdownMasterService.duplicate(dropdownCommonVO);

			logger.info(getMessage("processValidation"));
			this.saveValidation(dropdownCommonVO);
			logger.info(getMessage("processValidationCompleted"));
			
			commonDropdownMasterService.update(dropdownCommonVO,authDetailsVo);

			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("updateSuccessMessage"));
			response.setSuccesObject(CommonConstant.NULL);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (CommonException e) {
			logger.error(e.getMessage());
			response.setResponseCode(CommonConstant.FAILURE_CODE);
			response.setResponseMessage(e.getMessage());
			response.setSuccesObject(CommonConstant.NULL);
		} catch (LoginAuthException e) {
			logger.error("error", e);
			response.setResponseCode(CommonConstant.FAILURE_CODE);
			response.setResponseMessage(e.getMessage());
			response.setSuccesObject(CommonConstant.NULL);
		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setResponseCode(CommonConstant.ERROR_CODE);
			response.setResponseMessage(getMessage("updateErrorMessage"));
			response.setSuccesObject(CommonConstant.NULL);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	
	
	@PostMapping(FilePathConstants.CDM_VIEW)
	@ResponseBody
	public ResponseEntity<JSONResponse> view(HttpServletRequest request,@RequestBody JSONRequestVO commonVO) {
		JSONResponse response = new JSONResponse();
		String accessToken = getHeaderAccessToken(request);
		AuthDetailsVO authDetailsVo = null;
		try {
			 authDetailsVo = tokenValidate(accessToken);
			if (null == authDetailsVo) {
				//get from message.properties
				throw new LoginAuthException("Token Expired / Already Logined");
			}
			UserScreenMappingVO userScreenMappingVO = getScreen(commonVO.getSubScreenId(), authDetailsVo);
			DropdownCommonVO dropdownCommonVO= commonDropdownMasterService.view(commonVO.getId());
			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("successMessage"));
			response.setSuccesObject(dropdownCommonVO);
			response.setAuthSuccesObject(userScreenMappingVO);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (CommonException e) {
			logger.error(e.getMessage());
			response.setResponseCode(CommonConstant.FAILURE_CODE);
			response.setResponseMessage(e.getMessage());
			response.setSuccesObject(CommonConstant.NULL);
		} catch (LoginAuthException e) {
			logger.error("error", e);
			response.setResponseCode(CommonConstant.FAILURE_CODE);
			response.setResponseMessage(e.getMessage());
			response.setSuccesObject(CommonConstant.NULL);
		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setResponseCode(CommonConstant.ERROR_CODE);
			response.setResponseMessage(getMessage("errorMessage"));
			response.setSuccesObject(CommonConstant.NULL);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	@PostMapping(FilePathConstants.CDM_DELETE)
	@ResponseBody
	public ResponseEntity<JSONResponse> delete(HttpServletRequest request,@RequestBody DropdownCommonVO dropdownCommonVO) {
		JSONResponse response = new JSONResponse();
		String accessToken = getHeaderAccessToken(request);
		AuthDetailsVO authDetailsVo = null;
		try {
			 authDetailsVo = tokenValidate(accessToken);
			if (null == authDetailsVo) {
				//get from message.properties
				throw new LoginAuthException("Token Expired / Already Logined");
			}
			commonDropdownMasterService.delete(dropdownCommonVO,authDetailsVo);

			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("deleteSuccessMessage"));
			response.setSuccesObject(CommonConstant.NULL);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (CommonException e) {
			logger.error(e.getMessage());
			response.setResponseCode(CommonConstant.FAILURE_CODE);
			response.setResponseMessage(e.getMessage());
			response.setSuccesObject(CommonConstant.NULL);
		} catch (LoginAuthException e) {
			logger.error("error", e);
			response.setResponseCode(CommonConstant.FAILURE_CODE);
			response.setResponseMessage(e.getMessage());
			response.setSuccesObject(CommonConstant.NULL);
		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setResponseCode(CommonConstant.ERROR_CODE);
			response.setResponseMessage(getMessage("deleteErrorMessage"));
			response.setSuccesObject(CommonConstant.NULL);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
