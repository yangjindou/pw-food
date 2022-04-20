<template>
	<form @submit="formSubmit" class="grace-form">
		<view v-for="(item, index) in formColumns" :key="index" class="grace-form-item grace-border-b">
			<text class="grace-form-label">{{item.label}}</text>
			<!-- 时间段 -->
			<view v-if="item.type == 'rangDate'" class="grace-form-body">
				<input type="text" :disabled="true" class="grace-form-input" v-model="item.value"
					@tap="showRangeDatePick(item.name)" :name="item.name" :placeholder="item.placeholder" />
				<rangeDatePick :show="item.show" :value="item.value.split(' ~ ')" @change="rangeDateChange(item.name, $event)"
					@cancel="rangeDateCancel(item.name)" :start="start" :end="end" />
			</view>
			<!-- 输入框 -->
			<view v-if="item.type == 'text'" class="grace-form-body">
				<input type="text" :disabled="item.disabled" class="grace-form-input" v-model="item.value"
					:name="item.name" :placeholder="item.placeholder" />
			</view>
			<!-- 密码 -->
			<view v-if="item.type == 'password'" class="grace-form-body">
				<input type="password" :disabled="item.disabled" class="grace-form-input" v-model="item.value"
					:name="item.name" :placeholder="item.placeholder" />
			</view>
			<!-- 文本框 -->
			<view v-if="item.type == 'textarea'" class="grace-form-body">
				<textarea :disabled="item.disabled" class="grace-textarea" v-model="item.value" :name="item.name"
					:placeholder="item.placeholder" />
			</view>
			<!-- 地区 -->
			<view v-if="item.type == 'area'" class="grace-form-body">
				<input @tap="openPicker(item.disabled)" type="text" :disabled="true" class="grace-form-input"
					v-model="item.value" :name="item.name" :placeholder="item.placeholder" />
				<lotus-address :name="item.name" v-on:choseVal="choseValue" :lotusAddressData="lotusAddressData">
				</lotus-address>
			</view>
			<!-- 日期 -->
			<view v-if="item.type == 'date'" class="grace-form-body">
				<ugDateTime @confirm="dateChange" :name="item.name" :data-name="item.name" :value="item.value"
					:isTime="false">
					<input type="text" :disabled="true" class="grace-form-input" v-model="item.value" :name="item.name"
						:placeholder="item.placeholder" />
				</ugDateTime>
			</view>
			<text v-if="item.type == 'date'" class="grace-icons icon-arrow-down" style="margin-left:5px;"></text>
			<!-- 单选框 -->
			<view v-if="item.type == 'radio'" class="grace-form-body" style="padding:20rpx 0;">
				<radio-group class="radio-group" :name="item.name">
					<label v-for="(radio, radioIndex) in item.radioGroup" :key="radioIndex" class="grace-check-item-v">
						<radio :disabled="item.disabled" :checked="item.value == radio.value" :value="radio.value">
							{{radio.label}}
						</radio>
					</label>
				</radio-group>
			</view>
			<!-- 下拉框 -->
			<view v-if="item.type == 'dropbox'" class="grace-form-body" style="padding:20rpx 0;">
				<ugDropbox :name="item.name" :dropboxGroup="item.dropboxGroup" @dropboxChange="dropboxChange"
					:disabled="item.disabled">
					<input type="text" :disabled="true" class="grace-form-input" v-model="item.value" :name="item.name"
						:placeholder="item.placeholder" hidden />
					<input type="text" :disabled="true" class="grace-form-input" v-model="item.valueLabel"
						:name="item.name+'label'" :placeholder="item.placeholder" />
					<text class="grace-icons icon-arrow-down" style="margin-left:5px;"></text>
				</ugDropbox>
			</view>
			<!-- 上传图片 -->
			<view v-if="item.type == 'img'" class="grace-form-body radio-group">
				<input type="text" :disabled="true" class="grace-form-input" v-model="item.value" :name="item.name"
					:placeholder="item.placeholder" hidden />
				<ugSelectImg :disabled="item.disabled" @change="imgsChange" :name="item.name"
					:maxFileNumber="item.maxFileNumber ? item.maxFileNumber : 9" :urls="item.value"></ugSelectImg>
			</view>
		</view>
		<slot></slot>
		<view v-if="showSubmit" class="form-btn">
			<button v-for="item in submitbtn" :key="item" class="grace-button" formType="submit" type="primary"
				@touchstart="submitName = item">{{item}}</button>
		</view>
	</form>
