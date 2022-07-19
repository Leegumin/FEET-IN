<template>
  <nav class = "navbar navbar-expand-lg fixed-top">
    <div class = "container-fluid">
      <a class = "navbar-brand d-flex justify-content-between align-items-center order-lg-0"
         href = "/"
      >
        <!--            <img src="https://via.placeholder.com/120x60" alt="Logo">-->
        <!--            <img src="#" alt="Logo">-->
        <span class = "text-uppercase fw-lighter ms-2 logo-title">BRAND LOGO</span>
      </a>
      <button class = "navbar-toggler"
              type = "button"
              data-bs-toggle = "collapse"
              data-bs-target = "#navbarSupportedContent"
              aria-controls = "navbarSupportedContent"
              aria-expanded = "false"
              aria-label = "Toggle navigation"
      >
        <i class = "fa-solid fa-align-justify mx-1"></i>
      </button>
      <div class = "collapse navbar-collapse"
           id = "navbarSupportedContent"
      >
        <ul class = "navbar-nav me-auto mb-2 mb-lg-0">
          <li class = "nav-item dropdown outer-list">
            <a class = "nav-link active"
               href = "#"
               id = "navbarDropdown"
               role = "button"
               data-bs-toggle = "dropdown"
               aria-expanded = "false"
            >
              <!--                        <i class="fa-solid fa-align-justify mx-2"></i>SHOES-->
              SHOES
            </a>
            <ul class = "dropdown-menu"
                aria-labelledby = "navbarDropdown"
            >
              <li class = "inner-list"><a class = "dropdown-item"
                                          href = "/products"
              ><i class = "fa-solid fa-border-all"></i> 전체상품</a>
              </li>
              <li>
                <hr class = "dropdown-divider">
              </li>
              <li>
                <router-link to = "/category/100"
                             class = "dropdown-item"
                >러닝화
                </router-link>
              </li>
              <li>
                <router-link to = "/category/200"
                             class = "dropdown-item"
                >캐주얼
                </router-link>
              </li>
              <li>
                <router-link to = "/category/300"
                             class = "dropdown-item"
                >슬리퍼
                </router-link>
              </li>
              <li>
                <router-link to = "/category/400"
                             class = "dropdown-item"
                >기타
                </router-link>
              </li>
            </ul>
          </li>
          <li class = "nav-item">
            <a class = "nav-link"
               id = "sale-menu"
               aria-current = "page"
               href = "/sale"
            >SALE</a>
          </li>
          <li class = "nav-item">
            <a class = "nav-link"
               href = "/all-reviews"
            >REVIEW</a>
          </li>

          <li class = "nav-item dropdown outer-list"
          v-if="roleCheck">
            <a class = "nav-link active"
               href = "#"
               id = "navbarDropdown"
               role = "button"
               data-bs-toggle = "dropdown"
               aria-expanded = "false"
            >
              ADMIN PAGE
            </a>
            <ul class = "dropdown-menu"
                aria-labelledby = "navbarDropdown"
            >
              <li><a class = "dropdown-item"
                     href = "/add-product"
              >상품 등록</a></li>
              <li><a class = "dropdown-item"
                     href = "/admin-page"
              >상품 관리</a></li>
            </ul>
          </li>
        </ul>
        <form class = "d-flex nav-form">
          <input class = "search form-control me-1"
                 type = "search"
                 placeholder = "Search"
                 aria-label = "Search"
          >
          <button type = "button"
                  class = "btn position-relative search-icon"
                  onclick = "location.href='/search'"
          >
            <i class = "fa fa-search"></i>
          </button>
          <button type = "button"
                  class = "btn position-relative cart-icon"
                  onclick = "location.href='/mypage'"
          >
            <i class = "fa fa-shopping-cart"></i>
            <span class = "position-absolute top-0 start-100 translate-middle badge bg-primary cart-count">{{ carts.length }}</span>
          </button>
          <li class = "nav-item2 dropdown outer-list">
            <a class = "nav-link active"
               href = "#"
               id = "navbarDropdown2"
               role = "button"
               data-bs-toggle = "dropdown"
               aria-expanded = "false"
            >
              <i class = "fa-solid fa-user-large mx-2"></i>
            </a>
            <ul class = "dropdown-menu"
                aria-labelledby = "navbarDropdown"
            >
              <li><a class = "dropdown-item"
                     href = "/login"
                     v-if = "!currentUser"
              >로그인</a></li>
              <li><a class = "dropdown-item"
                     href = "/login"
                     @click.prevent = "logOut"
                     v-if = "currentUser"
              >로그아웃</a></li>
              <li><a class = "dropdown-item"
                     href = "/signup"
                     v-if = "!currentUser"
              >회원가입</a></li>
              <li><a class = "dropdown-item"
                     href = "/mypage"
              >마이페이지</a></li>
              <li><a class = "dropdown-item"
                     href = "/faqs"
              >FAQ</a></li>
            </ul>
          </li>
        </form>
      </div>
    </div>
  </nav>
</template>

<script>
import CartSerivce from '@/services/cart/CartDataService'

export default {
  name: 'NavCom',
  data () {
    return {
      carts: 0,
    }
  },
  computed: {
    currentUser () {
      // 공유저장소의 전역변수(공유변수 : user)
      // 자동으로 로그인 되었으면 loggedIn = true, user 객체 있음
      // 아닐 ㅣ시 loggedIn = false, user 객체 = null
      return this.$store.state.auth.user
      // return true
    },
    roleCheck () {
      if (this.currentUser && this.currentUser.roles) {
        return this.currentUser.roles.includes('ROLE_ADMIN');
      }
      return false
    },
  },
  methods : {
    logOut () {
      // dispatch (호출하는 메소드) : actions에 있는 메소드를 호출
      this.$store.dispatch('auth/logout')
      // 로그아웃 후 이동할 페이지 지정 : login 페이지
      this.$router.push('/login')
    },
    getRequestParams (id, page, pageSize) {
      let params = {}

      if (id) {
        params['id'] = id
      }

      if (page) {
        params['page'] = page - 1
      }

      if (pageSize) {
        params['size'] = pageSize
      }
      return params
    },
    handlePageChange (value) {
      // 현재 페이지 변경
      this.page = value
      // 재조회
      this.retrieveCart()
    },
    // 페이지 사이즈(3,6,9) 변경 시 호출되는 메소드
    handlePageSizeChange (event) {
      this.pageSize = event.target.value //select 박스의 값 (3,6,9)
      this.page = 1
      // 재조회
      this.retrieveCart()
    }, // 페이징 메소드 끝

    // 리뷰 불러오는 메소드
    retrieveCart () {
      // Todo : getRequestParams 호출 추가
      const params = this.getRequestParams(this.id, this.page, this.pageSize)
      CartSerivce.getCartDetail(params).then((response) => {
        this.carts = response.data
        this.count = this.carts[0].totalItems
      })
    },
  },
  mounted () {
    this.retrieveCart()
  },
}
</script>

<style scoped>
@import "@/assets/css/custom.css";
</style>