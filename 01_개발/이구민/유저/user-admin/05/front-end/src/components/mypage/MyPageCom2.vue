<template>
  <section class = "bg-white mypage-wrapper">

    <!-- * modal -->
    <ModalEditCom v-if="showModal" @close="showModal = false">
    </ModalEditCom>

    <div class = "container mypage-container">
      <div class = "row">
        <!-- * profile -->
        <div class = "col-lg-12 mb-4 mb-sm-5 profile-wrap">
          <div class = "card card-style1 border-0 profile-card">
            <div class = "card-body p-1-9 p-sm-2-3 p-md-6 p-lg-7">
              <div class = "row align-items-center">
                <div class = "col-lg-3 mb-4 mb-lg-0 profile-icon">
                  <i class = "fa-solid fa-user-large mx-2 fa-8x"></i>
                </div>
                <div class = "col-lg-9 px-xl-10 profile-content">
                  <div class = "bg-secondary d-lg-inline-block py-1-9 px-1-9 px-sm-6 mb-1-9 rounded">
                    <!--?수정---------- : 이구민-->
                    <h3 class = "h2 text-white">{{ currentUser.name }}의 MyPage</h3>
                    <span class = "text-primary">{{ currentUser.username }}</span>
                  </div>
                  <ul class = "list-unstyled">
                    <li class = "mb-2 mb-xl-2 display-28"><span class = "display-26">Phone: </span>{{ currentUser.phone }}</li>
                    <li class = "mb-2 mb-xl-2 display-28"><span class = "display-26">Email: </span>{{ currentUser.email }}</li>
                    <li class = "mb-2 mb-xl-2 display-28"><span class = "display-26">Address: </span>{{ currentUserAddress[0] }}</li>
                    <li class = "mb-2 mb-xl-2 display-28"><span class = "display-26">Address: </span>{{ currentUserAddress[1] }}</li>
                    <li class = "mb-2 mb-xl-2 display-28"><span class = "display-26">Address: </span>{{ currentUserAddress[2] }}</li>
                    <li class = "mb-2 mb-xl-2 display-28"><span class = "display-26">Address: </span>{{ currentUserAddress[3] }}</li>
                  </ul>
