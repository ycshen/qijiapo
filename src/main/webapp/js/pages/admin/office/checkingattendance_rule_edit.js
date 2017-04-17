function cancelEdit(){
	parent.layer.closeAll();
}
function isNotBlank(args){
	var result = false;
	if(args != "" && args != null && args != undefined){
		result = true;
	}

	return result;
}

function isBlank(args){
	var result = false;
	if(args == "" || args == null || args == undefined){
		result = true;
	}

	return result;
}

function returnInfo(id, name){
	$("#hidDepartmentIdd").val(id);
	$("#hidDepartmentName").val(name);

	$("#txtBeyondDeptName").val(name);
	layer.closeAll();
}
$(function(){
	var id = $("#hidId").val();
	if(isBlank(id)){
		$("#txtOnWorkTime").val("09 : 00");
		$("#txtOffWorkTime").val("18 : 00");
	}

	var onWorkTime = $("#txtOnWorkTime").val();
	var offWorkTime = $("#txtOffWorkTime").val();
	$('#txtOffWorkTime').timepicki({
		defaultTime: offWorkTime
	});
	$('#txtOnWorkTime').timepicki({
		defaultTime: onWorkTime
	});

})

function locate(){
	var url = ctx + "/inner/common/locate";
	layer.open({
		type: 2,
		title: '考勤点地图定位',
		shadeClose: false,
		shade: 0.8,
		area: ['80%', '100%'],
		content: url
	});
}

function getLocation(addr, latitude, longitude){
	$("#spanAddr").html(addr);
	$("#txtAddress").val(addr);
}