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
<script src="${ctx}/js/jQuery/jquery-2.2.3.min.js"></script>
<script src="${ctx}/js/bootstrap/js/bootstrap.min.js"></script>
<script src="${ctx}/js/adminlte/dist/js/app.min.js"></script>
<script type="text/javascript" src="${ctx}/js/pagination/jquery.pagination.js"></script>
<script type="text/javascript" src="${ctx}/js/layer/layer.js"></script>
<script type="text/javascript" src="${ctx}/js/pages/user/user_page_list.js"></script>
<script type="text/javascript">
	var ctx = "${ctx}";
</script>
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
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${auth.authDesc }
          </div>
          <!-- /.box-body -->
        </div>
      <div class="row">
        <div class="col-xs-12">
	        <div class="box-body">
	          	<form class="form-inline" role="form">
				  <button type="button" class="btn btn-default" onclick="adduser();">添加授权</button>
				</form>
	        </div>
      
            <!-- /.box-header -->
            <div class="box-body">
              <table id="syslist" class="table table-bordered table-hover">
                <thead>
                <tr class="info">
                  <th>姓名</th>
                  <th>部门</th>
                  <th>职务</th>
                </tr>
                </thead>
                <tbody>
                	<c:if test="${userQuery.items != null &&  userQuery.items.size() > 0}">
                		<c:forEach items="${userQuery.items}" var="user">
                			<tr>
		                		
		                		<td id="userName${user.id}">${user.userName }</td>
		                		<td id="userStatus${user.id}">
		                			${user.departmentName }
		                		</td>
		                		<td>${user.positionName }</td>
	                		</tr>
                		</c:forEach>
                	</c:if>
                </tbody>
                
                 
               
              </table>
            
            </div>
            <div class="box-header" style="text-align:center">
            	<%-- <%@include file="user_page_list_page.jsp" %>
					<input type="hidden" value="${userQuery.count}" id="count"/> --%>
					
            </div>
          </div>

        </div>

    </section>



</body>
</html>