import http from '../http-common'

class FaqDataService {
    getAll(params) {
        // http://localhost:8080/customers?page=0&size=3
        return http.get(`/faqs`,{ params });
    }

    get(id) {
        return http.get(`/faqs/${id}`);
    }
    detail(id){
        return http.get(`/faqs/detail/${id}`)
    }

    create(data) {
        return http.post(`/faqs`, data);
    }

    update(id, data) {
        return http.put(`/faqs/${id}`, data);
    }

    delete(id) {
        return http.put(`/faqs/deletion/${id}`);
    }

    deleteAll () {
        return http.put(`/faqs`)
    }

    findByTitle (title) {
        return http.get(`/faqs?title=${ title }`)
    }
}

export default new FaqDataService()







