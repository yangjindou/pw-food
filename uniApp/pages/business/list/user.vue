<template>
	<gracePage headerBG="#0088FE" :bounding="false">
		<ugNav slot="gHeader" :isBack="false" title="信息填报"></ugNav>
		<view slot="gBody" class="grace-body" id="gBody">
			<view class="content" v-if="show">
				<view class="info">
					<view>所选街道：{{street}}</view>
					<view>所选社区：{{community}}</view>
				</view>
				<view class="warn">
					<view>授权后，将无法修改街道及社区</view>
					<view>请确认信息是否正确</view>
				</view>
				<button type="primary" class="grace-button" @tap="login">点击授权</button>
			</view>
		</view>
	</gracePage>
</template>
<script>
	export default {
		data() {
			return {
				show: false,
				communityId: undefined,
				street: '',
				community: '',
			}
		},
		onLoad(params) {
			// params.q = 'https%3A%2F%2Fwww.shanghaiyly.com%2Freport%2F%3Fid%3D86'
			if (params.q) {
				const url = decodeURIComponent(params.q);
				const communityId = url.substr(url.lastIndexOf('=') + 1);
				if (!communityId) {
					this.$common.showToast('没有获取到社区信息，请联系管理员');
					return;
				}
				this.communityId = communityId;
				this.loginCheck();
			}
		},
		methods: {
			getData() {
				let params = {
					communityId: this.communityId,
				};
				this.$http.get("/user/community", {
					params
				}).then(res => {
					if (res && res.data && res.data.length) {
						this.street = res.data[0].street;
						this.community = res.data[0].community;
					}
				});
			},
			loginCheck() {
				uni.login({
					provider: 'weixin',
					success: res => {
						if (res.code) {
							let params = {
								code: res.code,
								communityId: this.communityId,
							};
							this.$http.post("/auth/login/check/wechat/uniapp", params).then(res => {
								if (res && res.data) {
									uni.setStorageSync('userWechat', res.data);
									uni.reLaunch({
										url: './list',
									});
								} else {
									this.show = true;
									this.getData();
								}
							});
						}
					}
				})
			},
			login() {
				if (!this.communityId) {
					this.$common.showToast('没有获取到社区信息，请联系管理员');
					return;
				}
				uni.login({
					provider: 'weixin',
					success: res => {
						if (res.code) {
							let params = {
								code: res.code,
								communityId: this.communityId,
							};
							this.$http.post("/auth/login/wechat/uniapp", params).then(res => {
								if (res && res.data) {
									uni.setStorageSync('userWechat', res.data);
									uni.reLaunch({
										url: './list',
									});
								}
							});
						}
					}
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
		margin-top: 100rpx;
		
		.info {
			view {
				font-size: 16px;
				text-align: center;
			}
		}
		
		.warn {
			margin-top: 40rpx;
			view {
				text-align: center;
			}
		}
	}
	
	.grace-button {
		margin-top: 40rpx;
		padding: 0 60rpx;
	}
</style>
