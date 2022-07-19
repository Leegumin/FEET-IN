import axios from 'axios'

export default axios.create({
  //springBoot 접속할 주소 정의
  baseURL: "http://localhost:8000/api/auth",
  // 학원컴퓨터
  // baseURL: "http://192.168.0.166:8000/api/auth",

})