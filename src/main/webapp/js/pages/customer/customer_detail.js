
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
	var title = "转移客户【" + competitorName   +"】";
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
	layer.confirm("确定要删除客户【" + name + "】相关信息吗？",{closeBtn: false,
  		skin: 'layui-layer-molv'
	  }, function(){
		  var url = ctx + "/inner/customer/deleteById?id=" + id +"&name=" + name;
		$.ajax({
			type: "get",
			url: url,
			success: function(result){
				if(result == 2){
					layer.alert("删除客户成功",{closeBtn: false,
				  		skin: 'layui-layer-molv'
					  }, function(){
						  parent.refreshTable();
						  parent.layer.closeAll();
					  });
				}else{
					layer.alert("删除客户失败",{closeBtn: false,
				  		skin: 'layui-layer-molv'
					  });
				}
			}
		});
	})
}

function addSalesOpportunity(customerId){
	var url = ctx + "/inner/salesOpportunity/forwardEdit?customerId=" + customerId;
	layer.open({
		type: 2,
		title: '新增销售机会',
		shadeClose: false,
		shade: 0.8,
		area: ['830px', '450px'],
		content: url
	});
}

function reloadSaleOppo(){

}

function viewSalesOppoDetail(id, name){
	parent.layer.closeAll();
	var url = ctx + "/inner/salesOpportunity/detail?id=" + id;
	var title = "销售机会【" + name   +"】";
	layer.open({
		type: 2,
		title: title,
		shadeClose: false,
		shade: 0.8,
		area: ['80%', '100%'],
		content: url
	});
}