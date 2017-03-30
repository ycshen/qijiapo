
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
function transfer(id, customerName){
	var url = ctx + "/inner/user/selectAllUser?id=" + id + "&name=" + customerName;
	var title = "转移销售线索【" + customerName   +"】";
	layer.open({
		type: 2,
		title: title,
		shadeClose: true,
		shade: 0.8,
		area: ['500px', '400px'],
		content: url
	}); 
}

function fixSalesLeads(){
	$("#btnEdit").hide();
	$("#btnCancel").show();
	$("#btnSave").show();
	$("#editTable").show();
	$("#detailTable").hide();
}

function saveActivity(){
	/*$("#btnEdit").hide();
	$("#btnCancel").show();
	$("#btnSave").show();*/
}

function cancelSalesLeads(){
	$("#btnEdit").show();
	$("#btnCancel").hide();
	$("#btnSave").hide();
}
function deleteById(id, name){
	layer.confirm("确定要删除销售线索【" + name + "】相关信息吗？",{closeBtn: false,
  		skin: 'layui-layer-molv'
	  }, function(){
		  var url = ctx + "/inner/salesLeads/deleteById?id=" + id +"&name=" + name;
		$.ajax({
			type: "get",
			url: url,
			success: function(result){
				if(result == 2){
					layer.alert("删除销售线索成功",{closeBtn: false,
				  		skin: 'layui-layer-molv'
					  }, function(){
						  parent.refreshTable();
						  parent.layer.closeAll();
					  });
				}else{
					layer.alert("删除销售线索失败",{closeBtn: false,
				  		skin: 'layui-layer-molv'
					  });
				}
			}
		});
	})
}
function getClueSource(clueSource){
    var typeStr = "";
    if(clueSource == 1){
        typeStr = "广告";
    }else if(clueSource == 2){
        typeStr = "研讨会/会议";
    }else if(clueSource == 3){
        typeStr = "电子邮件";
    }else if(clueSource == 4){
        typeStr = "电话营销";
    }else if(clueSource == 5){
        typeStr = "公共关系";
    }else if(clueSource == 6){
        typeStr = "合作伙伴";
    }else if(clueSource == 7){
        typeStr = "其它";
    }

    return typeStr;
}

