package com.kevamdg.sr.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kevamdg.sr.config.CommonException;
import com.kevamdg.sr.config.LoginAuthException;
import com.kevamdg.sr.constants.CommonConstant;
import com.kevamdg.sr.constants.FilePathConstants;
import com.kevamdg.sr.service.ProfileEditService;
import com.kevamdg.sr.util.ValidationUtil;
import com.kevamdg.sr.vo.AuthDetailsVO;
import com.kevamdg.sr.vo.JSONResponse;
import com.kevamdg.sr.vo.ProfileEditVo;
import com.kevamdg.sr.vo.UserVO;

@RestController
public class ProfileEditController extends CommonAction<ProfileEditVo> {

	Logger logger = LoggerFactory.getLogger(ProfileEditController.class);

	@Autowired
	private ProfileEditService profileEditService;

	/**
	 * Method is used to Load the Phone Booking Details
	 * 
	 *
	 * @return response Response
	 */
	@PostMapping(FilePathConstants.PROFILE_EDIT_VIEW)
	@ResponseBody
	public ResponseEntity<JSONResponse> load(@RequestBody UserVO user) {

		JSONResponse jsonResponse = new JSONResponse();
		
		try {
			
			ProfileEditVo profileEdit = profileEditService.load(user);

			jsonResponse.setSuccesObject(profileEdit);
			jsonResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
			jsonResponse.setResponseMessage(getMessage("successMessage"));
		} catch (CommonException e) {
			e.printStackTrace();
			logger.error("error", e);
			jsonResponse.setResponseCode(CommonConstant.FAILURE_CODE);
			jsonResponse.setResponseMessage(e.getMessage());
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		} catch (LoginAuthException e) {
			e.printStackTrace();
			logger.error("error", e);
			jsonResponse.setResponseCode(CommonConstant.FAILURE_CODE);
			jsonResponse.setResponseMessage(e.getMessage());
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error", e);
			jsonResponse.setResponseCode(CommonConstant.ERROR_CODE);
			jsonResponse.setResponseMessage(getMessage("errorMessage"));
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		}
		return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
	}

	/**
	 * Method is used for updating the attachment
	 * 
	 * @param uploadingFiles
	 * @param str
	 * @return
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	@RequestMapping(value = FilePathConstants.PROFILE_EDIT_UPDATE, method = RequestMethod.POST, consumes = {
			"multipart/form-data" })
	@ResponseBody
	public ResponseEntity<JSONResponse> updateAttachment(HttpServletRequest request,
			@RequestParam("file") MultipartFile[] uploadingFiles, @RequestParam("action") String str)
					throws JsonParseException, JsonMappingException, IOException {
		ProfileEditVo profileEditVo = new ProfileEditVo();
		ObjectMapper mapper = new ObjectMapper();
		JSONResponse response = new JSONResponse();
		String accessToken = getHeaderAccessToken(request);
		AuthDetailsVO authDetailsVo = null;
		try {
			authDetailsVo = tokenValidate(accessToken);
			if (null == authDetailsVo) {
				// get from message.properties
				throw new LoginAuthException("Token Expired / Already Logined");
			}
			try {
				profileEditVo = mapper.readValue(str, ProfileEditVo.class);

				this.updateValidation(profileEditVo, uploadingFiles);

				profileEditService.updateAttachment(profileEditVo, uploadingFiles, authDetailsVo);
				response.setResponseCode(CommonConstant.SUCCESS_CODE);
				response.setResponseMessage(getMessage("updateSuccessMessage"));
				response.setSuccesObject(CommonConstant.NULL);
			} catch (JsonParseException e) {
				e.printStackTrace();
				logger.error(e.getMessage());
				response.setResponseCode(CommonConstant.FAILURE_CODE);
				response.setResponseMessage(e.getMessage());
				response.setSuccesObject(CommonConstant.NULL);
			} catch (JsonMappingException e) {
				e.printStackTrace();
				logger.error(e.getMessage());
				response.setResponseCode(CommonConstant.FAILURE_CODE);
				response.setResponseMessage(e.getMessage());
				response.setSuccesObject(CommonConstant.NULL);
			} catch (IOException e) {
				e.printStackTrace();
				logger.error(e.getMessage());
				response.setResponseCode(CommonConstant.FAILURE_CODE);
				response.setResponseMessage(e.getMessage());
				response.setSuccesObject(CommonConstant.NULL);
			}
		} catch (CommonException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			response.setResponseCode(CommonConstant.FAILURE_CODE);
			response.setResponseMessage(e.getMessage());
			response.setSuccesObject(CommonConstant.NULL);
		} catch (LoginAuthException e) {
			e.printStackTrace();
			logger.error("error", e);
			response.setResponseCode(CommonConstant.FAILURE_CODE);
			response.setResponseMessage(e.getMessage());
			response.setSuccesObject(CommonConstant.NULL);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			response.setResponseCode(CommonConstant.ERROR_CODE);
			response.setResponseMessage(getMessage("updateErrorMessage"));
			response.setSuccesObject(CommonConstant.NULL);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	private void updateValidation(ProfileEditVo profileEditVo, MultipartFile[] uploadingFiles) {

		// First Name Validation
		if (ValidationUtil.isNullOrBlank(profileEditVo.getFirstName().trim())) {
			throw new CommonException(getMessage("user.firstName.required"));
		}
		if (ValidationUtil.onlyAlphabets(profileEditVo.getFirstName())) {
			throw new CommonException(getMessage("profileEdit.firstName.alphabets"));
		}
		if ((profileEditVo.getFirstName().length() > 255)) {
			throw new CommonException(getMessage("user.firstName.limit"));
		}

		// Middle Name Validation
		/*
		 * if(null != profileEditVo.getMiddleName() ||
		 * profileEditVo.getMiddleName().isEmpty()) { if
		 * (ValidationUtil.onlyAlphabets(profileEditVo.getMiddleName())) { throw
		 * new CommonException(getMessage("profileEdit.middleName.alphabets"));
		 * } if ((profileEditVo.getMiddleName().length() > 255)) { throw new
		 * CommonException(getMessage("user.middleName.limit")); }
		 * 
		 * 
		 * }
		 */

