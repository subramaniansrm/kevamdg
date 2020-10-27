package com.kevamdg.sr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.kevamdg.sr.entity.CommonDropdownEntity;

/**
 *   
 * @author vigneshs
 *
 */

public interface CommonDropdownRepository extends CrudRepository<CommonDropdownEntity, Integer> {
	
	@Query(value = "select e.id,e.dropdownCode,e.dropdownName,e.activeFlag,sf.screenFieldName "
			+ " ,CONCAT(so.salesOrgCode,' ',so.salesOrgName) from CommonDropdownEntity e,"
			+ " CommonScreenFieldEntity sf,SalesOrgEntity so where "
				+ " e.fieldId=sf.id AND so.id = e.salesOrgId AND e.deleteFlag=0  order by e.id desc")
	List<Object[]> getAll();

	
}
