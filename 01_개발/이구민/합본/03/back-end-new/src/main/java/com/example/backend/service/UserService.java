package com.example.backend.service;

import com.example.backend.model.User;

import java.util.Optional;

/**
 * packageName : com.example.taegyungsi.service
 * fileName : UserService
 * author : kangtaegyung
 * date : 2022/05/23
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/05/23         kangtaegyung          최초 생성
 */
public interface UserService {
    // username으로 유저 찾기
    Optional<User> findByUserName(String username);

    // 이미 존재하는 username인지 확인
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
