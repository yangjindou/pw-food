<template>
	<view class="grace-add-list" v-if="!disabled">
		<view v-if="fileUrls.length != 0" class="grace-add-list-items">
			<image :src="fileUrls[0]" @tap="showImgs" class="grace-add-list-img" :mode="imgMode"></image>
			<view class="grace-add-list-count" :style="{color:closeBtnColor}">{{fileUrls.length}}</view>
			<view v-if="fileUrls.length < maxFileNumber" class="grace-add-list-add grace-icons icon-add" :style="{color:closeBtnColor}" @tap="addImg"></view>
			<view class="grace-add-list-remove grace-icons icon-close" :style="{color:closeBtnColor}" @tap="removeImg">
			</view>
		</view>
		<view v-else class="grace-add-list-items grace-add-list-btn" @tap="addImg">
			<view class="grace-add-list-btn-icon">+</view>
			<view class="grace-add-list-btn-text">添加图片</view>
		</view>
	</view>
	<view v-else class="grace-add-list">
		<view v-if="fileUrls.length != 0" class="grace-add-list-items">
			<view class="grace-add-list-count" :style="{color:closeBtnColor}">{{fileUrls.length}}</view>
			<image :src="fileUrls[0]" @tap="showImgs" class="grace-add-list-img" :mode="imgMode"></image>
		</view>
		<view v-else class="grace-add-list-items grace-add-list-btn">
			<view class="grace-add-list-btn-icon">x</view>
			<view class="grace-add-list-btn-text">没有图片</view>
		</view>
	</view>
</template>
<script>
	export default {
		name: "ugSelectImg",
		props: {
			name: {
				type: String,
				default: ''
			},
			maxFileNumber: {
				type: Number,
				default: 9
			},
			urls: {
				type: String,
				default: ""
			},
			closeBtnColor: {
				type: String,
				default: "#666666"
			},
			imgMode: {
				type: String,
				default: 'widthFix'
			},
			disabled: {
				type: Boolean,
				default: false
			}
		},
		data() {
			return {
				files: [],
				fileUrls: [],
				uri: this.$common.imgServer
			}
		},
		mounted() {
			if(this.$common.varIsError(this.urls)) {
				return;
			}
			this.files = eval('(' + this.urls + ')');
			this.files.some((item) => {
				this.fileUrls.push(this.uri + item);
			});
		},
		methods: {
			addImg: function() {
				var _this = this;
				uni.chooseImage({
					count: 1,
					sizeType: ['compressed'],
					success: (res) => {
						for (var i = 0; i < res.tempFilePaths.length; i++) {
							var fileName = this.$common.createGuid() + ".jpg";
							uni.uploadFile({
								url: _this.$common.appServer + "uploadImg",
								filePath: res.tempFilePaths[i],
								formData: {
									"fileName": fileName,
								},
								header:{
									"Request-Type": "APP",// 一定要加上这个，不然后台返回403
								},
								name: "file",
								success: (uploadRes) => {
									if(!_this.$common.varIsError(uploadRes.data)) {
										_this.files.push(uploadRes.data);
										_this.fileUrls.push(_this.uri + uploadRes.data);
										this.$emit('change', this.name, this.files);
									}
								}
							});
						}
					}
				});
			},
			removeImg: function(e) {
				this.files = [];
				this.fileUrls = [];
				this.$emit('change', this.name, this.files);
			},
			showImgs: function(e) {
				uni.previewImage({
					urls: this.fileUrls,
					current: this.fileUrls[0]
				})
			},
		}
	}
</script>
<style scoped>
	.grace-add-list {
		display: flex;
		flex-wrap: wrap;
	}

	.grace-add-list-btn {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
	}

	.grace-add-list-btn-text {
		font-size: 26rpx;
		line-height: 36rpx;
		text-align: center;
		color: #999999;
		width: 100%;
	}

	.grace-add-list-btn-icon {
		font-size: 80rpx;
		height: 80rpx;
		line-height: 80rpx;
		margin-bottom: 20rpx;
		color: #999999;
	}

	.grace-add-list-items {
		width: 222rpx;
		height: 222rpx;
		overflow: hidden;
		margin-bottom: 20rpx;
		margin-right: 10rpx;
		background: #F6F7F8;
		font-size: 0;
		position: relative;
		border-radius: 10rpx;
	}

	.grace-add-list-image {
		width: 222rpx;
	}
	
	.grace-add-list-count {
		color: #fff !important;
		border-radius: 50%;
		background: #007AFF;
		width: 50rpx;
		height: 50rpx;
		line-height: 50rpx;
		text-align: center;
		font-size: 40rpx;
		position: absolute;
		z-index: 1;
		left: 0;
		top: 0;
	}
	
	.grace-add-list-add {
		color: #fff !important;
		border-radius: 50%;
		background: #888888;
		width: 50rpx;
		height: 50rpx;
		line-height: 50rpx;
		text-align: center;
		font-size: 40rpx;
		position: absolute;
		z-index: 1;
		right: 0;
		top: 0;
	}

	.grace-add-list-remove {
		width: 50rpx;
		height: 50rpx;
		line-height: 50rpx;
		text-align: center;
		font-size: 40rpx;
		position: absolute;
		z-index: 1;
		right: 0;
		bottom: 0;
		color: #888888;
	}

	.grace-add-list-img {
		width: 222rpx;
	}
</style>