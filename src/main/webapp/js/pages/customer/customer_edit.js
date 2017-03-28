
function cancelEdit(){
	parent.layer.closeAll();
}
function addMoreInfo(){
	$("#divViewer").hide();
	$("#divOther").show();
}
function returnInfo(id, name){
	layer.closeAll();
	$("#txtDepartmentName").removeAttr("disabled");//要变成Enable，JQuery只能这么写  
	$("#txtDepartmentName").val(name);
	$("#txtDepartmentName").attr("disabled","disabled");//再改成disabled  
	$("#hidDeptId").val(id);
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
