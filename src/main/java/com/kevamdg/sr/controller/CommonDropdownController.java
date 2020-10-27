package com.kevamdg.sr.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.log.Logger;
import com.itextpdf.text.log.LoggerFactory;
import com.kevamdg.sr.config.CommonException;
import com.kevamdg.sr.constants.CommonConstant;
import com.kevamdg.sr.constants.FilePathConstants;
import com.kevamdg.sr.entity.UserEntity;
import com.kevamdg.sr.repository.UserRepository;
import com.kevamdg.sr.service.CommonDropdownService;
import com.kevamdg.sr.vo.CommonDropDownVO;
import com.kevamdg.sr.vo.CommonRoleDropDownVO;
import com.kevamdg.sr.vo.CommonSalesOrgDropDownVO;
import com.kevamdg.sr.vo.CommonScreenDropDownVO;
import com.kevamdg.sr.vo.CommonScreenFieldDropDownVO;
import com.kevamdg.sr.vo.CommonScreenFunctionDropDownVO;
import com.kevamdg.sr.vo.CommonSubScreenDropDownVO;
import com.kevamdg.sr.vo.JSONResponse;
import com.kevamdg.sr.vo.PackTypeValidationVO;
import com.kevamdg.sr.vo.UserVO;

@RestController
public class CommonDropdownController extends CommonAction<CommonDropDownVO> {

	Logger logger = LoggerFactory.getLogger(DropdownController.class);

	@Autowired
	CommonDropdownService dropdownService;

	@Autowired
	UserRepository user;

