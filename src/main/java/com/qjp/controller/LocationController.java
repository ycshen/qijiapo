package com.qjp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qjp.service.LocationService;

/** 
 * <p>Project: BRP</p> 
 * <p>Title: TestController.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
@Controller
@RequestMapping("/inner/location")
public class LocationController {
	@Autowired
	private LocationService locationService;
	@RequestMapping(value = "/getProvinceCityArea", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getProvinceCityArea(){

		return locationService.getProvinceCityArea();
	}
}

