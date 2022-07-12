<template>
<!--  <div v-if="currentProduct" class="edit-form">-->
<!--    <h4>상품을 수정하세요</h4>-->
<!--    <form>-->
<!--      <div class="form-group">-->
<!--        <label for="category" class="form-label">분류</label>-->
<!--        <select name="category" id="category" v-model="currentProduct.category">-->
<!--          <option value="100">운동화</option>-->
<!--          <option value="200">슬리퍼</option>-->
<!--          <option value="300">구두</option>-->
<!--          <option value="400">기타</option>-->
<!--        </select>-->
<!--      </div>-->
<!--      <div class="form-group">-->
<!--        <label for="title">제목</label>-->
<!--        <input-->
<!--          type="text"-->
<!--          class="form-control"-->
<!--          id="title"-->
<!--          v-model="currentProduct.title"-->
<!--        />-->
<!--      </div>-->

<!--        &lt;!&ndash; 모델 &ndash;&gt;-->
<!--        <div class="mb-3">-->
<!--          <label for="model" class="form-label">상품코드</label>-->
<!--          <input-->
<!--            type="text"-->
<!--            class="form-control"-->
<!--            id="model"-->
<!--            required-->
<!--            name="model"-->
<!--            v-model="currentProduct.model"-->
<!--          />-->
<!--        </div>-->

<!--      <div class="form-group">-->
<!--        <label for="description">설명</label>-->
<!--        <input-->
<!--          type="text"-->
<!--          class="form-control"-->
<!--          id="description"-->
<!--          v-model="currentProduct.description"-->
<!--        />-->
<!--      </div>-->
<!--      <div class="form-group">-->
<!--        <label for="price">가격</label>-->
<!--        <input-->
<!--          type="text"-->
<!--          class="form-control"-->
<!--          id="price"-->
<!--          v-model="currentProduct.price"-->
<!--        />-->
<!--      </div>-->

<!--        &lt;!&ndash; 세일 적용 &ndash;&gt;-->
<!--        <div class="form-group">-->
<!--          <label for="sale">Sale</label>&nbsp;-->
<!--          <button class="btn btn-primary btn-sm" @click.prevent="sale">세일 적용</button>-->
<!--          <input-->
<!--            type="number"-->
<!--            class="form-control"-->
<!--            id="sale"-->
<!--            required-->
<!--            v-model="currentProduct.percent"-->
<!--            name="sale"-->
<!--          />-->
<!--        </div>-->
<!--    </form>-->

<!--    <button type="submit"  @click="updateProduct">-->
<!--      업데이트-->
<!--    </button>-->
<!--    <p>{{ message }}</p>-->
<!--  </div>-->

<!--  <div v-else>-->
<!--    <br />-->
<!--  </div>-->



  <div v-if="currentProduct" class="edit-form">
    <h4>상품을 수정하세요</h4>
    <form>
      <div class="form-group">
        <label for="category" class="form-label">분류</label>
        <select name="category" id="category" v-model="currentProduct.category">
          <option value="100">운동화</option>
          <option value="200">슬리퍼</option>
          <option value="300">구두</option>
          <option value="400">기타</option>
        </select>
      </div>
      <div class="form-group">
        <label for="title">제목</label>
        <input
            type="text"
            class="form-control"
            id="title"
            v-model="currentProduct.title"
        />
      </div>

      <!-- 모델 -->
      <div class="mb-3">
        <label for="model" class="form-label">상품코드</label>
        <input
            type="text"
            class="form-control"
            id="model"
            required
            name="model"
            v-model="currentProduct.model"
        />
      </div>

      <div class="form-group">
        <label for="description">설명</label>
        <input
            type="text"
            class="form-control"
            id="description"
            v-model="currentProduct.description"
        />
      </div>
      <div class="form-group">
        <label for="price">가격</label>
        <input
            type="text"
            class="form-control"
            id="price"
            v-model="currentProduct.price"
        />
      </div>

      <!-- 세일 적용 -->
      <div class="form-group">
        <label for="sale">Sale</label>&nbsp;
        <button class="btn btn-primary btn-sm" @click.prevent="sale">세일 적용</button>
        <input
            type="number"
            class="form-control"
            id="sale"
            required
            v-model="currentProduct.percent"
            name="sale"
        />
      </div>
    </form>

    <button type="submit"  @click="updateProduct">
      업데이트
    </button>
    <p>{{ message }}</p>
  </div>

  <div v-else>
    <br />
  </div>
</template>

<script>
import ProductService from "@/services/product/ProductService";

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
      ProductService.update(this.currentProduct.id, this.currentProduct)
        .then((response) => {
          console.log(response.data);
          this.message = "업데이트 성공";
        })
        .catch((e) => {
          console.log(e);
        });
    },

    sale() {
      if(this.currentProduct.percent >= 1) {
        let result = 0;

        result = this.currentProduct.price * this.currentProduct.percent / 100;

        this.currentProduct.discount = result;
        this.currentProduct.saleYn = "Y";
      }else {
        this.currentProduct.discount = 0;
        this.currentProduct.saleYn = "N";
      }
    }
  },
  mounted() {
    this.message = "";
    this.getProduct(this.$route.params.id);
  },
};
</script>

<style>
.edit-form {
  max-width: 300px;
  margin: auto;
}
</style>
