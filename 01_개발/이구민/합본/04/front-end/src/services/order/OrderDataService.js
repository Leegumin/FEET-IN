import http from "@/http-common";

class OrderDataService {

  getAll(params) {
    return http.get("/order",{params});
  }

  get(id) {
    return http.get(`/order/${id}`);
  }

  create(data) {
    return http.post("/order", data);
  }

  update(id, data) {
    return http.put(`/order/${id}`, data);
  }

  delete(id) {
    return http.put(`/order/deletion/${id}`);
  }

  deleteAll() {
    return http.put(`/order`);
  }
}

export default new OrderDataService();
