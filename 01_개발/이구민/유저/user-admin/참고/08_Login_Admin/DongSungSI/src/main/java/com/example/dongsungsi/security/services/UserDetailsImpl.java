package com.example.dongsungsi.security.services;

import com.example.dongsungsi.model.User;
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

@Getter
@Setter
@ToString
// @AllArgsConstructor : UserDetailsImp ( 모든 생성자 )
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {
  private static final long serialVersionUID = 1L;

  private Long id;

  private String username;
  @JsonIgnore
  private String password;
  private String name; // 이름
  private String address; // 주소
  private String phone; // 핸드폰번호
  private String email;
  private String birth; // 생일
  private String deleteYn; // 탈퇴여부
  private String insertTime; // 가입일자
  private String updateTime; // 회원정보수정 일자
  private String deleteTime; // 회원 탈퇴 일자

  private Collection<? extends GrantedAuthority> authorities;

  /*public UserDetailsImpl(Long id, String username, String password, String name, String address, String phone,
                         String email, String birth, String deleteYn, String insertTime, String updateTime,
                         String deleteTime, Collection<? extends GrantedAuthority> authorities) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.name = name;
    this.address = address;
    this.phone = phone;
    this.email = email;
    this.birth = birth;
    this.deleteYn = deleteYn;
    this.insertTime = insertTime;
    this.updateTime = updateTime;
    this.deleteTime = deleteTime;
    this.authorities = authorities;
  }*/

  public static UserDetailsImpl build(User user) {
    List<GrantedAuthority> authorities = user.getRoles().stream()
//        .map(role -> new SimpleGrantedAuthority(role.getName().name()))
//            Set<String>
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
        user.getBirth(),
        user.getDeleteYn(),
        user.getInsertTime(),
        user.getUpdateTime(),
        user.getDeleteTime(),
        authorities);
  }

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
