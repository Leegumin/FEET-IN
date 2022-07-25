<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
import menu from '@/assets/js/common'
export default {
  mounted() {
    menu();
  }
}
</script>

<style scoped>
</style>