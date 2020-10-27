package com.kevamdg.sr.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kevamdg.sr.config.CommonException;
import com.kevamdg.sr.constants.CommonConstant;
import com.kevamdg.sr.constants.FilePathConstants;
import com.kevamdg.sr.service.LogOutService;
import com.kevamdg.sr.vo.JSONResponse;
import com.kevamdg.sr.vo.UserVO;

@RestController
public class LogOutController extends CommonAction<UserVO>{
	
	@Autowired
	LogOutService logOutService;
	
	private static final Logger logger = LogManager.getLogger(LogOutController.class);
	
	
	@PostMapping(FilePathConstants.LOGOUT)
	public ResponseEntity<JSONResponse> logOut(@RequestBody UserVO userMasterVO) throws CommonException {

		JSONResponse jsonResponse = new JSONResponse();
		try {

			
			logOutService.logout(userMasterVO);
			
			jsonResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
			jsonResponse.setResponseMessage(getMessage("logout"));
			jsonResponse.setSuccesObject(CommonConstant.NULL);
			return new ResponseEntity<>(jsonResponse, HttpStatus.OK);

		} catch (Exception e) {
			jsonResponse.setResponseCode(CommonConstant.ERROR_CODE);
			jsonResponse.setResponseMessage(getMessage("errorMessage"));
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		}

		return new ResponseEntity<>(jsonResponse, HttpStatus.OK);

	}


	
	

}
