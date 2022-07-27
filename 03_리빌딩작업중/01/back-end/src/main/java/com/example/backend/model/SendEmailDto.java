package com.example.backend.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * packageName : com.example.backend.model
 * fileName : SendToMeDto
 * author : gumin
 * date : 2022-07-07
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-07-07         gumin          최초 생성
 */
@Getter
@Setter
@ToString
public class SendEmailDto {

    // 유저 이메일
    private String email;

    // 유저 이름
    private String name;

    // 이메일에 동봉될 코드
    private String code;

    // 유저 로그인용 id
    private String username;

}
