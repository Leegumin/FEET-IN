<template>
  <div>
    <form @submit.prevent = "sendEmail" v-if="!submitted">
      <input type = "text"
             id = "name"
             v-model = "user.name"
      >
      <label for = "name">이름</label>
      <input type = "text"
             id = "mail"
             v-model = "user.email"
      >
      <label for = "mail">메일</label>
      <button>전송</button>
    </form>
    <form @submit.prevent = "checkCode" v-show = submitted>
      <input type = "text"
             id = "codeCheck"
             v-model = "codeCheck"
      ><label for = "codeCheck">코드를 입력해주세요</label>
      <button>확인</button>
      <p v-show = "showID">아이디 : {{ id }}</p>
    </form>
    <!--테스트용 코드 노출, 테스트 끝나면 삭제-->
    {{ code }}
  </div>
</template>

<script>
export default {
  name: 'FindID',
  data () {
    return {
      user     : {
        email: '',
        name : '',
      },
      code     : '',
      id       : '',
      submitted: false,

      codeCheck: '',
      showID   : false,
    }
  },
  methods: {
    /*sendEmail () {
     this.message = ''
     this.submitted = true // 회원가입 버튼 클릭 시
     // 유효성 체크 로직 실행 ( Vee-Validate 이용)
     // $validator.validate() 입력양식 유효성 체크가 통과하면
     // isValid = true
     this.$validator.validate().then(isValid => {
     // isValid = true 일때만 아래가 실행됨
     if (isValid) {
     // springBoot 서버 통신 : 공유저장소의 비동기메소드 호출(register)
     this.$store.dispatch('auth/findID', this.user)
     // 성공 / 실패 then
     // 성공하면 첫번째 매개변수 실행
     // 실패하면 두번째 매개변수 실행
     .then(
     // 성공
     () => {
     /!*this.message = data.message
     this.successful = true*!/
     // 강제 페이지 전환 : login
     alert(this.user.name + ' 님 이메일을 발송했습니다!')
     },
     // 실패
     error => {
     this.message = (error.response && error.response.data && error.response.data.message) ||
     error.message ||
     error.toString()
     /!*this.successful = false*!/
     },
     )
     }
     })
     },*/
    sendEmail () {
      /*alert('실행!')*/
      this.message = ''
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
                /*this.message = data.message
                 this.successful = true*/
                alert(this.user.name + ' 님 이메일을 발송했습니다!')
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
                /*this.successful = false*/
              },
          )
    },
    checkCode () {
      /*alert('실행!')*/
      if (this.code === this.codeCheck) {
        this.showID = true
      }
      else {
        alert('코드가 일치하지 않습니다!')
      }
    },
  },
}
</script>

<style scoped>

</style>