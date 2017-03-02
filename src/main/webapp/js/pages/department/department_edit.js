
function cancelEdit(){
	window.parent.layer.closeAll();
}

function isBlank(args){
	var result = false;
	if(args == "" || args == null || args == undefined){
		result = true;
	}
	
	return result;
}
function editDepartment(){
	var txtdepartmentName = $("#txtdepartmentName").val();
	if(isBlank(txtdepartmentName)){
		layer.alert("部门名称不能为空");
		return;
	}
	
	var url = ctx + "/inner/department/saveOrUpdate";
	var data = $('#departmentForm').serialize();
	$.ajax({
        cache: true,
        type: "POST",
        url: url,
        data: data,
        async: false,
        success: function(data) {
          if(data == 2){
        	  layer.alert('更新成功',{closeBtn: false,
        	  		skin: 'layui-layer-molv'
        	  }, function(index){
        		  window.parent.layer.closeAll();
        		  var departmentId = $("#hidId").val();
        		  window.parent.updateDeptSuccess(departmentId, txtdepartmentName);
        		  
      		});
          }else{
        	  layer.alert("操作失败！",{closeBtn: false,
        	  		skin: 'layui-layer-molv'
        	  }); 
          }
        }
    });
	
}

