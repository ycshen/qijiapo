<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<meta charset="UTF-8">
<title>产品管理</title>

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
<input type="hidden" id="hidProductId" name="id" value="${salesopportunity.id}"/>
<input type="hidden" id="hidBeyondDeptId" name="departmentId" value="${salesopportunity.departmentId}"/>
<input type="hidden" id="hidBeyondDeptName" name="departmentName" value="${salesopportunity.departmentName}"/>

<div class="container content_div">
	<div class="layui-form-item my-layui-form-item my-top">
    <label class="layui-form-label">产品所有人<span style="color:red">*</span></label>
    <div class="layui-input-block">
      <input type="text" autocomplete="off" value="${user.companyName}" class="layui-input" style="border:0px;" disabled="disabled">
    </div>
  </div>
  <div class="layui-form-item my-layui-form-item my-top">
    <label class="layui-form-label">产品名称<span style="color:red">*</span></label>
    <div class="layui-input-block">
      <input type="text" name="salesopportunityName" lay-verify="salesopportunityName" autocomplete="off" placeholder="请输入产品名称" class="layui-input" maxlength="10" value="${salesopportunity.salesopportunityName}">
    </div>
  </div>
  <div class="layui-form-item my-layui-form-item">
    <label class="layui-form-label">启用状态</label>
    <div class="layui-input-block">
      <input type="radio" name="status" value="1" title="启用"  checked="">
      <input type="radio" name="status"  value="2" title="停用">
    </div>
  </div>
 <div class="layui-form-item my-layui-form-item my-top">
    <label class="layui-form-label">销售价格（元）<span style="color:red">*</span></label>
    <div class="layui-input-block">
      <input type="num" name="price"  autocomplete="off" value="${salesopportunity.price}"  placeholder="请输入销售价格" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item my-layui-form-item layui-form-text">
    <label class="layui-form-label">产品描述</label>
    <div class="layui-input-block">
      <textarea placeholder="请输入产品描述" class="layui-textarea" name="salesopportunityDesc"> ${salesopportunity.salesopportunityDesc} </textarea>
    </div>
  </div>
  <div id="divViewer">
  	
  	<div class="layui-form-item my-layui-form-item" style="margin-top:  20px;">
    <label class="layui-form-label"></label>
    <div class="layui-input-block">
      <a href="#" onclick="addMoreInfo();" style="color:#009688">
      	
      	<c:choose>
      		<c:when test="${salesopportunity != null && salesopportunity.id != '' }">
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
    <label class="layui-form-label">邮政编码</label>
    <div class="layui-input-block">
      <input type="num" name="postcode" autocomplete="off"   value="${salesopportunity.postcode}"  placeholder="请输入邮政编码" class="layui-input" maxlength="6">
    </div>
  </div>
  
   <div class="layui-form-item my-layui-form-item my-top">
    <label class="layui-form-label">传真</label>
    <div class="layui-input-block">
      <input type="text" name="facsimile" autocomplete="off"  value="${salesopportunity.facsimile}"  placeholder="请输入传真" class="layui-input" maxlength="10">
    </div>
  </div>
   
  <div class="layui-form-item my-layui-form-item layui-form-text">
    <label class="layui-form-label">备注</label>
    <div class="layui-input-block">
      <textarea placeholder="请输入备注" class="layui-textarea" name="remark"> ${salesopportunity.remark} </textarea>
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
  
  <input type="hidden" value="" id="hidProvinceName" name="provinceName"/>
  <input type="hidden" value="" id="hidCityName" name="cityName"/>
  <input type="hidden" value="" id="hidAreaName" name="areaName"/>
</form>

<script>
layui.use(['form', 'layedit', 'laydate'], function(){
  var form = layui.form()
  ,layer = layui.layer
  ,layedit = layui.layedit
  ,laydate = layui.laydate;
  
  //自定义验证规则
  form.verify({
	  salesopportunityName: function(value){
	      if(value.length < 2){
	        return '产品名称的长度不能小于2个字符';
	      }
	
	      if(value.length > 30){
	          return '产品名称的长度不能大于00个字符';
	       }
	    },
	  
  });
  
  
  
  //监听提交
  form.on('submit(mySubmit)', function(data){
	var url = ctx + "/inner/salesopportunity/saveOrUpdate"
	

	var salesopportunity =  $('#myForm').serialize();
    $.ajax({
        cache: true,
        type: "POST",
        url: url,
        data: salesopportunity,
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