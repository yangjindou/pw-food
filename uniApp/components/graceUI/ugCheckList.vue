<template name="ugCheckList">
	<view class="check-list" :style="{height:height+'px'}">
		<scroll-view scroll-y="true" :style="{height:height+'px'}">
			<view v-for="(item, index) in dataIn" :key="index" class="check-list-item" :data-index="index" @tap.stop="choose">
				<view class="check-list-icon grace-icons" v-if="item.checked && batch" :style="{color:checkColor}">&#xe7f8;</view>
				<view class="check-list-icon grace-icons" v-if="!item.checked && batch" style="color:#D2D2D2;">&#xe762;</view>
				<view class="check-list-body" :class="[isBorder && index < dataIn.length - 1 ? 'grace-border-b':'']">
					<view class="check-list-content">
						<view class="check-list-content-title" :style="{fontSize:fontSize, color:itemColor}" v-html="item.title"></view>
						<view v-for="(column, columnIndex) in item.columns" :key="columnIndex">
							<view v-if="column.length == 2" class="check-list-content-two">
								<view class="text" :style="{color:smallTextColor, fontSize:smallTextSize}" v-html="column[0]"></view>
								<view class="text" :style="{color:smallTextColor, fontSize:smallTextSize}" v-html="column[1]"></view>
							</view>
							<view v-if="column.length == 1" class="check-list-content-desc" :style="{color:smallTextColor, fontSize:smallTextSize}"
							 v-html="column[0]"></view>
						</view>
					</view>
				</view>
			</view>
			<view style="width:100%;" :style="isPage ? 'height:200rpx;' : 'height:120rpx;'" v-if="batch"></view>
			<view v-else style="width:100%;height:140rpx;"></view>
		</scroll-view>
		<view class="check-list-footer" v-if="height > 0">
			<view v-if="isPage">
				<slot name="page"></slot>
			</view>
			<view v-if="batch" class="check-list-footer-bottom">
				<view class="check-list-icon" @tap.stop="selectAll">
					<text class="grace-icons" v-if="sedAll" :style="{color:checkColor}">&#xe7f8;</text>
					<text class="grace-icons" v-if="!sedAll">&#xe762;</text>
				</view>
				<view class="check-list-footer-text" @tap.stop="selectAll">全选 {{sedNumbers > 0 ? sedNumbers +'/'+ dataIn.length : ''}}</view>
				<view class="check-list-footer-btns">
					<slot></slot>
				</view>
			</view>
		</view>
	</view>
</template>
<script>
	export default {
		props: {
			isPage: {
				type: Boolean,
				default: false
			},
			height: {
				type: Number,
				default: 0
			},
			lists: {
				type: Array,
				default: function() {
					return [];
				}
			},
			checkColor: {
				type: String,
				default: "#3688FF"
			},
			imgSize: {
				type: Array,
				default: function() {
					return ['68rpx', '68rpx'];
				}
			},
			isBorder: {
				type: Boolean,
				default: true
			},
			fontSize: {
				type: String,
				default: "28rpx"
			},
			itemColor: {
				type: String,
				default: "#323232"
			},
			imgBordeRadius: {
				type: String,
				default: "8rpx"
			},
			smallTextColor: {
				type: String,
				default: "#999999"
			},
			smallTextSize: {
				type: String,
				default: "24rpx"
			},
			batch: {
				type: Boolean,
				default: false
			}
		},
		data() {
			return {
				dataIn: [],
				sedAll: false,
				sedNumbers: 0,

				
			}
		},
		created: function() {
			this.dataIn = this.lists;
		},
		watch: {
			lists: function(val) {
				this.dataIn = val;
			},
			dataIn: function(val) {
				var sedNumber = 0;
				for (let i = 0; i < this.dataIn.length; i++) {
					if (this.dataIn[i].checked) {
						sedNumber++;
					}
				}
				this.sedNumbers = sedNumber;
				if (sedNumber >= val.length) {
					this.sedAll = true;
				} else {
					this.sedAll = false;
				}
			}
		},
		methods: {
			choose: function(e) {
				if (!this.batch) {
					this.$emit('listItemTap', e.currentTarget.dataset.index);
					return;
				}
				var index = e.currentTarget.dataset.index;
				if (this.dataIn[index].checked) {
					this.dataIn[index].checked = false;
					this.dataIn.splice(index, 1, this.dataIn[index]);
				} else {
					this.dataIn[index].checked = true;
					this.dataIn.splice(index, 1, this.dataIn[index]);
				}
				this.dataIn = this.dataIn;
				var sedArr = [];
				for (let i = 0; i < this.dataIn.length; i++) {
					if (this.dataIn[i].checked) {
						sedArr.push(i);
					}
				}
				this.$emit('change', sedArr);
			},
			selectAll: function() {
				if (this.sedAll) {
					for (let i = 0; i < this.dataIn.length; i++) {
						this.dataIn[i].checked = false;
						this.dataIn.splice(i, 1, this.dataIn[i]);
					}
					this.sedAll = false;
					this.$emit('change', []);
				} else {
					var sedArr = [];
					for (let i = 0; i < this.dataIn.length; i++) {
						this.dataIn[i].checked = true;
						this.dataIn.splice(i, 1, this.dataIn[i]);
						sedArr.push(i);
					}
					this.sedAll = true;
					this.$emit('change', sedArr);
				}
			}
		},
	}
</script>
<style scoped>
	.check-list-content-two {
		font-size: 14px;
		display: flex;
	}

	.check-list-content-two .text {
		width: 100%;
	}

	.check-list {
		position: relative;
	}

	.check-list-item {
		display: flex;
		flex-direction: row;
		flex-wrap: nowrap;
		align-items: center;
		font-size: 0;
		background-color: #FFFFFF;
		padding: 0 25rpx;
	}

	.check-list-icon {
		width: 60rpx;
		line-height: 60rpx;
		text-align: center;
		flex-shrink: 0;
		font-size: 36rpx;
		color: #D2D2D2;
		font-weight: 700;
		margin-right: 15rpx;
	}

	.check-list-img {
		flex-shrink: 0;
		border-radius: 10rpx;
		margin-right: 28rpx;
	}

	.check-list-body {
		width: 700rpx;
		display: flex;
		flex-wrap: nowrap;
		flex-direction: row;
		align-items: center;
	}

	.check-list-content {
		width: 200rpx;
		flex: 1;
		overflow: hidden;
		padding: 22rpx 0;
	}

	.check-list-content-title {
		line-height: 44rpx;
		font-size: 28rpx;
		display: block;
		width: 100%;
	}

	.check-list-content-desc {
		line-height: 36rpx;
		font-size: 22rpx;
		display: block;
		width: 100%;
	}

	.grace-border-b {
		border-bottom: 1px solid #F4F5F6;
	}

	.check-list-footer {
		background: #FFFFFF;
		position: absolute;
		z-index: 1;
		left: 0;
		bottom: 0;
		width: 100%;
		box-sizing: border-box;
		padding: 20rpx 0;
		flex-wrap: nowrap;
		align-items: center;
		flex-shrink: 0;
	}

	.check-list-footer-bottom {
		padding: 0 25rpx;
		display: flex;
		flex-direction: row;
		align-items: center;
	}

	.check-list-footer-text {
		font-size: 28rpx;
		color: #323232;
		flex-shrink: 0;
	}

	.check-list-footer-btns {
		width: 700rpx;
		margin-left: 28rpx;
	}
</style>
