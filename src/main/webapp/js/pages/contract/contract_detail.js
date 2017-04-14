
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
	var url = ctx + "/inner/user/selectAllUserContract?id=" + id + "&name=" + contractName;
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
var i=1;
function addReturnMoney(id){
	var numHtml = getReturnMoneyNum(id,0, 0, 0);
	$("#sectionDiv").html(numHtml);

}



function getReturnMoneyNum(id,planMoney, actualMoney, taxMoney){
	var numTitle = "";
    numTitle += "<div class=\"row\">";
    numTitle += "<div class=\"col-xs-12\">";
    numTitle += "<h2 class=\"page-box\">";
    numTitle += "第1期";

    numTitle += "</h2>";
    numTitle += "</div>";
    numTitle += "</div>";
    numTitle += "<div class=\"row invoice-info\"  id=\"rowDiv\">";
    numTitle += "<div class=\"col-sm-4 invoice-col\">";
    numTitle += "计划(元）:<span style=\"color: green;\">" + planMoney + "</span>   <a href=\"#\" title=\"添加回款计划\" onclick=\"addPlan(" + id + ")\"><i class=\"layui-icon\">&#xe61f;</i></a>";
    numTitle += "</div>";
    numTitle += "<div class=\"col-sm-4 invoice-col\">";
    numTitle += "实际(元）：<span style=\"color: green;\">" + actualMoney + "</span>  <a href=\"#\" title=\"添加回款记录\"><i class=\"layui-icon\">&#xe61f;</i></a>";
    numTitle += "</div>";
    numTitle += "<div class=\"col-sm-4 invoice-col\">";
    numTitle += "开票(元）：<span style=\"color: green;\">" + taxMoney + "</span>   <a href=\"#\" title=\"添加开票记录\"><i class=\"layui-icon\">&#xe61f;</i></a>";
    numTitle += "</div>";
    numTitle += "</div>";

    return numTitle;
}
/**
 *
 * @param type 1-计划 2-实际  3-开票
 * @param isNewNum  是否是新期次  0-不是  1-是
 * @returns {string}
 */
