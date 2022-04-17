<template>
	<view class="page">
		<gracePage headerBG="#0088FE" :bounding="false">
			<ugNav slot="gHeader" :isBack="true" title="注册"></ugNav>
			<view class="grace-body" slot="gBody">
				<ugForm ref="form" :columns="formColumns" @submit="formSubmit" :submitbtn="['注册']">
					<view class="grace-form-item grace-border-b">
						<text class="grace-form-label">账号类型</text>
						<view class="grace-form-body">
							<ugDropbox name="type" :dropboxGroup="dropboxGroup" @dropboxChange="dropboxChange">
								<input type="text" v-model="formData.type" name="type" placeholder="请选择账号类型" disabled
									class="grace-form-input" />
								<text class="grace-icons icon-arrow-down" style="margin-left:5px;"></text>
							</ugDropbox>
						</view>
					</view>
					<template v-if="formData.type === '社区'">
						<view class="grace-form-item grace-border-b">
							<text class="grace-form-label">街道</text>
							<view class="grace-form-body">
								<ugDropbox name="type" :dropboxGroup="streetList" @dropboxChange="streetChange">
									<input type="text" v-model="formData.street" name="street" placeholder="请选择街道"
										disabled class="grace-form-input" />
									<text class="grace-icons icon-arrow-down" style="margin-left:5px;"></text>
								</ugDropbox>
							</view>
						</view>
						<view class="grace-form-item grace-border-b">
							<text class="grace-form-label">社区</text>
							<view class="grace-form-body">
								<input name="community" v-model="formData.community" type="text" placeholder="请输入社区" class="grace-form-input" />
							</view>
						</view>
					</template>
					<template v-if="formData.type === '街道'">
						<view class="grace-form-item grace-border-b">
							<text class="grace-form-label">街道</text>
							<view class="grace-form-body">
								<input name="street" v-model="formData.street" type="text" placeholder="请输入街道" class="grace-form-input" />
							</view>
						</view>
					</template>
				</ugForm>
			</view>
		</gracePage>
	</view>
</template>
<script>
	import objUtils from '@/utils/objUtils.js'
	export default {
		data() {
			return {
				formColumns: [],
				dropboxGroup: [{
					label: '街道',
					value: '街道'
				}, {
					label: '社区',
					value: '社区'
				}],
				formData: {
					type: '',
					street: '',
				},
				streetList: []
			}
		},
		onLoad() {
			this.getFormColumns();
			this.getStreet();
		},
		methods: {
			getStreet() {
				this.$http.get("/user/street").then(res => {
					if (res.data) {
						this.streetList = res.data.map(e => {
							return {
								value: e.id,
								label: e.street
							};
						});
					}
				});
			},
			streetChange(index) {
				this.formData.street = this.streetList[index].label;
			},
			dropboxChange(index) {
				this.formData.type = this.dropboxGroup[index].label;
				this.formData.street = '';
			},
			getFormColumns() {
				this.formColumns = [{
					label: "账号",
					name: "loginName",
					type: "text",
					placeholder: "请输入账号",
					value: '',
					checkType: "notnull",
					checkRule: "",
					errorMsg: "请输入账号"
				}, {
					label: "密码",
					name: "password",
					type: "password",
					placeholder: "请输入密码",
					value: '',
					checkType: "notnull",
					checkRule: "",
					errorMsg: "请输入密码"
				}, {
					label: "确认密码",
					name: "rpassword",
					type: "password",
					placeholder: "请输入确认密码",
					value: '',
					checkType: "samewith",
					checkRule: "password",
					errorMsg: "两次密码不一致"
				}]
			},
			formSubmit(formData) {
				if (!this.formData.type) {
					this.$common.showToast('请选择账户类型');
					return;
				}
				if (this.formData.type == '街道') {
					if (!this.formData.street) {
						this.$common.showToast('请输入街道');
						return;
					}
				} else {
					if (!this.formData.street) {
						this.$common.showToast('请选择街道');
						return;
					}
					if (!this.formData.community) {
						this.$common.showToast('请输入社区');
						return;
					}
				}
				let params = objUtils.getObjectByKey(formData, 'loginName', 'password');
				params['street'] = this.formData.street;
				params['community'] = this.formData.community;
				this.$http.post("/auth/register", params).then(res => {
					uni.setStorageSync("registerMsg", "注册成功");
					uni.reLaunch({
						url: '../login/login',
					});
				});
			},
		},
	}
</script>
<style scoped>
	.page {
		background: #FFF !important;
	}

	.grace-form-label {
		width: auto;
	}

	.grace-check-item-v {
		margin-left: 20rpx;
	}

	.radio-group {
		display: flex;
		align-items: flex-end;
		flex-direction: row-reverse;
	}
</style>
