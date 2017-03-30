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
<script type="text/javascript">
	var ctx = "${pageContext.request.contextPath}";
</script>
</head>
<body>
	<div class="container">
			<div class="box">
		        <div class="box-body">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;是否将合同
				
				<c:choose>
					<c:when test="${name == null || name == ''}">
						批量
						<input type="hidden" id="hidTransferType" value="2"/>
					</c:when>
					<c:otherwise>
						【${name}】
						<input type="hidden" id="hidTransferType" value="1"/>
					</c:otherwise>
				</c:choose>
				转移给其他负责人？转移成功之后，该操作将无法恢复。
		        </div>
		      </div>
			
		  <div class="layui-form-item" style="height: 200px; overflow-y: auto">
		    <label class="layui-form-label" style="width:100px;">新负责人：</label>
		    <div class="layui-input-block" style="padding-top:8px;">
		    	<select class="form-control select2" style="width: 100%;display:inline;" name="userId">
		             <option value="">请选择新负责人</option>
		             <c:if test="${userList != null && userList.size() > 0 }">
		             	<c:forEach items="${userList}" var="user">
		             		 <option  value="${user.id}">${user.departmentName }-${user.userName }</option>
		             	</c:forEach>
		             </c:if>
		           </select>
		    </div>
		  </div>
	</div>
	
  
  <div class="layui-form-item">
    <div class="layui-input-block" style="text-align: right;">
      <button class="layui-btn" type="button"  onclick="transferContract('${id}');">确定</button>
      <button type="button" class="layui-btn layui-btn-primary" onclick="cancelEdit();" style="margin-right:50px;">取消</button>
    </div>
  </div>
	
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/laydate/laydate.js"></script>
<script type="text/javascript" src="${ctx}/js/layer/layer.js"></script>
<script src="${ctx}/js/select2/select2.full.min.js"></script>
<script type="text/javascript" src="${ctx}/js/pages/user/user_allcompany_select.js"></script>

</body>
</html>



