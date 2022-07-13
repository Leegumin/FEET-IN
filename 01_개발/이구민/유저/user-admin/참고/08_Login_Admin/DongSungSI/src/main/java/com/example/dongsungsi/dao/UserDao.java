package com.example.dongsungsi.dao;

import com.example.dongsungsi.model.User;
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
    Optional<User> findByUsername(String username);

    int existsByUsername(String username);

    int existsByEmail(String email);

    int insertUser(User user);
}
