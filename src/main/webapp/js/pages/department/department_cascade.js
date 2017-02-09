
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

function selectPosition(){
	var url = ctx + "/inner/position/list"
	layer.open({
		type: 2,
		title: '职位选择',
		shadeClose: true,
		shade: 0.8,
		area: ['550px', '300px'],
		content: url,
		closeBtn: 0
	});
	

	
}

function cascade(){
	var departmentName = "";
	var departmentId = $("select[name='departmentId']").val();
	if(isBlank(departmentId)){
		layer.alert("请选择部门");
		return;
	}else{
		departmentName = $("select[name='departmentId']").find("option:selected").text();
		$("#txtdepartmentName").val(departmentName);
	}

    var id = $("#hidId").val();
	var url = ctx + "/inner/department/cascadeDepartment?userId=" + id + "&departmentId="+ departmentId + "&departmentName=" + departmentName;
	$.ajax({
        cache: true,
        type: "get",
        url: url,
        async: false,
        success: function(data) {
          if(data == 2){
        	  var position = $("#btnPositionName").html();
        	  layer.alert('关联成功', function(index){
        		  window.parent.cascadeSuccess(id, departmentName);
        		  
      		});
          }else{
        	  layer.alert("关联失败！"); 
          }
        }
    });

}


function selectSuccess(id, positionName){
	$("#btnSelectAgain").show();
	$("#btnSelect").hide();
	$("#btnPositionName").html(positionName);
	$("#btnPositionName").show();
	$("#hidPositionId").val(id);
}

$(function(){
	$(".select2").select2();
})