import http from "@/http-common";

class CartDataService {

  getAll(params) {
    return http.get("/cart",{params});
  }

  getCartDetail(params) {
    // return http.get(`/files/${id}`);
    return http.get(`/cart/`, {params});
  }

  get(id) {
    return http.get(`/cart/${id}`);
  }

  createCart(data) {
    return http.post("/cart", data);
  }

  insertCart(data) {
    return http.post("/cartorder", data);
  }

  updateCart(id, data) {
    return http.put(`/updatecart/${id}`, data);
  }

  delete(id) {
    return http.put(`/cart/deletion/${id}`);
  }

  deleteAll() {
    return http.put(`/cart`);
  }
}

export default new CartDataService();
