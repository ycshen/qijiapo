
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
	  layer.closeAll();
	  window.location.reload();
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
	layer.confirm("确定要删除销售机会【" + name + "】相关信息吗？",{closeBtn: false,
  		skin: 'layui-layer-molv'
	  }, function(){
		  var url = ctx + "/inner/salesopportunity/deleteById?id=" + id +"&name=" + name;
		$.ajax({
			type: "get",
			url: url,
			success: function(result){
				if(result == 2){
					layer.alert("删除销售机会成功",{closeBtn: false,
				  		skin: 'layui-layer-molv'
					  }, function(){
						  parent.refreshTable();
						  parent.layer.closeAll();
					  });
				}else{
					layer.alert("删除销售机会失败",{closeBtn: false,
				  		skin: 'layui-layer-molv'
					  });
				}
			}
		});
	})
}


function isNotBlank(args){
	var result = false;
	if(args != "" && args != null && args != undefined){
		result = true;
	}

	return result;
}


function viewCustomer(id, name){
	parent.layer.closeAll();
	parent.viewCustomerDetail(id, name);
}
function getAppendProduct(product, saleOppoId){
	var trHtml = "";
	trHtml +="<tr>";
	trHtml +="<td><a href='#' onclick=\"deleteSopById('"+ product.id + "', '" + product.productName + "', '" + saleOppoId + "');\"><img src=\""+ ctx+"/img/product/product_delete_normal.png\"/></a></td>";
	trHtml +="<td style=\"display:none;\">" + product.id + "</td>";
	trHtml +="<td>" + handleStr(product.productName) + "</td>";
	trHtml +="<td>" + handleStr(product.productPrice) + "</td>";
	trHtml +="<td>" + handleStr(product.salePrice) + "</td>";
	trHtml +="<td>" + handleStr(product.saleNum) + "</td>";
	trHtml +="<td>" + handleStr(product.discount) + "</td>";
	trHtml +="<td>" + handleStr(product.saleMoney) + "</td>";
	trHtml +="<td>企家婆</td>";
	trHtml +="<td>" + handleStr(product.remark) + "</td>";
	trHtml +="</tr>";
	return trHtml;
}

function  getTrTitle() {
	var trHtml = "";
	trHtml +="<tr>";
	trHtml +="<td>操作</td>";
	trHtml +="<td>产品名称</td>";
	trHtml +="<td>标准价格（元）</td>";
	trHtml +="<td>销售单价（元）</td>";
	trHtml +="<td>数量</td>";
	trHtml +="<td>折扣（%）</td>";
	trHtml +="<td>销售金额（元）</td>";
	trHtml +="<td>销售单位</td>";
	trHtml +="<td>备注</td>";
	trHtml +="</tr>";

	return trHtml;
}
function handleStr(str){
	if(str == null || str == "" || str == undefined){
		return "";
	}

	return str;
}


function addProduct(id){
	var url = ctx + "/inner/product/addProduct?saleOppoId=" + id;
	layer.open({
		type: 2,
		title: '销售机会-添加产品',
		shadeClose: false,
		shade: 0.8,
		area: ['80%', '100%'],
		content: url
	});
}

function deleteSopById(productId, productName, saleOppoId){
	layer.confirm("确定要删除销售机会对应的产品【" + productName + "】吗？",{closeBtn: false,
		skin: 'layui-layer-molv'
	}, function(){
		var url = ctx + "/inner/sop/deleteSopById?id=" + productId +"&saleOppoId=" + saleOppoId  +"&saleProductName=" + productName;
		$.ajax({
			type: "get",
			url: url,
			success: function(result){
				if(result == 1){
					layer.alert("删除产品成功",{closeBtn: false,
						skin: 'layui-layer-molv'
					}, function(){
						reloadProduct(saleOppoId);
						layer.closeAll();
					});
				}else{
					layer.alert("删除产品失败",{closeBtn: false,
						skin: 'layui-layer-molv'
					});
				}
			}
		});
	});
}

function reloadProduct(saleOppoId){
	//产品tab
	var index = layer.load(0, {
		shade: [0.5,'#b8c7ce']
	});
	var url = ctx + "/inner/sop/listProduct?saleOppoId=" +　saleOppoId;
	$.ajax({
		type: "get",
		url: url,
		dataType: "json",
		success: function(data){
			var trHtml = "";
			var totalPrice = 0;
			$.each(data, function(index, obj){
				var saleMoney = obj.saleMoney;
				totalPrice += parseFloat(saleMoney);
				totalPrice = parseFloat(totalPrice).toFixed(2);
				trHtml += getAppendProduct(obj, saleOppoId);
			});
			if(isNotBlank(trHtml)){
				var tableHtml = getTrTitle() + trHtml;
				$("#tableProduct").empty()
				$("#tableProduct").html(tableHtml);
				$("#spanTotalPrice").html(totalPrice);
			}
		},
		error: function(){
			layer.alert("加载失败",{closeBtn: false,
				skin: 'layui-layer-molv'
			});
		}
	});

	layer.closeAll('loading');
}