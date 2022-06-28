package com.example.backend.service;


import com.example.backend.dao.UserDao;
import com.example.backend.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * packageName : com.example.taegyungsi.service
 * fileName : UserServiceImpl
 * author : kangtaegyung
 * date : 2022/05/23
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/05/23         kangtaegyung          최초 생성
 */
@Service
public class UserServiceImpl implements UserService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserDao userDao;

    // username으로 유저 찾기
    // *null 값이 안들어오게 Optional 사용
    @Override
    public Optional<User> findByUserName(String username) {
        Optional<User> resUser = userDao.findByUserName(username);

        return resUser;
    }

    // username이 존재하는지 확인
    @Override
    public Boolean existsByUserName(String username) {
        int queryResult = 0;

        queryResult = userDao.existsByUserName(username);

        return (queryResult >= 1) ? true : false;
    }

    // email이 존재하는지 확인
    @Override
    public Boolean existsByEmail(String email) {

        int queryResult = 0;

        queryResult = userDao.existsByEmail(email);

        return (queryResult >= 1) ? true : false;
    }

    // 회원가입
    @Override
    public Boolean save(User user) {
        int queryResult = 0;
        logger.info("save user : {}", user);
        queryResult = userDao.insertUser(user);

        return (queryResult >= 1) ? true : false;
    }

    // 회원 삭제
    @Override
    public boolean deleteUser(Long id) {
        int result = 0;
        result = userDao.deleteUser(id);

        return result >= 1;
    }

    // 회원 수정
    @Override
    public int updateUser(User user) {
        // User(model User임) 객체가 있으면(아이디가 존재하면) update(유저수정)
        if (userDao.findByUserName(user.getUsername()).isPresent()) {
            return userDao.updateUser(user);
        }
        // *보통 에러가 날때 -1, 정상처리됐으나 아무 효과가 없을때 0 값을 리턴해주는데 개발자 마음임
        else {
            return -1;
        }
    }
}