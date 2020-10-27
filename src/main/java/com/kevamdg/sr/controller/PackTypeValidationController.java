package com.kevamdg.sr.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kevamdg.sr.config.CommonException;
import com.kevamdg.sr.config.LoginAuthException;
import com.kevamdg.sr.constants.CommonConstant;
import com.kevamdg.sr.constants.FilePathConstants;
import com.kevamdg.sr.response.JSONResponse;
import com.kevamdg.sr.service.PackTypeValidationService;
import com.kevamdg.sr.util.ValidationUtil;
import com.kevamdg.sr.vo.AuthDetailsVO;
import com.kevamdg.sr.vo.JSONRequestVO;
import com.kevamdg.sr.vo.PackTypeValidationVO;
import com.kevamdg.sr.vo.UserScreenMappingVO;

@RestController
public class PackTypeValidationController extends CommonAction<PackTypeValidationVO> {
	
	private static final Logger logger = LogManager.getLogger(PackTypeValidationController.class);
	
	@Autowired
	private PackTypeValidationService packTypeValidationService;
	
	
	@PostMapping(FilePathConstants.PACK_TYPE_LIST)
	@ResponseBody
	public ResponseEntity<JSONResponse> getAll(HttpServletRequest request,@RequestBody JSONRequestVO commonVO) throws IOException {
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
			List<PackTypeValidationVO> packTypeValidationVOList = packTypeValidationService.getAll();

			if (null == packTypeValidationVOList) {
				throw new CommonException(getMessage("common.noRecord"));
			}
			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("successMessage"));
			response.setAuthSuccesObject(userScreenMappingVO);
			response.setSuccesObject(packTypeValidationVOList);
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
	
	
	@PostMapping(FilePathConstants.PACK_TYPE_SEARCH)
	@ResponseBody
	public ResponseEntity<JSONResponse> search(HttpServletRequest request,@RequestBody PackTypeValidationVO packTypeValidationVO) {
		JSONResponse response = new JSONResponse();
		String accessToken = getHeaderAccessToken(request);
		AuthDetailsVO authDetailsVo = null;
		try {
			 authDetailsVo = tokenValidate(accessToken);
			if (null == authDetailsVo) {
				//get from message.properties
				throw new LoginAuthException("Token Expired / Already Logined");
			}
			List<PackTypeValidationVO> list = packTypeValidationService.search(packTypeValidationVO);
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

	
	@PostMapping(FilePathConstants.PACK_TYPE_ADD)
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
	
	
	@PostMapping(FilePathConstants.PACK_TYPE_CREATE)
	@ResponseBody
	public ResponseEntity<JSONResponse> save(HttpServletRequest request,@RequestBody PackTypeValidationVO packTypeValidationVO) {
		JSONResponse response = new JSONResponse();
		String accessToken = getHeaderAccessToken(request);
		AuthDetailsVO authDetailsVo = null;
		try {
			 authDetailsVo = tokenValidate(accessToken);
			if (null == authDetailsVo) {
				//get from message.properties
				throw new LoginAuthException("Token Expired / Already Logined");
			}
			packTypeValidationService.duplicate(packTypeValidationVO);
			
			logger.info(getMessage("processValidation"));
			this.saveValidation(packTypeValidationVO);
			logger.info(getMessage("processValidationCompleted"));

			packTypeValidationService.save(packTypeValidationVO,authDetailsVo);

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
	
	public void saveValidation(PackTypeValidationVO packTypeValidationVO){
		
		if (ValidationUtil.isNullOrBlank(packTypeValidationVO.getPackSize())) {
			throw new CommonException(getMessage("packTypeValidationVO"));
		}
		
		if (ValidationUtil.isNullOrBlank(packTypeValidationVO.getPackTypeName().trim())) {
			throw new CommonException(getMessage("packtypeNameValidation"));
		}
		if (packTypeValidationVO.getPackTypeName().length()>50) {
			throw new CommonException(getMessage("packtypeName.length.validation"));
		}
		
		
	}
	
	@PostMapping(FilePathConstants.PACK_TYPE_UPDATE)
	@ResponseBody
	public ResponseEntity<JSONResponse> update(HttpServletRequest request,@RequestBody PackTypeValidationVO packTypeValidationVO) {
		JSONResponse response = new JSONResponse();
		String accessToken = getHeaderAccessToken(request);
		AuthDetailsVO authDetailsVo = null;
		try {
			 authDetailsVo = tokenValidate(accessToken);
			if (null == authDetailsVo) {
				//get from message.properties
				throw new LoginAuthException("Token Expired / Already Logined");
			}
			packTypeValidationService.duplicate(packTypeValidationVO);

			logger.info(getMessage("processValidation"));
			this.saveValidation(packTypeValidationVO);
			logger.info(getMessage("processValidationCompleted"));
			
			packTypeValidationService.update(packTypeValidationVO,authDetailsVo);

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
	
	
	@PostMapping(FilePathConstants.PACK_TYPE_VIEW)
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
			PackTypeValidationVO packTypeValidationVO = packTypeValidationService.view(commonVO.getId());
			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("successMessage"));
			response.setSuccesObject(packTypeValidationVO);
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
	
	
	@PostMapping(FilePathConstants.PACK_TYPE_DELETE)
	@ResponseBody
	public ResponseEntity<JSONResponse> delete(HttpServletRequest request,@RequestBody PackTypeValidationVO packTypeValidationVO) {
		JSONResponse response = new JSONResponse();
		String accessToken = getHeaderAccessToken(request);
		AuthDetailsVO authDetailsVo = null;
		try {
			 authDetailsVo = tokenValidate(accessToken);
			if (null == authDetailsVo) {
				//get from message.properties
				throw new LoginAuthException("Token Expired / Already Logined");
			}
			packTypeValidationService.delete(packTypeValidationVO,authDetailsVo);

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
