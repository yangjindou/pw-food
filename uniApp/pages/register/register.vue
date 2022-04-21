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
	import apiUtils from '@/utils/apiUtils.js';
	export default {
		data() {
			return {
				formColumns: [],
			}
		},
		onLoad() {
			this.getFormColumns();
			this.getSelectList();
		},
		methods: {
			async getSelectList() {
				for (var i = 0; i < this.formColumns.length; i++) {
					let e = this.formColumns[i];
					if (e.name == 'area') {
						await apiUtils.getDictData('register_area').then(res => {
							e.dropboxGroup = res.data.map(e => {
								return {
									value: e.id,
									label: e.name
								};
							});
						});
					}
				}
				this.$refs.form.changeColumns(this.formColumns);
			},
			getFormColumns() {
				this.formColumns = [{
					label: "登录名",
					name: "loginName",
					type: "text",
					placeholder: "请输入登录名",
					value: '',
					checkType: "notnull",
					checkRule: "",
					errorMsg: "请输入登录名"
				}, {
					label: "用户名",
					name: "userName",
					type: "text",
					placeholder: "请输入用户名",
					value: '',
					checkType: "notnull",
					checkRule: "",
					errorMsg: "请输入用户名"
				}, {
					label: "属地",
					name: "area",
					type: "dropbox",
					dropboxGroup: [],
					placeholder: "请选择属地",
					value: '',
					checkType: "notnull",
					checkRule: "",
					errorMsg: "请选择属地"
				}, {
					label: "联系电话",
					name: "phone",
					type: "text",
					placeholder: "请输入联系电话",
					value: '',
					checkType: "phoneno",
					checkRule: "",
					errorMsg: "请输入联系电话"
				}, {
					label: "企业名称",
					name: "enterpriseName",
					type: "text",
					placeholder: "请输入企业名称",
					value: '',
					checkType: "notnull",
					checkRule: "",
					errorMsg: "请输入企业名称"
				}, {
					label: "社会统一信用代码",
					name: "enterpriseCode",
					type: "text",
					placeholder: "请输入社会统一信用代码",
					value: '',
					checkType: "notnull",
					checkRule: "",
					errorMsg: "请输入社会统一信用代码"
				}, {
					label: "法人",
					name: "enterpriseOwner",
					type: "text",
					placeholder: "请输入法人",
					value: '',
					checkType: "notnull",
					checkRule: "",
					errorMsg: "请输入法人"
				}, {
					label: "营业执照注册地址",
					name: "enterpriseLicenseAddress",
					type: "text",
					placeholder: "请输入营业执照注册地址",
					value: '',
					checkType: "notnull",
					checkRule: "",
					errorMsg: "请输入营业执照注册地址"
				}, {
					label: "营业执照照片",
					name: "enterpriseLicenseImg",
					type: "img",
					maxFileNumber: 1,
					placeholder: "请输入营业执照照片",
					value: '',
					checkType: "notnull",
					checkRule: "",
					errorMsg: "请输入营业执照照片"
				}]
			},
			formSubmit(formData) {
				this.$http.post("/auth/register", formData).then(res => {
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
