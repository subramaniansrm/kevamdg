package com.kevamdg.sr.controller;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kevamdg.sr.config.CommonException;
import com.kevamdg.sr.config.LoginAuthException;
import com.kevamdg.sr.constants.CommonConstant;
import com.kevamdg.sr.constants.FilePathConstants;
import com.kevamdg.sr.response.JSONResponse;
import com.kevamdg.sr.service.FaqService;
import com.kevamdg.sr.vo.FaqVo;

/**
 * Controller is used for FAQ load
 * 
 * @author manoj
 *
 */
@RestController
public class FaqController extends CommonAction<T>{ 

	@Autowired
	private FaqService faqService;


	/**
	 * This method is to retrieve frequently asked question details from faq
	 * table
	 * 
	 * @return FaqForm
	 */
	@PostMapping(FilePathConstants.FAQ_LIST)
	@ResponseBody
	public ResponseEntity<JSONResponse> getCommonFaq() {

		JSONResponse response = new JSONResponse();
		try {
			FaqVo faqVo = new FaqVo();
			List<FaqVo> faqVoList = faqService.getCommonFaq();
			faqVo.setFaqVoList(faqVoList);

			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("successMessage"));
			response.setSuccesObject(faqVo);

		} catch (CommonException e) {
			response.setResponseCode(CommonConstant.FAILURE_CODE);
			response.setResponseMessage(e.getMessage());
			response.setSuccesObject(CommonConstant.NULL);
		} catch (LoginAuthException e) {
			response.setResponseCode(CommonConstant.FAILURE_CODE);
			response.setResponseMessage(e.getMessage());
			response.setSuccesObject(CommonConstant.NULL);
		} catch (Exception e) {
			response.setResponseCode(CommonConstant.ERROR_CODE);
			response.setResponseMessage(getMessage("errorMessage"));
			response.setSuccesObject(CommonConstant.NULL);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
}
