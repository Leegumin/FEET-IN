package com.example.backend.payload.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

// !클라이언트에 유저의 토큰을 넘져주기 위한 객체
@Setter
@Getter
@ToString
public class JwtResponse {
    // 웹 토큰
    private String       token;
    // JWT를 보낼 때 사용할 타입
    private String       type = "Bearer";
    // 시퀀스 번호, 기본키, 고유식별값
    private Long         id;
    // 유저 로그인 아이디
    private String       username;
    // 이름
    private String       name;
    // 주소
    private String       address;
    // 이메일
    private String       email;
    // 권한 ( ROLE_USER, ROLE_ADMIN )
    private List<String> roles;
    // 핸드폰번호
    private String       phone;
    // 탈퇴여부
    private String       deleteYn;
    // 가입일자
    private String       insertTime;
    // 회원정보수정 일자
    private String       updateTime;
    // 회원 탈퇴 일자
    private String       deleteTime;


    //  *private String       type = "Bearer"; 제외한 생성자
    // *AuthController -> authenticateUser Jwt 토큰에 정보를 담아 발행할 때 사용되는 생성자
    public JwtResponse(String token, Long id, String username, String name, String address, String email,
                       List<String> roles, String phone, String deleteYn, String insertTime,
                       String updateTime, String deleteTime) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.name = name;
        this.address = address;
        this.email = email;
        this.roles = roles;
        this.phone = phone;
        this.deleteYn = deleteYn;
        this.insertTime = insertTime;
        this.updateTime = updateTime;
        this.deleteTime = deleteTime;
    }
}
