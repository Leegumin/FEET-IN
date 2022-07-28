package com.example.backend.service;

import com.example.backend.model.SendEmailDto;
import com.example.backend.model.User;

/**
 * packageName : com.example.backend.service
 * fileName : SendEmail
 * author : gumin
 * date : 2022-07-07
 * description : 유저 이메일 인증을 위한 인터페이스 정의
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-07-07         gumin          최초 생성
 */
public interface SendEmailService {
    // 이메일에 동봉될 랜덤인증코드 생성
    String createKey();
    // 아이디 찾기
    User findID (SendEmailDto sendEmailDto);
    // 비밀번호 찾기
    User findPassword (SendEmailDto sendEmailDto);

    // 비밀번호 찾기 인증 완료 시 비밀번호 변경
    int changePassword(User user);
}
