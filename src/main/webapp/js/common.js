/**
 * ajax调用
 * 
 * @param url 链接地址
 * @param data 数据
 * @param callbackfun 回调函数
 * @param method 调用方法，默认为post
 * @param dataType 返回参数类型,默认为json
 */
function commitData(url, data, callbackfun, method, dataType) {
	if (method == null) {
		// 默认post方式
		method = "post";
	}
	if (dataType == null) {
		// 默认json数据类型
		dataType = "json";
	}
	$.ajax({
		url : url,
		dataType : dataType,
		type : method,
		data : data,
		jsonp : "callback",
		headers : {
			"returntype" : "ajax/json"
		},
		traditional : true,
		success : function(data) {
			callbackfun.complete && callbackfun.complete();
			if (data.code >= 10000 && data.code < 11000) {
				if (data.code == 10000) {
					top.location.href = "/login.htm";
					return;
				}
				top.dialog({
					quickClose : true,
					content : data.description
				}).showModal();
			}
			callbackfun.success(data);
		},
		error : callbackfun.error || function(XMLHttpRequest, textStatus, errorThrown){
			callbackfun.complete && callbackfun.complete();
			console.log("error code is:"+XMLHttpRequest.status);
		}
	});
}