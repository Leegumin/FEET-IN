<template>
  <nav class="navbar navbar-expand-lg fixed-top">
    <div class="container-fluid">
      <a class="navbar-brand d-flex justify-content-between align-items-center order-lg-0" href="/">
        <!--            <img src="https://via.placeholder.com/120x60" alt="Logo">-->
        <!--            <img src="#" alt="Logo">-->
        <span class="text-uppercase fw-lighter ms-2 logo-title"><strong>BRAND </strong>LOGO</span>
      </a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
              aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <i class="fa-solid fa-align-justify mx-1"></i>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item dropdown outer-list">
            <a class="nav-link active" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown"
               aria-expanded="false">
              <!--                        <i class="fa-solid fa-align-justify mx-2"></i>SHOES-->
              SHOES
            </a>
            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
              <li class="inner-list"><a class="dropdown-item" href="/all"><i class="fa-solid fa-border-all"></i> 전체상품</a>
              </li>
              <li>
                <hr class="dropdown-divider">
              </li>
              <li class="dropdown-item" @click="movePage(category.no)"
                  v-for="(category, index) in categoryList"
                  :key="index"
                  style="cursor:pointer;"
              >
                {{category.category}}
              </li>
            </ul>
          </li>
          <li class="nav-item">
            <a class="nav-link" id="sale-menu" aria-current="page" href="/sale">SALE</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/best">BEST</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/all-reviews">REVIEW</a>
          </li>

          <li class="nav-item dropdown outer-list">
            <a class="nav-link active" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown"
               aria-expanded="false">
              ADMIN PAGE
            </a>
            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
              <li><a class="dropdown-item" href="/add-product">글쓰기</a></li>
              <li><a class="dropdown-item" href="/admin-page">상품 관리</a></li>
            </ul>
          </li>
          <li class="nav-item dropdown outer-list">
            <router-link to="/adminboard">ADMIN Board</router-link>
          </li>
          <li class="nav-item dropdown outer-list">
            <router-link to="/userboard">user Board</router-link>
          </li>
        </ul>
        <form class="d-flex nav-form">
          <input class="search form-control me-1" type="search" placeholder="Search" aria-label="Search">
          <button type="button" class="btn position-relative search-icon" onclick="location.href='/search'">
            <i class="fa fa-search"></i>
          </button>
          <button type="button" class="btn position-relative cart-icon" onclick="location.href='/mypage'">
            <i class="fa fa-shopping-cart"></i>
            <span class="position-absolute top-0 start-100 translate-middle badge bg-primary cart-count">5</span>
          </button>
          <li class="nav-item2 dropdown outer-list">
            <a class="nav-link active" href="#" id="navbarDropdown2" role="button" data-bs-toggle="dropdown"
               aria-expanded="false">
              <i class="fa-solid fa-user-large mx-2"></i>
            </a>
            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
              <li><a class="dropdown-item" href="/login" v-if = "!currentUser">로그인</a></li>
              <li><a class="dropdown-item" href="/login" @click.prevent = "logOut" v-if = "currentUser">로그아웃</a></li>
              <li><a class="dropdown-item" href="/signup" v-if = "!currentUser">회원가입</a></li>
              <li><a class="dropdown-item" href="/mypage">마이페이지</a></li>
              <li><a class="dropdown-item" href="/faqs">FAQ</a></li>
            </ul>
          </li>
        </form>
      </div>
    </div>
  </nav>
</template>

<script>

export default {
  name: "NavCom",
  data() {
    return {
      categoryList: []
    }
  },
  computed: {
    currentUser() {
      // 공유저장소의 전역변수(공유변수 : user)
      // 자동으로 로그인 되었으면 loggedIn = true, user 객체 있음
      // 아닐 ㅣ시 loggedIn = false, user 객체 = null
      return this.$store.state.auth.user;
      // return true
    },
  },
  methods: {
    logOut() {
      // dispatch (호출하는 메소드) : actions에 있는 메소드를 호출
      this.$store.dispatch("auth/logout");
      // 로그아웃 후 이동할 페이지 지정 : login 페이지
      this.$router.push("/login");
    },
    movePage(id) {
      this.$router.push("/category/" + id);
    }
  },
  mounted() {
  },
};
</script>

<style scoped>
@import "@/assets/css/custom.css";
</style>