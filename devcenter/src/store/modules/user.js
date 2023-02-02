const getDefaultState = () => {
  return {
    name: '',
    email: '',
  }
}

const state = getDefaultState()

const mutations = {
  RESET: (state) => {
    Object.assign(state, getDefaultState())
  },
  SET_INFO: (state, info) => {
    state.email = info.email
    state.name = info.name
  }
}

const getters = {
  email: (state) => { return state['email'] },
  name: (state) => { return state['name'] }
}

export default {
  namespaced: true,
  getters,
  state,
  mutations,
}
