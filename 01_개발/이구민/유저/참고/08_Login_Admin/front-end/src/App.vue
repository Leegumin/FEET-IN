<template>
  <div id="app">
    <nav class="navbar sticky-top navbar-expand-lg navbar-dark bg-dark small">
      <a class="navbar-brand mr-md-5" href="#">bezkoder</a>
      <button
          class="navbar-toggler"
          type="button"
          data-toggle="collapse"
          data-target="#navbarNavDropdown"
          aria-controls="navbarNavDropdown"
          aria-expanded="false"
          aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
          <!-- dropdown #1 시작 -->
          <li class="nav-item dropdown mr-md-4">
            <a
                class="nav-link dropdown-toggle"
                href="#"
                id="navbarDropdownMenuLink"
                data-toggle="dropdown"
                aria-haspopup="true"
                aria-expanded="false"
            >
              About
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
              <router-link to="/home" class="dropdown-item">
                Home
              </router-link>
            </div>
          </li>
          <!-- dropdown #1 끝 -->

          <!-- dropdown #2 시작 -->
          <li class="nav-item dropdown mr-md-4">
            <a
                class="nav-link dropdown-toggle"
                href="#"
                id="navbarDropdownMenuLink"
                data-toggle="dropdown"
                aria-haspopup="true"
                aria-expanded="false"
            >
              Tutorial 메뉴
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
              <router-link to="/tutorials" class="dropdown-item">
                Tutorials
              </router-link>
              <router-link to="/add" class="dropdown-item">
                Add
              </router-link>
              <router-link to="/upload" class="dropdown-item">
                Upload Image
              </router-link>
            </div>
          </li>
          <!-- dropdown #2 끝 -->

          <li v-if="true" class="nav-item">
            <router-link to="/admin" class="nav-link">Admin Board</router-link>
          </li>
          <!-- menu #7 끝 -->
          <li class="nav-item">
            <router-link to="/user" class="nav-link">User Board</router-link>
          </li>
        </ul>

        <!--          Todo: login 추가 -->
        <!--      div #2 시작 -->
        <div v-if="!currentUser" class="navbar-nav ml-auto">
          <ul class="navbar-nav">
            <li class="nav-item">
              <router-link to="/register" class="nav-link">
                <font-awesome-icon icon="user-plus"/>
                Sign Up
              </router-link>
            </li>
            <li class="nav-item">
              <router-link to="/login" class="nav-link">
                <font-awesome-icon icon="sign-in-alt"/>
                Login
              </router-link>
            </li>
          </ul>
        </div>
        <!--      div #2 시작 -->

        <!--      div #3 시작 -->
        <div v-if="currentUser" class="navbar-nav ml-auto">
          <ul class="navbar-nav">
            <li class="nav-item">
              <router-link to="/profile" class="nav-link">
                <font-awesome-icon icon="user"/>
                {{ currentUser.username }}
              </router-link>
            </li>
            <li class="nav-item">
              <a class="nav-link" href @click.prevent="logOut">
                <font-awesome-icon icon="sign-out-alt"/>
                LogOut
              </a>
            </li>
          </ul>
        </div>
        <!--      div #3 시작 -->
      </div>
    </nav>

        <div class="container mt-4">
    <!-- 위의 router-link 있는 메뉴 화면이 아래위치에 표시 -->
    <router-view></router-view>
        </div>
  </div>
</template>

<script>
export default {
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
    showAdminBoard() {
      if (this.currentUser && this.currentUser.roles) {
        return this.currentUser.roles.includes('ROLE_ADMIN');
      }

      return false;
    },
  },
  methods: {
    logOut() {
      this.$store.dispatch('auth/logout');
      this.$router.push('/login');
    }
  }
};
</script>
