package com.kevamdg.sr.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.kevamdg.sr.constants.CommonConstant;
import com.kevamdg.sr.entity.CustomerEntity;
import com.kevamdg.sr.entity.SalesOrgEntity;
import com.kevamdg.sr.vo.CustomerVO;
import com.kevamdg.sr.vo.SalesOrgVO;

@Repository
public class CustomerDAO {

	@Autowired
	EntityManager entityManager;

	@Value("${commonDatabaseSchema}")
	private String commonDatabaseSchema;

	@Value("${mmDatabaseSchema}")
	private String mmDatabaseSchema;

	public List<Object[]> getAll() {

		String query = "select e.CUSTOMER_ID,e.CUSTOMER_CODE,e.CUSTOMER_NAME,e.AREA_NAGAR"
				+ " ,e.PARENT_CODE,e.PARENT_NAME,e.ACTIVE_FLAG,Concat(so.SALES_ORGANIZATIN_CODE,' - ',so.SALES_ORGANIZATION_NAME)"
				+ " ,concat(cd.COMMON_DROP_DOWN_CODE,' - ',cd.COMMON_DROP_DOWN_NAME),concat(p.PLANT_CODE,' - ',p.PLANT_NAME),ci.CITY_NAME"
				+ " from customer e" + " left join " + commonDatabaseSchema
				+ ".sales_organization so on so.SALES_ORGANIZATION_ID = e.SALES_ORG_ID" + " left join "
				+ commonDatabaseSchema + ".city ci on ci.CITY_ID = e.CITY_ID" + " left join " + commonDatabaseSchema
				+ ".common_drop_down cd on cd.COMMON_DROP_DOWN_ID = e.DISTRIBUTION_ID" + " left join "
				+ mmDatabaseSchema + ".plant p on p.PLANT_ID = e.PLANT_ID"
				+ " where e.DELETE_FLAG=0 order by e.CUSTOMER_ID desc ";

		List<Object[]> list = (List<Object[]>) entityManager.createNativeQuery(query).getResultList();

		return list;

	}

	@SuppressWarnings("unchecked")
	public List<Object[]> search(CustomerVO customer) {

		String query = "select e.CUSTOMER_ID,e.CUSTOMER_CODE,e.CUSTOMER_NAME,e.AREA_NAGAR"
				+ " ,e.PARENT_CODE,e.PARENT_NAME,e.ACTIVE_FLAG,Concat(so.SALES_ORGANIZATIN_CODE,' - ',so.SALES_ORGANIZATION_NAME)"
				+ " ,concat(cd.COMMON_DROP_DOWN_CODE,' - ',cd.COMMON_DROP_DOWN_NAME),concat(p.PLANT_CODE,' - ',p.PLANT_NAME),ci.CITY_NAME"
				+ " from customer e" + " left join " + commonDatabaseSchema
				+ ".sales_organization so on so.SALES_ORGANIZATION_ID = e.SALES_ORG_ID" + " left join "
				+ commonDatabaseSchema + ".city ci on ci.CITY_ID = e.CITY_ID" + " left join " + commonDatabaseSchema
				+ ".common_drop_down cd on cd.COMMON_DROP_DOWN_ID = e.DISTRIBUTION_ID" + " left join "
				+ mmDatabaseSchema + ".plant p on p.PLANT_ID = e.PLANT_ID" + " where e.DELETE_FLAG=0 ";

		if (null != customer.getCustomerName()) {
			query = query + " and LOWER(e.CUSTOMER_NAME) LIKE LOWER('%" + customer.getCustomerName() + "%')";
		}
		if (null != customer.getCustomerCode()) {
			query = query + " and LOWER(e.CUSTOMER_CODE) LIKE LOWER('%" + customer.getCustomerCode() + "%')";
		}
		if (null != customer.getAreaNagar()) {
			query = query + " and LOWER(e.AREA_NAGAR) LIKE LOWER('%" + customer.getAreaNagar() + "%')";
		}
		if (null != customer.getParentCode()) {
			query = query + " and LOWER(e.PARENT_CODE) LIKE LOWER('%" + customer.getParentCode() + "%')";
		}
		if (null != customer.getParentName()) {
			query = query + " and LOWER(e.PARENT_NAME) LIKE LOWER('%" + customer.getParentName() + "%')";
		}
		if (null != customer.getSalesOrgName()) {
			query = query
					+ " and LOWER(Concat(so.SALES_ORGANIZATIN_CODE,' - ',so.SALES_ORGANIZATION_NAME)) LIKE LOWER('%"
					+ customer.getSalesOrgName() + "%')";
		}
		if (null != customer.getDistributionName()) {
			query = query + " and LOWER(concat(cd.COMMON_DROP_DOWN_CODE,' - ',cd.COMMON_DROP_DOWN_NAME)) LIKE LOWER('%"
					+ customer.getDistributionName() + "%')";
		}
		if (null != customer.getPlantName()) {
			query = query + " and LOWER(concat(p.PLANT_CODE,' - ',p.PLANT_NAME)) LIKE LOWER('%"
					+ customer.getPlantName() + "%')";
		}
		if (customer.getStatus() != null) {
			if (customer.getStatus().equals(CommonConstant.Active)) {
				query = query + " AND e.ACTIVE_FLAG =" + CommonConstant.ACTIVE;
			} else {
				query = query + " AND e.ACTIVE_FLAG =" + CommonConstant.CONSTANT_ZERO;
			}
		}

		query = query + " order by e.CUSTOMER_ID desc";

		List<Object[]> list = (List<Object[]>) entityManager.createNativeQuery(query).getResultList();

		return list;
	}

