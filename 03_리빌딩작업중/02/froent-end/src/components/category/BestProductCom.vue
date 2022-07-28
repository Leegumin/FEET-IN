<template>
  <div class="container best-container">
    <!--    <div class = "section-title mb-3 mb-sm-4 mt-5 text-center all-title" id="all-title">All Products</div>-->
    <div class="best-title text-center mt-5">
      <h3 class="position-relative d-inline-block">인기 상품</h3>
      <br>
      <h2 class="position-relative d-inline-block">Best Products</h2>
    </div>
    <section class="py-0">
      <div class="container px-4 px-lg-5 mt-5 best-list-container">
        <div class="collection-list row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 mt-5 justify-content-center">
          <div class="col mb-5" v-for="(product, index) in products" :key="index">
            <div class="card h-100" >
              <div v-if="(product.saleYn == 'Y')" class="badge position-absolute sale-badge"
                   style="top: 0.5rem; right: 0.5rem" >
                Sale
              </div>
              <img class="img-fluid card-img-top" :src="product.url" alt="..." />
              <div class="card-body p-4">
                <div class="text-center">
                  <h5 class="fw-bolder">{{product.title}}</h5>
                  <div v-if="product.saleYn == 'Y'">
                    <span class="text-muted text-decoration-line-through">
                      {{product.price.toLocaleString()}}원
                    </span>
                    {{(product.price - product.discount)}}원
                  </div>
                  <div v-else>
                    {{product.price.toLocaleString()}}원
                    <br/>
                    <br/>
                  </div>
                </div>
              </div>
              <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                <div class="text-center">
                  <a class="btn mt-auto detail-btn" @click="onClick(product.id)">상세보기</a>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>

  </div>
</template>

<script>
import best from '@/assets/js/best'
import ProductService from "@/services/product/ProductService"

export default {
  name: "BestProductCom",
  data() {
    return {
      products: [],

      // 화면에 보일 상품 개수
      rowNum: 8,
    }
  },
  methods: {
    // 상품개수
    getRequestParams(rowNum) {
      let params = {};

      if (rowNum) {
        params["rowNum"] = rowNum;
      }

      return params;
    },

    // 상품 불러오기
    retrieveProducts() {
      const params = this.getRequestParams(
          this.rowNum
      );
      ProductService.getBest(params)
          .then((response) => {
            this.products = response.data;
            console.log(this.products);
          });
    },

    // 상품 상세페이지 이동
    onClick(id) {
      this.$router.push("/products/" + id);
    },
  },
  mounted() {
    this.retrieveProducts();
    best();
  }
}
</script>

<style scoped>
@import "@/assets/css/best.css";
</style>