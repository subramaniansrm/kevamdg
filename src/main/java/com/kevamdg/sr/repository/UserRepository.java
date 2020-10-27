package com.kevamdg.sr.repository;

import org.springframework.data.repository.CrudRepository;

import com.kevamdg.sr.entity.UserEntity;

/**
 * 
 * 
 * User Profile name
 * 
 * @author raathikaabm
 *
 */

public interface UserRepository extends CrudRepository<UserEntity, Integer> {

	
}
