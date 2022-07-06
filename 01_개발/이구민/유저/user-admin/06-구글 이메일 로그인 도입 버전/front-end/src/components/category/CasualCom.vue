<template>
  <div class="swiper-container">
    <div class="swiper-wrapper">
      <div class="swiper-slide"><img src="http://ktsmemo.cdn3.cafe24.com/p/0343.jpg"></div>
      <div class="swiper-slide"><img src="http://ktsmemo.cdn3.cafe24.com/p/0552.jpg"></div>
      <div class="swiper-slide"><img src="http://ktsmemo.cdn3.cafe24.com/p/0251.jpg"></div>
      <div class="swiper-slide"><img src="https://ktsmemo.cafe24.com/p/0246.jpg"></div>
      <div class="swiper-slide"><img src="http://oldmidi.cdn3.cafe24.com/p/0522.jpg"></div>
      <div class="swiper-slide"><img src="http://oldmidi.cdn3.cafe24.com/p/0582.jpg"></div>
      <div class="swiper-slide"><img src="https://ktsmemo.cafe24.com/p/0365.jpg"></div>
      <div class="swiper-slide"><img src="http://oldmidi.cdn3.cafe24.com/p/0342.jpg"></div>
      <div class="swiper-slide"><img src="http://oldmidi.cdn3.cafe24.com/p/0574.jpg"></div>
      <div class="swiper-slide"><img src="https://ktsmemo.cafe24.com/p/0431.jpg"></div>
      <div class="swiper-slide"><img src="https://ktsmemo.cafe24.com/p/0476.jpg"></div>
      <div class="swiper-slide"><img src="http://ktsmemo.cdn3.cafe24.com/p/0332.jpg"></div>
      <div class="swiper-slide"><img src="https://ktsmemo.cafe24.com/p/0579.jpg"></div>
      <div class="swiper-slide"><img src="https://ktsmemo.cafe24.com/p/0148.jpg"></div>
      <div class="swiper-slide"><img src="http://ktsmemo.cdn3.cafe24.com/p/0371.jpg"></div>
    </div>

    <!-- 페이징 -->
    <div class="swiper-pagination"></div>
  </div>
</template>
<script>
import casual from "@/assets/js/casual";
export default {
  name: "CasualCom",
  mounted() {
    casual();
  }
};
</script>