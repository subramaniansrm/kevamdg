package com.kevamdg.sr;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.kevamdg.sr.constants.CommonConstant;
import com.kevamdg.sr.controller.FaqController;
import com.kevamdg.sr.controller.HelpController;
import com.kevamdg.sr.response.JSONResponse;
import com.kevamdg.sr.service.FaqService;
import com.kevamdg.sr.service.HelpService;
import com.kevamdg.sr.vo.FaqVo;
import com.kevamdg.sr.vo.HelpVo;

@RunWith(MockitoJUnitRunner.class)
public class HelpControllerTests {
	
	@Mock
	private HelpService helpService;
	
	@Mock
	private HttpServletRequest httpServletRequestMock;

	@Mock
	private HttpSession httpSessionMock;

	@Mock
	private ServletContext servletContextMock;

	@InjectMocks
	HelpController helpController;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	
	@Test
	public void getHelpVoList() {

		JSONResponse response = new JSONResponse();
		
		HelpVo helpVo = new HelpVo();

		List<HelpVo> helpVoList = new ArrayList<HelpVo>();
		
		HelpVo help = new HelpVo();
			
		help.setHelpId(1);
		help.setTopic("emergency");
		help.setDetail("related to medical issue");
			
		helpVoList.add(help);
			
			
			
			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setSuccesObject(helpVoList);
			
			when(httpServletRequestMock.getSession()).thenReturn(httpSessionMock);
			when(httpSessionMock.getServletContext()).thenReturn(servletContextMock);

			

			when(helpService.getHelpVoList(helpVo)).thenReturn(helpVoList);
			
			helpController.getHelpVoList(helpVo);

			List<HelpVo> helpList = new ArrayList<HelpVo>();

			if (null != (List<HelpVo>) response.getSuccesObject()) {
				helpList = (List<HelpVo>) response.getSuccesObject();

				assertEquals("emergency", helpList.get(0).getTopic());
				assertEquals(new Integer(1),helpList.get(0).getHelpId());
				assertEquals("related to medical issue", helpList.get(0).getDetail());
				assertEquals("200", response.getResponseCode());
			}

	}

}
