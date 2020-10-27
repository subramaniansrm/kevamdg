package com.kevamdg.sr.service;

import javax.transaction.Transactional;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevamdg.sr.dao.LoginDao;
import com.kevamdg.sr.entity.UserEntity;
import com.kevamdg.sr.repository.UserRepository;
import com.kevamdg.sr.vo.UserVO;


/**
 * 
 * Method used to Login
 * 
 * @author raathikaabm
 *
 */

@Service
public class LoginService {

	Logger logger = LoggerFactory.getLogger(LoginService.class);

	@Autowired
	LoginDao userLoginDao;

	@Autowired
	private UserRepository userRepo;
	/**
	 * This method is to check valid login.
	 * 
	 * @param userName
	 *            UserMaster
	 * @return
	 */
	@Transactional
	public UserVO getLogin(String userName) {
		return userLoginDao.getLogin(userName);
	}
	
	@Transactional
	public void updateAccessToken(String accesstoken, String loginId) {

		UserEntity user = userLoginDao.getUser(loginId);
		
		//AuthInstance obj = AuthInstance.getInstance(user);
		
		String access = Base64.getEncoder().encodeToString(accesstoken.getBytes());
		
		user.setAccessToken(access);

		userRepo.save(user);

	}

}
