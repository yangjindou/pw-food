<template>
	<view class="page">
		<gracePage headerBG="#0088FE" :bounding="false">
			<ugNav slot="gHeader" :isBack="true" :title="action == 'M'?'修改':'添加'"></ugNav>
			<view class="grace-body" slot="gBody">
				<form @submit="formSubmit" class="grace-form">
					<view v-for="(item, index) in formColumns" :key="index" class="grace-form-item grace-border-b">
						<text class="grace-form-label">{{item.label}}</text>
						<!-- 输入框 -->
						<view v-if="item.type == 'text'" class="grace-form-body">
							<input type="text" :disabled="item.disabled" class="grace-form-input" v-model="item.value"
								:name="item.name" :placeholder="item.placeholder" />
						</view>
						<view v-if="item.type == 'password'" class="grace-form-body">
							<input type="password" :disabled="item.disabled" class="grace-form-input"
								v-model="item.value" :name="item.name" :placeholder="item.placeholder" />
						</view>
						<!-- 地区 -->
						<view v-if="item.type == 'area'" class="grace-form-body">
							<input @tap="openPicker" type="text" :disabled="item.disabled" class="grace-form-input"
								v-model="item.value" :name="item.name" :placeholder="item.placeholder" />
							<lotus-address :name="item.name" v-on:choseVal="choseValue"
								:lotusAddressData="lotusAddressData">
							</lotus-address>
						</view>
						<!-- 日期 -->
						<view v-if="item.type == 'date'" class="grace-form-body">
							<ugDateTime @confirm="dateChange" :name="item.name" :data-name="item.name"
								:value="item.value" :isTime="false">
								<input maxlength="-1" type="text" :disabled="true" class="grace-form-input"
									v-model="item.value" :name="item.name" :placeholder="item.placeholder" />
							</ugDateTime>
						</view>
						<text v-if="item.type == 'date'" class="grace-icons icon-arrow-down"
							style="margin-left:5px;"></text>
						<!-- 单选框 -->
						<view v-if="item.type == 'radio'" class="grace-form-body" style="padding:20rpx 0;">
							<radio-group :disabled="item.disabled" class="radio-group" :name="item.name">
								<label v-for="(radio, radioIndex) in item.radioGroup" :key="radioIndex"
									class="grace-check-item-v">
									<radio :checked="item.value == radio.value" :value="radio.value">{{radio.label}}
									</radio>
								</label>
							</radio-group>
						</view>
						<!-- 下拉框 -->
						<view v-if="item.type == 'dropbox'" class="grace-form-body" style="padding:20rpx 0;">
							<ugDropbox :name="item.name" :dropboxGroup="item.dropboxGroup"
								@dropboxChange="dropboxChange">
								<input type="text" :disabled="true" class="grace-form-input" v-model="item.value"
									:name="item.name" :placeholder="item.placeholder" />
								<!-- <text class="grace-text">{{item["dropboxGroup"][item["value"]]}}</text> -->
								<text class="grace-icons icon-arrow-down" style="margin-left:5px;"></text>
							</ugDropbox>
						</view>
					</view>
					<view style="padding:22rpx 0;">
						<button class="grace-button" style="line-height:80rpx;" formType="submit"
							type="primary">保存</button>
					</view>
				</form>
			</view>
		</gracePage>
	</view>
