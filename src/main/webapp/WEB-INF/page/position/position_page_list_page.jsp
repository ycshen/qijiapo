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
		var companyId = $("#txtCompanyId").val();
		getJsonData(companyId, page + 1);
        return false;
	}
	
	function getHead(tableId){
		var head =  $("#syslist").find("thead").html();
		head = "<thead>" + head  + "</thead>";
		return head;
	}

	function getJsonData(companyId, page){
		var url = ctx + "/inner/log/list?companyId=" + companyId + "&page=" + page
		$.ajax({
			type: "get",
			url: url,
			success: function(data){
				var tbody = "<tbody>";
				var index = 0;
				$.each(data, function(index, obj){
					tbody += getTr(obj, index);
				})
				
				var tableHead = getHead();
				tbody += "</tbody>";
				tableHead += tbody;
				$("#syslist").html(tableHead)
			}
			
		})
		
	}
	
	function getTr(obj, index){
		index = Number(index) + 1;
		var tr = "";
		tr+="<tr>";
		tr+="<td style=\"width: 50px;\">" + index + "</td>";
		tr+="<td style=\"width: 80px;\">" + obj.userName + "</td>";
		var logMsg = obj.logMsg;
		if(isNotBlank(logMsg) && logMsg.length > 50){
			logMsg = logMsg.substr(0, 50) + "......";
		}
		tr += "<td>" + logMsg + "</td>";		
		var createTime = "";
		if(isNotBlank(obj.createTime)){
			createTime = new Date(obj.createTime).Format("yyyy-MM-dd hh:mm:ss"); 
		}	
		tr += "<td>" + createTime + "</td>";	
		
		tr+="</tr>";

		return tr;
	
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
