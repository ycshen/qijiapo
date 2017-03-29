
function cancelEdit(){
	parent.layer.closeAll();
}
function addMoreInfo(){
	$("#divViewer").hide();
	$("#divOther").show();
}
function returnInfo(id, name){
	layer.closeAll();
	$("#txtBeyondDeptName").removeAttr("disabled");//要变成Enable，JQuery只能这么写  
	$("#txtBeyondDeptName").val(name);
	$("#txtBeyondDeptName").attr("disabled","disabled");//再改成disabled  
	$("#hidBeyondDeptId").val(id);
	$("#hidBeyondDeptName").val(name);
}

function isBlank(args){
	var result = false;
	if(args == "" || args == null || args == undefined){
		result = true;
	}
	
	return result;
}

$(function(){
	initLocation();
});

