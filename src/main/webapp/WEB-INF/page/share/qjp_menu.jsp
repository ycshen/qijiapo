<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">

    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">

      <!-- Sidebar user panel (optional) -->
      <div class="user-panel">
        <div class="pull-left image">
          <img src="${ctx}/js/adminlte/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p>${loginUser.userName}</p>
          <!-- Status -->
          <a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
        </div>
      </div>

      <!-- search form (Optional) -->
      <form action="#" method="get" class="sidebar-form">
        <div class="input-group">
          <input type="text" name="q" class="form-control" placeholder="Search...">
              <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                </button>
              </span>
        </div>
      </form>
      <!-- /.search form -->

      <!-- Sidebar Menu -->
      <ul class="sidebar-menu">
      	<c:if test="${menuList != null && menuList.size() > 0 }">
	        <c:forEach var="menu" items="${menuList}">
	        	 <c:choose>
	        	 	<c:when test="${menu.menuUrl !='#' && menu.menuType == 3}">
	        	 		<li  class="treeview"><a href="${ctx}${menu.menuUrl}"><i class="fa fa-link"></i> <span>${menu.menuName}</span></a></li>
	        	 	</c:when>
	        	 	<c:otherwise>
		        	 	<li class="treeview">
		        	 		<c:if test="${menu.menuUrl =='#' && menu.menuType == 3}">
		        	 		
						          <a href="#">
						            <i class="fa fa-dashboard"></i> <span>${menu.menuName}</span>
						            <span class="pull-right-container">
						              <i class="fa fa-angle-left pull-right"></i>
						            </span>
						          </a>
						        <ul class="treeview-menu">
		        	 			 <c:forEach var="submenu" items="${menuList}">
		        	 			 	<c:if test="${submenu.menuType == 7 && submenu.parentMenuId == menu.id}">
		        	 			 		 <li><a href="${ctx}${submenu.menuUrl}"><i class="fa fa-circle-o"></i>${submenu.menuName}</a></li>
		        	 			 	</c:if>
		        	 			 </c:forEach>
						           
						    </ul>  
		        	 		</c:if>
		        	 		
						  </li>
	        	 	</c:otherwise>
	        	 </c:choose>
	        	 
	        	 
	        </c:forEach>
        </c:if>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-dashboard"></i> <span>CRM</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="${ctx}/inner/competitor/list"><i class="fa fa-circle-o"></i>客户</a></li>
            <li><a href="${ctx}/inner/competitor/list"><i class="fa fa-circle-o"></i>销售机会</a></li>
            <li><a href="${ctx}/inner/competitor/list"><i class="fa fa-circle-o"></i>联系人</a></li>
            <li><a href="${ctx}/inner/competitor/list"><i class="fa fa-circle-o"></i>合同</a></li>
            <li><a href="${ctx}/inner/competitor/list"><i class="fa fa-circle-o"></i>市场活动</a></li>
            <li><a href="${ctx}/inner/competitor/list"><i class="fa fa-circle-o"></i>销售线索</a></li>
            <li><a href="${ctx}/inner/competitor/list"  class="active"><i class="fa fa-circle-o"></i>竞争对手</a></li>
            <li><a href="${ctx}/inner/competitor/list"><i class="fa fa-circle-o"></i>产品</a></li>
            <li><a href="${ctx}/inner/competitor/list"><i class="fa fa-circle-o"></i>目标</a></li>
            <li><a href="${ctx}/inner/competitor/list"><i class="fa fa-circle-o"></i>回款</a></li>
            <li><a href="${ctx}/inner/competitor/list"><i class="fa fa-circle-o"></i>活动记录</a></li>
          </ul>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-dashboard"></i> <span>CRM统计分析</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="${ctx}/inner/competitor/list"><i class="fa fa-circle-o"></i>销售绩效分析</a></li>
            <li><a href="${ctx}/inner/competitor/list"><i class="fa fa-circle-o"></i>系统使用概况</a></li>
            <li><a href="${ctx}/inner/competitor/list"><i class="fa fa-circle-o"></i>报表</a></li>
            <li><a href="${ctx}/inner/competitor/list"><i class="fa fa-circle-o"></i>工作报告统计</a></li>
            <li><a href="${ctx}/inner/competitor/list"><i class="fa fa-circle-o"></i>考勤统计</a></li>
            <li><a href="${ctx}/inner/competitor/list"><i class="fa fa-circle-o"></i>仪表盘</a></li>
          </ul>
        </li>
      </ul>
      <!-- /.sidebar-menu -->
    </section>
    <!-- /.sidebar -->
  </aside>
