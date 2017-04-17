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
	<link rel="stylesheet" href="${ctx}/js/plugins/TimePicki/css/timepicki.css">
	<%@include file="../../share/common_js.jsp"%>
	<script src="${ctx}/js/plugins/TimePicki/js/timepicki.js"></script>
	<script type="text/javascript" src="${ctx}/js/pages/admin/office/checkingattendance_rule_edit.js"></script>
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
				<input type="text"  autocomplete="off" value="${workAttendancePlace.placeName}"
					   placeholder="请输入考勤点名称" class="layui-input" name="placeName" lay-verify="placeName">
			</div>
		</div>
		<div class="layui-form-item my-layui-form-item my-top">
			<label class="layui-form-label">考勤地址<span style="color:red">*</span></label>
			<div class="layui-input-block">
				<input type="text" value="${workAttendancePlace.address}"
					   autocomplete="off" placeholder="请输入考勤地址" class="layui-input" name="address"  lay-verify="address">
			</div>
		</div>
		<div class="layui-form-item my-layui-form-item my-top">
			<label class="layui-form-label">上班时间<span style="color:red">*</span></label>
			<div class="layui-input-block">
				<input type="checkbox" id="chkWeek1" title="星期一" <c:if test="${workAttendancePlace.workTime.contains('星期一')}"> checked="checked"</c:if>>
				<input type="checkbox" id="chkWeek2" title="星期二" <c:if test="${workAttendancePlace.workTime.contains('星期二')}"> checked="checked"</c:if>>
				<input type="checkbox" id="chkWeek3" title="星期三" <c:if test="${workAttendancePlace.workTime.contains('星期三')}"> checked="checked"</c:if>>
				<input type="checkbox" id="chkWeek4" title="星期四" <c:if test="${workAttendancePlace.workTime.contains('星期四')}"> checked="checked"</c:if>>
				<input type="checkbox" id="chkWeek5" title="星期五" <c:if test="${workAttendancePlace.workTime.contains('星期五')}"> checked="checked"</c:if>>
				<input type="checkbox" id="chkWeek6" title="星期六" <c:if test="${workAttendancePlace.workTime.contains('星期六')}"> checked="checked"</c:if>>
				<input type="checkbox"  id="chkWeek7"  title="星期天" <c:if test="${workAttendancePlace.workTime.contains('星期天')}"> checked="checked"</c:if>>
			</div>
			<input type="hidden" id="hidWorkTime" value="" name="workTime">
		</div>
		<div class="layui-form-item my-layui-form-item my-top">
			<label class="layui-form-label">上班<span style="color:red">*</span></label>
			<div class="layui-input-block">
				<input id="txtOnWorkTime"  value="${workAttendancePlace.onWorkTime}"
					   type="text" class="layui-input" name="onWorkTime" lay-verify="onWorkTime">
			</div>
		</div>
		<div class="layui-form-item my-layui-form-item my-top">
			<label class="layui-form-label">下班<span style="color:red">*</span></label>
			<div class="layui-input-block">
				<input id="txtOffWorkTime"  value="${workAttendancePlace.offWorkTime}"
					   type="text" class="layui-input" name="offWorkTime" lay-verify="offWorkTime">
			</div>
		</div>
		<div class="layui-form-item my-layui-form-item">
			<label class="layui-form-label">适合部门<span style="color:red">*</span></label>
			<div class="layui-input-inline" style="margin-right: 0px;">
				<c:choose>
					<c:when test="${workAttendancePlace != null && workAttendancePlace.id != '' }">
						<input type="text" id="txtBeyondDeptName" autocomplete="off" class="layui-input"
							   value="${workAttendancePlace.departmentName}" disabled="disabled">
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
					<input type="text" name="errorRange" autocomplete="off" value="${workAttendancePlace.errorRange}"
						   placeholder="300" class="layui-input" maxlength="5">
				</div>
			</div>

		</div>
	<input type="hidden" value="${workAttendancePlace.id}" id="hidId" name="id"/>
	<input type="hidden" value="${workAttendancePlace.departmentId}" id="hidDepartmentId" name="departmentId"/>
	<input type="hidden" value="${workAttendancePlace.departmentName}" id="hidDepartmentName" name="departmentName"/>
</form>

<script>


	layui.use(['form', 'layedit', 'laydate'], function () {
		var form = layui.form()
				, layer = layui.layer
				, layedit = layui.layedit
				, laydate = layui.laydate;

		//自定义验证规则
		form.verify({
			placeName: function (value) {
				if (value.length < 2) {
					return '考勤点名称的长度不能小于2个字符';
				}

				if (value.length > 30) {
					return '考勤点名称的长度不能大于00个字符';
				}
			},
			address: function (value) {
				if (value.length < 2) {
					return '考勤点地址的长度不能小于2个字符';
				}

				if (value.length > 100) {
					return '考勤点地址长度不符合';
				}
			},
			onWorkTime: function (value) {
				if (value == null || value == "" || value == undefined) {
					return '请选择上班';
				}
			},
			offWorkTime: function (value) {
				if (value == null || value == "" || value == undefined) {
					return '请选择下班';
				}
			}

		});


		//监听提交
		form.on('submit(mySubmit)', function (data) {
			var url = ctx + "/admin/checkAttendance/saveOrUpdate";
			var monday = $("#chkWeek1").is(':checked');
			var tuesday = $("#chkWeek2").is(':checked');
			var wednesday = $("#chkWeek3").is(':checked');
			var thursday = $("#chkWeek4").is(':checked');
			var friday = $("#chkWeek5").is(':checked');
			var saturday = $("#chkWeek6").is(':checked');
			var sunday = $("#chkWeek7").is(':checked');
			var workTime = "";
			if(monday){
				workTime += "星期一,"
			}
			if(tuesday){
				workTime += "星期二,"
			}
			if(wednesday){
				workTime += "星期三,"
			}
			if(thursday){
				workTime += "星期四,"
			}
			if(friday){
				workTime += "星期五,"
			}
			if(saturday){
				workTime += "星期六,"
			}
			if(sunday){
				workTime += "星期天,"
			}
			if(isBlank(workTime)){
				layer.alert('请选择上班时间', { closeBtn: false, skin: 'layui-layer-molv'});
				return false;
			}else{
				workTime = workTime.substring(0, workTime.length - 1);
				$("#hidWorkTime").val(workTime);
			}

			var attendance = $('#myForm').serialize();
			$.ajax({
				cache: true,
				type: "POST",
				url: url,
				data: attendance,
				async: false,
				success: function (data) {
					layer.alert('保存成功',
							{
								closeBtn: false,
								skin: 'layui-layer-molv'
							},
							function (index) {
								parent.location.reload();
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