// 사용자?(user) 모델을 정의
// 자바스크립트 클래스 정의
export default class User {
  // 자바스크립트 생성자 정의 ( 서버에서 자동생성해주는 데이터 제외, 직접 입력되는 데이터 )
  constructor (username, password, name, birth, email, address, phone) {
    this.username = username
    this.password = password
    this.name = name
    this.birth = birth
    this.email = email
    this.address = address
    this.phone = phone

  }
}