package com.jsyh.common.server.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.alibaba.fastjson.JSON;
import com.jsyh.common.server.exception.ValidateException;
import com.jsyh.common.server.security.JWTUtils;

public class MainTest {
	public static void main(String[] args) throws Exception {
		
		Random random = new Random();
		for (int i = 0; i < 10000; i++) {
			System.out.println(random.nextInt(10));
		}
		
		
//		JWTUtils jw = new JWTUtils();
//		Map<String,Object> map=new HashMap<>();
//        map.put("clientId","lilong");
//        
//        String msg = JSON.toJSON(map).toString();
//        
//        Map<String,Object> map2=new HashMap<>();
//        map.put("clientId","lidan");
//        
//        String msg2 = JSON.toJSON(map).toString();
//		
//		String token = jw.creatJwtToken(msg);
//		System.out.println(token);
//		String token2 = jw.creatJwtToken(msg2);
//		System.out.println(token2);
//		System.out.println(jw.freeJwt(token));;
//		System.out.println(jw.freeJwt(token2));
//		jw.freeJwt("yJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ3bGd6cyIsImV4cCI6MTU5MzMyNDc3OCwidXNlciI6IjZBODQ3OUI3OUI3MkRDMEFCODhBQjQ2OTY1QTAxQTM5ODVCMTdCNkZCOTUwQUE4MjhDQzQyQzIyMDNDOEY3REEifQ.NS4fFDKz7nruZPuA8GhGcGphUhxnIlx-u3HXt9Psqco");
//		jw.freeJwt("eyJpc3MiOiJ3bGd6cyIsImV4cCI6MTU5MzMyNTgyMywidXNlciI6IjU4REVDOTBFRTgwQkQ4RDc1N0VENDA4QjcyMEM5QjAyMUVGQzEyNkEzNEQ4MjExQjJGMDREMUY0MUI2Q0JDRjcifQ");
//		jw.freeJwt("yJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ3bGd6cyIsImV4cCI6MTU5MzMyNDc3OCwidXNlciI6IjZBODQ3OUI3OUI3MkRDMEFCODhBQjQ2OTY1QTAxQTM5ODVCMTdCNkZCOTUwQUE4MjhDQzQyQzIyMDNDOEY3REEifQ.NS4fFDKz7nruZPuA8GhGcGphUhxnIlx-u3HXt9Psqco");

		
		
//		try {
//			test();
//			
//		} catch(ValidateException e) {
//			System.out.println("catch:\n");
//			e.printStackTrace();
//		} catch (Exception e) {
//			// TODO: handle exception
//			System.out.println("big");
//		}
	}
	
	public static void test() {
		throw new ValidateException("code1","message1");
	}
}
