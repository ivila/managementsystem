import { createRouter, createWebHistory } from 'vue-router'
import { GetInfo } from '@/apis/user.js'
import { store } from '@/store/index.js'
import { getRouteObj } from './route.js'


export const router = createRouter({
  routes: getRouteObj(),
  history: createWebHistory(),
})

// 系统重载的时候，需要检查一下permission
let hasCheckTokenValid = false
const checkHasPermission = async () => {
  // 检查permission
  if (!hasCheckTokenValid) {
    try {
      let info = await GetInfo()
      store.commit('user/SET_INFO', {
        email: info['email'],
        name: info['userName']
      })
      hasCheckTokenValid = true
      return true
    } catch {
      return false
    }
  }
  return true
}

export const reCheckPermission = () => {
  hasCheckTokenValid = false
}

const LoginPath = '/login'
// 对于未登录的，全部检查
router.beforeEach(async (to, from, next) => {
  let hasPermission = await checkHasPermission()
  if ((!hasPermission) && (to.path !== LoginPath)) {
    next({ path: LoginPath })
    return
  }
  if (hasPermission && to.path === LoginPath) {
    next({ path: '/' })
    return
  }
  next()
})
