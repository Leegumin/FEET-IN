package com.example.backend.service;

import com.example.backend.model.User;

import java.util.Optional;

/**
 * packageName : com.example.backend.dao
 * fileName : UserDao
 * author : gumin
 * date : 2022/06/23
 * description : 유저 서비스 인터페이스
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/06/23         gumin          최초 생성
 */
public interface UserService {
    // username(유저 로그인 아이디)으로 유저 찾기
    Optional<User> findByUserName(String username);

    // 이미 존재하는 username(유저 로그인 아이디)인지 확인
    Boolean existsByUserName(String username);

    // 이미 존재하는 이메일인지 확인
    Boolean existsByEmail(String email);

    // 회원가입
    Boolean save(User user);

    // id로 회원탈퇴. delete(update)
    boolean deleteUser(Long id);

    // 회원수정 : update
    int updateUser(User user);
}