</template>
<script>
	import objUtils from '@/utils/objUtils.js';
	import rangeDatePick from '@/components/pyh-rdtpicker/pyh-rdtpicker.vue';

	var graceChecker = require("@/graceUI/jsTools/graceChecker.js");
	export default {
		props: {
			columns: {
				type: Array,
				default: () => []
			},
			submitbtn: {
				type: Array,
				default: () => ["保存"]
			},
			showSubmit: {
				type: Boolean,
				default: true
			},
		},
		components: {
			rangeDatePick
		},
		data() {
			return {
				submitName: '',
				formColumns: [],
				lotusAddressData: {
					visible: false,
					provinceName: '',
					cityName: '',
					townName: '',
				},
				start: "2022-01-01",
				end: "2200-12-01"
			}
		},
		mounted(e) {
			this.$nextTick(() => {
				this.formColumns = objUtils.cloneObject(this.columns);
			});
		},
		methods: {
			rangeDateCancel(name) {
				this.formColumns.forEach((item) => {
					if (item.name == name) {
						this.$set(item, 'show', false);
					}
				});
			},
			rangeDateChange(name, value) {
				this.formColumns.forEach((item) => {
					if (item.name == name) {
						this.$set(item, 'value', value.join(' ~ '));
						this.$set(item, 'show', false);
					}
				});
			},
			showRangeDatePick(name) {
				this.formColumns.forEach((item) => {
					if (item.name == name) {
						this.$set(item, 'show', true);
					}
				});
			},
			changeColumns(obj) {
				this.formColumns = objUtils.cloneObject(obj);
			},
			//回传已选的省市区的值
			choseValue(res, name) {
				this.lotusAddressData.visible = res.visible;
				//res.isChose = 1省市区已选 res.isChose = 0;未选
				if (res.isChose) {
					this.formColumns.forEach((item) => {
						if (item.name == name) {
							item.value = `${res.province}/${res.city}/${res.town}`;
						}
					});
				}
			},
			openPicker(tf) {
				if (!tf) {
					this.lotusAddressData.visible = true;
				}
			},
			imgsChange(name, img) {
				this.formColumns.some((item) => {
					if (item.name == name) {
						item.value = img.join(',');
						return true;
					}
				});
			},
			formSubmit(e) {
				const formData = e.detail.value;
				const checkRes = graceChecker.check(formData, this.formColumns);
				if (checkRes) {
					this.$emit('submit', formData, this.submitName);
				} else {
					uni.showToast({
						title: graceChecker.error,
						icon: "none"
					});
				}
			},
			dateChange(res, name) {
				var dateStr = res[0] + '-' + res[1] + '-' + res[2];
				this.formColumns.some((item) => {
					if (item.name == name) {
						item.value = dateStr;
						return true;
					}
				});
			},
			dropboxChange(index, name) {
				this.formColumns.some((item) => {
					if (item.name == name) {
						item.value = item.dropboxGroup[index].value;
						item.valueLabel = item.dropboxGroup[index].label;
						return true;
					}
				});
			},
		}
	}
</script>
<style lang="less" scoped>
	.form-btn {
		padding: 22rpx 0;

		button {
			margin-bottom: 20rpx;
		}
	}

	.page {
		background: #FFF !important;
	}

	.grace-border-b {
		border-color: #F8F8F8;
	}

	.grace-form-label {
		width: auto;
	}

	.grace-check-item-v {
		margin-left: 20rpx;
	}

	.radio-group {
		display: flex;
		justify-content: flex-end;
	}
</style>
