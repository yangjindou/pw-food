<template>
	<gracePage headerBG="#0088FE" :bounding="false">
		<ugNav slot="gHeader" :isBack="true" title="生成二维码"></ugNav>
		<view slot="gBody" class="grace-body" id="gBody">
			<view class="content">
				<uQRCode :size="200" ref="uQRCode" :text="text" />
				<button type="primary" class="grace-button" @tap="save">保存到相册</button>
			</view>
		</view>
	</gracePage>
</template>
<script>
	import uQRCode from '@/components/Sansnn-uQRCode/components/uqrcode/uqrcode.vue'
	export default {
		components: {
			uQRCode
		},
		data() {
			return {
				text: '',
			}
		},
		created() {
			this.text = 'https://www.shanghaiyly.com/report/?id=' + uni.getStorageSync('userData')['id'];
		},
		methods: {
			save() {
				this.$refs.uQRCode.save({
					success: res => {
						this.$common.showToast(res.msg);
						console.log(res)
					},
					fail: res => {
						if(res.errMsg == 'saveImageToPhotosAlbum:fail auth deny') {
							this.$common.showToast("保存失败：没有添加到相册权限");
						} else {
							this.$common.showToast("保存失败：" + res.errMsg);
						}
					},
				})
			}
		},
	}
</script>
<style lang="less" scoped>
	.content {
		display: flex;
		flex-direction: column;
		align-items: center;
		margin: 40rpx 0;

		.grace-button {
			margin-top: 40rpx;
			padding: 0 60rpx;
		}
	}
</style>
