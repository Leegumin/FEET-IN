package com.example.backend.service;

import com.example.backend.model.SendEmailDto;
import com.example.backend.model.User;

/**
 * packageName : com.example.backend.service
 * fileName : SendEmail
 * author : gumin
 * date : 2022-07-07
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-07-07         gumin          최초 생성
 */
public interface SendEmailService {
    String createKey();

    User findID (SendEmailDto sendEmailDto);
    User findPassword (SendEmailDto sendEmailDto);

    int changePassword(User user);
}
