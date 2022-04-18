<template>
	<gracePage headerBG="#0088FE" :bounding="false">
		<ugNav slot="gHeader" :isBack="false" title="信息填报"></ugNav>
		<view slot="gBody" class="grace-flex-v1" id="gBody">
			<view id="top" class="grace-body grace-bg-white">
				<!-- <ugForm submitName="查询" :columns="formColumns" @submit="formSubmit"></ugForm> -->
			</view>
			<view class="">
				<ugCheckList :lists="listData" @change="checkChange" :isPage="true" :batch="true" :height="mainHeight">
					<view slot="page">
						<paging :total="total" :pageSize='pageSize' :first="false" :arrow="false" v-model="pageNum"
							@changes="pageChange"></paging>
					</view>
					<view class="grace-nowrap grace-flex-end list-btn">
						<text class="grace-blue grace-icons" @tap="action('详情')">详情</text>
						<text class="grace-blue grace-icons icon-article" @tap="action('修改')">修改</text>
						<text class="grace-blue grace-icons icon-add" @tap="action('新增')">上报</text>
					</view>
				</ugCheckList>
			</view>
			<graceDialog ref="delDialog" title="警告" :isCloseBtn="false">
				<view class="del-content" slot="content">
					<text>确定删除所选的数据？</text>
				</view>
				<view slot="btns" class="grace-space-between">
					<text class="grace-dialog-buttons" @tap="dialogTap(false)">取消</text>
					<text class="grace-dialog-buttons grace-blue" @tap="dialogTap(true)">确认</text>
				</view>
			</graceDialog>
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
			this.computedHeight();
		},
		onLoad() {
			const userWechat = uni.getStorageSync('userWechat');
			if (userWechat) {
				this.getList();
				this.getformColumns();
			} else {
				uni.reLaunch({
					url: './user',
				});
			}
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
				this.formColumns = [{
					label: "时间",
					name: "createDate",
					type: "date",
					value: ''
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
					createUser: uni.getStorageSync('userWechat')['id']
				}
				this.$http.get("/report/list", {
					params
				}).then(res => {
					const data = res.data.list;
					this.total = res.data.total;
					let listData = [];
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
			action(action) {
				if (action == "删除") {
					if (this.selectIndex.length == 0) {
						uni.showToast({
							title: "请选择需要删除的数据",
							icon: "none"
						});
						return;
					}
					this.$refs.delDialog.open();
					return;
				} else if (['修改', '详情'].includes(action)) {
					if (this.selectIndex.length != 1) {
						uni.showToast({
							title: `请选择一条${action}的数据`,
							icon: "none"
						});
						return;
					}
					const formData = this.listData[this.selectIndex[0]]["data"];
					if (action === '修改') {
						let createDate = formData.createDate.substr(0, 10);
						let nowDate = this.$common.formatDate(new Date(), 'yyyy-MM-dd');
						if (createDate != nowDate) {
							this.$common.showToast('仅允许修改当日的数据！');
							return;
						}
						if (formData['result'] === '已检测') {
							this.$common.showToast('已确认检测，无法修改！');
							return;
						}
					}
					uni.setStorageSync("formData", formData);
					uni.navigateTo({
						url: './form?action=' + action
					});
				} else {
					let params = {
						createDate: this.$common.formatDate(new Date(), 'yyyy-MM-dd'),
						createUser: uni.getStorageSync('userWechat')['id'],
					}
					this.$http.get("/report/list", {
						params
					}).then(res => {
						if (res.data.length > 0) {
							this.$common.showToast('今日已上报！');
						} else {
							const formData = {
								street: uni.getStorageSync('userWechat')['street'],
								community: uni.getStorageSync('userWechat')['community'],
							}
							uni.setStorageSync("formData", formData);
							uni.navigateTo({
								url: './form?action=' + action
							});
						}
					});
				}
			},
			checkChange(e) {
				this.selectIndex = e;
			},
			dialogTap(b) {
				if (b) {
					var tabledata = [];
					for (var i = 0; i < this.selectIndex.length; i++) {
						tabledata.push(this.listData[this.selectIndex[i]]["data"]["id"]);
					}
					var json = {
						table: "list",
						tabledata: tabledata
					}
					let datas = {
						action: "D",
						v_json: JSON.stringify(json)
					}
					this.$http.post("singleJsonbatch", datas).then(res => {
						if (res.data.code != "200") {
							uni.showToast({
								icon: 'none',
								position: 'center',
								title: res.data.msg
							});
						} else {
							uni.showToast({
								title: "删除成功",
								icon: "none"
							});
							setTimeout(() => {
								this.getList();
							}, 1500)
						}
					});
				}
				this.$refs.delDialog.hide();
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

	.text-green {
		color: green;
	}

	.text-blue {
		color: blue;
	}
</style>
