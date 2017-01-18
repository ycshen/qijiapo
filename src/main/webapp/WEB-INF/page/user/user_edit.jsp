<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<meta charset="UTF-8">
<title>部门编辑</title>
<%@include file="../share/common_css.jsp"%>
<link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
<script type="text/javascript">
	var ctx = "${pageContext.request.contextPath}";
</script>
</head>
<body>

<div class="container" style="margin-top: 10px;">
<form class="layui-form" method="post" id="myForm">
	<input type="hidden" value="${department.id}" name="id"/>
	<input type="hidden" value="${department.parentDepartmentId}" name="parentDepartmentId" id="hidPid"/>
  <div class="layui-form-item">
    <label class="layui-form-label" style="width:100px;">所属部门</label>
    <div class="layui-input-block">
      <input name="departmentName" id="txtparentDepartmentName"
							value="${department.departmentName}" maxlength="20"
							class="layui-input" type="text" placeholder="请输入上级部门">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label" style="width:100px;">员工姓名</label>
    <div class="layui-input-block">
      <input name="departmentName" id="txtdepartmentName"
							value="${department.departmentName}" maxlength="20"
							class="layui-input" type="text" placeholder="请输入部门名称"">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label" style="width:100px;">联系电话</label>
    <div class="layui-input-block">
      <input id="txtCompanyTelphone"
							value="${company.companyTelephone}" maxlength="11"
							class="layui-input" type="text" placeholder="请输入部门负责人">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label" style="width:100px;">职位</label>
    <div class="layui-input-block">
      <input id="txtCompanyTelphone"
							value="${company.companyTelephone}" maxlength="11"
							class="layui-input" type="text" placeholder="请输入部门负责人">
    </div>
  </div>
   <div class="layui-form-item">
    <label class="layui-form-label" style="width:100px;">登录账号</label>
    <div class="layui-input-block">
      <input id="txtCompanyTelphone"
							value="${company.companyTelephone}" maxlength="11"
							class="layui-input" type="text" placeholder="请输入部门负责人">
    </div>
  </div>
  <div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn" type="button"  onclick="editDepartment();">立即提交</button>
      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
      <button type="button" class="layui-btn layui-btn-primary" onclick="cancelEdit();">取消</button>
    </div>
  </div>
</form>

</div>	
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/layer/layer.js"></script>
<script type="text/javascript" src="${ctx}/js/pages/admin/dept_user_dept_edit.js"></script>
</body>
</html>