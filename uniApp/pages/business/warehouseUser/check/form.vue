<template>
	<view class="page">
		<gracePage headerBG="#0088FE" :bounding="false">
			<ugNav slot="gHeader" :isBack="true" :title="action" />
			<view class="grace-body" slot="gBody">
				<ugForm ref="form" :showSubmit="showSubmit" :columns="formColumns" @submit="formSubmit"></ugForm>
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
				pid: '',
				formColumns: [],
				showSubmit: true,
				formData: {},
			}
		},
		onLoad(e) {
			if (e.action) {
				this.action = e.action;
				this.pid = e.pid;
				this.getFormColumns();
				this.showSubmit = this.action != "详情";
				this.setFormData();
				this.getSelectList();
			}
		},
		methods: {
			async getSelectList() {
				let params = {
					warehouseCreateUser: uni.getStorageSync('userData')['id']
				}
				for (let i = 0; i < this.formColumns.length; i++) {
					let e = this.formColumns[i];
					if (e.name == 'user') {
						await this.$http.get(`/warehouseUser/list`, {
							params
						}).then(res => {
							e.dropboxGroup = res.data.map(e => {
								return {
									value: e.id,
									label: e.name
								};
							});
						});
					}
				}
				this.$nextTick(() => {
					this.$refs.form.changeColumns(this.formColumns);
				});
			},
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
				formData['pid'] = this.pid;
				if (this.action == '新增') {
					this.$http.post("/warehouseUser/check", formData).then(res => {
						uni.setStorageSync("msg", "新增成功");
						uni.navigateBack({
							delta: 1
						});
					});
				} else if (this.action == '修改') {
					formData['id'] = this.id;
					this.$http.put("/warehouseUser/check", formData).then(res => {
						uni.setStorageSync("msg", "修改成功");
						uni.navigateBack({
							delta: 1
						});
					});
				}
			},
			getFormColumns() {
				this.formColumns = [{
					label: "检测时间",
					name: "date",
					type: "date",
					placeholder: "请选择检测时间",
					value: '',
					checkType: "notnull",
					checkRule: "",
					errorMsg: "请选择检测时间"
				}, {
					label: "检测结果",
					name: "result",
					type: "radio",
					radioGroup: [{
						label: '阴性',
						value: '阴性',
					}, {
						label: '阳性',
						value: '阳性',
					}],
					value: '',
					checkType: "notnull",
					checkRule: "",
					errorMsg: "请选择检测结果"
				}, {
					label: "检测人",
					name: "user",
					type: "dropbox",
					dropboxGroup: [],
					value: '',
					placeholder: "请选择检测人",
					checkType: "notnull",
					checkRule: "",
					errorMsg: "请选择检测人"
				}]
			}
		}
	}
</script>

<style scoped>
</style>
