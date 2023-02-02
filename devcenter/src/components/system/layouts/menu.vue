<template>
  <div class="devcenter-menu">
  <!--
    <div style="margin: 20px 10px 5px;">
      <el-input v-model="searchText" placeholder="type to search" clearable></el-input>
    </div>
    -->
    <div align="left">
      <el-scrollbar max-height="maxHeight">
        <el-menu
          :default-active="activeMenu"
          :collapse="isCollapse"
          :unique-opened="false"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b"
          style="border-right: None;"
          :router="true">
          <menu-item
            v-for="(route, index) in validRoutes"
            :key="index"
            :value="route"
            :highlight-text="searchText">
          </menu-item>
        </el-menu>
      </el-scrollbar>
    </div>
  </div>
</template>

<script>
import { getSideRouteObj } from '@/router/route.js'
import MenuItem from './menu-item.vue'

export default {
  components: {
    'menu-item': MenuItem
  },
  data () {
    return {
      searchText: ''
    }
  },
  computed: {
    activeMenu () {
      return this.$route.fullPath
    },
    validRoutes () {
      let temp = getSideRouteObj()
      return temp
    },
    isCollapse () {
      return this.$store.getters['app/sidebarCollapsed']
    },
    maxHeight() {
      const OtherHeight = 100
      let shouldHeight = this.$globalVar.WindowHeight - OtherHeight
      let res = `${shouldHeight}px`
      return res
    }
  }
}
</script>

<style scoped>
.devcenter-menu {
  background: #545c64;
  height: 100%;
  overflow: hidden;
}
</style>
