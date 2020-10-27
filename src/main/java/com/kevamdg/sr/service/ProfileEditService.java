package com.kevamdg.sr.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.kevamdg.sr.config.CommonException;
import com.kevamdg.sr.config.PicturePath;
import com.kevamdg.sr.constants.CommonConstant;
import com.kevamdg.sr.controller.CommonAction;
import com.kevamdg.sr.dao.ProfileEditDao;
import com.kevamdg.sr.entity.DepartmentEntity;
import com.kevamdg.sr.entity.LocationEntity;
import com.kevamdg.sr.entity.PhoneBookEntity;
import com.kevamdg.sr.entity.SubLocationEntity;
import com.kevamdg.sr.entity.UserEntity;
import com.kevamdg.sr.repository.DepartmentRepository;
import com.kevamdg.sr.repository.LocationRepository;
import com.kevamdg.sr.repository.SubLocationRepository;
import com.kevamdg.sr.vo.AuthDetailsVO;
import com.kevamdg.sr.vo.ProfileEditVo;
import com.kevamdg.sr.vo.UserVO;

@Service
public class ProfileEditService extends CommonAction<ProfileEditVo>{

	Logger logger = LoggerFactory.getLogger(ProfileEditService.class);

	@Autowired
	private ProfileEditDao profileEditDao;

	@Autowired
	private PicturePath picturePath;
	
	@Autowired
	LocationRepository locationRepository;
	
