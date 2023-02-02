<template>
  <el-breadcrumb>
    <el-breadcrumb-item v-for="(label, index) in pathList" :key="index">
      {{ label }}
    </el-breadcrumb-item>
  </el-breadcrumb>
</template>

<script>
import { RouteMetaKey } from '@/libs/constant.js'
export default {
  computed: {
    pathList () {
      let meta = this.$route.meta
      if (!meta) {
        return []
      }
      let titlePath = meta[RouteMetaKey.TITLE_PATH]
      return titlePath ? titlePath : []
    }
  }
}
</script>
