<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<meta charset="UTF-8">
<title>销售机会管理</title>

<link href="${ctx}/js/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="${ctx}/css/common.css" rel="stylesheet">
<link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
<%@include file="../share/common_js.jsp"%>
<script src="${ctx}/js/pages/common/province_city_area.js"></script>
<script type="text/javascript" src="${ctx}/js/pages/salesopportunity/salesopportunity_edit.js"></script>
<script type="text/javascript">
	var ctx = "${pageContext.request.contextPath}";
</script>
<style type="text/css">
.my-layui-form-item{
margin: 0px 15px 0px 0px; 
    clear: both;
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
</style>
</head>
<body style="background: #fff;">
	
<form class="layui-form" id="myForm" onsubmit="return false;">
<input type="hidden" id="hidProductId" name="id" value="${salesOpportunity.id}"/>
<input type="hidden" id="hidBeyondDeptId" name="departmentId" value="${salesOpportunity.departmentId}"/>
<input type="hidden" id="hidBeyondDeptName" name="departmentName" value="${salesOpportunity.departmentName}"/>

<div class="container content_div">
	<div class="layui-form-item my-layui-form-item my-top">
    <label class="layui-form-label">销售机会所有人<span style="color:red">*</span></label>
    <div class="layui-input-block">
      <input type="text" autocomplete="off" value="${user.companyName}" class="layui-input" style="border:0px;" disabled="disabled">
    </div>
  </div>
  <div class="layui-form-item my-layui-form-item my-top">
    <label class="layui-form-label">销售机会名称<span style="color:red">*</span></label>
    <div class="layui-input-block">
      <input type="text" name="salesOpportunityName" lay-verify="salesOpportunityName" autocomplete="off" placeholder="请输入销售机会名称" class="layui-input" maxlength="10" value="${salesOpportunity.salesOpportunityName}">
    </div>
  </div>
  
  <div class="layui-form-item my-layui-form-item">
    <label class="layui-form-label">客户名称<span style="color:red">*</span></label>
    <div class="layui-input-inline" style="margin-right: 0px;">
    	<input placeholder="请选择客户名称" type="text" id="txtCustomerName" autocomplete="off" name="customerName"  class="layui-input" value="${salesOpportunity.customerName}" disabled="disabled">
           
    </div>
    <button class="layui-btn  layui-btn-primary" onclick="selectCustomer();"><i class="layui-icon">&#xe61f;</i></button>
  </div>
 <div class="layui-form-item my-layui-form-item my-top">
    <label class="layui-form-label">销售价格（元）<span style="color:red">*</span></label>
    <div class="layui-input-block">
      <input type="num" name="saleMoney"  autocomplete="off" value="${salesOpportunity.saleMoney}"  placeholder="请输入销售价格" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item my-layui-form-item layui-form-text">
    <label class="layui-form-label">销售阶段</label>
    <div class="layui-input-block">
      <select name="saleStage" lay-verify="required" lay-search="" >
          <option value="">搜索或者选择销售阶段</option>
          <option value="1">初步接洽</option>
          <option value="2">需求确定</option>
          <option value="3">方案/报价</option>
          <option value="4">谈判审核</option>
          <option value="5">赢单</option>
          <option value="5">输单</option>
        </select>
    </div>
  </div>
  <div id="divViewer">
  	
  	<div class="layui-form-item my-layui-form-item" style="margin-top:  20px;">
    <label class="layui-form-label"></label>
    <div class="layui-input-block">
      <a href="#" onclick="addMoreInfo();" style="color:#009688">
      	
      	<c:choose>
      		<c:when test="${salesOpportunity != null && salesOpportunity.id != '' }">
      		编辑更多信息
      		</c:when>
      		<c:otherwise>
      		新增更多信息
      		</c:otherwise>
      	</c:choose>
      </a>
    </div>
  </div>
  </div>
  <div id="divOther" style="display: none;">

   <div class="layui-form-item my-layui-form-item my-top">
    <label class="layui-form-label">结单日期</label>
    <div class="layui-input-block">
      <input type="num" name="postcode" autocomplete="off"   value="${salesOpportunity.postcode}"  placeholder="请输入邮政编码" class="layui-input" maxlength="6">
    </div>
  </div>
  
   <div class="layui-form-item my-layui-form-item my-top">
    <label class="layui-form-label">客户名称</label>
    <div class="layui-input-inline" style="margin-right: 0px;">
    	<input placeholder="请选择客户名称" type="text" id="txtCustomerName" autocomplete="off" name="customerName"  class="layui-input" value="${salesOpportunity.customerName}" disabled="disabled">
           
    </div>
    <button class="layui-btn  layui-btn-primary" onclick="selectDepartment();"><i class="layui-icon">&#xe61f;</i></button>
  </div>
   <div class="layui-form-item my-layui-form-item my-top">
    <label class="layui-form-label">市场活动</label>
    <div class="layui-input-block">
      <input type="text" name="facsimile" autocomplete="off"  value="${salesOpportunity.facsimile}"  placeholder="请输入传真" class="layui-input" maxlength="10">
    </div>
  </div>
  <div class="layui-form-item my-layui-form-item my-top">
    <label class="layui-form-label">机会类型</label>
    <div class="layui-input-block">
      <select name="oppoSource"  lay-search="">
          <option value="">搜索或者选择机会类型</option>
          <option value="1">新客户机会</option>
          <option value="2">老客户机会</option>
        </select>
    </div>
  </div>
  <div class="layui-form-item my-layui-form-item my-top">
    <label class="layui-form-label">机会来源</label>
    <div class="layui-input-block">
     <select name="oppoSource" lay-search="">
          <option value="">搜索或者选择机会来源</option>
          <option value="1">客户介绍</option>
          <option value="2">广告</option>
          <option value="3">研讨会</option>
          <option value="4">搜索引擎</option>
          <option value="5">其他</option>
        </select>
    </div>
  </div>
  <div class="layui-form-item my-layui-form-item layui-form-text">
    <label class="layui-form-label">备注</label>
    <div class="layui-input-block">
      <textarea placeholder="请输入备注" class="layui-textarea" name="remark"> ${salesOpportunity.remark} </textarea>
    </div>
  </div>
</div>
</div>	
  <div class="layui-form-item my-top">
    <div class="layui-input-block" style="text-align:right;">
      <button class="layui-btn" lay-submit="" lay-filter="mySubmit">保存</button>
      <button class="layui-btn layui-btn-primary" style="margin-right:50px;" onclick="cancelEdit();">取消</button>
    </div>
  </div>  
  
</form>

<script>
layui.use(['form', 'layedit', 'laydate'], function(){
  var form = layui.form()
  ,layer = layui.layer
  ,layedit = layui.layedit
  ,laydate = layui.laydate;
  
  //自定义验证规则
  form.verify({
	  salesOpportunityName: function(value){
	      if(value.length < 2){
	        return '销售机会名称的长度不能小于2个字符';
	      }
	
	      if(value.length > 30){
	          return '销售机会名称的长度不能大于00个字符';
	       }
	    },
	  
  });
  
  
  
  //监听提交
  form.on('submit(mySubmit)', function(data){
	var url = ctx + "/inner/salesOpportunity/saveOrUpdate"
	

	var salesOpportunity =  $('#myForm').serialize();
    $.ajax({
        cache: true,
        type: "POST",
        url: url,
        data: salesOpportunity,
        async: false,
        success: function(data) {
          layer.alert('保存成功',
			  {closeBtn: false,
			  skin: 'layui-layer-molv'
			  },
			  function(index){
		          parent.refreshTable();
		          parent.layer.closeAll();
			  });
        }
    }); 
    return false;
  });
  
  
});
</script>

</body>
</html>