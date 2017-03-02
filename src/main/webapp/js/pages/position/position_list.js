$(function(){
	bindUserDefined();
	bindAdd();
})

function bindUserDefined(){
	$("#btnAddDefined").click(function(){
		$(this).hide();
		$("#btnAdd").show();
		$("#txtPositionName").show();
		$("#txtPositionName").focus();
		
	})
}

function bindAdd(){
	$("#btnAdd").click(function(){
		var positionName = $("#txtPositionName").val();
		if(!isNotBlank(positionName)){
			layer.alert("职位名称不能为空",{closeBtn: false,
		  		skin: 'layui-layer-molv'
			  });
			return;
		}
		
		if(positionName.length > 7){
			layer.alert("职位名称长度过长",{closeBtn: false,
		  		skin: 'layui-layer-molv'
			  });
			return;
		}
		
		var url = ctx + "/inner/position/addPostion?postionName=" + positionName;
		$.ajax({
			url: url,
			type: "get",
			success: function(position){
				if(position == 4){
					layer.alert("职位名称已经存在",{closeBtn: false,
				  		skin: 'layui-layer-molv'
					  });
				}else{					
					var id = position.id;		
					var positionName = position.postionName;
					var oldHtml = $("#divUserDefined").html();
					oldHtml += "<button class=\"layui-btn layui-btn-radius layui-btn-mini\"  onclick=\"selectPosition('" + id + "', '" + positionName + "')\">" + positionName + "</button>";
					$("#divUserDefined").html(oldHtml);
					
					$("#btnAddDefined").show();
					$("#btnAdd").hide();
					$("#txtPositionName").hide();
					$("#txtPositionName").val('');
				}
				
			}
		});
		
	})
}

function isNotBlank(args){
	var result = false;
	if(args != "" && args != null && args != undefined){
		result = true;
	}
	
	return result;
}

function selectPosition(id, positionName){
	var index = parent.layer.getFrameIndex(window.name); 
	parent.layer.close(index)
	parent.selectSuccess(id, positionName);
}