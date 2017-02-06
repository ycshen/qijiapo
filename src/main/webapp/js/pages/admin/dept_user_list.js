
var layerIndex;
function addMenu(){
	var url = ctx + "/inner/menu/edit";
	layer.open({
		type: 2,
		title: '添加菜单权限信息',
		shadeClose: true,
		shade: 0.8,
		area: ['550px', '400px'],
		content: url
	});
}

function modifyConfig(id){
	var url = ctx + "/inner/config/edit?id=" + id;
	layer.open({
		type: 2,
		title: '编辑基础配置信息',
		shadeClose: true,
		shade: 0.8,
		area: ['550px', '400px'],
		content: url
	});
}

function addSameConfig(id){
	var url = ctx + "/inner/config/addSame?id=" + id;
	layer.open({
		type: 2,
		title: '新增基础配置信息',
		shadeClose: true,
		shade: 0.8,
		area: ['550px', '400px'],
		content: url
	});
}
function viewConfig(id){
	var url = ctx + "/inner/config/view?id=" + id;
	layer.open({
		type: 2,
		title: '查看基础配置详细信息',
		shadeClose: true,
		shade: 0.8,
		area: ['550px', '400px'],
		content: url
	});
}

function deleteMenu(id){
	layer.confirm("确定删除该菜单信息？", function(){
		var url = ctx + "/inner/menu/delete?id=" + id;
		$.ajax({
	        type: "get",
	        url: url,
	        success: function() {
	        	layer.alert('删除成功', function(index){
	      		  window.location.reload();
	    		});
	        }
	    });
	})
	
}


function queryMenu(page){
	var url = ctx + "/inner/menu/list?page=" + page;
	var menuName = $("#txtmenuName").val();
	if(isNotBlank(menuName)){
		url += "&menuName=" + menuName;
	}
	
	var menuTypeName = $("#menuTypeSelect").find("option:selected").text();
	if(menuTypeName != "请选择菜单类型"){
		var menuType = $("#menuTypeSelect").find("option:selected").val();
		url += "&menuType=" + menuType;
	}
	
	
	var menuUrl = $("#txtmenuUrl").val();
	if(isNotBlank(menuUrl)){
		url += "&menuUrl=" + menuUrl;
	}
	
	window.location.href = url;
}
function isNotBlank(args){
	var result = false;
	if(args != "" && args != null && args != undefined){
		result = true;
	}
	
	return result;
}
   
$(function(){
	initTree();
})

var tid = "";
var addPid = "";
function addHoverDom(treeId, treeNode) {
    var sObj = $("#" + treeNode.tId + "_span");
    if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;

	
	var title = "添加下级部门";	
    var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
        + "' title='" + title + "' onfocus='this.blur();'></span>";
    sObj.after(addStr);
    var btn = $("#addBtn_"+treeNode.tId);
    if (btn) btn.bind("click", function(){	
    	addPid = treeNode.id;
    	tid = treeNode.tId;
    	var pid = addPid.split("_")[0];
    	var nodeType = addPid.split("_")[1];
    	var name = treeNode.name;
    	var layTitle = "添加下级部门("+ name +")";
    	layer.open({
  		  type: 2,
  		  area: ['700px', '400px'],
  		  fixed: false, //不固定
  		  maxmin: true,
  		  title: layTitle,
  		  content: ctx + "/inner/admin/addSubDept?parentDepartmentId=" + pid + "&parentDepartmentName=" + name + "&nodeType=" + nodeType 
  		});
        return false;
    });
};

