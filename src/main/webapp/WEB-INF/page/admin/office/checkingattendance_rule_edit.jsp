<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
	<meta charset="UTF-8">
	<title>考勤点编辑管理</title>

	<link href="${ctx}/js/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="${ctx}/css/common.css" rel="stylesheet">
	<link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
	<link rel="stylesheet" href="${ctx}/js/plugins/timepicker/bootstrap-timepicker.min.css">
	<%@include file="../../share/common_js.jsp"%>
	<script src="${ctx}/js/plugins/timepicker/bootstrap-timepicker.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/pages/product/product_edit.js"></script>
	<script type="text/javascript">
		var ctx = "${pageContext.request.contextPath}";
	</script>
	<style type="text/css">
		.my-layui-form-item{
			margin: 0px 15px 0px 0px;
			clear: both;
		}
		.layui-form-label {
			float: left;
			display: block;
			width: 150px;
			font-weight: 400;
			text-align: right;
			padding: 9px 15px;
		}
		.layui-input-block {
			margin-left: 150px;
			min-height: 36px;
		}
		.my-top{
			margin-top: 10px;
		}
		.content_div{
			height: 300px;
			overflow-y:auto;
		}
	</style>
</head>
<body style="background: #fff;">

<form class="layui-form" id="myForm" onsubmit="return false;">
	<div class="layui-form-item my-top">
		<div class="layui-input-block" style="text-align:right;">
			<button class="layui-btn" lay-submit="" lay-filter="mySubmit">保存</button>
			<button class="layui-btn layui-btn-primary" style="margin-right:50px;" onclick="cancelEdit();">取消</button>
		</div>
	</div>
	<div class="container">

		<div class="layui-form-item my-layui-form-item my-top">
			<label class="layui-form-label">考勤点名称<span style="color:red">*</span></label>
			<div class="layui-input-block">
				<input type="text"  autocomplete="off"
					   placeholder="请输入考勤点名称" class="layui-input" >
			</div>
		</div>
		<div class="layui-form-item my-layui-form-item my-top">
			<label class="layui-form-label">考勤地址<span style="color:red">*</span></label>
			<div class="layui-input-block">
				<input type="text"
					   autocomplete="off" placeholder="请输入考勤地址" class="layui-input" >
			</div>
		</div>
		<div class="layui-form-item my-layui-form-item my-top">
			<label class="layui-form-label">上班时间<span style="color:red">*</span></label>
			<div class="layui-input-block">
				<input type="checkbox" name="like[write]" title="星期一" checked="">
				<input type="checkbox" name="like[read]" title="星期二" checked="">
				<input type="checkbox" name="like[game]" title="星期三" checked="">
				<input type="checkbox" name="like[write]" title="星期四" checked="">
				<input type="checkbox" name="like[read]" title="星期五" checked="">
				<input type="checkbox" name="like[game]" title="星期六" >
				<input type="checkbox" name="like[game]" title="星期天">
			</div>
		</div>
		<div class="layui-form-item my-layui-form-item my-top">
			<label class="layui-form-label">上班<span style="color:red">*</span></label>
			<div class="layui-input-block">
				<input id="timepicker1" type="text" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item my-layui-form-item my-top">
			<label class="layui-form-label">下班<span style="color:red">*</span></label>
			<div class="layui-input-block">
				<input id="timepicker" type="text" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item my-layui-form-item">
			<label class="layui-form-label">适合部门<span style="color:red">*</span></label>
			<div class="layui-input-inline" style="margin-right: 0px;">
				<c:choose>
					<c:when test="${competitor != null && competitor.id != '' }">
						<input type="text" id="txtBeyondDeptName" autocomplete="off" class="layui-input"
							   value="${competitor.departmentName}" disabled="disabled">
					</c:when>
					<c:otherwise>
						<input type="text" id="txtBeyondDeptName" autocomplete="off" class="layui-input"
							   value="全公司" disabled="disabled">
					</c:otherwise>
				</c:choose>

			</div>
			<button class="layui-btn  layui-btn-primary" onclick="selectDepartment();"><i class="layui-icon">
				&#xe61f;</i></button>
		</div>





			<div class="layui-form-item my-layui-form-item my-top">
				<label class="layui-form-label">偏差范围（米）<span style="color:red">*</span></label>
				<div class="layui-input-block">
					<input type="text" name="facsimile" autocomplete="off"
						   placeholder="300" class="layui-input" maxlength="10">
				</div>
			</div>

		</div>
</form>

<script>
	$(function(){
		//Timepicker
		$('#timepicker').timepicker();
		$('#timepicker1').timepicker();
	})

	layui.use(['form', 'layedit', 'laydate'], function () {
		var form = layui.form()
				, layer = layui.layer
				, layedit = layui.layedit
				, laydate = layui.laydate;

		//自定义验证规则
		form.verify({
			competitorName: function (value) {
				if (value.length < 2) {
					return '竞争对手名称的长度不能小于2个字符';
				}

				if (value.length > 30) {
					return '竞争对手名称的长度不能大于00个字符';
				}
			},

		});


		//监听提交
		form.on('submit(mySubmit)', function (data) {
			var url = ctx + "/inner/competitor/saveOrUpdate"
			var provinceName = $("select[name='provinceId']").find("option:selected").text();
			if (provinceName != "请选择省") {
				$("#hidProvinceName").val(provinceName);
			}

			var cityName = $("select[name='cityId']").find("option:selected").text();
			if (cityName != "请选择市") {
				$("#hidCityName").val(cityName);
			}

			var areaName = $("select[name='areaId']").find("option:selected").text();
			if (areaName != "请选择县/区") {
				$("#hidAreaName").val(areaName);
			}


			var competitor = $('#myForm').serialize();
			$.ajax({
				cache: true,
				type: "POST",
				url: url,
				data: competitor,
				async: false,
				success: function (data) {
					layer.alert('保存成功',
							{
								closeBtn: false,
								skin: 'layui-layer-molv'
							},
							function (index) {
								parent.refreshTable();
								parent.layer.closeAll();
							});
				}
			});
			return false;
		});


	});
</script>

</body>
</html>