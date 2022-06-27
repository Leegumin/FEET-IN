<template>
  <div id="page-top">
    <!-- Navigation-->
    <a class="menu-toggle rounded mt-5" href="#"><i class="fas fa-bars"></i></a>
    <nav id="sidebar-wrapper">
      <ul class="sidebar-nav">
        <li class="sidebar-brand"><a href="#page-top">Start Bootstrap</a></li>
        <li class="sidebar-nav-item"><a href="#page-top">Home</a></li>
        <li class="sidebar-nav-item"><a href="#about">About</a></li>
        <li class="sidebar-nav-item"><a href="#services">Services</a></li>
        <li class="sidebar-nav-item"><a href="#portfolio">Portfolio</a></li>
        <li class="sidebar-nav-item"><a href="#contact">Contact</a></li>
      </ul>
    </nav>

    <HeaderCom></HeaderCom>
    <SectionCom></SectionCom>
    <FooterCom></FooterCom>

  </div>
</template>

<script>
// Todo : Core theme JS
import loadWin from '@/assets/js/scripts.js'
// @ is an alias to /src
import SectionCom from "@/components/home/SectionCom.vue"
import HeaderCom from "@/components/home/HeaderCom.vue"
import FooterCom from "@/components/home/FooterCom.vue"

export default {
  name: 'HomeView',
  components: {
    SectionCom,
    HeaderCom,
    FooterCom
  },
  mounted() {
    loadWin();
  }
}
</script>

<style>
</style>
