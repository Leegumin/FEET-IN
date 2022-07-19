<template>
  <div>
    <section class="py-5">
      <div class="container px-4 px-lg-5 my-5 detail-container">
        <div class="row gx-4 gx-lg-5 align-items-center">
          <div class="col-md-6"><img class="card-img-top mb-5 mb-md-0" id="pic"
                                     src="@/assets/images/product/c1-1.jpeg" alt="..."/>
            <div id="small-pic">
              <img src="@/assets/images/product/b1-1.jpeg" class="small me-1" width="100" height="100">
              <img src="@/assets/images/product/r1-1.jpeg" class="small me-1" width="100" height="100">
              <img src="@/assets/images/product/c2-1.jpeg" class="small me-1" width="100" height="100">
            </div>
          </div>
          <div class="col-md-6 detail-title">
            <div class="small mb-1">SKU: BST-498</div>
            <h1 class="display-5 fw-bolder">Shop item template</h1>
            <div class="fs-5 mb-5">
              <span class="text-decoration-line-through">$45.00</span>
              <span>$40.00</span>
            </div>
            <p class="lead">Lorem ipsum dolor sit amet consectetur adipisicing elit. Praesentium at dolorem quidem modi.
              Nam sequi consequatur obcaecati excepturi alias magni, accusamus eius blanditiis delectus ipsam minima ea
              iste laborum vero?</p>
            <div class="d-flex">
              <input class="form-control text-center me-2" id="inputQuantity" type="number" value="1"
                     style="max-width: 4rem"/>
              <select class="form-select me-2" aria-label="Default select example" id="option-sel">
                <option selected>Option</option>
                <option value="1">220</option>
                <option value="2">230</option>
                <option value="3">235</option>
                <option value="4">240</option>
                <option value="5">245</option>
                <option value="6">250</option>
                <option value="7">255</option>
                <option value="8">260</option>
              </select>
              <button class="btn btn-outline-dark flex-shrink-0 add-btn me-2" type="button">
                <i class="bi-cart-fill me-1" id="cart-icon"></i>
                장바구니 담기
              </button>
              <button class="btn btn-outline-dark flex-shrink-0 add-btn" id="like-btn" type="button">
                <i class="fa-solid fa-heart fa-1x"></i>
                찜 하기
              </button>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- Related items section-->
    <section class="py-0">
      <div class="container px-4 px-lg-5">

        <!-- * review -->
        <div class="review-set mt-4">
          <h2 class="fw-bolder mb-4 review-title">Product Review</h2>
          <div class="card">
            <div class="card-body profile-container">
              <div class="row">
                <div class="col-md-2 text-center pt-4 dt-profile-icon-container">
                  <i class="fa-solid fa-user-large mx-2 fa-4x" id="dt-profile-icon"></i>
                </div>
                <div class="col-md-5">
                  <div class="float-left user-name">Maniruzzaman Akash</div>
                  <p>
                    <span class="float-right"><i class="text-warning fa fa-star"></i></span>
                    <span class="float-right"><i class="text-warning fa fa-star"></i></span>
                    <span class="float-right"><i class="text-warning fa fa-star"></i></span>
                    <span class="float-right"><i class="text-warning fa fa-star"></i></span>
                  </p>
                  <a class="review-content-title" href="#" onclick="return false;">Lorem Ipsum</a>
                  <div class="review-content">
                    Lorem Ipsum is simply dummy text of the pr make but also the leap into electronic typesetting,
                    remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets
                    containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus
                    PageMaker including versions of Lorem Ipsum.
                    <div class="mt-3 re-btn">
                      <a class="float-right btn text-white me-2" href="#" onclick="return false;">Edit</a>
                      <a class="float-right btn text-white" onclick="return false;">Delete</a>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="card mt-1">
            <div class="card-body profile-container">
              <div class="row">
                <div class="col-md-2 text-center pt-4 dt-profile-icon-container">
                  <i class="fa-solid fa-user-large mx-2 fa-4x" id="dt-profile-icon"></i>
                </div>
                <div class="col-md-5">
                  <div class="float-left user-name">{name}</div>
                  <p>
                    <span class="float-right"><i class="text-warning fa fa-star"></i></span>
                    <span class="float-right"><i class="text-warning fa fa-star"></i></span>
                    <span class="float-right"><i class="text-warning fa fa-star"></i></span>
                    <span class="float-right"><i class="text-warning fa fa-star"></i></span>
                  </p>
                  <a class="review-content-title" href="#" onclick="return false;">{title}</a>
                  <div class="review-content">
                    {content}
                    <div class="mt-3 re-btn">
                      <a class="float-right btn text-white me-2" href="#" onclick="return false;">Edit</a>
                      <a class="float-right btn text-white" onclick="return false;">Delete</a>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>

        <!-- * other -->
        <div class="other-set mt-4">
          <h2 class="fw-bolder mb-4 other-title mt-5">Other Products</h2>
          <a class="fw-bolder mb-4 other-link mt-5" href="/all">전체 상품 보기 <span><i
              class="fa-solid fa-angles-right"></i></span></a>
          <div
              class="other-collection-list row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
            <div class="col mb-5 fav">
              <div class="card h-100">
                <div class="badge position-absolute fav-icon" style="top: 0.5rem; right: 0.5rem">
                  <i class="fa-solid fa-heart fa-3x"></i>
                </div>
                <!-- Product image-->
                <img class="card-img-top" src="@/assets/images/product/c1-1.jpeg" alt="..."/>
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
            <div class="col mb-5 sale">
              <div class="card h-100">
                <!-- Sale badge-->
                <div class="badge position-absolute sale-badge" style="top: 0.5rem; right: 0.5rem">
                  Sale
                </div>
                <!-- Product image-->
                <img class="card-img-top" src="@/assets/images/product/c2-1.jpeg" alt="..."/>
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
            <div class="col mb-5 sale">
              <div class="card h-100">
                <!-- Sale badge-->
                <div class="badge position-absolute sale-badge" style="top: 0.5rem; right: 0.5rem">
                  Sale
                </div>
                <!-- Product image-->
                <img class="card-img-top" src="@/assets/images/product/b1-1.jpeg" alt="..."/>
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
            <div class="col mb-5 best">
              <div class="card h-100">
                <!-- Product image-->
                <img class="card-img-top" src="@/assets/images/product/s1-1.jpeg" alt="..."/>
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
      </div>
    </section>
  </div>
</template>

<script>
import detail from '@/assets/js/detail'

export default {
  name: "DetailCom",
  mounted() {
    detail();
  }
}
</script>

<style scoped>
@import "@/assets/css/detail.css";
</style>