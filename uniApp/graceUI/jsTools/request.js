

module.exports = {
	
	get : function(url, data, callback, headers){
		uni.request({
			url      : url,
			data     : data,
			method   : "GET",
			dataType : "json",
			header   : headers,
			success  : (res) => {callback(res.data);},
			fail     : () => {uni.showToast({title:"网络请求失败", icon:"none"});}
		});
	},
	
	post : function(url, data, contentType, headers, callback){
		switch(contentType){
			case "form" :
				var headerObj = {'content-type' : 'application/x-www-form-urlencoded'};
			break;
			case "json" : 
				var headerObj = {'content-type' : 'application/json'};
			break;
			default :
				var headerObj = {'content-type' : 'application/json'};
		}
		for(var k in headers){headerObj[k] = headers[k];}
		uni.request({
			url      : url,
			data     : data,
			method   : "POST",
			dataType : "json",
			header   : headerObj,
			success  : (res) => {callback(res.data);},
			fail     : () => {uni.showToast({title:"网络请求失败", icon:"none"});}
		});
	}
}