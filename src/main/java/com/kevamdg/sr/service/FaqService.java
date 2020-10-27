package com.kevamdg.sr.service;

import java.util.ArrayList;
import java.util.List;

import org.omg.CORBA.portable.ApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.kevamdg.sr.config.CommonException;
import com.kevamdg.sr.dao.FaqDao;
import com.kevamdg.sr.entity.FaqEntity;
import com.kevamdg.sr.vo.FaqVo;

/**
 * Service is used for the FAQ details
 * 
 * @author mohanapriyas
 *
 */
@Component
public class FaqService extends CommonService {

	Logger logger = LoggerFactory.getLogger(FaqService.class);

	@Autowired
	FaqDao faqDao;


	/**
	 * This method is to retrieve frequently asked question details from faq
	 * table
	 * 
	 * @return FaqVo
	 * @throws BusinessException
	 */
	@Transactional
	public List<FaqVo> getCommonFaq() throws CommonException {

		List<FaqVo> questionAnswersList = new ArrayList<FaqVo>();
		List<FaqEntity> faqRecords = null;
		try {
			faqRecords = faqDao.getCommonFaq();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(getMessage("errorMessage"));
		}
		for (FaqEntity faqEntity : faqRecords) {
			FaqVo faqVo = new FaqVo();
			BeanUtils.copyProperties(faqEntity, faqVo);
			questionAnswersList.add(faqVo);
		}
		return questionAnswersList;

	}

}
