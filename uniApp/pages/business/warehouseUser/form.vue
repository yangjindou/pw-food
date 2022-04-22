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
        let params = {
          createUser: uni.getStorageSync('userData')['id']
        }
        for (let i = 0; i < this.formColumns.length; i++) {
          let e = this.formColumns[i];
          if (e.name == 'warehouse') {
            await this.$http.get(`/warehouse/list`, {params}).then(res => {
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
					label: "姓名",
					name: "name",
					type: "text",
					placeholder: "请输入姓名",
					value: '',
					checkType: "notnull",
					checkRule: "",
					errorMsg: "请输入姓名"
				}, {
          label: "性别",
          name: "gender",
          type: "radio",
          radioGroup: [{
            label: '男',
            value: '男',
          }, {
            label: '女',
            value: '女',
          }],
          checkType: "notnull",
          checkRule: "",
          errorMsg: "请选择性别"
        }, {
          label: "年龄",
          name: "age",
          type: "text",
          placeholder: "请输入年龄",
          value: '',
          checkType: "notnull",
          checkRule: "",
          errorMsg: "请输入年龄"
        }, {
          label: "手机号",
          name: "phone",
          type: "text",
          placeholder: "请输入手机号",
          value: '',
          checkType: "notnull",
          checkRule: "",
          errorMsg: "请输入手机号"
        }, {
          label: "工作种类",
          name: "workType",
          type: "text",
          placeholder: "请输入工作种类",
          value: '',
          checkType: "notnull",
          checkRule: "",
          errorMsg: "请输入工作种类"
        }, {
          label: "监管仓",
          name: "warehouse",
          type: "dropbox",
          dropboxGroup: [],
          placeholder: "请选择监管仓",
          value: '',
          checkType: "notnull",
          checkRule: "",
          errorMsg: "请选择监管仓"
        }, {
          label: "是否接种疫苗",
          name: "isInoculate",
          type: "radio",
          radioGroup: [{
            label: '是',
            value: '是',
          }, {
            label: '否',
            value: '否',
          }],
          value: '',
          checkType: "notnull",
          checkRule: "",
          errorMsg: "请选择是否接种疫苗"
        }, {
          label: "接种次数",
          name: "inoculateCount",
          type: "text",
          placeholder: "请输入接种次数",
          value: '',
          checkType: "notnull",
          checkRule: "",
          errorMsg: "请输入接种次数"
        }, {
          label: "接种时间",
          name: "inoculateDate",
          type: "date",
          placeholder: "请选择接种时间",
          value: '',
          checkType: "notnull",
          checkRule: "",
          errorMsg: "请选择接种时间"
        }]
			}
		}
	}
</script>

<style scoped>
</style>
