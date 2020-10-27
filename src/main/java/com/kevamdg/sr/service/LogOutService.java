package com.kevamdg.sr.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevamdg.sr.entity.UserEntity;
import com.kevamdg.sr.repository.UserRepository;
import com.kevamdg.sr.vo.UserVO;

@Service
public class LogOutService {

	Logger logger = LoggerFactory.getLogger(LogOutService.class);

	@Autowired
	UserRepository userRepository;

	

	@Transactional
	public void logout(UserVO userMasterVO) {
		UserEntity user = null;
		try{
		 user = userRepository.findOne(userMasterVO.getId());
		}catch(Exception e){
			e.getMessage();
			e.printStackTrace();
		}
		if(user!=null){
			user.setAccessToken(null);
		}
		userRepository.save(user);
	}

}
