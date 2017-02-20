function addMemo(){
	var url = ctx + "/inner/memo/addMemo";
	layer.open({
		type: 2,
		title: '添加行程',
		shadeClose: true,
		shade: 0.8,
		area: ['600px', '500px'],
		content: url
	});
}

function addSuccess(memoName, startTime, endTime, id){
	layer.closeAll();
}

$(function () {
	$.ajax({
        url: ctx + "/inner/memo/getEvents?startMonth=2017-02",
        type: "get",
        success: function(json) { // 获取当前月的数据
            /*var events = [];
            events.push(json);
            callback(events);*/
      	  alert(json)
        }
    });
    $('#calendar').fullCalendar({
    	 buttonText: {
   	        today: '今天',
   	        month: '月视图',
   	        week: '周视图',
   	        day: '日视图'
   	    },
   	    allDayText: "全天",
   	    timeFormat: 'H:mm', 
   	    weekMode: "fixed",
   	    columnFormat: {
   	        month: 'dddd',
   	        week: 'dddd',
   	        day: 'dddd'
   	    },
	   	 titleFormat: {  
	         month: 'YYYY年MM月',  
	         week: 'YYYY年MM月DD日',  
	         day: 'YYYY年MM月DD日 dddd'  
	     },
   	    monthNames: ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"],
   	    dayNames: ["星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"],
   	    header: {
   	        left: 'prev,next today',
   	        center: 'title',
   	        right: 'month,agendaWeek,agendaDay'
   	    },
   	   editable: false,
   	   eventStartEditable: false,
       events:  function(start,end,timezone, callback) {
          var date = this.getDate().format('YYYY-MM');
          $.ajax({
              url: ctx + "/inner/memo/getEvents?startMonth" + start,
              type: "get",
              success: function(json) { // 获取当前月的数据
                  /*var events = [];
                  events.push(json);
                  callback(events);*/
            	  alert(json)
              }
          });
      }
    });

   
  });