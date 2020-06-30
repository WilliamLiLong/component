package com.jsyh.common.server.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.jsyh.common.core.constants.TokenConstants;
import com.jsyh.common.server.interceptor.JWTInterceptor;
import com.jsyh.common.server.utils.StringUtils;

public class WebConfig implements WebMvcConfigurer {
	
	@Autowired
	private JWTInterceptor jWTInterceptor;
	
	@Autowired
	private CustomerProperties customerProperties;
  
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jWTInterceptor)
                .addPathPatterns(getPath());
    }
    
    private String getPath() {
    	
    	if(StringUtils.isNull(customerProperties.getTokenInterceptPath())) {
    		return TokenConstants.TOKEN_INTERCEPT_PATH;
    	}
    	
    	return customerProperties.getTokenInterceptPath();
    }
}
