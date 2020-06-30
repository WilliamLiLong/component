package com.jsyh.common.server.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.jsyh.common.core.dto.AuthRequestData;
import com.jsyh.common.core.dto.AuthResponseData;
import com.jsyh.common.core.dto.ResponseData;
import com.jsyh.common.core.dto.ResponseUtil;
import com.jsyh.common.server.security.AuthService.IAuthService;
import com.jsyh.common.server.utils.StringUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author WilliamLi
 *
 */
@Slf4j
@RequestMapping("/oauth")
public class Authorization {
	
	@Autowired
	private IAuthService authService;
	
	@Autowired
	private JWTUtils jwtUtils;
	
	/**
	 * @author WilliamLi
	 * 验证clientId与clientSecret，成功即返回token
	 */
	@ResponseBody
	@RequestMapping(value = "/token",method = RequestMethod.POST)
	public ResponseData<AuthResponseData> verify(@RequestBody AuthRequestData ard) {
		
		String token = "";
		String refreshToken = "";
		long expiredTimeMinutes = jwtUtils.getTime();
		
		boolean flag = authService.auth(ard.getClientId(), ard.getClientSecret());
		
		if(flag) {
			
			Map<String,Object> tokenMap=new HashMap<>();
			tokenMap.put("clientId",ard.getClientId());
	        String tokenMsg = JSON.toJSON(tokenMap).toString();
	        
	        Map<String,Object> refreshTokenMap=new HashMap<>();
	        refreshTokenMap.put("refreshToken",StringUtils.getRandomString(10));
	        String refreshMsg = JSON.toJSON(refreshTokenMap).toString();
	        
			try {

				token = jwtUtils.creatJwtToken(tokenMsg);
				refreshToken = jwtUtils.creatJwtToken(refreshMsg);

				if (StringUtils.isNull(token) || StringUtils.isNull(refreshToken)) {
					return new ResponseUtil<AuthResponseData>().setErrorMsg("token刷新失败！");
				}

				AuthResponseData ard2 = new AuthResponseData();
				ard2.setRefreshToken(refreshToken);
				ard2.setToken(token);
				ard2.setExpiredTimeMinutes(expiredTimeMinutes);

				return new ResponseUtil<AuthResponseData>().setData(ard2, "获取token成功！");

			} catch (Exception e) {
				log.info("token创建失败：" + e);
				return new ResponseUtil<AuthResponseData>().setErrorMsg("获取token失败！");
			}
		}	
		
		return new ResponseUtil<AuthResponseData>().setErrorMsg("获取token失败！");
	}
	
	@ResponseBody
	@RequestMapping(value = "/refreshToken",method = RequestMethod.POST)
	public ResponseData<AuthResponseData> refreshToken(@RequestBody AuthRequestData ard){
		
		String token = "";
		long expiredTimeMinutes = jwtUtils.getTime();
		
		ResponseData<AuthResponseData> rd = new ResponseData<AuthResponseData>();
		String refreshToken = ard.getRefreshToken();
		
		Map<String,Object> tokenMap=new HashMap<>();
		tokenMap.put("clientId",ard.getClientId());
        String tokenMsg = JSON.toJSON(tokenMap).toString();
        
        Map<String,Object> refreshTokenMap=new HashMap<>();
        refreshTokenMap.put("refreshToken",StringUtils.getRandomString(10));
        String refreshMsg = JSON.toJSON(refreshTokenMap).toString();
		
		
		if("refresh_token".equals(ard.getGrantType())) {	//更新令牌
			if(StringUtils.isNull(refreshToken)) {
				return new ResponseUtil<AuthResponseData>().setErrorMsg("refresh_token值为空！");
			}else {
				String msg = jwtUtils.freeJwt(refreshToken);
				if(!JSON.parseObject(msg).containsKey("refreshToken")) {
					return new ResponseUtil<AuthResponseData>().setErrorMsg("refresh_token非法！");
				}else {
					try {
						token = jwtUtils.creatJwtToken(tokenMsg);
						refreshToken = jwtUtils.creatJwtToken(refreshMsg);
						
						if(StringUtils.isNull(token) || StringUtils.isNull(refreshToken)) {
							return new ResponseUtil<AuthResponseData>().setErrorMsg("token刷新失败！"); 
						}
						
						AuthResponseData ard2 = new AuthResponseData();
						ard2.setRefreshToken(refreshToken);
						ard2.setToken(token);
						ard2.setExpiredTimeMinutes(expiredTimeMinutes);
						
						return new ResponseUtil<AuthResponseData>().setData(ard2,"成功请求！");
					} catch (Exception e) {
						rd.setMessage("token刷新失败！");
						rd.setSuccess(false);
					}
				}				
			}
		}
		rd.setMessage("token刷新失败！");
		rd.setSuccess(false);
		
		return rd;
	}
	
}
