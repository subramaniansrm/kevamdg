package com.kevamdg.sr.controller;

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
import com.kevamdg.sr.service.DepartmentService;
import com.kevamdg.sr.vo.AuthDetailsVO;
import com.kevamdg.sr.vo.DepartmentVO;
import com.kevamdg.sr.vo.JSONRequestVO;
import com.kevamdg.sr.vo.JSONResponse;
import com.kevamdg.sr.vo.UserScreenMappingVO;

/**
 * Class used to list,create,update,delete and search location details
 * 
 * @author raathikaabm
 *
 */

@RestController
public class DepartmentController extends CommonAction<DepartmentVO> {

	private static final Logger logger = LogManager.getLogger(DepartmentController.class);


	@Autowired
	DepartmentService departmentService;
	
	/**
	 * method is used to list userDepartment details
	 * 
	 * @return response Response
	 */
	@PostMapping(FilePathConstants.DEPARTMENT_GETALL)
	@ResponseBody
	public ResponseEntity<JSONResponse> listUserDepartment(HttpServletRequest request,@RequestBody JSONRequestVO commonVO) {
		JSONResponse jsonResponse = new JSONResponse();

		String accessToken = getHeaderAccessToken(request);
		AuthDetailsVO authDetailsVo = null;
		try {
			 authDetailsVo = tokenValidate(accessToken);
			if (null == authDetailsVo) {
				//get from message.properties
				throw new LoginAuthException("Token Expired / Already Logined");
			}
			UserScreenMappingVO userScreenMappingVO = getScreen(commonVO.getSubScreenId(), authDetailsVo);

			List<DepartmentVO> departmentVOList = new ArrayList<DepartmentVO>();
			departmentVOList = departmentService.getAll();
			jsonResponse.setAuthSuccesObject(userScreenMappingVO);
			jsonResponse.setSuccesObject(departmentVOList);
			jsonResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
			jsonResponse.setResponseMessage(getMessage("successMessage"));
			return new ResponseEntity<>(jsonResponse, HttpStatus.OK);


		} catch (CommonException e) {
			logger.error(e.getMessage());
			jsonResponse.setResponseCode(CommonConstant.FAILURE_CODE);
			jsonResponse.setResponseMessage(e.getMessage());
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		} catch (LoginAuthException e) {
			logger.error("error", e);
			jsonResponse.setResponseCode(CommonConstant.FAILURE_CODE);
			jsonResponse.setResponseMessage(e.getMessage());
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		} catch (Exception e) {
			logger.error(e.getMessage());
			jsonResponse.setResponseCode(CommonConstant.ERROR_CODE);
			jsonResponse.setResponseMessage(getMessage("errorMessage"));
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		}
		return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
	}
	
	

	@PostMapping(FilePathConstants.DEPARTMENT_AUTH_ADD)
	public ResponseEntity<JSONResponse> addUserDepartment(HttpServletRequest request,@RequestBody JSONRequestVO commonVO)
			{
		JSONResponse jsonResponse = new JSONResponse();
		String accessToken = getHeaderAccessToken(request);
		AuthDetailsVO authDetailsVo = null;
		try {
			 authDetailsVo = tokenValidate(accessToken);
			if (null == authDetailsVo) {
				//get from message.properties
				throw new LoginAuthException("Token Expired / Already Logined");
			}
			UserScreenMappingVO userScreenMappingVO = getScreen(commonVO.getSubScreenId(), authDetailsVo);

			jsonResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
			jsonResponse.setAuthSuccesObject(userScreenMappingVO);
			jsonResponse.setResponseMessage(getMessage("successMessage"));
			return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
		} catch (CommonException e) {
			logger.error(e.getMessage());
			jsonResponse.setResponseCode(CommonConstant.FAILURE_CODE);
			jsonResponse.setResponseMessage(e.getMessage());
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		} catch (LoginAuthException e) {
			logger.error("error", e);
			jsonResponse.setResponseCode(CommonConstant.FAILURE_CODE);
			jsonResponse.setResponseMessage(e.getMessage());
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		} catch (Exception e) {
			logger.error(e.getMessage());
			jsonResponse.setResponseCode(CommonConstant.ERROR_CODE);
			jsonResponse.setResponseMessage(getMessage("errorMessage"));
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		}
		return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
	}
	
	
	
	/***
	 * Method to authentiacate 
	 * @param userLocationMasterVo
	 * @return
	 */
	
