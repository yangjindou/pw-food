<template>
	<view class="page">
		<gracePage headerBG="#0088FE" :bounding="false">
			<ugNav slot="gHeader" :isBack="true" title="公告详情" />
			<view class="grace-body" slot="gBody">
				<view class="notice-title">{{formData['title']}}</view>
				<view class="notice-time">{{formData['createDate']}}</view>
				<view class="notice-content" v-html="content"></view>
			</view>
		</gracePage>
	</view>
</template>
<script>
	export default {
		data() {
			return {
				formData: {},
				content: '',
			}
		},
		onLoad() {
			const formData = uni.getStorageSync("formData");
			if (formData) {
				uni.removeStorageSync("formData");
				this.formData = formData;
				this.content = this.formData['content'].replace(/\n/g, '<br/>');
			}
		},
		methods: {
		}
	}
</script>

<style lang="less" scoped>
	.notice-title {
		margin: 60rpx 40rpx 0 40rpx;
		text-align: center;
		font-size: 16px;
	}
	
	.notice-time {
		margin-top: 20rpx;
		display: flex;
		align-items: center;
		justify-content: center;
		color: #7d7d7d;
	}
	
	.notice-content {
		// margin-top: 60rpx;
		// margin-bottom: 60rpx;
		margin: 60rpx 40rpx;
	}
	
</style>
