<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
    <meta charset="UTF-8">
    <title>市场活动管理</title>

    <link href="${ctx}/js/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/css/common.css" rel="stylesheet">
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
    <%@include file="../share/common_js.jsp" %>
    <%@page language="java" import="java.util.Date" %>
    <script src="${ctx}/js/pages/common/province_city_area.js"></script>
    <script type="text/javascript" src="${ctx}/js/pages/activity/activity_edit.js"></script>
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
    <input type="hidden" id="hidAttnId" name="id" value="${activity.id}"/>
    <input type="hidden" id="hidBeyondDeptId" name="departmentId" value="${activity.departmentId}"/>
    <input type="hidden" id="hidBeyondDeptName" name="departmentName" value="${activity.departmentName}"/>

    <div class="container content_div">
        <div class="layui-form-item my-layui-form-item my-top">
            <label class="layui-form-label">业务类型<span style="color:red">*</span></label>
            <div class="layui-input-block">
                <input type="text" name="activityType" autocomplete="off" value="默认业务类型" class="layui-input"
                       style="border:0px;"
                       disabled="disabled">
            </div>
        </div>
        <div class="layui-form-item my-layui-form-item my-top">
            <label class="layui-form-label">市场活动所有人<span style="color:red">*</span></label>
            <div class="layui-input-block">
                <input type="text" autocomplete="off" value="${user.userName}" class="layui-input" style="border:0px;"
                       disabled="disabled">
            </div>
        </div>
        <div class="layui-form-item my-layui-form-item my-top">
            <label class="layui-form-label">市场活动名称<span style="color:red">*</span></label>
            <div class="layui-input-block">
                <input type="text" name="activityName" lay-verify="activityName" autocomplete="off"
                       placeholder="请输入市场活动名称"
                       class="layui-input" maxlength="10" value="${activity.activityName}">
            </div>
        </div>
        <div class="layui-form-item my-layui-form-item my-top">
            <label class="layui-form-label">开始日期<span style="color:red">*</span></label>
            <div class="layui-input-block">
                <input type="text" name="activityStartTime" id="txtStartTime" lay-verify="memoStartTime"
                       placeholder="请选择市场活动开始日期" autocomplete="off" class="layui-input"
                       onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD'})">
            </div>
        </div>
        <div class="layui-form-item my-layui-form-item my-top">
            <label class="layui-form-label">结束日期<span style="color:red">*</span></label>
            <div class="layui-input-block">
                <input type="text" name="activityEndTime" id="txtEndTime" lay-verify="memoStartTime"
                       placeholder="请选择市场活动结束日期" autocomplete="off" class="layui-input"
                       onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD'})">
            </div>
        </div>
        <div class="layui-form-item my-layui-form-item">
            <label class="layui-form-label">所属部门<span style="color:red">*</span></label>
            <div class="layui-input-inline" style="margin-right: 0px;">
                <c:choose>
                    <c:when test="${activity != null && activity.id != '' }">
                        <input type="text" id="txtBeyondDeptName" autocomplete="off" class="layui-input"
                               value="${activity.departmentName}" disabled="disabled">
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
                            <c:when test="${activity != null && activity.id != '' }">
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
                <label class="layui-form-label">活动状态</label>
                <div class="layui-input-block">
                    <select name="activityState"  lay-search="">
                        <option value="">搜索或者选择活动状态</option>
                        <option value="1">已计划</option>
                        <option value="2">进行中</option>
                        <option value="3">已结束</option>
                        <option value="4">中止</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item my-layui-form-item layui-form-text">
                <label class="layui-form-label">活动类型</label>
                <div class="layui-input-block">
                    <select name="activityType"  lay-search="">
                        <option value="">搜索或者选择活动类型</option>
                        </option>
                        <option value="1">广告</option>
                        <option value="2">研讨会/会议</option>
                        <option value="3">电子邮件</option>
                        <option value="4">电话营销</option>
                        <option value="5">公共关系</option>
                        <option value="6">合作伙伴</option>
                        <option value="7">其它</option>
                    </select>
                    </select>
                </div>
            </div>
            <div class="layui-form-item my-layui-form-item my-top">
                <label class="layui-form-label">活动说明</label>
                <div class="layui-input-block">
                    <textarea placeholder="请输入活动说明" class="layui-textarea" name="activityNote"> ${activity.remark} </textarea>
                </div>
            </div>
            <div class="layui-form-item my-layui-form-item my-top">
                <label class="layui-form-label">活动成本（元）</label>
                <div class="layui-input-block">
                    <input type="num" name="activityCost" autocomplete="off" value="${activity.postcode}"
                           placeholder="请输入活动成本"
                           class="layui-input" aria-valuemax="99999999999" autocomplete="off">
                </div>
            </div>
            <div class="layui-form-item my-layui-form-item my-top">
                <label class="layui-form-label">预期收入(元)</label>
                <div class="layui-input-block">
                    <input type="text" name="expectedIncome" autocomplete="off" value="${activity.phoneNum}"
                           placeholder="请输入预期收入"
                           class="layui-input" maxlength="11">
                </div>
            </div>
            <div class="layui-form-item my-layui-form-item my-top">
                <label class="layui-form-label">邀请人数</label>
                <div class="layui-input-block">
                    <input type="text" name="invitationPopulation" autocomplete="off" value="${activity.mobilePhoneNum}"
                           placeholder="请输入邀请人数"
                           class="layui-input" maxlength="11">
                </div>
            </div>
            <div class="layui-form-item my-layui-form-item my-top">
                <label class="layui-form-label">预期响应</label>
                <div class="layui-input-block">
                    <input type="text" name="expectedNum" autocomplete="off" value="${activity.duty}" placeholder="请输入预期响应"
                           class="layui-input" maxlength="10">
                </div>
            </div>
            <div class="layui-form-item my-layui-form-item my-top">
                <label class="layui-form-label">实际人数</label>
                <div class="layui-input-block">
                    <input type="text" name="realNum" autocomplete="off" value="${activity.email}" placeholder="请输入实际人数"
                           class="layui-input" maxlength="40">
                </div>
            </div>
            <div class="layui-form-item my-layui-form-item my-top">
                <label class="layui-form-label">实际成本(元)</label>
                <div class="layui-input-block">
                    <input type="text" name="realActivityCost" autocomplete="off" value="${activity.facsimile}"
                           placeholder="请输入实际成本"
                           class="layui-input">
                </div>
            </div>

        </div>

        <div class="layui-form-item my-layui-form-item layui-form-text">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-block">
                <textarea placeholder="请输入备注" class="layui-textarea" name="remark"> ${activity.remark} </textarea>
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
    <fmt:formatDate value="<%=new Date() %>" pattern="yyyy-MM-dd HH:mm:ss" type="hidden" id="recordTime" name="recordTime"/>
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
            activityName: function (value) {
                if (value.length < 2) {
                    return '市场活动名称的长度不能小于2个字符';
                }

                if (value.length > 30) {
                    return '市场活动名称的长度不能大于30个字符';
                }
            },

        });


        //监听提交
        form.on('submit(mySubmit)', function (data) {
            var url = ctx + "/inner/activity/saveOrUpdate"

            var activity = $('#myForm').serialize();
//            var activity = "realActivityCost=2121212"
            $.ajax({
                cache: true,
                type: "POST",
                url: url,
                data: activity,
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