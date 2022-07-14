<template>
  <div class = "signup-container">
    <section class = "vh-100 gradient-custom signup-form vh-100 vw-100"
             style = "min-height: 1200px"
    >
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
                             class = "alert-white text-danger"
                             for = "floatingInput"
                             role = "alert"
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
                             maxlength="12"
                      />
                      <label v-if = "!(errors.has('password') && submitted)"
                             class = "form-label"
                             for = "floatingInputPassword"
                      >Password</label>
                      <!--submit 버튼을 누르고 && 유효성에러가 발생했을 경우 아래 내용이 출력-->
                      <label v-else
                             class = "alert-white text-danger"
                             for = "floatingInputPassword"
                             role = "alert"
                      >Password가 필요합니다!</label>
                    </div>

                    <!-- * Repeat Password -->
                    <div class = "form-outline form-white mb-4 form-floating">
                      <input type = "password"
                             id = "floatingInputRepeatPassword"
                             class = "form-control form-control-lg"
                             placeholder = "Repeat Password"
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
                          class = "alert-white text-danger"
                          for = "floatingInputRepeatPassword"
                          role = "alert"
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
                          class = "alert-white text-danger"
                          for = "floatingInputName"
                          role = "alert"
                      >
                        Name이 필요합니다!
                      </label>
                    </div>

                    <!--
                                        &lt;!&ndash; * Address &ndash;&gt;
                                        <div class = "form-outline form-white mb-4 form-floating">
                                          <input type = "text"
                                                 id = "floatingInputAddress"
                                                 class = "form-control form-control-lg"
                                                 v-model = "user.address"
                                                 v-validate = "'required'"
                                                 data-vv-as = "Address"
                                                 name = "address"
                                                 placeholder = "Address"
                                          />
                                          <label v-if = "!(errors.has('address') && submitted)"
                                                 class = "form-label"
                                                 for = "floatingInputAddress"
                                          >Address</label>
                                          &lt;!&ndash;submit 버튼을 누르고 && 유효성에러가 발생했을 경우 아래 내용이 출력&ndash;&gt;
                                          <label
                                              v-else
                                              class = "alert-white text-danger"
                                              for = "floatingInputAddress"
                                              role = "alert"
                                          >
                                            Address가 필요합니다!
                                          </label>
                                        </div>
                    -->
                    <!-- * Address2 -->
                    <!-- !우편번호-->
                    <div>
                      <div class = "form-outline form-white mb-4 form-floating">
                        <input type = "text"
                               id = "postcode"
                               class = "form-control form-control-lg"
                               v-model = "postcode"
                               v-validate = "'required'"
                               data-vv-as = "postcode"
                               name = "postcode"
                               placeholder = "우편번호"
                               readonly
                        >
                        <label v-if = "!(errors.has('postcode') && submitted)"
                               class = "form-label"
                               for = "postcode"
                        >우편번호</label>
                        <!--submit 버튼을 누르고 && 유효성에러가 발생했을 경우 아래 내용이 출력-->
                        <label
                            v-else
                            class = "alert-white text-danger"
                            for = "postcode"
                            role = "alert"
                        >
                          우편번호가 필요합니다!
                        </label>
                        <button type = "button"
                                @click = "search()"
                        >우편번호 찾기
                        </button>

                      </div>
                      <!-- !우편번호-->
                      <!-- !도로명주소-->
                      <div class = "form-outline form-white mb-4 form-floating">
                        <input type = "text"
                               id = "roadAddress"
                               class = "form-control form-control-lg"
                               v-model = "roadAddress"
                               v-validate = "'required'"
                               data-vv-as = "RoadAddress"
                               name = "roadAddress"
                               placeholder = "우편번호"
                               readonly
                        >
                        <label class = "form-label"
                               for = "roadAddress"
                        >도로명주소</label>
                      </div>
                      <!-- !도로명주소-->
                      <!-- !지번주소-->
                      <div class = "form-outline form-white mb-4 form-floating">
                        <input type = "text"
                               id = "jibunAddress"
                               class = "form-control form-control-lg"
                               v-model = "jibunAddress"
                               data-vv-as = "JibunAddress"
                               name = "jibunAddress"
                               placeholder = "지번주소"
                               readonly
                        >
                        <label class = "form-label"
                               for = "jibunAddress"
                        >지번주소</label>
                      </div>
                      <!-- !지번주소-->
                      <!-- !상세주소-->
                      <div class = "form-outline form-white mb-4 form-floating">
                        <input type = "text"
                               id = "detailAddress"
                               class = "form-control form-control-lg"
                               v-model = "detailAddress"
                               data-vv-as = "DetailAddress"
                               name = "detailAddress"
                               placeholder = "상세주소"
                        >
                        <label class = "form-label"
                               for = "detailAddress"
                        >상세주소</label>
                      </div>
                    </div>
                    <!-- !상세주소-->

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
                          class = "alert-white text-danger"
                          for = "floatingInputPhone"
                          role = "alert"
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
                          class = "alert-white text-danger"
                          for = "floatingInputEmail"
                          role = "alert"
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

      postcode     : '',
      roadAddress  : '',
      jibunAddress : '',
      detailAddress: '',

    }
  },
  computed: {
    loggedIn () {
      // 공유저장소의 user객체에 속성인 loggedIn값을 가져옴
      return this.$store.state.auth.status.loggedIn
    },
    address () {
      return this.postcode + '/' + this.roadAddress + '/' + this.jibunAddress + '/' + this.detailAddress
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
          this.user.address = this.address
          console.log(this.user)
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
                    alert(this.user.name + ' 님 회원가입을 축하합니다!')
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
    search () { //@click을 사용할 때 함수는 이렇게 작성해야 한다.
      new window.daum.Postcode({
        oncomplete: (data) => { //function이 아니라 => 로 바꿔야한다.
          // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

          // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
          // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
          var roadAddr = data.roadAddress // 도로명 주소 변수
          var extraRoadAddr = '' // 참고 항목 변수

          // 법정동명이 있을 경우 추가한다. (법정리는 제외)
          // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
          if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
            extraRoadAddr += data.bname
          }
          // 건물명이 있고, 공동주택일 경우 추가한다.
          if (data.buildingName !== '' && data.apartment === 'Y') {
            extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName)
          }
          // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
          if (extraRoadAddr !== '') {
            extraRoadAddr = ' (' + extraRoadAddr + ')'
          }

          // 우편번호와 주소 정보를 해당 필드에 넣는다.
          this.postcode = data.zonecode
          this.roadAddress = roadAddr
          this.jibunAddress = data.jibunAddress

        },
      }).open()
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