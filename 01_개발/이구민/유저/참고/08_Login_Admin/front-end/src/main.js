import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
// 테마 공통 적용
/* Todo : 공통 적용 스타일 시트 */
import '@/assets/css/styles.css';

// 터미얼에서 부트스트랩 에러가 날 때 아래 실행 할것
//5.x 부트스프랩에서 아래 popper ( 팝업 관련 ) 가 빠져있어서 수동으로 설치해야함
// npm install --save @popperjs/core
// Todo : bootstrap 추가 4개
import { BootstrapVue } from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

// Todo : 유효성 체크 , 아이콘,
// 뷰 유효성 체크
import VeeValidate from 'vee-validate';
import { library } from '@fortawesome/fontawesome-svg-core';
// 웹 아이콘 설정
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import {
  faHome,
  faUser,
  faUserPlus,
  faSignInAlt,
  faSignOutAlt
} from '@fortawesome/free-solid-svg-icons';

library.add(faHome, faUser, faUserPlus, faSignInAlt, faSignOutAlt);

Vue.use(VeeValidate);
Vue.component('font-awesome-icon', FontAwesomeIcon);

Vue.config.productionTip = false

Vue.use(BootstrapVue)

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
