package com.qjp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.qjp.entity.UserEntity;
import com.qjp.util.UserUtils;
import com.qjp.util.query.CompetitorQuery;

@Controller
@RequestMapping("/inner/competitor")
public class CompetitorController {
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ModelAndView list(@ModelAttribute CompetitorQuery competitorQuery, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/competitor/competitor_list");
		UserEntity user = UserUtils.getLoginUser(request);
		/*companyQuery.setCompanyId(user.getCompanyId());
		companyQuery = companyService.getCompanyList(companyQuery);
		mav.addObject("companyQuery", companyQuery);*/
		return mav;
	}
}
