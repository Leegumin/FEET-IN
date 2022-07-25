<template>
  <div>
    <section class = "py-5">
      <div class = "container px-4 px-lg-5 my-5 detail-container">
        <div class = "row gx-4 gx-lg-5 align-items-center">
          <div class = "col-md-6 position-relative"><img class = "card-img-top mb-5 mb-md-0"
                                       id = "pic"
                                       :src = "products.url"
                                       alt = "..."
                                       width = "600px"
                                       height = "600px"
                                       style="min-width: 500px; max-width: 600px"
          />
            <!--            <div id="small-pic">-->
            <!--              <img src="@/assets/images/product/b1-1.jpeg" class="small me-1" width="100" height="100">-->
            <!--              <img src="@/assets/images/product/r1-1.jpeg" class="small me-1" width="100" height="100">-->
            <!--              <img src="@/assets/images/product/c2-1.jpeg" class="small me-1" width="100" height="100">-->
            <!--            </div>-->
            <div v-if="this.likeYn==false" class="badge position-absolute" style="top: 1.5rem; left: 3rem; z-index: 10">
              <i class="bi-heart fa-4x" style="color: #36363f"></i>
            </div>
            <div v-else-if="this.likeYn==true" class="badge position-absolute" style="top: 1.5rem; left: 3rem; z-index: 10">
              <i class="bi-heart-fill fa-4x" style="color: #028DFF;"></i>
            </div>
          </div>

          <div class = "col-md-6 detail-title">
            <div class = "small mb-1">{{ products.model }}</div>
            <h1 class = "display-5 fw-bolder">{{ products.title }}</h1>
            <div class = "fs-5 mb-5">
              <div v-if = "products.saleYn == 'Y'">
                <span class = "text-muted text-decoration-line-through">{{
                    products.price.toLocaleString()
                  }}원</span><br/>
                <span>{{ (products.price - products.discount).toLocaleString()}}원</span>
              </div>
              <div v-else>
                <span>{{ products.price.toLocaleString() }}원</span>
              </div>
            </div>
            <p class = "lead">{{ products.description }}</p>
            <div class = "d-flex">
              <input class = "form-control text-center me-2"
                     id = "inputQuantity"
                     type = "number"
                     value = "1"
                     min = "1"
                     v-model = "quantity"
                     style = "max-width: 4rem"
              />
              <select v-model = "shoseSize"
                      class = "form-select me-2"
                      aria-label = "Default select example"
                      id = "option-sel"
                      required
              >
                <option selected
                        disabled
                        value = "0"
                >사이즈
                </option>
                <option value = "220">220</option>
                <option value = "230">230</option>
                <option value = "235">235</option>
                <option value = "240">240</option>
                <option value = "245">245</option>
                <option value = "250">250</option>
                <option value = "255">255</option>
                <option value = "260">260</option>
              </select>
            </div>
            <div class = "d-flex mt-3">
              <button class = "btn btn-outline-dark flex-shrink-0 add-btn me-2"
                      type = "button"
                      @click = "OnOrder"
              >
                <i class = "fa-solid fa-credit-card fa-1x"
                   id = "buy-icon"
                ></i>
                바로 구매
              </button>
              <button class = "btn btn-outline-dark flex-shrink-0 add-btn me-2"
                      type = "button"
                      @click = "addCart"
              >
                <i class = "bi-cart-fill me-1"
                   id = "cart-icon"
                ></i>
                장바구니 담기
              </button>

              <div v-if = "this.likeYn == false">
                <button class = "btn btn-outline-dark flex-shrink-0 add-btn me-2"
                        id = "like-btn"
                        type = "button"
                        @click = "addLike"
                >
                  <i class = "bi-heart me-1"
                     id = "like-icon"
                  ></i>
                  {{likeCount}}
                </button>
              </div>
              <div v-else>
                <button class = "btn btn-outline-dark flex-shrink-0 add-btn me-2"
                        id = "like-btn"
                        type = "button"
                        @click = "deleteLike"
                        style = "background-color: #028DFF; border: 1px solid #028DFF"
                >
                  <i class = "bi-heart-fill me-1"
                     id = "like-icon"
                  ></i>
                  {{likeCount}}
                </button>
              </div>

              <!--              <div v-if="this.likeYn == false">-->
              <!--                <button class="btn btn-outline-dark flex-shrink-0 add-btn" type="button" @click="deleteLike">-->
              <!--                  <i class="bi-heart me-1" id="cart-icon">{{likeCount}}</i>-->
              <!--                </button>-->
              <!--              </div>-->
              <!--              <div v-else>-->
              <!--                <button class="btn btn-outline-dark flex-shrink-0 add-btn" type="button" @click="deleteLike">-->
              <!--                  <i class="bi-heart-fill me-1" id="cart-icon">{{likeCount}}</i>-->
              <!--                </button>-->
              <!--              </div>-->
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- Related items section-->
    <section class = "py-0">
      <div class = "container px-4 px-lg-5">

        <!-- * review -->
        <div class = "review-set mt-4 position-relative" >
          <h2 class = "fw-bolder mb-4 review-title">상품 후기</h2>
          <div class = "mt-3 re-btn position-absolute"
               id = "add-review-btn"
               style="top: -0.5rem; right: 0rem;"
          >
            <a class = "float-right btn text-white px-4"
               @click = "addReview"
            >글쓰기</a>
          </div>
          <div style = "clear:both;"></div>

          <!--          &lt;!&ndash; 페이지 바 &ndash;&gt;-->
          <!--          <div class="col-md-12" v-if="false">-->
          <!--            <div class="md-3">-->
          <!--              Items per Page :-->
          <!--              <select v-model="pageSize" @change="handlePageSizeChange($event)">-->
          <!--                &lt;!&ndash; pageSizes : [3,6,9] &ndash;&gt;-->
          <!--                <option v-for="size in pageSizes" :key="size" :value="size">-->
          <!--                  {{ size }}-->
          <!--                </option>-->
          <!--              </select>-->
          <!--            </div>-->
          <!--          </div>&lt;!&ndash; 페이지 바 &ndash;&gt;-->
          <div class = "card mb-1"
               v-for = "(review, index) in reviews"
               :key = "index"
          >
            <div class = "card-body profile-container">
              <div class = "row">
                <div class = "col-md-2 text-center dt-profile-container">
                  <img :src = "products.url"/>
                </div>
                <div class = "col-md-5">
                  <div class = "float-left user-name">{{review.writer}}</div>
                  <p>
                    <span v-for = "n in review.starNum"
                          :key = "n"
                    ><img class = "imgStar"
                          style = "height:20px;width:20px;"
                          src = "../../assets/images/staricon.png"
                    ></span>
                  </p>
                  <a class = "review-content-title"
                     @click = "showContent(index)"
                  >{{ review.title }}</a>
                  <div class = "review mt-2"
                       v-if = "!review.type"
                  >
                    {{ review.content }}
                    <br/>
                    <img id = "review-img"
                         :src = "review.url"
                    />

                    <div class = "re-btn mt-5">
                      <!-- 클릭이벤트 추가 -->
                      <a class = "float-right btn text-white"
                         onclick = "return false;"
                         style = "float: right"
                         @click="deleteReview(review.reviewId)"
                      >삭제</a>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div v-if = "!reviews.length">
            <div class = "text-center pt-3 pb-3"
                 id = "empty"
            >아직 등록된 후기가 없습니다.
            </div>
          </div>
          <!-- 페이징 -->
          <b-pagination
              v-model = "page"
              :total-rows = "count"
              :per-page = "pageSize"
              align = "center"
              @change = "handlePageChange"
              class = "mt-2"
          >
          </b-pagination><!-- 페이징 -->
        </div>

        <!-- * recommended -->
        <div class = "other-set mt-4 position-relative">
          <h2 class = "fw-bolder mb-4 other-title mt-5">추천 상품</h2>
          <a class = "fw-bolder mb-4 other-link mt-5 position-absolute"
             href = "/products"
             style="top: 1rem; right: 0rem;"
          >더 많은 상품 보러 가기 <span><i
              class = "fa-solid fa-angles-right"
          ></i></span></a>
          <div
              class = "other-collection-list row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center"
          >
            <div class = "col mb-5"
                 v-for = "(product, index) in recommendProducts"
                 :key = "index"
            >
              <div class = "card h-100">
                <div v-if = "(product.saleYn == 'Y')"
                     class = "badge position-absolute sale-badge"
                     style = "top: 0.5rem; right: 0.5rem"
                >
                  Sale
                </div>
                <!-- Product image-->
                <img class = "card-img-top"
                     :src = "product.url"
                     alt = "..."
                />
                <!-- Product details-->
                <div class = "card-body p-4">
                  <div class = "text-center">
                    <!-- Product name-->
                    <h5 class = "fw-bolder">{{ product.title }}</h5>
                    <!-- Product price-->
                    <div v-if = "product.saleYn == 'Y'">
                      <span class = "text-muted text-decoration-line-through">
                        {{ product.price.toLocaleString() }}원
                      </span>
                      <br/>
                      {{ (product.price - product.discount).toLocaleString() }}원
                    </div>
                    <div v-else>
                      {{ product.price.toLocaleString() }}원
                      <br/>
                      <br/>
                    </div>
                  </div>
                </div>
                <!-- Product actions-->
                <div class = "card-footer p-4 pt-0 border-top-0 bg-transparent">
                  <div class = "text-center"><a class = "btn mt-auto detail-btn"
                                                @click = "onclick(product.id)"
                  >
                    상품상세</a>
                  </div>
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
// 화면 설명 : 회원정보 수정 /삭제 화면
import ProductService from '@/services/product/ProductService'
import CartSerivce from '@/services/cart/CartDataService'
import UploadService from '@/services/review/UploadFilesService'
import LikeService from '@/services/like/LikeService'
import detail from '@/assets/js/detail'

