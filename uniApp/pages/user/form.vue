<template>
	<view class="page">
		<gracePage headerBG="#0088FE" :bounding="false">
			<ugNav slot="gHeader" :isBack="true" :title="action" />
			<view class="grace-body" slot="gBody">
				<ugForm :showSubmit="showSubmit" :columns="formColumns" @submit="formSubmit"></ugForm>
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
			if (e.action) {
				this.action = e.action;
				this.getFormColumns();
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
			formSubmit(formData) {
				if (this.action == '新增') {
					this.$http.post("/user", formData).then(res => {
						uni.setStorageSync("msg", "上报成功");
						uni.navigateBack({
							delta: 1
						});
					});
				} else if (this.action == '修改') {
					formData['id'] = this.id;
					this.$http.put("/user", formData).then(res => {
						uni.setStorageSync("msg", "修改成功");
						uni.navigateBack({
							delta: 1
						});
					});
				}
			},
			getFormColumns() {
				if(this.action == '新增') {
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
						label: "角色",
						name: "role",
						type: "text",
						placeholder: "监管仓管理员",
						disabled: true,
						value: '',
						checkType: "notnull",
						checkRule: "",
						errorMsg: ""
					}]
				} else {
					this.formColumns = [{
						label: "登录名",
						name: "loginName",
						type: "text",
						placeholder: "请输入登录名",
						disabled: true,
						value: '',
						checkType: "notnull",
						checkRule: "",
						errorMsg: "请输入登录名"
					}, {
						label: "用户名",
						name: "userName",
						type: "text",
						placeholder: "请输入用户名",
						disabled: true,
						value: '',
						checkType: "notnull",
						checkRule: "",
						errorMsg: "请输入用户名"
					}, {
						label: "角色",
						name: "role",
						type: "text",
						placeholder: "监管仓管理员",
						disabled: true,
						value: '',
						checkType: "notnull",
						checkRule: "",
						errorMsg: ""
					},{
						label: "是否禁用",
						name: "disabled",
						type: "radio",
						radioGroup: [{
							label: '是',
							value: true,
						}, {
							label: '否',
							value: false,
						}],
						value: '',
						checkType: "",
						checkRule: "",
						errorMsg: ""
					},]
				}
			}
		}
	}
</script>

<style scoped>
</style>
