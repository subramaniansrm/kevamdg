package com.kevamdg.sr.controller;

import java.io.IOException;
import java.util.ArrayList;
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
import com.kevamdg.sr.service.UserRoleService;
import com.kevamdg.sr.vo.AuthDetailsVO;
import com.kevamdg.sr.vo.JSONRequestVO;
import com.kevamdg.sr.vo.UserRoleVO;
import com.kevamdg.sr.vo.UserScreenMappingVO;

@RestController
public class UserRoleController extends CommonAction<UserRoleVO> {

	@Autowired
	private UserRoleService userRoleService;

	private static final Logger logger = LogManager.getLogger(UserRoleController.class);

	/**
	 * This method is used to list userRole details
	 * 
	 * 
	 * @return response Response
	 * @throws IOException
	 */
	@PostMapping(FilePathConstants.GET_USER_ROLE_LIST)
	@ResponseBody
	public ResponseEntity<JSONResponse> listUserRole(HttpServletRequest request, @RequestBody JSONRequestVO commonVO)
			throws IOException {
		JSONResponse response = new JSONResponse();
		String accessToken = getHeaderAccessToken(request);
		AuthDetailsVO authDetailsVo = null;
		try {
			authDetailsVo = tokenValidate(accessToken);
			if (null == authDetailsVo) {
				// get from message.properties
				throw new LoginAuthException("Token Expired / Already Logined");
			}
			UserScreenMappingVO userScreenMappingVO = getScreen(commonVO.getSubScreenId(), authDetailsVo);

			List<UserRoleVO> userRoleVoList = new ArrayList<UserRoleVO>();

			userRoleVoList = userRoleService.getUserRoleList();

			if (null == userRoleVoList) {
				throw new CommonException(getMessage("common.noRecord"));
			}
			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("successMessage"));
			response.setAuthSuccesObject(userScreenMappingVO);

			response.setSuccesObject(userRoleVoList);
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
	 * Method is used to add the screen fields
	 * 
	 * @param userRoleVo
	 * @return response
	 */
	@PostMapping(FilePathConstants.GET_USER_ROLE_ADD)
	@ResponseBody
	public ResponseEntity<JSONResponse> addScreenFields(HttpServletRequest request, @RequestBody JSONRequestVO commonVO)
			throws CommonException {
		JSONResponse response = new JSONResponse();
		String accessToken = getHeaderAccessToken(request);
		AuthDetailsVO authDetailsVo = null;
		try {
			authDetailsVo = tokenValidate(accessToken);
			if (null == authDetailsVo) {
				// get from message.properties
				throw new LoginAuthException("Token Expired / Already Logined");
			}
			UserRoleVO userRole = new UserRoleVO();
			UserScreenMappingVO userScreenMappingVO = getScreen(commonVO.getSubScreenId(), authDetailsVo);
			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("successMessage"));
			response.setAuthSuccesObject(userScreenMappingVO);
			response.setSuccesObject(userRole);
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

	/**
	 * This method is used to add new userRole deatils
	 * 
	 * @param saveUserRoleMaster
	 *            UserRoleVo
	 * @return response Response
	 */
	@PostMapping(FilePathConstants.SAVE_USER_ROLE)
	@ResponseBody
	ResponseEntity<JSONResponse> saveUserRole(HttpServletRequest request, @RequestBody UserRoleVO saveUserRoleMaster) {
		JSONResponse response = new JSONResponse();
		String accessToken = getHeaderAccessToken(request);
		AuthDetailsVO authDetailsVo = null;
		try {
			authDetailsVo = tokenValidate(accessToken);
			if (null == authDetailsVo) {
				// get from message.properties
				throw new LoginAuthException("Token Expired / Already Logined");
			}
			// saveValidate(saveUserRoleMaster);
			userRoleService.duplicateRole(saveUserRoleMaster);

			userRoleService.saveUserRole(saveUserRoleMaster, authDetailsVo);

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
	 * This method to update userRole details
	 * 
	 * @param updateUserRoleMaster
	 *            UserRoleVo
	 * @return response Response
	 */
	@PostMapping(FilePathConstants.UPDATE_USER_ROLE)
	@ResponseBody
	public ResponseEntity<JSONResponse> updateUserRole(HttpServletRequest request,
			@RequestBody UserRoleVO updateUserRoleMaster) {
		JSONResponse response = new JSONResponse();
		String accessToken = getHeaderAccessToken(request);
		AuthDetailsVO authDetailsVo = null;
		try {
			authDetailsVo = tokenValidate(accessToken);
			if (null == authDetailsVo) {
				// get from message.properties
				throw new LoginAuthException("Token Expired / Already Logined");
			}
			// updateValidate(updateUserRoleMaster);
			userRoleService.duplicateRole(updateUserRoleMaster);

			userRoleService.updateUserRole(updateUserRoleMaster, authDetailsVo);

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
	 * This method to delete userRole
	 * 
	 * @param deleteUserRoleMaster
	 *            UserRoleVo
	 * @return response Response
	 */
	@PostMapping(FilePathConstants.DELETE_USER_ROLE)
	@ResponseBody
	public ResponseEntity<JSONResponse> deleteUserRole(HttpServletRequest request,
			@RequestBody UserRoleVO deleteUserRoleMaster) {
		JSONResponse response = new JSONResponse();
		String accessToken = getHeaderAccessToken(request);
		AuthDetailsVO authDetailsVo = null;
		try {
			authDetailsVo = tokenValidate(accessToken);
			if (null == authDetailsVo) {
				// get from message.properties
				throw new LoginAuthException("Token Expired / Already Logined");
			}
			// deleteValidate(deleteUserRoleMaster);
			userRoleService.deleteUserRole(deleteUserRoleMaster, authDetailsVo);

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

	/**
	 * This method to search userRole
	 * 
	 * @param searchUserRoleMaster
	 *            UserRoleVo
	 * @return response Response
	 * @throws IOException
	 */
	@PostMapping(FilePathConstants.SEARCH_USER_ROLE)
	@ResponseBody
	public ResponseEntity<JSONResponse> searchGlobalUserRole(HttpServletRequest request,
			@RequestBody UserRoleVO searchUserRoleMaster) throws IOException {
		JSONResponse response = new JSONResponse();
		String accessToken = getHeaderAccessToken(request);
		AuthDetailsVO authDetailsVo = null;
		try {
			authDetailsVo = tokenValidate(accessToken);
			if (null == authDetailsVo) {
				// get from message.properties
				throw new LoginAuthException("Token Expired / Already Logined");
			}
			List<UserRoleVO> userRoleMasterVoList = userRoleService.getGlobalSearchUserRole(searchUserRoleMaster);
			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("successMessage"));
			response.setSuccesObject(userRoleMasterVoList);
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
	 * This method to view userDepartment
	 * 
	 * @param userRoleMaster
	 *            UserRoleVo
	 * @return response Response
	 * @throws IOException
	 */
	@PostMapping(FilePathConstants.VIEW_USER_ROLE)
	@ResponseBody
	public ResponseEntity<JSONResponse> viewUserRole(HttpServletRequest request, @RequestBody JSONRequestVO commonVO)
			throws IOException {
		JSONResponse response = new JSONResponse();
		String accessToken = getHeaderAccessToken(request);
		AuthDetailsVO authDetailsVo = null;
		try {
			authDetailsVo = tokenValidate(accessToken);
			if (null == authDetailsVo) {
				// get from message.properties
				throw new LoginAuthException("Token Expired / Already Logined");
			}
			UserScreenMappingVO userScreenMappingVO = getScreen(commonVO.getSubScreenId(), authDetailsVo);
			UserRoleVO userRoleVo = new UserRoleVO();

			
			userRoleVo.setId(commonVO.getId());
			userRoleVo = userRoleService.viewUserRole(commonVO.getId());
			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("successMessage"));
			response.setAuthSuccesObject(userScreenMappingVO);
			response.setSuccesObject(userRoleVo);
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

	/***
	 * Mthod used to copy
	 * 
	 * @param saveUserRoleMaster
	 * @return
	 */

	@PostMapping(FilePathConstants.COPY_USER_ROLE)
	@ResponseBody
	ResponseEntity<JSONResponse> copyUserRole(HttpServletRequest request, @RequestBody UserRoleVO saveUserRoleMaster) {
		JSONResponse response = new JSONResponse();
		String accessToken = getHeaderAccessToken(request);
		AuthDetailsVO authDetailsVo = null;
		try {
			authDetailsVo = tokenValidate(accessToken);
			if (null == authDetailsVo) {
				// get from message.properties
				throw new LoginAuthException("Token Expired / Already Logined");
			}
			// saveValidate(saveUserRoleMaster);
			userRoleService.duplicateRole(saveUserRoleMaster);

			userRoleService.saveUserRole(saveUserRoleMaster, authDetailsVo);

			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("copyErroMessage"));
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
			response.setResponseMessage(getMessage("errorMessage"));
			response.setSuccesObject(CommonConstant.NULL);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
