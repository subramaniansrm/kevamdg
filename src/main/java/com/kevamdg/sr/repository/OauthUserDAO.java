package com.kevamdg.sr.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kevamdg.sr.entity.UserEntity;



@Repository
public interface OauthUserDAO extends CrudRepository<UserEntity, Integer> {
	
    UserEntity findByuserName(String userLoginId);
    
    
  
    
}
