<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
    <meta charset="UTF-8">
    <title>合同管理</title>

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
    <input type="hidden" id="hidAttnId" name="id" value="${contract.id}"/>
    <input type="hidden" id="hidBeyondDeptId" name="departmentId" value="${contract.departmentId}"/>
    <input type="hidden" id="hidBeyondDeptName" name="departmentName" value="${contract.departmentName}"/>

    <div class="container content_div">
        <div class="layui-form-item my-layui-form-item my-top">
            <label class="layui-form-label">业务类型<span style="color:red">*</span></label>

            <div class="layui-input-block">
                <input type="text" name="businessType" autocomplete="off" value="0" class="layui-input" style="border:0px;"
                       disabled="disabled">
                <%--<input type="hidden" name="contractType" value="0" class="layui-input" style="border:0px;" disabled="disabled">默认业务类型--%>
            </div>
        </div>
        <div class="layui-form-item my-layui-form-item my-top">
            <label class="layui-form-label">合同所有人<span style="color:red">*</span></label>
            <div class="layui-input-block">
                <input type="text" autocomplete="off" value="${user.userName}" class="layui-input" style="border:0px;"
                       disabled="disabled">
            </div>
        </div>
        <div class="layui-form-item my-layui-form-item my-top">
            <label class="layui-form-label">主题<span style="color:red">*</span></label>
            <div class="layui-input-block">
                <input type="text" name="contractName" lay-verify="contractName" autocomplete="off"
                       placeholder="请输入合同名称"
                       class="layui-input" maxlength="10" value="${contract.contractName}">
            </div>
        </div>
        <div class="layui-form-item my-layui-form-item my-top">
            <div class="layui-form-item my-layui-form-item">
                <label class="layui-form-label">客户名称<span style="color:red">*</span></label>
                <div class="layui-input-inline" style="margin-right: 0px;">
                    <input placeholder="请选择客户名称" type="text" id="txtCustomerName" autocomplete="off"  class="layui-input" value="${contract.customerName}" disabled="disabled">
                    <input type="hidden" value="${contract.customerId}" id="txtCustomerId" name="customerId"/>
                    <input type="hidden" lay-verify="customerName" value="${contract.customerName}" id="txtCustomerNameHide" name="customerName"/>
                </div>
                <button class="layui-btn  layui-btn-primary" onclick="selectCustomer();"><i class="layui-icon">&#xe61f;</i></button>
            </div>
            <div class="layui-form-item my-layui-form-item my-top">
            <label class="layui-form-label">总金额<span style="color:red">*</span></label>
            <div class="layui-input-block">
                <input type="text" name="totalPrice"  autocomplete="off"
                       placeholder="请输入总金额"
                       class="layui-input" maxlength="10" value="${contract.totalPrice}">
            </div>
        </div>
        <div class="layui-form-item my-layui-form-item my-top">
            <label class="layui-form-label">开始日期<span style="color:red">*</span></label>
            <div class="layui-input-block">
                <input type="text" name="contractStartTime" id="contractStartTime" lay-verify="memoStartTime"
                       placeholder="请选择合同开始日期" autocomplete="off" class="layui-input"
                       dataformatas="YYYY-MM-DD"
                       value=""
                       onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD'})">
            </div>
        </div>
        <div class="layui-form-item my-layui-form-item my-top">
            <label class="layui-form-label">结束日期<span style="color:red">*</span></label>
            <div class="layui-input-block">
                <input type="text" name="contractEndTime" id="txtEndTime" lay-verify="memoStartTime"
                       placeholder="请选择合同结束日期" autocomplete="off" class="layui-input"
                       value="${contract.contractEndTime}"
                       onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD'})">
            </div>
        </div>
        <div class="layui-form-item my-layui-form-item">
            <label class="layui-form-label">所属部门<span style="color:red">*</span></label>
            <div class="layui-input-inline" style="margin-right: 0px;">
                <c:choose>
                    <c:when test="${contract != null && contract.id != '' }">
                        <input type="text" id="txtBeyondDeptName" autocomplete="off" class="layui-input"
                               value="${contract.departmentName}" disabled="disabled">
                    </c:when>
                    <c:otherwise>
                        <input type="text" id="txtBeyondDeptName" autocomplete="off" class="layui-input"
                               value="${user.departmentName}" disabled="disabled">
                    </c:otherwise>
                </c:choose>

            </div>
            <button class="layui-btn  layui-btn-primary" onclick="selectDepartment();"><i class="layui-icon">
                &#xe61f;</i></button>
        </div>
        <div id="divViewer">

            <div class="layui-form-item my-layui-form-item" style="margin-top:  20px;">
                <label class="layui-form-label"></label>
                <div class="layui-input-block">
                    <a href="#" onclick="addMoreInfo();" style="color:#009688">

                        <c:choose>
                            <c:when test="${contract != null && contract.id != '' }">
                                编辑更多信息
                            </c:when>
                            <c:otherwise>
                                新增更多信息
                            </c:otherwise>
                        </c:choose>
                    </a>
                </div>
            </div>
        </div>
        <div id="divOther" style="display: none;">
            <div class="layui-form-item my-layui-form-item layui-form-text">
                <label class="layui-form-label">合同类型</label>
                <div class="layui-input-block">
                    <select name="contractType" lay-search="" >
                        <option value="">请选择合同类型</option>
                        <option value="1">产品销售</option>
                        <option value="2">服务</option>
                        <option value="3">业务合作</option>
                        <option value="4">代理分销</option>
                        <option value="5">其它</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item my-layui-form-item layui-form-text">
                <label class="layui-form-label">合同状态</label>
                <div class="layui-input-block">
                    <select name="contractState" lay-search="">
                        <option value="">请选择合同状态</option>
                        <option value="1">执行中</option>
                        <option value="2">结束</option>
                        <option value="3">意外终止</option>
                    </select>
                    </select>
                </div>
            </div>
            <div class="layui-form-item my-layui-form-item layui-form-text">
                <label class="layui-form-label">付款方式</label>
                <div class="layui-input-block">
                    <select name="paymentMethod" lay-search="">
                        <option value="">请选择付款方式</option>
                        <option value="1">支票</option>
                        <option value="2">现金</option>
                        <option value="3">银行转账</option>
                        <option value="4">其它</option>
                    </select>
                    </select>
                </div>
            </div>
            <div class="layui-form-item my-layui-form-item my-top">
                <label class="layui-form-label">合同正文</label>
                <div class="layui-input-block">
                    <textarea placeholder="请输入活动说明" class="layui-textarea"
                              name="contractBody"> ${contract.contractBody} </textarea>
                </div>
            </div>
            <div class="layui-form-item my-layui-form-item my-top">
                <label class="layui-form-label">合同编号</label>
                <div class="layui-input-block">
                    <input type="num" name="contractNum" autocomplete="off" value="${contract.contractNum}"
                           placeholder="请输入合同编号"
                           class="layui-input"  autocomplete="off">
                </div>
            </div>
            <div class="layui-form-item my-layui-form-item my-top">
                <label class="layui-form-label">客户方签约人</label>
                <div class="layui-input-block">
                    <input type="text" name="customerSigner" autocomplete="off" value="${contract.customerSigner}"
                           placeholder="请输入客户方签约人"
                           class="layui-input" maxlength="11">
                </div>
            </div>
            <div class="layui-form-item my-layui-form-item my-top">
                <label class="layui-form-label">我方签约人</label>
                <div class="layui-input-block">
                    <input type="text" name="signerId" autocomplete="off" value="${contract.signerId}"
                           placeholder="请输入我方签约人"
                           class="layui-input" maxlength="11">
                </div>
            </div>
            <div class="layui-form-item my-layui-form-item my-top">
                <label class="layui-form-label">签约日期<span style="color:red">*</span></label>
                <div class="layui-input-block">
                    <input type="text" name="signTime" id="txtSignTime" lay-verify=""
                           placeholder="请选择合同签约日期" autocomplete="off" class="layui-input"
                           value="${contract.signTime}"
                           onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD'})">
                </div>
            </div>

        </div>

        <div class="layui-form-item my-layui-form-item layui-form-text">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-block">
                <textarea placeholder="请输入备注" class="layui-textarea" name="remark"> ${contract.remark} </textarea>
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
    <%--<input type="hidden" value="" id="recordTime" name="recordTime"/>--%>
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
            contractName: function (value) {
                if (value.length < 2) {
                    return '合同名称的长度不能小于2个字符';
                }

                if (value.length > 30) {
                    return '合同名称的长度不能大于30个字符';
                }
            },

        });


        //监听提交
        form.on('submit(mySubmit)', function (data) {
            var url = ctx + "/inner/contract/saveOrUpdate"

            var contract = $('#myForm').serialize();
//            var contract = "contractStartTime=2017-03-27"
            $.ajax({
                cache: true,
                type: "POST",
                url: url,
                data: contract,
                async: false,
                success: function (data) {
                    layer.alert('保存成功',
                        {
                            closeBtn: false,
                            skin: 'layui-layer-molv'
                        },
                        function (index) {
                            parent.refreshTable();
                            parent.layer.closeAll();
                        });
                }
            });
            return false;
        });


    });
</script>

</body>
</html>