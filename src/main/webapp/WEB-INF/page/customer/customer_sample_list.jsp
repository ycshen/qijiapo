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
<script src="${ctx}/js/pages/customer/customer_sample_list.js"></script>
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
<body class="hold-transition skin-black sidebar-mini">


    <section class="content">
      <div class="row">
        <div class="col-md-12">
          <div class="box mybox">
            <div class="box-body my-box-body">
            		<form class="layui-form"  onsubmit="return false;">
	          		<table class="table mytable">
	          			<tr>
	          			<td>
							
						</td>
	          				<td style="width:100px;">
	          				<label class="layui-form-label my-label">客户名称</label>
		          				
						  </td>
						  <td><input type="text" class="layui-input my-input" id="txtCustomerName" placeholder="客户名称"></td>
	          				<td style="width:100px;">
	          				<label class="layui-form-label my-label">电话</label>
		          				
						  </td>
						  <td><input type="text" class="layui-input my-input" id="txtMobile" placeholder="电话"></td>
	          				
	          				<td colspan="2">
									<button class="layui-btn layui-btn-primary" onclick="queryCustomer();">
										<i class="layui-icon">&#x1002;</i>查询
										
									  </button>
									 
	          				</td>
	          				<td>&nbsp;&nbsp;</td>
	          				
	          			</tr>
	          			<tr>
	          			
	          			
	          			</tr>
	          			
	          		</table>
				  
								</form>
	          					
	        </div>
            <div class="box-body"  style="height: 220px; width: 750px;overflow-y: auto;overflow-x: hidden;padding:0px;">
              <table id="myDataTable" class="table table-bordered table-striped">
                <thead>
                <tr>
                  <th>请选择</th>
                  <th>客户名称</th>
                  <th>电话</th>
                  <th>客户级别</th>
                  <th>省市区</th>
                </tr>
                </thead>
                <tbody>
                </tfoot>
              </table>
            </div>
            <div class="layui-form-item my-top" style="margin-top: 5px;">
			    <div class="layui-input-block" style="text-align:right;">
			      <button class="layui-btn" lay-submit="" lay-filter="mySubmit" onclick="confirmSelect();">确定选择</button>
			      <button class="layui-btn layui-btn-primary" style="margin-right:50px;" onclick="cancelEdit();">取消选择</button>
			    </div>
			  </div>  
          </div>
          
        </div>
         
      </div>
    </section>

</body>
</html>