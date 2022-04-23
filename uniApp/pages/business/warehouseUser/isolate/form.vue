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
					this.$http.post("/warehouseUser/isolate", formData).then(res => {
						uni.setStorageSync("msg", "新增成功");
						uni.navigateBack({
							delta: 1
						});
					});
				} else if (this.action == '修改') {
					formData['id'] = this.id;
					this.$http.put("/warehouseUser/isolate", formData).then(res => {
						uni.setStorageSync("msg", "修改成功");
						uni.navigateBack({
							delta: 1
						});
					});
				}
			},
			getFormColumns() {
				this.formColumns = [{
					label: "隔离时间",
					name: "date",
					type: "date",
					placeholder: "请选择隔离时间",
					value: '',
					checkType: "notnull",
					checkRule: "",
					errorMsg: "请选择隔离时间"
				}, {
          label: "隔离地点",
          name: "place",
          type: "text",
          placeholder: "请输入隔离地点",
          value: '',
          checkType: "notnull",
          checkRule: "",
          errorMsg: "请输入隔离地点"
        },{
          label: "备注",
          name: "remark",
          type: "text",
          placeholder: "请输入备注",
          value: '',
          checkType: "notnull",
          checkRule: "",
          errorMsg: "请输入备注"
        }]
			}
		}
	}
</script>

<style scoped>
</style>
