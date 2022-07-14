package com.example.backend.security.services;

import com.example.backend.dao.UserDao;
import com.example.backend.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  UserDao userDao;
Logger logger = LoggerFactory.getLogger(getClass());
  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    logger.info("인증 실행중");
    User user = userDao.findByUserName(username)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
//  응답 포함 JWT 및 UwerDetails 데이터
//    Role role = new Role();
//    role.setName(user.getRole());
//
    Set<String> roles = new HashSet<>();

//    테스트
//    roles.add(role);
    roles.add(user.getRole());
    user.setRoles(roles);

    return UserDetailsImpl.build(user);
  }

}
