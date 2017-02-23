
function cancelEdit(){
	window.parent.layer.closeAll();
}

function isBlank(args){
	var result = false;
	if(args == "" || args == null || args == undefined){
		result = true;
	}
	
	return result;
}

$(function(){
	$(".select2").select2();
})

function transferCompetitor(competitorId){
	layer.confirm("确定要转移竞争对手相关信息吗？",{closeBtn: false,
  		skin: 'layui-layer-molv'
	  }, function(){
		  var userId = $("select[name='userId']").val();
			if(isBlank(userId)){
				layer.alert("请选择新负责人",{closeBtn: false, skin: 'layui-layer-molv'});
				return;
			}

			var url = ctx + "/inner/competitor/cascadeDepartment?transferType=1&userId=" + userId + "&competitorId="+ competitorId;
			$.ajax({
		        cache: true,
		        type: "get",
		        url: url,
		        async: false,
		        success: function(data) {
		          if(data == 2){
		        	  var position = $("#btnPositionName").html();
		        	  layer.alert('转移竞争对手成功',{closeBtn: false, skin: 'layui-layer-molv'}, function(index){
		        		  window.parent.refreshTable();
		        		  
		      		});
		          }else{
		        	  layer.alert("转移竞争对手失败！",{closeBtn: false, skin: 'layui-layer-molv'} ); 
		          }
		        }
		    });
	})
	
	
}