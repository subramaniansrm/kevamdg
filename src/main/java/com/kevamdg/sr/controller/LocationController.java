package com.kevamdg.sr.controller;

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
import com.kevamdg.sr.service.LocationService;
import com.kevamdg.sr.vo.AuthDetailsVO;
import com.kevamdg.sr.vo.JSONRequestVO;
import com.kevamdg.sr.vo.JSONResponse;
import com.kevamdg.sr.vo.LocationVO;
import com.kevamdg.sr.vo.UserScreenMappingVO;

/**
 * Class used to list, create, modify and delete Location details
 * 
 * @author raathikaabm
 *
 */

@RestController
public class LocationController extends CommonAction<LocationVO> {

	private static final Logger logger = LogManager.getLogger(LocationController.class);

	@Autowired
	private LocationService locationService;

	
	/**
	 * Method used to list all location details
	 * 
	 * @return
	 */
	@PostMapping(FilePathConstants.LOCATION_LIST)
	@ResponseBody
	public ResponseEntity<JSONResponse> getLocation(HttpServletRequest request,@RequestBody JSONRequestVO commonVO) {
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
			List<LocationVO> listUserLocationMasterVo = locationService.getLocation();
			/*
			 * if (listUserLocationMasterVo == null ||
			 * listUserLocationMasterVo.size() <= 0) {
			 * this.loadValidation(listUserLocationMasterVo); }
			 */
			// userLocation.setUserLocationMasterList(listUserLocationMasterVo);
			response.setAuthSuccesObject(userScreenMappingVO);
			response.setSuccesObject(listUserLocationMasterVo);
			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("successMessage"));
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

	/**
	 * Method used to create Location details
	 * 
	 * @param userLocationMasterVo
	 * @return
	 */
	@PostMapping(FilePathConstants.LOCATION_CREATE)
	@ResponseBody
	public ResponseEntity<JSONResponse> create(HttpServletRequest request,@RequestBody LocationVO userLocationMasterVo) {

		JSONResponse response = new JSONResponse();

		String accessToken = getHeaderAccessToken(request);
		AuthDetailsVO authDetailsVo = null;
		try {
			 authDetailsVo = tokenValidate(accessToken);
			if (null == authDetailsVo) {
				//get from message.properties
				throw new LoginAuthException("Token Expired / Already Logined");
			}
			locationService.create(userLocationMasterVo,authDetailsVo);

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

	/**
	 * Method used to update Location Details
	 * 
	 * @param userLocationMasterVo
	 * @return
	 */
	@PostMapping(FilePathConstants.LOCATION_UPDATE)
	@ResponseBody
	public ResponseEntity<JSONResponse> update(HttpServletRequest request,@RequestBody LocationVO userLocationMasterVo) {
		JSONResponse response = new JSONResponse();

		String accessToken = getHeaderAccessToken(request);
		AuthDetailsVO authDetailsVo = null;
		try {
			 authDetailsVo = tokenValidate(accessToken);
			if (null == authDetailsVo) {
				//get from message.properties
				throw new LoginAuthException("Token Expired / Already Logined");
			}
			locationService.update(userLocationMasterVo,authDetailsVo);

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

	/**
	 * Method used to delete location by list
	 * 
	 * @param userLocationMasterVo
	 * @return
	 */
	@PostMapping(FilePathConstants.LOCATION_DELETE)
	@ResponseBody
	public ResponseEntity<JSONResponse> delete(HttpServletRequest request,@RequestBody LocationVO userLocationMasterVo) {
		JSONResponse response = new JSONResponse();
		String accessToken = getHeaderAccessToken(request);
		AuthDetailsVO authDetailsVo = null;
		try {
			 authDetailsVo = tokenValidate(accessToken);
			if (null == authDetailsVo) {
				//get from message.properties
				throw new LoginAuthException("Token Expired / Already Logined");
			}

			locationService.delete(userLocationMasterVo,authDetailsVo);

			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("deleteSuccessMessage"));
			response.setSuccesObject(CommonConstant.NULL);

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

	/**
	 * Method used to view Location Details
	 * 
	 * @param userLocationMasterVo
	 * @return
	 */
	@PostMapping(FilePathConstants.LOCATION_VIEW)
	@ResponseBody
	public ResponseEntity<JSONResponse> View(HttpServletRequest request,@RequestBody JSONRequestVO commonVO) {
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
			LocationVO userLocation = new LocationVO();
			userLocation.setId(commonVO.getId());

			userLocation = locationService.view(commonVO.getId());
			response.setAuthSuccesObject(userScreenMappingVO);

			response.setSuccesObject(userLocation);
			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("successMessage"));
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

	/**
	 * Method used to search location details
	 * 
	 * @param userLocationMasterVo
	 * @return
	 */
	@PostMapping(FilePathConstants.LOCATION_SEARCH)
	@ResponseBody
	public ResponseEntity<JSONResponse> Search(HttpServletRequest request,@RequestBody LocationVO userLocationMasterVo) {
		JSONResponse response = new JSONResponse();

		String accessToken = getHeaderAccessToken(request);
		AuthDetailsVO authDetailsVo = null;
		try {
			 authDetailsVo = tokenValidate(accessToken);
			if (null == authDetailsVo) {
				//get from message.properties
				throw new LoginAuthException("Token Expired / Already Logined");
			}
			List<LocationVO> userLocationList = locationService.search(userLocationMasterVo);

			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("successMessage"));
			response.setSuccesObject(userLocationList);
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
	
	
	@PostMapping(FilePathConstants.LOCATION_ADD)
	public ResponseEntity<JSONResponse> addUserLocation(HttpServletRequest request,@RequestBody JSONRequestVO commonVO) {
		JSONResponse jsonResponse = new JSONResponse();

		String accessToken = getHeaderAccessToken(request);
		AuthDetailsVO authDetailsVo = null;
		try {
			 authDetailsVo = tokenValidate(accessToken);
			if (null == authDetailsVo) {
				//get from message.properties
				throw new LoginAuthException("Token Expired / Already Logined");
			}
			UserScreenMappingVO userScreenMappingVO = getScreen(commonVO.getSubScreenId(), authDetailsVo);
			jsonResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
			jsonResponse.setResponseMessage(getMessage("successMessage"));
			jsonResponse.setAuthSuccesObject(userScreenMappingVO);
			return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
		} catch (LoginAuthException e) {
			logger.error("error", e);
			jsonResponse.setResponseCode(CommonConstant.FAILURE_CODE);
			jsonResponse.setResponseMessage(e.getMessage());
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		} catch (Exception e) {
			logger.error(e.getMessage());
			jsonResponse.setResponseCode(CommonConstant.ERROR_CODE);
			jsonResponse.setResponseMessage(getMessage("errorMessage"));
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		}

		return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
	}



}
