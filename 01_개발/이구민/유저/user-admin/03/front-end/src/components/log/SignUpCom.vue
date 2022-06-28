<template>
  <div class = "signup-container">
    <section class = "vh-100 gradient-custom signup-form">
      <div class = "container py-5 h-100">
        <div class = "row d-flex justify-content-center align-items-center h-100">
          <div class = "col-12 col-md-8 col-lg-6 col-xl-5">
            <div class = "card bg-dark text-white signup-card"
                 style = "border-radius: 1rem;"
            >
              <form name = "form"
                    @submit.prevent = "handleRegister"
              >
                <div class = "card-body p-5 text-center justify-content-center">
                  <div class = "mb-md-5 mt-md-4 pb-5">
                    <h2 class = "fw-bold mb-2 text-uppercase signup-title">Sign Up</h2>
                    <p class = "text-white-50 mb-5">Create Your Account!</p>

                    <!-- * ID -->
                    <div class = "form-outline form-white mb-4 form-floating">
                      <input type = "text"
                             id = "floatingInputId"
                             class = "form-control form-control-lg"
                             v-model = "user.username"
                             v-validate = "'required|min:3|max:20'"
                             data-vv-as = "ID"
                             name = "username"
                             placeholder = "ID"
                      />
                      <label v-if = "!(errors.has('username') && submitted)"
                             class = "form-label"
                             for = "floatingInputId"
                      >ID</label>

                      <!--submit 버튼을 누르고 && 유효성에러가 발생했을 경우 아래 내용이 출력-->
                      <label v-else
                             class="alert-white text-danger"
                             for="floatingInput"
                             role="alert"
                      >ID가 필요합니다!</label>

                      <!-- *밑으로 출력되는 버전-->
<!--                      <div
                          v-if = "errors.has('username') && submitted"
                          class = "alert-white text-danger"
                      >
                        {{ errors.first('username') }}
                      </div>-->
                    </div>

                    <!-- * password -->
                    <div class = "form-outline form-white mb-4 form-floating">
                      <input type = "password"
                             id = "floatingInputPassword"
                             class = "form-control form-control-lg"
                             placeholder = "Password"
                             v-model = "user.password"
                             v-validate = "'required|min:6|max:12'"
                             data-vv-as = "Password"
                             name = "password"
                             ref = "password"
                      />
                      <label v-if = "!(errors.has('password') && submitted)"
                             class = "form-label"
                             for = "floatingInputPassword"
                      >Password</label>
                      <!--submit 버튼을 누르고 && 유효성에러가 발생했을 경우 아래 내용이 출력-->
                      <label v-else
                             class="alert-white text-danger"
                             for="floatingInputPassword"
                             role="alert"
                      >Password가 필요합니다!</label>
                    </div>

                    <!-- * Repeat Password -->
                    <div class = "form-outline form-white mb-4 form-floating">
                      <input type = "password"
                             id = "floatingInputRepeatPassword"
                             class = "form-control form-control-lg"
                             placeholder = "Repeat Password"
                             v-model = "confirmPassword"
                             v-validate = "'required|confirmed:password'"
                             data-vv-as = "Repeat Password"
                             name = "confirmPassword"
                      />
                      <label v-if = "!(errors.has('confirmPassword') && submitted)"
                             class = "form-label"
                             for = "floatingInputRepeatPassword"
                      >Repeat Password</label>
                      <!--submit 버튼을 누르고 && 유효성에러가 발생했을 경우 아래 내용이 출력-->
                      <label
                          v-else
                          class="alert-white text-danger"
                          for="floatingInputRepeatPassword"
                          role="alert"
                      >
                        Password가 일치하지 않습니다!
                      </label>
                    </div>

                    <!-- * name -->
                    <div class = "form-outline form-white mb-4 form-floating">
                      <input type = "text"
                             id = "floatingInputName"
                             class = "form-control form-control-lg"
                             v-model = "user.name"
                             v-validate = "'required|max:20'"
                             data-vv-as = "Name"
                             name = "name"
                             placeholder = "Name"
                      />
                      <label v-if = "!(errors.has('name') && submitted)"
                             class = "form-label"
                             for = "floatingInputName"
                      >Name</label>
                      <!--submit 버튼을 누르고 && 유효성에러가 발생했을 경우 아래 내용이 출력-->
                      <label
                          v-else
                          class="alert-white text-danger"
                          for="floatingInputName"
                          role="alert"
                      >
                        Name이 필요합니다!
                      </label>
                    </div>

                    <!-- * Address -->
                    <div class = "form-outline form-white mb-4 form-floating">
                      <input type = "text"
                             id = "floatingInputAddress"
                             class = "form-control form-control-lg"
                             v-model = "user.address"
                             v-validate = "'required|max:40'"
                             data-vv-as = "Address"
                             name = "address"
                             placeholder = "Address"
                      />
                      <label v-if = "!(errors.has('address') && submitted)"
                             class = "form-label"
                             for = "floatingInputAddress"
                      >Address</label>
                      <!--submit 버튼을 누르고 && 유효성에러가 발생했을 경우 아래 내용이 출력-->
                      <label
                          v-else
                          class="alert-white text-danger"
                          for="floatingInputAddress"
                          role="alert"
                      >
                        Address가 필요합니다!
                      </label>
                    </div>

                    <!-- * Phone -->
                    <div class = "form-outline form-white mb-4 form-floating">
                      <input type = "number"
                             id = "floatingInputPhone"
                             class = "form-control form-control-lg"
                             v-model = "user.phone"
                             v-validate = "'required|min:9|max:11'"
                             data-vv-as = "Phone"
                             name = "phone"
                             placeholder = "Phone"
                      />
                      <label v-if = "!(errors.has('phone') && submitted)"
                             class = "form-label"
                             for = "floatingInputPhone"
                      >Phone</label>
                      <!--submit 버튼을 누르고 && 유효성에러가 발생했을 경우 아래 내용이 출력-->
                      <label
                          v-else
                          class="alert-white text-danger"
                          for="floatingInputPhone"
                          role="alert"
                      >
                        Phone이 필요합니다!
                      </label>
                    </div>

                    <!-- * Email -->
                    <div class = "form-outline form-white mb-4 form-floating">
                      <input type = "email"
                             id = "floatingInputEmail"
                             class = "form-control form-control-lg"
                             v-model = "user.email"
                             v-validate = "'required|max:40'"
                             data-vv-as = "Email"
                             name = "email"
                             placeholder = "Email"
                      />
                      <label v-if = "!(errors.has('email') && submitted)"
                             class = "form-label"
                             for = "floatingInputEmail"
                      >Email</label>
                      <!--submit 버튼을 누르고 && 유효성에러가 발생했을 경우 아래 내용이 출력-->
                      <label
                          v-else
                          class="alert-white text-danger"
                          for="floatingInputEmail"
                          role="alert"
                      >
                        Email이 필요합니다!
                      </label>
                    </div>

                    <!--          todo: 추가 시작 권한부여 창 -->
