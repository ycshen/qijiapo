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
  <%@include file="../../share/common_css.jsp"%>
<link rel="stylesheet" href="${ctx}/js/plugins/datatables/dataTables.bootstrap.css">
<%@include file="../../share/common_js.jsp" %>
    <script src="${ctx}/js/plugins/datatables/jquery.dataTables.min.js"></script>
    <script src="${ctx}/js/plugins/datatables/dataTables.bootstrap.min.js"></script>
    <script src="${ctx}/js/plugins/slimScroll/jquery.slimscroll.min.js"></script>
    <script src="${ctx}/js/plugins/fastclick/fastclick.js"></script>
    <script src="${ctx}/js/adminlte/dist/js/app.min.js"></script>
    <script src="${ctx}/js/select2/select2.full.min.js"></script>
<script type="text/javascript" src="${ctx}/js/pages/admin/office/checkingattendance_rule_list.js"></script>
<script type="text/javascript">
	var ctx = "${ctx}";
</script>
</head>
<body class="hold-transition skin-black sidebar-mini">
<div class="wrapper">
	<%@include file="../../share/qjp_header.jsp"%>
 <jsp:include page="../../share/qjp_admin_menu.jsp">
	<jsp:param name="nav" value="ck_att_list" />
</jsp:include>
  
 <div class="content-wrapper">
    <section class="content-header">
      <h1>
  		考勤点管理
        <small></small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 企家婆</a></li>
        <li class="active">考勤点管理</li>
      </ol>
    </section>
     <section class="content">
		
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title">考勤点列表</h3>
            </div>
            <div class="box">
        <div class="box-header with-border">
          <div class="box-tools pull-right">
            <button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip" title="隐藏">
              <i class="fa fa-minus"></i></button>
            <button type="button" class="btn btn-box-tool" data-widget="remove" data-toggle="tooltip" title="移除">
              <i class="fa fa-times"></i></button>
          </div>
        </div>
        <div class="box-body">
          	<form class="form-inline" role="form">
			  
			  <button type="button" class="btn btn-default" onclick="addCheckingAttendance();">添加考勤点</button>
			</form>
        </div>
      </div>
            <div class="box-body">
              <table id="myDataTable" class="table table-bordered table-hover">
                <thead>
                    <tr class="info">
                        <th>操作</th>
                        <th>考勤点名称</th>
                        <th>考勤地址</th>
                        <th>上班时间</th>
                        <th>上班</th>
                        <th>下班</th>
                        <th>适合部门</th>
                        <th>偏差范围(米)</th>
                        <th>状态</th>
                    </tr>
                </thead>
                <tbody>


                </tbody>
              </table>
            
            </div>
          </div>

        </div>
      </div>
    </section>
  </div>
  <%@include file="../../share/qjp_footer.jsp"%>
  <div class="control-sidebar-bg"></div>
</div>
</body>
</html>