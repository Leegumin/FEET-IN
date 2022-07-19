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
            v-model="currentProduct.category"
          >
            <option value="100">운동화</option>
            <option value="200">슬리퍼</option>
            <option value="300">구두</option>
            <option value="400">기타</option>
          </select>
        </div>

        <div class="mb-3">
          <label for="colors" class="form-label">색상</label>
          <select
              name="colors"
              id="colors"
              class="form-select add-product-form"
              v-model="currentProduct.colors"
          >
            <option value="gray">회색</option>
            <option value="red">빨간색</option>
            <option value="yellow">노란색</option>
            <option value="green">초록색</option>
            <option value="blue">파란색</option>
            <option value="white">흰색</option>
            <option value="black">검정색</option>
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
            v-model="currentProduct.title"
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
            v-model="currentProduct.model"
          />
        </div>

        <!-- 내용 -->
        <div class="mb-3">
          <label for="add-faq-content" class="form-label">내용</label>
          <form>
            <textarea id="add-product-content" class="form-control add-product-form" required name="content" v-model="currentProduct.description"></textarea>
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
            v-model="currentProduct.price"
          />
        </div>

        <!-- 세일 적용 -->
        <div class="form-group">
          <button class="btn btn-primary product-btn mb-2" @click.prevent="sale">
            세일 적용
          </button>
          <label for="sale">Sale</label>&nbsp;
          <input
            type="number"
            class="form-control add-product-form"
            id="sale"
            required
            v-model="currentProduct.percent"
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
                @click="updateProduct"
            >
              수정완료
            </button>
          </div>
        </div>
      </div>
    </div>
    <!-- row 끝 -->


  </div>
</template>

<script>
import ProductService from "@/services/product/ProductService";
import addProduct from "@/assets/js/addProduct";

/* eslint-disable */
export default {
  name: "retouch-product",
  data() {
    return {
      currentProduct: null,
      message: "",
    };
  },
  methods: {
    getProduct(id) {
      ProductService.get(id)
          .then((response) => {
            this.currentProduct = response.data;
            console.log(response.data);
          })
          .catch((e) => {
            console.log(e);
          });
    },

    updateProduct() {
      if(confirm("상품 정보를 변경하시겠습니까?")) {
        ProductService.update(this.currentProduct.id, this.currentProduct)
            .then((response) => {
              console.log(response.data);
              alert("상품 정보가 변경되었습니다.");
              this.$router.push('/admin-page')
            })
            .catch((e) => {
              console.log(e);
            });
      }
    },

    sale() {
      if(this.currentProduct.percent >= 1) {
        let result = 0;

        result = this.currentProduct.price * this.currentProduct.percent / 100;

        this.currentProduct.discount = result;
        this.currentProduct.saleYn = "Y";
        alert("세일이 적용되었습니다.");
      }else {
        this.currentProduct.discount = 0;
        this.currentProduct.saleYn = "N";
      }
    }
  },
  mounted() {
    this.message = "";
    this.getProduct(this.$route.params.id);
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
