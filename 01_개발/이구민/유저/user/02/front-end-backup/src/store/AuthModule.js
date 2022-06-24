import AuthService from '@/services/auth/AuthService'

// 로컬저장소(크롬의 localstorage)에서 user의 정보를 받아오기
const user = JSON.parse(localStorage.getItem('user'))

// user 상태정보 설정 : initialState
// 1) 로그인(loggedIn)이 되어 있을때 상태정보
// 2) 로그인(loggedIn)이 안돼 있을때 상태정보
// 3항 연산자 : 조건이 참일때 ? 참일경우 실행 : 거짓일경우 실행
const initialState = user ? {
  status: { loggedIn: true },
  user,
} : {
  status: { loggedIn: false },
  user  : null,
}

export const auth = {
  // namespaced
  namespaced: true, // 공유 데이터 정의(전역 변수)
  state     : initialState, // setter(변형가능) 정의
  mutations : {
    // 로그인 성공 메소드
    loginSuccess (state, user) {
      state.status.loggedIn = true
      state.user = user
    }, // 로그인 실패 메소드
    loginFailure (state) {
      state.status.loggedIn = false
      state.user = null
    }, // 로그아웃 메소드
    logout (state) {
      state.status.loggedIn = false
      state.user = null
    }, // 회원가입 성공 메소드 ( 상태정보가 ture일 경우 자동 로그인, false일 경우 수동 로그인)
    registerSuccess (state) {
      state.status.loggedIn = false
      state.user = null
    }, // 회원가입 실패 메소드
    registerFailure (state) {
      state.status.loggedIn = false
      state.user = null
    },
  }, // 비동기 함수들 정의
  // axios 통신 : 서버에서 결과를 언제 보내줄지 모름
  actions: {
    login ({ commit }, user) {
      // axios 통신 : login ( AuthService.login )
      // springBoot로 login 관련 요청 (username, password)
      return AuthService.login(user)
        // 성공하면 then으로 결과가 들어옴
        .then(user => {
            // commit(mutations 메소드명)
            // loginSuccess(state, user) 호출 => loggedIn 속성 : true, user 속성 : user
            commit('loginSuccess', user)
            return Promise.resolve(user)
          },
          // 실패하면 error으로 결과가 들어옴
          error => {
            // commit(mutations 메소드명)
            // loginFailure(state) 호출 => loggedIn 속성 : false, user 속성 : null
            commit('loginFailure')
            return Promise.reject(error)
          })
    },
    // logout : AuthService.logout 호출
    logout ({ commit }) {
      // 로컬저장소에서 user 삭제(제거)
      AuthService.logout()
      // mutations : logout(state) 메소드 호출 => 상태정보 갱신 : loggedIn = false
      commit('logout')
    },
    // register : AuthService.register 호출
    register ({ commit }, user) {
      // axios 서버쪽으로 user 객체를 전송해서 insert 요청
      return AuthService.register(user)
        // 성공하면 then으로 결과가 들어옴
        .then(response => {
            // commit(mutations 메소드명)
            // registerSuccess(state) 호출 => loggedIn 속성 : false, user 속성 : null
            commit('registerSuccess')
            // 비동기함수 성공 시 : promise.resolve 호출됨
            return Promise.resolve(response.data)
          },
          // 실패하면 error으로 결과가 들어옴
          error => {
            // commit(mutations 메소드명)
            // registerFailure(state) 호출 => loggedIn 속성 : false, user 속성 : null
            commit('registerFailure')
            // 비동기함수 성공 시 : promise.reject 호출됨
            return Promise.reject(error)
          })
    },
  },
}
