<template>
  <el-scrollbar>
    <div class="tags-view-container">
      <router-link
        v-for="tag in visitedViews"
        :key="tag.path"
        :to="tag.path"
        tag="span">
        <span class="link-item" :class="isActive(tag) ? 'is-active': ''">{{ tag.meta.title }}</span>
      </router-link>
    </div>
  </el-scrollbar>
</template>

<script>
import { RouteMetaKey } from '@/libs/constant.js'

export default {
  mounted () {
    this.addTags()
  },
  computed: {
    visitedViews () {
      return this.$store.getters['tagsview/visitedViews']
    }
  },
  watch: {
    $route () {
      this.addTags()
    }
  },
  methods: {
    addTags () {
      if (this.$route.meta &&
        this.$route.meta[RouteMetaKey.TITLE] &&
        this.$route.meta[RouteMetaKey.CACHE] !== false) {
        this.$store.commit('tagsview/ADD_VISITED', this.$route)
      }
    },
    isActive (tag) {
      return tag.path === this.$route.path
    },
  }
}
</script>

<style scoped>
.link-item {
  display: inline-block;
  height: 26px;
  line-height: 26px;
  border: 1px solid var(--el-color-info);
  color: var(--el-text-color-regular);
  background: white;
  padding: 0 8px;
  font-size: 12px;
  margin-left: 5px;
  margin-top: 4px;
}
.is-active {
  background-color: #42b983;
  border-color: #42b983;
}
.tags-view-container {
  height: 36px;
  border-bottom: 1px solid #d8dce5;
  display: flex;
}
</style>
