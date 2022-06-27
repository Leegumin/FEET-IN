package com.example.dongsungsi.service;

import com.example.dongsungsi.model.User;

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
    Optional<User> findByUserName(String username);

    Boolean existsByUserName(String username);

    Boolean existsByEmail(String email);

    Boolean save(User user);
}
