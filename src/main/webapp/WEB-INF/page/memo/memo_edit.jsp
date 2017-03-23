<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<meta charset="UTF-8">
<title>行程编辑</title>

<link href="${ctx}/js/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="${ctx}/css/common.css" rel="stylesheet">
<link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
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
    width: 100px;
    font-weight: 400;
    text-align: right;
    padding: 9px 15px;
}
.my-top{
	margin-top: 10px;
}
</style>
</head>
<body style="background: #fff;">
	
<form class="layui-form" id="myForm">
  <div class="layui-form-item my-layui-form-item my-top">
    <label class="layui-form-label">行程主题</label>
    <div class="layui-input-block">
      <input type="text" name="memoName" lay-verify="memoName" autocomplete="off" placeholder="请输入行程主题" class="layui-input" maxlength="10">
    </div>
  </div>
 <div class="layui-form-item my-layui-form-item">
    <label class="layui-form-label">行程状态</label>
    <div class="layui-input-block">
      <input type="radio" name="status" value="1" title="未开始"  checked="">
      <input type="radio" name="status"  value="2" title="已开始">
      <input type="radio" name="status"  value="3" title="已取消">
    </div>
  </div>
  <div class="layui-form-item my-layui-form-item">
    <label class="layui-form-label">行程类型</label>
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
  <div class="layui-form-item my-layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label">开始时间</label>
      <div class="layui-input-block">
        <input type="text" name="memoStartTime" id="txtStartTime" lay-verify="memoStartTime" placeholder="请选择开始时间" autocomplete="off" class="layui-input" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">结束时间</label>
      <div class="layui-input-block">
        <input type="text" name="memoEndTime" id="date" lay-verify="memoEndTime" placeholder="请选择结束时间" autocomplete="off" class="layui-input" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
      </div>
    </div>
  </div>
  
  
  
  <div class="layui-form-item my-layui-form-item layui-form-text">
    <label class="layui-form-label">行程备注</label>
    <div class="layui-input-block">
      <textarea placeholder="请输入行程备注" class="layui-textarea" name="memoDesc"></textarea>
    </div>
  </div>
  <div class="layui-form-item my-top">
    <div class="layui-input-block">
      <button class="layui-btn" lay-submit="" lay-filter="mySubmit">立即提交</button>
      <button class="layui-btn layui-btn-primary" onclick="cancelEdit();">取消</button>
    </div>
  </div>
</form>
 <script type="text/javascript" src="${ctx}/js/jquery.js"></script>         
<script src="${ctx}/js/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/js/pages/memo/memo_edit.js"></script>
<script>
layui.use(['form', 'layedit', 'laydate'], function(){
  var form = layui.form()
  ,layer = layui.layer
  ,layedit = layui.layedit
  ,laydate = layui.laydate;
  
  //自定义验证规则
  form.verify({
	  memoName: function(value){
	      if(value.length < 2){
	        return '行程主题的长度不能小于2个字符';
	      }
	
	      if(value.length > 10){
	          return '行程主题的长度不能大于10个字符';
	       }
	    },
	  memoTypeId: function(value){
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
		 var activityStartTime = document.getElementById("txtStartTime").value;
		 if(value <activityStartTime){
			 return '结束时间不能早于开始时间';
			 }
	  }
  });
  
  
  
  //监听提交
  form.on('submit(mySubmit)', function(data){
	var url = ctx + "/inner/memo/saveOrUpdate"
	var memo =  $('#myForm').serialize();
    $.ajax({
        cache: true,
        type: "POST",
        url: url,
        data: memo,
        async: false,
        success: function(data) {
           if(data.indexOf("_") > 0){
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
         					  var activityStartTime = $("input[name='memoStartTime']").val();;
         					  var endTime = $("input[name='memoEndTime']").val();;
     						  window.parent.addSuccess(memoName, activityStartTime, endTime, id);
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
            }
        }
    });
    return false;
  });
  
  
});
</script>

</body>
</html>