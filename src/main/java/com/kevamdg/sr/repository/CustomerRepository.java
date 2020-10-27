package com.kevamdg.sr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.kevamdg.sr.entity.CustomerEntity;

/**
 *   
 * @author vigneshs
 *
 */

public interface CustomerRepository extends CrudRepository<CustomerEntity, Integer> {

	@Query(value = "select e from CustomerEntity e where e.deleteFlag=0 order by e.id desc")
	List<CustomerEntity> getAll();
	
}
