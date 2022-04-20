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
			this.$http.get("/disinfectant/list", {
				params
			}).then(res => {
				const data = res.data.list;
				this.total = res.data.total;
				const listData = [];
				data.some((item) => {
					listData.push({
						checked: false,
						title: item["name"],
						columns: [
							[`消毒液配比比例：${item["ratio"]}`],
							[`调配时间：${item["deploymentDate"]}`],
							[`调配人：${item["deploymentUser"]}`],
							[`消毒液浓度：${item["concentration"]}`],
							[`消杀评估：${item["assessment"]}`],
							[`录入人：${item["createUserName"]}`],
						],
						data: item
					});
				})
				this.listData = listData;
				this.selectIndex = [];
			});
		},
		delOk() {
			const ids = this.selectIndex.map(i => this.listData[i]["data"]['id']);
			this.$http.delete(`/disinfectant?ids=${ids.join(',')}`).then(res => {
				if (res) {
					this.$refs.dialog.hide();
					this.$common.showToast('删除成功');
					this.selectIndex = [];
					setTimeout(() => {
						this.getList();
					}, 1500);
				}
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
