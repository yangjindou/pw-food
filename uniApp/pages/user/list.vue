<template>
	<gracePage headerBG="#0088FE" :bounding="false">
		<ugNav slot="gHeader" :isBack="true" title="用户管理"></ugNav>
		<view slot="gBody" class="grace-flex-v1" id="gBody">
			<view id="top"></view>
			<ugCheckList :lists="listData" @change="selectIndexChange" :height="mainHeight">
				<view slot="page">
					<paging :total="total" :pageSize='pageSize' v-model="pageNum" @changes="pageChange"></paging>
				</view>
				<view class="grace-nowrap grace-flex-end list-btn">
					<text class="grace-blue grace-icons" @tap="action('删除')">删除</text>
					<text class="grace-blue grace-icons" @tap="action('详情')">详情</text>
					<text class="grace-blue grace-icons" @tap="action('修改')">修改</text>
					<text class="grace-blue grace-icons" @tap="action('新增')">新增</text>
				</view>
			</ugCheckList>
			<ugDialog ref="dialog" content="确定删除这些数据?" @ok="delOk" />
		</view>
	</gracePage>
</template>
<script>
	import listMixin from './list.js';
	export default {
		mixins: [listMixin],
		data() {
			return {}
		},
		onLoad() {
			this.getList();
		},
		methods: {
			delOk() {
				const ids = this.selectIndex.map(i => this.listData[i]["data"]['id']);
				this.$http.delete(`user?ids=${ids.join(',')}`).then(res => {
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
			action(action) {
				if (action == "删除") {
					if (this.selectIndex.length == 0) {
						uni.showToast({
							title: "请选择需要删除的数据",
							icon: "none"
						});
						return;
					}
					this.$refs.dialog.open();
					return;
				} else if (['修改', '详情'].includes(action)) {
					if (this.selectIndex.length != 1) {
						uni.showToast({
							title: `请选择一条${action}的数据`,
							icon: "none"
						});
						return;
					}
					uni.setStorageSync("formData", this.listData[this.selectIndex[0]]["data"]);
					uni.navigateTo({
						url: './form?action=' + action
					});
				} else {
					uni.navigateTo({
						url: './form?action=' + action
					});
				}
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
</style>
