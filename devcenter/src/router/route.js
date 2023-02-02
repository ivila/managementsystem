import { RouteMetaKey } from '@/libs/constant'
import Login from '@/components/system/login.vue'
import Layout from '@/components/system/layout.vue'
import Page404 from '@/components/system/404.vue'
import Welcome from '@/components/system/welcome.vue'
import BookRoute from './modules/book.js'


export const sideRoutes = [
  BookRoute,
]

const dashboardRoute = {
  path: 'dashboard',
  component: Welcome,
  meta: { title: 'Dashboard', cache: false }
}

const routes = [
  {
    path: '/login',
    component: Login,
  },
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [
      dashboardRoute
    ],
  },
  BookRoute,
  {
    path: '/:catchAll(.*)',
    component: Page404,
  }
]

function makePath (p, b) {
  if (!b.startsWith('/')) {
    b = '/'+b
  }
  return p ? `${p}${b}` : b
}

function getRouteMeta (conf, parentMeta) {
  const srcMeta = conf.meta || {}
  let title = srcMeta.title
  let parentPath = parentMeta ? parentMeta[RouteMetaKey.FULL_PATH] : ''
  let fullPath = makePath(parentPath, conf.path)
  let titlePath = title ? [title] : []
  if (parentMeta && parentMeta[RouteMetaKey.TITLE_PATH] && parentMeta[RouteMetaKey.TITLE_PATH].length > 0) {
    titlePath = parentMeta[RouteMetaKey.TITLE_PATH].concat(titlePath)
  }

  let meta = {
    [RouteMetaKey.COMPONENT_NAME]: conf.component.name,
    [RouteMetaKey.TITLE]: title,
    [RouteMetaKey.TITLE_PATH]: titlePath,
    [RouteMetaKey.FULL_PATH]: fullPath,
    [RouteMetaKey.CACHE]: srcMeta.cache
  }
  return meta
}

// TODO permissions check
function makeRouteObj (conf, parentMeta) {
  let data = {
    path: conf.path,
    component: conf.component,
    children: [],
    meta: getRouteMeta(conf, parentMeta)
  }
  if (conf.children && conf.children.length > 0) {
    data.children = conf.children.map(v => makeRouteObj(v, data.meta))
  }
  return data
}

export function getRouteObj () {
  return routes.map(v => makeRouteObj(v, null))
}

export function getSideRouteObj () {
  return [dashboardRoute].concat(sideRoutes).map(v => makeRouteObj(v, null))
}
