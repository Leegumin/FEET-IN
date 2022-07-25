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
  // user라고만 넣어도 인설트 되는지 확인
  register (user) {
    return http.post('/signup', {
      username: user.username,
      password: user.password,
      name    : user.name,
      birth   : user.birth,
      email   : user.email,
      address : user.address,
      phone   : user.phone,
      role    : user.role,
    })
  }

  // PUT 방식 : springBoot 서버에 회원 수정을 요청(update)
  update (user) {
    return http.put(`/user-update`, {
      name       : user.name,
      email      : user.email,
      password   : user.password,
      newPassword: user.newPassword,
      address    : user.address,
      phone      : user.phone,
      username   : user.username,
    })
  }

  /*
   // PUT 방식 : springBoot 서버에 회원 수정을 요청(update)
   update (id, user) {
   return http.put(`/user-update/${ id }`, {
   email   : user.email,
   password: user.password,
   address : user.address,
   phone   : user.phone,
   username: user.username,
   })
   }
   */

  // PUT 방식 : springBoot 서버에 회원 탈퇴를 요청(update)
  delete (id) {
    return http.put(`/user-deletion/${ id }`)
  }

  // 아이디 찾기 요청
  findID (user) {
    return http.post(`/user-findid`, {
      name    : user.name,
      email   : user.email,
      username: user.username,
    })
  }

  // 패스워드 찾기(변경) 요청
  findPassword (user) {
    return http.put(`/user-findpassword`, {
      username   : user.username,
      newPassword: user.newPassword,
    })
  }

}

export default new AuthService()

