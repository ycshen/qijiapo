<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
/*  layui.use(['laypage', 'layer'], function(){
	  var laypage = layui.laypage
	  ,layer = layui.layer;
	  
	  
	  laypage({
	    cont: 'demo7'
	    ,pages: '${competitorQuery.count}'
	    ,skip: true
	  });
	  
	}); */
	
	
	
	function getHead(tableId){
		var head =  $("#syslist").find("thead").html();
		head = "<thead>" + head  + "</thead>";
		return head;
	}

	function getJsonData(departmentId, userName, telphone, page, isInit){
		if(userName == undefined){
			userName = '';
		}
		if(telphone == undefined){
			telphone = '';
		}
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
		var operHtml = getOper(obj.id, obj.status, obj.userName, obj.departmentId);
		tr+="<td>"  + operHtml + "</td>";
		var spanStr = getStatusSpan(obj.status)
		tr+="<td>" + spanStr + "</td>";
		tr += "<td id=\"tdUserName"+obj.id+"\">" + obj.userName + "</td>";			
		tr += "<td id=\"tdUserName"+obj.id+"\">" + obj.userName + "</td>";		
		tr += "<td  id=\"tdTelephone"+obj.id+"\" id=\"tdUserName"+obj.id+"\">" + obj.telphone + "</td>";
		var email = "";
		if(isBlank(obj.email)){
			email = "";
		}else{
			email = obj.email;
		}
				
		tr += "<td id=\"tdEmail"+obj.id+"\">" + email + "</td>";	
		var deaprtmentName = "";
		if(isBlank(obj.departmentName)){
			deaprtmentName = "<span class=\"label label-warning\">无部门状态</span>";
		}else{
			deaprtmentName = obj.departmentName;
		}
			
		tr += "<td id=\"tdDeptName"+obj.id+"\">" + deaprtmentName + "</td>";
		var positionName = "";
		if(isBlank(obj.positionName)){
			positionName = "";
		}else{
			positionName = obj.positionName;
		}
		tr += "<td id=\"tdPosition"+obj.id+"\">"  + positionName + "</td>";
		
		tr+="</tr>";

		return tr;
	
	}

	function getOper(id, status, userName,departmentId){
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
		if(departmentId == -1){
			 operHtml +="<li><a href=\"#\" onclick=\"cascadeDept('" + id + "', '" + userName + "')\">关联部门</a></li>";
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

	

	
</script>
