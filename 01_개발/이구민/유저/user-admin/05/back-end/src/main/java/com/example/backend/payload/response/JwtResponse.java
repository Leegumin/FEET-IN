package com.example.backend.payload.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class JwtResponse {
    // 웹 토큰
    private String       token;
    // JWT를 보낼 때 사용할 타입
    private String       type = "Bearer";
    private Long         id; // 시퀀스 번호, 기본키, 고유식별값
    private String       username; // 유저 id
    private String       name; // 이름
    private String       address; // 주소
    private String       email; // 이메일
    private List<String> roles; // 권한 ( ROLE_USER, ROLE_ADMIN )
    /*private String       birth; // 생일*/
    private String       phone; // 핸드폰번호
    private String       deleteYn; // 탈퇴여부
    private String       insertTime; // 가입일자
    private String       updateTime; // 회원정보수정 일자
    private String       deleteTime; // 회원 탈퇴 일자


    //  private String       type = "Bearer"; 제외
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
