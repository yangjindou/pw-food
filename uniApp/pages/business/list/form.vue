<template>
	<view class="page">
		<gracePage headerBG="#0088FE" :bounding="false">
			<ugNav slot="gHeader" :isBack="true" :title="action" />
			<view class="grace-body" slot="gBody">
				<ugForm :showSubmit="showSubmit" :columns="formColumns" @submit="formSubmit"
					:submitbtn="['保存', '确认检测']"></ugForm>
				<graceDialog ref="dialog" title="提示" :isCloseBtn="false">
					<view class="content1" slot="content">
						<text>请确认信息是否无误？</text>
					</view>
					<view slot="btns" class="grace-space-between">
						<text class="grace-dialog-buttons" @tap="dialogTap(false)">取消</text>
						<text class="grace-dialog-buttons grace-blue" @tap="dialogTap(true)">确认</text>
					</view>
				</graceDialog>
			</view>
		</gracePage>
	</view>
</template>
<script>
	export default {
		data() {
			return {
				action: '新增',
				id: '',
				formColumns: [],
				showSubmit: true,
				formData: {},
			}
		},
		onLoad(e) {
			this.getFormColumns();
			if (e.action) {
				this.action = e.action;
				this.showSubmit = this.action != "详情";
				this.setFormData();
			}
		},
		methods: {
			setFormData() {
				const formData = uni.getStorageSync("formData");
				const disabled = this.action == "详情";
				uni.removeStorageSync("formData");
				this.id = formData["id"];
				for (let key in formData) {
					for (let i = 0; i < this.formColumns.length; i++) {
						if (key == this.formColumns[i].name) {
							this.formColumns[i].value = formData[key];
							if (disabled) {
								this.formColumns[i].disabled = disabled;
							}
							break;
						}
					}
				}
			},
			formSubmit(formData, submitType) {
				if (submitType === '确认检测') {
					formData['result'] = '已检测';
					this.formData = formData;
					this.$refs.dialog.open();
				} else {
					formData['result'] = '未检测';
					this.save(formData);
				}
			},
			dialogTap(b) {
				if (b) {
					this.save(this.formData);
				}
				this.$refs.dialog.hide();
			},
			save(formData) {
				if (this.action == '新增') {
					formData['createUser'] = uni.getStorageSync('userWechat')['id'];
					this.$http.post("/report", formData).then(res => {
						uni.setStorageSync("msg", "上报成功");
						uni.navigateBack({
							delta: 1
						});
					});
				} else if (this.action == '修改') {
					formData['id'] = this.id;
					formData['updateUser'] = uni.getStorageSync('userWechat')['id'];
					this.$http.put("/report", formData).then(res => {
						uni.setStorageSync("msg", "修改成功");
						uni.navigateBack({
							delta: 1
						});
					});
				}
			},
			getFormColumns() {
				this.formColumns = [{
					label: "街道",
					name: "street",
					type: "text",
					placeholder: "",
					disabled: true,
					value: '',
					checkType: "notnull",
					checkRule: "",
					errorMsg: "请输入街道"
				}, {
					label: "社区",
					name: "community",
					type: "text",
					placeholder: "",
					disabled: true,
					value: '',
					checkType: "notnull",
					checkRule: "",
					errorMsg: "请输入社区"
				}, {
					label: "详细地址",
					name: "address",
					type: "text",
					placeholder: "请输入详细地址",
					value: '',
					checkType: "notnull",
					checkRule: "",
					errorMsg: "请输入详细地址"
				}, {
					label: "姓名",
					name: "name",
					type: "text",
					placeholder: "请输入姓名",
					value: '',
					checkType: "notnull",
					checkRule: "",
					errorMsg: "请输入姓名"
				}, {
					label: "手机号",
					name: "phone",
					type: "text",
					placeholder: "请输入手机号",
					value: '',
					checkType: "phoneno",
					checkRule: "",
					errorMsg: "请输入正确的手机号"
				}, {
					label: "家庭人数",
					name: "familySize",
					type: "text",
					placeholder: "请输入家庭人数",
					value: '',
					checkType: "notnull",
					checkRule: "",
					errorMsg: "请输入家庭人数"
				}, {
					label: "共参加几轮核酸检测",
					name: "amount",
					type: "text",
					placeholder: "请输入共参加几轮核酸检测",
					value: '',
					checkType: "notnull",
					checkRule: "",
					errorMsg: "请输入共参加几轮核酸检测"
				}, {
					label: "今天是否做过核酸检测",
					name: "isTodayGet",
					type: "radio",
					radioGroup: [{
						label: '否',
						value: '否'
					}, {
						label: '是',
						value: '是'
					}],
					placeholder: "请选择今天是否做过核酸检测",
					disabled: false,
					value: '',
					checkType: "notnull",
					checkRule: "",
					errorMsg: "请选择今天是否做过核酸检测"
				}, {
					label: "家里是否有黄码成员",
					name: "isHasYellow",
					type: "radio",
					radioGroup: [{
						label: '否',
						value: '否'
					}, {
						label: '是',
						value: '是'
					}],
					placeholder: "请选择家里是否有黄码成员",
					disabled: false,
					value: '',
					checkType: "notnull",
					checkRule: "",
					errorMsg: "请选择家里是否有黄码成员"
				}]
			}
		}
	}
</script>
<style scoped>
	.page {
		background: #FFF !important;
	}
	
	.content1 {
			padding: 25rpx;
			font-size: 26rpx;
			text-align: center;
			line-height: 60rpx;
			background-color: #F8F8F8;
			display: block;
		}
</style>
