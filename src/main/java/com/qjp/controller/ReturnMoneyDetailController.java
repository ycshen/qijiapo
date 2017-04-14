package com.qjp.controller;

import com.qjp.entity.UserEntity;
import com.qjp.service.*;
import com.qjp.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value = "/addPlan", method = RequestMethod.GET)
    public ModelAndView addPlan(String contractId, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("/returnMoney/returnMoney_plan_edit");
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

}
