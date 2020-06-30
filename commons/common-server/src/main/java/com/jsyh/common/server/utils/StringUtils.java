package com.jsyh.common.server.utils;

import java.util.Random;

public class StringUtils {
	
	//生成随机字符串
	public static String getRandomString(int length) {
		
		String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(str.length());
			sb.append(str.charAt(number));
		}
		return sb.toString();
	}
	
	//判断字符串是否为空
	public static boolean isNull(String str) {
		if("".equals(str) || str == null) {
			return true;
		}
		return false;
	}
}
