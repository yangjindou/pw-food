const option1 = {
  namespaced: true,
  actions: {
    add(context,value) {
      console.log('add',context,value);
      context.commit('ADD',value)
    }
  },
  mutations: {
    ADD(state,value) {
      console.log('ADD',state,value);
      state.sum += value;
    }
  },
  state: {
    sum: 0
  },
  getters: {
    bigSum(state) {
      return state.sum * 10;
    }
  }
}

export default option1;