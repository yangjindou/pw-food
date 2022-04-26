<template>
	<view class="page">
		<gracePage headerBG="#0088FE" :bounding="false">
			<ugNav slot="gHeader" :isBack="true" title="采样" />
			<view class="grace-body" slot="gBody">
				<ugForm ref="form" :showSubmit="showSubmit" :columns="formColumns"
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
				this.$http.put("/appointment", formData).then(res => {
					uni.setStorageSync("msg", "操作成功");
					uni.navigateBack({
						delta: 1
					});
				});
			},
			getFormColumns() {
				this.formColumns = [{
					label: "采样货物名称",
					name: "samplingGoodName",
					type: "text",
					placeholder: "请输入采样货物名称",
					value: '',
					checkType: "notnull",
					checkRule: "",
					errorMsg: "请输入采样货物名称"
				}, {
					label: "采样数量",
					name: "samplingAmount",
					type: "text",
					placeholder: "请输入采样数量",
					value: '',
					checkType: "notnull",
					checkRule: "",
					errorMsg: "请输入正确的采样数量"
				}, {
					label: "采样检测人",
					name: "samplingUser",
					type: "text",
					placeholder: "请输入采样检测人",
					value: '',
					checkType: "notnull",
					checkRule: "",
					errorMsg: "请输入采样检测人"
				}, {
					label: "备注",
					name: "samplingRemark",
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
