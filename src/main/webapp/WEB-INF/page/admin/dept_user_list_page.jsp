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
		getJsonData(departmentId, userName, telphone, page + 1, false);
        return false;
	}
	
	function getHead(tableId){
		var head =  $("#syslist").find("thead").html();
		head = "<thead>" + head  + "</thead>";
		return head;
	}

	function getJsonData(departmentId, userName, telphone, page, isInit){
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
				$("#syslist").html(tableHead);
				$("#spanCount").text(data.count);
				if(isInit){
					$(".pagination").pagination(data.count, {
					    num_edge_entries: 5,
					    num_display_entries: 10,
					    callback: pageselectCallback,
					    items_per_page:10,
					    maxentries: count,
					    prev_text: "上一页",
					    next_text: "下一页"
					});
				}
			}
			
		})

	}
	
	function getTr(obj){
		var tr = "";
		tr+="<tr>";
		var operHtml = getOper(obj.id, obj.status, obj.userName);
		tr+="<td>"  + operHtml + "</td>";
		var spanStr = getStatusSpan(obj.status)
		tr+="<td>" + spanStr + "</td>";
		tr += "<td>" + obj.userName + "</td>";			
		tr += "<td>" + obj.userName + "</td>";		
		tr += "<td>" + obj.telphone + "</td>";
		var email = "";
		if(isBlank(obj.email)){
			email = "";
		}else{
			email = obj.email;
		}
				
		tr += "<td>" + email + "</td>";	
		var deaprtmentName = "";
		if(isBlank(obj.departmentName)){
			deaprtmentName = "<span class=\"label label-warning\">无部门状态</span>";
		}else{
			deaprtmentName = obj.departmentName;
		}
			
		tr += "<td>" + deaprtmentName + "</td>";
		var positionName = "";
		if(isBlank(obj.positionName)){
			positionName = "";
		}else{
			positionName = obj.positionName;
		}
		tr += "<td>"  + positionName + "</td>";
		
		tr+="</tr>";

		return tr;
	
	}

	function getOper(id, status, userName){
		var operHtml = "<div class=\"btn-group\">";
		operHtml +="<button type=\"button\" class=\"btn btn-default dropdown-toggle\" data-toggle=\"dropdown\">";
		operHtml +="<span class=\"caret\"></span>";
		operHtml +="</button>";
		operHtml +=" <ul class=\"dropdown-menu\" id=\"ulOper" + id +"\">";
        if(status == 103){
        	operHtml +="<li><a href=\"#\" onclick=\"editUser('" + id + "')\">编辑</a></li>";
        	operHtml +="<li><a href=\"#\" onclick=\"resetPass('" + id + "', '" + userName + "')\">重置密码</a></li>";
        	operHtml +="<li><a href=\"#\" onclick=\"enableUser('" + id + "', '" + userName + "')\">启用</a></li>";
        	operHtml +="<li><a href=\"#\" onclick=\"forbidUser('" + id + "', '" + userName + "')\">停用</a></li>";
        }else if(status == 102){
        	 operHtml +=" <li><a href=\"#\" onclick=\"editUser('" + id + "')\">编辑</a></li>";
             operHtml +=" <li><a href=\"#\" onclick=\"resetPass('" + id + "', '" + userName + "')\">重置密码</a></li>";
             operHtml +=" <li><a href=\"#\" onclick=\"forbidLogin('" + id + "', '" + userName + "')\">禁止登陆</a></li>";
             operHtml +=" <li><a href=\"#\" onclick=\"enableUser('" + id + "', '" + userName + "')\">启用</a></li>";
              
        }else if(status == 101){
        	 operHtml +=" <li><a href=\"#\" onclick=\"editUser('" + id + "')\">编辑</a></li>";
             operHtml +="  <li><a href=\"#\" onclick=\"resetPass('" + id + "', '" + userName + "')\">重置密码</a></li>";
             operHtml +="  <li><a href=\"#\" onclick=\"forbidLogin('" + id + "', '" + userName + "')\">禁止登陆</a></li>";
             operHtml +="  <li><a href=\"#\" onclick=\"forbidUser('" + id + "', '" + userName + "')\">停用</a></li>";
            
        }
           
        operHtml +=" </ul>";
        operHtml +="  </div>";

        return operHtml;
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
