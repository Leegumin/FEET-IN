package com.example.backend.service;

import com.example.backend.dao.UserDao;
import com.example.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * packageName : com.example.backend.service
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
        return userDao.findByUserName(username);
    }

    /*// 회원 생성 혹은 수정
    public int CreateUpdateUser(User user) {
        // User(model User임) 객체가 null이면 insert(유저생성)
        if (userDao.findByUserName(user.getUsername()) == null) {
            return userDao.insertUser(user);
        }
        // 아니면 update(유저정보 수정)을 반환
        else if (userDao.findByUserName(user.getUsername()) != null) {
            return userDao.updateUser(user);
        }
        // *보통 에러가 날때 -1, 정상처리됐으나 아무 효과가 없을때 0 값을 리턴해주는데 개발자 마음임
        // *create와 update를 같이 사용시 -1을 리턴 못해줘서 이미 존재하는 회원인지 확인 불가능
        else {
            return -1;
        }
    }*/

    // 회원 생성
    public int CreateUser(User user) {
        // User(model User임) 객체가 null이면 insert(유저생성)
        if (userDao.findByUserName(user.getUsername()) == null) {
            return userDao.insertUser(user);
        }
        // *보통 에러가 날때 -1, 정상처리됐으나 아무 효과가 없을때 0 값을 리턴해주는데 개발자 마음임
        else {
            return -1;
        }
    }

    // 회원 수정
    public int UpdateUser(User user) {
        // User(model User임) 객체가 null이면 insert(유저생성)
        if (userDao.findByUserName(user.getUsername()) != null) {
            return userDao.updateUser(user);
        }
        // *보통 에러가 날때 -1, 정상처리됐으나 아무 효과가 없을때 0 값을 리턴해주는데 개발자 마음임
        else {
            return -1;
        }
    }

    // 회원 탈퇴
    public boolean deleteById(String id) {
        User result = null;
        result = userDao.findById(id);

        return result != null;
    }
}
