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
			if(this.formData['warehousedDate']) {
				const warehousedDate = this.formData['warehousedDate'].split(' ~ ');
				this.formData["warehousedDateStart"] = warehousedDate[0] + ' 00:00:00';
				this.formData["warehousedDateEnd"] = warehousedDate[1] + ' 23:59:59';
				delete this.formData["warehousedDate"];
			}
			let params = {
				pageSize: this.pageSize,
				pageNum: this.pageNum,
				...this.formData,
				...this.basicParams,
			}
			this.$http.get("/appointment/list", {
				params
			}).then(res => {
				const data = res.data.list;
				this.total = res.data.total;
				const listData = [];
				data.some((item) => {
					const allWeight = item['warehousedWeightPoultry'] + item['warehousedWeightLivestock'] + item['warehousedWeightAquatic'] + item['warehousedWeightOther'];
					listData.push({
						checked: false,
						title: item["filingOrder"],
						columns: [
							[`监管仓：${item["warehouseName"]}`],
							[`出仓时间：${item["warehousedDate"]}`],
							[`总重量（Kg）：${allWeight}`],
							[`禽类（Kg）：${item["warehousedWeightPoultry"]}`],
							[`畜类（Kg）：${item["warehousedWeightLivestock"]}`],
							[`水产品（Kg）：${item["warehousedWeightAquatic"]}`],
							[`其他（Kg）：${item["warehousedWeightOther"]}`],
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
			this.$http.delete(`/appointment?ids=${ids.join(',')}`).then(res => {
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
