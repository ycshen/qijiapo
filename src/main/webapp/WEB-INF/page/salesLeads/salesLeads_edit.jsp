<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
    <meta charset="UTF-8">
    <title>产品管理</title>

    <link href="${ctx}/js/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/css/common.css" rel="stylesheet">
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
    <%@include file="../share/common_js.jsp" %>
    <script src="${ctx}/js/pages/common/province_city_area.js"></script>
    <script type="text/javascript" src="${ctx}/js/pages/salesLeads/salesLeads_edit.js"></script>
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
    <input type="hidden" id="hidProductId" name="id" value="${salesLeads.id}"/>
    <input type="hidden" id="hidBeyondDeptId" name="departmentId" value="${salesLeads.departmentId}"/>
    <input type="hidden" id="hidBeyondDeptName" name="departmentName" value="${salesLeads.departmentName}"/>
    <div class="container content_div">
        <div class="layui-form-item my-layui-form-item my-top">
            <label class="layui-form-label">销售线索所有人<span style="color:red">*</span></label>
            <div class="layui-input-block">
                <input type="text" autocomplete="off" value="${user.companyName}" class="layui-input"
                       style="border:0px;" disabled="disabled">
            </div>
        </div>
        <div class="layui-form-item my-layui-form-item my-top">
            <label class="layui-form-label">名称<span style="color:red">*</span></label>
            <div class="layui-input-block">
                <input type="text" name="customerName" lay-verify="customerName" autocomplete="off" placeholder="请输入名称"
                       class="layui-input" value="${salesLeads.customerName}">
            </div>
        </div>
        <div class="layui-form-item my-layui-form-item">
            <label class="layui-form-label">公司名称<span style="color:red">*</span></label>
            <div class="layui-input-block">
                <input type="text" name="customerCompanyName" lay-verify="customerCompanyName" autocomplete="off"
                       lay-verify="customerCompanyName"
                       placeholder="请输入公司名称" class="layui-input" maxlength="10"
                       value="${salesLeads.customerCompanyName}">

            </div>
        </div>
        <div class="layui-form-item my-layui-form-item">
            <label class="layui-form-label">所属部门<span style="color:red">*</span></label>
            <div class="layui-input-inline" style="margin-right: 0px;">
                <c:choose>
                    <c:when test="${salesLeads != null && salesLeads.id != '' }">
                        <input type="text" id="txtBeyondDeptName" autocomplete="off" class="layui-input"
                               value="${salesLeads.departmentName}" disabled="disabled">
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
                            <c:when test="${salesLeads != null && salesLeads.id != '' }">
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

            <div class="layui-form-item my-layui-form-item my-top">
                <label class="layui-form-label">跟进状态</label>
                <div class="layui-input-block">
                    <select name="followUpStatus" lay-search="">
                        <option value="">请选择跟进状态</option>
                        <option value="1">未处理</option>
                        <option value="2">已联系</option>
                        <option value="3">关闭</option>
                    </select>
                </div>

            </div>
            <div class="layui-form-item my-layui-form-item my-top">
                <label class="layui-form-label">性别</label>
                <%--<input class="layui-input-block">--%>
                <%--<input type="text" name="gender" autocomplete="off" value="${attn.gender}" placeholder="请输入总人数"--%>
                <%--class="layui-input" maxlength="10">--%>
                <label><input name="gender" type="radio" value="0" checked="checked" title="男"/></label>
                <label><input name="gender" type="radio" value="1" title="女"/></label>
            </div>
            <div class="layui-form-item my-layui-form-item my-top">
                <label class="layui-form-label">部门</label>
                <div class="layui-input-block">
                    <input type="text" name="customerDepartmentName" autocomplete="off"
                           value="${salesLeads.customerDepartmentName}"
                           placeholder="请输入部门" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item my-layui-form-item my-top">
                <label class="layui-form-label">职务</label>
                <div class="layui-input-block">
                    <input type="text" name="customerDuty" autocomplete="off" value="${salesLeads.customerDuty}"
                           placeholder="请输入职务" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item my-layui-form-item my-top">
                <label class="layui-form-label">电话</label>
                <div class="layui-input-block">
                    <input type="text" name="customerPhoneNum" autocomplete="off" value="${salesLeads.customerPhoneNum}"
                           placeholder="请输入电话" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item my-layui-form-item my-top">
                <label class="layui-form-label">手机</label>
                <div class="layui-input-block">
                    <input type="text" name="customerMobileNum" autocomplete="off"
                           value="${salesLeads.customerMobileNum}"
                           placeholder="请输入手机" class="layui-input" maxlength="11">
                </div>
            </div>
            <div class="layui-form-item my-layui-form-item my-top">
                <label class="layui-form-label">电子邮件</label>
                <div class="layui-input-block">
                    <input type="text" name="email" autocomplete="off" value="${salesLeads.email}"
                           placeholder="请输入电子邮件" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item my-layui-form-item my-top">
                <label class="layui-form-label">微博</label>
                <div class="layui-input-block">
                    <input type="text" name="weibo" autocomplete="off" value="${salesLeads.weibo}"
                           placeholder="请输入微博" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item my-layui-form-item my-top">
                <label class="layui-form-label">省份</label>
                <div class="layui-input-block">
                    <input type="text" name="provinceName" autocomplete="off" value="${salesLeads.provinceName}"
                           placeholder="请输入省份" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item my-layui-form-item my-top">
                <label class="layui-form-label">地址</label>
                <div class="layui-input-block">
                    <input type="text" name="address" autocomplete="off" value="${salesLeads.address}"
                           placeholder="请输入地址" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item my-layui-form-item my-top">
                <label class="layui-form-label">邮政编码</label>
                <div class="layui-input-block">
                    <input type="num" name="postcode" autocomplete="off" value="${salesLeads.postcode}"
                           placeholder="请输入邮政编码" class="layui-input" maxlength="6">
                </div>
            </div>
            <div class="layui-form-item my-layui-form-item my-top">
                <label class="layui-form-label">传真</label>
                <div class="layui-input-block">
                    <input type="text" name="facsimile" autocomplete="off" value="${salesLeads.facsimile}"
                           placeholder="请输入传真" class="layui-input" maxlength="10">
                </div>
            </div>
            <div class="layui-form-item my-layui-form-item my-top">
                <label class="layui-form-label">线索来源</label>
                <div class="layui-input-block">
                    <select name="clueSource" lay-search="">
                        <option value="">请选择线索来源</option>
                        <option value="1">广告</option>
                        <option value="2">研讨会</option>
                        <option value="3">搜索引擎</option>
                        <option value="4">客户介绍</option>
                        <option value="5">其它</option>
                    </select>
                </div>

            </div>
        </div>
        <div class="layui-form-item my-layui-form-item layui-form-text">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-block">
                <textarea placeholder="请输入备注" class="layui-textarea" name="remark"> ${salesLeads.remark} </textarea>
            </div>
        </div>
    </div>

    <div class="layui-form-item my-top">
        <div class="layui-input-block" style="text-align:right;">
            <button class="layui-btn" lay-submit="" lay-filter="mySubmit">保存</button>
            <button class="layui-btn layui-btn-primary" style="margin-right:50px;" onclick="cancelEdit();">取消</button>
        </div>
    </div>

    <%--<input type="hidden" value="" id="hidProvinceName" name="provinceName"/>--%>
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
            customerName: function (value) {
                if (value.length < 2) {
                    return '产品名称的长度不能小于2个字符';
                }

                if (value.length > 30) {
                    return '产品名称的长度不能大于30个字符';
                }
            },

        });


        //监听提交
        form.on('submit(mySubmit)', function (data) {
            var url = ctx + "/inner/salesLeads/saveOrUpdate"


            var salesLeads = $('#myForm').serialize();
            $.ajax({
                cache: true,
                type: "POST",
                url: url,
                data: salesLeads,
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