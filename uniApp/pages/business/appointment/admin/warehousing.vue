<template>
	<view class="page">
		<gracePage headerBG="#0088FE" :bounding="false">
			<ugNav slot="gHeader" :isBack="true" title="入仓" />
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
			async getSelectList() {
				// let params = {
				// 	warehouseCreateUser: uni.getStorageSync('userData')['id']
				// };
				// for (let i = 0; i < this.formColumns.length; i++) {
				// 	let e = this.formColumns[i];
				// 	if (e.name == 'uninstallUser') {
				// 		await this.$http.get(`/warehouseUser/list`, {params}).then(res => {
				// 			e.dropboxGroup = res.data.map(e => {
				// 				return {
				// 					value: e.id,
				// 					label: e.name
				// 				};
				// 			});
				// 		});
				// 	}
				// }
				// this.$nextTick(() => {
				// 	this.$refs.form.changeColumns(this.formColumns);
				// });
			},
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
					label: "禽类（Kg）",
					name: "warehousingWeightPoultry",
					type: "text",
					placeholder: "请输入禽类",
					value: '',
					checkType: "numbers",
					checkRule: "1,11",
					errorMsg: "请输入禽类"
				},{
					label: "畜类（Kg）",
					name: "warehousingWeightLivestock",
					type: "text",
					placeholder: "请输入畜类",
					value: '',
					checkType: "numbers",
					checkRule: "1,11",
					errorMsg: "请输入畜类"
				},{
					label: "水产品（Kg）",
					name: "warehousingWeightAquatic",
					type: "text",
					placeholder: "请输入水产品",
					value: '',
					checkType: "numbers",
					checkRule: "1,11",
					errorMsg: "请输入水产品"
				},{
					label: "其他（Kg）",
					name: "warehousingWeightOther",
					type: "text",
					placeholder: "请输入其他",
					value: '',
					checkType: "numbers",
					checkRule: "1,11",
					errorMsg: "请输入其他"
				}]
			}
		}
	}
</script>

<style scoped>
</style>
