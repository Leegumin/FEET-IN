package com.example.taegyungsi.dao;

import com.example.taegyungsi.model.User;
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

    // name으로 유저 가져오기 메소드
    User findByName(String name);

    // insert : 회원정보 (user)
    int insertUser(User user);

    // delete : 회원정보 (user)
    int deleteUser(String name);
}
