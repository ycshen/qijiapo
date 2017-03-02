
function cancelEdit(){
	layer.closeAll();
}

function isBlank(args){
	var result = false;
	if(args == "" || args == null || args == undefined){
		result = true;
	}
	
	return result;
}

function addSuccess(){
	var companyId = $("#txtCompanyId").val();
	getJsonData(companyId, 1);
	layer.closeAll();
}

function editCompany(id){
	layer.open({
		  type: 2,
		  area: ['700px', '400px'],
		  fixed: false, //不固定
		  maxmin: true,
		  title: "编辑分公司信息",
		  content: ctx + "/inner/company/edit?id=" + id
		});
}

function addCompany(){
	layer.open({
		  type: 2,
		  area: ['700px', '400px'],
		  fixed: false, //不固定
		  maxmin: true,
		  title: "新增分公司信息",
		  content: ctx + "/inner/company/edit"
		});
}

