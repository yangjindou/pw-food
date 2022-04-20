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
					this.$http.post("/wasteDisposal", formData).then(res => {
						uni.setStorageSync("msg", "新增成功");
						uni.navigateBack({
							delta: 1
						});
					});
				} else if (this.action == '修改') {
					formData['id'] = this.id;
					this.$http.put("/wasteDisposal", formData).then(res => {
						uni.setStorageSync("msg", "修改成功");
						uni.navigateBack({
							delta: 1
						});
					});
				}
			},
			getFormColumns() {
				this.formColumns = [{
					label: "物品名称",
					name: "itemName",
					type: "text",
					placeholder: "请输入物品名称",
					value: '',
					checkType: "notnull",
					checkRule: "",
					errorMsg: "请输入物品名称"
				}, {
					label: "物品数量",
					name: "itemAmount",
					type: "text",
					placeholder: "请输入物品数量",
					value: '',
					checkType: "notnull",
					checkRule: "",
					errorMsg: "请输入物品数量"
				}, {
					label: "存放地方",
					name: "savePlace",
					type: "text",
					placeholder: "请输入存放地方",
					value: '',
					checkType: "notnull",
					checkRule: "",
					errorMsg: "请输入存放地方"
				}, {
					label: "是否存放48小时",
					name: "isSave48hour",
					type: "radio",
					radioGroup: [{
						label: '是',
						value: '是',
					}, {
						label: '否',
						value: '否',
					}],
					placeholder: "请选择是否存放48小时",
					value: '',
					checkType: "notnull",
					checkRule: "",
					errorMsg: "请选择是否存放48小时"
				}, {
					label: "备注",
					name: "remark",
					type: "text",
					placeholder: "请输入备注",
					value: '',
					checkType: "",
					checkRule: "",
					errorMsg: ""
				}]
			}
		}
	}
</script>

<style scoped>
</style>
