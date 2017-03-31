<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <link rel="shortcut icon" href="${pageContext.request.contextPath }/system.ico" >
  <title>企家婆-专业的企业服务好帮手</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
   <%@include file="../share/common_css.jsp"%>
<link rel="stylesheet" href="${ctx}/js/plugins/datatables/dataTables.bootstrap.css">
<link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
<link rel="stylesheet" href="${ctx}/js/select2/select2.min.css">
<%@include file="../share/common_js.jsp"%>
<script src="${ctx}/js/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="${ctx}/js/plugins/datatables/dataTables.bootstrap.min.js"></script>
<script src="${ctx}/js/plugins/slimScroll/jquery.slimscroll.min.js"></script> 
<script src="${ctx}/js/plugins/fastclick/fastclick.js"></script>
<script src="${ctx}/js/adminlte/dist/js/app.min.js"></script>
<script src="${ctx}/js/select2/select2.full.min.js"></script>
<script src="${ctx}/js/pages/product/select_product_list.js"></script>
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

    <section class="content">
      <div class="row">
        <div class="col-md-12">
          <div class="box mybox"  style="border: 0px;">
            <div class="box-body my-box-body">
            		<form class="layui-form"  onsubmit="return false;">
	          		<table class="table mytable">
	          			<tr>
	          				<td style="width:100px;">
	          				<label class="layui-form-label my-label">产品名称</label>
		          				
						  </td>
						  <td><input type="text" class="layui-input my-input" id="txtProductName" placeholder="产品名称"></td>
	          				
	          				<td colspan="4">
									
	          				</td>
	          				
	          			</tr>
	          			<tr>
	          			
	          			<td colspan="6">
				  		<div class="layui-btn-group">
					  	  <button class="layui-btn layui-btn-primary" onclick="queryProduct();">
							<i class="layui-icon">&#x1002;</i>查询
							
						  </button>
						  <button class="layui-btn layui-btn-primary" onclick="confirmSelect();">
						    <i class="layui-icon">&#xe61f;</i>选择产品
						  </button>
                            <button class="layui-btn layui-btn-primary" onclick="confirmSelect();">
                                <i class="layui-icon"></i>取消选择
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
                  <th></th>
                  <th>产品名称</th>
                  <th>标准价格(元)</th>
                  <th>启用状态</th>
                  <th>创建日期</th>
                </tr>
                </thead>
                <tbody>
                </tfoot>
              </table>
            </div>
          </div>
          
        </div>
         
      </div>
    </section>

</body>
</html>