function showAuthMenu(authId, authName){
	$("#spanTips").html("<span style=\"color:#009688 \">" + authName + "</span>菜单权限定义");
	$("#divMenuOper").show();
	$("#hidAuthId").val(authId);
	$("#hidAuthName").val(authName);
	$("#divMenuTips").hide();
	initMenuTree(authId);
}

$(function(){
	var authInfo = $("#hidAuthInfo0").val();
	var authId = authInfo.split("_")[0];
	var authname = authInfo.split("_")[1];
	showAuthMenu(authId, authname);
});

function isBlank(args){
	var result = false;
	if(args == "" || args == null || args == undefined){
		result = true;
	}
	
	return result;
}
function definedByUser(){
	window.location.href = ctx + "/inner/admin/menudefined/defineByUser";
}

function definedByDept(){
	window.location.href = ctx + "/inner/admin/menudefined/defineByDept";
}

function definedByPosition(){
	window.location.href = ctx + "/inner/admin/menudefined/defineByPosition";
}

function resetMenu(){
	var authId = $("#hidAuthId").val();
	if(isBlank(authId)){
		layer.alert("请选择需要重置权限的部门",{closeBtn: false,
	  		skin: 'layui-layer-molv'
		  });
		return;
	}

	initMenuTree(authId);
	
}
function isNotBlank(args){
	var result = false;
	if(args != "" && args != null && args != undefined){
		result = true;
	}
	
	return result;
}


function submitMD(){
	var authId = $("#hidAuthId").val();
	if(isBlank(authId)){
		layer.alert("请选择需要菜单定义的部门",{closeBtn: false,
	  		skin: 'layui-layer-molv'
		  });
		return;
	}

	var authIName = $("#hidAuthName").val();
	layer.confirm(authIName + "旗下的所有员工即将获得授权菜单的权限，确定要授权" + authIName + "的菜单权限吗？该操作不可逆！",{closeBtn: false,
  		skin: 'layui-layer-molv'
	  }, function(){
		  var treeObj = $.fn.zTree.getZTreeObj("menuTree"); 
		    var nodes = treeObj.getCheckedNodes(true);
		    var idStr = "";
		    for(var i=0; i < nodes.length;i++){
		    	idStr+= nodes[i].id + ",";
		    }
		    
		    if(isNotBlank(idStr)){
		    	idStr = idStr.substring(0, idStr.length - 1);
		    	
		    }
		    
		    //无节点时需要提示
		    var url = ctx + "/inner/admin/menudefined/saveForDept?defineType=4&idStr=" + idStr + "&casecadeId=" + authId
		    $.ajax({
		    	type: "get",
		    	url: url,
		    	success: function(result){
		    		if(result == 2){
						layer.alert("设置菜单权限成功",{closeBtn: false,
					  		skin: 'layui-layer-molv'
						  });
					}else{
						layer.alert("设置菜单权限失败",{closeBtn: false,
					  		skin: 'layui-layer-molv'
						  });
					}
		    	}
		    });
	})
    
    
}
function initMenuTree(authId) {
	   $.ajax({
		   type : 'get',
		   url : ctx + "/inner/menu/menuTree?definedType=4&casecadeId=" + authId,
		   success: function(zNodes){
			   zTreeObj_menu = $.fn.zTree.init($("#menuTree"), setting_menu, zNodes);
			   zTreeObj_menu.expandAll(true); 
			   
		   }
	   })         
	}

var zTreeObj_menu;
//zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
var setting_menu = {
     
     data: {
         simpleData: {
             enable: true
         }
     },
     check: {
     	chkStyle: "checkbox",
     	enable: true
     }
 };
