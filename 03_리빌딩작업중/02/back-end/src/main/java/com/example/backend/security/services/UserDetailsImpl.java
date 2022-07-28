package com.example.backend.security.services;

import com.example.backend.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * packageName : com.example.backend.dao
 * fileName : UserDao
 * author : gumin
 * date : 2022/06/23
 * description : 유저 시큐리티용 객체&서비스
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/06/23         gumin          최초 생성
 */

@Getter
@Setter
@ToString
// @AllArgsConstructor : UserDetailsImp ( 모든 생성자 )
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {
  // *https://m.blog.naver.com/writer0713/220922099055 참고
  private static final long serialVersionUID = 1L;

  // 유저 등록 시 고유 번호
  private Long id;
  // 유저 로그인용 아이디
  private String username;

  // *@JsonIgnore : 직렬화 역직렬화에 사용되는 논리적 프로퍼티(속성..) 값을 무시
  // *직렬화 : 객체를 직렬화하여 전송 가능한 형태로 만드는 것을 의미한다. 객체들의 데이터를 연속적인 데이터로 변형하여 Stream을 통해 데이터를 읽도록 해준다.
  // *역직렬화 : 직렬화된 파일 등을 역으로 직렬화하여 다시 객체의 형태로 만드는 것을 의미한다. 저장된 파일을 읽거나 전송된 스트림 데이터를 읽어 원래 객체의 형태로 복원한다.
  @JsonIgnore
  // 비밀번호
  private String password;
  // 이름
  private String name;
  // 주소
  private String address;
  // 핸드폰번호
  private String phone;
  // 이메일
  private String email;
  // 탈퇴여부
  private String deleteYn;
  // 가입일자
  private String insertTime;
  // 회원정보수정 일자
  private String updateTime;
  // 회원 탈퇴 일자
  private String deleteTime;

  // 계정이 가지고 있는 권한 목록을 리턴
  private Collection<? extends GrantedAuthority> authorities;

  // 유저정보 빌드 패턴
  public static UserDetailsImpl build(User user) {
    List<GrantedAuthority> authorities = user.getRoles().stream()
        .map(role -> new SimpleGrantedAuthority(role))
        .collect(Collectors.toList());

    return new UserDetailsImpl(
        user.getId(), 
        user.getUsername(),
        user.getPassword(),
        user.getName(),
        user.getAddress(),
        user.getPhone(),
        user.getEmail(),
        user.getDeleteYn(),
        user.getInsertTime(),
        user.getUpdateTime(),
        user.getDeleteTime(),
        authorities);
  }

  // 계정이 가지고 있는 권한 목록을 리턴
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  public Long getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    UserDetailsImpl user = (UserDetailsImpl) o;
    return Objects.equals(id, user.id);
  }
}
