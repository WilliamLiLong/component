package com.jsyh.common.server.security.AuthService;

public interface IAuthService {
	
	boolean auth(String clientId,String clientSecret);
}
