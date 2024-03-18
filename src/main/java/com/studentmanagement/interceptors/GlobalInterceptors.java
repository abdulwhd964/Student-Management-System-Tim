/**
 * Author: Abdul Wahid
 * Date:24/02/2024
 * Time:2:19 PM
 */
package com.studentmanagement.interceptors;

import com.studentmanagement.constant.JWTConstant;
import com.studentmanagement.exception.JwtException;
import com.studentmanagement.util.jwt.JwtTokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@Configuration
@EnableWebMvc
@NoArgsConstructor
@AllArgsConstructor
public class GlobalInterceptors implements HandlerInterceptor {

	boolean tokenEnabled;

	JwtTokenUtil jwtTokenUtil;

	String userName;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (this.tokenEnabled) {
			String jwtToken = request.getHeader("Authorization");
			if (ObjectUtils.isEmpty(jwtToken))
				throw new JwtException(JWTConstant.JWT_TOKEN_MISSING_OR_EMPTY.getMessage());

			String jwtTokenResponse = jwtTokenUtil.validateToken(jwtToken.substring(7));

			if (jwtTokenResponse.equalsIgnoreCase(JWTConstant.JWT_TOKEN_EXPIRED.getMessage())) {
				log.info(" regenerating the token ");
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				// hardcode values since we have loaded this user
				String newJwtToken = jwtTokenUtil.generateToken(userName);
				response.addHeader("Authorization", newJwtToken);
			} else if (jwtTokenResponse.equalsIgnoreCase(JWTConstant.JWT_TOKEN_INVALID.getMessage())) {
				throw new JwtException(JWTConstant.JWT_TOKEN_INVALID.getMessage());
			}
			return true;
		}
		return true;
	}
}
