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