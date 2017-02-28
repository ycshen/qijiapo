package com.qjp.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qjp.base.ResponseStatus;
import com.qjp.entity.CompetitorEntity;
import com.qjp.entity.LogEntity;
import com.qjp.entity.UserEntity;
import com.qjp.service.CompetitorService;
import com.qjp.service.LogService;
import com.qjp.service.UserService;
import com.qjp.util.JsonUtils;
import com.qjp.util.LogUtils;
import com.qjp.util.StringUtils;
import com.qjp.util.UserUtils;
import com.qjp.util.query.CompetitorQuery;
import com.qjp.util.query.LogQuery;

@Controller
@RequestMapping("/inner/competitor")
public class CompetitorController {
	@Autowired
	private CompetitorService competitorService;
	@Autowired
	private LogService logService;
	@Autowired
	private UserService userService;
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
	public ModelAndView forwardEdit(String id, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/competitor/competitor_edit");
		UserEntity user = UserUtils.getLoginUser(request);
		CompetitorEntity competitor = null;
		if(StringUtils.isNotBlank(id)){
			competitor = competitorService.getCompetitorById(id);
		}
		
		mav.addObject("competitor", competitor);
		mav.addObject("user", user);
		return mav;
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView detail(String id, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/competitor/competitor_detail");
		UserEntity user = UserUtils.getLoginUser(request);
		mav.addObject("user", user);
		CompetitorEntity competitor = competitorService.getCompetitorById(id);
		mav.addObject("competitor", competitor);
		LogQuery logQuery = new LogQuery();
		logQuery.setCasecadeId(id);
		logQuery.setLogType("2");
		logQuery.setSize(30);
		logQuery.setCompanyId(user.getCompanyId().toString());
		logQuery = logService.getLogPage(logQuery);
		List<LogEntity> logList = logQuery.getItems();
		mav.addObject("logList", logList);
		
		return mav;
	}
	
	@RequestMapping(value = "/deleteById", method = RequestMethod.GET)
	@ResponseBody
	public Integer deleteById(String id, String name,HttpServletRequest request){
		Integer result = ResponseStatus.INIT;
		if(StringUtils.isNotBlank(id)){
			competitorService.deleteCompetitorById(id);
			UserEntity user = UserUtils.getLoginUser(request);
			LogUtils.logCRMCompetitor("删除了竞争对手(" + name + ")", id, user);
			result = ResponseStatus.UPDATE_SUCCESS;
		}
		
		return result;
	}
	
	@RequestMapping(value = "/transferCompetitor", method = RequestMethod.GET)
	@ResponseBody
	public Integer transferCompetitor(String userId, String competitorId, String transferType, HttpServletRequest request){
		Integer result = ResponseStatus.INIT;
		if(StringUtils.isNotBlank(userId)){
			UserEntity transferToUser = userService.getUserById(userId);
			UserEntity loginUser = UserUtils.getLoginUser(request);
			if("1".equals(transferType)){
				this.transferCompetitor(competitorId, transferToUser, loginUser);
			}else if("2".equals(transferType)){
				String[] idArr = competitorId.split("\\,");
				for (String id : idArr) {
					this.transferCompetitor(id, transferToUser, loginUser);
				}
			}
			
			result = ResponseStatus.UPDATE_SUCCESS;
			
		}
		
		return result;
	}
	
	private void transferCompetitor(String competitorId, UserEntity transferToUser, UserEntity loginUser){
		CompetitorEntity oldCompetitor = competitorService.getCompetitorById(competitorId);
		oldCompetitor.setBeyondOf(transferToUser.getId().toString());
		String transferUserName = transferToUser.getUserName();
		oldCompetitor.setBeyondOfName(transferUserName);
		oldCompetitor.setUpdateTime(new Date());
		oldCompetitor.setUpdateUser(loginUser.getUserName());
		LogUtils.logCRMCompetitor("转移竞争对手(" + oldCompetitor.getCompetitorName() + ")到" + transferUserName, competitorId, loginUser);
	}
	
	@RequestMapping(value = "/batchDeleteById", method = RequestMethod.GET)
	@ResponseBody
	public Integer batchDeleteById(String ids, HttpServletRequest request){
		Integer result = ResponseStatus.INIT;
		if(StringUtils.isNotBlank(ids)){
			String[] idArr = ids.split("\\,");
			List<String> idList =  Arrays.asList(idArr);
			competitorService.batchDeleteCompetitor(idList);
			UserEntity user = UserUtils.getLoginUser(request);
			for (String id : idList) {
				LogUtils.logCRMCompetitor("删除了竞争对手", id, user);
			}
			result = ResponseStatus.UPDATE_SUCCESS;
		}
		
		return result;
	}
	
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(@ModelAttribute CompetitorEntity competitor, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/competitor/competitor_edit");
		UserEntity user = UserUtils.getLoginUser(request);
		String provinceId = competitor.getProvinceId();
		competitor.setProvinceId(StringUtils.splitLocation(provinceId));
		String cityId = competitor.getCityId();
		competitor.setCityId(StringUtils.splitLocation(cityId));
		String areaId = competitor.getAreaId();
		competitor.setAreaId(StringUtils.splitLocation(areaId));
		Long id = competitor.getId();
		if(id == null){
			competitor.setBeyondDeptType("1");
			competitor.setBeyondOf(user.getId().toString());
			competitor.setBeyondOfName(user.getUserName());
			competitor.setCreateTime(new Date());
			competitor.setCreateUser(user.getUserName());
			String returnId = competitorService.insertCompetitor(competitor);
			LogUtils.logCRMCompetitor("添加了竞争对手(" + competitor.getCompetitorName() + ")", returnId, user);
		}else{
			competitor.setUpdateTime(new Date());
			competitor.setUpdateUser(user.getUserName());
			competitorService.updateCompetitor(competitor);
			LogUtils.logCRMCompetitor("修改了竞争对手(" + competitor.getCompetitorName() + ")", id.toString(), user);
		}
		mav.addObject("user", user);
		return mav;
	}
	
}
