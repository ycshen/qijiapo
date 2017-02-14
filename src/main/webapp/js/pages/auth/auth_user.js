
function cancelAuth(){
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
	var authList = $("#hidAuthStr").val();
	var notAuthList = $("#hidNotAuthStr").val();
	var notAuthCount = $("#hidNotAuthCount").val();
	var authCount = $("#hidAuthCount").val();
	$('#custom-headers').multiSelect({
		  selectableHeader: "<input type='text' class='search-input' autocomplete='off' placeholder='搜索未授权员工'>",
		  selectionHeader: "<input type='text' class='search-input' autocomplete='off' placeholder='搜索已授权员工'>",
		  selectableFooter: "<div class='custom-header'>未授权员工【<span id='spanNotAuth'>" + notAuthCount + "</span>】</div>",
		  selectionFooter: "<div class='custom-header'>已授权员工【<span id='spanAuth'>" + authCount + "</span>】</div>",
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
			  if(isNotBlank(authList)){
				  authList = userId;
			  }else{
				  authList += "^" +  userId;
			  }
			  if(isNotBlank(notAuthList)){
				  var notAuthArr = notAuthList.split("^");
				  notAuthList = "";
				  for(var i=0; i< notAuthArr.length;i++){
					  var authUserId = notAuthArr[i];
					  if(userId != authUserId){
						  notAuthList += authUserId + "^";
					  }
				  }
				  if(isNotBlank(notAuthList)){
					  notAuthList = notAuthList.substring(0, notAuthList.length - 1)
				  }
			  }

			  $("#hidAuthStr").val(authList);
			  $("#hidNotAuthStr").val(notAuthList);
		  },
		  afterDeselect: function(userId){
			  this.qs1.cache();
			  this.qs2.cache();	
			  if(isNotBlank(notAuthList)){
				  notAuthList = userId;
			  }else{
				  notAuthList += "^" +  userId;
			  }
			  
			  if(isNotBlank(authList)){
				  var authArr = authList.split("^");
				  authList = "";
				  for(var i=0; i< authArr.length;i++){
					  var authUserId = authArr[i];
					  if(userId != authUserId){
						  authList += authUserId + "^";
					  }
				  }
				  
				  if(isNotBlank(authList)){
					  authList = authList.substring(0, authList.length - 1)
				  }
			  }
			  
			  $("#hidAuthStr").val(authList);
			  $("#hidNotAuthStr").val(notAuthList);
		  }
		});    

});

function addAuth(authId){
	var authList = $("#hidAuthStr").val();
	var notAuthList = $("#hidNotAuthStr").val();
	var url = ctx + "/inner/auth/addAuth?authStr=" + authList + "&notAuthStr=" + notAuthList + "&authId=" + authId;
	$.ajax({
		type: "get",
		url: url,
		success: function(){
			
		}
	})
}
