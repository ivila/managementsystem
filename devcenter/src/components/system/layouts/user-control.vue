<template>
  <div align="right">
    <span style="margin-right: 10px;">{{ userName }}</span>
    <el-button size="small" :icon="FullScreen" circle @click="doToggleFullscreen"></el-button>
    <el-popconfirm title="Are you sure to logout?" @confirm="doLogout">
      <template #reference>
        <el-button :icon="CircleClose" type="danger" circle/>
      </template>
    </el-popconfirm>
  </div>
</template>

<script>
import screenfull from 'screenfull'
import { FullScreen, CircleClose } from '@element-plus/icons-vue'
import { Logout } from '@/apis/auth.js'

export default {
  data() {
    return {
      FullScreen,
      CircleClose,
      isFullscreen: false
    }
  },
  computed: {
    userName () {
      return this.$store.getters['user/name']
    }
  },
  methods: {
    doToggleFullscreen () {
      if (!screenfull.isEnabled) {
        this.$message.error('Your brower does not support fullscreen')
        return
      }
      screenfull.toggle()
      this.isFullscreen = !this.isFullscreen
    },
    async doLogout () {
      await Logout()
      window.location.reload()
    }
  }
}
</script>

<style scoped>
</style>
