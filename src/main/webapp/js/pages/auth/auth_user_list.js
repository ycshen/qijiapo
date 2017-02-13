$(function(){
	initAllCheckEvt();
});
function cancelAuth(){
	layer.confirm("确定取消授权信息？", function(){
		var idList = "";
		var userList = "";
		if($("#chkAll").is(':checked')){
			var chkArray = $("input:checkbox");
			var size = chkArray.length;
			for(var i=0;i < size;i++ ){
				var isChkAll = chkArray[i].id;
				if( isChkAll  != "chkAll"){
					var id = chkArray[i].value;
					idList += id + "^";
					userList += $("#userName" + id).html() + ";";
				}
			}
			
			
			
		}else{
			var chkArray = $("input:checkbox");
			var size = chkArray.length;
			var size = chkArray.length;
			for(var i=0;i < size;i++ ){
				var isChkAll = chkArray[i].id;
				if( isChkAll  != "chkAll" && chkArray[i].checked){
					var id = chkArray[i].value;
					idList += id + "^";
					userList += $("#userName" + id).html() + ";";
				}
			}
		}
		
		if(isNotBlank(idList)){
			idList = idList.substring(0, idList.length - 1);
			var authId = $("#hidAuthId").val();
			var authName = $("#hidAuthName").val();
			var url = ctx + "/inner/auth/cancelAuth?idList=" + idList+ "&authName="+ authName + "&authId="+ authId + "&userList=" + userList;
			$.ajax({
				url: url,
				type: "get",
				success: function(result){
					if(result == 1){
						layer.alert("取消授权成功");
						
					}else{
						layer.alert("取消授权失败");
					}
				}
			});
		}
	})
	
	
}
function addAuth(){
	var html = $("#multiDiv");
	layer.open({
		  type: 1,
		  title: false,
		  closeBtn: 0,
		  shadeClose: true,
		  skin: 'yourclass',
		  area: ['600px', '300px'],
		  content: html
		});
}
function initAllCheckEvt(){
	$("#chkAll").change(function(){
		if($("#chkAll").is(':checked')) {
		    $("#cancelAuth").show();
		    $("#addAuth").hide();
		    $("input:checkbox").each(function () {     
	            $(this).prop('checked', true);//  
	  
	        });  
		   
		}else{
			$("#cancelAuth").hide();
		    $("#addAuth").show();
		    $("input:checkbox").removeAttr("checked");  
		}
	});
	
	 $("input:checkbox").change(function(){
		 var idValue = $(this).attr("id");
		if(idValue != "chkAll"){
			if($("#chkAll").is(':checked')){
				var chkArray = $("input:checkbox");
				var isCheckedAll = true;
				var size = chkArray.length;
				for(var i=0;i < size;i++ ){
					var isChkAll = chkArray[i].id;
					if( isChkAll  != "chkAll" && chkArray[i].checked){
						isCheckedAll = false;
						break;
					}
				}
				
				if(!isCheckedAll){
					$("#chkAll").removeAttr("checked");  
				}
			}
			
			var chkArray = $("input:checkbox");
			var size = chkArray.length;
			var isShowAddAuth = "0";
			for(var i=0;i < size;i++ ){
				var isChkAll = chkArray[i].id;
				if( isChkAll  != "chkAll" && chkArray[i].checked){
					isShowAddAuth += "1";
				}
			}
			
			if(isShowAddAuth.indexOf("1") > 0){
				 $("#cancelAuth").show();
				 $("#addAuth").hide(); 
			}else{
				$("#cancelAuth").hide();
			    $("#addAuth").show();
			}
		}
	 })
}

function isNotBlank(args){
	var result = false;
	if(args != "" && args != null && args != undefined){
		result = true;
	}
	
	return result;
}


