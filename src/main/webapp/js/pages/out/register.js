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
	var submitBool = $("#hidIsSubmit").val();
	if(submitBool == 0){
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
		
		var email = $("#txtEmail").val();
		var emailRegex =  /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
		if(isBlank(email)){
			tips("亲，电子邮箱不能为空哦~~~");
			isSubmit = false;
			return;
		}else if(!emailRegex.test(email)){
			tips("亲，请输入正确的电子邮箱格式~~~");
			isSubmit = false;
			return;
		}
		
		var password = $("#txtPass").val();
		var passRegex = new RegExp('(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{6,15}');
		if(isBlank(password)){
			tips("亲，密码不能为空哦~~~");
			isSubmit = false;
			return;
		}else if(password.length < 6){
			tips("亲，密码的长度是由6-15位的数字、字母、特殊字符组成~~~");
			isSubmit = false;
			return;
		}else if(passRegex.test(password)){
			
			tips("亲，密码的长度是由6-15位的数字、字母、下划线组成~~~");
			isSubmit = false;
			return;
		}
		
		var confirmPass = $("#txtConfirmPass").val();
		if(isBlank(confirmPass)){
			tips("亲，请确认密码~~~");
			isSubmit = false;
			return;
		}else if(confirmPass.length < 6){
			tips("亲，两次输入的密码不一致，请重新输入~~");
			isSubmit = false;
			return;
		}else if(password != confirmPass){
			tips("亲，两次输入的密码不一致，请重新输入~~");
			isSubmit = false;
			return;
		}
		
		if(isSubmit){
			$("#btnRegister").html("正在初始化......");
			$("#btnRegister").removeAttr("onclick");
			var registerForm = $("#registerForm");
			registerForm.action = ctx + "/out/register";
			registerForm.submit();
		}
	}
}

function tips(msg){
	$("#tipDiv").html(msg);
}

$(function(){
	initTelephone();
})

function initTelephone(){
	
	$("#txtTelephone").focusout(function(){
		var telephone = $("#txtTelephone").val();
		var reg = /^1[3|4|7|5|8]\d{9}$/;
		if(isBlank(telephone)){
			tips("亲，手机号码不能为空哦~~~");
			$("#hidIsSubmit").val(1)
			return;
		}else if(telephone.length != 11){
			tips("亲，手机号码的长度是11位哦~~~");
			$("#hidIsSubmit").val(1)
			return;
		}else if(!reg.test(telephone)){
			tips("亲，请输入正确的手机号码格式~~~");
			$("#hidIsSubmit").val(1)
			return;
		}else{
			var url = ctx + "/out/isExistTelephone?telephone=" + telephone;
			$.ajax({
				url: url,
				type: "get",
				success: function(result){
					if(result > 0){
						$("#hidIsSubmit").val(1);
						tips("亲，该手机号码已经注册了哦~~~");
					}else{
						$("#hidIsSubmit").val(0)
						tips("");
					}
				}
			});
		}
	});
	
}

