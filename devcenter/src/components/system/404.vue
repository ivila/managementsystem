<template>
  <div align="center">
    <h1>Page Not Found</h1>
    <el-button @click="doBackToHome">Lead Me Back To Home</el-button>
  </div>
</template>

<script>
export default {
  methods: {
    doBackToHome () {
      this.$router.push('/')
    }
  }
}
</script>
