package com.example.backend.dao;

import com.example.backend.model.SendEmailDto;
import com.example.backend.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * packageName : com.example.backend.dao
 * fileName : SendEmailDao
 * author : gumin
 * date : 2022-07-07
 * description : 유저 이메일 인증을 위한 DAO
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-07-07         gumin          최초 생성
 */
@Mapper
public interface SendEmailDao {
    // 유저 아이디 찾기
    User findID(SendEmailDto sendEmailDto);
    // 유저 패스워드 찾기
    User findPassword(SendEmailDto sendEmailDto);
    // 유저 패스워드 찾기 이메일 인증 성공 시 비밀번호 변경
    int changePassword(User user);
}
