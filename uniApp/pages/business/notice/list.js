import {
	data,
	methods
} from '@/utils/tableUtils';
export default {
	data() {
		return {
			...data,
			basicParams: {},
			pageSize: 10,
			mainHeight: 0,
		}
	},
	methods: {
		...methods,
		getList() {
			let params = {
				pageSize: this.pageSize,
				pageNum: this.pageNum,
				...this.formData,
				...this.basicParams,
			}
			this.$http.get("/notice/list", {
				params
			}).then(res => {
				const data = res.data.list;
				this.total = res.data.total;
				const listData = [];
				data.some((item) => {
					listData.push({
						checked: false,
						title: item["title"],
						columns: [
							[`公告人：${item["createUserName"]}`],
							[`公告时间：${item["createDate"]}`],
						],
						data: item
					});
				})
				this.listData = listData;
				this.selectIndex = [];
			});
		},
		computedHeight() {
			uni.createSelectorQuery().select('#gBody').fields({
				size: true
			}, (res) => {
				uni.createSelectorQuery().select('#top').fields({
					size: true
				}, (topRes) => {
					this.mainHeight = res.height - topRes.height;
				}).exec();
			}).exec();
		},
	},
	onReady() {
		setTimeout(() => {
			this.computedHeight();
		}, 500);
	},
	onShow() {
		if (uni.getStorageSync("msg")) {
			this.$common.showToast(uni.getStorageSync("msg"));
			uni.removeStorageSync("msg");
			setTimeout(() => {
				this.getList();
			}, 1500)
		}
	},
}
