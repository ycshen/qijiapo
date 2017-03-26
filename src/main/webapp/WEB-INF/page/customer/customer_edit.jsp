<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<meta charset="UTF-8">
<title>客户管理</title>

<link href="${ctx}/js/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="${ctx}/css/common.css" rel="stylesheet">
<link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
<%@include file="../share/common_js.jsp"%>
<script src="${ctx}/js/pages/common/province_city_area.js"></script>
<script type="text/javascript" src="${ctx}/js/pages/customer/customer_edit.js"></script>
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
	height: 500px;
	overflow-y:auto;
}
</style>
</head>
<body style="background: #fff;">
	
<form class="layui-form" id="myForm" onsubmit="return false;">
<input type="hidden" id="hidProductId" name="id" value="${customer.id}"/>
<input type="hidden" id="hidBeyondDeptId" name="departmentId" value="${customer.departmentId}"/>
<input type="hidden" id="hidBeyondDeptName" name="departmentName" value="${customer.departmentName}"/>

<div class="container content_div">
	
  <div class="layui-form-item my-layui-form-item my-top">
    <label class="layui-form-label">客户名称<span style="color:red">*</span></label>
    <div class="layui-input-block">
      <input type="text" name="customerName" lay-verify="customerName" autocomplete="off" placeholder="请输入客户名称" class="layui-input" maxlength="10" value="${customer.customerName}">
    </div>
  </div>
  <div class="layui-form-item my-layui-form-item">
    <label class="layui-form-label">省市区</label>
    <div class="layui-input-block">
      <div class="layui-input-inline" style="width: 120px;">
			<select name="provinceId" lay-filter="province">
				<c:choose>
		      		<c:when test="${customer != null && customer.id != '' }">
		      			<option value="${customer.provinceId}">${customer.provinceName}</option>
		      		</c:when>
		      		<c:otherwise>
						<option value="">请选择省</option>
		      		</c:otherwise>
		      	</c:choose>
			</select>
		</div>
		<div class="layui-input-inline" style="width: 120px;">
			<select name="cityId" lay-filter="city">
				<c:choose>
		      		<c:when test="${customer != null && customer.id != '' }">
		      			<option value="${customer.cityId}">${customer.cityName}</option>
		      		</c:when>
		      		<c:otherwise>
						<option value="">请选择市</option>
		      		</c:otherwise>
		      	</c:choose>
			</select>
		</div>
		<div class="layui-input-inline" style="width: 120px;">
			<select name="areaId" lay-filter="area">
				<c:choose>
		      		<c:when test="${customer != null && customer.id != '' }">
		      			<option value="${customer.areaId}">${customer.areaName}</option>
		      		</c:when>
		      		<c:otherwise>
		      			<option value="">请选择县/区</option>
		      		</c:otherwise>
		      	</c:choose>
				
			</select>
		</div>
    </div>
  </div>
  <div class="layui-form-item my-layui-form-item my-top">
    <label class="layui-form-label">详细地址<span style="color:red">*</span></label>
    <div class="layui-input-block">
      <input type="text" name="address" lay-verify="address" autocomplete="off" placeholder="请输入详细地址" class="layui-input"  value="${customer.address}">
    </div>
  </div>
  <div class="layui-form-item my-layui-form-item my-top">
    <label class="layui-form-label">电话<span style="color:red">*</span></label>
    <div class="layui-input-block">
      <input type="text" name="mobile" lay-verify="mobile" autocomplete="off" placeholder="请输入电话" class="layui-input"  value="${customer.mobile}">
    </div>
  </div>
  <div class="layui-form-item my-layui-form-item my-top">
    <label class="layui-form-label">客户级别<span style="color:red">*</span></label>
    <div class="layui-input-block">
 		<select name="level"  lay-search="">
          <option value="">搜索或者选择客户级别</option>
          <option value="1">A(重点客户)</option>
          <option value="2">B(普通用户)</option>
          <option value="3">C(非优先客户)</option>
          
        </select>    </div>
  </div>
   <div class="layui-form-item my-layui-form-item layui-form-text">
    <label class="layui-form-label">备注</label>
    <div class="layui-input-block">
      <textarea placeholder="请输入备注" class="layui-textarea" name="remark"> ${customer.remark} </textarea>
    </div>
  </div>
  <div class="layui-form-item my-layui-form-item">
    <label class="layui-form-label">所属部门<span style="color:red">*</span></label>
    <div class="layui-input-inline" style="margin-right: 0px;">
    	<c:choose>
    		<c:when test="${customer == null || customer.id == null || customer.id == '' }">
    			<input placeholder="请选择所属部门" type="text" id="txtDepartmentName" autocomplete="off" name="departmentName"  class="layui-input" value="全公司" disabled="disabled">
       			<input type="hidden" value="-${user.companyId}" id="hidDeptId"/>   
    		</c:when>
    		<c:otherwise>
    			<input placeholder="请选择所属部门" type="text" id="txtDepartmentName" autocomplete="off" name="departmentName"  class="layui-input" value="${customer.departmentName}" disabled="disabled">
       			<input type="hidden" value="${customer.departmentId}" id="hidDeptId"/>   
    		</c:otherwise>
    	</c:choose>
    	
    </div>
    <button class="layui-btn  layui-btn-primary" onclick="selectDepartment();"><i class="layui-icon">&#xe61f;</i></button>
  </div>
 
  <div id="divViewer">
  	 <div class="layui-form-item my-layui-form-item">
	    <label class="layui-form-label">上级客户</label>
	    <div class="layui-input-inline" style="margin-right: 0px;">
	    	<input placeholder="请输入或者选择上级客户" type="text" id="txtParentCustomerName" autocomplete="off"   class="layui-input" value="${customer.parentCustomerId}">
	           
	    </div>
	    <button class="layui-btn  layui-btn-primary" onclick="selectDepartment();"><i class="layui-icon">&#xe61f;</i></button>
	  </div>
  	<div class="layui-form-item my-layui-form-item" style="margin-top:  20px;">
    <label class="layui-form-label"></label>
    <div class="layui-input-block">
      <a href="#" onclick="addMoreInfo();" style="color:#009688">
      	
      	<c:choose>
      		<c:when test="${customer != null && customer.id != '' }">
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
   <div class="layui-form-item my-layui-form-item layui-form-text">
    <label class="layui-form-label">行业</label>
    <div class="layui-input-block">
      <select name="industry"  lay-search="">
          <option value="">搜索或者选择行业</option>
          <option value="1">金融</option>
          <option value="2">电信</option>
          <option value="3">教育</option>
          <option value="4">高科技</option>
          <option value="5">政府</option>
          <option value="6">制造业</option>
          <option value="7">服务</option>
          <option value="8">能源</option>
          <option value="9">零售</option>
          <option value="10">媒体</option>
          <option value="11">制造业</option>
          <option value="12">娱乐</option>
          <option value="13">咨询</option>
          <option value="14">非盈利事业</option>
          <option value="15">公共事业</option>
          <option value="16">其他</option>
        </select>
    </div>
  </div>
	<div class="layui-form-item my-layui-form-item my-top">
    <label class="layui-form-label">邮政编码</label>
    <div class="layui-input-block">
      <input type="num" name="postcode"  autocomplete="off" value="${customer.postcode}"  placeholder="请输入邮政编码" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item my-layui-form-item my-top">
    <label class="layui-form-label">传真</label>
    <div class="layui-input-block">
      <input type="text" name="facsimile"  autocomplete="off" value="${customer.facsimile}"  placeholder="请输入传真" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item my-layui-form-item my-top">
    <label class="layui-form-label">公司网址</label>
    <div class="layui-input-block">
      <input type="text" name="website"  autocomplete="off" value="${customer.website}"  placeholder="请输入公司网址" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item my-layui-form-item my-top">
    <label class="layui-form-label">微博</label>
    <div class="layui-input-block">
      <input type="text" name="weibo"  autocomplete="off" value="${customer.weibo}"  placeholder="请输入微博" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item my-layui-form-item my-top">
    <label class="layui-form-label">总人数</label>
    <div class="layui-input-block">
      <input type="num" name="totalStaff"  autocomplete="off" value="${customer.totalStaff}"  placeholder="请输入总人数" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item my-layui-form-item my-top">
    <label class="layui-form-label">销售额（元）<span style="color:red">*</span></label>
    <div class="layui-input-block">
      <input type="text" name="saleMoney"  autocomplete="off" value="${customer.saleMoney}"  placeholder="请输入销售额" class="layui-input">
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
	  customerName: function(value){
	      if(value.length < 2){
	        return '客户名称的长度不能小于2个字符';
	      }
	
	      if(value.length > 30){
	          return '客户名称的长度不能大于00个字符';
	       }
	    },
	  
  });
  
  
  
  //监听提交
  form.on('submit(mySubmit)', function(data){
	var url = ctx + "/inner/customer/saveOrUpdate"
	var provinceName = $("select[name='provinceId']").find("option:selected").text();
	if(provinceName != "请选择省"){
		$("#hidProvinceName").val(provinceName);
	}

	var cityName = $("select[name='cityId']").find("option:selected").text();
	if(cityName != "请选择市"){
		$("#hidCityName").val(cityName);
	}

	var areaName = $("select[name='areaId']").find("option:selected").text();
	if(areaName != "请选择县/区"){
		$("#hidAreaName").val(areaName);
	}

	var customer =  $('#myForm').serialize();
    $.ajax({
        cache: true,
        type: "POST",
        url: url,
        data: customer,
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