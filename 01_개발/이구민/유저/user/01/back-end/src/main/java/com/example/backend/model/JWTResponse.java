package com.example.backend.model;

import lombok.Data;

import java.util.List;

/**
 * packageName : com.example.taegyungsi.model
 * fileName : JWTResponse
 * author : gumin
 * date : 2022-06-17
 * description : 클라이언트로 응답을 보낼 객체( 웹 토큰 포함 )
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-17         gumin          최초 생성
 */
@Data
public class JWTResponse {

    // 웹 토큰
    private String       token;
    private String       type = "Bearer"; // JWT를 보낼 때 사용할 타입
    private String       id; // 시퀀스 번호, 기본키, 고유식별값
    private String       username; // 유저 id
    private String       password; // 유저 비밀번호
    private String       name; // 이름
    private String       birth; // 생일
    private String       email; // 이메일
    private String       address; // 주소
    private String       phone; // 핸드폰번호
    private List<String> roles; // 권한 ( ROLE_USER, ROLE_ADMIN )
    private String       deleteYn; // 탈퇴여부
    private String       insertTime; // 가입일자
    private String       updateTime; // 회원정보수정 일자
    private String       deleteTime; // 회원 탈퇴 일자

    // type을 제외한 모든 매개변수가 있는 생성자 정의
    public JWTResponse(String token, String id, String username, String password, String name, String birth,
                       String email, String address, String phone, List<String> roles, String deleteYn,
                       String insertTime, String updateTime, String deleteTime) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.birth = birth;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.roles = roles;
        this.deleteYn = deleteYn;
        this.insertTime = insertTime;
        this.updateTime = updateTime;
        this.deleteTime = deleteTime;
    }
}
