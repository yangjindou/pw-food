export default {
  namespaced: true,
  mutations: {
    setUser(state, value) {
      state.userData = value;
      localStorage.setItem("user",JSON.stringify(value));
    },
    removeUser(state) {
      state.userData = {};
      localStorage.removeItem("user");
    }
  },
  state: {
    userData: JSON.parse(localStorage.getItem("user")) || {}
  },
  getters: {
    getUser(state) {
      return state.userData;
    },
  }
}