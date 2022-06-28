import Vue from 'vue'
import Vuex from 'vuex'

import { auth } from '@/store/AuthModule';

Vue.use(Vuex)

// src/store 폴더 에 인증용 Vuex 모듈을 넣음
export default new Vuex.Store({
  modules: {
    auth
  }
})
