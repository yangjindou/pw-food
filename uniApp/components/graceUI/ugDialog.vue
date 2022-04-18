<template>
	<graceDialog ref="dialog" :title="title" :isCloseBtn="false">
		<view class="del-content" slot="content">
			<text>{{content}}</text>
		</view>
		<view slot="btns" class="grace-space-between">
			<text class="grace-dialog-buttons" @tap="dialogTap(false)">取消</text>
			<text class="grace-dialog-buttons grace-blue" @tap="dialogTap(true)">确认</text>
		</view>
	</graceDialog>
</template>

<script>
	export default {
		props: {
			title: {
				type: String,
				default: '提示'
			},
			content: {
				type: String,
			},
		},
		data() {
			return {}
		},
		methods: {
			open() {
				this.$refs.dialog.open();
			},
			hide() {
				this.$refs.dialog.hide();
			},
			dialogTap(b) {
				if (b) {
					this.$emit('ok');
				} else {
					this.$emit('cancel');
					this.$refs.dialog.hide();
				}
			},
		}
	}
</script>

<style lang="less" scoped>
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
