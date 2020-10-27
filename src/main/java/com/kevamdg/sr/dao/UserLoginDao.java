package com.kevamdg.sr.dao;
import java.util.List;

import javax.persistence.EntityManager;
/*import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;*/
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kevamdg.sr.config.CommonException;
import com.kevamdg.sr.constants.CodeSecurity;
import com.kevamdg.sr.constants.CommonConstant;
import com.kevamdg.sr.entity.UserEntity;
import com.kevamdg.sr.entity.UserRoleEntity;
import com.kevamdg.sr.entity.UserType;
import com.kevamdg.sr.vo.DepartmentVO;
import com.kevamdg.sr.vo.LoginForm;
import com.kevamdg.sr.vo.SubLocationVO;
import com.kevamdg.sr.vo.UserLocationVo;
import com.kevamdg.sr.vo.UserMasterVo;
import com.kevamdg.sr.vo.UserRoleVO;

/**
 * This dao class is used for login ,change password and forget password
 * process.
 * 
 * @author Priya [SRM]
 */
@Repository
public class UserLoginDao  {
	
	@Autowired
	EntityManager entityManager;
	
	/**
	 * This method is to check valid login.
	 * 
	 * @param userName
	 *            UserMaster
	 * @return
	 * @throws Exception
	 */
	public UserMasterVo getLogin(String userName) {

		@SuppressWarnings("unchecked")
		List<UserEntity> userList = (List<UserEntity>) entityManager
				.createQuery("SELECT e FROM UserEntity e where e.activeFlag = " + CommonConstant.FLAG_ONE + " and  e.userLoginId = :uname and e.deleteFlag = " + CommonConstant.FLAG_ZERO)
				.setParameter("uname", userName).getResultList();

		if (userList != null && userList.size() > 0) {
			UserMasterVo userMstr = new UserMasterVo();
			BeanUtils.copyProperties(userList.get(0), userMstr);

			String dycryptedPassword = null;
			try {
				dycryptedPassword = CodeSecurity.decrypt(userList.get(0).getPassword());
			} catch (Exception e) {
				e.printStackTrace();
			}
			userMstr.setPassword(dycryptedPassword.toString());

			if (null != userList.get(0).getRoleId()
					&& userList.get(0).getRoleId() != 0) {
				UserRoleVO roleMstr = new UserRoleVO();
				roleMstr.setId(userList.get(0).getRoleId());
				
				UserRoleEntity userRoleEntity = entityManager.find(UserRoleEntity.class, userList.get(0).getRoleId());
				if(null != userRoleEntity.getRoleType()){
					roleMstr.setRoleType(userRoleEntity.getRoleType());
				}
				

				if (null != roleMstr.getRoleType()) {
					UserType userTypeEntity = entityManager.find(UserType.class,
							roleMstr.getRoleType());
					userMstr.setUrl(userTypeEntity.getUrl());
				}

				userMstr.setUserRoleMaster(roleMstr);
			}
			if (null != userList.get(0).getLocationId()
					&& userList.get(0).getLocationId() != 0) {
				UserLocationVo locationMstr = new UserLocationVo();
				locationMstr.setId(userList.get(0).getLocationId());
				userMstr.setUserLocationMaster(locationMstr);
			}
			if (userList.get(0).getSublocationId() != 0) {
				SubLocationVO subLocationVo = new SubLocationVO();
				subLocationVo.setSublocationId(userList.get(0).getSublocationId());
				userMstr.setSubLocationMaster(subLocationVo);
			}
			if (null != userList.get(0).getDepartmentId()
					&& userList.get(0).getDepartmentId() != 0) {
				DepartmentVO departmentMstr = new DepartmentVO();
				departmentMstr.setDepartmentId(userList.get(0).getDepartmentId());
				userMstr.setUserDepartmentMaster(departmentMstr);
			}
			return userMstr;
		}
		return null;
	}
	/*public UserMasterVo getLogin(String userName) {

		@SuppressWarnings("unchecked")
		List<Object[]> userList = (List<Object[]>) getEntityCommon()
				.createQuery("SELECT e.id, e.userLoginId, e.password, ur.userRoleEntity.id, ud.userDepartmentEntity.id " //4
						+ " ,e.userLocationEntity.id , r.roleType, uc.userLocationEntity.id, sub.subLocationEntity.sublocationId"//7
						+ " FROM UserEntity e "
						+ " LEFT JOIN UserRoleMappingEntity ur ON ur.userEntity.id = e.id "
						+ " LEFT JOIN UserRoleEntity r ON r.id= ur.userRoleEntity.id "
						+ " LEFT JOIN UserDepartmentMappingEntity ud ON ud.userEntity.id = e.id "
						+ " LEFT JOIN UserCdcMappingEntity uc ON uc.userEntity.id = e.id "
						+ " LEFT JOIN UserSubLocationMappingEntity sub on sub.userEntity.id = e.id "
						+ " where e.activeFlag= '1' and uc.active = '0' and uc.default1 = '1' and  e.userLoginId = '"+userName+"'"
						+ " and ur.systemApplicationEntity.sysAppId = " +userConstants.getSysAppId()
						+ " and ud.systemApplicationEntity.sysAppId = " +userConstants.getSysAppId()
						+ " and uc.sysAppEntity.sysAppId = " +userConstants.getSysAppId()
						+ " and e.deleteFlag = "+CommonConstant.CONSTANT_ZERO
						+ " and r.deleteFlag = "+CommonConstant.CONSTANT_ZERO
						+ " and uc.deleteFlag = "+CommonConstant.CONSTANT_ZERO
						+ " and sub.active = "+CommonConstant.CONSTANT_ZERO)
				.getResultList();

		if (userList != null && userList.size() > 0) {
			
			for (Object[] object : userList) {
				
				UserMasterVo userMstr = new UserMasterVo();
				BeanUtils.copyProperties(userList.get(0), userMstr);
				
				userMstr.setId((Integer) (((Object[]) object)[0]));
				userMstr.setUserLoginId((String) (((Object[]) object)[1]));
				
				String dycryptedPassword = null;
				try {
					dycryptedPassword = CodeSecurity.decrypt((String) (((Object[]) object)[2]));
				} catch (Exception e) {
					e.printStackTrace();
				}
				userMstr.setPassword(dycryptedPassword.toString());
				
				if (null != (Integer) (((Object[]) object)[3])
						&& (Integer) (((Object[]) object)[3]) != 0) {
					UserRoleVo roleMstr = new UserRoleVo();
					roleMstr.setId((Integer) (((Object[]) object)[3]));
					roleMstr.setRoleType((Integer) (((Object[]) object)[6]));
					if (null != roleMstr.getRoleType()) {
						UserTypeEntity userTypeEntity = getEntityCommon().find(UserTypeEntity.class,
								roleMstr.getRoleType());
						userMstr.setUrl(userTypeEntity.getUrl());
					}
					userMstr.setUserRoleMaster(roleMstr);
				}
				if (null != (Integer) (((Object[]) object)[7])
						&& (Integer) (((Object[]) object)[7]) != 0) {
					UserLocationVo locationMstr = new UserLocationVo();
					locationMstr.setId( (Integer) (((Object[]) object)[7]));
					userMstr.setUserLocationMaster(locationMstr);
				}
				if (null != (Integer) (((Object[]) object)[4])
						&& (Integer) (((Object[]) object)[4]) != 0) {
					DepartmentVo departmentMstr = new DepartmentVo();
					departmentMstr.setId((Integer) (((Object[]) object)[4]));
					userMstr.setUserDepartmentMaster(departmentMstr);
				}
				if (null != (Integer) (((Object[]) object)[8])
						&& (Integer) (((Object[]) object)[8]) != 0) {
					SubLocationVo subLocationVo = new SubLocationVo();
					subLocationVo.setSublocationId((Integer) (((Object[]) object)[8]));
					userMstr.setSubLocationMaster(subLocationVo);
				}
				return userMstr;
			}
		}
		return null;
	}
*/

