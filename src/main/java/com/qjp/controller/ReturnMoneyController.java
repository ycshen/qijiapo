package com.qjp.controller;

import com.qjp.base.ResponseStatus;
import com.qjp.base.RoleEnum;
import com.qjp.entity.LogEntity;
import com.qjp.entity.ReturnMoneyEntity;
import com.qjp.entity.UserEntity;
import com.qjp.service.DepartmentService;
import com.qjp.service.LogService;
import com.qjp.service.UserService;
import com.qjp.util.JsonUtils;
import com.qjp.util.LogUtils;
import com.qjp.util.StringUtils;
import com.qjp.util.UserUtils;
import com.qjp.util.query.LogQuery;
import com.qjp.util.query.ReturnMoneyQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>Project: qijiapo</p>
 * <p>Title: ReturnMoneyController.java</p>
 * <p>Description: TODO</p>
 * <p>Copyright (c) 2017xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
@Controller
@RequestMapping("/inner/returnMoney")
public class ReturnMoneyController {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private LogService logService;
    @Autowired
    private UserService userService;

    private ReturnMoneyEntity getTestEntity(){
        ReturnMoneyEntity returnMoney = new ReturnMoneyEntity();
        returnMoney.setCustomerId("2");
        returnMoney.setCode("201703300001");
        returnMoney.setId("ESDFFASFD320012441ERER");
        return returnMoney;
    }
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public ModelAndView list(@ModelAttribute ReturnMoneyQuery returnMoneyQuery, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("/returnMoney/returnMoney_list");
        mav.addObject("returnMoneyQuery", returnMoneyQuery);
        return mav;
    }


    @RequestMapping(value = "/listAjax", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String listAjax(@ModelAttribute ReturnMoneyQuery returnMoneyQuery, HttpServletRequest request){
        returnMoneyQuery.init(request);
        String roleType = returnMoneyQuery.getRoleType();
        if(RoleEnum.DEP.getRoleId().toString().equals(roleType)){
            String idList = departmentService.getSubDepList(returnMoneyQuery.getDepartmentId(), returnMoneyQuery.getCompanyId());
            returnMoneyQuery.setDepartmentId(idList);
        }

        //eturnMoneyQuery = returnMoneyService.getReturnMoneyPage(returnMoneyQuery);
        List<ReturnMoneyEntity> list = new LinkedList<ReturnMoneyEntity>();
        list.add(this.getTestEntity());
        returnMoneyQuery.setItems(list);
        String jsonStr = JsonUtils.json2Str(returnMoneyQuery);

        return jsonStr;
    }
    @RequestMapping(value = "/forwardEdit", method = RequestMethod.GET)
    public ModelAndView forwardEdit(String id, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("/returnMoney/returnMoney_edit");
        UserEntity user = UserUtils.getLoginUser(request);
        ReturnMoneyEntity returnMoney = null;
        if(StringUtils.isNotBlank(id)){
            //returnMoney = returnMoneyService.getReturnMoneyById(id);
        }

        mav.addObject("returnMoney", returnMoney);
        mav.addObject("user", user);
        return mav;
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public ModelAndView detail(String id, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("/returnMoney/returnMoney_detail");
        UserEntity user = UserUtils.getLoginUser(request);
        mav.addObject("user", user);
       ReturnMoneyEntity returnMoney = this.getTestEntity();
        mav.addObject("returnMoney", returnMoney);
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
            //returnMoneyService.deleteReturnMoneyById(id);
            UserEntity user = UserUtils.getLoginUser(request);
            result = ResponseStatus.UPDATE_SUCCESS;
        }

        return result;
    }

    @RequestMapping(value = "/batchDeleteById", method = RequestMethod.GET)
    @ResponseBody
    public Integer batchDeleteById(String ids, HttpServletRequest request){
        Integer result = ResponseStatus.INIT;
        if(StringUtils.isNotBlank(ids)){
            String[] idArr = ids.split("\\,");
            List<String> idList =  Arrays.asList(idArr);
            //returnMoneyService.batchDeleteReturnMoney(idList);
            UserEntity user = UserUtils.getLoginUser(request);
            for (String id : idList) {
                LogUtils.logCRMProduct("删除了合同", id, user);
            }
            result = ResponseStatus.UPDATE_SUCCESS;
        }

        return result;
    }

    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    public ModelAndView saveOrUpdate(@ModelAttribute ReturnMoneyEntity returnMoney, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("/returnMoney/returnMoney_edit");
        /*returnMoney.init(request); //初始化公司、部门、用户信息
        String provinceId = returnMoney.getProvinceId();
        returnMoney.setProvinceId(StringUtils.splitLocation(provinceId));
        String cityId = returnMoney.getCityId();
        returnMoney.setCityId(StringUtils.splitLocation(cityId));
        String areaId = returnMoney.getAreaId();
        returnMoney.setAreaId(StringUtils.splitLocation(areaId));
        Long id = returnMoney.getId();
        UserEntity user = UserUtils.getLoginUser(request);
        if(id == null){
            String returnId = returnMoneyService.insertReturnMoney(returnMoney);
            LogUtils.logCRMReturnMoney("添加了产品(" + returnMoney.getReturnMoneyName() + ")", returnId, user);
        }else{
            returnMoneyService.updateReturnMoney(returnMoney);
            LogUtils.logCRMReturnMoney("修改了产品(" + returnMoney.getReturnMoneyName() + ")", id.toString(), user);
        }*/

        return mav;
    }

}
