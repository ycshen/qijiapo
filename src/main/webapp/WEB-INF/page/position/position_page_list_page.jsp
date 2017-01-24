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
		getJsonData(page + 1);
        return false;
	}
	
	function getHead(tableId){
		var head =  $("#syslist").find("thead").html();
		head = "<thead>" + head  + "</thead>";
		return head;
	}

	function getJsonData(page){
		var url = ctx + "/inner/position/page?page=" + page
		$.ajax({
			type: "get",
			url: url,
			success: function(data){
				var tbody = "<tbody>";
				var plist = data.items;
				$.each(plist, function(index, obj){
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
		var operStr = getOper(obj.isDelete, obj.id, obj.postionName);
		var tr = "";
		tr+="<tr>";
		tr+="<td id=\"positionOper" + obj.id + "\">" + operStr + "</td>";
		tr+="<td id=\"positionName" + obj.id + "\">" + obj.postionName + "</td>";
		var status = getStatus(obj.isDelete);
		tr+="<td id=\"positionStatus" + obj.id + "\">" + status + "</td>";
		
		tr+="</tr>";

		return tr;
	
	}

	function getOper(status, id, positionName){
		var operStr = "<div class=\"btn-group\">";
		operStr +="<button type=\"button\" class=\"btn btn-default dropdown-toggle\" data-toggle=\"dropdown\">";
		operStr +=" <span class=\"caret\"></span>";
		operStr +="</button>";
		operStr +="<ul class=\"dropdown-menu\">";
		if(status == 0){
			operStr +="<li><a href=\"#\" onclick=\"editPosition('" + id + "');\">编辑</a></li>";
			operStr +=" <li><a href=\"#\" onclick=\"stopPosition('" + id + "', '" + positionName + "')\">停用</a></li>";
		}else{
            
			operStr +=" <li><a href=\"#\" onclick=\"startPosition('" + id + "', '" + positionName + "')\">启用</a></li>";
		}

		operStr +="</ul>";
		operStr +="</div>";

		return operStr;
	}
	
	function getStatus(status){
		if(status == 0){
			return "<span class=\"label label-success\">正常</span>";
		}else{
			return "<span class=\"label label-warning\">停用</span>";
		}
		
	}
	function isNotBlank(args){
		var result = false;
		if(args != null && args != "" && args != undefined){
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
<div class="pagination"></div> 
