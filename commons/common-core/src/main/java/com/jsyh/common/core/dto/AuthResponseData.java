package com.jsyh.common.core.dto;

public class AuthResponseData {
	
	private String token;	//access token
	private String refreshToken;	//刷新token
	private long expiredTimeMinutes;	//过期时间

	public long getExpiredTimeMinutes() {
		return expiredTimeMinutes;
	}
	public void setExpiredTimeMinutes(long expiredTimeMinutes) {
		this.expiredTimeMinutes = expiredTimeMinutes;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	
	
	
}
