<template>

  <div class = "casual-container">

    <!-- * recommend -->
    <div class = "text-center">
      <div class = "position-relative d-inline-block"
           id = "rec-title"
      >Recommended Products
      </div>
    </div>
    <swiper
        class = "swiper-container"
        :options = "swiperOption"
    >
      <swiper-slide v-for = "(item,index) in swiperList"
                    :key = "index"
      >
        <img :src = "item">
      </swiper-slide>
      <div
          class = "swiper-pagination"
          slot = "pagination"
      >
      </div>
      <div class = "swiper-button-prev"
           slot = "button-prev"
      ></div>
      <div class = "swiper-button-next"
           slot = "button-next"
      ></div>
    </swiper>


    <!-- 페이징 버튼 처리 상황에 따라 추가 삭제가능-->
    <!--      <div class="swiper-pagination"></div>-->
    <!-- * title -->
    <!--    <div class="casual-title text-center mt-5">-->
    <!--      <h3 class="position-relative d-inline-block">캐주얼</h3>-->
    <!--      <br>-->
    <!--      <h2 class="position-relative d-inline-block">Casual</h2>-->
    <!--    </div>-->

    <div class = "casual-title text-center mt-5"
         v-if = "categories.categry100"
    >
      <h3 class = "position-relative d-inline-block">운동화</h3>
      <br>
      <h2 class = "position-relative d-inline-block">Sneakers</h2>
    </div>

    <div class = "casual-title text-center mt-5"
         v-else-if = "categories.categry200"
    >
      <h3 class = "position-relative d-inline-block">구두</h3>
      <br>
      <h2 class = "position-relative d-inline-block">Oxfords</h2>
    </div>

    <div class = "casual-title text-center mt-5"
         v-else-if = "categories.categry300"
    >
      <h3 class = "position-relative d-inline-block">슬리퍼</h3>
      <br>
      <h2 class = "position-relative d-inline-block">Slippers</h2>
    </div>

    <div class = "casual-title text-center mt-5"
         v-else-if = "categories.categry400"
    >
      <h3 class = "position-relative d-inline-block">기타 신발</h3>
      <br>
      <h2 class = "position-relative d-inline-block">Other Shoes</h2>
    </div>

    <!-- * list -->
    <section class = "py-0">
      <div class = "container px-4 px-lg-5 mt-5 casual-list-container">
        <div class = "collection-list row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 mt-5 justify-content-center">
          <!-- 상품 -->
          <div v-for = "(product, index) in products"
               :key = "index"
          >
            <div class = "col mb-5"
            >
              <div class = "card h-100">
                <!-- Sale badge-->
                <div v-if = "(product.saleYn == 'Y')"
                     class = "badge position-absolute sale-badge"
                     style = "top: 0.5rem; right: 0.5rem"
                >
                  Sale
                </div>
                <!-- <div class="badge position-absolute fav-icon" style="top: 0.5rem; right: 0.5rem">
                  <i class="fa-solid fa-heart fa-3x"></i>
                </div> -->
                <!-- Product image-->
                <img class = "img-fluid card-img-top"
                     :src = "product.url"
                     alt = "..."
                     height = "270"
                />
                <!-- Product details-->
                <div class = "card-body p-4">
                  <div class = "text-center">
                    <!-- Product name-->
                    <h5 class = "fw-bolder">{{ product.title }}</h5>
                    <!-- Product price-->
                    <div v-if = "product.saleYn == 'Y'">
                    <span class = "text-muted text-decoration-line-through">
                      {{ product.price.toLocaleString() + '원' }}
                    </span>
                      <br/>
                      {{ (product.price - product.discount).toLocaleString() + '원' }}
                    </div>
                    <div v-else>
                      {{ product.price.toLocaleString() + '원' }}
                    </div>
                  </div>
                </div>
                <!-- Product actions-->
                <div class = "card-footer p-4 pt-0 border-top-0 bg-transparent">
                  <div class = "text-center">
                    <a class = "btn mt-auto detail-btn"
                       @click = "onclick(product.id)"
                    >
                      상품상세
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
        class = "mt-2"
        v-model = "page"
        :total-rows = "count"
        :per-page = "pageSize"
        align = "center"
        @change = "handlePageChange"
    >
    </b-pagination>
  </div>
</template>
<script>
import ProductService from '@/services/product/ProductService'
import CategoryListService from '@/services/category/CategoryListService'
import Vue from 'vue'
import { Swiper, SwiperSlide } from 'vue-awesome-swiper'

