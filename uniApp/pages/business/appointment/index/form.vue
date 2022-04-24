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
				for (let i = 0; i < this.formColumns.length; i++) {
					let e = this.formColumns[i];
					if (e.name == 'warehouse') {
					  await this.$http.get(`/warehouse/list?stateName=正常`).then(res => {
					    e.dropboxGroup = res.data.map(e => {
					      return {
					        value: e.id,
					        label: e.name
					      };
					    });
					  });
					}
					if (e.name == 'goodType') {
						await apiUtils.getDictData('goodType').then(res => {
							e.dropboxGroup = res.data.map(e => {
								return {
									value: e.id,
									label: e.name
								};
							});
						});
					}
					if (e.name == 'goodSource') {
						await apiUtils.getDictData('goodSource').then(res => {
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
				debugger
				if (this.action == '新增') {
					this.$http.post("/appointment", formData).then(res => {
						uni.setStorageSync("msg", "新增成功");
						uni.navigateBack({
							delta: 1
						});
					});
				} else if (this.action == '修改') {
					formData['id'] = this.id;
					this.$http.put("/appointment", formData).then(res => {
						uni.setStorageSync("msg", "修改成功");
						uni.navigateBack({
							delta: 1
						});
					});
				}
			},
			getFormColumns() {
				this.formColumns = [{
					label: "上报的省",
					name: "area",
					type: "area",
					placeholder: "请选择上报的省",
					value: '',
					checkType: "notnull",
					checkRule: "",
					errorMsg: "请选择上报的省"
				}, {
          label: "预约监管仓",
          name: "warehouse",
          type: "dropbox",
          dropboxGroup: [],
          placeholder: "请选择预约监管仓",
          value: '',
          checkType: "notnull",
          checkRule: "",
          errorMsg: "请选择预约监管仓"
        }, {
          label: "预约入仓时间",
          name: "appointmentWarehousingDate",
          type: "date",
          placeholder: "请选择预约入仓时间",
          value: '',
          checkType: "notnull",
          checkRule: "",
          errorMsg: "请选择预约入仓时间"
        },{
          label: "货物类别",
          name: "goodType",
          type: "dropbox",
          dropboxGroup: [],
          placeholder: "请选择货物类别",
          value: '',
          checkType: "notnull",
          checkRule: "",
          errorMsg: "请选择货物类别"
        }, {
          label: "货物名称",
          name: "goodName",
          type: "text",
          placeholder: "请输入货物名称",
          value: '',
          checkType: "notnull",
          checkRule: "",
          errorMsg: "请输入货物名称"
        },{
          label: "货物来源",
          name: "goodSource",
          type: "dropbox",
          dropboxGroup: [],
          placeholder: "请选择货物来源",
          value: '',
          checkType: "notnull",
          checkRule: "",
          errorMsg: "请选择货物来源"
        }, {
          label: "来源名称",
          name: "sourceName",
          type: "text",
          placeholder: "请输入来源名称",
          value: '',
          checkType: "notnull",
          checkRule: "",
          errorMsg: "请输入来源名称"
        }, {
          label: "原产国/产地",
          name: "originPlace",
          type: "text",
          placeholder: "请输入原产国/产地",
          value: '',
          checkType: "notnull",
          checkRule: "",
          errorMsg: "请输入原产国/产地"
        }, {
          label: "件数",
          name: "amount",
          type: "text",
          placeholder: "请输入件数",
          value: '',
          checkType: "notnull",
          checkRule: "",
          errorMsg: "请输入件数"
        }, {
          label: "重量（Kg）",
          name: "weight",
          type: "text",
          placeholder: "请输入重量（Kg）",
          value: '',
          checkType: "notnull",
          checkRule: "",
          errorMsg: "请输入重量（Kg）"
        }, {
          label: "承运司机",
          name: "driver",
          type: "text",
          placeholder: "请输入承运司机",
          value: '',
          checkType: "notnull",
          checkRule: "",
          errorMsg: "请输入承运司机"
        }, {
          label: "车牌号",
          name: "carNumber",
          type: "text",
          placeholder: "请输入车牌号",
          value: '',
          checkType: "notnull",
          checkRule: "",
          errorMsg: "请输入车牌号"
        }, {
          label: "司机电话",
          name: "driverPhone",
          type: "text",
          placeholder: "请输入司机电话",
          value: '',
          checkType: "notnull",
          checkRule: "",
          errorMsg: "请输入司机电话"
        }, {
          label: "出仓证明",
          name: "warehousedProve",
          type: "img",
          placeholder: "请上传出仓证明",
          value: '',
          checkType: "",
          checkRule: "",
          errorMsg: ""
        }, {
          label: "检疫证明",
          name: "quarantineCertificate",
          type: "img",
          placeholder: "请上传检疫证明",
          value: '',
          checkType: "notnull",
          checkRule: "",
          errorMsg: "请上传检疫证明"
        }, {
          label: "报关单",
          name: "customsBill",
          type: "img",
          placeholder: "请上传报关单",
          value: '',
          checkType: "notnull",
          checkRule: "",
          errorMsg: "请上传报关单"
        }, {
          label: "港口核酸证明",
          name: "portInspectionCertificate",
          type: "img",
          placeholder: "请上传港口核酸证明",
          value: '',
          checkType: "notnull",
          checkRule: "",
          errorMsg: "请上传港口核酸证明"
        }, {
          label: "港口消杀证明",
          name: "portDisinfectionCertificate",
          type: "img",
          placeholder: "请上传港口消杀证明",
          value: '',
          checkType: "notnull",
          checkRule: "",
          errorMsg: "请上传港口消杀证明"
        }]
			}
		}
	}
</script>

<style scoped>
</style>
