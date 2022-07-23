<template>
  <div class="mt-5 container review-container">
    <div class="review-title text-center mt-5">
      <h3 class="position-relative d-inline-block">후기 게시판</h3>
      <br>
      <h2 class="position-relative d-inline-block">Reviews</h2>
    </div>
    <div class="text-center mt-5">
      <a class="btn mt-5" id="re-wr-btn">글쓰기</a>
    </div>
    <div style="clear: both"></div>
    <div class="card-wrapper">
      <div class="card mt-1">
        <div class="card-body profile-container">
          <div class="row">
            <div class="col-md-2 text-center pt-3 dt-profile-icon-container">
              <img src="https://via.placeholder.com/150x150" id="product-img">
            </div>
            <div class="col-md-5 mt-3">
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
                  <a class="float-right btn text-white me-1" href="#" onclick="return false;">수정</a>
                  <a class="float-right btn text-white" onclick="return false;">삭제</a>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="card mt-1">
        <div class="card-body profile-container">
          <div class="row">
            <div class="col-md-2 text-center pt-3 dt-profile-icon-container">
              <img src="https://via.placeholder.com/150x150" id="product-img">
            </div>
            <div class="col-md-5 mt-3">
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
                  <a class="float-right btn text-white me-1" href="#" onclick="return false;">수정</a>
                  <a class="float-right btn text-white" onclick="return false;">삭제</a>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import review from '@/assets/js/review'

export default {
  name: "FaqCom",
  mounted() {
    review();
  }
}
</script>

<style scoped>
@import "@/assets/css/review.css";
</style>