<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<meta charset="UTF-8">
<title>菜单信息编辑</title>

 <%@include file="../share/common_css.jsp"%>
<link href="${ctx}/js/layui/css/layui.css" rel="stylesheet">
<link href="${ctx}/css/common.css" rel="stylesheet">
<script type="text/javascript">
	var ctx = "${pageContext.request.contextPath}";
</script>
</head>
<body style="background-color: #fff;">
	
	<section class="content">
      <!-- Default box -->
      <div class="box" style="border-top:0px; margin-bottom: 0px;">
        <div class="box-header" style="background-color: #fff;padding:0px;">
          <h6 class="box-title">自定义
         	 <button class="layui-btn  layui-btn-mini  layui-btn-primary  layui-btn-radius">
			  <i class="layui-icon">&#xe608;</i> 添加自定义职位
			</button>
		</h6>

        </div>
        <div class="box-body" style="line-height:30px;">
         	<c:if test="${userPositionList != null && userPositionList.size() > 0 }">
			<c:forEach items="${userPositionList}" var="position" varStatus="status">
				<c:if test="${status.index == 0 }">
					<button class="layui-btn layui-btn-radius layui-btn-mini" style="margin-left: 10px;">${position.postionName }</button>
				</c:if>
				<c:if test="${status.index != 0 }">
					<button class="layui-btn layui-btn-radius layui-btn-mini">${position.postionName }</button>
				</c:if>
			</c:forEach>
			</c:if>
        </div>
      </div>
       <div class="box" style="border-top:0px; margin-bottom: 0px;">
        <div class="box-header"  style="background-color: #fff;padding:0px;">
          <h6 class="box-title">系统定义</h6>
			
        </div>
        <div class="box-body" style="line-height:30px;">
         	<c:if test="${systemPositionList != null && systemPositionList.size() > 0 }">
			<c:forEach items="${systemPositionList}" var="position" varStatus="status"> 
				<button class=" layui-btn-primary  layui-btn-radius layui-btn-mini" style="margin-left: 10px;">${position.postionName }</button>
				
			
			</c:forEach>
			</c:if>
        </div>
      </div>
    </section>
		

<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/laydate/laydate.js"></script>
<script type="text/javascript" src="${ctx}/js/layer/layer.js"></script>
<script type="text/javascript" src="${ctx}/js/layer/layui.js"></script>
<script type="text/javascript" src="${ctx}/js/pages/menu/menu_edit.js"></script>
</body>
</html>