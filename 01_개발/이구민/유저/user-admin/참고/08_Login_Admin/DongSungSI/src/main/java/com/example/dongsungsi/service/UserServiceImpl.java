package com.example.dongsungsi.service;


import com.example.dongsungsi.dao.UserDao;
import com.example.dongsungsi.model.User;
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

    @Override
    public Optional<User> findByUserName(String username) {
        Optional<User> resUser = userDao.findByUserName(username);

        return resUser;
    }

    @Override
    public Boolean existsByUserName(String username) {
        int queryResult = 0;

        queryResult = userDao.existsByUserName(username);

        return (queryResult >= 1) ? true : false;
    }

    @Override
    public Boolean existsByEmail(String email) {

        int queryResult = 0;

        queryResult = userDao.existsByEmail(email);

        return (queryResult >= 1) ? true : false;
    }

    @Override
    public Boolean save(User user) {
        int queryResult = 0;
        logger.info("save user : {}", user);
        queryResult = userDao.insertUser(user);

        return (queryResult >= 1) ? true : false;
    }
}