<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>企家婆-专业的企业服务好帮手</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
   <%@include file="../share/common_css.jsp"%>
  <link rel="stylesheet" href="${ctx}/js/plugins/fullcalendar/fullcalendar.min.css">
  <link rel="stylesheet" href="${ctx}/js/plugins/fullcalendar/fullcalendar.print.css" media="print">
<link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
 <script type="text/javascript">
	var ctx = "${pageContext.request.contextPath}";
</script>


</head>
<body class="hold-transition skin-black sidebar-mini">
<div class="wrapper">

 <%@include file="../share/qjp_header.jsp"%>
  <%@include file="../share/qjp_menu.jsp"%>
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        	行程安排
        <small>管理你的行程</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 企家婆</a></li>
        <li class="active">行程安排</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-md-4">
          <div class="box box-solid">
            <div class="box-header with-border">
              <h4 class="box-title">你的行程</h4>
            </div>
            <div class="box-body">
             	<input type="button" class="btn btn-sm btn-info" value="添加行程" onclick="addMemo();"/>
             	<!-- <input type="button" class="btn btn-sm btn-info" value="管理行程" onclick="addMemo();"/> -->
            </div>
             <div class="box-header with-border">
              <h3 class="box-title">今日行程</h3>
            </div>
            <div class="box-body">
            <c:if test="${todayMemos != null && todayMemos.size() > 0}">
            	<table class="table table-striped">
					 <tr>
						 	<td>开始时间</td>
	            			<td >行程主题</td>
						 </tr>
					 <c:forEach items="${todayMemos}" var="memo">
	            		<tr class="danger">
						 	<td><f:formatDate value="${memo.memoStartTime}" pattern="HH:mm" /></td>
	            			<td >${memo.memoName }</td>
						 </tr>
	            	</c:forEach>
				</table>
            	
            </c:if>
            </div>
           
          </div>
        </div>
        <!-- /.col -->
        <div class="col-md-8">
          <div class="box box-primary">
            <div class="box-body no-padding">
              <!-- THE CALENDAR -->
              <div id="calendar"></div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
  <!-- /.content-wrapper -->
<%@include file="../share/qjp_footer.jsp"%>
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/layer/layer.js"></script>
<script src="${ctx}/js/bootstrap/js/bootstrap.min.js"></script>
<script src="${ctx}/js/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<script src="${ctx}/js/plugins/fastclick/fastclick.js"></script>
<script src="${ctx}/js/adminlte/dist/js/app.min.js"></script>
<script src="${ctx }/js/fullcalendar-3.2.0/lib/moment.min.js"></script>
<script src="${ctx}/js/plugins/fullcalendar/fullcalendar.min.js"></script>
<script type="text/javascript" src="${ctx}/js/layui/layui.js"></script>
<script src="${ctx}/js/pages/memo/memo.js"></script>
<script type="text/javascript">

</script>
</body>
</html>