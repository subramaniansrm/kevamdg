package com.kevamdg.sr.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.log.Logger;
import com.itextpdf.text.log.LoggerFactory;
import com.kevamdg.sr.config.CommonException;
import com.kevamdg.sr.config.LoginAuthException;
import com.kevamdg.sr.constants.CommonConstant;
import com.kevamdg.sr.constants.FilePathConstants;
import com.kevamdg.sr.service.DropDownService;
import com.kevamdg.sr.vo.AuthDetailsVO;
import com.kevamdg.sr.vo.CommonDropDownVO;
import com.kevamdg.sr.vo.CommonScreenFieldVO;
import com.kevamdg.sr.vo.CommonVO;
import com.kevamdg.sr.vo.DepartmentVO;
import com.kevamdg.sr.vo.JSONResponse;
import com.kevamdg.sr.vo.ModuleVO;
import com.kevamdg.sr.vo.ScreenMMVO;
import com.kevamdg.sr.vo.SubLocationVO;
import com.kevamdg.sr.vo.UserMappingModuleVO;
import com.kevamdg.sr.vo.UserRoleTypeVO;
import com.kevamdg.sr.vo.UserRoleVO;
import com.kevamdg.sr.vo.UserVO;

/**
 * COmmon Drop Down Controller
 * 
 * @author raathikaabm
 *
 */

@RestController
@Scope(value = "request")
public class DropdownController extends CommonAction<CommonDropDownVO> {

	Logger logger = LoggerFactory.getLogger(DropdownController.class);

	@Autowired
	DropDownService dropdownService;

	/**
	 * LOCATION DROPDOWN
	 * 
	 * @return
	 * @throws IOException
	 */

	@PostMapping(FilePathConstants.LOCATION_LOAD)
	@ResponseBody
	public ResponseEntity<JSONResponse> loadLocation() throws IOException {

		JSONResponse jsonResponse = new JSONResponse();
		try {
			List<CommonDropDownVO> locationList = dropdownService.getAllLocation();
			jsonResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
			jsonResponse.setSuccesObject(locationList);
			jsonResponse.setResponseMessage(getMessage("successMessage"));
			return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
		} catch (CommonException e) {
			logger.error("error", e);
			jsonResponse.setResponseCode(CommonConstant.FAILURE_CODE);
			jsonResponse.setResponseMessage(e.getMessage());
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		} catch (Exception e) {
			logger.error("error", e);
			jsonResponse.setResponseCode(CommonConstant.ERROR_CODE);
			jsonResponse.setResponseMessage(getMessage("errorMessage"));
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		}
		return new ResponseEntity<>(jsonResponse, HttpStatus.OK);

	}

	/**
	 * DEPARTMENT LOAD BASED ON LOCATION AND SUBLOCATION
	 * 
	 * @param departmentVo
	 * @return
	 * @throws IOException
	 */

	@PostMapping(FilePathConstants.DEPARTMENT_LOAD)
	@ResponseBody
	public ResponseEntity<JSONResponse> loadDepartment(@RequestBody DepartmentVO departmentVo) throws IOException {

		JSONResponse jsonResponse = new JSONResponse();
		try {
			
			List<CommonDropDownVO> departmentList = dropdownService.getAllDepartment(departmentVo);
			jsonResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
			jsonResponse.setSuccesObject(departmentList);
			jsonResponse.setResponseMessage(getMessage("successMessage"));
			return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
		} catch (CommonException e) {
			logger.error("error", e);
			jsonResponse.setResponseCode(CommonConstant.FAILURE_CODE);
			jsonResponse.setResponseMessage(e.getMessage());
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		} catch (Exception e) {
			logger.error("error", e);
			jsonResponse.setResponseCode(CommonConstant.ERROR_CODE);
			jsonResponse.setResponseMessage(getMessage("errorMessage"));
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		}
		return new ResponseEntity<>(jsonResponse, HttpStatus.OK);

	}

	/**
	 * USER ROLE DROP DOWN BASED ON LOCATION, SUBLOCATION AND DEPARTMENT
	 * 
	 * @param userRoleVo
	 * @return
	 * @throws IOException
	 */

