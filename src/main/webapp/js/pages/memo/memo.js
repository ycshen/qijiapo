function addMemo(){
	var url = ctx + "/inner/memo/addMemo";
	layer.open({
		type: 2,
		title: '添加行程',
		shadeClose: true,
		shade: 0.8,
		area: ['550px', '400px'],
		content: url
	});
}
