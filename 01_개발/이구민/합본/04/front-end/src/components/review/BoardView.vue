<template>
  <div class="mt-5 container review-container">
    <div class="review-title text-center mt-5">
      <h3 class="position-relative d-inline-block">후기 게시판</h3>
      <br>
      <h2 class="position-relative d-inline-block">Reviews</h2>
    </div>
    <!-- TODO: 수정시작 -->
    <!-- search 관련 입력박스 시작 -->
    <div class="col-md-5">
      <div class="input-group mb-3">
        <input
          type="text"
          class="form-control"
          placeholder="Search by Title"
          v-model="searchTitle"
        />
        <div class="input-group-append">
          <button class="btn btn-outline-secondary" type="button"
            @click="page = 1; retrieveBoards();">
            Search
          </button>
        </div>
      </div>
    </div>
    <!-- search 관련 입력박스 끝 -->

    <!-- TODO: 페이지 바 시작 -->
    <div class="col-md-12" v-if="false">
      <div class="md-3">
        Items per Page :
        <select v-model="pageSize" @change="handlePageSizeChange($event)">
          <!-- pageSizes : [3,6,9] -->
          <option v-for="size in pageSizes" :key="size" :value="size">
            {{ size }}
          </option>
        </select>
      </div>
    </div>

    <div style="clear: both"></div>
    <div class="card-wrapper" v-for="(board, index) in boards" :key="index">
      <div class="card mt-1">
        <div class="card-body profile-container">
          <div class="row">
            <div class="col-md-2 text-center pt-3 dt-profile-icon-container">
              <img :src="board.url2" id="product-img" />
            </div>
            <div class="col-md-5 mt-3">
              <div class="float-left user-name">{{ board.writer }}</div>
              <!-- 별점추가 -->
              <span v-for="n in board.starNum" :key="n"><img class="imgStar" style="height:20px;width:20px;" src="../../assets/images/staricon.png"></span>
              <div class="mt-2"></div>
              <a class="review-content-title" @click="toogleShow(index)" style="cursor:pointer;">
                {{ board.title }}
              </a>
              <div class="review mt-2" v-if="!board.type">
                <div>
                  {{ board.content }}
                </div>
                <div>
                  <img :src="board.url" id="product-img" />
                </div>
                <div class="mt-5 re-btn">
                  <a class="btn text-black" @click="deleteBoard(board.reviewId)" style="float: right">삭제</a>
                </div>
              </div>
              <div>{{ board.insertTime }}</div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <b-pagination
      class="mt-2"
      v-model="page"
      :total-rows="count"
      :per-page="pageSize"
      align="center"
      @change="handlePageChange"
    >
    </b-pagination>
  </div>
</template>

<script>
import UploadService from "@/services/review/UploadFilesService";
import review from "@/assets/js/review";

/* eslint-disable */
export default {
  name: "boards",
  // 변수를 초기화 하기
  data() {
    return {
      boards: [],
      isShow1: true,
      // Todo: title => searchEmail 변경 수정
      searchTitle: "",
      // Todo:아래 변수 추가
      page: 1,
      count: 0,
      pageSize: 9,
      pageSizes: [9],
    };
  },
  methods: {
    getRequestParams(searchTitle, page, pageSize) {
      let params = {};
      // searchEmail값이 있으면 매개변수로 전송

      if (searchTitle) {
        params["searchTitle"] = searchTitle;
      }

      if (page) {
        params["page"] = page - 1;
      }

      if (pageSize) {
        params["size"] = pageSize;
      }
      return params;
    },
    // onclick(id) {
    //   this.$router.push("/files/" + id);
    // },
    toogleShow(index) {
      this.boards[index].type = !this.boards[index].type;
    },

    deleteBoard(id) {
      UploadService.delete(id)
        .then(() => {
          this.retrieveBoards();
        })
        .catch((e) => {
          alert(e);
        });
    },

    // 모든 회원 조회 서비스 호출
    retrieveBoards() {
      // Todo : getRequestParams 호출 추가
      const params = this.getRequestParams(
        this.searchTitle,
        this.page,
        this.pageSize
      );
      UploadService.getFiles(params)
        // 성공하면 then에 들어옴(객체, 응답메세지)
        .then((response) => {
          // response.data : 서버쪽에서 전송된 객체
          this.boards = response.data;
          this.count = this.boards[0].totalItems;
        });
    },
    // todo : 신규 메소드 2개 추가
    // 현재 페이지를 다른 페이지번호로 변경 시 호출되는 메소드
    handlePageChange(value) {
      // 현재 페이지 변경
      this.page = value;
      // 재조회
      this.retrieveBoards();
    },
    // 페이지 사이즈(3,6,9) 변경 시 호출되는 메소드
    handlePageSizeChange(event) {
      this.pageSize = event.target.value; //select 박스의 값 (3,6,9)
      this.page = 1;
      // 재조회
      this.retrieveBoards();
    },

  },
  //화면이 뜨자마자 실행되는 이벤트(모든 회원조회가 실행됨)
  mounted() {
    this.retrieveBoards();
    review();
  },
};
</script>

<style scoped>
@import "@/assets/css/review.css";
</style>
