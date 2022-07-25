<template>
  <div class="container mt-5 order-detail-container">
    <div class="all-title text-center mt-5 title-wrapper">
      <h1 class="position-relative d-inline-block" id="order-title">주문 상세내역</h1>
    </div>
    <div>
      <span class="section-title mb-3 mb-sm-4 sub-title">상세 내역</span>
      <!-- wish list -->
      <div class="box mt-3">
        <div id="tab">
          <table class="table">
            <thead class="mp-tr-header">
            <tr>
              <th scope="col" class="pb-3 pt-3">주문번호</th>
              <th scope="col" class="pb-3 pt-3">주문일자</th>
              <th scope="col" class="pb-3 pt-3">주문내역</th>
              <th scope="col" class="pb-3 pt-3">주문금액</th>
              <th scope="col" class="pb-3 pt-3">주문상태</th>
            </tr>
            </thead>
            <tbody class="align-middle text-center">
            <tr>
              <td scope="row">{{ order.id }}</td>
              <td>{{ order.orderTime }}</td>
              <td>
                <img :src="order.url" style="height:100px;width:100px;" class="me-2">
                <small class="text-muted">{{ order.model }}</small>
                {{ order.title }}
              </td>
              <td>
                {{ (order.price * order.quantity).toLocaleString() }}원 <br/>
                <small class="text-muted">{{ order.quantity }}개</small>
              </td>
              <td>
                입금완료
              </td>
            </tr>
            </tbody>
          </table>
          <div class="tb-margin"></div>

          <span class="section-title mb-3 mb-sm-4 sub-title">구매자 정보</span>
          <table class="table mt-3 delivery-table">
            <thead class="mp-tr-header">
            <tr>
              <th scope="col" colspan="2" style="text-align: center" class="pb-3 pt-3">구매자 정보</th>
            </tr>
            </thead>
            <tbody>
            <tr>
              <th class="delivery-th">구매자 :</th>
              <td class="delivery-td">{{ order.orderName }}</td>
            </tr>
            <tr>
              <th class="delivery-th">주소 :</th>
              <td class="delivery-td">{{ order.address }}</td>
            </tr>
            <tr>
              <th class="delivery-th">휴대폰 번호 :</th>
              <td class="delivery-td">{{ order.tel }}</td>
            </tr>
            </tbody>
          </table>
          <div class="tb-margin"></div>

          <span class="section-title mb-3 mb-sm-4 sub-title">구매 정보</span>
          <table class="table mt-3 delivery-table">
            <thead class="mp-tr-header">
            <tr>
              <th scope="col" colspan="2" style="text-align: center" class="pb-3 pt-3">구매 정보</th>
            </tr>
            </thead>
            <tbody>
            <tr>
              <th class="delivery-th">주문시간 :</th>
              <td class="delivery-td">{{ order.orderTime }}</td>
            </tr>
            <tr>
              <th class="delivery-th">상품명(모델명) :</th>
              <td class="delivery-td">{{ order.title }}({{ order.title }})</td>
            </tr>
            <tr>
              <th class="delivery-th">사이즈 :</th>
              <td class="delivery-td">{{ order.shoseSize }}</td>
            </tr>
            <tr>
              <th class="delivery-th">개수 :</th>
              <td class="delivery-td">{{ order.quantity }}</td>
            </tr>
            <tr>
              <th class="delivery-th">가격 :</th>
              <td class="delivery-td">{{ order.price.toLocaleString() }}원</td>
            </tr>
            <tr>
              <th class="delivery-th">할인 :</th>
              <td class="delivery-td">{{ order.discount }}%</td>
            </tr>
            <tr>
              <th class="delivery-th">결제가격 :</th>
              <td class="delivery-td">{{ ((order.price - order.discount) * order.quantity).toLocaleString() }}원</td>
            </tr>
            </tbody>
          </table>
          <div class="tb-margin"></div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import mypage from "@/assets/js/mypage";
import OrderService from "@/services/order/OrderDataService"

export default {
  name: "MyPageCom",
  data() {
    return {
      carts: [],
      userId: this.$store.state.auth.user.id,
      showModal: false,
      order: [],
      orderId: this.$route.params.id,
      page: 1,
      sumcart: 0,
      message: "",
      count: 0,
      pageSize: 3,
      pageSizes: [3, 6, 9],
    };
  },
  methods: {
    getOrderList() {
      OrderService.getListDetail(this.orderId)
          .then((res) => {
            this.order = res.data;
          })
          .catch((e) => {
            alert(e);
          })
    },

  },
  // ?수정 : 이구민
  // ?수정 : 이구민
  // 화면이 뜨자마자 실행되는 이벤트
  mounted() {
    mypage();
    this.getOrderList();

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
@import "@/assets/css/order2.css";
</style>
