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
<script type="text/javascript" src="${ctx}/js/pages/customer/customer_detail.js"></script>
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
        	<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
			  <ul class="layui-tab-title">
			    <li class="layui-this">资料</li>
			    <li>动态</li>
			   <li>产品</li>
			  </ul>
			  <div class="layui-tab-content">
			    <div class="layui-tab-item layui-show">
			    	<table class="table my-table" id="detailTable">
			    		
			    		<tr>
			    			<td class="title-td">客户名称:</td>
			    			<td>${customer.customerName}</td>
			    			<td class="title-td">客户所属人:</td>
			    			<td>${customer.departmentName}</td>
			    			
			    		</tr>
			    		<tr>
			    			<td class="title-td">备注:</td>
			    			<td colspan="3">${customer.remark}</td>
			    			
			    		</tr>
			    		<tr>
			    			<td class="title-td">客户级别:</td>
			    			<td>
			    				
			    				<c:choose>
			    					<c:when test="${customer.level == 1}">
			    						A(重点客户)
			    					</c:when>
			    					<c:when test="${customer.level == 2}">
			    						B(普通用户)
			    					</c:when>
			    					<c:otherwise>
			    						C(非优先客户)
			    					</c:otherwise>
			    				</c:choose>
			    			</td>
			    			<td class="title-td">电话号码:</td>
			    			<td>${customer.mobile}</td>
			    			
			    		</tr>
			    		<tr>
			    			<td class="title-td">行业:</td>
			    			<td>
			    				<c:choose>
			    					<c:when test="${customer.industry == 1}">
			    						金融
			    					</c:when>
			    					<c:when test="${customer.industry == 2}">
			    						电信
			    					</c:when>
			    					<c:when test="${customer.industry == 3}">
			    						教育
			    					</c:when>
			    					<c:when test="${customer.industry == 4}">
			    						高科技
			    					</c:when>
			    					<c:when test="${customer.industry == 5}">
			    						政府
			    					</c:when>
			    					<c:when test="${customer.industry == 6}">
			    						制造业
			    					</c:when>
			    					<c:when test="${customer.industry == 7}">
			    						服务
			    					</c:when>
			    					<c:when test="${customer.industry == 8}">
			    						能源
			    					</c:when>
			    					<c:when test="${customer.industry == 9}">
			    						零售
			    					</c:when>
			    					<c:when test="${customer.industry == 10}">
			    						媒体
			    					</c:when>
			    					<c:when test="${customer.industry == 11}">
			    						制造业
			    					</c:when>
			    					<c:when test="${customer.industry == 12}">
			    						娱乐
			    					</c:when>
			    					<c:when test="${customer.industry == 13}">
			    						咨询
			    					</c:when>
			    					<c:when test="${customer.industry == 14}">
			    						非盈利事业
			    					</c:when>
			    					<c:when test="${customer.industry == 15}">
			    						公共事业
			    					</c:when>
			    					<c:otherwise>
			    						其他
			    					</c:otherwise>
			    				</c:choose>
			    				<option value="1"></option>
          
			    			</td>
			    			<td class="title-td">总人数:</td>
			    			<td>${customer.totalStaff}</td>
			    			
			    		</tr>
			    		<tr>
			    			<td class="title-td">省市区:</td>
			    			<td>
			    				${customer.provinceName}${customer.cityName}${customer.areaName}
			    			</td>
			    			<td class="title-td">详细地址:</td>
			    			<td>
			    				${customer.address}
			    			</td>
			    			
			    		</tr>
			    		<tr>
			    			
			    			<td class="title-td">公司网址:</td>
			    			<td>
			    				${customer.website}
			    			</td>
			    			<td class="title-td">销售额:</td>
			    			
			    			<td>${customer.saleMoney}</td>
			    			
			    		</tr>
			    		<tr>
			    			<td class="title-td">创建时间:</td>
			    			<td><f:formatDate value="${customer.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
			    			<td class="title-td">最近修改时间:</td>
			    			<td><f:formatDate value="${customer.updateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
			    			
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
			    
			    <div class="layui-tab-item">产品</div> 
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
									 
									  <button class="layui-btn layui-btn-primary" onclick="deleteById('${customer.id}','${customer.customerName}');">
									    <i class="layui-icon">&#xe640;</i>删除
									  </button>
									   <button class="layui-btn layui-btn-primary" onclick="deleteById('${customer.id}','${customer.customerName}');">
									    <i class="layui-icon">&#xe640;</i>添加产品
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


<script>
layui.use('element', function(){
	  var $ = layui.jquery
	  ,element = layui.element(); //Tab的切换功能，切换事件监听等，需要依赖element模块
	  
	  //触发事件
	  var active = {
	    tabAdd: function(){
	      //新增一个Tab项
	      element.tabAdd('demo', {
	        title: '新选项'+ (Math.random()*1000|0) //用于演示
	        ,content: '内容'+ (Math.random()*1000|0)
	      })
	    }
	    ,tabDelete: function(){
	      //删除指定Tab项
	      element.tabDelete('demo', 2); //删除第3项（注意序号是从0开始计算）
	    }
	    ,tabChange: function(){
	      //切换到指定Tab项
	      element.tabChange('demo', 1); //切换到第2项（注意序号是从0开始计算）
	    }
	  };
	  
	  $('.site-demo-active').on('click', function(){
	    var type = $(this).data('type');
	    active[type] ? active[type].call(this) : '';
	  });
	});
	</script>

</script>

</body>
</html>