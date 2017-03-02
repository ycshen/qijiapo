<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>部门树</title>
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<%@include file="../share/common_css.jsp"%>
<link rel="stylesheet" href="${ctx}/js/zTree/css/metroStyle/metroStyle.css" type="text/css">

<%@include file="../share/common_js.jsp"%>
<script src="${ctx}/js/adminlte/dist/js/app.min.js"></script>
<script type="text/javascript" src="${ctx}/js/zTree/js/jquery.ztree.core.js"></script>
    <script type="text/javascript" src="${ctx}/js/zTree/js/jquery.ztree.excheck.js"></script>
    <script type="text/javascript" src="${ctx}/js/zTree/js/jquery.ztree.exedit.js"></script>
<script type="text/javascript" src="${ctx}/js/pages/common/department.js"></script>
<script type="text/javascript">
	var ctx = "${ctx}";
</script>
</head>
<body>
<div class="container">
  <ul id="treeDemo" class="ztree"></ul> 
 </div>


</body>
</html>