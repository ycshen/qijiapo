<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>企家婆-专业的企业服务好帮手</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
   <%@include file="../share/common_css.jsp"%>
<link rel="stylesheet" href="${ctx}/js/plugins/datatables/dataTables.bootstrap.css">
<link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
<link rel="stylesheet" href="${ctx}/js/select2/select2.min.css">
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script src="${ctx}/js/bootstrap/js/bootstrap.min.js"></script>
<script src="${ctx}/js/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="${ctx}/js/plugins/datatables/dataTables.bootstrap.min.js"></script>
<script src="${ctx}/js/plugins/slimScroll/jquery.slimscroll.min.js"></script> 
<script src="${ctx}/js/plugins/fastclick/fastclick.js"></script>
<script src="${ctx}/js/adminlte/dist/js/app.min.js"></script>
<script src="${ctx}/js/select2/select2.full.min.js"></script>
<script type="text/javascript" src="${ctx}/js/layer/layer.js"></script> 
<script type="text/javascript" src="http://zhengjinfan.cn/js/area.js"></script> 
<script type="text/javascript" src="${ctx}/js/layui/layui.js"></script> 
<script src="${ctx}/js/pages/attn/attn_list.js"></script>
<style type="text/css">
	.mybox{
		margin-bottom: 0px;
	}
	.my-box-body{
		padding: 0px;
	}
	.my-label{
	    width: 120px;
    
    }
    .mytable tr td{
    	border: 0px;
    }
    .my-input{
    	width: 150px;
    }
    .my-layui-input-inline{
    	width: 100px;
    }
</style>
 <script type="text/javascript">
	var ctx = "${pageContext.request.contextPath}";
</script>


</head>
<c:choose>
	<c:when test="${loginUser.isCollapseMenu == 1}">
		<body class="hold-transition skin-black sidebar-mini sidebar-collapse">
	</c:when>
	<c:otherwise>
		<body class="hold-transition skin-black sidebar-mini">
	</c:otherwise>
</c:choose>


<div class="wrapper">

 <%@include file="../share/qjp_header.jsp"%>
  <%@include file="../share/qjp_menu.jsp"%>
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        	联系人
        <small></small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 企家婆</a></li>
        <li class="active">联系人</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-md-12">
          <div class="box mybox">
            <div class="box-body my-box-body">
            		<form class="layui-form"  onsubmit="return false;">
	          		<table class="table mytable">
	          			<tr>
	          				<td>
	          				<label class="layui-form-label my-label">联系人名称</label>
		          				
						  </td>
						  <td><input type="text" class="layui-input my-input" id="txtpositionName" placeholder="联系人名称"></td>
	          				<td><label class="layui-form-label my-label">省市区</label></td>
	          				<td colspan="3">
									<div class="layui-form-item">
										
										<div class="layui-input-inline" style="width: 120px;">
											<select name="province" lay-filter="province">
												<option value="">请选择省</option>
											</select>
										</div>
										<div class="layui-input-inline" style="width: 120px;">
											<select name="city" lay-filter="city">
												<option value="">请选择市</option>
											</select>
										</div>
										<div class="layui-input-inline" style="width: 120px;">
											<select name="area" lay-filter="area">
												<option value="">请选择县/区</option>
											</select>
										</div>
									</div>
							</div>
	          				</td>
	          				
	          			</tr>
	          			<tr>
	          			<td colspan="4">
				  		<div class="layui-btn-group">
					  	  <button class="layui-btn layui-btn-primary">
							<i class="layui-icon">&#x1002;</i>查询
							
						  </button>
						  <button class="layui-btn layui-btn-primary" onclick="addAttn();">
						    <i class="layui-icon">&#xe61f;</i>新增联系人
						  </button>
						  <button class="layui-btn layui-btn-primary">
						    <i class="layui-icon">&#xe640;</i>删除
						  </button>
						  <button class="layui-btn layui-btn-primary">
						    <i class="layui-icon">&#xe620;</i>转移
						  </button>
						</div>
	          			</td>
	          			</tr>
	          			
	          		</table>
				  
								</form>
	          					
	        </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table id="myDataTable" class="table table-bordered table-striped">
                <thead>
                <tr>
                  <th><input type="checkbox" name="attn-list-checkable"></th>
                  <th>操作</th>
                  <th>联系人名称</th>
                  <th>联系人所有人</th>
                  <th>所属部门</th>
                  <th>省份</th>
                  <th>地址</th>
                  <th>公司网址</th>
                  <th>总人数</th>
                  <th>上年销售额</th>
                </tr>
                </thead>
                <tbody>
               
                
                
                
                </tfoot>
              </table>
            </div>
            <!-- /.box-body -->
          </div>
          
        </div>
         
      </div>
    </section>
  </div>
  <!-- /.content-wrapper -->
<%@include file="../share/qjp_footer.jsp"%>
</body>
</html>