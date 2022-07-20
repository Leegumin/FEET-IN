<template>
  <section class="bg-white mypage-wrapper">
    <!-- * modal -->
    <ModalEditCom v-if="showModal" @close="showModal = false"> </ModalEditCom>

    <div class="container mypage-container">
      <div class="row">
        <div class = "col-lg-12 mb-sm-5 mt-5 wish-container">
          <div>
            <span class = "section-title mb-3 mb-sm-4 sub-title">상세 내역</span>
            <!-- wish list -->
            <div class="box mt-3">
              <div id="tab">
                <table class="table">
                  <thead class="text-center">
                    <tr>
                      <th style="width: 15%" scope="col">주문번호</th>
                      <th style="width: 15%" scope="col">주문일자</th>
                      <th style="width: 35%" scope="col">주문내역</th>
                      <th style="width: 10%" scope="col">주문금액</th>
                      <th style="width: 15%" scope="col">주문상태</th>
                    </tr>
                  </thead>
                  <tbody class="align-middle text-center">
                    <tr>
                      <td scope="row">{{order.id}}</td>
                      <td>{{order.orderTime}}</td>
                      <td>
                        <img :src="order.url" style="height:80px;width:100px;">
                        <small class="text-muted">{{order.model}}</small>
                        {{order.title}}
                      </td>
                      <td>
                        {{order.price * order.quantity}}원 <br/>
                        <small class="text-muted">{{order.quantity}}개</small>
                      </td>
                      <td>
                        입금대기
                      </td>
                    </tr>
                  </tbody>
                </table>
                <div class="row">
                    <div class="col-6">
                        <h4>구매자 정보</h4>
                        <p>구매자 : {{currentUser.name}}</p>
                        <p>주소 : {{currentUser.address}}</p>
                        <p>휴대폰 : {{currentUser.phone}}</p>
                    </div>
                    <div class="col-6">
                        <h4>구매 정보</h4>
                        <div>
                            <p>주문시간 : {{order.orderTime}}</p>
                            <p>상품명 : {{order.title}}</p>
                            <p>사이즈 : {{order.shoseSize}}</p>
                            <p>개수 : {{order.quantity}}</p>
                            <div v-if="order.discount != null">
                              <p>
                                가격 : <span class="text-decoration-line-through">{{order.price}}</span>
                                      {{order.price - order.discount}}
                              </p>
                            </div>
                            <p>결제가격 : {{(order.price - order.discount) * order.quantity}}</p>
                        </div>
                    </div>
                </div>
              </div>
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
  components: {
    ModalEditCom,
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
@import "@/assets/css/mypage.css";
</style>
