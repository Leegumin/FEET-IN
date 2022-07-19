<template>
  <div class="container mt-5 order-container">
    <div class="all-title text-center mt-5">
      <h1 class="position-relative d-inline-block" id="order-title">주문하기</h1>
    </div>
    <div v-if="!submitted">
      <table class="table">
        <thead>
          <tr>
            <th scope="col">주문자</th>
            <th scope="col">휴대전화</th>
            <th scope="col">배송메모</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>{{ currentUser.name }}</td>
            <td>{{ currentUser.phone }}</td>
            <td>
              <select v-if="this.value == 1">
                <option value="1">오기 전 연락주세요</option>
                <option value="2">앞에 놔두고 가세요</option>
                <option value="3">기타</option>
              </select>
            </td>
          </tr>
        </tbody>
      </table>

<div class="container mt-5">
      <h2>주문 내역</h2>
      <table class="table" style="margin: 0">
        <tr>
          <th>상품명</th>
          <th>수량</th>
          <th>사이즈</th>
          <th>배송비</th>
          <th>가격</th>
        </tr>
        <tr>
          <td>
            <img style="width: 150px; height: 100px" :src="products.url" />
            {{ products.title }} , {{ products.model }}
          </td>
          <td>{{ quantity }}</td>
          <td>{{ shoseSize }}</td>
          <td>
            {{ deliverFee }}
          </td>
          <td>
            <div v-if="products.saleYn == 'Y'">
              <span class="text-muted text-decoration-line-through">
                {{ products.price }}
              </span>
              <br />
              {{ products.price - products.discount }}
            </div>
            <div v-else>
              {{ products.price }}
            </div>
          </td>
        </tr>
      </table>
      </div>
      <!-- 주문자 정보 -->
      <div class="container">
        <table class="table table-bordered">
          <caption class="caption caption-top" style="color:black; font-size:20px;">주문 정보 확인</caption>
        <tr class="mt-3">
           <th> 배송 받는 분 성함 : </th>
          <td> {{currentUser.name}}</td>
        </tr>
        <tr class="mt-3">
        <th>배송 받을 주소 : </th> 
        <td>  {{ currentUserAddress[1] }} {{ currentUserAddress[3] }}</td>
        </tr>
        <tr class="mt-3">
           <th>휴대폰 번호 :</th> 
           <td>{{currentUser.phone}}</td>
        </tr>
        </table>
      </div>

      <!-- 상품 결제 내역 -->
      <div style="margin-top:50px;">
        <table>
          <h2>결제 금액 확인</h2>

          <tr>
            <th>상품 금액</th>
            <td>{{ products.price }}</td>
          </tr>
          <tr>
            <th>할인 금액</th>
            <td>{{ products.discount }}</td>
          </tr>
          <tr>
            <th>배송비</th>
            <td>{{ deliveryFee }}</td>
          </tr>
          <tr>
            <th>최종 구매가</th>
            <td>{{ finalPrice }}</td>
          </tr>
        </table>
      </div>
      <div>
        <button @click="orderSave" class="btn btn-primary" href="#">
          결제하기
        </button>
      </div>
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

export default {
  name: "order-service",
  data() {
    return {
      products: [],
      id: this.$route.params.id,
      userId: this.$store.state.auth.user.id ,
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
    orderSave() {
      var data = {
        userId:this.$store.state.auth.user.id,
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
    },
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
