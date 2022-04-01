import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

import user from "./user";
import menu from "./menu";

const store = new Vuex.Store({
  modules: {
    user,menu
  },
});

export default store;