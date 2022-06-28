<template>
  <div class="col-md-12">
    <div class="card card-container">
      <img
        id="profile-img"
        src="//ssl.gstatic.com/accounts/ui/avatar_2x.png"
        class="profile-img-card"
      />
      <form name="form" @submit.prevent="handleLogin">
        <div class="form-group">
          <label for="username">Username</label>
          <input
            v-model="user.username"
            v-validate="'required'"
            type="text"
            class="form-control"
            name="username"
          />
          <div
            v-if="errors.has('username')"
            class="alert alert-danger"
            role="alert"
          >Username is required!</div>
        </div>
        <div class="form-group">
          <label for="password">Password</label>
          <input
            v-model="user.password"
            v-validate="'required'"
            type="password"
            class="form-control"
            name="password"
          />
          <div
            v-if="errors.has('password')"
            class="alert alert-danger"
            role="alert"
          >Password is required!</div>
        </div>
        <div class="form-group">
          <button class="btn btn-primary btn-block" :disabled="loading">
            <span v-show="loading" class="spinner-border spinner-border-sm"></span>
            <span>Login</span>
          </button>
        </div>
        <div class="form-group">
          <div v-if="message" class="alert alert-danger" role="alert">{{message}}</div>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import User from '@/models/user';

/* eslint-disable */
export default {
  name: 'Login',
  data() {
    return {
      user: new User('', ''),
      loading: false,
      message: ''
    };
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    }
  },
  // this.$store.state.auth.status.loggedIn. 상태가 true인 경우 ( 인증 사용자일 경우 )
  // 사용자를 프로필 페이지 로 안내( redirect )
  created() {
    if (this.loggedIn) {
      this.$router.push('/profile');
    }
  },
  methods: {
    handleLogin() {
      // 1) input tag에 v-validate로 rules를 설정합니다.
      // 2) this.$validator.validateAll()로 검증합니다.
      // 3) this.errors.any()로 에러가 있는지 확인합니다.
      // -> this.errors.has('field')로 확인 가능, this.errors.first('field')로 첫번째 가져올수 있음
      this.loading = true;
      this.$validator.validateAll().then(isValid => {
        // 유효성 체크 : false 이면 함수 종료
        if (!isValid) {
          this.loading = false;
          return;
        }

        if (this.user.username && this.user.password) {
          // 'auth/login'Vuex Store에 Action을 전달
          this.$store.dispatch('auth/login', this.user).then(
            //  로그인에 성공하면 프로필 페이지 로 이동
            () => {
              this.$router.push('/profile');
            },
            //  그렇지 않으면 오류 메시지를 표시
            error => {
              this.loading = false;
              this.message =
                (error.response && error.response.data && error.response.data.message) ||
                error.message ||
                error.toString();
            }
          );
        }
      });
    }
  }
};
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