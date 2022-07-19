<template>
  <div class="container mt-5 order-container">
    <div class="all-title text-center mt-5 title-wrapper">
      <h1 class="position-relative d-inline-block" id="order-title">주문하기</h1>
    </div>
    <div v-if="!submitted">
      <span class="section-title mb-3 mb-sm-4 sub-title">주문자 정보</span>
      <table class="table mt-3 cart-table">
        <thead class="mp-tr-header">
        <tr>
          <th scope="col" class="pb-3 pt-3">주문자</th>
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
      <table class="table mt-3 cart-table">
        <thead class="mp-tr-header">
        <tr>
          <th scope="col" class="pb-3 pt-3">상품명</th>
          <th scope="col" class="pb-3 pt-3">상품코드</th>
          <th scope="col" class="pb-3 pt-3">수량</th>
          <th scope="col" class="pb-3 pt-3">사이즈</th>
          <th scope="col" class="pb-3 pt-3">가격</th>
        </tr>
        </thead>
        <tbody>
        <tr>
          <td>
            <img :src="products.url"
                 width="100"
                 height="100"
                 alt="Logo"
                 style="margin-right: 20px"
            >
            {{ products.title }}
          </td>
          <td class="td-center-product">{{ products.model }}</td>
          <td class="td-center-product">{{ quantity }}</td>
          <td class="td-center-product">{{ shoseSize }}</td>
          <td class="td-center-product">
            <div v-if="products.saleYn == 'Y'">
              {{ (products.price - products.discount).toLocaleString() + "원" }}
            </div>
          </td>
        </tr>
        </tbody>
      </table>
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
      <div class="tb-margin"></div>

      <span class="section-title mb-3 mb-sm-4 sub-title">결제 정보</span>
      <table class="table mt-3 delivery-table">
        <thead class="mp-tr-header">
        <tr>
          <th scope="col" colspan="2" style="text-align: center" class="pb-3 pt-3">결제금액 확인</th>
        </tr>
        </thead>
        <tbody>
        <tr>
          <th class="delivery-th">상품 금액 :</th>
          <td class="delivery-td">{{ products.price.toLocaleString() + "원" }}</td>
        </tr>
        <tr>
          <th class="delivery-th">할인 금액 :</th>
          <td class="delivery-td">{{ products.discount.toLocaleString() + "원" }}</td>
        </tr>
        <tr>
          <th class="delivery-th">배송비 :</th>
          <td class="delivery-td">{{ deliveryFee.toLocaleString() + "원" }}</td>
        </tr>
        <tr>
          <th class="delivery-th">최종 결제금액 :</th>
          <td class="delivery-td" style="color: #028DFF;"><strong>{{ finalPrice.toLocaleString() + "원" }}</strong></td>
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

    <!--  -->
    <div style="margin-top:150px;" class="d-grid gap-2" v-else>
      <div class="alert alert-secondary" role="alert">상품구매가 완료되었습니다.</div>
      <button @click="onClick" class="btn btn-primary">더 많은 상품 구경하기</button>
      <a href="#" class="btn btn-primary">주문 내역 보기</a>
    </div>
    <!--  -->
  </div>
</template>

<script>
import ProductService from "@/services/product/ProductService";
import OrderDataService from "@/services/order/OrderDataService";
const { IMP } = window;

export default {
  name: "order-service",
  data() {
    return {
      products: [],
      id: this.$route.params.id,
      userId: this.$store.state.auth.user.id,
      productId: this.$route.params.id,
      quantity: this.$route.params.quantity,
      shoseSize: this.$route.params.shoseSize,
      deliverFee: 2500,
      price: 0,
      deliveryFee: 2500,
      finalPrice: 0,
      orderName: "",
      address: "",
      tel: "",
      submitted: false,
    };
  },
  methods: {
    getProduct(id) {
      console.log(this.$store.state.auth.user);
      ProductService.get(id)
          // 성공하면 then에 들어옴(객체, 응답메세지)
          .then((image) => {
            // response.data : 서버쪽에서 전송된 객체
            console.log(this.products);
            this.products = image.data;
            this.price = this.products.price - this.products.discount;
            this.finalPrice = this.price + this.deliverFee;
          });
    },
    PaymentBtn(){
      IMP.init("imp62621327");
      IMP.request_pay({
        pg: "kakao",
        pay_method: "card",
        merchant_uid: 'merchant_' + new Date().getTime(),
        name: "TEST",
        amount: this.finalPrice,
        buyer_name: this.$store.state.auth.user.name,
      }, rsp => {
        console.log(rsp);
        if (rsp.success) {
          console.log("결제 성공");
          var data = {
            userId: this.$store.state.auth.user.id,
            productId: this.$route.params.id,
            quantity: this.$route.params.quantity,
            shoseSize: this.$route.params.shoseSize,
            finalPrice: this.finalPrice,
            orderName: this.$store.state.auth.user.name,
            address: this.$store.state.auth.user.address,
            tel: this.$store.state.auth.user.phone,
          };

          OrderDataService.create(data)
              .then((response) => {
                this.products.id = response.data.id;
                this.submitted = true;
              })
              .catch((e) => {
                console.log(e);
              });
        }
      });
    },
    // orderSave() {
    //   var data = {
    //     userId: this.$store.state.auth.user.id,
    //     productId: this.$route.params.id,
    //     quantity: this.$route.params.quantity,
    //     shoseSize: this.$route.params.shoseSize,
    //     finalPrice: this.finalPrice,
    //     orderName: this.$store.state.auth.user.name,
    //     address: this.$store.state.auth.user.address,
    //     tel: this.$store.state.auth.user.phone,
    //   };

    //   OrderDataService.create(data)
    //       .then((response) => {
    //         this.products.id = response.data.id;
    //         this.submitted = true;
    //       })
    //       .catch((e) => {
    //         console.log(e);
    //       });
    // },
    onClick() {
      this.$router.push("/products")
    },
  },
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
    currentUserAddress() {
      return this.currentUser.address.split("/");
    },
  },

  mounted() {
    this.getProduct(this.$route.params.id);
  },
};
</script>

<style scoped>
@import "@/assets/css/order.css";
</style>
