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
import com.kevamdg.sr.response.JSONResponse;
import com.kevamdg.sr.service.FaqService;
import com.kevamdg.sr.vo.FaqVo;

@RunWith(MockitoJUnitRunner.class)
public class FaqControllerTests {
	
	@Mock
	private FaqService faqService;
	
	@Mock
	private HttpServletRequest httpServletRequestMock;

	@Mock
	private HttpSession httpSessionMock;

	@Mock
	private ServletContext servletContextMock;

	@InjectMocks
	FaqController faqController;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	
	@Test
	public void getCommonFaq() {

		JSONResponse response = new JSONResponse();
		
		FaqVo faq = new FaqVo();

		List<FaqVo> faqVoList = new ArrayList<FaqVo>();
		
			FaqVo faqVo = new FaqVo();
			
			faqVo.setFaqId(1);
			faqVo.setQuestion("what is KIOSK");
			faqVo.setAnswer("small standalone application");
			
			faqVoList.add(faqVo);
			
			faq.setFaqVoList(faqVoList);
			
			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setSuccesObject(faq);
			
			when(httpServletRequestMock.getSession()).thenReturn(httpSessionMock);
			when(httpSessionMock.getServletContext()).thenReturn(servletContextMock);

			

			when(faqService.getCommonFaq()).thenReturn(faqVoList);
			
			faqController.getCommonFaq();

			FaqVo fa = new FaqVo();

			if (null != (FaqVo) response.getSuccesObject()) {
				fa = (FaqVo) response.getSuccesObject();

				assertEquals("small standalone application", fa.getFaqVoList().get(0).getAnswer());
				assertEquals(new Integer(1),fa.getFaqVoList().get(0).getFaqId());
				assertEquals("what is KIOSK", fa.getFaqVoList().get(0).getQuestion());
				assertEquals("200", response.getResponseCode());
			}

	}

}
