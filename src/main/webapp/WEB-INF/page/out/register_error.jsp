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
<%@include file="../share/common_css.jsp"%>
<style type="text/css">
.myfont{
	font: 14px 'Microsoft Yahei', Verdana, 宋体, sans-serif;
}
.my-register-box{
	margin-top: 30px;
}

.my-background{
	background: #ffffff;
}

.my-borderradius{
	border-radius: 5px;
}
</style>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
       	 注册失败
      </h1>
      <ol class="breadcrumb">
        <li><a href="http://www.qijiapo.com"><i class="fa fa-dashboard"></i> 企家婆</a></li>
        <li><a href="${ctx}/out/register">注册</a></li>
        <li class="active">注册失败</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="error-page">
        <h2 class="headline text-red"> ERROR!</h2>

        <div class="error-content">
          <h3><i class="fa  fa-close text-red "></i>很遗憾! 注册失败.</h3>

          <p class="myfont">
           		<h4 style="color:red;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${tip}</h4>
          </p>
			<form class="search-form">
				<a href="${ctx}/out/register"  class="btn bg-info btn-flat myfont">重新注册</a>
			</form>
        
        </div>
        <!-- /.error-content -->
      </div>
      <!-- /.error-page -->
    </section>


  
  <!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
</div>
<!-- /.register-box -->

<script src="${ctx}/js/jQuery/jquery-2.2.3.min.js"></script>
<script src="${ctx}/js/bootstrap/js/bootstrap.min.js"></script>

</body>
</html>