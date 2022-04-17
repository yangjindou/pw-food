import Vue from 'vue';
import Request from '@/components/luch-request/index.js';
import common from '@/common/common.js';
const http = new Request();

// 设置全局配置
http.setConfig(config => {
	config.baseURL = common.appServer;
	return config;
});

http.interceptors.request.use(config => {
	// 显示加载中动画
	uni.showLoading({
		mask: true,
		title: '加载中'
	});
	let userData = uni.getStorageSync('userData');
	if (userData && userData.token) {
		config.header['token'] = userData.token;
	}
	return config;
});

http.interceptors.response.use(response => {
	uni.hideLoading();
	return response;
}, (response) => {
	uni.hideLoading();
	if (response.statusCode === 400) {
		common.showToast(response.data.message || '出错了');
	} else if (response.statusCode === 401) {
		uni.reLaunch({
			url: '/pages/login/login',
		});
	} else {
		common.showToast(response.errMsg);
	}
	return Promise.reject(response);
})

export {
	http
}
