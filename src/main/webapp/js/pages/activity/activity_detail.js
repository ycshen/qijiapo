
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
function transfer(id, activityName){
	var url = ctx + "/inner/user/selectAllUserActivity?id=" + id + "&name=" + activityName;
	var title = "转移市场活动【" + activityName   +"】";
	layer.open({
		type: 2,
		title: title,
		shadeClose: true,
		shade: 0.8,
		area: ['500px', '400px'],
		content: url
	}); 
}

function fixActivity(){
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

function cancelActivity(){
	$("#btnEdit").show();
	$("#btnCancel").hide();
	$("#btnSave").hide();
}
function deleteById(id, name){
	layer.confirm("确定要删除市场活动【" + name + "】相关信息吗？",{closeBtn: false,
  		skin: 'layui-layer-molv'
	  }, function(){
		  var url = ctx + "/inner/activity/deleteById?id=" + id +"&name=" + name;
		$.ajax({
			type: "get",
			url: url,
			success: function(result){
				if(result == 2){
					layer.alert("删除市场活动成功",{closeBtn: false,
				  		skin: 'layui-layer-molv'
					  }, function(){
						  parent.refreshTable();
						  parent.layer.closeAll();
					  });
				}else{
					layer.alert("删除市场活动失败",{closeBtn: false,
				  		skin: 'layui-layer-molv'
					  });
				}
			}
		});
	})
}
function getActivityType(activityType){
    var typeStr = "";
    if(activityType == 1){
        typeStr = "广告";
    }else if(activityType == 2){
        typeStr = "研讨会/会议";
    }else if(activityType == 3){
        typeStr = "电子邮件";
    }else if(activityType == 4){
        typeStr = "电话营销";
    }else if(activityType == 5){
        typeStr = "公共关系";
    }else if(activityType == 6){
        typeStr = "合作伙伴";
    }else if(activityType == 7){
        typeStr = "其它";
    }

    return typeStr;
}

function getActivityState(activityState){
    var stateStr = "";
    if(activityState == 1){
        stateStr = "已计划";
    }else if(activityState == 2){
        stateStr = "进行中";
    }else if(activityState == 3){
        stateStr = "已结束";
    }else if(activityState == 4){
        stateStr = "中止";
    }

    return stateStr;
}
function getActivityBusinessType(businessType){
    var businessTypeStr = "";
    if(businessType == 0){
        businessTypeStr = "默认业务类型";
    }

    return businessTypeStr;
}
