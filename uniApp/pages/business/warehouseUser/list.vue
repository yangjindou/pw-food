<template>
	<gracePage headerBG="#0088FE" :bounding="false">
		<ugNav slot="gHeader" :isBack="true" title="监管仓管理"></ugNav>
		<view slot="gBody" class="grace-flex-v1" id="gBody">
			<view id="top" class="grace-body grace-bg-white">
				<ugForm v-if="false" ref="form" submitName="查询" :columns="formColumns" @submit="formSubmit"
					:submitbtn="['查询']" />
			</view>
			<ugCheckList :lists="listData" @change="selectIndexChange" :height="mainHeight">
				<view slot="page">
					<paging :total="total" :pageSize='pageSize' v-model="pageNum" @changes="pageChange"></paging>
				</view>
				<view class="grace-nowrap grace-flex-end list-btn">
					<text class="grace-blue grace-icons" @tap="showActionSheet">更多</text>
          <text class="grace-blue grace-icons" @tap="action('详情')">详情</text>
          <text class="grace-blue grace-icons" @tap="action('修改')">修改</text>
          <text class="grace-blue grace-icons" @tap="action('新增')">新增</text>
				</view>
			</ugCheckList>
			<graceActionSheet title="请选择操作功能" @cancel="cancel" :items="actionSheetItems" ref="graceActionSheet"
				@selected="selected"></graceActionSheet>
			<ugDialog ref="dialog" content="确定删除这些数据?" @ok="delOk" />
		</view>
	</gracePage>
</template>
<script>
	import listMixin from './list.js';
	export default {
		mixins: [listMixin],
		data() {
			return {
				formColumns: [],
				actionSheetItems: ['删除' ,'核酸检测', '日常健康记录', '隔离记录', '应急处理'] ,
			}
		},
		onLoad() {
			this.basicParams['createUser'] = uni.getStorageSync('userData')['id'];
			this.getformColumns();
			this.getList();
		},
		methods: {
			selected(e) {
				uni.showToast({
					title: "您选择了 : " + this.actionSheetItems[e],
					icon: "none"
				});
				//this.$refs.graceActionSheet.close();
			},
			cancel() {
				uni.showToast({
					title: "您点击了取消",
					icon: "none"
				});
			},
			showActionSheet() {
				this.$refs.graceActionSheet.show();
			},
			formSubmit(e) {
				this.formData = e;
				this.pageNum = 1;
				this.getList();
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
			getformColumns() {
				this.formColumns = [{
					label: "录入时间",
					name: "createDate",
					type: "date",
					value: ''
				}];
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
