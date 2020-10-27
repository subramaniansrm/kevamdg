package com.kevamdg.sr.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;
import org.omg.CORBA.portable.ApplicationException;
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
import com.kevamdg.sr.service.HelpService;
import com.kevamdg.sr.vo.HelpVo;

/**
 * This Action class is used to get help details from help table
 * 
 * @author Priya [SRM]
 */
@RestController
public class HelpController extends CommonAction<T> {

	private static final Logger logger = LogManager.getLogger(HelpController.class);

	@Autowired
	private HelpService helpService;

	/**
	 * This method is to retrive help help details from help table
	 * 
	 * @param helpRequest
	 * @return HelpForm
	 */
	@PostMapping(FilePathConstants.GET_HELP)
	@ResponseBody
	public ResponseEntity<JSONResponse> getHelpVoList(@RequestBody HelpVo helpRequest) {
		JSONResponse response = new JSONResponse();
		try {
			List<HelpVo> helpVo = helpService.getHelpVoList(helpRequest);

			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("successMessage"));
			response.setSuccesObject(helpVo);

		} catch (CommonException e) {
			response.setResponseCode(CommonConstant.FAILURE_CODE);
			response.setResponseMessage(e.getMessage());
			response.setSuccesObject(CommonConstant.NULL);
		} catch (LoginAuthException e) {
			response.setResponseCode(CommonConstant.FAILURE_CODE);
			response.setResponseMessage(e.getMessage());
			response.setSuccesObject(CommonConstant.NULL);
		}catch (Exception e) {
			logger.error(e.getMessage());
			response.setResponseCode(CommonConstant.ERROR_CODE);
			response.setResponseMessage(getMessage("errorMessage"));
			response.setSuccesObject(CommonConstant.NULL);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
}