</template>
<script>
	var _this;
	var graceChecker = require("@/graceUI/jsTools/graceChecker.js");
	export default {
		data() {
			return {
				action: '',
				id: '',
				formColumns: [],
				lotusAddressData: {
					visible: false,
					provinceName: '',
					cityName: '',
					townName: '',
				},
			}
		},
		onLoad(e) {
			this.action = e.action;
			this.getFormColumns();
			if (this.action == "M") {
				var formData = uni.getStorageSync("formData");
				uni.removeStorageSync("formData");
				this.id = formData["id"];
				for (var key in formData) {
					for (var i = 0; i < this.formColumns.length; i++) {
						if (key == this.formColumns[i].name) {
							this.formColumns[i].value = formData[key];
							break;
						}
					}
				}
			}
		},
		methods: {
			formSubmit: function(e) {
				console.log(e);
				_this = this;
				var formData = e.detail.value;
				var checkRes = graceChecker.check(formData, _this.formColumns);
				if (checkRes) {
					var password = formData["password"];
					delete formData["password"]; // 删除密码
					delete formData["rpassword"]; // 删除确认密码
					formData["role"] = "用户";
					if (_this.action == "C") {
						formData["createUser"] = uni.getStorageSync("userData")["id"];
						let datas = {
							password: password,
							formData: JSON.stringify(formData)
						}
						_this.$http.post("register", datas).then(res => {
							if (res.data.code != "200") {
								uni.showToast({
									icon: 'none',
									position: 'center',
									title: res.data.msg
								});
							} else {
								uni.setStorageSync("msg", "保存成功");
								uni.navigateBack({
									delta: 1
								});
							}
						});
					} else {
						formData["updateUser"] = uni.getStorageSync("userData")["id"];
						formData["id"] = _this.id;
						var json = {
							table: "users",
							tabledata: formData
						}
						let datas = {
							action: _this.action,
							v_json: JSON.stringify(json)
						}
						_this.$http.post("manyJson", datas).then(res => {
							if (res.data.code != "200") {
								uni.showToast({
									icon: 'none',
									position: 'center',
									title: res.data.msg
								});
							} else {
								uni.setStorageSync("msg", "保存成功");
								uni.navigateBack({
									delta: 1
								});
							}
						});
					}
				} else {
					uni.showToast({
						title: graceChecker.error,
						icon: "none"
					});
				}
			},
			dateChange(res, name) {
				_this = this;
				var dateStr = res[0] + '-' + res[1] + '-' + res[2];
				_this.formColumns.some((item) => {
					if (item.name == name) {
						item.value = dateStr
						return true;
					}
				});
			},
			dropboxChange(e, name) {
				_this = this;
				var index = e.detail.value;
				_this.formColumns.some((item) => {
					if (item.name == name) {
						item.value = item.dropboxGroup[index];
						return true;
					}
				});
			},
			getFormColumns() {
				if (this.action == "M") {
					this.formColumns = [{
						label: "账号",
						name: "name",
						type: "text",
						placeholder: "请输入账号",
						disabled: true,
						value: '',
						checkType: "notnull",
						checkRule: "",
						errorMsg: "请输入账号"
					}, {
						label: "地区",
						name: "area",
						type: "area",
						placeholder: "请输入地区",
						disabled: false,
						value: '',
						checkType: "notnull",
						checkRule: "",
						errorMsg: "请选择地区"
					}]
				} else {
					this.formColumns = [{
						label: "账号",
						name: "name",
						type: "text",
						placeholder: "请输入账号",
						disabled: false,
						value: '',
						checkType: "notnull",
						checkRule: "",
						errorMsg: "请输入账号"
					}, {
						label: "密码",
						name: "password",
						type: "password",
						placeholder: "请输入密码",
						disabled: false,
						value: '',
						checkType: "notnull",
						checkRule: "",
						errorMsg: "请输入密码"
					}, {
						label: "确认密码",
						name: "rpassword",
						type: "password",
						placeholder: "请输入确认密码",
						disabled: false,
						value: '',
						checkType: "samewith",
						checkRule: "password",
						errorMsg: "两次密码不一致"
					}, {
						label: "地区",
						name: "area",
						type: "area",
						placeholder: "请输入地区",
						disabled: false,
						value: '',
						checkType: "notnull",
						checkRule: "",
						errorMsg: "请选择地区"
					}]
				}
			},
			openPicker() {
				this.lotusAddressData.visible = true;
			},
			//回传已选的省市区的值
			choseValue(res, name) {
				_this = this;
				this.lotusAddressData.visible = res.visible;
				//res.isChose = 1省市区已选 res.isChose = 0;未选
				if (res.isChose) {
					_this.formColumns.some((item) => {
						if (item.name == name) {
							item.value = `${res.province}/${res.city}/${res.town}`;
							return true;
						}
					});
				}
			}
		},
	}
</script>
<style scoped>
	.page {
		background: #FFF !important;
	}

	.grace-border-b {
		border-color: #F8F8F8;
	}

	.grace-form-label {
		width: auto;
	}
</style>
