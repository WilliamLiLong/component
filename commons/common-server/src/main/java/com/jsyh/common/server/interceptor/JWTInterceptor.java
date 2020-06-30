package com.jsyh.common.server.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.jsyh.common.core.dto.ResponseData;
import com.jsyh.common.core.dto.ResponseUtil;
import com.jsyh.common.server.security.JWTUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JWTInterceptor extends HandlerInterceptorAdapter{

	@Autowired
	JWTUtils jwtUtils;
	
    public static String ACCESS_TOKEN="access_token";

    public static String USER_INFO_KEY="userInfo";

    @Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) 
    		throws Exception {
    	log.info("进入token拦截器！");
        String token = request.getHeader("authorization");

        if(StringUtils.isEmpty(token)){
            ResponseData<String> responseData=new ResponseUtil<String>().setErrorMsg("请求未携带token！");
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSON(responseData).toString());
            return false;
        }

		String decodeToken = "";
		try {

			jwtUtils.freeJwt(token);
			request.setAttribute(USER_INFO_KEY, decodeToken);

		} catch (Exception e) {
			log.info("token解密失败！");
			response.setContentType("applicaiton/json;charset=UTF-8");
			response.getWriter().write(JSON.toJSON(new ResponseUtil<String>().setErrorMsg("token解密失败！")).toString());
			return false;
		}

		return super.preHandle(request, response, handler);
         
    }
}
