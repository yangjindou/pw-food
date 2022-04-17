<template>
	<gracePage headerBG="#0088FE" :bounding="false">
		<ugNav slot="gHeader" :isBack="true" title="列表"></ugNav>
		<view slot="gBody" class="grace-flex-v1" id="gBody">
			<view id="top" class="grace-body grace-bg-white">
				<ugForm ref="form" submitName="查询" :columns="formColumns" @submit="formSubmit" :submitbtn="['查询']">
				</ugForm>
			</view>
			<ugCheckList :lists="listData" :isPage="true" :batch="false" :height="mainHeight"
				@listItemTap="listItemTap">
				<view slot="page">
					<paging :total="total" :pageSize='pageSize' :first="false" :arrow="false" v-model="pageNum"
						@changes="pageChange"></paging>
				</view>
			</ugCheckList>
		</view>
	</gracePage>
</template>
<script>
	export default {
		data() {
			return {
				formColumns: [],
				mainHeight: 0,
				listData: [],
				selectIndex: [],
				formData: {},
				pageSize: 5,
				total: 0,
				pageNum: 1,
			}
		},
		onReady() {
			setTimeout(() => {
				this.computedHeight();
			}, 500);
		},
		onLoad() {
			this.getList();
			this.getformColumns();
			this.getSelectList();
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
		methods: {
			listItemTap(index) {
				uni.setStorageSync("formData", this.listData[index]["data"]);
				uni.navigateTo({
					url: './form?action=详情'
				});
			},
			async getSelectList() {
				for (var i = 0; i < this.formColumns.length; i++) {
					let e = this.formColumns[i];
					if (e.name == 'community') {
						await this.$http.get('/user/community?street='+uni.getStorageSync('userData')['street']).then(res => {
							if (res.data) {
								e.dropboxGroup = res.data.map(e => {
									return {
										value: e.community,
										label: e.community
									};
								});
							}
						});
					}
				}
				this.$refs.form.changeColumns(this.formColumns);
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
			getformColumns() {
				this.formColumns = [ {
					label: "时间",
					name: "createDate",
					type: "date",
					value: ''
				}, {
					label: "手机号",
					name: "phone",
					type: "text",
					value: ''
				},{
					label: "社区",
					name: "community",
					type: "dropbox",
					dropboxGroup: [],
					value: '',
					valueLabel: '',
				}];
			},
			pageChange(index) {
				this.pageNum = index;
				this.getList();
			},
			formSubmit(e) {
				this.formData = e;
				this.pageNum = 1;
				this.getList();
			},
			getList() {
				let params = {
					pageSize: this.pageSize,
					pageNum: this.pageNum,
					...this.formData,
					street: uni.getStorageSync('userData')['street']
				}
				this.$http.get("/report/list", {
					params
				}).then(res => {
					var data = res.data.list;
					this.total = res.data.total;
					var listData = [];
					data.some((item) => {
						let columns = [
							['街道：' + item["street"]],
							['社区：' + item["community"]],
							['详细地址：' + item["address"]],
							['手机号：' + item["phone"]],
							['检测结果：' + item["result"]],
							['时间：' + item["createDate"]],
							['检测次数：' + item["amount"]],
						];
						let title = item["name"];
						if (item.result == '已检测') {
							title = `<span style='color: green'>${title}</span>`;
							columns = columns.map(o => o.map(e =>
								`<span style='color: green'>${e}</span>`));
						} else {
							title = `<span style='color: blue'>${title}</span>`;
							columns = columns.map(o => o.map(e =>
								`<span style='color: blue'>${e}</span>`));
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
		},
	}
</script>
<style lang="less" scoped>
	.list-btn {
		text {
			margin-left: 30rpx;
		}
	}

	.del-content {
		padding: 25rpx;
		font-size: 26rpx;
		text-align: center;
		line-height: 60rpx;
		background-color: #F8F8F8;
		display: block;
	}

	.grace-dialog-buttons {
		width: 700rpx;
		line-height: 88rpx;
		height: 88rpx;
		display: block;
		overflow: hidden;
		text-align: center;
		font-size: 26rpx;
		color: #999999;
	}
</style>
