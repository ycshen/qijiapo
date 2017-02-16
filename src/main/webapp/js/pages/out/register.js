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

function submit(){
	var isSubmit = true;
	var companyName = $("#txtCompanyName").val();
	if(isBlank(companyName)){
		tips("亲，公司名称不能为空哦~~~");
		isSubmit = false;
		return;
	}
	var userName = $("#txtUserName").val();
	if(isBlank(userName)){
		tips("亲，姓名不能为空哦~~~");
		isSubmit = false;
		return;
	}
	
	var telephone = $("#txtTelephone").val();
	var reg = /^1[3|4|7|5|8]\d{9}$/;
	if(isBlank(telephone)){
		tips("亲，手机号码不能为空哦~~~");
		isSubmit = false;
		return;
	}else if(telephone.length != 11){
		tips("亲，手机号码的长度是11位哦~~~");
		isSubmit = false;
		return;
	}else if(!reg.test(telephone)){
		tips("亲，请输入正确的手机号码格式~~~");
		isSubmit = false;
		return;
	}
	
	if(isSubmit){
		var registerForm = $("#registerForm");
		registerForm.action = ctx + "/out/register";
		registerForm.submit();
	}
	
}

function tips(msg){
	$("#tipDiv").html(msg);
}

