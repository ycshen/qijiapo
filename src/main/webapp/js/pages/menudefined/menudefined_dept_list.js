
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

$(function(){
	initTree();
	//initMenuTree();
})

var tid = "";
var addPid = "";
var operTreeNode;

function getRoot() {  
    var treeObj = $.fn.zTree.getZTreeObj("treeDemo");  
    //返回一个根节点  
   var node = treeObj.getNodesByFilter(function (node) {
	   if(node.level == 0){
		   return node;
	   }
	   }, true);  
   return node;
} 

        
var zTreeObj_dept;
var zTreeObj_menu;
// zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
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
var setting_dept = {
        
        data: {
            simpleData: {
                enable: true
            }
        },
        callback:{
        	onClick: returnDeptInfo,
        }
    };
// zTree 的数据属性，深入使用请参考 API 文档（zTreeNode 节点数据详解）
function initTree() {
   $.ajax({
	   type : 'get',
	   url : ctx + "/inner/admin/deptree",
	   success: function(zNodes){
		   zTreeObj_dept = $.fn.zTree.init($("#treeDemo"), setting_dept, zNodes);
		   zTreeObj_dept.expandAll(true); 
		   
	   }
   })         
}

function initMenuTree(departmentId) {
	   $.ajax({
		   type : 'get',
		   url : ctx + "/inner/menu/menuTree?definedType=3&casecadeId=" + departmentId,
		   success: function(zNodes){
			   zTreeObj_menu = $.fn.zTree.init($("#menuTree"), setting_menu, zNodes);
			   zTreeObj_menu.expandAll(true); 
			   
		   }
	   })         
	}
 
function returnDeptInfo(event, treeId, treeNode){
	var nodeIdStr = treeNode.id;
	var departmentName = treeNode.name;
	var departmentId = nodeIdStr.split("_")[0];
	$("#spanTips").html("<span style=\"color:#009688 \">" + departmentName + "</span>菜单权限定义");
	$("#divMenuOper").show();
	$("#hidDepartmentId").val(departmentId);
	$("#hidDepartmentName").val(departmentName);
	$("#divMenuTips").hide();
	initMenuTree(departmentId);
}

function cancleType(){
	layer.closeAll();
}

function returnMenuDefined(){
	window.location.href = ctx + "/inner/admin/menudefined/list";
}

function definedByUser(){
	window.location.href = ctx + "/inner/admin/menudefined/defineByUser";
}

function definedByRole(){
	window.location.href = ctx + "/inner/admin/menudefined/defineByRole";
}

function definedByPosition(){
	window.location.href = ctx + "/inner/admin/menudefined/defineByPosition";
}

function resetMenu(){
	var departmentId = $("#hidDepartmentId").val();
	if(isBlank(departmentId)){
		layer.alert("请选择需要重置权限的部门",{closeBtn: false,
	  		skin: 'layui-layer-molv'
		  });
		return;
	}

	initMenuTree(departmentId);
	
}
function submitMD(){
	var departmentId = $("#hidDepartmentId").val();
	if(isBlank(departmentId)){
		layer.alert("请选择需要菜单定义的部门",{closeBtn: false,
	  		skin: 'layui-layer-molv'
		  });
		return;
	}

	var departmentIName = $("#hidDepartmentName").val();
	layer.confirm(departmentIName + "旗下的所有部门以及部门对应的员工即将获得授权菜单的权限，确定要授权" + departmentIName + "的菜单权限吗？该操作不可逆！",{closeBtn: false,
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
		    var url = ctx + "/inner/admin/menudefined/saveForDept?defineType=3&idStr=" + idStr + "&casecadeId=" + departmentId
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

 

