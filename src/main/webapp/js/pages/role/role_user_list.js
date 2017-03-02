$(function(){
	initAllCheckEvt();
});
function cancelRole(){
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
			var roleId = $("#hidRoleId").val();
			var roleName = $("#hidRoleName").val();
			var url = ctx + "/inner/role/cancelRole?idList=" + idList+ "&roleName="+ roleName + "&roleId="+ roleId + "&userList=" + userList;
			$.ajax({
				url: url,
				type: "get",
				success: function(result){
					if(result == 1){
						layer.alert("取消授权成功",function(){
							reloadRoleUser(roleId);
							layer.closeAll();
						});
						
					}else{
						layer.alert("取消授权失败");
					}
				}
			});
		}
	})
	
	
}

function reloadRoleUser(roleId){
	var url = ctx + "/inner/role/roleUserListAsyc?roleId=" + roleId;
	$.ajax({
		type: "get",
		url: url,
		success: function(data){
			var count = data.count;
				var tbody = "<tbody>";
				var userNameList = "";
				$.each(data.items, function(index, obj){
					tbody += getTr(obj);
					userNameList += obj.userName + ";"
				})
				
				if(userNameList.length > 0){
					userNameList = userNameList.substring(0, userNameList.length - 1)
				}
				
				parent.roleSuccess(roleId, userNameList, count)
				var tableHead = getHead();
				tbody += "</tbody>";
				tableHead += tbody;
				$("#syslist").html(tableHead);
				
		}
	});
}

function getTr(obj){
	var tr = "";
	tr+="<tr>";
	tr+="<td>";
	tr+="<div class=\"checkbox\" style=\"margin: 0px\">";
	tr+="<label>";
	tr+="<input type=\"checkbox\" id=\"chkUser" + obj.roleUserId + "\" value=\"" + obj.roleUserId + "\">";
	tr+="</label>";
	tr+="</div>";
	tr+="</td>";
	tr+="<td id=\"userName" + obj.roleUserId + "\">" + obj.userName + "</td>";
	tr+="<td id=\"userStatus" + obj.id + "\">";
	var departmentName = "";
	if(isNotBlank(obj.departmentName)){
		departmentName = obj.departmentName;
	}
	tr+="" + departmentName + "";
	tr+="</td>";
	var positionName = "";
	if(isNotBlank(obj.positionName)){
		positionName = obj.positionName;
	}
	tr+="<td>" + positionName + "</td>";
	tr+="</tr>";

	return tr;

}

function getHead(tableId){
	var head =  $("#syslist").find("thead").html();
	head = "<thead>" + head  + "</thead>";
	return head;
}

function addRole(roleId, roleName){
	var url = ctx + "/inner/role/roleUserPage?roleId=" + roleId;
	layer.open({
		type: 2,
		title: "[" + roleName + "]分配员工",
		shadeClose: true,
		shade: 0.8,
		area: ['400px', '400px'],
		content: url
	});
}
function initAllCheckEvt(){
	$("#chkAll").change(function(){
		if($("#chkAll").is(':checked')) {
		    $("#cancelRole").show();
		    $("#addRole").hide();
		    $("input:checkbox").each(function () {     
	            $(this).prop('checked', true);//  
	  
	        });  
		   
		}else{
			$("#cancelRole").hide();
		    $("#addRole").show();
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
			var isShowAddRole = "0";
			for(var i=0;i < size;i++ ){
				var isChkAll = chkArray[i].id;
				if( isChkAll  != "chkAll" && chkArray[i].checked){
					isShowAddRole += "1";
				}
			}
			
			if(isShowAddRole.indexOf("1") > 0){
				 $("#cancelRole").show();
				 $("#addRole").hide(); 
			}else{
				$("#cancelRole").hide();
			    $("#addRole").show();
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


