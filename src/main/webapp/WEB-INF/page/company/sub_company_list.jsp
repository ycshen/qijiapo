<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>企家婆-专业的企业好管家</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <%@include file="../share/common_css.jsp"%>
	<script src="${ctx}/js/jQuery/jquery-2.2.3.min.js"></script>
<script src="${ctx}/js/bootstrap/js/bootstrap.min.js"></script>
<!-- AdminLTE App -->
<script src="${ctx}/js/adminlte/dist/js/app.min.js"></script>
<script type="text/javascript" src="${ctx}/js/pagination/jquery.pagination.js"></script>
<script type="text/javascript">
	var ctx = "${ctx}";
</script>
</head>
<body class="hold-transition skin-black sidebar-mini">
<div class="wrapper">
	<%@include file="../share/qjp_header.jsp"%>
 	<%@include file="../share/qjp_admin_menu.jsp"%>
  
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        企业管理
        <small></small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 企家婆</a></li>
        <li class="active">企业管理</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
		
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title">分公司数据列表</h3>
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
			  <div class="form-group">
			    <label class="sr-only" for="exampleInputEmail2">Email address</label>
			    <input type="email" class="form-control" id="exampleInputEmail2" placeholder="分公司名称">
			  </div>
			  
			  <button type="submit" class="btn btn-default">查询</button>
			  <button type="submit" class="btn btn-default">添加分公司</button>
			  <button type="submit" class="btn btn-default">编辑</button>
			  <button type="submit" class="btn btn-default">禁用</button>
			</form>
        </div>
        
      </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table id="syslist" class="table table-bordered table-hover">
                <thead>
                <tr>
                  <th></th>
                  <th>分公司名称</th>
                  <th>公司地址</th>
                  <th>联系方式</th>
                  <th>公司网址</th>
                </tr>
                </thead>
                <tbody>
                	<c:if test="${companyQuery.items != null &&  companyQuery.items.size() > 0}">
                		<c:forEach items="${companyQuery.items}" var="company">
                			<tr>
		                		<td><input type="checkbox"/></td>
		                		<td>${company.companyName }</td>
		                		<td>${company.companyAddress }</td>
		                		<td>${company.companyTelephone }</td>
		                		<td>${company.companySite }</td>
	                		</tr>
                		</c:forEach>
                	</c:if>
                </tbody>
                
                 
               
              </table>
            
            </div>
            <div class="box-header" style="text-align:center">
            	<%@include file="sub_company_list_page.jsp" %>
					<input type="hidden" value="${companyQuery.count}" id="count"/>
					
					<input type="hidden" value="${companyQuery.companyId}" id="txtCompanyId"/>
					
            </div>
          </div>

        </div>
      </div>

    </section>
  </div>

  <%@include file="../share/qjp_footer.jsp"%>

  <!-- Control Sidebar -->
  <aside class="control-sidebar control-sidebar-dark">
    <!-- Create the tabs -->
    <ul class="nav nav-tabs nav-justified control-sidebar-tabs">
      <li class="active"><a href="#control-sidebar-home-tab" data-toggle="tab"><i class="fa fa-home"></i></a></li>
      <li><a href="#control-sidebar-settings-tab" data-toggle="tab"><i class="fa fa-gears"></i></a></li>
    </ul>
    <!-- Tab panes -->
    <div class="tab-content">
      <!-- Home tab content -->
      <div class="tab-pane active" id="control-sidebar-home-tab">
        <h3 class="control-sidebar-heading">Recent Activity</h3>
        <ul class="control-sidebar-menu">
          <li>
            <a href="javascript::;">
              <i class="menu-icon fa fa-birthday-cake bg-red"></i>

              <div class="menu-info">
                <h4 class="control-sidebar-subheading">Langdon's Birthday</h4>

                <p>Will be 23 on April 24th</p>
              </div>
            </a>
          </li>
        </ul>
        <!-- /.control-sidebar-menu -->

        <h3 class="control-sidebar-heading">Tasks Progress</h3>
        <ul class="control-sidebar-menu">
          <li>
            <a href="javascript::;">
              <h4 class="control-sidebar-subheading">
                Custom Template Design
                <span class="pull-right-container">
                  <span class="label label-danger pull-right">70%</span>
                </span>
              </h4>

              <div class="progress progress-xxs">
                <div class="progress-bar progress-bar-danger" style="width: 70%"></div>
              </div>
            </a>
          </li>
        </ul>
        <!-- /.control-sidebar-menu -->

      </div>
      <!-- /.tab-pane -->
      <!-- Stats tab content -->
      <div class="tab-pane" id="control-sidebar-stats-tab">Stats Tab Content</div>
      <!-- /.tab-pane -->
      <!-- Settings tab content -->
      <div class="tab-pane" id="control-sidebar-settings-tab">
        <form method="post">
          <h3 class="control-sidebar-heading">General Settings</h3>

          <div class="form-group">
            <label class="control-sidebar-subheading">
              Report panel usage
              <input type="checkbox" class="pull-right" checked>
            </label>

            <p>
              Some information about this general settings option
            </p>
          </div>
          <!-- /.form-group -->
        </form>
      </div>
      <!-- /.tab-pane -->
    </div>
  </aside>
  <div class="control-sidebar-bg"></div>
</div>


</body>
</html>