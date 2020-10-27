package com.kevamdg.sr.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kevamdg.sr.constants.CodeSecurity;
import com.kevamdg.sr.constants.CommonConstant;
import com.kevamdg.sr.entity.UserEntity;
import com.kevamdg.sr.vo.UserVO;

@Repository
public class LoginDao {

	@Autowired
	private EntityManager manager;

	/*
	 * @Autowired private UserConstants userConstants;
	 */

	/**
	 * This method is to check valid login.
	 * 
	 * @param userName
	 *            UserMaster
	 * @return
	 * @throws Exception
	 */
	public UserVO getLogin(String userName) {

		@SuppressWarnings("unchecked")
		List<UserEntity> userList = (List<UserEntity>) manager
				.createQuery("SELECT e FROM UserEntity e where e.activeFlag = " + CommonConstant.FLAG_ONE
						+ " and  e.userLoginId = :uname and e.deleteFlag = " + CommonConstant.FLAG_ZERO)
				.setParameter("uname", userName).getResultList();

		if (userList != null && userList.size() > 0) {
			UserVO userMstr = new UserVO();
			BeanUtils.copyProperties(userList.get(0), userMstr);

			String dycryptedPassword = null;
			try {
				dycryptedPassword = CodeSecurity.decrypt(userList.get(0).getPassword());
			} catch (Exception e) {
				e.printStackTrace();
			}
			userMstr.setPassword(dycryptedPassword.toString());

			return userMstr;
		}
		return null;
	}

	public UserEntity getUser(String userName) {

		String query = "SELECT e FROM UserEntity e where e.activeFlag = " + CommonConstant.FLAG_ONE
				+ " and  e.userName = '" + userName + "' and e.deleteFlag = " + CommonConstant.FLAG_ZERO;

		UserEntity userList = (UserEntity) manager.createQuery(query).getSingleResult();

		return userList;
	}

}
