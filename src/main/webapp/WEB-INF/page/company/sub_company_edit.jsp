<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<meta charset="UTF-8">
<title>面单申请</title>
<%@include file="../share/common_css.jsp"%>
<link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
<script type="text/javascript">
	var ctx = "${pageContext.request.contextPath}";
</script>
</head>
<body>

<div class="container" style="margin-top: 10px;">
<form class="layui-form" method="post" id="companyForm">
	<input type="hidden" value="${company.id}" name="id"/>
	
  <div class="layui-form-item">
    <label class="layui-form-label" style="width:100px;">分公司名称</label>
    <div class="layui-input-block">
      <input name="companyName" id="txtCompanyName"
							value="${company.companyName}" maxlength="20"
							class="layui-input" type="text" placeholder="请输入分公司名称">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label" style="width:100px;">分公司地址</label>
    <div class="layui-input-block">
      <input name="companyAddress" id="txtCompanyAddress"
							value="${company.companyAddress}" maxlength="20"
							class="layui-input" type="text" placeholder="请输入分公司地址"">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label" style="width:100px;">联系方式</label>
    <div class="layui-input-block">
      <input name="companyTelephone" id="txtCompanyTelphone"
							value="${company.companyTelephone}" maxlength="11"
							class="layui-input" type="text" placeholder="请输入联系方式">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label" style="width:100px;">公司负责人</label>
    <div class="layui-input-block">
      <input name="companyCeo" id="txtCompanyCeo"
							value="${company.companyCeo}" maxlength="20"
							class="layui-input" type="text" placeholder="请输入分公司负责人">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label" style="width:100px;">分公司网址</label>
    <div class="layui-input-block">
      <input name="companySite" id="txtCompanySite"
							value="${company.companySite}" maxlength="20"
							class="layui-input" type="text" placeholder="请输入分公司网址">
    </div>
  </div>
  <div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn" type="button"  onclick="editCompany();">立即提交</button>
      <button type="button" class="layui-btn layui-btn-primary" onclick="cancelEdit();">取消</button>
    </div>
  </div>
</form>

</div>	
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/layer/layer.js"></script>
<script type="text/javascript" src="${ctx}/js/pages/company/sub_company_edit.js"></script>
</body>
</html>