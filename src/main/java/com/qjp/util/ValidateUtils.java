package com.qjp.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
 * <p>Project: qijiapo</p> 
 * <p>Title: ValidateUtils.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
public class ValidateUtils {
	/**
	 * 校验是否是手机号码
	 * @param telephone
	 * @return true 是
	 */
	public static boolean isTelephone(String telephone){
		Pattern p = Pattern.compile("^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");
		Matcher m = p.matcher(telephone);
		boolean isTel = m.matches();
		
		return isTel;
	}
	
	/**
	 * 判断密码是否由数字、字母以及特殊字符组成
	 * @param password
	 * @return true 是
	 */
	public static boolean isValidPass(String password){
		Pattern p = Pattern.compile("^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");
		Matcher m = p.matcher(password);
		boolean isValid = m.matches();
		
		return isValid;
	}
	
	/**
	 * 校验是否是电子邮箱
	 * @param email
	 * @return true 是
	 */
	public static boolean isEmail(String email){
		Pattern p = Pattern.compile("^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");
		Matcher m = p.matcher(email);
		boolean isEmail = m.matches();
		
		return isEmail;
	}
}

