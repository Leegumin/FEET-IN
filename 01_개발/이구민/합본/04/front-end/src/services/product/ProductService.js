import http from "@/http-common";

class ProductService {
  upload(category, colors, title, model, description, price, discount, percent, saleYn, file, onUploadProgress) {
    let formData = new FormData();

    formData.append("category", category);
    formData.append("colors", colors);
    formData.append("title", title);
    formData.append("model", model);
    formData.append("description", description);
    formData.append("price", price);
    formData.append("discount", discount);
    formData.append("percent", percent);
    formData.append("saleYn", saleYn);
    formData.append("file", file);

    return http.post("/uploadProduct", formData, {
      headers: {
        "Content-Type": "multipart/form-data"
      },
      onUploadProgress
    });
  }
  
  get(id) {
    // return http.get(`/files/${id}`);
    return http.get(`/productDetail`, {
      params: {
        id : id
      }});
  }

  getFiles(params) {
    return http.get("/products", {params});
  }

  update(id, data) {
    return http.put(`/product/${id}`, data);
  }

  delete(id) {
      return http.put(`/products/deletion/${id}`);
  }

  getCat(params) {
    return http.get(`/category/`, {params});
  }

  getSale(params) {
    return http.get("/sales", {params});
  }

  getNew() {
    return http.get("/new");
  }

  getBest() {
    return http.get("/best");
  }

  getRandom() {
    return http.get("/random");
  }

  getRandomImg() {
    return http.get("/randomImg");
  }
}

export default new ProductService();
