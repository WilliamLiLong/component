package com.jsyh.common.server.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import com.jsyh.common.server.interceptor.JWTInterceptor;
import com.jsyh.common.server.security.Authorization;
import com.jsyh.common.server.security.JWTUtils;

@Configuration
public class AuthorizationAutoConfiguration {
	
	@Bean
	@Lazy
	@ConditionalOnMissingBean(Authorization.class)
	public Authorization authorization() {

		return new Authorization();
	}
	
	@Bean
	@ConditionalOnMissingBean(JWTUtils.class)
	public JWTUtils jwtUtils() {
		
		return new JWTUtils();
	}
	
	@Bean
	@ConditionalOnMissingBean(WebConfig.class)
	public WebConfig getWebConfig() {
	
		return new WebConfig();
	}
	
	@Bean
	@ConditionalOnMissingBean(JWTInterceptor.class)
	public JWTInterceptor getJWTInterceptor() {
	
		return new JWTInterceptor();
	}
	
	@Bean
	@ConditionalOnMissingBean(CustomerProperties.class)
	public CustomerProperties getCustomerProperties() {
	
		return new CustomerProperties();
	}
}
