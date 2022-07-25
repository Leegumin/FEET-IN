<template>
  <div class="casual-container">
    <!-- ? recommend -->
<!--    <div class="text-center">-->
<!--      <div class="position-relative d-inline-block" id="rec-title">Recommended Products</div>-->
<!--    </div>-->
<!--    <div class="swiper-container">-->
<!--      &lt;!&ndash; 보여지는 영역 &ndash;&gt;-->
<!--      <div class="swiper-wrapper">-->
<!--        &lt;!&ndash; <div class="swiper-slide">내용</div> 를 추가하면된다 &ndash;&gt;-->
<!--        <div class="swiper-slide"><img src="@/assets/images/product/c1-1.jpeg" href="#"></div>-->
<!--        <div class="swiper-slide"><img src="@/assets/images/product/r1-1.jpeg" href="#"></div>-->
<!--        <div class="swiper-slide"><img src="@/assets/images/product/r2-1.jpeg" href="#"></div>-->
<!--        <div class="swiper-slide"><img src="@/assets/images/product/c3-1.jpeg" href="#"></div>-->
<!--        <div class="swiper-slide"><img src="@/assets/images/product/b4-1.jpeg" href="#"></div>-->
<!--      </div>-->
<!--    </div>-->

<!--    &lt;!&ndash; * recommend &ndash;&gt;-->
<!--    <div class="text-center">-->
<!--      <div class="position-relative d-inline-block" id="rec-title">Recommended Products</div>-->
<!--    </div>-->
<!--    <div class="swiper-container">-->
<!--      &lt;!&ndash; 보여지는 영역 &ndash;&gt;-->
<!--      <div class="swiper-wrapper">-->
<!--          &lt;!&ndash; <div class="swiper-slide">내용</div> 를 추가하면된다 &ndash;&gt;-->
<!--          <div class="swiper-slide" v-for="(rec, index) in recommendProducts" :key="index">-->
<!--            <img :src="rec.url" href="#">-->
<!--          </div>-->
<!--      </div>-->
<!--    </div>-->

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
            <p>{{(rec.price - rec.discount).toLocaleString()}}원</p>
          </div>
        </div>
      </div>
    </div>

    <div class="casual-title text-center mt-5" v-if="categories.categry100">
      <h3 class="position-relative d-inline-block">러닝화</h3>
      <br>
      <h2 class="position-relative d-inline-block">Running</h2>
    </div>

    <div class="casual-title text-center mt-5" v-else-if="categories.categry200">
      <h3 class="position-relative d-inline-block">캐주얼</h3>
      <br>
      <h2 class="position-relative d-inline-block">Casual</h2>
    </div>

    <div class="casual-title text-center mt-5" v-else-if="categories.categry300">
      <h3 class="position-relative d-inline-block">슬리퍼</h3>
      <br>
      <h2 class="position-relative d-inline-block">Slippers</h2>
    </div>

    <div class="casual-title text-center mt-5" v-else-if="categories.categry400">
      <h3 class="position-relative d-inline-block">기타 신발</h3>
      <br>
      <h2 class="position-relative d-inline-block">Other Shoes</h2>
    </div>

    <!-- * list -->
    <section class="py-0">
