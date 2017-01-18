var index = parent.layer.getFrameIndex(window.name);
function cancelEdit(){
	parent.layer.close(index);
}

function isBlank(args){
	var result = false;
	if(args == "" || args == null || args == undefined){
		result = true;
	}
	
	return result;
}
function editDepartment(){
	var departmentName = $("#txtdepartmentName").val();
	if(isBlank(departmentName)){
		layer.alert("部门名称不能为空",{closeBtn: false,
	  		skin: 'layui-layer-molv'
		  });
		return;
	}
	
	
	var pid = $("#hidPid").val();
	var url = ctx + "/inner/department/saveOrUpdate";
	var data = $('#myForm').serialize();
	$.ajax({
        cache: true,
        type: "POST",
        url: url,
        data: data,
        async: false,
        success: function(data) {
        	var id = "";
        	if(data.indexOf("id") > 0){
        		var idObj = data.split("_")[1];
        		id = idObj.substring(2);
        		data = data.split("_")[0];
        	}
			  if(data == 1){
				  layer.alert('新增成功',
						  {closeBtn: false,
						  skin: 'layui-layer-molv'
						  },
						  function(index){
							  window.parent.addSuccess(departmentName, id);
						  }
			);
          }else if(data == 2){
        	  layer.alert('更新成功', 
        			  {closeBtn: false,
  		  		skin: 'layui-layer-molv'
  			  },function(index){
        		  window.parent.addSuccess();
        		  
      		});
          }else if(data == 4){
        	  layer.alert("该部门已存在相同的下级部门名称！",
        			  {closeBtn: false,
  		  		skin: 'layui-layer-molv'
  			  }); 
          }else{
        	  layer.alert("操作失败！",
        			  {closeBtn: false,
  		  		skin: 'layui-layer-molv'
  			  }); 
          }
        }
    });
	
}

