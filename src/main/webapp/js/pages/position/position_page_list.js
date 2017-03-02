var index = parent.layer.getFrameIndex(window.name);
function addPosition(){
	var url = ctx + "/inner/position/edit";
	layer.open({
		type: 2,
		title: '添加职位信息',
		shadeClose: true,
		shade: 0.8,
		area: ['550px', '200px'],
		content: url
	});
}

function queryPosition(){
	var positionName = $("#txtpositionName").val();
	var url = ctx + "/inner/position/page";
	if(isNotBlank(positionName)){
		url += "?postionName=" + positionName;
		
	}
	
	$.ajax({
		type: "get",
		url: url,
		success: function(query){
			var count = query.count;
			var data = query.items;
			showTable(data);
			$(".pagination").pagination(count, {
			    num_edge_entries: 5,
			    num_display_entries: 10,
			    callback: pageselectCallback,
			    items_per_page:10,
			    maxentries: count,
			    prev_text: "上一页",
			    next_text: "下一页"
			}); 
		}
	});
	
}
function editPosition(id){
	var url = ctx + "/inner/position/edit?id=" + id;
	layer.open({
		type: 2,
		title: '编辑职位信息',
		shadeClose: true,
		shade: 0.8,
		area: ['550px', '200px'],
		content: url
	});
}

		
function addSubCompany(id){
	var url = ctx + "/inner/company/addSub?id=" + id;
	layer.open({
		type: 2,
		title: '添加子公司信息',
		shadeClose: true,
		shade: 0.8,
		area: ['550px', '400px'],
		content: url
	});
}
function addSuccess(){
	window.location.href = ctx + "/inner/position/listInit";
}

function updateSuccess(id, positionName){
	layer.closeAll();
	$("#positionName" + id).html(positionName);
}
function queryCompany(page){
	var url = ctx + "/inner/company/list?page=" + page;
	var level = $("#levelSelect").find("option:selected").val();
	if(isNotBlank(level)){
		url += "&level=" + level;
	}
	
	
	
	var companyName = $("#txtCompanyName").val();
	if(isNotBlank(companyName)){
		url += "&companyName=" + companyName;
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

function stopPosition(id, positionName){
	layer.confirm("确定要停用职位【" + positionName + "】吗？",{closeBtn: false,
  		skin: 'layui-layer-molv'
	  }, function(){
		  var url = ctx + "/inner/position/stop?id=" + id + "&positionName=" + positionName;
		$.ajax({
			type: "get",
			url: url,
			success: function(result){
				if(result == 2){
					layer.alert("停用成功",{closeBtn: false,
				  		skin: 'layui-layer-molv'
					  }, function(){
						  setStop(id, positionName);
						  layer.closeAll();
					  });
				}else{
					layer.alert("停用失败",{closeBtn: false,
				  		skin: 'layui-layer-molv'
					  });
				}
			}
		});
	})
}

function setStop(id, positionName){
	$("#positionStatus" + id).html("<span class=\"label label-warning\">停用</span>");
	var oper = "<div class=\"btn-group\">";
	oper +="<button type=\"button\" class=\"btn btn-default dropdown-toggle\" data-toggle=\"dropdown\">";
	oper +=" <span class=\"caret\"></span>";
	oper +="</button>";
	oper +="<ul class=\"dropdown-menu\">";
	oper +="<li><a href=\"#\" onclick=\"startPosition('" + id + "', '" + positionName+ "')\">启用</a></li>";
	oper +="</ul>";
	oper +="</div>";
	$("#positionOper" + id).html(oper);
	
	
}

function setStart(id, positionName){
	$("#positionStatus" + id).html("<span class=\"label label-success\">正常</span>");
	var oper = "<div class=\"btn-group\">";
	oper +="<button type=\"button\" class=\"btn btn-default dropdown-toggle\" data-toggle=\"dropdown\">";
	oper +=" <span class=\"caret\"></span>";
	oper +="</button>";
	oper +="<ul class=\"dropdown-menu\">";
	oper +="<li><a href=\"#\" onclick=\"editPosition('" + id + "');\">编辑</a></li>";
	oper +="<li><a href=\"#\" onclick=\"stopPosition('" + id + "', '" + positionName+ "')\">停用</a></li>";
	oper +="</ul>";
	oper +="</div>";
	$("#positionOper" + id).html(oper);
}

function startPosition(id, positionName){
	layer.confirm("确定要启用职位【" + positionName + "】吗？",{closeBtn: false,
  		skin: 'layui-layer-molv'
	  }, function(){
		  var url = ctx + "/inner/position/start?id=" + id + "&positionName=" + positionName;
			$.ajax({
				type: "get",
				url: url,
				success: function(result){
					if(result == 2){
						layer.alert("启用成功",{closeBtn: false,
					  		skin: 'layui-layer-molv'
						  }, function(){
							  setStart(id, positionName);
							  layer.closeAll();
						  });
					}else{
						layer.alert("启用失败",{closeBtn: false,
					  		skin: 'layui-layer-molv'
						  });
					}
				}
			});
	})
}
