
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

function transferAttn(attnId){
	layer.confirm("确定要转移联系人相关信息吗？",{closeBtn: false,
  		skin: 'layui-layer-molv'
	  }, function(){
		  var userId = $("select[name='userId']").val();
			if(isBlank(userId)){
				layer.alert("请选择新负责人",{closeBtn: false, skin: 'layui-layer-molv'});
				return;
			}
			
			var transferType = $("#hidTransferType").val();
			var url = ctx + "/inner/attn/transferAttn?transferType=" + transferType + "&userId=" + userId + "&attnId="+ attnId;
			$.ajax({
		        cache: true,
		        type: "get",
		        url: url,
		        async: false,
		        success: function(data) {
		          if(data == 2){
		        	  var position = $("#btnPositionName").html();
		        	  layer.alert('转移联系人成功',{closeBtn: false, skin: 'layui-layer-molv'}, function(index){
		        		  window.parent.refreshTable();
		        		  window.parent.layer.closeAll();
		        		  
		      		});
		          }else{
		        	  layer.alert("转移联系人失败！",{closeBtn: false, skin: 'layui-layer-molv'} ); 
		          }
		        }
		    });
	})
	
	
}