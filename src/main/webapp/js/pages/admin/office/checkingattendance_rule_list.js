
function addCheckingAttendance(){
	var url = ctx + "/admin/checkAttendance/add";
	layer.open({
		type: 2,
		title: '添加考勤点信息',
		shadeClose: false,
		shade: 0.8,
		area: ['60%', '100%'],
		content: url
	});
}
