<template>
  <div class="container faq-container">
    <div class = "section-title mb-3 mb-sm-4 mt-5 text-center" id="faq-title">FAQ</div>
    <table class="table mt-5 mb-5">
      <thead class="text-center">
      <tr class="faq-tr-header">
        <th scope="col" class="no pb-3 pt-3">No</th>
        <th scope="col" class="title pb-3 pt-3">Title</th>
      </tr>
      </thead>
      <tbody>
      <tr>
        <th scope="row" class="th-content pb-3 pt-3">{id}</th>
        <td class="td-content pb-3 pt-3"><a href="#" class="view">클릭하면 글 내용이 보입니다.</a></td>
      </tr>
      <!-- 상세내용 -->
      <tr class="detail" style="display: none">
        <th scope="row" class="th-content pb-3 pt-3"></th>
        <td class="td-content-detail pb-3 pt-3">어쩌구~~</td>
      </tr>
      <tr>
        <th scope="row" class="th-content pb-3 pt-3">{id}</th>
        <td class="td-content pb-3 pt-3"><a href="#" class="view">클릭하면 글 내용이 보입니다.</a></td>
      </tr>
      <!-- 상세내용 -->
      <tr class="detail" style="display: none">
        <th scope="row" class="th-content pb-3 pt-3"></th>
        <td class="td-content-detail pb-3 pt-3">어쩌구~~</td>
      </tr>
      <tr>
        <th scope="row" class="th-content pb-3 pt-3">{id}</th>
        <td class="td-content pb-3 pt-3"><a href="#" class="view">{title}</a></td>
      </tr>
      <!-- 상세내용 -->
      <tr class="detail" style="display: none">
        <th scope="row" class="th-content pb-3 pt-3"></th>
        <td class="td-content-detail pb-3 pt-3">{content}</td>
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