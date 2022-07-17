<template>
  <div class="container all-container">
    <div class="all-title text-center mt-5">
      <h3 class="position-relative d-inline-block">상품 관리</h3>
      <br>
      <h2 class="position-relative d-inline-block">Product Management</h2>
    </div>
    <section class="py-0">
      <div class="container px-4 px-lg-5 mt-5 all-list-container">
        <div class="collection-list row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 mt-5 justify-content-center">
          <div class="col mb-5" v-for="(product, index) in products" :key="index">
            <div class="card h-100">
              <!-- Sale badge-->
              <div v-if="(product.saleYn == 'Y')" class="badge position-absolute sale-badge" style="top: 0.5rem; right: 0.5rem">
                Sale
              </div>
              <!-- fav icon -->
<!--              <div v-if="(product.saleYn == 'Y')" class="badge position-absolute fav-icon" style="top: 0.5rem; right: 0.5rem">-->
<!--                <i class="fa-solid fa-heart fa-3x"></i>-->
<!--              </div>-->
              <!-- Product image-->
              <img class="img-fluid card-img-top" :src="product.url" alt="..." height="270"/>
              <!-- Product details-->
              <div class="card-body p-4">
                <div class="text-center">
                  <!-- Product name-->
                  <h5 class="fw-bolder">{{product.title}}</h5>
                  <!-- Product price-->
                  <div v-if="product.saleYn == 'Y'">
                    <span class="text-muted text-decoration-line-through">
                      {{product.price.toLocaleString() + "원"}}
                    </span>
                    <br/>
                    {{(product.price - product.discount).toLocaleString() + "원"}}
                  </div>
                  <div v-else>
                    {{product.price.toLocaleString() + "원"}}
                  </div>
                </div>
              </div>
              <!-- Product actions-->
              <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                <div class="text-center">
                  <router-link :to="'/product/' + product.id" class="bbtn mt-auto btn btn-primary admin-btn me-1">
                    수정
                  </router-link>
                  <button class="btn mt-auto btn btn-danger admin-btn" @click="deleteProduct(product.id)">삭제</button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <b-pagination
        class="mt-2"
        align="center"
        v-model="page"
        :total-rows="count"
        :per-page="pageSize"
        @change="handlePageChange"
    >
    </b-pagination>
  </div>
</template>

<script>
import all from "@/assets/js/all";
import ProductService from "@/services/product/ProductService";

export default {
  name: "products-view",
  data() {
    return {
      bflag: false,
      products: [],
      // Todo: title => searchEmail 변경 수정
      searchTitle: "",
      // Todo:아래 변수 추가
      page: 1,
      count: 0,
      pageSize: 12,
      pageSizes: [12],
    };
  },
  methods: {
    // todo : getRequestParams 추가
    // 역할 : springboot로 매개변수를 전송
    // 웹 매개변수 : http:/locathost:8080/customers?page=1&pageSize=3
    getRequestParams(searchTitle, page, pageSize) {
      let params = {};
      // searchEmail값이 있으면 매개변수로 전송

      if (searchTitle) {
        params["title"] = searchTitle;
      }

      if (page) {
        params["page"] = page - 1;
      }

      if (pageSize) {
        params["size"] = pageSize;
      }
      return params;
    },
    onclick(id) {
      this.$router.push("/products/" + id);
    },
    // 모든 회원 조회 서비스 호출
    retrieveProducts() {
      // Todo : getRequestParams 호출 추가
      const params = this.getRequestParams(
        this.searchTitle,
        this.page,
        this.pageSize
      );
      ProductService.getFiles(params)
        // 성공하면 then에 들어옴(객체, 응답메세지)
        .then((response) => {
          // response.data : 서버쪽에서 전송된 객체
          this.products = response.data;
          this.count = this.products[0].totalItems;
          this.bflag = true;
        });
    },
    // todo : 신규 메소드 2개 추가
    // 현재 페이지를 다른 페이지번호로 변경 시 호출되는 메소드
    handlePageChange(value) {
      // 현재 페이지 변경
      this.page = value;
      // 재조회
      this.retrieveProducts();
    },
    // 페이지 사이즈(3,6,9) 변경 시 호출되는 메소드
    handlePageSizeChange(event) {
      this.pageSize = event.target.value; //select 박스의 값 (3,6,9)
      this.page = 1;
      // 재조회
      this.retrieveProducts();
    },
        deleteProduct(id) {
      if(confirm("정말 삭제 하시겠습니까?")) {
        ProductService.delete(id)
            .then(() => {
              this.retrieveProducts();
              alert("상품이 목록에서 삭제되었습니다.");
            })
            .catch((e) => {
              alert(e);
            });
      }
    },
  },
  //화면이 뜨자마자 실행되는 이벤트(모든 회원조회가 실행됨)
  mounted() {
    this.retrieveProducts();
    if(this.bflag == 'true') {
      all();
    }
  },
};
</script>

<style>
@import "@/assets/css/adminPage.css";
</style>
