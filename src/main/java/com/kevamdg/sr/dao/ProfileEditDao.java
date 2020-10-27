package com.kevamdg.sr.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.kevamdg.sr.constants.CommonConstant;
import com.kevamdg.sr.entity.PhoneBookEntity;
import com.kevamdg.sr.entity.UserEntity;
import com.kevamdg.sr.vo.AuthDetailsVO;
import com.kevamdg.sr.vo.UserVO;

@Repository
public class ProfileEditDao {

	Logger logger = LoggerFactory.getLogger(ProfileEditDao.class);

	@Autowired
	EntityManager entityManager;

	@Value("${commonDatabaseSchema}")
	private String commonDatabaseSchema;

	@Value("${mmDatabaseSchema}")
	private String mmDatabaseSchema;

	public UserEntity load(UserVO user) {

		String query = " SELECT u FROM UserEntity u" + " WHERE u.deleteFlag = '" + CommonConstant.CONSTANT_ZERO
				+ "' AND u.id = " + user.getId
				();

		UserEntity profile = (UserEntity) entityManager.createQuery(query).getSingleResult();

		return profile;

	}

	public PhoneBookEntity findId(int phoneBookId) {
		String query = "SELECT e FROM PhoneBookEntity e where deleteFlag = ' " + CommonConstant.CONSTANT_ZERO
				+ " ' AND phoneBookId = " + phoneBookId;
		PhoneBookEntity phoneBookEntity = (PhoneBookEntity) entityManager.createQuery(query).getSingleResult();
		return phoneBookEntity;
	}

	public PhoneBookEntity findByEmpId(String employeeId) {
		PhoneBookEntity phoneBookEntity = new PhoneBookEntity();
		List<PhoneBookEntity> listPhoneBook = new ArrayList<>();
		try {
			String query = "SELECT e FROM PhoneBookEntity e where e.deleteFlag = '" + CommonConstant.CONSTANT_ZERO
					+ "' AND e.phoneBookIsActive = '" + CommonConstant.CONSTANT_ONE + "' AND e.employeeId = '"
					+ employeeId + "'";

			listPhoneBook = (List<PhoneBookEntity>) entityManager.createQuery(query).getResultList();
			if (listPhoneBook != null && !listPhoneBook.isEmpty()) {
				phoneBookEntity = listPhoneBook.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {

		}

		return phoneBookEntity;
	}

	public UserEntity findUserById(int userId) {
		String query = "SELECT e FROM UserEntity e where deleteFlag = '" + CommonConstant.CONSTANT_ZERO + "' AND id = "
				+ userId;

		UserEntity userEntity = (UserEntity) entityManager.createQuery(query).getSingleResult();
		return userEntity;
	}

	public void create(UserEntity userEntity) {

		entityManager.merge(userEntity);

	}

	public void Update(PhoneBookEntity phoneBookEntity) {

		entityManager.persist(phoneBookEntity);

	}

}
