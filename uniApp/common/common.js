module.exports = {
	// api 基础路径地址
	// appServer: 'http://182.92.124.118:9090/qrcode/',
	// fileServer: 'http://182.92.124.118:9090/file/',
	appServer: 'https://www.shanghaiyly.com/qrcodeReport/',
	fileServer: 'http://192.168.1.69:8090/file',
	
	
	formatDate(date, fmt) {
		if (/(y+)/.test(fmt)) {
			fmt = fmt.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length));
		}
		let o = {
			'M+': date.getMonth() + 1,
			'd+': date.getDate(),
			'h+': date.getHours(),
			'm+': date.getMinutes(),
			's+': date.getSeconds()
		};
		for (let k in o) {
			if (new RegExp(`(${k})`).test(fmt)) {
				let str = o[k] + '';
				fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? str : ('00' + str).substr(str.length));
			}
		}
		return fmt;
	},
	showToast(title) {
		uni.showToast({
			title,
			icon: "none"
		});
	}
}
