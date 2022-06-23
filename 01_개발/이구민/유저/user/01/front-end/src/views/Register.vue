<template>
  <!--부트스트랩 화면 배치 : col(열)로 12등분-->
  <!--Extra small (for smartphone)   .col-xs-* : 항상 가로로 배치 -->
  <!--Small (for tablets)   .col-sm-* : 768px 이하에서 세로로 표시 시작-->
  <!--Medium (for laptops)   .col-md-* : 992px 이하에서 세로로 표시 시작-->
  <!--Large (for laptops / desktops)   .col-lg-* :1200px 이하에서 세로로 표시 시작 -->
  <!--col-md-12 : medium화면 size(에서 화면을 12등분해서 보겠다.-->
  <div class = "col-md-12">
    <div class = "card card-container">
      <img src = "//ssl.gstatic.com/accounts/ui/avatar_2x.png"
           alt = "None"
           id = "profile-img"
           class = "profile-img-card"
      />
      <form name = "form"
            @submit.prevent = "handleRegister"
      >
        <!--successful: false => 회원가입 버튼을 클릭 안한 상태, 혹은 에러가 난 상태-->
        <!--successful: true => 회원가입 버튼을 클릭한 상태-->
        <div v-if = "!successful">
          <div class = "form-group">
            <label for = "username">UserName</label>
            <input v-model = "user.username"
                   v-validate = "'required|min:3|max:20'"
                   type = "text"
                   class = "form-control"
                   name = "username"
            >
          </div>
          <!--submit 버튼을 누르고 && 유효성에러가 발생했을 경우 아래 내용이 출력-->
          <div
              v-if = "errors.has('username') && submitted"
              class = "alert-danger"
          >
            {{ errors.first('username') }}
          </div>

          <!--이메일 입력 양식 #1 시작-->
          <div class = "form-group">
            <label for = "email">Email</label>
            <input v-model = "user.email"
                   v-validate = "'required|max:50'"
                   type = "text"
                   class = "form-control"
                   name = "email"
            >
          </div>
          <!--submit 버튼을 누르고 && 유효성에러가 발생했을 경우 아래 내용이 출력-->
          <div
              v-if = "errors.has('email') && submitted"
              class = "alert-danger"
          >
            {{ errors.first('email') }}
          </div>
          <!--이메일 입력 양식 #1 끝-->

          <!--패스워드 입력양식 #1 시작-->
          <div class = "form-group">
            <label for = "password">Password</label>
            <input v-model = "user.password"
                   v-validate = "'required|min:6|max:40'"
                   type = "password"
                   class = "form-control"
                   name = "password"
            >
          </div>
          <!--submit 버튼을 누르고 && 유효성에러가 발생했을 경우 아래 내용이 출력-->
          <div
              v-if = "errors.has('password') && submitted"
              class = "alert-danger"
          >
            {{ errors.first('password') }}
          </div>
          <!--패스워드 입력양식 #1 끝-->
          <!--가입 버튼 #1 시작-->
          <div class = "form-group">
            <button class = "btn btn-primary btn-block">Sign Up</button>
          </div>
          <!--가입 버튼 #1 끝-->
        </div>
      </form>
      <!--화면에 에러 메시지 출력-->
      <div v-if = "message"
           class = "alert"
           :class = "successful ? 'alert-success' : 'alert-danger'"
      >
        {{ message }}
      </div>
    </div>
  </div>
</template>

<script>
import User from '@/models/user'

/* eslint-disable*/
export default {
  name: 'Register',
  data () {
    return {
      user      : new User('', '', ''),
      submitted : false,
      successful: false,
      message   : '', // 아래 메시지가 저장됨
    }
  },
  computed: {
    loggedIn () {
      // 공유저장소의 user객체에 속성인 loggedIn값을 가져옴
      return this.$store.state.auth.status.loggedIn
    },
  },
  // 화면이 뜨자마자 실해오디는 이벤트
  mounted () {
    // 로그인 되어 있는 유저이면 /profile로 강제 페이지 이동시킴
    if (this.loggedIn) {
      this.$router.push('/profile')
    }
  },
  methods: {
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
                    // this.$router.push("/login")
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
}
</script>

<style scoped>
label {
  display: block;
  margin-top: 10px;
}

.card-container.card {
  max-width: 350px !important;
  padding: 40px 40px;
}

.card {
  background-color: #f7f7f7;
  padding: 20px 25px 30px;
  margin: 0 auto 25px;
  margin-top: 50px;
  -moz-border-radius: 2px;
  -webkit-border-radius: 2px;
  border-radius: 2px;
  -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
}

.profile-img-card {
  width: 96px;
  height: 96px;
  margin: 0 auto 10px;
  display: block;
  -moz-border-radius: 50%;
  -webkit-border-radius: 50%;
  border-radius: 50%;
}
</style>