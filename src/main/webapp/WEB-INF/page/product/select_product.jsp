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
<script src="${ctx}/js/pages/common/province_city_area.js"></script>
<script src="${ctx}/js/pages/product/product_list.js"></script>
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
    	text-align:center;
    }
    .mytable tr th{
    	border: 0px;
    	text-align:center;
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

      <div class="row">
        <div class="col-md-12">
            <table class="table mytable" style="margin-bottom: 0px;text-align:right;margin-top: 20px;">
                <tr>
                    <td>已添加产品：(9)种</td>
                    <td>销售总金额（元）：1699</td>
                    <td>
                        <button class="layui-btn layui-btn-primary" onclick="addProduct();">
                            <i class="layui-icon">&#xe61f;</i>选择产品
                        </button>
                        <button class="layui-btn" lay-submit="" lay-filter="mySubmit" onclick="confirmSelect();">确定添加</button>
                        <button class="layui-btn layui-btn-primary" style="margin-right:50px;" onclick="cancelEdit();">取消添加</button>
                    </td>
                </tr>
            </table>
            <!-- /.box-header -->
            <div class="box-body" style="overflow-y:auto;overflow-x:auto;">
              <table class="table table-bordered table-striped mytable">
                <thead>
                <tr>
                  <th>操作</th>
                  <th>产品名称</th>
                  <th>标准价格(元)</th>
                  <th>销售单价(元)</th>
                  <th>数量</th>
                  <th>折扣</th>
                  <th>销售金额(元)</th>
                  <th>销售单位</th>
                  <th>备注</th>
                </tr>
                 <tr>
                  <td><a><img src="${ctx}/img/product/product_delete_normal.png"/></a></td>
                  <td>CRM</td>
                  <td>1699</td>
                  <td><input type="text" class="form-control" value="1" style="width: 40px;height:30px;display:inline"/></td>
                  <td><input type="text" class="form-control" value="1" style="width: 40px;height:30px;display:inline"/></td>
                  <td><input type="text" class="form-control" value="1" style="width: 40px;height:30px;display:inline"/></td>
                  <td><input type="text" class="form-control" value="1" style="width: 40px;height:30px;display:inline"/></td>
                  <td>企家婆</td>
                  <td><textarea style="height: 30px;width: 80px;"></textarea></td>
                </tr>
                 <tr>
                  <td><a><img src="${ctx}/img/product/product_delete_normal.png"/></a></td>
                  <td>CRM</td>
                  <td>1699</td>
                  <td><input type="text" class="form-control" value="1" style="width: 40px;height:30px;display:inline"/></td>
                  <td><input type="text" class="form-control" value="1" style="width: 40px;height:30px;display:inline"/></td>
                  <td><input type="text" class="form-control" value="1" style="width: 40px;height:30px;display:inline"/></td>
                  <td><input type="text" class="form-control" value="1" style="width: 40px;height:30px;display:inline"/></td>
                  <td>企家婆</td>
                  <td><textarea style="height: 30px;width: 80px;"></textarea></td>
                </tr>
                 <tr>
                  <td><a><img src="${ctx}/img/product/product_delete_normal.png"/></a></td>
                  <td>CRM</td>
                  <td>1699</td>
                  <td><input type="text" class="form-control" value="1" style="width: 40px;height:30px;display:inline"/></td>
                  <td><input type="text" class="form-control" value="1" style="width: 40px;height:30px;display:inline"/></td>
                  <td><input type="text" class="form-control" value="1" style="width: 40px;height:30px;display:inline"/></td>
                  <td><input type="text" class="form-control" value="1" style="width: 40px;height:30px;display:inline"/></td>
                  <td>企家婆</td>
                  <td><textarea style="height: 30px;width: 80px;"></textarea></td>
                </tr>
                 <tr>
                  <td><a><img src="${ctx}/img/product/product_delete_normal.png"/></a></td>
                  <td>CRM</td>
                  <td>1699</td>
                  <td><input type="text" class="form-control" value="1" style="width: 40px;height:30px;display:inline"/></td>
                  <td><input type="text" class="form-control" value="1" style="width: 40px;height:30px;display:inline"/></td>
                  <td><input type="text" class="form-control" value="1" style="width: 40px;height:30px;display:inline"/></td>
                  <td><input type="text" class="form-control" value="1" style="width: 40px;height:30px;display:inline"/></td>
                  <td>企家婆</td>
                  <td><textarea style="height: 30px;width: 80px;"></textarea></td>
                </tr>
                 <tr>
                  <td><a><img src="${ctx}/img/product/product_delete_normal.png"/></a></td>
                  <td>CRM</td>
                  <td>1699</td>
                  <td><input type="text" class="form-control" value="1" style="width: 40px;height:30px;display:inline"/></td>
                  <td><input type="text" class="form-control" value="1" style="width: 40px;height:30px;display:inline"/></td>
                  <td><input type="text" class="form-control" value="1" style="width: 40px;height:30px;display:inline"/></td>
                  <td><input type="text" class="form-control" value="1" style="width: 40px;height:30px;display:inline"/></td>
                  <td>企家婆</td>
                  <td><textarea style="height: 30px;width: 80px;"></textarea></td>
                </tr>
                
                </thead>
                <tbody>
                </tfoot>
              </table>
            </div>
            <!-- /.box-body -->

          </div>
        </div>
</body>
</html>