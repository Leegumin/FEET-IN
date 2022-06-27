// AuthHeader
// JWT(Java Web Token)를 헤더에 저장해서 전송함

export default function authHeader () {
  // user 항목에 대한 로컬스토리지(크롬 애플리케이션/로컬스토리지)를 확인
  // key값인 user가 있으면 user에 객체가 들어옴, 없으면 빈 객체
  let user = JSON.parse(localStorage.getItem('user'))

  // 서버페이지 접근할 때 유저가 null 아니고 토큰이 있다면 실행됨
  if (user && user.token) {
    // springBoot로 토큰 전송 시 아래 포맷을 이용
    return { Authorization: 'Bearer' + user.token }
    // *참고 : node.js 사용시 return { 'x-access-token': user.accessToken };
  }
  // 값이 없다면 빈 객체 전송
  else {
    return {}
  }
}