	@PostMapping(FilePathConstants.ROLE_LOAD)
	@ResponseBody
	public ResponseEntity<JSONResponse> loadRole(@RequestBody UserRoleVO userRoleVo) throws IOException {

		JSONResponse jsonResponse = new JSONResponse();
		try {
			List<CommonDropDownVO> roleList = dropdownService.getAllRole(userRoleVo);
			jsonResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
			jsonResponse.setSuccesObject(roleList);
			jsonResponse.setResponseMessage(getMessage("successMessage"));
			return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
		} catch (CommonException e) {
			logger.error("error", e);
			jsonResponse.setResponseCode(CommonConstant.FAILURE_CODE);
			jsonResponse.setResponseMessage(e.getMessage());
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		} catch (Exception e) {
			logger.error("error", e);
			jsonResponse.setResponseCode(CommonConstant.ERROR_CODE);
			jsonResponse.setResponseMessage(getMessage("errorMessage"));
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		}
		return new ResponseEntity<>(jsonResponse, HttpStatus.OK);

	}

	/**
	 * COUNTRY DROP DOWN
	 * 
	 * @return
	 * @throws IOException
	 */

	@PostMapping(FilePathConstants.COUNTRY_LOAD)
	@ResponseBody
	public ResponseEntity<JSONResponse> loadCountry() throws IOException {

		JSONResponse jsonResponse = new JSONResponse();
		try {
			List<CommonDropDownVO> countryMasterVoList = dropdownService.getAllCountry();

			jsonResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
			jsonResponse.setSuccesObject(countryMasterVoList);
			jsonResponse.setResponseMessage(getMessage("successMessage"));
			return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
		} catch (CommonException e) {
			logger.error("error", e);
			jsonResponse.setResponseCode(CommonConstant.FAILURE_CODE);
			jsonResponse.setResponseMessage(e.getMessage());
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		} catch (Exception e) {
			logger.error("error", e);
			jsonResponse.setResponseCode(CommonConstant.ERROR_CODE);
			jsonResponse.setResponseMessage(getMessage("errorMessage"));
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		}
		return new ResponseEntity<>(jsonResponse, HttpStatus.OK);

	}

	/**
	 * STATE DROPDOWN
	 * 
	 * @return
	 * @throws IOException
	 */

	@PostMapping(FilePathConstants.STATE_LOAD)
	@ResponseBody
	public ResponseEntity<JSONResponse> loadState() throws IOException {

		JSONResponse jsonResponse = new JSONResponse();
		try {
			List<CommonDropDownVO> stateMasterVoList = dropdownService.getAllState();

			jsonResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
			jsonResponse.setSuccesObject(stateMasterVoList);
			jsonResponse.setResponseMessage(getMessage("successMessage"));
			return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
		} catch (CommonException e) {
			logger.error("error", e);
			jsonResponse.setResponseCode(CommonConstant.FAILURE_CODE);
			jsonResponse.setResponseMessage(e.getMessage());
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		} catch (Exception e) {
			logger.error("error", e);
			jsonResponse.setResponseCode(CommonConstant.ERROR_CODE);
			jsonResponse.setResponseMessage(getMessage("errorMessage"));
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		}
		return new ResponseEntity<>(jsonResponse, HttpStatus.OK);

	}

	/**
	 * CITY DROPDOWN
	 * 
	 * @return
	 * @throws IOException
	 */
	@PostMapping(FilePathConstants.CITY_LOAD)
	@ResponseBody
	public ResponseEntity<JSONResponse> loadCity() throws IOException {

		JSONResponse jsonResponse = new JSONResponse();
		try {
			List<CommonDropDownVO> cityMasterVoList = dropdownService.getAllCity();

			jsonResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
			jsonResponse.setSuccesObject(cityMasterVoList);
			jsonResponse.setResponseMessage(getMessage("successMessage"));
			return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
		} catch (CommonException e) {
			logger.error("error", e);
			jsonResponse.setResponseCode(CommonConstant.FAILURE_CODE);
			jsonResponse.setResponseMessage(e.getMessage());
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		} catch (Exception e) {
			logger.error("error", e);
			jsonResponse.setResponseCode(CommonConstant.ERROR_CODE);
			jsonResponse.setResponseMessage(getMessage("errorMessage"));
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		}
		return new ResponseEntity<>(jsonResponse, HttpStatus.OK);

	}

