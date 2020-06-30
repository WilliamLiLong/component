package com.jsyh.common.client.security;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.jsyh.common.client.configuration.ExternalServerDataSource;
import com.jsyh.common.core.constants.TokenConstants;
import com.jsyh.common.core.dto.AuthRequestData;
import com.jsyh.common.core.dto.AuthResponseData;
import com.jsyh.common.core.dto.ResponseData;
import com.jsyh.common.core.dto.ResponseUtil;

public class ExternalAuthorization {
	
	@Autowired
	private ExternalServerDataSource externalServerDataSource;
	
	@Autowired
	private IClientInfoManager clientInfoManager;
	
	//刷新token
	public ResponseData<AuthResponseData> refreshToken() throws Exception{
		AuthRequestData ard = new AuthRequestData();
		ard.setClientId(clientInfoManager.getClientInfo().getClientId());
		ard.setRefreshToken(clientInfoManager.getRefreshToken());
		ard.setGrantType(TokenConstants.REFRESH_TOKE);
		
		ResponseData<AuthResponseData> rd = null;
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost httpPost = new HttpPost(externalServerDataSource.getRefreshTokenPath());
		httpPost.addHeader("Content-Type", "application/json;charset=utf8");	//设置contentType为json
		
	    String content = JSON.toJSON(ard).toString();	    
	    StringEntity se = new StringEntity(content);
	    se.setContentEncoding("UTF-8");
	    se.setContentType("application/json");
	    httpPost.setEntity(se);
	
		CloseableHttpResponse response = null;	//响应模型
		
		response = httpClient.execute(httpPost);	//由客户端执行Post请求
		
		HttpEntity responseEntity = response.getEntity();	//从响应模型中获取响应实体
					
		if (responseEntity != null) {
			String result = EntityUtils.toString(responseEntity);
			rd = JSON.parseObject(result, new TypeReference<ResponseData<AuthResponseData>>() {
			});
		}

		return rd;
	}
	
	//使用post方式获取验证并获取授权（安全性更高）
	public ResponseData<AuthResponseData> getAuthorization() throws Exception {
		
		ResponseData<AuthResponseData> rd = null;
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost httpPost = new HttpPost(externalServerDataSource.getAuthPath());
		
		httpPost.addHeader("Content-Type", "application/json;charset=utf8");	//设置contentType为json
	    
		ClientInfo ci = clientInfoManager.getClientInfo();
		if(ci == null) {
			return new ResponseUtil<AuthResponseData>().setErrorMsg("未获取到用户信息！");
		}
			
	    Map<String,String>  map = new HashMap<String,String>();
	    map.put("grantType",TokenConstants.CLIENT_CREDENTIALS);    
	    map.put("clientId", ci.getClientId());
	    map.put("clientSecret", ci.getClientSecret());
	    String content = JSON.toJSON(map).toString();	    
	    StringEntity se = new StringEntity(content);
	    se.setContentEncoding("UTF-8");
	    se.setContentType("application/json");
	    httpPost.setEntity(se);
	
		CloseableHttpResponse response = null;	//响应模型
		
		response = httpClient.execute(httpPost);	//由客户端执行(发送)Post请求
		
		HttpEntity responseEntity = response.getEntity();	//从响应模型中获取响应实体
					
		if (responseEntity != null) {
			String result = EntityUtils.toString(responseEntity);
			rd = JSON.parseObject(result,new TypeReference<ResponseData<AuthResponseData>>() {});

		}
			
		return rd;
	}

}
