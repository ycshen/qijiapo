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


</style>
</head>
<body style="background: #fff;overflow-y:auto;">
	
<form class="layui-form" id="myForm" onsubmit="return false;">
<input type="hidden" id="hidProductId" name="id" value="${salesOpportunity.id}"/>
<input type="hidden" id="hidBeyondDeptId" name="departmentId" value="${salesOpportunity.departmentId}"/>
<input type="hidden" id="hidBeyondDeptName" name="departmentName" value="${salesOpportunity.departmentName}"/>
<div class="layui-form-item my-top" style="position:fixed; top: 0; left: 0;background-color:#F8F8F8;width: 100%;z-index: 999">
    <div class="layui-input-block" style="text-align:right;">
        <button class="layui-btn" lay-submit="" lay-filter="mySubmit">保存</button>
        <button class="layui-btn layui-btn-primary" style="margin-right:50px;" onclick="cancelEdit();">取消</button>
    </div>
</div>
<div class="container" style="margin-top: 50px;">
	<div class="layui-form-item my-layui-form-item my-top">
    <label class="layui-form-label">销售机会所有人<span style="color:red">*</span></label>
    <div class="layui-input-block">
      <input type="text" autocomplete="off" value="${user.companyName}" class="layui-input" style="border:0px;" disabled="disabled">
    </div>
  </div>
  <div class="layui-form-item my-layui-form-item my-top">
    <label class="layui-form-label">销售机会名称<span style="color:red">*</span></label>
    <div class="layui-input-block">
      <input type="text" name="salesOpportunityName" lay-verify="salesOpportunityName" autocomplete="off" placeholder="请输入销售机会名称" class="layui-input"  value="${salesOpportunity.salesOpportunityName}">
    </div>
  </div>
    <c:choose>
        <c:when test="${customer != null && customer.id != null}">
            <div class="layui-form-item my-layui-form-item">
                <label class="layui-form-label">客户名称<span style="color:red">*</span></label>
                <div class="layui-input-inline" style="margin-right: 0px;">
                    <input type="text" style="border: 0px;" class="layui-input" value=" ${customer.customerName}" disabled="disabled">
                    <input type="hidden" value="${customer.id}"  name="customerId"/>
                    <input type="hidden"  value="${customer.customerName}"  name="customerName"/>
                    <input type="hidden" value="1" id="hidEditType"/>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <div class="layui-form-item my-layui-form-item">
                <label class="layui-form-label">客户名称<span style="color:red">*</span></label>
                <div class="layui-input-inline" style="margin-right: 0px;">
                    <input placeholder="请选择客户名称" type="text" id="txtCustomerName" autocomplete="off"  class="layui-input" value="${salesOpportunity.customerName}" disabled="disabled">
                    <input type="hidden" value="${salesOpportunity.customerId}" id="txtCustomerId" name="customerId"/>
                    <input type="hidden" lay-verify="customerName" value="${salesOpportunity.customerName}" id="txtCustomerNameHide" name="customerName"/>
                    <input type="hidden" value="0" id="hidEditType"/>
                </div>
                <button class="layui-btn  layui-btn-primary" onclick="selectCustomer();"><i class="layui-icon">&#xe61f;</i></button>
            </div>

        </c:otherwise>
    </c:choose>

    <div class="layui-form-item my-layui-form-item layui-form-text">
        <label class="layui-form-label">销售阶段<span style="color:red">*</span></label>
        <div class="layui-input-block">
            <select name="saleStage" lay-verify="saleStage" lay-search="" >
                <option value="">搜索或者选择销售阶段</option>
                <option value="1" <c:if test="${salesOpportunity.saleStage == 1}">selected="selected"</c:if>>初步接洽</option>
                <option value="2" <c:if test="${salesOpportunity.saleStage == 2}">selected="selected"</c:if>>需求确定</option>
                <option value="3" <c:if test="${salesOpportunity.saleStage == 3}">selected="selected"</c:if>>方案/报价</option>
                <option value="4" <c:if test="${salesOpportunity.saleStage == 4}">selected="selected"</c:if>>谈判审核</option>
                <option value="5" <c:if test="${salesOpportunity.saleStage == 5}">selected="selected"</c:if>>赢单</option>
                <option value="6" <c:if test="${salesOpportunity.saleStage == 6}">selected="selected"</c:if>>输单</option>
            </select>
        </div>
    </div>
 <div class="layui-form-item my-layui-form-item my-top">
    <label class="layui-form-label">销售价格（元)<span style="color:red">*</span></label>
    <div class="layui-input-block">
      <input type="num" name="saleMoney"
        onkeyup="clearNoNum(this)"
             autocomplete="off" value="${salesOpportunity.saleMoney}"  placeholder="请输入销售价格" class="layui-input">
    </div>
  </div>
   <div class="layui-form-item my-layui-form-item my-top">
    <label class="layui-form-label">结单日期</label>
    <div class="layui-input-block">

        <input type="text" name="endOppoTime" id="txtEndOppoTime"
               placeholder="请选择结单日期" autocomplete="off" class="layui-input"
               onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD'})">
    </div>
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
      <select name="oppotunityType"  lay-search="">
          <option value="">搜索或者选择机会类型</option>
          <option value="1" <c:if test="${salesOpportunity.oppotunityType == 1}">selected="selected"</c:if>>新客户机会</option>
          <option value="2" <c:if test="${salesOpportunity.oppotunityType == 2}">selected="selected"</c:if>>老客户机会</option>
        </select>
    </div>
  </div>
  <div class="layui-form-item my-layui-form-item my-top">
    <label class="layui-form-label">机会来源</label>
    <div class="layui-input-block">
     <select name="oppoSource" lay-search="">
          <option value="">搜索或者选择机会来源</option>
          <option value="1" <c:if test="${salesOpportunity.oppoSource == 1}">selected="selected"</c:if>>客户介绍</option>
          <option value="2" <c:if test="${salesOpportunity.oppoSource == 2}">selected="selected"</c:if>>广告</option>
          <option value="3" <c:if test="${salesOpportunity.oppoSource == 3}">selected="selected"</c:if>>研讨会</option>
          <option value="4" <c:if test="${salesOpportunity.oppoSource == 4}">selected="selected"</c:if>>搜索引擎</option>
          <option value="5" <c:if test="${salesOpportunity.oppoSource == 5}">selected="selected"</c:if>>其他</option>
        </select>
    </div>
  </div>
  <div class="layui-form-item my-layui-form-item layui-form-text">
    <label class="layui-form-label">备注</label>
    <div class="layui-input-block">
      <textarea placeholder="请输入备注" class="layui-textarea" name="remark">${salesOpportunity.remark}</textarea>
    </div>
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
      saleStage: function(value){
          if(value == null || value == '' || value == undefined){
              return '请选择销售阶段';
          }
      },
      customerName: function(value){
          if(value == null || value == '' || value == undefined){
              return '请选择客户';
          }
      }
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
                  var editType = $("#hidEditType").val();
                  if(editType == 1){
                      parent.layer.closeAll();
                      parent.reloadSaleOppo();
                  }else {
                      parent.refreshTable();
                      parent.layer.closeAll();
                  }
			  });
        }
    }); 
    return false;
  });
  
  
});
</script>

</body>
</html>
