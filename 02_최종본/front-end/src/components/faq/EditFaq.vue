<template>
  <!--    기존 회원이 있을 경우 currentCustomer != null -->
  <div class="container mt-3 edit-faq-container" v-if="faq">
<!--    mb-3 : m(margin), p(padding) : l(left), r(right), b(bottom) , 1 : 25px, 2 50px -->
        <div class="mb-3">
          <label for="edit-faq-title" class="form-label">제목</label>
          <input type="text" class="form-control edit-faq-form" id="edit-faq-title" required name="title" v-model="faq.title">
        </div>
        <div class="mb-3">
          <label for="edit-faq-content" class="form-label">내용</label>
          <form>
            <textarea id="edit-faq-content" class="form-control edit-faq-form" required name="content" v-model="faq.content"></textarea>
          </form>
        </div>
      <div class="mb-3">
        <button @click="deleteFaq" class="btn btn-primary me-1 faq-btn">삭제</button>
      </div>
<!--    수정버튼을 클릭후 성공했을때 아래가 보임 -->
<!--    <div class="alert alert-success"-->
<!--         role="alert"-->
<!--         v-if="message"-->
<!--    >-->
<!--      {{ message }}-->
<!--    </div>-->
</div>
</template>

<script>
// 화면 설명 : 회원정보 수정 /삭제 화면
import FaqDataService from "@/services/FaqDataService";
import editFaq from '@/assets/js/editFaq'

export default {
  name: "edit-faq",
  data() {
    return {
      // 객체 ( 회원정보 )
      faq: null,
      message: ''
    }
  },
  methods: {
    // id에 해당하는 회원정보를 조회하는 서비스를 요청(springboot)
    getFaqs(id) {
      // axios 통신이용 서비스 호출( springboot )
      console.log(id)
      FaqDataService.detail(id)
      // 성공하면 then으로 결과 데이터가 들어옴( response.data )
          .then(response => {
            this.faq = response.data;
          })
    //  실패하면 catch로 에러 데이터가 들어옴
          .catch(e => {
            alert(e);
          })
    },
    updateFaq() {
      if(confirm("정말 수정 하시겠습니까?")) {
        FaqDataService
            .update(this.faq.id, this.faq )
            .then(response => {
              console.log(response.data)
              this.$router.push({ name: 'faqs' })
            })
            .catch(e => {
              alert(e);
            })
      }
    },
    deleteFaq() {
      if(confirm("정말 삭제 하시겠습니까?")) {
        FaqDataService.delete(this.faq.id)
            .then(response => {
              console.log(response.data)
              this.$router.push({ name: 'faqs' })
            })
            //  실패하면 catch로 에러 데이터가 들어옴
            .catch(e => {
              alert(e);
              //  console.log(e)
            })
      }
    }
  },
  mounted() {
    editFaq();
    this.getFaqs(this.$route.params.id);
  }
}
</script>

<style>
@import "@/assets/css/editFaq.css";
</style>