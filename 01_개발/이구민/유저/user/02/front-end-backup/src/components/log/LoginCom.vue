<template>
  <section class = "vh-100 gradient-custom login-form">
    <div class = "container py-5 h-100">
      <div class = "row d-flex justify-content-center align-items-center h-100">
        <div class = "col-12 col-md-8 col-lg-6 col-xl-5">
          <div class = "card bg-dark text-white login-card"
               style = "border-radius: 1rem;"
          >
            <form name = "form"
                  @submit.prevent = "handleLogin"
            >
              <div class = "card-body p-5 text-center">
                <div class = "mb-md-5 mt-md-4 pb-5">
                  <h2 class = "fw-bold mb-2 text-uppercase login-title">Login</h2>
                  <p class = "text-white-50 mb-5">Please enter your login and password!</p>

                  <div class = "form-outline form-white mb-4">
                    <input type = "text"
                           id = "typeIdlX"
                           class = "form-control form-control-lg"
                           v-model = "user.username"
                           v-validate = "'required'"
                           name = "username"
                    />
                    <label class = "form-label"
                           for = "typeIdlX"
                    >ID</label>
                    <!--error.has('username') : 유저네임을 입력 안해서 참값이 되면 오류 노출-->
                    <div v-if = "errors.has('username')"
                         class = "alert alert-white text-danger"
                         role = "alert"
                    >
                      ID가 필요합니다!
                    </div>
                  </div>

                  <div class = "form-outline form-white mb-4">
                    <input type = "password"
                           id = "typePasswordX"
                           class = "form-control form-control-lg"
                           v-model = "user.password"
                           v-validate = "'required'"
                           name = "password"
                    />
                    <label class = "form-label"
                           for = "typePasswordX"
                    >Password</label>
                    <!--error.has('username') : 유저네임을 입력 안해서 참값이 되면 오류 노출-->
                    <div v-if = "errors.has('password')"
                         class = "alert alert-white text-danger"
                         role = "alert"
                    >
                      Password가 필요합니다!
                    </div>
                  </div>

                  <p class = "small mb-5 pb-lg-2"><a class = "text-white-50"
                                                     href = "#!"
                  >Forgot password?</a></p>

                  <div class = "form-group">
                    <button class = "btn btn-outline-light btn-lg px-5 login-btn"
                            :disabled = "loading"
                    ><span v-show = "loading"
                           class = "spinner-border spinner-border-sm border-dark"
                    ></span>
                      <span>Login</span>
                    </button>
                  </div>
                  <div class = "form-group">
                    <div v-if = "message"
                         class = "alert alert-danger"
                         role = "alert"
                    >
                      {{ message }}
                    </div>
                  </div>
                  <div class = "d-flex justify-content-center text-center mt-4 pt-1">
                    <a href = "#!"
                       class = "text-white"
                    ><i class = "fab fa-facebook-f fa-lg"></i></a>
                    <a href = "#!"
                       class = "text-white"
                    ><i class = "fab fa-twitter fa-lg mx-4 px-2"></i></a>
                    <a href = "#!"
                       class = "text-white"
                    ><i class = "fab fa-google fa-lg"></i></a>
                  </div>
                </div>
                <div class = "signup-link">
                  <p class = "mb-0">Don't have an account? <a href = "#!"
                                                              class = "text-white-50 fw-bold"
                  >Sign
                   Up</a>
                  </p>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import login from '@/assets/js/login'
import * as mdb from 'mdb-ui-kit' // lib
import { Input } from 'mdb-ui-kit' // module
import User from '@/models/user'
/*eslint-disable*/

export default {
  name: 'LoginCom',
  data () {
    return {
      user   : new User('', ''),
      loading: false,
      message: '',
    }
  },
  computed: {
    // loggedIn = false or true
    loggedIn () {
      return this.$store.state.auth.status.loggedIn
    },
  },
  methods : {
    // 로그인 메소드 정의
    handleLogin () {
      this.loading = true
      // Vee-Validation npm : 뷰에서 유효성을 체크하는 모듈
      // validateAll : v-validate 체크해서 모두 true, false인지 확인
      // 값이 isValid = true(false)
      this.$validator.validateAll().then(isValid => {
        // 유효성 체크가 걸린 입력박스가 하나라도 에러가 나면 false
        // isValid = false => 함수 종료
        if (!isValid) {
          this.loading = false
          return
        }

        // 로그인 절차 진행
        // springBoot 서버와 통신
        if (this.user.username && this.user.password) {
          // springBoot로 username, password 전송해서 로그인 진행
          // 공유저장소의 비동기 메소드 호출 (login)
          this.$store.dispatch('auth/login', this.user)
              // 결과가 성공하면 then(첫번째매개변수)
              // 실패하면 then(두번째매개변수)
              .then(
                  // 성공
                  () => {
                    // 로그인 성공시 자동 이동
                    this.$router.push('/profile')
                  },
                  // 실패
                  error => {
                    this.loading = false
                    this.message = (error.message || error.toString())
                  })
        }
      })

    },
  },
  mounted () {
    login()
    mdb()
    Input()
    // 로그인이 됐으면 loggedIn=true
    // 로그인이 안됐으면 loggedIn=false
    if (this.loggedIn) {
      // profile 페이지로 강제 이동
      this.$router.push('/profile')
    }
  },
}
</script>

<style scoped>
@import "@/assets/css/login2.css";
/*@import '~mdb-ui-kit/css/mdb.min.css';*/
</style>