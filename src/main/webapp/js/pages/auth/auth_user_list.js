$(function(){
	initAllCheckEvt();
});

function addAuth(){
	var html = $("#multiDiv");
	layer.open({
		  type: 1,
		  title: false,
		  closeBtn: 0,
		  shadeClose: true,
		  skin: 'yourclass',
		  area: ['600px', '300px'],
		  content: html
		});
}
function initAllCheckEvt(){
	$("#chkAll").change(function(){
		if($("#chkAll").is(':checked')) {
		    $("#cancelAuth").show();
		    $("#addAuth").hide();
		    $("input:checkbox").each(function () {     
	            $(this).prop('checked', true);//  
	  
	        });  
		   
		}else{
			$("#cancelAuth").hide();
		    $("#addAuth").show();
		    $("input:checkbox").removeAttr("checked");  
		}
	});
	
	 $("input:checkbox").change(function(){
		 var idValue = $(this).attr("id");
		if(idValue != "chkAll"){
			if($("#chkAll").is(':checked')){
				var chkArray = $("input:checkbox");
				var isCheckedAll = true;
				var size = chkArray.length;
				for(var i=0;i < size;i++ ){
					var isChkAll = chkArray[i].id;
					if( isChkAll  != "chkAll" && chkArray[i].checked){
						isCheckedAll = false;
						break;
					}
				}
				
				if(!isCheckedAll){
					$("#chkAll").removeAttr("checked");  
				}
			}
			
			var chkArray = $("input:checkbox");
			var size = chkArray.length;
			var isShowAddAuth = "0";
			for(var i=0;i < size;i++ ){
				var isChkAll = chkArray[i].id;
				if( isChkAll  != "chkAll" && chkArray[i].checked){
					isShowAddAuth += "1";
				}
			}
			
			if(isShowAddAuth.indexOf("1") > 0){
				 $("#cancelAuth").show();
				 $("#addAuth").hide(); 
			}else{
				$("#cancelAuth").hide();
			    $("#addAuth").show();
			}
		}
	 })
}

