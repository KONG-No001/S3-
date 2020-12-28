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
    this.$router.push({path:"/shouye"});
  }
}
</script>

<style scoped>
</style>