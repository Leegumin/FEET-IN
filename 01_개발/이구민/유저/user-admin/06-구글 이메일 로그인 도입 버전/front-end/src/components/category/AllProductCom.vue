<template>
  <div class="container all-container">
<!--    <div class = "section-title mb-3 mb-sm-4 mt-5 text-center all-title" id="all-title">All Products</div>-->
    <div class="all-title text-center mt-5">
      <h3 class="position-relative d-inline-block">전체 상품</h3>
      <br>
      <h2 class="position-relative d-inline-block">All Products</h2>
    </div>
    <section class="py-0">
      <div class="d-flex flex-wrap justify-content-center mt-3 mb-5 filter-button-group">
        <button type="button" class="collection-button btn m-2 active-filter-btn" data-filter="*">
          전체
        </button>
        <button type="button" class="collection-button btn m-2" data-filter=".like">찜한 상품</button>
        <button type="button" class="collection-button btn m-2" data-filter=".rec">MD 추천</button>
      </div>
      <div class="container px-4 px-lg-5 mt-5 all-list-container">
        <div class="collection-list row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 mt-5 justify-content-center">
          <div class="col mb-5 like">
            <div class="card h-100">
              <div class="badge position-absolute fav-icon" style="top: 0.5rem; right: 0.5rem">
                <i class="fa-solid fa-heart fa-3x"></i>
              </div>
              <!-- Product image-->
              <img class="img-fluid card-img-top" src="@/assets/images/product/c1-1.jpeg" alt="..." height="250"/>
              <!-- Product details-->
              <div class="card-body p-4">
                <div class="text-center">
                  <!-- Product name-->
                  <h5 class="fw-bolder">Fancy Product</h5>
                  <!-- Product price-->
                  $40.00 - $80.00
                </div>
              </div>
              <!-- Product actions-->
              <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                <div class="text-center"><a class="btn mt-auto detail-btn" href="/detail">
                  상품상세</a>
                </div>
              </div>
            </div>
          </div>
          <div class="col mb-5 rec">
            <div class="card h-100">
              <!-- Sale badge-->
              <div class="badge position-absolute sale-badge" style="top: 0.5rem; right: 0.5rem">
                Sale
              </div>
              <!-- Product image-->
              <img class="img-fluid card-img-top" src="@/assets/images/product/c2-1.jpeg" alt="..." height="250"/>
              <!-- Product details-->
              <div class="card-body p-4">
                <div class="text-center">
                  <!-- Product name-->
                  <h5 class="fw-bolder">Special Item</h5>
                  <!-- Product price-->
                  <span class="text-muted text-decoration-line-through">$20.00</span>
                  $18.00
                </div>
              </div>
              <!-- Product actions-->
              <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                <div class="text-center"><a class="btn mt-auto detail-btn" href="#">
                  상품상세</a>
                </div>
              </div>
            </div>
          </div>
          <div class="col mb-5">
            <div class="card h-100">
              <!-- Sale badge-->
              <div class="badge position-absolute sale-badge" style="top: 0.5rem; right: 0.5rem">
                Sale
              </div>
              <!-- Product image-->
              <img class="img-fluid card-img-top" src="@/assets/images/product/b1-1.jpeg" alt="..."/>
              <!-- Product details-->
              <div class="card-body p-4">
                <div class="text-center">
                  <!-- Product name-->
                  <h5 class="fw-bolder">Sale Item</h5>
                  <!-- Product price-->
                  <span class="text-muted text-decoration-line-through">$50.00</span>
                  $25.00
                </div>
              </div>
              <!-- Product actions-->
              <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                <div class="text-center"><a class="btn mt-auto detail-btn" href="#">
                  상품상세</a>
                </div>
              </div>
            </div>
          </div>
          <div class="col mb-5 like">
            <div class="card h-100">
              <!-- Product image-->
              <img class="img-fluid card-img-top" src="@/assets/images/product/s1-1.jpeg" alt="..."/>
              <!-- Product details-->
              <div class="card-body p-4">
                <div class="text-center">
                  <!-- Product name-->
                  <h5 class="fw-bolder">Popular Item</h5>
                  <!-- Product price-->
                  $40.00
                </div>
              </div>
              <!-- Product actions-->
              <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                <div class="text-center"><a class="btn mt-auto detail-btn" href="#">
                  상품상세</a>
                </div>
              </div>
            </div>
          </div>
          <div class="col mb-5 like">
            <div class="card h-100">
              <div class="badge position-absolute fav-icon" style="top: 0.5rem; right: 0.5rem">
                <i class="fa-solid fa-heart fa-3x"></i>
              </div>
              <!-- Product image-->
              <img class="img-fluid card-img-top" src="@/assets/images/product/c1-1.jpeg" alt="..."/>
              <!-- Product details-->
              <div class="card-body p-4">
                <div class="text-center">
                  <!-- Product name-->
                  <h5 class="fw-bolder">Fancy Product</h5>
                  <!-- Product price-->
                  $40.00 - $80.00
                </div>
              </div>
              <!-- Product actions-->
              <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                <div class="text-center"><a class="btn mt-auto detail-btn" href="/detail">
                  상품상세</a>
                </div>
              </div>
            </div>
          </div>
          <div class="col mb-5 rec">
            <div class="card h-100">
              <!-- Sale badge-->
              <div class="badge position-absolute sale-badge" style="top: 0.5rem; right: 0.5rem">
                Sale
              </div>
              <!-- Product image-->
              <img class="img-fluid card-img-top" src="@/assets/images/product/c2-1.jpeg" alt="..."/>
              <!-- Product details-->
              <div class="card-body p-4">
                <div class="text-center">
                  <!-- Product name-->
                  <h5 class="fw-bolder">Special Item</h5>
                  <!-- Product price-->
                  <span class="text-muted text-decoration-line-through">$20.00</span>
                  $18.00
                </div>
              </div>
              <!-- Product actions-->
              <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                <div class="text-center"><a class="btn mt-auto detail-btn" href="#">
                  상품상세</a>
                </div>
              </div>
            </div>
          </div>
          <div class="col mb-5">
            <div class="card h-100">
              <!-- Sale badge-->
              <div class="badge position-absolute sale-badge" style="top: 0.5rem; right: 0.5rem">
                Sale
              </div>
              <!-- Product image-->
              <img class="img-fluid card-img-top" src="@/assets/images/product/b1-1.jpeg" alt="..."/>
              <!-- Product details-->
              <div class="card-body p-4">
                <div class="text-center">
                  <!-- Product name-->
                  <h5 class="fw-bolder">Sale Item</h5>
                  <!-- Product price-->
                  <span class="text-muted text-decoration-line-through">$50.00</span>
                  $25.00
                </div>
              </div>
              <!-- Product actions-->
              <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                <div class="text-center"><a class="btn mt-auto detail-btn" href="#">
                  상품상세</a>
                </div>
              </div>
            </div>
          </div>
          <div class="col mb-5 like">
            <div class="card h-100">
              <!-- Product image-->
              <img class="img-fluid card-img-top" src="@/assets/images/product/s1-1.jpeg" alt="..."/>
              <!-- Product details-->
              <div class="card-body p-4">
                <div class="text-center">
                  <!-- Product name-->
                  <h5 class="fw-bolder">Popular Item</h5>
                  <!-- Product price-->
                  $40.00
                </div>
              </div>
              <!-- Product actions-->
              <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                <div class="text-center"><a class="btn mt-auto detail-btn" href="#">
                  상품상세</a>
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
import all from '@/assets/js/all'

export default {
  name: "AllProductCom",
  mounted() {
    all();
  }
}
</script>

<style scoped>
@import "@/assets/css/all.css";
</style>