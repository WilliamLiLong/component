package com.jsyh.common.client.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties(prefix = "external.server")
@Data
public class ExternalServerProperties {
	
	private String ip;	//服务ip
	private String port;	//服务端口
	private String authPath;	//授权路径
	private String refreshTokenPath; //token刷新路径
	
	private String grantType;	//Oauth2类型
	private String clientId;	//客户id名称
	private String clientSecret;	//客户秘钥

}
