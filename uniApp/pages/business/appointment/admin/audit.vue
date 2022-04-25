<template>
	<view class="page">
		<gracePage headerBG="#0088FE" :bounding="false">
			<ugNav slot="gHeader" :isBack="true" title="审核" />
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
				if(formData['filingState'] === '驳回' && !formData['refuseReason']) {
					this.$common.showToast(`请填写驳回理由`);
					return;
				}
				this.$http.put("/appointment", formData).then(res => {
					uni.setStorageSync("msg", "修改成功");
					uni.navigateBack({
						delta: 1
					});
				});
			},
			getFormColumns() {
				this.formColumns = [ {
					label: "审核",
					name: "filingState",
					type: "radio",
					radioGroup: [{
						label: '审核通过',
						value: '审核通过',
					}, {
						label: '驳回',
						value: '驳回',
					}],
					value: '',
					checkType: "notnull",
					checkRule: "",
					errorMsg: "请选择审核"
				},{
					label: "驳回理由",
					name: "refuseReason",
					type: "text",
					placeholder: "审核驳回需要填写驳回理由",
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
