<template>
  <div class="container">
    <header class="jumbotron">
      <h3>{{content}}</h3>
    </header>
  </div>
</template>

<script>
import UserService from '@/services/auth/UserDataService';

/* eslint-disable */
export default {
  name: 'BoardAdmin',
  data() {
    return {
      content: ''
    };
  },
  mounted() {
    // 인증된 사용자만 볼 수 있는 페이지 : 헤더에 JWT를 실어 보냄
    UserService.getAdminBoard().then(
      response => {
        this.content = response.data;
      },
      error => {
        this.content =
          (error.response && error.response.data && error.response.data.message) ||
          error.message ||
          error.toString();
      }
    );
  }
};
</script>