	/**
	 * SUBLOCATION DROPDOWN BASED ON LOCATION
	 * 
	 * @param subLocationVo
	 * @return
	 * @throws IOException
	 */
	@PostMapping(FilePathConstants.SUBLOCATION_LOAD)
	@ResponseBody
	public ResponseEntity<JSONResponse> getAllSublocation(@RequestBody SubLocationVO subLocationVo) throws IOException {

		JSONResponse jsonResponse = new JSONResponse();
		try {
			List<CommonDropDownVO> subLocationVolist = dropdownService.getAllsublocationList(subLocationVo.getId());

			jsonResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
			jsonResponse.setSuccesObject(subLocationVolist);
			jsonResponse.setResponseMessage(getMessage("successMessage"));
			return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
		} catch (CommonException e) {
			logger.error("error", e);
			jsonResponse.setResponseCode(CommonConstant.FAILURE_CODE);
			jsonResponse.setResponseMessage(e.getMessage());
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		} catch (Exception e) {
			logger.error("error", e);
			jsonResponse.setResponseCode(CommonConstant.ERROR_CODE);
			jsonResponse.setResponseMessage(getMessage("errorMessage"));
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		}
		return new ResponseEntity<>(jsonResponse, HttpStatus.OK);

	}

	/**
	 * USER DROPDOWN
	 * 
	 * @param userMasterVo
	 * @return
	 * @throws IOException
	 */
	@PostMapping(FilePathConstants.USER_DROPDOWN)
	@ResponseBody
	public ResponseEntity<JSONResponse> getAllUser(@RequestBody UserVO userMasterVo) throws IOException {

		JSONResponse jsonResponse = new JSONResponse();
		try {
			List<CommonDropDownVO> userMasterVoList = dropdownService.getAllUser(userMasterVo);

			jsonResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
			jsonResponse.setSuccesObject(userMasterVoList);
			jsonResponse.setResponseMessage(getMessage("successMessage"));
			return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
		} catch (CommonException e) {
			logger.error("error", e);
			jsonResponse.setResponseCode(CommonConstant.FAILURE_CODE);
			jsonResponse.setResponseMessage(e.getMessage());
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		} catch (Exception e) {
			logger.error("error", e);
			jsonResponse.setResponseCode(CommonConstant.ERROR_CODE);
			jsonResponse.setResponseMessage(getMessage("errorMessage"));
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		}
		return new ResponseEntity<>(jsonResponse, HttpStatus.OK);

	}

	@PostMapping(FilePathConstants.USER_ROLE_ROLETYPE)
	@ResponseBody
	public ResponseEntity<JSONResponse> getRoleType(@RequestBody UserRoleTypeVO userRoleTypeVo) throws IOException {

		JSONResponse jsonResponse = new JSONResponse();
		try {
			List<UserRoleTypeVO> userRoleTypeVoList = dropdownService.getRoleType(userRoleTypeVo);

			jsonResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
			jsonResponse.setSuccesObject(userRoleTypeVoList);
			jsonResponse.setResponseMessage(getMessage("successMessage"));
			return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
		} catch (CommonException e) {
			logger.error("error", e);
			jsonResponse.setResponseCode(CommonConstant.FAILURE_CODE);
			jsonResponse.setResponseMessage(e.getMessage());
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		} catch (Exception e) {
			logger.error("error", e);
			jsonResponse.setResponseCode(CommonConstant.ERROR_CODE);
			jsonResponse.setResponseMessage(getMessage("errorMessage"));
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		}
		return new ResponseEntity<>(jsonResponse, HttpStatus.OK);

	}
	
	
	@PostMapping(FilePathConstants.MODULE)
	@ResponseBody
	public ResponseEntity<JSONResponse> getModule() throws IOException {

		JSONResponse jsonResponse = new JSONResponse();
		try {
			List<ModuleVO> moduleList = dropdownService.getModule();

			jsonResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
			jsonResponse.setSuccesObject(moduleList);
			jsonResponse.setResponseMessage(getMessage("successMessage"));
			return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
		} catch (CommonException e) {
			logger.error("error", e);
			jsonResponse.setResponseCode(CommonConstant.FAILURE_CODE);
			jsonResponse.setResponseMessage(e.getMessage());
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		} catch (Exception e) {
			logger.error("error", e);
			jsonResponse.setResponseCode(CommonConstant.ERROR_CODE);
			jsonResponse.setResponseMessage(getMessage("errorMessage"));
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		}
		return new ResponseEntity<>(jsonResponse, HttpStatus.OK);

	}
	
	
	@PostMapping(FilePathConstants.USERMAPPING_MODULE)
	@ResponseBody
	public ResponseEntity<JSONResponse> getModuleRights(@RequestBody UserMappingModuleVO userMappingModuleVO) throws IOException {

		JSONResponse jsonResponse = new JSONResponse();
		try {
			List<UserMappingModuleVO> moduleList = dropdownService.getModuleRights(userMappingModuleVO.getUserId());

			jsonResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
			jsonResponse.setSuccesObject(moduleList);
			jsonResponse.setResponseMessage(getMessage("successMessage"));
			return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
		} catch (CommonException e) {
			logger.error("error", e);
			jsonResponse.setResponseCode(CommonConstant.FAILURE_CODE);
			jsonResponse.setResponseMessage(e.getMessage());
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		} catch (Exception e) {
			logger.error("error", e);
			jsonResponse.setResponseCode(CommonConstant.ERROR_CODE);
			jsonResponse.setResponseMessage(getMessage("errorMessage"));
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		}
		return new ResponseEntity<>(jsonResponse, HttpStatus.OK);

	}
	
	
	
