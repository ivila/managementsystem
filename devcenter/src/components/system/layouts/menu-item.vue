<template>
  <el-menu-item
    v-if="!hasChildren"
    :index="fullPath"
    :route="fullPath">
    {{ title }}
  </el-menu-item>
  <el-sub-menu v-else :index="fullPath">
    <template #title>{{ title }}</template>
    <menu-item
      v-for="children in value.children"
      :key="children.path"
      :value="children">
    </menu-item>
  </el-sub-menu>
</template>

<script>
import { RouteMetaKey } from '@/libs/constant.js'
export default {
  name: 'menu-item',
  props: {
    value: {
      type: Object,
      required: true
    }
  },
  computed: {
    meta () {
      return this.value.meta
    },
    fullPath () {
      return this.meta[RouteMetaKey.FULL_PATH]
    },
    hasChildren () {
      return this.value.children && this.value.children.length > 0
    },
    title () {
      return this.meta.title
    }
  }
}
</script>
