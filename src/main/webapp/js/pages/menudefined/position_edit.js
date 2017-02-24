function editPosition(){
	var positionName = $("#txtPositionName").val();
	if(!isNotBlank(positionName)){
		layer.alert("职位名称不能为空",{closeBtn: false,
	  		skin: 'layui-layer-molv'
		  });
		return;
	}
	
	if(positionName.length > 7){
		layer.alert("职位名称长度过长",{closeBtn: false,
	  		skin: 'layui-layer-molv'
		  });
		return;
	}
	var id = $("#txtId").val();
	var title = "添加";
	var url = ctx + "/inner/position/addPostion?postionName=" + positionName;
	if(isNotBlank(id)){
		url = ctx + "/inner/position/updatePostion?id=" + id + "&postionName=" + positionName;
		title = "更新";
	}
	
	$.ajax({
		url: url,
		type: "get",
		success: function(position){
			if(position == 2){
				layer.alert(title +"成功",{closeBtn: false,
			  		skin: 'layui-layer-molv'
				  }, function(){
					  
					  if(isNotBlank(id)){
						  window.parent.updateSuccess(id, positionName);
					  }else{
						  window.parent.addSuccess();
					  }
				  });
			}else if(position == 4){
				layer.alert("职位名称已经存在",{closeBtn: false,
			  		skin: 'layui-layer-molv'
				  });
			}else{					
				layer.alert(title + "成功",{closeBtn: false,
			  		skin: 'layui-layer-molv'
				  }, function(){
					  if(isNotBlank(id)){
						  window.parent.updateSuccess(id, positionName);
					  }else{
						  window.parent.addSuccess();
					  }
					  
				  });
			}
			
		}
	});
}

function isNotBlank(args){
	var result = false;
	if(args != "" && args != null && args != undefined){
		result = true;
	}
	
	return result;
}
var index = parent.layer.getFrameIndex(window.name);
function cancelEdit(){
	parent.layer.close(index);
}