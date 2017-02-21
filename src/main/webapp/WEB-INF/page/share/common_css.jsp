<%@ page language="java" pageEncoding="UTF-8"%>
 <!-- Bootstrap 3.3.6 -->
  <link rel="stylesheet" href="${ctx}/js/bootstrap/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="${ctx}/js/font-awesome-4.7.0/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="${ctx}/js/ionicons-2.0.1/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="${ctx}/js/adminlte/dist/css/AdminLTE.min.css">
  <link rel="stylesheet" href="${ctx}/js/adminlte/dist/css/skins/skin-black.min.css">
<link href="${ctx}/js/pagination/pagination.css" rel="stylesheet">

   	  <script type="text/javascript">
			function changeCollapse(){
				$.ajax({
					url: "${ctx}/inner/user/changeCollapse",
					type: 'get',
					success: function(){
						
						}
				});
			}
   	  </script>