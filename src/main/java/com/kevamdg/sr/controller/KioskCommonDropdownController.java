package com.kevamdg.sr.controller;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kevamdg.sr.config.CommonException;
import com.kevamdg.sr.config.LoginAuthException;
import com.kevamdg.sr.constants.CommonConstant;
import com.kevamdg.sr.constants.FilePathConstants;
import com.kevamdg.sr.response.JSONResponse;
import com.kevamdg.sr.service.KioskCommonDropdownService;
import com.kevamdg.sr.vo.CityVo;
import com.kevamdg.sr.vo.CommonStorageVo;
import com.kevamdg.sr.vo.CommonVO;
import com.kevamdg.sr.vo.KioskCountryVo;
import com.kevamdg.sr.vo.DivisionMasterVo;
import com.kevamdg.sr.vo.DropdownDepartmentVo;
import com.kevamdg.sr.vo.DropdownLocationVo;
import com.kevamdg.sr.vo.KioskDepartmentVo;
import com.kevamdg.sr.vo.ScreenDropdownVo;
import com.kevamdg.sr.vo.StateVO;
import com.kevamdg.sr.vo.SubLocationVO;
import com.kevamdg.sr.vo.UserLocationVo;
import com.kevamdg.sr.vo.UserMasterVo;
import com.kevamdg.sr.vo.UserRoleTypeVO;
import com.kevamdg.sr.vo.UserRoleVO;

@RestController
@Scope(value = "request")
public class KioskCommonDropdownController extends CommonAction<T> {

	@Autowired
	KioskCommonDropdownService kioskCommonDropdownService;

