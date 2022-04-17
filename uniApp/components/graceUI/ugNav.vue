<template name="ugNav">
	<view class="grace-header-body">
		<view class="grace-header-leader grace-nowrap grace-space-between grace-flex-vcenter">
			<view v-if="isBack" class="grace-header-leader-btns grace-icons icon-arrow-left grace-white grace-bold" hover-class="navigator-hover"
			 @tap="goback"></view>
		</view>
		<view class="grace-header-content grace-flex-center" style="margin-right:180rpx;">
			<text class="grace-header-text grace-ellipsis grace-white">{{title}}</text>
		</view>
	</view>
</template>

<script>
	export default {
		name: "ugNav",
		props: {
			title: String,
			isBack: {
				type: Boolean,
				default: false
			},
		},
		methods: {
			goback() {
				if (this.isBack) {
					uni.navigateBack({
						delta: 1
					});
				}
			},
		}
	}
</script>

<style scoped>
	.grace-header-leader {
		background-color: rgb(0, 0, 0, 0);
	}
</style>
