package com.qjp.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qjp.entity.CompetitorEntity;
import com.qjp.entity.UserEntity;
import com.qjp.service.CompetitorService;
import com.qjp.util.JsonUtils;
import com.qjp.util.UserUtils;
import com.qjp.util.query.CompetitorQuery;

@Controller
@RequestMapping("/inner/competitor")
public class CompetitorController {
	@Autowired
	private CompetitorService competitorService;
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ModelAndView list(@ModelAttribute CompetitorQuery competitorQuery, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/competitor/competitor_list");
		UserEntity user = UserUtils.getLoginUser(request);
		competitorQuery.setCompanyId(user.getCompanyId());
		competitorQuery = competitorService.getCompetitorPage(competitorQuery);
		mav.addObject("competitorQuery", competitorQuery);
		return mav;
	}
	
	
	@RequestMapping(value = "/listAjax", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String listAjax(@ModelAttribute CompetitorQuery competitorQuery, HttpServletRequest request){
		UserEntity user = UserUtils.getLoginUser(request);
		competitorQuery.setCompanyId(user.getCompanyId());
		competitorQuery = competitorService.getCompetitorPage(competitorQuery);
		competitorQuery.setSize(65);
		String jsonStr = JsonUtils.json2Str(competitorQuery);
		
		return jsonStr;
	}
	@RequestMapping(value = "/forwardEdit", method = RequestMethod.GET)
	public ModelAndView forwardEdit(@ModelAttribute CompetitorQuery competitorQuery, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/competitor/competitor_edit");
		UserEntity user = UserUtils.getLoginUser(request);
		/*companyQuery.setCompanyId(user.getCompanyId());
		companyQuery = companyService.getCompanyList(companyQuery);
		mav.addObject("companyQuery", companyQuery);*/
		mav.addObject("user", user);
		return mav;
	}
	
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(@ModelAttribute CompetitorEntity competitor, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/competitor/competitor_edit");
		UserEntity user = UserUtils.getLoginUser(request);
		Long id = competitor.getId();
		if(id == null){
			competitor.setBeyondDeptType("1");
			competitor.setBeyondOf(user.getId().toString());
			competitor.setBeyondOfName(user.getUserName());
			competitor.setCreateTime(new Date());
			competitor.setCreateUser(user.getUserName());
			competitorService.insertCompetitor(competitor);
		}else{
			
		}
		mav.addObject("user", user);
		return mav;
	}
}
