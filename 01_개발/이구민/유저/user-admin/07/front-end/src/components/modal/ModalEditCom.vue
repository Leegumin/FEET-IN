<template>
  <transition name = "modal">
    <div class = "modal-mask">
      <div class = "modal-wrapper">
        <div class = "modal-container">

          <div class = "modal-body">
            <slot name = "body">
              <div v-if = "currentUser"
                   class = "edit-form"
              >
                <form>
                  <!--      <div class = "form-group">
                          <p class = "w-25">{{ currentUser }}</p>
                        </div>-->
                  <div class = "edit-form-group">
                    <label for = "name">Name</label>
                    <input type = "text"
                           class = "form-control"
                           id = "name"
                           v-model = "currentUser.name"
                           readonly
                    />
                  </div>

                  <div class = "edit-form-group">
                    <label for = "username">Id</label>
                    <input type = "text"
                           class = "form-control"
                           id = "username"
                           v-model = "currentUser.username"
                           readonly
                    />
                  </div>

                  <div class = "edit-form-group">
                    <label for = "password">Current Password</label>
                    <!--비밀번호 자동완성 방지로 넣음 참고 : https://server0.tistory.com/45-->
                    <input type = "password"
                           style = "display: block; width:0; height:0; border: 0;"
                           @focus = "$refs.pwdInput.focus()"
                    >
                    <!--비밀번호 자동완성 방지로 넣음 autocomplete="off", ref="pwdInput"-->
                    <input type = "password"
                           class = "form-control"
                           id = "password"
                           v-model = "currentUser.password"
                           autocomplete = "off"
                           v-validate = "'required'"
                           ref = "pwdInput"
                           name = "password"
                    />
                    <div v-if = "(errors.has('password') && submitted)"
                         class = "alert-white text-danger"
                         for = "password"
                         role = "alert"
                    >{{ CurrentPasswordRequired }}
                    </div>
                  </div>

                  <div class = "edit-form-group">
                    <label for = "newPassword">Change Password</label>
                    <input type = "password"
                           class = "form-control"
                           id = "newPassword"
                           v-model = "currentUser.newPassword"
                    />
                  </div>
                  <div class = "edit-form-group">
                    <label for = "newPassword2">Confirm Change Password</label>
                    <input type = "password"
                           class = "form-control"
                           id = "newPassword2"
                    />
                  </div>

                  <div class = "edit-form-group">
                    <label for = "email">Email</label>
                    <input type = "text"
                           class = "form-control"
                           id = "email"
                           v-model = "currentUser.email"
                    />
                  </div>
                  <div class = "edit-form-group">
                    <label for = "postcode">postcode</label>
                    <input type = "text"
                           class = "form-control"
                           id = "postcode"
                           v-model = "postcode"
                           readonly
                    />
                    <button type = "button"
                            @click = "search()"
                    >우편번호 찾기
                    </button>
                  </div>
                  <div class = "edit-form-group">
                    <label for = "roadAddress">roadAddress</label>
                    <input type = "text"
                           class = "form-control"
                           id = "roadAddress"
                           v-model = "roadAddress"
                           readonly
                    />
                  </div>
                  <div class = "edit-form-group">
                    <label for = "jibunAddress">jibunAddress</label>
                    <input type = "text"
                           class = "form-control"
                           id = "jibunAddress"
                           v-model = "jibunAddress"
                           readonly
                    />
                  </div>
                  <div class = "edit-form-group">
                    <label for = "detailAddress">detailAddress</label>
                    <input type = "text"
                           class = "form-control"
                           id = "detailAddress"
                           v-model = "detailAddress"
                    />
                  </div>

                  <div class = "edit-form-group">
                    <label for = "phone">Phone</label>
                    <input type = "text"
                           class = "form-control"
                           id = "phone"
                           v-model = "currentUser.phone"
                    />
                  </div>
                </form>

                <div class = "btn-set">
                  <button class = "badge badge-danger mr-2"
                          @click = "deleteUser"
                  >
                    Delete
                  </button>

                  <button type = "submit"
                          class = "badge badge-success"
                          @click = "updateUser"
                  >
                    Update
                  </button>
                  <button class = "badge badge-success"
                          id = "close-btn"
                          @click = "$emit('close')"
                  >
                    Close
                  </button>
                </div>
                <div class = "message">{{ message }}</div>
              </div>

              <div v-else>
                <br/>
                <p>User Data Not Found</p>
              </div>
            </slot>
          </div>

        </div>
      </div>
    </div>
  </transition>
</template>

<script>
import AuthService from '@/services/auth/AuthService'

/* eslint-disable */
export default {
  name: 'user',
  data () {
    return {
      submitted: false,
      message  : '',
      Change   : true,

      postcode     : '',
      roadAddress  : '',
      jibunAddress : '',
      detailAddress: '',
    }
  },
  computed: {
    currentUser () {
      return this.$store.state.auth.user
    },
    address () {
      return this.postcode + '/' + this.roadAddress + '/' + this.jibunAddress + '/' + this.detailAddress
    },
    currentUserAddress () {
      return this.currentUser.address.split('/')
    },
    CurrentPasswordRequired () {
      return alert('Password가 필요합니다!')
    },
  },
  methods : {
    /*getCurrentUser () {
     return this.currentUser = this.$store.state.auth.user
     },*/
    updateUser () {
      this.submitted = true
      this.$validator.validate().then(isValid => {
        // isValid = true 일때만 아래가 실행됨
        if (isValid) {
          if (confirm('수정 하시겠습니까?')) {
            this.currentUser.address = this.address
            AuthService.update(this.currentUser).then(response => {
              console.log(response.data)
              this.message = 'The user was updated successfully!'
            }).catch(e => {
              console.log(this.currentUser)
              console.log(e)
            })
          }
        }
      })
    },

    deleteUser () {
      // confirm 경고창
      if (confirm('정말 탈퇴 하시겠습니까?')) {
        AuthService.delete(this.currentUser.id).then(response => {
          console.log(response.data)
          // dispatch (호출하는 메소드) : actions에 있는 메소드를 호출
          this.$store.dispatch('auth/logout')
          // 메인페이지로 이동
          this.$router.push('/')
        }).catch(e => {
          console.log(e)
        })
      }
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
    this.message = ''
    this.postcode = this.currentUserAddress[0]
    this.roadAddress = this.currentUserAddress[1]
    this.jibunAddress = this.currentUserAddress[2]
    this.detailAddress = this.currentUserAddress[3]
  },
}
</script>
<style>
@import "@/assets/css/modal.css";
</style>
<style>

.message {
  font-size: 14px;
  color: #36363f;
  font-weight: 500;
}
</style>