<template>
  <div class="container add-faq-container mt-3">
    <div v-if="!submitted">
      <div class="mb-3">
        <label for="add-faq-title" class="form-label">제목</label>
        <input type="text" class="form-control add-faq-form" id="add-faq-title" required name="title" v-model="faq.title">
      </div>
      <div class="mb-3">
        <label for="add-faq-content" class="form-label">내용</label>
        <form>
          <textarea id="add-faq-content" class="form-control add-faq-form" required name="content" v-model="faq.content"></textarea>
        </form>
      </div>

      <div class="mb-3">
        <button @click="saveFaq" class="btn btn-primary faq-btn">글쓰기</button>
      </div>
    </div>
  </div>
</template>

<script>
import FaqDataService from "@/services/FaqDataService";
import addFaq from '@/assets/js/addFaq'

export default {
  name: "add-faq",
  data() {
    // 객체 , 변수 초기화
    return {
      faq: {
        id: null,
        title: '',
        content: '',
        writer: '',
      },
      submitted: false
    }
  },
  methods: {
    saveFaq() {
      if(confirm("글을 등록 하시겠습니까?")) {
        var data = {
          title: this.faq.title,
          content: this.faq.content,
          writer: this.faq.writer
        }
        FaqDataService.create(data)
            .then(response => {
              this.faq.id = response.data.id;
              this.submitted = true;
              this.$router.push({ name: 'faqs' })
            })
            .catch(e => {
              alert(e);
            })
      }
    },
    newFaq() {
      this.submitted = false;
      this.faq = {};
    }
  },
  mounted() {
    addFaq();
  }
}
</script>

<style scoped>
@import "@/assets/css/addFaq.css";
</style>






