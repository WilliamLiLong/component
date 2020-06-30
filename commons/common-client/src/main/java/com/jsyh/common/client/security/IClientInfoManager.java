package com.jsyh.common.client.security;

public interface IClientInfoManager {
	
	//获取clientId和clientSecret
	ClientInfo getClientInfo();
	
	//获取最新的刷新token
	String getRefreshToken();
	
}