	public int duplicate(CustomerVO customer) {
		int count = 0;

		String query = "SELECT COUNT(id) FROM CustomerEntity " + " where deleteFlag = " + CommonConstant.CONSTANT_ZERO
				+ " AND LOWER(customerName) = LOWER('" + customer.getCustomerName().trim() + "')"
				+ " AND LOWER(customerCode) = LOWER('" + customer.getCustomerCode().trim() + "')";
		StringBuffer modifiedQuery = new StringBuffer(query);
		if (null != customer.getId()) {
			modifiedQuery.append(" AND id != " + customer.getId());
		}

		count = (int) (long) entityManager.createQuery(modifiedQuery.toString()).getSingleResult();

		return count;

	}

	public Object[] view(int id) {

		String query = "select e.CUSTOMER_ID,e.CUSTOMER_CODE,e.CUSTOMER_NAME,e.AREA_NAGAR"
				+ " ,e.PARENT_CODE,e.PARENT_NAME,e.ACTIVE_FLAG,Concat(so.SALES_ORGANIZATIN_CODE,' - ',so.SALES_ORGANIZATION_NAME)"
				+ " ,concat(cd.COMMON_DROP_DOWN_CODE,' - ',cd.COMMON_DROP_DOWN_NAME),concat(p.PLANT_CODE,' - ',p.PLANT_NAME),ci.CITY_NAME"
				+ " ,e.SALES_ORG_ID,e.PLANT_ID,e.DISTRIBUTION_ID,e.CITY_ID"
				+ " from customer e" + " left join " + commonDatabaseSchema
				+ ".sales_organization so on so.SALES_ORGANIZATION_ID = e.SALES_ORG_ID" + " left join "
				+ commonDatabaseSchema + ".city ci on ci.CITY_ID = e.CITY_ID" + " left join " + commonDatabaseSchema
				+ ".common_drop_down cd on cd.COMMON_DROP_DOWN_ID = e.DISTRIBUTION_ID" + " left join "
				+ mmDatabaseSchema + ".plant p on p.PLANT_ID = e.PLANT_ID"
				+ " where e.CUSTOMER_ID = " + id;

		Object[] list = (Object[]) entityManager.createNativeQuery(query).getSingleResult();

		return list;

	}
}