<!--                  <div class = "text-center"><router-link :to = "'/user-edit/' + currentUser.id" class = "btn profile-edit-btn"-->
<!--                  >-->
<!--                    Edit</router-link>-->
<!--                  </div>-->

                  <div class = "text-center">
                    <a class ="btn profile-edit-btn" id="show-modal" @click="showModal = true">Edit</a>
                  </div>
                  <!--?수정---------- : 이구민-->
                </div>
              </div>
            </div>
          </div>
        </div>
        <!-- * cart -->
        <div class = "col-lg-12 mb-4 mb-sm-5 cart-container" id="cart-page">
          <div>
            <span class = "section-title mb-3 mb-sm-4 sub-title">Shopping Cart</span>
            <table class = "table mt-3 cart-table">
              <thead class="mp-tr-header">
              <tr>
                <th scope = "col" class="pb-3 pt-3">상품정보</th>
                <th scope = "col" class="pb-3 pt-3">옵션</th>
                <th scope = "col" class="pb-3 pt-3">수량</th>
                <th scope = "col" class="pb-3 pt-3">결제금액</th>
                <th scope = "col" class="pb-3 pt-3">삭제</th>
              </tr>
              </thead>
              <tbody>
              <tr>
                <td>
                  <img src = "https://via.placeholder.com/100x100"
                       alt = "Logo"
                  >
                  {product}
                </td>
                <td class = "td-center">{ooption}</td>
                <td class = "td-center">{quantity}</td>
                <td class = "td-center">{price}</td>
                <td class = "td-center">{delete}</td>
              </tr>
              </tbody>
            </table>
          </div>
          <div class = "text-center"><a class = "buy-btn"
                                        href = "#"
          >
            Buy</a>
          </div>
        </div>
        <!-- * wish list -->
        <div class = "col-lg-12 mb-sm-5 mt-5 wish-container">
          <div>
            <span class = "section-title mb-3 mb-sm-4 sub-title">Wish List</span>
            <!-- wish list -->
            <div class="box mt-3">
              <div id="tab">
                <ul>
                  <li>
                    <div class="col mb-5 like">
                      <div class="card h-100 product-card">
                        <div class="badge position-absolute fav-icon" style="top: 0.5rem; right: 0.5rem">
                          <i class="fa-solid fa-heart fa-3x"></i>
                        </div>
                        <!-- Product image-->
                        <img class="img-fluid card-img-top" src="@/assets/images/product/c1-1.jpeg" alt="..." height="250"/>
                        <!-- Product details-->
                        <div class="card-body p-4">
                          <div class="text-center">
                            <!-- Product name-->
                            <h5 class="fw-bolder">Fancy Product</h5>
                            <!-- Product price-->
                            $40.00 - $80.00
                          </div>
                        </div>
                        <!-- Product actions-->
                        <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                          <div class="text-center"><a class="btn mt-auto detail-btn" href="/detail">
                            상품상세</a>
                          </div>
                        </div>
                      </div>
                    </div>
                  </li>
                  <li>
                    <div class="col mb-5 like">
                      <div class="card h-100 product-card">
                        <div class="badge position-absolute fav-icon" style="top: 0.5rem; right: 0.5rem">
                          <i class="fa-solid fa-heart fa-3x"></i>
                        </div>
                        <!-- Product image-->
                        <img class="img-fluid card-img-top" src="@/assets/images/product/c1-1.jpeg" alt="..." height="250"/>
                        <!-- Product details-->
                        <div class="card-body p-4">
                          <div class="text-center">
                            <!-- Product name-->
                            <h5 class="fw-bolder">Fancy Product</h5>
                            <!-- Product price-->
                            $40.00 - $80.00
                          </div>
                        </div>
                        <!-- Product actions-->
                        <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                          <div class="text-center"><a class="btn mt-auto detail-btn" href="/detail">
                            상품상세</a>
                          </div>
                        </div>
                      </div>
                    </div>
                  </li>
                  <li>
                    <div class="col mb-5 like">
                      <div class="card h-100 product-card">
                        <div class="badge position-absolute fav-icon" style="top: 0.5rem; right: 0.5rem">
                          <i class="fa-solid fa-heart fa-3x"></i>
                        </div>
                        <!-- Product image-->
                        <img class="img-fluid card-img-top" src="@/assets/images/product/c1-1.jpeg" alt="..." height="250"/>
                        <!-- Product details-->
                        <div class="card-body p-4">
                          <div class="text-center">
                            <!-- Product name-->
                            <h5 class="fw-bolder">Fancy Product</h5>
                            <!-- Product price-->
                            $40.00 - $80.00
                          </div>
                        </div>
                        <!-- Product actions-->
                        <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                          <div class="text-center"><a class="btn mt-auto detail-btn" href="/detail">
                            상품상세</a>
                          </div>
                        </div>
                      </div>
                    </div>
                  </li>
                  <li>
                    <div class="col mb-5 like">
                      <div class="card h-100 product-card">
                        <div class="badge position-absolute fav-icon" style="top: 0.5rem; right: 0.5rem">
                          <i class="fa-solid fa-heart fa-3x"></i>
                        </div>
                        <!-- Product image-->
                        <img class="img-fluid card-img-top" src="@/assets/images/product/c1-1.jpeg" alt="..." height="250"/>
                        <!-- Product details-->
                        <div class="card-body p-4">
                          <div class="text-center">
                            <!-- Product name-->
                            <h5 class="fw-bolder">Fancy Product</h5>
                            <!-- Product price-->
                            $40.00 - $80.00
                          </div>
                        </div>
                        <!-- Product actions-->
                        <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                          <div class="text-center"><a class="btn mt-auto detail-btn" href="/detail">
                            상품상세</a>
                          </div>
                        </div>
                      </div>
                    </div>
                  </li>
                  <li>
                    <div class="col mb-5 like">
                      <div class="card h-100 product-card">
                        <div class="badge position-absolute fav-icon" style="top: 0.5rem; right: 0.5rem">
                          <i class="fa-solid fa-heart fa-3x"></i>
                        </div>
                        <!-- Product image-->
                        <img class="img-fluid card-img-top" src="@/assets/images/product/c1-1.jpeg" alt="..." height="250"/>
                        <!-- Product details-->
                        <div class="card-body p-4">
                          <div class="text-center">
                            <!-- Product name-->
                            <h5 class="fw-bolder">Fancy Product</h5>
                            <!-- Product price-->
                            $40.00 - $80.00
                          </div>
                        </div>
                        <!-- Product actions-->
                        <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                          <div class="text-center"><a class="btn mt-auto detail-btn" href="/detail">
                            상품상세</a>
                          </div>
                        </div>
                      </div>
                    </div>
                  </li>
                  <li>
                    <div class="col mb-5 like">
                      <div class="card h-100 product-card">
                        <div class="badge position-absolute fav-icon" style="top: 0.5rem; right: 0.5rem">
                          <i class="fa-solid fa-heart fa-3x"></i>
                        </div>
                        <!-- Product image-->
                        <img class="img-fluid card-img-top" src="@/assets/images/product/c1-1.jpeg" alt="..." height="250"/>
                        <!-- Product details-->
                        <div class="card-body p-4">
                          <div class="text-center">
                            <!-- Product name-->
                            <h5 class="fw-bolder">Fancy Product</h5>
                            <!-- Product price-->
                            $40.00 - $80.00
                          </div>
                        </div>
                        <!-- Product actions-->
                        <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                          <div class="text-center"><a class="btn mt-auto detail-btn" href="/detail">
                            상품상세</a>
                          </div>
                        </div>
                      </div>
                    </div>
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import mypage from '@/assets/js/mypage'
import ModalEditCom from "@/components/modal/ModalEditCom";

export default {
  name    : 'MyPageCom',
  data() {
    return {
      showModal: false
    }
  },
  components: {
    ModalEditCom
  },
  // ?수정 : 이구민
  computed: {
    currentUser () {
      return this.$store.state.auth.user
    },
    currentUserAddress () {
      return this.currentUser.address.split("/")
    }
  },
  // ?수정 : 이구민
  // 화면이 뜨자마자 실행되는 이벤트
  mounted () {
    mypage()

    // ?수정 : 이구민
    /*// 사용자가 로그인하지 않은 경우 로그인 페이지로 강제 이동
    if (!this.currentUser) {
      // 강제 login 페이지로 이동시킴
      this.$router.push('/login')
    }*/
    // ?수정 : 이구민
  },
}
</script>

<style scoped>
@import "@/assets/css/mypage.css";
</style>