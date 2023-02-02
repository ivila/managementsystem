import { reactive } from 'vue'

const globalVar = reactive({
  WindowHeight: window.innerHeight
})

window.addEventListener('resize', () => {
  globalVar.WindowHeight = window.innerHeight
})

export default {
  install: function (app) {
    app.config.globalProperties.$globalVar = globalVar
  }
}
