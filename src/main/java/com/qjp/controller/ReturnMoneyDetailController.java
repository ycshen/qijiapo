package com.qjp.controller;

import com.qjp.base.ResponseStatus;
import com.qjp.entity.LogEntity;
import com.qjp.entity.ReturnMoneyDetailEntity;
import com.qjp.entity.ReturnMoneyDetailEntity;
import com.qjp.entity.UserEntity;
import com.qjp.service.*;
import com.qjp.util.LogUtils;
import com.qjp.util.StringUtils;
import com.qjp.util.UserUtils;
import com.qjp.util.query.LogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by fengyue on 2017/4/7.
 */
@Controller
@RequestMapping("/inner/returnMoneyDetail")
public class ReturnMoneyDetailController {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private LogService logService;
    @Autowired
    private UserService userService;
    @Autowired
    private ReturnMoneyService returnMoneyService;
    @Autowired
    private ReturnMoneyDetailService returnMoneyDetailService;
    @Autowired
    private ContractService contractService;

    @RequestMapping(value = "/addDetail", method = RequestMethod.GET)
    public ModelAndView addDetail(Integer type, String contractId, HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        if(type == 1){
        	mav.setViewName("/returnMoney/returnMoney_plan_edit");
        }else if(type == 2){
        	mav.setViewName("/returnMoney/returnMoney_actual_edit");
        }else{
        	mav.setViewName("/returnMoney/returnMoney_tax_edit");
        }
        
        UserEntity user = UserUtils.getLoginUser(request);
        //接口1：获取该合同本次调用的回款期次
        //start
        Integer returnMoneyNum = 1;
        //end
        UserEntity loginUser = UserUtils.getLoginUser(request);
        List<UserEntity> userList = userService.getUserListByCompanyId(loginUser.getCompanyId().toString());
        mav.addObject("userList", userList);
        mav.addObject("contractId",contractId);
        mav.addObject("user", user);
        mav.addObject("returnMoneyNum", returnMoneyNum);
        return mav;
    }
    
    @RequestMapping(value = "/deleteById", method = RequestMethod.GET)
    @ResponseBody
    public Integer deleteById(String id,HttpServletRequest request){
        Integer result = ResponseStatus.INIT;
        if(StringUtils.isNotBlank(id)){
            ReturnMoneyDetailEntity returnMoneyDetail = returnMoneyDetailService.getReturnMoneyDetailById(id);
            returnMoneyDetailService.deleteReturnMoneyDetailById(id);
            UserEntity user = UserUtils.getLoginUser(request);
            String returnMoneyDetailType = "回款";
            //回款类型（1-计划  2-实际  3-开票）
            if (returnMoneyDetail.getReturnMoneyType() == 1)
                returnMoneyDetailType = "计划回款";
            else if (returnMoneyDetail.getReturnMoneyType() == 2)
                returnMoneyDetailType = "实际回款";
            else returnMoneyDetailType = "开票回款";
            LogUtils.logCRMReturnMoneyDetail("删除了" + returnMoneyDetailType, id, user);
            result = ResponseStatus.UPDATE_SUCCESS;
        }

        return result;
    }

    @RequestMapping(value = "/forwardEdit", method = RequestMethod.GET)
    public ModelAndView forwardEdit(String id, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("/returnMoneyDetail/returnMoneyDetail_edit");
        UserEntity user = UserUtils.getLoginUser(request);
        ReturnMoneyDetailEntity returnMoneyDetail = null;
        if(StringUtils.isNotBlank(id)){
            returnMoneyDetail = returnMoneyDetailService.getReturnMoneyDetailById(id);
        }

        mav.addObject("returnMoneyDetail", returnMoneyDetail);
        mav.addObject("user", user);
        return mav;
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public ModelAndView detail(String id, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("/returnMoneyDetail/returnMoneyDetail_detail");
        UserEntity user = UserUtils.getLoginUser(request);
        mav.addObject("user", user);
        ReturnMoneyDetailEntity returnMoneyDetail = returnMoneyDetailService.getReturnMoneyDetailById(id);
        mav.addObject("returnMoneyDetail", returnMoneyDetail);
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
    
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    public ModelAndView saveOrUpdate(@ModelAttribute ReturnMoneyDetailEntity returnMoneyDetail, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/returnMoneyDetail/returnMoneyDetail_edit");
        returnMoneyDetail.init(request); //初始化公司、部门、用户信息
        String id = returnMoneyDetail.getId();
        UserEntity user = UserUtils.getLoginUser(request);
        String returnMoneyDetailType = "回款";
        //回款类型（1-计划  2-实际  3-开票）
        if (returnMoneyDetail.getReturnMoneyType() == 1)
            returnMoneyDetailType = "计划回款";
        else if (returnMoneyDetail.getReturnMoneyType() == 2)
            returnMoneyDetailType = "实际回款";
        else returnMoneyDetailType = "开票回款";
        if (id == null) {
            String returnId = returnMoneyDetailService.insertReturnMoneyDetail(returnMoneyDetail);
            LogUtils.logCRMReturnMoneyDetail("添加了" + returnMoneyDetailType, returnId, user);
        } else {
            returnMoneyDetailService.updateReturnMoneyDetail(returnMoneyDetail);
            LogUtils.logCRMReturnMoneyDetail("更新了" + returnMoneyDetailType, id, user);
        }

        return mav;
    }
}
