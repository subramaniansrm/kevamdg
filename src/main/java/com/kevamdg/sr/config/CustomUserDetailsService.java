package com.kevamdg.sr.config;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kevamdg.sr.auth.AuthUserInfo;
import com.kevamdg.sr.auth.KerberosUserInfo;
import com.kevamdg.sr.service.LoginService;
import com.kevamdg.sr.vo.Authorities;
import com.kevamdg.sr.vo.UserVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j // Lombok annotation for logging
@Service
public class CustomUserDetailsService implements UserDetailsService {
	

	@Autowired
	private LoginService userLoginService;
	@Autowired
	
	//private AuthoritiesService authoritiesService;
	static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException,AuthenticationException {
		System.out.println("----------Second Call User-----------" + userName);
		/*
		 * return new User(username, "notUsed", true, true, true, true,
		 * AuthorityUtils.createAuthorityList("ROLE_USER"));
		 */
		if (userName != null && !"".equals(userName)) {
			String user="";
			StringTokenizer st=new StringTokenizer(userName,"@");
			while (st.hasMoreElements()) {
				user=(String) st.nextElement();
				break;
			}
			System.out.println("User>>>>>>"+user);
			UserVO userMaster = userLoginService.getLogin(user);

			if (userMaster != null) {
				System.out.println("----------Second Call User First Name---------" + userMaster.getFirstName());
				//List<Authorities> authorities = authoritiesService.getAuthority(userName);
				AuthUserInfo authUserInfo = new AuthUserInfo();
				//authUserInfo.setUserMaster(userMaster);
				 UUID uuid = UUID.randomUUID();
				String token = uuid.toString();
				
				List<SimpleGrantedAuthority> authList = new ArrayList<>();
				//List<SimpleGrantedAuthority> authList = getAuthorities(authorities);
				//String encodedPassword ="";
				String encodedPassword = passwordEncoder.encode(userMaster.getPassword());
				//return new User(userName, "notUsed", true, true, true, true, AuthorityUtils.createAuthorityList("ROLE_USER"));
				return new KerberosUserInfo(userMaster.getFirstName(), encodedPassword, authList, userMaster);
			} else {
				throw new AuthenticationException("Authentication Failure"){
				};
			}
		}else{
			throw new AuthenticationException("Authentication Failure") {
			};
		}
		
	}
	
	 private List<SimpleGrantedAuthority> getAuthorities(List<Authorities> authorities) {
	        List<SimpleGrantedAuthority> authList = new ArrayList<SimpleGrantedAuthority>();
	        
	        for (Authorities authority : authorities) {
	        	authList.add(new SimpleGrantedAuthority(authority.getAuthority()));
			}
	        
	        return authList;
	    }	
}