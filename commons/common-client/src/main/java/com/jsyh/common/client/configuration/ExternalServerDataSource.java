package com.jsyh.common.client.configuration;

import lombok.Data;

@Data
public class ExternalServerDataSource {
	
	private String serverIP;	//服务器ip
	private String serverPort;	//服务器端口
	private String authPath;	//授权访问路径
	private String refreshTokenPath; //token刷新路径
	
	private String grantType;	//Oauth2类型
	private String clientId;	//客户id名称
	private String clientSecret;	//客户秘钥
}
