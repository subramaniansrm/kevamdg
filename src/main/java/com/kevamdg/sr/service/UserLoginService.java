package com.kevamdg.sr.service;

import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.kevamdg.sr.config.CommonException;
import com.kevamdg.sr.config.MailMessages;
import com.kevamdg.sr.constants.CodeSecurity;
import com.kevamdg.sr.constants.CommonConstant;
import com.kevamdg.sr.dao.UserLoginDao;
import com.kevamdg.sr.entity.UserEntity;
import com.kevamdg.sr.vo.LoginForm;
import com.kevamdg.sr.vo.UserMasterVo;
import com.sun.mail.util.MailSSLSocketFactory;

/**
 * This Service class is used for login ,change password and forget password
 * process.
 * 
 * @author Priya [SRM]
 */
@Component
public class UserLoginService  {

	Logger logger = LoggerFactory.getLogger(UserLoginService.class);

	@Autowired
	UserLoginDao userLoginDao;


	@Autowired
	private MailMessages mailMessages;

	/**
	 * This method is to check valid login.
	 * 
	 * @param userName
	 *            UserMaster
	 * @return
	 */
	@Transactional
	public UserMasterVo getLogin(String userName) {
		return userLoginDao.getLogin(userName);
	}

	/**
	 * This method is to used to check old password.
	 * 
	 * @param changePasswordRequest
	 * @return LoginForm
	 */
	public void getChangePassword(LoginForm changePasswordRequest) {
/*
		// Empty check of old password , new password , confirm password
		if ((null != changePasswordRequest.getOldPassword() && !changePasswordRequest.getOldPassword().equals(""))) {
			if ((null != changePasswordRequest.getNewPassword()
					&& !changePasswordRequest.getNewPassword().equals(""))) {
				if ((null != changePasswordRequest.getConfirmPassword()
						&& !changePasswordRequest.getConfirmPassword().equals(""))) {

					// Check the new password and confirm password same or not
					if (changePasswordRequest.getNewPassword().equals(changePasswordRequest.getConfirmPassword())) {

						UserEntity userEntity = null;

						// Get the user details of login user id to verify the
						// old
						// password match check
						boolean status = false;
						try {

							userEntity = userLoginDao.getOldPassword(changePasswordRequest);

						} catch (NoResultException e) {
							logger.error(e.getMessage());
							throw new CommonException("Data failure please contact administrator.");
						} catch (NonUniqueResultException e) {
							logger.error(e.getMessage());
							throw new CommonException("Data failure please contact administrator.");
						} catch (Exception e) {
							logger.error(e.getMessage());
							throw new CommonException("Data failure please contact administrator.");
						}

						if (userEntity != null) {

							// Old password encryption
							String oldPassword = null;
							try {
								oldPassword = CodeSecurity.encrypt(changePasswordRequest.getOldPassword());
							} catch (Exception e) {
								logger.error(e.getMessage());
								throw new CommonException("Password Encryption Failure . Please contact administrator.");
							}

							if (oldPassword.equals(userEntity.getPassword())) {

								// New password encryption
								String newPassword = null;
								try {
									newPassword = CodeSecurity.encrypt(changePasswordRequest.getNewPassword());
								} catch (Exception e) {
									logger.error(e.getMessage());
									throw new CommonException("Password Encryption Failure . Please contact administrator.");
								}
								changePasswordRequest.setNewPassword(newPassword);

								// Update the password in Data base
								try {

									status = userLoginDao.changePassword(changePasswordRequest);

								} catch (Exception e) {
									logger.error(e.getMessage());
									throw new CommonException("Data failure please contact administrator.");
								}

								if (status == false) {
									throw new CommonException("Data failure please contact administrator.");
								}

							} else {
								throw new CommonException("Old Password Mismatched");
							}

						} else {
							throw new CommonException("Data failure please contact administrator.");
						}
					} else {
						throw new CommonException("New password and Confirm password are not same");
					}
				}

				else {
					throw new CommonException("Please enter Confirm password");
				}
			} else {
				throw new CommonException("Please enter New password");
			}
		} else {
			throw new CommonException("Old Password Mismatched");
		}*/
		
		String newPassword = null;
		String newPasswordValidate = null;
		try {
		// Empty check of old password , new password , confirm password
		if ((null != changePasswordRequest.getOldPassword() && !changePasswordRequest.getOldPassword().equals(""))) {
			if ((null != changePasswordRequest.getNewPassword()
					&& !changePasswordRequest.getNewPassword().equals(""))) {
				if ((null != changePasswordRequest.getConfirmPassword()
						&& !changePasswordRequest.getConfirmPassword().equals(""))) {

					// Check the new password and confirm password same or not
					if (changePasswordRequest.getNewPassword().equals(changePasswordRequest.getConfirmPassword())) {

						UserEntity userEntity = null;

						// Get the user details of login user id to verify the
						// old
						// password match check
							boolean status = false;

							userEntity = userLoginDao.getOldPassword(changePasswordRequest);

							if (userEntity != null) {

								BCryptPasswordEncoder Oencoder = new BCryptPasswordEncoder();

							if (Oencoder.matches(changePasswordRequest.getOldPassword(),userEntity.getPassword())) {
								
									// New password encryption
									BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
									newPassword = passwordEncoder.encode(changePasswordRequest.getNewPassword());
									newPasswordValidate = changePasswordRequest.getNewPassword();

									changePasswordRequest.setNewPassword(newPassword);

									// Update the password in Data base

								status = userLoginDao.changePassword(changePasswordRequest);
								 
								if (status == false) {
									throw new CommonException("Data failure please contact administrator.");
								}

							} else {
								throw new CommonException("Old Password Mismatched");
							}

						} else {
							throw new CommonException("Data failure please contact administrator.");
						}
					} else {
						throw new CommonException("New password and Confirm password are not same");
					}
				}

				else {
					throw new CommonException("Confirm Password Mandatory");
				}
			} else {
				throw new CommonException("New Password Mandatory");
			}
		} else {
			throw new CommonException("Old Password Mandatory");
		}
		
		} catch (NoResultException e) {
			logger.error(e.getMessage());
			throw new CommonException(e.getMessage());
		} catch (NonUniqueResultException e) {
			logger.error(e.getMessage());
			throw new CommonException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw new CommonException(e.getMessage());
		}
		
		
		
	}