<!--      &lt;!&ndash; * tag 1 &ndash;&gt;-->
<!--      <div class="d-flex flex-wrap justify-content-center mt-3 mb-4 filter-button-group">-->
<!--        <button type="button" class="collection-button btn m-2 active-filter-btn" data-filter="*" id="all">-->
<!--          전체-->
<!--        </button>-->
<!--        <button type="button" class="collection-button btn m-2" data-filter=".like">찜한 상품</button>-->
<!--        <button type="button" class="collection-button btn m-2" data-filter=".sale">세일</button>-->
<!--        <button type="button" class="collection-button btn m-2" data-filter=".best">베스트</button>-->
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


      <div class="container px-4 px-lg-5 mt-5 casual-list-container">
        <div class="collection-list row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 mt-5 justify-content-center">
          <!-- 상품 -->
          <div v-for="(product, index) in products" :key="index">
          <div class="col mb-5" :class="product.colors" >
            <div class="card h-100" >
              <!-- Sale badge-->
              <div v-if="(product.saleYn == 'Y')"
                   class="badge position-absolute sale-badge" style="top: 0.5rem; right: 0.5rem">
                Sale
              </div>
              <!-- <div class="badge position-absolute fav-icon" style="top: 0.5rem; right: 0.5rem">
                <i class="fa-solid fa-heart fa-3x"></i>
              </div> -->
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
                      {{product.price.toLocaleString()}}원
                    </span>
                    <br/>
                    {{(product.price - product.discount).toLocaleString()}}원
                  </div>
                  <div v-else>
                    {{product.price.toLocaleString()}}원
                    <br/>
                    <br/>
                  </div>
                </div>
              </div>
              <!-- Product actions-->
              <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                <div class="text-center">
                  <a class="btn mt-auto detail-btn" @click="onclick(product.id)">
                    상세보기
                  </a>
                </div>
              </div>
            </div>
          </div>
          </div>
          <!-- 상품 -->
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
import casual1 from "@/assets/js/casual1";
import ProductService from "@/services/product/ProductService"
import CategoryListService from "@/services/category/CategoryListService";

export default {
  name: "category-detail",
  data() {
    return {
      num: ['1','2','3','4', '5'], // test
      recommendProducts: [],
      categories: {
        categry100: false,
        categry200: false,
        categry300: false,
        categry400: false
      },
      bflag: false,
      products: [],
      categoryList: [],
      page: 1,
      count: 0,
      pageSize: 12,
      pageSizes: [12],
      searchTitle: "",
      catId: ""
    }
  },
  methods: {
    getRequestParams(catId, page, pageSize) {
      let params = {};
      // searchEmail값이 있으면 매개변수로 전송

      if (catId) {
        params["catId"] = catId;
      }

      if (page) {
        params["page"] = page - 1;
      }

      if (pageSize) {
        params["size"] = pageSize;
      }
      return params;
    },

    retrieveCatProducts() {
      // alert(this.$route.params.id);
      // Todo : getRequestParams 호출 추가
      const params = this.getRequestParams(
        this.catId,
        this.page,
        this.pageSize
      );
      ProductService.getCat(params)
      .then(res => {
        this.products = res.data;
        this.bflag = true;
        console.log(this.products)

        if(this.products[0].category == 100) {
          this.categories.categry100 = true
        } else if(this.products[0].category == 200) {
          this.categories.categry200 = true
        } else if(this.products[0].category == 300) {
          this.categories.categry300 = true
        } else if(this.products[0].category == 400) {
          this.categories.categry400 = true
        }
      })
      .catch(e => {
        alert(e);
      })
    },

    retrieveCatList() {
      CategoryListService.getCat()
          .then(res => {
            this.categoryList = res.data;
          })
          .catch(e => {
            alert(e);
          })
    },

    onclick(id) {
      this.$router.push("/products/" + id);
    },
    handlePageChange(value) {
      // 현재 페이지 변경
      this.page = value;
      // 재조회
      this.retrieveCatProducts();
    },
    // 페이지 사이즈(3,6,9) 변경 시 호출되는 메소드
    handlePageSizeChange(event) {
      this.pageSize = event.target.value; //select 박스의 값 (3,6,9)
      this.page = 1;
      // 재조회
      this.retrieveCatProducts();
    },
    // 추천상품
    retrieveRecommendProductsImg() {
      ProductService.getRandomImg()
          // 성공하면 then에 들어옴(객체, 응답메세지)
          .then((response) => {
            // response.data : 서버쪽에서 전송된 객체
            this.recommendProducts = response.data;
          });
    },
  },
  mounted() {
    this.catId = this.$route.params.id;
    this.retrieveRecommendProductsImg();
    this.retrieveCatProducts();
    this.retrieveCatList();
    casual1();
  },
  watch: {
    '$route' (to, from) {
      if(to.path != from.path) {
        this.catId = this.$route.params.id;
        this.retrieveCatProducts();
        this.$router.go();
      }
    }
  },
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    }
  }


};
</script>

<style>
@import "@/assets/css/casual2.css";
@import "@/assets/css/page.css";
</style>