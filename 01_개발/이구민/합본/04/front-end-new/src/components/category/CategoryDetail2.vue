<template>
  <div>
    <swiper
        class = "swiper"
        :options = "swiperOption"
    >
      <swiper-slide ref="mySwiper" v-for="(item,index) in test" :key="index">
        <div style = "width: 200px; height: 200px; background: gray">{{ item }}</div>
      </swiper-slide>
      <div
          class = "swiper-pagination"
          slot = "pagination"
      >
      </div>
      <div class = "swiper-button-prev"
           slot = "button-prev"
      ></div>
      <div class = "swiper-button-next"
           slot = "button-next"
      ></div>
    </swiper>
  </div>
</template>
<script>
import Vue from 'vue'
import { Swiper, SwiperSlide } from 'vue-awesome-swiper'

//import "swiper/swiper-bundle.css";

//import store from "@/src/store";
export default Vue.extend({
  components: {
    Swiper,
    SwiperSlide,
  },
  data () {
    return {
      test        : [],
      swiperOption: {
        //기본 셋팅
        //방향 셋팅 vertical 수직, horizontal 수평 설정이 없으면 수평
        direction: 'horizontal',
        //한번에 보여지는 페이지 숫자
        slidesPerView: 7,
        //페이지와 페이지 사이의 간격
        spaceBetween: 0,
        //드레그 기능 true 사용가능 false 사용불가
        debugger: true,
        //마우스 휠기능 true 사용가능 false 사용불가
        mousewheel: false,
        //반복 기능 true 사용가능 false 사용불가
        loop: true,
        loopedSlides: 2,
        //선택된 슬라이드를 중심으로 true 사용가능 false 사용불가 djqt
        centeredSlides: true,
        // 페이지 전환효과 slidesPerView효과와 같이 사용 불가
        // effect: 'fade',

        //자동 스크를링
        autoplay: {
          //시간 1000 이 1초
          delay               : 2500,
          disableOnInteraction: false,
        },
      },
    }
  },
  created () {
    setTimeout(() => {
      for (let i = 0; i < 9; i++) {
        this.test.push({ a: 'a' + i })
      }
    }, 100)
    setTimeout(() => {
        this.swiper.slideTo(2, 100, false)
    }, 300)
  },
  computed: {
    swiper () {
      return this.$refs.mySwiper.$swiper
    },
  },
  methods : {},
})
</script>
<style>
@import "@/assets/css/swiper.css";
</style>