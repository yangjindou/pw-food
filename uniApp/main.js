import Vue from 'vue'
import App from './App'

// 引入http.js
import {
	http
} from "./utils/http.js";
Vue.prototype.$http = http;

var common = require('@/common/common.js');
Vue.prototype.$common = common;

import lotusAddress from "@/components/Winglau14-lotusAddress/Winglau14-lotusAddress.vue";
Vue.component('lotus-address',lotusAddress);

import paging from "@/components/yang-paging/yang-paging.vue";
Vue.component('paging', paging);


Vue.config.productionTip = false
App.mpType = 'app'

const app = new Vue({
    ...App
})
app.$mount()
