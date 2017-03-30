
function cancelEdit(){
	parent.layer.closeAll();
}
function addMoreInfo(){
	$("#divViewer").hide();
	$("#divOther").show();
}
function returnInfo(id, name){
	layer.closeAll();
}

function isBlank(args){
	var result = false;
	if(args == "" || args == null || args == undefined){
		result = true;
	}
	
	return result;
}
function refreshTable(){
	  window.parent.refreshTable();
	  window.parent.layer.closeAll();
}
function transfer(id, attnName){
	var url = ctx + "/inner/user/selectAllUserAttn?id=" + id + "&name=" + attnName;
	var title = "转移联系人【" + attnName   +"】";
	layer.open({
		type: 2,
		title: title,
		shadeClose: true,
		shade: 0.8,
		area: ['500px', '400px'],
		content: url
	}); 
}

function fixAttn(){
	$("#btnEdit").hide();
	$("#btnCancel").show();
	$("#btnSave").show();
	$("#editTable").show();
	$("#detailTable").hide();
}

function saveAttn(){
	/*$("#btnEdit").hide();
	$("#btnCancel").show();
	$("#btnSave").show();*/
}

function cancelAttn(){
	$("#btnEdit").show();
	$("#btnCancel").hide();
	$("#btnSave").hide();
}
function deleteById(id, name){
	layer.confirm("确定要删除联系人【" + name + "】相关信息吗？",{closeBtn: false,
  		skin: 'layui-layer-molv'
	  }, function(){
		  var url = ctx + "/inner/attn/deleteById?id=" + id +"&name=" + name;
		$.ajax({
			type: "get",
			url: url,
			success: function(result){
				if(result == 2){
					layer.alert("删除联系人成功",{closeBtn: false,
				  		skin: 'layui-layer-molv'
					  }, function(){
						  parent.refreshTable();
						  parent.layer.closeAll();
					  });
				}else{
					layer.alert("删除联系人失败",{closeBtn: false,
				  		skin: 'layui-layer-molv'
					  });
				}
			}
		});
	})
}
