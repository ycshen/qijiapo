<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="shortcut icon" href="${pageContext.request.contextPath }/system.ico">
    <title>企家婆-专业的企业服务好帮手</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <%@include file="../share/common_css.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/plugins/datatables/dataTables.bootstrap.css">
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
    <link rel="stylesheet" href="${ctx}/js/select2/select2.min.css">
    <%@include file="../share/common_js.jsp" %>
    <script src="${ctx}/js/plugins/datatables/jquery.dataTables.min.js"></script>
    <script src="${ctx}/js/plugins/datatables/dataTables.bootstrap.min.js"></script>
    <script src="${ctx}/js/plugins/slimScroll/jquery.slimscroll.min.js"></script>
    <script src="${ctx}/js/plugins/fastclick/fastclick.js"></script>
    <script src="${ctx}/js/adminlte/dist/js/app.min.js"></script>
    <script src="${ctx}/js/select2/select2.full.min.js"></script>
    <script src="${ctx}/js/pages/common/province_city_area.js"></script>
    <script src="${ctx}/js/pages/user/user_profile.js"></script>
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

        .my-input {
            width: 150px;
        }

        .my-layui-input-inline {
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

    <%@include file="../share/qjp_header.jsp" %>

    <%@include file="../share/qjp_menu.jsp" %>

        <div class="content-wrapper">
            <!-- Content Header (Page header) -->
            <section class="content-header">
                <h1>
                    个人中心
                </h1>
                <ol class="breadcrumb">
                    <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
                    <li><a href="#">个人</a></li>
                    <li class="active">个人中心</li>
                </ol>
            </section>

            <!-- Main content -->
            <section class="content">

                <div class="row">
                    <div class="col-md-3">

                        <!-- Profile Image -->
                        <div class="box box-primary">
                            <div class="box-body box-profile">
                                <img class="profile-user-img img-responsive img-circle" src="${ctx}/js/adminlte/dist/img/user2-160x160.jpg" alt="我的头像">

                                <h3 class="profile-username text-center">${user.userName}</h3>

                                <p class="text-muted text-center">${user.positionName}</p>

                                <ul class="list-group list-group-unbordered">
                                    <li class="list-group-item">
                                        <b>公司同事</b> <a class="pull-right" href="#"><input type="button" value="${userCount}(人)" class="btn btn-default btn-xs"/></a>
                                    </li>
                                    <li class="list-group-item">
                                        <b>我的客户</b>  <a class="pull-right" href="${ctx}/inner/customer/list"><input type="button" value="${customerCount}(位)" class="btn btn-default btn-xs"/></a>
                                    </li>
                                    <li class="list-group-item">
                                        <b>销售业绩</b> <a class="pull-right">13,287</a>
                                    </li>
                                </ul>

                                <a href="#" class="btn btn-primary btn-block"><b>详细资料</b></a>
                            </div>
                            <!-- /.box-body -->
                        </div>
                        <!-- /.box -->

                        <!-- About Me Box -->
                        <div class="box box-primary">
                            <div class="box-header with-border">
                                <h3 class="box-title">关于我</h3>
                            </div>
                            <!-- /.box-header -->
                            <div class="box-body">
                                <strong><i class="fa fa-book margin-r-5"></i>教育程度</strong>

                                <p class="text-muted">
                                    ${user.school}
                                    <c:choose>
                                        <c:when test="${user.education != null && user.education != ''}">
                                            -${user.education}
                                        </c:when>
                                        <c:otherwise>

                                        </c:otherwise>
                                    </c:choose>

                                </p>

                                <hr>

                                <strong><i class="fa fa-map-marker margin-r-5"></i> 所在位置</strong>

                                <p class="text-muted">${user.provinceName}
                                    <c:if test="${user.cityName != null && user.cityName != ''}">
                                        ，${user.cityName}
                                    </c:if>
                                    <c:if test="${user.areaName != null && user.areaName != ''}">
                                        , ${user.areaName}
                                    </c:if>
                                   </p>

                                <%--<hr>

                                <strong><i class="fa fa-pencil margin-r-5"></i> 负责</strong>

                                <p>
                                    <span class="label label-danger">客户查找</span>
                                    <span class="label label-success">电话访问</span>
                                    <span class="label label-info">销售</span>
                                    <span class="label label-warning">售后</span>
                                    <span class="label label-primary">行政</span>
                                </p>--%>

                                <hr>

                                <strong><i class="fa fa-file-text-o margin-r-5"></i>个性签名</strong>

                                <p>${user.signature}</p>
                            </div>
                            <!-- /.box-body -->
                        </div>
                        <!-- /.box -->
                    </div>
                    <!-- /.col -->
                    <div class="col-md-9">
                        <div class="nav-tabs-custom">
                            <ul class="nav nav-tabs">
                               <%-- <li class="active"><a href="#activity" data-toggle="tab">Activity</a></li>--%>

                                <li class="active"><a href="#settings" data-toggle="tab">个人资料设置</a></li>
                            </ul>
                            <div class="tab-content">
                                <%--<div class="active tab-pane" id="activity">
                                    <!-- Post -->
                                    <div class="post">
                                        <div class="user-block">
                                            <img class="img-circle img-bordered-sm" src="../../dist/img/user1-128x128.jpg" alt="user image">
                                            <span class="username">
                          <a href="#">Jonathan Burke Jr.</a>
                          <a href="#" class="pull-right btn-box-tool"><i class="fa fa-times"></i></a>
                        </span>
                                            <span class="description">Shared publicly - 7:30 PM today</span>
                                        </div>
                                        <!-- /.user-block -->
                                        <p>
                                            Lorem ipsum represents a long-held tradition for designers,
                                            typographers and the like. Some people hate it and argue for
                                            its demise, but others ignore the hate as they create awesome
                                            tools to help create filler text for everyone from bacon lovers
                                            to Charlie Sheen fans.
                                        </p>
                                        <ul class="list-inline">
                                            <li><a href="#" class="link-black text-sm"><i class="fa fa-share margin-r-5"></i> Share</a></li>
                                            <li><a href="#" class="link-black text-sm"><i class="fa fa-thumbs-o-up margin-r-5"></i> Like</a>
                                            </li>
                                            <li class="pull-right">
                                                <a href="#" class="link-black text-sm"><i class="fa fa-comments-o margin-r-5"></i> Comments
                                                    (5)</a></li>
                                        </ul>

                                        <input class="form-control input-sm" type="text" placeholder="Type a comment">
                                    </div>
                                    <!-- /.post -->

                                    <!-- Post -->
                                    <div class="post clearfix">
                                        <div class="user-block">
                                            <img class="img-circle img-bordered-sm" src="../../dist/img/user7-128x128.jpg" alt="User Image">
                                            <span class="username">
                          <a href="#">Sarah Ross</a>
                          <a href="#" class="pull-right btn-box-tool"><i class="fa fa-times"></i></a>
                        </span>
                                            <span class="description">Sent you a message - 3 days ago</span>
                                        </div>
                                        <!-- /.user-block -->
                                        <p>
                                            Lorem ipsum represents a long-held tradition for designers,
                                            typographers and the like. Some people hate it and argue for
                                            its demise, but others ignore the hate as they create awesome
                                            tools to help create filler text for everyone from bacon lovers
                                            to Charlie Sheen fans.
                                        </p>

                                        <form class="form-horizontal">
                                            <div class="form-group margin-bottom-none">
                                                <div class="col-sm-9">
                                                    <input class="form-control input-sm" placeholder="Response">
                                                </div>
                                                <div class="col-sm-3">
                                                    <button type="submit" class="btn btn-danger pull-right btn-block btn-sm">Send</button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                    <!-- /.post -->

                                    <!-- Post -->
                                    <div class="post">
                                        <div class="user-block">
                                            <img class="img-circle img-bordered-sm" src="../../dist/img/user6-128x128.jpg" alt="User Image">
                                            <span class="username">
                          <a href="#">Adam Jones</a>
                          <a href="#" class="pull-right btn-box-tool"><i class="fa fa-times"></i></a>
                        </span>
                                            <span class="description">Posted 5 photos - 5 days ago</span>
                                        </div>
                                        <!-- /.user-block -->
                                        <div class="row margin-bottom">
                                            <div class="col-sm-6">
                                                <img class="img-responsive" src="../../dist/img/photo1.png" alt="Photo">
                                            </div>
                                            <!-- /.col -->
                                            <div class="col-sm-6">
                                                <div class="row">
                                                    <div class="col-sm-6">
                                                        <img class="img-responsive" src="../../dist/img/photo2.png" alt="Photo">
                                                        <br>
                                                        <img class="img-responsive" src="../../dist/img/photo3.jpg" alt="Photo">
                                                    </div>
                                                    <!-- /.col -->
                                                    <div class="col-sm-6">
                                                        <img class="img-responsive" src="../../dist/img/photo4.jpg" alt="Photo">
                                                        <br>
                                                        <img class="img-responsive" src="../../dist/img/photo1.png" alt="Photo">
                                                    </div>
                                                    <!-- /.col -->
                                                </div>
                                                <!-- /.row -->
                                            </div>
                                            <!-- /.col -->
                                        </div>
                                        <!-- /.row -->

                                        <ul class="list-inline">
                                            <li><a href="#" class="link-black text-sm"><i class="fa fa-share margin-r-5"></i> Share</a></li>
                                            <li><a href="#" class="link-black text-sm"><i class="fa fa-thumbs-o-up margin-r-5"></i> Like</a>
                                            </li>
                                            <li class="pull-right">
                                                <a href="#" class="link-black text-sm"><i class="fa fa-comments-o margin-r-5"></i> Comments
                                                    (5)</a></li>
                                        </ul>

                                        <input class="form-control input-sm" type="text" placeholder="Type a comment">
                                    </div>
                                    <!-- /.post -->
                                </div>--%>

                                <div  class="active tab-pane" id="settings">
                                    <form class="form-horizontal">
                                        <div class="form-group">
                                            <label for="inputName" class="col-sm-2 control-label">姓名</label>

                                            <div class="col-sm-10">
                                                <input type="email" class="form-control" value="${user.userName}" disabled="disabled" placeholder="请输入姓名">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="inputName" class="col-sm-2 control-label">电话号码</label>

                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" placeholder="请输入电话号码" value="${user.telphone}" disabled="disabled">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="txtEmail" class="col-sm-2 control-label">邮箱地址</label>

                                            <div class="col-sm-10">
                                                <input type="email" id="txtEmail" class="form-control" value="${user.email}" placeholder="请输入邮箱地址">
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label for="txtSchool" class="col-sm-2 control-label">学校</label>

                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" value="${user.school}" id="txtSchool" placeholder="请输入学校">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">教育程度</label>
                                            <div class="col-sm-10">
                                                <select class="form-control" id="selectEducation">
                                                    <option value="">请选择教育程度</option>
                                                    <option value="本科" <c:if test="${user.education == '本科'}"> selected="selected"</c:if>>本科</option>
                                                    <option value="硕士" <c:if test="${user.education == '硕士'}"> selected="selected"</c:if>>硕士</option>
                                                    <option value="博士" <c:if test="${user.education == '博士'}"> selected="selected"</c:if>>博士</option>
                                                    <option value="中专" <c:if test="${user.education == '中专'}"> selected="selected"</c:if>>中专</option>
                                                    <option value="高中" <c:if test="${user.education == '高中'}"> selected="selected"</c:if>>高中</option>
                                                    <option value="其他" <c:if test="${user.education == '其他'}"> selected="selected"</c:if>>其他</option>
                                                </select>
                                             </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="txtSignature" class="col-sm-2 control-label">个性签名</label>

                                            <div class="col-sm-10">
                                                <textarea class="form-control" id="txtSignature" placeholder="请输入个性签名">${user.signature}</textarea>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-sm-offset-2 col-sm-10">
                                                <button type="button" class="btn btn-danger" onclick="modifyInfo();">提交修改</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <!-- /.tab-pane -->
                            </div>
                            <!-- /.tab-content -->
                        </div>
                        <!-- /.nav-tabs-custom -->
                    </div>
                    <!-- /.col -->
                </div>
                <!-- /.row -->

            </section>
            <!-- /.content -->
        </div>
    <!-- /.content-wrapper -->
    <%@include file="../share/qjp_footer.jsp" %>
</body>
</html>