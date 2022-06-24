<template>
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

                  <div class = "form-outline form-white mb-4">
                    <input type = "text"
                           id = "typeIdlX"
                           class = "form-control form-control-lg"
                           v-model = "user.username"
                           v-validate = "'required|min:3|max:20'"
                           data-vv-as = "ID"
                           name = "username"
                    />
                    <label class = "form-label"
                           for = "typeIdlX"
                    >ID</label>
                    <!--submit 버튼을 누르고 && 유효성에러가 발생했을 경우 아래 내용이 출력-->
                    <div
                        v-if = "errors.has('username') && submitted"
                        class = "alert-white text-danger"
                    >
                      {{ errors.first('username') }}
                    </div>
                  </div>

                  <div class = "form-outline form-white mb-4">
                    <input type = "password"
                           id = "typePasswordX"
                           class = "form-control form-control-lg"
                           placeholder = "최소 6자, 최대 12자"
                           v-model = "user.password"
                           v-validate = "'required|min:6|max:12'"
                           data-vv-as = "Password"
                           name = "password"
                           ref = "password"
                    />
                    <label class = "form-label"
                           for = "typePasswordX"
                    >Password</label>
                    <!--submit 버튼을 누르고 && 유효성에러가 발생했을 경우 아래 내용이 출력-->
                    <div
                        v-if = "errors.has('password') && submitted"
                        class = "alert-white text-danger"
                    >
                      {{ errors.first('password') }}
                    </div>
                  </div>

                  <div class = "form-outline form-white mb-4">
                    <input type = "password"
                           id = "typeRepeatPasswordX"
                           class = "form-control form-control-lg"
                           placeholder="Password, Again"
                           v-model = "confirmPassword"
                           v-validate = "'required|confirmed:password'"
                           data-vv-as = "Repeat Password"
                           name = "confirmPassword"
                    />
                    <label class = "form-label"
                           for = "typeRepeatPasswordX"
                    >Repeat Password</label>
                    <!--submit 버튼을 누르고 && 유효성에러가 발생했을 경우 아래 내용이 출력-->
                    <div
                        v-if = "errors.has('confirmPassword') && submitted"
                        class = "alert-white text-danger"
                    >
                      <!--{{ errors.first('confirmPassword') }}-->
                      Password가 일치하지 않습니다!
                    </div>
                  </div>


                  <div class = "form-outline form-white mb-4">
                    <input type = "text"
                           id = "typeNamelX"
                           class = "form-control form-control-lg"
                           v-model = "user.name"
                           v-validate = "'required|min:3|max:20'"
                           data-vv-as = "Name"
                           name = "name"
                    />
                    <label class = "form-label"
                           for = "typeNamelX"
                    >Name</label>
                    <!--submit 버튼을 누르고 && 유효성에러가 발생했을 경우 아래 내용이 출력-->
                    <div
                        v-if = "errors.has('name') && submitted"
                        class = "alert-white text-danger"
                    >
                      {{ errors.first('name') }}
                    </div>
                  </div>

                  <!-- * personal number -->
                  <!--                                <div class="d-flex justify-content-center">
                                                    <div class="form-outline form-white mb-4">
                                                      <input type="number" id="typePersonalNumlX" class="form-control form-control-lg"/>
                                                      <label class="form-label" for="typetypePersonalNumlX">Personal Number</label>
                                                    </div>
                                                    <span class="pd"> - </span>
                                                    <div class="form-outline form-white mb-4">
                                                      <input type="password" id="typePersonalNumlX2" class="form-control form-control-lg"/>
                                                      <label class="form-label" for="typetypePersonalNumlX"></label>
                                                    </div>
                                                  </div>-->

                  <!-- * datepicker -->
                  <!--                                <div class="form-outline form-white mb-4">
                                                    <input type="date" id="typeDatelX" class="form-control form-control-lg"/>
                                                    <label class="form-label" for="typeDatelX">Birth</label>
                                                  </div>-->

                  <div class = "form-outline form-white mb-4">
                    <input type = "text"
                           id = "typeAddresslX"
                           class = "form-control form-control-lg"
                           v-model = "user.address"
                           v-validate = "'required|min:3|max:40'"
                           data-vv-as = "Address"
                           name = "address"
                    />
                    <label class = "form-label"
                           for = "typeAddresslX"
                    >Address</label>
                    <!--submit 버튼을 누르고 && 유효성에러가 발생했을 경우 아래 내용이 출력-->
                    <div
                        v-if = "errors.has('address') && submitted"
                        class = "alert-white text-danger"
                    >
                      {{ errors.first('address') }}
                    </div>
                  </div>

                  <!-- * phone -->
                  <!--                                <div class="d-flex justify-content-center">
                                                    <div class="form-outline form-white mb-4">
                                                      <input type="number" id="typePhonelX" class="form-control form-control-lg" min="3" max="3"/>
                                                      <label class="form-label" for="typePhonelX">Phone</label>
                                                    </div>
                                                    <span class="pd"> - </span>
                                                    <div class="form-outline form-white mb-4">
                                                      <input type="number" id="typePhonelX2" class="form-control form-control-lg"/>
                                                      <label class="form-label" for="typePhonelX"></label>
                                                    </div>
                                                  </div>-->

                  <div class = "form-outline form-white mb-4">
                    <input type = "tel"
                           id = "typePhonelX"
                           class = "form-control form-control-lg"
                           placeholder = "'-' 제외하고 입력"
                           v-model = "user.phone"
                           v-validate = "'required|min:9|max:11'"
                           data-vv-as = "Phone"
                           name = "phone"
                    />
                    <label class = "form-label"
                           for = "typePhonelX"
                    >Phone</label>
                    <!--submit 버튼을 누르고 && 유효성에러가 발생했을 경우 아래 내용이 출력-->
                    <div
                        v-if = "errors.has('phone') && submitted"
                        class = "alert-white text-danger"
                    >
                      {{ errors.first('phone') }}
                    </div>
                  </div>

                  <div class = "form-outline form-white mb-4">
                    <input type = "email"
                           id = "typeEmaillX"
                           class = "form-control form-control-lg"
                           v-model = "user.email"
                           v-validate = "'required|max:40'"
                           data-vv-as = "Email"
                           name = "email"
                    />
                    <label class = "form-label"
                           for = "typeEmaillX"
                    >Email</label>
                    <!--submit 버튼을 누르고 && 유효성에러가 발생했을 경우 아래 내용이 출력-->
                    <div
                        v-if = "errors.has('email') && submitted"
                        class = "alert-white text-danger"
                    >
                      {{ errors.first('email') }}
                    </div>
                  </div>

                  <button class = "btn btn-outline-light btn-lg px-5 signup-btn"
                  >Sign Up
                  </button>
                  <!--화면에 에러 메시지 출력-->
                  <div v-if = "message"
                       class = "alert"
                       :class = "successful ? 'alert-success' : 'alert-danger'"
                  >
                    {{ message }}
                  </div>
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
import signup from '@/assets/js/signup'
import * as mdb from 'mdb-ui-kit' // lib
import { Input } from 'mdb-ui-kit' // module
import User from '@/models/user'

export default {
  name: 'LoginCom',
  data () {
    return {
      confirmPassword: '',
      user           : new User('', '', '', '', '', '', ''),
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
                    this.$router.push("/login")
                  },
                  // 실패
                  error => {
                    this.message = (error.message || error.toString())
                    this.successful = false
                  },
              )
        }
      })
    },
  },
  mounted () {
    signup()
    mdb()
    Input()
    // 로그인 되어 있는 유저이면 /로 강제 페이지 이동시킴
    if (this.loggedIn) {
      this.$router.push('/')
    }
  },
}
</script>

<style>
@import "@/assets/css/signup.css";
</style>