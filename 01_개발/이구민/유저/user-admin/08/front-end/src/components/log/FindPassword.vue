<template>
  <div class = "login-container">
    <section class = "gradient-custom login-form vh-100 vw-100"
             style = "min-height: 800px"
    >
      <div class = "container py-5 h-100">
        <div class = "row d-flex justify-content-center align-items-center h-100">
          <div class = "col-12 col-md-8 col-lg-6 col-xl-5">
            <div class = "card bg-dark text-white login-card"
                 style = "border-radius: 1rem;"
            >
              <div class = "card-body p-5 text-center">
                <div class = "mb-md-5 mt-md-4 pb-5">
                  <h2 class = "fw-bold mb-2 login-title">FORGOT PASSWORD</h2>

                  <form @submit.prevent = "sendEmail"
                        v-if = "!submitted"
                  >
                    <p class = "text-white-50 mb-5">가입정보를 입력해 주세요.</p>
                    <!-- * name -->
                    <div class = "form-outline form-white mb-4 form-floating">
                      <input type = "text"
                             id = "floatingInputId"
                             class = "form-control form-control-lg"
                             v-model = "user.username"
                             v-validate = "'required'"
                             name = "name"
                             placeholder = "Name"
                      />
                      <label v-if = "!errors.has('name')"
                             class = "form-label"
                             for = "floatingInputId"
                      >아이디</label>

                      <!--submit 버튼을 누르고 && 유효성에러가 발생했을 경우 아래 내용이 출력-->
                      <label v-else
                             class = "alert-white text-danger"
                             for = "floatingInputId"
                             role = "alert"
                      >아이디가 필요합니다!</label>
                    </div>

                    <!-- * email -->
                    <div class = "form-outline form-white mb-4 form-floating">
                      <input type = "email"
                             id = "floatingInputEmail"
                             class = "form-control form-control-lg"
                             v-model = "user.email"
                             v-validate = "'required'"
                             name = "email"
                             placeholder = "email"
                      />
                      <label v-if = "!errors.has('email')"
                             class = "form-label"
                             for = "floatingInputEmail"
                      >이메일 주소</label>
                      <label v-else
                             class = "alert-white text-danger"
                             for = "floatingInputEmail"
                             role = "alert"
                      >이메일 주소가 필요합니다!</label>
                      <!--error.has('username') : 유저네임을 입력 안해서 참값이 되면 오류 노출-->
                      <!--                      <div v-if="errors.has('password')"-->
                      <!--                           class="alert alert-white text-danger"-->
                      <!--                           role="alert"-->
                      <!--                      >-->
                      <!--                        Password가 필요합니다!-->
                      <!--                      </div>-->
                      <button id = "code-btn"
                      >코드 전송
                      </button>
                    </div>
                  </form>
                  <form @submit.prevent = "checkCode"
                        v-else-if = "!submitted2"
                  >
                    <!-- * code -->
                    <div class = "form-outline form-white mb-4 form-floating">
                      <input type = "text"
                             id = "floatingInputCode"
                             class = "form-control form-control-lg"
                             v-model = "codeCheck"
                             v-validate = "'required'"
                             name = "codeCheck"
                             placeholder = "code"
                      />
                      <label v-if = "!errors.has('codeCheck')"
                             class = "form-label"
                             for = "floatingInputCode"
                      >코드 입력</label>

                      <!--submit 버튼을 누르고 && 유효성에러가 발생했을 경우 아래 내용이 출력-->
                      <label v-else
                             class = "alert-white text-danger"
                             for = "floatingInputCode"
                             role = "alert"
                      >코드가 필요합니다!</label>
                    </div>

                    <div class = "form-group">
                      <button class = "btn btn-outline-light btn-lg px-5 login-btn"
                              :disabled = "loading"
                      ><span v-if = "loading"
                             class = "spinner-border spinner-border-sm border-dark text-center"
                      ></span>
                        <span v-else>코드 확인</span>
                      </button>
                    </div>
                    <div class = "form-group mt-3">
                      <div v-if = "message"
                           class = "alert alert-danger"
                           role = "alert"
                      >
                        {{ message }}
                      </div>
                    </div>
                  </form>

                  <form @submit.prevent = "changePassword"
                        v-else
                  >
                    <p class = "text-white-50 mb-5">변경할 비밀번호를 입력해주세요.</p>
                    <!-- * newPaswword -->
                    <div class = "form-outline form-white mb-4 form-floating">
                      <input type = "password"
                             id = "floatingInputNewPassword"
                             class = "form-control form-control-lg"
                             v-model = "newPassword"
                             v-validate = "'required|min:6|max:12'"
                             name = "newPassword"
                             placeholder = "newPassword"
                             ref = "newPassword"
                             maxlength = "12"
                      />
                      <label v-if = "!errors.has('newPassword')"
                             class = "form-label"
                             for = "floatingInputNewPassword"
                      >변경할 비밀번호</label>

                      <!--submit 버튼을 누르고 && 유효성에러가 발생했을 경우 아래 내용이 출력-->
                      <label v-else
                             class = "alert-white text-danger"
                             for = "floatingInputNewPassword"
                             role = "alert"
                      >비밀번호가 필요합니다!</label>
                    </div>
                    <!-- * Repeat Password -->
                    <div class = "form-outline form-white mb-4 form-floating">
                      <input type = "password"
                             id = "floatingInputRepeatPassword"
                             class = "form-control form-control-lg"
                             placeholder = "Repeat Password"
                             v-validate = "'required|confirmed:newPassword'"
                             data-vv-as = "Repeat Password"
                             name = "confirmPassword"
                      />
                      <label v-if = "!errors.has('confirmPassword')"
                             class = "form-label"
                             for = "floatingInputRepeatPassword"
                      >비밀번호 재입력</label>
                      <!--submit 버튼을 누르고 && 유효성에러가 발생했을 경우 아래 내용이 출력-->
                      <label
                          v-else
                          class = "alert-white text-danger"
                          for = "floatingInputRepeatPassword"
                          role = "alert"
                      >
                        비밀번호가 일치하지 않습니다!
                      </label>
                    </div>
                    <button id = "code-btn"
                    >변경하기
                    </button>
                  </form>


                </div>
                <div class = "signup-link">
                  <p class = "mb-0">비번호를 잊으셨나요? <a href = "/findPw"
                                                   class = "text-white-50 fw-bold"
                                                   style = "font-size: 18px; font-weight: 700;"
                  >비밀번호 찾기</a>
                  </p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
