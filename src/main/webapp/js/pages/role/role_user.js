
function cancelRole(){
	parent.layer.closeAll();
	
}

function isNotBlank(args){
	var result = false;
	if(args != "" && args != null && args != undefined){
		result = true;
	}
	
	return result;
}
$(function(){
	var roleList = $("#hidRoleStr").val();
	var notRoleList = $("#hidNotRoleStr").val();
	var notRoleCount = $("#hidNotRoleCount").val();
	var roleCount = $("#hidRoleCount").val();
	$('#custom-headers').multiSelect({
		  selectableHeader: "<input type='text' class='search-input' autocomplete='off' placeholder='搜索未分配员工'>",
		  selectionHeader: "<input type='text' class='search-input' autocomplete='off' placeholder='搜索已分配员工'>",
		  selectableFooter: "<div class='custom-header'>未分配员工【<span id='spanNotRole'>" + notRoleCount + "</span>】</div>",
		  selectionFooter: "<div class='custom-header'>已分配员工【<span id='spanRole'>" + roleCount + "</span>】</div>",
		  afterInit: function(ms){
		    var that = this,
		        $selectableSearch = that.$selectableUl.prev(),
		        $selectionSearch = that.$selectionUl.prev(),
		        selectableSearchString = '#'+that.$container.attr('id')+' .ms-elem-selectable:not(.ms-selected)',
		        selectionSearchString = '#'+that.$container.attr('id')+' .ms-elem-selection.ms-selected';

		    that.qs1 = $selectableSearch.quicksearch(selectableSearchString)
		    .on('keydown', function(e){
		      if (e.which === 40){
		        that.$selectableUl.focus();
		        return false;
		      }
		    });

		    that.qs2 = $selectionSearch.quicksearch(selectionSearchString)
		    .on('keydown', function(e){
		      if (e.which == 40){
		        that.$selectionUl.focus();
		        return false;
		      }
		    });
		  },
		  afterSelect: function(userId){
			  this.qs1.cache();
			  this.qs2.cache();
			  if(isNotBlank(roleList)){
				  roleList += "^" +  userId;
			  }else{
				  roleList = userId;
			  }
			  if(isNotBlank(notRoleList) && notRoleList.indexOf("^") > 0){
				  var notRoleArr = notRoleList.split("^");
				  notRoleList = "";
				  for(var i=0; i< notRoleArr.length;i++){
					  var roleUserId = notRoleArr[i];
					  if(userId != roleUserId){
						  notRoleList += roleUserId + "^";
					  }
				  }
				  if(isNotBlank(notRoleList)){
					  notRoleList = notRoleList.substring(0, notRoleList.length - 1)
				  }
			  }else{
				  notRoleList = "";
			  }
			  
			  var noCount = $("#spanNotRole").html();
			  var count = $("#spanRole").html();
			  
			  $("#spanNotRole").html(Number(noCount) - 1);
			  $("#spanRole").html(Number(count) + 1)
			  $("#hidRoleStr").val(roleList);
			  $("#hidNotRoleStr").val(notRoleList);
		  },
		  afterDeselect: function(userId){
			  this.qs1.cache();
			  this.qs2.cache();	
			  if(isNotBlank(notRoleList)){
				  notRoleList += "^" +  userId;
			  }else{
				  notRoleList = userId;
			  }
			  
			  if(isNotBlank(roleList) && roleList.indexOf("^") > 0){
				  var roleArr = roleList.split("^");
				  roleList = "";
				  for(var i=0; i< roleArr.length;i++){
					  var roleUserId = roleArr[i];
					  if(userId != roleUserId){
						  roleList += roleUserId + "^";
					  }
				  }
				  
				  if(isNotBlank(roleList)){
					  roleList = roleList.substring(0, roleList.length - 1)
				  }
			  }else{
				  roleList = "";
			  }
			  
			  var noCount = $("#spanNotRole").html();
			  var count = $("#spanRole").html();
			  
			  $("#spanNotRole").html(Number(noCount) + 1);
			  $("#spanRole").html(Number(count) - 1)
			  $("#hidRoleStr").val(roleList);
			  $("#hidNotRoleStr").val(notRoleList);
		  }
		});    

});

function addRole(roleId){
	var roleList = $("#hidRoleStr").val();
	var notRoleList = $("#hidNotRoleStr").val();
	var url = ctx + "/inner/role/addRole?roleStr=" + roleList + "&notRoleStr=" + notRoleList + "&roleId=" + roleId;
    $.ajax({
		type: "get",
		url: url,
		success: function(result){
			if(result == 1){
				layer.alert("分配员工成功", function(){
					
					parent.reloadRoleUser(roleId);
					parent.layer.closeAll();
				})
			}else{
				layer.alert("分配员工失败")
			}
		}
	})
}
