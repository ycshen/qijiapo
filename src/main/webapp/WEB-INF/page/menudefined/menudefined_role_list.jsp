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
  <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
<link rel="stylesheet" href="${ctx}/js/zTree/css/metroStyle/metroStyle.css" type="text/css">
<script src="${ctx}/js/jQuery/jquery-2.2.3.min.js"></script>
<script src="${ctx}/js/bootstrap/js/bootstrap.min.js"></script>
<script src="${ctx}/js/adminlte/dist/js/app.min.js"></script>
<script type="text/javascript" src="${ctx}/js/pagination/jquery.pagination.js"></script>
<script type="text/javascript" src="${ctx}/js/layer/layer.js"></script>
<script type="text/javascript" src="${ctx}/js/zTree/js/jquery.ztree.core.js"></script>
    <script type="text/javascript" src="${ctx}/js/zTree/js/jquery.ztree.excheck.js"></script>
    <script type="text/javascript" src="${ctx}/js/zTree/js/jquery.ztree.exedit.js"></script>
<script type="text/javascript" src="${ctx}/js/pages/menudefined/menudefined_role_list.js"></script>
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
  		菜单自定义->按权限定义
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
      <div class="col-xs-12" style="margin-bottom:10px;">
	      <div class="layui-btn-group">
		  	  <button class="layui-btn layui-btn-primary"  onclick="returnMenuDefined();">
				<i class="layui-icon">&#x1002;</i>返回菜单自定义
				
			  </button>
			  <button class="layui-btn layui-btn-primary"  onclick="definedByUser();">
			    <i class="layui-icon">&#xe620;</i>按用户定义
			  </button>
			  <button class="layui-btn layui-btn-primary"  onclick="definedByPosition();">
			    <i class="layui-icon">&#xe620;</i>按职位定义
			  </button>
			  <button class="layui-btn layui-btn-primary"  onclick="definedByDept();">
			    <i class="layui-icon">&#xe620;</i>按部门定义
			  </button>
			</div>
      </div>
        <div class="col-xs-5">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title">权限（权限）列表</h3>
            </div>
            <div class="box">
        <!-- <div class="box-body">
          	<form class="form-inline" role="form">
			  <div class="form-group">
			    <input type="text" class="form-control" id="txtAuthName" placeholder="权限(权限)名称">
			  </div>
			  
			  <button type="button" class="btn btn-default">查询</button>
			</form>
        </div> -->
        
      </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table id="syslist" class="table table-bordered table-hover">
                <thead>
                <tr class="info">
               		 <th>序号</th>
	                  <th>权限（权限）名称</th>
                </tr>
                </thead>
                <tbody>
                	<c:if test="${authQuery.items != null &&  authQuery.items.size() > 0}">
                		<c:forEach items="${authQuery.items}" var="auth" varStatus="status">
                			<input type="hidden" id="hidAuthInfo${status.index }" value="${auth.id}_${auth.authName}"/>
                			<tr onclick="showAuthMenu('${auth.id}', '${auth.authName}')">
                				<td>${status.index + 1 }</td>
		                		<td>${auth.authName }</td>
	                		</tr>
                		</c:forEach>
                	</c:if>
                </tbody>
                
                 
               
              </table>
            
            </div>
            <div class="box-header" style="text-align:center">
            	<%@include file="menudefined_role_list_page.jsp" %>
					<input type="hidden" value="${authQuery.count}" id="count"/>
					
					
            </div>
          </div>

        </div>
        
        <div class="col-xs-7">
          <div class="box-header">
       			菜单
       		</div>
       		
       		<div class="box-header" id="divMenuOper">
       			<span id="spanTips"></span>
       			<button class="btn btn-info " onclick="submitMD();">提交菜单权限</button>
       			<button class="btn" onclick="resetMenu();">重置</button>
       		</div>
          <div class="box">
            <!-- /.box-header -->
            <div class="box-body">
              <ul id="menuTree" class="ztree"></ul> 
            
            </div>
           
          </div>

        </div>
      </div>

    </section>
  </div>

	<input type="hidden" id="hidAuthId"/>
	<input type="hidden" id="hidAuthName"/>
  <%@include file="../share/qjp_footer.jsp"%>

  <!-- Control Sidebar -->
  
  <div class="control-sidebar-bg"></div>
</div>


</body>
</html>