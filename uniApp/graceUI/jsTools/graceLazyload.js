
var graceLazyWinHeight = 500;
var graceLazyTimer = null;
module.exports = {
	load : function(top, _page){
		if (graceLazyTimer != null){clearTimeout(graceLazyTimer);}
		graceLazyTimer = setTimeout(function(){
			uni.getSystemInfo({
				success: function (res) {
					graceLazyWinHeight = res.windowHeight;
					//遍历img
					uni.createSelectorQuery().selectAll('.grace-img-lazy').fields(
						{
							rect: true,
						}, function (res) {
							var isShow = [];
							//检查图片是否在窗口可视区域
							for (var i = 0; i < res.length; i++) {
								if (res[i].top <= graceLazyWinHeight + top) {
									isShow[i] = true;
								} else {
									break;
								}
							}
							_page.isShow = isShow;
						}
					).exec();
				}
			});
		}, 200);
	}
}