export default {
  name: 'product-detail',
  data () {
    return {
      products         : [],
      reviews          : [],
      recommendProducts: [],
      message          : '',
      shoseSize        : 0,
      quantity         : 1,
      id               : this.$route.params.id,
      finalPrice       : this.finalPrice,
      price            : 0,
      // 페이징 관련 데이터
      page    : 1,
      count   : 0,
      pageSize: 3,

      //
      likes    : [],
      likeCount: 0,
      likeYn   : '',
      rowNum   : 4,
      userId   : '',
    }
  },
  computed: {
    loggedIn () {
      // 공유저장소의 user객체에 속성인 loggedIn값을 가져옴
      return this.$store.state.auth.status.loggedIn
    },
  },
  methods : {
    // 페이징 메소드
    getRequestParams (id, page, pageSize) {
      let params = {}

      if (id) {
        params['id'] = id
      }

      if (page) {
        params['page'] = page - 1
      }

      if (pageSize) {
        params['size'] = pageSize
      }
      return params
    },
    addCart () {
      if (this.shoseSize == 0) {
        alert('사이즈는 필수 선택항목입니다.')
      }
      else if (!this.loggedIn) {
        alert('로그인 후 사용가능합니다.')
      }
      else {
        var data = {
          userId    : this.$store.state.auth.user.id,
          productId : this.$route.params.id,
          quantity  : this.quantity,
          shoseSize : this.shoseSize,
          finalPrice: this.finalPrice,
        }

        CartSerivce.createCart(data).then((response) => {
          this.products.id = response.data.id
          alert('상품이 장바구니에 추가되었습니다')
        }).catch((e) => {
          console.log(e)
        })
      }
    },
    handlePageChange (value) {
      // 현재 페이지 변경
      this.page = value
      // 재조회
      this.retrieveReview()
    },

    // 상품정보 불러오는 메소드
    getProduct (id) {
      ProductService.get(id)
          // 성공하면 then에 들어옴(객체, 응답메세지)
          .then((image) => {
            // response.data : 서버쪽에서 전송된 객체
            this.products = image.data
            this.price = this.products.price
            this.finalPrice = this.products.price - this.products.discount
          })
    },

    // 리뷰 불러오는 메소드
    retrieveReview () {
      // Todo : getRequestParams 호출 추가
      const params = this.getRequestParams(
          // this.searchTitle,
          this.id,
          this.page,
          this.pageSize,
      )
      UploadService.getProductReview(params)
          // 성공하면 then에 들어옴(객체, 응답메세지)
          .then((response) => {
            // response.data : 서버쪽에서 전송된 객체
            this.reviews = response.data
            this.count = this.reviews[0].totalItems
          })
    },

    deleteReview (id) {
      if(confirm("후기를 삭제하시겠습니까?")) {
        UploadService.delete(id)
            .then(() => {
              this.retrieveReview()
            }).catch((e) => {
          alert(e)
        })
      }
    },

    addReview () {
      this.$router.push('/add-review/' + this.$route.params.id)
    },
    OnOrder () {
      if (this.shoseSize == 0) {
        alert('사이즈는 필수 선택항목입니다.')
      }
      else if (!this.loggedIn) {
        alert('로그인 후 사용가능합니다.')
      }
      else {
        this.$router.push(
            '/order/' +
            this.$route.params.id +
            '/' +
            this.shoseSize +
            '/' +
            this.quantity,
        )
      }
    },

    showContent (index) {
      this.reviews[index].type = !this.reviews[index].type
    },

    // 추천상품
    getRanParams (rowNum) {
      let params = {}

      if (rowNum) {
        params['rowNum'] = rowNum
      }

      return params
    },

    retrieveRecommendProducts () {
      const params = this.getRanParams(
          this.rowNum,
      )
      ProductService.getRandom(params)
          // 성공하면 then에 들어옴(객체, 응답메세지)
          .then((response) => {
            // response.data : 서버쪽에서 전송된 객체
            this.recommendProducts = response.data
            console.log(this.products)
          })
    },

    // 관심상품 메소드
    getLikeParams (userId, id) {
      let params = {}

      if (userId) {
        params['userId'] = userId
      }

      if (id) {
        params['productId'] = id
      }

      return params
    },

    getLike () {
      const params = this.getLikeParams(
          this.userId,
          this.id,
      )
      LikeService.getLike(params).then((res) => {
        this.likes = res.data
        this.likeCount = this.likes.length
        this.checkLikeYn()
      })
    },

    checkLikeYn () {
      let data = {
        userId   : this.userId,
        productId: this.id,
      }
      LikeService.checkLikeYn(data).then((res) => {
        this.likeYn = res.data
      })
    },

    addLike () {
      if (!this.loggedIn) {
        alert('로그인 후 사용가능합니다.')
        return
      }
      let data = {
        userId   : this.userId,
        productId: this.id,
      }
      LikeService.addLike(data).then(() => {
        this.getLike()
      }).catch((e) => {
        console.log(e)
      })
    },

    deleteLike () {
      let data = {
        userId   : this.userId,
        productId: this.id,
      }
      LikeService.deleteLike(data).then(() => {
        this.getLike()
      })
    },

    checkLogin () {
      if (this.$store.state.auth.user.id != null) {
        this.userId = this.$store.state.auth.user.id
      }
      else {
        this.userId = ''
      }
    },

    onclick (id) {
      this.$router.push('/products/' + id)
      this.$router.go()
    },
  },
  mounted () {
    detail()
    this.getProduct(this.$route.params.id)
    this.retrieveReview()
    this.retrieveRecommendProducts()
    this.getLike()
    this.checkLogin()
  },
}
</script>

<style scoped>
@import "@/assets/css/detail.css";
@import "@/assets/css/page.css";
</style>