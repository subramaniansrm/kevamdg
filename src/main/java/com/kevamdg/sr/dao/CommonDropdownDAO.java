package com.kevamdg.sr.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.kevamdg.sr.constants.CommonConstant;
import com.kevamdg.sr.entity.PackTypeValidationEntity;
import com.kevamdg.sr.vo.CommonDropDownVO;
import com.kevamdg.sr.vo.PackTypeValidationVO;

@Repository
public class CommonDropdownDAO {

	@Autowired
	EntityManager entityManager;

	@Value("${commonDatabaseSchema}")
	private String commonDatabaseSchema;

	@Value("${mmDatabaseSchema}")
	private String mmDatabaseSchema;

	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> loadDistributionChannel() {
		try {
			String query = "SELECT l.id,l.dropdownName,l.dropdownCode from CommonDropdownEntity l "
					+ " where l.deleteFlag= '0' and l.fieldId = '" + CommonConstant.DISTRIBUTION_CHANNEL
					+ "' order by l.id";

			List<Object[]> list = (List<Object[]>) entityManager.createQuery(query).getResultList();

			return list;
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();

		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> materialGroup() {
		try {
			String query = "SELECT l.id,l.dropdownName,l.dropdownCode from CommonDropdownEntity l "
					+ " where l.deleteFlag= '0' and l.fieldId = '" + CommonConstant.MATERIAL_GROUP + "' order by l.id";

			List<Object[]> list = (List<Object[]>) entityManager.createQuery(query).getResultList();

			return list;
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();

		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> flavourSolubility() {
		try {
			String query = "SELECT l.id,l.dropdownName,l.dropdownCode from CommonDropdownEntity l "
					+ " where l.deleteFlag= '0' and l.fieldId = '" + CommonConstant.FLAVOUR_SOLUBILITY
					+ "' order by l.id";

			List<Object[]> list = (List<Object[]>) entityManager.createQuery(query).getResultList();

			return list;
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();

		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> legalStatus() {
		try {
			String query = "SELECT l.id,l.dropdownName,l.dropdownCode from CommonDropdownEntity l "
					+ " where l.deleteFlag= '0' and l.fieldId = '" + CommonConstant.LEGAL_STATUS + "' order by l.id";

			List<Object[]> list = (List<Object[]>) entityManager.createQuery(query).getResultList();

			return list;
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();

		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> legalDelcaration() {
		try {
			String query = "SELECT l.id,l.dropdownName,l.dropdownCode from CommonDropdownEntity l "
					+ " where l.deleteFlag= '0' and l.fieldId = '" + CommonConstant.LEGAL_DECLARATION
					+ "' order by l.id";

			List<Object[]> list = (List<Object[]>) entityManager.createQuery(query).getResultList();

			return list;
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();

		}
		return null;
	}

	public List<Object[]> loadMapCategory() {
		try {
			String query = "SELECT l.id,l.dropdownName from CommonDropdownEntity l "
					+ " where l.deleteFlag= '0' and l.fieldId = '" + CommonConstant.CATEGORY_MAP + "' order by l.id";

			List<Object[]> list = (List<Object[]>) entityManager.createQuery(query).getResultList();

			return list;
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();

		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> loadOther(int id) {
		try {
			String query = "SELECT l.id,l.dropdownName from CommonDropdownEntity l "
					+ " where l.deleteFlag= '0' and l.salesOrgId=" + id + " and l.fieldId = '" + CommonConstant.OTHER
					+ "' order by l.id";

			List<Object[]> list = (List<Object[]>) entityManager.createQuery(query).getResultList();

			return list;
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();

		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> loadPackType(PackTypeValidationVO packTypeValidationVO) {
		try {
			String query = "SELECT l.PACK_TYPE_ID,l.PACK_TYPE_NAME from pack_type_map_with_sales_organisation l "
					+ " where l.DELETE_FLAG= '0'";
			if (null != packTypeValidationVO.getSalesOrgId()) {
				query = query + " and l.SALES_ORG_ID=" + packTypeValidationVO.getSalesOrgId();
			}
			if (null != packTypeValidationVO.getPackSize()) {
				query = query + " and l.PACK_SIZE_ID = " + packTypeValidationVO.getPackSize();
			}
			query = query + " order by l.PACK_TYPE_ID";

			List<Object[]> list = (List<Object[]>) entityManager.createNativeQuery(query).getResultList();

			return list;
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();

		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> loadPackSize(int id) {
		try {
			String query = "SELECT l.id,l.dropdownName from CommonDropdownEntity l "
					+ " where l.deleteFlag= '0' and l.salesOrgId=" + id + " and l.fieldId = '"
					+ CommonConstant.PACK_SIZE + "' order by l.id";

			List<Object[]> list = (List<Object[]>) entityManager.createQuery(query).getResultList();

			return list;
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();

		}
		return null;
	}

	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> loadDivision(int id) {
		try {
			String query = "SELECT l.id,l.divisionName,l.divisionCode from DivisionEntity l,DivisionMappingEntity dl "
					+ " where dl.divisionId = l.id and" + " dl.deleteFlag= '0'" + " and dl.plantId =" + id
					+ " order by dl.defaultId desc";

			List<Object[]> list = (List<Object[]>) entityManager.createQuery(query).getResultList();

			return list;
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();

		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> currency() {
		try {
			String query = "SELECT l.id,l.currency from CurrencyEntity l " + " where l.deleteFlag= '0' order by l.id";

			List<Object[]> list = (List<Object[]>) entityManager.createQuery(query).getResultList();

			return list;
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();

		}
		return null;
	}

	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> loadSalesOrg(int id) {
		try {
			String query = "SELECT s.SALES_ORGANIZATION_ID,s.SALES_ORGANIZATION_NAME,s.SALES_ORGANIZATIN_CODE "
					+ " FROM " + commonDatabaseSchema + ".sales_organization s" + " LEFT JOIN " + mmDatabaseSchema
					+ ".user_sales_organization_mapping ums ON ums.SALES_ORGANIZATION_FK = s.SALES_ORGANIZATION_ID"
					+ " WHERE ums.IS_ACTIVE = 1 AND ums.USER_FK =" + id + " order by ums.DEFAULT_ID DESC";

			List<Object[]> list = (List<Object[]>) entityManager.createNativeQuery(query).getResultList();

			return list;
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();

		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> loadMaterialType() {
		try {
			String query = "SELECT l.id,l.dropdownName,l.dropdownCode from CommonDropdownEntity l "
					+ " where l.deleteFlag= '0' and l.fieldId ='" + CommonConstant.MATERIAL_TYPE + "' order by l.id";

			List<Object[]> list = (List<Object[]>) entityManager.createQuery(query).getResultList();

			return list;
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();

		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> newextends() {
		try {
			String query = "SELECT l.id,l.dropdownName,l.dropdownCode from CommonDropdownEntity l "
					+ " where l.deleteFlag= '0' and l.fieldId ='" + CommonConstant.NEW_EXTENDS + "' order by l.id";

			List<Object[]> list = (List<Object[]>) entityManager.createQuery(query).getResultList();

			return list;
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();

		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> loadUnit() {
		try {
			String query = "SELECT l.id,l.dropdownName,l.dropdownCode from CommonDropdownEntity l "
					+ " where l.deleteFlag= '0' and l.fieldId ='" + CommonConstant.UNIT + "' order by l.id";

			List<Object[]> list = (List<Object[]>) entityManager.createQuery(query).getResultList();

			return list;
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();

		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> uom() {
		try {
			String query = "SELECT l.id,l.dropdownName,l.dropdownCode from CommonDropdownEntity l "
					+ " where l.deleteFlag= '0' and l.fieldId ='" + CommonConstant.CONSTANT_FOURTEEN + "' order by l.id";

			List<Object[]> list = (List<Object[]>) entityManager.createQuery(query).getResultList();

			return list;
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();

		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> loadMaterialPurpose() {
		try {
			String query = "SELECT l.id,l.dropdownName,l.dropdownCode from CommonDropdownEntity l "
					+ " where l.deleteFlag= '0' and l.fieldId ='" + CommonConstant.MATERIAL_PURPOSE + "' order by l.id";

			List<Object[]> list = (List<Object[]>) entityManager.createQuery(query).getResultList();

			return list;
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();

		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> role(int id) {
		try {
			String query = "SELECT  ROLE_ID,USER_ROLE_NAME,IFNULL(IF(ROLE_FK!=0,'success','fail'),'fail')Selecttion"
					+ " FROM " + commonDatabaseSchema + ".user_role r "
					+ " LEFT JOIN  (SELECT ROLE_FK FROM " + mmDatabaseSchema + ".user_role_mapping  WHERE USER_FK="+ id + ") a"
					+ " ON r.ROLE_ID=a.ROLE_FK GROUP BY ROLE_ID ORDER BY ROLE_ID";

			List<Object[]> list = (List<Object[]>) entityManager.createNativeQuery(query).getResultList();

			return list;
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();

		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> salesOrgMapping(int id) {
		try {
			String query = "SELECT  SALES_ORGANIZATION_ID,SALES_ORGANIZATION_NAME,SALES_ORGANIZATIN_CODE,a.DEFAULT_ID,"
					+ " IFNULL(IF(SALES_ORGANIZATION_FK!=0,'success','fail'),'fail')Selecttion,a.USER_MAPPING_SALES_ORGANIZATION_PK  "
					+ " FROM " + commonDatabaseSchema + ".sales_organization r "
					+ " LEFT JOIN  (SELECT SALES_ORGANIZATION_FK,DEFAULT_ID,USER_MAPPING_SALES_ORGANIZATION_PK  "
					+ " FROM " + mmDatabaseSchema + ".user_sales_organization_mapping  WHERE IS_ACTIVE = 1 AND USER_FK="
					+ id + ") a " + " ON a.SALES_ORGANIZATION_FK=r.SALES_ORGANIZATION_ID"
					+ " GROUP BY SALES_ORGANIZATION_ID order by SALES_ORGANIZATION_ID";

			List<Object[]> list = (List<Object[]>) entityManager.createNativeQuery(query).getResultList();

			return list;
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();

		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> screen(int id) {
		try {
			String query = "SELECT  r.SCREEN_ID,SCREEN_NAME,"
					+ " IFNULL(IF(SCREEN_FK!=0,'success','fail'),'fail')Selecttion FROM " + commonDatabaseSchema
					+ ".mm_screen r" + " LEFT JOIN  (SELECT SCREEN_FK  FROM " + mmDatabaseSchema
					+ ".user_screen_mapping  WHERE USER_FK=" + id + ") a" + " ON SCREEN_FK=r.SCREEN_ID"
					+ " WHERE r.ACTIVE_FLAG = 1 GROUP BY r.SCREEN_ID order by r.SCREEN_ID";

			List<Object[]> list = (List<Object[]>) entityManager.createNativeQuery(query).getResultList();

			return list;
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();

		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> subScreen(CommonDropDownVO commonDropDownVO) {
		try {
			String query = "SELECT  r.SUB_SCREEN_PK_ID,SUB_SCREEN_NAME,"
					+ " IFNULL(IF(SUB_SCREEN_FK!=0,'success','fail'),'fail')Selecttion FROM " + commonDatabaseSchema
					+ ".mm_sub_screen r" + " LEFT JOIN  (SELECT SUB_SCREEN_FK  FROM " + mmDatabaseSchema
					+ ".user_sub_screen_mapping" + " WHERE USER_FK=" + commonDropDownVO.getUserId() + " AND SCREEN_ID ="
					+ commonDropDownVO.getId() + ") a ON SUB_SCREEN_FK=r.SUB_SCREEN_PK_ID" + " WHERE SCREEN_ID ="
					+ commonDropDownVO.getId() + " AND r.ACTIVE_FLAG = 1 GROUP BY r.SUB_SCREEN_PK_ID order by r.SUB_SCREEN_PK_ID";

			List<Object[]> list = (List<Object[]>) entityManager.createNativeQuery(query).getResultList();

			return list;
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();

		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> screenField(CommonDropDownVO commonDropDownVO) {
		try {
			String query = "SELECT  r.FIELD_ID,FIELD_NAME,"
					+ " IFNULL(IF(SCREEN_FIELD_FK!=0,'success','fail'),'fail')Selecttion" + " FROM "
					+ commonDatabaseSchema + ".mm_screen_field r" + " LEFT JOIN  (SELECT SCREEN_FIELD_FK  FROM "
					+ mmDatabaseSchema + ".user_screen_field_mapping" + " WHERE USER_FK=" + commonDropDownVO.getUserId()
					+ " AND SUB_SCREEN_ID =" + commonDropDownVO.getId()
					+ ") a ON SCREEN_FIELD_FK=r.FIELD_ID WHERE SUB_SCREEN_ID =" + commonDropDownVO.getId()
					+ " AND r.ACTIVE_FLAG = 1 GROUP BY r.FIELD_ID order by r.FIELD_ID";

			List<Object[]> list = (List<Object[]>) entityManager.createNativeQuery(query).getResultList();

			return list;
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();

		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> screenFunction(CommonDropDownVO commonDropDownVO) {
		try {
			String query = "SELECT  r.SCREEN_FUNCTION_ID,FUNCTION_NAME,"
					+ " IFNULL(IF(SCREEN_FUNCTION_FK!=0,'success','fail'),'fail')Selecttion FROM "
					+ commonDatabaseSchema + ".mm_screen_function r" + " LEFT JOIN  (SELECT SCREEN_FUNCTION_FK  FROM "
					+ mmDatabaseSchema + ".user_screen_function_mapping" + " WHERE USER_FK="
					+ commonDropDownVO.getUserId() + " AND SUB_SCREEN_ID = " + commonDropDownVO.getId()
					+ ") a ON SCREEN_FUNCTION_FK=r.SCREEN_FUNCTION_ID" + " WHERE SUB_SCREEN_ID = "
					+ commonDropDownVO.getId() + " AND r.ACTIVE_FLAG = 1 GROUP BY r.SCREEN_FUNCTION_ID order by r.SCREEN_FUNCTION_ID";

			List<Object[]> list = (List<Object[]>) entityManager.createNativeQuery(query).getResultList();

			return list;
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();

		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> loadPlant(int id) {
		try {
			String query = "SELECT l.PLANT_ID,l.PLANT_CODE,l.PLANT_NAME from " + mmDatabaseSchema + ".plant l "
					+ " where l.DELETE_FLAG= '0' and l.SALES_ORG_ID =" + id + " order by l.DEFAULT_ID desc ";

			List<Object[]> list = (List<Object[]>) entityManager.createNativeQuery(query).getResultList();

			return list;
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();

		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> customer(CommonDropDownVO commonDropDownVO) {
		try {
			String query = "SELECT l.id,l.customerName,l.customerCode,l.parentCode from CustomerEntity l "
					+ " where l.deleteFlag= '0' and l.activeFlag ='" + CommonConstant.CONSTANT_ONE + "'";
			if (null != commonDropDownVO.getName()) {
				query = query + " and l.customerName ='" + commonDropDownVO.getName() + "'";
			}
			if (null != commonDropDownVO.getCode()) {
				query = query + " and l.customerCode ='" + commonDropDownVO.getCode() + "'";
			}
			query = query + " order by l.id desc ";

			List<Object[]> list = (List<Object[]>) entityManager.createQuery(query).getResultList();

			return list;
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();

		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> plantMapping(CommonDropDownVO commonDropDownVO) {
		try {
			String query = "SELECT  r.PLANT_ID,PLANT_NAME,PLANT_CODE,"
					+ " IFNULL(IF(PLANT_FK!=0,'success','fail'),'fail')Selecttion,a.DEFAULT_ID,a.USER_PLANT_MAPPING_PK FROM "
					+ mmDatabaseSchema + ".plant r"
					+ " LEFT JOIN  (SELECT PLANT_FK,DEFAULT_ID,USER_PLANT_MAPPING_PK  FROM " + mmDatabaseSchema
					+ ".user_plant_mapping" + " WHERE IS_ACTIVE = 1 AND USER_FK=" + commonDropDownVO.getUserId()
					+ " AND SALES_ORGANIZATION_FK = " + commonDropDownVO.getId() + ") a ON PLANT_FK=r.PLANT_ID"
					+ " WHERE SALES_ORG_ID = " + commonDropDownVO.getId() + " GROUP BY r.PLANT_ID order by r.PLANT_ID";

			List<Object[]> list = (List<Object[]>) entityManager.createNativeQuery(query).getResultList();

			return list;
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();

		}
		return null;
	}

	public PackTypeValidationEntity findCommonDropdownId(int id) {

		String query = "SELECT e.* from " + commonDatabaseSchema
				+ ".pack_type_map_with_sales_organisation e where e.PACK_TYPE_ID =" + id;

		PackTypeValidationEntity commonDropdown = (PackTypeValidationEntity) entityManager
				.createNativeQuery(query, PackTypeValidationEntity.class).getSingleResult();

		return commonDropdown;

	}

	@SuppressWarnings("unchecked")
	public List<Object[]> user(int id) {

		String query = "SELECT u.USER_ID,u.FIRST_NAME,u.LAST_NAME FROM " + mmDatabaseSchema + ".user_role_mapping urm"
				+ " LEFT JOIN " + commonDatabaseSchema + ".user u ON u.USER_ID = urm.USER_FK"
				+ " WHERE urm.IS_ACTIVE=1 AND urm.ROLE_FK =" + id;

		List<Object[]> objList = null;

		try {
			objList = (List<Object[]>) entityManager.createNativeQuery(query).getResultList();

		} catch (NoResultException e) {
			e.getMessage();
			e.printStackTrace();

		}

		return objList;

	}

}
