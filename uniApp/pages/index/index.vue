<template>
	<gracePage headerBG="#008AFF" :bounding="false">
		<ugNav slot="gHeader" title="首页"></ugNav>
		<view slot="gBody">
			<view class="index-top">
				<view class="text-box">
					<text v-if="userData['role'] == '系统管理员'">您好，管理员。欢迎使用本平台</text>
					<text v-else>您好，{{userData['userName']}}。欢迎使用本平台</text>
				</view>
			</view>
			<view class="index-menu grace-margin-top">
				<view class="grace-grids-menu">
					<view @tap="openMenu(index)" v-for="(item,index) in menuArr" :key="index"
						class="grace-grids-items-menu grace-relative">
						<text :class="'icon-'+item.icon"
							:style="'color:'+item.color+' !important;background-color:'+item.bgColor+' !important;'"
							class="grace-bg-blue grace-grids-icon-menu grace-icons"></text>
						<text class="grace-grids-text-menu">{{item.name}}</text>
						<view v-if="item.iconMark"
							class="grace-badge grace-bg-red grace-badge-absolute grace-white red-dot">{{item.iconMark}}
						</view>
					</view>
				</view>
			</view>
			<view class="index-list grace-margin-top">
				<view class="grace-title">
					<view class="grace-title-border"></view>
					<text class="grace-title-text grace-blue">公告</text>
					<text class="grace-text-small grace-gray" @tap="notice">更多</text>
				</view>
				<view class="grace-list">
					<view class="grace-list-items" v-for="(item, index) in noticeList" :key="index">
						<view class="grace-list-body grace-border-b" v-if="index < 10">
							<view class="grace-list-title" @tap="notice(item)">
								<text class="grace-list-title-text">{{item['title'].length > 10 ? item['title'].substr(0,16) + "..." : item['title']}}</text>
								<text class="grace-list-title-time">{{item['createDate']}}</text>
							</view>
						</view>
					</view>
				</view>
			</view>
			<view class="text-center grace-margin-top">技术支持：XXX 维护电话：XXX</view>
		</view>
	</gracePage>
</template>
<script>
	import menuData from './menu.js';
	export default {
		data() {
			return {
				menuArr: [],
				userData: {},
				menuData,
				noticeList: [],
			}
		},
		onLoad() {
			const userData = uni.getStorageSync('userData');
			if (!userData) {
				uni.reLaunch({
					url: '../login/login',
				});
				return;
			}
			this.userData = userData;
			this.getMenu();
		},
		onShow() {
			this.getNotice();
		},
		methods: {
			notice(item) {
				if(item.title) {
					uni.setStorageSync("formData", item);
					uni.navigateTo({
						url: '../business/notice/detail'
					});
				} else {
					uni.navigateTo({
						url: '../business/notice/user'
					});
				}
			},
			getNotice() {
				this.$http.get("/notice/list").then(res => {
					if (res && res.data && res.data.length) {
						this.noticeList = res.data;
					}
				});
			},
			getMenu() {
				const role = this.userData['role'];
				let menuRoleData = [];
				if (role === '企业用户') {
					menuRoleData = menuRoleData.concat(this.menuData.user);
				} else if (role === '监管仓管理员') {
					menuRoleData = menuRoleData.concat(this.menuData.admin);
				} else if (role === '系统管理员') {
					menuRoleData = menuRoleData.concat(this.menuData.system);
				}
				menuRoleData = menuRoleData.concat(this.menuData.all);
				this.menuArr = menuRoleData;
			},
			openMenu(index) {
				var menu = this.menuArr[index];
				if (menu.name == "退出") {
					uni.removeStorageSync("userData");
					uni.reLaunch({
						url: '../login/login',
					});
				} else {
					uni.navigateTo({
						url: menu.url,
					});
				}
			}
		},
	}
</script>
<style lang="less" scoped>
	.index-list {
		padding: 0 20rpx;
		background-color: #FFFFFF;

		.grace-list {
			background-color: #FFFFFF;

			.grace-list-body {
				margin-left: 0;

				.grace-list-title {
					align-items: center;
				}

				.grace-list-title-time {
					margin-left: 20rpx;
					color: #7e7e7e;
					font-size: 12px;
				}
			}
		}
	}

	.index-top {
		display: flex;
		padding: 20rpx 40rpx;
		background-color: #FFFFFF;
		align-items: center;

		.text-box {
			padding-left: 20rpx;
			display: flex;
			flex-direction: column;
		}
	}

	.index-menu {
		padding: 0 25rpx;
		background: #FFFFFF;

		.grace-grids-menu {
			display: flex;
			padding: 30rpx 0;
			flex-wrap: wrap;

			.grace-grids-items-menu {
				width: 25%;
				padding: 20rpx 0;
				display: flex;
				box-sizing: border-box;
				flex-direction: column;
				align-items: center;

				.grace-grids-text-menu {
					margin-top: 10rpx;
					width: 100%;
					text-align: center;
					font-size: 26rpx;
				}

				.grace-grids-icon-menu {
					border-radius: 14rpx;
					width: 90rpx;
					height: 90rpx;
					font-size: 56rpx;
					line-height: 80rpx;
					display: flex;
					align-items: center;
					justify-content: center;
				}
			}
		}
	}
</style>