		/**
	 * Method used to create department details
	 * 
	 * @param departmentVO
	 * @return
	 */
	@PostMapping(FilePathConstants.DEPARTMENT_CREATE)
	@ResponseBody
	public ResponseEntity<JSONResponse> create(HttpServletRequest request,@RequestBody DepartmentVO departmentVO) {
		JSONResponse jsonResponse = new JSONResponse();

		String accessToken = getHeaderAccessToken(request);
		AuthDetailsVO authDetailsVo = null;
		try {
			 authDetailsVo = tokenValidate(accessToken);
			if (null == authDetailsVo) {
				//get from message.properties
				throw new LoginAuthException("Token Expired / Already Logined");
			}
			departmentService.create(departmentVO,authDetailsVo);
			jsonResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
			jsonResponse.setResponseMessage(getMessage("saveSuccessMessage"));
			jsonResponse.setSuccesObject(CommonConstant.NULL);
			return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
		} catch (CommonException e) {
			logger.error(e.getMessage());
			jsonResponse.setResponseCode(CommonConstant.FAILURE_CODE);
			jsonResponse.setResponseMessage(e.getMessage());
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		} catch (LoginAuthException e) {
			logger.error("error", e);
			jsonResponse.setResponseCode(CommonConstant.FAILURE_CODE);
			jsonResponse.setResponseMessage(e.getMessage());
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		} catch (Exception e) {
			logger.error(e.getMessage());
			jsonResponse.setResponseCode(CommonConstant.ERROR_CODE);
			jsonResponse.setResponseMessage(getMessage("saveErrorMessage"));
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		}

		return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
	}

	/**
	 * Method used to update department details
	 * 
	 * @param departmentVO
	 * @return
	 */
	@PostMapping(FilePathConstants.DEPARTMENT_UPDATE)
	@ResponseBody
	public ResponseEntity<JSONResponse> update(HttpServletRequest request,@RequestBody DepartmentVO departmentVO) {
		JSONResponse jsonResponse = new JSONResponse();

		String accessToken = getHeaderAccessToken(request);
		AuthDetailsVO authDetailsVo = null;
		try {
			 authDetailsVo = tokenValidate(accessToken);
			if (null == authDetailsVo) {
				//get from message.properties
				throw new LoginAuthException("Token Expired / Already Logined");
			}
			departmentService.update(departmentVO,authDetailsVo);

			jsonResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
			jsonResponse.setResponseMessage(getMessage("updateSuccessMessage"));
			jsonResponse.setSuccesObject(CommonConstant.NULL);
			return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
		} catch (CommonException e) {
			logger.error(e.getMessage());
			jsonResponse.setResponseCode(CommonConstant.FAILURE_CODE);
			jsonResponse.setResponseMessage(e.getMessage());
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		} catch (LoginAuthException e) {
			logger.error("error", e);
			jsonResponse.setResponseCode(CommonConstant.FAILURE_CODE);
			jsonResponse.setResponseMessage(e.getMessage());
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		} catch (Exception e) {
			logger.error(e.getMessage());
			jsonResponse.setResponseCode(CommonConstant.ERROR_CODE);
			jsonResponse.setResponseMessage(getMessage("updateErrorMessage"));
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		}
		return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
	}

	/**
	 * Method used to delete department
	 * 
	 * @param departmentVO
	 * @return
	 */
	@PostMapping(FilePathConstants.DEPARTMENT_DELETE)
	@ResponseBody
	public ResponseEntity<JSONResponse> delete(HttpServletRequest request,@RequestBody DepartmentVO departmentVO) {
		JSONResponse jsonResponse = new JSONResponse();

		String accessToken = getHeaderAccessToken(request);
		AuthDetailsVO authDetailsVo = null;
		try {
			 authDetailsVo = tokenValidate(accessToken);
			if (null == authDetailsVo) {
				//get from message.properties
				throw new LoginAuthException("Token Expired / Already Logined");
			}

			departmentService.delete(departmentVO,authDetailsVo);

			jsonResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
			jsonResponse.setResponseMessage(getMessage("deleteSuccessMessage"));
			jsonResponse.setSuccesObject(CommonConstant.NULL);
			return new ResponseEntity<>(jsonResponse, HttpStatus.OK);

		} catch (CommonException e) {
			logger.error(e.getMessage());
			jsonResponse.setResponseCode(CommonConstant.FAILURE_CODE);
			jsonResponse.setResponseMessage(e.getMessage());
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		} catch (LoginAuthException e) {
			logger.error("error", e);
			jsonResponse.setResponseCode(CommonConstant.FAILURE_CODE);
			jsonResponse.setResponseMessage(e.getMessage());
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		} catch (Exception e) {
			logger.error(e.getMessage());
			jsonResponse.setResponseCode(CommonConstant.ERROR_CODE);
			jsonResponse.setResponseMessage(getMessage("deleteErrorMessage"));
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		}

		return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
	}

