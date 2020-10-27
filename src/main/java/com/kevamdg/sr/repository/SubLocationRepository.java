package com.kevamdg.sr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kevamdg.sr.entity.SubLocationEntity;

public interface SubLocationRepository extends JpaRepository<SubLocationEntity, Integer> {

	@Query(value = "SELECT sub.SUBLOCATION_CODE,sub.SUBLOCATION_NAME,sub.SUBLOCATION_ISACTIVE, loca.USER_LOCATION_NAME "
			+ " , sub.SUBLOCATION_ID,sub.LOCATION_ID "
			+ " FROM common_mdg.user_sublocation sub JOIN common_mdg.user_location loca "
			+ " ON loca.USER_LOCATION_ID = sub.LOCATION_ID"
			+ " WHERE loca.DELETE_FLAG = '0' AND sub.DELETE_FLAG = '0'"
			+ " ORDER BY sub.SUBLOCATION_ID DESC ", nativeQuery = true)
	public List<Object[]> getAll();

}
