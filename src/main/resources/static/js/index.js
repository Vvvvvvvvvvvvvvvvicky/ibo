function del(){
  alert("OK")
}
function changShowStatus(id){
	$.ajax({
		type:"GET",
		dataType:"json",
		url:"/changeShowStatus",
		data:{"id":id},
		success:function(result){
			alert(result.code);
			var resultJson = JSON.stringify(result);  
			alert(resultJson);
			if(result.code=="200"){
				alert("success:"+result.code);
				
			}else{
				alert("异常:"+result.id);
			}
		},
		error:function(){
			alert("异常");
		}
	});
}
//<!--${item["isShow"]?c}-->
