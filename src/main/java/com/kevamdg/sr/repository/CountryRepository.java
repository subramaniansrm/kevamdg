package com.kevamdg.sr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kevamdg.sr.entity.CountryEntity;

/**
 * Location Repository
 * 
 * @author vigneshs
 *
 */

public interface CountryRepository extends JpaRepository<CountryEntity, Integer> {

	@Query(value = "select e from CountryEntity e where e.deleteFlag=0 order by e.id desc")
	List<CountryEntity> getAll();

}
