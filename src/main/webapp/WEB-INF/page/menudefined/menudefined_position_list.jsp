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
  <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
<script src="${ctx}/js/jQuery/jquery-2.2.3.min.js"></script>
<script src="${ctx}/js/bootstrap/js/bootstrap.min.js"></script>
<script src="${ctx}/js/adminlte/dist/js/app.min.js"></script>
<script type="text/javascript" src="${ctx}/js/pagination/jquery.pagination.js"></script>
<script type="text/javascript" src="${ctx}/js/layer/layer.js"></script>
<script type="text/javascript" src="${ctx}/js/pages/position/position_page_list.js"></script>
<script type="text/javascript">
	var ctx = "${ctx}";
</script>
</head>
<body class="hold-transition skin-black sidebar-mini">
<div class="wrapper">
	<%@include file="../share/qjp_header.jsp"%>
 <jsp:include page="../share/qjp_admin_menu.jsp">
	<jsp:param name="nav" value="position_list" />
</jsp:include>
  
  <!-- Content Wrapper. Contains page content -->
 <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
  		职位管理
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
      	<div class="col-xs-12" style="margin-bottom:10px;">
	      <div class="layui-btn-group">
		  	  <button class="layui-btn layui-btn-primary">
				<i class="layui-icon">&#x1002;</i>返回菜单自定义
				
			  </button>
			  <button class="layui-btn layui-btn-primary" onclick="addCompeotitor();">
			    <i class="layui-icon">&#xe620;</i>按用户定义
			  </button>
			  <button class="layui-btn layui-btn-primary">
			    <i class="layui-icon">&#xe620;</i>按部门定义
			  </button>
			  <button class="layui-btn layui-btn-primary">
			    <i class="layui-icon">&#xe620;</i>按权限定义
			  </button>
			</div>
      </div>
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title">职位列表</h3>
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
			    <input type="text" class="form-control" id="txtpositionName" placeholder="职位名称">
			  </div>
			  
			  <button type="button" class="btn btn-default" onclick="queryPosition();">查询</button>
			  <button type="button" class="btn btn-default" onclick="addPosition();">添加职位</button>
			</form>
        </div>
        
      </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table id="syslist" class="table table-bordered table-hover">
                <thead>
                <tr class="info">
                  <th>操作</th>
                  <th>职位</th>
                  <th>状态</th>
                </tr>
                </thead>
                <tbody>
                	<c:if test="${positionQuery.items != null &&  positionQuery.items.size() > 0}">
                		<c:forEach items="${positionQuery.items}" var="position">
                			<tr>
		                		<td id="positionOper${position.id}">
									<div class="btn-group">
			                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
			                          <span class="caret"></span>
			                        </button>
			                        <ul class="dropdown-menu">
			                          
			                          <c:if test="${position.isDelete == 0}">
			                         	 <li><a href="#" onclick="editPosition('${position.id}');">编辑</a></li>
			                         	 <li><a href="#" onclick="stopPosition('${position.id}', '${position.postionName}')">停用</a></li>
			                         	</c:if>
			                         	<c:if test="${position.isDelete == 1}">
			                         	 <li><a href="#" onclick="startPosition('${position.id}', '${position.postionName}')">启用</a></li>
			                         	</c:if>
			                        </ul>
			                      </div>
								</td>
		                		<td id="positionName${position.id}">${position.postionName }</td>
		                		<td id="positionStatus${position.id}">
		                			<c:if test="${position.isDelete == 0}">
										<span class="label label-success">正常</span>
									</c:if>
									<c:if test="${position.isDelete == 1}">
										<span class="label label-warning">停用</span>
									</c:if>
								
		                		</td>
	                		</tr>
                		</c:forEach>
                	</c:if>
                </tbody>
                
                 
               
              </table>
            
            </div>
            <div class="box-header" style="text-align:center">
            	<%@include file="menudefined_position_list_page.jsp" %>
					<input type="hidden" value="${positionQuery.count}" id="count"/>
					
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