<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style type="text/css">
.active a{
	color: #009688 !important;
}
.my-ul li:hover a{
	color: #009688 !important;
}
</style>
<%
	String path = request.getContextPath();
	String ctx = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
 <!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">

    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">

      <div class="user-panel">
        <div class="pull-left image">
          <img src="<%=ctx%>/js/adminlte/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p>${loginUser.userName}</p>
          <!-- Status -->
          <a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
        </div>
      </div>

      <!-- search form (Optional) -->
      <form action="#" method="get" class="sidebar-form">
        <div class="input-group">
          <input type="text" name="q" class="form-control" placeholder="Search...">
              <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                </button>
              </span>
        </div>
      </form>
      <!-- /.search form -->

      <!-- Sidebar Menu -->
      <ul class="sidebar-menu my-ul">
        <li class="header">菜单</li>
        <!-- Optionally, you can add icons to the links -->
        <li <c:if test="${param.nav=='subcompany_list'}"> class="active"</c:if>>
        	<a href="<c:url value="/inner/company/list"/>"><i class="fa fa-sitemap"></i> <span>分公司管理</span></a></li>
       	<li <c:if test="${param.nav=='deptuser_list'}"> class="active"</c:if>>
       		<a href="<c:url value="/inner/admin/departmentUserList"/>"><i class="fa fa-group"></i> 部门与员工</a></li>
        <li <c:if test="${param.nav=='position_list'}"> class="active"</c:if>>
        	<a href="<c:url value="/inner/position/listInit"/>"><i class="fa fa-circle-o"></i> 职位管理</a></li>
        <li <c:if test="${param.nav=='auth_list'}"> class="active"</c:if>>
        	<a href="<c:url value="/inner/auth/list"/>"><i class="fa fa-unlock-alt"></i> <span>权限管理</span></a></li> 
        <li <c:if test="${param.nav=='role_list'}"> class="active"</c:if>>
        	<a href="<c:url value="/inner/role/list"/>"><i class="fa fa-unlock-alt"></i> <span>角色管理</span></a></li>
        <li <c:if test="${param.nav=='menudefined_list'}"> class="active"</c:if>>
        	<a href="<c:url value="/inner/admin/menudefined/list"/>"><i class="fa fa-cog"></i> <span>菜单自定义</span></a></li>
        <li <c:if test="${param.nav=='log_list'}"> class="active"</c:if>>
        	<a href="<c:url value="/inner/log/logs"/>"><i class="fa fa-file-text"></i> <span>管理日志</span></a></li>
        <li <c:if test="${param.nav=='auth_list'}"> class="active"</c:if>>
        	<a href="<c:url value="/inner/memo/memo"/>"><i class="fa fa-bullhorn"></i> <span>公告管理</span></a></li>
        <li <c:if test="${param.nav=='auth_list'}"> class="active"</c:if>>
        	<a href="<c:url value="/inner/memo/memo"/><i class="fa fa-weibo"></i> <span>话题管理</span></a></li>
      </ul>
    </section>
  </aside>
