import http from '@/http-auth'
// import axios from 'axios';
// const API_URL = 'http://192.168.0.25:9000/api/auth/';

class AuthService {
  // POST {username, password} & JWT를 로컬 저장소에 저장
  login (user) {
    // return axios
    return http.post('signin', {
      username: user.username,
      password: user.password,
    }).then(response => {
      console.log(response.data)
      console.log('token' + response.data.token)

      // 서버에서 받은 JWT를 로컬 저장소에 저장
      if (response.data.token) {
        localStorage.setItem('user', JSON.stringify(response.data))
      }

      return response.data
    })
  }

  // JWT를 로컬 저장소(웹 브라우저)에서 제거
  logout () {
    localStorage.removeItem('user')
  }

  // todo:
  // POST {사용자 이름, 이메일, 비밀번호, 역할}
  register (user) {
    return http.post('signup', {
      username: user.username,
      email   : user.email,
      password: user.password,
      role    : user.role,
    })
  }
}

export default new AuthService()
