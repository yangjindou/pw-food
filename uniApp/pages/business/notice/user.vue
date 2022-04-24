<template>
	<gracePage headerBG="#0088FE" :bounding="false">
		<ugNav slot="gHeader" :isBack="true" title="公告"></ugNav>
		<view slot="gBody" class="grace-flex-v1" id="gBody">
			<view id="top" class="grace-body grace-bg-white">
				<ugForm ref="form" :columns="formColumns" @submit="formSubmit" :submitbtn="['查询']" />
			</view>
			<ugCheckList :lists="listData" @change="selectIndexChange" :height="mainHeight" :batch="false" @listItemTap="listItemTap">
				<view slot="page">
					<paging :total="total" :pageSize='pageSize' v-model="pageNum" @changes="pageChange"></paging>
				</view>
				<view class="grace-nowrap grace-flex-end list-btn">
					<text class="grace-blue grace-icons" @tap="action('详情')">详情</text>
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
				formColumns: [],
			}
		},
		onLoad() {
			this.getformColumns();
			this.getList();
		},
		methods: {
			listItemTap(index) {
				uni.setStorageSync("formData", this.listData[index]['data']);
				uni.navigateTo({
					url: './detail?'
				});
			},
			formSubmit(e) {
				this.formData = e;
				this.pageNum = 1;
				this.getList();
			},
			getformColumns() {
				this.formColumns = [ {
					label: "公告标题",
					name: "title",
					type: "text",
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