<!--                    <div class="form-group form-outline form-white mb-4">
                      <select
                          v-model="user.role"
                          class="form-select form-control form-control-lg" aria-label="Default select example"
                      >
                        <option value="ROLE_USER">User</option>
                        <option value="ROLE_ADMIN">Admin</option>
                      </select>
                    </div>-->
                    <!--          todo: 추가 끝 -->

                    <!-- *회원가입 버튼-->
                    <button class = "btn btn-outline-light btn-lg px-5 signup-btn"
                    >Sign Up
                    </button>

                    <!-- *화면에 에러 메시지 출력-->
                    <div v-if = "message"
                         class = "alert"
                         :class = "successful ? 'alert-success' : 'alert-danger'"
                    >
                      {{ message }}
                    </div>
                  </div>
                  <div class = "signin-link">
                    <p class = "mb-0">Back to <a href = "/login"
                                                 class = "text-white-50 fw-bold"
                    >Sign
                     In</a>
                    </p>
                  </div>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import signup from '@/assets/js/signup'
import User from '@/models/user'

export default {
  name: 'SignUpCom',
  data () {
    return {
      confirmPassword: '',
      user           : new User('', '', '', '', '', '', '', 'ROLE_USER'),
      submitted      : false,
      successful     : false,
      message        : '', // 아래 메시지가 저장됨
    }
  },
  computed: {
    loggedIn () {
      // 공유저장소의 user객체에 속성인 loggedIn값을 가져옴
      return this.$store.state.auth.status.loggedIn
    },
  },
  methods : {
    handleRegister () {
      this.message = ''
      this.submitted = true // 회원가입 버튼 클릭 시
      // 유효성 체크 로직 실행 ( Vee-Validate 이용)
      // $validator.validate() 입력양식 유효성 체크가 통과하면
      // isValid = true
      this.$validator.validate().then(isValid => {
        // isValid = true 일때만 아래가 실행됨
        if (isValid) {
          // springBoot 서버 통신 : 공유저장소의 비동기메소드 호출(register)
          this.$store.dispatch('auth/register', this.user)
              // 성공 / 실패 then
              // 성공하면 첫번째 매개변수 실행
              // 실패하면 두번째 매개변수 실행
              .then(
                  // 성공
                  data => {
                    this.message = data.message
                    this.successful = true
                    // 강제 페이지 전환 : login
                    this.$router.push('/login')
                  },
                  // 실패
                  error => {
                    this.message = (error.response && error.response.data && error.response.data.message) ||
                        error.message ||
                        error.toString()
                    this.successful = false
                  },
              )
        }
      })
    },
  },
  mounted () {
    signup()
    // mdb()
    // Input()
    // 로그인 되어 있는 유저이면 /로 강제 페이지 이동시킴
    if (this.loggedIn) {
      this.$router.push('/')
    }
  },
}
</script>

<style scoped>
@import "@/assets/css/signup.css";
</style>