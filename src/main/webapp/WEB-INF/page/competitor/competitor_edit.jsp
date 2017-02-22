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
      <input type="text" name="competitorName" lay-verify="competitorName" autocomplete="off" placeholder="请输入竞争对手名称" class="layui-input" maxlength="10">
    </div>
  </div>
 <div class="layui-form-item my-layui-form-item">
    <label class="layui-form-label">所属部门<span style="color:red">*</span></label>
    <div class="layui-input-inline" style="margin-right: 0px;">
            <input type="text" name="beyondDeptName" autocomplete="off"  class="layui-input" value="${user.departmentName}" readonly="readonly">
    </div>
    <button class="layui-btn  layui-btn-primary" onclick="selectDepartment();"><i class="layui-icon">&#xe61f;</i></button>
  </div>
  <div id="divViewer">
  	
  	<div class="layui-form-item my-layui-form-item" style="margin-top:  20px;">
    <label class="layui-form-label"></label>
    <div class="layui-input-block">
      <a href="#" onclick="addMoreInfo();" style="color:#009688">新增更多信息</a>
    </div>
  </div>
  </div>
  <div id="divOther" style="display: none;">
  <div class="layui-form-item my-layui-form-item">
    <label class="layui-form-label">省市区</label>
    <div class="layui-input-block">
      <select name="memoTypeId" lay-filter="aihao" lay-verify="memoTypeId">
        <option value="-1"  selected="">请选择行程类型</option>
        <c:if test="${configList != null && configList.size() > 0}">
        	<c:forEach items="${configList}" var="config">
        		<option value="${config.value}">${config.key}</option>
        	</c:forEach>
        </c:if>
        
      </select>
    </div>
  </div>
   <div class="layui-form-item my-layui-form-item my-top">
    <label class="layui-form-label">地址</label>
    <div class="layui-input-block">
      <input type="text" name="memoName" lay-verify="memoName" autocomplete="off" placeholder="请输入地址" class="layui-input" maxlength="10">
    </div>
  </div>
   <div class="layui-form-item my-layui-form-item my-top">
    <label class="layui-form-label">邮政编码</label>
    <div class="layui-input-block">
      <input type="text" name="memoName" lay-verify="memoName" autocomplete="off" placeholder="请输入邮政编码" class="layui-input" maxlength="10">
    </div>
  </div>
   <div class="layui-form-item my-layui-form-item my-top">
    <label class="layui-form-label">电话</label>
    <div class="layui-input-block">
      <input type="text" name="memoName" lay-verify="memoName" autocomplete="off" placeholder="请输入电话" class="layui-input" maxlength="10">
    </div>
  </div>
   <div class="layui-form-item my-layui-form-item my-top">
    <label class="layui-form-label">传真</label>
    <div class="layui-input-block">
      <input type="text" name="memoName" lay-verify="memoName" autocomplete="off" placeholder="请输入传真" class="layui-input" maxlength="10">
    </div>
  </div>
   <div class="layui-form-item my-layui-form-item my-top">
    <label class="layui-form-label">公司网址</label>
    <div class="layui-input-block">
      <input type="text" name="memoName" lay-verify="memoName" autocomplete="off" placeholder="请输入公司网址" class="layui-input" maxlength="10">
    </div>
  </div>
   <div class="layui-form-item my-layui-form-item my-top">
    <label class="layui-form-label">微博</label>
    <div class="layui-input-block">
      <input type="text" name="memoName" lay-verify="memoName" autocomplete="off" placeholder="请输入微博" class="layui-input" maxlength="10">
    </div>
  </div>
   <div class="layui-form-item my-layui-form-item my-top">
    <label class="layui-form-label">总人数</label>
    <div class="layui-input-block">
      <input type="text" name="memoName" lay-verify="memoName" autocomplete="off" placeholder="请输入总人数" class="layui-input" maxlength="10">
    </div>
  </div>
   <div class="layui-form-item my-layui-form-item my-top">
    <label class="layui-form-label">上年销售额</label>
    <div class="layui-input-block">
      <input type="text" name="memoName" lay-verify="memoName" autocomplete="off" placeholder="请输入上年销售额" class="layui-input" maxlength="10">
    </div>
  </div>
  <div class="layui-form-item my-layui-form-item layui-form-text">
    <label class="layui-form-label">备注</label>
    <div class="layui-input-block">
      <textarea placeholder="请输入备注" class="layui-textarea" name="remark"></textarea>
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
	  competitorName: function(value){
	      if(value.length < 2){
	        return '竞争对手名称的长度不能小于2个字符';
	      }
	
	      if(value.length > 30){
	          return '竞争对手名称的长度不能大于00个字符';
	       }
	    },
	  /* memoTypeId: function(value){
			if(value == -1){
				 return '请选择行程类型';
				}
	  },
	  memoStartTime: function(value){
		if(value == '' || value == null || value == undefined){
			return '请选择开始时间';
		}
	  },
	  memoEndTime: function(value){
		  if(value == '' || value == null || value == undefined){
				return '请选择结束时间';
			}
		 var startTime = document.getElementById("txtStartTime").value;
		 if(value <startTime){
			 return '结束时间不能早于开始时间';
			 }
	  } */
  });
  
  
  
  //监听提交
  form.on('submit(mySubmit)', function(data){
	var url = ctx + "/inner/competitor/saveOrUpdate"
	var memo =  $('#myForm').serialize();
    $.ajax({
        cache: true,
        type: "POST",
        url: url,
        data: memo,
        async: false,
        success: function(data) {
          /*  if(data.indexOf("_") > 0){
               var idArr = data.split("_");
               var result = idArr[0];
               var id = idArr[1];
        	   if(result == 1){
     			  layer.alert('添加行程成功',
     					  {closeBtn: false,
     					  skin: 'layui-layer-molv'
     					  },
     					  function(index){
         					  var memoName = $("input[name='memoName']").val();
         					  var startTime = $("input[name='memoStartTime']").val();;
         					  var endTime = $("input[name='memoEndTime']").val();;
     						  window.parent.addSuccess(memoName, startTime, endTime, id);
     					  }
     			);
               }else if(result == 2){
             	  layer.alert('更新成功', 
             			  {closeBtn: false,
       		  		skin: 'layui-layer-molv'
       			  },function(index){
             		 // window.parent.addSuccess();
             		  
           		});
               }
            }else{
            	layer.alert("操作失败！",
          			  {closeBtn: false,
    		  		skin: 'layui-layer-molv'
    			  }); 
            } */
        }
    });
    return false;
  });
  
  
});
</script>

</body>
</html>