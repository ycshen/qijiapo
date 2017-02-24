
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

        
var nodeId = "";
var zTreeObj;
// zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
var setting = {
        
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
var nodeId = "";
function initTree() {
   $.ajax({
	   type : 'get',
	   url : ctx + "/inner/admin/deptree",
	   success: function(zNodes){
		   zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
		   zTreeObj.expandAll(true); 
		   
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


 

