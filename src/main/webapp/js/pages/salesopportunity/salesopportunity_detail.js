
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
function transfer(id, competitorName){
	var url = ctx + "/inner/user/selectAllUser?id=" + id + "&name=" + competitorName;
	var title = "转移产品【" + competitorName   +"】";
	layer.open({
		type: 2,
		title: title,
		shadeClose: true,
		shade: 0.8,
		area: ['500px', '400px'],
		content: url
	}); 
}

function fixCompetitor(){
	$("#btnEdit").hide();
	$("#btnCancel").show();
	$("#btnSave").show();
	$("#editTable").show();
	$("#detailTable").hide();
}

function saveCompetitor(){
	/*$("#btnEdit").hide();
	$("#btnCancel").show();
	$("#btnSave").show();*/
}

function cancelCompetitor(){
	$("#btnEdit").show();
	$("#btnCancel").hide();
	$("#btnSave").hide();
}
function deleteById(id, name){
	layer.confirm("确定要删除产品【" + name + "】相关信息吗？",{closeBtn: false,
  		skin: 'layui-layer-molv'
	  }, function(){
		  var url = ctx + "/inner/salesopportunity/deleteById?id=" + id +"&name=" + name;
		$.ajax({
			type: "get",
			url: url,
			success: function(result){
				if(result == 2){
					layer.alert("删除产品成功",{closeBtn: false,
				  		skin: 'layui-layer-molv'
					  }, function(){
						  parent.refreshTable();
						  parent.layer.closeAll();
					  });
				}else{
					layer.alert("删除产品失败",{closeBtn: false,
				  		skin: 'layui-layer-molv'
					  });
				}
			}
		});
	})
}

function viewCustomer(id, name){
	parent.layer.closeAll();
	parent.viewCustomerDetail(id, name);
}
