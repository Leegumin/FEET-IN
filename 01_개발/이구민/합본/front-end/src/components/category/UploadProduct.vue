<template>
  <div class="container add-product-container mt-3">
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
          <label for="category" class="form-label">분류</label>
          <select
            name="category"
            id="category"
            class="form-select add-product-form"
            v-model="category"
          >
            <option value="100">운동화</option>
            <option value="200">구두</option>
            <option value="300">슬리퍼</option>
            <option value="400">기타</option>
          </select>
        </div>
        <!-- 카테고리 -->
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

        <!-- 모델 -->
        <div class="mb-3">
          <label for="model" class="form-label">상품코드</label>
          <input
            type="text"
            class="form-control add-product-form"
            id="model"
            required
            name="model"
            v-model="model"
          />
        </div>

        <!-- 내용 -->
        <div class="mb-3">
          <label for="add-faq-content" class="form-label">내용</label>
          <form>
            <textarea id="add-product-content" class="form-control add-product-form" required name="content" v-model="description"></textarea>
          </form>

<!--          <label for="description" class="form-label">설명</label>-->
<!--          <input-->
<!--            type="text"-->
<!--            class="form-control add-product-form"-->
<!--            id="description"-->
<!--            required-->
<!--            name="description"-->
<!--            v-model="description"-->
<!--            style="height: 300px"-->
<!--          />-->
        </div>

        <!-- 가격 -->
        <div class="mb-3">
          <label for="price" class="form-label">가격</label>
          <input
            type="text"
            class="form-control add-product-form"
            id="price"
            required
            name="price"
            v-model="price"
          />
        </div>

        <!-- 세일 적용 -->
        <div class="form-group">
          <button class="btn btn-primary product-btn mb-2" @click="sale">
            세일 적용
          </button>
          <label for="sale">Sale</label>&nbsp;
          <input
            type="number"
            class="form-control add-product-form"
            id="sale"
            required
            v-model="percent"
            name="sale"
          />
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
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ProductService from "@/services/product/ProductService";
import addProduct from '@/assets/js/addProduct'

export default {
  name: "upload-image",
  data() {
    // 변수 초기화
    return {
      currentImage: undefined, // 현재이미지
      previewImage: undefined, // 미리보기 이미지
      progress: 0, // 프로그래스바를 위한 변수
      message: "",
      imageInfo: [], // 이미지 정보 객체배열
      // todo: 이미지와 변수 같이 보내기
      category: "",
      title: "",
      model: "",
      description: "",
      price: 0,
      percent: 0,
      discount: 0,
      saleYn: "N",
      insertTime: "",
      // Todo : 아래 변수 추가
      page: 1,
      count: 0,
      pageSize: 15,

      pageSizes: [9],
    };
  },
  methods: {
    // Todo : getRequestParams 추가
    getRequestParams(searchTitle, page, pageSize) {
      let params = {};

      if (searchTitle) {
        params["title"] = searchTitle;
      }

      if (page) {
        params["page"] = page - 1;
      }

      if (pageSize) {
        params["size"] = pageSize;
      }

      return params;
    },
    // todo :
    retrieveProduct() {
      // Todo : getRequestParams 호출 추가
      const params = this.getRequestParams(
        this.searchTitle,
        this.page,
        this.pageSize
      );
      ProductService.getFiles(params)
        // 성공하면 then에 들어옴(객체, 응답메세지)
        .then((response) => {
          // response.data : 서버쪽에서 전송된 객체
          this.imageInfo = response.data;
          this.count = this.imageInfo[0].totalItems;
        });
    },
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
      if(confirm("상품을 등록하시겠습니까?")) {
        this.progress = 0;

        // 서버에 이미지 업로드 요청(insert 문 실행)
        ProductService.upload(
            this.category,
            this.title,
            this.model,
            this.description,
            this.price,
            this.discount,
            this.percent,
            this.saleYn,
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
              return ProductService.getFiles();
            })
            // 성공하면 then으로 들어옴(서버쪽 객체 받음)
            .then((image) => {
              this.imageInfo = image.data;
              alert("상품이 등록되었습니다.");
              this.$router.push({ name: 'admin-page' })
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
      this.retrieveProduct();
    },

    handlePageSizeChange(event) {
      this.pageSize = event.target.value;
      this.page = 1;
      this.retrieveProduct();
    },

    sale() {
      if (this.percent >= 1) {
        let result = 0;

        result = (this.price * this.percent) / 100;

        this.discount = result;
        this.saleYn = "Y";
      } else {
        this.discount = 0;
        this.saleYn = "N";
      }
    },
  },
  // 화면이 뜨자마자 실행되는 이벤트
  mounted() {
    // springboot 서버에 이미지파일 요청
    this.retrieveProduct();
    addProduct();
  },
};
</script>

<style scoped>
@import "@/assets/css/addProduct.css";

.btn {
  width:150px;
}
</style>
