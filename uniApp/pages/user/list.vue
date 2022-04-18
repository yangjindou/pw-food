<template>
	<gracePage headerBG="#0088FE" :bounding="false">
		<ugNav slot="gHeader" :isBack="true" title="用户管理"></ugNav>
		<view slot="gBody" class="grace-flex-v1" id="gBody">
			<view id="top"></view>
			<ugCheckList :lists="listData" @change="selectIndexChange" :isPage="true" :batch="true"
				:height="mainHeight">
				<view slot="page">
					<paging :total="total" :pageSize='pageSize' :first="false" :arrow="false" v-model="pageNum"
						@changes="pageChange"></paging>
				</view>
				<view class="grace-nowrap grace-flex-end">
					<text class="grace-blue grace-icons icon-add" style="margin-right: 30rpx;"
						@tap="action('C')">添加</text>
					<text class="grace-blue grace-icons icon-article" style="margin-right: 30rpx;"
						@tap="action('M')">修改</text>
					<text class="grace-blue grace-icons icon-remove" @tap="action('D')">删除</text>
				</view>
			</ugCheckList>
		</view>
	</gracePage>
</template>
<script>
	import listMixin from './list.js';
	export default {
		mixins: [listMixin],
		data() {
			return {
				mainHeight: 0,
			}
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
		},
	}
</script>

<style scoped>

</style>