export default {
  name: 'FindID',
  data () {
    return {
      user           : {
        email   : '',
        username: '',
      },
      code           : '',
      id             : '',
      submitted      : false,
      submitted2     : false,
      newPassword    : '',
      codeCheck      : '',
      showID         : false,

      loading: false,
      message: '',
    }
  },
  methods: {
    sendEmail () {
      this.message = ''
      this.$validator.validate().then(isValid => {
        if (isValid) {
          // 유효성 체크 로직 실행 ( Vee-Validate 이용)
          // $validator.validate() 입력양식 유효성 체크가 통과하면
          // isValid = true
          // isValid = true 일때만 아래가 실행됨
          // springBoot 서버 통신 : 공유저장소의 비동기메소드 호출(register)

          this.$store.dispatch('auth/findID', this.user)
              // 성공 / 실패 then
              // 성공하면 첫번째 매개변수 실행
              // 실패하면 두번째 매개변수 실행
              .then(
                  // 성공
                  (data) => {
                    alert(data.name + '님 이메일을 발송했습니다!')
                    this.submitted = true
                    this.code = data.code
                    this.id = data.username

                    // 입력값 초기화
                    this.user.email = ''
                    this.user.name = ''
                  },
                  // 실패
                  error => {
                    this.message = (error.response && error.response.data && error.response.data.message) ||
                        error.message ||
                        error.toString()
                  },
              )
        }
      })

    },
    checkCode () {
      this.$validator.validate().then(isValid => {
        if (isValid) {
          if (this.code === this.codeCheck) {
            this.submitted2 = true
          }
          else {
            alert('코드가 일치하지 않습니다!')
          }
        }
      })
    },
    changePassword () {
      this.$validator.validate().then(isValid => {
        if (isValid) {
          let user = {
            newPassword: this.newPassword,
            username   : this.id,
          }
          this.$store.dispatch('auth/findPassword', user).then(
              // 성공
              (data) => {
                console.log(data.data)
                alert('비밀번호 변경에 성공했습니다!')
                this.$router.push('/login')
              },
              // 실패
              error => {
                this.message = (error.response && error.response.data && error.response.data.message) ||
                    error.message ||
                    error.toString()
              },
          )
        }
      })

    },
  },
}
</script>


<style scoped>
@import "@/assets/css/login.css";
/*@import '~mdb-ui-kit/css/mdb.min.css';*/
</style>