	/**
	 * This method is to used to check old password.
	 * 
	 * @param changePasswordRequest
	 * @return LoginForm
	 */
	public UserEntity getOldPassword(LoginForm changePasswordRequest) {

		UserEntity userList = (UserEntity) entityManager.createQuery("SELECT e FROM UserEntity e where e.id=:id")
				.setParameter("id", changePasswordRequest.getUserId()).getSingleResult();

		return userList;

	}

	/**
	 * This method is to used for change password.
	 * 
	 * @param changePasswordRequest
	 * @return LoginForm
	 */
	@Transactional
	public boolean changePassword(LoginForm passwordRequest) {

		int updateRow = entityManager.createQuery("UPDATE UserEntity set password =:newPassword where  id=:userId")
				.setParameter("userId", passwordRequest.getUserId())
				.setParameter("newPassword", passwordRequest.getNewPassword()).executeUpdate();

		if (updateRow > 0) {
			return true;

		} else {

			return false;
		}

	}

	/**
	 * This method is to used for forgot password process.
	 * 
	 * @param forgotPasswordRequest
	 * @return LoginForm
	 * @throws Exception
	 */
	@Transactional
	public UserEntity forgotPassword(LoginForm forgotPasswordRequest) throws CommonException, Exception {

		UserEntity userList = (UserEntity) entityManager
				.createQuery("SELECT e FROM UserEntity e where e.userLoginId=:userName")
				.setParameter("userName", forgotPasswordRequest.getUserLoginId()).getSingleResult();

		return userList;
	}
	
	
	public int forgotPassword(String loginId) throws CommonException, Exception {
		
		String query = "Select Count(e.userLoginId) from UserEntity e where e.userLoginId =" + loginId;

		int count = (int)(long) entityManager.createQuery(query).getSingleResult();

		return count;
	}

}
