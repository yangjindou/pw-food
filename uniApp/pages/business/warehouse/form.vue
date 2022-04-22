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
				this.getSelectList();
			}
		},
		methods: {
			async getSelectList() {
				for (var i = 0; i < this.formColumns.length; i++) {
					let e = this.formColumns[i];
					if (e.name == 'state') {
						await apiUtils.getDictData('waste_disposal_state').then(res => {
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
				if (this.action == '新增') {
					this.$http.post("/warehouse", formData).then(res => {
						uni.setStorageSync("msg", "新增成功");
						uni.navigateBack({
							delta: 1
						});
					});
				} else if (this.action == '修改') {
					formData['id'] = this.id;
					this.$http.put("/warehouse", formData).then(res => {
						uni.setStorageSync("msg", "修改成功");
						uni.navigateBack({
							delta: 1
						});
					});
				}
			},
			getFormColumns() {
				this.formColumns = [{
					label: "国家平台备案号",
					name: "recordNumber",
					type: "text",
					placeholder: "请输入国家平台备案号",
					value: '',
					checkType: "notnull",
					checkRule: "",
					errorMsg: "请输入国家平台备案号"
				}, {
          label: "监管仓编码",
          name: "code",
          type: "text",
          placeholder: "请输入监管仓编码",
          value: '',
          checkType: "notnull",
          checkRule: "",
          errorMsg: "请输入监管仓编码"
        }, {
          label: "监管仓名称",
          name: "name",
          type: "text",
          placeholder: "请输入监管仓名称",
          value: '',
          checkType: "notnull",
          checkRule: "",
          errorMsg: "请输入监管仓名称"
        }, {
          label: "集中监管仓分类",
          name: "type",
          type: "text",
          placeholder: "请输入集中监管仓分类",
          value: '',
          checkType: "notnull",
          checkRule: "",
          errorMsg: "请输入集中监管仓分类"
        }, {
          label: "建筑面积（平方米）",
          name: "s",
          type: "text",
          placeholder: "请输入建筑面积",
          value: '',
          checkType: "notnull",
          checkRule: "",
          errorMsg: "请输入建筑面积"
        }, {
          label: "容量（千克）",
          name: "v",
          type: "text",
          placeholder: "请输入容量",
          value: '',
          checkType: "notnull",
          checkRule: "",
          errorMsg: "请输入容量"
        }, {
          label: "监管仓所在省（市、区）",
          name: "area",
          type: "area",
          placeholder: "请输入监管仓所在省",
          value: '',
          checkType: "notnull",
          checkRule: "",
          errorMsg: "请输入监管仓所在省"
        }, {
          label: "详细地址",
          name: "address",
          type: "text",
          placeholder: "请输入详细地址",
          value: '',
          checkType: "notnull",
          checkRule: "",
          errorMsg: "请输入详细地址"
        }, {
          label: "启用日期",
          name: "enableDate",
          type: "date",
          placeholder: "请选择启用日期",
          value: '',
          checkType: "notnull",
          checkRule: "",
          errorMsg: "请选择启用日期"
        }, {
          label: "运营企业名称",
          name: "enterpriseName",
          type: "text",
          placeholder: "请输入运营企业名称",
          value: '',
          checkType: "notnull",
          checkRule: "",
          errorMsg: "请输入运营企业名称"
        }, {
          label: "运营企业统一社会信用代码",
          name: "enterpriseCode",
          type: "text",
          placeholder: "运营企业统一社会信用代码",
          value: '',
          checkType: "notnull",
          checkRule: "",
          errorMsg: "请输入运营企业统一社会信用代码"
        }, {
          label: "联系电话",
          name: "phone",
          type: "text",
          placeholder: "请输入联系电话",
          value: '',
          checkType: "notnull",
          checkRule: "",
          errorMsg: "请输入联系电话"
        }, {
          label: "集中监管仓经度",
          name: "longitude",
          type: "text",
          placeholder: "请输入集中监管仓经度",
          value: '',
          checkType: "notnull",
          checkRule: "",
          errorMsg: "请输入集中监管仓经度"
        }, {
          label: "集中监管仓纬度",
          name: "latitude",
          type: "text",
          placeholder: "请输入集中监管仓纬度",
          value: '',
          checkType: "notnull",
          checkRule: "",
          errorMsg: "请输入集中监管仓纬度"
        }, {
          label: "监管仓状态",
          name: "state",
          type: "dropbox",
          dropboxGroup: [],
          placeholder: "请选择监管仓状态",
          value: '',
          checkType: "notnull",
          checkRule: "",
          errorMsg: "请选择监管仓状态"
        }]
			}
		}
	}
</script>

<style scoped>
</style>
