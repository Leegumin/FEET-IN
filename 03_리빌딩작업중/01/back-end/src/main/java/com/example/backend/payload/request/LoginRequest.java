package com.example.backend.payload.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

// !로그인을 처리하기 위한 정보를 받는 객체
@Setter
@Getter
@ToString
public class LoginRequest {
    @NotBlank
    // 유저 로그인용 id
    private String username;
    @NotBlank
    // 유저 패스워드
    private String password;

}
