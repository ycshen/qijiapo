function addCompeotitor(){
	var url = ctx + "/inner/competitor/forwardEdit";
	layer.open({
		type: 2,
		title: '新增竞争对手',
		shadeClose: true,
		shade: 0.8,
		area: ['800px', '400px'],
		content: url
	});
}

function addSubCompany(id){
	var url = ctx + "/inner/company/addSub?id=" + id;
	layer.open({
		type: 2,
		title: '添加子公司信息',
		shadeClose: true,
		shade: 0.8,
		area: ['550px', '400px'],
		content: url
	});
}
function addSuccess(){
	window.location.href = ctx + "/inner/company/list";
}

function queryCompany(page){
	var url = ctx + "/inner/company/list?page=" + page;
	var level = $("#levelSelect").find("option:selected").val();
	if(isNotBlank(level)){
		url += "&level=" + level;
	}
	
	
	
	var companyName = $("#txtCompanyName").val();
	if(isNotBlank(companyName)){
		url += "&companyName=" + companyName;
	}
	
	window.location.href = url;
}
function isNotBlank(args){
	var result = false;
	if(args != "" && args != null && args != undefined){
		result = true;
	}
	
	return result;
}

function addDepartment(id){
	var url = ctx + "/inner/department/add?id=" + id;
	layer.open({
		type: 2,
		title: '添加部门信息',
		shadeClose: true,
		shade: 0.8,
		area: ['550px', '400px'],
		content: url
	});
}

function viewDetail(){
	var index = layer.open({
		  type: 2,
		  content: 'http://layim.layui.com',
		  area: ['320px', '195px'],
		  maxmin: true
		});
	layer.full(index);
}

$(function(){
	/*$(".select2").select2();
*/
	
	
	//初始数据
	var areaData = Area;

	var $form;
	var form;
	var $;
	layui.use(['jquery', 'form'], function() {
		$ = layui.jquery;
		form = layui.form();
		$form = $('form');
		loadProvince();
	});

	function loadProvince() {
		var proHtml = '';
		for(var i = 0; i < areaData.length; i++) {
			proHtml += '<option value="' + areaData[i].provinceCode + '_' + areaData[i].mallCityList.length + '_' + i + '">' +
				areaData[i].provinceName + '</option>';
		}
		//初始化省数据
		$form.find('select[name=province]').append(proHtml);
		form.render();
		form.on('select(province)', function(data) {
			$form.find('select[name=area]').html('<option value="">请选择县/区</option>').parent().hide();
			var value = data.value;
			var d = value.split('_');
			var code = d[0];
			var count = d[1];
			var index = d[2];
			if(count > 0) {
				loadCity(areaData[index].mallCityList);
			} else {
				$form.find('select[name=city]').parent().hide();
			}
		});
	}

	function loadCity(citys) {
		var cityHtml = '';
		for(var i = 0; i < citys.length; i++) {
			cityHtml += '<option value="' + citys[i].cityCode + '_' + citys[i].mallAreaList.length + '_' + i + '">' +
				citys[i].cityName + '</option>';
		}
		$form.find('select[name=city]').html(cityHtml).parent().show();
		form.render();
		form.on('select(city)', function(data) {
			var value = data.value;
			var d = value.split('_');
			var code = d[0];
			var count = d[1];
			var index = d[2];
			if(count > 0) {
				loadArea(citys[index].mallAreaList);
			} else {
				$form.find('select[name=area]').parent().hide();
			}
		});
	}

	function loadArea(areas) {
		var areaHtml = '';
		for(var i = 0; i < areas.length; i++) {
			areaHtml += '<option value="' + areas[i].areaCode + '">' +
				areas[i].areaName + '</option>';
		}
		$form.find('select[name=area]').html(areaHtml).parent().show();
		form.render();
		form.on('select(area)', function(data) {
			//console.log(data);
		});
	}
});