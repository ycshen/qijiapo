<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<meta charset="UTF-8">
<title>部门编辑</title>
<%@include file="../share/common_css.jsp"%>
<link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
<%--<link rel="stylesheet" href="${ctx}/js/select2/select2.min.css">--%>
<%@include file="../share/common_js.jsp"%>
<%--<script src="${ctx}/js/select2/select2.full.min.js"></script>--%>
<script type="text/javascript" src="${ctx}/js/pages/user/user_edit.js"></script>
<script type="text/javascript">
	var ctx = "${pageContext.request.contextPath}";
</script>
</head>
<body>

<div class="container" style="margin-top: 10px;">
  
<form class="layui-form" method="post" id="myForm">
<input type="hidden" value="${user.positionId}" name="positionId" id="hidPositionId"/>
<input type="hidden" value="${editType}" id="hidEditType"/>
	<input type="hidden" value="${department.id}" name="departmentId" id="hidDepartmentId"/>
	<input type="hidden" value="${user.id}" id="hidId" name="id"/>
    <div class="layui-form-item">
        <label class="layui-form-label" style="width:100px;">所属部门</label>
        <div class="layui-input-block">
            <select name="departmentId" class="form-control" lay-verify="required" lay-search="">
                <option value="-1">搜索或选择部门</option>
                <c:if test="${departmentList != null && departmentList.size() > 0 }">
                    <c:forEach items="${departmentList}" var="department">
                        <option <c:if test="${department.id == user.departmentId}">selected="selected"</c:if> value="${department.id}">${department.departmentName }</option>
                    </c:forEach>
                </c:if>
            </select>
        </div>
    </div>
  <div class="layui-form-item">
    <label class="layui-form-label" style="width:100px;">员工姓名</label>
    <div class="layui-input-block">
      <input name="userName" id="txtUserName"
							value="${user.userName}" maxlength="20"
							class="layui-input" type="text" placeholder="请输入员工姓名"">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label" style="width:100px;">联系电话</label>
    <div class="layui-input-block">
      <input id="txtTelphone" name="telphone"
							value="${user.telphone}" maxlength="11"
							class="layui-input" type="text" placeholder="请输入联系电话">
    </div>
  </div>
   <div class="layui-form-item">
    <label class="layui-form-label" style="width:100px;">邮箱</label>
    <div class="layui-input-block">
      <input id="txtEmail" name="email"
							value="${user.email}" 
							class="layui-input" type="text" placeholder="请输入邮箱地址">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label" style="width:100px;">职位</label>
    <div class="layui-input-block">
		<c:if test="${editType == 1}">
	      	<a class="layui-btn   layui-btn-primary  layui-btn-radius"  onclick="selectPosition();" id="btnSelect">
				  <i class="layui-icon">&#xe608;</i> 选择职位
			</a>
			<a style="display: none" class="layui-btn layui-btn-small layui-btn-normal layui-btn-radius" id="btnPositionName"></a>
			<a style="display: none" class="layui-btn   layui-btn-primary  layui-btn-radius"  onclick="selectPosition();" id="btnSelectAgain">
			  <i class="layui-icon">&#xe608;</i> 重新选择
		</a>
		</c:if>
		<c:if test="${editType == 2}">
			<a  class="layui-btn layui-btn-small layui-btn-normal layui-btn-radius" id="btnPositionName">${user.positionName}</a>
			<a class="layui-btn   layui-btn-primary  layui-btn-radius"  onclick="selectPosition();" id="btnSelectAgain">
			  <i class="layui-icon">&#xe608;</i> 重新选择
		</a>
		</c:if>
		
    </div>
  </div>
  <div class="layui-form-item">
    <div class="layui-input-block">
    	<c:if test="${editType == 1}">
	      <button class="layui-btn" type="button"  onclick="editUser();">添加</button>
	      <button class="layui-btn" type="button"  onclick="editAgainUser();">添加后继续添加</button>
      </c:if>
      <c:if test="${editType == 2}">
	      <button class="layui-btn" type="button"  onclick="editUser();">确定</button>
      </c:if>
      <button type="button" class="layui-btn layui-btn-primary" onclick="cancelEdit();">取消</button>
    </div>
  </div>
</form>

</div>
<script type="text/javascript">
    layui.use(['form', 'layedit', 'laydate'], function() {
        var form = layui.form()
                , layer = layui.layer
                , layedit = layui.layedit
                , laydate = layui.laydate;

        //自定义验证规则
        form.verify({

            departmentId: function (value) {
                if (value == null || value == '' || value == undefined) {
                    return '请选择所属部门';
                }
            }
        });
    });
</script>

</body>
</html>