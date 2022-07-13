package com.example.dongsungsi.security.services;

import com.example.dongsungsi.dao.UserDao;
import com.example.dongsungsi.model.ERole;
import com.example.dongsungsi.model.Role;
import com.example.dongsungsi.model.User;
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

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userDao.findByUsername(username)
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
