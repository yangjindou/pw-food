import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

import option1 from "@/store/option1";

const store = new Vuex.Store({
  modules: {
    option1,
  },
});

export default store;