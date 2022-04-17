<template>
	<gracePage headerBG="#0088FE" :bounding="false">
		<ugNav slot="gHeader" :isBack="true" title="列表"></ugNav>
		<view slot="gBody" class="grace-flex-v1" id="gBody">
			<view id="top" class="grace-body grace-bg-white">
				<ugForm submitName="查询" :columns="formColumns" @submit="formSubmit" :submitbtn="['查询']">
					<slot>
						<view class="grace-form-item grace-border-b">
							<text class="grace-form-label">街道</text>
							<view class="grace-form-body">
								<ugDropbox name="type" :dropboxGroup="streetList" @dropboxChange="streetChange">
									<input type="text" v-model="formData2.street" name="street"
										disabled class="grace-form-input" />
									<text class="grace-icons icon-arrow-down" style="margin-left:5px;"></text>
								</ugDropbox>
							</view>
						</view>
						<view class="grace-form-item grace-border-b">
							<text class="grace-form-label">社区</text>
							<view class="grace-form-body">
								<ugDropbox name="type" :dropboxGroup="communityList" @dropboxChange="communityChange">
									<input type="text" v-model="formData2.community" name="community"
										disabled class="grace-form-input" />
									<text class="grace-icons icon-arrow-down" style="margin-left:5px;"></text>
								</ugDropbox>
							</view>
						</view>
					</slot>
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
				formData2: {
					street: '',
					community: '',
				},
				streetList: [],
				communityList: [],
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
			this.getStreet();
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
			getStreet() {
				this.$http.get("/user/street").then(res => {
					if (res.data) {
						this.streetList = res.data.map(e => {
							return {
								value: e.id,
								label: e.street
							};
						});
					}
				});
			},
			streetChange(index) {
				this.formData2.street = this.streetList[index].label;
				this.formData2.community = '';
				this.$http.get("/user/community?street=" + this.formData2.street).then(res => {
					if (res.data) {
						this.communityList = res.data.map(e => {
							return {
								value: e.id,
								label: e.community
							};
						});
					}
				});
			},
			communityChange(index) {
				this.formData2.community = this.communityList[index].label;
			},
			listItemTap(index) {
				uni.setStorageSync("formData", this.listData[index]["data"]);
				uni.navigateTo({
					url: './form?action=详情'
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
			getformColumns() {
				this.formColumns = [{
					label: "时间",
					name: "createDate",
					type: "date",
					value: ''
				}, {
					label: "手机号",
					name: "phone",
					type: "text",
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
					...this.formData2,
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
