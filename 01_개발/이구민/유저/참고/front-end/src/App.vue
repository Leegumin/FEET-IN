<template>
  <div id = "app">
    <nav class = "navbar navbar-expand navbar-dark bg-dark">
      <router-link to = "/"
                   class = "navbar-brand"
      >bezKoder
      </router-link>
      <div class = "navbar-nav mr-auto">
        <li class = "nav-item">
          <router-link to = "/tutorials"
                       class = "nav-link"
          >Tutorials
          </router-link>
        </li>
        <li class = "nav-item">
          <router-link to = "/add"
                       class = "nav-link"
          >Add
          </router-link>
        </li>
      </div>
      <!--로그인 태그 추가-->
      <div v-if = "!currentUser"
           class = "navbar-nav ml-auto"
      >
        <li class = "nav-item">
          <router-link to = "/register"
                       class = "nav-link"
          >
            <!--웹 아이콘 추가-->
            <font-awesome-icon icon = "user-plus"></font-awesome-icon>
            Sign Up
          </router-link>
        </li>
        <li class = "nav-item">
          <router-link to = "/login"
                       class = "nav-link"
          >
            <!--웹 아이콘 추가-->
            <font-awesome-icon icon = "sign-in-alt"></font-awesome-icon>
            Login
          </router-link>
        </li>
      </div>
      <!--로그아웃 태그 추가-->
      <div v-if = "currentUser"
           class = "navbar-nav ml-auto"
      >
        <li class = "nav-item">
          <router-link to = "/profile"
                       class = "nav-link"
          >
            <!--웹 아이콘 추가-->
            <font-awesome-icon icon = "user"></font-awesome-icon>
            {{ currentUser.username }}
          </router-link>
        </li>
        <li class = "nav-item">
          <!--@click.prevent="logOut" : 'a'태그의 기본 기능 대신 logOut 기능이 구현되도록 함-->
          <a href
             @click.prevent = "logOut"
          >
            <!--웹 아이콘 추가-->
            <font-awesome-icon icon = "sign-out-alt"></font-awesome-icon>
            LogOut
          </a>
        </li>
      </div>
    </nav>

    <div class = "container mt-3">
      <router-view/>
    </div>
  </div>
</template>

<script>
export default {
  // computed: 변수처럼 호출, 뒤에 () 안붙임
  computed: {
    currentUser () {
      // 공유저장소의 전역변수(공유변수 : user)
      // 자동으로 로그인 되었으면 loggedIn = true, user 객체 있음
      // 아닐 ㅣ시 loggedIn = false, user 객체 = null
      return this.$store.state.auth.user
      // return true
    },
  },
  // methods : 함수호출, 뒤에 () 붙음
  methods: {
    logOut () {
      // dispatch (호출하는 메소드) : actions에 있는 메소드를 호출
      this.$store.dispatch('auth/logout')
      // 로그아웃 후 이동할 페이지 지정 : login 페이지
      this.$router.push('/login')
    },
  },
}
</script>
