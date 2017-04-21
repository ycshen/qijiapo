<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
    <meta charset="UTF-8">
    <title>客户管理</title>

    <link href="${ctx}/js/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/css/common.css" rel="stylesheet">
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
    <%@include file="../share/common_js.jsp"%>
    <script src="${ctx}/js/pages/common/province_city_area.js"></script>
    <script type="text/javascript" src="${ctx}/js/pages/returnMoney/returnMoney_plan_edit.js"></script>
    <script type="text/javascript">
        var ctx = "${pageContext.request.contextPath}";
    </script>
    <style type="text/css">
        .my-layui-form-item{
            margin: 0px 15px 0px 0px;
            clear: both;
        }
        .layui-form-label {
            float: left;
            display: block;
            width: 150px;
            font-weight: 400;
            text-align: right;
            padding: 9px 15px;
        }
        .layui-input-block {
            margin-left: 150px;
            min-height: 36px;
        }
        .my-top{
            margin-top: 10px;
        }
        .content_div{
            height: 500px;
            overflow-y:auto;
        }
        .qjp_oper{
            position:fixed; top: 0; left: 0;background-color:#F8F8F8;width: 100%;z-index: 999;
        }
    </style>
</head>
<body style="background: #fff;overflow-y:auto;">

<form class="layui-form" id="myForm" onsubmit="return false;">
    <input type="hidden" id="hidProductId" name="id" value="${customer.id}"/>
    <div class="layui-form-item qjp_oper">
        <div class="layui-input-block" style="text-align:right;">
            <button class="layui-btn" lay-submit="" lay-filter="mySubmit">保存</button>
            <button class="layui-btn layui-btn-primary" style="margin-right:50px;" onclick="cancelEdit();">取消</button>
        </div>
    </div>
    <div class="container" style="margin-top: 50px;">
        <div class="layui-form-item my-layui-form-item my-top">
            <label class="layui-form-label">回款期次<span style="color:red">*</span></label>
            <div class="layui-input-block">
                <input type="text" class="layui-input"  value="${returnMoneyNum}" disabled="disabled" style="border:0px;">

            </div>
        </div>
        <div class="layui-form-item my-layui-form-item my-top">
            <label class="layui-form-label">实际回款金额<span style="color:red">*</span></label>
            <div class="layui-input-block">
                <input type="text" name="money" lay-verify="money" autocomplete="off" placeholder="请输入计划回款金额" class="layui-input"  value="${customer.mobile}">
            </div>
        </div>
        <div class="layui-form-item my-layui-form-item my-top">
            <label class="layui-form-label">实际回款日期<span style="color:red">*</span></label>
            <div class="layui-input-block">
                <input type="text" name="returnMoneyTime" lay-verify="returnMoneyTime"
                       autocomplete="off" placeholder="请输入计划回款日期" class="layui-input"
                       onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD'})"
                       dataformatas="YYYY-MM-DD" value="${customer.mobile}">
            </div>
        </div>
        <div class="layui-form-item my-layui-form-item layui-form-text">
                <label class="layui-form-label">付款方式</label>
                <div class="layui-input-block">
                    <select name="contractType" lay-search="" >
                        <option value="">请选择付款方式</option>
                        <option value="1" <c:if test="${contract.contractType == 1}">selected</c:if>>支票</option>
                        <option value="2" <c:if test="${contract.contractType == 2}">selected</c:if>>现金</option>
                        <option value="3" <c:if test="${contract.contractType == 3}">selected</c:if>>银行转账</option>
                        <option value="4" <c:if test="${contract.contractType == 4}">selected</c:if>>支付宝</option>
                        <option value="5" <c:if test="${contract.contractType == 5}">selected</c:if>>微信</option>
                        <option value="5" <c:if test="${contract.contractType == 5}">selected</c:if>>其他</option>
                    </select>
                </div>
            </div>
        <div class="layui-form-item my-layui-form-item my-top">
            <label class="layui-form-label">所有人<span style="color:red">*</span></label>
            <div class="layui-input-block">
                <select name="userId"  lay-search="" lay-verify="userId">
                    <option value="">搜索或者选择所有人</option>
                    <c:if test="${userList != null && userList.size() > 0}">
                        <c:forEach items="${userList}" var="userInfo">
                            <option <c:if test="${userInfo.id == user.id}">selected</c:if> value="${userInfo.userName}">${userInfo.userName}(${userInfo.departmentName})</option>
                        </c:forEach>
                    </c:if>

                </select>    </div>
        </div>

        <div class="layui-form-item my-layui-form-item layui-form-text">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-block">
                <textarea placeholder="请输入备注" class="layui-textarea" name="remark">${customer.remark}</textarea>
            </div>
        </div>

    </div>
    <input type="hidden" value="" id="hidAreaName" name="areaName"/>
</form>

<script>
    layui.use(['form', 'layedit', 'laydate'], function(){
        var form = layui.form()
                , layer = layui.layer
                , layedit = layui.layedit
                , laydate = layui.laydate;

        //自定义验证规则
        form.verify({

            userId: function(value){
                if(value == null || value == '' || value == undefined){
                    return '请选择所有人';
                }
            },
            money: function(value){
                if(value == null || value == '' || value == undefined){
                    return '请输入计划回款金额';
                }
            },
            returnMoneyTime: function(value){
                if(value == null || value == '' || value == undefined){
                    return '请输入计划回款日期';
                }
            }
        });



        //监听提交
        form.on('submit(mySubmit)', function(data){
            var obj = new Object();
            obj.returnMoneyTime = $("input[name='returnMoneyTime']").val();
            obj.returnMoneyType = 2;
            obj.money = $("input[name='money']").val();
            parent.addPlanSuccess(obj);
            return false;
        });


    });
</script>

</body>
</html>