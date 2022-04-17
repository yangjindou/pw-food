<template>
	<gracePage :headerHeight="0">
		<view class="grace-body" slot="gBody">
			<view class="login-header">
				<image src="@/static/default.jpg"></image>
			</view>
			<view class="login-main">
				<view class="input-box">
					<input v-model="loginName" type="text" placeholder="账号" />
				</view>
				<view class="input-box">
					<input v-model="password" type="password" placeholder="密码" />
				</view>
			</view>
			<view class="login-button">
				<view @tap="loginClick" class="login-btn">登 录</view>
				<view @tap="registerClick" class="register-btn">注 册</view>
			</view>
		</view>
		<view slot="gFooter">
			<view class="text-center">技术支持：XXX 维护电话：XXX</view>
		</view>
	</gracePage>
</template>
<script>
	export default {
		data() {
			return {
				loginName: '',
				password: ''
			}
		},
		onLoad() {
			if (uni.getStorageSync("registerMsg")) {
				this.$common.showToast(uni.getStorageSync("registerMsg"));
				uni.removeStorageSync("registerMsg");
			}
		},
		methods: {
			loginClick() {
				if (!this.loginName) {
					this.$common.showToast('请输入账号');
					return;
				}
				if (!this.password) {
					this.$common.showToast('请输入密码');
					return;
				}
				let datas = {
					loginName: this.loginName,
					password: this.password
				}
				this.$http.post("/auth/login", datas).then(res => {
					uni.setStorageSync('userData', res.data);
					uni.reLaunch({
						url: '../index/index',
					});
				});
			},
			registerClick() {
				uni.navigateTo({
					url: '../register/register',
				});
			}
		},
	}
</script>
<style lang="less" scoped>
	.login-header {
		width: 161rpx;
		height: 161rpx;
		box-shadow: 0 0 60rpx 0 rgba(0, 0, 0, 0.1);
		border-radius: 50%;
		background-color: #000000;
		margin-top: 180rpx;
		margin-bottom: 70rpx;
		margin-left: auto;
		margin-right: auto;

		image {
			width: 161rpx;
			height: 161rpx;
			border-radius: 50%;
		}
	}

	.login-main {
		display: flex;
		flex-direction: column;
		padding-left: 60rpx;
		padding-right: 60rpx;

		.input-box {
			background: white;
			border-radius: 50rpx;
			margin-bottom: 50rpx;
			padding: 0 40rpx;
			box-shadow: 0 0 7px 0 #cbcbcb;

			input {
				height: 100rpx;
			}
		}
	}

	.login-button {
		padding-left: 60rpx;
		padding-right: 60rpx;

		view {
			text-align: center;
			padding: 20rpx 0;
			color: #FFFFFF;
			border-radius: 100rpx;
			margin-top: 40rpx;
			font-size: 16px;
		}

		.login-btn {
			background: #4B4B4B;
		}

		.register-btn {
			background: #888888;
		}
	}
</style>
