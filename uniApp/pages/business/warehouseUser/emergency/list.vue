<template>
	<gracePage headerBG="#0088FE" :bounding="false">
		<ugNav slot="gHeader" :isBack="true" :title="'应急处理-'+ name"></ugNav>
		<view slot="gBody" class="grace-flex-v1" id="gBody">
			<view id="top" class="grace-body grace-bg-white">
				<ugForm v-if="false" ref="form" :columns="formColumns" @submit="formSubmit" :submitbtn="['查询']" />
			</view>
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
			return {
				formColumns: [],
				name: '',
				pid: -1
			}
		},
		onLoad(e) {
			if (e.id) {
				const id = e.id;
				if (id) {
					this.$http.get("/warehouseUser/list", {
						params: {
							id
						}
					}).then(res => {
						if (res && res.data && res.data.length) {
							this.name = res.data[0].name;
							this.pid = id;
							this.basicParams['pid'] = id;
							this.getformColumns();
							this.getList();
						}
					});
				}
			}
		},
		methods: {
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
						url: `./form?action=${action}&pid=${this.pid}`
					});
				} else {
					uni.navigateTo({
						url: `./form?action=${action}&pid=${this.pid}`
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
