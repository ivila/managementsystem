const getDefaultState = () => {
  return {
    visitedViews: []
  }
}

const state = getDefaultState()

const mutations = {
  ADD_VISITED: (state, view) => {
    if (state.visitedViews.some(v => v.path === view.path)) {
      return
    }
    state.visitedViews.push(Object.assign({}, view, { title: view.meta.title }))
  },
  DEL_VISITED: (state, view) => {
    for (const [i, v] of state.visitedViews.entries()) {
      if (v.path !== view.path) {
        continue
      }
      state.visitedViews.splice(i, 1)
      break
    }
  },
  DEL_ALL_OTHER_VISITED: (state, view) => {
    state.visitedViews = state.visitedViews.filter(v => v.path === view.path)
  },
  DEL_ALL_VISITED: (state) => {
    state.visitedViews = []
  }
}

const getters = {
  visitedViews: (state) => { return state.visitedViews }
}

export default {
  namespaced: true,
  getters,
  state,
  mutations,
}
