<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<!DOCTYPE html>
<html>
<head>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<meta charset="UTF-8">
<title>员工信息管理</title>
<%@include file="../share/common_css.jsp"%>
<link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
<link rel="stylesheet" href="${ctx}/js/select2/select2.min.css">
<link href="${ctx}/css/common.css" rel="stylesheet">
<script type="text/javascript">
	var ctx = "${pageContext.request.contextPath}";
</script>
</head>
<body>
<form class="layui-form" method="post" id="myForm">
	<input type="hidden" value="${competitorId}" id="hidCompetitorId"/>
	是否将竞争对手【dsyc】转移给其他负责人？转移成功之后，该操作将无法恢复。
  <div class="layui-form-item">
    <label class="layui-form-label" style="width:100px;">新负责人：</label>
    <div class="layui-input-block">
    	<select class="form-control select2" style="width: 100%;display:inline;" name="departmentId">
             <option value="-1">请选择新负责人</option>
             <c:if test="${userList != null && userList.size() > 0 }">
             	<c:forEach items="${userList}" var="user">
             		 <option  value="${user.id}">${user.departmentName }-${user.userName }</option>
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
	
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/laydate/laydate.js"></script>
<script type="text/javascript" src="${ctx}/js/layer/layer.js"></script>
<script type="text/javascript" src="${ctx}/js/pages/user/user_list.js"></script>

</body>
</html>



