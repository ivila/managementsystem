<template>
  <el-container class="common-layout">
    <el-aside width="200px" class="no-padding">
      <menu-view/>
    </el-aside>

    <el-container style="height: 100%;">
      <el-header class="no-padding" height="42px">
        <el-row class="my-header">
          <el-col :span="12"><route-navi/></el-col>
          <el-col :span="12"><user-control/></el-col>
        </el-row>
      </el-header>
      <el-header class="no-padding" height="38px">
        <tags-view/>
      </el-header>
      <el-main style="padding: 10px;">
        <router-view v-slot="{ Component }">
          <transition name="fade" mode="out-in">
            <keep-alive :include="cacheComponents">
              <component :is="Component"></component>
            </keep-alive>
          </transition>
        </router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import { RouteMetaKey } from '@/libs/constant.js'
import UserControl from './layouts/user-control.vue'
import RouteNavi from './layouts/route-navi.vue'
import Menu from './layouts/menu.vue'
import TagsView from './layouts/tags-view.vue'

export default {
  components: {
    'user-control': UserControl,
    'route-navi': RouteNavi,
    'menu-view': Menu,
    'tags-view': TagsView
  },
  computed: {
    cacheComponents () {
      const views = this.$store.getters['tagsview/visitedViews']
      let componentNames = views.map(v => v.meta[RouteMetaKey.COMPONENT_NAME])
      return componentNames
    }
  }
}
</script>

<style scoped>
.common-layout {
  height: 100vh;
}
.no-padding {
  padding: 0px;
}
.my-header {
  height: 100%;
  padding: 0px 20px 0px;
  box-shadow: 0 1px 4px rgb(0 21 41 / 10%);
  display: flex;
  align-items: center;
}
</style>
