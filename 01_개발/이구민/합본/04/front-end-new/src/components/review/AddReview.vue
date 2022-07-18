<template>
  <div class="container add-review-container mt-3">
    <!-- TODO: 수정 필요 -->
    <!-- row 시작 -->
    <div class="row justify-content-md-center">
      <div>
        <!--    Todo : page 바 시작 -->
        <div class="col-md-12">
          <div class="mb-3" v-if="false">
            Items per Page:
            <select v-model="pageSize" @change="handlePageSizeChange($event)">
              <option v-for="size in pageSizes" :key="size" :value="size">
                <!--            size : 3, 6, 9 -->
                {{ size }}
              </option>
            </select>
          </div>

          <b-pagination
            v-if="false"
            v-model="page"
            :total-rows="count"
            :per-page="pageSize"
            prev-text="Prev"
            next-text="Next"
            @change="handlePageChange"
          ></b-pagination>
        </div>
        <!--    Todo : page 바 끝 -->
        <div class="mb-3">
          <label for="title" class="form-label">제목</label>
          <input
            type="text"
            class="form-control add-product-form"
            id="title"
            required
            name="title"
            v-model="title"
          />
        </div>

        <!-- 내용 -->
        <div class="mb-3">
          <label for="add-faq-content" class="form-label">내용</label>
          <form>
            <textarea id="add-product-content" class="form-control add-product-form" required name="content" v-model="content"></textarea>
          </form>
        </div>

        <!--  작성자 -->
        <div class="mb-3">
          <label for="writer" class="form-label">작성자</label>
          <input
              type="text"
              class="form-control add-product-form"
              id="writer"
              required
              name="writer"
              v-model="writer"
          />
        </div>

        <!-- 별점 -->
        <div class="mb-3">
          <label for="starNum" class="form-label">별점 </label>
          <select
              name="starNum"
              class="form-select add-product-form"
              id="starNum"
              v-model="starNum"
          >
            <option value="1">⭐️</option>
            <option value="2">⭐️⭐️</option>
            <option value="3">⭐️⭐️⭐️</option>
            <option value="4">⭐️⭐️⭐️⭐️</option>
            <option value="5">⭐️⭐️⭐️⭐️⭐️</option>
          </select>
        </div>
        <br />
        <!-- 파일업로드 -->
        <div>
          <div class="mb-3">
            <!-- 서버에 insert 문 호출 -->
            <button
                class="btn btn-primary product-btn mb-2"
                :disabled="!currentImage"
                @click="upload"
            >
              등록하기
            </button>
          </div>
          <div class="filebox">
            <input id="img-upload" class="upload-name" value="파일선택" disabled="disabled" />
            <label for="ex_filename"></label>
            <input type="file"
            id="ex_filename"
              @change="selectImage"
              accept="image/*"
              ref="file"
            class="upload-hidden" />
          </div>

        </div>

      </div>
    </div>
    <!-- row 끝 -->

    <!-- 프로그래스 바 시작-->
    <div v-if="currentImage" class="progress">
      <div
        class="progress-bar progress-bar-info"
        role="progressbar"
        :style="{ width: progress + '%' }"
        :aria-valuenow="progress"
        aria-valuemin="0"
        aria-valuemax="100"
      >
        {{ progress }}%
      </div>
    </div>
    <!-- 프로그래스 바 끝-->

    <!-- 미리보기 이미지 시작-->
    <div v-if="previewImage">
      <div>
        <img class="preview my-3" :src="previewImage" alt="" />
      </div>
    </div>
    <!--  미리보기 이미지 끝 -->

    <!-- 서버 에러 메세지가 있을경우 아래 출력 -->
    <div v-if="message" class="alert alert-secondary" role="alert">
      {{ message }}
    </div>

    <!-- 쇼핑 카트 형태 디자인 -->
    <!-- v-for 시작 -->
    <div v-if="false">
      <div class="card mt-3" v-for="(image, index) in imageInfo" :key="index">
        <div class="card-header">이미지 번호 : {{ index }}</div>
        <div class="row">
          <div class="col-md-5 col-md-offset-0">
            <figure>
              <img class="img-thumbnail" :src="image.url" />
            </figure>
          </div>
          <div class="col-md-6 col-md-offset-0 description">
            <p v-html="image.name"></p>
            <p class="price">{{ image.title }}</p>
            <p class="price">{{ image.content }}</p>
            <p class="price">{{ image.writer }}</p>
            <p class="price">{{ image.insertTime }}</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import UploadService from "@/services/review/UploadFilesService";
import addReview from "@/assets/js/addReview";

export default {
  name: "add-review",
  data() {
    // 변수 초기화
    return {
      currentImage: undefined, // 현재이미지
      previewImage: undefined, // 미리보기 이미지
      progress: 0, // 프로그래스바를 위한 변수
      message: "",
      imageInfo: [], // 이미지 정보 객체배열
      // todo: 이미지와 변수 같이 보내기
      id: 0,
      title: "",
      content: "",
      writer: "",
      starNum: 0,
      insertTime: "",
    };
  },
  methods: {
    // 이미지를 선택하면 변수에 저장하는 메소드
    selectImage() {
      // 파일선택상자에서 첫번째로 선택한 이미지가 저장됨
      this.currentImage = this.$refs.file.files.item(0);
      // 아래는 미리보기 이미지를 위한 주소가 저장됨
      this.previewImage = URL.createObjectURL(this.currentImage);
      this.progress = 0;
      this.message = "";
    },
    // 파일 업로드를 위한 메소드
    upload() {
      if(confirm("후기를 등록하시겠습니까?")) {
        this.progress = 0;

        // 서버에 이미지 업로드 요청(insert 문 실행)
        UploadService.upload(
            this.id,
            this.title,
            this.content,
            this.writer,
            this.starNum,
            this.currentImage,
            (event) => {
              // 파일이 업로드될때 진척상황이 저장됨(%)
              this.progress = Math.round((100 * event.loaded) / event.total);
            }
        )
            // 성공하면 then 으로 들어옴(서버에서 응답한 객체)
            .then((response) => {
              // 서버쪽 응답 메시지 저장
              this.message = response.data.message;
              // 서버쪽에 insert가 잘되었는지
              // select문 호출
              return UploadService.getFiles();
            })
            // 성공하면 then으로 들어옴(서버쪽 객체 받음)
            .then((image) => {
              this.imageInfo = image.data;
              alert("후기가 등록되었습니다.");
              this.$router.push("/products/" + this.$route.params.id)
            })
            // 실패하면 catch으로 들어옴
            // 화면에 실패메세지 출력
            .catch((err) => {
              this.progress = 0;
              this.message = "Could not upload the image!" + err;
              this.currentImage = undefined;
            });
      }
    },
    // Todo : handlePageChange, handlePageSizeChange
    handlePageChange(value) {
      this.page = value;
      this.retrieveUpload();
    },

    handlePageSizeChange(event) {
      this.pageSize = event.target.value;
      this.page = 1;
      this.retrieveUpload();
    },
  },
  // 화면이 뜨자마자 실행되는 이벤트
  mounted() {
    // springboot 서버에 이미지파일 요청
    this.id = this.$route.params.id;
    addReview();
  },
};
</script>

<style scoped>
@import "@/assets/css/addReview.css";
</style>
