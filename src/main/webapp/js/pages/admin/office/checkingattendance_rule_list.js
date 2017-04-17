
function addCheckingAttendance(){
	var url = ctx + "/admin/checkAttendance/add";
	layer.open({
		type: 2,
		title: '添加考勤点信息',
		shadeClose: false,
		shade: 0.8,
		area: ['60%', '100%'],
		content: url
	});
}

function isNotBlank(args){
	var result = false;
	if(args != "" && args != null && args != undefined){
		result = true;
	}

	return result;
}




function editAttendance(id){
	var url = ctx + "/admin/checkAttendance/forwardEdit?id=" + id;
	layer.open({
		type: 2,
		title: '编辑考勤点',
		shadeClose: true,
		shade: 0.8,
		area: ['60%', '100%'],
		content: url
	});
}

$(function(){
	initDataTable();
});


var CONSTANT = {
	// datatables常量
	DATA_TABLES : {
		DEFAULT_OPTION : { // DataTables初始化选项
			LANGUAGE : {
				sProcessing : "数据加载中...",
				sLengthMenu : "显示 _MENU_ 项结果",
				sZeroRecords : "没有匹配结果",
				sInfo : "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
				sInfoEmpty : "显示第 0 至 0 项结果，共 0 项",
				sInfoFiltered : "(由 _MAX_ 项结果过滤)",
				sInfoPostFix : "",
				sSearch : "本页搜索:",
				sUrl : "",
				sEmptyTable : "抱歉，没有查询到数据",
				sLoadingRecords : "载入中...",
				sInfoThousands : ",",
				oPaginate : {
					sFirst : "首页",
					sPrevious : "上页",
					sNext : "下页",
					sLast : "末页"
				},
				"oAria" : {
					"sSortAscending" : ": 以升序排列此列",
					"sSortDescending" : ": 以降序排列此列"
				}
			},
			// 禁用自动调整列宽
			autoWidth : false,
			// 为奇偶行加上样式，兼容不支持CSS伪类的场合
			stripeClasses : [ "odd", "even" ],
			// 取消默认排序查询,否则复选框一列会出现小箭头
			order : [],
			// 隐藏加载提示,自行处理
			processing : false,
			// 启用服务器端分页
			serverSide : true,
			// 禁用原生搜索
			searching : false
		},
		COLUMN : {
			// 复选框单元格
			CHECKBOX : {
				className: "td-checkbox",
				orderable : false,
				bSortable : false,
				data : "id",
				render : function(data, type, row, meta) {
					var content = '<input type="checkbox"  value="' + data + '" />';

					return content;
				}
			}
		},
		// 常用render可以抽取出来，如日期时间、头像等
		RENDER : {
			ELLIPSIS : function(data, type, row, meta) {
				data = data || "";
				return '<span title="' + data + '">' + data + '</span>';
			}
		}
	}


};
function refreshTable(){
	var table = $('#myDataTable').dataTable();
	table.fnDraw(false);
}

function deleteById(id, name){
	layer.confirm("确定要删除考勤点相关信息吗？",{closeBtn: false,
		skin: 'layui-layer-molv'
	}, function(){
		var url = ctx + "/admin/checkAttendance/deleteById?id=" + id +"&name=" + name;
		$.ajax({
			type: "get",
			url: url,
			success: function(result){
				if(result == 2){
					layer.alert("删除考勤点成功",{closeBtn: false,
						skin: 'layui-layer-molv'
					}, function(){
						refreshTable();
						layer.closeAll();
					});
				}else{
					layer.alert("删除考勤点失败",{closeBtn: false,
						skin: 'layui-layer-molv'
					});
				}
			}
		});
	})
}


