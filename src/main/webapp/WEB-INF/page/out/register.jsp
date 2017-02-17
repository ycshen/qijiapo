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
    <a href="http://www.qijiapo.com">欢迎注册企家婆</a>
  </div>
  <div class="register-box-body">
    <form  method="post" id="registerForm">
    	<div class="form-group has-feedback my-tip" id="tipDiv"></div>
      <div class="form-group has-feedback">
        <input type="text" class="form-control myfont my-borderradius" name="companyName" placeholder="请输入公司名称" maxlength="20" value="${user.companyName}" id="txtCompanyName">
        <span class="glyphicon glyphicon-tower form-control-feedback"></span>
      </div>
       <div class="form-group has-feedback">
        <input type="text" class="form-control myfont my-borderradius" name="userName" placeholder="请输入姓名" maxlength="15" value="${user.userName}" id="txtUserName">
        <span class="glyphicon glyphicon-user form-control-feedback"></span>
      </div>
      <div class="form-group has-feedback">
        <input type="text" class="form-control myfont my-borderradius" name="telphone" placeholder="请输入手机号码"  maxlength="11" value="${user.telephone}" id="txtTelephone">
        <span class="glyphicon glyphicon-earphone form-control-feedback"></span>
      </div>
      <div class="form-group has-feedback">
        <input type="email" class="form-control myfont my-borderradius" name="email" placeholder="请输入电子邮箱" value="${user.email}" id="txtEmail">
        <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
      </div>
      <div class="form-group has-feedback">
        <input type="password" class="form-control myfont my-borderradius"  name="password" placeholder="请输入密码" id="txtPass" maxlength="15">
        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
      </div>
      <div class="form-group has-feedback">
        <input type="password" class="form-control myfont my-borderradius" placeholder="确认密码" id="txtConfirmPass" maxlength="15">
        <span class="glyphicon glyphicon-log-in form-control-feedback"></span>
      </div>
      <div class="row">
        <div class="col-xs-12">
          <div class="checkbox icheck">
            <label class="myfont">
             	 您是否同意接受《<a href="${ctx}/out/clause" class="myfont">企家婆使用条款</a>》
            </label>
          </div>
        </div>
         
      </div>
      <div class="row">
        <div class="col-xs-12">
         <a  class="btn btn-primary btn-block btn-sm myfont"  onclick="submit();" id="btnRegister">同意，注册</a>
        </div>
        </div>
         <div class="row">
        <div class="col-xs-12 myfont" style="text-align:center;">
        	<br/>
         	   已有账户，在这里<a href="${ctx}/login" class="text-center">登录</a>
        </div>
        </div>
    </form>

    <input type="hidden" value="1" id="hidIsSubmit"/>

  </div>
  <!-- /.form-box -->
</div>
<!-- /.register-box -->

<script src="${ctx}/js/jQuery/jquery-2.2.3.min.js"></script>
<script src="${ctx}/js/bootstrap/js/bootstrap.min.js"></script>
<script src="${ctx}/js/pages/out/register.js"></script>

</body>
</html>