	public int forgotPassword(String loginId) throws CommonException, Exception{
		
		int count = userLoginDao.forgotPassword(loginId);
		
		return count;
		
	}
	
	/**
	 * This method is to used for forgot password.
	 * 
	 * @param forgotPasswordRequest
	 * @return LoginForm
	 * @throws Exception
	 */
	public void forgotPassword(LoginForm forgotPasswordRequest) {

		UserEntity userEntity = null;

		boolean status = false;

		String encryptedPassword = null;

		String genPassword = null;

		// To get the user details
		try {

			userEntity = userLoginDao.forgotPassword(forgotPasswordRequest);

		} catch (NoResultException e) {
			logger.error(e.getMessage());
			throw new CommonException("No Result found in Database.");
		} catch (NonUniqueResultException e) {
			logger.error(e.getMessage());
			throw new CommonException("Duplicate Result found.");
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException("Duplicate Result found.");
		}
		if (userEntity != null) {

			// New password generation
			try {

				genPassword = passwordGenerator();

			} catch (Exception e) {
				logger.error(e.getMessage());
				throw new CommonException("Generate password failed please contact administrator");
			}

			// New password mail send to user
			try {

				forgotpasswordMail(genPassword, userEntity.getEmailId());

			} catch (Exception e) {
				logger.error(e.getMessage());
				throw new CommonException("Mail sending failed please contact administrator");
			}

			// Encrypt the new password
			try {

				encryptedPassword = CodeSecurity.encrypt(genPassword);

			} catch (Exception e) {
				logger.error(e.getMessage());
				throw new CommonException("Password Encryption Failure . Please contact administrator.");
			}

			forgotPasswordRequest.setNewPassword(encryptedPassword);
			forgotPasswordRequest.setUserId(userEntity.getId());

			// Update the password in Data base
			try {

				status = userLoginDao.changePassword(forgotPasswordRequest);

			} catch (Exception e) {
				logger.error(e.getMessage());
				throw new CommonException("Data failure please contact administrator.");
			}

			if (status == false) {
				throw new CommonException("Data failure please contact administrator.");
			}
		}
	}

	/**
	 * This method is to used for password generation.
	 * 
	 * @return String
	 */
	public String passwordGenerator() {

		String values = CommonConstant.CAPITAL_CHARS + CommonConstant.SMALL_CHARS + CommonConstant.NUMBERS
				+ CommonConstant.SYMBOLS;
		Random rndm_method = new Random();
		char[] password = new char[CommonConstant.NEW_PASSWORD_LENGTH];

		for (int i = 0; i < CommonConstant.NEW_PASSWORD_LENGTH; i++) {
			password[i] = values.charAt(rndm_method.nextInt(values.length()));
		}
		return password.toString();
	}

	/**
	 * Method is used to send a mail of forget password
	 * 
	 * @param generatedPassword
	 * @param emailId
	 * @return
	 * @throws CommonException
	 * @throws Exception
	 */
	public LoginForm forgotpasswordMail(String generatedPassword, String emailId) throws CommonException, Exception {

		LoginForm loginForm = new LoginForm();
		Properties props = new Properties();
		props.put("mail.smtp.host", mailMessages.getSmtpHost());
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.starttls.enable", "true");

		MailSSLSocketFactory sf = new MailSSLSocketFactory();
		sf.setTrustAllHosts(true);
		props.put("mail.smtp.ssl.socketFactory", sf);
		Authenticator auth = new SMTPAuthenticator();
		// Session mailSession = Session.getInstance(props, auth);
		Session session = Session.getInstance(props, auth);

		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(mailMessages.getFromMailAddress()));
		if (null != emailId) {
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailId));
		}
		InternetAddress emailAddr = new InternetAddress(emailId);
		try {
			emailAddr.validate();
		} catch (AddressException ex) {
			throw new CommonException("Email Id is not valid");
			// ex.printStackTrace();
		}
		message.setSubject("Changed Password");
		message.setText(" Password generated is " + generatedPassword);
		message.saveChanges();

		Transport.send(message);

		return loginForm;
	}

	private class SMTPAuthenticator extends Authenticator {
		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(mailMessages.getSmtpUserName(), mailMessages.getSmtpPassword());
		}
	}

}
