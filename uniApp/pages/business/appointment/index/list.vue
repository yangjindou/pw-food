<template>
	<gracePage headerBG="#0088FE" :bounding="false">
		<ugNav slot="gHeader" :isBack="true" title="预约管理"></ugNav>
		<view slot="gBody" class="grace-flex-v1" id="gBody">
			<view id="top" class="grace-body grace-bg-white">
				<ugForm ref="form" :columns="formColumns" @submit="formSubmit" :submitbtn="['查询']" />
			</view>
			<ugCheckList :lists="listData" @change="selectIndexChange" :height="mainHeight">
				<view slot="page">
					<paging :total="total" :pageSize='pageSize' v-model="pageNum" @changes="pageChange"></paging>
				</view>
				<view class="grace-nowrap grace-flex-end list-btn">
					<text class="grace-blue grace-icons" @tap="showActionSheet">更多</text>
					<text class="grace-blue grace-icons" @tap="action('详情')">详情</text>
					<text class="grace-blue grace-icons" @tap="action('新增')">新增</text>
				</view>
			</ugCheckList>
			<graceActionSheet height="300rpx" title="请选择操作功能" @selected="selected" :items="actionSheetItems"
				ref="graceActionSheet" />
			<ugDialog ref="dialog" content="确定删除这些数据?" @ok="delOk" />
		</view>
	</gracePage>
</template>
<script>
	import listMixin from './list.js';
	import apiUtils from '@/utils/apiUtils.js';
	export default {
		mixins: [listMixin],
		data() {
			return {
				formColumns: [],
				actionSheetItems: ['删除', '申请修改', '修改'],
				formData: {},
			}
		},
		onLoad() {
			this.basicParams['createUser'] = uni.getStorageSync('userData')['id'];
			this.getformColumns();
			this.getSelectList();
			this.getList();
		},
		methods: {
			async getSelectList() {
				for (var i = 0; i < this.formColumns.length; i++) {
					let e = this.formColumns[i];
					if (e.name == 'goodType') {
						await apiUtils.getDictData('goodType').then(res => {
							e.dropboxGroup = res.data.map(e => {
								return {
									value: e.id,
									label: e.name
								};
							});
						});
					}
					if (e.name == 'goodSource') {
						await apiUtils.getDictData('goodSource').then(res => {
							e.dropboxGroup = res.data.map(e => {
								return {
									value: e.id,
									label: e.name
								};
							});
						});
					}
				}
				this.$nextTick(() => {
					this.$refs.form.changeColumns(this.formColumns);
				});
			},
			selected(e) {
				const actionSheet = this.actionSheetItems[e];
				if (actionSheet == '删除') {
					this.action('删除');
					return;
				}
				if (this.selectIndex.length != 1) {
					this.$common.showToast('请选择一条数据的数据');
					return;
				}
				const formData = this.listData[this.selectIndex[0]]["data"];
				if (formData['emergency']) {
					this.$common.showToast('该数据被应急通知，无法操作！');
					return;
				}
				if (actionSheet == '修改') {
					if(!['保存','驳回'].includes(formData['filingState'])) {
						this.$common.showToast(`该数据已经${formData['filingState']}，无法修改！`);
						return;
					}
					this.action('修改');
				} else if (actionSheet == '申请修改') {
					if (formData['filingState'] != '审核通过') {
						this.$common.showToast('该数据未审核通过！');
						return;
					}
					if (formData['applyUpdateCount'] && formData['applyUpdateCount'] >= 3) {
						this.$common.showToast('已超过修改次数，无法修改！');
						return;
					}
					uni.setStorageSync("formData", formData);
					uni.navigateTo({
						url: './applyUpdate?action=修改'
					});
				}
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
				} else if (action == "修改") {
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
				} else if (action == "详情") {
					if (this.selectIndex.length != 1) {
						uni.showToast({
							title: `请选择一条${action}的数据`,
							icon: "none"
						});
						return;
					}
					uni.setStorageSync("formData", this.listData[this.selectIndex[0]]["data"]);
					uni.navigateTo({
						url: '../detail?action=' + action
					});
				} else {
					uni.navigateTo({
						url: './form?action=' + action
					});
				}
			},
			getformColumns() {
				this.formColumns = [{
					label: "货物类别",
					name: "goodType",
					type: "dropbox",
					dropboxGroup: [],
					value: ''
				}, {
					label: "货物来源",
					name: "goodSource",
					type: "dropbox",
					dropboxGroup: [],
					value: ''
				}, {
					label: "车牌号",
					name: "carNumber",
					type: "text",
					value: ''
				}, {
					label: "入仓时间",
					name: "warehousingDate",
					type: "rangDate",
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
