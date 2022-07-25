import http from "@/http-common";

class UploadFilesService {
  upload(id, title, content, writer, starNum, file, onUploadProgress) {
    let formData = new FormData();

    formData.append("id", id);
    formData.append("title", title);
    formData.append("content", content);
    formData.append("writer", writer);
    formData.append("starNum", starNum);
    formData.append("file", file);

    return http.post("/upload", formData, {
      headers: {
        "Content-Type": "multipart/form-data"
      },
      onUploadProgress
    });
  }
  
  getProductReview(params) {
    // return http.get(`/files/${id}`);
    return http.get(`/files/`, {params});
  }

  getReviews(params) {
    return http.get("/reviews", {params});
  }

  getFiles(params) {
    return http.get("/files", {params});
  }

  update(id, data) {
    return http.put(`/files/${id}`, data);
  }
  
  delete(id) {
    return http.put(`/files/deletion/${id}`);
}
}

export default new UploadFilesService();
