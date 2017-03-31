function addProduct(){
	var url = ctx + "/inner/product/forwardEdit";
	layer.open({
		type: 2,
		title: '新增产品',
		shadeClose: false,
		shade: 0.8,
		area: ['800px', '500px'],
		content: url
	});
}


function editProduct(id){
	var url = ctx + "/inner/product/forwardEdit?id=" + id;
	layer.open({
		type: 2,
		title: '编辑产品',
		shadeClose: false,
		shade: 0.8,
		area: ['800px', '500px'],
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

function viewDetail(id, productName){
	var url = ctx + "/inner/product/detail?id=" + id;
	var title = "产品【" + productName   +"】";
	layer.open({
		type: 2,
		title: title,
		shadeClose: true,
		shade: 0.8,
		area: ['80%', '80%'],
		content: url
	}); 
}

function transfer(id, productName){
	var url = ctx + "/inner/user/selectAllUser?id=" + id + "&name=" + productName;
	var title = "转移产品【" + productName   +"】";
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
	initDataTable();
});



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


function renderProductName(id, name){
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
	layer.confirm("确定要删除产品【" + name + "】相关信息吗？",{closeBtn: false,
  		skin: 'layui-layer-molv'
	  }, function(){
		  var url = ctx + "/inner/product/deleteById?id=" + id +"&name=" + name;
		$.ajax({
			type: "get",
			url: url,
			success: function(result){
				if(result == 2){
					layer.alert("删除产品成功",{closeBtn: false,
				  		skin: 'layui-layer-molv'
					  }, function(){
						  refreshTable();
						  layer.closeAll();
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

function deleteById(id, name){
	layer.confirm("确定要删除产品【" + name + "】相关信息吗？",{closeBtn: false,
  		skin: 'layui-layer-molv'
	  }, function(){
		  var url = ctx + "/inner/product/deleteById?id=" + id +"&name=" + name;
		$.ajax({
			type: "get",
			url: url,
			success: function(result){
				if(result == 2){
					layer.alert("删除产品成功",{closeBtn: false,
				  		skin: 'layui-layer-molv'
					  }, function(){
						  refreshTable();
						  layer.closeAll();
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

function queryProduct(){
	$("#myDataTable").dataTable().fnDestroy(); 
	var productName = $("#txtProductName").val();
	$("#myDataTable").dataTable( {
	       ajax : {  
	            type: "GET",  
	            url: ctx + '/inner/product/listAjax',  
	            // 传入已封装的参数  
	            data: function(data){ 
	            	data.productName = productName;
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
				    {data : "id",
						render : function(data, type, product) {
							var content = "<input type=\"checkbox\"  value='"+ product.id + "' />" +
								"<input type=\"hidden\" id='hidProductName" + product.id + "' value='"+ product.productName + "' />" +
								"<input type=\"hidden\" id='hidPrice" + product.id + "' value='"+ product.price + "' />";

							return content;
						}
					},
					{ data: 'productName',
	   	            	render: function(data, type, product){
	   	            		return renderProductName(product.id, product.productName);
	   	            	}
	   	             },
	   	             { data: 'price' },
	   	             { data: 'status',
		   	            	render: function(data, type, product){
		   	            		return getStatus(product.status);
		   	            	}
		   	         },
	   	             /*{ data: 'imgPath' },*/
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

function getStatus(status){
	var statusStr = "";
	if(status == 1){
		statusStr = "<span class=\"label label-success\">启用</span>";
	}else if(status == 2){
		statusStr = "<span class=\"label label-success\">停用</span>";
	}

	return statusStr;
}


function initDataTable(){
	$("#myDataTable").dataTable( {
	       ajax : {  
	            type: "GET",  
	            url: ctx + '/inner/product/listAjax',  
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
				{data : "id",
					render : function(data, type, product) {
						var content = "<input type=\"checkbox\"  value='"+ product.id + "' />" +
							"<input type=\"hidden\" id='hidProductName" + product.id + "' value='"+ product.productName + "' />" +
							"<input type=\"hidden\" id='hidPrice" + product.id + "' value='"+ product.price + "' />";

						return content;
					}
				},
	             { data: 'productName',
	            	render: function(data, type, product){
	            		return renderProductName(product.id, product.productName);
	            	}
	             },
	             { data: 'price' },
	             { data: 'status',
	   	            	render: function(data, type, product){
	   	            		return getStatus(product.status);
	   	            	}
	   	         },
	             
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

function confirmSelect(){
	var id = getCheckedBoxValue();
	if(isBlank(id)){
		layer.alert("请选择产品",{closeBtn: false,
			skin: 'layui-layer-molv'
		});
	}else{
		if(id.indexOf(",") > 0){
			layer.alert("只能选择一款产品",{closeBtn: false,
				skin: 'layui-layer-molv'
			});

		}else{
			var productName = $("#hidProductName" + id).val();
			var price = $("#hidPrice" + id).val();
			var obj = new Object();
			obj.id = id;
			obj.productName = productName;
			obj.price = price;
			parent.getProduct(obj);

		}
	}

}

function clearNoNum(obj){
	obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符
	obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的
	obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
	obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');//只能输入两个小数
	if(obj.value.indexOf(".")< 0 && obj.value !=""){//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额
		obj.value= parseFloat(obj.value);
	}
}

function isNotBlank(args){
	var result = false;
	if(args != "" && args != null && args != undefined){
		result = true;
	}

	return result;
}

function cancelEdit(){
	parent.layer.closeAll();
}

function isBlank(args){
	var result = false;
	if(args == "" || args == null || args == undefined){
		result = true;
	}

	return result;
}

function getCheckedBoxValue(){
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