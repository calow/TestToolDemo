function toolAction(){
	$.ajax({
		url : requestUrl + "&toolAction=" + "test",
		success : function(data) {
			alert("success = " + data);
		},
		error : function(data){
			alert("error = " + data);
		}
	});
}

function runTool4ward(){
	var a = document.createElement('a');
	a.href = forwardUrl + "&path=index.jsp";
	document.body.appendChild(a);
	a.click();
//	$.ajax({
//		url : forwardUrl + "&path=index.jsp",
//		success : function(data) {
//			//alert("success = " + data);
//		},
//		error : function(data){
//			alert("error = " + data);
//		}
//	});
}