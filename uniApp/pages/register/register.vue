<template>
	<view class="page">
		<gracePage headerBG="#0088FE" :bounding="false">
			<ugNav slot="gHeader" :isBack="true" title="注册"></ugNav>
			<view class="grace-body" slot="gBody">
				<ugForm ref="form" :columns="formColumns" @submit="formSubmit" :submitbtn="['注册']" />
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
			}
		},
		onLoad() {
			this.getFormColumns();
		},
		methods: {
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
<style lang="less" scoped>
</style>
