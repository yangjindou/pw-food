<template>
	<view class="page">
		<gracePage headerBG="#0088FE" :bounding="false">
			<ugNav slot="gHeader" :isBack="true" title="日常健康记录" />
			<view class="grace-body" slot="gBody">
				<ugForm ref="form" :showSubmit="showSubmit" :columns="formColumns" @submit="formSubmit" />
			</view>
		</gracePage>
	</view>
</template>
<script>
	import apiUtils from '@/utils/apiUtils.js';
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
							if (this.formColumns[i]['type'] === 'dropbox') {
								this.formColumns[i].valueLabel = formData[key + 'Name'];
							}
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
					this.$http.post("/warehouseUser", formData).then(res => {
						uni.setStorageSync("msg", "新增成功");
						uni.navigateBack({
							delta: 1
						});
					});
				} else if (this.action == '修改') {
					formData['id'] = this.id;
					this.$http.put("/warehouseUser", formData).then(res => {
						uni.setStorageSync("msg", "修改成功");
						uni.navigateBack({
							delta: 1
						});
					});
				}
			},
			getFormColumns() {
				this.formColumns = [{
					label: "是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否",
					name: "option1",
					type: "longRadio",
					radioGroup: [{
						label: '是',
						value: '是',
					}, {
						label: '否',
						value: '否',
					}],
					checkType: "notnull",
					checkRule: "",
					errorMsg: "请选择选项1"
				}, {
					label: "是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否",
					name: "option2",
					type: "longRadio",
					radioGroup: [{
						label: '是',
						value: '是',
					}, {
						label: '否',
						value: '否',
					}],
					checkType: "notnull",
					checkRule: "",
					errorMsg: "请选择选项2"
				}, {
					label: "是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否",
					name: "option3",
					type: "longRadio",
					radioGroup: [{
						label: '是',
						value: '是',
					}, {
						label: '否',
						value: '否',
					}],
					checkType: "notnull",
					checkRule: "",
					errorMsg: "请选择选项3"
				}, {
					label: "是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否",
					name: "option4",
					type: "longRadio",
					radioGroup: [{
						label: '是',
						value: '是',
					}, {
						label: '否',
						value: '否',
					}],
					checkType: "notnull",
					checkRule: "",
					errorMsg: "请选择选项4"
				}, {
					label: "是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否",
					name: "option5",
					type: "longRadio",
					radioGroup: [{
						label: '是',
						value: '是',
					}, {
						label: '否',
						value: '否',
					}],
					checkType: "notnull",
					checkRule: "",
					errorMsg: "请选择选项5"
				}, {
					label: "是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否",
					name: "option6",
					type: "longRadio",
					radioGroup: [{
						label: '是',
						value: '是',
					}, {
						label: '否',
						value: '否',
					}],
					checkType: "notnull",
					checkRule: "",
					errorMsg: "请选择选项6"
				}, {
					label: "是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否",
					name: "option7",
					type: "longRadio",
					radioGroup: [{
						label: '是',
						value: '是',
					}, {
						label: '否',
						value: '否',
					}],
					checkType: "notnull",
					checkRule: "",
					errorMsg: "请选择选项7"
				}, {
					label: "是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否",
					name: "option8",
					type: "longRadio",
					radioGroup: [{
						label: '是',
						value: '是',
					}, {
						label: '否',
						value: '否',
					}],
					checkType: "notnull",
					checkRule: "",
					errorMsg: "请选择选项8"
				}, {
					label: "是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否",
					name: "option9",
					type: "longRadio",
					radioGroup: [{
						label: '是',
						value: '是',
					}, {
						label: '否',
						value: '否',
					}],
					checkType: "notnull",
					checkRule: "",
					errorMsg: "请选择选项9"
				}, {
					label: "是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否",
					name: "option10",
					type: "longRadio",
					radioGroup: [{
						label: '是',
						value: '是',
					}, {
						label: '否',
						value: '否',
					}],
					checkType: "notnull",
					checkRule: "",
					errorMsg: "请选择选项10"
				}]
			}
		}
	}
</script>

<style scoped>
</style>
