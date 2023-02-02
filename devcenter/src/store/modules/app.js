const getDefaultState = () => {
  return {
    sidebarCollapsed: false,
  }
}

const state = getDefaultState()

const mutations = {
  TOGGLE_SIDEBAR: (state) => {
    state.sidebarCollapsed = !state.sidebarCollapsed
  }
}

const getters = {
  sidebarCollapsed: (state) => { return state.sidebarCollapsed }
}

export default {
  namespaced: true,
  state,
  getters,
  mutations,
}
