import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import { store } from './store/index.js'
import { router } from './router/index.js'
import App from './App.vue'
import { handleError } from './errhandle'
import GlobalVar from './plugins/variable.js'

const app = createApp(App)
app.use(store)
app.use(router)
app.use(ElementPlus)
app.use(GlobalVar)
app.mount('#app')
// 注册最顶层的错误捕捉
app.config.errorHandler = handleError