	/**
	 * Method used to view department details
	 * 
	 * @param department
	 * @return
	 */
	@PostMapping(FilePathConstants.DEPARTMENT_VIEW)
	@ResponseBody
	public ResponseEntity<JSONResponse> View(HttpServletRequest request,@RequestBody JSONRequestVO commonVO) {
		JSONResponse jsonResponse = new JSONResponse();
		String accessToken = getHeaderAccessToken(request);
		AuthDetailsVO authDetailsVo = null;
		try {
			 authDetailsVo = tokenValidate(accessToken);
			if (null == authDetailsVo) {
				//get from message.properties
				throw new LoginAuthException("Token Expired / Already Logined");
			}
			UserScreenMappingVO userScreenMappingVO = getScreen(commonVO.getSubScreenId(), authDetailsVo);
			DepartmentVO departmentVO = new DepartmentVO();

			departmentVO = departmentService.view(commonVO.getId());
			jsonResponse.setAuthSuccesObject(userScreenMappingVO);


			jsonResponse.setSuccesObject(departmentVO);
			jsonResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
			jsonResponse.setResponseMessage(getMessage("successMessage"));
			return new ResponseEntity<>(jsonResponse, HttpStatus.OK);

		} catch (CommonException e) {
			logger.error(e.getMessage());
			jsonResponse.setResponseCode(CommonConstant.FAILURE_CODE);
			jsonResponse.setResponseMessage(e.getMessage());
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		} catch (LoginAuthException e) {
			logger.error("error", e);
			jsonResponse.setResponseCode(CommonConstant.FAILURE_CODE);
			jsonResponse.setResponseMessage(e.getMessage());
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		} catch (Exception e) {
			logger.error(e.getMessage());
			jsonResponse.setResponseCode(CommonConstant.ERROR_CODE);
			jsonResponse.setResponseMessage(getMessage("errorMessage"));
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		}

		return new ResponseEntity<>(jsonResponse, HttpStatus.OK);


	}

	/**
	 * Method used to search departmebt details
	 * 
	 * @param department
	 * @return
	 */
	@PostMapping(FilePathConstants.DEPARTMENT_SEARCH)
	@ResponseBody
	public ResponseEntity<JSONResponse> Search(HttpServletRequest request,@RequestBody DepartmentVO department) {
		
		JSONResponse jsonResponse = new JSONResponse();

		String accessToken = getHeaderAccessToken(request);
		AuthDetailsVO authDetailsVo = null;
		try {
			 authDetailsVo = tokenValidate(accessToken);
			if (null == authDetailsVo) {
				//get from message.properties
				throw new LoginAuthException("Token Expired / Already Logined");
			}
			List<DepartmentVO> departmentVOList = departmentService.search(department);

			jsonResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
			jsonResponse.setResponseMessage(getMessage("successMessage"));
			jsonResponse.setSuccesObject(departmentVOList);
			return new ResponseEntity<>(jsonResponse, HttpStatus.OK);

		} catch (CommonException e) {
			logger.error(e.getMessage());
			jsonResponse.setResponseCode(CommonConstant.FAILURE_CODE);
			jsonResponse.setResponseMessage(e.getMessage());
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		} catch (LoginAuthException e) {
			logger.error("error", e);
			jsonResponse.setResponseCode(CommonConstant.FAILURE_CODE);
			jsonResponse.setResponseMessage(e.getMessage());
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		} catch (Exception e) {
			logger.error(e.getMessage());
			jsonResponse.setResponseCode(CommonConstant.ERROR_CODE);
			jsonResponse.setResponseMessage(getMessage("errorMessage"));
			jsonResponse.setSuccesObject(CommonConstant.NULL);
		}
		return new ResponseEntity<>(jsonResponse, HttpStatus.OK);

	}

}
