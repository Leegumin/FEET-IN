// AuthService.js
// login / logout / register(회원가입) 처리
import http from '@/http-auth'

class AuthService {
  // 로그인 메소드 (함수 )
  login (user) {
    return http.post('/signin', {
      username: user.username,
      password: user.password,
    })
      // 성공하면 then으로 결과가 들어옴
      .then(response => {
        console.log(response.data)
        console.log('accessToken : ' + response.data.token)

        // springBoot 서버에서 전송한 웹토큰을 받으면
        if (response.data.token) {
          // JSON.stringify : JSON객체 -> 문자열로 변환
          // JSON.parse : 문자열 -> JSON객체로 변환
          // localStorage.setItem : user라는 이름으로 로컬스토리지에 저장
          localStorage.setItem('user', JSON.stringify(response.data))
        }

        return response.data
      })
  }

  // logout
  // JWT(Java Web Token)를 로컬저장소(localstorage)에서 제거
  logout () {
    localStorage.removeItem('user')
  }

  // POST 방식 : springBoot 서버에 회원 가입을 요청(insert)
  register (user) {
    return http.post('/signup', {
      username: user.username,
      email   : user.email,
      password: user.password,
    })
  }
}

export default new AuthService()

