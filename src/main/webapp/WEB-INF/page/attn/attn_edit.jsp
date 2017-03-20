<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
    <meta charset="UTF-8">
    <title>联系人管理</title>

    <link href="${ctx}/js/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/css/common.css" rel="stylesheet">
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
    <%@include file="../share/common_js.jsp" %>
    <script src="${ctx}/js/pages/common/province_city_area.js"></script>
    <script type="text/javascript" src="${ctx}/js/pages/attn/attn_edit.js"></script>
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
    <input type="hidden" id="hidAttnId" name="id" value="${attn.id}"/>
    <input type="hidden" id="hidBeyondDeptId" name="departmentId" value="${attn.departmentId}"/>
    <input type="hidden" id="hidBeyondDeptName" name="departmentName" value="${attn.departmentName}"/>

    <div class="container content_div">
        <div class="layui-form-item my-layui-form-item my-top">
            <label class="layui-form-label">联系人所有人<span style="color:red">*</span></label>
            <div class="layui-input-block">
                <input type="text" autocomplete="off" value="${user.userName}" class="layui-input" style="border:0px;"
                       disabled="disabled">
            </div>
        </div>
        <div class="layui-form-item my-layui-form-item my-top">
            <label class="layui-form-label">联系人名称<span style="color:red">*</span></label>
            <div class="layui-input-block">
                <input type="text" name="attnName" lay-verify="attnName" autocomplete="off" placeholder="请输入联系人名称"
                       class="layui-input" maxlength="10" value="${attn.attnName}">
            </div>
        </div>
        <div class="layui-form-item my-layui-form-item">
            <label class="layui-form-label">所属部门<span style="color:red">*</span></label>
            <div class="layui-input-inline" style="margin-right: 0px;">
                <c:choose>
                    <c:when test="${attn != null && attn.id != '' }">
                        <input type="text" id="txtBeyondDeptName" autocomplete="off" class="layui-input"
                               value="${attn.departmentName}" disabled="disabled">
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
                            <c:when test="${attn != null && attn.id != '' }">
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
            <div class="layui-form-item my-layui-form-item">
                <label class="layui-form-label">省市区</label>
                <div class="layui-input-block">
                    <div class="layui-input-inline" style="width: 120px;">
                        <select name="provinceId" lay-filter="province">
                            <c:choose>
                                <c:when test="${attn != null && attn.id != '' }">
                                    <option value="${attn.provinceId}">${attn.provinceName}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="">请选择省</option>
                                </c:otherwise>
                            </c:choose>
                        </select>
                    </div>
                    <div class="layui-input-inline" style="width: 120px;">
                        <select name="cityId" lay-filter="city">
                            <c:choose>
                                <c:when test="${attn != null && attn.id != '' }">
                                    <option value="${attn.cityId}">${attn.cityName}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="">请选择市</option>
                                </c:otherwise>
                            </c:choose>
                        </select>
                    </div>
                    <div class="layui-input-inline" style="width: 120px;">
                        <select name="areaId" lay-filter="area">
                            <c:choose>
                                <c:when test="${attn != null && attn.id != '' }">
                                    <option value="${attn.areaId}">${attn.areaName}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="">请选择县/区</option>
                                </c:otherwise>
                            </c:choose>

                        </select>
                    </div>
                </div>
            </div>
            <div class="layui-form-item my-layui-form-item my-top">
                <label class="layui-form-label">地址</label>
                <div class="layui-input-block">
                    <input type="text" name="address" lay-verify="address" value="${attn.address}" autocomplete="off"
                           placeholder="请输入地址" class="layui-input" maxlength="50">
                </div>
            </div>
            <div class="layui-form-item my-layui-form-item my-top">
                <label class="layui-form-label">邮政编码</label>
                <div class="layui-input-block">
                    <input type="num" name="postcode" autocomplete="off" value="${attn.postcode}" placeholder="请输入邮政编码"
                           class="layui-input" maxlength="6">
                </div>
            </div>
            <div class="layui-form-item my-layui-form-item my-top">
                <label class="layui-form-label">电话</label>
                <div class="layui-input-block">
                    <input type="text" name="phoneNum" autocomplete="off" value="${attn.phoneNum}" placeholder="请输入电话"
                           class="layui-input" maxlength="11">
                </div>
            </div>
            <div class="layui-form-item my-layui-form-item my-top">
                <label class="layui-form-label">手机</label>
                <div class="layui-input-block">
                    <input type="text" name="mobilePhoneNum" autocomplete="off" value="${attn.mobilePhoneNum}" placeholder="请输入电话"
                           class="layui-input" maxlength="11">
                </div>
            </div>
            <div class="layui-form-item my-layui-form-item my-top">
                <label class="layui-form-label">职务</label>
                <div class="layui-input-block">
                    <input type="text" name="duty" autocomplete="off" value="${attn.duty}" placeholder="请输入传真"
                           class="layui-input" maxlength="10">
                </div>
            </div>
            <div class="layui-form-item my-layui-form-item my-top">
                <label class="layui-form-label">邮箱</label>
                <div class="layui-input-block">
                    <input type="text" name="email" autocomplete="off" value="${attn.email}" placeholder="请输入公司网址"
                           class="layui-input" maxlength="40">
                </div>
            </div>
            <div class="layui-form-item my-layui-form-item my-top">
                <label class="layui-form-label">传真</label>
                <div class="layui-input-block">
                    <input type="text" name="facsimile" autocomplete="off" value="${attn.facsimile}" placeholder="请输入公司网址"
                           class="layui-input" >
                </div>
            </div>
            <div class="layui-form-item my-layui-form-item my-top">
                <label class="layui-form-label">微博</label>
                <div class="layui-input-block">
                    <input type="text" name="weibo" autocomplete="off" value="${attn.weibo}" placeholder="请输入微博"
                           class="layui-input" >
                </div>
            </div>
            <div class="layui-form-item my-layui-form-item my-top">
                <label class="layui-form-label">性别</label>
                <div class="layui-input-block">
                    <input type="text" name="gender" autocomplete="off" value="${attn.gender}" placeholder="请输入总人数"
                           class="layui-input" maxlength="10">
                </div>
            </div>
            <div class="layui-form-item my-layui-form-item my-top">
                <label class="layui-form-label">生日</label>
                <div class="layui-input-block">
                    <input type="num" name="birthDay" autocomplete="off" value="${attn.birthDay}"
                           placeholder="请输入上年销售额" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item my-layui-form-item layui-form-text">
                <label class="layui-form-label">备注</label>
                <div class="layui-input-block">
                    <textarea placeholder="请输入备注" class="layui-textarea" name="remark"> ${attn.remark} </textarea>
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

    <input type="hidden" value="" id="hidProvinceName" name="provinceName"/>
    <input type="hidden" value="" id="hidCityName" name="cityName"/>
    <input type="hidden" value="" id="hidAreaName" name="areaName"/>
</form>

<script>
    layui.use(['form', 'layedit', 'laydate'], function () {
        var form = layui.form()
            , layer = layui.layer
            , layedit = layui.layedit
            , laydate = layui.laydate;

        //自定义验证规则
        form.verify({
            attnName: function (value) {
                if (value.length < 2) {
                    return '联系人名称的长度不能小于2个字符';
                }

                if (value.length > 30) {
                    return '联系人名称的长度不能大于00个字符';
                }
            },

        });


        //监听提交
        form.on('submit(mySubmit)', function (data) {
            var url = ctx + "/inner/attn/saveOrUpdate"
            var provinceName = $("select[name='provinceId']").find("option:selected").text();
            if (provinceName != "请选择省") {
                $("#hidProvinceName").val(provinceName);
            }

            var cityName = $("select[name='cityId']").find("option:selected").text();
            if (cityName != "请选择市") {
                $("#hidCityName").val(cityName);
            }

            var areaName = $("select[name='areaId']").find("option:selected").text();
            if (areaName != "请选择县/区") {
                $("#hidAreaName").val(areaName);
            }


            var attn = $('#myForm').serialize();
//            var attn = "provinceId=123"
            $.ajax({
                cache: true,
                type: "POST",
                url: url,
                data: attn,
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