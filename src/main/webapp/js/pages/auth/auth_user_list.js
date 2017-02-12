$(function(){
	initAllCheckEvt();
});

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
			
		}
	 })
}

