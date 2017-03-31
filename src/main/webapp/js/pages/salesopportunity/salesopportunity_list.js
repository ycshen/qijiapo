function addSalesOpportunity(){
	var url = ctx + "/inner/salesOpportunity/forwardEdit";
	layer.open({
		type: 2,
		title: '新增销售机会',
		shadeClose: false,
		shade: 0.8,
		area: ['830px', '450px'],
		content: url
	});
}


function editSalesOpportunity(id){
	var url = ctx + "/inner/salesOpportunity/forwardEdit?id=" + id;
	layer.open({
		type: 2,
		title: '编辑销售机会',
		shadeClose: false,
		shade: 0.8,
		area: ['800px', '400px'],
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
	window.location.href = ctx + "/inner/company/list";
}


function isNotBlank(args){
	var result = false;
	if(args != "" && args != null && args != undefined){
		result = true;
	}
	
	return result;
}

function addDepartment(id){
	var url = ctx + "/inner/department/add?id=" + id;
	layer.open({
		type: 2,
		title: '添加部门信息',
		shadeClose: true,
		shade: 0.8,
		area: ['550px', '400px'],
		content: url
	});
}

function viewDetail(id, salesOpportunityName){
	var url = ctx + "/inner/salesOpportunity/detail?id=" + id;
	var title = "销售机会【" + salesOpportunityName   +"】";
	layer.open({
		type: 2,
		title: title,
		shadeClose: false,
		shade: 0.8,
		area: ['80%', '100%'],
		content: url
	}); 
}

function transfer(id, salesOpportunityName){
	var url = ctx + "/inner/user/selectAllUser?id=" + id + "&name=" + salesOpportunityName;
	var title = "转移销售机会【" + salesOpportunityName   +"】";
	layer.open({
		type: 2,
		title: title,
		shadeClose: true,
		shade: 0.8,
		area: ['500px', '400px'],
		content: url
	}); 
}

$(function(){
	/*$(".select2").select2();
*/
	
	initDataTable();
	initLocation();
	initAllCheckEvt();
});


function initAllCheckEvt(){
	$("#chkAll").change(function(){
		if($("#chkAll").is(':checked')) {
		    $("input:checkbox").each(function () {     
	            $(this).prop('checked', true);//  
	  
	        });  
		   
		}else{
		    $("input:checkbox").removeAttr("checked");  
		}
	});
	
	 $("input[type='checkbox']").on("change", function(){
		 var idValue = $(this).attr("id");
		if(idValue != "chkAll"){
			if($("#chkAll").is(':checked')){
				var chkArray = $("input:checkbox");
				var isCheckedAll = true;
				var size = chkArray.length;
				for(var i=0;i < size;i++ ){
					var isChkAll = chkArray[i].id;
					if( isChkAll  != "chkAll" && chkArray[i].checked){
						isCheckedAll = false;
						break;
					}
				}
				
				if(!isCheckedAll){
					$("#chkAll").removeAttr("checked");  
				}
			}
		}
	 })
}



function getCheckedBox(){
	var chkArr = $("input:checkbox");
	var chkedStr = "";
	$.each(chkArr, function(index, obj){
		var attrId = $(obj).attr("id");
		if(attrId != "chkAll"){
			if($(obj).is(':checked')){
				chkedStr += $(obj).val() + ",";
			}
		}
	});
	
	if(isNotBlank(chkedStr)){
		chkedStr = chkedStr.substring(0, chkedStr.length - 1);
	}
	
	return chkedStr;
}

function batchDelete(){
	var deleteIdArr = getCheckedBox();
	if(isNotBlank(deleteIdArr)){
		layer.confirm("确定要批量删除销售机会相关信息吗？",{closeBtn: false,
	  		skin: 'layui-layer-molv'
		  }, function(){
			  var url = ctx + "/inner/salesOpportunity/batchDeleteById?ids=" + deleteIdArr;
			$.ajax({
				type: "get",
				url: url,
				success: function(result){
					if(result == 2){
						layer.alert("批量删除销售机会成功",{closeBtn: false,
					  		skin: 'layui-layer-molv'
						  }, function(){
							  refreshTable();
							  layer.closeAll();
						  });
					}else{
						layer.alert("批量删除销售机会失败",{closeBtn: false,
					  		skin: 'layui-layer-molv'
						  });
					}
				}
			});
		})
	}else{
		layer.alert("请选择需要批量删除的销售机会",{closeBtn: false,
	  		skin: 'layui-layer-molv'
		  });
	}
}

function batchTransfer(){
	var idArr = getCheckedBox();
	if(isNotBlank(idArr)){
		layer.confirm("确定要批量转移销售机会相关信息吗？",{closeBtn: false,
	  		skin: 'layui-layer-molv'
		  }, function(){
			  var url = ctx + "/inner/user/selectAllUser?id=" + idArr;
				var title = "批量转移销售机会";
				layer.open({
					type: 2,
					title: title,
					shadeClose: true,
					shade: 0.8,
					area: ['500px', '400px'],
					content: url
				}); 
		})
	}else{
		layer.alert("请选择需要批量转移的销售机会",{closeBtn: false,
	  		skin: 'layui-layer-molv'
		  });
	}
}

function renderSalesOpportunityName(id, name){
	var str = "<a href=\"#\" onclick=\"viewDetail('" + id + "', '" + name + "');\" style=\"color:#009688;\">" + name + "</a>";
	return str;
}

function getArea(data){
	var area = "";
	if(isNotBlank(data.provinceName)){
		area += data.provinceName;
	}
	
	if(isNotBlank(data.cityName)){
		area += "-" + data.cityName;
	}
	
	if(isNotBlank(data.areaName)){
		area += "-" + data.areaName;
	}
	
	return area;
}

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
	layer.confirm("确定要删除销售机会【" + name + "】相关信息吗？",{closeBtn: false,
  		skin: 'layui-layer-molv'
	  }, function(){
		  var url = ctx + "/inner/salesOpportunity/deleteById?id=" + id +"&name=" + name;
		$.ajax({
			type: "get",
			url: url,
			success: function(result){
				if(result == 2){
					layer.alert("删除销售机会成功",{closeBtn: false,
				  		skin: 'layui-layer-molv'
					  }, function(){
						  refreshTable();
						  layer.closeAll();
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

function deleteById(id, name){
	layer.confirm("确定要删除销售机会【" + name + "】相关信息吗？",{closeBtn: false,
  		skin: 'layui-layer-molv'
	  }, function(){
		  var url = ctx + "/inner/salesOpportunity/deleteById?id=" + id +"&name=" + name;
		$.ajax({
			type: "get",
			url: url,
			success: function(result){
				if(result == 2){
					layer.alert("删除销售机会成功",{closeBtn: false,
				  		skin: 'layui-layer-molv'
					  }, function(){
						  refreshTable();
						  layer.closeAll();
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

function querySalesOpportunity(){
	$("#myDataTable").dataTable().fnDestroy(); 
	var salesOpportunityName = $("#txtSalesOpportunityName").val();
	$("#myDataTable").dataTable( {
	       ajax : {  
	            type: "GET",  
	            url: ctx + '/inner/salesOpportunity/listAjax',  
	            // 传入已封装的参数  
	            data: function(data){ 
	            	data.salesOpportunityName = salesOpportunityName;
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
	   	             CONSTANT.DATA_TABLES.COLUMN.CHECKBOX, 
	   	             {  
	   	                 data : 'operate',  
	   	                 bSortable : false,  
	   	                 visible : true,  
	   	                 render : function(data, type, salesOpportunity) {  
	   	                       
	   	                	 var operResult = "<div class=\"btn-group\">";
	   	                	 operResult += "<button type=\"button\" class=\"btn btn-default dropdown-toggle\" data-toggle=\"dropdown\">";
	   	                	 operResult +=  "<span class=\"caret\"></span>";
	   	                	 operResult +=  "</button>";
	   	                	 operResult += " <ul class=\"dropdown-menu\">";
	   	                          
	   	                	 operResult += 	" <li><a href=\"#\" onclick=\"deleteById('" + salesOpportunity.id + "', '" + salesOpportunity.salesOpportunityName + "');\">删除</a></li>";
	   	                	 operResult += 	" <li><a href=\"#\" onclick=\"editSalesOpportunity('" + salesOpportunity.id + "');\">编辑</a></li>";
	   	                	 operResult += 	" <li><a href=\"#\" onclick=\"addProduct('" + salesOpportunity.id + "');\">添加产品</a></li>";
	   	                	 operResult += " </ul>";
	   	                	 operResult += " </div>";
	   	                     
	   	                     return operResult;  
	   	                 }   
	   	             },
	   	             { data: 'salesOpportunityName',
	   	            	render: function(data, type, salesOpportunity){
	   	            		return renderSalesOpportunityName(salesOpportunity.id, salesOpportunity.salesOpportunityName);
	   	            	}
	   	             },
	   	             { data: 'price' },
	   	             { data: 'status',
		   	            	render: function(data, type, salesOpportunity){
		   	            		return getStatus(salesOpportunity.status);
		   	            	}
		   	         },
	   	             /*{ data: 'imgPath' },*/
	   	             { data: 'createTime' }
	   	         ],
	       "bProcessing" : true, //DataTables载入数据时，是否显示‘进度’提示  
	       "bPaginate": true,
	       "serverSide": true, 
           "searching" : true,
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

function getStatus(status){
	var statusStr = "";
	if(status == 1){
		statusStr = "<span class=\"label label-success\">启用</span>";
	}else if(status == 0){
		statusStr = "<span class=\"label label-success\">停用</span>";
	}

	return statusStr;
}


function initDataTable(){
	$("#myDataTable").dataTable( {
	       ajax : {  
	            type: "GET",  
	            url: ctx + '/inner/salesOpportunity/listAjax',  
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
	             CONSTANT.DATA_TABLES.COLUMN.CHECKBOX, 
	             {  
	                 data : 'operate',  
	                 bSortable : false,  
	                 visible : true,  
	                 render : function(data, type, salesOpportunity) {  
	                       
	                	 var operResult = "<div class=\"btn-group\">";
	                	 operResult += "<button type=\"button\" class=\"btn btn-default dropdown-toggle\" data-toggle=\"dropdown\">";
	                	 operResult +=  "<span class=\"caret\"></span>";
	                	 operResult +=  "</button>";
	                	 operResult += " <ul class=\"dropdown-menu\">";
	                          
	                	 operResult += 	" <li><a href=\"#\" onclick=\"deleteById('" + salesOpportunity.id + "', '" + salesOpportunity.salesOpportunityName + "');\">删除</a></li>";
	                	 operResult += 	" <li><a href=\"#\" onclick=\"editSalesOpportunity('" + salesOpportunity.id + "');\">编辑</a></li>";
	                	 operResult += 	" <li><a href=\"#\" onclick=\"addProduct('" + salesOpportunity.id + "');\">添加产品</a></li>";    	
	                	 operResult += " </ul>";
	                	 operResult += " </div>";
	                     
	                     return operResult;  
	                 }   
	             },
	             { data: 'salesOpportunityName',
	            	render: function(data, type, salesOpportunity){
	            		return renderSalesOpportunityName(salesOpportunity.id, salesOpportunity.salesOpportunityName);
	            	}
	             },
				{ data: 'saleStage',
					render: function(data, type, salesOpportunity){
						return renderSaleStage(salesOpportunity.saleStage);
					}
				},
	             { data: 'companyName' },
	             { data: 'customerName' },
	             { data: 'oppotunityType',
					 render: function(data, type, salesOpportunity){
						 return renderOppotunityType(salesOpportunity.oppotunityType);
					 } },
	             { data: 'saleMoney'},
	             { data: 'endOppoTime' },
	             { data: 'winRate' },
	             { data: 'createTime' }
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
function renderSaleStage(value){
		if(value == 1){
			return "初步接洽";
		}else if(value == 2){
			return "需求确定";
		}else if(value == 3){
			return "方案/报价";
		}else if(value == 4){
			return "谈判审核";
		}else if(value == 5){
		return "赢单";
		}else if(value == 6){
		return "输单";
		}

		return "";
}

function renderOppotunityType(value){
	if(value == 2){
		return "老客户机会";
	}else if(value == 1){
		return "新客户机会";
	}

	return "";
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

function viewCustomerDetail(id, customerName){
	var url = ctx + "/inner/customer/detail?id=" + id;
	var title = "客户【" + customerName   +"】";
	layer.open({
		type: 2,
		title: title,
		shadeClose: false,
		shade: 0.8,
		area: ['80%', '100%'],
		content: url
	});
}
