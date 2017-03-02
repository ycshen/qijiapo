<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<meta charset="UTF-8">
<title>关联部门</title>
<%@include file="../share/common_css.jsp"%>
<link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
<link rel="stylesheet" href="${ctx}/js/select2/select2.min.css">
<script type="text/javascript">
	var ctx = "${pageContext.request.contextPath}";
</script>
</head>
<body>

<div class="container" style="margin-top: 10px;">
  
<form class="layui-form" method="post" id="myForm">
	<input type="hidden" value="${userId}" id="hidId"/>
  <div class="layui-form-item">
    <label class="layui-form-label" style="width:100px;">所属部门</label>
    <div class="layui-input-block">
    	<select class="form-control select2" style="width: 100%;display:inline;" name="departmentId">
             <option value="-1">无部门状态</option>
             <c:if test="${departmentList != null && departmentList.size() > 0 }">
             	<c:forEach items="${departmentList}" var="department">
             		 <option <c:if test="${department.id == user.departmentId}">selected="selected"</c:if> value="${department.id}">${department.departmentName }</option>
             	</c:forEach>
             </c:if>
           </select>
    </div>
  </div>
  
  <div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn" type="button"  onclick="cascade();">确定</button>
      <button type="button" class="layui-btn layui-btn-primary" onclick="cancelEdit();">取消</button>
    </div>
  </div>
</form>

</div>	
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/layer/layer.js"></script>
<script src="${ctx}/js/select2/select2.full.min.js"></script>
<script type="text/javascript" src="${ctx}/js/pages/department/department_cascade.js"></script>
</body>
</html>