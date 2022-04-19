<template>
	<view class="page">
		<gracePage headerBG="#0088FE" :bounding="false">
			<ugNav slot="gHeader" :isBack="true" :title="action" />
			<view class="grace-body" slot="gBody">
				<ugForm :showSubmit="showSubmit" :columns="formColumns" @submit="formSubmit"></ugForm>
			</view>
		</gracePage>
	</view>
</template>
<script>
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
			}
		},
		methods: {
			setFormData() {
				const formData = uni.getStorageSync("formData");
				const disabled = this.action == "详情";
				uni.removeStorageSync("formData");
				this.id = formData["id"];
				for (let key in formData) {
					for (let i = 0; i < this.formColumns.length; i++) {
						if (key == this.formColumns[i].name) {
							this.formColumns[i].value = formData[key];
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
					this.$http.post("/notice", formData).then(res => {
						uni.setStorageSync("msg", "新增成功");
						uni.navigateBack({
							delta: 1
						});
					});
				} else if (this.action == '修改') {
					formData['id'] = this.id;
					this.$http.put("/notice", formData).then(res => {
						uni.setStorageSync("msg", "修改成功");
						uni.navigateBack({
							delta: 1
						});
					});
				}
			},
			getFormColumns() {
					this.formColumns = [{
						label: "公告标题",
						name: "title",
						type: "text",
						placeholder: "请输入公告标题",
						value: '',
						checkType: "notnull",
						checkRule: "",
						errorMsg: "请输入公告标题"
					}, {
						label: "公告内容",
						name: "content",
						type: "textarea",
						placeholder: "请输入公告内容",
						value: '',
						checkType: "notnull",
						checkRule: "",
						errorMsg: "请输入公告内容"
					}]
			}
		}
	}
</script>

<style scoped>
</style>