function appendDetail(type, isNewNum){
	var appendHtml = "";
    appendHtml += "<div class=\"row\">";
    appendHtml += "<div class=\"col-xs-12 table-responsive\">";
    appendHtml += " <table class=\"table\">";
    var planTr = getPlanTr();
    appendHtml += planTr;
    var actualTr = getActualTr();
    appendHtml += actualTr;
    var taxTr = getTaxTr();
    appendHtml += taxTr;
    appendHtml += " </table>";
    appendHtml += "   </div>";
    appendHtml += "  </div>";

        return appendHtml;
}
function getTaxTr(){
    var appendHtml = "";
    appendHtml += "<tr>";
    appendHtml += " <td><span class=\"label label-warning\">开票</span></td>";
    appendHtml += " <td>2017-08-08</td>";
    appendHtml += " <td>收款：100元</td>";
    appendHtml += "<td>已完成</td>";
    appendHtml += "<td>";
    appendHtml += "<div class=\"task_tool\">";
    appendHtml += " <a href=\"\" target=\"_blank\" title=\"查看\" style=\"float: left;margin-right: 10px;color: #aaa;\">";
    appendHtml += " <img src=\"" + ctx + "/img/contract/view.png\">";
    appendHtml += " </a>";
    appendHtml += " <a title=\"编辑\" class=\"edit\"  postdata=\"{&quot;planId&quot;:156536}\" >";
    appendHtml += " <img src=\"" + ctx + "/img/contract/edit_pen.png\">";
    appendHtml += " </a>";
    appendHtml += " <a title=\"删除\" business=\"paymentplan\" class=\"delete\" href=\"javascript:;\">";
    appendHtml += " <img src=\"" + ctx + "/img/contract/delete_gray.png\">";
    appendHtml += " </a>";
    appendHtml += "</div>";
    appendHtml += " </td>";
    appendHtml += " </tr>";

    return appendHtml;
}
function getActualTr(){
    var appendHtml = "";
    appendHtml += " <tr>";
    appendHtml += " <td><span class=\"label label-info\">实际</span></td>";
    appendHtml += " <td>2017-08-08</td>";
    appendHtml += " <td>收款：100元</td>";
    appendHtml += "<td>已完成</td>";
    appendHtml += "<td>";
    appendHtml += "<div class=\"task_tool\">";
    appendHtml += "<a href=\"\" target=\"_blank\" title=\"查看\" style=\"float: left;margin-right: 10px;color: #aaa;\">";
    appendHtml += "<img src=\"" + ctx + "/img/contract/view.png\">";
    appendHtml += " </a>";
    appendHtml += "<a title=\"编辑\" class=\"edit\"  postdata=\"{&quot;planId&quot;:156536}\" >";
    appendHtml += "<img src=\"" + ctx + "/img/contract/edit_pen.png\">";
    appendHtml += "</a>";
    appendHtml += " <a title=\"删除\" business=\"paymentplan\" class=\"delete\" href=\"javascript:;\">";
    appendHtml += " <img src=\"" + ctx + "/img/contract/delete_gray.png\">";
    appendHtml += " </a>";
    appendHtml += " </div>";
    appendHtml += " </td>";
    appendHtml += " </tr>";

    return appendHtml;
}
function getPlanTr(){
    var appendHtml = "";
    appendHtml += " <tr>";
    appendHtml += "<td>";
    appendHtml += " <span class=\"label label-success\">计划</span>";
    appendHtml += " </td>";
    appendHtml += "<td>2017-08-08</td>";
    appendHtml += " <td>收款：100元</td>";
    appendHtml += " <td>已完成</td>";
    appendHtml += "<td>";
    appendHtml += "<div class=\"task_tool\">";
    appendHtml += " <a href=\"\" target=\"_blank\" title=\"查看\" style=\"float: left;margin-right: 10px;color: #aaa;\">";
    appendHtml += "<img src=\"" + ctx + "/img/contract/view.png\">";
    appendHtml += " </a>";
    appendHtml += " <a title=\"编辑\" class=\"edit\"  postdata=\"{&quot;planId&quot;:156536}\" >";
    appendHtml += " <img src=\"" + ctx + "/img/contract/edit_pen.png\">";
    appendHtml += " </a>";
    appendHtml += " <a title=\"删除\" business=\"paymentplan\" class=\"delete\" href=\"javascript:;\">";
    appendHtml += " <img src=\"" + ctx + "/img/contract/delete_gray.png\">";
    appendHtml += " </a>";
    appendHtml += " </div>";
    appendHtml += "</td>";
    appendHtml += " </tr>";

    return appendHtml;
}

function addPlanSuccess(obj){
	//获取回款其次
	var returnMoney = new Object();
	returnMoney.returnMoneyDetail = obj;
    returnMoney.returnMoneyNum = 1;
    returnMoney.contractId = 21;


	//把obj封装回款其次
    var url = ctx + "/inner/returnMoney/save"

    var jsonData = JSON.stringify(returnMoney);
    $.ajax({
        cache: true,
        type: "POST",
        url: url,
        data: jsonData,
        contentType: "application/json",
        async: false,
        success: function (returnMoney) {
            layer.alert('保存成功',
                {
                    closeBtn: false,
                    skin: 'layui-layer-molv'
                },
                function (index) {
                    layer.closeAll();
                    var detailHtml = appendDetail();
                    $("#rowDiv").append(detailHtml);
                });
        }
    });
    return false;
}



function addPlan(id){
    var url = ctx + "/inner/returnMoneyDetail/addPlan?id=" + id;
    layer.open({
        type: 2,
        title: "新建回款计划",
        shadeClose: false,
        shade: 0.8,
        area: ['80%', '80%'],
        content: url
    });
     /*var url = ctx + "/inner/returnMoneyDetail/forwardEdit?contractId=" + id;
     layer.open({
         type: 2,
         title: "新建回款计划",
         shadeClose: false,
         shade: 0.8,
         area: ['500px', '400px'],
         content: url
     });*/
}