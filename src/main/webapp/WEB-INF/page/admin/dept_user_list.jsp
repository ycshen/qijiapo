<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>企家婆-专业的企业好管家</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <%@include file="../share/common_css.jsp"%>
<link rel="stylesheet" href="${ctx}/js/zTree/css/metroStyle/metroStyle.css" type="text/css">
<script src="${ctx}/js/jQuery/jquery-2.2.3.min.js"></script>
<script src="${ctx}/js/bootstrap/js/bootstrap.min.js"></script>
<script src="${ctx}/js/adminlte/dist/js/app.min.js"></script>
<script type="text/javascript" src="${ctx}/js/pagination/jquery.pagination.js"></script>
<script type="text/javascript" src="${ctx}/js/layer/layer.js"></script>
<script type="text/javascript" src="${ctx}/js/zTree/js/jquery.ztree.core.js"></script>
    <script type="text/javascript" src="${ctx}/js/zTree/js/jquery.ztree.excheck.js"></script>
    <script type="text/javascript" src="${ctx}/js/zTree/js/jquery.ztree.exedit.js"></script>
<script type="text/javascript" src="${ctx}/js/pages/admin/dept_user_list.js"></script>
<script type="text/javascript">
	var ctx = "${ctx}";
</script>
</head>
<body class="hold-transition skin-black sidebar-mini">
<div class="wrapper">
	<%@include file="../share/qjp_header.jsp"%>
 	<%@include file="../share/qjp_admin_menu.jsp"%>
  
  <div class="content-wrapper">
    <section class="content-header">
      <h1>
  		组织
        <small></small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 企家婆</a></li>
        <li class="active">企业管理</li>
        <li class="active">部门与员工</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
		
      <div class="row">
       <div class="col-xs-3">
       		<div class="box">
       			<ul id="treeDemo" class="ztree"></ul> 
       		</div>
       </div>
        <div class="col-xs-9">
          <div class="box">

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
		          	 	<input type="hidden" value="" id="hidDid"/>
		          		<h5><span id="spanName">${loginUser.companyName}</span>（<span id="spanCount">${userQuery.count}</span>名员工）</h5> 
		          	 </div>
		          	 <button type="button" id="btnAddUser" class="btn btn-default" style="display:none;" onclick="addUser();">添加本部门员工</button>
					  <div class="form-group">
					    <input type="text" class="form-control" id="txtUserName" placeholder="员工姓名">
					  </div>
					  <div class="form-group">
					    <input type="text" class="form-control" id="txtUserName" placeholder="电话号码">
					  </div>
					  <button type="button" class="btn btn-default">查询</button>
					  
					</form>
		        </div>
        
      </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table id="syslist" class="table table-bordered table-hover">
                <thead>
                <tr>
                  <th>操作</th>
                  <th>员工状态</th>
                  <th>员工姓名</th>
                  <th>员工账号</th>
                  <th>联系电话</th>
                  <th>所属部门</th>
                  <th>职务</th>
                </tr>
                </thead>
                <tbody>
                
                	<c:if test="${userQuery.items != null &&  userQuery.items.size() > 0}">
                		<c:forEach items="${userQuery.items}" var="user">
                			<tr>
		                		<td>
		                		<div class="btn-group">
			                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
			                          <span class="caret"></span>
			                        </button>
			                        <ul class="dropdown-menu">
			                          <li><a href="#">编辑</a></li>
			                          <c:if test="${user.status != 103}">
			                          	<li><a href="#" onclick="forbidLogin('${user.id}', '${user.userName}')">禁止登陆</a></li>
			                          </c:if>
			                          <li><a href="#">重置密码</a></li>
			                          <c:if test="${user.status == 101}">
			                         	 <li><a href="#" onclick="forbidUser('${user.id}', '${user.userName}')">停用</a></li>
			                         	</c:if>
			                        </ul>
			                      </div>
								</td>
								<td>
									<c:if test="${user.status == 101}">
										<span class="label label-success">正常</span>
									</c:if>
									<c:if test="${user.status == 102}">
										<span class="label label-warning">停用</span>
									</c:if>
									<c:if test="${user.status == 103}">
										<span class="label label-danger">禁止登陆</span>
									</c:if>
									
									
								</td>
		                		<td>${user.userName }</td>
		                		<td>${user.userName }</td>
		                		<td>${user.telphone }</td>
		                		<td>${user.departmentName }</td>
		                		<td></td>
	                		</tr>
                		</c:forEach>
                	</c:if>
                </tbody>
              </table>
            
            </div>
            <div class="box-header" style="text-align:center;padding:0px;">
            	<%@include file="dept_user_list_page.jsp" %>
					<input type="hidden" value="${userQuery.count}" id="count"/>
					
					<input type="hidden" value="${userQuery.userName}" id="txtUserName"/>
					<input type="hidden" value="${userQuery.telphone}" id="txtTelphone"/>
            </div>
          </div>

        </div>
      </div>

    </section>
  </div>

  <%@include file="../share/qjp_footer.jsp"%>
</div>


</body>
</html>