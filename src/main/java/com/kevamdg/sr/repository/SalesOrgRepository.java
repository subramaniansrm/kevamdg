package com.kevamdg.sr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.kevamdg.sr.entity.SalesOrgEntity;

/**
 *   
 * @author vigneshs
 *
 */

public interface SalesOrgRepository extends CrudRepository<SalesOrgEntity, Integer> {
	
	@Query(value = "select e from SalesOrgEntity e where e.deleteFlag=0 order by e.id desc")
	List<SalesOrgEntity> getAll();

	
}
