<template>
  <div class="container faq-container">
    <!--    <div class = "section-title mb-3 mb-sm-4 mt-5 text-center" id="faq-title">FAQ</div>-->
    <div class="section-title text-center mt-5">
      <h3 class="position-relative d-inline-block">자주 묻는 질문</h3>
      <br>
      <h2 class="position-relative d-inline-block">FAQ</h2>
    </div>


    <div class="text-center">
      <a class="btn mt-5" id="faq-btn">글쓰기</a>
    </div>
    <table class="table faq-table">
      <thead class="text-center">
      <tr class="faq-tr-header">
        <th scope="col" class="no pb-3 pt-3">No</th>
        <th scope="col" class="title pb-3 pt-3">Title</th>
      </tr>
      </thead>
      <tbody>
      <tr>
        <th scope="row" class="th-content pb-3 pt-3">{id}</th>
        <td class="td-content pb-3 pt-3">
          <a href="#" class="view">
            클릭하면 글 내용이 보입니다.
          </a>
        </td>
      </tr>
      <!-- 상세내용 -->
      <tr class="detail" style="display: none">
        <th scope="row" class="th-content pb-3 pt-3"></th>
        <td class="td-content-detail pb-3 pt-3">
          어쩌구~~
          <a class="btn mt-5 faq-btn mx-1">수정</a>
          <a class="btn mt-5 faq-btn">삭제</a>
        </td>
      </tr>
      <tr>
        <th scope="row" class="th-content pb-3 pt-3">{id}</th>
        <td class="td-content pb-3 pt-3"><a href="#" class="view">클릭하면 글 내용이 보입니다.</a></td>
      </tr>
      <!-- 상세내용 -->
      <tr class="detail" style="display: none">
        <th scope="row" class="th-content pb-3 pt-3"></th>
        <td class="td-content-detail pb-3 pt-3">
          어쩌구~~
          <a class="btn mt-5 faq-btn mx-1">수정</a>
          <a class="btn mt-5 faq-btn">삭제</a>
        </td>
      </tr>
      <tr>
        <th scope="row" class="th-content pb-3 pt-3">{id}</th>
        <td class="td-content pb-3 pt-3"><a href="#" class="view">{title}</a></td>
      </tr>
      <!-- 상세내용 -->
      <tr class="detail" style="display: none">
        <th scope="row" class="th-content pb-3 pt-3"></th>
        <td class="td-content-detail pb-3 pt-3">
          {content}
          <a class="btn mt-5 faq-btn mx-1">수정</a>
          <a class="btn mt-5 faq-btn">삭제</a>
        </td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import faq from '@/assets/js/faq'

export default {
  name: "FaqCom",
  mounted() {
    faq();
  }
}
</script>

<style scoped>
@import "@/assets/css/faq.css";
</style>