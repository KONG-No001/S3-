<template>
  <div>
    <!--     路由   -->
      <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: "index",
  created() {
    this.$router.push({path:"/spfl"});
  }
}
</script>

<style scoped>
</style>