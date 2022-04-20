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
					this.$http.post("/device", formData).then(res => {
						uni.setStorageSync("msg", "新增成功");
						uni.navigateBack({
							delta: 1
						});
					});
				} else if (this.action == '修改') {
					formData['id'] = this.id;
					this.$http.put("/device", formData).then(res => {
						uni.setStorageSync("msg", "修改成功");
						uni.navigateBack({
							delta: 1
						});
					});
				}
			},
			getFormColumns() {
				this.formColumns = [{
					label: "检验计量器",
					name: "inspectionMeter",
					type: "text",
					placeholder: "请输入检验计量器",
					value: '',
					checkType: "notnull",
					checkRule: "",
					errorMsg: "请输入检验计量器"
				}, {
					label: "是否定期校验",
					name: "isPeriodicInspection",
					type: "radio",
					radioGroup: [{
						label: '是',
						value: '是',
					}, {
						label: '否',
						value: '否',
					}],
					placeholder: "请选择是否定期校验",
					value: '',
					checkType: "notnull",
					checkRule: "",
					errorMsg: "请选择是否定期校验"
				}, {
					label: "校验日期",
					name: "inspectionDate",
					type: "date",
					placeholder: "请选择校验日期",
					value: '',
					checkType: "notnull",
					checkRule: "",
					errorMsg: "请选择校验日期"
				}, {
					label: "检测废弃物是否按规定存放",
					name: "isRequiredSave",
					type: "radio",
					radioGroup: [{
						label: '是',
						value: '是',
					}, {
						label: '否',
						value: '否',
					}],
					placeholder: "请选择检测废弃物是否按规定存放",
					value: '',
					checkType: "notnull",
					checkRule: "",
					errorMsg: "请选择检测废弃物是否按规定存放"
				}]
			}
		}
	}
</script>

<style scoped>
</style>
