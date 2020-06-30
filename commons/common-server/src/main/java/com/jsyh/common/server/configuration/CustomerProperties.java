package com.jsyh.common.server.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "customer.token")
public class CustomerProperties {

	private long expiredTimeMinutes;
	private String tokenInterceptPath;
	
}
