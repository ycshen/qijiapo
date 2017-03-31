function selectProduct(){
	var url = ctx + "/inner/product/selectProduct";
	layer.open({
		type: 2,
		title: false,
		closeBtn: false,
		shadeClose: false,
		shade: 0.8,
		area: ['800px', '100%'],
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

$(function(){
	initDataTable();
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
		layer.confirm("确定要批量删除产品相关信息吗？",{closeBtn: false,
	  		skin: 'layui-layer-molv'
		  }, function(){
			  var url = ctx + "/inner/product/batchDeleteById?ids=" + deleteIdArr;
			$.ajax({
				type: "get",
				url: url,
				success: function(result){
					if(result == 2){
						layer.alert("批量删除产品成功",{closeBtn: false,
					  		skin: 'layui-layer-molv'
						  }, function(){
							  refreshTable();
							  layer.closeAll();
						  });
					}else{
						layer.alert("批量删除产品失败",{closeBtn: false,
					  		skin: 'layui-layer-molv'
						  });
					}
				}
			});
		})
	}else{
		layer.alert("请选择需要批量删除的产品",{closeBtn: false,
	  		skin: 'layui-layer-molv'
		  });
	}
}

function batchTransfer(){
	var idArr = getCheckedBox();
	if(isNotBlank(idArr)){
		layer.confirm("确定要批量转移产品相关信息吗？",{closeBtn: false,
	  		skin: 'layui-layer-molv'
		  }, function(){
			  var url = ctx + "/inner/user/selectAllUser?id=" + idArr;
				var title = "批量转移产品";
				layer.open({
					type: 2,
					title: title,
					shadeClose: false,
					shade: 0.8,
					area: ['500px', '400px'],
					content: url
				}); 
		})
	}else{
		layer.alert("请选择需要批量转移的产品",{closeBtn: false,
	  		skin: 'layui-layer-molv'
		  });
	}
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
	   	             CONSTANT.DATA_TABLES.COLUMN.CHECKBOX, 
	   	             {  
	   	                 data : 'operate',  
	   	                 bSortable : false,  
	   	                 visible : true,  
	   	                 render : function(data, type, product) {  
	   	                       
	   	                	 var operResult = "<div class=\"btn-group\">";
	   	                	 operResult += "<button type=\"button\" class=\"btn btn-default dropdown-toggle\" data-toggle=\"dropdown\">";
	   	                	 operResult +=  "<span class=\"caret\"></span>";
	   	                	 operResult +=  "</button>";
	   	                	 operResult += " <ul class=\"dropdown-menu\">";
	   	                          
	   	                	 operResult += 	" <li><a href=\"#\" onclick=\"deleteById('" + product.id + "', '" + product.productName + "');\">删除</a></li>";
	   	                	 operResult += 	" <li><a href=\"#\" onclick=\"editProduct('" + product.id + "');\">编辑</a></li>";
	   	                         	
	   	                	 operResult += " </ul>";
	   	                	 operResult += " </div>";
	   	                     
	   	                     return operResult;  
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
	             CONSTANT.DATA_TABLES.COLUMN.CHECKBOX, 
	             {  
	                 data : 'operate',  
	                 bSortable : false,  
	                 visible : true,  
	                 render : function(data, type, product) {  
	                       
	                	 var operResult = "<div class=\"btn-group\">";
	                	 operResult += "<button type=\"button\" class=\"btn btn-default dropdown-toggle\" data-toggle=\"dropdown\">";
	                	 operResult +=  "<span class=\"caret\"></span>";
	                	 operResult +=  "</button>";
	                	 operResult += " <ul class=\"dropdown-menu\">";
	                          
	                	 operResult += 	" <li><a href=\"#\" onclick=\"deleteById('" + product.id + "', '" + product.productName + "');\">删除</a></li>";
	                	 operResult += 	" <li><a href=\"#\" onclick=\"editProduct('" + product.id + "');\">编辑</a></li>";
	                         	
	                	 operResult += " </ul>";
	                	 operResult += " </div>";
	                     
	                     return operResult;  
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

function getProduct(product){
	var spanNum = $("#spanNum").html();
	$("#spanNum").html(Number(spanNum) + 1);
	var spanTotalPrice = $("#spanTotalPrice").html();
	var totalPrice = parseFloat(spanTotalPrice) + parseFloat(product.price);
	totalPrice = totalPrice.toFixed(2);
	$("#spanTotalPrice").html(totalPrice);
	var oldHtml =$("#productTable").html();
	var newHtml = oldHtml + getAppendProduct(product);
	$("#productTable").empty();
	$("#productTable").html(newHtml);
	layer.closeAll();
}

function getAppendProduct(product){
	var trHtml = "";
	trHtml +="<tr>";
	trHtml +="<td><a><img src=\""+ ctx+"/img/product/product_delete_normal.png\"/></a></td>";
	trHtml +="<td style=\"display:none;\">" + product.id + "</td>";
	trHtml +="<td>" + product.productName + "</td>";
	trHtml +="<td id='tdPrice" + product.id + "'>" + product.price + "</td>";
	trHtml +="<td><input type=\"text\" id='txtPrice" + product.id + "' title='按Enter键或者失去焦点自动计算' onblur='calculate("+product.id +", 1)' onkeydown='enterCalculate("+product.id +", 1)' onkeyup='clearNoNum(this)'  class=\"form-control\" value=\"" + product.price + "\" style=\"width: 100px;height:30px;display:inline\"/></td>";
	trHtml +="<td><input type=\"text\" id='txtNum" + product.id + "' title='按Enter键或者失去焦点自动计算' onblur='calculate("+product.id +", 2)' onkeydown='enterCalculate("+product.id +", 2)' onkeyup='clearNoNum(this)' class=\"form-control\" value=\"1\" style=\"width: 100px;height:30px;display:inline\"/></td>";
	trHtml +="<td><input type=\"text\" id='txtDiscount" + product.id + "' title='按Enter键或者失去焦点自动计算' onblur='calculate("+product.id +", 3)' onkeydown='enterCalculate("+product.id +", 3)' onkeyup='clearNoNum(this)'class=\"form-control\" value=\"100\" style=\"width: 100px;height:30px;display:inline\"/></td>";
	trHtml +="<td><input type=\"text\" id='txtActualPrice" + product.id + "' title='按Enter键或者失去焦点自动计算' onblur='calculate("+product.id +", 4)' onkeydown='enterCalculate("+product.id +", 4)' onkeyup='clearNoNum(this)' class=\"form-control\" value=\"" + product.price + "\" style=\"width: 100px;height:30px;display:inline\"/></td>";
	trHtml +="<td>企家婆</td>";
	trHtml +="<td><textarea style=\"height: 30px;width: 100px;\" id='txtRemark" + product.id + "'></textarea></td>";
	trHtml +="</tr>";
	return trHtml;
}

function calculate(id, txtNum){
	if(txtNum == 1){
		var price = $("#txtPrice" + id).val();
		var num = $("#txtNum" + id).val();
		var calPrice  = parseFloat(price) * parseFloat(num);
		calPrice = calPrice.toFixed(2);
		$("#txtActualPrice" + id).val(calPrice);
		var tdPrice = $("#tdPrice" + id).html();
		var discount = price / parseFloat(tdPrice) * 100;
		discount = discount.toFixed(2);
		$("#txtDiscount" + id).val(discount);
	}

	if(txtNum == 2){
		var price = $("#txtPrice" + id).val();
		var num = $("#txtNum" + id).val();
		var calPrice  = parseFloat(price) * parseFloat(num);
		calPrice = calPrice.toFixed(2);
		$("#txtActualPrice" + id).val(calPrice);
	}

	if(txtNum == 3){
		var num = $("#txtNum" + id).val();
		var discount = $("#txtDiscount" + id).val();
		var tdPrice = $("#tdPrice" + id).html();
		var price = parseFloat(tdPrice) * parseFloat(discount) / 100;
		price = price.toFixed(2);
		$("#txtPrice" + id).val(price);
		var calPrice = price * parseFloat(discount) * parseFloat(num) / 100;
		calPrice = calPrice.toFixed(2);
		$("#txtActualPrice" + id).val(calPrice);
	}

	if(txtNum == 4){
		var tdPrice = $("#tdPrice" + id).html(); //标准价格
		var num = $("#txtNum" + id).val(); //数量
		var calPrice = $("#txtActualPrice" + id).val(); //输入价格

		var txtPrice = parseFloat(calPrice) * parseFloat(num);
		$("#txtPrice" + id).val(txtPrice);
		var discount = parseFloat(txtPrice) / parseFloat(tdPrice) * 100;
		discount = discount.toFixed(2);
		$("#txtDiscount" + id).val(discount);

	}
}

function enterCalculate(id, txtNum) {
	if (event.keyCode == 13){
		calculate(id, txtNum);
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

function getTableTitle(){
    titleHtml +="<tr>";
	titleHtml +="<td>操作</td>";
	titleHtml +="<td>产品名称</td>";
	titleHtml +="<td>标准价格(元)</td>";
	titleHtml +="<td>销售单价(元)</td>";
	titleHtml +="<td>数量</td>";
	titleHtml +="<td>折扣</td>";
	titleHtml +="<td>销售金额(元)</td>";
	titleHtml +="<td>销售单位</td>";
	titleHtml +="<td>备注</td>";
	titleHtml +="</tr>";

	return titleHtml;
}

/**
 * 确定给销售机会添加产品
 * @param saleOpportunityId
 */
function confirmAdd(saleOpportunityId){
	var length = $("#productTable").find("tr").length;
	if(length > 1){
		var index = layer.load(0, {
			  shade: [0.5,'#b8c7ce']
			});
		//开始保存
		for(var i =1; i< length; i ++){
			var obj = new Object();
			obj.salesOppoId = saleOpportunityId;
			//产品id
			var productId = $("#productTable").find("tr").eq(i).find("td").eq(1).html();
			obj.productId = productId;
			var productName = $("#productTable").find("tr").eq(i).find("td").eq(2).html();
			obj.productName = productName;
			
			var productPrice = $("#productTable").find("tr").eq(i).find("td").eq(3).html();
			obj.productPrice = productPrice;
			var salePrice = $("#tdPrice" + productId).html();
			obj.salePrice = salePrice;
			var saleNum = $("#txtNum" + productId).val();
			obj.saleNum = saleNum;
			var discount = $("#txtDiscount" + productId).val();
			obj.discount = discount;
			var saleMoney = $("#txtActualPrice" + productId).val();
			obj.saleMoney = saleMoney;
			var saleUnit = $("#productTable").find("tr").eq(i).find("td").eq(8).html();
			var remark = $("#txtRemark" + productId).val();
			obj.remark = remark;
			//产品名称
			//标准价格
			//销售价格
			//数量
			//折扣
			//销售总金额
			//备注
			var saleOppoJson = JSON.stringify(obj);
			$.ajax({
				type: "post",
				url: ctx + "/inner/sop/saveOrUpdate",
				contentType:"application/json",
				data: saleOppoJson,
				success:function(result){
					console.log(result)
				}
			});
		}
		
		 layer.closeAll('loading');
        layer.alert("添加产品成功",{closeBtn: false,
	  		skin: 'layui-layer-molv'
		  }, function(){
			  parent.refreshTable();
		        parent.layer.closeAll();
		  });
	}

}