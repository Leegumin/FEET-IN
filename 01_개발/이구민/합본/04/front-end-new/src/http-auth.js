import axios from 'axios'

export default axios.create({
  //springBoot 접속할 주소 정의
  baseURL: "http://192.168.0.12:8000/api/auth",

})