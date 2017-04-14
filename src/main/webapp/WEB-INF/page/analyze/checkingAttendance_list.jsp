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
    <%--<script src="${ctx}/js/pages/analyze/checkingAttendance_list.js"></script>--%>
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
    <section class="content-header">

        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> 企家婆</a></li>
            <li class="active">考勤统计</li>
        </ol>
    </section>
    <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
        <ul class="layui-tab-title">
            <li class="layui-this">考勤记录</li>
            <li>考勤分析</li>
        </ul>
        <div class="layui-tab-content">
            <div class="layui-tab-item layui-show">

                <section class="content">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="box mybox">
                                <div class="box-body my-box-body">
                                    <form class="layui-form"  onsubmit="return false;">
                                        <table class="table mytable">
                                            <tr>
                                                <td>
                                                    <label class="layui-form-label my-label">姓名</label>
                                                    <input type="text" class="layui-input my-input" id="txtUserName" placeholder="姓名">
                                                </td>
                                                <td>
                                                    <label class="layui-form-label my-label">开始日期</label>
                                                    <input type="text" class="layui-input my-input" id="txtStartTime" placeholder="开始日期">

                                                </td>
                                                <td>
                                                    <label class="layui-form-label my-label">结束日期</label>
                                                    <input type="text" class="layui-input my-input" id="txtEndTime" placeholder="结束日期">
                                                </td>
                                                <td colspan="3">

                                                </td>

                                            </tr>
                                            <tr>

                                                <td colspan="6">
                                                    <div class="layui-btn-group">
                                                        <button class="layui-btn layui-btn-primary" onclick="queryProduct();">
                                                            <i class="layui-icon">&#x1002;</i>筛选
                                                        </button>
                                                        <button class="layui-btn layui-btn-primary" onclick="queryProduct();">
                                                            <i class="layui-icon">&#xe63c;</i>导出
                                                        </button>
                                                    </div>
                                                </td>
                                            </tr>

                                        </table>

                                    </form>

                                </div>
                                <div class="box-body">
                                    <table id="myDataTable" class="table table-bordered table-striped">
                                        <thead>
                                        <tr>
                                            <th>姓名</th>
                                            <th>部门</th>
                                            <th>地点</th>
                                            <th>日期</th>
                                            <th>上班</th>
                                            <th>下班</th>
                                            <th>工作时间</th>
                                            <th>状态</th>
                                            <th>迟到原因</th>
                                            <th>早退原因</th>
                                            <th>上班打卡位置</th>
                                            <th>下班打卡位置</th>
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
            </div>
            <div class="layui-tab-item">
                <section class="content">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="box mybox">
                                <div class="box-body my-box-body">
                                    <form class="layui-form"  onsubmit="return false;">
                                        <table class="table mytable">
                                            <tr>
                                                <td>
                                                    <label class="layui-form-label my-label">开始日期</label>
                                                    <input type="text" class="layui-input my-input" id="txtStartTime" placeholder="开始日期">

                                                </td>
                                                <td>
                                                    <label class="layui-form-label my-label">结束日期</label>
                                                    <input type="text" class="layui-input my-input" id="txtEndTime" placeholder="结束日期">
                                                </td>
                                                <td colspan="3">

                                                </td>

                                            </tr>
                                            <tr>

                                                <td colspan="6">
                                                    <div class="layui-btn-group">
                                                        <button class="layui-btn layui-btn-primary" onclick="queryProduct();">
                                                            <i class="layui-icon">&#x1002;</i>筛选
                                                        </button>
                                                        <button class="layui-btn layui-btn-primary" onclick="queryProduct();">
                                                            <i class="layui-icon">&#xe63c;</i>导出
                                                        </button>
                                                    </div>
                                                </td>
                                            </tr>

                                        </table>

                                    </form>

                                </div>
                                <div class="box-body">
                                    <table id="myDataTable1" class="table table-bordered table-striped">
                                        <thead>
                                        <tr>
                                            <th>姓名</th>
                                            <th>正常（天）</th>
                                            <th>迟到（次）</th>
                                            <th>早退（次）</th>
                                            <th>不完整打卡（次）</th>
                                            <th>设备异常（次）</th>
                                            <th>不在考勤点（次）</th>
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
            </div>
        </div>
    </div>
</div>

<%@include file="../share/qjp_footer.jsp"%>
    <script type="text/javascript">
        layui.use('element', function(){
            var $ = layui.jquery
                    ,element = layui.element(); //Tab的切换功能，切换事件监听等，需要依赖element模块

            element.on('tab(docDemoTabBrief)', function(data){
                if(data.index == 2){
                    //产品tab
                    reloadProduct(${salesOpportunity.id});
                }
            });
        });

    </script>
</body>

</html>