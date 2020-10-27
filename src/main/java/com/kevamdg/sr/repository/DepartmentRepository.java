package com.kevamdg.sr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kevamdg.sr.entity.DepartmentEntity;


/**
 * Department Repository
 * @author raathikaabm
 *
 */
@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Integer> {

	
	@Query(value = " Select d from DepartmentEntity d where d.deleteFlag='0' order by d.departmentId desc")
	List<DepartmentEntity> getAll();

}
