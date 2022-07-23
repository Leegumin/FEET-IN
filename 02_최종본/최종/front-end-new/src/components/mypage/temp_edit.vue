<template>
  <div v-if = "currentUser"
       class = "edit-form"
  >
    <h4>User</h4>
    <form>
      <!--      <div class = "form-group">
              <p class = "w-25">{{ currentUser }}</p>
            </div>-->
      <div class = "form-group">
        <label for = "name">Name</label>
        <input type = "text"
               class = "form-control"
               id = "name"
               v-model = "currentUser.name"
               disabled
        />
      </div>

      <div class = "form-group">
        <label for = "username">Id</label>
        <input type = "text"
               class = "form-control"
               id = "username"
               v-model = "currentUser.username"
               disabled
        />
      </div>

      <div class = "form-group">
        <label for = "password">Current Password</label>
        <!--비밀번호 자동완성 방지로 넣음 참고 : https://server0.tistory.com/45-->
        <input type = "password"
               style="display: block; width:0; height:0; border: 0;"
               @focus="$refs.pwdInput.focus()"
        >
        <!--비밀번호 자동완성 방지로 넣음 autocomplete="off", ref="pwdInput"-->
        <input type = "password"
               class = "form-control"
               id = "password"
               v-model = "currentUser.password"
               autocomplete="off"
               ref="pwdInput"
        />
      </div>

      <div class = "form-group">
        <label for = "newPassword">Change Password</label>
        <input type = "password"
               class = "form-control"
               id = "newPassword"
               v-model = "currentUser.newPassword"
        />
      </div>
      <div class = "form-group">
        <label for = "newPassword2">Confirm Change Password</label>
        <input type = "password"
               class = "form-control"
               id = "newPassword2"
        />
      </div>

      <div class = "form-group">
        <label for = "email">Email</label>
        <input type = "text"
               class = "form-control"
               id = "email"
               v-model = "currentUser.email"
        />
      </div>
      <div class = "form-group">
        <label for = "address">Address</label>
        <input type = "text"
               class = "form-control"
               id = "address"
               v-model = "currentUser.address"
        />
      </div>
      <div class = "form-group">
        <label for = "phone">Phone</label>
        <input type = "text"
               class = "form-control"
               id = "phone"
               v-model = "currentUser.phone"
        />
      </div>
    </form>

    <button class = "badge badge-danger mr-2 text-black"
            @click = "deleteUser"
    >
      Delete
    </button>

    <button type = "submit"
            class = "badge badge-success text-black"
            @click = "updateUser"
    >
      Update
    </button>
    <p>{{ message }}</p>
  </div>

  <div v-else>
    <br/>
    <p>User Data Not Found</p>
  </div>

</template>

<script>
import AuthService from '@/services/auth/AuthService'

/* eslint-disable */
export default {
  name: 'user',
  data () {
    return {
      message: '',
    }
  },
  computed: {
    currentUser () {
      return this.$store.state.auth.user
    },
  },
  methods : {
    /*getCurrentUser () {
     return this.currentUser = this.$store.state.auth.user
     },*/
    updateUser () {
      AuthService.update(this.currentUser).then(response => {
        console.log(response.data)
        this.message = 'The user was updated successfully!'
      }).catch(e => {
        console.log(this.currentUser)
        console.log(e)
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
  },
  mounted () {
    this.message = ''
  },
}
</script>

<style>
.edit-form {
  max-width: 300px;
  margin: auto;
}
</style>
