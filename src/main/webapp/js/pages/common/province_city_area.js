//初始数据
var areaData;
var $form;
var form;
var $;
function initLocation(){
	var url = ctx +"/inner/location/getProvinceCityArea"
	$.ajax({
		type: "get",
		url: url,
		success:function(data){
			areaData = data;
			layui.use(['jquery', 'form'], function() {
				$ = layui.jquery;
				form = layui.form();
				$form = $('form');
				loadProvince();
			});
		}
	});
}
function loadProvince() {
	var proHtml = "<option value=\"\">请选择省</option>";
	for(var i = 0; i < areaData.length; i++) {
		var size = 0;
		if(areaData[i].cityList != undefined){
			size = areaData[i].cityList.length;
		}
		
		proHtml += '<option value="' + areaData[i].provinceId + '_' + size + '_' + i + '">' +
		areaData[i].provinceName + '</option>';
	}
	//初始化省数据
	$form.find('select[name=provinceId]').append(proHtml);
	form.render();
	form.on('select(province)', function(data) {
		//$form.find('select[name=area]').html('<option value="">请选择县/区</option>').parent().hide();
		var value = data.value;
		if(value != "" && value != null && value != undefined){
			var d = value.split('_');
			var code = d[0];
			var count = d[1];
			var index = d[2];
			if(count > 0) {
				loadCity(areaData[index].cityList);
			} else {
				//$form.find('select[name=city]').parent().hide();
			}

			loadArea(null);
		}else{
			loadCity(null);
			loadArea(null);
		}
		
	});
}

function loadCity(citys) {
	var cityHtml = "<option value=\"\">请选择市</option>";
	if(citys != null){
		for(var i = 0; i < citys.length; i++) {
			var size = 0;
			if(citys[i].areaList != undefined){
				size = citys[i].areaList.length;
			}
			
			cityHtml += '<option value="' + citys[i].cityId + '_' + size + '_' + i + '">' +
				citys[i].cityName + '</option>';
		}
	}
	
	//$form.find('select[name=city]').html(cityHtml).parent().show();
	$form.find('select[name=cityId]').html(cityHtml)
	form.render();
	form.on('select(city)', function(data) {
		var value = data.value;
		if(value != "" && value != null && value != undefined){
			var d = value.split('_');
			var code = d[0];
			var count = d[1];
			var index = d[2];
			if(count > 0) {
				loadArea(citys[index].areaList);
			} else {
				
			}

			
		}else{
			loadArea(null);
		}
		
	});
}

function loadArea(areas) {
	var areaHtml = "<option value=\"\">请选择县/区</option>";
	if(areas != null){
		for(var i = 0; i < areas.length; i++) {
			areaHtml += '<option value="' + areas[i].areaId + '">' +
				areas[i].areaName + '</option>';
		}
	}
	
	//$form.find('select[name=area]').html(areaHtml).parent().show();
	$form.find('select[name=areaId]').html(areaHtml)
	form.render();
	form.on('select(area)', function(data) {
		//console.log(data);
	});
}