function initDataTable(){
	$("#myDataTable").dataTable( {
		ajax : {
			type: "GET",
			url: ctx + '/admin/checkAttendance/listAjax',
			// 传入已封装的参数
			data: function(data){
				data.page = data.start / data.length + 1;
				data.size = data.length;
				// 右上角搜索
				delete data.columns;
			},
			dataType: "json",
			dataSrc : function(result) {
				// 后台不实现过滤功能，每次查询均视作全部结果
				result.recordsTotal = result.count;
				result.recordsFiltered = result.count || 0;
				result.data = result.items || {};
				delete result.count;
				delete result.items;
				return result.data;
			}
		},
		"columns": [
			{
				data : 'operate',
				bSortable : false,
				visible : true,
				render : function(data, type, attendance) {

					var operResult = "<div class=\"btn-group\">";
					operResult += "<button type=\"button\" class=\"btn btn-default dropdown-toggle\" data-toggle=\"dropdown\">";
					operResult +=  "<span class=\"caret\"></span>";
					operResult +=  "</button>";
					operResult += " <ul class=\"dropdown-menu\">";
					var state = attendance.state;
					if(state == 0){
						operResult += 	" <li><a href=\"#\" onclick=\"changeStatus('" + attendance.id + "', '0','" + attendance.placeName + "');\">停用</a></li>";
					}else{
						operResult += 	" <li><a href=\"#\" onclick=\"changeStatus('" + attendance.id + "', '1','" + attendance.placeName + "');\">启用</a></li>";
					}

					operResult += 	" <li><a href=\"#\" onclick=\"deleteById('" + attendance.id + "','" + attendance.placeName + "');\">删除</a></li>";
					operResult += 	" <li><a href=\"#\" onclick=\"editAttendance('" + attendance.id + "');\">编辑</a></li>";

					operResult += " </ul>";
					operResult += " </div>";

					return operResult;
				}
			},
			{ data: 'placeName'
			},
			{ data: 'address' },
			{ data: 'workTime' },
			{ data: 'onWorkTime' },
			{ data: 'offWorkTime' },
			{ data: 'departmentName' },
			{ data: 'errorRange' },
			{ data: 'state' ,
				render : function(data, type, attendance) {

					var stateStr = "";
					var state = attendance.state;
					if(state == 0){
						operResult = "<span class=\"label label-success\">启用</span>";
					}else{
						operResult = "<span class=\"label label-warning\">停用</span>";
					}

					return operResult;
				}}
		],
		"bProcessing" : true, //DataTables载入数据时，是否显示‘进度’提示
		"bPaginate": true,
		"serverSide": true,
		"searching" : false,
		"bFilter": true,
		"sPaginationType": "full_numbers",
		"paging": true,
		"lengthChange": true,
		"info": true,
		"autoWidth": true,
		"bSort": false,
		"oLanguage": CONSTANT.DATA_TABLES.DEFAULT_OPTION.LANGUAGE

	} );
}

function changeStatus(id, state, name){
	var title = "启用";
	if(state == 0){
		title = "停用";
	}

	layer.confirm("确定要" + title + "考勤点吗？",{closeBtn: false,
		skin: 'layui-layer-molv'
	}, function(){
		var url = ctx + "/admin/checkAttendance/enableOrDisableById?id=" + id +"&name=" + name;
		$.ajax({
			type: "get",
			url: url,
			success: function(result){
				if(result == 2){
					layer.alert( title + "考勤点成功",{closeBtn: false,
						skin: 'layui-layer-molv'
					}, function(){
						refreshTable();
						layer.closeAll();
					});
				}else{
					layer.alert(title + "考勤点失败",{closeBtn: false,
						skin: 'layui-layer-molv'
					});
				}
			}
		});
	})
}
Date.prototype.Format = function (fmt) { //author: meizz
	var o = {
		"M+": this.getMonth() + 1, //月份
		"d+": this.getDate(), //日
		"h+": this.getHours(), //小时
		"m+": this.getMinutes(), //分
		"s+": this.getSeconds(), //秒
		"q+": Math.floor((this.getMonth() + 3) / 3), //季度
		"S": this.getMilliseconds() //毫秒
	};
	if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	for (var k in o)
		if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}