package com.kevamdg.sr.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
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
import com.kevamdg.sr.service.ScreenAssignmentService;
import com.kevamdg.sr.vo.AuthenticationListCombo;
import com.kevamdg.sr.vo.ScreenAuthenticationMaster;
import com.kevamdg.sr.vo.ScreenAuthorizationVO;
import com.kevamdg.sr.vo.UserRoleMasterListDisplayVo;;



/**
 * 
 * @author raathikaabm
 *
 */
@RestController
@Scope(value = "request")
public class ScreenAssignmentAction extends CommonAction<ScreenAuthenticationMaster> {

	@Autowired
	private ScreenAssignmentService screenAssignmentService;


	private static final Logger logger = LogManager.getLogger(ScreenAssignmentAction.class);

	@PostMapping(FilePathConstants.AUTHENTICATION_SEARCH)
	@ResponseBody
	public ResponseEntity<JSONResponse> searchScreenAssignmentList(HttpServletRequest request,
			@RequestBody ScreenAuthenticationMaster screenAuthorizationMaster) {

		JSONResponse response = new JSONResponse();
		
		try {
			
			ScreenAuthenticationMaster screenAuthenticationMaster = new ScreenAuthenticationMaster();
			List<UserRoleMasterListDisplayVo> userRoleMasterListDisplayVos = new ArrayList<UserRoleMasterListDisplayVo>();
			userRoleMasterListDisplayVos = screenAssignmentService
					.searchScreenAuthentication(screenAuthorizationMaster);
			screenAuthenticationMaster.setUserRoleList(userRoleMasterListDisplayVos);

			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("successMessage"));

			response.setSuccesObject(screenAuthenticationMaster);
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
	 * method to save Screen Assignment
	 * 
	 * @param screenAuthenticationMaster
	 * @param model
	 * @param result
	 * @return screenAuthenticationMaster
	 */
	@PostMapping(FilePathConstants.AUTHENTICATION_CREATE)
	@ResponseBody
	public ResponseEntity<JSONResponse> saveScreenAssignment(
			@RequestBody ScreenAuthenticationMaster screenAuthenticationMaster) {


		JSONResponse response = new JSONResponse();
		
		try {
			
			screenAssignmentService.saveScreenAssignment(screenAuthenticationMaster,screenAuthenticationMaster.getAuthDetailsVo());
			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("saveSuccessMessage"));
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (LoginAuthException e) {
			logger.error("error", e);
			response.setResponseCode(CommonConstant.FAILURE_CODE);
			response.setResponseMessage(e.getMessage());
			response.setSuccesObject(CommonConstant.NULL);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			response.setResponseCode(CommonConstant.ERROR_CODE);
			response.setResponseMessage(getMessage("errorMessage"));
			response.setSuccesObject(CommonConstant.NULL);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@PostMapping(FilePathConstants.AUTHENTICATION_DELETE)
	@ResponseBody
	public ResponseEntity<JSONResponse> deleteScreenAssignment(HttpServletRequest request,
			@RequestBody ScreenAuthenticationMaster screenAuthenticationMaster) throws CommonException {


		JSONResponse response = new JSONResponse();
		
		try {
			

			if (null != screenAuthenticationMaster.getRoleId()) {
				screenAssignmentService.deleteScreenAssignment(screenAuthenticationMaster);
			}
			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("deleteSuccessMessage"));
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

	@PostMapping(FilePathConstants.AUTHENTICATION_LIST)
	@ResponseBody
	public ResponseEntity<JSONResponse> listRoles(HttpServletRequest request,
			@RequestBody ScreenAuthorizationVO screenAuthenticationMaster) {

		JSONResponse response = new JSONResponse();
		
		try {
			
			ScreenAuthenticationMaster screenAuthenticationMaster1 = screenAssignmentService
					.getScreenFields(screenAuthenticationMaster,screenAuthenticationMaster.getAuthDetailsVo());

			List<UserRoleMasterListDisplayVo> userRoleMasterListDisplayVos = new ArrayList<UserRoleMasterListDisplayVo>();
			if (null != screenAuthenticationMaster) {
				// Load Role based User List
				userRoleMasterListDisplayVos = screenAssignmentService.getRoleList();
				screenAuthenticationMaster1.setUserRoleList(userRoleMasterListDisplayVos);
			}
			response.setSuccesObject(screenAuthenticationMaster1);
			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("successMessage"));
		}catch (LoginAuthException e) {
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

	@PostMapping(FilePathConstants.AUTHENTICATION_ADD)
	@ResponseBody
	public ResponseEntity<JSONResponse> load(@RequestBody ScreenAuthenticationMaster  screenAuthenticationMstr) {
		JSONResponse response = new JSONResponse();
		try {


			ScreenAuthenticationMaster  screenAuthenticationMaster = screenAssignmentService
					.getScreenFields(screenAuthenticationMstr.getScreenAuthorizationMaster(),screenAuthenticationMstr.getAuthDetailsVo());

			if (null != screenAuthenticationMstr.getRoleId()) {
				screenAuthenticationMaster.setRoleId(screenAuthenticationMstr.getRoleId());
			}

			screenAssignmentService.load(screenAuthenticationMaster);
			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("successMessage"));
			response.setSuccesObject(screenAuthenticationMaster);

		} catch (LoginAuthException e) {
			logger.error("error", e);
			response.setResponseCode(CommonConstant.FAILURE_CODE);
			response.setResponseMessage(e.getMessage());
			response.setSuccesObject(CommonConstant.NULL);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			response.setResponseCode(CommonConstant.ERROR_CODE);
			response.setResponseMessage(getMessage("errorMessage"));
			response.setSuccesObject(CommonConstant.NULL);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(FilePathConstants.AUTHENTICATION_SUB_SCREEN)
	@ResponseBody
	public ResponseEntity<JSONResponse> loadRoleBasedSubScreenList(HttpServletRequest request,
			@RequestBody ScreenAuthenticationMaster screenAuthenticationMaster) {

		JSONResponse response = new JSONResponse();
		
		try {
			
			List<AuthenticationListCombo> authenticationListComboList = screenAssignmentService
					.loadRoleBasedSubScreenList(screenAuthenticationMaster);
			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("successMessage"));

			response.setSuccesObject(authenticationListComboList);
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

	@PostMapping(FilePathConstants.AUTHENTICATION_FIELD)
	@ResponseBody
	public ResponseEntity<JSONResponse> loadRoleBasedScreenFieldsList(HttpServletRequest request,
			@RequestBody ScreenAuthenticationMaster screenAuthenticationMaster) {


		JSONResponse response = new JSONResponse();
		
		try {
			
			List<AuthenticationListCombo> authenticationListComboList = screenAssignmentService
					.loadRoleBasedScreenFieldsList(screenAuthenticationMaster);
			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("successMessage"));

			response.setSuccesObject(authenticationListComboList);
		}
		catch (LoginAuthException e) {
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

	@PostMapping(FilePathConstants.AUTHENTICATION_FUNCTION)
	@ResponseBody
	public ResponseEntity<JSONResponse> loadRoleBasedFunctionList(HttpServletRequest request,
			@RequestBody ScreenAuthenticationMaster screenAuthenticationMaster) {
		screenAssignmentService.loadRoleBasedFunctionList(screenAuthenticationMaster);

		JSONResponse response = new JSONResponse();
		
		try {
			
			List<AuthenticationListCombo> authenticationListComboList = screenAssignmentService
					.loadRoleBasedFunctionList(screenAuthenticationMaster);
			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("successMessage"));

			response.setSuccesObject(authenticationListComboList);
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

	@ResponseBody
	public Map<Integer, String> loadRoleCombo() {
		return screenAssignmentService.loadRoleCombo();
	}

}
