package com.kevamdg.sr.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kevamdg.sr.config.CustomUserDetails;
import com.kevamdg.sr.entity.UserEntity;
import com.kevamdg.sr.repository.OauthUserDAO;


@Service(value = "userService")
public class OauthUserServiceImpl implements UserDetailsService {

	@Autowired
	private OauthUserDAO userDao;

	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		UserEntity user = userDao.findByuserName(userId);
		
		
		CustomUserDetails customUserDetails = new CustomUserDetails(user.getUserName());
		customUserDetails.setAuthentication(true);
		customUserDetails.setPassword(user.getPassword());
		if(user.getUserName()!=null) {
			customUserDetails.setUserId(user.getId());
		}
		customUserDetails.setFirstName(user.getFirstName());
		customUserDetails.setLastName(user.getLastName());
		customUserDetails.setRoleId(user.getRoleId());
		return customUserDetails;
	}

	
	public List<UserEntity> findAll() {
		List<UserEntity> list = new ArrayList<>();
		userDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

}
