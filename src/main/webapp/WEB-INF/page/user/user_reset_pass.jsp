<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<meta charset="UTF-8">
<title>重置密码</title>
<%@include file="../share/common_css.jsp"%>
<link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
<script type="text/javascript">
	var ctx = "${pageContext.request.contextPath}";
</script>
</head>
<body>

<div class="container" style="margin-top: 10px;">
<form class="layui-form" action="" id="myForm">

   <div class="layui-form-item">
    <label class="layui-form-label" style="width:100px;">方式一：</label>
    <div class="layui-input-block">
    
      <input type="text" id="txtPass" autocomplete="off" class="layui-input" style="width: 300px;float:left;" placeholder="请输入8-16位重置密码">
      <button class="layui-btn" type="button"  onclick="resetByPass('${user.id}','${user.userName }');"  style="width: 100px;float:left;">重置</button>
							 
    </div>

  </div>
  <div class="layui-form-item">
    <label class="layui-form-label" style="width:100px;"></label>
    <div class="layui-input-block">
   		 重置后请告知员工，以确保其正常登录				 
    </div>

  </div>
  <div class="layui-form-item">
    <label class="layui-form-label" style="width:100px;">方式二：</label>
    <div class="layui-input-block">
    
      <input type="text" name="email" lay-verify="email" autocomplete="off" class="layui-input" style="width: 300px;float:left;">
      <button class="layui-btn" type="button"  onclick="editUser();"  style="width: 100px;float:left;">发送邮件</button>
							 
    </div>

  </div>
  <div class="layui-form-item">
    <label class="layui-form-label" style="width:100px;"></label>
    <div class="layui-input-block">
    	此方式会以邮件方式向员工发送随机密码。			 
    </div>

  </div>
</form>

</div>	
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/layer/layer.js"></script>
<script type="text/javascript" src="${ctx}/js/pages/user/user_reset_pass.js"></script>
</body>
</html>