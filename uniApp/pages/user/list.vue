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
					<text class="grace-blue grace-icons icon-add" @tap="action('C')">添加</text>
					<text class="grace-blue grace-icons icon-article" @tap="action('M')">修改</text>
					<text class="grace-blue grace-icons icon-remove" @tap="action('D')">删除</text>
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
				if (action == "D") {
					if (this.selectIndex.length == 0) {
						uni.showToast({
							title: "请选择需要删除的数据",
							icon: "none"
						});
						return;
					}
					this.$refs.dialog.open();
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
