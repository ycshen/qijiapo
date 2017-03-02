<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>企家婆-专业的企业好管家</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <%@include file="../share/common_css.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/js/multi-select/css/multi-select.css">
<script src="${ctx}/js/jQuery/jquery-2.2.3.min.js"></script>
<script src="${ctx}/js/bootstrap/js/bootstrap.min.js"></script>
<script src="${ctx}/js/adminlte/dist/js/app.min.js"></script>
<script type="text/javascript" src="${ctx}/js/pagination/jquery.pagination.js"></script>
<script type="text/javascript" src="${ctx}/js/layer/layer.js"></script>
<script type="text/javascript" src="${ctx}/js/pages/role/role_user_list.js"></script>
	<script src="${ctx}/js/multi-select/js/jquery.multi-select.js"></script>
	<script src="${ctx}/js/jquery.quicksearch.js"></script>
<script type="text/javascript">
	var ctx = "${ctx}";
</script>
<style type="text/css">
input.search-input {
    box-sizing: border-box;
    -moz-box-sizing: border-box;
    width: 100%;
    margin-bottom: 5px;
    height: auto;
}
.custom-header{
	font: 14px "Microsoft Yahei", Verdana, 宋体, sans-serif;
}
</style>
</head>
<body>

  <!-- Content Wrapper. Contains page content -->

    <!-- Main content -->
    <section class="content">
	 <div class="box box-default">
          <div class="box-header with-border">
            <h3 class="box-title">说明：</h3>
          </div>
          <div class="box-body">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${role.roleDesc }
          </div>
          <!-- /.box-body -->
        </div>
      <div class="row">
        <div class="col-xs-12">
	        <div class="box-body">
				  <button type="button" class="btn btn-default" onclick="addRole('${role.id}','${role.roleName}');" id="addRole">分配角色</button>
				  <button type="button" class="btn btn-default" onclick="cancelRole();" id="cancelRole" style="display: none;">取消分配角色</button>
	        </div>
      <input type="hidden" value="${role.id }" id="hidRoleId">
            <input type="hidden" value="${role.roleName }" id="hidRoleName">
            <!-- /.box-header -->
            <div class="box-body"  style="height: 200px;overflow-x:auto;">
            
              <table id="syslist" class="table table-bordered table-hover">
                <thead>
                <tr class="info">
                	<th>
                	<div class="checkbox"  style="margin: 0px">
					    <label>
					      <input type="checkbox"  id="chkAll">
					    </label>
					  </div>
                	</th>
                  <th>姓名</th>
                  <th>部门</th>
                  <th>职务</th>
                </tr>
                </thead>
                <tbody>
                	<c:if test="${userRoleQuery.items != null &&  userRoleQuery.items.size() > 0}">
                		<c:forEach items="${userRoleQuery.items}" var="userRole">
                			<tr >
		                		<td>
		                			<div class="checkbox" style="margin: 0px">
								    <label>
								      <input type="checkbox" id="chkUser${userRole.roleUserId}" value="${userRole.roleUserId}">
								    </label>
								  </div>
		                		</td>
		                		<td id="userName${userRole.roleUserId}">${userRole.userName }</td>
		                		<td id="userStatus${userRole.id}">
		                			${userRole.departmentName }
		                		</td>
		                		<td>${userRole.positionName }</td>
	                		</tr>
                		</c:forEach>
                		
                		
                	</c:if>
                </tbody>
              </table>
            </div>
          </div>

        </div>

    </section>

</body>
</html>