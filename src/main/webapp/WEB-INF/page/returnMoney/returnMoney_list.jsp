<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>企家婆-专业的企业服务好帮手</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <%@include file="../share/common_css.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/plugins/datatables/dataTables.bootstrap.css">
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
    <script type="text/javascript" src="${ctx}/js/jquery.js"></script>
    <script src="${ctx}/js/bootstrap/js/bootstrap.min.js"></script>
    <script src="${ctx}/js/plugins/datatables/jquery.dataTables.min.js"></script>
    <script src="${ctx}/js/plugins/datatables/dataTables.bootstrap.min.js"></script>
    <script src="${ctx}/js/plugins/slimScroll/jquery.slimscroll.min.js"></script>
    <script src="${ctx}/js/plugins/fastclick/fastclick.js"></script>
    <script src="${ctx}/js/adminlte/dist/js/app.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/layer/layer.js"></script>
    <script type="text/javascript" src="${ctx}/js/layui/layui.js"></script>
    <script src="${ctx}/js/pages/common/province_city_area.js"></script>
    <script src="${ctx}/js/pages/returnMoney/returnMoney_list.js"></script>
    <style type="text/css">
        .mybox {
            margin-bottom: 0px;
        }

        .my-box-body {
            padding: 0px;
        }

        .my-label {
            width: 120px;

        }

        .mytable tr td {
            border: 0px;
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

    <%@include file="../share/qjp_header.jsp" %>
    <%@include file="../share/qjp_menu.jsp" %>
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                回款
                <small></small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 企家婆</a></li>
                <li class="active">回款</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <div class="box mybox">
                        <div class="box-body my-box-body">
                            <form class="layui-form" onsubmit="return false;">
                                <table class="table mytable">
                                    <tr>
                                        <td colspan="2">
                                            <label class="layui-form-label my-label">合同名称</label>
                                            <div class="layui-input-inline">
                                                <select lay-search="">
                                                    <option value="">请选择或搜索合同名称</option>
                                                    <option value="1">layer</option>
                                                    <option value="2">form</option>
                                                    <option value="3">layim</option>
                                                    <option value="4">element</option>
                                                    <option value="5">laytpl</option>
                                                    <option value="6">upload</option>
                                                    <option value="7">laydate</option>
                                                    <option value="8">laypage</option>
                                                    <option value="9">flow</option>
                                                    <option value="10">util</option>
                                                    <option value="11">code</option>
                                                    <option value="12">tree</option>
                                                    <option value="13">layedit</option>
                                                    <option value="14">nav</option>
                                                    <option value="15">tab</option>
                                                    <option value="16">table</option>
                                                    <option value="17">select</option>
                                                    <option value="18">checkbox</option>
                                                    <option value="19">switch</option>
                                                    <option value="20">radio</option>
                                                </select>
                                            </div>
                                        </td>
                                        <td colspan="2">
                                            <label class="layui-form-label my-label">客户名称</label>
                                            <div class="layui-input-inline">
                                                <select  lay-search="">
                                                    <option value="">请选择或搜索客户名称</option>
                                                    <option value="1">layer</option>
                                                    <option value="2">form</option>
                                                    <option value="3">layim</option>
                                                    <option value="4">element</option>
                                                    <option value="5">laytpl</option>
                                                    <option value="6">upload</option>
                                                    <option value="7">laydate</option>
                                                    <option value="8">laypage</option>
                                                    <option value="9">flow</option>
                                                    <option value="10">util</option>
                                                    <option value="11">code</option>
                                                    <option value="12">tree</option>
                                                    <option value="13">layedit</option>
                                                    <option value="14">nav</option>
                                                    <option value="15">tab</option>
                                                    <option value="16">table</option>
                                                    <option value="17">select</option>
                                                    <option value="18">checkbox</option>
                                                    <option value="19">switch</option>
                                                    <option value="20">radio</option>
                                                </select>
                                            </div>
                                        </td>
                                        <td colspan="2">
                                            <label class="layui-form-label my-label">是否逾期</label>
                                            <div class="layui-input-inline">
                                                <select lay-search="">
                                                    <option value="">请选择或搜索是否逾期</option>
                                                    <option value="1">未逾期</option>
                                                    <option value="2">有逾期</option>
                                                </select>
                                            </div>
                                        </td>

                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <div class="layui-btn-group">
                                                <button class="layui-btn layui-btn-primary" onclick="queryXXX()">
                                                    <i class="layui-icon">&#x1002;</i>查询

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
                                    <th>编号</th>
                                    <th>合同名称</th>
                                    <th>客户名称</th>
                                    <th>回款期次</th>
                                    <th>计划回款时间</th>
                                    <th>计划回款金额</th>
                                    <th>实际回款金额</th>
                                    <th>未回款金额</th>
                                    <th>本期回款状态</th>
                                    <th>总金额</th>
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
    <%@include file="../share/qjp_footer.jsp" %>
        <script>
            layui.use(['form', 'layedit', 'laydate'], function(){
                var form = layui.form()
                        ,layer = layui.layer
                        ,layedit = layui.layedit
                        ,laydate = layui.laydate;



            });
        </script>
</body>
</html>