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
	$('#txtOffWorkTime').timepicki();
	$('#txtOnWorkTime').timepicki();
})