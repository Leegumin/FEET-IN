import axios from 'axios'

export default axios.create({
  //springBoot 접속할 주소 정의
  baseURL: 'http://localhost:8001/api/auth',
})