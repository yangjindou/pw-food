<template>
	<view class="page">
		<gracePage headerBG="#0088FE" :bounding="false">
			<ugNav slot="gHeader" :isBack="true" title="申请修改" />
			<view class="grace-body" slot="gBody">
				<view class="text-center grace-margin-top">当前已经申请修改{{count}}次，申请3次后将无法申请修改</view>
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
				uni.removeStorageSync("formData");
				this.id = formData["id"];
				if (formData['applyUpdateCount']) {
					this.count = formData['applyUpdateCount'];
				}
				// for (let key in formData) {
				// 	for (let i = 0; i < this.formColumns.length; i++) {
				// 		if (key == this.formColumns[i].name) {
				// 			this.formColumns[i].value = formData[key];
				// 			if (this.formColumns[i]['type'] === 'dropbox') {
				// 				this.formColumns[i].valueLabel = formData[key + 'Name'];
				// 			}
				// 			if (disabled) {
				// 				this.formColumns[i].disabled = disabled;
				// 			}
				// 			break;
				// 		}
				// 	}
				// }
			},
			formSubmit(formData) {
				formData['id'] = this.id;
				formData['filingState'] = '申请修改';
				this.$http.put("/appointment", formData).then(res => {
					uni.setStorageSync("msg", "修改成功");
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
					value: '',
					checkType: "notnull",
					checkRule: "",
					errorMsg: "请输入修改理由"
				}]
			}
		}
	}
</script>

<style scoped>
</style>
