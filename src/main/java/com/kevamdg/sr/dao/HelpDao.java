package com.kevamdg.sr.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kevamdg.sr.entity.HelpEntity;
import com.kevamdg.sr.vo.HelpVo;


/**
 * This Dao class is used to  get help details from help table
 * 
 * @author Priya [SRM]
 */
@Repository
public class HelpDao {
	
	@Autowired
	EntityManager entityManager;
	
	/**
	 * This method is to retrive help help details from help table 
	 * @param helpRequest
	 * @return HelpForm
	 */
	@SuppressWarnings("unchecked")
	public List<HelpVo> getHelpVoList(HelpVo helpRequest) {
		List<HelpVo> helpList = new ArrayList<HelpVo>();
		List<HelpEntity> helpRecords = new ArrayList<HelpEntity>();
		if (null != helpRequest.getHelpSearchText() && !helpRequest.getHelpSearchText().equals("")) {
			helpRecords = (List<HelpEntity>) entityManager
					.createQuery("SELECT c FROM  HelpEntity c where c.topic like '%'||:searchText||'%' OR "
							+ "c.detail like '%'||:searchText||'%' ")
					.setParameter("searchText", helpRequest.getHelpSearchText()).getResultList();
		} else {
			helpRecords = (List<HelpEntity>) entityManager.createQuery("FROM HelpEntity c").getResultList();
		}
		HelpVo helpVo = null;
		for (HelpEntity helpEntity : helpRecords) {
			if ((null != helpEntity.getTopic() || !helpEntity.getTopic().equals(""))
					&& (null != helpEntity.getTopic() || !helpEntity.getTopic().equals(""))) {
			}
			helpVo = new HelpVo();
			BeanUtils.copyProperties(helpEntity, helpVo);
			helpList.add(helpVo);
		}
		return helpList;
	}
}