	/**
	 * method is used to Load the Location
	 * 
	 * @return response
	 */
	@PostMapping(FilePathConstants.KIOSK_LOCATION_LOAD)
	@ResponseBody
	public ResponseEntity<JSONResponse> loadLocation() {
		JSONResponse response = new JSONResponse();
		try {

			List<UserLocationVo> locationList = kioskCommonDropdownService.getAllLocation();

			response.setSuccesObject(locationList);
			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("successMessage"));

		} catch (CommonException e) {
			response.setResponseCode(CommonConstant.FAILURE_CODE);
			response.setResponseMessage(e.getMessage());
			response.setSuccesObject(CommonConstant.NULL);
		} catch (LoginAuthException e) {
			response.setResponseCode(CommonConstant.FAILURE_CODE);
			response.setResponseMessage(e.getMessage());
			response.setSuccesObject(CommonConstant.NULL);
		}  catch (Exception e) {
			response.setResponseCode(CommonConstant.ERROR_CODE);
			response.setResponseMessage(getMessage("errorMessage"));
			response.setSuccesObject(CommonConstant.NULL);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * method is used to Load the department
	 * 
	 * @return response
	 */
	@PostMapping(FilePathConstants.KIOSK_DEPARTMENT_LOAD)
	@ResponseBody
	public ResponseEntity<JSONResponse> loadDepartment(@RequestBody KioskDepartmentVo departmentVo) {
		JSONResponse response = new JSONResponse();
		try {

			List<KioskDepartmentVo> departmentList = kioskCommonDropdownService.getAllDepartment(departmentVo);

			response.setSuccesObject(departmentList);
			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("successMessage"));

		} catch (CommonException e) {
			response.setResponseCode(CommonConstant.FAILURE_CODE);
			response.setResponseMessage(e.getMessage());
			response.setSuccesObject(CommonConstant.NULL);
		} catch (LoginAuthException e) {
			response.setResponseCode(CommonConstant.FAILURE_CODE);
			response.setResponseMessage(e.getMessage());
			response.setSuccesObject(CommonConstant.NULL);
		}  catch (Exception e) {
			response.setResponseCode(CommonConstant.ERROR_CODE);
			response.setResponseMessage(getMessage("errorMessage"));
			response.setSuccesObject(CommonConstant.NULL);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * method is used to Load the Role
	 * 
	 * @return response
	 */
	@PostMapping(FilePathConstants.KIOSK_ROLE_LOAD)
	@ResponseBody
	public ResponseEntity<JSONResponse> loadRole(@RequestBody UserRoleVO userRoleVo) {
		JSONResponse response = new JSONResponse();
		try {

			List<UserRoleVO> roleList = kioskCommonDropdownService.getAllRole(userRoleVo);

			response.setSuccesObject(roleList);
			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("successMessage"));

		}catch (CommonException e) {
			response.setResponseCode(CommonConstant.FAILURE_CODE);
			response.setResponseMessage(e.getMessage());
			response.setSuccesObject(CommonConstant.NULL);
		} catch (LoginAuthException e) {
			response.setResponseCode(CommonConstant.FAILURE_CODE);
			response.setResponseMessage(e.getMessage());
			response.setSuccesObject(CommonConstant.NULL);
		}  catch (Exception e) {
			response.setResponseCode(CommonConstant.ERROR_CODE);
			response.setResponseMessage(getMessage("errorMessage"));
			response.setSuccesObject(CommonConstant.NULL);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * method is used to Load the Division
	 * 
	 * @return response
	 */
	@PostMapping(FilePathConstants.KIOSK_DIVISIONS_LOAD)
	@ResponseBody
	public ResponseEntity<JSONResponse> loadDivision() {
		JSONResponse response = new JSONResponse();
		try {

			List<DivisionMasterVo> divisionList = kioskCommonDropdownService.getAllDivision();

			response.setSuccesObject(divisionList);
			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("successMessage"));

		} catch (CommonException e) {
			response.setResponseCode(CommonConstant.FAILURE_CODE);
			response.setResponseMessage(e.getMessage());
			response.setSuccesObject(CommonConstant.NULL);
		} catch (LoginAuthException e) {
			response.setResponseCode(CommonConstant.FAILURE_CODE);
			response.setResponseMessage(e.getMessage());
			response.setSuccesObject(CommonConstant.NULL);
		}  catch (Exception e) {
			response.setResponseCode(CommonConstant.ERROR_CODE);
			response.setResponseMessage(getMessage("errorMessage"));
			response.setSuccesObject(CommonConstant.NULL);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	
	
	

	/**
	 * method is used to load userDepartment
	 * 
	 * @return UserRoleMaster
	 */
	@GetMapping(FilePathConstants.KIOSK_USER_ROLE_LOAD_USER_DEPARTMENT)
	@ResponseBody
	public ResponseEntity<JSONResponse> loadUserDepartmentDetails(@RequestBody KioskDepartmentVo departmentVo ) {
		JSONResponse response = new JSONResponse();
		try {

			List<DropdownDepartmentVo> dropdownDepartmentVoList = kioskCommonDropdownService.getLoadUserDepartmentDetails();

			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("successMessage"));
			response.setSuccesObject(dropdownDepartmentVoList);

		} catch (CommonException e) {
			response.setResponseCode(CommonConstant.FAILURE_CODE);
			response.setResponseMessage(e.getMessage());
			response.setSuccesObject(CommonConstant.NULL);
		} catch (LoginAuthException e) {
			response.setResponseCode(CommonConstant.FAILURE_CODE);
			response.setResponseMessage(e.getMessage());
			response.setSuccesObject(CommonConstant.NULL);
		}  catch (Exception e) {
			response.setResponseCode(CommonConstant.ERROR_CODE);
			response.setResponseMessage(getMessage("errorMessage"));
			response.setSuccesObject(CommonConstant.NULL);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	/**
	 * method is used to load userLocation
	 * 
	 * @return UserRoleMaster
	 */
	@GetMapping(FilePathConstants.KIOSK_USER_ROLE_LOAD_USER_LOCATION)
	@ResponseBody
	public ResponseEntity<JSONResponse> loadUserLocationDetails() {
		JSONResponse response = new JSONResponse();
		try {

			List<DropdownLocationVo> dropdownLocationVoList = kioskCommonDropdownService.getLoadUserLocationDetails();

			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("successMessage"));
			response.setSuccesObject(dropdownLocationVoList);

		}catch (CommonException e) {
			response.setResponseCode(CommonConstant.FAILURE_CODE);
			response.setResponseMessage(e.getMessage());
			response.setSuccesObject(CommonConstant.NULL);
		} catch (LoginAuthException e) {
			response.setResponseCode(CommonConstant.FAILURE_CODE);
			response.setResponseMessage(e.getMessage());
			response.setSuccesObject(CommonConstant.NULL);
		}  catch (Exception e) {
			response.setResponseCode(CommonConstant.ERROR_CODE);
			response.setResponseMessage(getMessage("errorMessage"));
			response.setSuccesObject(CommonConstant.NULL);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Method is used to load department
	 * 
	 * @return
	 */
	@PostMapping(FilePathConstants.LOAD_DEPARTMENT_LOCATION)
	@ResponseBody
	public ResponseEntity<JSONResponse> listLocation() {
		JSONResponse response = new JSONResponse();
		try {

			List<UserLocationVo> locationList = kioskCommonDropdownService.getAllLocation();

			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("successMessage"));
			response.setSuccesObject(locationList);

		}catch (CommonException e) {
			response.setResponseCode(CommonConstant.FAILURE_CODE);
			response.setResponseMessage(e.getMessage());
			response.setSuccesObject(CommonConstant.NULL);
		} catch (LoginAuthException e) {
			response.setResponseCode(CommonConstant.FAILURE_CODE);
			response.setResponseMessage(e.getMessage());
			response.setSuccesObject(CommonConstant.NULL);
		}  catch (Exception e) {
			response.setResponseCode(CommonConstant.ERROR_CODE);
			response.setResponseMessage(getMessage("errorMessage"));
			response.setSuccesObject(CommonConstant.NULL);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * method is used to Load Country
	 * 
	 * @return response
	 * @throws BusinessException
	 */
	@PostMapping(FilePathConstants.KIOSK_COUNTRY_LOAD)
	@ResponseBody
	public ResponseEntity<JSONResponse> loadCountry() {
		JSONResponse response = new JSONResponse();
		try {

			List<KioskCountryVo> CountryMasterVoList = kioskCommonDropdownService.getAllCountry();

			response.setSuccesObject(CountryMasterVoList);
			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("successMessage"));
		}catch (CommonException e) {
			response.setResponseCode(CommonConstant.FAILURE_CODE);
			response.setResponseMessage(e.getMessage());
			response.setSuccesObject(CommonConstant.NULL);
		} catch (LoginAuthException e) {
			response.setResponseCode(CommonConstant.FAILURE_CODE);
			response.setResponseMessage(e.getMessage());
			response.setSuccesObject(CommonConstant.NULL);
		}  catch (Exception e) {
			response.setResponseCode(CommonConstant.ERROR_CODE);
			response.setResponseMessage(getMessage("errorMessage"));
			response.setSuccesObject(CommonConstant.NULL);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * method is used to Load State
	 * 
	 * @return response
	 * @throws BusinessException
	 */
	@PostMapping(FilePathConstants.KIOSK_STATE_LOAD)
	@ResponseBody
	public ResponseEntity<JSONResponse> loadState() {
		JSONResponse response = new JSONResponse();
		try {

			List<StateVO> stateMasterVoList = kioskCommonDropdownService.getAllState();

			response.setSuccesObject(stateMasterVoList);
			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("successMessage"));

		} catch (CommonException e) {
			response.setResponseCode(CommonConstant.FAILURE_CODE);
			response.setResponseMessage(e.getMessage());
			response.setSuccesObject(CommonConstant.NULL);
		} catch (LoginAuthException e) {
			response.setResponseCode(CommonConstant.FAILURE_CODE);
			response.setResponseMessage(e.getMessage());
			response.setSuccesObject(CommonConstant.NULL);
		}  catch (Exception e) {
			response.setResponseCode(CommonConstant.ERROR_CODE);
			response.setResponseMessage(getMessage("errorMessage"));
			response.setSuccesObject(CommonConstant.NULL);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * method is used to Load City
	 * 
	 * @return response
	 * @throws BusinessException
	 */
	@PostMapping(FilePathConstants.KIOSK_CITY_LOAD)
	@ResponseBody
	public ResponseEntity<JSONResponse> loadCity() {
		JSONResponse response = new JSONResponse();
		try {

			List<CityVo> cityMasterVoList = kioskCommonDropdownService.getAllCity();

			response.setSuccesObject(cityMasterVoList);
			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("successMessage"));

		} catch (CommonException e) {
			response.setResponseCode(CommonConstant.FAILURE_CODE);
			response.setResponseMessage(e.getMessage());
			response.setSuccesObject(CommonConstant.NULL);
		} catch (LoginAuthException e) {
			response.setResponseCode(CommonConstant.FAILURE_CODE);
			response.setResponseMessage(e.getMessage());
			response.setSuccesObject(CommonConstant.NULL);
		}  catch (Exception e) {
			response.setResponseCode(CommonConstant.ERROR_CODE);
			response.setResponseMessage(getMessage("errorMessage"));
			response.setSuccesObject(CommonConstant.NULL);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * method is used to Load the Screen
	 * 
	 * @return Response
	 * @throwResponseEntity<JSONResponse>ssException
	 */
	@PostMapping(FilePathConstants.KIOSK_SCREEN_LOAD)
	@ResponseBody
	public ResponseEntity<JSONResponse> loadScreen() {
		JSONResponse response = new JSONResponse();
		try {

			List<ScreenDropdownVo> screenDropdownVoList = kioskCommonDropdownService.getAllScreen();

			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("successMessage"));
			response.setSuccesObject(screenDropdownVoList);

		} catch (CommonException e) {
			response.setResponseCode(CommonConstant.FAILURE_CODE);
			response.setResponseMessage(e.getMessage());
			response.setSuccesObject(CommonConstant.NULL);
		} catch (LoginAuthException e) {
			response.setResponseCode(CommonConstant.FAILURE_CODE);
			response.setResponseMessage(e.getMessage());
			response.setSuccesObject(CommonConstant.NULL);
		}  catch (Exception e) {
			response.setResponseCode(CommonConstant.ERROR_CODE);
			response.setResponseMessage(getMessage("errorMessage"));
			response.setSuccesObject(CommonConstant.NULL);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * method is used to Load the sub type
	 * 
	 * @param subLocationVo
	 * @return response
	 */

	@PostMapping(FilePathConstants.KIOSK_SUBLOCATION_LOAD)
	@ResponseBody
	public ResponseEntity<JSONResponse> getAllSubTypeList(@RequestBody SubLocationVO subLocationVo) {
		JSONResponse response = new JSONResponse();
		try {

			List<SubLocationVO> subLocationVolist = kioskCommonDropdownService.getAllsublocationList(subLocationVo.getId());

			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("successMessage"));
			response.setSuccesObject(subLocationVolist);

		} catch (CommonException e) {
			response.setResponseCode(CommonConstant.FAILURE_CODE);
			response.setResponseMessage(e.getMessage());
			response.setSuccesObject(CommonConstant.NULL);
		} catch (LoginAuthException e) {
			response.setResponseCode(CommonConstant.FAILURE_CODE);
			response.setResponseMessage(e.getMessage());
			response.setSuccesObject(CommonConstant.NULL);
		}  catch (Exception e) {
			response.setResponseCode(CommonConstant.ERROR_CODE);
			response.setResponseMessage(getMessage("errorMessage"));
			response.setSuccesObject(CommonConstant.NULL);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Method is used to GetAll the user for User dropdown
	 * 
	 * @param subLocationVo
	 * @return response
	 */
	@PostMapping(FilePathConstants.KIOSK_USER_DROPDOWN)
	@ResponseBody
	public ResponseEntity<JSONResponse> getAllUser(@RequestBody UserMasterVo userMasterVo) {
		JSONResponse response = new JSONResponse();
		try {

			List<UserMasterVo> userMasterVoList = kioskCommonDropdownService.getAllUser(userMasterVo);

			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("successMessage"));
			response.setSuccesObject(userMasterVoList);

		} catch (CommonException e) {
			response.setResponseCode(CommonConstant.FAILURE_CODE);
			response.setResponseMessage(e.getMessage());
			response.setSuccesObject(CommonConstant.NULL);
		} catch (LoginAuthException e) {
			response.setResponseCode(CommonConstant.FAILURE_CODE);
			response.setResponseMessage(e.getMessage());
			response.setSuccesObject(CommonConstant.NULL);
		}  catch (Exception e) {
			response.setResponseCode(CommonConstant.ERROR_CODE);
			response.setResponseMessage(getMessage("errorMessage"));
			response.setSuccesObject(CommonConstant.NULL);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Method is used to GetAll the user for User dropdown
	 * 
	 * @param subLocationVo
	 * @return response
	 */
	@PostMapping(FilePathConstants.USER_EXECUTER)
	@ResponseBody
	public ResponseEntity<JSONResponse> getUserExecuter(@RequestBody UserMasterVo userMasterVo) {
		JSONResponse response = new JSONResponse();
		try {

			List<UserMasterVo> userMasterVoList = kioskCommonDropdownService.getUserExecuter(userMasterVo);

			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("successMessage"));
			response.setSuccesObject(userMasterVoList);

		} catch (CommonException e) {
			response.setResponseCode(CommonConstant.FAILURE_CODE);
			response.setResponseMessage(e.getMessage());
			response.setSuccesObject(CommonConstant.NULL);
		} catch (LoginAuthException e) {
			response.setResponseCode(CommonConstant.FAILURE_CODE);
			response.setResponseMessage(e.getMessage());
			response.setSuccesObject(CommonConstant.NULL);
		}  catch (Exception e) {
			response.setResponseCode(CommonConstant.ERROR_CODE);
			response.setResponseMessage(getMessage("errorMessage"));
			response.setSuccesObject(CommonConstant.NULL);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	

	/**
	 * Method is used to get the user Level
	 * 
	 * @param commonStorageVo
	 * @return response
	 */
	@PostMapping(FilePathConstants.KIOSK_USER_LEVEL)
	@ResponseBody
	public ResponseEntity<JSONResponse> getUserLevel(@RequestBody CommonStorageVo commonStorageVo) {
		JSONResponse response = new JSONResponse();
		try {

			List<CommonStorageVo> commonStorageVoList = kioskCommonDropdownService.getUserLevel(commonStorageVo);

			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("successMessage"));
			response.setSuccesObject(commonStorageVoList);

		} catch (CommonException e) {
			response.setResponseCode(CommonConstant.FAILURE_CODE);
			response.setResponseMessage(e.getMessage());
			response.setSuccesObject(CommonConstant.NULL);
		} catch (LoginAuthException e) {
			response.setResponseCode(CommonConstant.FAILURE_CODE);
			response.setResponseMessage(e.getMessage());
			response.setSuccesObject(CommonConstant.NULL);
		}  catch (Exception e) {
			response.setResponseCode(CommonConstant.ERROR_CODE);
			response.setResponseMessage(getMessage("errorMessage"));
			response.setSuccesObject(CommonConstant.NULL);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(FilePathConstants.KIOSK_USER_DEP)
	@ResponseBody
	public ResponseEntity<JSONResponse> getUserDep(@RequestBody UserMasterVo userMasterVo) {
		JSONResponse response = new JSONResponse();
		try {
			List<UserMasterVo> userMasterVoList = kioskCommonDropdownService.getUserDep(userMasterVo);
			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("successMessage"));
			response.setSuccesObject(userMasterVoList);
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

	@PostMapping(FilePathConstants.KIOSK_USER_ROLE_ROLETYPE)
	@ResponseBody
	public ResponseEntity<JSONResponse> getRoleType(@RequestBody UserRoleTypeVO userRoleTypeVo) {
		JSONResponse response = new JSONResponse();
		try {
			List<UserRoleTypeVO> userRoleTypeVoList = kioskCommonDropdownService.getRoleType(userRoleTypeVo);
			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("successMessage"));
			response.setSuccesObject(userRoleTypeVoList);
		} catch (CommonException e) {
			response.setResponseCode(CommonConstant.FAILURE_CODE);
			response.setResponseMessage(e.getMessage());
			response.setSuccesObject(CommonConstant.NULL);
		} catch (LoginAuthException e) {
			response.setResponseCode(CommonConstant.FAILURE_CODE);
			response.setResponseMessage(e.getMessage());
			response.setSuccesObject(CommonConstant.NULL);
		}  catch (Exception e) {
			response.setResponseCode(CommonConstant.ERROR_CODE);
			response.setResponseMessage(getMessage("errorMessage"));
			response.setSuccesObject(CommonConstant.NULL);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Reassign User Id
	 * 
	 * @param userRoleTypeVo
	 * @return
	 */
	@PostMapping(FilePathConstants.KIOSK_RESOLVER_REASSIGN)
	@ResponseBody
	public ResponseEntity<JSONResponse> getReassignUser(@RequestBody CommonVO requestWorkFlowAuditVo) {
		JSONResponse response = new JSONResponse();
		try {

			List<UserMasterVo> userMasterVoList = kioskCommonDropdownService.getReassignUser(requestWorkFlowAuditVo);

			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("successMessage"));
			response.setSuccesObject(userMasterVoList);

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
