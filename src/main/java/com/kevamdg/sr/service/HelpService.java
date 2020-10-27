package com.kevamdg.sr.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kevamdg.sr.dao.HelpDao;
import com.kevamdg.sr.vo.HelpVo;
 
/**
 * This Service class is used to  get help details from help table
 * 
 * @author Priya [SRM]
 */
@Component
public class HelpService {

	@Autowired
	HelpDao helpDao;

	/**
	 * This method is to retrive help help details from help table 
	 * @param helpRequest
	 * @return HelpForm
	 */
	@Transactional
	public List<HelpVo> getHelpVoList(HelpVo helpRequest ){
		return helpDao.getHelpVoList(helpRequest);
	}
}
