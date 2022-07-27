<template>
  <section class="bg-white mypage-wrapper">

    <!-- * modal -->
    <ModalEditCom v-if="showModal" @close="showModal = false">
    </ModalEditCom>

    <div class="container mypage-container">
      <div class="row">
        <!-- * profile -->
        <div class="col-lg-12 mb-4 mb-sm-5 profile-wrap">
          <div class="card card-style1 border-0 profile-card">
            <div class="card-body p-1-9 p-sm-2-3 p-md-6 p-lg-7">
              <div class="row align-items-center">
                <div class="col-lg-3 mb-4 mb-lg-0 profile-icon">
                  <i class="fa-solid fa-user-large mx-2 fa-8x"></i>
                </div>
                <div class="col-lg-9 px-xl-10 profile-content">
                  <div class="bg-secondary d-lg-inline-block py-1-9 px-1-9 px-sm-6 mb-1-9 rounded">
                    <!--?수정---------- : 이구민-->
                    <h3 class="h2 text-white">{{ currentUser.name }}의 마이페이지</h3>
                    <span class="text-primary">{{ currentUser.username }}</span>
                  </div>
                  <ul class="list-unstyled">
                    <li class="mb-2 mb-xl-2 display-28"><span class="display-26">휴대폰 : </span>{{ currentUser.phone }}
                    </li>
                    <li class="mb-2 mb-xl-2 display-28"><span class="display-26">이메일 : </span>{{ currentUser.email }}
                    </li>
                    <li class="mb-2 mb-xl-2 display-28"><span class="display-26">우편번호 : </span>{{
                        currentUserAddress[0]
                      }}
                    </li>
                    <li class="mb-2 mb-xl-2 display-28"><span class="display-26">주소 : </span>{{ currentUserAddress[1] }}
                      {{ currentUserAddress[3] }}
                    </li>
                    <!--                    <li class = "mb-2 mb-xl-2 display-28"><span class = "display-26">지번주소 : </span>{{ currentUserAddress[2] }}</li>-->
                  </ul>
                  <!--                  <div class = "text-center"><router-link :to = "'/user-edit/' + currentUser.id" class = "btn profile-edit-btn"-->
                  <!--                  >-->
                  <!--                    Edit</router-link>-->
                  <!--                  </div>-->

                  <div class="text-center">
                    <a class="btn profile-edit-btn" id="show-modal" @click="showModal = true">수정</a>
                  </div>
                  <!--?수정---------- : 이구민-->
                </div>
              </div>
            </div>
          </div>
        </div>
        <!-- * cart -->
        <!-- 이미지-false처리 -->
        <div class="col-md-12" v-if="false">
          <div class="md-3">
            Items per Page :
            <select v-model="pageSize" @change="handlePageSizeChange($event)">
              <!-- pageSizes : [3,6,9] -->
              <option v-for="size in pageSizes" :key="size" :value="size">
                {{ size }}
              </option>
            </select>
          </div>
        </div>
        <div class="col-lg-12 mb-4 mb-sm-5 cart-container" id="cart-page">
          <div>
            <span class="section-title mb-3 mb-sm-4 sub-title">장바구니</span>
            <table class="table mt-3 cart-table">
              <thead class="mp-tr-header">
              <tr>
                <th scope="col" class="pb-3 pt-3">상품명</th>
                <th scope="col" class="pb-3 pt-3">상품코드</th>
                <th scope="col" class="pb-3 pt-3">사이즈</th>
                <th scope="col" class="pb-3 pt-3">수량</th>
                <th scope="col" class="pb-3 pt-3">가격</th>
                <th scope="col" class="pb-3 pt-3">삭제</th>
              </tr>
              </thead>
              <tbody>
              <tr v-for="(cart, index) in carts" :key="index">
                <td>
                  <img :src="cart.url"
                       width="100"
                       height="100"
                       style="margin-right: 20px"
                       alt="Logo"
                  >
                  {{ cart.title }}
                </td>
                <td class="td-center-mp">{{ cart.model }}</td>
                <td class="td-center-mp">{{ cart.shoseSize }}</td>
                <td class="td-center-mp">
                  <div class="input-group">
                    <input
                        class="form-control me-2"
                        id="quantity"
                        type="number"
                        min="1"
                        value="1"
                        v-model="cart.quantity"
                        aria-describedby="basic-addon2"
                    />
                    <div class="input-group-append">
                      <button
                          class="btn btn-outline-dark"
                          type="button"
                          @click="updateCart(cart.id, cart)"
                      >
                        변경
                      </button>
                    </div>
                  </div>
                </td>
                <td class="td-center-mp">{{ cart.finalPrice.toLocaleString() + "원" }}</td>
                <td class="td-center-mp">
                  <button
                      class="btn btn-outline-danger"
                      @click="deleteCart(cart.id)">삭제
                  </button>
                </td>
              </tr>
              </tbody>
              <tr v-if="carts.length==0">
                <td colspan="6" class="text-center pt-3 pb-3" style="border-bottom: 1px solid #e3e3e8;">장바구니가 비어있습니다.
                </td>
              </tr>
            </table>
            <!-- (주문상품)페이지네이션 -->
            <div class="paging">
              <b-pagination
                  v-model="page"
                  :total-rows="count"
                  :per-page="pageSize"
                  @change="handlePageChange"
              >
              </b-pagination
              ><!-- 페이징 -->
            </div>
            <div class="text-end mb-5 sum">
              <strong>합계 : {{ sumcart.toLocaleString() + "원" }}</strong>
            </div>


            <div class="alert alert-success" role="alert" v-if="message">
              {{ message }}
            </div>
          </div>
          <div class="buy-btn-wrapper"><a class="buy-btn"
                                          @click="OnOrder"
          >
            구매하기</a>
          </div>
        </div>
        <!-- * 구매내역 -->
        <div class="col-lg-12 mb-sm-5 mt-3 wish-container">
          <div>
            <span class="section-title mb-3 mb-sm-4 sub-title">구매 내역</span>
            <!-- wish list -->
            <!-- // order 추가 -->
            <div class="box mt-3">
              <table class="table">
                <thead class="mp-tr-header">
                <tr class="text-center">
                  <th scope="col" class="pb-3 pt-3">주문번호</th>
                  <th scope="col" class="pb-3 pt-3">주문일자</th>
                  <th scope="col" class="pb-3 pt-3">주문내역</th>
                  <th scope="col" class="pb-3 pt-3">주문금액</th>
                  <th scope="col" class="pb-3 pt-3">주문상태</th>
                  <th scope="col" class="pb-3 pt-3">삭제</th>
                </tr>
                </thead>
                <tbody class="align-middle text-center">
                <tr v-for="(order, index) in orders" :key="index">
                  <td scope="row">{{ order.id }}</td>
                  <td>{{ order.orderTime }}</td>
                  <td @click="showDetail(order.id)" class="text-start">
                    <img :src="order.url" style="height:100px;width:100px;">
                    {{ order.title }}
                    (<small class="text-muted">{{ order.model }}</small>)
                    <small>size : {{ order.shoseSize }}</small>
                  </td>
                  <td>
                    {{ order.price * order.quantity }}원 <br/>
                    <small class="text-muted">{{ order.quantity }}개</small>
                  </td>
                  <td>
                    입금완료(배송준비중)
                  </td>
                  <td>
                    <button class="btn btn-outline-danger" @click="deleteOrder(order.id)">삭제</button>
                  </td>
                </tr>
                <tr v-if="orders.length==0">
                  <td colspan="6" class="text-center pt-3 pb-3" style="border-bottom: 1px solid #e3e3e8;">구매내역이
                    없습니다.
                  </td>
                </tr>
                </tbody>
              </table>
              <b-pagination
                  v-model="orderPage"
                  :total-rows="orderCount"
                  :per-page="orderPageSize"
                  align="center"
                  @change="handleOrderPageChange"
              ></b-pagination>
            </div>
          </div>

        </div>


        <!-- * wish list -->
        <div class="col-lg-12 mb-sm-5 mt-3 wish-container">
          <div>
            <span class="section-title mb-3 mb-sm-4 sub-title">찜 목록</span>
            <!-- wish list -->
            <div v-if="!lists.length==0" class="box mt-3 wish-list-box">
              <div id="tab">
                <ul>
                  <li v-for="(list, index) in lists" :key="index">
                    <div class="col mb-5 like">
                      <div class="card h-100 product-card">
                        <div class="badge position-absolute fav-icon" style="top: 0.5rem; right: 0.5rem">
                          <i class="fa-solid fa-heart fa-3x"></i>
                        </div>
                        <!-- Product image-->
                        <img class="img-fluid card-img-top" :src="list.url"/>
                        <!-- Product details-->
                        <div class="card-body p-4">
                          <div class="text-center">
                            <!-- Product name-->
                            <h5 class="fw-bolder">{{ list.title }}</h5>
                            <!-- Product price-->
                            <div v-if="list.saleYn == 'Y'">
                              <span class="text-muted text-decoration-line-through">
                                {{ list.price.toLocaleString() + "원" }}
                              </span>
                              {{ (list.price - list.discount).toLocaleString() + "원" }}
                            </div>
                            <div v-else>
                              {{ list.price.toLocaleString() + "원" }}
                              <br/>
                              <br/>
                            </div>
                          </div>
                        </div>
                        <!-- Product actions-->
                        <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                          <div class="text-center">
                            <a class="btn mt-auto detail-btn" @click="onclick(list.id)"> 상품상세</a>
                          </div>
                        </div>
                      </div>
                    </div>
                  </li>
                </ul>
              </div>
            </div>
            <div v-else class="mt-3">
              <div class="text-center pt-3 pb-3" id="like-empty">찜한 상품이 없습니다.</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import mypage from "@/assets/js/mypage";
