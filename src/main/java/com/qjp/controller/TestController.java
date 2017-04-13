package com.qjp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/** 
 * <p>Project: BRP</p> 
 * <p>Title: TestController.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
@Controller
public class TestController {
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public void test(){
		System.out.println("aaaaaa");
	}

	public static void main(String[] args) {
		String key = "04013b78d38a71987f31ca935eeb18ec";
		String requestUrl ="http://api.map.baidu.com/location/ip";

	}
}

