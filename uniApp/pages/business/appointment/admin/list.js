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
			if (this.formData['warehousingDate']) {
				const warehousingDate = this.formData['warehousingDate'].split(' ~ ');
				this.formData["warehousingDateStart"] = warehousingDate[0] + ' 00:00:00';
				this.formData["warehousingDateEnd"] = warehousingDate[1] + ' 23:59:59';
				delete this.formData["warehousingDate"];
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
					let columns = [
						[`备案状态：${item["filingState"]}`],
						[`预约监管仓：${item["warehouseName"]}`],
						[`入仓时间：${item["warehousingDate"]}`],
						[`货物类别：${item["goodTypeName"]}`],
						[`货物名称：${item["goodName"]}`],
						[`货物来源：${item["goodSourceName"]}`],
						[`车牌号：${item["carNumber"]}`],
					];
					let title = item["filingOrder"];
					if (item['emergency']) {
						title = `<span style='color: red'>${title}</span>`;
						columns = columns.map(o => o.map(e =>
							`<span style='color: red'>${e}</span>`));
					}
					listData.push({
						checked: false,
						title,
						columns,
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
