<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function(){

		var count = $("#count").val();
		$(".pagination").pagination(count, {
		    num_edge_entries: 5,
		    num_display_entries: 10,
		    callback: pageselectCallback,
		    items_per_page:10,
		    maxentries: count,
		    prev_text: "上一页",
		    next_text: "下一页"
		}); 
	});

	function pageselectCallback(page){
		var userName = $("#txtUserName").val();
		var telphone = $("#txtTelphone").val();
		var departmentId = "";
		getJsonData(departmentId, userName, telphone, page + 1);
        return false;
	}
	
	function getHead(tableId){
		var head =  $("#syslist").find("thead").html();
		head = "<thead>" + head  + "</thead>";
		return head;
	}

	function getJsonData(departmentId, userName, telphone, page){
		var url = ctx + "/inner/admin/userList?telphone=" + telphone + "&userName=" + userName + "&departmentId=" + departmentId +  "&page=" + page + "&status=101"
		$.ajax({
			type: "get",
			url: url,
			success: function(data){
				 $("#count").val(data.count);
				var tbody = "<tbody>";
				$.each(data.items, function(index, obj){
					tbody += getTr(obj);
				})
				var tableHead = getHead();
				tbody += "</tbody>";
				tableHead += tbody;
				$("#syslist").html(tableHead)
			}
			
		})
		
	}
	
	function getTr(obj){
		var tr = "";
		tr+="<tr>";
		tr+="<td></td>";
		var spanStr = getStatusSpan(obj.status)
		tr+="<td>" + spanStr + "</td>";
		tr += "<td>" + obj.userName + "</td>";			
		tr += "<td>" + obj.userName + "</td>";		
		tr += "<td>" + obj.telphone + "</td>";	
		var deaprtmentName = "";
		if(isBlank(obj.departmentName)){
			deaprtmentName = "<span class=\"label label-warning\">无部门状态</span>";
		}else{
			deaprtmentName = obj.departmentName;
		}
			
		tr += "<td>" + deaprtmentName + "</td>";
		tr += "<td>"  + obj.positionName + "</td>";
		
		tr+="</tr>";

		return tr;
	
	}

	function getStatusSpan(status){
		var spanStr = "";
		if(status == 101){
			spanStr = "<span class=\"label label-success\">正常</span>";
		}

		if(status == 102){
			spanStr = "<span class=\"label label-warning\">停用</span>";
		}

		if(status == 103){
			spanStr = "<span class=\"label label-danger\">禁止登陆</span>";
		}

		return spanStr;
	}
	
	function isNotBlank(args){
		var result = false;
		if(args != null && args != "" && args != undefined){
			result = true;
		}

		return result;
	}

	function isBlank(args){
		var result = false;
		if(args == null || args == "" || args == undefined){
			result = true;
		}

		return result;
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

	
</script>
<div class="pagination" style="margin:0px;"></div> 