		// Last Name Validation
		if (ValidationUtil.isNullOrBlank(profileEditVo.getLastName().trim())) {
			throw new CommonException(getMessage("user.lasttName.required"));
		}
		if (ValidationUtil.onlyAlphabets(profileEditVo.getLastName())) {
			throw new CommonException(getMessage("profileEdit.lastName.alphabets"));
		}
		if ((profileEditVo.getLastName().length() > 255)) {
			throw new CommonException(getMessage("user.lasttName.limit"));
		}

		// Email Validation
		if (ValidationUtil.isNullOrBlank(profileEditVo.getEmailId().trim())) {
			throw new CommonException(getMessage("user.email.required"));
		}
		if (ValidationUtil.isEmail(profileEditVo.getEmailId())) {
			throw new CommonException(getMessage("user.email.validation"));
		}
		if ((profileEditVo.getEmailId().length() > 255)) {
			throw new CommonException(getMessage("user.email.limit"));
		}

		// Mobile Validation
		/*
		 * if (ValidationUtil.isNullOrBlank(profileEditVo.getMobile().trim())) {
		 * throw new CommonException(getMessage("user.mobile.required")); } if
		 * (ValidationUtil.onlyDigits(profileEditVo.getMobile())) { throw new
		 * CommonException(getMessage("profileEdit.mobile.number")); } if
		 * (ValidationUtil.isMobileNumber(profileEditVo.getMobile())) { throw
		 * new CommonException(getMessage("profileEdit.mobile.number.val")); }
		 * 
		 * // Current Address Validation if
		 * (ValidationUtil.isNullOrBlank(profileEditVo.getCurrentAddress().trim(
		 * ))) { throw new
		 * CommonException(getMessage("user.currentAddress.required")); }
		 * 
		 * // Permanent Address Validation if
		 * (ValidationUtil.isNullOrBlank(profileEditVo.getPermanentAddress().
		 * trim())) { throw new
		 * CommonException(getMessage("user.permanentAddress.required")); }
		 * 
		 * // Skype Id Validation if
		 * (ValidationUtil.isNullOrBlank(profileEditVo.getSkypeId().trim())) {
		 * throw new
		 * CommonException(getMessage("profileEdit.skypeId.required")); } if
		 * (ValidationUtil.isAlphanumeric(profileEditVo.getSkypeId())) { throw
		 * new CommonException(getMessage("profileEdit.skypeId.alphanumeric"));
		 * } if (profileEditVo.getSkypeId().length() > 50) { throw new
		 * CommonException(getMessage("profileEdit.skypeId.length")); }
		 */

		// Image Validation
		if (ValidationUtil.isImage(uploadingFiles)) {
			throw new CommonException(getMessage("image_val"));

		}

	}
	
	
	

}
