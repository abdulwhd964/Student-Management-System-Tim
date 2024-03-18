package com.studentmanagement.configuration;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.studentmanagement.constant.AppConstant;
import com.studentmanagement.interceptors.GlobalInterceptors;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.extern.slf4j.Slf4j;
import com.studentmanagement.util.jwt.*;

/*
 * Author: Abdul Wahid
 * Date:   23 Feb 2024
 */
@Configuration
@EnableCaching
@Slf4j
public class Appconfiguration implements WebMvcConfigurer {
	

	private static final String[] AUTH_WHITE_LIST = { "/h2-console/**", "/swagger-ui/index.html/**", "/login",
			"/v3/api-docs/**", "/swagger-ui/**", "/webjars/**", "/login", "/swagger-resources/**" };

	@Value("${jwt.token.enabled}")
	boolean isJwtTokenEnable;

	@Value("${jwt.user.name}")
	String jwtUserName;

	@Bean
	public JwtTokenUtil getJwtTokenUtil() {
		return new JwtTokenUtil();
	}

	@Bean
	public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> webServerFactoryCustomizer() {
		return factory -> factory.setContextPath(AppConstant.API);
	}

	@Bean
	public CommonsRequestLoggingFilter logFilter() {
		CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
		filter.setIncludeQueryString(true);
		filter.setIncludePayload(true);
		filter.setMaxPayloadLength(10000);
		filter.setIncludeHeaders(false);
		filter.setAfterMessagePrefix("REQUEST DATA: ");
		return filter;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new GlobalInterceptors(isJwtTokenEnable, getJwtTokenUtil(), jwtUserName))
				.addPathPatterns("/**").excludePathPatterns(AUTH_WHITE_LIST);
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/**").allowedOrigins("http://localhost:3000")
				.allowedMethods("GET", "POST", "PUT", "DELETE").allowedHeaders("Origin", "X-Requested-With",
						"Content-Type", "Accept", "Access-Control-Allow-Origin", "authorization")
				.allowedOriginPatterns("*");
	}

	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter() {
		log.info("sucesss in FilterRegistrationBeans");
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(Arrays.asList("*"));
		config.setAllowedHeaders(Arrays.asList("*"));
		config.setAllowedMethods(Arrays.asList("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
		config.setAllowedHeaders(Arrays.asList("authorization", "content-type"));
		source.registerCorsConfiguration("/**", config);

		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
		bean.setOrder(0);
		log.info("sucesss in FilterRegistrationBeans" + bean);
		return bean;
	}

	@Bean
	public OpenAPI openAPI() {
		final String securitySchemeName = "bearer-key";
		return new OpenAPI().addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
				.components(new Components().addSecuritySchemes(securitySchemeName, new SecurityScheme()
						.name(securitySchemeName).type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
	}

}
