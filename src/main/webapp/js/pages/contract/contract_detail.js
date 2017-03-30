
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
function transfer(id, contractName){
	var url = ctx + "/inner/user/selectAllUser?id=" + id + "&name=" + contractName;
	var title = "转移合同【" + contractName   +"】";
	layer.open({
		type: 2,
		title: title,
		shadeClose: false,
		shade: 0.8,
		area: ['500px', '400px'],
		content: url
	}); 
}

function fixContract(){
	$("#btnEdit").hide();
	$("#btnCancel").show();
	$("#btnSave").show();
	$("#editTable").show();
	$("#detailTable").hide();
}

function saveContract(){
	/*$("#btnEdit").hide();
	$("#btnCancel").show();
	$("#btnSave").show();*/
}

function cancelContract(){
	$("#btnEdit").show();
	$("#btnCancel").hide();
	$("#btnSave").hide();
}
function deleteById(id, name){
	layer.confirm("确定要删除合同【" + name + "】相关信息吗？",{closeBtn: false,
  		skin: 'layui-layer-molv'
	  }, function(){
		  var url = ctx + "/inner/contract/deleteById?id=" + id +"&name=" + name;
		$.ajax({
			type: "get",
			url: url,
			success: function(result){
				if(result == 2){
					layer.alert("删除合同成功",{closeBtn: false,
				  		skin: 'layui-layer-molv'
					  }, function(){
						  parent.refreshTable();
						  parent.layer.closeAll();
					  });
				}else{
					layer.alert("删除合同失败",{closeBtn: false,
				  		skin: 'layui-layer-molv'
					  });
				}
			}
		});
	})
}
function getContractType(contractType){
    var typeStr = "";
    if(contractType == 1){
        typeStr = "广告";
    }else if(contractType == 2){
        typeStr = "研讨会/会议";
    }else if(contractType == 3){
        typeStr = "电子邮件";
    }else if(contractType == 4){
        typeStr = "电话营销";
    }else if(contractType == 5){
        typeStr = "公共关系";
    }else if(contractType == 6){
        typeStr = "合作伙伴";
    }else if(contractType == 7){
        typeStr = "其它";
    }

    return typeStr;
}

function getContractState(contractState){
    var stateStr = "";
    if(contractState == 1){
        stateStr = "已计划";
    }else if(contractState == 2){
        stateStr = "进行中";
    }else if(contractState == 3){
        stateStr = "已结束";
    }else if(contractState == 4){
        stateStr = "中止";
    }

    return stateStr;
}
function getContractBusinessType(businessType){
    var businessTypeStr = "";
    if(businessType == 0){
        businessTypeStr = "默认业务类型";
    }

    return businessTypeStr;
}

function viewCustomer(id, name){
	parent.layer.closeAll();
	parent.viewCustomerDetail(id, name);
}

function addReturnMoney(){
	var url = ctx + "/inner/returnMoney/forwardEdit";
	layer.open({
		type: 2,
		title: '新增回款期次',
		shadeClose: false,
		shade: 0.8,
		area: ['830px', '450px'],
		content: url
	});
}