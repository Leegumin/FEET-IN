package com.example.taegyungsi.service;

import com.example.taegyungsi.dao.UserDao;
import com.example.taegyungsi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * packageName : com.example.taegyungsi.service
 * fileName : CustomUserDetailService
 * author : gumin
 * date : 2022-06-20
 * description : UserDetails(User) : 유저정보를 얻기 위한 서비스(id, 이름, 패스워드 등)
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-20         gumin          최초 생성
 */
// UserDetailsService : SpringSecurity에서 기본적으로 제공해주는 서비스
@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    UserDao userDao;

    // id로 검색하는 메소드로 바꿈
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        return userDao.findById(id);
    }

    // username로 검색하는 메소드
    public UserDetails findByName(String username) {
        return userDao.findByName(username);
    }

    // User(model User임) 객체가 null이면 insert(유저생성)하고, 아니면 -1을 반환
    public int signInUser(User user) {
        if (userDao.findByName(user.getUsername()) == null) {
            return userDao.insertUser(user);
        }
        // *보통 에러가 날때 -1, 정상처리됐으나 아무 효과가 없을때 0 값을 리턴해주는데 개발자 마음임
        else {
            return -1;
        }
    }
}
