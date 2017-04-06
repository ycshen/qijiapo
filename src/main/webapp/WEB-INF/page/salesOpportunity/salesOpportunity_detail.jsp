<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>企家婆-专业的企业服务好帮手</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<%@include file="../share/common_css.jsp"%>
<link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
<%@include file="../share/common_js.jsp"%>
<script src="${ctx}/js/pages/common/province_city_area.js"></script>
<script type="text/javascript" src="${ctx}/js/pages/salesopportunity/salesopportunity_detail.js"></script>
<script type="text/javascript">
	var ctx = "${pageContext.request.contextPath}";
</script>
<style type="text/css">
.my-layui-form-item{
margin: 0px 15px 0px 0px; 
    clear: both;
}

.my-table tbody tr td{
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
.my-top{
	margin-top: 10px;
}
.content_div{
	height: 300px;
	overflow-y:auto;
}
.title-td{
	width: 150px;
	text-align:right;
}

.title-td-left{
	width: 150px;
	text-align:left;
}
legend{
font-size: 14px !important;
}
</style>
</head>
<body style="background: #fff;">
<div class="container-fluid">
	<div class="row">
        <div class="col-md-8">
			<div class="box-body">
				<table class="table">
					<tr>
						<th>客户名称：<a class="btn btn-link"  href="#" onclick="viewCustomer('${customer.id}', '${customer.customerName}')">${customer.customerName}</a></th>
						<th>本次销售金额（元）：<a class="btn btn-default  disabled"  style="border:0px;" href="#">${salesOpportunity.saleMoney}</a></th>
						<th>客户级别：
							<c:choose>
							<c:when test="${customer.level == 1}">
								<a class="btn btn-default  disabled"  style="border:0px;" href="#">A(重点客户)</a>
							</c:when>
							<c:when test="${customer.level == 2}">
								<a class="btn btn-default  disabled" style="border:0px;"  href="#">B(普通用户)</a>

							</c:when>
							<c:otherwise>
								<a class="btn btn-default  disabled" style="border:0px;"  href="#">C(非优先客户)</a>

							</c:otherwise>
						</c:choose>
						</th>
						<th>客户电话：
							<a class="btn btn-default  disabled" style="border:0px;"  href="#">${customer.mobile}</a></th>
					</tr>

				</table>
			</div>
			<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
			  <ul class="layui-tab-title">
			    <li class="layui-this" lay-id>详细资料</li>
			    <li>动态</li>
			    <li  lay-filter="tabProduct">相关产品</li>
			  </ul>
			  <div class="layui-tab-content">
			    <div class="layui-tab-item layui-show" >
			    	<table class="table my-table" id="detailTable">
			    		
			    		<tr>
			    			<td class="title-td">销售机会名称:</td>
			    			<td>${salesOpportunity.salesOpportunityName}</td>
			    			<td class="title-td">销售机会所属人:</td>
			    			<td>${salesOpportunity.companyName}</td>
			    			
			    		</tr>
			    		<tr>
			    			<td class="title-td">销售金额（元）:</td>
			    			<td>${salesOpportunity.saleMoney}</td>
			    			<td class="title-td">销售阶段:</td>
			    			<td>${salesOpportunity.saleStage}</td>
			    			
			    		</tr>
			    		<tr>
			    			<td class="title-td">客户名称:</td>
			    			<td>${salesOpportunity.customerName}</td>
			    			<td class="title-td">机会类型:</td>
			    			<td>
			    			<c:choose>
			    					<c:when test="${salesOpportunity.oppotunityType == 1}">
			    						新客户机会
			    					</c:when>
			    					<c:otherwise>
			    						老客户机会
			    					</c:otherwise>
			    				</c:choose>
			    			</td>
			    			
			    		</tr>
			    		<tr>
			    			<td class="title-td">盈率:</td>
			    			<td>
			    				${salesOpportunity.winRate}%
			    			</td>
			    			<td class="title-td">结单日期:</td>
			    			<td><f:formatDate value="${salesOpportunity.endOppoTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
			    			
			    		</tr>
			    		<tr>
			    			<td class="title-td">机会来源:</td>
			    			<td>
			    				<c:choose>
			    					<c:when test="${salesOpportunity.oppoSource == 1}">
			    						客户介绍
			    					</c:when>
			    					<c:when test="${salesOpportunity.oppoSource == 2}">
			    						广告
			    					</c:when>
			    					<c:when test="${salesOpportunity.oppoSource == 3}">
			    						研讨会
			    					</c:when>
			    					<c:when test="${salesOpportunity.oppoSource == 4}">
			    						搜素引擎
			    					</c:when>
			    					<c:otherwise>
			    						其他
			    					</c:otherwise>
			    				</c:choose>
			    			</td>
			    			<td class="title-td">创建人:</td>
			    			<td>${salesOpportunity.userName}</td>
			    			
			    		</tr>
			    		<tr>
			    			<td class="title-td">创建时间:</td>
			    			<td><f:formatDate value="${salesOpportunity.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
			    			<td class="title-td">最近修改时间:</td>
			    			<td><f:formatDate value="${salesOpportunity.updateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
			    			
			    		</tr>
			    		<tr>
			    			<td class="title-td">备注:</td>
			    			<td colspan="3">${salesOpportunity.remark}</td>
			    			
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
			    				<td><f:formatDate value="${log.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				    			<td>${log.userName }</td>
				    			<td>${log.logMsg }</td>
				    		</tr>
			    			</c:forEach>
			    		</c:if>
			    	</table>
			    </div>
			    
			    <div class="layui-tab-item">
					<p class="pull-right">产品销售总金额（元）：<span id="spanTotalPrice">0</span>
					</p>

					<table class="table">
						<tbody id="tableProduct">
							<tr>
								<td>操作</td>
								<td>产品名称</td>
								<td>标准价格（元）</td>
								<td>销售单价（元）</td>
								<td>数量</td>
								<td>折扣（%）</td>
								<td>销售金额（元）</td>
								<td>销售单位</td>
								<td>备注</td>
							</tr>
						</tbody>
					</table>
				</div>
			  </div>
			</div> 
        </div>
        <div class="col-md-4">
        	<table class="table my-table">
        				<tr>
			    			<td class="title-td-left" colspan="1">
								<fieldset class="layui-elem-field">
								  <legend>销售机会操作</legend>
								  <div class="layui-field-box">
								<div class="layui-btn-group">
									 
									  <button class="layui-btn layui-btn-primary" onclick="deleteById('${salesOpportunity.id}','${salesOpportunity.salesOpportunityName}');">
									    <i class="layui-icon">&#xe640;</i>删除
									  </button>
									<button class="layui-btn layui-btn-primary" onclick="addProduct('${salesOpportunity.id}');">
										<i class="layui-icon">&#xe61f;</i>添加产品
									</button>
									</div>
								  </div>
								</fieldset>
							</td>
			    			
			    		</tr>
			    		
			    	</table>
        </div>
    </div>
</div>


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