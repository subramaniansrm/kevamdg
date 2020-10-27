package com.kevamdg.sr;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
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

import com.kevamdg.sr.config.CommonException;
import com.kevamdg.sr.config.LoginAuthException;
import com.kevamdg.sr.constants.CommonConstant;
import com.kevamdg.sr.controller.SalesOrgController;
import com.kevamdg.sr.response.JSONResponse;
import com.kevamdg.sr.service.SalesOrgService;
import com.kevamdg.sr.vo.AuthDetailsVO;
import com.kevamdg.sr.vo.FaqVo;
import com.kevamdg.sr.vo.JSONRequestVO;
import com.kevamdg.sr.vo.SalesOrgVO;
import com.kevamdg.sr.vo.UserScreenMappingVO;

@RunWith(MockitoJUnitRunner.class)
public class SalesOrgControllerTests {
	
	@Mock
	private SalesOrgService salesOrgService;
	
	@Mock
	private HttpServletRequest httpServletRequestMock;

	@Mock
	private HttpSession httpSessionMock;

	@Mock
	private ServletContext servletContextMock;

	@InjectMocks
	SalesOrgController salesOrgController;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	
	@Test
	public void getAll() throws IOException {

		JSONResponse response = new JSONResponse();
		
		JSONRequestVO commonVO = new JSONRequestVO();
		
		commonVO.setSubScreenId(2);
		
		List<SalesOrgVO> salesOrgVOList = new ArrayList<SalesOrgVO>();
		
		SalesOrgVO salesOrgVO = new SalesOrgVO();
			
		salesOrgVO.setId(1);
		salesOrgVO.setSalesOrgCode("1000");
		salesOrgVO.setSalesOrgName("SH KELKAR & CO.LTD");
			
		salesOrgVOList.add(salesOrgVO);
			
			
			
			
			
			
				
			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setSuccesObject(salesOrgVOList);
			
			when(httpServletRequestMock.getSession()).thenReturn(httpSessionMock);
			when(httpSessionMock.getServletContext()).thenReturn(servletContextMock);

			

			when(salesOrgService.getAll()).thenReturn(salesOrgVOList);
			
			salesOrgController.getAll(httpServletRequestMock,commonVO);

			List<SalesOrgVO> salesOrgList = new ArrayList<SalesOrgVO>();

			if (null != (List<SalesOrgVO>) response.getSuccesObject()) {
				salesOrgList = (List<SalesOrgVO>) response.getSuccesObject();

				assertEquals("1000", salesOrgList.get(0).getSalesOrgCode());
				assertEquals(new Integer(1),salesOrgList.get(0).getId());
				assertEquals("SH KELKAR & CO.LTD", salesOrgList.get(0).getSalesOrgName());
				assertEquals("200", response.getResponseCode());
			}

	}
	
	@Test
	public void search() throws IOException {

		JSONResponse response = new JSONResponse();
		
		SalesOrgVO salesOrg = new SalesOrgVO();
		
		List<SalesOrgVO> salesOrgVOList = new ArrayList<SalesOrgVO>();
		
		SalesOrgVO salesOrgVO = new SalesOrgVO();
			
		salesOrgVO.setId(1);
		salesOrgVO.setSalesOrgCode("1000");
		salesOrgVO.setSalesOrgName("SH KELKAR & CO.LTD");
			
		salesOrgVOList.add(salesOrgVO);
			
			
			
			
			
			
				
			response.setResponseCode(CommonConstant.SUCCESS_CODE);
			response.setSuccesObject(salesOrgVOList);
			
			when(httpServletRequestMock.getSession()).thenReturn(httpSessionMock);
			when(httpSessionMock.getServletContext()).thenReturn(servletContextMock);

			

			when(salesOrgService.search(salesOrg)).thenReturn(salesOrgVOList);
			
			salesOrgController.search(httpServletRequestMock,salesOrg);

			List<SalesOrgVO> salesOrgList = new ArrayList<SalesOrgVO>();

			if (null != (List<SalesOrgVO>) response.getSuccesObject()) {
				salesOrgList = (List<SalesOrgVO>) response.getSuccesObject();

				assertEquals("1000", salesOrgList.get(0).getSalesOrgCode());
				assertEquals(new Integer(1),salesOrgList.get(0).getId());
				assertEquals("SH KELKAR & CO.LTD", salesOrgList.get(0).getSalesOrgName());
				assertEquals("200", response.getResponseCode());
			}

	}
			
		

}
