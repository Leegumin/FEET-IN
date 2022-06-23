package com.example.taegyungsi.model;

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
    private String       type = "Bearer"; // *JWT를 보낼 때 사용할 타입
    private String       id;
    private String       username;
    private String       email;
    private List<String> roles; // *역할 ( ROLE_ADMIN, ROLE_USER 등등)

    // type를 제외한 모든 매개변수가 있는 생성자 정의
    public JWTResponse(String token, String id, String username, String email, List<String> roles) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}
