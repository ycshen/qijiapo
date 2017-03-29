<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>企家婆-专业的企业服务好帮手</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <%@include file="../share/common_css.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
    <%@include file="../share/common_js.jsp" %>
    <script src="${ctx}/js/pages/common/province_city_area.js"></script>
    <script type="text/javascript" src="${ctx}/js/pages/contract/contract_detail.js"></script>
    <script type="text/javascript">
        var ctx = "${pageContext.request.contextPath}";
    </script>
    <style type="text/css">
        .my-layui-form-item {
            margin: 0px 15px 0px 0px;
            clear: both;
        }

        .my-table tbody tr td {
            border-top: 0px;
        }

        .layui-form-label {
            float: left;
            display: block;
            width: 150px;
            font-weight: 400;
            text-align: right;
            padding: 9px 15px;
        }

        .layui-input-block {
            margin-left: 150px;
            min-height: 36px;
        }

        .my-top {
            margin-top: 10px;
        }

        .content_div {
            height: 300px;
            overflow-y: auto;
        }

        .title-td {
            width: 150px;
            text-align: right;
        }

        .title-td-left {
            width: 150px;
            text-align: left;
        }

        legend {
            font-size: 14px !important;
        }
    </style>
</head>
<body style="background: #fff;">
<div class="container-fluid">
    <div class="row">
        <div class="col-md-8">
            <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
                <ul class="layui-tab-title">
                    <li class="layui-this">资料</li>
                    <li>动态</li>
                    <li>销售机会</li>
                    <!--  <li>文档</li> -->
                </ul>
                <div class="layui-tab-content">
                    <div class="layui-tab-item layui-show">
                        <table class="table my-table" id="detailTable">
                            <!-- <tr>
                                <td class="title-td" colspan="4">
                                    <div class="layui-btn-group">
                                      <button class="layui-btn layui-btn-primary" id="btnEdit" onclick="fixCompetitor();">
                                        <i class="layui-icon">&#xe642;</i>编辑资料
                                      </button>
                                      <button  onclick="saveCompetitor();" class="layui-btn" id="btnSave" style="display:none;">
                                            <i class="layui-icon">&#xe620;</i>保存资料
                                      </button>
                                       <button  onclick="cancelCompetitor();" class="layui-btn layui-btn-primary"  id="btnCancel"  style="display:none;">
                                            <i class="layui-icon">&#xe620;</i>取消编辑
                                      </button>
                                    </div>
                                </td>

                            </tr> -->
                            <tr>
                                <td class="title-td">主题:</td>
                                <td>${contract.contractName}</td>
                                <td class="title-td">合同所属人:</td>
                                <td>${contract.userName}</td>

                            </tr>
                            <tr>
                                <td class="title-td">客户:</td>
                                <td>${contract.customerName}</td>
                                <td class="title-td">合同所属部门:</td>
                                <td>${contract.departmentName}</td>
                            </tr>

                            <tr>
                                <td class="title-td">开始日期:</td>
                                <td><f:formatDate value="${contract.contractStartTime}" pattern="yyyy-MM-dd"/></td>
                                <td class="title-td">结束日期:</td>
                                <td><f:formatDate value="${contract.contractEndTime}" pattern="yyyy-MM-dd"/></td>
                            </tr>
                            <tr>
                                <td class="title-td">合同类型:</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${contract.contractType == 1}">
                                            产品销售
                                        </c:when>
                                        <c:when test="${contract.contractType == 2}">
                                            服务
                                        </c:when>
                                        <c:when test="${contract.contractType == 3}">
                                            业务合作
                                        </c:when>
                                        <c:when test="${contract.contractType == 4}">
                                            代理分销
                                        </c:when>
                                        <c:otherwise>
                                            其他
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td class="title-td">合同状态:</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${contract.contractType == 1}">
                                            执行中
                                        </c:when>
                                        <c:when test="${contract.contractType == 2}">
                                            结束
                                        </c:when>
                                        <c:when test="${contract.contractType == 3}">
                                            意外终止
                                        </c:when>

                                    </c:choose>
                                </td>

                            </tr>
                            <tr>
                                <td class="title-td">付款方式:</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${contract.paymentMethod == 1}">
                                            支票
                                        </c:when>
                                        <c:when test="${contract.paymentMethod == 2}">
                                            现金
                                        </c:when>
                                        <c:when test="${contract.paymentMethod == 3}">
                                            银行转账
                                        </c:when>
                                        <c:otherwise>
                                            其他
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td class="title-td">总金额:</td>
                                <td>${contract.totalPrice}</td>
                            </tr>
                            <tr>

                                <td class="title-td">客户方签约人:</td>
                                <td>${contract.customerSigner}</td>
                                <td class="title-td">我方签约人:</td>
                                <td>${contract.signerId}</td>
                            </tr>
                            <tr>
                                <td class="title-td">签约日期:</td>
                                <td><f:formatDate value="${contract.signTime}" pattern="yyyy-MM-dd" /></td>
                                <td class="title-td">合同编号:</td>
                                <td>${contract.contractNum}</td>

                            </tr>
                            <tr>

                                <td class="title-td">业务类型:</td>
                                <td>默认业务类型</td>

                            </tr>
                            <tr>
                                <td class="title-td">合同正文:</td>
                                <td colspan="3">${contract.contractBody}</td>

                            </tr>
                            <tr>
                                <td class="title-td">备注:</td>
                                <td colspan="3">${contract.remark}</td>

                            </tr>
                        </table>


                    </div>
                    <div class="layui-tab-item">
                        <table class="table table-hover">
                            <tr>
                                <td>操作时间</td>
                                <td>操作人</td>
                                <td>动态内容</td>
                            </tr>
                            <c:if test="${logList != null && logList.size() > 0 }">
                                <c:forEach items="${logList}" var="log">
                                    <tr>
                                        <td><f:formatDate value="${log.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                        <td>${log.userName }</td>
                                        <td>${log.logMsg }</td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                        </table>
                    </div>
                    <div class="layui-tab-item">内容3</div>
                    <!-- <div class="layui-tab-item">内容4</div> -->
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <table class="table my-table">
                <tr>
                    <td class="title-td-left" colspan="1">
                        <fieldset class="layui-elem-field">
                            <legend>操作</legend>
                            <div class="layui-field-box">
                                <div class="layui-btn-group">
                                    <button class="layui-btn layui-btn-primary"
                                            onclick="transfer('${contract.id}', '${contract.contractName}');">
                                        <i class="layui-icon">&#xe620;</i>转移给他人
                                    </button>
                                    <button class="layui-btn layui-btn-primary"
                                            onclick="deleteById('${contract.id}','${contract.contractName}');">
                                        <i class="layui-icon">&#xe640;</i>删除
                                    </button>
                                </div>
                            </div>
                        </fieldset>
                    </td>

                </tr>
                <tr>
                    <td class="title-td-left" colspan="1">
                        <fieldset class="layui-elem-field">
                            <legend>合同负责人</legend>
                            <div class="layui-field-box">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <!-- The user image in the navbar-->
                                    <img src="/qijiapo/js/adminlte/dist/img/user2-160x160.jpg" class="user-image"
                                         style="width: 35px;height:35px;" alt="User Image">
                                    <span class="hidden-xs">${contract.userName}</span>
                                </a>

                            </div>
                        </fieldset>
                    </td>

                </tr>
                <tr>
                    <td class="title-td-left" colspan="1">
                        <fieldset class="layui-elem-field">
                            <legend>负责员工</legend>
                            <div class="layui-field-box">
                            </div>
                        </fieldset>
                    </td>

                </tr>
                <tr>
                    <td class="title-td-left" colspan="1">
                        <fieldset class="layui-elem-field">
                            <legend>相关员工</legend>
                            <div class="layui-field-box">
                            </div>
                        </fieldset>
                    </td>

                </tr>
                <%-- <tr>
                    <td class="title-td-left" colspan="1">
                        <fieldset class="layui-elem-field">
                          <legend>文档</legend>
                          <div class="layui-field-box">
                        </div>
                        </fieldset>
                    </td>

                </tr> --%>
            </table>
        </div>
    </div>
</div>


<script>
    layui.use('element', function () {
        var $ = layui.jquery
            , element = layui.element(); //Tab的切换功能，切换事件监听等，需要依赖element模块

        //触发事件
        var active = {
            tabAdd: function () {
                //新增一个Tab项
                element.tabAdd('demo', {
                    title: '新选项' + (Math.random() * 1000 | 0) //用于演示
                    , content: '内容' + (Math.random() * 1000 | 0)
                })
            }
            , tabDelete: function () {
                //删除指定Tab项
                element.tabDelete('demo', 2); //删除第3项（注意序号是从0开始计算）
            }
            , tabChange: function () {
                //切换到指定Tab项
                element.tabChange('demo', 1); //切换到第2项（注意序号是从0开始计算）
            }
        };

        $('.site-demo-active').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    });
</script>


</body>
</html>