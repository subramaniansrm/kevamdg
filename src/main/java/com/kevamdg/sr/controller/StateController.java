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
import com.kevamdg.sr.constants.FilePathConstants;
import com.kevamdg.sr.service.StateService;
import com.kevamdg.sr.vo.AuthDetailsVO;
import com.kevamdg.sr.vo.JSONRequestVO;
import com.kevamdg.sr.vo.JSONResponse;
import com.kevamdg.sr.vo.StateVO;

/**
 * State Controller used to list,create,update,delete,search sub location
 * details
 * 
 * @author raathikaabm
 *
 */

@RestController
public class StateController extends CommonAction<StateVO> {

	private static final Logger logger = LogManager.getLogger(LocationController.class);

	@Autowired
	private StateService stateService;
	
	/**
	 * Method used to get all state list
	 * 
	 * @return
	 */
	@PostMapping(FilePathConstants.STATE_LIST)
	@ResponseBody
	public ResponseEntity<JSONResponse> getState(HttpServletRequest request,@RequestBody JSONRequestVO commonVO) {
		JSONResponse response = new JSONResponse();

		String accessToken = getHeaderAccessToken(request);
		AuthDetailsVO authDetailsVo = null;
		try {
			 authDetailsVo = tokenValidate(accessToken);
			if (null == authDetailsVo) {
				//get from message.properties
				throw new LoginAuthException("Token Expired / Already Logined");
			}
			List<StateVO> stateVolist = stateService.getAllList();
			
			response.setSuccesObject(stateVolist);
			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("successMessage"));
			return new ResponseEntity<>(response, HttpStatus.OK);
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
	 * Method used to create state details
	 * 
	 * @param stateVO
	 * @return
	 */
	@PostMapping(FilePathConstants.STATE_CREATE)
	@ResponseBody
	public ResponseEntity<JSONResponse> create(HttpServletRequest request,@RequestBody StateVO stateVO) {
		JSONResponse response = new JSONResponse();

		String accessToken = getHeaderAccessToken(request);
		AuthDetailsVO authDetailsVo = null;
		try {
			 authDetailsVo = tokenValidate(accessToken);
			if (null == authDetailsVo) {
				//get from message.properties
				throw new LoginAuthException("Token Expired / Already Logined");
			}
			stateService.create(stateVO,authDetailsVo);

			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("saveSuccessMessage"));
			response.setSuccesObject(CommonConstant.NULL);
			return new ResponseEntity<>(response, HttpStatus.OK);
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
			response.setResponseMessage(getMessage("saveErrorMessage"));
			response.setSuccesObject(CommonConstant.NULL);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Method used to modify state details
	 * 
	 * @param stateVO
	 * @return
	 */

	@PostMapping(FilePathConstants.STATE_UPDATE)

	@ResponseBody
	public ResponseEntity<JSONResponse> update(HttpServletRequest request,@RequestBody StateVO stateVO) {
		JSONResponse response = new JSONResponse();

		String accessToken = getHeaderAccessToken(request);
		AuthDetailsVO authDetailsVo = null;
		try {
			 authDetailsVo = tokenValidate(accessToken);
			if (null == authDetailsVo) {
				//get from message.properties
				throw new LoginAuthException("Token Expired / Already Logined");
			}
			stateService.update(stateVO);

			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("updateSuccessMessage"));
			response.setSuccesObject(CommonConstant.NULL);
			return new ResponseEntity<>(response, HttpStatus.OK);
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
			response.setResponseMessage(getMessage("updateErrorMessage"));
			response.setSuccesObject(CommonConstant.NULL);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Method used to delte state
	 * 
	 * @param stateVO
	 * @return
	 */

	@PostMapping(FilePathConstants.STATE_DELETE)
	@ResponseBody
	public ResponseEntity<JSONResponse> delete(HttpServletRequest request,@RequestBody StateVO stateVO) {
		JSONResponse response = new JSONResponse();

		String accessToken = getHeaderAccessToken(request);
		AuthDetailsVO authDetailsVo = null;
		try {
			 authDetailsVo = tokenValidate(accessToken);
			if (null == authDetailsVo) {
				//get from message.properties
				throw new LoginAuthException("Token Expired / Already Logined");
			}
			stateService.delete(stateVO);

			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("deleteSuccessMessage"));
			response.setSuccesObject(CommonConstant.NULL);
			return new ResponseEntity<>(response, HttpStatus.OK);
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
			response.setResponseMessage(getMessage("deleteErrorMessage"));
			response.setSuccesObject(CommonConstant.NULL);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Method used to view state details
	 * 
	 * @param stateVO
	 * @return
	 */

	@PostMapping(FilePathConstants.STATE_VIEW)
	@ResponseBody
	public ResponseEntity<JSONResponse> View(HttpServletRequest request,@RequestBody JSONRequestVO commonVO) {
		JSONResponse response = new JSONResponse();
		String accessToken = getHeaderAccessToken(request);
		AuthDetailsVO authDetailsVo = null;
		try {
			 authDetailsVo = tokenValidate(accessToken);
			if (null == authDetailsVo) {
				//get from message.properties
				throw new LoginAuthException("Token Expired / Already Logined");
			}
			StateVO state = new StateVO();

			state = stateService.view(commonVO.getId());

			response.setSuccesObject(state);
			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("successMessage"));
			return new ResponseEntity<>(response, HttpStatus.OK);
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
	 * Method used to search state details
	 * 
	 * @param stateVO
	 * @return
	 */

	@PostMapping(FilePathConstants.STATE_SEARCH)
	@ResponseBody
	public ResponseEntity<JSONResponse> Search(HttpServletRequest request,@RequestBody StateVO stateVO) {
		JSONResponse response = new JSONResponse();

		String accessToken = getHeaderAccessToken(request);
		AuthDetailsVO authDetailsVo = null;
		try {
			 authDetailsVo = tokenValidate(accessToken);
			if (null == authDetailsVo) {
				//get from message.properties
				throw new LoginAuthException("Token Expired / Already Logined");
			}
			List<StateVO> stateVoList = stateService.search(stateVO);

			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("successMessage"));
			response.setSuccesObject(stateVoList);
			return new ResponseEntity<>(response, HttpStatus.OK);
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