export default Vue.extend({
      name      : 'category-detail',
      components: {
        Swiper,
        SwiperSlide,
      },
      data () {
        return {
          num              : ['1', '2', '3', '4', '5'], // test
          recommendProducts: [],
          categories       : {
            categry100: false,
            categry200: false,
            categry300: false,
            categry400: false,
          },
          products         : [],
          categoryList     : [],
          page             : 1,
          count            : 0,
          pageSize         : 9,
          pageSizes        : [3, 6, 9],
          searchTitle      : '',
          catId            : '',
          swiperList       : [],
          swiperOption     : {
            //기본 셋팅
            //방향 셋팅 vertical 수직, horizontal 수평 설정이 없으면 수평
            direction: 'horizontal',
            //한번에 보여지는 페이지 숫자
            slidesPerView: 5,
            //페이지와 페이지 사이의 간격
            spaceBetween: 0,
            //드레그 기능 true 사용가능 false 사용불가
            debugger: true,
            //마우스 휠기능 true 사용가능 false 사용불가
            mousewheel: false,
            //반복 기능 true 사용가능 false 사용불가
            loop: true,
            //선택된 슬라이드를 중심으로 true 사용가능 false 사용불가 djqt
            centeredSlides: true,
            // 페이지 전환효과 slidesPerView효과와 같이 사용 불가
            // effect: 'fade',

            //자동 스크를링
            autoplay: {
              //시간 1000 이 1초
              delay               : 2500,
              disableOnInteraction: false,
            },
          },
        }

      },
      methods: {
        getRequestParams (catId, page, pageSize) {
          let params = {}
          // searchEmail값이 있으면 매개변수로 전송

          if (catId) {
            params['catId'] = catId
          }

          if (page) {
            params['page'] = page - 1
          }

          if (pageSize) {
            params['size'] = pageSize
          }
          return params
        },

        retrieveCatProducts () {
          // alert(this.$route.params.id);
          // Todo : getRequestParams 호출 추가
          const params = this.getRequestParams(
              this.catId,
              this.page,
              this.pageSize,
          )
          ProductService.getCat(params).then(res => {
            this.products = res.data
            this.bflag = true
            console.log(JSON.stringify(this.products))

            if (this.products[0].category == 100) {
              this.categories.categry100 = true
            }
            else if (this.products[0].category == 200) {
              this.categories.categry200 = true
            }
            else if (this.products[0].category == 300) {
              this.categories.categry300 = true
            }
            else if (this.products[0].category == 400) {
              this.categories.categry400 = true
            }
          }).catch(e => {
            alert(e)
          })
        },

        retrieveCatList () {
          CategoryListService.getCat().then(res => {
            this.categoryList = res.data
          }).catch(e => {
            alert(e)
          })
        },

        onclick (id) {
          this.$router.push('/products/' + id)
        },
        handlePageChange (value) {
          // 현재 페이지 변경
          this.page = value
          // 재조회
          this.retrieveCatProducts()
        },
        // 페이지 사이즈(3,6,9) 변경 시 호출되는 메소드
        handlePageSizeChange (event) {
          this.pageSize = event.target.value //select 박스의 값 (3,6,9)
          this.page = 1
          // 재조회
          this.retrieveCatProducts()
        },
        // 추천상품
        retrieveRecommendProductsImg () {
          ProductService.getRandomImg()
              // 성공하면 then에 들어옴(객체, 응답메세지)
              .then((response) => {
                // response.data : 서버쪽에서 전송된 객체
                this.recommendProducts = response.data
              })
        },
      },
      mounted () {
        this.catId = this.$route.params.id
        this.retrieveRecommendProductsImg()
        this.retrieveCatProducts()
        this.retrieveCatList()
      },
      created () {
        setTimeout(() => {
          for (let i = 0; i < this.products.length; i++) {
            this.swiperList.push(this.products[i].url)
            console.log('url : ' + this.products[i].url)
          }
          console.log('urlList : ' + this.swiperList)
        }, 3000)

      },
      watch: {
        '$route' (to, from) {
          if (to.path != from.path) {
            this.catId = this.$route.params.id
            this.retrieveCatProducts()
            this.$router.go()
          }
        },
      },
    },
)
</script>

<style>
@import "@/assets/css/casual2.css";
</style>