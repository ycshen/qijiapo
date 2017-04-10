package com.qjp.controller;

import com.qjp.base.ResponseStatus;
import com.qjp.base.RoleEnum;
import com.qjp.entity.LogEntity;
import com.qjp.entity.ContractEntity;
import com.qjp.entity.UserEntity;
import com.qjp.service.*;
import com.qjp.util.JsonUtils;
import com.qjp.util.LogUtils;
import com.qjp.util.StringUtils;
import com.qjp.util.UserUtils;
import com.qjp.util.query.LogQuery;
import com.qjp.util.query.ContractQuery;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by fengyue on 2017/3/24.
 */
@Controller
@RequestMapping("/inner/contract")
public class ContractController {
    @Autowired
    private ContractService contractService;

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private LogService logService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public ModelAndView list(@ModelAttribute ContractQuery contractQuery, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("/contract/contract_list");
        mav.addObject("contractQuery", contractQuery);
        return mav;
    }


    @RequestMapping(value = "/listAjax", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String listAjax(@ModelAttribute ContractQuery contractQuery, HttpServletRequest request){
        contractQuery.init(request);
        String roleType = contractQuery.getRoleType();
        if(RoleEnum.DEP.getRoleId().toString().equals(roleType)){
            String idList = departmentService.getSubDepList(contractQuery.getDepartmentId(), contractQuery.getCompanyId());
            contractQuery.setDepartmentId(idList);
        }

        contractQuery = contractService.getContractPage(contractQuery);
        String jsonStr = JsonUtils.json2Str(contractQuery);

        return jsonStr;
    }
    @RequestMapping(value = "/forwardEdit", method = RequestMethod.GET)
    public ModelAndView forwardEdit(String id, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("/contract/contract_edit");
        UserEntity user = UserUtils.getLoginUser(request);
        ContractEntity contract = null;
        if(StringUtils.isNotBlank(id)){
            contract = contractService.getContractById(id);
        }

        mav.addObject("contract", contract);
        mav.addObject("user", user);
        return mav;
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public ModelAndView detail(String id, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("/contract/contract_detail");
        UserEntity user = UserUtils.getLoginUser(request);
        mav.addObject("user", user);
        ContractEntity contract = contractService.getContractById(id);
        mav.addObject("contract", contract);
        double notReturnMoney = contract.getTotalPrice();
        if (!TextUtils.isBlank(contract.getReturnMoney())) {
            notReturnMoney -= Float.parseFloat(contract.getReturnMoney());
        }
        contract.setNotReturnMoney(notReturnMoney + "");
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
            contractService.deleteContractById(id);
            UserEntity user = UserUtils.getLoginUser(request);
            LogUtils.logCRMContract("删除了合同(" + name + ")", id, user);
            result = ResponseStatus.UPDATE_SUCCESS;
        }

        return result;
    }

    @RequestMapping(value = "/transferContract", method = RequestMethod.GET)
    @ResponseBody
    public Integer transferContract(String userId, String contractId, String transferType, HttpServletRequest request){
        Integer result = ResponseStatus.INIT;
        if(StringUtils.isNotBlank(userId)){
            UserEntity transferToUser = userService.getUserById(userId);
            UserEntity loginUser = UserUtils.getLoginUser(request);
            if("1".equals(transferType)){
                this.transferContract(contractId, transferToUser, loginUser);
            }else if("2".equals(transferType)){
                String[] idArr = contractId.split("\\,");
                for (String id : idArr) {
                    this.transferContract(id, transferToUser, loginUser);
                }
            }

            result = ResponseStatus.UPDATE_SUCCESS;

        }

        return result;
    }

    private void transferContract(String contractId, UserEntity transferToUser, UserEntity loginUser){
        ContractEntity oldContract = contractService.getContractById(contractId);
        oldContract.setUserId(transferToUser.getId().toString());
        String transferUserName = transferToUser.getUserName();
        oldContract.setUserName(transferUserName);
        oldContract.setUpdateTime(new Date());
        oldContract.setUpdateUser(loginUser.getUserName());
        contractService.updateContract(oldContract);
        LogUtils.logCRMContract("删除了合同(" + oldContract.getContractName() + ")到" + transferUserName, contractId, loginUser);
    }

    @RequestMapping(value = "/batchDeleteById", method = RequestMethod.GET)
    @ResponseBody
    public Integer batchDeleteById(String ids, HttpServletRequest request){
        Integer result = ResponseStatus.INIT;
        if(StringUtils.isNotBlank(ids)){
            String[] idArr = ids.split("\\,");
            List<String> idList =  Arrays.asList(idArr);
            contractService.batchDeleteContract(idList);
            UserEntity user = UserUtils.getLoginUser(request);
            for (String id : idList) {
                LogUtils.logCRMProduct("删除了合同", id, user);
            }
            result = ResponseStatus.UPDATE_SUCCESS;
        }

        return result;
    }

    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    public ModelAndView saveOrUpdate(@ModelAttribute ContractEntity contract, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("/contract/contract_edit");
        contract.init(request); //初始化公司、部门、用户信息
        String provinceId = contract.getProvinceId();
        contract.setProvinceId(StringUtils.splitLocation(provinceId));
        String cityId = contract.getCityId();
        contract.setCityId(StringUtils.splitLocation(cityId));
        String areaId = contract.getAreaId();
        contract.setAreaId(StringUtils.splitLocation(areaId));
        Long id = contract.getId();
        UserEntity user = UserUtils.getLoginUser(request);
        if(id == null){
            String returnId = contractService.insertContract(contract);
            LogUtils.logCRMContract("添加了产品(" + contract.getContractName() + ")", returnId, user);
        }else{
            contractService.updateContract(contract);
            LogUtils.logCRMContract("修改了产品(" + contract.getContractName() + ")", id.toString(), user);
        }

        return mav;
    }

}
