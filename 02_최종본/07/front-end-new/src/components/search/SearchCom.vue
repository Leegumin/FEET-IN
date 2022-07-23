<template>
  <div class="container search-container">
    <!--    <div class = "section-title mb-3 mb-sm-4 mt-5 text-center all-title" id="all-title">All Products</div>-->
    <div class="search-title text-center mt-5">
      <h3 class="position-relative d-inline-block">검색 결과</h3>
      <br>
      <h2 class="position-relative d-inline-block">Search Result</h2>
    </div>
    <section class="py-0">
      <div class="container px-4 px-lg-5 mt-5 search-list-container">
        <div class="search-result" v-if="searchResult">
          총 <strong>{{this.count}}</strong>건의 검색결과가 있습니다.
        </div>
        <div v-else>
          <div class="search-result">'{{this.searchTitle}}'에 대한 검색결과가 없습니다.</div>
          <!-- * recommend -->
          <div class="container rec-container">
            <div v-if = "currentUser != null" class = "text-center" style = "font-size: 15px">
              <strong>{{ currentUser.username }}</strong>님, 이런 상품들은 어떠신가요?
            </div>
            <div v-else class = "text-center" style = "font-size: 15px">
              <strong>방문자</strong>님, 이런 상품들은 어떠신가요?
            </div>
            <div class="rec-wrapper mt-4">
              <div class="rec-img" v-for="(rec,index) in recommendProducts" :key="index">
                <img :src="rec.url">
                <div class="caption" @click="onclick(rec.id)">
                  <p id="rec-item-title">{{ rec.title }}</p>
                  <p>{{(rec.price - rec.discount).toLocaleString() + "원"}}</p>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="collection-list row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 mt-5 justify-content-center">
          <!-- 검색 상품 목록 -->
          <div class="col mb-5" v-for="(product, index) in searchResults" :key="index">
            <div class="card" >
              <!-- Sale badge-->
              <div v-if="(product.saleYn == 'Y')"
                   class="badge position-absolute sale-badge"
                   style="top: 0.5rem; right: 0.5rem"
              >
                Sale
              </div>
              <!-- Product image-->
              <img
                  class="img-fluid card-img-top"
                  :src="product.url"
                  alt="..."
                  height="270"
              />
              <!-- Product details-->
              <div class="card-body p-4">
                <div class="text-center">
                  <!-- Product name-->
                  <h5 class="fw-bolder">{{product.title}}</h5>
                  <!-- Product price-->
                  <div v-if="product.saleYn == 'Y'">
                    <span class="text-muted text-decoration-line-through">
                      {{product.price}}
                    </span>
                    {{product.price - product.discount}}
                  </div>
                  <div v-else>
                    {{product.price}}
                  </div>
                </div>
              </div>
              <!-- Product actions-->
              <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                <div class="text-center">
                  <a class="btn mt-auto detail-btn" @click="onclick(product.id)"> 상품상세</a>
                </div>
              </div>
            </div>
          </div>
          <!-- 검색 상품 목록 -->
        </div>
      </div>
    </section>

  </div>
</template>

<script>
import search from '@/assets/js/search'
import ProductService from '@/services/product/ProductService';

export default {
  name: "SearchCom",
  data() {
    return {
      searchResult: false,
      searchResults: [],
      recommendProducts: [],
      searchTitle: this.$route.params.search,
      page: 1,
      count: 0,
      pageSize: 9,
    }
  },
  methods: {
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
    getSearchProduct() {
      const params = this.getRequestParams(
          this.searchTitle,
          this.page,
          this.pageSize
      );
      ProductService.getFiles(params)
          .then(res => {
            this.searchResults = res.data;
            this.count = this.searchResults[0].totalItems;
            this.searchResult = true;
          })
          .catch(() => {
            this.retrieveRecommendProducts();
          })
    },
    retrieveRecommendProductsImg() {
      ProductService.getRandomImg()
          // 성공하면 then에 들어옴(객체, 응답메세지)
          .then((response) => {
            // response.data : 서버쪽에서 전송된 객체
            this.recommendProducts = response.data;
            console.log(this.recommendProducts);
          });
    },
    onclick(id) {
      this.$router.push("/products/" + id);
    },
  },
  mounted() {
    search();
    this.getSearchProduct();
    this.retrieveRecommendProductsImg();
  },
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    }
  },
  watch: {
    '$route' (to, from) {
      if(to.path != from.path) {
        this.$router.go();
      }
    }
  }
}
</script>

<style scoped>
@import "@/assets/css/search.css";
</style>