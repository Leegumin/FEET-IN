import http from "@/http-auth";
// import axios from 'axios';
import authHeader from '@/services/auth/AuthHeader';

// const API_URL = 'http://localhost:9000/api/test/';

class UserDataService {
  getPublicContent() {
    return http.get('/all');
  }

  // authHeader()권한이 부여된 리소스를 요청할 때 함수 의 도움으로 HTTP 헤더를 추가하여 요청함
  getUserBoard() {
    return http.get('/user', { headers: authHeader() });
  }

  // authHeader()권한이 부여된 리소스를 요청할 때 함수 의 도움으로 HTTP 헤더를 추가하여 요청함
  getModeratorBoard() {
    return http.get('/mod', { headers: authHeader() });
  }

  // authHeader()권한이 부여된 리소스를 요청할 때 함수 의 도움으로 HTTP 헤더를 추가하여 요청함
  getAdminBoard() {
    return http.get('/admin', { headers: authHeader() });
  }
}

export default new UserDataService();
