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
<script src="${ctx}/js/jQuery/jquery-2.2.3.min.js"></script>
<script src="${ctx}/js/bootstrap/js/bootstrap.min.js"></script>
<script src="${ctx}/js/adminlte/dist/js/app.min.js"></script>
<script type="text/javascript" src="${ctx}/js/pagination/jquery.pagination.js"></script>
<script type="text/javascript" src="${ctx}/js/layer/layer.js"></script>
<script type="text/javascript" src="${ctx}/js/pages/menudefined/menudefined_list.js"></script>
<style type="text/css">
.placeholder img {
    padding-top: 1.5rem;
    padding-bottom: 1.5rem;
}
.rounded-circle {
    border-radius: 50%;
}
.img-fluid {
    max-width: 100%;
    height: auto;
}
</style>
<script type="text/javascript">
	var ctx = "${ctx}";
</script>
</head>
<body class="hold-transition skin-black sidebar-mini">
<div class="wrapper">
	<%@include file="../share/qjp_header.jsp"%>
 <jsp:include page="../share/qjp_admin_menu.jsp">
	<jsp:param name="nav" value="menudefined_list" />
</jsp:include>
  
  <!-- Content Wrapper. Contains page content -->
 <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
  		菜单自定义
        <small></small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 企家婆</a></li>
        <li class="active">菜单自定义</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
		
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
          	<div class="box-body">
          		<h5>说明：</h5>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1.企家婆平台默认为按照权限定义用户使用菜单;<p/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2.菜单定以后显示优先级：个人>职位>部门>权限，即当一个用户的菜单同时按照个人、职位、部门、权限定义时，用户使用系统显示个人定义的菜单，如果按照部门、权限定义，那么显示按照部门定义的菜单，依次类推;<p/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.如果同时有两种定义规则，需要使用优先级小的规则，请到部门与员工里编辑规则即可，比如按照个人、权限定义时，用户需要使用权限定义的菜单，则去部门与员工的用户信息里改变定义规则即可;<p/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.权限维护对应<a href="${ctx}/inner/auth/list"">权限管理</a>，职位维护对应<a href="${ctx}/inner/position/listInit">职位管理</a>，部门维护对应<a href="${ctx}/inner/admin/departmentUserList">部门与员工</a>，用户维护对应<a href="${ctx}/inner/admin/departmentUserList">部门与员工</a>.

		        </div>
            <!-- /.box-header -->
            <div class="box-body">
             <section class="row text-center placeholders">
	            
	            <div class="col-6 col-sm-3 placeholder">
	              <img src="${ctx}/img/menudefined/user.png" width="100" height="100" class="img-fluid rounded-circle" alt="Generic placeholder thumbnail">
	              <h4>按用户定义</h4>
	              <span class="text-muted">
					<button class="btn btn-info " onclick="defineByUser();">去定义</button>
					</span>
	            </div>
	            <div class="col-6 col-sm-3 placeholder">
	              <img src="${ctx}/img/menudefined/manager.png" width="100" height="100" class="img-fluid rounded-circle" alt="Generic placeholder thumbnail">
	              <h4>按职位定义</h4>
	              <span class="text-muted">
	              	<button class="btn btn-info " onclick="defineByPosition();">去定义</button>
	              	
	              </span>
	            </div>
	            <div class="col-6 col-sm-3 placeholder">
	              <img src="${ctx}/img/menudefined/user_group_01.png" width="100" height="100" class="img-fluid rounded-circle" alt="Generic placeholder thumbnail">
	              <h4>按部门定义</h4>
	              <span class="text-muted">
					<button class="btn btn-info " onclick="defineByDept();">去定义</button>
	              	
	              </span>
	            </div>
	            <div class="col-6 col-sm-3 placeholder">
	              <img src="${ctx}/img/menudefined/user_group.png" width="100" height="100" class="img-fluid rounded-circle" alt="Generic placeholder thumbnail">
	              <h4>按权限定义</h4>
	              <div class="text-muted">
					<button class="btn btn-info " onclick="defineByRole();">去定义</button>
				  </div>
	            </div>
	          </section>
            
            </div>
           
          </div>

        </div>
      </div>

    </section>
  </div>

  <%@include file="../share/qjp_footer.jsp"%>

  <div class="control-sidebar-bg"></div>
</div>


</body>
</html>