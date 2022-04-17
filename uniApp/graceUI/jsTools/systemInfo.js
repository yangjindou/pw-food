
module.exports = {
	info : function () {
		try {
		    var res = uni.getSystemInfoSync();
			var iPhoneXBottom = 0;
			res.model = res.model.replace(' ', '');
			res.model = res.model.toLowerCase();
			var res1  = res.model.indexOf('iphonex');
			if(res1 > 5){res1 = -1;}
			var res2 = res.model.indexOf('iphone1');
			if(res2 > 5){res2 = -1;}
			if(res1 != -1 || res2 != -1){
				res.iPhoneXBottomHeightRpx = 50;
				res.iPhoneXBottomHeightPx = uni.upx2px(50);
			}else{
				res.iPhoneXBottomHeightRpx = 0;
				res.iPhoneXBottomHeightPx  = 0;
			}
			return res;
		} catch (e){
		    return null;
		}
	}
}