	@PostMapping(FilePathConstants.DISTRIBUTION_CHANNEL)
	@ResponseBody
	public ResponseEntity<JSONResponse> loadDistributionChannel() throws IOException {

		JSONResponse jsonResponse = new JSONResponse();
		try {
			List<CommonDropDownVO> dcList = dropdownService.loadDistributionChannel();
			jsonResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
			jsonResponse.setSuccesObject(dcList);
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

	@PostMapping(FilePathConstants.MATERIAL_GROUP)
	@ResponseBody
	public ResponseEntity<JSONResponse> materialGroup() throws IOException {

		JSONResponse jsonResponse = new JSONResponse();
		try {
			List<CommonDropDownVO> dcList = dropdownService.materialGroup();
			jsonResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
			jsonResponse.setSuccesObject(dcList);
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

	@PostMapping(FilePathConstants.FLAVOUR_SOLUBILITY)
	@ResponseBody
	public ResponseEntity<JSONResponse> flavourSolubility() throws IOException {

		JSONResponse jsonResponse = new JSONResponse();
		try {
			List<CommonDropDownVO> dcList = dropdownService.flavourSolubility();
			jsonResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
			jsonResponse.setSuccesObject(dcList);
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

	@PostMapping(FilePathConstants.LEGAL_STATUS)
	@ResponseBody
	public ResponseEntity<JSONResponse> legalStatus() throws IOException {

		JSONResponse jsonResponse = new JSONResponse();
		try {
			List<CommonDropDownVO> dcList = dropdownService.legalStatus();
			jsonResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
			jsonResponse.setSuccesObject(dcList);
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

	@PostMapping(FilePathConstants.LEGAL_DECLARATION)
	@ResponseBody
	public ResponseEntity<JSONResponse> legalDelcaration() throws IOException {

		JSONResponse jsonResponse = new JSONResponse();
		try {
			List<CommonDropDownVO> dcList = dropdownService.legalDelcaration();
			jsonResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
			jsonResponse.setSuccesObject(dcList);
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

	@PostMapping(FilePathConstants.CATEGORY_MAP)
	@ResponseBody
	public ResponseEntity<JSONResponse> loadMapCategory() throws IOException {

		JSONResponse jsonResponse = new JSONResponse();
		try {
			List<CommonDropDownVO> dcList = dropdownService.loadMapCategory();
			jsonResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
			jsonResponse.setSuccesObject(dcList);
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

	@PostMapping(FilePathConstants.OTHER)
	@ResponseBody
	public ResponseEntity<JSONResponse> loadOther(@RequestBody CommonDropDownVO commonDropDownVO) throws IOException {

		JSONResponse jsonResponse = new JSONResponse();
		try {
			List<CommonDropDownVO> dcList = dropdownService.loadOther(commonDropDownVO.getId());
			jsonResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
			jsonResponse.setSuccesObject(dcList);
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

	@PostMapping(FilePathConstants.PACK_TYPE)
	@ResponseBody
	public ResponseEntity<JSONResponse> loadPackType(@RequestBody PackTypeValidationVO packTypeValidationVO)
			throws IOException {

		JSONResponse jsonResponse = new JSONResponse();
		try {
			List<CommonDropDownVO> dcList = dropdownService.loadPackType(packTypeValidationVO);
			jsonResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
			jsonResponse.setSuccesObject(dcList);
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

	@PostMapping(FilePathConstants.PACK_SIZE)
	@ResponseBody
	public ResponseEntity<JSONResponse> loadPackSize(@RequestBody CommonDropDownVO commonDropDownVO)
			throws IOException {

		JSONResponse jsonResponse = new JSONResponse();
		try {
			List<CommonDropDownVO> dcList = dropdownService.loadPackSize(commonDropDownVO.getId());
			jsonResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
			jsonResponse.setSuccesObject(dcList);
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

	@PostMapping(FilePathConstants.DIVISION_DROPDOWN)
	@ResponseBody
	public ResponseEntity<JSONResponse> loadDivision(@RequestBody CommonDropDownVO commonDropDownVO)
			throws IOException {

		JSONResponse jsonResponse = new JSONResponse();
		try {
			List<CommonDropDownVO> divisionList = dropdownService.loadDivision(commonDropDownVO.getId());
			jsonResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
			jsonResponse.setSuccesObject(divisionList);
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

	@PostMapping(FilePathConstants.SALES_ORG_DROPDOWN)
	@ResponseBody
	public ResponseEntity<JSONResponse> loadSalesOrg(@RequestBody CommonDropDownVO commonDropDownVO)
			throws IOException {

		JSONResponse jsonResponse = new JSONResponse();
		try {
			List<CommonDropDownVO> soList = dropdownService.loadSalesOrg(commonDropDownVO.getUserId());
			jsonResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
			jsonResponse.setSuccesObject(soList);
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

	@PostMapping(FilePathConstants.MATERIAL_TYPE_DROPDOWN)
	@ResponseBody
	public ResponseEntity<JSONResponse> loadMaterialType() throws IOException {

		JSONResponse jsonResponse = new JSONResponse();
		try {
			List<CommonDropDownVO> mtList = dropdownService.loadMaterialType();
			jsonResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
			jsonResponse.setSuccesObject(mtList);
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

	@PostMapping(FilePathConstants.UNIT_DROPDOWN)
	@ResponseBody
	public ResponseEntity<JSONResponse> loadUnit() throws IOException {

		JSONResponse jsonResponse = new JSONResponse();
		try {
			List<CommonDropDownVO> unitList = dropdownService.loadUnit();
			jsonResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
			jsonResponse.setSuccesObject(unitList);
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
	
	
	@PostMapping(FilePathConstants.UOM_DROPDOWN)
	@ResponseBody
	public ResponseEntity<JSONResponse> uom() throws IOException {

		JSONResponse jsonResponse = new JSONResponse();
		try {
			List<CommonDropDownVO> unitList = dropdownService.uom();
			jsonResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
			jsonResponse.setSuccesObject(unitList);
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

	@PostMapping(FilePathConstants.MATERIAL_PURPOSE_DROPDOWN)
	@ResponseBody
	public ResponseEntity<JSONResponse> loadMaterialPurpose() throws IOException {

		JSONResponse jsonResponse = new JSONResponse();
		try {
			List<CommonDropDownVO> mpList = dropdownService.loadMaterialPurpose();
			jsonResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
			jsonResponse.setSuccesObject(mpList);
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

	@PostMapping(FilePathConstants.CURRENCY_DROPDOWN)
	@ResponseBody
	public ResponseEntity<JSONResponse> currency() throws IOException {

		JSONResponse jsonResponse = new JSONResponse();
		try {
			List<CommonDropDownVO> currencyList = dropdownService.currency();
			jsonResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
			jsonResponse.setSuccesObject(currencyList);
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

	@PostMapping(FilePathConstants.NEW_EXTENDS_DROPDOWN)
	@ResponseBody
	public ResponseEntity<JSONResponse> newextends() throws IOException {

		JSONResponse jsonResponse = new JSONResponse();
		try {
			List<CommonDropDownVO> currencyList = dropdownService.newextends();
			jsonResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
			jsonResponse.setSuccesObject(currencyList);
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

	@PostMapping(FilePathConstants.PLANT_DROPDOWN)
	@ResponseBody
	public ResponseEntity<JSONResponse> loadPlant(@RequestBody CommonDropDownVO commonDropDownVO) throws IOException {

		JSONResponse jsonResponse = new JSONResponse();
		try {
			List<CommonDropDownVO> plantList = dropdownService.loadPlant(commonDropDownVO.getId());
			jsonResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
			jsonResponse.setSuccesObject(plantList);
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

	@PostMapping(FilePathConstants.CUSTOMER_DROPDOWN)
	@ResponseBody
	public ResponseEntity<JSONResponse> customer(@RequestBody CommonDropDownVO commonDropDownVO) throws IOException {

		JSONResponse jsonResponse = new JSONResponse();
		try {
			List<CommonDropDownVO> plantList = dropdownService.customer(commonDropDownVO);
			jsonResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
			jsonResponse.setSuccesObject(plantList);
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

	@PostMapping(FilePathConstants.ROLE_MAPPING_DROPDOWN)
	@ResponseBody
	public ResponseEntity<JSONResponse> role(@RequestBody CommonDropDownVO commonDropDownVO) throws IOException {

		JSONResponse jsonResponse = new JSONResponse();
		try {
			List<CommonRoleDropDownVO> plantList = dropdownService.role(commonDropDownVO.getUserId());
			UserEntity userEntity = user.findOne(commonDropDownVO.getUserId());
			jsonResponse.setUserName(userEntity.getFirstName().concat(" " + userEntity.getLastName()));
			jsonResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
			jsonResponse.setSuccesObject(plantList);
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

	@PostMapping(FilePathConstants.SALESORG_MAPPING_DROPDOWN)
	@ResponseBody
	public ResponseEntity<JSONResponse> salesOrgMapping(@RequestBody CommonDropDownVO commonDropDownVO)
			throws IOException {

		JSONResponse jsonResponse = new JSONResponse();
		try {
			List<CommonSalesOrgDropDownVO> plantList = dropdownService.salesOrgMapping(commonDropDownVO.getUserId());
			jsonResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
			jsonResponse.setSuccesObject(plantList);
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

	@PostMapping(FilePathConstants.SCREEN_MAPPING_DROPDOWN)
	@ResponseBody
	public ResponseEntity<JSONResponse> screen(@RequestBody CommonDropDownVO commonDropDownVO) throws IOException {

		JSONResponse jsonResponse = new JSONResponse();
		try {
			List<CommonScreenDropDownVO> screenList = dropdownService.screen(commonDropDownVO.getUserId());
			jsonResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
			jsonResponse.setSuccesObject(screenList);
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

	@PostMapping(FilePathConstants.SUB_SCREEN_MAPPING_DROPDOWN)
	@ResponseBody
	public ResponseEntity<JSONResponse> subScreen(@RequestBody CommonDropDownVO commonDropDownVO) throws IOException {

		JSONResponse jsonResponse = new JSONResponse();
		try {
			List<CommonSubScreenDropDownVO> screenList = dropdownService.subScreen(commonDropDownVO);
			jsonResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
			jsonResponse.setSuccesObject(screenList);
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

	@PostMapping(FilePathConstants.SCREEN_FIELD_MAPPING_DROPDOWN)
	@ResponseBody
	public ResponseEntity<JSONResponse> screenField(@RequestBody CommonDropDownVO commonDropDownVO) throws IOException {

		JSONResponse jsonResponse = new JSONResponse();
		try {
			List<CommonScreenFieldDropDownVO> screenList = dropdownService.screenField(commonDropDownVO);
			jsonResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
			jsonResponse.setSuccesObject(screenList);
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

	@PostMapping(FilePathConstants.SCREEN_FUNCTION_MAPPING_DROPDOWN)
	@ResponseBody
	public ResponseEntity<JSONResponse> screenFunction(@RequestBody CommonDropDownVO commonDropDownVO)
			throws IOException {

		JSONResponse jsonResponse = new JSONResponse();
		try {
			List<CommonScreenFunctionDropDownVO> screenList = dropdownService.screenFunction(commonDropDownVO);
			jsonResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
			jsonResponse.setSuccesObject(screenList);
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

	@PostMapping(FilePathConstants.PLANT_MAPPING_DROPDOWN)
	@ResponseBody
	public ResponseEntity<JSONResponse> plantMapping(@RequestBody CommonDropDownVO commonDropDownVO)
			throws IOException {

		JSONResponse jsonResponse = new JSONResponse();
		try {
			List<CommonDropDownVO> screenList = dropdownService.plantMapping(commonDropDownVO);
			jsonResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
			jsonResponse.setSuccesObject(screenList);
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

	@PostMapping(FilePathConstants.PACK_TYPE_VALIDATION)
	@ResponseBody
	public ResponseEntity<JSONResponse> findCommonDropdownId(@RequestBody CommonDropDownVO commonDropDownVO)
			throws IOException {

		JSONResponse jsonResponse = new JSONResponse();
		try {
			PackTypeValidationVO screenList = dropdownService.findCommonDropdownId(commonDropDownVO.getId());
			jsonResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
			jsonResponse.setSuccesObject(screenList);
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

	@PostMapping(FilePathConstants.USER_DROPDOWN_MAPPING)
	@ResponseBody
	public ResponseEntity<JSONResponse> user(@RequestBody CommonDropDownVO commonDropDownVO) throws IOException {

		JSONResponse jsonResponse = new JSONResponse();
		try {
			List<UserVO> list = dropdownService.user(commonDropDownVO.getId());
			jsonResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
			jsonResponse.setSuccesObject(list);
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
