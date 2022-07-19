<template>
  <div class="container sale-container">
    <!--    <div class = "section-title mb-3 mb-sm-4 mt-5 text-center all-title" id="all-title">All Products</div>-->
    <div class="sale-title text-center mt-5">
      <h3 class="position-relative d-inline-block">세일 중인 상품</h3>
      <br>
      <h2 class="position-relative d-inline-block">Products On Sale</h2>
    </div>
    <section class="py-0">
<!--      &lt;!&ndash; * tag 1 &ndash;&gt;-->
<!--      <div class="d-flex flex-wrap justify-content-center mt-3 mb-4 filter-button-group">-->
<!--        <button type="button" class="collection-button btn m-2 active-filter-btn" data-filter="*" id="all">-->
<!--          전체-->
<!--        </button>-->
<!--        <button type="button" class="collection-button btn m-2" data-filter=".like">찜한 상품</button>-->
<!--        <button type="button" class="collection-button btn m-2" data-filter=".rec">MD 추천</button>-->
<!--      </div>-->
<!--      &lt;!&ndash; * tag 2 &ndash;&gt;-->
<!--      <div class="d-flex flex-wrap justify-content-center mb-5 filter-button-group2">-->
<!--        <button class="collection-button2 me-2" data-filter="*"></button>-->
<!--        <button class="collection-button2 me-2" data-filter=".gray"></button>-->
<!--        <button class="collection-button2 me-2" data-filter=".red"></button>-->
<!--        <button class="collection-button2 me-2" data-filter=".yellow"></button>-->
<!--        <button class="collection-button2 me-2" data-filter=".green"></button>-->
<!--        <button class="collection-button2 me-2" data-filter=".blue"></button>-->
<!--        <button class="collection-button2 me-2" data-filter=".white"></button>-->
<!--        <button class="collection-button2 me-2" data-filter=".black"></button>-->
<!--      </div>-->
      <div class="container px-4 px-lg-5 mt-5 sale-list-container">
        <div class="collection-list row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 mt-5 justify-content-center">
          <div class="col mb-5" v-for="(product, index) in products" :key="index">
            <div class="card h-100">
              <div v-if="(product.saleYn == 'Y')"
                   class="badge position-absolute sale-badge" style="top: 0.5rem; right: 0.5rem">
                Sale
              </div>
              <!-- Product image-->
              <img class="img-fluid card-img-top" :src="product.url" alt="..."/>
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
                    <br/>
                    <br/>
                  </div>
                </div>
              </div>
              <!-- Product actions-->
              <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                <div class="text-center">
                  <a class="btn mt-auto detail-btn" @click="onclick(product.id)">
                  상품상세
                  </a>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <b-pagination
        class="mt-2"
        v-model="page"
        :total-rows="count"
        :per-page="pageSize"
        align="center"
        @change="handlePageChange"
    >
    </b-pagination>

  </div>
</template>

<script>
import sale from '@/assets/js/sale'
import sale2 from '@/assets/js/sale2'
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
    retrieveSaleProducts() {
      // Todo : getRequestParams 호출 추가
      const params = this.getRequestParams(
          this.searchTitle,
          this.page,
          this.pageSize
      );
      ProductService.getSale(params)
          // 성공하면 then에 들어옴(객체, 응답메세지)
          .then((response) => {
            // response.data : 서버쪽에서 전송된 객체
            this.products = response.data;
            console.log(this.products)
            this.count = this.products[0].totalItems;
            console.log(this.count)
            this.bflag = true;
          });
    },
    // todo : 신규 메소드 2개 추가
    // 현재 페이지를 다른 페이지번호로 변경 시 호출되는 메소드
    handlePageChange(value) {
      // 현재 페이지 변경
      this.page = value;
      // 재조회
      this.retrieveSaleProducts();
    },
    // 페이지 사이즈(3,6,9) 변경 시 호출되는 메소드
    handlePageSizeChange(event) {
      this.pageSize = event.target.value; //select 박스의 값 (3,6,9)
      this.page = 1;
      // 재조회
      this.retrieveSaleProducts();
    },
  },
  //화면이 뜨자마자 실행되는 이벤트(모든 회원조회가 실행됨)
  mounted() {
    this.retrieveSaleProducts();
    sale2();
    if(this.bflag==true) {
      sale();
    }
  },
};
</script>

<style scoped>
@import "@/assets/css/sale.css";
@import "@/assets/css/page.css";
</style>