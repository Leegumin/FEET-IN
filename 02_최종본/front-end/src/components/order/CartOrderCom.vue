<template>
  <div class="container mt-5 order-container">
    <div class="all-title text-center mt-5 title-wrapper">
      <h1 class="position-relative d-inline-block" id="cart-order-title">주문하기</h1>
    </div>
      <span class="section-title mb-3 mb-sm-4 sub-title">구매자 정보</span>
      <table class="table mt-3 cart-table">
        <thead class="mp-tr-header">
        <tr>
          <th scope="col" class="pb-3 pt-3">구매자</th>
          <th scope="col" class="pb-3 pt-3">휴대전화</th>
          <th scope="col" class="pb-3 pt-3">배송메모</th>
        </tr>
        </thead>
        <tbody>
        <tr>
          <td class="td-center">{{ currentUser.name }}</td>
          <td class="td-center">{{ currentUser.phone }}</td>
          <td class="td-center">
            <select>
              <option value="1">배송 전 연락바랍니다.</option>
              <option value="2">문 앞에 놔두고 가주세요.</option>
              <option value="3">기타</option>
            </select>
          </td>
        </tr>
        </tbody>
      </table>
      <div class="tb-margin"></div>

      <span class="section-title mb-3 mb-sm-4 sub-title">주문상품 정보</span>
    <table class = "table mt-3 cart-table">
      <thead class="mp-tr-header">
      <tr>
        <th scope = "col" class="pb-3 pt-3">상품명</th>
        <th scope = "col" class="pb-3 pt-3">상품코드</th>
        <th scope = "col" class="pb-3 pt-3">사이즈</th>
        <th scope = "col" class="pb-3 pt-3">수량</th>
        <th scope = "col" class="pb-3 pt-3">가격</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(cart, index) in carts" :key="index">
        <td>
          <img :src="cart.url"
               width="100"
               height="100"
               alt="product"
               style="margin-right: 20px"
          >
          {{ cart.title }}
        </td>
        <td class = "td-center-product">{{ cart.model }}</td>
        <td class = "td-center-product">{{ cart.shoseSize }}</td>
        <td class = "td-center-product">
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
        <td class = "td-center-product">{{ cart.finalPrice.toLocaleString() + "원" }}</td>
      </tr>
      </tbody>
      <tr v-if="carts.length==0">
        <td colspan="6" class="text-center pt-3 pb-3" style="border-bottom: 1px solid #e3e3e8;">장바구니가 비어있습니다.</td>
      </tr>
    </table>
    <!-- (주문상품)페이지네이션 -->
    <div class="paging">
<!--      <b-pagination-->
<!--          v-model="page"-->
<!--          :total-rows="count"-->
<!--          :per-page="pageSize"-->
<!--          @change="handlePageChange"-->
<!--      >-->
<!--      </b-pagination-->
<!--      >&lt;!&ndash; 페이징 &ndash;&gt;-->
    </div>
    <div class="text-end mb-5 sum">
      <strong>합계 : {{ sumcart.toLocaleString() + "원" }}</strong>
    </div>
      <div class="tb-margin"></div>

      <span class="section-title mb-3 mb-sm-4 sub-title">배송 정보</span>
      <table class="table mt-3 delivery-table">
        <thead class="mp-tr-header">
        <tr>
          <th scope="col" colspan="2" style="text-align: center" class="pb-3 pt-3">배송지 정보</th>
        </tr>
        </thead>
        <tbody>
        <tr>
          <th class="delivery-th">배송 받는 분 성함 :</th>
          <td class="delivery-td">{{ currentUser.name }}</td>
        </tr>
        <tr>
          <th class="delivery-th">배송 받을 주소 :</th>
          <td class="delivery-td">{{ currentUserAddress[1] }} {{ currentUserAddress[3] }}</td>
        </tr>
        <tr>
          <th class="delivery-th">휴대폰 번호 :</th>
          <td class="delivery-td">{{ currentUser.phone }}</td>
        </tr>
        </tbody>
      </table>

      <div>
        <div>
          <button @click="PaymentBtn" class="btn btn-primary" id="order-btn">
            결제하기
          </button>
        </div>
      </div>
      <div class="tb-margin-btn"></div>
  </div>
</template>

<script>
import mypage from "@/assets/js/mypage";
import CartSerivce from "@/services/cart/CartDataService";
// import OrderDataService from "@/services/order/OrderDataService";
const { IMP } = window;

export default {
  name: "MyPageCom",
  data() {
    return {
      carts: [],
      userId: this.$store.state.auth.user.id,
      showModal: false,
      sumcart: 0,
      sumQuantity: 0,
      page: 1,
      impCode: "imp62621327",
      message: "",
      count: 0,
      pageSize: 9999,
      pageSizes: [3, 6, 9],
    };
  },
  created(){
    document.cookie = "safeCookie1=foo; SameSite=Lax";
    document.cookie = "safeCookie2=foo";
    document.cookie = "crossCookie=bar; SameSite=None; Secure";
  },
  methods: {
    getRequestParams(id, userId, page, pageSize) {
      let params = {};

      if (userId) {
        params["userId"] = userId;
      }

      if (id) {
        params["id"] = id;
      }

      if (page) {
        params["page"] = page - 1;
      }

      if (pageSize) {
        params["size"] = pageSize;
      }
      return params;
    },
    updateCart(id, cart) {
      CartSerivce
          // .update(this.cart.id, this.cart )
          .updateCart(id, cart)
          .then(() => {
            alert("수정 완료");
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
      const params = this.getRequestParams(this.id, this.userId, this.page, this.pageSize);
      CartSerivce.getCartDetail(params).then((response) => {
        this.carts = response.data;
        this.count = this.carts[0].totalItems;
        this.sumcart = this.carts.reduce(
            (acc, cur) => acc + cur.quantity * cur.finalPrice,
            0
        );
        this.sumQuantity = this.carts.reduce(
            (acc, cur) => acc + cur.quantity,
            0
        );
      });
    },

    deleteCart(id) {
      CartSerivce.delete(id)
          .then(() => {
            this.retrieveCart();
          })
          .catch((e) => {
            alert(e);
          });
    },
    PaymentBtn(){
      IMP.init("imp62621327");
      IMP.request_pay({
        pg: "kakao",
        pay_method: "card",
        merchant_uid: 'merchant_' + new Date().getTime(),
        name: "TEST",
        amount: this.sumcart,
        buyer_name: this.$store.state.auth.user.name,
      }, rsp => {
        console.log(rsp);
        if (rsp.success) {
          console.log("결제 성공");
          var data = {
            // userId: this.$store.state.auth.user.id,
            // orderName: this.$store.state.auth.user.name,
            // address: this.$store.state.auth.user.address,
            // tel: this.$store.state.auth.user.phone,
            userId: this.currentUser.id,
            orderName: this.currentUser.name,
            address: this.currentUser.address,
            tel: this.currentUser.phone
          };

          CartSerivce.insertCart(data)
              .then((response) => {
                this.carts.id = response.data.id;
                alert("구매가 완료되었습니다.");
                this.$router.push("/mypage");
              })
              .catch((e) => {
                console.log(e);
              });
        } else {
          console.log("결제 실패");
        }
      });
    }
  },
  // ?수정 : 이구민
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
    currentUserAddress() {
      return this.currentUser.address.split("/");
    },
  },
  // ?수정 : 이구민
  // 화면이 뜨자마자 실행되는 이벤트
  mounted() {
    mypage();
    this.retrieveCart();

    var IMP = window.IMP;
    IMP.init("imp62621327");

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
@import "@/assets/css/order.css";
</style>
