<template name="ugDropbox">
	<picker class="grace-form-picker" @change="dropboxChange" :disabled="disabled" :range="dropboxGroup" range-key="label">
		<view class="grace-flex grace-nowrap grace-flex-end grace-flex-vcenter">
			<slot></slot>
		</view>
	</picker>
</template>

<script>
	export default {
		name: "ugDropbox",
		props: {
			name: {
				type: String,
				default: ''
			},
			dropboxGroup: {
				type: Array,
				default: []
			},
			disabled : {type : Boolean, default : false},
		},
		methods: {
			dropboxChange(e) {
				this.$emit('dropboxChange', e.detail.value, this.name);
			},
		}
	}
</script>

<style scoped>
	.grace-header-leader {
		background-color: rgb(0, 0, 0, 0);
	}
</style>
