<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<meta charset="UTF-8">
<title>竞争对手管理</title>

<link href="${ctx}/js/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="${ctx}/css/common.css" rel="stylesheet">
<link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
<%@include file="../share/common_js.jsp"%>
<script src="${ctx}/js/pages/common/province_city_area.js"></script>
<script type="text/javascript" src="${ctx}/js/pages/competitor/competitor_edit.js"></script>
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
<input type="hidden" id="hidCompetitorId" name="id" value="${competitor.id}"/>
<div class="container content_div">
	<div class="layui-form-item my-layui-form-item my-top">
    <label class="layui-form-label">竞争对手所有人<span style="color:red">*</span></label>
    <div class="layui-input-block">
      <input type="text" autocomplete="off" value="${user.userName}" class="layui-input" style="border:0px;" disabled="disabled">
    </div>
  </div>
  <div class="layui-form-item my-layui-form-item my-top">
    <label class="layui-form-label">竞争对手名称<span style="color:red">*</span></label>
    <div class="layui-input-block">
      <input type="text" name="competitorName" lay-verify="competitorName" autocomplete="off" placeholder="请输入竞争对手名称" class="layui-input" maxlength="10" value="${competitor.competitorName}">
    </div>
  </div>
 <div class="layui-form-item my-layui-form-item">
    <label class="layui-form-label">所属部门<span style="color:red">*</span></label>
    <div class="layui-input-inline" style="margin-right: 0px;">
    	<c:choose>
      		<c:when test="${competitor != null && competitor.id != '' }">
      			 <input type="text" name="beyondDeptName" autocomplete="off"  class="layui-input" value="${competitor.beyondDeptName}" readonly="readonly">
      		</c:when>
      		<c:otherwise>
      			 <input type="text" name="beyondDeptName" autocomplete="off"  class="layui-input" value="${user.departmentName}" readonly="readonly">
      		</c:otherwise>
      	</c:choose>
           
    </div>
    <button class="layui-btn  layui-btn-primary" onclick="selectDepartment();"><i class="layui-icon">&#xe61f;</i></button>
  </div>
  <div id="divViewer">
  	
  	<div class="layui-form-item my-layui-form-item" style="margin-top:  20px;">
    <label class="layui-form-label"></label>
    <div class="layui-input-block">
      <a href="#" onclick="addMoreInfo();" style="color:#009688">
      	
      	<c:choose>
      		<c:when test="${competitor != null && competitor.id != '' }">
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
  <div class="layui-form-item my-layui-form-item">
    <label class="layui-form-label">省市区</label>
    <div class="layui-input-block">
      <div class="layui-input-inline" style="width: 120px;">
			<select name="provinceId" lay-filter="province">
				<c:choose>
		      		<c:when test="${competitor != null && competitor.id != '' }">
		      			<option value="${competitor.provinceId}">${competitor.provinceName}</option>
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
		      		<c:when test="${competitor != null && competitor.id != '' }">
		      			<option value="${competitor.cityId}">${competitor.cityName}</option>
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
		      		<c:when test="${competitor != null && competitor.id != '' }">
		      			<option value="${competitor.areaId}">${competitor.areaName}</option>
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
    <label class="layui-form-label">地址</label>
    <div class="layui-input-block">
      <input type="text" name="address" lay-verify="address"  value="${competitor.address}"  autocomplete="off" placeholder="请输入地址" class="layui-input" maxlength="50">
    </div>
  </div>
   <div class="layui-form-item my-layui-form-item my-top">
    <label class="layui-form-label">邮政编码</label>
    <div class="layui-input-block">
      <input type="num" name="postcode" autocomplete="off"   value="${competitor.postcode}"  placeholder="请输入邮政编码" class="layui-input" maxlength="6">
    </div>
  </div>
   <div class="layui-form-item my-layui-form-item my-top">
    <label class="layui-form-label">电话</label>
    <div class="layui-input-block">
      <input type="text" name="mobile"  autocomplete="off"  value="${competitor.mobile}"  placeholder="请输入电话" class="layui-input" maxlength="10">
    </div>
  </div>
   <div class="layui-form-item my-layui-form-item my-top">
    <label class="layui-form-label">传真</label>
    <div class="layui-input-block">
      <input type="text" name="facsimile" autocomplete="off"  value="${competitor.facsimile}"  placeholder="请输入传真" class="layui-input" maxlength="10">
    </div>
  </div>
   <div class="layui-form-item my-layui-form-item my-top">
    <label class="layui-form-label">公司网址</label>
    <div class="layui-input-block">
      <input type="text" name="website"  autocomplete="off"  value="${competitor.website}"  placeholder="请输入公司网址" class="layui-input" maxlength="10">
    </div>
  </div>
   <div class="layui-form-item my-layui-form-item my-top">
    <label class="layui-form-label">微博</label>
    <div class="layui-input-block">
      <input type="text" name="weibo" autocomplete="off"  value="${competitor.weibo}" placeholder="请输入微博" class="layui-input" maxlength="10">
    </div>
  </div>
   <div class="layui-form-item my-layui-form-item my-top">
    <label class="layui-form-label">总人数</label>
    <div class="layui-input-block">
      <input type="text" name="staffNum"  autocomplete="off" value="${competitor.staffNum}"  placeholder="请输入总人数" class="layui-input" maxlength="10">
    </div>
  </div>
   <div class="layui-form-item my-layui-form-item my-top">
    <label class="layui-form-label">上年销售额</label>
    <div class="layui-input-block">
      <input type="num" name="saleMoney"  autocomplete="off" value="${competitor.saleMoney}"  placeholder="请输入上年销售额" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item my-layui-form-item layui-form-text">
    <label class="layui-form-label">备注</label>
    <div class="layui-input-block">
      <textarea placeholder="请输入备注" class="layui-textarea" name="remark"> ${competitor.remark} </textarea>
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
	  competitorName: function(value){
	      if(value.length < 2){
	        return '竞争对手名称的长度不能小于2个字符';
	      }
	
	      if(value.length > 30){
	          return '竞争对手名称的长度不能大于00个字符';
	       }
	    },
	  
  });
  
  
  
  //监听提交
  form.on('submit(mySubmit)', function(data){
	var url = ctx + "/inner/competitor/saveOrUpdate"
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


	var competitor =  $('#myForm').serialize();
    $.ajax({
        cache: true,
        type: "POST",
        url: url,
        data: competitor,
        async: false,
        success: function(data) {
          layer.alert('新增成功',
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