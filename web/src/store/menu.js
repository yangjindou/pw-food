export default {
  namespaced: true,
  mutations: {
    set(state, value) {
      state.selectedKeys = [value];
    },
  },
  state: {
    selectedKeys: [],
  },
}