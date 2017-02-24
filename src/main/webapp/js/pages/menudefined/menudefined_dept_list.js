
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
	initMenuTree();
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
        callback:{
        	onClick: returnInfo,
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
        	onClick: returnInfo,
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

function initMenuTree() {
	   $.ajax({
		   type : 'get',
		   url : ctx + "/inner/menu/menuTree",
		   success: function(zNodes){
			   zTreeObj_menu = $.fn.zTree.init($("#menuTree"), setting_menu, zNodes);
			   zTreeObj_menu.expandAll(true); 
			   
		   }
	   })         
	}
 
function returnInfo(event, treeId, treeNode){
	var nodeIdStr = treeNode.id;
	var id = nodeIdStr.split("_")[0];
	var departmentName = treeNode.name;
	
	parent.returnInfo(id, departmentName);
}

function cancleType(){
	layer.closeAll();
}


 