import ModalEditCom from "@/components/modal/ModalEditCom";
import CartSerivce from "@/services/cart/CartDataService";
import OrderService from "@/services/order/OrderDataService"
import LikeService from "@/services/like/LikeService"

export default {
  name: "MyPageCom",
  data() {
    return {
      userId: this.$store.state.auth.user.id,
      showModal: false,

      // order 추가
      orders: [],
      orderPage: 1,
      orderCount: 0,
      orderPageSize: 3,

      message: "",

      lists: [],

      carts: [],
      sumcart: 0,
      count: 0,
      page: 1,
      pageSize: 3,
      pageSizes: [3, 6, 9],
    };
  },
  components: {
    ModalEditCom,
  },
  methods: {
    // order 추가
    handleOrderPageChange(value) {
      this.orderPage = value;
      this.getOrderList();
    },

    orderParams(userId, orderPage, orderPageSize) {
      let params = {};

      if (userId) {
        params["userId"] = userId;
      }

      if (orderPage) {
        params["page"] = orderPage - 1;
      }

      if (orderPageSize) {
        params["size"] = orderPageSize;
      }

      return params;
    },

    getOrderList() {
      const params = this.orderParams(
          this.userId,
          this.orderPage,
          this.orderPageSize
      );
      OrderService.getLists(params)
          .then((res) => {
            this.orders = res.data;
            this.orderCount = this.orders[0].totalItems;
          })
    },

    deleteOrder(id) {
      if(confirm("정말 삭제하시겠습니까?")) {
        OrderService.delete(id)
            .then(() => {
              this.getOrderList();
            })
            .catch((e) => {
              alert(e);
            });
      }
    },

    // 카트
    getRequestParams(userId, page, pageSize) {
      let params = {};

      if (userId) {
        params["userId"] = userId;
      }

      if (page) {
        params["page"] = page - 1;
      }

      if (pageSize) {
        params["size"] = pageSize;
      }
      return params;
    },

    OnOrder() {
      this.$router.push("/cart-order");
    },

    updateCart(id, cart) {
      CartSerivce
          // .update(this.cart.id, this.cart )
          .updateCart(id, cart)
          .then(() => {
            alert("수량이 변경되었습니다.");
            this.retrieveCart();
          })
          .catch((e) => {
            alert(e);
          });
    },
    handlePageChange(value) {
      this.page = value;
      this.retrieveCart();
    },
    handlePageSizeChange(event) {
      this.pageSize = event.target.value;
      this.page = 1;
      this.retrieveCart();
    },

    // 장바구니 불러오기
    retrieveCart() {
      const params = this.getRequestParams(this.userId, this.page, this.pageSize);
      CartSerivce.getCartDetail(params).then((response) => {
        this.carts = response.data;
        this.count = this.carts[0].totalItems;
        this.sumcart = this.carts.reduce(
            (acc, cur) => acc + cur.quantity * cur.finalPrice,
            0
        );
      });
    },

    deleteCart(id) {
      if(confirm("정말 삭제하시겠습니까?")) {
        CartSerivce.delete(id)
            .then(() => {
              // this.$router.go();
              this.retrieveCart();
            })
            .catch((e) => {
              alert(e);
            });
      }
    },

    showDetail(id) {
      this.$router.push("/order-detail/" + id);
    },

    onclick(id) {
      this.$router.push("/products/" + id);
    },

    // 관심상품 메소드
    getLikeParams(userId, productId) {
      let params = {};

      if (userId) {
        params["userId"] = userId;
      }

      if (productId) {
        params["productId"] = productId;
      }

      return params;
    },

    getMyLike() {
      const params = this.getLikeParams(
          this.userId,
          this.productId
      )
      LikeService.getMyLike(params)
          .then((res) => {
            this.lists = res.data;
          })
    },
  },
  // ?수정 : 이구민
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
    currentUserAddress() {
      return this.currentUser.address.split("/");
    },
    // sumCart: function() {
    //   var n = this.carts.length;
    //   var sumCart = 0;
    //   for(let i = 0; i<n;i++) {
    //    sumCart+=this.carts.finalPrice
    //   }
    //   return sumCart;
    // }
  },
  // ?수정 : 이구민
  // 화면이 뜨자마자 실행되는 이벤트
  mounted() {
    mypage();
    this.retrieveCart();
    this.getOrderList();
    this.getMyLike();

    // ?수정 : 이구민
    /*// 사용자가 로그인하지 않은 경우 로그인 페이지로 강제 이동
    if (!this.currentUser) {
      // 강제 login 페이지로 이동시킴
      this.$router.push('/login')
    }*/
    // ?수정 : 이구민
  },
};
</script>

<style scoped>
@import "@/assets/css/mypage.css";
@import "@/assets/css/page.css";
</style>