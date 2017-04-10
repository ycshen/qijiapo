package com.qjp.controller;

import com.qjp.entity.ContractEntity;
import com.qjp.entity.ReturnMoneyDetailEntity;
import com.qjp.entity.ReturnMoneyEntity;
import com.qjp.entity.UserEntity;
import com.qjp.service.*;
import com.qjp.util.LogUtils;
import com.qjp.util.StringUtils;
import com.qjp.util.UserUtils;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

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

    @RequestMapping(value = "/forwardEdit", method = RequestMethod.GET)
    public ModelAndView forwardEdit(String contractId, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("/returnMoney/returnMoney_plan_edit");
        UserEntity user = UserUtils.getLoginUser(request);
        ReturnMoneyDetailEntity detailEntity = null;
        if(StringUtils.isNotBlank(contractId)){
//            contract = returnMoneyDetailService.(id);
        }

        mav.addObject("returnMoneyDetail", detailEntity);
        mav.addObject("contractId",contractId);
        mav.addObject("user", user);
        return mav;
    }
}
