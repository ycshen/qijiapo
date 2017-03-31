
function addSubCompany(id){
	var url = ctx + "/inner/company/addSub?id=" + id;
	layer.open({
		type: 2,
		title: '添加子公司信息',
		shadeClose: false,
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

$(function(){
	/*$(".select2").select2();
*/
	
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

function queryCustomer(){
	$("#myDataTable").dataTable().fnDestroy(); 
	var customerName = $("#txtCustomerName").val();
	var mobile = $("#txtMobile").val();
	$("#myDataTable").dataTable( {
	       ajax : {  
	            type: "GET",  
	            url: ctx + '/inner/customer/listAjax',  
	            // 传入已封装的参数  
	            data: function(data){ 
	            	data.customerName = customerName;
	            	data.mobile = mobile;
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
				{ data: 'customerName',render: function(data, type, customer){
					return renderCustomer(customer.id, customer.customerName);
				}},
				{ data: 'mobile' },
				{ data: 'level',
				   	render: function(data, type, customer){
				   		return renderLevel(customer.level);
				   	}
				},
				{ data: 'provinceName',
				   	render: function(data, type, customer){
				   		return renderPCA(customer.provinceName, customer.cityName, customer.areaName);
				   	}
				}
	   	         ],
	       "bProcessing" : true, //DataTables载入数据时，是否显示‘进度’提示  
	       "bPaginate": true,
	       "serverSide": true, 
           "searching" : false,
	       "bFilter": false, 
	       "sPaginationType": "full_numbers", 
	       "paging": true,
	       "lengthChange": false,
	       "info": true,
	       "autoWidth": true,
	       "bSort": false,
	       "oLanguage": CONSTANT.DATA_TABLES.DEFAULT_OPTION.LANGUAGE
	      
	   } );
	
}


function initDataTable(){
	$("#myDataTable").dataTable( {
	       ajax : {  
	            type: "GET",  
	            url: ctx + '/inner/customer/listAjax',  
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
	             { data: 'customerName',render: function(data, type, customer){
					 return renderCustomer(customer.id, customer.customerName);
				 }},
	             { data: 'mobile' },
	             { data: 'level',
		            	render: function(data, type, customer){
		            		return renderLevel(customer.level);
		            	}
		         },
	             { data: 'provinceName',
		            	render: function(data, type, customer){
		            		return renderPCA(customer.provinceName, customer.cityName, customer.areaName);
		            	}
		         }
	         ],
	       "bProcessing" : true, //DataTables载入数据时，是否显示‘进度’提示  
	       "bPaginate": true,
	       "serverSide": true, 
           "searching" : false,
	       "bFilter": false, 
	       "sPaginationType": "full_numbers", 
	       "paging": true,
	       "lengthChange": false,
	       "info": true,
	       "autoWidth": true,
	       "bSort": false,
	       "oLanguage": CONSTANT.DATA_TABLES.DEFAULT_OPTION.LANGUAGE
	      
	   } );
}
function renderPCA(provinceName, cityName, areaName){
	return provinceName + cityName + areaName;
}

function renderCustomer(id, name){
	return "<span id='customerName" + id + "'>" +  name +"</span>";
}
function renderLevel(level){
	if(level == 1){
		return "A(重点客户)";
	}else if(level == 2){
		return "B(普通客户)";
	}else{
		return "C(非优先级客户)";
	}
}
function isBlank(args){
	var result = false;
	if(args == "" || args == null || args == undefined){
		result = true;
	}

	return result;
}

function confirmSelect(){
	var id = getCheckedBoxValue();
	if(isBlank(id)){
		layer.alert("请选择客户",{closeBtn: false,
			skin: 'layui-layer-molv'
		});
	}else{
		if(id.indexOf(",") > 0){
			layer.alert("只能选择一位客户",{closeBtn: false,
				skin: 'layui-layer-molv'
			});

		}else{
            console.log("获取信息开始")
			var name = $("#customerName" + id).html();
			parent.getCustomer(id, name);
            console.log("获取信息结束")
		}
	}

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