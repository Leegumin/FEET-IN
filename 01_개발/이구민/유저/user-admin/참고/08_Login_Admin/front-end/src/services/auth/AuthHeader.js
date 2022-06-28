export default function authHeader() {
  // user항목 에 대한 로컬 저장소를 확인
  // (JWT) 로 로그인한 경우 user HTTP accessToken Authorization 헤더를 반환
  // 아니면 빈 객체를 반환
  let user = JSON.parse(localStorage.getItem('user'));

  // 보호된 리소스에 액세스하는 경우 HTTP 요청에는 Authorization 헤더가 필요
  // 토큰을 헤더에 담아서 springboot 로 전송 ( 서버 확인용 )
  if (user && user.token) {
    return { Authorization: 'Bearer ' + user.token }; // for Spring Boot back-end
    // return { 'x-access-token': user.accessToken };       // for Node.js Express back-end
  } else {
    return {};
  }
}
