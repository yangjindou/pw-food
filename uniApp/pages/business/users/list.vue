<template>
	<gracePage headerBG="#0088FE" :bounding="false">
		<ugNav slot="gHeader" :isBack="true" title="用户管理"></ugNav>
		<view slot="gBody" class="grace-flex-v1" id="gBody">
			<view class="">
				<ugCheckList :lists="listData" @change="change" :isPage="true" :batch="true" :height="mainHeight">
					<view slot="page">
						<paging :total="total" :pageSize='pageSize' :first="false" :arrow="false" v-model="pageNum" @changes="pageChange"></paging>
					</view>
					<view class="grace-nowrap grace-flex-end">
						<text class="grace-blue grace-icons icon-add" style="margin-right: 30rpx;" @tap="action('C')">添加</text>
						<text class="grace-blue grace-icons icon-article" style="margin-right: 30rpx;" @tap="action('M')">修改</text>
						<text class="grace-blue grace-icons icon-remove" @tap="action('D')">删除</text>
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
				mainHeight: 0,
				listData: [],
				selectIndex: [],
				pageSize : 5,
				total: 0,
				pageNum: 1
			}
		},
		onReady: function() {
			uni.createSelectorQuery().select('#gBody').fields({
				size: true
			}, (res) => {
				this.mainHeight = res.height
			}).exec();
		},
		onLoad() {
			this.getList();
		},
		onShow() {
			if (uni.getStorageSync("msg")) {
				uni.showToast({
					title: uni.getStorageSync("msg"),
					icon: "none"
				});
				uni.removeStorageSync("msg");
				setTimeout(() => {
					this.getList();
				}, 1500)
			}
		},
		methods: {
			pageChange(index) {
				this.pageNum = index;
				this.getList();
			},
			dialogTap(b) {
				if (b) {
					var tabledata = [];
					for (var i = 0; i < this.selectIndex.length; i++) {
						tabledata.push(this.listData[this.selectIndex[i]]["data"]["id"]);
					}
					var json = {
						table: "users",
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
			action(action) {
				if (action == "D") {
					if (this.selectIndex.length == 0) {
						uni.showToast({
							title: "请选择需要删除的数据",
							icon: "none"
						});
						return;
					}
					this.$refs.delDialog.open();
				} else {
					if (action == "M") {
						if (this.selectIndex.length != 1) {
							uni.showToast({
								title: "请选择一条修改的数据",
								icon: "none"
							});
							return;
						}
						uni.setStorageSync("formData", this.listData[this.selectIndex[0]]["data"]);
					}
					uni.navigateTo({
						url: './form?action=' + action
					});
				}
			},
			getList() {
				let params = {
					pageSize : this.pageSize,
					pageNum : this.pageNum,
				}
				this.$http.get("/user/list", {params}).then(res => {
					const data = res.data.list;
					this.total = res.data.total;
					const listData = [];
					data.some((item) => {
						listData.push({
							checked: false,
							title: item["userName"],
							columns: [
								["地区：" + item["loginName"]]
							],
							data: item
						});
					})
					this.listData = listData;
					this.selectIndex = [];
				});
			},
			change: function(e) {
				this.selectIndex = e;
			}
		},
	}
</script>
<style scoped>
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
