package com.kevamdg.sr.controller;



import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.kevamdg.sr.constants.FilePathConstants;
import com.kevamdg.sr.service.LoginService;
import com.kevamdg.sr.util.CommonUtil;
import com.kevamdg.sr.vo.LoginUserVO;
import com.kevamdg.sr.vo.Response;
import com.kevamdg.sr.vo.Token;
import com.kevamdg.sr.vo.UserVO;


/**
 * User Login Controller
 * @author raathikaabm
 *
 */
@RestController
public class LoginController {

	@Autowired
	private LoginService userLoginService;
	
	@Autowired
	private RestTemplate restTemplate;

	/**
	 * 
	 * Method used to login the user using the credentials
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@PostMapping(FilePathConstants.KEVA_LOGIN)
	@ResponseBody
	public Response postForm(HttpServletRequest request, @RequestBody LoginUserVO user) {
		Response errorResponse = new Response();
		errorResponse.setResponseCode("401");
		errorResponse.setResponseMessage("Invalid user name and password");
		errorResponse.setSuccesObject(null);

		try {
			String baseKey = request.getHeader("authorization");
			if (baseKey != null) {
				byte[] decodedBytes = Base64.getDecoder().decode(baseKey.split("\\s+")[1]);
				String decodedString = new String(decodedBytes);
				String credintial[] = decodedString.split(":");
				if (credintial.length == 2) {
					String userName = credintial[0];
					String password = credintial[1];
					// Check the UserName.
					UserVO mstUserActionVo = userLoginService.getLogin(userName);
					if (mstUserActionVo != null) {
						// Check the match of the Password.
						if (mstUserActionVo.getPassword().equals(password)) {
							// To Generate the Token
							String token = baseKey.split("\\s+")[1];// TokenManager.generateToken(baseKey);

							UUID uuid = UUID.randomUUID();
							token = uuid.toString();

							
							//TokenManager.tokenMap.put(token, authUserInfo);

							Token resToken = new Token();
							// resToken.setAccess_token(baseKey.split("\\s+")[1]);
							resToken.setAccess_token(token);
							resToken.setRedirect_path("/dashboard");
							//resToken.setUserName(authUserInfo.getUserMaster().getFirstName());
							// resToken.setRedirect_path(AuthUtil.getAuthUserInfo().getUserMaster().getUrl());

							errorResponse.setResponseCode("200");
							errorResponse.setResponseMessage("Success");
							errorResponse.setSuccesObject(resToken);
							return errorResponse;

						} else {
							// Throw the Message If userName or Password are
							// Invalid
							return errorResponse;
						}
					} else {
						return errorResponse;
					}
				} else {
					return errorResponse;
				}
			} else {
				return errorResponse;
			}
		} catch (Exception e) {
			return errorResponse;
		}
	}

	/**
	 * This method is used to login process.
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param loginRequest
	 *            LoginRequest
	 * @return response Response
	 */
	@RequestMapping(FilePathConstants.KEVA_AUTOLOGIN)
	public void autoLogin(HttpServletRequest req, HttpServletResponse res) {
		Response errorResponse = new Response();
		errorResponse.setResponseCode("401");
		errorResponse.setResponseMessage("Invalid user name and password");
		errorResponse.setSuccesObject(null);
		try {
			String baseKey = req.getHeader("access_token");
			if (baseKey != null) {
				byte[] decodedBytes = Base64.getDecoder().decode(baseKey);
				String decodedString = new String(decodedBytes);
				String credintial[] = decodedString.split(":");
				System.out.println("User IP Address : " + req.getRemoteAddr());
				if (credintial.length == 2) {
					String userName = credintial[0];
					String password = credintial[1];

					// Check the UserName.
					UserVO mstUserActionVo = userLoginService.getLogin(userName);
					if (mstUserActionVo != null) {
						// Check the match of the Password.
						if (mstUserActionVo.getPassword().equals(password)) {
							// To Generate the Token
							String token = baseKey;// TokenManager.generateToken(baseKey);
						
							//TokenManager.tokenMap.put(token, authUserInfo);

							res.setHeader("Access-Control-Allow-Origin", "*");
							res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
							res.setHeader("Access-Control-Max-Age", "3600");
							res.setHeader("Access-Control-Allow-Headers",
									"authorization,content-type,xsrf-token,access_token");
							res.addHeader("Access-Control-Expose-Headers", "x-auth-token");

							RequestDispatcher dispatcher = req.getServletContext()
									.getRequestDispatcher(String.valueOf(req.getAttribute("originalUrl")));

							dispatcher.forward(req, res);
						} else {
							// Throw the Message If userName or Password are
							// Invalid
							return;
						}
					} else {
						return;
					}
				} else {
					// Throw the Exception Message Authentication Failure
					return;
				}
			} else {
				return;
			}
		} catch (Exception e) {
			return;
		}
	}

	@GetMapping(FilePathConstants.KEVA_LOGOUT)
	@ResponseBody
	public Token logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Token token = new Token();
		token.setRedirect_path("/login");
		return token;
	}
	
	@PostMapping("/core/oauth/token")
	public ResponseEntity<OAuth2AccessToken> login(@RequestHeader("auth") String auth, @RequestBody LoginUserVO login,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, String> parameters = new LinkedHashMap<String, String>();
		ResponseEntity<OAuth2AccessToken> res = null;

		parameters.put("username", login.getUsername());
		parameters.put("password", CommonUtil.decode(login.getPassword()));
		parameters.put("grant_type", login.getGrantType());

		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<String, String>();
		multiValueMap.setAll(parameters);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", auth);
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(multiValueMap,
				headers);

		String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath() + "/oauth/token";
		try {
			res = restTemplate.exchange(url, HttpMethod.POST, entity, OAuth2AccessToken.class);
 		} catch (HttpClientErrorException ex) {
			ex.printStackTrace();
			if (ex.getRawStatusCode() == 400) {
				throw new HttpClientErrorException(ex.getStatusCode(), "INVALID USERID/PASSWORD.");
			} else if (ex.getRawStatusCode() == 401) {
				throw new HttpClientErrorException(ex.getStatusCode(), "Account is Locked. Please Contact Administrator.");
			} else {
				throw new HttpClientErrorException(ex.getStatusCode(), "Something went wrong. Please try again!.");
			}
		}
		
		String accessToken = res.getBody().getValue();

		userLoginService.updateAccessToken(accessToken, login.getUsername());

		return new ResponseEntity<>(res.getBody(), res.getStatusCode());
	}

}
