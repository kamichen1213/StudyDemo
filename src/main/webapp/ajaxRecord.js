$(document).ready(function (){
	console.log("[ConsoleLog]:Doc init with ajaxRequestRecordMethod!");	
	ajaxRecord();	
});

function ajaxRecord(){
var recordPo = {
    actionId : $(document).Attr("title"),
    employeeId : $('#username').val(),
    operateType : "page"
};

$.ajax({
      type:'POST',
      url:"http://192.168.45.10:8080/myPvReport/reqReciver",
      data:recordPo,      
      dataType:"jsonp",
      crossDomain:true,
      jsonpCallback:"cb",
      success:function recordSuccess(data){
    	  console.log("[ConsoleLog]:Record Success!");
    	  console.log("[ConsoleLog]:"+data);
      },
      error: function(XMLHttpRequest, textStatus, errorThrown) {
    	  consol.log("requestStatus:"+XMLHttpRequest.status+",textStatus:"+textStatus);
      },
    });
}
