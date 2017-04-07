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

    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    public Integer saveOrUpdate(@ModelAttribute ReturnMoneyDetailEntity returnMoneyDetailEntity, HttpServletRequest request){
        UserEntity user = UserUtils.getLoginUser(request);
        returnMoneyDetailEntity.setUserId(user.getId().toString());
        returnMoneyDetailEntity.setUserName(user.getUserName());
        String returnMoneyId = returnMoneyDetailEntity.getReturnMoneyId();
        if (!TextUtils.isBlank(returnMoneyId)){//判断回款详情是否有回款，有回款则插入回款详情，更新回款，无则新增回款，插入回款详情
            ReturnMoneyEntity returnMoneyEntity = returnMoneyService.getReturnMoneyById(returnMoneyId);

            returnMoneyService.insertReturnMoney(returnMoneyEntity);
            returnMoneyDetailService.insertReturnMoneyDetail(returnMoneyDetailEntity);

            //回款类型（1-计划  2-实际  3-开票）
            switch (returnMoneyDetailEntity.getReturnMoneyType()){
                case 1:
                    returnMoneyEntity.setPlanReturnMoney(returnMoneyDetailEntity.getMoney());
                    returnMoneyEntity.setPlanReturnDate(returnMoneyDetailEntity.getStartDate());
                    returnMoneyService.updateReturnMoney(returnMoneyEntity);
                    break;
                case 2:
                    returnMoneyEntity.setActualReturnMoney(returnMoneyDetailEntity.getMoney());
                    returnMoneyEntity.setActualReturnDate(returnMoneyDetailEntity.getStartDate());
                    returnMoneyService.updateReturnMoney(returnMoneyEntity);
                    //实际回款，修改合同回款信息
                    String contractId = returnMoneyEntity.getContractId();
                    String newReturnMoney = returnMoneyEntity.getActualReturnMoney();
                    if (StringUtils.isNotBlank(newReturnMoney)){
                        ContractEntity contract = contractService.getContractById(contractId);
                        if (contract != null){
                            String rm = contract.getReturnMoney();
                            Float oldRM = 0f;
                            if (StringUtils.isNotBlank(rm)){
                                oldRM = Float.parseFloat(rm);
                            }
                            oldRM += Float.parseFloat(newReturnMoney);
                            contractService.updateReturnMoneyById(contractId,oldRM.toString());
                            LogUtils.log(LogUtils.RETURN_MONEY,"添加回款后更新合同对应金额【添加前回款金额（元）" + rm + ",添加后回款金额（元）" + oldRM + "]",contractId,"回款ID",  UserUtils.getLoginUser(request));
                        }
                    }
                    break;

            }


        }else {
            ReturnMoneyEntity returnMoneyEntity = new ReturnMoneyEntity();
        }

        return 1;
    }
}
