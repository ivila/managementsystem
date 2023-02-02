import { createStore } from "vuex"
import VuexPersistence from 'vuex-persist'
import user from './modules/user.js'
import app from './modules/app.js'
import tagsview from './modules/tagsview.js'

const vuexLocal = new VuexPersistence({
  key: process.env.VUE_APP_VUEX_KEY,
  storage: window.localStorage,
  reducer: (state) => {
    return {'user': state.user}
  },
  filter: (mutation) => mutation.type.startsWith('user')
})

export const store = createStore({
  modules: {
    app,
    user,
    tagsview,
  },
  plugins: [
    vuexLocal.plugin
  ]
})
