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
import com.kevamdg.sr.constants.ControlNameConstants;
import com.kevamdg.sr.constants.FilePathConstants;
import com.kevamdg.sr.response.JSONResponse;
import com.kevamdg.sr.service.UserMappingService;
import com.kevamdg.sr.util.ValidationUtil;
import com.kevamdg.sr.vo.AuthDetailsVO;
import com.kevamdg.sr.vo.ScreenAuthorizationVO;
import com.kevamdg.sr.vo.UserMappingVo;

/**
 * This Controller is used to Create , Update , Delete , Get all , Get all
 * search , Load in the user mapping .
 * 
 * @author Sai
 *
 */

@RestController
public class UserMappingController extends CommonAction<UserMappingVo> {
	@Autowired
	UserMappingService userMappingService;

	
	
	private static final Logger logger = LogManager.getLogger(UserMappingController.class);

	/**
	 * Method is used to get all the details.
	 * 
	 * @return response Response
	 * 
	 */
	@PostMapping(FilePathConstants.USER_MAPPING_LIST)
	@ResponseBody
	public ResponseEntity<JSONResponse> getAll(@RequestBody ScreenAuthorizationVO screenAuthorizationMaster) {

		JSONResponse response = new JSONResponse();
		
		try {
			
			UserMappingVo userMappingVo = userMappingService.getScreenFields(screenAuthorizationMaster);
			List<UserMappingVo> userMappingVoList = userMappingService.getAll();
			userMappingVo.setUserMappingVoList(userMappingVoList);		
			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("successMessage"));
			response.setSuccesObject(userMappingVo);
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
	 * This Method is used to Add user mapping.
	 * 
	 * @param userMappingVo
	 *            UserMappingVo
	 * @return response Response
	 */

	@PostMapping(FilePathConstants.USER_MAPPING_ADD)
	@ResponseBody
	public ResponseEntity<JSONResponse> addRequestType(@RequestBody UserMappingVo userMappingVo) {
		JSONResponse response = new JSONResponse();
		
		try {
			 			
			UserMappingVo usermappingVo = userMappingService
					.getScreenFields(userMappingVo.getScreenAuthorizationMaster());
			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("saveSuccessMessage"));
			response.setSuccesObject(usermappingVo);
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
	 * This Method is used to create user mapping.
	 * 
	 * @param userMappingVo
	 *            UserMappingVo
	 * @return response Response
	 */

	@PostMapping(FilePathConstants.USER_MAPPING_CREATE)
	@ResponseBody
	public ResponseEntity<JSONResponse> create(@RequestBody UserMappingVo userMappingVo) {

		JSONResponse response = new JSONResponse();
		
		try {
			
			logger.info(getMessage("processValidation"));
			saveValidation(userMappingVo);
			logger.info(getMessage("processValidationCompleted"));
			findDuplicateUser(userMappingVo);
			userMappingService.UserMapping(userMappingVo);
			
			userMappingService.create(userMappingVo);

			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("saveSuccessMessage"));
			response.setSuccesObject(CommonConstant.NULL);
		} catch (CommonException e) {
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

	private void findDuplicateUser(UserMappingVo userMappingVo) {
		
		userMappingService.findDuplicate(userMappingVo);
	}
	/**
	 * This Method is used to update user mapping.
	 * 
	 * @param userMappingVo
	 *            UserMappingVo
	 * @return response Response
	 */

	@PostMapping(FilePathConstants.USER_MAPPING_UPDATE)
	@ResponseBody
	public ResponseEntity<JSONResponse> update(@RequestBody UserMappingVo userMappingVo) {

		JSONResponse response = new JSONResponse();
		
		try {
			
			userMappingService.UserMapping(userMappingVo);
			findDuplicateUser(userMappingVo);

			userMappingService.update(userMappingVo);

			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("updateSuccessMessage"));
			response.setSuccesObject(CommonConstant.NULL);
		} catch (CommonException e) {
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
	 * This Method is used to load user mapping.
	 * 
	 * @param userMappingVo
	 *            UserMappingVo
	 * @return response Response
	 */

	@PostMapping(FilePathConstants.USER_MAPPING_LOAD)
	@ResponseBody
	public ResponseEntity<JSONResponse> load(@RequestBody UserMappingVo userMappingVo) {

		JSONResponse response = new JSONResponse();
		
		try {
			
			logger.info(getMessage("processValidation"));

			//loadValidation(userMappingVo);

			logger.info(getMessage("processValidationCompleted"));

			UserMappingVo usermappingVo = userMappingService
					.getScreenFields(userMappingVo.getScreenAuthorizationMaster());

			usermappingVo.setUserMappingId(userMappingVo.getUserMappingId());

			usermappingVo = userMappingService.load(usermappingVo);

			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("successMessage"));
			response.setSuccesObject(usermappingVo);
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
	 * This Method is used to delete user mapping.
	 * 
	 * @param userMappingVo
	 *            UserMappingVo
	 * @return response Response
	 */

	@PostMapping(FilePathConstants.USER_MAPPING_DELETE)
	@ResponseBody
	public ResponseEntity<JSONResponse> delete(@RequestBody UserMappingVo userMappingVo) {

		JSONResponse response = new JSONResponse();
		
		try {
			
			logger.info(getMessage("processValidation"));
			deleteValidate(userMappingVo);
			logger.info(getMessage("processValidationCompleted"));
			userMappingService.delete(userMappingVo);

			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("deleteSuccessMessage"));
			response.setSuccesObject(CommonConstant.NULL);
		} catch (CommonException e) {
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
	 * This Method is used to search user mapping.
	 * 
	 * @param userMappingVo
	 *            UserMappingVo
	 * @return response Response
	 */
	@PostMapping(FilePathConstants.USER_MAPPING_SEARCH)
	@ResponseBody
	public ResponseEntity<JSONResponse> getAllSearch(@RequestBody UserMappingVo userMappingVo) {

		JSONResponse response = new JSONResponse();
		
		try {
			

			List<UserMappingVo> userMappingVoList = userMappingService.getAllSearch(userMappingVo);
			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("successMessage"));
			response.setSuccesObject(userMappingVoList);

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
	 * This method is used to validate the Create and Update user mapping
	 * detail.
	 * 
	 * @param userMappingVo
	 *            UserMappingVo
	 */
	private void saveValidation(UserMappingVo userMappingVo) {

		for (String field : userMappingVo.getScreenFieldDisplayVoList()) {

			// User Id Validation
			if (ControlNameConstants.USER_MAPPING_USER.equals(field)) {

				if (ValidationUtil.isNullOrBlank(userMappingVo.getUserId())) {
					throw new CommonException(getMessage("usermapping.user.required"));
				}
			}
			// Location Validation
			if (ControlNameConstants.USER_MAPPING_LOCATION.equals(field)) {

				if (ValidationUtil.isNullOrBlank(userMappingVo.getUserLocationId())) {
					throw new CommonException(getMessage("usermapping.location.required"));
				}
			}
			// Department Validation
			if (ControlNameConstants.USER_MAPPING_DEPARTMENT.equals(field)) {

				if (ValidationUtil.isNullOrBlank(userMappingVo.getUserDepartmentId())) {
					throw new CommonException(getMessage("usermapping.department.required"));
				}
			}
			// Level Validation
			if (ControlNameConstants.USER_MAPPING_LEVEL.equals(field)) {

				if (ValidationUtil.isNullOrBlank(userMappingVo.getLevelId())) {
					throw new CommonException(getMessage("usermapping.level.required"));
				}
			}
			// Role Validation
			if (ControlNameConstants.USER_MAPPING_USERROLE.equals(field)) {

				if (ValidationUtil.isNullOrBlank(userMappingVo.getUserRoleId())) {
					throw new CommonException(getMessage("usermapping.role.required"));
				}
			}
			// Reporting User Department
			if (ControlNameConstants.USER_MAPPING_REPORTING_USER_DEPARTMENT.equals(field)) {

				if (ValidationUtil.isNullOrBlank(userMappingVo.getReportingUserDepartment())) {
					throw new CommonException(getMessage("usermapping.reportingUserDepartment.required"));
				}
			}
			// Reporting To User
			if (ControlNameConstants.USER_MAPPING_REPORTING_TO_USER.equals(field)) {

				if (ValidationUtil.isNullOrBlank(userMappingVo.getReportingToUser())) {
					throw new CommonException(getMessage("usermapping.reportingToUser.required"));
				}
				if (userMappingVo.getUserId() == userMappingVo.getReportingToUser()) {
					throw new CommonException(getMessage("userandreportingtousercannotbesame"));
				}
			}
		}
	}

	/**
	 * This method is used to validate the Update user mapping detail.
	 * 
	 * @param userMappingVo
	 *            UserMappingVo
	 */

	@SuppressWarnings("unused")
	private void updateValidation(UserMappingVo userMappingVo) {

		for (String field : userMappingVo.getScreenFieldDisplayVoList()) {

			// User Mapping Id Validation

			if (ValidationUtil.isNullOrBlank(userMappingVo.getUserMappingId())) {
				throw new CommonException(getMessage("usermapping.usermapping.required"));

			}
			// User Id Validation
			if (ControlNameConstants.USER_MAPPING_USER.equals(field)) {

				if (ValidationUtil.isNullOrBlank(userMappingVo.getUserId())) {
					throw new CommonException(getMessage("usermapping.user.required"));
				}
			}
			// Location Validation
			if (ControlNameConstants.USER_MAPPING_LOCATION.equals(field)) {

				if (ValidationUtil.isNullOrBlank(userMappingVo.getUserLocationId())) {
					throw new CommonException(getMessage("usermapping.location.required"));
				}
			}
			// Department Validation
			if (ControlNameConstants.USER_MAPPING_DEPARTMENT.equals(field)) {

				if (ValidationUtil.isNullOrBlank(userMappingVo.getUserDepartmentId())) {
					throw new CommonException(getMessage("usermapping.department.required"));
				}
			}
			// Level Validation
			if (ControlNameConstants.USER_MAPPING_LEVEL.equals(field)) {

				if (ValidationUtil.isNullOrBlank(userMappingVo.getLevelId())) {
					throw new CommonException(getMessage("usermapping.level.required"));
				}
			}
			// Role Validation
			if (ControlNameConstants.USER_MAPPING_USERROLE.equals(field)) {

				if (ValidationUtil.isNullOrBlank(userMappingVo.getUserRoleId())) {
					throw new CommonException(getMessage("usermapping.role.required"));
				}
			}
			// Reporting User Department
			if (ControlNameConstants.USER_MAPPING_REPORTING_USER_DEPARTMENT.equals(field)) {

				if (ValidationUtil.isNullOrBlank(userMappingVo.getReportingUserDepartment())) {
					throw new CommonException(getMessage("usermapping.reportingUserDepartment.required"));
				}
			}
			// Reporting To User
			if (ControlNameConstants.USER_MAPPING_REPORTING_TO_USER.equals(field)) {

				if (ValidationUtil.isNullOrBlank(userMappingVo.getReportingToUser())) {
					throw new CommonException(getMessage("usermapping.reportingToUser.required"));
				}
			}
		}
	}

	/**
	 * This method is used to validate the Load of user Mapping details.
	 * 
	 * @param userMappingVo
	 *            UserMappingVo
	 */
	/*private void loadValidation(UserMappingVo userMappingVo) {
		if (userMappingVo == null) {
			throw new CommonException(getMessage("common.noRecord"));
		}
	}*/

	/**
	 * This method is used to validate the GetAllSearch of user mapping detail.
	 * 
	 * @param userMappingVo
	 *            UserMappingVo
	 */
	private void deleteValidate(UserMappingVo userMappingVo) {

		if (null == userMappingVo.getUserMappingList()) {
			throw new CommonException(getMessage("delete.validation"));
		}

	}

	/**
	 * This method is used to validate the GetAllsearch Id
	 * 
	 * @param userMappingVo
	 *            UserMappingVo
	 */
	@SuppressWarnings("unused")
	private void idValidation(UserMappingVo userMappingVo) {

		if (userMappingVo.getUserMappingId() <= 0 || userMappingVo.getUserMappingId() == 0) {
			throw new CommonException(getMessage("search.validation"));
		}
	}
	/**
	 * This method is used to validate the duplicate Id
	 * 
	 * @param userMappingVo
	 *            UserMappingVo
	 */
	public int UserMapping(UserMappingVo userMappingVo) {

		if (userMappingVo.getUserMappingId() > 0) {
			throw new CommonException(getMessage("duplicate.validation"));

		}

		return 0;
	}

}
