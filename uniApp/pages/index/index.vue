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
				menuArr: [],
				userData: {},
				menuData: {
					all: [{
						icon: 'article',
						name: '修改密码',
						bgColor: '#FF2851',
						url: '../passwordModify/passwordModify',
					}, {
						icon: 'article',
						name: '退出',
						bgColor: '#FF2851',
					}],
					user: [{
						icon: 'article',
						name: '预约管理',
						bgColor: '#FF2851',
						url: '../passwordModify/passwordModify',
					}],
					admin: [{
						icon: 'article',
						name: '公告管理',
						bgColor: '#FF2851',
						url: '../business/notice/list',
					}, {
						icon: 'article',
						name: '监管仓管理',
						bgColor: '#FF2851',
						url: '../passwordModify/passwordModify',
					}, {
						icon: 'article',
						name: '监管仓人员',
						bgColor: '#FF2851',
						url: '../passwordModify/passwordModify',
					}, {
						icon: 'article',
						name: '设备管理',
						bgColor: '#FF2851',
						url: '../passwordModify/passwordModify',
					}, {
						icon: 'article',
						name: '废物处理',
						bgColor: '#FF2851',
						url: '../passwordModify/passwordModify',
					}, {
						icon: 'article',
						name: '消毒液管理',
						bgColor: '#FF2851',
						url: '../passwordModify/passwordModify',
					}, {
						icon: 'article',
						name: '环境消杀记录',
						bgColor: '#FF2851',
						url: '../passwordModify/passwordModify',
					}, {
						icon: 'article',
						name: '预约管理',
						bgColor: '#FF2851',
						url: '../passwordModify/passwordModify',
					}, {
						icon: 'article',
						name: '入仓管理',
						bgColor: '#FF2851',
						url: '../passwordModify/passwordModify',
					}, {
						icon: 'article',
						name: '出仓管理',
						bgColor: '#FF2851',
						url: '../passwordModify/passwordModify',
					}],
					system: [{
						icon: 'article',
						name: '用户管理',
						bgColor: '#FF2851',
						url: '../user/list',
					}]
				}
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
		methods: {
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
	.index-top {
		display: flex;
		background-color: #FFFFFF;
		padding: 20rpx 40rpx;
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
