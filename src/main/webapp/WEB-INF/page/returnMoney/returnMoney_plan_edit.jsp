<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
    <meta charset="UTF-8">
    <title>新增计划回款</title>

    <link href="${ctx}/js/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/css/common.css" rel="stylesheet">
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
    <%@include file="../share/common_js.jsp" %>
    <%@page language="java" import="java.util.Date" %>
    <script src="${ctx}/js/pages/common/province_city_area.js"></script>
    <script type="text/javascript" src="${ctx}/js/pages/contract/contract_edit.js"></script>
    <script type="text/javascript">
        var ctx = "${pageContext.request.contextPath}";
    </script>
    <style type="text/css">
        .my-layui-form-item {
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

        .my-top {
            margin-top: 10px;
        }

        .content_div {
            height: 300px;
            overflow-y: auto;
        }
    </style>
</head>
<body style="background: #fff;">

<form class="layui-form" id="myForm" onsubmit="return false;">
    <%--<input type="hidden" id="hidAttnId" name="id" value="${contract.id}"/>--%>
    <input type="hidden" id="departmentId" name="departmentId" value="${contract.departmentId}"/>
    <input type="hidden" id="departmentName" name="departmentName" value="${contract.departmentName}"/>

    <div class="container content_div">
        <div class="layui-form-item my-layui-form-item my-top">
            <label class="layui-form-label">回款期次<span style="color:red">*</span></label>

            <div class="layui-input-block">
                <input type="text" name="returnMoneyNum" autocomplete="off" value="1" class="layui-input"
                       style="border:0px;"
                       disabled="disabled">
                <%--<input type="hidden" name="contractType" value="0" class="layui-input" style="border:0px;" disabled="disabled">默认业务类型--%>
            </div>
        </div>

        <div class="layui-form-item my-layui-form-item my-top">
            <label class="layui-form-label">计划回款金额<span style="color:red">*</span></label>
            <div class="layui-input-block">
                <input type="text" name="planReturnMoney" lay-verify="planReturnMoney" autocomplete="off"
                       placeholder="请输入计划回款金额"
                       class="layui-input" maxlength="20" value="${returnMoneyDetail.money}">
            </div>
        </div>
        <div class="layui-form-item my-layui-form-item my-top">
            <div class="layui-form-item my-layui-form-item">
                <label class="layui-form-label">计划回款日期<span style="color:red">*</span></label>
                <div class="layui-input-block">
                    <input type="text" name="startDate" id="startDate" lay-verify="startDate"
                           placeholder="请选择计划回款日期" autocomplete="off" class="layui-input"
                           dataformatas="YYYY-MM-DD"
                           value="${returnMoneyDetail.startDate}"
                           onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD'})">
                </div>
            </div>
            <div class="layui-form-item my-layui-form-item my-top">
                <label class="layui-form-label">所有人<span style="color:red">*</span></label>
                <div class="layui-input-block">
                    <input type="text" autocomplete="off" value="${user.userName}" class="layui-input"
                           style="border:0px;"
                           disabled="disabled">
                </div>
            </div>


            <div class="layui-form-item my-layui-form-item layui-form-text">
                <label class="layui-form-label">备注</label>
                <div class="layui-input-block">
                    <textarea placeholder="请输入备注" class="layui-textarea"
                              name="remark"> ${returnMoneyDetail.remark} </textarea>
                </div>
            </div>
        </div>
    </div>
    <div class="layui-form-item my-top">
        <div class="layui-input-block" style="text-align:right;">
            <button class="layui-btn" lay-submit="" lay-filter="mySubmit">保存</button>
            <button class="layui-btn layui-btn-primary" style="margin-right:50px;" onclick="cancelEdit();">取消</button>
        </div>
    </div>
    <%--<fmt:formatDate value="<%=new Date() %>" pattern="yyyy-MM-dd HH:mm:ss" type="hidden" id="recordTime"--%>
    <%--name="recordTime"/>--%>
    <input type="hidden" value="${contractId}" id="contractId" name="contractId"/>
    <%--<input type="hidden" value="" id="hidCityName" name="cityName"/>--%>
    <%--<input type="hidden" value="" id="hidAreaName" name="areaName"/>--%>
</form>

<script>
    layui.use(['form', 'layedit', 'laydate'], function () {
        var form = layui.form()
            , layer = layui.layer
            , layedit = layui.layedit
            , laydate = layui.laydate;

        //自定义验证规则
        form.verify({
            planReturnMoney: function (value) {
                if (value < 1) {
                    return '计划金额还未填写';
                }


            },
            startDate: function (value) {
                if (value == null || value == '' || value == undefined) {
                    return '开始时间还未选择';
                }
            },

        });
//        var data = $('#myForm').serialize();
        //监听提交
        form.on('submit(mySubmit)', function (data) {
            data = $('#myForm').serialize();
            parent.addPlanSuccess(data);
        });


    });
</script>

</body>
</html>