function addUserSuccess(){
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	var selectedTreeNode = zTree.getSelectedNodes();
	var treeNode = selectedTreeNode[0];
	var nodeIdStr = treeNode.id;
	var id = nodeIdStr.split("_")[0];
	var nodeType = nodeIdStr.split("_")[1];
	//判断有没有子节点
	var childrenNodes = treeNode.children;
	if(childrenNodes.length > 0){
		$("#btnAddUser").hide();
	}else{
		$("#btnAddUser").show();
	}
	if(nodeType == 3){
		//获取对应部门的员工
		$("#btnAddUser").text("添加" + treeNode.name + "旗下员工");
		$("#spanName").text(treeNode.name);
		$("#hidDid").val(id);
		var userName = $("#txtUserName").val();
		var telphone = $("#txtTelphone").val();
		getJsonData(id, userName, telphone, 1);
	}else if(nodeType == 1){
		//获取全部员工
		$("#spanName").text(treeNode.name);
		getJsonData('', userName, telphone, 1);
	}
	
	layer.closeAll();
	
}
function addSuccess(name, id){
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	var treeNode = zTree.getNodeByTId("treeDemo_" + tid);
	var idStr = id + "_";
	var pNodeId = addPid.split("_")[0];
	var pNodeType = addPid.split("_")[1];
	if(pNodeType == 3){
		idStr += "3_" + pNodeId;
	}
	
    zTree.addNodes(treeNode, {id:idStr, pId:addPid, name:name});

	layer.closeAll();
	
}

function removeHoverDom(treeId, treeNode) {
    $("#addBtn_"+treeNode.tId).unbind().remove();
};
        
var nodeId = "";
var zTreeObj;
// zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
var setting = {
        view: {
            addHoverDom: addHoverDom
        },
        data: {
            simpleData: {
                enable: true
            }
        },
       edit: {
            enable: true,
            drag: {
    			isCopy: false,
    			isMove: false
    		}
        },
        callback:{
        	onClick: showStaff
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
 

function showStaff(event, treeId, treeNode){
	var nodeIdStr = treeNode.id;
	var id = nodeIdStr.split("_")[0];
	var nodeType = nodeIdStr.split("_")[1];
	//判断有没有子节点
	var childrenNodes = treeNode.children;
	if(childrenNodes.length > 0){
		$("#btnAddUser").hide();
	}else{
		$("#btnAddUser").show();
	}
	if(nodeType == 3){
		//获取对应部门的员工
		$("#btnAddUser").text("添加" + treeNode.name + "旗下员工");
		$("#spanName").text(treeNode.name);
		$("#hidDid").val(id);
		var userName = $("#txtUserName").val();
		var telphone = $("#txtTelphone").val();
		getJsonData(id, userName, telphone, 1);
	}else if(nodeType == 1){
		//获取全部员工
		$("#spanName").text(treeNode.name);
		getJsonData('', userName, telphone, 1);
	}
}

function addUser(){
	var did = $("#hidDid").val();
	layer.open({
		  type: 2,
		  area: ['700px', '400px'],
		  fixed: false, //不固定
		  maxmin: true,
		  title: "添加员工",
		  content: ctx + "/inner/user/add?did=" + did 
		});
}

function addMultiSub(id){
	var divMenuTag = $("#divMenuType");
	//var url = ctx + "/inner/menu/addSubMenu?parentMenuId=" + id + "&menuType=" + menuType;
	$("#hidMenuId").val(id);
	layer.open({
		  title: "请选择事件类型",
		  type: 1,
		  skin: 'layui-layer-rim', //加上边框
		  area: ['600px', '200px'], //宽高
		  content: divMenuTag
	});
}

function confirmType(){
	var value = $("input[name='menuType']:checked").val()
	if(isNotBlank(value)){
		var id = $("#hidMenuId").val();
		addSub(id, value);
	}else{
		layer.alert("请选择子元素类型");
	}
}

function cancleType(){
	layer.closeAll();
}

function forbidUser(id, userName){
	
	$.ajax({
		type: "get",
		url: ctx + "/inner/user/forbidUser?id=" + id + "&userName=" + userName,
		success: function(result){
			if(result == 2){
				//成功
				layer.alert("停用员工成功");
			}else{
				//失败
				layer.alert("停用员工失败");
			}
		}
	});
}

function forbidLogin(id, userName){
	
	$.ajax({
		type: "get",
		url: ctx + "/inner/user/forbidLogin?id=" + id + "&userName=" + userName,
		success: function(result){
			if(result == 2){
				//成功
				layer.alert("停用员工成功");
			}else{
				//失败
				layer.alert("停用员工失败");
			}
		}
	});
}
