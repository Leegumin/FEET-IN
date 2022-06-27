<template>
  <div id="page-top">
    <!-- * Navigation-->
    <NavCom></NavCom>
    <!-- * router-view -->
    <router-view></router-view>
    <!-- * Footer -->
    <FooterCom></FooterCom>
  </div>
</template>

<script>
import NavCom from "@/components/home/NavCom";
import FooterCom from "@/components/home/FooterCom";
import menu from '@/assets/js/common'
import home from "@/assets/js/home";

export default {
  name: "HomeView",
  components: {
    NavCom,
    FooterCom,
  },
  // 화면이 뜨자마자 실행되는 이벤트
  mounted() {
    home();
    menu();
  }
}
</script>

<style scoped>
  @import "@/assets/css/custom.css";
</style>