<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<meta charset="UTF-8">
<title>账号管理</title>

<link href="${ctx}/js/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="${ctx}/css/common.css" rel="stylesheet">
<link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
<%@include file="../share/common_js.jsp"%>
<script src="${ctx}/js/pages/common/province_city_area.js"></script>
<script type="text/javascript" src="${ctx}/js/pages/dailyAccount/dailyAccount_edit.js"></script>
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
	height: 400px;
	overflow-y:auto;
}
</style>
</head>
<body style="background: #fff;">
	
<form class="layui-form" id="myForm" onsubmit="return false;">
<input type="hidden" id="hidDailyAccountId" name="id" value="${dailyAccount.id}"/>

  <div class="layui-form-item my-layui-form-item my-top">
    <label class="layui-form-label">账号<span style="color:red">*</span></label>
    <div class="layui-input-block">
      <input type="text" name="account" lay-verify="account" autocomplete="off" placeholder="请输入账号" class="layui-input" value="${dailyAccount.account}">
    </div>
  </div>

 <div class="layui-form-item my-layui-form-item my-top">
    <label class="layui-form-label">密码</label>
    <div class="layui-input-block">
      <input type="text" name="password"   lay-verify="price" autocomplete="off" value="${dailyAccount.password}"  placeholder="请输入密码" class="layui-input">
    </div>
  </div>

   <div class="layui-form-item my-layui-form-item my-top">
    <label class="layui-form-label">所属系统</label>
    <div class="layui-input-block">
      <input type="text" name="system"  autocomplete="off"   value="${dailyAccount.system}"  placeholder="请输入所属系统" class="layui-input">
    </div>
  </div>
   <div class="layui-form-item my-layui-form-item my-top">
    <label class="layui-form-label">访问地址</label>
    <div class="layui-input-block">
      <input type="text" name="systemUrl"  autocomplete="off"   value="${dailyAccount.systemUrl}"  placeholder="请输入访问地址" class="layui-input">
    </div>
  </div>

  <div class="layui-form-item my-layui-form-item layui-form-text">
    <label class="layui-form-label">备注</label>
    <div class="layui-input-block">
      <textarea placeholder="请输入备注" class="layui-textarea" name="remark">${dailyAccount.remark}</textarea>
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
      account:function(value){
          if(value == null || value == '' || value == undefined){
              return '请输入账号';
          }
      }
	  
  });
  
  
  
  //监听提交
  form.on('submit(mySubmit)', function(data){
	var url = ctx + "/inner/account/saveOrUpdate"
	

	var dailyAccount =  $('#myForm').serialize();
    $.ajax({
        cache: true,
        type: "POST",
        url: url,
        data: dailyAccount,
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