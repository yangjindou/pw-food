import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

import user from "./user";

const store = new Vuex.Store({
  modules: {
    user,
  },
});

export default store;