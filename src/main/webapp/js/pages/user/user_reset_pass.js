
function cancelEdit(){
	layer.closeAll();
}

function isBlank(args){
	var result = false;
	if(args == "" || args == null || args == undefined){
		result = true;
	}
	
	return result;
}

function selectPosition(){
	var url = ctx + "/inner/position/list"
	layer.open({
		type: 2,
		title: '职位选择',
		shadeClose: true,
		shade: 0.8,
		area: ['550px', '300px'],
		content: url,
		closeBtn: 0
	});
	

	
}

function resetByPass(id, userName){
	var password = $("#txtPass").val();
	
	if (isBlank(password)) {
		layer.alert("请输入重置密码");
		return;
	}else{
		var url = ctx + "/inner/user/resetByPass?id=" + id + "&userName=" + userName + "&password=" + password;
		$.ajax({
	        cache: true,
	        type: "GET",
	        url: url,
	        async: false,
	        success: function(data) {
	          if(data == 2){
	        	  layer.alert("重置成功，请告知员工！");
	        	  layer.closeAll();
	      		  return;
	          }else{
	        	  layer.alert("重置失败！");
	          }
	        }
	    });
	}

}


function selectSuccess(id, positionName){
	$("#btnSelectAgain").show();
	$("#btnSelect").hide();
	$("#btnPositionName").html(positionName);
	$("#btnPositionName").show();
	$("#hidPositionId").val(id);
}

