package com.kevamdg.sr.controller;

import java.io.IOException;
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
import com.kevamdg.sr.response.JSONResponse;
import com.kevamdg.sr.service.CustomerService;
import com.kevamdg.sr.util.ValidationUtil;
import com.kevamdg.sr.vo.AuthDetailsVO;
import com.kevamdg.sr.vo.CustomerVO;
import com.kevamdg.sr.vo.JSONRequestVO;
import com.kevamdg.sr.vo.UserScreenMappingVO;

@RestController
public class CustomerController extends CommonAction<CustomerVO> {

	private static final Logger logger = LogManager.getLogger(CustomerController.class);

	@Autowired
	CustomerService customerService;

	@PostMapping(FilePathConstants.CUSTOMER_LIST)
	@ResponseBody
	public ResponseEntity<JSONResponse> getAll(HttpServletRequest request, @RequestBody JSONRequestVO commonVO)
			throws IOException {
		JSONResponse response = new JSONResponse();
		String accessToken = getHeaderAccessToken(request);
		AuthDetailsVO authDetailsVo = null;
		try {
			authDetailsVo = tokenValidate(accessToken);
			if (null == authDetailsVo) {
				// get from message.properties
				throw new LoginAuthException("Token Expired / Already Logined");
			}
			UserScreenMappingVO userScreenMappingVO = getScreen(commonVO.getSubScreenId(), authDetailsVo);

			List<CustomerVO> customerVOList = customerService.getAll();

			if (null == customerVOList) {
				throw new CommonException(getMessage("common.noRecord"));
			}
			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("successMessage"));
			response.setAuthSuccesObject(userScreenMappingVO);
			response.setSuccesObject(customerVOList);
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

	@PostMapping(FilePathConstants.CUSTOMER_SEARCH)
	@ResponseBody
	public ResponseEntity<JSONResponse> search(HttpServletRequest request, @RequestBody CustomerVO customerVO) {
		JSONResponse response = new JSONResponse();
		String accessToken = getHeaderAccessToken(request);
		AuthDetailsVO authDetailsVo = null;
		try {
			authDetailsVo = tokenValidate(accessToken);
			if (null == authDetailsVo) {
				// get from message.properties
				throw new LoginAuthException("Token Expired / Already Logined");
			}
			List<CustomerVO> list = customerService.search(customerVO);
			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("successMessage"));
			response.setSuccesObject(list);
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

	@PostMapping(FilePathConstants.CUSTOMER_ADD)
	@ResponseBody
	public ResponseEntity<JSONResponse> add(HttpServletRequest request, @RequestBody JSONRequestVO commonVO) {
		JSONResponse response = new JSONResponse();
		String accessToken = getHeaderAccessToken(request);
		AuthDetailsVO authDetailsVo = null;
		try {
			authDetailsVo = tokenValidate(accessToken);
			if (null == authDetailsVo) {
				// get from message.properties
				throw new LoginAuthException("Token Expired / Already Logined");
			}
			UserScreenMappingVO userScreenMappingVO = getScreen(commonVO.getSubScreenId(), authDetailsVo);

			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("saveSuccessMessage"));
			response.setAuthSuccesObject(userScreenMappingVO);
			return new ResponseEntity<>(response, HttpStatus.OK);
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

	@PostMapping(FilePathConstants.CUSTOMER_CREATE)
	@ResponseBody
	public ResponseEntity<JSONResponse> save(HttpServletRequest request, @RequestBody CustomerVO customerVO) {
		JSONResponse response = new JSONResponse();
		String accessToken = getHeaderAccessToken(request);
		AuthDetailsVO authDetailsVo = null;
		try {
			authDetailsVo = tokenValidate(accessToken);
			if (null == authDetailsVo) {
				// get from message.properties
				throw new LoginAuthException("Token Expired / Already Logined");
			}
			customerService.duplicate(customerVO);

			logger.info(getMessage("processValidation"));
			this.saveValidation(customerVO);
			logger.info(getMessage("processValidationCompleted"));

			customerService.save(customerVO, authDetailsVo);

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

	public void saveValidation(CustomerVO customerVO) {

		if (ValidationUtil.isNullOrBlank(customerVO.getCustomerCode())) {
			throw new CommonException(getMessage("customer.code.validation"));
		}
		if (ValidationUtil.onlyDigits(customerVO.getCustomerCode())) {
			throw new CommonException(getMessage("customer.digit.validation"));
		}
		if (customerVO.getCustomerCode().length() > 7) {
			throw new CommonException(getMessage("customer.code.validation"));
		}
		if (ValidationUtil.isNullOrBlank(customerVO.getCustomerName())) {
			throw new CommonException(getMessage("customer.name.validation"));
		}
		if (ValidationUtil.onlyAlphabets(customerVO.getCustomerName())) {
			throw new CommonException(getMessage("customer.name.alphabet.validation"));
		}
		if (customerVO.getCustomerName().length() > 50) {
			throw new CommonException(getMessage("customer.name.alphabet.validation"));
		}
		if (ValidationUtil.isNullOrBlank(customerVO.getSalesOrgId())) {
			throw new CommonException(getMessage("customer.sales.validation"));
		}
		if (ValidationUtil.isNullOrBlank(customerVO.getPlantId())) {
			throw new CommonException(getMessage("customer.plant.validation"));
		}
		if (ValidationUtil.isNullOrBlank(customerVO.getDistributionId())) {
			throw new CommonException(getMessage("customer.distribution.validation"));
		}
		if (ValidationUtil.isNullOrBlank(customerVO.getCityId())) {
			throw new CommonException(getMessage("customer.city.validation"));
		}

	}

	@PostMapping(FilePathConstants.CUSTOMER_UPDATE)
	@ResponseBody
	public ResponseEntity<JSONResponse> update(HttpServletRequest request, @RequestBody CustomerVO customerVO) {
		JSONResponse response = new JSONResponse();
		String accessToken = getHeaderAccessToken(request);
		AuthDetailsVO authDetailsVo = null;
		try {
			authDetailsVo = tokenValidate(accessToken);
			if (null == authDetailsVo) {
				// get from message.properties
				throw new LoginAuthException("Token Expired / Already Logined");
			}
			customerService.duplicate(customerVO);

			logger.info(getMessage("processValidation"));
			this.saveValidation(customerVO);
			logger.info(getMessage("processValidationCompleted"));

			customerService.update(customerVO, authDetailsVo);

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

	@PostMapping(FilePathConstants.CUSTOMER_VIEW)
	@ResponseBody
	public ResponseEntity<JSONResponse> view(HttpServletRequest request, @RequestBody JSONRequestVO commonVO) {
		JSONResponse response = new JSONResponse();
		String accessToken = getHeaderAccessToken(request);
		AuthDetailsVO authDetailsVo = null;
		try {
			authDetailsVo = tokenValidate(accessToken);
			if (null == authDetailsVo) {
				// get from message.properties
				throw new LoginAuthException("Token Expired / Already Logined");
			}
			UserScreenMappingVO userScreenMappingVO = getScreen(commonVO.getSubScreenId(), authDetailsVo);
			CustomerVO customerVO = customerService.view(commonVO.getId());
			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setResponseMessage(getMessage("successMessage"));
			response.setSuccesObject(customerVO);
			response.setAuthSuccesObject(userScreenMappingVO);
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

	@PostMapping(FilePathConstants.CUSTOMER_DELETE)
	@ResponseBody
	public ResponseEntity<JSONResponse> delete(HttpServletRequest request, @RequestBody CustomerVO customerVO) {
		JSONResponse response = new JSONResponse();
		String accessToken = getHeaderAccessToken(request);
		AuthDetailsVO authDetailsVo = null;
		try {
			authDetailsVo = tokenValidate(accessToken);
			if (null == authDetailsVo) {
				// get from message.properties
				throw new LoginAuthException("Token Expired / Already Logined");
			}
			customerService.delete(customerVO, authDetailsVo);

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

}
