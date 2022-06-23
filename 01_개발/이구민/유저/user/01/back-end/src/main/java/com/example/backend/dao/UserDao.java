package com.example.backend.dao;

import com.example.backend.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * packageName : com.example.taegyungsi.dao
 * fileName : UserDao
 * author : gumin
 * date : 2022-06-17
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-17         gumin          최초 생성
 */
@Mapper
public interface UserDao {
    // id로 유저 가져오기 메소드
    User findById(String id);

    // username으로 유저 가져오기 메소드
    User findByUserName(String username);

    // 회원가입 : insert
    int insertUser(User user);

    // id로 회원탈퇴. delete(update)
    int deleteUser(String id);

    // 회원수정 : update
    int updateUser(User user);
}
