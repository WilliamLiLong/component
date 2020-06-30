package com.jsyh.common.client.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import com.jsyh.common.client.security.ExternalAuthorization;

@Configuration
@EnableConfigurationProperties(ExternalServerProperties.class)
public class AuthorizationAutoConfiguration {
	
	@Autowired
	private ExternalServerProperties externalServerProperties;
	
	@Lazy
	@Bean
	@ConditionalOnMissingBean(ExternalAuthorization.class)
	ExternalAuthorization getAuthorization () {
		
		return new ExternalAuthorization();
	}
	
	@Bean
	@ConditionalOnMissingBean(ExternalServerDataSource.class)
	ExternalServerDataSource getExternalServerDataSource() {
		ExternalServerDataSource esds = new ExternalServerDataSource();
		esds.setServerIP(externalServerProperties.getIp());
		esds.setServerPort(externalServerProperties.getPort());
		esds.setAuthPath(externalServerProperties.getAuthPath());
		esds.setRefreshTokenPath(externalServerProperties.getRefreshTokenPath());
		
		esds.setGrantType(externalServerProperties.getGrantType());
		esds.setClientId(externalServerProperties.getClientId());
		esds.setClientSecret(externalServerProperties.getClientSecret());
		
		
		return esds;
	}
	
	@Bean
	@ConditionalOnMissingBean(ExternalServerProperties.class)
	ExternalServerProperties getExternalServerProperties() {
		
		return new ExternalServerProperties();
	}
	
	
	
}
