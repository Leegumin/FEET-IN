import http from "@/http-common";

class LikeService {
    getLike(params) {
        return http.get("/likes", {params});
    }

    getMyLike(params) {
        return http.get("/mylikes", {params});
    }

    addLike(data) {
        return http.post("/like", data);
    }

    deleteLike(data) {
        return http.delete("/like/deletion", {data});
    }
    
    checkLikeYn(data) {
        return http.post("/likeyn", data);
    }
}

export default new LikeService();
