package com.kevamdg.sr.controller;

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
import com.kevamdg.sr.service.UserLoginService;
import com.kevamdg.sr.vo.LoginForm;

/**
 * Controller is used for Login
 * 
 * @author Mohanapriya S
 *
 */
@RestController
public class UserLoginController extends CommonAction<LoginForm> {

	private static final Logger logger = LogManager.getLogger(UserLoginController.class);

	@Autowired
	private UserLoginService userLoginService;


	/**
	 * This method is to used for change password.
	 * 
	 * @param changePasswordRequest
	 * @return LoginForm
	 */
	@PostMapping(FilePathConstants.KIOSK_CHANGE_PASSWORD)
	@ResponseBody
	public ResponseEntity<JSONResponse> changePassword(@RequestBody LoginForm changePasswordRequest)throws CommonException {
		JSONResponse response = new JSONResponse();
		try {

			userLoginService.getChangePassword(changePasswordRequest);

			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("successMessage"));
			response.setSuccesObject(CommonConstant.NULL);

		} catch (CommonException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			response.setResponseCode(CommonConstant.FAILURE_CODE);
			response.setResponseMessage(e.getMessage());
			response.setSuccesObject(CommonConstant.NULL);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (LoginAuthException e) {
			logger.error("error", e);
			response.setResponseCode(CommonConstant.FAILURE_CODE);
			response.setResponseMessage(e.getMessage());
			response.setSuccesObject(CommonConstant.NULL);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			response.setResponseCode(CommonConstant.ERROR_CODE);
			response.setResponseMessage(getMessage("errorMessage"));
			response.setSuccesObject(CommonConstant.NULL);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * This method is to used for forgot password process.
	 * 
	 * @param forgotPasswordRequest
	 * @return LoginForm
	 * @throws Exception
	 */
	@PostMapping(FilePathConstants.KIOSK_FORGOT_PASSWORD)
	@ResponseBody
	public ResponseEntity<JSONResponse> forgotPassword(@RequestBody LoginForm forgotPasswordRequest) throws CommonException {
		JSONResponse response = new JSONResponse();
		try {

			if(forgotPasswordRequest.getUserLoginId() != null){
				
				int count = userLoginService.forgotPassword(forgotPasswordRequest.getUserLoginId());
				
			}
			
			userLoginService.forgotPassword(forgotPasswordRequest);

			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("successMessage"));
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
			response.setResponseMessage(getMessage("errorMessage"));
			response.setSuccesObject(CommonConstant.NULL);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}