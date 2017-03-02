<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>企家婆-专业的企业好管家</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <%@include file="../share/common_css.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/js/multi-select/css/multi-select.css">
<script src="${ctx}/js/jQuery/jquery-2.2.3.min.js"></script>
<script src="${ctx}/js/bootstrap/js/bootstrap.min.js"></script>
<script src="${ctx}/js/adminlte/dist/js/app.min.js"></script>
<script type="text/javascript" src="${ctx}/js/pagination/jquery.pagination.js"></script>
<script type="text/javascript" src="${ctx}/js/layer/layer.js"></script>
<script src="${ctx}/js/multi-select/js/jquery.multi-select.js"></script>
<script src="${ctx}/js/jquery.quicksearch.js"></script>
<script type="text/javascript" src="${ctx}/js/pages/auth/auth_user.js"></script>
<script type="text/javascript">
	var ctx = "${ctx}";
</script>
<style type="text/css">
input.search-input {
    box-sizing: border-box;
    -moz-box-sizing: border-box;
    width: 100%;
    margin-bottom: 5px;
    height: auto;
}
.custom-header{
	font: 14px "Microsoft Yahei", Verdana, 宋体, sans-serif;
}
</style>
</head>
<body>

<div  class="container" style="text-align: center;margin-top: 10px;">
	<div  id="multiDiv" style="text-align: center;">
		<select id='custom-headers' multiple='multiple'>
			<c:if test="${authUserList != null && authUserList.size() > 0}">
				<c:forEach items="${authUserList}" var="authUser">
					<c:choose>
						<c:when test="${authUser.authUserId != null && authUser.authUserId == userAuthQuery.authId}">
							<option value='${authUser.id}' selected>${authUser.userName}</option>
						</c:when>
						<c:otherwise>
							<option value='${authUser.id}'>${authUser.userName}</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:if>
		</select>
	</div>
	
	
</div>
<div  class="container" style="text-align: center;margin-top: 30px;">
<button type="button" class="btn btn-default" onclick="addAuth('${userAuthQuery.authId}');" id="addAuth">提交</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<button type="button" class="btn btn-default" onclick="cancelAuth();">取消</button>
</div>
<input type="hidden" value="${authCount}" id="hidAuthCount"/>
<input type="hidden" value="${notAuthCount}" id="hidNotAuthCount"/>
<input type="hidden" value="${authStr}" id="hidAuthStr"/>
<input type="hidden" value="${notAuthStr}" id="hidNotAuthStr"/>
</body>
</html>