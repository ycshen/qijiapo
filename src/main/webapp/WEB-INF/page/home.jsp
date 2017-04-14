<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>企家婆-专业的企业好帮手</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.6 -->
<%@include file="share/common_css.jsp"%>

</head>
<c:choose>
	<c:when test="${loginUser.isCollapseMenu == 1}">
		<body class="hold-transition skin-black sidebar-mini sidebar-collapse">
	</c:when>
	<c:otherwise>
		<body class="hold-transition skin-black sidebar-mini">
	</c:otherwise>
</c:choose>
<div class="wrapper">
	<%@include file="share/qjp_header.jsp"%>
 	<%@include file="share/qjp_menu.jsp"%>
  
  <div class="content-wrapper">
    <section class="content-header">
      <h1>
        企家婆
        <small>你身边的企业服务管家</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 企家婆</a></li>
        <li class="active">控制面板</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">

      <div class="row">
        <c:if test="${roleType == 1}">
          <div class="col-md-3 col-sm-6 col-xs-12">
            <div class="info-box">
              <a href="${ctx}/inner/admin/home">
                  <span class="info-box-icon bg-aqua"><i class="ion ion-ios-gear-outline"></i></span>
              </a>
              <div class="info-box-content">
                <a href="${ctx}/inner/admin/home"><span class="info-box-text">>></span>
                <span class="info-box-number">企业管理<small></small></span></a>
              </div>
            </div>
          </div>
        </c:if>
        <div class="col-md-3 col-sm-6 col-xs-12">
          <div class="info-box">
            <span class="info-box-icon bg-red"><i class="fa fa-google-plus"></i></span>

            <div class="info-box-content">
              <span class="info-box-text">6</span>
              <span class="info-box-number">我的应用</span>
            </div>
          </div>
        </div>

        <div class="clearfix visible-sm-block"></div>

        <div class="col-md-3 col-sm-6 col-xs-12">
          <div class="info-box">
            <span class="info-box-icon bg-green"><i class="ion ion-ios-cart-outline"></i></span>

            <div class="info-box-content">
              <span class="info-box-text">9600</span>
              <span class="info-box-number">我的销售</span>
            </div>
          </div>
        </div>
        <div class="col-md-3 col-sm-6 col-xs-12">
          <div class="info-box">
            <a href="${ctx}/inner/admin/home" title="查看详细信息">
              <span class="info-box-icon bg-yellow"><i class="ion ion-ios-people-outline"></i></span>
            </a>
            <div class="info-box-content">
              <a href="${ctx}/inner/user/listCompanyUser"  title="查看详细信息"><span class="info-box-text">${customerCount}</span>
              <span class="info-box-number">公司通讯录</span>
              </a>
            </div>
          </div>
        </div>
      </div>

    </section>
  </div>

  <%@include file="share/qjp_footer.jsp"%>

  <!-- /.control-sidebar -->
  <!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
  <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->

<!-- REQUIRED JS SCRIPTS -->

<!-- jQuery 2.2.3 -->
<script src="${ctx}/js/jQuery/jquery-2.2.3.min.js"></script>
<script src="${ctx}/js/bootstrap/js/bootstrap.min.js"></script>
<!-- AdminLTE App -->
<script src="${ctx}/js/adminlte/dist/js/app.min.js"></script>

</body>
</html>