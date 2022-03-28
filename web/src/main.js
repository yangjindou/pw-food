import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import "./core/antdv.use";
import "./core/axios.config";

import ECharts from 'vue-echarts';
Vue.component('v-chart', ECharts);

import $ from 'jquery';
Vue.prototype.$ = $;


Vue.config.productionTip = false;
new Vue({
  router,
  store,
  render: h => h(App),
}).$mount("#app");