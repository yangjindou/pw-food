<template>
	<view class="grace-box-banner grace-border-radius-small" :style="{background:background, paddingTop:padding, paddingBottom:padding}">
		<view class="grace-box-items" v-for="(item, index) in items" :key="index" @tap="itemTap(item)">
			<view class="line1" :style="{color:'#333333',fontSize:fontSize[0]}">{{item[0]}}</view>
			<view class="line2" :style="{color:color[index],fontSize:fontSize[1]}">{{item[1]}}</view>
		</view>
	</view>
</template>
<script>
	export default {
		props: {
			items: {
				type: Array,
				default: function() {
					return [
						['', '']
					]
				}
			},
			color: {
				type: Array,
				default: function() {
					return ['#333333', '#999999']
				}
			},
			fontSize: {
				type: Array,
				default: function() {
					return ['24rpx', '24rpx']
				}
			},
			background: {
				type: String,
				default: ''
			},
			padding: {
				type: String,
				default: '25rpx'
			}
		},
		methods: {
			itemTap(item) {
				this.$emit('itemTap',item);
			}
		}
	}
</script>
<style scoped>
	.grace-box-banner {
		padding: 26rpx 0;
		display: flex;
		flex-wrap: nowrap;
		overflow: hidden;
		background: #FFF;
	}

	.grace-box-items {
		width: 100%;
		justify-content: center;
		line-height: 1.5em;
		text-align: center;
	}

	.grace-box-banner .line1 {
		line-height: 50rpx;
		overflow: hidden;
		text-align: center;
	}

	.grace-box-banner .line1 text {
		line-height: 50rpx;
		margin-left: 2px;
	}

	.grace-box-banner .line2 {
		line-height: 32rpx;
		text-align: center;
	}
</style>
