package com.jsyh.common.core.constants;

public class TokenConstants {
	public static final long TOKEN_EXPIRED_TIME_MINUTES = 60 * 24;
	public static final String REFRESH_TOKE = "refresh_token";	//token刷新
	public static final String AUTHORIZATION_CODE = "authorization_code";	//授权码模式
	public static final String PASSWORD = "password";	//密码模式
	public static final String CLIENT_CREDENTIALS = "client_credentials";	//客户端模式
	public static final String TOKEN_INTERCEPT_PATH = "/external/**";	//拦截token的拦截器路径
	
}
