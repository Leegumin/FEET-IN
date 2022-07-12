<template>
  <div class = "container faq-container">
    <div class = "section-title text-center mt-5">
      <h3 class = "position-relative d-inline-block">자주 묻는 질문</h3>
      <br>
      <h2 class = "position-relative d-inline-block">FAQ</h2>
    </div>

    <div class = "text-center">
      <a class = "btn mt-5"
         id = "faq-btn"
         href = "/add-faq"
      >글쓰기</a>
    </div>

    <div class = "list-wrapper">
      <ul class = "faq-list">
        <!--        <li class="faq-list-item">-->
        <!-- *@click = "toggle_faq(faq)" 추가-->
        <li class = "faq-list-item"
            v-for = "(faq, index) in faqs"
            :key = "index"
        >
          <div>
            <span class = "Q">Q. </span><span class = "view" @click = "toggle_faq(faq)">{{ faq.title }}</span>
            <!--            <span class="Q">Q. </span><span class="view">젬ㅎㄱ어렁랑ㄹ어ㅏ</span>-->
          </div>
          <!-- *v-show="faq.show" 추가-->
          <div class = "detail"
               v-show="faq.show"
          >
            <table>
              <tbody>
              <tr>
                <td class = "A">A.</td>
                <td >{{ faq.content }}</td>
                <!--                <td>ㄹㄴ어라ㅣㄴ어힌어</td>-->
              </tr>
              <tr> <!-- 관리자만 보이는 부분 -->
                <td></td>
                <td class = "faq-btn-set">
                  <a class = "btn mt-1 faq-btn"
                     :href = "'/faqs/' + faq.id"
                  >수정</a>
                </td>
              </tr>
              </tbody>
            </table>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import FaqDataService from '@/services/FaqDataService'
import faq from '@/assets/js/faq2'

/* eslint-disable */
export default {
  name      : 'faqs',
  components: {},

  data () {
    return {
      faqs        : [],
      currentFaq  : null,
    }
  },
  methods: {
    // * 토글기능 추가
    toggle_faq (faq) {
     faq.show = !faq.show
    },

    getRequestParams (searchTitle) {
      let params = {}

      if (searchTitle) {
        params['title'] = searchTitle
      }
      return params
    },
    retrieveFaqs () {
      const params = this.getRequestParams(
          this.searchTitle,
      )

      FaqDataService.getAll(params).then(response => {
        const { faqs } = response.data
        // * 토글기능을 위한 개체 요소, 속성 추가
        let show = { show : false }
        this.faqs = faqs.map(item => Object.assign(item,show))

        console.log(response.data)
      }).catch(e => {
        console.log(e)
      })
    },
    getFaqs (id) {
      // axios 통신이용 서비스 호출( springboot )
      FaqDataService.get(id)
          // 성공하면 then으로 결과 데이터가 들어옴( response.data )
          .then(response => {

            this.currentFaq = response.data
          })
          //  실패하면 catch로 에러 데이터가 들어옴
          .catch(e => {
            alert(e)
          })
    },
  },
  mounted () {
    this.retrieveFaqs()
    faq()
  },
}
</script>

<style scoped>
@import "@/assets/css/faq2.css";
</style>