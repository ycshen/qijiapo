<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">

    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">

      <!-- Sidebar user panel (optional) -->
      <div class="user-panel">
        <div class="pull-left image">
          <img src="${ctx}/js/adminlte/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
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
      <ul class="sidebar-menu">
        <li class="header">菜单</li>
        <!-- Optionally, you can add icons to the links -->
        <li class="active"><a href="${ctx}/inner/company/list"><i class="fa fa-sitemap"></i> <span>分公司管理</span></a></li>
        <li class="active"><a href="${ctx}/inner/admin/departmentUserList"><i class="fa fa-group"></i> <span>部门与员工</span></a></li>
        <li class="active"><a href="${ctx}/inner/memo/memo"><i class="fa fa-unlock-alt"></i> <span>权限管理</span></a></li>
        <li class="active"><a href="${ctx}/inner/memo/memo"><i class="fa fa-cog"></i> <span>菜单自定义</span></a></li>
        <li class="active"><a href="${ctx}/inner/log/logs"><i class="fa fa-file-text"></i> <span>管理日志</span></a></li>
        <li class="active"><a href="${ctx}/inner/memo/memo"><i class="fa fa-bullhorn"></i> <span>公告管理</span></a></li>
        <li class="active"><a href="${ctx}/inner/memo/memo"><i class="fa fa-weibo"></i> <span>话题管理</span></a></li>
      </ul>
      <!-- /.sidebar-menu -->
    </section>
    <!-- /.sidebar -->
  </aside>
