<%@ page language="java" pageEncoding="UTF-8"%>
<script src="${ctx}/js/jQuery/jquery-2.2.3.min.js"></script>
<script src="${ctx}/js/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/js/layer/layer.js"></script>
<script src="${ctx}/js/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript">
function changeCollapse(){
	$.ajax({
		url: "${ctx}/inner/user/changeCollapse",
		type: 'get',
		success: function(){
			
			}
	});
}

function selectDepartment(){
	var url = ctx + "/inner/common/selectDepartment";
	layer.open({
		type: 2,
		title: '选择部门',
		shadeClose: true,
		shade: 0.8,
		area: ['400px', '300px'],
		content: url
	});
}

</script>