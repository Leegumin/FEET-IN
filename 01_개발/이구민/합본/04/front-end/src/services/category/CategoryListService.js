import http from "@/http-common";

class CategoryListService {
  getCat() {
    return http.get("/category");
  }
}

export default new CategoryListService();
