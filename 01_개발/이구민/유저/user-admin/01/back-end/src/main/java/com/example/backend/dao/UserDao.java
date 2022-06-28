package com.example.backend.dao;

import com.example.backend.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

/**
 * packageName : com.example.taegyungsi.dao
 * fileName : UserDao
 * author : kangtaegyung
 * date : 2022/05/23
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/05/23         kangtaegyung          최초 생성
 */
@Mapper
public interface UserDao {
    
    // UserName로 찾기
    Optional<User> findByUserName(String username);

    // 존재하는 유저 UserName 찾기
    int existsByUserName(String username);

    // 존재하는 Email 찾기
    int existsByEmail(String email);

    // 회원가입
    int insertUser(User user);
}
