
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

function selectCustomer(){
	var url = ctx + "/inner/customer/sampleList";
	layer.open({
		type: 2,
		title : false,
		shadeClose: false,
		closeBtn: 0,
		shade: 0.8,
		area: ['830px', '400px'],
		content: url
	});
}

function getCustomer(id, name){
	$("#txtCustomerName").val(name);
	$("#txtCustomerId").val(id);
	$("#txtCustomerNameHide").val(name);
	layui.layer.closeAll();
}

function addProduct(){

}

function clearNoNum(obj){
	obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符
	obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的
	obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
	obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');//只能输入两个小数
	if(obj.value.indexOf(".")< 0 && obj.value !=""){//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额
		obj.value= parseFloat(obj.value);
	}
}