	@Autowired
	private SubLocationRepository subLocationRepository;
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	/**
	 * Method is used to Load the Phone Booking Details
	 * 
	 * @param phoneBook
	 *            PhoneBookVo
	 * @return phoneBookVo List<PhoneBookVo>
	 */
	@Transactional
	public ProfileEditVo load(UserVO user) {
		try {
			ProfileEditVo profileEditVo = new ProfileEditVo();
			UserEntity userEntity = profileEditDao.load(user);
			profileEditVo = getAllList(userEntity);
			return profileEditVo;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("dataFailure"));
		}

	}

	/**
	 * Method is to set the parameters in the phone booking
	 * 
	 * @param object
	 *            Object[]
	 * @return ProfileEditVoList List<ProfileEditVo>
	 */
	private ProfileEditVo getAllList(UserEntity userEntity) {

		ProfileEditVo profileEditVo = new ProfileEditVo();

		if (0 != userEntity.getId()) {
			profileEditVo.setId(userEntity.getId());
		}
		if (null != userEntity.getUserEmployeeId()) {
			profileEditVo.setEmployeeId(userEntity.getUserEmployeeId());
		}
		if (null != userEntity.getFirstName()) {
			profileEditVo.setFirstName(userEntity.getFirstName());
		}
		if (null != userEntity.getMiddleName()) {
			profileEditVo.setMiddleName(userEntity.getMiddleName());
		}
		if (null != userEntity.getLastName()) {
			profileEditVo.setLastName(userEntity.getLastName());
		}
		if (null != userEntity.getSkypeId()) {
			profileEditVo.setSkypeId(userEntity.getSkypeId());
		}
		if (null != userEntity.getEmailId()) {
			profileEditVo.setEmailId(userEntity.getEmailId());
		}
		if (null != userEntity.getMobile()) {
			profileEditVo.setMobile(userEntity.getMobile());
		}
		if (null != userEntity.getCurrentAddress()) {
			profileEditVo.setCurrentAddress(userEntity.getCurrentAddress());
		}
		if (null != userEntity.getPermanentAddress()) {
			profileEditVo.setPermanentAddress(userEntity.getPermanentAddress());
		}
		LocationEntity loction = new LocationEntity();
		if (null != userEntity.getLocationId()) {
			profileEditVo.setLocationId(userEntity.getLocationId());
			
			 loction = locationRepository.findOne(userEntity.getLocationId());
		}
		if (null != loction.getUserLocationName()) {
			profileEditVo.setLocationName(loction.getUserLocationName());
		}
		SubLocationEntity subLocation = new SubLocationEntity();
		if (null != userEntity.getSublocationId()) {
			profileEditVo.setSublocationId(userEntity.getSublocationId());
			subLocation = subLocationRepository.findOne(userEntity.getSublocationId());
		}
		if (null != subLocation.getSubLocationName()) {
			profileEditVo.setSubLocationName(subLocation.getSubLocationName());
		}
		DepartmentEntity department = new DepartmentEntity();
		if (null != userEntity.getDepartmentId()) {
			profileEditVo.setDepartmentId(userEntity.getDepartmentId());
			department = departmentRepository.findOne(userEntity.getDepartmentId());
		}
		if (null != department.getDepartmentName()) {
			profileEditVo.setDepartmentName(department.getDepartmentName());
		}
		if (null != userEntity.getUserProfile()) {

			/*StringBuffer modifiedQuery = new StringBuffer(picturePath.getUserDownloadPath());
			File file = new File(userEntity.getUserProfile());
			modifiedQuery.append(file.getName());
			profileEditVo.setPhoneBookProfile(modifiedQuery.toString());*/

			try {
				profileEditVo.setImageLoad(imageLoading(userEntity.getUserProfile()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {

			try {
				profileEditVo.setImageLoad(imageLoading(picturePath.getDefaultImagePath()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			/*StringBuffer modifiedQuery = new StringBuffer(picturePath.getDefaultProfilePath());

			profileEditVo.setPhoneBookProfile(modifiedQuery.toString());*/

		}

		return profileEditVo;
	}

	@Transactional
	public UserEntity saveAttachment(UserEntity userEntity, MultipartFile[] uploadingFiles) {

		try {
			for (MultipartFile uploadedFile : uploadingFiles) {

				String fileName = dateAppend(uploadedFile);
				String path = picturePath.getUserFilePath();
				File fileToCreate = new File(path);
				if (fileToCreate.exists()) {
					path = path + CommonConstant.SLASH;
					if (!fileToCreate.exists()) {
						fileToCreate.mkdir();
					}
				} else {
					fileToCreate.mkdir();
					path = path + CommonConstant.SLASH;
					fileToCreate = new File(path);
					fileToCreate.mkdir();
				}
				fileToCreate = new File(path + fileName);
				// uploadedFile.getOriginalFilename().replace(uploadedFile.getOriginalFilename(),
				// fileName);
				uploadedFile.transferTo(fileToCreate);
				path = path + fileName;
				userEntity.setUserProfile(path);

			}
		} catch (JsonParseException e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("dataFailure"));
		} catch (JsonMappingException e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("dataFailure"));
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("dataFailure"));
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("dataFailure"));
		}
		return userEntity;
	}

	/**
	 * Method used to append Date for Uploaded File
	 * 
	 * @param uploadedFile
	 * @return
	 */
	public String dateAppend(MultipartFile uploadedFile) {
		String fileName = uploadedFile.getOriginalFilename().substring(0,
				uploadedFile.getOriginalFilename().lastIndexOf("."));
		String date = CommonConstant.formatDatetoString(new Date(), CommonConstant.FILE_NAME_FORMAT_DATE);
		fileName = fileName + date;
		String format = "." + getfileFormat(uploadedFile.getOriginalFilename());
		fileName = fileName + format;
		return fileName;

	}

	/**
	 * Method used to Get the File Format
	 * 
	 * @param attachmentFileName
	 * @return
	 */
	public static String getfileFormat(String attachmentFileName) {
		try {
			return attachmentFileName.substring(attachmentFileName.lastIndexOf(".") + 1);
		} catch (Exception e) {
			return "";
		}
	}

	@Transactional
	public void updateAttachment(ProfileEditVo profileEditVo, MultipartFile[] uploadingFiles,
			AuthDetailsVO authDetailsVo) {
		// TODO Auto-generated method stub
		UserEntity userEntity = new UserEntity();

		userEntity = profileEditDao.findUserById(profileEditVo.getId());

		if (null != profileEditVo.getEmployeeId()) {
			userEntity.setUserEmployeeId(profileEditVo.getEmployeeId());
		}
		if (null != profileEditVo.getFirstName()) {
			userEntity.setFirstName(profileEditVo.getFirstName());
		}
		if (null != profileEditVo.getMiddleName()) {
			userEntity.setMiddleName(profileEditVo.getMiddleName());
		}
		if (null != profileEditVo.getLastName()) {
			userEntity.setLastName(profileEditVo.getLastName());
		}
		if (null != profileEditVo.getMobile()) {
			userEntity.setMobile(profileEditVo.getMobile());
		}
		if (null != profileEditVo.getCurrentAddress()) {
			userEntity.setCurrentAddress(profileEditVo.getCurrentAddress());
		}
		if (null != profileEditVo.getPermanentAddress()) {
			userEntity.setPermanentAddress(profileEditVo.getPermanentAddress());
		}
		if (null != profileEditVo.getEmailId()) {
			userEntity.setEmailId(profileEditVo.getEmailId());
		}
		if (null != profileEditVo.getSkypeId()) {
			userEntity.setSkypeId(profileEditVo.getSkypeId());
		}
		if (null != profileEditVo.getSkypeId()) {
			userEntity.setSkypeId(profileEditVo.getSkypeId());
		}
		if (uploadingFiles != null) {
			userEntity = saveAttachment(userEntity, uploadingFiles);
		}

		if (0 != profileEditVo.getLocationId()) {

			userEntity.setLocationId(profileEditVo.getLocationId());

		}
		if (0 != profileEditVo.getSublocationId()) {

			userEntity.setSublocationId(profileEditVo.getSublocationId());

		}
		if (0 != profileEditVo.getDepartmentId()) {
			userEntity.setDepartmentId(profileEditVo.getDepartmentId());

		}
		userEntity = setUpdateUserDetails(userEntity, authDetailsVo);

		profileEditDao.create(userEntity);

		PhoneBookEntity phoneBookEntity = new PhoneBookEntity();

		phoneBookEntity = profileEditDao.findByEmpId(profileEditVo.getEmployeeId());

		if (null != phoneBookEntity) {
			if (null != profileEditVo.getEmployeeId()) {
				phoneBookEntity.setEmployeeId(profileEditVo.getEmployeeId());
			}
			if (null != profileEditVo.getFirstName()) {

				if (null != profileEditVo.getLastName()) {
					phoneBookEntity
							.setEmployeeName(profileEditVo.getFirstName().concat(" " + profileEditVo.getLastName()));
				} else {
					phoneBookEntity.setEmployeeName(profileEditVo.getFirstName());
				}
			}
			if (null != profileEditVo.getMobile()) {
				phoneBookEntity.setPhoneNumber(profileEditVo.getMobile());
			}
			if (null != profileEditVo.getEmailId()) {
				phoneBookEntity.setEmailId(profileEditVo.getEmailId());
			}
			if (null != profileEditVo.getSkypeId()) {
				phoneBookEntity.setSkypeId(profileEditVo.getSkypeId());
			}
			if (userEntity.getUserProfile() != null) {
				phoneBookEntity.setPhoneBookProfile(userEntity.getUserProfile());
			}
			if (0 != profileEditVo.getLocationId()) {
				phoneBookEntity.setUserLocationId(profileEditVo.getLocationId());
			}
			if (0 != profileEditVo.getSublocationId()) {
				phoneBookEntity.setSublocationId(profileEditVo.getSublocationId());
			}
			if (0 != profileEditVo.getDepartmentId()) {
				phoneBookEntity.setUserDepartmentId(profileEditVo.getDepartmentId());
			}
			phoneBookEntity = setUpdateUserDetails(phoneBookEntity, authDetailsVo);

			profileEditDao.Update(phoneBookEntity);

		}

	}

	private UserEntity setUpdateUserDetails(UserEntity userEntity, AuthDetailsVO authDetailsVo) {

		userEntity.setUpdateBy(authDetailsVo.getUserId());
		userEntity.setUpdateDate(CommonConstant.getCalenderDate());

		return userEntity;
	}

	private PhoneBookEntity setUpdateUserDetails(PhoneBookEntity phoneBookEntity, AuthDetailsVO authDetailsVo) {

		phoneBookEntity.setUpdateBy(authDetailsVo.getUserId());
		phoneBookEntity.setUpdateDate(CommonConstant.getCalenderDate());

		return phoneBookEntity;
	}

	public byte[] imageLoading(String fileName) throws IOException {
		BufferedImage originalImage;
		byte[] imageInByte;
		originalImage = ImageIO.read(new File(fileName));
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(originalImage, getMediaType1(fileName) , baos);
		baos.flush();
		imageInByte = baos.toByteArray();
		return imageInByte;
	}
	public String getMediaType1(String filename) {
		String arr[] = filename.split("\\.");
		String type = arr[arr.length - CommonConstant.CONSTANT_ONE];
		return type;
	}
	
}
