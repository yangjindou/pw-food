<template>
	<view class="">
		<gracePage headerBG="#0088FE" :bounding="false">
			<ugNav :isBack="true" slot="gHeader" title="修改密码"></ugNav>
			<view slot="gBody">
				<view class="main">
					<view class="input-box">
						<input v-model="oldPwd" type="password" placeholder="请输入原密码" />
					</view>
					<view class="input-box">
						<input v-model="newPwd" type="password" placeholder="请输入新密码" />
					</view>
					<view class="input-box">
						<input v-model="newPwd2" type="password" placeholder="请再次输入新密码" />
					</view>
				</view>
				<view class="btn-group">
					<view @tap="passwordModifyClick" class="btn">修改密码</view>
				</view>
			</view>
			<view slot="gFooter">
				<view class="text-center">技术支持：XXX 维护电话：XXX</view>
			</view>
		</gracePage>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				userData: "",
				oldPwd: "", //原密码
				newPwd: "", //密码
				newPwd2: "", //再次确认密码
			}
		},
		onLoad() {
			this.userData = uni.getStorageSync('userData');
		},
		methods: {
			passwordModifyClick() {
				if (!this.oldPwd) {
					this.$common.showToast('原密码不能为空');
					return;
				}
				if (!this.newPwd) {
					this.$common.showToast('新密码不能为空');
					return;
				}
				if (!this.newPwd2) {
					this.$common.showToast('确认密码不能为空');
					return;
				}
				if (this.newPwd != this.newPwd2) {
					this.$common.showToast('两次输入新密码不一致');
					return;
				}
				let datas = {
					oldPwd: this.oldPwd,
					newPwd: this.newPwd,
				};
				this.$http.put("/auth/passwordModify", datas).then(res => {
					this.$common.showToast('修改密码成功，请重新登录');
					uni.removeStorageSync('userData');
					setTimeout(function() {
						uni.reLaunch({
							url: '../login/login',
						});
					}, 1000);
				});
			}
		},
	}
</script>

<style lang="less" scoped>
	.main {
		margin-top: 50rpx;
		padding: 0 70rpx;

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

	.btn-group {
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

		.btn {
			background: #0088fe;
		}

	}
</style>
