// 사용자 모델 정의
// 인증을 위한 사용자 모델
export default class User {
  constructor(username, email, password, role) {
    this.username = username;
    this.email = email;
    this.password = password;
    // todo: 추가
    this.role = role;
  }
}
