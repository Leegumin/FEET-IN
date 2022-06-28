package com.example.dongsungsi.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@ToString
public class User {

    //  유저 아이디 넘버
    private Long id;

    // *User로 들어오는 데이터 유효성 검사돌림
    // *@NotBlank : 유효성 검사, 공백이 아니어야함
    // *@Size(max = 50, min = 10) : 유효성 검사, 최대 50자, 최소 10자
    //  사용자 아이디
    @NotBlank
    @Size(max = 20)
    private String username;

    //  비밀번호
    @NotBlank
    @Size(max = 120)
    private String password;

    // 이름
    @NotBlank
    @Size(max = 50)
    private String name;

    // 생일
    @Size(max = 50)
    private String birth;

    //  이메일
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    // 주소
    @NotBlank
    @Size(max = 120)
    private String address;

    // 핸드폰번호
    @NotBlank
    @Size(max = 20)
    private String phone;

    // 탈퇴여부
    private String deleteYn;

    // 가입일자
    private String insertTime;

    // 회원정보수정 일자
    private String updateTime;

    // 회원 탈퇴 일자
    private String deleteTime;

    //  todo : role column 추가 할것
    //  todo : role 변수 추가 할것 테스트
    //  권한 ( ROLE_USER, ROLE_ADMIN )
    //  DB 역할 컬럼
    private String role;

    //  SpringSecuriry 역활
    //  private Set<Role> roles = new HashSet<>();
    private Set<String> roles = new HashSet<>();

    public User(String username, String email, String password, String name, String birth, String address, String phone,
                String deleteYn, String insertTime, String updateTime, String deleteTime) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.birth = birth;
        this.address = address;
        this.phone = phone;
        this.deleteYn = deleteYn;
        this.insertTime = insertTime;
        this.updateTime = updateTime;
        this.deleteTime = deleteTime;
    }
}
