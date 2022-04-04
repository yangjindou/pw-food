import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import "./core/antdv.use";
import "./core/axios.config";

import moment from 'moment';
moment.locale('zh-cn');
Vue.prototype.$moment = moment;

Vue.config.productionTip = false;
new Vue({
  router,
  store,
  render: h => h(App),
}).$mount("#app");