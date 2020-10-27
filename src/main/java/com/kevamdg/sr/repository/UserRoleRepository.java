package com.kevamdg.sr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kevamdg.sr.entity.UserRoleEntity;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Integer> {

	@Query(value = " SELECT ur.ROLE_ID,ur.USER_ROLE_NAME,ut.TYPE_OF_USER,ur.DESCRIPTION,"
			+ " ul.USER_LOCATION_NAME,us.SUBLOCATION_NAME,ud.USER_DEPARTMENT_NAME,"
			+ " ul.USER_LOCATION_ID,us.SUBLOCATION_ID,ud.USER_DEPARTMENT_ID "
			+ " FROM user_role ur LEFT JOIN user_type ut ON ut.USER_TYPE_ID = ur.ROLE_TYPE"
			+ " LEFT JOIN user_location ul ON ul.USER_LOCATION_ID = ur.USER_LOCATION_ID"
			+ " LEFT JOIN user_sublocation us ON us.SUBLOCATION_ID = ur.SUBLOCATION_ID"
			+ " LEFT JOIN user_department ud ON ud.USER_DEPARTMENT_ID = ur.USER_DEPARTMENT_ID"
			+ " WHERE ur.DELETE_FLAG = 0 "
			+ " ORDER BY ur.ROLE_ID desc", nativeQuery = true)
	public List<Object[]> getUserRoleList();

}
