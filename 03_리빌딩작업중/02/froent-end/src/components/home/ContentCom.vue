<template>
  <div class="home-container">
    <!-- * header * -->
    <header>
      <div id="carouselExampleCaptions" class="carousel slide carousel-fade" data-bs-ride="carousel">
        <div class="carousel-indicators">
          <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active"
                  aria-current="true" aria-label="Slide 1"></button>
          <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1"
                  aria-label="Slide 2"></button>
          <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2"
                  aria-label="Slide 3"></button>
        </div>
        <div class="carousel-inner">
          <div class="carousel-item active">
            <video muted autoplay loop class="d-block w-100" alt="...">
              <source src="@/assets/video/Shoe-Laces.mp4" type="video/mp4">
            </video>
<!--            <img src="@/assets/images/banner-3.jpg" class="d-block w-100" alt="...">-->
            <div class="carousel-caption d-none d-md-block first-cap">
              <h5>SUMMER</h5>
              <h5 style="font-size: 120px; font-weight: 900">BIG SALE</h5>
              <h4>최대 60% 할인 중</h4>
            </div>
          </div>
          <div class="carousel-item">
            <video muted autoplay loop class="d-block w-100" alt="...">
              <source src="@/assets/video/Skate.mp4" type="video/mp4">
            </video>
<!--            <img src="@/assets/images/banner-5.jpg" class="d-block w-100" alt="...">-->
            <div class="carousel-caption d-none d-md-block">
              <h5>NIKE SEASON OFF</h5>
              <h4>나이키 시즌오프 할인</h4>
            </div>
          </div>
          <div class="carousel-item">
            <video muted autoplay loop class="d-block w-100" alt="...">
              <source src="@/assets/video/Walking.mp4" type="video/mp4">
            </video>
<!--            <img src="@/assets/images/banner-1.jpg" class="d-block w-100" alt="...">-->
            <div class="carousel-caption d-none d-md-block">
              <h5>4CM UP HEIGHT FLIP-FLOPS</h5>
              <h4>4cm 키높이 슬리퍼 2COLOR</h4>
            </div>
          </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions"
                data-bs-slide="prev">
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions"
                data-bs-slide="next">
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Next</span>
        </button>
      </div>
    </header>
    <!-- * section * -->
    <section id="collection" class="py-5">
      <div class="container">
        <!-- 컬렉션 타이틀 -->
        <div class="collection-title text-center mt-5">
          <h3 class="position-relative d-inline-block">새롭게 추가된 상품</h3>
          <br>
          <h2 class="position-relative d-inline-block">New Products</h2>
        </div>

        <!-- 컬렉션 태그 -->
        <div class="row g-0">
<!--          <div class="d-flex flex-wrap justify-content-center mt-5 filter-button-group">-->
<!--            <button type="button" class="collection-button btn m-2 active-filter-btn" data-filter="*">-->
<!--              전체상품-->
<!--            </button>-->
<!--            <button type="button" class="collection-button btn m-2" data-filter=".sale">세일</button>-->
<!--            <button type="button" class="collection-button btn m-2" data-filter=".best">베스트</button>-->
<!--            <button type="button" class="collection-button btn m-2" data-filter=".fav">MD 추천</button>-->
<!--          </div>-->
          <!-- 컬렉션 리스트 -->
          <div class="container px-4 px-lg-5 mt-5">
            <div class="collection-list row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
              <div class="col mb-5" v-for="(product, index) in products" :key="index">
                <div class="card h-100">
                  <div v-if="(product.saleYn == 'Y')" class="badge position-absolute sale-badge" style="top: 0.5rem; right: 0.5rem">
                    Sale
                  </div>
                  <img class="img-fluid card-img-top" :src="product.url" alt="..." height="270"/>
                  <div class="card-body p-4">
                    <div class="text-center">
                      <h5 class="fw-bolder">{{product.title}}</h5>
                      <div v-if="product.saleYn == 'Y'">
                        <span class="text-muted text-decoration-line-through">
                          {{product.price.toLocaleString()}}원
                        </span>
                        <br/>
                        {{(product.price - product.discount).toLocaleString()}}원
                      </div>
                      <div v-else>
                        {{product.price.toLocaleString()}}원
                      </div>
                    </div>
                  </div>
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
          </div>
          <div class="all-btn text-center">
            <a class="position-relative d-inline-block" href="/products">더 많은 상품 보러가기 <span><i
                class="fa-solid fa-angles-right"></i></span></a>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import ProductService from "@/services/product/ProductService"

export default {
  name: "ContentCom",
  data() {
    return {
      products: [],
    }
  },
  methods: {
    retrieveNewProduct() {
      ProductService.getNew()
      .then(res => {
        this.products = res.data;
        console.log(this.products);
      })
      .catch(e => {
        alert(e);
      })
    },

    onclick(id) {
      this.$router.push("/products/" + id);
    },
  },
  mounted() {
    this.retrieveNewProduct();
  },
}
</script>

<style scoped>
@import "@/assets/css/custom.css";
</style>