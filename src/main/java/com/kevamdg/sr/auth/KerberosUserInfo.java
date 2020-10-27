package com.kevamdg.sr.auth;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.kevamdg.sr.vo.UserVO;

public class KerberosUserInfo extends User {

	private static final long serialVersionUID = 1L;

	private UserVO userMaster;

	public KerberosUserInfo(String username, String password, Collection<? extends GrantedAuthority> authorities,
			UserVO userMaster) {
		super(username, password, authorities);
		this.userMaster = userMaster;
	}

	
	

	

	/**
	 * @return the userMaster
	 */
	public UserVO getUserMaster() {
		return userMaster;
	}

	/**
	 * @param userMaster
	 *            the userMaster to set
	 */
	public void setUserMaster(UserVO userMaster) {
		this.userMaster = userMaster;
	}

}
