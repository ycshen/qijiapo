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
<script type="text/javascript">
	var ctx = "${ctx}";
</script>
<style type="text/css">
.myfont{
	font: 14px 'Microsoft Yahei', Verdana, 宋体, sans-serif;
}
.my-register-box{
	margin-top: 30px;
}

.my-background{
	background: #ffffff;
	background-image: url('${ctx}/img/bg_register.jpg');
	background-size: cover;
}

.my-borderradius{
	border-radius: 5px;
}

.my-tip{
	text-align: center;
	font: 14px 'Microsoft Yahei', Verdana, 宋体, sans-serif;
	color: red;
}
</style>
</head>
<body class="hold-transition  my-background">
<div class="register-box my-register-box">
  <div class="register-logo">
    <a href="http://www.qijiapo.com">欢迎登录企家婆</a>
  </div>
  <div class="register-box-body">
    <form  method="POST" action="login">
    	<c:if test="${msg != null && msg != ''}">
				<div class="alert alert-info" role="alert" style="text-align:center;">${msg}</div>
			</c:if>
       <div class="form-group has-feedback">
        <input type="text" class="form-control myfont my-borderradius" name="account" placeholder="请输入手机号码/电子邮箱" id="inputAccount">
        <span class="glyphicon glyphicon-user form-control-feedback"></span>
      </div>
      <div class="form-group has-feedback">
        <input type="password" class="form-control myfont my-borderradius"  name="password" placeholder="请输入密码" id="inputPassword" maxlength="15">
        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
      </div>
     
      
      <div class="row">
        <div class="col-xs-12">
         <button  class="btn btn-primary btn-block btn-sm myfont" type="submit">立即登录</button>
        </div>
        </div>
         <div class="row">
        <div class="col-xs-12 myfont" style="text-align:center;">
        	<br/>
         	   没有账号? <a href="${ctx}/out/register" class="text-center">免费注册</a>
        </div>
        </div>
    </form>

    

  </div>
  <!-- /.form-box -->
</div>
<!-- /.register-box -->

<script src="${ctx}/js/jQuery/jquery-2.2.3.min.js"></script>
<script src="${ctx}/js/bootstrap/js/bootstrap.min.js"></script>
<script src="${ctx}/js/pages/out/register.js"></script>

</body>
</html>