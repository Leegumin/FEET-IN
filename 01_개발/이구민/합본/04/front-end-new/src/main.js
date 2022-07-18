import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import BootstrapVue from 'bootstrap-vue'


import VueAwesomeSwiper from 'vue-awesome-swiper'
Vue.use(VueAwesomeSwiper)
//bootstrap
// popper : bootstrap 팝업창 띄우는 소스
//fontawesome-svg-icons 등 : 화면에 아이콘 설치
//vee-validate : 뷰에서 유효성을 체크하는 모듈
/*
import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'
*/

// 부트스트랩 뷰
Vue.config.productionTip = false

Vue.use(BootstrapVue)


// 뷰의 유효성 체크
import VeeValidate from 'vee-validate'
import ko from 'vee-validate/dist/locale/ko'
const config = {
  locale: 'ko',
  dictionary: {
    ko
  }
}
// 뷰 유효성 체크 모듈 사용 설정
Vue.use(VeeValidate, config)

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')



