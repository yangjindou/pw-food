<template>
	<view class="page">
		<gracePage headerBG="#0088FE" :bounding="false">
			<ugNav slot="gHeader" :isBack="true" title="处理修改" />
			<view class="grace-body" slot="gBody">
				<ugForm class="grace-padding-top" ref="form" :showSubmit="showSubmit" :columns="formColumns"
					@submit="formSubmit" />
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
				count: 0,
			}
		},
		onLoad(e) {
			if (e.action) {
				this.action = e.action;
				this.getFormColumns();
				this.showSubmit = this.action != "详情";
				this.setFormData();
				this.getSelectList();
			}
		},
		methods: {
			async getSelectList() {},
			setFormData() {
				const formData = uni.getStorageSync("formData");
				const disabled = this.action == "详情";
				uni.removeStorageSync("formData");
				if (formData['applyUpdateCount']) {
					this.count = formData['applyUpdateCount'];
				}
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
				formData['id'] = this.id;
				if (formData['state'] === '同意') {
					formData['updateReason'] = '';
					formData['filingState'] = '保存';
					formData['applyUpdateCount'] = this.count + 1;
				} else {
					delete formData['updateReason'];
					formData['filingState'] = '审核通过';
				}
				this.$http.put("/appointment", formData).then(res => {
					uni.setStorageSync("msg", "操作成功");
					uni.navigateBack({
						delta: 1
					});
				});
			},
			getFormColumns() {
				this.formColumns = [{
					label: "修改理由",
					name: "updateReason",
					type: "text",
					placeholder: "请输入修改理由",
					disabled: true,
					value: '',
					checkType: "notnull",
					checkRule: "",
					errorMsg: "请输入修改理由"
				}, {
					label: "修改",
					name: "state",
					type: "radio",
					radioGroup: [{
						label: '同意',
						value: '同意',
					}, {
						label: '驳回',
						value: '驳回',
					}],
					value: '',
					checkType: "notnull",
					checkRule: "",
					errorMsg: "请选择修改是否同意"
				}]
			}
		}
	}
</script>

<style scoped>
</style>
