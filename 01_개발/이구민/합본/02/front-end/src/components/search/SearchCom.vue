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
          총 <strong>{count.length}</strong>개의 검색결과가 있습니다.
        </div>
        <div v-else>
          <div class="search-result">'{}'에 대한 검색결과가 없습니다.</div>
          <div class="rec-item mt-5">
            <p class="text-center mt-5">추천 상품</p>

          </div>
        </div>
        <div class="collection-list row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 mt-5 justify-content-center">
<!--          <div class="col mb-5 like">-->
<!--            <div class="card h-100">-->
<!--              <div class="badge position-absolute fav-icon" style="top: 0.5rem; right: 0.5rem">-->
<!--                <i class="fa-solid fa-heart fa-3x"></i>-->
<!--              </div>-->
<!--              &lt;!&ndash; Product image&ndash;&gt;-->
<!--              <img class="img-fluid card-img-top" src="@/assets/images/product/c1-1.jpeg" alt="..." height="250"/>-->
<!--              &lt;!&ndash; Product details&ndash;&gt;-->
<!--              <div class="card-body p-4">-->
<!--                <div class="text-center">-->
<!--                  &lt;!&ndash; Product name&ndash;&gt;-->
<!--                  <h5 class="fw-bolder">Fancy Product</h5>-->
<!--                  &lt;!&ndash; Product price&ndash;&gt;-->
<!--                  $40.00 - $80.00-->
<!--                </div>-->
<!--              </div>-->
<!--              &lt;!&ndash; Product actions&ndash;&gt;-->
<!--              <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">-->
<!--                <div class="text-center"><a class="btn mt-auto detail-btn" href="/detail">-->
<!--                  상품상세</a>-->
<!--                </div>-->
<!--              </div>-->
<!--            </div>-->
<!--          </div>-->
<!--          <div class="col mb-5 rec">-->
<!--            <div class="card h-100">-->
<!--              &lt;!&ndash; Sale badge&ndash;&gt;-->
<!--              <div class="badge position-absolute sale-badge" style="top: 0.5rem; right: 0.5rem">-->
<!--                Sale-->
<!--              </div>-->
<!--              &lt;!&ndash; Product image&ndash;&gt;-->
<!--              <img class="img-fluid card-img-top" src="@/assets/images/product/c2-1.jpeg" alt="..." height="250"/>-->
<!--              &lt;!&ndash; Product details&ndash;&gt;-->
<!--              <div class="card-body p-4">-->
<!--                <div class="text-center">-->
<!--                  &lt;!&ndash; Product name&ndash;&gt;-->
<!--                  <h5 class="fw-bolder">Special Item</h5>-->
<!--                  &lt;!&ndash; Product price&ndash;&gt;-->
<!--                  <span class="text-muted text-decoration-line-through">$20.00</span>-->
<!--                  $18.00-->
<!--                </div>-->
<!--              </div>-->
<!--              &lt;!&ndash; Product actions&ndash;&gt;-->
<!--              <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">-->
<!--                <div class="text-center"><a class="btn mt-auto detail-btn" href="#">-->
<!--                  상품상세</a>-->
<!--                </div>-->
<!--              </div>-->
<!--            </div>-->
<!--          </div>-->
<!--          <div class="col mb-5">-->
<!--            <div class="card h-100">-->
<!--              &lt;!&ndash; Sale badge&ndash;&gt;-->
<!--              <div class="badge position-absolute sale-badge" style="top: 0.5rem; right: 0.5rem">-->
<!--                Sale-->
<!--              </div>-->
<!--              &lt;!&ndash; Product image&ndash;&gt;-->
<!--              <img class="img-fluid card-img-top" src="@/assets/images/product/b1-1.jpeg" alt="..."/>-->
<!--              &lt;!&ndash; Product details&ndash;&gt;-->
<!--              <div class="card-body p-4">-->
<!--                <div class="text-center">-->
<!--                  &lt;!&ndash; Product name&ndash;&gt;-->
<!--                  <h5 class="fw-bolder">Sale Item</h5>-->
<!--                  &lt;!&ndash; Product price&ndash;&gt;-->
<!--                  <span class="text-muted text-decoration-line-through">$50.00</span>-->
<!--                  $25.00-->
<!--                </div>-->
<!--              </div>-->
<!--              &lt;!&ndash; Product actions&ndash;&gt;-->
<!--              <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">-->
<!--                <div class="text-center"><a class="btn mt-auto detail-btn" href="#">-->
<!--                  상품상세</a>-->
<!--                </div>-->
<!--              </div>-->
<!--            </div>-->
<!--          </div>-->
        </div>
      </div>
    </section>

  </div>
</template>

<script>
import search from '@/assets/js/search'

export default {
  name: "SearchCom",
  data() {
    return {
      searchResult: false
    }
  },
  mounted() {
    search();
  }
}
</script>

<style scoped>
@import "@/assets/css/search.css";
</style>