	@PostMapping(FilePathConstants.MDG_MODULE)
	@ResponseBody
	public ResponseEntity<JSONResponse> mdgModuleRights(@RequestBody UserMappingModuleVO userMappingModuleVO) throws IOException {

		JSONResponse jsonResponse = new JSONResponse();
		try {
			List<UserMappingModuleVO> moduleList = dropdownService.mdgModuleRights(userMappingModuleVO.getUserId());

			jsonResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
			jsonResponse.setSuccesObject(moduleList);
			jsonResponse.setResponseMessage(getMessage("successMessage"));
			return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
		} catch (CommonException e) {
			logger.error("error", e);
			jsonResponse.setResponseCode(CommonConstant.FAILURE_CODE);
			jsonResponse.setResponseMessage(e.getMessage());
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		} catch (Exception e) {
			logger.error("error", e);
			jsonResponse.setResponseCode(CommonConstant.ERROR_CODE);
			jsonResponse.setResponseMessage(getMessage("errorMessage"));
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		}
		return new ResponseEntity<>(jsonResponse, HttpStatus.OK);

	}
	
	@PostMapping(FilePathConstants.NAME_DROPDOWN)
	@ResponseBody
	public ResponseEntity<JSONResponse> name(HttpServletRequest request,@RequestBody CommonDropDownVO commonDropDownVO) throws CommonException {

		JSONResponse jsonResponse = new JSONResponse();
		String accessToken = getHeaderAccessToken(request);
		AuthDetailsVO authDetailsVo = null;
		try {
			authDetailsVo = tokenValidate(accessToken);
			if (null == authDetailsVo) {
				// get from message.properties
				throw new LoginAuthException("Token Expired / Already Logined");
			}
			CommonVO commonVO = new CommonVO();
			CommonVO com = new CommonVO();
			CommonDropDownVO common = dropdownService.name(authDetailsVo.getUserId());
			List<ScreenMMVO> screenList = dropdownService.screenList(authDetailsVo.getUserId());
			List<ScreenMMVO> list = dropdownService.cmScreenList(authDetailsVo.getUserId());
			commonVO.setScreenMMVoList(screenList);
			com.setScreenCMVoList(list);
			jsonResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
			jsonResponse.setSuccesObject(common);
			jsonResponse.setCmSuccesObject(com);
			jsonResponse.setAuthSuccesObject(commonVO);
			jsonResponse.setResponseMessage(getMessage("successMessage"));
			return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
		} catch (CommonException e) {
			logger.error("error", e);
			jsonResponse.setResponseCode(CommonConstant.FAILURE_CODE);
			jsonResponse.setResponseMessage(e.getMessage());
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		} catch (Exception e) {
			logger.error("error", e);
			jsonResponse.setResponseCode(CommonConstant.ERROR_CODE);
			jsonResponse.setResponseMessage(getMessage("errorMessage"));
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		}
		return new ResponseEntity<>(jsonResponse, HttpStatus.OK);

	}
	
	
	@PostMapping(FilePathConstants.SCREEN_FIELD_DROPDOWN)
	@ResponseBody
	public ResponseEntity<JSONResponse> screenField() throws CommonException {

		JSONResponse jsonResponse = new JSONResponse();
		try {
			
			List<CommonScreenFieldVO>common = dropdownService.screenField();
			jsonResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
			jsonResponse.setSuccesObject(common);
			jsonResponse.setResponseMessage(getMessage("successMessage"));
			return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
		} catch (CommonException e) {
			logger.error("error", e);
			jsonResponse.setResponseCode(CommonConstant.FAILURE_CODE);
			jsonResponse.setResponseMessage(e.getMessage());
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		} catch (Exception e) {
			logger.error("error", e);
			jsonResponse.setResponseCode(CommonConstant.ERROR_CODE);
			jsonResponse.setResponseMessage(getMessage("errorMessage"));
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		}
		return new ResponseEntity<>(jsonResponse, HttpStatus.OK);

	}
	
}
