package com.kevamdg.sr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kevamdg.sr.entity.LocationEntity;

/**
 * Location Repository
 * 
 * @author raathikaabm
 *
 */

public interface LocationRepository extends JpaRepository<LocationEntity, Integer> {

	@Query(value = "select e from LocationEntity e where e.deleteFlag='0' order by e.id desc")
	List<LocationEntity> getLocation();

}
