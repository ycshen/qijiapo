function isNotBlank(args){
	var result = false;
	if(args != "" && args != null && args != undefined){
		result = true;
	}
	
	return result;
}

function submit(){
	/*var menuName = $("#txtmenuName").val();
	if(isBlank(menuName)){
		layer.alert("菜单名称不能为空");
		return;
	}
	
	var menuType = $("#menuTypeSelect").find("option:selected").text();
	if(menuType == "请选择菜单类型"){
		layer.alert("请选择菜单类型！");
		return;
	}
	*/
	var registerForm = $("#registerForm");
	registerForm.action = ctx + "/out/register";
	registerForm.submit();
	
}

